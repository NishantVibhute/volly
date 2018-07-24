/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerScores;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.renderer.ColumnGroup;
import com.vollyball.renderer.GroupableTableHeader;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Supriya
 */
public class PanMatchBestTeamScorer extends javax.swing.JPanel {

    List<Integer> evaluationteamId = new ArrayList<>();
    ReportDao reportDao = new ReportDao();
    JTable tbReport;
    TeamDao td = new TeamDao();
    DefaultTableModel dm;
    List<Player> playerList;
    int cb;

    /**
     * Creates new form PanMatchBestTeamScorer
     */
    public PanMatchBestTeamScorer(final int cb, List<Player> playerList, List<Integer> evaluationId) {
        initComponents();
        this.playerList = playerList;
        this.evaluationteamId = evaluationId;
        this.cb = cb;
        createTable();
        setRow(null);
    }

    public void setRow(Player player) {
        List<PlayerScores> playerScoresList = new ArrayList<>();

        for (int i = dm.getRowCount() - 1; i >= 0; i--) {
            dm.removeRow(i);
        }

        if (player == null) {
            for (int e : evaluationteamId) {
                for (Player p : playerList) {
                    PlayerScores playerScore = reportDao.getPlayerScores(cb, p, e);
                    playerScoresList.add(playerScore);
                }
            }

            Collections.sort(playerScoresList, new Comparator<PlayerScores>() {
                @Override
                public int compare(PlayerScores c1, PlayerScores c2) {

                    return Double.compare(c2.getAttemptRate(), c1.getAttemptRate());
                }
            });

            int i = 0;
            for (PlayerScores p : playerScoresList) {
                Object[] row = {i + 1, p.getPlayerName(), p.getTeamName(), p.getMatchesPlayed(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc()};
                dm.addRow(row);
                i++;
            }
        } else {

            PlayerScores playerScore = reportDao.getPlayerScores(cb, player, 0);
            playerScoresList.add(playerScore);

            int i = 0;
            for (PlayerScores p : playerScoresList) {
                Object[] row = {i + 1, p.getPlayerName(), p.getTeamName(), p.getMatchesPlayed(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc()};
                dm.addRow(row);
                i++;
            }

        }
    }

    public void createTable() {
        dm = new DefaultTableModel();

        dm.setDataVector(new Object[][]{},
                new Object[]{"SNo.", "Player Name", "<html>Team<br> Name</html>", "<html>Matches<br> Played</html>", "Service", "Attack", "Block", "Set", "Reception", "Defend", "Total"});

        tbReport = new JTable(dm) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbReport.setFont(new java.awt.Font("Times New Roman", 0, 12));
        TableColumnModel cm = tbReport.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("SuccessRate");
        g_name.add(cm.getColumn(4));
        g_name.add(cm.getColumn(5));
        g_name.add(cm.getColumn(6));
        g_name.add(cm.getColumn(7));
        g_name.add(cm.getColumn(8));
        g_name.add(cm.getColumn(9));
        g_name.add(cm.getColumn(10));

        GroupableTableHeader header = (GroupableTableHeader) tbReport.getTableHeader();
        header.addColumnGroup(g_name);

        JScrollPane scroll = new JScrollPane(tbReport);

        Color heading = new Color(204, 204, 204);
        dm = (DefaultTableModel) tbReport.getModel();
        JTableHeader tbheader = tbReport.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 45));
        tbheader.setDefaultRenderer(new TableHeaderRenderer(tbReport));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbReport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(0).setWidth(10);

        tbReport.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        tbReport.setOpaque(true);
        tbReport.setFillsViewportHeight(true);
        tbReport.setBackground(ivory);

        tbReport.setRowHeight(25);
        resizeColumns();
        panReport.add(scroll, BorderLayout.CENTER);
    }
    float[] columnWidthPercentage = {5.0f, 23.0f, 9.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f};

    private void resizeColumns() {
        int tW = tbReport.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbReport.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
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

        panReport = new javax.swing.JPanel();

        panReport.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panReport;
    // End of variables declaration//GEN-END:variables
}
