 /* Copyright 2012 Yaqiang Wang,
 * yaqiang.wang@gmail.com
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or (at
 * your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser
 * General Public License for more details.
 */
package org.meteoinfo.desktop.forms;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.meteoinfo.desktop.config.GenericFileFilter;

/**
 *
 * @author Yaqiang Wang
 */
public class FrmDataInfo extends javax.swing.JFrame {
    
    /**
     * Creates new form FrmDataInfo
     */
    public FrmDataInfo() {
        initComponents();
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        
        BufferedImage image = null;
        try {
            image = ImageIO.read(this.getClass().getResource("/org/meteoinfo/desktop/resources/MeteoInfo_1_16x16x8.png"));
            this.setIconImage(image);
        } catch (Exception e) {
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton_Save = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Info = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setRollover(true);

        jButton_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/meteoinfo/desktop/resources/Disk_1_16x16x8.png"))); // NOI18N
        jButton_Save.setFocusable(false);
        jButton_Save.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Save.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SaveActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton_Save);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jTextArea_Info.setColumns(20);
        jTextArea_Info.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Info);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SaveActionPerformed
        // TODO add your handling code here:
        JFileChooser aDlg = new JFileChooser();
        String[] fileExts = new String[]{"txt"};
        GenericFileFilter mapFileFilter = new GenericFileFilter(fileExts, "Text File (*.txt)");
        aDlg.setFileFilter(mapFileFilter);
        File dir = new File(System.getProperty("user.dir"));
        if (dir.isDirectory()) {
            aDlg.setCurrentDirectory(dir);
        }
        aDlg.setAcceptAllFileFilterUsed(false);
        if (aDlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = aDlg.getSelectedFile();
            System.setProperty("user.dir", file.getParent());
            String extent = ((GenericFileFilter) aDlg.getFileFilter()).getFileExtent();
            String fileName = file.getAbsolutePath();
            if (!fileName.substring(fileName.length() - extent.length()).equals(extent)) {
                fileName = fileName + "." + extent;
                file = new File(fileName);
            }  
            
            try {
                BufferedWriter sw = new BufferedWriter(new FileWriter(file));
                sw.write(this.jTextArea_Info.getText());
                sw.flush();
                sw.close();
            } catch (IOException ex) {
                Logger.getLogger(FrmDataInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_SaveActionPerformed

    /**
     * Set text
     *
     * @param text Text
     */
    public void setText(String text) {
        this.jTextArea_Info.setText(text);
        this.jTextArea_Info.setCaretPosition(0);
    }

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
            java.util.logging.Logger.getLogger(FrmDataInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDataInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDataInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDataInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDataInfo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Save;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Info;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
