package com.yll.sharde.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author：linlin.yang
 * @date：2017/9/12 10:54
 */
public class ReleaseUtil {
    public static Log logger = LogFactory.getLog(ReleaseUtil.class);

    /**
     * 关闭文件流
     * @param object
     * @author：linlin.yang
     * @date：2017/7/27
     */
    public static void close(Closeable object) {
        if (object != null) {
            try {
                object.close();
            } catch (IOException e) {
                logger.error("关闭文件流失败，error：" + e);
            }
        }
    }


    /**
     * 关闭数据库操作资源
     * @param object
     */
    public static void close(AutoCloseable object) {
        if (object != null) {
            try {
                object.close();
            } catch (Exception e) {
                logger.error("关闭文件流失败，error：" + e);
            }
        }
    }
}
