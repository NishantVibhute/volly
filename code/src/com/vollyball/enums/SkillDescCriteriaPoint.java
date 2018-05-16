/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.enums;

/**
 *
 * @author nishant.vibhute
 */
public enum SkillDescCriteriaPoint {

    ServiceJF(1, " Jump Float", "JF", 1),
    ServiceJS(2, " Jump Spin", "JS", 1),
    ServiceJP(3, " Jump Power", "JP", 1),
    ServiceSF(4, " Standing Float", "SF", 1),
    ServiceSS(5, " Standing Spin", "SS", 1),
    ServiceDC(6, " Deep Corners ", "DC", 2),
    ServicePP(7, " Penetrating Players", "PP", 2),
    ServiceSP(8, " Switching Players", "SP", 2),
    ServiceMBs(9, " Middle Blockers", "MB", 2),
    ServiceBP(10, " Between Players", "BP", 2),
    ServiceNE(11, " Service from near endline", "NE", 2),
    ServiceAE(12, " Service from away endline", "AE", 2),
    ServiceC(13, " Crosscourt", "C", 3),
    ServiceSL(14, " Straight Line", "L", 3),
    ServiceST(15, " Straight", "ST", 3),
    ServiceS(16, " Setter", "S", 16),
    ServiceOH(17, " Outside Hitter", "OH", 17),
    ServiceMB(18, " Middle Blocker", "MB", 18),
    ServiceU(19, " Universal Player", "U", 19),
    ServiceL(20, " Libero", "L", 20),
    ServiceAT(21, " After Time out", "AT", 21),
    ServiceRC(22, " Rally continue", "RC", 22),
    ServiceSC(23, " Service Change", "SC", 23),
    ServiceTPS(24, " Team Player Substitution", "TPS", 24),
    ServiceOPS(25, " Opponents Player Substitution.", "OPS", 25);

    int id;
    String type;
    String abbreviation;
    int skillDescCriteriaId;

    private SkillDescCriteriaPoint(int id, String type, String abbreviation, int skillDescCriteriaId) {
        this.id = id;
        this.type = type;
        this.abbreviation = abbreviation;
        this.skillDescCriteriaId = skillDescCriteriaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getSkillDescCriteriaId() {
        return skillDescCriteriaId;
    }

    public void setSkillDescCriteriaId(int skillDescCriteriaId) {
        this.skillDescCriteriaId = skillDescCriteriaId;
    }

}
