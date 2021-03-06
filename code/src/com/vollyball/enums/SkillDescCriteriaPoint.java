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
    ServiceA2(2, "", "JP", 1),
    ServiceA3(3, "", "SF", 1),
    ServiceA4(4, "", "SS", 1),
    ServiceB1(5, "", "DC", 2),
    ServiceB2(6, "", "PP", 2),
    ServiceB3(7, "", "SP", 2),
    ServiceB4(8, "", "MB", 2),
    ServiceB5(9, "", "BP", 2),
    ServiceB6(10, "", "NE", 2),
    ServiceB7(11, "", "AE", 2),
    ServiceC1(12, "", "C", 3),
    ServiceC2(13, "", "L", 3),
    ServiceC3(14, "", "ST", 3),
    ServiceD1(15, "", "1", 4),
    ServiceD2(16, "", "5", 4),
    ServiceD3(17, "", "6", 4),
    ServiceE1(18, "", "1", 5),
    ServiceE2(19, "", "2", 5),
    ServiceE3(20, "", "3", 5),
    ServiceE4(21, "", "4", 5),
    ServiceE5(22, "", "5", 5),
    ServiceE6(23, "", "6", 5),
    ServiceF1(24, "", "5", 6),
    ServiceF2(25, "", "4", 6),
    ServiceF3(26, "", "3", 6),
    ServiceF4(27, "", "2", 6),
    ServiceG1(28, "", "OH", 7),
    ServiceG2(29, "", "MB", 7),
    ServiceG3(30, "", "L", 7),
    ServiceG4(31, "", "U", 7),
    ServiceH1(32, "", "0", 8),
    ServiceI1(33, "", "AT", 9),
    ServiceI2(34, "", "RC", 9),
    ServiceI3(35, "", "SC", 9),
    ServiceI4(36, "", "TPS", 9),
    ServiceI5(37, "", "OPS", 9),
    ServiceJ1(38, "", "FR", 10),
    ServiceJ2(39, "", "FM", 10),
    ServiceJ3(40, "", "FL", 10),
    ServiceJ4(41, "", "RR", 10),
    ServiceJ5(42, "", "RM", 10),
    ServiceJ6(43, "", "RL", 10),
    AttackA1(44, "", "C", 11),
    AttackA2(45, "", "L", 11),
    AttackA3(46, "", "ST", 11),
    AttackA4(47, "", "IN", 11),
    AttackA5(48, "", "OT", 11),
    AttackA6(49, "", "BT", 11),
    AttackA7(50, "", "OL", 11),
    AttackA8(51, "", "DC", 11),
    AttackB1(52, "", "4C", 12),
    AttackB2(53, "", "3C", 12),
    AttackB3(54, "", "2C", 12),
    AttackC1(55, "", "DC", 13),
    AttackC2(56, "", "PP", 13),
    AttackC3(57, "", "SP", 13),
    AttackC4(58, "", "MB", 13),
    AttackC5(59, "", "BP", 13),
    AttackC6(60, "", "NSE", 13),
    AttackC7(61, "", "NEL", 13),
    AttackD1(62, "", "LOW", 14),
    AttackD2(63, "", "MEDIUM", 14),
    AttackD3(64, "", "HIGH", 14),
    AttackE1(65, "", "1", 15),
    AttackE2(66, "", "2", 15),
    AttackE3(67, "", "3", 15),
    AttackE4(68, "", "4", 15),
    AttackE5(69, "", "5", 15),
    AttackE6(70, "", "6", 15),
    AttackF1(71, "", "1", 16),
    AttackF2(72, "", "2", 16),
    AttackF3(73, "", "3", 16),
    AttackF4(74, "", "4", 16),
    AttackF5(75, "", "5", 16),
    AttackF6(76, "", "6", 16),
    AttackG1(77, "", "K1", 17),
    AttackG2(78, "", "K2", 17),
    AttackG3(79, "", "TPS", 17),
    AttackH1(80, "", "OH", 18),
    AttackH2(81, "", "MB", 18),
    AttackH3(82, "", "U", 18),
    AttackH4(83, "", "S", 18),
    AttackI1(84, "", "SGL", 19),
    AttackI2(85, "", "DBL", 19),
    AttackI3(86, "", "TPL", 19),
    AttackJ1(87, "", "01-02-2003", 20),
    AttackJ2(88, "", "01-03-2002", 20),
    AttackJ3(89, "", "02-01-2002", 20),
    AttackJ4(90, "", "2-0-4", 20),
    AttackJ5(91, "", "03-01-2003", 20),
    AttackJ6(92, "", "3-0-3", 20),
    AttackJ7(93, "", "03-02-2001", 20),
    AttackK1(94, "", "1", 21),
    AttackK2(95, "", "2", 21),
    AttackK3(96, "", "3", 21),
    AttackK4(97, "", "4", 21),
    AttackK5(98, "", "5", 21),
    AttackK6(99, "", "6", 21),
    AttackL1(100, "", "S", 22),
    AttackL2(101, "", "OH", 22),
    AttackL3(102, "", "MB", 22),
    AttackL4(103, "", "L", 22),
    AttackL5(104, "", "U", 22),
    AttackM1(105, "", "00:00", 23),
    AttackN1(106, "", "AT", 24),
    AttackN2(107, "", "RC", 24),
    AttackN3(108, "", "TPS", 24),
    AttackN4(109, "", "OPS", 24),
    AttackO1(110, "", "FR", 25),
    AttackO2(111, "", "FM", 25),
    AttackO3(112, "", "FL", 25),
    AttackO4(113, "", "RR", 25),
    AttackO5(114, "", "RM", 25),
    AttackO6(115, "", "RL", 25),
    BlockA1(116, "", "Commit", 26),
    BlockA2(117, "", "Read and react", 26),
    BlockA3(118, "", "Stack or zone", 26),
    BlockB1(119, "", "Kill", 27),
    BlockB2(120, "", "Soft", 27),
    BlockB3(121, "", "LC", 27),
    BlockB4(122, "", "LO", 27),
    BlockC1(123, "", "C", 28),
    BlockC2(124, "", "L", 28),
    BlockC3(125, "", "IN", 28),
    BlockC4(126, "", "OT", 28),
    BlockC5(127, "", "BT", 28),
    BlockC6(128, "", "OL", 28),
    BlockC7(129, "", "D", 28),
    BlockC8(130, "", "BC", 28),
    BlockD1(131, "", "4C", 29),
    BlockD2(132, "", "3C", 29),
    BlockD3(133, "", "2C", 29),
    BlockE1(134, "", "LOW", 30),
    BlockE2(135, "", "MEDIUM", 30),
    BlockE3(136, "", "HIGH", 30),
    BlockF1(137, "", "1", 31),
    BlockF2(138, "", "2", 31),
    BlockF3(139, "", "3", 31),
    BlockG1(140, "", "4", 31),
    BlockG2(141, "", "5", 31),
    BlockG3(142, "", "6", 31),
    BlockG4(143, "", "2", 32),
    BlockG5(144, "", "3", 32),
    BlockG6(145, "", "4", 32),
    BlockH1(146, "", "1", 33),
    BlockH2(147, "", "2", 33),
    BlockH3(148, "", "3", 33),
    BlockH4(149, "", "4", 33),
    BlockH5(150, "", "5", 33),
    BlockH6(151, "", "6", 33),
    BlockH7(152, "", "LOC", 33),
    BlockH8(153, "", "ROC", 33),
    BlockH9(154, "", "BOC", 33),
    BlockI1(155, "", "K1", 34),
    BlockI2(156, "", "K2", 34),
    BlockI3(157, "", "TP", 34),
    BlockJ1(158, "", "OH", 35),
    BlockJ2(159, "", "MB", 35),
    BlockJ3(160, "", "U", 35),
    BlockJ4(161, "", "S", 35),
    BlockK1(162, "", "SGL", 36),
    BlockK2(163, "", "DBL", 36),
    BlockK3(164, "", "TPL", 36),
    BlockL1(165, "", "01-02-2003", 37),
    BlockL2(166, "", "01-03-2002", 37),
    BlockL3(167, "", "02-01-2002", 37),
    BlockL4(168, "", "2-0-4", 37),
    BlockL5(169, "", "03-01-2003", 37),
    BlockL6(170, "", "3-0-3", 37),
    BlockL7(171, "", "03-02-2001", 37),
    BlockM1(175, "", "H", 38),
    BlockM2(176, "", "OPP", 38),
    BlockN1(177, "", "00:00", 39),
    BlockO1(178, "", "AT", 40),
    BlockO2(179, "", "RC", 40),
    BlockO3(180, "", "SC", 40),
    BlockO4(181, "", "TPS", 40),
    BlockO5(182, "", "OPS", 40),
    BlockP1(183, "", "FR", 41),
    BlockP2(184, "", "FM", 41),
    BlockP3(185, "", "FL", 41),
    BlockP4(186, "", "RR", 41),
    BlockP5(187, "", "RM", 41),
    BlockP6(188, "", "RL", 41),
    SetA1(189, "", "JS", 42),
    SetA2(190, "", "RB", 42),
    SetA3(191, "", "FP", 42),
    SetA4(192, "", "HP", 42),
    SetA5(193, "", "BC", 42),
    SetA6(194, "", "BS", 42),
    SetA7(195, "", "FS", 42),
    SetB1(196, "", "HIGH", 43),
    SetB2(197, "", "MEDIUM", 43),
    SetB3(198, "", "LOW", 43),
    SetC1(199, "", "ON", 44),
    SetC2(200, "", "CN", 44),
    SetC3(201, "", "AN", 44),
    SetC4(202, "", "LT", 44),
    SetD1(203, "", "5", 45),
    SetD2(204, "", "4", 45),
    SetD3(205, "", "3", 45),
    SetD4(206, "", "2", 45),
    SetE1(207, "", "Favourable", 46),
    SetE2(208, "", "Semi Favourable", 46),
    SetE3(209, "", "Non Favourable", 46),
    SetF1(210, "", "1", 47),
    SetF2(211, "", "2", 47),
    SetF3(212, "", "3", 47),
    SetF4(213, "", "4", 47),
    SetF5(214, "", "5", 47),
    SetF6(215, "", "6", 47),
    SetG1(216, "", "1", 48),
    SetG2(217, "", "2", 48),
    SetG3(218, "", "3", 48),
    SetG4(219, "", "4", 48),
    SetG5(220, "", "5", 48),
    SetG6(221, "", "6", 48),
    SetH1(222, "", "4C", 49),
    SetH2(223, "", "3C", 49),
    SetH3(224, "", "2C", 49),
    SetI1(225, "", "C", 50),
    SetI2(226, "", "L", 50),
    SetI3(227, "", "IN", 50),
    SetI4(228, "", "OT", 50),
    SetI5(229, "", "BT", 50),
    SetI6(230, "", "BL", 50),
    SetI7(231, "", "D", 50),
    SetI8(232, "", "BC", 50),
    SetJ1(233, "", "SGL", 51),
    SetJ2(234, "", "DBL", 51),
    SetJ3(235, "", "TPL", 51),
    SetJ4(236, "", "NB", 51),
    SetK1(237, "", "K1", 52),
    SetK2(238, "", "K2", 52),
    SetK3(239, "", "TP", 52),
    SetL1(240, "", "OH", 53),
    SetL2(241, "", "MB", 53),
    SetL3(242, "", "U", 53),
    SetL4(243, "", "S", 53),
    SetM1(244, "", "00:00", 54),
    SetN1(245, "", "FR", 55),
    SetN2(246, "", "FM", 55),
    SetN3(247, "", "FL", 55),
    SetN4(248, "", "RR", 55),
    SetN5(249, "", "RM", 55),
    SetN6(250, "", "RL", 55),
    ReceptionA1(251, "", "JF", 56),
    ReceptionA2(252, "", "JS", 56),
    ReceptionA3(253, "", "JP", 56),
    ReceptionA4(254, "", "SF", 56),
    ReceptionA5(255, "", "SS", 56),
    ReceptionB1(256, "", "2", 57),
    ReceptionB2(257, "", "3", 57),
    ReceptionB3(258, "", "4", 57),
    ReceptionB4(259, "", "5", 57),
    ReceptionC1(260, "", "1", 58),
    ReceptionC2(261, "", "2", 58),
    ReceptionC3(262, "", "3", 58),
    ReceptionC4(263, "", "4", 58),
    ReceptionC5(264, "", "5", 58),
    ReceptionC6(265, "", "6", 58),
    ReceptionD1(266, "", "1", 59),
    ReceptionD2(267, "", "2", 59),
    ReceptionD3(268, "", "3", 59),
    ReceptionD4(269, "", "4", 59),
    ReceptionD5(270, "", "5", 59),
    ReceptionD6(271, "", "6", 59),
    ReceptionE1(272, "", "OH", 60),
    ReceptionE2(273, "", "MB", 60),
    ReceptionE3(274, "", "U", 60),
    ReceptionE4(275, "", "S", 60),
    ReceptionE5(276, "", "L", 60),
    ReceptionF1(277, "", "Favourable", 61),
    ReceptionF2(278, "", "Semi Favourable", 61),
    ReceptionF3(279, "", "Non Favourable", 61),
    ReceptionG1(280, "", "ON", 62),
    ReceptionG2(281, "", "CN", 62),
    ReceptionG3(282, "", "AN", 62),
    ReceptionG4(283, "", "LT", 62),
    ReceptionH1(284, "", "JS", 63),
    ReceptionH2(285, "", "RB", 63),
    ReceptionH3(286, "", "FP", 63),
    ReceptionH4(287, "", "HP", 63),
    ReceptionH5(288, "", "BC", 63),
    ReceptionH6(289, "", "BS", 63),
    ReceptionH7(290, "", "FS", 63),
    ReceptionI1(291, "", "HIGH", 64),
    ReceptionI2(292, "", "MEDIUM", 64),
    ReceptionI3(293, "", "LOW", 64),
    ReceptionJ1(294, "", "00:00", 65),
    ReceptionK1(295, "", "FR", 66),
    ReceptionK2(296, "", "FM", 66),
    ReceptionK3(297, "", "FL", 66),
    ReceptionK4(298, "", "RR", 66),
    ReceptionK5(299, "", "RM", 66),
    ReceptionK6(300, "", "RL", 66),
    DefenceA1(301, "", "C", 67),
    DefenceA2(302, "", "L", 67),
    DefenceA3(303, "", "IN", 67),
    DefenceA4(304, "", "OT", 67),
    DefenceA5(305, "", "BT", 67),
    DefenceA6(306, "", "OL", 67),
    DefenceA7(307, "", "D", 67),
    DefenceA8(308, "", "BC", 67),
    DefenceB1(309, "", "HIGH", 68),
    DefenceB2(310, "", "MEDIUM", 68),
    DefenceB3(311, "", "LOW", 68),
    DefenceC1(312, "", "5C", 69),
    DefenceC2(313, "", "4C", 69),
    DefenceC3(314, "", "3C", 69),
    DefenceC4(315, "", "2C", 69),
    DefenceC5(316, "", "SINGLE", 69),
    DefenceD1(317, "", "4", 70),
    DefenceD2(318, "", "3", 70),
    DefenceD3(319, "", "2", 70),
    DefenceE1(320, "", "SGL", 71),
    DefenceE2(321, "", "DBL", 71),
    DefenceE3(322, "", "TPL", 71),
    DefenceF1(323, "", "LO", 72),
    DefenceF2(324, "", "LC", 72),
    DefenceG1(325, "", "01-02-2003", 73),
    DefenceG2(326, "", "01-03-2002", 73),
    DefenceG3(327, "", "02-01-2002", 73),
    DefenceG4(328, "", "2-0-4", 73),
    DefenceG5(329, "", "03-01-2003", 73),
    DefenceG6(330, "", "3-0-3", 73),
    DefenceG7(331, "", "03-02-2001", 73),
    DefenceH1(332, "", "1", 74),
    DefenceH2(333, "", "2", 74),
    DefenceH3(334, "", "3", 74),
    DefenceH4(335, "", "4", 74),
    DefenceH5(336, "", "5", 74),
    DefenceH6(337, "", "6", 74),
    DefenceI1(338, "", "1", 75),
    DefenceI2(339, "", "2", 75),
    DefenceI3(340, "", "3", 75),
    DefenceI4(341, "", "4", 75),
    DefenceI5(342, "", "5", 75),
    DefenceI6(343, "", "6", 75),
    DefenceJ1(344, "", "OH", 76),
    DefenceJ2(345, "", "MB", 76),
    DefenceJ3(346, "", "U", 76),
    DefenceJ4(347, "", "S", 76),
    DefenceJ5(348, "", "L", 76),
    DefenceK1(349, "", "ON", 77),
    DefenceK2(350, "", "CN", 77),
    DefenceK3(351, "", "AN", 77),
    DefenceK4(352, "", "LT", 77),
    DefenceL1(353, "", "Favourable", 78),
    DefenceL2(354, "", "Semi Favourable", 78),
    DefenceL3(355, "", "Non Favourable", 78),
    DefenceM1(356, "", "K2", 79),
    DefenceM2(357, "", "TP", 79),
    DefenceN1(358, "", "FR", 80),
    DefenceN2(359, "", "FM", 80),
    DefenceN3(360, "", "FL", 80),
    DefenceN4(361, "", "RR", 80),
    DefenceN5(362, "", "RM", 80),
    DefenceN6(363, "", "RL", 80),
    DefenceO1(364, "", "JS", 81),
    DefenceO2(365, "", "RB", 81),
    DefenceO3(366, "", "FP", 81),
    DefenceO4(367, "", "HP", 81),
    DefenceO5(368, "", "BC", 81),
    DefenceO6(369, "", "BS", 81),
    DefenceO7(370, "", "FS", 81),
    DefenceP1(371, "", "HIGH", 82),
    DefenceP2(372, "", "MEDIUM", 82),
    DefenceP3(373, "", "LOW", 82),
    DefenceQ1(374, "", "00:00", 83);

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
