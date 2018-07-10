/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.PlayerReportBean;
import com.vollyball.dao.ReportDao;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanTeamPlayerDeails extends javax.swing.JPanel {

    ReportDao reportDao = new ReportDao();
    DefaultTableModel modelMatchwise;

    /**
     * Creates new form PanTeamPlayerDeails
     */
    public PanTeamPlayerDeails(PlayerReportBean prb, String SkillName, int skillId, int compId, int matchId) {
        initComponents();
        lblTeamName.setText(prb.getName());
        modelMatchwise = (DefaultTableModel) tbPlayerreport.getModel();
        List<PlayerReportBean> pbList = reportDao.getTeamPlayerReportList(skillId, compId, matchId, prb.getId());
        int i = 0;
        for (PlayerReportBean pb : pbList) {

            Object[] rowMatch = {pb.getName(), pb.getTotal(), pb.getSuccess(), pb.getSuccessrate()};
            modelMatchwise.addRow(rowMatch);
            i++;
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

        jPanel1 = new javax.swing.JPanel();
        lblTeamName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPlayerreport = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(58, 74, 108));

        lblTeamName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTeamName.setForeground(new java.awt.Color(255, 255, 255));
        lblTeamName.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTeamName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTeamName, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        tbPlayerreport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PLAYER NAME", "TOTAL ATTEMPT", "SUCCESSFUL ATTEMPT", "SUCCESS RATE"
            }
        ));
        tbPlayerreport.setRowHeight(30);
        jScrollPane1.setViewportView(tbPlayerreport);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("PLAYERS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTeamName;
    private javax.swing.JTable tbPlayerreport;
    // End of variables declaration//GEN-END:variables
}