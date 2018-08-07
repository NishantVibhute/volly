/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerScores;
import com.vollyball.bean.PlayerSkillScore;
import com.vollyball.chart.BarChart;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.Skill;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Supriya
 */
public class PanMatchChart extends javax.swing.JPanel {

    ReportDao rd = new ReportDao();
    MatchDao matchDao = new MatchDao();
    String team1Name, team2Name;
    int team1id, team2id;
    int compId;
    int matchId;
    TeamDao teamDao = new TeamDao();

    /**
     * Creates new form PanMatchChart
     */
    public PanMatchChart(int compId, int matchId) {
        initComponents();
        panPlayer.setVisible(false);
        MatchBean team = matchDao.getMatchesById(compId, matchId);
        team1id = team.getTeam1();
        team2id = team.getTeam2();
        team1Name = team.getTeam1name();
        team2Name = team.getTeam2name();
        cmbTeam.addItem(team1Name);
        cmbTeam.addItem(team2Name);
        this.compId = compId;
        this.matchId = matchId;
        createBothTeamChart();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panPlayer = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmbPlayer = new javax.swing.JComboBox<>();
        panPrint2 = new javax.swing.JPanel();
        lblPrint2 = new javax.swing.JLabel();
        panChart = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("TEAM : ");

        cmbTeam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbTeam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Both Team" }));
        cmbTeam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTeamItemStateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SEARCH");
        jLabel2.setToolTipText("");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("PLAYER : ");

        cmbPlayer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "INDIVIDUAL" }));

        javax.swing.GroupLayout panPlayerLayout = new javax.swing.GroupLayout(panPlayer);
        panPlayer.setLayout(panPlayerLayout);
        panPlayerLayout.setHorizontalGroup(
            panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPlayerLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panPlayerLayout.setVerticalGroup(
            panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmbPlayer)
        );

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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panPrint2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panPrint2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panChart.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panChart, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panChart, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        panChart.removeAll();
        String item = (String) cmbTeam.getSelectedItem();

        if (item.equalsIgnoreCase("Both Team")) {
            createBothTeamChart();
        } else if (item.equals(team1Name)) {
            String player = (String) cmbPlayer.getSelectedItem();
            if (player.equalsIgnoreCase("all")) {
                createTeamWiseTeamChart(team1id);
            } else {
                List<Player> playerList = teamDao.getTeamPlayers(team1id);
                createTeamPlayerChart(team1id, playerList);
            }
        } else if (item.equals(team2Name)) {
            String player = (String) cmbPlayer.getSelectedItem();
            if (player.equalsIgnoreCase("all")) {
                createTeamWiseTeamChart(team2id);
            } else {
                List<Player> playerList = teamDao.getTeamPlayers(team2id);
                createTeamPlayerChart(team2id, playerList);

            }
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void cmbTeamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTeamItemStateChanged
        // TODO add your handling code here:
        String item = (String) cmbTeam.getSelectedItem();
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (item.equalsIgnoreCase("Both Team")) {
                panPlayer.setVisible(false);
            } else if (item.equals(team1Name)) {
                panPlayer.setVisible(true);
            } else if (item.equals(team2Name)) {
                panPlayer.setVisible(true);
            }
            this.validate();
            this.repaint();
        }
    }//GEN-LAST:event_cmbTeamItemStateChanged

    private void lblPrint2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrint2MouseClicked
        // TODO add your handling code here:
        panPrint2.setVisible(false);

        printComponenet(this);
        panPrint2.setVisible(true);

    }//GEN-LAST:event_lblPrint2MouseClicked

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

    public void createBothTeamChart() {
        List<PlayerScores> list = rd.getMatchChart(compId, matchId);

        DefaultCategoryDataset datasetBlock = new DefaultCategoryDataset();

        for (PlayerScores p : list) {
            datasetBlock.addValue(p.getServiceRate(), "" + p.getTeamName(), "Service");
            datasetBlock.addValue(p.getAttackRate(), "" + p.getTeamName(), "Attack");
            datasetBlock.addValue(p.getBlockRate(), "" + p.getTeamName(), "Block");
            datasetBlock.addValue(p.getSetRate(), "" + p.getTeamName(), "Set");
            datasetBlock.addValue(p.getReceptionRate(), "" + p.getTeamName(), "Reception");
            datasetBlock.addValue(p.getDefenceRate(), "" + p.getTeamName(), "Defence");
        }

        JFreeChart chartFreeBlock = BarChart.createChart(datasetBlock, "Success Rate", "Skills", true);
        BarRenderer r = (BarRenderer) chartFreeBlock.getCategoryPlot().getRenderer();

//        r.setSeriesPaint(0, new Color(57, 74, 108));
        ChartPanel panel = new ChartPanel(chartFreeBlock);
        panChart.add(panel, BorderLayout.CENTER);
        validate();
        repaint();
    }

    public void createTeamPlayerChart(int teamId, List<Player> playerList) {
        List<PlayerScores> list = rd.getMatchChartForPlayer(playerList, compId, matchId, teamId);

        DefaultCategoryDataset datasetBlock = new DefaultCategoryDataset();

        for (PlayerScores p : list) {
            datasetBlock.addValue(p.getServiceRate(), "Service", p.getTeamName());
            datasetBlock.addValue(p.getAttackRate(), "Attack", "" + p.getTeamName());
            datasetBlock.addValue(p.getBlockRate(), "Block", p.getTeamName());
            datasetBlock.addValue(p.getSetRate(), "Set", p.getTeamName());
            datasetBlock.addValue(p.getReceptionRate(), "Reception", p.getTeamName());
            datasetBlock.addValue(p.getDefenceRate(), "Defence", p.getTeamName());
        }

        JFreeChart chartFreeBlock = BarChart.createChart(datasetBlock, "Success Rate", "Players", true);
        BarRenderer r = (BarRenderer) chartFreeBlock.getCategoryPlot().getRenderer();

//        r.setSeriesPaint(0, new Color(57, 74, 108));
        ChartPanel panel = new ChartPanel(chartFreeBlock);
        panChart.add(panel, BorderLayout.CENTER);
        validate();
        repaint();
    }

    public void createTeamWiseTeamChart(int teamId) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        PlayerSkillScore pservice = rd.getTeamSkillWiseScoreReport(compId, Skill.Service.getId(), matchId, teamId);
        PlayerSkillScore pAttack = rd.getTeamSkillWiseScoreReport(compId, Skill.Attack.getId(), matchId, teamId);
        PlayerSkillScore pBlock = rd.getTeamSkillWiseScoreReport(compId, Skill.Block.getId(), matchId, teamId);
        PlayerSkillScore pSet = rd.getTeamSkillWiseScoreReport(compId, Skill.Set.getId(), matchId, teamId);
        PlayerSkillScore pReception = rd.getTeamSkillWiseScoreReport(compId, Skill.Reception.getId(), matchId, teamId);
        PlayerSkillScore pDefence = rd.getTeamSkillWiseScoreReport(compId, Skill.Defence.getId(), matchId, teamId);

        dataset.addValue(pservice.getOne(), "One", "Service");
        dataset.addValue(pAttack.getOne(), "One", "Attack");
        dataset.addValue(pBlock.getOne(), "One", "Block");
        dataset.addValue(pSet.getOne(), "One", "Set");
        dataset.addValue(pReception.getOne(), "One", "Reception");
        dataset.addValue(pDefence.getOne(), "One", "Defence");

        dataset.addValue(pservice.getTwo(), "Two", "Service");
        dataset.addValue(pAttack.getTwo(), "Two", "Attack");
        dataset.addValue(pBlock.getTwo(), "Two", "Block");
        dataset.addValue(pSet.getTwo(), "Two", "Set");
        dataset.addValue(pReception.getTwo(), "Two", "Reception");
        dataset.addValue(pDefence.getTwo(), "Two", "Defence");

        dataset.addValue(pservice.getThree(), "Three", "Service");
        dataset.addValue(pAttack.getThree(), "Three", "Attack");
        dataset.addValue(pBlock.getThree(), "Three", "Block");
        dataset.addValue(pSet.getThree(), "Three", "Set");
        dataset.addValue(pReception.getThree(), "Three", "Reception");
        dataset.addValue(pDefence.getThree(), "Three", "Defence");

        dataset.addValue(pservice.getFour(), "Four", "Service");
        dataset.addValue(pAttack.getFour(), "Four", "Attack");
        dataset.addValue(pBlock.getFour(), "Four", "Block");
        dataset.addValue(pSet.getFour(), "Four", "Set");
        dataset.addValue(pReception.getFour(), "Four", "Reception");
        dataset.addValue(pDefence.getFour(), "Four", "Defence");

        dataset.addValue(pservice.getFive(), "Five", "Service");
        dataset.addValue(pAttack.getFive(), "Five", "Attack");
        dataset.addValue(pBlock.getFive(), "Five", "Block");
        dataset.addValue(pSet.getFive(), "Five", "Set");
        dataset.addValue(pReception.getFive(), "Five", "Reception");
        dataset.addValue(pDefence.getFive(), "Five", "Defence");

        JFreeChart chartFreeService = BarChart.createChart(dataset, "", "Skill", true);

        BarRenderer r = (BarRenderer) chartFreeService.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, Color.RED);
        r.setSeriesPaint(1, Color.YELLOW);
        r.setSeriesPaint(2, Color.ORANGE);
        r.setSeriesPaint(3, Color.BLUE);
        r.setSeriesPaint(4, Color.GREEN);
        ChartPanel CPService = new ChartPanel(chartFreeService);

        panChart.add(CPService, BorderLayout.CENTER);
        validate();
        repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbPlayer;
    private javax.swing.JComboBox<String> cmbTeam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblPrint1;
    private javax.swing.JLabel lblPrint2;
    private javax.swing.JPanel panChart;
    private javax.swing.JPanel panPlayer;
    private javax.swing.JPanel panPrint1;
    private javax.swing.JPanel panPrint2;
    // End of variables declaration//GEN-END:variables
}
