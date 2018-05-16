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
public enum SkillsDescCriteria {

    ServiceA(1, "Type & Techniques of Service", 1),
    ServiceB(2, "Serve Tactics", 1),
    ServiceC(3, "Direction of Service", 1),
    ServiceD(4, "Serve From To Zone", 1),
    ServiceE(5, "Reception formation", 1),
    ServiceF(6, "Receiver Position", 1),
    ServiceG(7, "Score at the time of ace serve", 1),
    ServiceH(8, "Serve in situation", 1),
    ServiceI(9, "Opponents Setter Position", 1);

    int id;
    String type;
    int skillId;

    private SkillsDescCriteria(int id, String type, int skillId) {
        this.id = id;
        this.type = type;
        this.skillId = skillId;
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

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

}
