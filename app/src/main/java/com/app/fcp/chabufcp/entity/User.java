package com.app.fcp.chabufcp.entity;

/**
 * Created by arm on 24/4/2559.
 */
public class User {
    static private String USERID;
    static private String USERNAME;
    static private String POSITION;
    static private String FNAME;
    static private String LNAME;
    static private String SEX;

    public static String getUSERID() {
        return USERID;
    }

    public static void setUSERID(String USERID) {
        User.USERID = USERID;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        User.USERNAME = USERNAME;
    }

    public static String getPOSITION() {
        return POSITION;
    }

    public static void setPOSITION(String POSITION) {
        User.POSITION = POSITION;
    }

    public static String getFNAME() {
        return FNAME;
    }

    public static void setFNAME(String FNAME) {
        User.FNAME = FNAME;
    }

    public static String getLNAME() {
        return LNAME;
    }

    public static void setLNAME(String LNAME) {
        User.LNAME = LNAME;
    }

    public static String getSEX() {
        return SEX;
    }

    public static void setSEX(String SEX) {
        User.SEX = SEX;
    }
}
