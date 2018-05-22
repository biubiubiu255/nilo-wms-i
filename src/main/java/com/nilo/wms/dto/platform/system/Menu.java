package com.nilo.wms.dto.platform.system;


import java.io.Serializable;
import java.util.List;

/**
 * Created by ronny on 2017/9/14.
 */
public class Menu implements Serializable{
    private static final long serialVersionUID = 5648973232909168716L;
    private String id;

    private String text;

    private String icon;

    private String url;

    private String targetType;

    private Boolean isHeader;

    private List<com.nilo.wms.dto.system.Menu> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Boolean getHeader() {
        return isHeader;
    }

    public void setHeader(Boolean header) {
        isHeader = header;
    }

    public List<com.nilo.wms.dto.system.Menu> getChildren() {
        return children;
    }

    public void setChildren(List<com.nilo.wms.dto.system.Menu> children) {
        this.children = children;
    }
}
