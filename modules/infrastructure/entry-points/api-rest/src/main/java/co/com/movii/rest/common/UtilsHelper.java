package co.com.movii.rest.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import com.fasterxml.jackson.databind.DeserializationFeature;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.UUID;
import org.apache.commons.lang3.ArrayUtils;
import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
public class UtilsHelper {

    private UtilsHelper(){

    }

    @Getter
    private static final ObjectMapper JSON_MAPPER;

    static {
        JSON_MAPPER = new ObjectMapper();
        JSON_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, false);
        JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String protectFields(String jsonTextI, String... protectFields) {
        var jsonText = jsonTextI;
        for (String protectField : protectFields) {
            var regex = ConstantsHelper.QUOTES + protectField + ConstantsHelper.REGEX_REPLACE_JSON_VALUE;
            var m = Pattern.compile(regex).matcher(jsonText);
            while (m.find()) {
                var match = m.group();
                var replace = m.group().replace(protectField, ConstantsHelper.EMPTY_STRING)
                        .replace(ConstantsHelper.QUOTES, ConstantsHelper.EMPTY_STRING)
                        .replace(ConstantsHelper.TWO_DOTS, ConstantsHelper.EMPTY_STRING);
                var placeHolder = StringUtils.repeat(ConstantsHelper.ASTERISK, replace.length());
                var ret = match.replace(replace, placeHolder);
                jsonText = jsonText.replace(match, ret);
            }
        }
        return jsonText;
    }

    public static String printIgnore(@NotNull String xmlString, String... tags) {
        if (StringUtils.isEmpty(xmlString)) {
            return xmlString;
        }
        xmlString = safelyCommand(xmlString);
        if (tags != null && !ArrayUtils.isEmpty(tags)) {
            for (String s : tags) {
                var tag = "<".concat(s).concat(">([^<]*)</").concat(s).concat(">");
                var pattern = Pattern.compile(tag);
                var matcher = pattern.matcher(xmlString);
                var listMatches = new ArrayList<String>();

                while (matcher.find()) {
                    listMatches.add(matcher.group(0));
                }

                String se;
                String replaceValue;
                for (Iterator<String> var11 = listMatches.iterator(); var11.hasNext(); xmlString = xmlString.replaceAll(se, replaceValue)) {
                    se = var11.next();
                    var longitud = se.replaceAll("<" + s + ">", "")
                            .replaceAll("</" + s + ">", "").length();
                    replaceValue = "<".concat(s)
                            .concat(StringUtils.rightPad(">", longitud + 1, "*"))
                            .concat("</").concat(s).concat(">");
                }
            }

        }
        return xmlString;
    }

    public static String safelyCommand(@NotNull String xmlString) {
        if (!StringUtils.isEmpty(xmlString)) {
            xmlString = xmlString.replaceAll("(\r\n|\n|\r|\t)", "");
            xmlString = xmlString.replace("&", "&amp;");
        }
        return xmlString;
    }

    public static void assignCorrelative(String componentName, String correlation) {
        var cId = correlation;

        if (correlation == null || correlation.isEmpty()) {
            cId = getCorrelative();
        }

        MDC.putCloseable(ConstantsHelper.CORRELATIVE_ID, cId);
        MDC.putCloseable(ConstantsHelper.COMPONENT_CORRELATIVE, componentName);
    }

    public static void assignCorrelative(String componentName) {
        assignCorrelative(componentName, null);
    }

    public static String getCorrelative() {
        return UUID.randomUUID().toString().replace(ConstantsHelper.STRING_LINE, ConstantsHelper.EMPTY_STRING);
    }
}
