package com.github.onsn.data;

import com.google.gson.Gson;

/**
 * Created by OnSN on 2017/1/14.
 *
 * @author OnSN
 * @version 1.0
 */
public class JsonConverter {
    private static final Gson converter = new Gson();

    /**
     * Can't create a instance of Json Converter.
     */
    private JsonConverter() {
    }

    public static String toJson(Diary b) {
        if (b == null) return "";
        String s = converter.toJson(b, Diary.class);
        if (s == null) return "";
        return s;
    }

    public static Diary fromJson(String b) {
        Diary d = converter.fromJson(b, Diary.class);
        if (d == null) throw new NullPointerException();
        return d;
    }
}
