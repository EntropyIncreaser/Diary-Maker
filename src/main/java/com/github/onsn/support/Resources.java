package com.github.onsn.support;

import java.net.URL;

/**
 * Created by OnSN on 2017/1/10.
 *
 * @author OnSN
 * @version 1.0
 */
public class Resources {
    /**
     * To get a url from resources.
     */
    public static URL get(String name) {
        return Resources.class.getResource(name);
    }

    /**
     * To get a stream from resources.
     */
    public static URL getByStream(String name) {
        return Resources.class.getResource(name);
    }
}
