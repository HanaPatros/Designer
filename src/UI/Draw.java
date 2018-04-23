package UI;



import Helpers.Canvas;
import Service.JsonService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import Model.Building;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;


public class Draw extends JFrame{
    
    
    DefaultListModel<String> model = new DefaultListModel<>();
    DefaultComboBoxModel model2 = new DefaultComboBoxModel();
    JLabel coordinaten, lblX, lblY, lblHeight, lblWidth;
    JTextField inputX, inputY, inputHeight, inputWidth;
    int test, x, y, width1, height1;
    DefaultListModel listModel = new DefaultListModel();
    Building bld = new Building();
    List<Building> bl = new ArrayList();
    JList list = new JList();
    JList lstDesk= new JList();
    List<String> desk;
    JsonService json;
    Canvas canvas;
    Color color = Color.WHITE;
    JButton clearButton, blackButton, blueButton, greenButton, redButton,
            colorPicker, magentaButton, grayButton, orangeButton, yellowButton,
            pinkButton, cyanButton, lightGrayButton, saveButton, loadButton,
            saveAsButton, rectangle, pencil, undoButton, redoButton, Aanmaken, flexOphalen,
            Gebouwen;
    private JFileChooser fileChooser;
    private File file;
    private Icon save = new ImageIcon(getClass().getResource("/./demo/save.png"));

    private Icon undo = new ImageIcon(getClass().getResource("/./demo/undo.png"));
    private Icon redo = new ImageIcon(getClass().getResource("/./demo/redo.png"));
    private Icon pencilIcon = new ImageIcon(getClass()
            .getResource("/./demo/pencil.png"));
    private Icon rect = new ImageIcon(getClass().getResource("/./demo/rect.png"));
    private int saveCounter = 0;
    private JLabel filenameBar, thicknessStat;
    private JSlider thicknessSlider;
    private int width, height;
    String value;
    JComboBox cmbDesk;
    
    
     public Draw(){
       putDesk();
       
    }

   

     
    
    ChangeListener thick = new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            thicknessStat.setText(String.format("%s",
                    thicknessSlider.getValue()));
            canvas.setThickness(thicknessSlider.getValue());
        }
    };

    ActionListener listener = new ActionListener() {

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == clearButton) {
                canvas.clear();
                 canvas.remove();
            } else if (event.getSource() == blackButton) {
                canvas.black();
            } else if (event.getSource() == blueButton) {
                canvas.blue();
            } else if (event.getSource() == greenButton) {
                canvas.green();
            } else if (event.getSource() == redButton) {
                canvas.red();
            } else if (event.getSource() == magentaButton) {
                canvas.magenta();
            } else if (event.getSource() == grayButton) {
                canvas.gray();
            } else if (event.getSource() == orangeButton) {
                canvas.orange();
            } else if (event.getSource() == yellowButton) {
                canvas.yellow();
            } else if (event.getSource() == pinkButton) {
                canvas.pink();
            } else if (event.getSource() == cyanButton) {
                canvas.cyan();
            } else if (event.getSource() == lightGrayButton) {
                canvas.lightGray();
            } else if (event.getSource() == undoButton) {
                canvas.undo();
            } else if (event.getSource() == redoButton) {
                canvas.redo();
            } else if (event.getSource() == saveButton) {
                if (saveCounter == 0) {
                    fileChooser = new JFileChooser();
                    if (fileChooser.showSaveDialog(saveButton) == JFileChooser.APPROVE_OPTION) {
                        file = fileChooser.getSelectedFile();
                        saveCounter = 1;
                        filenameBar.setText(file.toString());
                        canvas.save(file);
                    }
                } else {
                    filenameBar.setText(file.toString());
                    canvas.save(file);
                }
            } else if (event.getSource() == saveAsButton) {
                saveCounter = 1;
                fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(saveAsButton) == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    filenameBar.setText(file.toString());
                    canvas.save(file);
                }
            } else if (event.getSource() == loadButton) {
                fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(loadButton) == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    filenameBar.setText(file.toString());
                    canvas.load(file);
                }
            } else if (event.getSource() == colorPicker) {
                color = JColorChooser.showDialog(null, "Pick your color!",
                        color);
                if (color == null) {
                    color = (Color.WHITE);
                }
                canvas.picker(color);
            } else if (event.getSource() == rectangle) {
                canvas.rect();

            } else if (event.getSource() == pencil) {
                canvas.pencil();

            } else if (event.getSource() == Aanmaken) {
                int x = Integer.parseInt(inputX.getText());
                int y = Integer.parseInt(inputY.getText());
                int width1 = Integer.parseInt(inputWidth.getText());
                int height1 = Integer.parseInt(inputHeight.getText());
                //value = lstDesk.getSelectedValue().toString();     
                value = cmbDesk.getSelectedItem().toString();

                canvas.setRechthoek(x, y, width1, height1, value);
                clearInput();
                
            } else if(event.getSource() == cmbDesk){
                //value = lstDesk.getSelectedValue().toString(); 
                value = cmbDesk.getSelectedItem().toString();
                canvas.setGegeven(value);
              
            }else if(event.getSource() == Gebouwen){
                try {
                    JFrameGebouwen jf= new JFrameGebouwen();
                    jf.setVisible(true);
                   // jf.enable(true);
                } catch (IOException ex) {
                    Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
            }
        
        }
    };

    public void setWH(int width, int height) {
        this.width = width;
        this.height = height;
    }
    

    public void openPaint() throws ClassNotFoundException {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
        }
        
        
        JFrame frame = new JFrame("Paint (" + width + "X" + height + ")");

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        canvas = new Canvas();

        container.add(canvas, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        Box box = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();

        panel1.setLayout(new FlowLayout());

        pencil = new JButton(pencilIcon);
        pencil.setPreferredSize(new Dimension(40, 40));
        pencil.addActionListener(listener);
        rectangle = new JButton(rect);
        rectangle.setPreferredSize(new Dimension(40, 40));
        rectangle.addActionListener(listener);
        thicknessSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 1);
        thicknessSlider.setMajorTickSpacing(25);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPreferredSize(new Dimension(40, 40));
        thicknessSlider.addChangeListener(thick);
        undoButton = new JButton(undo);
        undoButton.setPreferredSize(new Dimension(20, 20));
        undoButton.addActionListener(listener);
        redoButton = new JButton(redo);
        redoButton.setPreferredSize(new Dimension(20, 20));
        redoButton.addActionListener(listener);
        blackButton = new JButton();
        blackButton.setBackground(Color.BLACK);
        blackButton.setPreferredSize(new Dimension(40, 40));
        blackButton.addActionListener(listener);
        blueButton = new JButton();
        blueButton.setBackground(Color.BLUE);
        blueButton.setPreferredSize(new Dimension(40, 40));
        blueButton.addActionListener(listener);
        greenButton = new JButton();
        greenButton.setBackground(Color.GREEN);
        greenButton.setPreferredSize(new Dimension(40, 40));
        greenButton.addActionListener(listener);
        redButton = new JButton();
        redButton.setBackground(Color.RED);
        redButton.setPreferredSize(new Dimension(40, 40));
        redButton.addActionListener(listener);
        magentaButton = new JButton();
        magentaButton.setBackground(Color.MAGENTA);
        magentaButton.setPreferredSize(new Dimension(40, 40));
        magentaButton.addActionListener(listener);
        grayButton = new JButton();
        grayButton.setBackground(Color.GRAY);
        grayButton.setPreferredSize(new Dimension(40, 40));
        grayButton.addActionListener(listener);
        orangeButton = new JButton();
        orangeButton.setBackground(Color.ORANGE);
        orangeButton.setPreferredSize(new Dimension(40, 40));
        orangeButton.addActionListener(listener);
        yellowButton = new JButton();
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.setPreferredSize(new Dimension(40, 40));
        yellowButton.addActionListener(listener);
        pinkButton = new JButton();
        pinkButton.setBackground(Color.PINK);
        pinkButton.setPreferredSize(new Dimension(40, 40));
        pinkButton.addActionListener(listener);
        cyanButton = new JButton();
        cyanButton.setBackground(Color.CYAN);
        cyanButton.setPreferredSize(new Dimension(40, 40));
        cyanButton.addActionListener(listener);
        lightGrayButton = new JButton();
        lightGrayButton.setBackground(Color.LIGHT_GRAY);
        lightGrayButton.setPreferredSize(new Dimension(40, 40));
        lightGrayButton.addActionListener(listener);
        saveButton = new JButton(save);
        saveButton.addActionListener(listener);
        saveAsButton = new JButton("Save As");
        saveAsButton.addActionListener(listener);
        loadButton = new JButton("Load");
        loadButton.addActionListener(listener);
        colorPicker = new JButton("Color Picker");
        colorPicker.addActionListener(listener);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(listener);

        filenameBar = new JLabel("No file");
        thicknessStat = new JLabel("1");
        Aanmaken = new JButton("Aanmaken");
        Aanmaken.addActionListener(listener);
        Aanmaken.setFont(new Font("serif", Font.BOLD, 20));
        Aanmaken.setBackground(Color.DARK_GRAY);
        Aanmaken.setForeground(Color.WHITE);
        this.getRootPane().setDefaultButton(Aanmaken);
//        flexOphalen = new JButton("Desk ophalen");
//        flexOphalen.setBackground(Color.DARK_GRAY);
//        flexOphalen.setForeground(Color.GRAY);
//        flexOphalen.setFont(new Font("serif", Font.BOLD, 20));
//        flexOphalen.addActionListener(listener);
        
        Gebouwen = new JButton("Gebouwen");
        Gebouwen.addActionListener(listener);
        

        box.add(Box.createVerticalStrut(40));
        box1.add(thicknessSlider, BorderLayout.NORTH);
        box1.add(thicknessStat, BorderLayout.NORTH);
        box.add(box1, BorderLayout.NORTH);
        panel1.add(filenameBar, BorderLayout.SOUTH);
        box.add(Box.createVerticalStrut(20));
        box.add(undoButton, BorderLayout.NORTH);
        box.add(Box.createVerticalStrut(5));
        box.add(redoButton, BorderLayout.NORTH);
        box.add(Box.createVerticalStrut(5));
        box.add(pencil, BorderLayout.NORTH);
        
        

        panel.add(greenButton);
        panel.add(blueButton);
        panel.add(blackButton);
        panel.add(redButton);
        panel.add(magentaButton);
        panel.add(grayButton);
        panel.add(orangeButton);
        panel.add(yellowButton);
        panel.add(pinkButton);
        panel.add(cyanButton);
        panel.add(lightGrayButton);
        panel.add(saveButton);
        panel.add(saveAsButton);
        panel.add(loadButton);
        panel.add(colorPicker);
        panel.add(clearButton);
        //panel.add(Aanmaken);
        panel.add(Gebouwen);
        panel.setBackground(Color.DARK_GRAY);

        coordinaten = new JLabel();
        lblX = new JLabel("X: ");
        lblX.setForeground(Color.WHITE);
        lblX.setFont(new Font("Serif", Font.PLAIN, 14));
        inputX = new JTextField();

        lblY = new JLabel("Y: ");
        lblY.setForeground(Color.WHITE);
        lblY.setFont(new Font("Serif", Font.PLAIN, 14));
        inputY = new JTextField();

        lblWidth = new JLabel("Width: ");
        lblWidth.setForeground(Color.WHITE);
        lblWidth.setFont(new Font("Serif", Font.PLAIN, 14));
        inputWidth = new JTextField();

        lblHeight = new JLabel("Height: ");
        lblHeight.setForeground(Color.WHITE);
        lblHeight.setFont(new Font("Serif", Font.PLAIN, 14));
        inputHeight = new JTextField();


        //leest list van desks uit
//        for (int i = 0; i < desk.size(); i++) {
//            model.addElement(desk.get(i));
//        }
//        lstDesk.setModel(model);
//        lstDesk.setVisibleRowCount(-1);
//        JScrollPane sp = new JScrollPane(lstDesk);
//        lstDesk.setPreferredSize(new Dimension(150, 200));
        
        
        for (int i = 0; i < desk.size(); i++) {
            model2.addElement(desk.get(i));
        }
        cmbDesk = new JComboBox(model2);
        cmbDesk.setFont(new Font("serif", Font.BOLD, 20));
        cmbDesk.addActionListener(listener);
        box.add(cmbDesk, BorderLayout.NORTH);
       //box.add(lstDesk, BorderLayout.NORTH);
       
       //box.add(flexOphalen, BorderLayout.NORTH);
        box.add(Box.createVerticalStrut(5));
        box.add(rectangle, BorderLayout.WEST);
        box.add(Box.createVerticalStrut(30));
        box.add(Aanmaken, BorderLayout.WEST);
        box.add(coordinaten, BorderLayout.NORTH);
        box.add(lblX, BorderLayout.NORTH);
        box.add(inputX, BorderLayout.NORTH);
        box.add(lblY, BorderLayout.NORTH);
        box.add(inputY, BorderLayout.NORTH);
        box.add(lblWidth, BorderLayout.NORTH);
        box.add(inputWidth, BorderLayout.NORTH);
        box.add(lblHeight, BorderLayout.NORTH);
        box.add(inputHeight, BorderLayout.NORTH);
        
        //box.setBackground(Color.DARK_GRAY);
        container.add(panel, BorderLayout.NORTH);
        container.add(panel1, BorderLayout.SOUTH);
        container.add(box, BorderLayout.WEST);
        container.setFont(new Font("Dialog", Font.BOLD, 18));
        //container.setForeground(Color.DARK_GRAY);

        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setSize(width + 79, height + 11);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        

    }
    
    //constructor voor rechthoek
    public void addRect() {
        int x = Integer.parseInt(inputX.getText());
        int y = Integer.parseInt(inputY.getText());
        int width1 = Integer.parseInt(inputWidth.getText());
        int height1 = Integer.parseInt(inputHeight.getText());
        value = lstDesk.getSelectedValue().toString();

        canvas.setRechthoek(x, y, width1, height1, value);
    }

    public void clearInput(){
        inputX.setText("");
        inputY.setText("");
        inputWidth.setText("");
        inputHeight.setText("");
    }
    
    //maakt desks aan voor lstDesk
    public List<String>putDesk(){
    desk = new ArrayList();
    desk.add("desk1");
    desk.add("desk2");
    desk.add("desk3");
    desk.add("desk4");
    desk.add("desk5");
    desk.add("desk6");
    desk.add("desk7");
    desk.add("desk8");
    return desk;
    
    }
    public List<String> getDesk1() {
        return desk;
    }

    public void setDesk1(List<String> desk) {
        this.desk = desk;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
}
