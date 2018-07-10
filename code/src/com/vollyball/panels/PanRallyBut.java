package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.controller.Controller;
import java.util.LinkedHashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nishant.vibhute
 */
public class PanRallyBut extends javax.swing.JPanel {

    int rallyNum;
    int evaluationId;
    int evaluationType;
    public LinkedHashMap<Integer, Player> positionMap;

    /**
     * Creates new form PanRallyBut
     */
    public PanRallyBut() {
        initComponents();
    }

    public void setRally(int i, int evaluationId, LinkedHashMap<Integer, Player> positionMap, int evaluationType) {
        butRally.setText("" + i);
        this.rallyNum = i;
        this.evaluationId = evaluationId;
        this.positionMap = positionMap;
        this.evaluationType = evaluationType;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        butRally = new javax.swing.JLabel();

        setBackground(new java.awt.Color(54, 78, 108));
        setForeground(new java.awt.Color(54, 78, 108));

        butRally.setBackground(new java.awt.Color(255, 255, 255));
        butRally.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        butRally.setForeground(new java.awt.Color(255, 255, 255));
        butRally.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        butRally.setText("jLabel1");
        butRally.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butRallyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butRally, javax.swing.GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butRally, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void butRallyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butRallyMouseClicked
        Controller.panMatchSet.rallyNumNext = rallyNum;
        Controller.panMatchSet.panRallyCurrent = new PanRallyLiveEvaluation(rallyNum, evaluationId, positionMap, evaluationType);
        Controller.panMatchSet.panNext.setVisible(true);
        Controller.panMatchSet.panRallyShow.removeAll();
        Controller.panMatchSet.panRallyShow.add(Controller.panMatchSet.panRallyCurrent);
        Controller.panMatchSet.setBackNextVisible();
        Controller.panMatchSet.validate();
        Controller.panMatchSet.repaint();

    }//GEN-LAST:event_butRallyMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel butRally;
    // End of variables declaration//GEN-END:variables
}
