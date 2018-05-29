/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.MatchBean;
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
            ps1.setInt(2, teamid);
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
}
