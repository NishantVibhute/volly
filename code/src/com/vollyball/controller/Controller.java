/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.controller;

import com.vollyball.frames.FrmRegister;
import com.vollyball.util.CommonUtil;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author nishant.vibhute
 */
public class Controller {

    public static JFrame frmMain;

    public static JPanel panActivate;
    public static JPanel panUserDetails;
    public static JPanel panDetails;
    public static JPanel panLoading;

    public static void main(String args[]) {

        new CommonUtil();

        File file = new File(CommonUtil.getResourceProperty("db.name"));
        if (!file.exists()) {
            frmMain = new FrmRegister();
        }
    }

}
