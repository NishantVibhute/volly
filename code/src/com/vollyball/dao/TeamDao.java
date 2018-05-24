/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.Player;
import com.vollyball.bean.Team;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nishant.vibhute
 */
public class TeamDao {

    DbUtil db = new DbUtil();
    Connection con;

    public int insertTeam(Team team) {
        int count = 0;
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.team"));
            ps.setString(1, team.getName());
            ps.setString(2, team.getCoach());
            ps.setString(3, team.getAsstCoach());
            ps.setString(4, team.getTrainer());
            ps.setString(5, team.getMedicalOffice());
            ps.setString(6, team.getAnalyzer());
            ps.setInt(7, team.getCompId());
            count = ps.executeUpdate();

            if (count != 0) {
                PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latest.team.id"));
                ResultSet rs = ps1.executeQuery();

                while (rs.next()) {
                    id = rs.getInt(1);
                }

                if (id != 0) {
                    for (Player p : team.getPlayerList()) {
                        if (p.getName() != null && !p.getName().equals("")) {
                            PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.player"));
                            ps2.setString(1, p.getName());
                            ps2.setString(2, p.getChestNo());
                            ps2.setInt(3, id);
                            ps2.executeUpdate();
                        }
                    }
                }

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;

    }

}
