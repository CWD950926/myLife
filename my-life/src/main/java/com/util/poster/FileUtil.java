package com.util.poster;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author chenanlian
 */
public class FileUtil {

    private final static Logger log = LoggerFactory.getLogger(FileUtil.class);


    /**
     * 项目的上一层目录
     */
    public final static String userDir = System.getProperty("user.dir") + File.separator;


    public static String rePath2AbPath(String path) {

        return userDir + path;
    }


}
