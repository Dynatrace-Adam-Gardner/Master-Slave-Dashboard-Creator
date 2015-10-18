package masterslave.dashboard.creator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.w3c.dom.Element;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cwuk-agardner
 */
public class MasterSlaveDashboard extends javax.swing.JFrame {

    private List<Dashlet> m_oDashlets;
    private MasterSlaveDashboardCreator m_oProcessor;
    /**
     * Creates new form MasterSlaveDashboard
     */
    public MasterSlaveDashboard() {
        initComponents();
        m_oDashlets = new ArrayList<Dashlet>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_select_dashboard = new javax.swing.JButton();
        label_select_master_dashlet = new javax.swing.JLabel();
        combobox_master_dashlet = new javax.swing.JComboBox();
        button_process_dashboard = new javax.swing.JButton();
        label_about_dashboard_cleaner = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Master / Slave Dashboard Creator v1.0");

        button_select_dashboard.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        button_select_dashboard.setText("Select Dashboard");
        button_select_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_select_dashboardActionPerformed(evt);
            }
        });

        label_select_master_dashlet.setText("Select Master Dashlet");
        label_select_master_dashlet.setEnabled(false);

        combobox_master_dashlet.setEnabled(false);

        button_process_dashboard.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        button_process_dashboard.setText("Process Dashboard");
        button_process_dashboard.setEnabled(false);
        button_process_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_process_dashboardActionPerformed(evt);
            }
        });

        label_about_dashboard_cleaner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_about_dashboard_cleaner.setText("<html>Master / Slave Dashboard Creator v1.0<br />Adam Gardner (adam.gardner@dynatrace.com)</html>");
        label_about_dashboard_cleaner.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_process_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_select_dashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_select_master_dashlet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(combobox_master_dashlet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(label_about_dashboard_cleaner, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_select_dashboard)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_select_master_dashlet)
                    .addComponent(combobox_master_dashlet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(button_process_dashboard)
                .addGap(18, 18, 18)
                .addComponent(label_about_dashboard_cleaner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_select_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_select_dashboardActionPerformed
        
        // Clear any existing dashlets / UI elements
        m_oDashlets.clear();
        combobox_master_dashlet.removeAllItems();
        combobox_master_dashlet.validate();
        
        JFileChooser oFileChooser = new JFileChooser();
        oFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Dashboard XML Files (.xml)","xml");
        oFileChooser.setFileFilter(filter);
        oFileChooser.showOpenDialog(this);
        
        File oFile = oFileChooser.getSelectedFile();
        boolean bSuccess = false;
        
        // Handle the case where a user clicks cancel
        if (oFile == null) return;
        
        if (!oFile.getName().endsWith(".dashboard.xml"))
        {
            JOptionPane.showMessageDialog(null, "Invalid dashboard file. Please try again.", "Severe Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // Now that we have a valid dashboard, enable other items
            label_select_master_dashlet.setEnabled(true);
            combobox_master_dashlet.setEnabled(true);
            button_process_dashboard.setEnabled(true);
            
            m_oProcessor = new MasterSlaveDashboardCreator(oFile);
            m_oDashlets = m_oProcessor.getDashlets();
            
            // Populate list
            for (Dashlet oDashlet : m_oDashlets) combobox_master_dashlet.addItem(oDashlet);
            
        }
        
    }//GEN-LAST:event_button_select_dashboardActionPerformed

    private void button_process_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_process_dashboardActionPerformed
        
        // Get selected item. Represented as dashlet name
        Dashlet oSelectedDashlet = (Dashlet) combobox_master_dashlet.getSelectedItem();
        
        m_oProcessor.processDashboard(oSelectedDashlet,m_oDashlets);
        boolean bSuccess = m_oProcessor.save();
        
        if (bSuccess) JOptionPane.showMessageDialog(null, "Dashboard successfully processed. Look in the original directory for the _master_slave.dashboard.xml file", "Dashboard Processing Complete", JOptionPane.INFORMATION_MESSAGE);
                
    }//GEN-LAST:event_button_process_dashboardActionPerformed

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
            java.util.logging.Logger.getLogger(MasterSlaveDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MasterSlaveDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MasterSlaveDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MasterSlaveDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MasterSlaveDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_process_dashboard;
    private javax.swing.JButton button_select_dashboard;
    private javax.swing.JComboBox combobox_master_dashlet;
    private javax.swing.JLabel label_about_dashboard_cleaner;
    private javax.swing.JLabel label_select_master_dashlet;
    // End of variables declaration//GEN-END:variables
}
