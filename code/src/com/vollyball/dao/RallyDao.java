/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nishant.vibhute
 */
public class RallyDao {

    DbUtil db = new DbUtil();
    Connection con;

    public int insertRally(RallyEvaluation re) {
        int id = 0;
        int rid = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.rally"));
            ps.setInt(1, re.getRallyNum());
            ps.setInt(2, re.getHomeScore());
            ps.setInt(3, re.getOpponentScore());
            ps.setString(4, re.getStartTime());
            ps.setString(5, re.getEndTime());
            ps.setInt(6, re.getMatchEvaluationId());

            id = ps.executeUpdate();

            if (id != 0) {
                PreparedStatement ps4 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.score"));
                ps4.setInt(1, re.getHomeScore());
                ps4.setInt(2, re.getOpponentScore());
                ps4.setInt(3, re.getMatchEvaluationId());
                ps4.executeUpdate();

                PreparedStatement ps5 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.plusminus"));
                ps5.setInt(1, re.getOp());
                ps5.setInt(2, re.getTf());
                ps5.setInt(3, re.getMatchEvaluationId());
                ps5.executeUpdate();

                PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latest.rally.id"));
                ResultSet rs = ps3.executeQuery();

                while (rs.next()) {
                    rid = rs.getInt(1);
                }

                for (RallyEvaluationSkillScore ress : re.getRallyEvaluationSkillScore()) {
                    PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.rallydetails"));
                    ps1.setInt(1, ress.getSkillId());
                    ps1.setInt(2, ress.getPlayerId());
                    ps1.setInt(3, ress.getScore());
                    ps1.setInt(4, rid);
                    ps1.executeUpdate();
                }

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rid;

    }

    public int updateRally(RallyEvaluation re) {

        int id = 0;
        int rid = re.getId();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("update.rally"));

            ps.setInt(1, re.getHomeScore());
            ps.setInt(2, re.getOpponentScore());
            ps.setInt(3, re.getId());

            id = ps.executeUpdate();

            if (id != 0) {
                PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("delete.rallydetails"));
                ps3.setInt(1, rid);
                ps3.executeUpdate();

                for (RallyEvaluationSkillScore ress : re.getRallyEvaluationSkillScore()) {
                    PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.rallydetails"));
                    ps1.setInt(1, ress.getSkillId());
                    ps1.setInt(2, ress.getPlayerId());
                    ps1.setInt(3, ress.getScore());
                    ps1.setInt(4, rid);
                    ps1.executeUpdate();
                }

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rid;

    }

    public RallyEvaluation getRally(int rallyNum, int evaluationId) {
        RallyEvaluation re = new RallyEvaluation();
        List<RallyEvaluationSkillScore> rallyEvaluationSkillScore = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rally"));
            ps.setInt(1, rallyNum);
            ps.setInt(2, evaluationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                re.setId(rs.getInt(1));
                re.setRallyNum(rs.getInt(2));
                re.setHomeScore(rs.getInt(3));
                re.setOpponentScore(rs.getInt(4));
                re.setStartTime(rs.getString(5));
                re.setEndTime(rs.getString(6));
                re.setMatchEvaluationId(rs.getInt(7));
            }
            if (re.getId() != 0) {
                PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallydetails"));
                ps1.setInt(1, re.getId());
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    RallyEvaluationSkillScore ress = new RallyEvaluationSkillScore();
                    ress.setId(rs1.getInt(1));
                    ress.setSkillId(rs1.getInt(2));
                    ress.setPlayerId(rs1.getInt(3));
                    ress.setScore(rs1.getInt(4));
                    ress.setRallyId(rs1.getInt(5));
                    rallyEvaluationSkillScore.add(ress);
                }
                re.setRallyEvaluationSkillScore(rallyEvaluationSkillScore);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return re;
    }

    public List<RallyEvaluation> getRalliesList(int matchevaluationId) {
        List<RallyEvaluation> list = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallylist"));
            ps.setInt(1, matchevaluationId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RallyEvaluation r = new RallyEvaluation();
                r.setId(rs.getInt(1));
                r.setRallyNum(rs.getInt(2));
                list.add(r);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;

    }

}
