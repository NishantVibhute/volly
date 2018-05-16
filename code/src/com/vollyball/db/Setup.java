/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.db;

import com.vollyball.bean.UserBean;
import com.vollyball.controller.Controller;
import com.vollyball.enums.Rating;
import com.vollyball.enums.SetupEnum;
import com.vollyball.enums.Skill;
import com.vollyball.enums.SkillDetails;
import com.vollyball.frames.FrmRegister;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            createUserTable(60);
            createCompetationTable(65);
            createTeamTable(70);
            createPlayersTable(75);
            createPoolTable(80);
            createMatchesTable(80);
            createMSkillsTable(80);
            createMRatingTable(80);
            createMSkillDescCriteriaTable(80);
            createMSkillDetailsTable(80);
            createMSkillDescCriteriaPointTable(80);
            createMatchEvaluationTable(80);
            createRallyTable(80);
            createRallyDetailsTable(80);
            createRallyDetailsCriteriaTable(80);
            insertRatings(90);
            insertSkills(95);
            insertSkillDetails(97);
            insertUser(100);
            FrmRegister.lblStatus.setText("Done");
            FrmRegister.lblFinish.setVisible(true);
        }

    }

    public String setupDb() {
        boolean isCreated = db.createNewDatabase();
        if (isCreated) {
            Controller.stepCompleted.put(SetupEnum.Database.getStep(), SetupEnum.Database.getValue());
            FrmRegister.pgrStatus.setValue(70);
        }
        return "success";
    }

    public void createUserTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.user"), SetupEnum.User, status);
    }

    public void createCompetationTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.competition"), SetupEnum.Competition, status);
    }

    public void createTeamTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.team"), SetupEnum.Teams, status);
    }

    public void createPlayersTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.players"), SetupEnum.Players, status);
    }

    public void createPoolTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.pool"), SetupEnum.Pool, status);
    }

    public void createMatchesTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.matches"), SetupEnum.Matches, status);
    }

    public void createMSkillsTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.mskills"), SetupEnum.MSkills, status);
    }

    public void createMRatingTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.mrating"), SetupEnum.MRating, status);
    }

    public void createMSkillDescCriteriaTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.mskilldesccriteria"), SetupEnum.MSkillDescCriteria, status);
    }

    public void createMSkillDetailsTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.mskilldetails"), SetupEnum.MSkillDetails, status);
    }

    public void createMSkillDescCriteriaPointTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.mskilldesccriteriapoint"), SetupEnum.MSkillDescCriteriaPoint, status);
    }

    public void createMatchEvaluationTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.matchevaluation"), SetupEnum.MatchEvaluation, status);
    }

    public void createRallyTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.rally"), SetupEnum.Rally, status);
    }

    public void createRallyDetailsTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.rallydetails"), SetupEnum.RallyDetails, status);
    }

    public void createRallyDetailsCriteriaTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.rallydetailscriteria"), SetupEnum.RallyDetailsCriteria, status);
    }

    public void insertRatings(int status) {
        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.ratings");
        for (Rating dir : Rating.values()) {
            Connection conn = db.getConnection();
            int resp = 0;
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, dir.getId());
                preparedStmt.setString(2, dir.getType());
                resp = preparedStmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnection(conn);
            }
            count = count + resp;
        }
        int total = Rating.values().length;
        if (total == count) {
            Controller.stepCompleted.put(SetupEnum.InsertRating.getStep(), SetupEnum.InsertRating.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }
    }

    public void insertSkills(int status) {
        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.skills");
        for (Skill dir : Skill.values()) {
            Connection conn = db.getConnection();
            int resp = 0;
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, dir.getId());
                preparedStmt.setString(2, dir.getType());
                resp = preparedStmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnection(conn);
            }
            count = count + resp;
        }

        int total = Skill.values().length;

        if (total == count) {
            Controller.stepCompleted.put(SetupEnum.InsertSkills.getStep(), SetupEnum.InsertSkills.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }

    }

    public void insertSkillDetails(int status) {
        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.skillsdetails");
        for (SkillDetails dir : SkillDetails.values()) {
            Connection conn = db.getConnection();
            int resp = 0;
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, dir.getId());
                preparedStmt.setInt(2, dir.getSkillId());
                preparedStmt.setInt(3, dir.getRatingId());
                preparedStmt.setString(4, dir.getName());
                preparedStmt.setString(5, dir.getDescription());
                resp = preparedStmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnection(conn);
            }
            count = count + resp;
        }

        int total = SkillDetails.values().length;

        if (total == count) {
            Controller.stepCompleted.put(SetupEnum.InsertSkillDetails.getStep(), SetupEnum.InsertSkillDetails.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }

    }

    public void insertUser(int status) {
        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.user");

        Connection conn = db.getConnection();
        int resp = 0;
        try {
            UserBean ub = Controller.userBean;
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, ub.getUserName());
            preparedStmt.setString(2, ub.getEmailId());
            preparedStmt.setString(3, ub.getCode());
            preparedStmt.setInt(4, 1);
            preparedStmt.setString(5, "");
            preparedStmt.setString(6, ub.getPassword());
            resp = preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(conn);
        }

        if (resp != 0) {
            Controller.stepCompleted.put(SetupEnum.InsertUser.getStep(), SetupEnum.InsertUser.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }
    }

    public boolean executeQuery(String query, SetupEnum enumvalue, int status) {
        Connection conn = db.getConnection();
        Statement stmt;
        boolean resp = false;
        try {
            stmt = conn.createStatement();
            resp = stmt.execute(query);
            Controller.stepCompleted.put(enumvalue.getStep(), enumvalue.getValue());
            FrmRegister.pgrStatus.setValue(status);
        } catch (SQLException ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(conn);
        }
        return resp;
    }

}
