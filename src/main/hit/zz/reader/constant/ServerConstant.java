package zz.reader.constant;

/**
 * Created by zz on 2016-07-05.
 */
public class ServerConstant {


    public static final String SERVER_PROTOCAL_IP = "http://192.168.191.2";

    public static final String SERVER_PORT = ":8080";

    public static final String USER_AGENT = "Mozilla/5.0";

    public static final String LOGIN_URL = SERVER_PROTOCAL_IP+SERVER_PORT+"/logincheck";

    public static final String REGISTER_URL = SERVER_PROTOCAL_IP+SERVER_PORT+"/userRegist";

    public static final String BOOK_LIST = SERVER_PROTOCAL_IP+SERVER_PORT+"/loadBookList";

    public static final String DELETE_BOOK = SERVER_PROTOCAL_IP+SERVER_PORT+"/deleteBook";

    public static final String ADD_BOOK = SERVER_PROTOCAL_IP+SERVER_PORT+"/addBook";

    public static final String RECORD_INDEX = SERVER_PROTOCAL_IP+SERVER_PORT+"/recordIndex";

    public static final String SEARCH_BOOK = SERVER_PROTOCAL_IP+SERVER_PORT+"/search";

    public static final String DOWNLOAD_BOOK = SERVER_PROTOCAL_IP+SERVER_PORT+"/downloadTxt";

    public static final String IMAGE_DOWNLOAD = SERVER_PROTOCAL_IP+SERVER_PORT;

    public static final String UPDATE_USER_PREFERENCE = SERVER_PROTOCAL_IP+SERVER_PORT+"/pushPreference";

    public static final String GET_USER_PREFERENCE = SERVER_PROTOCAL_IP+SERVER_PORT+"/getPreference";

}
