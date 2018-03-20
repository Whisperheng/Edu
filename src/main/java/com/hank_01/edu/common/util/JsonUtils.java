package com.hank_01.edu.common.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final Gson GSON = new Gson();

    public static Gson getGSONInstance() {
        return GSON;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> transferSpecialChar(Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            return map;
        }
        ObjectMapper objectMappter = new ObjectMapper();
        Map<String, Object> param = new HashMap<String, Object>();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof String) {

                if (value.toString().matches("\\{.*\\}")) {
                    try {
                        Map<String, Object> subMap = objectMappter.readValue(value.toString(), Map.class);
                        if (subMap != null) {
                            param.put(key, subMap);
                        }
                    } catch (Exception e) {
                        logger.error("convert json fail", e);
                        param.put(key, "convert json fail");
                    }
                } else {
                    param.put(key, value.toString().replace("\"", "'"));
                }
            } else {
                param.put(key, map.get(key));
            }
        }
        return param;
    }

    public static String Json2String(JSONObject json) {
        return json.toString();
    }

    public static JSONObject String2Json(String string) throws JSONException {
        return new JSONObject(string);
    }

    public static String Object2JsonString(Object obj) throws JSONException {
        return GSON.toJson(obj);
    }

    public static Object string2Object(String jsonStr, Class clazz) {
        return GSON.fromJson(jsonStr, clazz);
    }

    public static String Map2JsonString(Map obj) throws JSONException {
        return new JSONObject(obj).toString();
    }

    public static Map JsonString2Map(String obj) throws JSONException, IOException {
        return new ObjectMapper().readValue(String.valueOf(new JSONObject(obj)), HashMap.class);
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return (new ObjectMapper()).getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
