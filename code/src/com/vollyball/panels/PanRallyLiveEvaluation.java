/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.controller.Controller;
import com.vollyball.dao.RallyDao;
import com.vollyball.enums.Skill;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Robot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author nishant.vibhute
 */
public class PanRallyLiveEvaluation extends javax.swing.JPanel {

    Robot robot;
    public int rallyNum, matchEvaluationId, id;
    String result = "";
    RallyDao rallyDao = new RallyDao();
    PanCompListValue panCompListValue;
    public List<PanRallyEvaluationRow> panListRow = new ArrayList<>();
    LinkedHashMap<Integer, Player> positionMap;
    int k;
    int rallyRow = 0;
    public boolean isInserted = false;
    public String startTime, endTime;
    int evaluationType;
//    List<PanRallyEvaluationRow> panList = new ArrayList<>();

    /**
     * Creates new form PanRally
     *
     * @param rallyNum
     * @param matchEvaluationId
     * @param getData
     */
    public PanRallyLiveEvaluation(int rallyNum, int matchEvaluationId, LinkedHashMap<Integer, Player> positionMap, int evaluationType) {

//        Controller.panMatchSet.lblBack.setVisible(true);
        initComponents();
        k = 1;
        this.positionMap = positionMap;
        this.rallyNum = rallyNum;
        this.matchEvaluationId = matchEvaluationId;
        this.evaluationType = evaluationType;
        Controller.panMatchSet.lblRallyHeading.setText("RALLY : " + rallyNum);

        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(PanRallyLiveEvaluation.class.getName()).log(Level.SEVERE, null, ex);
        }
        panCompListValue = new PanCompListValue();
        RallyEvaluation re = rallyDao.getRally(rallyNum, matchEvaluationId);
        if (re.getId() != 0) {
            isInserted = true;

            panCompListValue.addRallyList();
            this.id = re.getId();
            Controller.panMatchSet.butNext.setText("Update");
        } else {
            panCompListValue.addBlankRow();
            Controller.panMatchSet.butNext.setText("Save");
        }
        panCompListValue.setBounds(0, 0, 353, 294);
        panDynamic.add(panCompListValue);

    }

    public void refresh() {
        panCompListValue.add();
    }

    public void addToPosition() {
        panCompListValue.addToPosition();
    }

    public void removePosition() {
        panCompListValue.removeRow();
    }

    public class PanCompListValue extends JPanel {

        private JPanel mainList;
        JScrollPane s;

        public PanCompListValue() {

            setLayout(new BorderLayout());
            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);
            mainList.setBackground(Color.WHITE);
            s = new JScrollPane(mainList);
            s.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            s.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
            s.getVerticalScrollBar().setBackground(Color.red);
            s.setBackground(Color.WHITE);
            s.setBorder(null);
            add(s);

        }

        public void addBlankRow() {
            PanRallyEvaluationRow panel = new PanRallyEvaluationRow(PanRallyLiveEvaluation.this);
            GridBagConstraints gbcRow = new GridBagConstraints();
            gbcRow.gridwidth = GridBagConstraints.REMAINDER;
            gbcRow.weightx = 1;
            gbcRow.gridheight = 2;
            gbcRow.fill = GridBagConstraints.HORIZONTAL;
            panel.hideButton();
            panel.hideMinusButton();
            mainList.add(panel, gbcRow, 0);
            validate();
            repaint();
            panListRow.add(panel);
        }

        public void add() {

            PanRallyEvaluationRow panel = new PanRallyEvaluationRow(PanRallyLiveEvaluation.this);
            panel.hideButton();
            GridBagConstraints gbcRow = new GridBagConstraints();
            gbcRow.gridwidth = GridBagConstraints.REMAINDER;
            gbcRow.weightx = 1;
            gbcRow.gridheight = 2;
            gbcRow.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbcRow, k);
            validate();
            repaint();
            JScrollBar vertical = s.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
            k++;
            panListRow.add(panel);
        }

        public void removeRow() {
            int atRow = 0;
            int i = 0;
            for (PanRallyEvaluationRow p : panListRow) {
                if (p.isAddClicked) {

                    p.isAddClicked = false;
                    panListRow.remove(i);
                    break;

                }
                i++;
            }
            mainList.remove(i);
            validate();
            repaint();
        }

        public void addToPosition() {

            int atRow = 0;
            PanRallyEvaluationRow panel = new PanRallyEvaluationRow(PanRallyLiveEvaluation.this);
            GridBagConstraints gbcRow = new GridBagConstraints();
            gbcRow.gridwidth = GridBagConstraints.REMAINDER;
            gbcRow.weightx = 1;
            gbcRow.gridheight = 2;
            gbcRow.fill = GridBagConstraints.HORIZONTAL;

            int i = 0;
            for (PanRallyEvaluationRow p : panListRow) {
                if (p.isAddClicked) {
                    atRow = i + 1;
                    p.isAddClicked = false;
                    panListRow.add(atRow, panel);
                    break;

                }
                i++;
            }

            mainList.add(panel, gbcRow, atRow);
            validate();
            repaint();
            JScrollBar vertical = s.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
            k++;

        }

        public void addRallyList() {
            RallyEvaluation re = rallyDao.getRally(rallyNum, matchEvaluationId);
            lblResult.setText(re.getHomeScore() + " - " + re.getOpponentScore());
            lblRallyStartTime.setText(re.getStartTime());
            lblRallyEndTime.setText(re.getEndTime());

            int i = 0;
            for (RallyEvaluationSkillScore ress : re.getRallyEvaluationSkillScore()) {
                rallyRow++;
                PanRallyEvaluationRow panel = new PanRallyEvaluationRow(PanRallyLiveEvaluation.this);
                panel.setValues(Skill.getNameById(ress.getSkillId()).getType(), ress.getPlayerId(), ress.getScore(), ress.getId());
                GridBagConstraints gbcRow = new GridBagConstraints();
                gbcRow.gridwidth = GridBagConstraints.REMAINDER;
                gbcRow.weightx = 1;
                gbcRow.gridheight = 2;
                gbcRow.fill = GridBagConstraints.HORIZONTAL;
                mainList.add(panel, gbcRow, i);
//                panList.add(panel);
                i++;
                validate();
                repaint();
                JScrollBar vertical = s.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
                panListRow.add(panel);
            }
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

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblBack = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblRallyEndTime = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblRallyStartTime = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        panDynamic = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(54, 78, 108));

        lblBack.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblBack.setForeground(new java.awt.Color(251, 205, 1));
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/arrow_left.png"))); // NOI18N
        lblBack.setToolTipText("BACK");
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Start Time :");

        lblRallyEndTime.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblRallyEndTime.setForeground(new java.awt.Color(251, 201, 0));
        lblRallyEndTime.setText(" ");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("End Time :");

        lblRallyStartTime.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblRallyStartTime.setForeground(new java.awt.Color(251, 201, 0));
        lblRallyStartTime.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBack, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRallyStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRallyEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRallyStartTime)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRallyEndTime)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        panDynamic.setBackground(new java.awt.Color(255, 255, 255));
        panDynamic.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panDynamicLayout = new javax.swing.GroupLayout(panDynamic);
        panDynamic.setLayout(panDynamicLayout);
        panDynamicLayout.setHorizontalGroup(
            panDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panDynamicLayout.setVerticalGroup(
            panDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(54, 78, 108));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Skill");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Chest No");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Score");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel7.setBackground(new java.awt.Color(54, 78, 108));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Result :");

        lblResult.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblResult.setForeground(new java.awt.Color(251, 201, 0));
        lblResult.setText(" ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblResult))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
            .addComponent(panDynamic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panDynamic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        // TODO add your handling code here:
        Controller.panMatchSet.setBackNextInVisible();
        Controller.panMatchSet.showRallyList();
    }//GEN-LAST:event_lblBackMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblBack;
    public javax.swing.JLabel lblRallyEndTime;
    public javax.swing.JLabel lblRallyStartTime;
    public javax.swing.JLabel lblResult;
    private javax.swing.JPanel panDynamic;
    // End of variables declaration//GEN-END:variables
}
