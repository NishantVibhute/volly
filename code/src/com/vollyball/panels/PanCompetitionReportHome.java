/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.bean.PlayerReportBean;
import com.vollyball.dao.ReportDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanCompetitionReportHome extends javax.swing.JPanel {

    ReportDao reportDao = new ReportDao();
    DefaultTableModel model;
    CompetitionBean cb;

    List<JPanel> panMenuList = new ArrayList<>();
    LinkedHashMap<Integer, PlayerReportBean> playerId = new LinkedHashMap<Integer, PlayerReportBean>();
    String skillName;
    int skillId;

    /**
     * Creates new form PanCompReportHome
     */
    public PanCompetitionReportHome(final CompetitionBean cb) {
        initComponents();
        this.cb = cb;
        lblComHeading.setText(cb.getName());
        panMatches.setBackground(new Color(57, 74, 108));
        panPlayer.setBackground(new Color(255, 255, 255));
        panMatches1.setBackground(new Color(255, 255, 255));
        lblMatch.setForeground(Color.WHITE);
        lblPlayer.setForeground(new Color(57, 74, 108));
        lblTeam.setForeground(new Color(57, 74, 108));

        PanMatchReport p = new PanMatchReport(cb);
        panReports.removeAll();
        panReports.add(p, BorderLayout.CENTER);
        panReports.validate();
        panReports.repaint();

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
        lblComHeading = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panReports = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        panMatches = new javax.swing.JPanel();
        lblMatch = new javax.swing.JLabel();
        panMatches1 = new javax.swing.JPanel();
        lblTeam = new javax.swing.JLabel();
        panPlayer = new javax.swing.JPanel();
        lblPlayer = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblComHeading.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblComHeading.setForeground(new java.awt.Color(244, 195, 1));
        lblComHeading.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblComHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(728, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblComHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panReports.setBackground(new java.awt.Color(255, 255, 255));
        panReports.setLayout(new java.awt.BorderLayout());

        panMatches.setBackground(new java.awt.Color(255, 255, 255));
        panMatches.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMatch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMatch.setForeground(new java.awt.Color(57, 74, 108));
        lblMatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMatch.setText("MATCHES");
        lblMatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panMatchesLayout = new javax.swing.GroupLayout(panMatches);
        panMatches.setLayout(panMatchesLayout);
        panMatchesLayout.setHorizontalGroup(
            panMatchesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panMatchesLayout.setVerticalGroup(
            panMatchesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMatch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panMatches1.setBackground(new java.awt.Color(255, 255, 255));
        panMatches1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTeam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTeam.setForeground(new java.awt.Color(57, 74, 108));
        lblTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeam.setText("TEAM");
        lblTeam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTeamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panMatches1Layout = new javax.swing.GroupLayout(panMatches1);
        panMatches1.setLayout(panMatches1Layout);
        panMatches1Layout.setHorizontalGroup(
            panMatches1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panMatches1Layout.setVerticalGroup(
            panMatches1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTeam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panPlayer.setBackground(new java.awt.Color(255, 255, 255));
        panPlayer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPlayer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPlayer.setForeground(new java.awt.Color(57, 74, 108));
        lblPlayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayer.setText("PLAYER");
        lblPlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPlayerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panPlayerLayout = new javax.swing.GroupLayout(panPlayer);
        panPlayer.setLayout(panPlayerLayout);
        panPlayerLayout.setHorizontalGroup(
            panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panPlayerLayout.setVerticalGroup(
            panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPlayer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panMatches, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panMatches1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panMatches, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panMatches1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReports, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblMatchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatchMouseClicked
        // TODO add your handling code here:
        panMatches.setBackground(new Color(57, 74, 108));
        panPlayer.setBackground(new Color(255, 255, 255));
        panMatches1.setBackground(new Color(255, 255, 255));
        lblMatch.setForeground(Color.WHITE);
        lblPlayer.setForeground(new Color(57, 74, 108));
        lblTeam.setForeground(new Color(57, 74, 108));

        PanMatchReport p = new PanMatchReport(cb);
        panReports.removeAll();
        panReports.add(p, BorderLayout.CENTER);
        panReports.validate();
        panReports.repaint();

    }//GEN-LAST:event_lblMatchMouseClicked

    private void lblTeamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTeamMouseClicked
        // TODO add your handling code here:
        panMatches1.setBackground(new Color(57, 74, 108));
        panPlayer.setBackground(new Color(255, 255, 255));
        panMatches.setBackground(new Color(255, 255, 255));

        lblTeam.setForeground(Color.WHITE);
        lblMatch.setForeground(new Color(57, 74, 108));
        lblPlayer.setForeground(new Color(57, 74, 108));
        PanTeamSkillWiseReport p = new PanTeamSkillWiseReport(cb);
        panReports.removeAll();
        panReports.add(p, BorderLayout.CENTER);
        panReports.validate();
        panReports.repaint();
    }//GEN-LAST:event_lblTeamMouseClicked

    private void lblPlayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPlayerMouseClicked
        // TODO add your handling code here:
        panPlayer.setBackground(new Color(57, 74, 108));
        panMatches.setBackground(new Color(255, 255, 255));
        panMatches1.setBackground(new Color(255, 255, 255));

        lblPlayer.setForeground(Color.WHITE);
        lblMatch.setForeground(new Color(57, 74, 108));
        lblTeam.setForeground(new Color(57, 74, 108));

        PanPlayerSkillWiseReport p = new PanPlayerSkillWiseReport(cb);
        panReports.removeAll();
        panReports.add(p, BorderLayout.CENTER);
        panReports.validate();
        panReports.repaint();

    }//GEN-LAST:event_lblPlayerMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblComHeading;
    private javax.swing.JLabel lblMatch;
    private javax.swing.JLabel lblPlayer;
    private javax.swing.JLabel lblTeam;
    private javax.swing.JPanel panMatches;
    private javax.swing.JPanel panMatches1;
    private javax.swing.JPanel panPlayer;
    private javax.swing.JPanel panReports;
    // End of variables declaration//GEN-END:variables
}
