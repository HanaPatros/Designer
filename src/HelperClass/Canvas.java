package HelperClass;


import DAO.DaCoördinaatJPA;
import Model.Coördinaat;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
import Model.Floor;


public final class Canvas extends JComponent {

    DaCoördinaatJPA jpa = new DaCoördinaatJPA();
    private int X1, Y1, X2, Y2;
    private int x, y, width, height;
    private Graphics2D g;
    private Image img = null;
    private Image background;
    private Rectangle shape;
    private Point startPoint;
    private MouseMotionListener motion;
    private MouseListener listener;
    private String svg;
    private Coördinaat cd;
    private List<Coördinaat> coördinaten;
    private List<String> lst;
    private String cmbDeskId;
    private int XEdit, YEdit, WidthEdit, HeightEdit;
    private String cmbDeskIdEdit;
    private Long IdEdit;
    private Floor floor;
    private Graphics2D g2d = null;

    public void save(File file) {
        try {
            ImageIO.write((RenderedImage) img, "PNG", file);

        } catch (IOException ex) {
        }
    }

    @Override
    protected void paintComponent(Graphics g1) {

        if (img == null) {
            img = createImage(getSize().width, getSize().height);
            g = (Graphics2D) img.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            clear();
            svgOpladen();

        }
        g1.drawImage(img, 0, 0, null);
        drawGrid();

    }

    public void svgOpladen() {
        if (shape != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.draw(shape);

        }

        coördinaten = new ArrayList<>();

        coördinaten = jpa.getAllDesks();

        if (coördinaten != null) {
            for (Coördinaat cd : coördinaten) {

                g2d = (Graphics2D) img.getGraphics();
                g2d.setColor(Color.orange);
                g2d.fill3DRect(cd.getX1(), cd.getY1(), cd.getWidth1(), cd.getHeight1(), true);

            }
        }
        repaint();
    }

    public Canvas() {
        setBackground(Color.WHITE);
        cd = new Coördinaat();
        lst = new ArrayList();
        defaultListener();
        getData();
        floor = new Floor();
        floor.setSVG(lst.toString());
    }

    public void drawGrid() {
        int xZ = 1;
        int yZ = 1;
        int size = 10;
        int teller = 0;

        for (int i = 0; i < 1500; i++) {
            for (int j = 0; j < 1500; j++) {
                teller = teller + 1;
                g.drawRect(xZ, yZ, size, size);
                yZ += size;
            }
            xZ += size;
            yZ = 1;
        }

    }

    public void defaultListener() {
        setDoubleBuffered(false);
        listener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                X2 = e.getX();
                Y2 = e.getY();
            }
        };

        motion = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                X1 = e.getX();
                Y1 = e.getY();

                if (g != null) {
                    g.drawLine(X2, Y2, X1, Y1);
                    repaint();
                    X2 = X1;
                    Y2 = Y1;
                }
            }
        };
        addMouseListener(listener);
        addMouseMotionListener(motion);
    }

    public void addRectangle(Rectangle rectangle, Color color) {

        Graphics2D g2d = (Graphics2D) img.getGraphics();
        g2d.setColor(color);
        g2d.draw(rectangle);

        repaint();

    }

    public void clear() {
        int yZ = 1;
        int size = 10;
        int teller = 0;
        if (background != null) {
            setImage(copyImage(background));
            int xZ = 1;

            drawGrid();
        } else {
            g.setPaint(Color.white);
            g.fillRect(0, 0, getSize().width, getSize().height);
            g.setPaint(Color.ORANGE);
            int xZ = 1;

            drawGrid();
        }
        repaint();
    }

    public void pencil() {
        removeMouseListener(listener);
        removeMouseMotionListener(motion);
        defaultListener();

    }

    public void rect() {
        removeMouseListener(listener);
        removeMouseMotionListener(motion);
        MyMouseListener ml = new MyMouseListener();
        addMouseListener(ml);
        addMouseMotionListener(ml);

    }

    private void setImage(Image img) {
        g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setPaint(Color.black);
        this.img = img;
        repaint();
    }

    public void setBackground(Image img) {
        background = copyImage(img);
        setImage(copyImage(img));
    }

    private BufferedImage copyImage(Image img) {
        BufferedImage copyOfImage = new BufferedImage(getSize().width,
                getSize().height, BufferedImage.TYPE_INT_RGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        return copyOfImage;
    }

    class MyMouseListener extends MouseInputAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            startPoint = e.getPoint();
            shape = new Rectangle();

        }

        @Override
        public void mouseDragged(MouseEvent e) {

            x = Math.min(startPoint.x, e.getX());

            y = Math.min(startPoint.y, e.getY());

            width = Math.abs(startPoint.x - e.getX());

            height = Math.abs(startPoint.y - e.getY());

            repaint();
            Graphics2D g2d = (Graphics2D) img.getGraphics();

            g2d.setColor(Color.orange);

            g2d.fill3DRect(x, y, width, height, true);

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (shape.width != 0 || shape.height != 0) {
                addRectangle(shape, e.getComponent().getForeground());
               // svgOpladen();
                
                
            }
            shape = null;
            repaint();
            saveOnMouseRelease();

        }
    }

    // voedt databank en haalt de gegevens van mousdragactionlistener
    public void saveOnMouseRelease() {

        cd.setX1(x);
        cd.setY1(y);
        cd.setWidth1(width);
        cd.setHeight1(height);
        cd.setDeskId(getCmbDeskId());

        try {

            jpa.save(cd);

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Attention: Desk is already in use!",
                    "Error message",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        //verwijderd desk indien reeds dubbel
        getRechthoekenOnStartUp();
    }

    // haalt gegevens op uit de rechthoek die met knop aangemaakt wordt
    public void opslaanMetKnop() {

        cd.setX1(x);
        cd.setY1(y);
        cd.setWidth1(width);
        cd.setHeight1(height);

        try {

            jpa.save(cd);

        } catch (Exception ex) {

            javax.swing.JOptionPane.showMessageDialog(null, "Attention: Desk is already in use!",
                    "Error message",
                    javax.swing.JOptionPane.ERROR_MESSAGE);

        }
    }

    // haalt alle gegevens uit tabel coordinaat op
    public void getData() {

        coördinaten = new ArrayList<>();
        List<Coördinaat> coordinaten = jpa.getAllDesks();
        svg = "<svg>";
        for (Coördinaat cd : coordinaten) {
            String openAttr = "<rect id=";
            String closeAttr = "/>";
            String acc = "'";
            String w = " width=";
            String h = " height=";
            String xx = " x=";
            String yy = " y=";

            svg += openAttr + acc + cd.getDeskId() + acc + w + acc + cd.getWidth1() + acc + h + acc
                    + cd.getHeight1() + acc + xx + acc + cd.getX1() + acc + yy + acc + cd.getY1() + acc + closeAttr;
        }
        svg += "</svg>";
        lst.add(svg);

    }

    public void getRechthoekenOnStartUp() {
        clear();

        coördinaten = new ArrayList<>();

        coördinaten = jpa.getAllDesks();
        if (coördinaten.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Attention: No desks available in database!",
                    "Error message",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        } else if (coördinaten != null) {
            for (Coördinaat cd : coördinaten) {

                g2d = (Graphics2D) img.getGraphics();
                g2d.setColor(Color.orange);
                g2d.fill3DRect(cd.getX1(), cd.getY1(), cd.getWidth1(), cd.getHeight1(), true);
                repaint();
            }
        }

    }

    public void verwijderen(String deskId) {

        jpa.removeById(deskId);
        clear();
        coördinaten = new ArrayList<>();
        List<Coördinaat> coordinaten = jpa.getAllDesks();
        for (Coördinaat cd : coordinaten) {
            setRechthoeken(cd.getX1(), cd.getY1(), cd.getWidth1(), cd.getHeight1());
        }

    }

    public void getDeskById(String deskId) {

        Coördinaat coord = jpa.getDeskById(deskId);

        IdEdit = coord.getId();
        cmbDeskIdEdit = coord.getDeskId();
        XEdit = coord.getX1();
        YEdit = coord.getY1();
        WidthEdit = coord.getWidth1();
        HeightEdit = coord.getHeight1();

    }

    public void editDesk() {

        cd.setX1(XEdit);
        cd.setY1(YEdit);
        cd.setWidth1(WidthEdit);
        cd.setHeight1(HeightEdit);
        cd.setDeskId(getCmbDeskId());

        try {
            jpa.edit(cd);

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Attention: Desk is already in use!",
                    "Error message",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        } finally {

            getRechthoekenOnStartUp();
        }

    }

    public List<String> getLst() {
        return lst;
    }

    // constructor om rechthoek aan te maken hier zitten de desks ook   
    public void setRechthoek(int x, int y, int width, int height, String deskId) {

        try {

            g2d = (Graphics2D) img.getGraphics();

            g2d.setColor(Color.orange);

            g2d.fill3DRect(x, y, width, height, true);
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            cd.deskId = deskId;

            opslaanMetKnop();
            getRechthoekenOnStartUp();
            repaint();

        } catch (Exception ex) {
            ex.getStackTrace();
        }

    }

    public void setRechthoeken(int x, int y, int width, int height) {

        g2d = (Graphics2D) img.getGraphics();
        g2d.setColor(Color.orange);
        g2d.fill3DRect(x, y, width, height, true);

        repaint();

    }

    public void getDataPreset() {
        Graphics2D g2d = (Graphics2D) img.getGraphics();

        coördinaten = new ArrayList<>();

        List<Coördinaat> coordinaten = jpa.getAllDesks();

        for (Coördinaat cd : coordinaten) {
            g2d.setColor(Color.orange);
            g2d.fillRect(cd.getX1(), cd.getY1(), cd.getWidth1(), cd.getHeight1());
        }

        repaint();
    }
    
    public void getDeskByColor(String deskId) {
        
        getRechthoekenOnStartUp();
        Coördinaat coördi = jpa.getDeskById(deskId);
        g2d = (Graphics2D) img.getGraphics();

        g2d.setColor(Color.red);
        g2d.fillRect(coördi.getX1(), coördi.getY1(), coördi.getWidth1(), coördi.getHeight1());
        
        repaint();
        
    }

    // geeft geselcteerde desk weer
    public String getCmbDeskId() {
        return cmbDeskId;
    }

    public void setCmbDeskId(String cmbDeskId) {
        this.cmbDeskId = cmbDeskId;
    }

    public int getYEdit() {
        return YEdit;
    }

    public void setYEdit(int YEdit) {
        this.YEdit = YEdit;
    }

    public int getWidthEdit() {
        return WidthEdit;
    }

    public void setWidthEdit(int WidthEdit) {
        this.WidthEdit = WidthEdit;
    }

    public int getHeightEdit() {
        return HeightEdit;
    }

    public void setHeightEdit(int HeightEdit) {
        this.HeightEdit = HeightEdit;
    }

    public String getCmbDeskIdEdit() {
        return cmbDeskIdEdit;
    }

    public void setCmbDeskIdEdit(String cmbDeskIdEdit) {
        this.cmbDeskIdEdit = cmbDeskIdEdit;
    }

    public Long getIdEdit() {
        return IdEdit;
    }

    public void setIdEdit(Long IdEdit) {
        this.IdEdit = IdEdit;
    }

    public int getXEdit() {
        return XEdit;
    }

    public void setXEdit(int XEdit) {
        this.XEdit = XEdit;
    }

}
