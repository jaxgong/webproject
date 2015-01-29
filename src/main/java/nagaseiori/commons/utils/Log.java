package nagaseiori.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提供获得日志记录器的方法
 */
public class Log {

    /**
     * 通过回溯当前运行的线程,找到调用此方法的类,从而获得正确的代表此类的Logger
     * 
     * @return
     */
    public static Logger getLogger() {
        return LoggerFactory.getLogger(java.lang.Thread.currentThread().getStackTrace()[2].getClassName());
    }

    /**
     * 获得代表obj的类的Logger,使用getLogger(this)来获得对应子类的logger
     * 
     * @param obj 指定的要获得其类的对象
     * @return
     */
    public static Logger getLogger(Object obj) {
        if (obj instanceof Class<?>) {
            return LoggerFactory.getLogger((Class<?>) obj);
        }
        return LoggerFactory.getLogger(obj.getClass().getName());
    }

    /**
     * 直接指定logger名称
     * 
     * @param str
     * @return
     */
    public static Logger getLogger(String str) {
        return LoggerFactory.getLogger(str);
    }

    /**
     * 通过指定对象、前缀和后缀来获得Logger
     * 
     * @param obj 对象
     * @param prefix 前缀
     * @param suffix 后缀
     * @return
     */
    public static Logger getLoggerWith(Object obj, String prefix, String suffix) {
        return LoggerFactory.getLogger(prefix + "." + obj.getClass().getName() + "." + suffix);
    }

    /**
     * 通过前缀和后缀来获得Logger
     * 
     * @param prefix 前缀
     * @param suffix 后缀
     * @return
     */
    public static Logger getLoggerWith(String prefix, String suffix) {
        return LoggerFactory.getLogger(prefix + "." + java.lang.Thread.currentThread().getStackTrace()[2].getClassName() + "." + suffix);
    }

    /**
     * 通过对象和前缀来获得Logger
     * 
     * @param obj 对象
     * @param prefix 前缀
     * @return
     */
    public static Logger getLoggerWithPrefix(Object obj, String prefix) {
        return LoggerFactory.getLogger(prefix + "." + obj.getClass().getName());
    }

    /**
     * 通过前缀来获得Logger
     * 
     * @param prefix 前缀
     * @return
     */
    public static Logger getLoggerWithPrefix(String prefix) {
        return LoggerFactory.getLogger(prefix + "." + java.lang.Thread.currentThread().getStackTrace()[2].getClassName());
    }

    /**
     * 通过对象和后缀来获得Logger
     * 
     * @param obj 对象
     * @param suffix 后缀
     * @return
     */
    public static Logger getLoggerWithSuffix(Object obj, String suffix) {
        return LoggerFactory.getLogger(obj.getClass().getName() + "." + suffix);
    }

    /**
     * 通过后缀来获得Logger
     * 
     * @param suffix 后缀
     * @return
     */
    public static Logger getLoggerWithSuffix(String suffix) {
        return LoggerFactory.getLogger(java.lang.Thread.currentThread().getStackTrace()[2].getClassName() + "." + suffix);
    }
}
