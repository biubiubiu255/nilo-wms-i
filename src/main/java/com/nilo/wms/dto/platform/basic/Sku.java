package com.nilo.wms.dto.platform.basic;

import com.nilo.wms.common.BaseDo;

public class Sku extends BaseDo<Integer> {

    private String customerId;

    private String sku;

    private String desc_c;

    private String desc_e;

    private Integer status;

    private Integer length;

    private Integer width;

    private Integer height;

    private Integer price;

    private Integer weight;

    private String type;

    private String image;

    private Integer battery;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDesc_c() {
        return desc_c;
    }

    public void setDesc_c(String desc_c) {
        this.desc_c = desc_c;
    }

    public String getDesc_e() {
        return desc_e;
    }

    public void setDesc_e(String desc_e) {
        this.desc_e = desc_e;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "customerId='" + customerId + '\'' +
                ", sku='" + sku + '\'' +
                ", desc_c='" + desc_c + '\'' +
                ", desc_e='" + desc_e + '\'' +
                ", status=" + status +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                ", battery=" + battery +
                '}';
    }
}
