package com.github.onsn.data;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by OnSN on 2017/1/14.
 *
 * @author OnSN
 * @version 1.0
 */
public class JsonConverterTest {

    @Test
    public void fromAndTo() {
        Diary diary = new Diary(new DiaryPage("0", "1", "2"), new DiaryPage("1", "2", "3"));
        String s = JsonConverter.toJson(diary);
        System.out.println(s);
        Diary diary1 = JsonConverter.fromJson(s);
        DiaryPage page = diary1.getPage(1);
        assertEquals(page.getContent(), "3");
    }

}