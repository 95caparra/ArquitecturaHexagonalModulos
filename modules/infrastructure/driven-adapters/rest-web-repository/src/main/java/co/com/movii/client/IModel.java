package co.com.movii.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public abstract class IModel implements Serializable {

    /**
     * This method return string with data of object
     *
     * @return to String of object in json format
     */
    @Override
    public String toString() {
        return toJson();
    }

    /**
     * This method return string to show in logs with sensible fields protected
     *
     * @param protectFields fields to protect
     * @return protected to String of object in json format
     */
    public String toJson(String... protectFields) {
        try {
            return UtilsHelperClient.protectFields(toJsonString(), protectFields);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return ConstantsHelper.EMPTY_STRING;
        }
    }

    /**
     * This method return string to show in logs with sensible fields protected
     *
     * @param protectFields fields to protect
     * @return protected to String of object in xml format
     */
    public String toXml(String... protectFields) {
        try {
            return UtilsHelperClient.printIgnore(toXmlString(), protectFields);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return ConstantsHelper.EMPTY_STRING;
        }
    }

    public String toJsonString() throws JsonProcessingException {
        return UtilsHelperClient.getJSON_MAPPER().writeValueAsString(this);
    }

    public String toXmlString() throws JsonProcessingException {
        return UtilsHelperClient.getXML_MAPPER().writeValueAsString(this);
    }

    /**
     * This method return string to show in logs with sensible fields protected
     *
     * @return protected to String of object in xml/json format
     */
    public String protectedToString() {
        return toJson("password", "pin", "mpin", "otp", "PASSWORD", "PIN", "MPIN", "OTP");
    }

    public String protectedToJsonString() {
        return toJson("password", "pin", "mpin", "otp", "PASSWORD", "PIN", "MPIN", "OTP");
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DefaultModel extends IModel {

    }
}