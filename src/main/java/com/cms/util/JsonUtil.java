package com.cms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public final class JsonUtil {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    private static final Gson COMPACT_GSON = new GsonBuilder()
            .serializeNulls()
            .create();

    private JsonUtil() {}

    public static String toJson(Object object) {
        try {
            return COMPACT_GSON.toJson(object);
        } catch (Exception e) {
            return "{}";
        }
    }

    public static String toPrettyJson(Object object) {
        try {
            return GSON.toJson(object);
        } catch (Exception e) {
            return "{}";
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return COMPACT_GSON.fromJson(json, clazz);
    }

    public static boolean isValidJson(String json) {
        try {
            JsonElement element = JsonParser.parseString(json);
            return element.isJsonObject() || element.isJsonArray();
        } catch (Exception e) {
            return false;
        }
    }
}
