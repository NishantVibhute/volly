/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.bean.VollyCourtCoordinateBean;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.Rating;
import com.vollyball.enums.Skill;
import com.vollyball.enums.SkillsDescCriteria;
import com.vollyball.util.CommonUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Supriya
 */
public class PanMatchDiagram extends javax.swing.JPanel {

    RallyDao rd = new RallyDao();
    MatchDao matchDao = new MatchDao();
    String team1Name, team2Name;
    int team1id, team2id;
    int compId;
    int matchId;
    TeamDao teamDao = new TeamDao();

    /**
     * Creates new form DialogMatchDiagram
     */
    public PanMatchDiagram(int compId, int matchId) {
        initComponents();

        MatchBean team = matchDao.getMatchesById(compId, matchId);
        team1id = team.getTeam1();
        team2id = team.getTeam2();
        team1Name = team.getTeam1name();
        team2Name = team.getTeam2name();
        cmbTeam.addItem(team1Name);
        cmbTeam.addItem(team2Name);
        this.compId = compId;
        this.matchId = matchId;

        for (Skill dir : Skill.values()) {
            cmbSkill.addItem(dir.getType());
        }

        for (Rating dir : Rating.values()) {
            cmbRating.addItem("" + dir.getId());
        }

        lblMatch.setText(team1Name + " vs " + team2Name);

    }

    public void setCourt(int matchId, int teamId, int skill, int rating) {
        panGraph.removeAll();
        List<RallyEvaluationSkillScore> rallyDetailsList = null;

        rallyDetailsList = rd.getRallyDetailsOfMatch(teamId, matchId, skill, rating);
        int home = 0, opp = 0;
        String type = null;
        List<VollyCourtCoordinateBean> listCCB = new ArrayList<>();
//        lblAttempt.setText("" + rallyDetailsList.size());
        if (!rallyDetailsList.isEmpty()) {
            for (RallyEvaluationSkillScore ress : rallyDetailsList) {

                int skillId = ress.getSkillId();
                String chestNum = ress.getChestNo();
                LinkedHashMap<Integer, String> Dig = ress.getDetailsValues();
                if (!Dig.isEmpty()) {
                    if (skillId == Skill.Service.getId()) {
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.ServiceD.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.ServiceE.getId()));
                        type = Skill.getNameById(skillId).getType();
                    }

                    if (skillId == Skill.Attack.getId()) {
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.AttackE.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.AttackF.getId()));
                        type = Skill.getNameById(skillId).getType();
                    }

                    if (skillId == Skill.Set.getId()) {
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.SetF.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.SetG.getId()));
                        type = Skill.getNameById(skillId).getType() + "H";
                    }

                    if (skillId == Skill.Reception.getId()) {
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.ReceptionC.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.ReceptionD.getId()));
                        type = Skill.getNameById(skillId).getType();
                    }

                    if (skillId == Skill.Defence.getId()) {
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.DefenceH.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.DefenceI.getId()));
                        type = Skill.getNameById(skillId).getType();
                    }

                    if (skillId == Skill.Block.getId()) {

                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.BlockF.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.BlockG.getId()));
                        type = Skill.getNameById(skillId).getType() + "Attack";
                        Color c = CommonUtil.getColorONScore("" + ress.getScore());
                        VollyCourtCoordinateBean v = rd.getCordinates(type, home, opp);
                        v.setColor(c);
                        listCCB.add(v);

                        String court = Dig.get(SkillsDescCriteria.BlockM.getId());
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.BlockG.getId()));
                        if (court.equalsIgnoreCase("H")) {
                            type = Skill.getNameById(skillId).getType() + "RH";

                        } else {
                            type = Skill.getNameById(skillId).getType() + "RO";
                        }

                        String oppH = Dig.get(SkillsDescCriteria.BlockH.getId());

                        switch (oppH) {
                            case "LOC":
                                opp = 7;
                                break;
                            case "ROC":
                                opp = 8;
                                break;
                            case "BOC":
                                opp = 9;
                                break;
                            default:
                                opp = Integer.parseInt(oppH);
                                break;
                        }
                        VollyCourtCoordinateBean v1 = rd.getCordinates(type, home, opp);
                        listCCB.add(v1);
                    }

                    if (skillId != Skill.Block.getId()) {
                        Color c = CommonUtil.getColorONScore("" + ress.getScore());
                        VollyCourtCoordinateBean v = rd.getCordinates(type, home, opp);
                        v.setColor(c);
                        listCCB.add(v);
                        v.setChestNum(chestNum);
                    }
                    PanVolleyCourt panMatch = new PanVolleyCourt();
                    panMatch.setValues(listCCB, "Report");
                    panGraph.add(panMatch, BorderLayout.CENTER);
                } else {
                    panGraph.add(lblNoData, BorderLayout.CENTER);
                }

            }
        } else {
            panGraph.add(lblNoData, BorderLayout.CENTER);
        }
        validate();
        repaint();
    }

    public void printComponenet(final Component comp) {

        PageFormat documentPageFormat = new PageFormat();
        documentPageFormat.setOrientation(PageFormat.LANDSCAPE);

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("Score Report");

        pj.setPrintable(new Printable() {
            public int print(Graphics g, PageFormat format, int page_index)
                    throws PrinterException {
                if (page_index > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                format.setOrientation(PageFormat.LANDSCAPE);
                // get the bounds of the component
                Dimension dim = comp.getSize();
                double cHeight = dim.getHeight();
                double cWidth = dim.getWidth();

                // get the bounds of the printable area
                double pHeight = format.getImageableHeight();
                double pWidth = format.getImageableWidth();

                double pXStart = format.getImageableX();
                double pYStart = format.getImageableY();

                double xRatio = pWidth / cWidth;
                double yRatio = pHeight / cHeight;

                Graphics2D g2 = (Graphics2D) g;
                g2.translate(pXStart, pYStart);
                g2.scale(xRatio, yRatio);
                comp.printAll(g2);

                return Printable.PAGE_EXISTS;
            }
        }, documentPageFormat);
        if (pj.printDialog() == false) {
            return;
        }

        try {
            pj.print();
        } catch (PrinterException ex) {
            // handle exception
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
        jLabel1 = new javax.swing.JLabel();
        cmbTeam = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbSkill = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbRating = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMatch = new javax.swing.JLabel();
        panPrint2 = new javax.swing.JPanel();
        lblPrint2 = new javax.swing.JLabel();
        panGraph = new javax.swing.JPanel();
        lblNoData = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(57, 74, 108)));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Team : ");

        cmbTeam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("SKILL : ");

        cmbSkill.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbSkill.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL" }));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("RATING : ");

        cmbRating.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbRating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL" }));

        jPanel3.setBackground(new java.awt.Color(57, 74, 108));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SEARCH");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Match : ");

        lblMatch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        panPrint2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPrint2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblPrint2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/printer.png"))); // NOI18N
        lblPrint2.setToolTipText("Print This Page");
        lblPrint2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrint2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panPrint2Layout = new javax.swing.GroupLayout(panPrint2);
        panPrint2.setLayout(panPrint2Layout);
        panPrint2Layout.setHorizontalGroup(
            panPrint2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );
        panPrint2Layout.setVerticalGroup(
            panPrint2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTeam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbRating, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(187, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbSkill, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panPrint2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panPrint2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbSkill, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbRating)
                    .addComponent(cmbTeam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panGraph.setBackground(new java.awt.Color(255, 255, 255));
        panGraph.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(57, 74, 108)));
        panGraph.setLayout(new java.awt.BorderLayout());

        lblNoData.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNoData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoData.setText("No Data Available");
        panGraph.add(lblNoData, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 818, Short.MAX_VALUE)
            .addComponent(panGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        String team = (String) cmbTeam.getSelectedItem();
        int teamId = 0;
        if (team.equalsIgnoreCase(team1Name)) {
            teamId = team1id;
        } else if (team.equalsIgnoreCase(team2Name)) {
            teamId = team2id;
        }

        String skill = (String) cmbSkill.getSelectedItem();
        int skillId;
        if (skill.equalsIgnoreCase("ALL")) {
            skillId = 0;

        } else {
            skillId = Skill.getIdByName(skill).getId();
        }

        String r = (String) cmbRating.getSelectedItem();
        int rating;
        if (r.equalsIgnoreCase("ALL")) {
            rating = 0;
        } else {
            rating = Integer.parseInt(r);
        }

        setCourt(teamId, this.matchId, skillId, rating);

    }//GEN-LAST:event_jLabel4MouseClicked

    private void lblPrint2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrint2MouseClicked
        // TODO add your handling code here:
        panPrint2.setVisible(false);

        printComponenet(this);
        panPrint2.setVisible(true);
    }//GEN-LAST:event_lblPrint2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbRating;
    private javax.swing.JComboBox<String> cmbSkill;
    private javax.swing.JComboBox<String> cmbTeam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblMatch;
    private javax.swing.JLabel lblNoData;
    private javax.swing.JLabel lblPrint2;
    private javax.swing.JPanel panGraph;
    private javax.swing.JPanel panPrint2;
    // End of variables declaration//GEN-END:variables
}
