package com.github.onsn.data;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by OnSN on 2017/1/11.
 *
 * @author OnSN
 * @version 1.0
 */
public class DiaryPageTest {
    @Test
    public void getRealTime() throws Exception {
        assertEquals(14, new DiaryPage("", "", "", 14).getRealModificationTime());
    }

    @Test
    public void getTime() throws Exception {
        assertEquals("2016-8-17 04:30:21", new DiaryPage("2016-8-17 04:30:21", "", "").getTime());
    }

    @Test
    public void getTitle() throws Exception {
        assertEquals("Today is my favorite day.", new DiaryPage("", "Today is my favorite day.", "").getTitle());
    }

    @Test
    public void getContent() throws Exception {
        assertEquals("ddd", new DiaryPage("", "", "ddd").getContent());
    }

}