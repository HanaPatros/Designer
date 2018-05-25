package Helpers;

import DAO.DaCoordinaat;
import DAO.DaCoordinaatJPA;
import Model.Coördinaat;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
import Model.Floor;
import UI.JFrameGebouwen;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public final class Canvas extends JComponent {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesigntoolPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    private int X1, Y1, X2, Y2;
    int x, y, width, height;
    private Graphics2D g;
    Image img = null, undoTemp, redoTemp;
    Image background;
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    private final SizedStack<Image> undoStack = new SizedStack<>(12);
    private final SizedStack<Image> redoStack = new SizedStack<>(12);
    private Rectangle shape;
    private Point startPoint;
    private MouseMotionListener motion;
    private MouseListener listener;
    String svg;
    Coördinaat cd;
    List<Coördinaat> coördinaten;
    List<String> lst;
    String cmbDeskId;
    
    int XEdit, YEdit,WidthEdit, HeightEdit;
    String cmbDeskIdEdit;
    Long IdEdit;
    
    Floor floor;
    //Draw draw1 = new Draw();
    //List<String> svg = new ArrayList<>();
    Graphics2D g2d = null;
    Color color;

    public void save(File file) {
        try {
            ImageIO.write((RenderedImage) img, "PNG", file);

        } catch (IOException ex) {
        }
    }

    public void load(File file) {
        try {
            img = ImageIO.read(file);
            g = (Graphics2D) img.getGraphics();
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

    public void svgOpladen(){
         if (shape != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.draw(shape);

        }
        
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        
        coördinaten = new ArrayList<>();      

        coördinaten = (List<Coördinaat>) em.createQuery("SELECT t FROM Coördinaat t ").getResultList();

        if(coördinaten != null){
        for (Coördinaat cd : coördinaten) {
           
            g2d = (Graphics2D) img.getGraphics();
            g2d.setColor(Color.orange);
            g2d.fill3DRect(cd.getX1(), cd.getY1(), cd.getWidth1(), cd.getHeight1(), true);
//            repaint();
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
    public void drawGrid(){
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
                saveToStack(img);
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

        //g2d.fillRect(X1, Y1,getSize().width, getSize().height);
        repaint();

    }

    public void red() {
        g.setPaint(Color.red);
    }

    public void black() {
        g.setPaint(Color.black);
    }

    public void magenta() {
        g.setPaint(Color.magenta);
    }

    public void green() {
        g.setPaint(Color.green);
    }

    public void blue() {
        g.setPaint(Color.blue);
    }

    public void gray() {
        g.setPaint(Color.GRAY);
    }

    public void orange() {
        g.setPaint(Color.ORANGE);
    }

    public void yellow() {
        g.setPaint(Color.YELLOW);
    }

    public void pink() {
        g.setPaint(Color.PINK);

    }

    public void cyan() {
        g.setPaint(Color.CYAN);
    }

    public void lightGray() {
        g.setPaint(Color.lightGray);
    }

    public void picker(Color color) {
        if (color != null) {
            g.setPaint(color);
        }
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

    public void remove() {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        em.getTransaction().begin();

        try {

            TypedQuery<Coördinaat> query = (TypedQuery<Coördinaat>) em.createQuery("DELETE FROM Coördinaat c ", Coördinaat.class);
            query.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {
        } finally {
            em.close();
        }
    }

    public void undo() {
        if (undoStack.size() > 0) {
            undoTemp = undoStack.pop();
            redoStack.push(img);
            setImage(undoTemp);
        }
    }

    public void redo() {
        if (redoStack.size() > 0) {
            redoTemp = redoStack.pop();
            undoStack.push(img);
            setImage(redoTemp);
        }
    }

    public void pencil() {
        removeMouseListener(listener);
        removeMouseMotionListener(motion);
        //defaultListener();

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

    private void saveToStack(Image img) {
        undoStack.push(copyImage(img));
    }

    public void setThickness(int thick) {
        g.setStroke(new BasicStroke(thick));
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
            //g2d.setColor(kleur(color));
            g2d.fill3DRect(x, y, width, height, true);

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (shape.width != 0 || shape.height != 0) {
                addRectangle(shape, e.getComponent().getForeground());
                svgOpladen();
            }
          
               
                shape = null;
               
                repaint();
                saveOnMouseRelease();
              
           
           
        }
    }

    // voedt databank en haalt de gegevens van mousdragactionlistener
    public void saveOnMouseRelease() {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
            
        }

        cd.setX1(x);
        cd.setY1(y);
        cd.setWidth1(width);
        cd.setHeight1(height);
        cd.setDeskId(getCmbDeskId());
        em.getTransaction().begin();

      
             try {
      
        em.merge(cd);
                 
        em.getTransaction().commit();            

        } catch (Exception ex) {
             javax.swing.JOptionPane.showMessageDialog(null, "Opgelet: Desk is reeds in gebruik!",
                            "                                                        Foutbericht",
                            javax.swing.JOptionPane.ERROR_MESSAGE);     
        } finally {
            em.close();
        }
             //verwijderd desk indien reeds dubbel
             getRechthoekenOnStartUp();
    }
   
    // haalt gegevens op uit de rechthoek die met knop aangemaakt wordt
    public void opslaanMetKnop() {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }

        cd.setX1(x);
        cd.setY1(y);
        cd.setWidth1(width);
        cd.setHeight1(height);
        em.getTransaction().begin();

       try{


        // em.detach(cd);
            em.merge(cd);
            em.getTransaction().commit();
 
        } catch (Exception ex) {
            //em.getTransaction().rollback();
            javax.swing.JOptionPane.showMessageDialog(null, "Opgelet: Desk is reeds in gebruik!",
                            "                                                        Foutbericht",
                            javax.swing.JOptionPane.ERROR_MESSAGE);     
            
        } finally {
            em.close();
        }
    
    }

    // haalt alle gegevens uit tabel coordinaat op
    public void getData() {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }

        coördinaten = new ArrayList<>();
        List<Coördinaat> coordinaten = (List<Coördinaat>) em.createQuery("SELECT t FROM Coördinaat t ").getResultList();
        svg = "<svg>";
        for (Coördinaat cd : coordinaten) {
            String openAttr = "<rect id=";
            String closeAttr =  "/>";
            String acc = "'";
            String w = " width=";
            String h = " height=";
            String xx = " x=";
            String yy = " y=";

            svg += openAttr + acc + cd.getDeskId() + acc + w + acc + cd.getWidth1() + acc + h + acc + cd.getHeight1() + acc + xx + acc + cd.getX1() + acc + yy + acc + cd.getY1() + acc + closeAttr;

        }

        svg += "</svg>";

        lst.add(svg);

        em.close();
    }

    public void getRechthoekenOnStartUp() {
        clear();
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        
        coördinaten = new ArrayList<>();      

        coördinaten = (List<Coördinaat>) em.createQuery("SELECT t FROM Coördinaat t ").getResultList();
        if (coördinaten.isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(null, "Opgelet: Geen desk aanwezig in de databank!",
                            "                                                        Foutbericht",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        else if(coördinaten != null){
        for (Coördinaat cd : coördinaten) {
           
            g2d = (Graphics2D) img.getGraphics();
            g2d.setColor(Color.orange);
            g2d.fill3DRect(cd.getX1(), cd.getY1(), cd.getWidth1(), cd.getHeight1(), true);
            repaint();
        }       
        }
       
    }

    public void verwijderen(String deskId) {

        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        em.getTransaction().begin();

        try {
            Coördinaat coord = em.createQuery("SELECT d FROM Coördinaat d WHERE d.deskId = :deskId  ", Coördinaat.class).setParameter("deskId", deskId).getSingleResult();
            Query query = em.createQuery("DELETE FROM Coördinaat c WHERE c.id = :coord", Coördinaat.class).setParameter("coord", coord.getId());
            query.executeUpdate();
            em.getTransaction().commit();
            clear();
            coördinaten = new ArrayList<>();
            List<Coördinaat> coordinaten = (List<Coördinaat>) em.createQuery("SELECT t FROM Coördinaat t ").getResultList();
            for (Coördinaat cd : coordinaten) {
                setRechthoeken(cd.getX1(), cd.getY1(), cd.getWidth1(), cd.getHeight1());
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        //getData();
        //repaint();
    }
    
    public void edit(String deskId){
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        em.getTransaction().begin();

        try {
            Coördinaat coord = em.createQuery("SELECT d FROM Coördinaat d WHERE d.deskId = :deskId  ", Coördinaat.class).setParameter("deskId", deskId).getSingleResult();
           
             IdEdit =   coord.getId();    
             cmbDeskIdEdit =  coord.getDeskId();
             XEdit = coord.getX1();
             YEdit = coord.getY1();
             WidthEdit = coord.getWidth1();
             HeightEdit = coord.getHeight1();
                            
            } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void oplsaan(){
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
       

        
        cd.setX1(XEdit);
        cd.setY1(YEdit);
        cd.setWidth1(WidthEdit);
        cd.setHeight1(HeightEdit);
        cd.setDeskId(getCmbDeskId());
        em.getTransaction().begin();

      
             try {
      
        em.merge(cd);
                 
        em.getTransaction().commit();            

        } catch (Exception ex) {
             javax.swing.JOptionPane.showMessageDialog(null, "Opgelet: Desk is reeds in gebruik!",
                            "                                                        Foutbericht",
                            javax.swing.JOptionPane.ERROR_MESSAGE);     
        } finally {
            em.close();
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

//     public Color kleur(Color color){
//            g2d = (Graphics2D) img.getGraphics();
//            
//            if (color != null) {
//            g2d.setPaint(color);
//        }
//            return color;
//        }
    public void setRechthoeken(int x, int y, int width, int height) {

        g2d = (Graphics2D) img.getGraphics();
        g2d.setColor(Color.orange);
        g2d.fill3DRect(x, y, width, height, true);

        repaint();

    }

    public void getDataPreset() {
        Graphics2D g2d = (Graphics2D) img.getGraphics();
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }

        coördinaten = new ArrayList<>();
        try {
            List<Coördinaat> coordinaten = (List<Coördinaat>) em.createQuery("SELECT t FROM Coördinaat t ").getResultList();

            for (Coördinaat cd : coordinaten) {
                g2d.setColor(Color.orange);

                g2d.fillRect(cd.getX1(), cd.getY1(), cd.getWidth1(), cd.getHeight1());
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        repaint();
    }

    // geeft geselcteerde desk weer
    public String getCmbDeskId() {
        return cmbDeskId;
    }

    public void setCmbDeskId(String cmbDeskId) {
        this.cmbDeskId = cmbDeskId;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
