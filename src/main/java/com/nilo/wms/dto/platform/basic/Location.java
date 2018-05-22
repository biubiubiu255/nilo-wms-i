package com.nilo.wms.dto.platform.basic;

import com.nilo.wms.common.BaseDo;

public class Location extends BaseDo<Integer> {

    private String  locationID;
    private String warehouseCode;
    private Short   putawaySequence;   //上架顺序
    private Short   pickSequence;      //拣货顺序
    private Short   putawayZone;       //上架区
    private Short   pickZone;          //拣货区
    private Boolean areMixGoods;       //是否允许混放产品
    private Boolean areMixLot;         //是否允许混放批次
    private Byte    status;            //状态

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Short getPutawaySequence() {
        return putawaySequence;
    }

    public void setPutawaySequence(Short putawaySequence) {
        this.putawaySequence = putawaySequence;
    }

    public Short getPickSequence() {
        return pickSequence;
    }

    public void setPickSequence(Short pickSequence) {
        this.pickSequence = pickSequence;
    }

    public Short getPutawayZone() {
        return putawayZone;
    }

    public void setPutawayZone(Short putawayZone) {
        this.putawayZone = putawayZone;
    }

    public Short getPickZone() {
        return pickZone;
    }

    public void setPickZone(Short pickZone) {
        this.pickZone = pickZone;
    }

    public Boolean getAreMixGoods() {
        return areMixGoods;
    }

    public void setAreMixGoods(Boolean areMixGoods) {
        this.areMixGoods = areMixGoods;
    }

    public Boolean getAreMixLot() {
        return areMixLot;
    }

    public void setAreMixLot(Boolean areMixLot) {
        this.areMixLot = areMixLot;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationID='" + locationID + '\'' +
                ", warehouseCode=" + warehouseCode +
                ", putawaySequence=" + putawaySequence +
                ", pickSequence=" + pickSequence +
                ", putawayZone=" + putawayZone +
                ", pickZone=" + pickZone +
                ", areMixGoods=" + areMixGoods +
                ", areMixLot=" + areMixLot +
                ", status=" + status +
                '}';
    }
}
