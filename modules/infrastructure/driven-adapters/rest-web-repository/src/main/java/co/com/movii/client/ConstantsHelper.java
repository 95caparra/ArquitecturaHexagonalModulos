package co.com.movii.client;

public class ConstantsHelper {

    private ConstantsHelper(){

    }

    static final String CORRELATIVE_ID = "correlation-id";
    static final String COMPONENT_CORRELATIVE = "component";
    public static final String STRING_LINE = "-";
    public static final String EMPTY_STRING = "";
    public static final String LOG_THIRD_REQUEST = "[{}] [{}] SOLICITUD: URL: [{}], SOLICITUD: [{}]"; // Third request
    public static final String LBL_RESPONSE = "[{}] [{}] RESPUESTA: [\"{}\"]"; // Third or service response
    public static final String LBL_ERROR_THIRD = "[{}] [{}] RESPUESTA DE ERROR: [\"{}\"]"; // Error response
    public static final String REGEX_REPLACE_JSON_VALUE = "\".*?:.*?\".*?\"";
    public static final String ASTERISK = "*";
    public static final String TWO_DOTS = ":";
    public static final String QUOTES = "\"";
    public static final String PATH_PARAMETERS_SEPARATOR = "\\/";
    public static final String EQUALS_SIGN = "=";
    public static final String URL_PARAMETERS_SEPARATOR = "\\?";

}
