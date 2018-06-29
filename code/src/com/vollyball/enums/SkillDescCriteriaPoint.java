/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nishant.vibhute
 */
public enum SkillDescCriteriaPoint {

    ServiceA1(1, "", "JF", 1),
    ServiceA2(2, "", "JS", 1),
    ServiceA3(3, "", "JP", 1),
    ServiceA4(4, "", "SF", 1),
    ServiceA5(5, "", "SS", 1),
    ServiceB1(6, "", "DC", 2),
    ServiceB2(7, "", "PP", 2),
    ServiceB3(8, "", "SP", 2),
    ServiceB4(9, "", "MB", 2),
    ServiceB5(10, "", "BP", 2),
    ServiceB6(11, "", "NE", 2),
    ServiceB7(12, "", "AE", 2),
    ServiceC1(13, "", "C", 3),
    ServiceC2(14, "", "SL", 3),
    ServiceD1(15, "", "1", 4),
    ServiceD2(16, "", "5", 4),
    ServiceD3(17, "", "6", 4),
    ServiceE1(18, "", "1", 5),
    ServiceE2(19, "", "2", 5),
    ServiceE3(20, "", "3", 5),
    ServiceE4(21, "", "4", 5),
    ServiceE5(22, "", "5", 5),
    ServiceE6(23, "", "6", 5),
    ServiceF1(15, "", "5", 6),
    ServiceF2(16, "", "4", 6),
    ServiceF3(17, "", "3", 6),
    ServiceG1(24, "", "S", 7),
    ServiceG2(25, "", "OH", 7),
    ServiceG3(26, "", "MB", 7),
    ServiceG4(27, "", "L", 7),
    ServiceG5(28, "", "U", 7),
    ServiceH1(29, "", "0", 8),
    ServiceI1(30, "", "AT", 9),
    ServiceI2(31, "", "RC", 9),
    ServiceI3(32, "", "SC", 9),
    ServiceI4(33, "", "TPS", 9),
    ServiceI5(34, "", "OPS", 9),
    ServiceJ1(35, "", "FR", 10),
    ServiceJ2(36, "", "FM", 10),
    ServiceJ3(37, "", "FL", 10),
    ServiceJ4(38, "", "RR", 10),
    ServiceJ5(39, "", "RM", 10),
    ServiceJ6(40, "", "RL", 10),
    AttackA1(41, "", "C", 11),
    AttackA2(42, "", "L", 11),
    AttackA3(43, "", "IN", 11),
    AttackA4(44, "", "OT", 11),
    AttackA5(45, "", "BT", 11),
    AttackA6(46, "", "OL", 11),
    AttackA7(47, "", "D", 11),
    AttackB1(48, "", "5C", 12),
    AttackB2(49, "", "4C", 12),
    AttackB3(50, "", "3C", 12),
    AttackB4(51, "", "2C", 12),
    AttackB5(52, "", "Single", 12),
    AttackC1(53, "", "DC", 13),
    AttackC2(54, "", "PP", 13),
    AttackC3(55, "", "SP", 13),
    AttackC4(56, "", "MB", 13),
    AttackC5(57, "", "BP", 13),
    AttackC6(58, "", "NSE", 13),
    AttackC7(59, "", "NEL", 13),
    AttackD1(60, "", "Spit Half", 14),
    AttackD2(61, "", "Tandem", 14),
    AttackE1(62, "", "1", 15),
    AttackE2(63, "", "2", 15),
    AttackE3(64, "", "3", 15),
    AttackE4(65, "", "4", 15),
    AttackE5(66, "", "5", 15),
    AttackE6(67, "", "A", 15),
    AttackE7(68, "", "B", 15),
    AttackE8(69, "", "C", 15),
    AttackF1(70, "", "Low", 16),
    AttackF2(71, "", "Medium", 16),
    AttackF3(72, "", "High", 16),
    AttackG1(73, "", "4", 17),
    AttackG2(74, "", "3", 17),
    AttackG3(75, "", "2", 17),
    AttackG4(76, "", "6", 17),
    AttackG5(77, "", "5", 17),
    AttackG6(78, "", "1", 17),
    AttackH1(79, "", "K1", 18),
    AttackH2(80, "", "K2", 18),
    AttackH3(81, "", "T", 18),
    AttackI1(82, "", "OH", 19),
    AttackI2(83, "", "MB", 19),
    AttackI3(84, "", "U", 19),
    AttackI4(85, "", "S", 19),
    AttackJ1(86, "", "SGL", 20),
    AttackJ2(87, "", "DBL", 20),
    AttackJ3(88, "", "TPL", 20),
    AttackK1(89, "", "1", 21),
    AttackK2(90, "", "2", 21),
    AttackK3(91, "", "3", 21),
    AttackK4(92, "", "4", 21),
    AttackK5(93, "", "5", 21),
    AttackL1(94, "", "1-2-3", 22),
    AttackL2(95, "", "1-3-2", 22),
    AttackL3(96, "", "2-1-2", 22),
    AttackL4(97, "", "2-0-4", 22),
    AttackL5(98, "", "3-1-3", 22),
    AttackL6(99, "", "3-0-3", 22),
    AttackM1(100, "", "1", 23),
    AttackM2(101, "", "2", 23),
    AttackM3(102, "", "3", 23),
    AttackM4(103, "", "4", 23),
    AttackM5(104, "", "5", 23),
    AttackM6(105, "", "6", 23),
    AttackN1(106, "", "S", 24),
    AttackN2(107, "", "OH", 24),
    AttackN3(108, "", "MB", 24),
    AttackN4(109, "", "L", 24),
    AttackN5(110, "", "U", 24),
    AttackO1(111, "", "0:0", 25),
    AttackP1(112, "", "AT", 26),
    AttackP2(113, "", "RC", 26),
    AttackP3(114, "", "SC", 26),
    AttackP4(115, "", "TPS", 26),
    AttackP5(116, "", "OPS", 26),
    AttackQ1(117, "", "FR", 27),
    AttackQ2(118, "", "FM", 27),
    AttackQ3(119, "", "FL", 27),
    AttackQ4(120, "", "RR", 27),
    AttackQ5(121, "", "RM", 27),
    AttackQ6(122, "", "RL", 27);

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

    public static List<SkillDescCriteriaPoint> getTypeBySkillDescId(int skillId) {
        List<SkillDescCriteriaPoint> list = new ArrayList<>();
        for (SkillDescCriteriaPoint e : values()) {
            if (e.skillDescCriteriaId == skillId) {
                list.add(e);
            }
        }
        return list;
    }

}
