/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.renderer.ColumnGroup;
import com.vollyball.renderer.GroupableTableHeader;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
 * @author nishant.vibhute
 */
public class PanBestScorer extends javax.swing.JPanel {

    DefaultTableModel model;
    JTable tbReport;

    /**
     * Creates new form PanBestScorer
     */
    public PanBestScorer() {
        initComponents();
        createTable();
    }

    public void createTable() {
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(new Object[][]{
            {"119", "foo", "bar", "ja", "ko", "zh", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"}

        },
                new Object[]{"SNo.", "Player Name", "<html>Matches<br> Played</html>", "Service", "Attack", "Block", "Set", "Reception", "Defend", "Total"});

        tbReport = new JTable(dm) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbReport.setFont(new java.awt.Font("Times New Roman", 0, 14));
        TableColumnModel cm = tbReport.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("SuccessRate");
        g_name.add(cm.getColumn(3));
        g_name.add(cm.getColumn(4));
        g_name.add(cm.getColumn(5));
        g_name.add(cm.getColumn(6));
        g_name.add(cm.getColumn(7));
        g_name.add(cm.getColumn(8));
        g_name.add(cm.getColumn(9));

        GroupableTableHeader header = (GroupableTableHeader) tbReport.getTableHeader();
        header.addColumnGroup(g_name);

        JScrollPane scroll = new JScrollPane(tbReport);

        Color heading = new Color(204, 204, 204);
        model = (DefaultTableModel) tbReport.getModel();
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

        Color ivory = new Color(255, 255, 255);
        tbReport.setOpaque(true);
        tbReport.setFillsViewportHeight(true);
        tbReport.setBackground(ivory);

        tbReport.setOpaque(false);
        tbReport.setRowHeight(30);
        resizeColumns();
        panReport.add(scroll, BorderLayout.CENTER);
    }
    float[] columnWidthPercentage = {5.0f, 23.0f, 9.0f, 9.0f, 9.0f, 9.0f, 9.0f, 9.0f, 9.0f, 9.0f};

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

        panSkillReports = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lblReportHeading = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panReport = new javax.swing.JPanel();

        panSkillReports.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        lblReportHeading.setBackground(new java.awt.Color(255, 255, 255));
        lblReportHeading.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblReportHeading.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblReportHeading.setText("BEST SCORER");

        jTextField1.setText("jTextField1");

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SEARCH");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblReportHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 403, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(lblReportHeading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panReport.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panSkillReportsLayout = new javax.swing.GroupLayout(panSkillReports);
        panSkillReports.setLayout(panSkillReportsLayout);
        panSkillReportsLayout.setHorizontalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panSkillReportsLayout.setVerticalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 976, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panSkillReports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JLabel lblReportHeading;
    private javax.swing.JPanel panReport;
    private javax.swing.JPanel panSkillReports;
    // End of variables declaration//GEN-END:variables
}
