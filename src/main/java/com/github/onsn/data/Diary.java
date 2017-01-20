package com.github.onsn.data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by OnSN on 2017/1/12.
 *
 * @author OnSN
 * @version 1.0
 */
public class Diary {
    public static final Diary DEBUG_DIARY = new Diary(new DiaryPage("2017-1-1 23:59:59", "Test 1", "Test 1's Content."),
            new DiaryPage("2017-1-2 23:59:59", "Test 2", "Test 2's Content."),
            new DiaryPage("2017-1-3 23:59:59", "Test 3", "Test 3's Content."));
    public static final DiaryPage DEBUG_DIARY_PAGE = new DiaryPage("2017-1-1 23:59:59", "Test 1", "Test 1's Content.");

    /**
     * The diary pagesList field.
     */
    private final ArrayList<DiaryPage> pagesList = new ArrayList<>();

    /**
     * Diary's create time.
     */
    private final long createTime;

    /**
     * Diary's modification time.<br/>
     * When you use the setters, time will update.
     */
    private long modificationTime;

    /**
     * A constructor for creating instance for load from a string.
     */
    public Diary(DiaryPage... diaryPages) {
        pagesList.addAll(Arrays.asList(diaryPages)); //Add the pages to pagesList.
        createTime = System.currentTimeMillis();
        modificationTime = createTime;
        updateAttribute();
    }

    /**
     * A default constructor for no params.
     */
    public Diary() {
        createTime = System.currentTimeMillis();
        modificationTime = createTime;
    }

    /**
     * Getter for createTime field.
     */
    public long getCreateTime() {
        return createTime;
    }

    /**
     * Getter for modificationTime field.
     */
    public long getModificationTime() {
        return modificationTime;
    }

    /* --------------------- */
    /* ------ Setters ------ */
    /* --------------------- */
    /**
     * Add a page into the pagesList's last.
     */
    public void addPage(DiaryPage element) {
        updateAttribute();
        pagesList.add(element);
    }

    /**
     * Add many pages into the pagesList's last.
     */
    public void addPages(DiaryPage... elements) {
        updateAttribute();
        pagesList.addAll(Arrays.asList(elements));
    }

    /**
     * Add a page into the pagesList with a special position.
     */
    public void addPageTo(int index, DiaryPage element) {
        updateAttribute();
        pagesList.add(index, element);
    }

    /**
     * Add many pages into the pagesList with a special position.
     */
    public void addPagesTo(int index, DiaryPage... elements) {
        updateAttribute();
        pagesList.addAll(index, Arrays.asList(elements));
    }

    /**
     * Remove a page with a special position.
     */
    public void remove(int index) {
        updateAttribute();
        pagesList.remove(index);
    }

    private void updateAttribute() {
        modificationTime = System.currentTimeMillis();
        pagesList.sort(((o1, o2) -> o2.getTime().compareTo(o1.getTime())));
    }

    /* --------------------- */
    /* ------ Getters ------ */
    /* --------------------- */
    /**
     * Get a page with a special position.
     */
    public DiaryPage getPage(int index) {
        return pagesList.get(index);
    }

    /**
     * Get all pages.
     */
    public DiaryPage[] getAllPages() {
        return pagesList.toArray(new DiaryPage[pagesList.size()]);
    }

    /**
     * Get all pages's title.
     */
    public String[] getAllPageTitles() {
        DiaryPage[] allPages = getAllPages();
        String[] st = new String[allPages.length];
        for (int i = 0; i < allPages.length; i++) {
            st[i] = allPages[i].getTime();
        }
        return st;
    }

    /**
     * @see ArrayList#size()
     */
    public int size() {
        return pagesList.size();
    }
}
