/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nishant.vibhute
 */
public class RallyEvaluation {

    int id;
    int rallyNum;
    int homeScore;
    int opponentScore;
    String startTime;
    String endTime;
    int matchEvaluationId;
    List<RallyEvaluationSkillScore> rallyEvaluationSkillScore = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRallyNum() {
        return rallyNum;
    }

    public void setRallyNum(int rallyNum) {
        this.rallyNum = rallyNum;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<RallyEvaluationSkillScore> getRallyEvaluationSkillScore() {
        return rallyEvaluationSkillScore;
    }

    public void setRallyEvaluationSkillScore(List<RallyEvaluationSkillScore> rallyEvaluationSkillScore) {
        this.rallyEvaluationSkillScore = rallyEvaluationSkillScore;
    }

    public int getMatchEvaluationId() {
        return matchEvaluationId;
    }

    public void setMatchEvaluationId(int matchEvaluationId) {
        this.matchEvaluationId = matchEvaluationId;
    }

}
