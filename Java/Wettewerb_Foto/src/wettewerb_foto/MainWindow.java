package wettewerb_foto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Hannes
 */
public class MainWindow extends javax.swing.JFrame {

    static ArrayList<Camera> cam = new ArrayList();
    Camera uCam;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();


        uCam = new Camera(0, "Unbekannt");
        for (int i = 1; i <= 5; i++) {
            cam.add(new Camera(i, "Kamera"));
        }
        ListModel lm = new AbstractListModel() {
            @Override
            public int getSize() {
                return cam.size();
            }

            @Override
            public Object getElementAt(int i) {
                return cam.get(i);
            }
        };
        jList1.setModel(lm);
        jList1.setSelectedIndex(0);
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
        jList1 = new javax.swing.JList();
        addImageButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        unknownImage = new javax.swing.JButton();
        export = new javax.swing.JButton();
        JaktuellLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        addImageButton.setText("Lade Bilder für Camera 1");
        addImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addImageButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Unbekanntes Bild: ");

        unknownImage.setText("Lade unbekanntes Bild");
        unknownImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unknownImageActionPerformed(evt);
            }
        });

        export.setText("Ergebnis exportieren");
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        JaktuellLabel.setText("Anzahl der Bilder:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(export, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(unknownImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JaktuellLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(addImageButton, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(JaktuellLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addImageButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unknownImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addImageButtonActionPerformed
        final JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter ppmFileFilter = new FileNameExtensionFilter("multiple selection recommended (*.ppm)", "ppm");
        fc.setFileFilter(ppmFileFilter);

        //Allow Multiplefiles
        fc.setMultiSelectionEnabled(true);

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File[] tmpFiles = fc.getSelectedFiles();
            for (int i = 0; i < tmpFiles.length; i++) {
                cam.get(jList1.getSelectedIndex()).AddImage(tmpFiles[i].getAbsolutePath());
            }

        }

        changeInfo();
    }//GEN-LAST:event_addImageButtonActionPerformed

    private void unknownImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unknownImageActionPerformed
        uCam = new Camera(0, "Unbekannt");
        final JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter ppmFileFilter = new FileNameExtensionFilter("PPM Images (*.ppm)", "ppm");
        fc.setFileFilter(ppmFileFilter);


        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File tmpFile = fc.getSelectedFile();
            uCam.AddImage(tmpFile.getAbsolutePath());
        }

        jLabel1.setText("Unbekanntes Bild: " + uCam.countImages());
    }//GEN-LAST:event_unknownImageActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        //Open FileSaveDialo
        final JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter ppmFileFilter = new FileNameExtensionFilter("Save as csv (*.csv)", "csv");
        fc.setFileFilter(ppmFileFilter);
        int returnVal = fc.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                //File Saving
                File tmpFile = fc.getSelectedFile();
                createCSV(tmpFile.getParent(), tmpFile.getName() + ".csv");
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_exportActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        changeInfo();
    }//GEN-LAST:event_jList1ValueChanged
    public void createCSV(String path, String filename) throws IOException {
        File file = new File(path, filename);
        if (!file.isFile() && !file.createNewFile()) {
            throw new IOException("Error creating new file: " + file.getAbsolutePath());
        }
        try {
            FileWriter writer = new FileWriter(path + "\\" + filename);

            writer.append("Foto;Durchschnittlicher Abstand; Streuung" + System.getProperty("line.separator"));

            for (int i = 0; i < cam.size(); i++) {
                writeLine(writer, cam.get(i));
            }

            writeLine(writer, uCam);


            System.out.println("Written Data to File");

            //generate whatever data you want

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static FileWriter writeLine(FileWriter writer, Camera camera) throws IOException {
        writer.append(camera.writeLine());
        return writer;
    }

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JaktuellLabel;
    private javax.swing.JButton addImageButton;
    private javax.swing.JButton export;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton unknownImage;
    // End of variables declaration//GEN-END:variables

    private void changeInfo() {
        Camera c = cam.get(jList1.getSelectedIndex());
        JaktuellLabel.setText("Anzahl der Bilder: " + c.countImages());
        addImageButton.setText("Lade Bilder für " + c);
    }
}
