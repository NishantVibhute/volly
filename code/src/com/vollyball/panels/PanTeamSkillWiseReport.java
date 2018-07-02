/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.bean.PlayerReportBean;
import com.vollyball.dao.ReportDao;
import com.vollyball.dialog.CreateDialogPanMatchWiseReport;
import com.vollyball.enums.Skill;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author nishant.vibhute
 */
public class PanTeamSkillWiseReport extends javax.swing.JPanel {

    ReportDao reportDao = new ReportDao();
    DefaultTableModel model;
    CompetitionBean cb;

    List<JPanel> panMenuList = new ArrayList<>();
    LinkedHashMap<Integer, PlayerReportBean> mapData = new LinkedHashMap<Integer, PlayerReportBean>();
    String skillName;
    int skillId;

    /**
     * Creates new form PanTeamSkillWiseReport
     */
    public PanTeamSkillWiseReport(final CompetitionBean cb) {
        initComponents();
        this.cb = cb;
        Color heading = new Color(204, 204, 204);
        model = (DefaultTableModel) tbReport.getModel();
        JTableHeader header = tbReport.getTableHeader();
        header.setBackground(heading);
//
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 35));
        header.setDefaultRenderer(new TableHeaderRenderer(tbReport));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//
        tbReport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        tbReport.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        Color ivory = new Color(255, 255, 255);
        tbReport.setOpaque(true);
        tbReport.setFillsViewportHeight(true);
        tbReport.setBackground(ivory);

        panMenuList.add(panServer1);
        panMenuList.add(panAttacker);
        panMenuList.add(panBlocker);
        panMenuList.add(panSetter);
        panMenuList.add(panReceiver);
        panMenuList.add(panDefender);

        tbReport.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    String selectedName = null;

                    int row = tbReport.getSelectedRow();
                    for (int i = 0; i <= row; i++) {
                        selectedName = (String) tbReport.getValueAt(row, 0);

                    }

                    if (selectedName != null) {
                        CreateDialogPanMatchWiseReport createDialogPanMatchWiseReport = new CreateDialogPanMatchWiseReport();
                        createDialogPanMatchWiseReport.setValues(mapData.get(row), skillName, skillId, cb.getId(), "team");
                        createDialogPanMatchWiseReport.init();
                        createDialogPanMatchWiseReport.show();
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        panServer1 = new javax.swing.JPanel();
        lblServer1 = new javax.swing.JLabel();
        panAttacker = new javax.swing.JPanel();
        lblAttacker = new javax.swing.JLabel();
        panBlocker = new javax.swing.JPanel();
        lblBlocker = new javax.swing.JLabel();
        panDefender = new javax.swing.JPanel();
        lblDefender = new javax.swing.JLabel();
        panReceiver = new javax.swing.JPanel();
        lblReceiver = new javax.swing.JLabel();
        panSetter = new javax.swing.JPanel();
        lblSetter = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        panSkillReports = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lblReportHeading = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbReport = new javax.swing.JTable();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panServer1.setBackground(new java.awt.Color(255, 255, 255));
        panServer1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblServer1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblServer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServer1.setText("BEST SERVER");
        lblServer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblServer1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panServer1Layout = new javax.swing.GroupLayout(panServer1);
        panServer1.setLayout(panServer1Layout);
        panServer1Layout.setHorizontalGroup(
            panServer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblServer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panServer1Layout.setVerticalGroup(
            panServer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblServer1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panAttacker.setBackground(new java.awt.Color(255, 255, 255));
        panAttacker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblAttacker.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAttacker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAttacker.setText("BEST ATTACKER");
        lblAttacker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAttackerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panAttackerLayout = new javax.swing.GroupLayout(panAttacker);
        panAttacker.setLayout(panAttackerLayout);
        panAttackerLayout.setHorizontalGroup(
            panAttackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttacker, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
        );
        panAttackerLayout.setVerticalGroup(
            panAttackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttacker, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panBlocker.setBackground(new java.awt.Color(255, 255, 255));
        panBlocker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblBlocker.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblBlocker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBlocker.setText("BEST BLOCKER");
        lblBlocker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBlockerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panBlockerLayout = new javax.swing.GroupLayout(panBlocker);
        panBlocker.setLayout(panBlockerLayout);
        panBlockerLayout.setHorizontalGroup(
            panBlockerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBlockerLayout.createSequentialGroup()
                .addComponent(lblBlocker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        panBlockerLayout.setVerticalGroup(
            panBlockerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlocker, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panDefender.setBackground(new java.awt.Color(255, 255, 255));
        panDefender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDefender.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDefender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDefender.setText("BEST DEFENDER");
        lblDefender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDefenderMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panDefenderLayout = new javax.swing.GroupLayout(panDefender);
        panDefender.setLayout(panDefenderLayout);
        panDefenderLayout.setHorizontalGroup(
            panDefenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panDefenderLayout.setVerticalGroup(
            panDefenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefender, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panReceiver.setBackground(new java.awt.Color(255, 255, 255));
        panReceiver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblReceiver.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblReceiver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReceiver.setText("BEST RECEIVER");
        lblReceiver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReceiverMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panReceiverLayout = new javax.swing.GroupLayout(panReceiver);
        panReceiver.setLayout(panReceiverLayout);
        panReceiverLayout.setHorizontalGroup(
            panReceiverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReceiver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panReceiverLayout.setVerticalGroup(
            panReceiverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReceiver, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panSetter.setBackground(new java.awt.Color(255, 255, 255));
        panSetter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSetter.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSetter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSetter.setText("BEST SETTER");
        lblSetter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSetterLayout = new javax.swing.GroupLayout(panSetter);
        panSetter.setLayout(panSetterLayout);
        panSetterLayout.setHorizontalGroup(
            panSetterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSetter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSetterLayout.setVerticalGroup(
            panSetterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSetter, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(57, 74, 108));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SKILLS");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panServer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panAttacker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panBlocker, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSetter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panDefender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panReceiver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panServer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panAttacker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panBlocker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panSetter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panDefender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panSkillReports.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        lblReportHeading.setBackground(new java.awt.Color(255, 255, 255));
        lblReportHeading.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblReportHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReportHeading.setText("SELECT SKILL");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblReportHeading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReportHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        tbReport.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TEAM NAME", "TOTAL ATTEMPT", "SUCCESSFUL ATTEMPT", "SUCCESS RATE "
            }
        ));
        tbReport.setOpaque(false);
        tbReport.setRowHeight(30);
        jScrollPane1.setViewportView(tbReport);

        javax.swing.GroupLayout panSkillReportsLayout = new javax.swing.GroupLayout(panSkillReports);
        panSkillReports.setLayout(panSkillReportsLayout);
        panSkillReportsLayout.setHorizontalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        panSkillReportsLayout.setVerticalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panSkillReports, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblDefenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDefenderMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Defence.getId());
        lblReportHeading.setText(lblDefender.getText());
        skillName = lblDefender.getText();
        changeColor(panDefender);
    }//GEN-LAST:event_lblDefenderMouseClicked

    private void lblReceiverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReceiverMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Reception.getId());
        lblReportHeading.setText(lblReceiver.getText());
        skillName = lblReceiver.getText();
        changeColor(panReceiver);
    }//GEN-LAST:event_lblReceiverMouseClicked

    private void lblSetterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetterMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Set.getId());
        lblReportHeading.setText(lblSetter.getText());
        skillName = lblSetter.getText();
        changeColor(panSetter);
    }//GEN-LAST:event_lblSetterMouseClicked

    private void lblBlockerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBlockerMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Block.getId());
        lblReportHeading.setText(lblBlocker.getText());
        skillName = lblBlocker.getText();
        changeColor(panBlocker);
    }//GEN-LAST:event_lblBlockerMouseClicked

    private void lblAttackerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAttackerMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Attack.getId());
        lblReportHeading.setText(lblAttacker.getText());
        skillName = lblAttacker.getText();
        changeColor(panAttacker);
    }//GEN-LAST:event_lblAttackerMouseClicked

    private void lblServer1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblServer1MouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Service.getId());
        lblReportHeading.setText(lblServer1.getText());
        skillName = lblServer1.getText();
        changeColor(panServer);
    }//GEN-LAST:event_lblServer1MouseClicked

    public void changeColor(JPanel panIn) {
        for (JPanel pan : panMenuList) {
            if (pan == panIn) {
                pan.setBackground(new Color(204, 204, 204));
            } else {
                pan.setBackground(Color.WHITE);
            }
        }
    }

    public void setPlayerReport(int skillid) {

        this.skillId = skillid;
//        if(model.getRowCount()!=0)
//        {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        List<PlayerReportBean> pbList = reportDao.getTeamReportList(skillid, cb.getId());
        int i = 0;
        for (PlayerReportBean pb : pbList) {
            mapData.put(i, pb);
            Object[] row = {pb.getName(), pb.getTotal(), pb.getSuccess(), pb.getSuccessrate()};
            model.addRow(row);
            i++;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAttacker;
    private javax.swing.JLabel lblBlocker;
    private javax.swing.JLabel lblDefender;
    private javax.swing.JLabel lblReceiver;
    private javax.swing.JLabel lblReportHeading;
    private javax.swing.JLabel lblServer;
    private javax.swing.JLabel lblServer1;
    private javax.swing.JLabel lblSetter;
    private javax.swing.JPanel panAttacker;
    private javax.swing.JPanel panBlocker;
    private javax.swing.JPanel panDefender;
    private javax.swing.JPanel panReceiver;
    private javax.swing.JPanel panServer;
    private javax.swing.JPanel panServer1;
    private javax.swing.JPanel panSetter;
    private javax.swing.JPanel panSkillReports;
    private javax.swing.JTable tbReport;
    // End of variables declaration//GEN-END:variables
}
