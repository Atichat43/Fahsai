/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Dhitiwat
 */
public class NotificationGui extends javax.swing.JFrame {

    /**
     * Creates new form NotificationGui
     *
     * @param tableNum
     * @param mes
     */
    int tableNum;

    public NotificationGui(int tableNum, String mes, Point pos) {
        this.setLocation(pos.x +100, pos.y+100);
        this.setAlwaysOnTop(true);
        initComponents();
        this.tableNum = tableNum;
        tableNumberLabel.setText("" + tableNum);
        if (mes.equals("check bill")) {
            messageLabel.setText("Check Bill...");
        } else if (mes.equals("call waiter")) {
            messageLabel.setText("Need Assistant...");
        }

    }

    public NotificationGui(String mes) {
        this.setLocation(100, 100);
        this.setAlwaysOnTop(true);
        initComponents();
        messageLabel.setText(mes);
        tableLabel.setText("");
        tableNumberLabel.setText("");
        tableNum = 1;

    }

    public NotificationGui() {
        this.setLocation(500, 300);
        this.setAlwaysOnTop(true);
        initComponents();
        tableNum = 1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        bodyPanel = new javax.swing.JPanel();
        tableLabel = new javax.swing.JLabel();
        tableNumberLabel = new javax.swing.JLabel();
        messageLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 300));
        setResizable(false);

        topPanel.setBackground(new java.awt.Color(85, 194, 237));

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finalproject/pic/logo_fahsai.png"))); // NOI18N

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoLabel, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        bodyPanel.setBackground(new java.awt.Color(255, 255, 255));

        tableLabel.setFont(new java.awt.Font("Leelawadee", 0, 36)); // NOI18N
        tableLabel.setText("Table:");

        tableNumberLabel.setFont(new java.awt.Font("Leelawadee", 0, 36)); // NOI18N
        tableNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tableNumberLabel.setText("0");

        messageLabel.setFont(new java.awt.Font("Leelawadee", 0, 36)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 51, 51));
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        messageLabel.setText("Need Assisatant....");

        okButton.setBackground(new java.awt.Color(51, 255, 102));
        okButton.setFont(new java.awt.Font("Leelawadee", 0, 48)); // NOI18N
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableLabel)
                .addGap(18, 18, 18)
                .addComponent(tableNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(okButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tableNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(messageLabel)
                    .addComponent(tableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        Counter.notiBarStatus[tableNum - 1] = false;
        if (Counter.table[tableNum - 1] == null) {
            Counter.setGuiTableButtonColor(tableNum, new Color(232, 224, 224));
        } else if (Counter.table[tableNum - 1].checkingBill) {
            Counter.setGuiTableButtonColor(tableNum, Color.red);
        } else {
            Counter.setGuiTableButtonColor(tableNum, new Color(192, 226, 202));
        }

    }//GEN-LAST:event_okButtonActionPerformed

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
            java.util.logging.Logger.getLogger(NotificationGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotificationGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotificationGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotificationGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotificationGui().setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel tableLabel;
    private javax.swing.JLabel tableNumberLabel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
