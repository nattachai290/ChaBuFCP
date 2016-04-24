package com.app.fcp.constant;

/**
 * Created by arm on 22/3/2559.
 */
public class DatabaseConstant {
    static final String WEB_HOST = "";
    static final String AVD = "http://10.0.2.2/";
    static final String GENYMOTION = "http://10.0.3.2/";
    static final String HTTP_URL = GENYMOTION+"android/";

    /*** INSERT ***/
    public static final String INSERT_HISTRNSHDR = HTTP_URL+"InsertHistrnshdr.php";
    public static final String INSERT_HISTRNSDTL = HTTP_URL+"InsertHistrnsdtl.php";
    public static final String INSERT_ITMGNL = HTTP_URL+"InsertItmgnl.php";
    public static final String INSERT_TYPE = HTTP_URL+"InsertType.php";
    public static final String INSERT_ADMIN = HTTP_URL+"InsertAdmin.php";

    /*** SELECT ***/
    public static final String SELECT_HISTRNSHDR = HTTP_URL+"SelectHistrnshdr.php";
    public static final String SELECT_HISTRNSDTL = HTTP_URL+"SelectHistrnsdtl.php";
    public static final String SELECT_ITMGNL = HTTP_URL+"SelectItmgnl.php";
    public static final String SELECT_TYPE = HTTP_URL+"SelectType.php";
    public static final String SELECT_ADMIN = HTTP_URL+"SelectAdmin.php";

    /*** UPDATE ***/
    public static final String UPDATE_HISTRNSHDR = HTTP_URL+"UpdateHistrnshdr.php";
    public static final String UPDATE_HISTRNSDTL = HTTP_URL+"UpdateHistrnsdtl.php";
    public static final String UPDATE_ITMGNL = HTTP_URL+"UpdateItmgnl.php";
    public static final String UPDATE_TYPE = HTTP_URL+"UpdateType.php";
    public static final String UPDATE_ADMIN = HTTP_URL+"UpdateAdmin.php";

    /*** DELETE ***/
    public static final String DELETE_HISTRNSHDR = HTTP_URL+"DeleteHistrnshdr.php";
    public static final String DELETE_HISTRNSDTL = HTTP_URL+"DeleteHistrnsdtl.php";
    public static final String DELETE_ITMGNL = HTTP_URL+"DeleteItmgnl.php";
    public static final String DELETE_TYPE = HTTP_URL+"DeleteType.php";
    public static final String DELETE_ADMIN = HTTP_URL+"DeleteAdmin.php";
}
