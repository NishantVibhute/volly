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
public enum VollyCourtCoordinate {

    Service11(1, "Service", 1, 1, 90, 335, 296, 262, 525, 183, 600, 157),
    Service12(2, "Service", 1, 2, 90, 335, 296, 235, 410, 181, 451, 161),
    Service13(3, "Service", 1, 3, 90, 335, 296, 284, 412, 256, 448, 246),
    Service14(4, "Service", 1, 4, 90, 335, 296, 332, 412, 332, 454, 333),
    Service15(5, "Service", 1, 5, 90, 335, 296, 332, 412, 332, 592, 331),
    Service16(6, "Service", 1, 6, 90, 335, 296, 298, 412, 278, 594, 246),
    Service51(7, "Service", 5, 1, 90, 244, 296, 208, 526, 169, 601, 158),
    Service52(8, "Service", 5, 2, 90, 244, 296, 194, 412, 165, 457, 156),
    Service53(9, "Service", 5, 3, 90, 244, 296, 243, 412, 242, 454, 242),
    Service54(10, "Service", 5, 4, 90, 244, 296, 293, 412, 319, 454, 329),
    Service55(11, "Service", 5, 5, 90, 244, 296, 278, 526, 317, 595, 329),
    Service56(12, "Service", 5, 6, 90, 244, 296, 243, 526, 245, 600, 245),
    Service61(13, "Service", 6, 1, 90, 155, 296, 155, 411, 154, 596, 154),
    Service62(14, "Service", 6, 2, 90, 155, 296, 155, 411, 154, 455, 155),
    Service63(15, "Service", 6, 3, 90, 155, 296, 205, 411, 233, 452, 244),
    Service64(16, "Service", 6, 4, 90, 155, 296, 256, 411, 310, 453, 332),
    Service65(17, "Service", 6, 5, 90, 155, 296, 227, 411, 266, 596, 331),
    Service66(18, "Service", 6, 6, 90, 155, 296, 190, 411, 212, 596, 245),
    Attack11(19, "Attack", 1, 1, 234, 337, 295, 308, 411, 252, 604, 159),
    Attack12(20, "Attack", 1, 2, 234, 337, 296, 287, 411, 196, 458, 158),
    Attack13(21, "Attack", 1, 3, 234, 337, 296, 313, 411, 267, 458, 247),
    Attack14(22, "Attack", 1, 4, 234, 337, 296, 338, 411, 338, 458, 335),
    Attack15(23, "Attack", 1, 5, 234, 337, 296, 338, 411, 338, 604, 338),
    Attack16(24, "Attack", 1, 6, 234, 337, 296, 324, 411, 297, 604, 249),
    Attack21(25, "Attack", 2, 1, 369, 336, 411, 307, 525, 220, 604, 159),
    Attack22(26, "Attack", 2, 2, 369, 336, 389, 299, 436, 207, 458, 158),
    Attack23(27, "Attack", 2, 3, 369, 336, 389, 317, 438, 268, 458, 247),
    Attack24(28, "Attack", 2, 4, 369, 336, 396, 337, 434, 337, 458, 335),
    Attack25(29, "Attack", 2, 5, 369, 336, 411, 338, 527, 338, 604, 338),
    Attack26(30, "Attack", 2, 6, 369, 336, 411, 324, 526, 280, 604, 249),
    Attack31(31, "Attack", 3, 1, 369, 248, 411, 234, 526, 191, 604, 159),
    Attack32(32, "Attack", 3, 2, 369, 248, 411, 205, 447, 168, 458, 158),
    Attack33(33, "Attack", 3, 3, 369, 248, 411, 246, 441, 247, 458, 247),
    Attack34(34, "Attack", 3, 4, 369, 248, 411, 288, 443, 322, 458, 335),
    Attack35(35, "Attack", 3, 5, 369, 248, 411, 263, 525, 306, 604, 338),
    Attack36(36, "Attack", 3, 6, 369, 248, 411, 247, 526, 247, 604, 249),
    Attack41(37, "Attack", 4, 1, 369, 158, 411, 156, 526, 160, 604, 159),
    Attack42(38, "Attack", 4, 2, 369, 158, 411, 156, 439, 158, 458, 158),
    Attack43(39, "Attack", 4, 3, 369, 158, 411, 199, 442, 231, 458, 247),
    Attack44(40, "Attack", 4, 4, 369, 158, 411, 247, 442, 306, 458, 335),
    Attack45(41, "Attack", 4, 5, 369, 158, 411, 188, 526, 278, 604, 338),
    Attack46(42, "Attack", 4, 6, 369, 158, 411, 174, 526, 218, 604, 249),
    Attack51(43, "Attack", 5, 1, 295, 157, 296, 160, 412, 160, 604, 159),
    Attack52(44, "Attack", 5, 2, 295, 157, 296, 160, 412, 160, 458, 158),
    Attack53(45, "Attack", 5, 3, 295, 157, 296, 184, 411, 228, 458, 247),
    Attack54(46, "Attack", 5, 4, 295, 157, 296, 208, 411, 296, 458, 335),
    Attack55(47, "Attack", 5, 5, 295, 157, 296, 191, 411, 247, 604, 338),
    Attack56(48, "Attack", 5, 6, 295, 157, 296, 174, 411, 203, 604, 249),
    Attack61(49, "Attack", 6, 1, 238, 250, 296, 235, 411, 178, 604, 159),
    Attack62(50, "Attack", 6, 2, 238, 250, 296, 226, 411, 178, 458, 158),
    Attack63(51, "Attack", 6, 3, 238, 250, 296, 250, 411, 250, 458, 247),
    Attack64(52, "Attack", 6, 4, 238, 250, 296, 272, 411, 317, 458, 335),
    Attack65(53, "Attack", 6, 5, 238, 250, 296, 264, 411, 290, 604, 338),
    Attack66(54, "Attack", 6, 6, 238, 250, 296, 250, 411, 250, 604, 249);

    int id;
    String skill;
    int from;
    int to;
    int x1;
    int y1;
    int x2;
    int y2;
    int x3;
    int y3;
    int x4;
    int y4;

    private VollyCourtCoordinate(int id, String skill, int from, int to, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.id = id;
        this.skill = skill;
        this.from = from;
        this.to = to;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    public int getX4() {
        return x4;
    }

    public void setX4(int x4) {
        this.x4 = x4;
    }

    public int getY4() {
        return y4;
    }

    public void setY4(int y4) {
        this.y4 = y4;
    }

}
