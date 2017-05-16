/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.meteoinfo.desktop.forms;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.meteoinfo.data.mapdata.Field;
import org.meteoinfo.global.GenericFileFilter;
import org.meteoinfo.global.MIMath;
import org.meteoinfo.global.PointD;
import org.meteoinfo.data.DataTypes;
import org.meteoinfo.global.util.GlobalUtil;
import org.meteoinfo.layer.LayerDrawType;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.legend.LegendManage;
import org.meteoinfo.shape.PointShape;
import org.meteoinfo.shape.ShapeTypes;

/**
 *
 * @author yaqiang
 */
public class FrmAddXYData extends javax.swing.JDialog {

    private FrmMain _parent;
    private String separator = null;
    private List<Field> fields = new ArrayList<>();

    /**
     * Creates new form FrmAddXYData
     * @param parent
     * @param modal
     */
    public FrmAddXYData(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.jPanel_SelFields.setEnabled(false);

        _parent = (FrmMain) parent;
        this.jComboBox_LonField.removeAllItems();
        this.jComboBox_LatField.removeAllItems();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton_InputFile = new javax.swing.JButton();
        jTextField_InputFile = new javax.swing.JTextField();
        jPanel_SelFields = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_LonField = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_LatField = new javax.swing.JComboBox();
        jButton_AddData = new javax.swing.JButton();
        jButton_Close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane1.setWheelScrollingEnabled(false);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(java.awt.SystemColor.info);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(4);
        jTextArea1.setText("This tool will create a station layer from a ASCII file with comma, simicolon or space separator. The file must contain column titles as the first row.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(jTextArea1);

        jButton_InputFile.setText("Input File");
        jButton_InputFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InputFileActionPerformed(evt);
            }
        });

        jPanel_SelFields.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Fields From Input File"));

        jLabel1.setText("Lon Field:");

        jComboBox_LonField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Lat Field:");

        jComboBox_LatField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel_SelFieldsLayout = new javax.swing.GroupLayout(jPanel_SelFields);
        jPanel_SelFields.setLayout(jPanel_SelFieldsLayout);
        jPanel_SelFieldsLayout.setHorizontalGroup(
            jPanel_SelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SelFieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SelFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_LonField, 0, 248, Short.MAX_VALUE))
                    .addGroup(jPanel_SelFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_LatField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_SelFieldsLayout.setVerticalGroup(
            jPanel_SelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SelFieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox_LonField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_SelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_LatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton_AddData.setText("Add Data");
        jButton_AddData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddDataActionPerformed(evt);
            }
        });

        jButton_Close.setText("Close");
        jButton_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_SelFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_InputFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_InputFile)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_AddData)
                .addGap(67, 67, 67)
                .addComponent(jButton_Close)
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_InputFile)
                    .addComponent(jTextField_InputFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel_SelFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_AddData)
                    .addComponent(jButton_Close))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_InputFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InputFileActionPerformed
        // TODO add your handling code here:
        String path = System.getProperty("user.dir");
        File pathDir = new File(path);

        JFileChooser aDlg = new JFileChooser();
        aDlg.setAcceptAllFileFilterUsed(false);
        aDlg.setCurrentDirectory(pathDir);
        String[] fileExts = new String[]{"csv", "txt"};
        GenericFileFilter allFileFilter = new GenericFileFilter(fileExts, "Supported Formats");
        aDlg.addChoosableFileFilter(allFileFilter);
        fileExts = new String[]{"csv"};
        GenericFileFilter mapFileFilter = new GenericFileFilter(fileExts, "CSV File (*.csv)");
        aDlg.addChoosableFileFilter(mapFileFilter);
        fileExts = new String[]{"txt"};
        mapFileFilter = new GenericFileFilter(fileExts, "Text File (*.txt)");
        aDlg.addChoosableFileFilter(mapFileFilter);
        aDlg.setFileFilter(allFileFilter);
        if (JFileChooser.APPROVE_OPTION == aDlg.showOpenDialog(this)) {
            File aFile = aDlg.getSelectedFile();
            System.setProperty("user.dir", aFile.getParent());
            this.jTextField_InputFile.setText(aFile.getAbsolutePath());
            try {
                //BufferedReader sr = new BufferedReader(new FileReader(aFile));
                BufferedReader sr = new BufferedReader(new InputStreamReader(new FileInputStream(aFile), "UTF-8"));
                String title = sr.readLine().trim();
                //Determine separator
                separator = GlobalUtil.getDelimiter(title);
                String[] titleArray = GlobalUtil.split(title, separator);
                if (titleArray.length <= 2) {
                    JOptionPane.showMessageDialog(null, "File Format Error!");
                    sr.close();
                } else {
                    //Get fields
                    String aLine = sr.readLine().trim();    //Second line
                    String[] dataArray = GlobalUtil.split(aLine, separator);
                    if (dataArray.length != titleArray.length) {
                        JOptionPane.showMessageDialog(null, "File Format Error!");
                        sr.close();
                        return;
                    }
                    String fieldName;
                    DataTypes dataType;
                    for (int i = 0; i < dataArray.length; i++) {
                        fieldName = titleArray[i];
                        if (MIMath.isNumeric(dataArray[i])) {
                            dataType = DataTypes.Double;
                        } else {
                            dataType = DataTypes.String;
                        }
                        fields.add(new Field(fieldName, dataType));
                    }

                    sr.close();

                    this.jPanel_SelFields.setEnabled(true);
                    this.jComboBox_LonField.removeAllItems();
                    this.jComboBox_LatField.removeAllItems();
                    for (int i = 0; i < titleArray.length; i++) {
                        this.jComboBox_LonField.addItem(titleArray[i]);
                        this.jComboBox_LatField.addItem(titleArray[i]);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrmAddXYData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrmAddXYData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_InputFileActionPerformed

    private void jButton_AddDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddDataActionPerformed
        // TODO add your handling code here:
        String lonField = this.jComboBox_LonField.getSelectedItem().toString();
        String latField = this.jComboBox_LatField.getSelectedItem().toString();
        if (lonField.isEmpty() || latField.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields should be set!");
            return;
        }

        JFileChooser aDlg = new JFileChooser();
        String curDir = System.getProperty("user.dir");
        aDlg.setCurrentDirectory(new File(curDir));
        String[] fileExts = {"shp"};
        GenericFileFilter pFileFilter = new GenericFileFilter(fileExts, "Shape File (*.shp)");
        aDlg.setFileFilter(pFileFilter);
        aDlg.setAcceptAllFileFilterUsed(false);
        if (JFileChooser.APPROVE_OPTION == aDlg.showSaveDialog(null)) {
            BufferedReader sr = null;
            try {
                File aFile = aDlg.getSelectedFile();
                System.setProperty("user.dir", aFile.getParent());
                String extent = ((org.meteoinfo.global.GenericFileFilter) aDlg.getFileFilter()).getFileExtent();
                String fileName = aFile.getAbsolutePath();
                if (!fileName.substring(fileName.length() - extent.length()).equals(extent)) {
                    fileName = fileName + "." + extent;
                }

                //New layer
                VectorLayer aLayer = new VectorLayer(ShapeTypes.Point);
                aLayer.setLayerDrawType(LayerDrawType.Map);
                aLayer.setLayerName(aFile.getName());
                aLayer.setFileName(fileName);
                aLayer.setLegendScheme(LegendManage.createSingleSymbolLegendScheme(ShapeTypes.Point, Color.black, 5));
                aLayer.setVisible(true);
                for (Field field : fields){
                    aLayer.editAddField(field);
                }
                
                int lonIdx = this.jComboBox_LonField.getSelectedIndex();
                int latIdx = this.jComboBox_LatField.getSelectedIndex();
                double lon, lat;
                String inFile = this.jTextField_InputFile.getText();
                sr = new BufferedReader(new InputStreamReader(new FileInputStream(inFile), "UTF-8"));
                //sr = new BufferedReader(new FileReader(new File(inFile)));
                String[] dataArray;
                sr.readLine();    //First line - title                
                String aLine = sr.readLine();    //First data line                
                while (aLine != null) {
                    aLine = aLine.trim();
                    dataArray = GlobalUtil.split(aLine, separator);
                    if (dataArray.length < 2) {
                        aLine = sr.readLine();
                        continue;
                    }

                    PointD aPoint = new PointD();
                    lon = Double.parseDouble(dataArray[lonIdx].trim());
                    lat = Double.parseDouble(dataArray[latIdx].trim());
                    aPoint.X = lon;
                    aPoint.Y = lat;

                    //Add shape
                    PointShape aPS = new PointShape();
                    aPS.setPoint(aPoint);
                    int shapeNum = aLayer.getShapeNum();
                    if (aLayer.editInsertShape(aPS, shapeNum)) {
                        //Edit record value
                        for (int j = 0; j < aLayer.getFieldNumber(); j++) {
                            Field field = aLayer.getField(j);
                            switch (field.getDataType()) {
                                case Double:
                                    aLayer.editCellValue(j, shapeNum, Double.parseDouble(dataArray[j].trim()));
                                    break;
                                case String:
                                    aLayer.editCellValue(j, shapeNum, dataArray[j].trim());
                                    break;
                            }
                        }
                    }

                    aLine = sr.readLine();
                }

                aLayer.saveFile(fileName);
                this._parent.getMapDocument().getActiveMapFrame().addLayer(aLayer);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrmAddXYData.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(FrmAddXYData.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(FrmAddXYData.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } finally {
                try {
                    sr.close();
                } catch (IOException ex) {
                    Logger.getLogger(FrmAddXYData.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton_AddDataActionPerformed

    private void jButton_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton_CloseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FrmAddXYData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAddXYData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAddXYData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAddXYData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAddXYData dialog = new FrmAddXYData(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AddData;
    private javax.swing.JButton jButton_Close;
    private javax.swing.JButton jButton_InputFile;
    private javax.swing.JComboBox jComboBox_LatField;
    private javax.swing.JComboBox jComboBox_LonField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel_SelFields;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField_InputFile;
    // End of variables declaration//GEN-END:variables
}
