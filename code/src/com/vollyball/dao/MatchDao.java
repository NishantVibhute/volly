/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.MatchSet;
import com.vollyball.bean.SetRotationOrder;
import com.vollyball.bean.SetSubstitution;
import com.vollyball.bean.SetTimeout;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class MatchDao {

    DbUtil db = new DbUtil();
    Connection con;

    public int insertMatch(MatchBean mb) {
        int count = 0;
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.match"));
            ps.setInt(1, mb.getTeam1());
            ps.setInt(2, mb.getTeam2());
            ps.setInt(3, mb.getDayNumber());
            ps.setInt(4, mb.getMatchNumber());
            ps.setString(5, mb.getDate());
            ps.setString(6, mb.getTime());
            ps.setString(7, mb.getPhase());
            ps.setInt(8, mb.getCompId());
            count = ps.executeUpdate();

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;

    }

    public List<MatchBean> getMatches(int id) {
        List<MatchBean> matchList = new ArrayList<>();
        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matches"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MatchBean mb = new MatchBean();
                mb.setTeam1name(rs.getString(1));
                mb.setTeam2name(rs.getString(2));
                mb.setDate(rs.getString(3));
                mb.setTeam1(rs.getInt(4));
                mb.setTeam2(rs.getInt(5));
                mb.setId(rs.getInt(6));
                matchList.add(mb);

            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matchList;
    }

    public MatchBean getMatchesById(int id, int matchId) {
        MatchBean mb = new MatchBean();
        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchesbyid"));
            ps.setInt(1, id);
            ps.setInt(2, matchId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                mb.setTeam1name(rs.getString(1));
                mb.setTeam2name(rs.getString(2));
                mb.setDate(rs.getString(3));
                mb.setTeam1(rs.getInt(4));
                mb.setTeam2(rs.getInt(5));
                mb.setId(rs.getInt(6));

            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mb;
    }

    public int insertMatchPlayers(int matchId, int teamid, List<Integer> players) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("delete.matchPlayers"));
            ps1.setInt(1, matchId);
//            ps1.setInt(2, teamid);
            ps1.executeUpdate();

            for (int id : players) {
                PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchplayers"));
                ps.setInt(1, matchId);
                ps.setInt(2, teamid);
                ps.setInt(3, id);

                count = ps.executeUpdate();
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public int isTeamSelected(int matchId) {
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.isMatchSelected"));
            ps.setInt(1, matchId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public int saveMatchSet(MatchSet ms) {
        int id = 0;
        int mid = 0;

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchset"));
            ps.setInt(1, ms.getMatchId());
            ps.setInt(2, ms.getEvaluationTeamId());
            ps.setInt(3, ms.getOpponentTeamId());
            ps.setInt(4, ms.getSetNo());
            ps.setInt(5, ms.getWon_by());
            ps.setString(6, ms.getStart_time());
            ps.setString(7, ms.getEnd_time());
            ps.setString(8, ms.getEvaluator());
            ps.setString(9, ms.getDate());
            id = ps.executeUpdate();

            if (id != 0) {

                PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latest.matchset.id"));
                ResultSet rs = ps3.executeQuery();

                while (rs.next()) {
                    mid = rs.getInt(1);
                }

                for (SetRotationOrder s : ms.getRotationOrder()) {
                    PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchset.rotationorder"));
                    ps1.setInt(1, s.getPosition());
                    ps1.setInt(2, s.getPlayerId());
                    ps1.setInt(3, mid);
                    ps1.executeUpdate();
                }

                for (SetSubstitution s : ms.getSetSubstitutions()) {
                    if (s.getPosition() != 7) {
                        PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchset.substitution"));
                        ps1.setInt(1, s.getPosition());
                        ps1.setInt(2, s.getRotation_player_id());
                        ps1.setInt(3, mid);
                        ps1.executeUpdate();
                    }
                }

                PreparedStatement ps5 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchset.plusminus"));
                ps5.setInt(1, 0);
                ps5.setInt(2, 0);
                ps5.setInt(3, mid);
                ps5.executeUpdate();

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mid;

    }

    public MatchSet getMatchSet(int setId, int matchId) {

        MatchSet ms = new MatchSet();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset"));
            ps.setInt(1, matchId);
            ps.setInt(2, setId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ms.setId(rs.getInt(1));
                ms.setMatchId(rs.getInt(2));
                ms.setEvaluationTeamId(rs.getInt(3));
                ms.setOpponentTeamId(rs.getInt(4));
                ms.setSetNo(rs.getInt(5));
                ms.setHomeScore(rs.getInt(6));
                ms.setOpponentScore(rs.getInt(7));
                ms.setWon_by(rs.getInt(8));
                ms.setStart_time(rs.getString(9));
                ms.setEnd_time(rs.getString(10));
                ms.setEvaluator(rs.getString(11));
                ms.setDate(rs.getString(12));
            }

            if (ms.getId() != 0) {
                List<SetRotationOrder> rotationOrder = new ArrayList<>();

                PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.rotationorder"));
                ps1.setInt(1, ms.getId());
                ResultSet rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    SetRotationOrder s = new SetRotationOrder();
                    s.setId(rs1.getInt(1));
                    s.setPosition(rs1.getInt(2));
                    s.setPlayerId(rs1.getInt(3));
                    s.setMatch_evaluation_id(rs1.getInt(4));
                    rotationOrder.add(s);

                }
                ms.setRotationOrder(rotationOrder);
                List<SetSubstitution> setSubstitutions = new ArrayList<>();
                PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.substitution"));
                ps2.setInt(1, ms.getId());
                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {
                    SetSubstitution s = new SetSubstitution();
                    s.setId(rs2.getInt(1));
                    s.setPosition(rs2.getInt(2));
                    s.setRotation_player_id(rs2.getInt(3));
                    s.setMatch_evaluation_id(rs2.getInt(4));
                    s.setSubstitutePlayerId(rs2.getInt(5));
                    s.setPoint1(rs2.getString(6));
                    s.setPoint2(rs2.getString(7));
                    setSubstitutions.add(s);

                }
                ms.setSetSubstitutions(setSubstitutions);

                PreparedStatement ps6 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.plusminus"));
                ps6.setInt(1, ms.getId());
                ResultSet rs6 = ps6.executeQuery();

                while (rs6.next()) {
                    ms.setOp(rs6.getInt(1));
                    ms.setTf(rs6.getInt(2));

                }

                List<SetTimeout> setTimeout = new ArrayList<>();
                PreparedStatement ps7 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.timeout"));
                ps7.setInt(1, ms.getId());
                ResultSet rs7 = ps7.executeQuery();

                while (rs7.next()) {
                    SetTimeout s = new SetTimeout();
                    s.setPosition(rs7.getInt(1));
                    s.setTeam(rs7.getString(2));
                    s.setScoreA(rs7.getInt(3));
                    s.setScoreB(rs7.getInt(4));
                    setTimeout.add(s);

                }
                ms.setSetTimeout(setTimeout);

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ms;

    }

    public int updateSubstitution(int subPlayerId, String score, int position, int matchEvaluationId) {
        int id = 0;
        try {

            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.substitution.point1"));
            ps1.setInt(1, subPlayerId);
            ps1.setString(2, score);
            ps1.setInt(3, position);
            ps1.setInt(4, matchEvaluationId);
            id = ps1.executeUpdate();

            this.db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public int updateSubstitutionPoint2(String score, int position, int matchEvaluationId) {
        int id = 0;
        try {

            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.substitution.point2"));

            ps1.setString(1, score);
            ps1.setInt(2, position);
            ps1.setInt(3, matchEvaluationId);
            id = ps1.executeUpdate();

            this.db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public int getTimeOutCount(int matchEvaluationId) {
        int id = 0;
        try {

            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.timeout.count"));
            ps1.setInt(1, matchEvaluationId);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                id = rs1.getInt(1);
            }

            this.db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public int insertTimeout(SetTimeout st) {
        int id = 0;
        try {

            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchset.timeout"));

            ps1.setInt(1, st.getPosition());
            ps1.setString(2, st.getTeam());
            ps1.setInt(3, st.getScoreA());
            ps1.setInt(4, st.getScoreB());
            ps1.setInt(5, st.getMatchEvalId());
            id = ps1.executeUpdate();

            this.db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
