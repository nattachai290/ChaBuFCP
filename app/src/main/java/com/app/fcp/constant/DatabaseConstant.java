package com.app.fcp.constant;

/**
 * Created by arm on 22/3/2559.
 */
public class DatabaseConstant {
    static final String AVD = "http://10.0.2.2/";
    static final String GENYMOTION = "http://10.0.3.2/";
    static final String HTTP_URL = GENYMOTION+"android/";

    /*** INSERT ***/
    public static final String INSERT_HISTRNSHDR = HTTP_URL+"InsertHistrnshdr";
    public static final String INSERT_HISTRNSDTL = HTTP_URL+"InsertHistrnsdtl";
    public static final String INSERT_ITMGNL = HTTP_URL+"InsertItmgnl";
    public static final String INSERT_TYPE = HTTP_URL+"InsertType";
    public static final String INSERT_ADMIN = HTTP_URL+"InsertAdmin";

    /*** SELECT ***/
    public static final String SELECT_HISTRNSHDR = HTTP_URL+"SelectHistrnshdr";
    public static final String SELECT_HISTRNSDTL = HTTP_URL+"SelectHistrnsdtl";
    public static final String SELECT_ITMGNL = HTTP_URL+"SelectItmgnl";
    public static final String SELECT_TYPE = HTTP_URL+"SelectType";
    public static final String SELECT_ADMIN = HTTP_URL+"SelectAdmin";

    /*** UPDATE ***/
    public static final String UPDATE_HISTRNSHDR = HTTP_URL+"UpdateHistrnshdr";
    public static final String UPDATE_HISTRNSDTL = HTTP_URL+"UpdateHistrnsdtl";
    public static final String UPDATE_ITMGNL = HTTP_URL+"UpdateItmgnl";
    public static final String UPDATE_TYPE = HTTP_URL+"UpdateType";
    public static final String UPDATE_ADMIN = HTTP_URL+"UpdateAdmin";

    /*** DELETE ***/
    public static final String DELETE_HISTRNSHDR = HTTP_URL+"DeleteHistrnshdr";
    public static final String DELETE_HISTRNSDTL = HTTP_URL+"DeleteHistrnsdtl";
    public static final String DELETE_ITMGNL = HTTP_URL+"DeleteItmgnl";
    public static final String DELETE_TYPE = HTTP_URL+"DeleteType";
    public static final String DELETE_ADMIN = HTTP_URL+"DeleteAdmin";
}
