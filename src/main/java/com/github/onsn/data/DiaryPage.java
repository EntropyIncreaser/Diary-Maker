package com.github.onsn.data;

/**
 * Created by OnSN on 2017/1/11.
 *
 * @author OnSN
 * @version 1.0
 */
public class DiaryPage {
    /* --- Fields --- */
    /** Display time */
    private final String time;

    /** Display title */
    private final String title;

    /** Display content */
    private final String content;

    /** The real modification time */
    private final long realTime;


    /**
     * A default constructor for {@code DiaryPage}.
     * @param time The time of the time text field entered.
     * @param title The title of the title text field entered.
     * @param content The content of the content text area entered.
     */
    public DiaryPage(String time, String title, String content) {
        this.time = time;
        this.title = title;
        this.content = content;
        this.realTime = System.currentTimeMillis();
    }


    /**
     * A constructor for the custom real time.<br/>
     * Params: {@link DiaryPage#DiaryPage(String, String, String)}
     */
    public DiaryPage(String time, String title, String content, long realTime) {
        this.time = time;
        this.title = title;
        this.content = content;
        this.realTime = realTime;
    }


    /**
     * Getter for the time field.
     */
    public String getTime() {
        return time;
    }

    /**
     * Getter for the title field.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the content field.
     */
    public String getContent() {
        return content;
    }

    /**
     * Getter for the realTime field.
     */
    public long getRealTime() {
        return realTime;
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
        result = 31 * result + (int) (getRealTime() ^ (getRealTime() >>> 32));
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
                ", realTime=" + realTime +
                '}';
    }
}
