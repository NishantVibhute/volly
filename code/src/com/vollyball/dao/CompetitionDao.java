/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author nishant.vibhute
 */
public class CompetitionDao {

    DbUtil db = new DbUtil();
    Connection con;

    public int insertCompetition(CompetitionBean cb) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.competition"));
            ps.setString(1, cb.getName());
            ps.setString(2, cb.getVenue());
            ps.setString(3, cb.getStartDate());
            ps.setString(4, cb.getName());
            ps.setString(5, cb.getAgeGroup());
            count = ps.executeUpdate();

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }

}
