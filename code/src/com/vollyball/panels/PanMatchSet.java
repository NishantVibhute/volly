/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchSet;
import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.bean.SetRotationOrder;
import com.vollyball.bean.SetSubstitution;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.SetRotationDialog;
import com.vollyball.dialog.SetSubstituteSelectPlayerDialog;
import com.vollyball.enums.Skill;
import com.vollyball.util.CommonUtil;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author nishant.vibhute
 */
public class PanMatchSet extends javax.swing.JPanel {

    public int currentRally = 0;
    int setNum;
    int matchId;
    int teamEvaluateId;
    int opponentId;
    int matchEvaluationId = 0;
    public SetRotationDialog setRotationDialog;
    public LinkedHashMap<Integer, RallyEvaluation> rallyMap = new LinkedHashMap<Integer, RallyEvaluation>();
    public LinkedHashMap<Integer, PanRallyLiveEvaluation> panRallyMap = new LinkedHashMap<Integer, PanRallyLiveEvaluation>();
    public LinkedHashMap<Integer, Player> positionMap;

    public LinkedHashMap<Integer, Player> playerMap = new LinkedHashMap<Integer, Player>();
    public LinkedHashMap<String, Player> ChestMap = new LinkedHashMap<String, Player>();
    MatchDao matchDao = new MatchDao();
    TeamDao teamDao = new TeamDao();
    RallyDao rallyDao = new RallyDao();
    PanRallyLiveEvaluation panRallyCurrent;
    int totalRally = 0;
    PanRallyNew panRallyNew;
    List<Player> playerList;
    public int homeScore = 0, opponentScore = 0;
    String currentScore;
    String startTime, endTime;

    /**
     * Creates new form PanMatchSet
     *
     * @param setNum
     * @param matchId
     * @param teamEvaluateId
     * @param opponentId
     */
    public PanMatchSet(int setNum, int matchId, int teamEvaluateId, int opponentId) {

        initComponents();
//        panNext.setVisible(false);
        playerList = teamDao.getTeamPlayers(teamEvaluateId);
        panRallyList.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setNum = setNum;
        this.matchId = matchId;
        this.teamEvaluateId = teamEvaluateId;
        this.opponentId = opponentId;
        positionMap = new LinkedHashMap<>();

        MatchSet ms = matchDao.getMatchSet(setNum, matchId);

        if (ms.getId() != 0) {
            this.matchEvaluationId = ms.getId();
            lblDate.setText(CommonUtil.ConvertDateFromDbToNormal(ms.getDate()));
            lblTime.setText(ms.getStart_time());
            String evaluationName = Controller.panMatchEvaluationHome.getTeamsMap().get(ms.getEvaluationTeamId());
            String opponentName = Controller.panMatchEvaluationHome.getTeamsMap().get(ms.getOpponentTeamId());
            lblevaluationName.setText(evaluationName);
            lblopponentName.setText(opponentName);
            txtEvaluator.setText(ms.getEvaluator());
            lblScore.setText(ms.getHomeScore() + " - " + ms.getOpponentScore());
            homeScore = ms.getHomeScore();
            opponentScore = ms.getOpponentScore();

            List<Player> playerListL = teamDao.getTeamPlayers(ms.getEvaluationTeamId());
            for (Player p : playerListL) {
                playerMap.put(p.getId(), p);
                ChestMap.put(p.getChestNo(), p);
            }

            for (SetRotationOrder s : ms.getRotationOrder()) {
                positionMap.put(s.getPosition(), playerMap.get(s.getPlayerId()));
            }

            for (SetSubstitution s : ms.getSetSubstitutions()) {

                String cNo = s.getSubstitutePlayerId() == 0 ? "" : playerMap.get(s.getSubstitutePlayerId()).getChestNo();
                String p1 = s.getPoint1();
                String p2 = s.getPoint2();
                switch (s.getPosition()) {
                    case 1:
                        su1.setText(cNo);
                        pt11.setText(p1);
                        pt21.setText(p2);
                        break;
                    case 2:
                        su2.setText(cNo);
                        pt12.setText(p1);
                        pt22.setText(p2);
                        break;
                    case 3:
                        su3.setText(cNo);
                        pt13.setText(p1);
                        pt23.setText(p2);
                        break;
                    case 4:
                        su4.setText(cNo);
                        pt14.setText(p1);
                        pt24.setText(p2);
                        break;
                    case 5:
                        su5.setText(cNo);
                        pt15.setText(p1);
                        pt25.setText(p2);
                        break;
                    case 6:
                        su6.setText(cNo);
                        pt16.setText(p1);
                        pt26.setText(p2);
                        break;
                }

            }

            pos1.setText(positionMap.get(1).getChestNo());
            pos2.setText(positionMap.get(2).getChestNo());
            pos3.setText(positionMap.get(3).getChestNo());
            pos4.setText(positionMap.get(4).getChestNo());
            pos5.setText(positionMap.get(5).getChestNo());
            pos6.setText(positionMap.get(6).getChestNo());
            libero.setText(positionMap.get(7).getChestNo());

            ro1.setText(positionMap.get(1).getChestNo());
            ro2.setText(positionMap.get(2).getChestNo());
            ro3.setText(positionMap.get(3).getChestNo());
            ro4.setText(positionMap.get(4).getChestNo());
            ro5.setText(positionMap.get(5).getChestNo());
            ro6.setText(positionMap.get(6).getChestNo());

            List<RallyEvaluation> rallies = rallyDao.getRalliesList(matchEvaluationId);

            for (RallyEvaluation rally : rallies) {
                PanRallyBut pnBut = new PanRallyBut();
                pnBut.setRally(rally.getRallyNum(), matchEvaluationId, positionMap);
                panRallyList.add(pnBut);
                currentRally++;
            }
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            lblDate.setText(formatter.format(date));
            lblTime.setText(formatterTime.format(date));
            String evaluationName = Controller.panMatchEvaluationHome.getTeamsMap().get(teamEvaluateId);
            String opponentName = Controller.panMatchEvaluationHome.getTeamsMap().get(opponentId);
            lblevaluationName.setText(evaluationName);
            lblopponentName.setText(opponentName);
        }
        panRallyNew = new PanRallyNew();
        panRallyList.add(panRallyNew);
        setScore();
    }

    public void setScore() {
        currentScore = homeScore + " - " + opponentScore;
        lblScore.setText(currentScore);
    }

    public LinkedHashMap<Integer, RallyEvaluation> getRallyMap() {
        return rallyMap;
    }

    public LinkedHashMap<Integer, PanRallyLiveEvaluation> getPanRallyMap() {
        return panRallyMap;
    }

    public void mediaPlayer() {
        setLayout(new BorderLayout());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblevaluationName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblopponentName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEvaluator = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pos4 = new javax.swing.JTextField();
        pos3 = new javax.swing.JTextField();
        pos2 = new javax.swing.JTextField();
        pos5 = new javax.swing.JTextField();
        pos6 = new javax.swing.JTextField();
        pos1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        pt11 = new javax.swing.JTextField();
        pt12 = new javax.swing.JTextField();
        pt13 = new javax.swing.JTextField();
        pt14 = new javax.swing.JTextField();
        pt15 = new javax.swing.JTextField();
        pt16 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        pt21 = new javax.swing.JTextField();
        pt22 = new javax.swing.JTextField();
        pt23 = new javax.swing.JTextField();
        pt24 = new javax.swing.JTextField();
        pt25 = new javax.swing.JTextField();
        pt26 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        ro1 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        but1 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        ro2 = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        but2 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        ro3 = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        but3 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        ro4 = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        ro5 = new javax.swing.JTextField();
        but4 = new javax.swing.JPanel();
        but5 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        ro6 = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        but6 = new javax.swing.JLabel();
        su1 = new javax.swing.JTextField();
        su2 = new javax.swing.JTextField();
        su3 = new javax.swing.JTextField();
        su4 = new javax.swing.JTextField();
        su5 = new javax.swing.JTextField();
        su6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jTextField17 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jTextField58 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jTextField60 = new javax.swing.JTextField();
        jTextField61 = new javax.swing.JTextField();
        jTextField62 = new javax.swing.JTextField();
        jTextField63 = new javax.swing.JTextField();
        jTextField64 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jTextField44 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        lblSetRotationOrder = new javax.swing.JLabel();
        libero = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        lblrotateleft = new javax.swing.JLabel();
        lblrotateright = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        panRallyList = new javax.swing.JPanel();
        panShowRallyInput = new javax.swing.JPanel();
        panRallyShow = new javax.swing.JPanel();
        lblStart = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        panNext = new javax.swing.JPanel();
        butNext = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBackground(new java.awt.Color(54, 78, 108));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Score :");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Evaluating Team :");

        lblevaluationName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblevaluationName.setForeground(new java.awt.Color(251, 205, 1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Opponent Team :");

        lblopponentName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblopponentName.setForeground(new java.awt.Color(251, 205, 1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Won By :");

        lblScore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblScore.setForeground(new java.awt.Color(251, 205, 1));
        lblScore.setText(" ");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(251, 205, 1));
        jLabel18.setText(" ");
        jLabel18.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblevaluationName, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblopponentName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(lblevaluationName)
                    .addComponent(jLabel2)
                    .addComponent(lblopponentName)
                    .addComponent(jLabel7)
                    .addComponent(lblScore)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));
        jPanel6.setForeground(new java.awt.Color(54, 78, 108));

        lblDate.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Time :");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Name Of Evaluator");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Rotation Order");

        pos4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        pos4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        pos3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        pos2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        pos5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        pos5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        pos6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos6.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        pos1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jTextField12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setText("RO");
        jTextField12.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField12.setEnabled(false);
        jTextField12.setOpaque(false);

        jTextField22.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.setText("SU");
        jTextField22.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField22.setEnabled(false);
        jTextField22.setOpaque(false);

        jTextField29.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField29.setText("PT");
        jTextField29.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField29.setEnabled(false);
        jTextField29.setOpaque(false);

        pt11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt11.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt11.setEnabled(false);
        pt11.setOpaque(false);

        pt12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt12.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt12.setEnabled(false);
        pt12.setOpaque(false);

        pt13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt13.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt13.setEnabled(false);
        pt13.setOpaque(false);

        pt14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt14.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt14.setEnabled(false);
        pt14.setOpaque(false);

        pt15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt15.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt15.setEnabled(false);
        pt15.setOpaque(false);

        pt16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt16.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt16.setEnabled(false);
        pt16.setOpaque(false);

        jTextField36.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField36.setText("PT");
        jTextField36.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField36.setEnabled(false);
        jTextField36.setOpaque(false);

        pt21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt21.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt21.setEnabled(false);
        pt21.setOpaque(false);

        pt22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt22.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt22.setEnabled(false);
        pt22.setOpaque(false);

        pt23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt23.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt23.setEnabled(false);
        pt23.setOpaque(false);

        pt24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt24.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt24.setEnabled(false);
        pt24.setOpaque(false);

        pt25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt25.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt25.setEnabled(false);
        pt25.setOpaque(false);

        pt26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt26.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt26.setEnabled(false);
        pt26.setOpaque(false);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("SUBSTITUTE");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        ro1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro1.setEnabled(false);
        ro1.setOpaque(false);

        jPanel16.setBackground(new java.awt.Color(0, 204, 51));
        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        but1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but1.setText("SET");
        but1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but1, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ro1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(ro1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        ro2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ro2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro2.setEnabled(false);
        ro2.setOpaque(false);

        jPanel25.setBackground(new java.awt.Color(0, 204, 51));
        jPanel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        but2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but2.setText("SET");
        but2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but2, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ro2)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(ro2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        ro3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ro3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro3.setEnabled(false);
        ro3.setOpaque(false);

        jPanel27.setBackground(new java.awt.Color(0, 204, 51));
        jPanel27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        but3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but3.setText("SET");
        but3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but3, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but3, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ro3))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(ro3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        ro4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ro4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro4.setEnabled(false);
        ro4.setOpaque(false);

        jPanel29.setBackground(new java.awt.Color(0, 204, 51));
        jPanel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("SET");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ro4)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(ro4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        ro5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ro5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro5.setEnabled(false);
        ro5.setOpaque(false);

        but4.setBackground(new java.awt.Color(0, 204, 51));
        but4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        but5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but5.setText("SET");
        but5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout but4Layout = new javax.swing.GroupLayout(but4);
        but4.setLayout(but4Layout);
        but4Layout.setHorizontalGroup(
            but4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but5, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );
        but4Layout.setVerticalGroup(
            but4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but5, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ro5)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(ro5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(but4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        ro6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ro6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro6.setEnabled(false);
        ro6.setOpaque(false);

        jPanel33.setBackground(new java.awt.Color(0, 204, 51));
        jPanel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but6.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        but6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but6.setText("SET");
        but6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but6, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but6, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ro6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(ro6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        su1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        su1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su1.setEnabled(false);
        su1.setOpaque(false);

        su2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        su2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su2.setEnabled(false);
        su2.setOpaque(false);

        su3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        su3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su3.setEnabled(false);
        su3.setOpaque(false);

        su4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        su4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su4.setEnabled(false);
        su4.setOpaque(false);

        su5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        su5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su5.setEnabled(false);
        su5.setOpaque(false);

        su6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        su6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su6.setEnabled(false);
        su6.setOpaque(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(su1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pt11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pt12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(pt13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pt14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pt15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pt16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(su3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(su4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(su5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(su6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt22, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt24, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt25, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt26, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Substitution");

        jTextField17.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.setText("TM");

        jTextField51.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField51.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField51.setText("A");

        jTextField58.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField58.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField58.setText("B");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("TimeOut");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText(" Plus And Minus Point");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Date :");

        jTextField44.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField44.setText("OP +");
        jTextField44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField44ActionPerformed(evt);
            }
        });

        jTextField43.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField43.setText("TF -");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField43, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jTextField15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel10.setBackground(new java.awt.Color(54, 78, 108));

        lblSetRotationOrder.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetRotationOrder.setForeground(new java.awt.Color(255, 255, 255));
        lblSetRotationOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSetRotationOrder.setText("SET");
        lblSetRotationOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetRotationOrderMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSetRotationOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSetRotationOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        libero.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        libero.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel17.setText("LIEBRO");

        lblrotateleft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/rotateleft.png"))); // NOI18N
        lblrotateleft.setToolTipText("Rotate Left");
        lblrotateleft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblrotateleftMouseClicked(evt);
            }
        });

        lblrotateright.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/rotateright.png"))); // NOI18N
        lblrotateright.setToolTipText("Rotate Right");
        lblrotateright.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblrotaterightMouseClicked(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEvaluator, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblrotateleft, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblrotateright, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(libero)))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEvaluator, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(libero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblrotateright, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblrotateleft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panRallyList.setBackground(new java.awt.Color(255, 255, 255));
        panRallyList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

        panShowRallyInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

        panRallyShow.setBackground(new java.awt.Color(255, 255, 255));
        panRallyShow.setLayout(new java.awt.BorderLayout());

        lblStart.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblStart.setForeground(new java.awt.Color(54, 78, 108));
        lblStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStart.setText("START");
        lblStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStartMouseClicked(evt);
            }
        });
        panRallyShow.add(lblStart, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panShowRallyInputLayout = new javax.swing.GroupLayout(panShowRallyInput);
        panShowRallyInput.setLayout(panShowRallyInputLayout);
        panShowRallyInputLayout.setHorizontalGroup(
            panShowRallyInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panShowRallyInputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panRallyShow, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
        );
        panShowRallyInputLayout.setVerticalGroup(
            panShowRallyInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panShowRallyInputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panRallyShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(54, 78, 108));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("R");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("A");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("L");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("L");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Y");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(57, 74, 108)));

        panNext.setBackground(new java.awt.Color(57, 74, 108));
        panNext.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        butNext.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        butNext.setForeground(new java.awt.Color(255, 255, 255));
        butNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        butNext.setText("NEXT");
        butNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butNextMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panNextLayout = new javax.swing.GroupLayout(panNext);
        panNext.setLayout(panNextLayout);
        panNextLayout.setHorizontalGroup(
            panNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butNext, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        panNextLayout.setVerticalGroup(
            panNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butNext, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(panRallyList, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panShowRallyInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panShowRallyInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panRallyList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField44ActionPerformed

    private void lblStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStartMouseClicked
        // TODO add your handling code here:
        currentRally++;
        if (this.matchEvaluationId == 0) {
            MatchSet ms = new MatchSet();
            ms.setMatchId(matchId);
            ms.setEvaluationTeamId(teamEvaluateId);
            ms.setOpponentTeamId(opponentId);
            ms.setStart_time(lblTime.getText());
            ms.setEvaluator(txtEvaluator.getText());
            ms.setSetNo(setNum);
            ms.setEnd_time("00:00");
            ms.setDate(CommonUtil.ConvertDateFromNormalToDB(lblDate.getText()));

            for (Map.Entry<Integer, Player> entry : positionMap.entrySet()) {
                SetRotationOrder sro = new SetRotationOrder();
                sro.setPosition(entry.getKey());
                sro.setPlayerId(entry.getValue().getId());
                ms.getRotationOrder().add(sro);
            }

            for (Map.Entry<Integer, Player> entry : positionMap.entrySet()) {
                SetSubstitution sro = new SetSubstitution();
                sro.setPosition(entry.getKey());
                sro.setRotation_player_id(entry.getValue().getId());
                ms.getSetSubstitutions().add(sro);
            }

            matchEvaluationId = matchDao.saveMatchSet(ms);
        }
        PanRallyBut pnBut = new PanRallyBut();
        pnBut.setRally(currentRally, matchEvaluationId, positionMap);
        panRallyList.add(pnBut);
        panRallyShow.removeAll();
        panRallyCurrent = new PanRallyLiveEvaluation(currentRally, matchEvaluationId, positionMap);
        panRallyShow.add(panRallyCurrent);
        Controller.panMatchSet.validate();
        Controller.panMatchSet.repaint();
        panRallyList.remove(panRallyNew);
        panNext.setVisible(true);
        butNext.setText("Save");

    }//GEN-LAST:event_lblStartMouseClicked

    private void lblSetRotationOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetRotationOrderMouseClicked
        // TODO add your handling code here:
        setRotationDialog = new SetRotationDialog();
        setRotationDialog.setMatchId(this.matchId);
        setRotationDialog.setTeamEvaluteId(this.teamEvaluateId);
        setRotationDialog.init();
        setRotationDialog.show();

    }//GEN-LAST:event_lblSetRotationOrderMouseClicked

    private void lblrotateleftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblrotateleftMouseClicked
        // TODO add your handling code here:
        String temp = pos1.getText();
        pos1.setText(pos6.getText());
        pos6.setText(pos5.getText());
        pos5.setText(pos4.getText());
        pos4.setText(pos3.getText());
        pos3.setText(pos2.getText());
        pos2.setText(temp);

        String temp1 = ro1.getText();
        ro1.setText(ro6.getText());
        ro6.setText(ro5.getText());
        ro5.setText(ro4.getText());
        ro4.setText(ro3.getText());
        ro3.setText(ro2.getText());
        ro2.setText(temp1);

    }//GEN-LAST:event_lblrotateleftMouseClicked

    private void lblrotaterightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblrotaterightMouseClicked
        // TODO add your handling code here:
        String temp = pos1.getText();
        pos1.setText(pos2.getText());
        pos2.setText(pos3.getText());
        pos3.setText(pos4.getText());
        pos4.setText(pos5.getText());
        pos5.setText(pos6.getText());
        pos6.setText(temp);

        String temp1 = ro1.getText();
        ro1.setText(ro2.getText());
        ro2.setText(ro3.getText());
        ro3.setText(ro4.getText());
        ro4.setText(ro5.getText());
        ro5.setText(ro6.getText());
        ro6.setText(temp1);
    }//GEN-LAST:event_lblrotaterightMouseClicked

    private void butNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butNextMouseClicked
        // TODO add your handling code here:
        Object o = evt.getSource();
        String type = ((JLabel) o).getText();
        switch (type) {
            case "Update":
                RallyEvaluation rallyUpdate = new RallyEvaluation();
                rallyUpdate.setId(panRallyCurrent.id);
                rallyUpdate.setRallyNum(panRallyCurrent.rallyNum);
//                rallyUpdate.setHomeScore();
                rallyUpdate.setMatchEvaluationId(matchEvaluationId);

                for (int i = 0; i < panRallyCurrent.panListRow.size(); i++) {
                    try {
                        PanRallyEvaluationRow panRallyEvaluationRow = panRallyCurrent.panListRow.get(i);
                        if (!panRallyEvaluationRow.txtSkill.getText().isEmpty()) {
                            RallyEvaluationSkillScore rs = new RallyEvaluationSkillScore();
                            rs.setSkill(panRallyEvaluationRow.txtSkill.getText());
                            rs.setSkillId(Skill.getIdByName(panRallyEvaluationRow.txtSkill.getText()).getId());
                            rs.setChestNo(String.valueOf(panRallyEvaluationRow.cmbChest.getSelectedItem()));
                            rs.setPlayerId(Integer.parseInt(String.valueOf(panRallyEvaluationRow.cmbChest.getSelectedItem())));
                            rs.setScore(Integer.parseInt(String.valueOf(panRallyEvaluationRow.cmbScore.getSelectedItem())));
                            rallyUpdate.getRallyEvaluationSkillScore().add(rs);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(PanRallyLiveEvaluation.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                int id = rallyDao.updateRally(rallyUpdate);
                if (id != 0) {
                    JOptionPane.showMessageDialog(panRallyCurrent, "Updated Successfully");
                } else {
                    JOptionPane.showMessageDialog(panRallyCurrent, "Failed to save Rally");
                }

                break;
            case "Save":
                if (!panRallyCurrent.panListRow.get(0).txtSkill.getText().equals("")) {
                    RallyEvaluation rallyInsert = new RallyEvaluation();
                    rallyInsert.setRallyNum(panRallyCurrent.rallyNum);
                    rallyInsert.setHomeScore(homeScore);
                    rallyInsert.setOpponentScore(opponentScore);
                    rallyInsert.setStartTime(panRallyCurrent.startTime);
                    rallyInsert.setEndTime(panRallyCurrent.endTime);
                    rallyInsert.setMatchEvaluationId(matchEvaluationId);

                    for (int i = 0; i < panRallyCurrent.panListRow.size(); i++) {
                        try {
                            PanRallyEvaluationRow panRallyEvaluationRow = panRallyCurrent.panListRow.get(i);
                            if (!panRallyEvaluationRow.txtSkill.getText().isEmpty()) {
                                RallyEvaluationSkillScore rs = new RallyEvaluationSkillScore();
                                rs.setSkill(panRallyEvaluationRow.txtSkill.getText());
                                rs.setSkillId(Skill.getIdByName(panRallyEvaluationRow.txtSkill.getText()).getId());
                                rs.setChestNo(String.valueOf(panRallyEvaluationRow.cmbChest.getSelectedItem()));
                                rs.setPlayerId(Integer.parseInt(String.valueOf(panRallyEvaluationRow.cmbChest.getSelectedItem())));
                                rs.setScore(Integer.parseInt(String.valueOf(panRallyEvaluationRow.cmbScore.getSelectedItem())));
                                rallyInsert.getRallyEvaluationSkillScore().add(rs);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(PanRallyLiveEvaluation.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    int idInserted = rallyDao.insertRally(rallyInsert);
                    if (idInserted != 0) {
                        newRally();
                    } else {
                        JOptionPane.showMessageDialog(panRallyCurrent, "Failed to save Rally");
                    }
                } else {
                    JOptionPane.showMessageDialog(panRallyCurrent, "Please Insert Skill");
                }
                break;

        }
    }//GEN-LAST:event_butNextMouseClicked

    private void but1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but1MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su1.getText().equals("")) {
            Player p = ChestMap.get(ro1.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 1, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 1, matchEvaluationId);
            Controller.panMatchSet.pt21.setText(score);
        }
    }//GEN-LAST:event_but1MouseClicked

    private void but2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but2MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su2.getText().equals("")) {
            Player p = ChestMap.get(ro2.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 2, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 2, matchEvaluationId);
            Controller.panMatchSet.pt22.setText(score);
        }
    }//GEN-LAST:event_but2MouseClicked

    private void but3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but3MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su3.getText().equals("")) {
            Player p = ChestMap.get(ro3.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 3, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 3, matchEvaluationId);
            Controller.panMatchSet.pt23.setText(score);
        }
    }//GEN-LAST:event_but3MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su4.getText().equals("")) {
            Player p = ChestMap.get(ro5.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 4, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 4, matchEvaluationId);
            Controller.panMatchSet.pt24.setText(score);
        }

    }//GEN-LAST:event_jLabel28MouseClicked

    private void but5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but5MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su5.getText().equals("")) {
            Player p = ChestMap.get(ro5.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 5, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 5, matchEvaluationId);
            Controller.panMatchSet.pt25.setText(score);
        }
    }//GEN-LAST:event_but5MouseClicked

    private void but6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but6MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su6.getText().equals("")) {
            Player p = ChestMap.get(ro6.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 6, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 6, matchEvaluationId);
            Controller.panMatchSet.pt26.setText(score);
        }
    }//GEN-LAST:event_but6MouseClicked

    public void setRotaionOrders() {
        pos1.setText(positionMap.get(1).getChestNo());
        pos2.setText(positionMap.get(2).getChestNo());
        pos3.setText(positionMap.get(3).getChestNo());
        pos4.setText(positionMap.get(4).getChestNo());
        pos5.setText(positionMap.get(5).getChestNo());
        pos6.setText(positionMap.get(6).getChestNo());
    }

    public void setCombo(java.awt.event.FocusEvent evt) {
        JComboBox cmb = (JComboBox) evt.getSource();
        cmb.removeAllItems();
        cmb.addItem("");
        for (Player p : playerList) {
            boolean exist = false;
            for (Map.Entry<Integer, Player> entry : positionMap.entrySet()) {
                Player pl = entry.getValue();
                if (p.getId() == pl.getId()) {
                    exist = true;
                }
            }
            if (!exist) {
                cmb.addItem(p.getChestNo());
            }
        }
    }

    public void newRally() {
        setScore();
        panRallyList.remove(panRallyNew);
        currentRally++;
        panRallyShow.removeAll();
        validate();
        repaint();
        panRallyCurrent = new PanRallyLiveEvaluation(currentRally, matchEvaluationId, positionMap);
        panRallyShow.add(panRallyCurrent);
        PanRallyBut pnBut = new PanRallyBut();
        pnBut.setRally(currentRally, matchEvaluationId, positionMap);
        panRallyList.add(pnBut);
        validate();
        repaint();
        butNext.setText("Save");

    }

    public void setRotations() {
        pos1.setText(positionMap.get(1).getChestNo());
        pos2.setText(positionMap.get(2).getChestNo());
        pos3.setText(positionMap.get(3).getChestNo());
        pos4.setText(positionMap.get(4).getChestNo());
        pos5.setText(positionMap.get(5).getChestNo());
        pos6.setText(positionMap.get(6).getChestNo());
        libero.setText(positionMap.get(7).getChestNo());

        ro1.setText(positionMap.get(1).getChestNo());
        ro2.setText(positionMap.get(2).getChestNo());
        ro3.setText(positionMap.get(3).getChestNo());
        ro4.setText(positionMap.get(4).getChestNo());
        ro5.setText(positionMap.get(5).getChestNo());
        ro6.setText(positionMap.get(6).getChestNo());

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel but1;
    private javax.swing.JLabel but2;
    private javax.swing.JLabel but3;
    private javax.swing.JPanel but4;
    private javax.swing.JLabel but5;
    private javax.swing.JLabel but6;
    protected javax.swing.JLabel butNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblSetRotationOrder;
    private javax.swing.JLabel lblStart;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblevaluationName;
    private javax.swing.JLabel lblopponentName;
    private javax.swing.JLabel lblrotateleft;
    private javax.swing.JLabel lblrotateright;
    private javax.swing.JTextField libero;
    public javax.swing.JPanel panNext;
    public javax.swing.JPanel panRallyList;
    public javax.swing.JPanel panRallyShow;
    public javax.swing.JPanel panShowRallyInput;
    private javax.swing.JTextField pos1;
    private javax.swing.JTextField pos2;
    private javax.swing.JTextField pos3;
    private javax.swing.JTextField pos4;
    private javax.swing.JTextField pos5;
    private javax.swing.JTextField pos6;
    public javax.swing.JTextField pt11;
    public javax.swing.JTextField pt12;
    public javax.swing.JTextField pt13;
    public javax.swing.JTextField pt14;
    public javax.swing.JTextField pt15;
    public javax.swing.JTextField pt16;
    private javax.swing.JTextField pt21;
    private javax.swing.JTextField pt22;
    private javax.swing.JTextField pt23;
    private javax.swing.JTextField pt24;
    private javax.swing.JTextField pt25;
    private javax.swing.JTextField pt26;
    private javax.swing.JTextField ro1;
    private javax.swing.JTextField ro2;
    private javax.swing.JTextField ro3;
    private javax.swing.JTextField ro4;
    private javax.swing.JTextField ro5;
    private javax.swing.JTextField ro6;
    public javax.swing.JTextField su1;
    public javax.swing.JTextField su2;
    public javax.swing.JTextField su3;
    public javax.swing.JTextField su4;
    public javax.swing.JTextField su5;
    public javax.swing.JTextField su6;
    private javax.swing.JTextField txtEvaluator;
    // End of variables declaration//GEN-END:variables
}
