/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.db;

import com.vollyball.frames.FrmRegister;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class Setup extends Thread {

    DbUtil db = new DbUtil();

    public void run() {
        String resp = setupDb();
        if (resp.equalsIgnoreCase("success")) {
            createCompetationTable();
            FrmRegister.lblStatus.setText("Done");
            FrmRegister.lblFinish.setVisible(true);
        }
        for (int i = 10; i <= 100; i = i + 10) {
            FrmRegister.pgrStatus.setValue(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String setupDb() {
        boolean isCreated = db.createNewDatabase();
        if (isCreated) {
            FrmRegister.pgrStatus.setValue(70);
        }
        return "success";
    }

    public String createCompetationTable() {
        boolean isCreated = executeQuery(CommonUtil.getResourceProperty("create.competition"));
        FrmRegister.pgrStatus.setValue(80);
        return "success";
    }

    public boolean executeQuery(String query) {
        Connection conn = db.getConnection();
        Statement stmt;
        boolean resp = false;
        try {
            stmt = conn.createStatement();
            resp = stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(conn);
        }
        return resp;
    }
}
