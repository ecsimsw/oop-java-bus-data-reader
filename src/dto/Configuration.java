package dto;

public class Configuration {
    private Configuration(){}

    private static final String DATE_FORMAT =  "yyyy-dd-mm";
    private static final String DATE_TIME_FORMAT =  "yyyy-MM-dd HH:mm:ss";
    private static final String CONFIGURE_FILE_PATH = "./\\요금정보.txt";
    private static final String USER_DATA_PATH = "./\\버스직원데이터.xls";
    private static final String[] BUS_NAMES = new String[]{"평택", "교대", "사당", "천안"};
    private static final int NUMBER_OF_BUS = BUS_NAMES.length;
    private static final String SAVE_PATH = "./\\검색자료";
    private static final String PRICE_SEPARATOR = ",";

    public static String getDateFormat() {
        return DATE_FORMAT;
    }

    public static String getDateTimeFormat() {
        return DATE_TIME_FORMAT;
    }

    public static String getConfigureFilePath(){
        return CONFIGURE_FILE_PATH;
    }

    public static String getUserDataPath(){
        return USER_DATA_PATH;
    }

    public static String[] getBusNames() {
        return BUS_NAMES;
    }

    public static int getNumberOfBus() {
        return NUMBER_OF_BUS;
    }

    public static String getSavePath() {
        return SAVE_PATH;
    }

    public static String getPriceSeparator() {
        return PRICE_SEPARATOR;
    }
}
