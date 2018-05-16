package com.vollyball.enums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nishant.vibhute
 */
public enum SetupEnum {

    Database(1, "Database Created"),
    User(2, "user"),
    Competition(3, "Competition Created"),
    Teams(4, "Teams"),
    Players(5, "Players"),
    Pool(6, "Pool Created"),
    Matches(7, "Matches Created"),
    MSkills(8, "m_skills"),
    MRating(9, "m_rating"),
    MSkillDescCriteria(10, "m_skill_desc_criteria"),
    MSkillDetails(11, "m_skill_details"),
    MSkillDescCriteriaPoint(12, "m_skill_desc_criteria_point"),
    MatchEvaluation(13, "match_evaluation"),
    Rally(14, "rally"),
    RallyDetails(15, "rally_details"),
    RallyDetailsCriteria(16, "rally_details_criteria"),
    InsertRating(17, "Ratings Inserted"),
    InsertSkills(18, "Skills Inserted"),
    InsertSkillDetails(19, "Skill details Inserted"),
    InsertUser(20, "User Inserted");

    int step;
    String value;

    SetupEnum(int step, String value) {
        this.step = step;
        this.value = value;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
