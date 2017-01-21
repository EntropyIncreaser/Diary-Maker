package com.github.onsn.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by OnSN on 2017/1/13.
 *
 * @author OnSN
 * @version 1.0
 */
public class DiaryTest {
    public Diary getField;
    public long time;
    public DiaryPage[] pages;

    @Before
    public void setUp() throws Exception {
        pages = new DiaryPage[]{new DiaryPage("2016-07-30 15:13:35", "Today is a lucky day.", "Contents..."),
                new DiaryPage("2016-8-1 15:13:36", "Today is a lucky day too!", "Contents..."),
                new DiaryPage("2016-8-2 15:13:37", "Just a null diary", ""),
                new DiaryPage("2016-8-3 15:13:38", "Penta Kill!", "Penta kill.")};
        getField = new Diary(pages);
        time = System.currentTimeMillis();
    }

    @Test
    public void getCreateTime() throws Exception {
        if (time + 1000 > getField.getCreateTime()) return;
        if (time - 1000 < getField.getCreateTime()) return;
        assertEquals(getField.getCreateTime(), time);
    }

    @Test
    public void getModificationTime() throws Exception {
        if (time + 1000 > getField.getCreateTime()) return;
        if (time - 1000 < getField.getCreateTime()) return;
        assertEquals(getField.getCreateTime(), time);
    }

    @Test
    public void addPage() throws Exception {
        DiaryPage error = new DiaryPage("Error...!", "Error...!", "Error...!");
        getField.addPage(error);
        assertEquals(error, getField.getPage(getField.size() - 1));
    }

    @Test
    public void addPages() throws Exception {
        DiaryPage error = new DiaryPage("Error...!2", "Error...!2", "Error...!2");
        getField.addPages(new DiaryPage("Error...!2", "Error...!2", "Error...!2"), error);
        assertEquals(error, getField.getPage(getField.size() - 1));
    }

    @Test
    public void addPageTo() throws Exception {
        DiaryPage error = new DiaryPage("Error...!", "Error...!", "Error...!");
        getField.addPageTo(1, error);
        assertEquals(error, getField.getPage(1));
    }

    @Test
    public void addPagesTo() throws Exception {
        DiaryPage error = new DiaryPage("Error...!2", "Error...!2", "Error...!2");
        getField.addPagesTo(0, new DiaryPage("Error...!2", "Error...!2", "Error...!2"), error);
        assertEquals(error, getField.getPage(1));
    }

    @Test
    public void remove() throws Exception {
        getField.remove(0);
        assertEquals(getField.getPage(0), pages[1]);
    }

    @Test
    public void getPage() throws Exception {
        assertEquals(getField.getPage(0), pages[0]);
    }

    @Test
    public void getAllPages() throws Exception {
        DiaryPage[] pages = new DiaryPage[]{new DiaryPage("2016-07-30 15:13:35", "Today is a lucky day.", "Contents..."),
                new DiaryPage("2016-8-1 15:13:36", "Today is a lucky day too!", "Contents..."),
                new DiaryPage("2016-8-2 15:13:37", "Just a null diary", ""),
                new DiaryPage("2016-8-3 15:13:38", "Penta Kill!", "Penta kill.")};
        getField = new Diary(pages);
        assertArrayEquals(pages, getField.getAllPages());
    }

}