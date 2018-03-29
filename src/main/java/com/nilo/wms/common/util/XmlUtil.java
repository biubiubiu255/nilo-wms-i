/**
 * KILIMALL Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * 富勒工具类
 *
 * @author Ronny.zeng
 * @version $Id: FluxUtil.java, v 0.1 2016年6月20日 下午3:37:28 Exp $
 */
public class XmlUtil {

    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * convert java bean to XML String
     *
     * @param obj
     * @return
     */
    public static String BeanToXML(Object obj) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, DEFAULT_ENCODING);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(obj, stringWriter);
            return stringWriter.toString();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Convert To XML Failed.");
        }
    }

    public static String ListToXML(List<? extends Object> list) {

        try {

            StringBuffer buf = new StringBuffer();

            for (Object obj : list) {

                JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_ENCODING, DEFAULT_ENCODING);
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                StringWriter stringWriter = new StringWriter();
                marshaller.marshal(obj, stringWriter);
                buf.append(stringWriter.toString());
            }
            return buf.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }


    /**
     * convert xmlString to java bean.
     *
     * @param xmlStr
     * @param requiredType
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T XMLToBean(String xmlStr, Class<T> requiredType) {

        T t = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(requiredType);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlStr);
            t = (T) jaxbUnmarshaller.unmarshal(reader);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return t;
    }

    /**
     * xml转json
     *
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml2Json(String xmlStr) {

        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xmlStr);
        } catch (DocumentException e) {
            throw new RuntimeException("xml Parse Failed.");
        }
        JSONObject json = new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        return json;
    }

    /**
     * xml转json
     *
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element, JSONObject json) {
        //如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (!isEmpty(attr.getValue())) {
                json.put("@" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && !isEmpty(element.getText())) {//如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for (Element e : chdEl) {//有子元素
            if (!e.elements().isEmpty()) {//子元素也有子元素
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject) {//如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject) o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray) o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                    }
                }


            } else {//子元素没有子元素
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (!isEmpty(attr.getValue())) {
                        json.put("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

    public static boolean isEmpty(String str) {

        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }
}
