package UI;

import Helpers.Canvas;
import Helpers.Preset;
import Model.Building;
import Model.Floor;
import Service.JsonService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sa59053
 */
public final class JFrameGebouwen extends javax.swing.JFrame {

    DefaultListModel listModel;
    DefaultListModel listModel2;
    DefaultListModel listModelVerdiep;
    Building gebouw;
    Floor verdieping;
    
    List<Building> gebouwen;
    List<Floor> verdiepingen;
    
    JsonService json;
    Canvas canvas;
    Preset pr;
    private DefaultTableModel model;
    private int row = 0;
    int getConvGebouwRow;
    private Object[] rowData;
    List<Floor> vloeren;
    long gebouwId = 0;    
    Long selectedFloorId;
    String svg;
    InputWH ip ;
    public int width;
    public int height;
    List<String> desk;
    List<String> presets;    
    DefaultComboBoxModel model2 = new DefaultComboBoxModel();
    DefaultComboBoxModel model3 = new DefaultComboBoxModel();
//    JButton clearButton, blackButton, blueButton, greenButton, redButton,
//            colorPicker, magentaButton, grayButton, orangeButton, yellowButton,
//            pinkButton, cyanButton, lightGrayButton, saveButton, loadButton,
//            saveAsButton, rectangle, pencil, undoButton, redoButton, Aanmaken, flexOphalen,
//            Gebouwen, Verwijderen;
//    JLabel coordinaten, lblX, lblY, lblHeight, lblWidth;
//    JTextField inputX, inputY, inputHeight, inputWidth;
    String value;
    Color color = Color.WHITE;
    private JFileChooser fileChooser;
    private File file;
//    private final Icon save = new ImageIcon(getClass().getResource("/./demo/save.png"));
//
//    private final Icon undo = new ImageIcon(getClass().getResource("/./demo/undo.png"));
//    private final Icon redo = new ImageIcon(getClass().getResource("/./demo/redo.png"));
//    private final Icon pencilIcon = new ImageIcon(getClass()
//            .getResource("/./demo/pencil.png"));
//    private final Icon rect = new ImageIcon(getClass().getResource("/./demo/desk.png"));
    private int saveCounter = 0;
   // private JLabel filenameBar, thicknessStat;
    private JSlider thicknessSlider;
//    JList lstDesk = new JList();
//    JComboBox cmbDesk, cmbPreset;
    public JFrameGebouwen() throws IOException {
        this.setLocationRelativeTo(null);
        this.setSize(2000,2000);
        this.setDefaultCloseOperation(JFrameGebouwen.EXIT_ON_CLOSE);
        this.listModelVerdiep = new DefaultListModel<String>();
        this.listModel2 = new DefaultListModel<String>();
        this.listModel = new DefaultListModel<String>();
        initComponents();
        this.setLocationRelativeTo(null); 
        this.getContentPane().setBackground(Color.DARK_GRAY);
        gebouwen = new ArrayList();
        json = new JsonService();
        fillJlist();
        gebouwen = json.getAllBuildings();
        canvas = new Canvas();
        gebouw = new Building();
        
        model = new DefaultTableModel();
        verdieping = new Floor();
        JTableHeader header = tblGebouw.getTableHeader();
        header.setFont(new Font("Dialog", Font.BOLD, 18));
          
        verdiepingen = new ArrayList();
        
            
        vloeren = new ArrayList();
        
        populateGebouwTabel();
        populateLSTSVG();
        //ip = new InputWH();

        JInputWH();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
    public void populateLSTSVG(){
        List<String> lst = canvas.getLst();
        listModel2.addElement(lst);
        lstSVG.setModel(listModel2);
        lstSVG.setLayoutOrientation(JList.VERTICAL_WRAP);    
    }

    public void populateGebouwTabel() throws IOException {
        gebouwen = json.getAllBuildings();
        model = (DefaultTableModel) tblGebouw.getModel();
        model.setRowCount(0);
        rowData = new Object[6];
        for (int i = 0; i < gebouwen.size(); i++) {
            rowData[0] = gebouwen.get(i).getBuildingId();
            rowData[1] = gebouwen.get(i).getName();
            rowData[2] = gebouwen.get(i).getStreet();
            rowData[3] = gebouwen.get(i).getNumber();
            rowData[4] = gebouwen.get(i).getCity();
            rowData[5] = gebouwen.get(i).getZipCode();
            model.addRow(rowData);
        }

        model.fireTableDataChanged();

    }    

    public void fillJlist() throws IOException {
        List<Floor> floors = new ArrayList();
        floors = json.getAllFloors();
        for (int i = 0; i < floors.size(); i++) {
            
                
                listModel.addElement(floors.get(i).getBuildings().getName());
                listModel.addElement(floors.get(i).getName());
                listModel.addElement(" ");
                
        }

            jlistVerdiepingen.setModel(listModel);
            jlistVerdiepingen.setSelectedIndex(0);
            jlistVerdiepingen.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
           
            jlistVerdiepingen.setVisibleRowCount(-1);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JInputWH = new javax.swing.JFrame();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        widthField = new javax.swing.JTextField();
        heightField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        JDraw = new javax.swing.JFrame();
        Verwijderen = new javax.swing.JButton();
        Aanmaken = new javax.swing.JButton();
        Gebouwen = new javax.swing.JButton();
        cmbDesk = new javax.swing.JComboBox<>();
        cmbPreset = new javax.swing.JComboBox<>();
        colorPicker = new javax.swing.JButton();
        saveAsButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        rectangle = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        lblX = new javax.swing.JLabel();
        inputX = new javax.swing.JTextField();
        lblY = new javax.swing.JLabel();
        inputY = new javax.swing.JTextField();
        lblWidth = new javax.swing.JLabel();
        inputWidth = new javax.swing.JTextField();
        lblHeight = new javax.swing.JLabel();
        inputHeight = new javax.swing.JTextField();
        filenameBar = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblGebouw = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstVerdiepingen = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlistVerdiepingen = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstSVG = new javax.swing.JList<>();
        btnDesigner = new javax.swing.JButton();
        btnJson = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        JInputWH.setTitle("Enter canvas width and height");
        JInputWH.setBackground(new java.awt.Color(102, 102, 102));
        JInputWH.setForeground(new java.awt.Color(102, 102, 102));

        jLayeredPane1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel3.setText("Width");

        jLabel4.setText("Height");

        jLabel5.setText("Minimum width: 900 and height: 800");

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(widthField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(heightField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnOK, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(heightField)
                            .addComponent(widthField))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(widthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOK)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout JInputWHLayout = new javax.swing.GroupLayout(JInputWH.getContentPane());
        JInputWH.getContentPane().setLayout(JInputWHLayout);
        JInputWHLayout.setHorizontalGroup(
            JInputWHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JInputWHLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JInputWHLayout.setVerticalGroup(
            JInputWHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JInputWHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );

        JDraw.setTitle("\"Paint (\" + width + \"X\" + height + \")\"");

        Verwijderen.setText("Verwijderen");

        Aanmaken.setText("Tekenen");

        Gebouwen.setText("Open gebouwen");

        cmbDesk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecteer een desk ...", "desk1", "desk2", "desk3", "desk4", "desk5", "desk6", "desk7", "desk8", "desk9", "desk10", "desk11", "desk12", "desk13", "desk14", "desk15", "desk16", "desk17", "desk18" }));

        cmbPreset.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecteer een opstelling...", "2x2", "4x4", "8x8" }));

        colorPicker.setText("Color picker");

        saveAsButton.setText("Save as");

        clearButton.setText("Clear");

        loadButton.setText("Load");

        rectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recources/desk.png"))); // NOI18N

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recources/save.png"))); // NOI18N

        lblX.setText("X:");

        lblY.setText("Y:");

        lblWidth.setText("Width:");

        lblHeight.setText("Height:");

        filenameBar.setText("no file");

        javax.swing.GroupLayout JDrawLayout = new javax.swing.GroupLayout(JDraw.getContentPane());
        JDraw.getContentPane().setLayout(JDrawLayout);
        JDrawLayout.setHorizontalGroup(
            JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDrawLayout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHeight)
                    .addGroup(JDrawLayout.createSequentialGroup()
                        .addComponent(Verwijderen)
                        .addGap(44, 44, 44)
                        .addComponent(Aanmaken)))
                .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JDrawLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Gebouwen)
                            .addGroup(JDrawLayout.createSequentialGroup()
                                .addComponent(rectangle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saveButton)))
                        .addGap(17, 17, 17)
                        .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbPreset, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbDesk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(loadButton)
                            .addComponent(colorPicker))
                        .addGap(18, 18, 18)
                        .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saveAsButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(56, Short.MAX_VALUE))
                    .addGroup(JDrawLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(inputHeight)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDrawLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(filenameBar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputWidth, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                    .addComponent(inputY)
                    .addComponent(inputX))
                .addContainerGap())
            .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JDrawLayout.createSequentialGroup()
                    .addGap(343, 343, 343)
                    .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblWidth)
                        .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblY, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(625, Short.MAX_VALUE)))
        );
        JDrawLayout.setVerticalGroup(
            JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDrawLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Verwijderen)
                    .addComponent(Aanmaken)
                    .addComponent(Gebouwen)
                    .addComponent(cmbDesk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorPicker)
                    .addComponent(saveAsButton))
                .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JDrawLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPreset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clearButton)
                            .addComponent(rectangle)))
                    .addGroup(JDrawLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDrawLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)))
                .addGap(86, 86, 86)
                .addComponent(inputX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JDrawLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(inputY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JDrawLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(filenameBar)))
                .addGap(18, 18, 18)
                .addComponent(inputWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeight)
                    .addComponent(inputHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(160, Short.MAX_VALUE))
            .addGroup(JDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JDrawLayout.createSequentialGroup()
                    .addGap(159, 159, 159)
                    .addComponent(lblX)
                    .addGap(18, 18, 18)
                    .addComponent(lblY)
                    .addGap(18, 18, 18)
                    .addComponent(lblWidth)
                    .addContainerGap(248, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 255));

        jLabel1.setBackground(new java.awt.Color(133, 181, 205));
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(133, 181, 205));
        jLabel1.setText("Gebouwen");

        tblGebouw.setBackground(new java.awt.Color(133, 181, 205));
        tblGebouw.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tblGebouw.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        tblGebouw.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "BuildingId", "Gebouwnaam", "Straat", "Huisnr", "Stad", "Postcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblGebouw.setSelectionBackground(new java.awt.Color(232, 255, 255));
        tblGebouw.setSelectionForeground(new java.awt.Color(102, 102, 102));
        tblGebouw.setShowVerticalLines(true);
        tblGebouw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGebouwMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblGebouw);

        jLabel2.setBackground(new java.awt.Color(133, 181, 205));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(133, 181, 205));
        jLabel2.setText("Verdiepingen");

        lstVerdiepingen.setBackground(new java.awt.Color(133, 181, 205));
        lstVerdiepingen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 18))); // NOI18N
        lstVerdiepingen.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lstVerdiepingen.setSelectionBackground(new java.awt.Color(232, 255, 255));
        lstVerdiepingen.setSelectionForeground(new java.awt.Color(102, 102, 102));
        jScrollPane3.setViewportView(lstVerdiepingen);

        jlistVerdiepingen.setBackground(new java.awt.Color(133, 181, 205));
        jlistVerdiepingen.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jlistVerdiepingen.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlistVerdiepingen.setSelectionBackground(new java.awt.Color(232, 255, 255));
        jScrollPane1.setViewportView(jlistVerdiepingen);

        lstSVG.setBackground(new java.awt.Color(133, 181, 205));
        lstSVG.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lstSVG.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(lstSVG);

        btnDesigner.setBackground(new java.awt.Color(114, 158, 180));
        btnDesigner.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        btnDesigner.setText("Designer");
        btnDesigner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesignerActionPerformed(evt);
            }
        });

        btnJson.setBackground(new java.awt.Color(108, 149, 169));
        btnJson.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        btnJson.setText("Verzenden");
        btnJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJsonActionPerformed(evt);
            }
        });

        btnClose.setBackground(new java.awt.Color(108, 149, 169));
        btnClose.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        btnClose.setText("Sluiten");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnDesigner, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnJson, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnClose, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(100, 100, 100))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(btnDesigner, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(302, 302, 302)
                                .addComponent(btnJson, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(45, Short.MAX_VALUE))))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnJson, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesigner, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void tblGebouwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGebouwMouseClicked
        row = tblGebouw.getSelectedRow();
        getConvGebouwRow = tblGebouw.convertRowIndexToModel(row);

        gebouw.setBuildingId((long) model.getValueAt(row, 0));
        gebouw.setName(model.getValueAt(row, 1).toString());
        gebouw.setStreet(model.getValueAt(row, 2).toString());
        gebouw.setNumber((int) model.getValueAt(row, 3));
        gebouw.setCity(model.getValueAt(row, 4).toString());
        gebouw.setZipCode((int) model.getValueAt(row, 5));
        try {
            getFloor();

        } catch (IOException ex) {
            Logger.getLogger(JFrameGebouwen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tblGebouwMouseClicked

    private void btnDesignerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesignerActionPerformed
           this.dispose();
           this.JInputWH.setVisible(true);
           this.JInputWH.setLocationRelativeTo(null); 
           this.JInputWH.setSize(600, 200);
           this.JInputWH.setDefaultCloseOperation(JInputWH.EXIT_ON_CLOSE);
// InputWH ip = new InputWH(); 
//            SwingUtilities.invokeLater(new Runnable() {            
//            @Override
//            public void run() {
//                InputWH ip = new InputWH();                
//            }
//        });

           //this.ip = new InputWH();    
           //ip = new InputWH();
           //ip.setVisible(true);
           
    }//GEN-LAST:event_btnDesignerActionPerformed
    
    private void btnJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJsonActionPerformed
    
        selectedFloorId = Long.parseLong(lstVerdiepingen.getSelectedValue());
        svg = canvas.getLst().toString();
        Floor verdiep = new Floor();        
        Floor verdieping1 = new Floor();

        try {
            
            verdieping1 = json.getFloorById(selectedFloorId);
            
            verdiep.setName(verdieping1.getName());
            verdiep.setNumber(verdieping1.getNumber());
            verdiep.setFloorCode(verdieping1.getFloorCode());
            verdiep.setSVG(svg);
            verdiep.setFloorId(selectedFloorId);
            verdiep.setBuildingId(verdieping1.getBuildingId());

            json.addFloorSVG(verdiep);
            
            
        } catch (IOException ex) {
            ex.getStackTrace();
        }

        
    }//GEN-LAST:event_btnJsonActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        
         try {
         width = Integer.parseInt(widthField.getText()) + 2;
         height = Integer.parseInt(heightField.getText()) + 2;
         if (width < 900 || height < 800) {
             JOptionPane.showMessageDialog(null,
                    "W:900,H:800 Minimum required",
                    "                                Foutbericht",
                    JOptionPane.ERROR_MESSAGE);            
             
            }         
         } catch (IllegalArgumentException e) {
             
                         JOptionPane.showMessageDialog(null,
                "Please enter valid number!",
                "                                Foutbericht",
                JOptionPane.ERROR_MESSAGE);
             
         }
         
         JDraw();
        
         
//        Draw draw = new Draw();
//
//        try {
//            width = Integer.parseInt(widthField.getText()) + 2;
//            height = Integer.parseInt(heightField.getText()) + 2;
//            if (width < 900 || height < 800) {
//                JOptionPane.showMessageDialog(null,
//                    "W:900,H:800 Minimum required",
//                    "                                Foutbericht",
//                    JOptionPane.ERROR_MESSAGE);
//            }
//            draw.setWH(width, height);
//            
//                
//            try {
//               
//
//                draw.openPaint();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(JFrameGebouwen.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//           
// 
//        } catch (IllegalArgumentException e) {

//        }

        this.JInputWH.dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    public void JInputWH(){
        JInputWH.setBackground(Color.DARK_GRAY);
         JInputWH.setForeground(Color.DARK_GRAY);
        jLayeredPane1.setBackground(Color.DARK_GRAY);
        this.JInputWH.getRootPane().setDefaultButton(btnOK);
        this.JInputWH.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.JInputWH.setLocationRelativeTo(null);
    }
    
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    public void JDraw(){

         
        Container container =  this.JDraw.getContentPane();
        container.setLayout(new BorderLayout());
        canvas = new Canvas();
        container.add(canvas, BorderLayout.CENTER);
         
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        Box box = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        panel1.setLayout(new FlowLayout());


        



        filenameBar.setFont(new Font("serif", Font.BOLD, 20));        
        filenameBar.setForeground(Color.WHITE);        
        panel1.add(filenameBar);


         panel.setBackground(Color.DARK_GRAY);
         panel.add(saveButton);
         saveButton.setFont(new Font("serif", Font.BOLD, 20));
         saveButton.setBackground(Color.DARK_GRAY);
         saveButton.setForeground(Color.WHITE);
         saveButton.addActionListener(listener);
                 
         panel.add(saveAsButton);
         saveAsButton.setFont(new Font("serif", Font.BOLD, 20));
         saveAsButton.setBackground(Color.DARK_GRAY);
         saveAsButton.setForeground(Color.WHITE);
         saveAsButton.addActionListener(listener);
         
         panel.add(loadButton);
         loadButton.setFont(new Font("serif", Font.BOLD, 20));
         loadButton.setBackground(Color.DARK_GRAY);
         loadButton.setForeground(Color.WHITE);
         loadButton.addActionListener(listener);
                  
         panel.add(colorPicker);
         colorPicker.setFont(new Font("serif", Font.BOLD, 20));
         colorPicker.setBackground(Color.DARK_GRAY);
         colorPicker.setForeground(Color.WHITE);
         colorPicker.addActionListener(listener);
         
         panel.add(clearButton);
         clearButton.setFont(new Font("serif", Font.BOLD, 20));
         clearButton.setBackground(Color.DARK_GRAY);
         clearButton.setForeground(Color.WHITE);
         clearButton.addActionListener(listener);
         
         panel.add(Verwijderen);
         Verwijderen.setFont(new Font("serif", Font.BOLD, 20));
         Verwijderen.setBackground(Color.DARK_GRAY);
         Verwijderen.setForeground(Color.WHITE);
         Verwijderen.addActionListener(listener);
         
         panel.add(Gebouwen);
         Gebouwen.setFont(new Font("serif", Font.BOLD, 20));
         Gebouwen.setBackground(Color.DARK_GRAY);
         Gebouwen.setForeground(Color.WHITE);
         Gebouwen.addActionListener(listener);
         
         
         preset();
         for (int i = 0; i < presets.size(); i++) {
            model3.addElement(presets.get(i));
         }
         panel.add(cmbPreset);
         cmbPreset.setFont(new Font("serif", Font.BOLD, 20));
         cmbPreset.addActionListener(listener);
         
         
         
         
         
         box.add(Box.createVerticalStrut(40));
         
         //Comboboc desk
         putDesk();
         for (int i = 0; i < desk.size(); i++) {
            model2.addElement(desk.get(i));
          }
         cmbDesk.setFont(new Font("serif", Font.BOLD, 20));
         cmbDesk.addActionListener(listener);
         box.add(cmbDesk);
         
         
         rectangle.setPreferredSize(new Dimension(65, 55));
         rectangle.addActionListener(listener);
         box.add(rectangle);
         box.add(Box.createVerticalStrut(40));
                           
         lblX.setForeground(Color.WHITE);
         lblX.setFont(new Font("Serif", Font.PLAIN, 24));
         box.add(lblX);
         inputX.setFont(new Font("Serif", Font.CENTER_BASELINE, 24));
         box.add(inputX);
         
         lblY.setForeground(Color.WHITE);
         lblY.setFont(new Font("Serif", Font.PLAIN, 24));
         box.add(lblY);
         inputY.setFont(new Font("Serif", Font.CENTER_BASELINE, 24));
         box.add(inputY);
         
         lblWidth.setForeground(Color.WHITE);
         lblWidth.setFont(new Font("Serif", Font.PLAIN, 24));
         box.add(lblWidth);
         inputWidth.setFont(new Font("Serif", Font.CENTER_BASELINE, 24));
         box.add(inputWidth);
         
         lblHeight.setForeground(Color.WHITE);
         lblHeight.setFont(new Font("Serif", Font.PLAIN, 24));
         box.add(lblHeight);
         inputHeight.setFont(new Font("Serif", Font.CENTER_BASELINE, 24));
         box.add(inputHeight);
         
         Aanmaken.setFont(new Font("serif", Font.BOLD, 20));
         Aanmaken.setBackground(Color.DARK_GRAY);
         Aanmaken.setForeground(Color.WHITE);
         Aanmaken.addActionListener(listener);
         this.JDraw.getRootPane().setDefaultButton(Aanmaken);
         box.add(Aanmaken);
         box.add(Box.createVerticalStrut(20));
         
         
         
        container.add(panel, BorderLayout.NORTH);
        container.add(panel1, BorderLayout.SOUTH);
        container.add(box, BorderLayout.WEST);
        container.setFont(new Font("Dialog", Font.BOLD, 18));
        
         this.JDraw.setVisible(true);
         this.JDraw.getContentPane().setBackground(Color.DARK_GRAY);
         this.JDraw.setSize(width + 79, height + 11);
         this.JDraw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.JDraw.setLocationRelativeTo(null);
         
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameGebouwen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameGebouwen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameGebouwen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameGebouwen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameGebouwen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameGebouwen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameGebouwen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameGebouwen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new JFrameGebouwen().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(JFrameGebouwen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    //Filtert de verdiepingen uit die overeenkomen met een gebouw
    public void getFloor() throws IOException {
        
        List<Floor> floors = new ArrayList();
        List<Building> buildings = new ArrayList();
        List<String> vloeren = new ArrayList();
        
        
        buildings = json.getAllBuildings();
        vloeren.clear();        
        listModelVerdiep.removeAllElements();        
       
        if (gebouw.getBuildingId() != null) {
            gebouwId = gebouw.getBuildingId();
        }


        for (int i = 0; i < buildings.size(); i++) {
            if (gebouw.getName().equals(buildings.get(i).getName())) {
                gebouwId = buildings.get(i).getBuildingId();
            }
        }
        
        
        floors = json.getAllFloors();

        for (int i = 0; i < floors.size(); i++) {

            if (gebouwId == floors.get(i).getBuildings().getBuildingId()) {

                vloeren.add(floors.get(i).getFloorId().toString());
                vloeren.add(floors.get(i).getName());
                 
            }
        }
        for (int t = 0; t < vloeren.size(); t++) {
                listModelVerdiep.addElement(vloeren.get(t));
                
                }
             lstVerdiepingen.setModel(listModelVerdiep);
//        for (int i = 0; i < vloeren.size(); i++) {
//            System.out.println(vloeren.get(i));
//        }

    }

    public List<Floor> getVloeren() {
        return vloeren;
    }

    public void setVloeren(List<Floor> vloeren) {
        this.vloeren = vloeren;
    }

    public Long getSelectedFloorId() {
        return selectedFloorId;
    }

    public void setSelectedFloorId(Long selectedFloorId) {
        this.selectedFloorId = selectedFloorId;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }
//    ChangeListener thick = new ChangeListener() {
//        @Override
//        public void stateChanged(ChangeEvent e) {
//            thicknessStat.setText(String.format("%s",
//                    thicknessSlider.getValue()));
//            canvas.setThickness(thicknessSlider.getValue());
//        }
//    };
    
    ActionListener listener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == clearButton) {
                canvas.clear();
                canvas.remove();
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
                if (value == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Opgelet: U hebt geen desk geselecteerd!",
                            "                                                        Foutbericht",
                            javax.swing.JOptionPane.ERROR_MESSAGE);

                } else {
                canvas.rect();
                }

            } else if (event.getSource() == Aanmaken) {
                int x = Integer.parseInt(inputX.getText());
                int y = Integer.parseInt(inputY.getText());
                int width1 = Integer.parseInt(inputWidth.getText());
                int height1 = Integer.parseInt(inputHeight.getText());
                //value = lstDesk.getSelectedValue().toString();  
                if (value == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Opgelet: U hebt geen desk geselecteerd!",
                            "                                                        Foutbericht",
                            javax.swing.JOptionPane.ERROR_MESSAGE);

                } else {
                    value = cmbDesk.getSelectedItem().toString();

                    canvas.setRechthoek(x, y, width1, height1, value);
                    clearInput();
                }

            } else if (event.getSource() == cmbDesk) {
                //value = lstDesk.getSelectedValue().toString(); 
                value = cmbDesk.getSelectedItem().toString();
                canvas.setGegeven(value);

            } else if (event.getSource() == Gebouwen) {
                try {
                    JFrameGebouwen jf = new JFrameGebouwen();
                    jf.setVisible(true);
                    // jf.enable(true);
                } catch (IOException ex) {
                    Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (event.getSource() == cmbPreset) {
                String twee = "2x2";
                String vier = "4x4";
                String acht = "8x8";
                String combinatie = cmbPreset.getSelectedItem().toString();
                pr = new Preset();
                if (vier.equals(combinatie)) {

                    pr.vierOpVier();
                    canvas.getDataPreset();
                } else if (twee.equals(combinatie)) {
                    pr.tweeOpTwee();
                    canvas.getDataPreset();
                } else if (acht.equals(combinatie)) {
                    pr.achtOpAcht();
                    canvas.getDataPreset();
                }
            } else if (event.getSource() == Verwijderen) {
                if (value == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Opgelet: U hebt geen desk geselecteerd!",
                            "                                                        Foutbericht",
                            javax.swing.JOptionPane.ERROR_MESSAGE);

                } else {
                    canvas.verwijderen(value);
                }
            }

        }
    };

    public void clearInput() {
        inputX.setText("");
        inputY.setText("");
        inputWidth.setText("");
        inputHeight.setText("");
    }
    
     //maakt desks aan voor lstDesk
    public List<String> putDesk() {
        
        desk = new ArrayList();
        desk.add("Selecteer een desk ...");
        desk.add("desk1");
        desk.add("desk2");
        desk.add("desk3");
        desk.add("desk4");
        desk.add("desk5");
        desk.add("desk6");
        desk.add("desk7");
        desk.add("desk8");
        desk.add("desk9");
        desk.add("desk10");
        desk.add("desk11");
        desk.add("desk12");
        desk.add("desk13");
        desk.add("desk14");
        desk.add("desk15");
        desk.add("desk16");
        desk.add("desk17");
        desk.add("desk18");
        return desk;

    }
    public List<String> preset() {
        presets = new ArrayList();
        presets.add("Selecteer een opstelling ...");
        presets.add("2x2");
        presets.add("4x4");
        presets.add("8x8");
        return presets;
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

    public List<String> getPresets() {
        return presets;
    }

    public void setPresets(List<String> presets) {
        this.presets = presets;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aanmaken;
    private javax.swing.JButton Gebouwen;
    private javax.swing.JFrame JDraw;
    private javax.swing.JFrame JInputWH;
    private javax.swing.JButton Verwijderen;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDesigner;
    private javax.swing.JButton btnJson;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox<String> cmbDesk;
    private javax.swing.JComboBox<String> cmbPreset;
    private javax.swing.JButton colorPicker;
    private javax.swing.JLabel filenameBar;
    private javax.swing.JTextField heightField;
    private javax.swing.JTextField inputHeight;
    private javax.swing.JTextField inputWidth;
    private javax.swing.JTextField inputX;
    private javax.swing.JTextField inputY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> jlistVerdiepingen;
    private javax.swing.JLabel lblHeight;
    private javax.swing.JLabel lblWidth;
    private javax.swing.JLabel lblX;
    private javax.swing.JLabel lblY;
    private javax.swing.JButton loadButton;
    private javax.swing.JList<String> lstSVG;
    private javax.swing.JList<String> lstVerdiepingen;
    private javax.swing.JButton rectangle;
    private javax.swing.JButton saveAsButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable tblGebouw;
    private javax.swing.JTextField widthField;
    // End of variables declaration//GEN-END:variables

   

        
    
}
