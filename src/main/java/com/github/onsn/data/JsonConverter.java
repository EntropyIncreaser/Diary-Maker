package com.github.onsn.data;

import com.google.gson.Gson;

/**
 * Created by OnSN on 2017/1/14.
 *
 * @author OnSN
 * @version 1.0
 */
public class JsonConverter {
    private static Gson converter = new Gson();

    /**
     * Can't create a instance of Json Converter.
     */
    private JsonConverter() {
    }

    /**
     * Conversion a json Diary to a String.
     * @param b The Diary used for conversion.
     * @return The result json String.
     */
    public static String toJson(Diary b) {
        if (b == null) return "";
        String s = converter.toJson(b, Diary.class);
        if (s == null) return "";
        return s;
    }

    /**
     * Conversion a json String to a Diary.
     * @param b The json String used for conversion.
     * @return The result Diary.
     */
    public static Diary fromJson(String b) {
        Diary d = converter.fromJson(b, Diary.class);
        if (d == null) throw new NullPointerException();
        return d;
    }
}
