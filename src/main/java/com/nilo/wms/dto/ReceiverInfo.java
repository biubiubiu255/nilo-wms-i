package com.nilo.wms.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by ronny on 2017/9/27.
 */
public class ReceiverInfo {

    private String receiverName;
    private String receiverPhone;
    private String receiverCountry;
    private String receiverProvince;
    private String receiverCity;
    private String receiverArea;
    private String receiverAddress;
    private String countryId;
    private String provinceId;
    private String cityId;
    private String areaId;

    public String getCountryId() {
        return countryId;
    }
    @JSONField(name = "country_id")
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getProvinceId() {
        return provinceId;
    }
    @JSONField(name = "province_id")
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }
    @JSONField(name = "city_id")
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }
    @JSONField(name = "area_id")
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getReceiverName() {
        return receiverName;
    }
    @JSONField(name = "contact_name")
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }
    @JSONField(name = "contact_number")
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }
    @JSONField(name = "country")
    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }
    @JSONField(name = "province")
    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }
    @JSONField(name = "city")
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }
    @JSONField(name = "receiver_address")
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
}
