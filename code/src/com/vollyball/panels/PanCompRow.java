/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.controller.Controller;
import java.awt.Dimension;

/**
 *
 * @author nishant.vibhute
 */
public class PanCompRow extends javax.swing.JPanel {

    CompetitionBean cb;

    /**
     * Creates new form PanCompRow
     */
    public PanCompRow(CompetitionBean cb) {
        this.cb = cb;
        initComponents();
        lblCompName.setText(cb.getName());
        lblVenue.setText(cb.getVenue());
        lblStartDate.setText(cb.getStartDate());
        lblEndDate.setText(cb.getEndDate());
        lblGroup.setText(cb.getAgeGroup());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblCompName = new javax.swing.JLabel();
        lblVenue = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        lblEndDate = new javax.swing.JLabel();
        lblGroup = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        lblCompName.setText("jLabel1");

        lblVenue.setText("jLabel1");

        lblStartDate.setText("jLabel2");

        lblEndDate.setText("jLabel3");

        lblGroup.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblStartDate)
                                .addGap(98, 98, 98)
                                .addComponent(lblEndDate))
                            .addComponent(lblCompName)
                            .addComponent(lblGroup))
                        .addContainerGap(408, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblVenue)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCompName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVenue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStartDate)
                    .addComponent(lblEndDate))
                .addGap(18, 18, 18)
                .addComponent(lblGroup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:

        Controller.panComptitionHome = new PanComptitionHome(cb);
//
        Dimension dim = Controller.frmDashBoard.panContent.getSize();
//        Controller.frmDashBoard.panCompHeading.setVisible(false);
        Controller.frmDashBoard.panCompListValue.setVisible(false);

        Controller.panComptitionHome.setBounds(0, 0, 800, 686);

        Controller.frmDashBoard.panContent.add(Controller.panComptitionHome);

    }//GEN-LAST:event_jPanel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCompName;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblGroup;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblVenue;
    // End of variables declaration//GEN-END:variables
}