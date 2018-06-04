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
    String result;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
