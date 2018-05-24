/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

/**
 *
 * @author nishant.vibhute
 */
public class Player {

    int id;
    String name;
    String chestNo;
    int teamId;
    int position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChestNo() {
        return chestNo;
    }

    public void setChestNo(String chestNo) {
        this.chestNo = chestNo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
