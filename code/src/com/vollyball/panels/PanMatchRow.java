/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchBean;
import com.vollyball.dialog.CreateSelectTeamDialog;
import com.vollyball.enums.EvaluationType;

/**
 *
 * @author nishant.vibhute
 */
public class PanMatchRow extends javax.swing.JPanel {

    int matchId1;
    int matchId2;

    /**
     * Creates new form PanMatchRow
     */
    public PanMatchRow() {
        initComponents();
        panMatch1.setVisible(false);
        panMatch2.setVisible(false);
    }

    public void setMatch1(MatchBean mb) {
        panMatch1.setVisible(true);
        lblMatch1team1.setText(mb.getTeam1name());
        lblMatch1team2.setText(mb.getTeam2name());
        matchId1 = mb.getId();
    }

    public void setMatch2(MatchBean mb) {
        panMatch2.setVisible(true);
        lblMatch2team1.setText(mb.getTeam1name());
        lblMatch2team2.setText(mb.getTeam2name());
        matchId2 = mb.getId();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panMatch1 = new javax.swing.JPanel();
        lblMatch1team1 = new javax.swing.JLabel();
        lblMatch1team2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        set1live = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        set1post = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panMatch2 = new javax.swing.JPanel();
        lblMatch2team1 = new javax.swing.JLabel();
        lblMatch2team2 = new javax.swing.JLabel();
        set2live = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        panMatch1.setBackground(new java.awt.Color(255, 255, 255));
        panMatch1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panMatch1.setForeground(new java.awt.Color(57, 74, 108));

        lblMatch1team1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblMatch1team1.setForeground(new java.awt.Color(57, 74, 108));
        lblMatch1team1.setText("jLabel1");

        lblMatch1team2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblMatch1team2.setForeground(new java.awt.Color(57, 74, 108));
        lblMatch1team2.setText("jLabel1");

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        set1live.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        set1live.setForeground(new java.awt.Color(153, 255, 51));
        set1live.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        set1live.setText("LIVE");
        set1live.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                set1liveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(set1live, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(set1live, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(57, 74, 108));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        set1post.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        set1post.setForeground(new java.awt.Color(255, 51, 51));
        set1post.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        set1post.setText("POST");
        set1post.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                set1postMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(set1post, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(set1post, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(57, 74, 108));
        jLabel4.setText("V/S");

        javax.swing.GroupLayout panMatch1Layout = new javax.swing.GroupLayout(panMatch1);
        panMatch1.setLayout(panMatch1Layout);
        panMatch1Layout.setHorizontalGroup(
            panMatch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMatch1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMatch1team1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMatch1team2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panMatch1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        panMatch1Layout.setVerticalGroup(
            panMatch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMatch1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panMatch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panMatch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMatch1team2)
                        .addComponent(jLabel4))
                    .addComponent(lblMatch1team1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panMatch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblMatch2team1.setText("jLabel1");

        lblMatch2team2.setText("jLabel1");

        set2live.setBackground(new java.awt.Color(153, 255, 153));
        set2live.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                set2liveMouseClicked(evt);
            }
        });

        jLabel2.setText("LIVE");

        javax.swing.GroupLayout set2liveLayout = new javax.swing.GroupLayout(set2live);
        set2live.setLayout(set2liveLayout);
        set2liveLayout.setHorizontalGroup(
            set2liveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(set2liveLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        set2liveLayout.setVerticalGroup(
            set2liveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(set2liveLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panMatch2Layout = new javax.swing.GroupLayout(panMatch2);
        panMatch2.setLayout(panMatch2Layout);
        panMatch2Layout.setHorizontalGroup(
            panMatch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMatch2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblMatch2team1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMatch2team2)
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMatch2Layout.createSequentialGroup()
                .addContainerGap(313, Short.MAX_VALUE)
                .addComponent(set2live, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        panMatch2Layout.setVerticalGroup(
            panMatch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMatch2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panMatch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatch2team1)
                    .addComponent(lblMatch2team2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(set2live, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panMatch1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(panMatch2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panMatch2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panMatch1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel1MouseClicked

    private void set2liveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_set2liveMouseClicked
        // TODO add your handling code here:
        CreateSelectTeamDialog obj = new CreateSelectTeamDialog();
        obj.setValues(matchId2, EvaluationType.LIVE.getId());
        obj.init();
        obj.show();
    }//GEN-LAST:event_set2liveMouseClicked

    private void set1liveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_set1liveMouseClicked
        // TODO add your handling code here:
        CreateSelectTeamDialog obj = new CreateSelectTeamDialog();
        obj.setValues(matchId1, EvaluationType.LIVE.getId());
        obj.init();
        obj.show();
    }//GEN-LAST:event_set1liveMouseClicked

    private void set1postMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_set1postMouseClicked
        // TODO add your handling code here:
        CreateSelectTeamDialog obj = new CreateSelectTeamDialog();
        obj.setValues(matchId1, EvaluationType.POST.getId());
        obj.init();
        obj.show();
    }//GEN-LAST:event_set1postMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblMatch1team1;
    private javax.swing.JLabel lblMatch1team2;
    private javax.swing.JLabel lblMatch2team1;
    private javax.swing.JLabel lblMatch2team2;
    private javax.swing.JPanel panMatch1;
    private javax.swing.JPanel panMatch2;
    private javax.swing.JLabel set1live;
    private javax.swing.JLabel set1post;
    private javax.swing.JPanel set2live;
    // End of variables declaration//GEN-END:variables
}
