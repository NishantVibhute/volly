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
public enum SkillsDescCriteria {

    ServiceA(1, "Type & Techniques of Service", 1),
    ServiceB(2, "Serve Tactics", 1),
    ServiceC(3, "Direction of Service", 1),
    ServiceD(4, "Serve From Zone", 1),
    ServiceE(5, "Serve To Zone", 1),
    ServiceF(6, "Reception formation", 1),
    ServiceG(7, "Receiver Position", 1),
    ServiceH(8, "Score at the time of ace serve", 1),
    ServiceI(9, "Serve in situation", 1),
    ServiceJ(10, "Setter Position", 1),
    AttackA(11, "Type & Techniques of attack", 2),
    AttackB(12, "Attack Strategy", 2),
    AttackC(13, "Attacking Tactics", 2),
    AttackD(14, "Type of Combination", 2),
    AttackE(15, "Attacking Slot", 2),
    AttackF(16, "Attack on Tempo", 2),
    AttackG(17, "Attacking Zone", 2),
    AttackH(18, "Attack in phase", 2),
    AttackI(19, "Attackers position", 2),
    AttackJ(20, "No of Block", 2),
    AttackK(21, "Attack Targets Zone", 2),
    AttackL(22, "Defence Formation", 2),
    AttackM(23, "Attack Defended Zone", 2),
    AttackN(24, "Defender Position", 2),
    AttackO(25, "Score at time of attack", 2),
    AttackP(26, "Attack in situation", 2),
    AttackQ(27, "Setter position", 2),
    BlockA(28, "Type & Techniques of Block", 3),
    BlockB(29, "Type of Attack", 3),
    BlockC(30, "Block on Combination of attack", 3),
    BlockD(31, "Blocking Tactics", 3),
    BlockE(32, "Type of Combination", 3),
    BlockF(33, "Blocking Slot", 3),
    BlockG(34, "Block on Attacking Tempo", 3),
    BlockH(35, "Blocking Zone", 3),
    BlockI(36, "Blocking in phase", 3),
    BlockJ(37, "Blockers Position", 3),
    BlockK(38, "No of Blockers", 3),
    BlockL(39, "Defence Formation", 3),
    BlockM(40, "Block reflected ball at zone", 3),
    BlockN(41, "Block Defended court", 3),
    BlockO(42, "Defender Position", 3),
    BlockP(43, "Score at time of attack", 3),
    BlockQ(44, "Attack in situation", 3),
    BlockR(45, "Aetter Position", 3),
    SetA(46, "Type of Set", 4),
    SetB(47, "Set Tempo", 4),
    SetC(48, "Reception at", 4),
    SetD(49, "Set Slot", 4),
    SetE(50, "Set Delivery zone", 4),
    SetF(51, "Combination of attack", 4),
    SetG(52, "Type of Attack", 4),
    SetH(53, "Type of Blockers", 4),
    SetI(54, "Blocking tactics", 4),
    SetJ(55, "Game of phase", 4),
    SetK(56, "Attackers position", 4),
    SetL(57, "Defence formation", 4),
    SetM(58, "Defender position", 4),
    SetN(59, "Score at the time of set", 4),
    SetO(60, "Setter Position", 4),
    ReceptionA(61, "Type of Serve", 5),
    ReceptionB(62, "Type of Reception", 5),
    ReceptionC(63, "Reception Zone", 5),
    ReceptionD(64, "Reception Formation", 5),
    ReceptionE(65, "Reception at", 5),
    ReceptionF(66, "Receiver Position", 5),
    ReceptionG(67, "Type of set", 5),
    ReceptionH(68, "Set tempo", 5),
    ReceptionI(69, "Set Slot", 5),
    ReceptionJ(70, "Set delivery zone", 5),
    ReceptionK(71, "Combination of Attack", 5),
    ReceptionL(72, "Type of Attack", 5),
    ReceptionM(73, "Type of Blockers", 5),
    ReceptionN(74, "Attacker Position", 5),
    ReceptionO(75, "Score at the time of Reception", 5),
    ReceptionP(76, "Setter Position", 5),
    DefenceA(77, "Type of Attack by opponent", 6),
    DefenceB(78, "Combination of Attack", 6),
    DefenceC(79, "Blocking Zone", 6),
    DefenceD(80, "Type of Block", 6),
    DefenceE(81, "Blocking Tactics", 6),
    DefenceF(82, "Blockers Position", 6),
    DefenceG(83, "Block reflected ball at", 6),
    DefenceH(84, "Defence formation", 6),
    DefenceI(85, "Defence zone", 6),
    DefenceJ(86, "Type of Defence", 6),
    DefenceK(87, "Defenders Position", 6),
    DefenceL(88, "Defence ball at", 6),
    DefenceM(89, "Defence in phase", 6),
    DefenceN(90, "Setter position", 6),
    DefenceO(91, "Type of set", 6),
    DefenceP(92, "Set Tempo", 6),
    DefenceQ(93, "Set Slot", 6),
    DefenceR(94, "Set delivery zone", 6),
    DefenceS(95, "Type of Block by Opponent", 6),
    DefenceT(96, "Attack combination of home team", 6),
    DefenceU(97, "Score at time of Reception", 6);

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

    public static List<SkillsDescCriteria> getTypeBySkill(int skillId) {
        List<SkillsDescCriteria> list = new ArrayList<>();
        for (SkillsDescCriteria e : values()) {
            if (e.skillId == skillId) {
                list.add(e);
            }
        }
        return list;
    }

    public static SkillsDescCriteria getTypeByName(String name) {

        for (SkillsDescCriteria e : values()) {
            if (e.type.equals(name)) {
                return e;
            }
        }
        return null;
    }

}
