/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 * @author Mnenmenth
 */
public class OptionMenuPanel extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton apply;
    public javax.swing.JButton back;
    public javax.swing.JFormattedTextField backKB;
    public javax.swing.JButton debug;
    public javax.swing.JButton defaultKB;
    public javax.swing.JFormattedTextField eatKB;
    public javax.swing.JFormattedTextField fwdKB;
    public javax.swing.JFormattedTextField leftKB;
    public javax.swing.JFormattedTextField rightKB;
    public javax.swing.JFormattedTextField spellKB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    /**
     * Creates new form optionPanel
     */
    public OptionMenuPanel() {
        initComponents();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fwdKB = new javax.swing.JFormattedTextField();
        leftKB = new javax.swing.JFormattedTextField();
        rightKB = new javax.swing.JFormattedTextField();
        spellKB = new javax.swing.JFormattedTextField();
        eatKB = new javax.swing.JFormattedTextField();
        backKB = new javax.swing.JFormattedTextField();
        apply = new javax.swing.JButton();
        defaultKB = new javax.swing.JButton();
        back = new javax.swing.JButton();
        debug = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setPreferredSize(new java.awt.Dimension(750, 500));

        jLabel1.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        jLabel1.setText("Forward Directional Keybind");

        jLabel2.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        jLabel2.setText("Backwards Directional Keybind");

        jLabel3.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        jLabel3.setText("Left Directional Keybind");

        jLabel4.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        jLabel4.setText("Right Directional Keybind");

        jLabel5.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        jLabel5.setText("Skip Keybind");

        jLabel6.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        jLabel6.setText("Pause Keybind");

        fwdKB.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N

        leftKB.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N

        rightKB.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N

        spellKB.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N

        eatKB.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N

        backKB.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N

        apply.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        apply.setText("Apply");

        defaultKB.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        defaultKB.setText("Default Keybinds");

        back.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        debug.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        debug.setText("Debug Menu");
        debug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(80, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(eatKB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spellKB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rightKB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(leftKB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backKB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fwdKB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(defaultKB, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(apply, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(58, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(317, Short.MAX_VALUE)
                                .addComponent(debug)
                                .addGap(298, 298, 298))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(139, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel2)
                                                .addGap(17, 17, 17)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(1, 1, 1)
                                                                .addComponent(jLabel3))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(leftKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(14, 14, 14)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel4)
                                                                .addGap(21, 21, 21)
                                                                .addComponent(jLabel5))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(1, 1, 1)
                                                                .addComponent(rightKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(19, 19, 19)
                                                                .addComponent(spellKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel6)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(1, 1, 1)
                                                                .addComponent(eatKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(fwdKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(backKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(apply)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(defaultKB)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(back)))))
                                .addGap(52, 52, 52)
                                .addComponent(debug)
                                .addGap(67, 67, 67))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void debugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_debugActionPerformed
    // End of variables declaration//GEN-END:variables
}
