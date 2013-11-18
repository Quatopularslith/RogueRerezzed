/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

/**
 *
 * @author 1003749
 */
public class MainMenuPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainMenu
     */
    public MainMenuPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        newGame = new javax.swing.JButton();
        loadGame = new javax.swing.JButton();
        options = new javax.swing.JButton();
        quit = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(750, 500));

        title.setFont(new java.awt.Font("Hobo Std", 0, 48)); // NOI18N
        title.setText("Rogue Rerezzed");

        newGame.setFont(new java.awt.Font("Hobo Std", 0, 36)); // NOI18N
        newGame.setText("New Game");

        loadGame.setFont(new java.awt.Font("Hobo Std", 0, 36)); // NOI18N
        loadGame.setText("Load Game");

        options.setFont(new java.awt.Font("Hobo Std", 0, 36)); // NOI18N
        options.setText("Options");

        quit.setFont(new java.awt.Font("Hobo Std", 0, 36)); // NOI18N
        quit.setText("Quit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(198, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(newGame)
                    .addComponent(loadGame)
                    .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(newGame)
                .addGap(18, 18, 18)
                .addComponent(loadGame)
                .addGap(18, 18, 18)
                .addComponent(options)
                .addGap(18, 18, 18)
                .addComponent(quit)
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton loadGame;
    public javax.swing.JButton newGame;
    public javax.swing.JButton options;
    public javax.swing.JButton quit;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
