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
import com.vollyball.renderer.TableHeaderRenderer;
import com.vollyball.renderer.ViewButtonRenderer;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanTableTeamSkillWiseReport extends javax.swing.JPanel {

    ReportDao reportDao = new ReportDao();
    DefaultTableModel model;
    String skillName;
    int skillId;
    CompetitionBean cb;
    LinkedHashMap<Integer, PlayerReportBean> playerId;

    /**
     * Creates new form PanTableSkillWiseReport
     */
    public PanTableTeamSkillWiseReport() {
        initComponents();
        createSkillWiseReportTable();
    }

    public void createSkillWiseReportTable() {

        Color heading = new Color(204, 204, 204);
        model = (DefaultTableModel) tbReport.getModel();
        JTableHeader header = tbReport.getTableHeader();
        header.setBackground(heading);
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 40));
        header.setFont(new Font("Times New Romam", Font.BOLD, 14));
        header.setDefaultRenderer(new TableHeaderRenderer(tbReport));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbReport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        ViewButtonRenderer viewButtonRenderer = new ViewButtonRenderer();
        tbReport.getColumnModel().getColumn(5).setCellRenderer(viewButtonRenderer);
        resizeColumns();
        Color ivory = new Color(255, 255, 255);
        tbReport.setOpaque(true);
        tbReport.setFillsViewportHeight(true);
        tbReport.setBackground(ivory);
        tbReport.setRowHeight(35);
        tbReport.setSelectionBackground(Color.WHITE);
        tbReport.setSelectionForeground(Color.BLACK);

        MouseMotionAdapter mma;
        mma = new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                if (tbReport.columnAtPoint(p) == 5) {
                    tbReport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    tbReport.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        };
        tbReport.addMouseMotionListener(mma);

        tbReport.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    String selectedName = null;

                    int row = tbReport.getSelectedRow();
                    int selectedCol = tbReport.getSelectedColumn();
                    tbReport.clearSelection();
                    for (int i = 0; i <= row; i++) {
                        selectedName = (String) tbReport.getValueAt(row, 1);

                    }

                    if (selectedName != null) {
                        if (selectedCol == 5) {
                            CreateDialogPanMatchWiseReport createDialogPanMatchWiseReport = new CreateDialogPanMatchWiseReport();
                            createDialogPanMatchWiseReport.setValues(playerId.get(row), skillName, skillId, cb.getId(), "team");
                            createDialogPanMatchWiseReport.init();
                            createDialogPanMatchWiseReport.show();
                        }
                    }
                }
            }
        });

    }

    float[] columnWidthPercentage = {5.0f, 25.0f, 20.0f, 20.0f, 20.0f, 20.0f};

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

    public void setValues() {

    }

    public void setTeamReport(int skillid, String skillName, CompetitionBean cb, LinkedHashMap<Integer, PlayerReportBean> playerId) {
        this.cb = cb;
        this.skillName = skillName;
        this.playerId = playerId;
        this.skillId = skillid;

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        List<PlayerReportBean> pbList = reportDao.getTeamReportList(skillid);
        int i = 0;
        for (PlayerReportBean pb : pbList) {
            playerId.put(i, pb);
            i++;
            Object[] row = {i, pb.getName(), pb.getTotal(), pb.getSuccess(), pb.getSuccessrate(), new JPanel()};
            model.addRow(row);

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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbReport = new javax.swing.JTable();

        panSkillReports.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        lblReportHeading.setBackground(new java.awt.Color(255, 255, 255));
        lblReportHeading.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblReportHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReportHeading.setText("BEST SCORER");

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
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(lblReportHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel1.setLayout(new java.awt.BorderLayout());

        tbReport.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SR No.", "TEAM", "TOTAL ATTEMPT", "SUCCESSFUL ATTEMPT", "SUCCESS RATE ", "CHART"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbReport.setOpaque(false);
        tbReport.setRowHeight(30);
        jScrollPane1.setViewportView(tbReport);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panSkillReportsLayout = new javax.swing.GroupLayout(panSkillReports);
        panSkillReports.setLayout(panSkillReportsLayout);
        panSkillReportsLayout.setHorizontalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panSkillReportsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        panSkillReportsLayout.setVerticalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblReportHeading;
    private javax.swing.JPanel panSkillReports;
    private javax.swing.JTable tbReport;
    // End of variables declaration//GEN-END:variables
}
