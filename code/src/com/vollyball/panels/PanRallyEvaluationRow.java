/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.controller.Controller;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.Rating;
import com.vollyball.enums.Skill;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author nishant.vibhute
 */
public class PanRallyEvaluationRow extends javax.swing.JPanel {

    Robot robot;
    PanRallyLiveEvaluation p;
    TeamDao teamDao = new TeamDao();
    int num = 0;
    String skill;

    /**
     * Creates new form PanRallyEvaluationRow
     *
     * @param p
     */
    public PanRallyEvaluationRow(PanRallyLiveEvaluation p) {
        initComponents();
        ((JLabel) cmbChest.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) cmbScore.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        this.p = p;
        num = p.rallyRow;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(PanRallyLiveEvaluation.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Map.Entry<Integer, Player> entry : p.positionMap.entrySet()) {
            cmbChest.addItem(entry.getValue().getChestNo());
        }
        for (Rating dir : Rating.values()) {
            cmbScore.addItem(dir.getId());
        }

    }

    public void setValues(String skill, String chestNo, int score) {

        txtSkill.setText(skill);
        cmbChest.setSelectedItem(chestNo);
        cmbScore.setSelectedItem(score);
    }

    public void hideButton() {
        lblAddNew.setIcon(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSkill = new javax.swing.JTextField();
        cmbChest = new javax.swing.JComboBox();
        cmbScore = new javax.swing.JComboBox();
        lblAddNew = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        txtSkill.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtSkill.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSkill.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));
        txtSkill.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSkillKeyReleased(evt);
            }
        });

        cmbChest.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbChest.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cmbChest.setToolTipText("");
        cmbChest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));
        cmbChest.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbChestItemStateChanged(evt);
            }
        });

        cmbScore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbScore.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cmbScore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));
        cmbScore.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbScoreItemStateChanged(evt);
            }
        });

        lblAddNew.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-plus-20.png"))); // NOI18N
        lblAddNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddNewMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtSkill, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbChest, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbScore, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAddNew, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtSkill, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addComponent(cmbChest)
            .addComponent(cmbScore)
            .addComponent(lblAddNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblAddNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddNewMouseClicked
        // TODO add your handling code here:
        p.refresh();

    }//GEN-LAST:event_lblAddNewMouseClicked

    private void txtSkillKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSkillKeyReleased
        // TODO add your handling code here:
        if (num == 0) {
            SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
            Date time = new Date();
            p.startTime = formatterTime.format(time);
            p.lblRallyStartTime.setText(p.startTime);
        }

        JTextField comp = (JTextField) evt.getSource();
        char text = evt.getKeyChar();
        setValue(comp, text);

    }//GEN-LAST:event_txtSkillKeyReleased

    private void cmbChestItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbChestItemStateChanged
        // TODO add your handling code here:
        robot.keyPress(KeyEvent.VK_TAB);
    }//GEN-LAST:event_cmbChestItemStateChanged

    private void cmbScoreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbScoreItemStateChanged
        // TODO add your handling code here:

        String item = String.valueOf(evt.getItem());
        if (!evt.getItem().equals("")) {
            if (!p.isInserted) {
                switch (item) {

                    case "1":
                        Controller.panMatchSet.opponentScore++;
                        p.lblResult.setText(Controller.panMatchSet.homeScore + " - " + Controller.panMatchSet.opponentScore);
                        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
                        Date time = new Date();
                        p.endTime = formatterTime.format(time);
                        p.lblRallyEndTime.setText(p.endTime);
                        break;
                    case "5":
                        if (skill.equals(Skill.Service.getType()) || skill.equals(Skill.Attack.getType()) || skill.equals(Skill.Block.getType()) || skill.equals(Skill.OP.getType())) {
                            Controller.panMatchSet.homeScore++;
                            p.lblResult.setText(Controller.panMatchSet.homeScore + " - " + Controller.panMatchSet.opponentScore);
                            SimpleDateFormat formatterTime1 = new SimpleDateFormat("HH:mm:ss");
                            Date time1 = new Date();
                            p.endTime = formatterTime1.format(time1);
                            p.lblRallyEndTime.setText(p.endTime);
                        } else {
                            if (!p.isInserted) {
                                p.refresh();
                                robot.keyPress(KeyEvent.VK_TAB);
                            }
                        }
                        break;
                    default:
                        p.refresh();
                        robot.keyPress(KeyEvent.VK_TAB);
                }
            }
        }

    }//GEN-LAST:event_cmbScoreItemStateChanged

    public void setValue(JTextField txt, char key) {
        switch (("" + key).toUpperCase()) {
            case "S":
                txt.setText("");
                txt.setText(Skill.Service.getType());
                skill = Skill.Service.getType();
                robot.keyPress(KeyEvent.VK_TAB);
                break;
            case "A":
                txt.setText("");
                txt.setText(Skill.Attack.getType());
                skill = Skill.Attack.getType();
                robot.keyPress(KeyEvent.VK_TAB);
                break;
            case "B":
                txt.setText("");
                txt.setText(Skill.Block.getType());
                skill = Skill.Block.getType();
                robot.keyPress(KeyEvent.VK_TAB);
                break;
            case "E":
                txt.setText("");
                txt.setText(Skill.Set.getType());
                skill = Skill.Set.getType();
                robot.keyPress(KeyEvent.VK_TAB);
                break;
            case "R":
                txt.setText("");
                txt.setText(Skill.Reception.getType());
                skill = Skill.Reception.getType();
                robot.keyPress(KeyEvent.VK_TAB);
                break;
            case "D":
                txt.setText("");
                txt.setText(Skill.Defence.getType());
                skill = Skill.Defence.getType();
                robot.keyPress(KeyEvent.VK_TAB);
                break;
            case "O":
                txt.setText("");
                txt.setText(Skill.OP.getType());
                skill = Skill.OP.getType();
                cmbScore.setSelectedItem(5);
                break;
            case "T":
                txt.setText("");
                txt.setText(Skill.TF.getType());
                skill = Skill.TF.getType();
                cmbScore.setSelectedItem(1);
                break;
            default:
                txt.setText("");
                cmbScore.setSelectedIndex(0);
                cmbChest.setSelectedIndex(0);
                break;
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox cmbChest;
    public javax.swing.JComboBox cmbScore;
    private javax.swing.JLabel lblAddNew;
    public javax.swing.JTextField txtSkill;
    // End of variables declaration//GEN-END:variables
}