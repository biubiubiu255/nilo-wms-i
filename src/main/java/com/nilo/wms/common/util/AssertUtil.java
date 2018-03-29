package com.nilo.wms.common.util;

import com.nilo.wms.common.exception.ErrorCode;
import com.nilo.wms.common.exception.WMSException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 参数检查断言工具类，可以减少一些<code>if</code>代码逻辑<br>
 * 当断言不成立时，抛出指定错误代码的异常 LdPayBaseException
 *
 * @author songze
 * @version $Id: AssertUtil.java, v 0.1 2015年7月28日 下午2:06:49 songze Exp $
 */
public final class AssertUtil {

    /**
     * 禁用构造函数
     */
    private AssertUtil() {
        // 禁用构造函数
    }

    /**
     * 期待字符串str1，str2相等
     *
     * @param str1
     * @param str2
     * @param resutlCode
     */
    public static void isNotEquals(String str1, String str2,
                                   ErrorCode resutlCode) throws WMSException {
        if (StringUtil.equals(str1, str2)) {
            throw new WMSException(resutlCode);
        }
    }

    /**
     * 期待字符串str1，str2不相等
     *
     * @param str1
     * @param str2
     * @param resutlCode
     */
    public static void isEquals(String str1, String str2,
                                ErrorCode resutlCode) throws WMSException {
        if (!StringUtil.equals(str1, str2)) {
            throw new WMSException(resutlCode);
        }
    }

    /**
     * 是否为正确的长度，不是指定长度抛异常
     *
     * @param
     */
    public static void isRightSize(String obj, int size, ErrorCode errCode) throws WMSException {
        if (!StringUtil.isEmpty(obj) && obj.length() != size) {
            throw new WMSException(errCode);
        }
    }

    /**
     * 是否大于指定长度,不大于抛异常
     *
     * @param str
     * @param size
     * @param errCode
     * @throws WMSException
     */
    public static void isGreaterSize(String str, int size, ErrorCode errCode) throws WMSException {
        if (!StringUtil.isEmpty(str) && str.length() < size) {
            throw new WMSException(errCode);
        }
    }

    /**
     * 是否小于指定长度，不小于抛异常
     *
     * @param str
     * @param size
     * @param errCode
     * @throws WMSException
     */
    public static void isLessSize(String str, int size, ErrorCode errCode) throws WMSException {
        if (!StringUtil.isEmpty(str) && str.length() > size) {
            throw new WMSException(errCode);
        }
    }


    /**
     * 都为空抛出异常
     *
     * @param errCode
     * @param objs
     * @throws WMSException
     */
    public static void isBothNull(ErrorCode errCode, Object... objs) throws WMSException {
        int num = 0;
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] == null || objs[i].equals("")) {
                num++;
            }
        }
        if (num == objs.length) {
            throw new WMSException(errCode);

        }
    }


    /**
     * 期待对象为非空，如果检查的对象为<code>null</code>，抛出异常<code>LdPayBaseException</code>
     *
     * @param object
     * @param resutlCode
     * @throws WMSException
     */
    public static void isNotNull(Object object, ErrorCode resutlCode) throws WMSException {
        if (object == null) {
            throw new WMSException(resutlCode);
        }
    }

    /**
     * 期待对象是数字类型
     *
     * @param text
     * @param resutlCode
     * @throws WMSException
     */
    public static void isNotNumeric(String text, ErrorCode resutlCode) throws WMSException {
        if (!StringUtil.isNumeric(text)) {
            throw new WMSException(resutlCode);
        }
    }


    /**
     * 期待字符串为非空，如果检查字符串是空白：<code>null</code>、空字符串""或只有空白字符，抛出异常<code>LdPayBaseException</code>
     *
     * @param text       待检查的字符串
     * @param resutlCode 异常代码
     * @throws WMSException
     */
    public static void isNotBlank(String text, ErrorCode resutlCode) throws WMSException {
        if (StringUtil.isBlank(text)) {
            throw new WMSException(resutlCode);
        }
    }

    /**
     * 期待集合对象为非空，如果检查集合对象是否为null或者空数据，抛出异常<code>LdPayBaseException</code>
     *
     * @param collection 集合对象
     * @param resutlCode 异常代码
     * @throws WMSException
     */
    public static void notEmpty(Collection<?> collection,
                                ErrorCode resutlCode) throws WMSException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new WMSException(resutlCode);
        }
    }

    /**
     * 期待的正确值为true，如果实际为false，抛出异常<code>LdPayBaseException</code>
     *
     * @param expression
     * @param resutlCode 异常代码
     * @throws WMSException
     */
    public static void isTrue(boolean expression, ErrorCode resutlCode) throws WMSException {
        if (!expression) {
            throw new WMSException(resutlCode);
        }
    }

    /**
     * 期待的正确值为false，如果实际为true，抛出异常<code>LdPayBaseException</code>
     *
     * @param expression
     * @param resutlCode 异常代码
     * @throws WMSException
     */
    public static void isFalse(boolean expression, ErrorCode resutlCode) throws WMSException {
        if (expression) {
            throw new WMSException(resutlCode);
        }
    }
}
