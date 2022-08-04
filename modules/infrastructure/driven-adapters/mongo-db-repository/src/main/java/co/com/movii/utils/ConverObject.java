package co.com.movii.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.StringWriter;

public class ConverObject {

    private ConverObject(){

    }
    public static String objectToString(Object object) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringEmp = new StringWriter();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.writeValue(stringEmp, object);
        return stringEmp.toString();
    }
}
