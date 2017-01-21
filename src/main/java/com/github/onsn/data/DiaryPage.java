package com.github.onsn.data;

import com.github.onsn.DiaryMakerController;

import java.time.LocalDateTime;

/**
 * Created by OnSN on 2017/1/11.
 *
 * @author OnSN
 * @version 1.0
 */
@SuppressWarnings("SameParameterValue")
public class DiaryPage {
    /* --- Fields --- */
    /**
     * Display time
     */
    private String time;

    /**
     * Display title
     */
    private String title;

    /**
     * Display content
     */
    private String content;

    /**
     * The real create time.
     */
    private long realCreateTime;

    /**
     * The real modification time
     */
    private long realModificationTime;

    public DiaryPage() {
        this.time = generateTime();
        this.title = "";
        this.content = "";
        this.realCreateTime = System.currentTimeMillis();
        this.realModificationTime = this.realCreateTime;
    }

    /**
     * A default constructor for {@code DiaryPage}.
     *
     * @param time    The time of the time text field entered.
     * @param title   The title of the title text field entered.
     * @param content The content of the content text area entered.
     */
    public DiaryPage(String time, String title, String content) {
        this.time = time;
        this.title = title;
        this.content = content;
        this.realCreateTime = System.currentTimeMillis();
        this.realModificationTime = this.realCreateTime;
    }


    /**
     * A constructor for the custom real time.<br/>
     * Params: {@link DiaryPage#DiaryPage(String, String, String)}
     */
    public DiaryPage(String time, String title, String content, long realCreateTime) {
        this.time = time;
        this.title = title;
        this.content = content;
        this.realCreateTime = realCreateTime;
        this.realModificationTime = realCreateTime;
    }

    private String generateTime() {
        return DiaryMakerController.timeFormatter.format(LocalDateTime.now());
    }

    /**
     * Getter for the time field.
     */
    public String getTime() {
        return time;
    }

    /**
     * Setter for the time field.
     */
    public void setTime(String time) {
        this.time = time;
        this.realModificationTime = System.currentTimeMillis();
    }

    /**
     * Getter for the title field.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title field.
     */
    public void setTitle(String title) {
        this.title = title;
        this.realModificationTime = System.currentTimeMillis();
    }

    /**
     * Getter for the content field.
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter for the content field.
     */
    public void setContent(String content) {
        this.content = content;
        this.realModificationTime = System.currentTimeMillis();
    }

    /**
     * Getter for the realCreateTime field.
     */
    public long getRealCreateTime() {
        return realCreateTime;
    }

    /**
     * Setter for the realCreateTime field.
     */
    public void setRealCreateTime(long realCreateTime) {
        this.realCreateTime = realCreateTime;
        this.realModificationTime = System.currentTimeMillis();
    }

    /**
     * Getter for the realModificationTime field.
     */
    public long getRealModificationTime() {
        return realModificationTime;
    }

    /**
     * Setter for the realModificationTime field.
     */
    public void setRealModificationTime(long realModificationTime) {
        this.realModificationTime = realModificationTime;
        this.realModificationTime = System.currentTimeMillis();
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = getTime() != null ? getTime().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (int) (getRealModificationTime() ^ (getRealModificationTime() >>> 32));
        return result;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "DiaryPage{" +
                "time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", realCreateTime=" + realCreateTime +
                ", realModificationTime=" + realModificationTime +
                '}';
    }
}
