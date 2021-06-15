package com.example.finaleproject.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shipment_db")
public class DataShipment {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "idShipment")
    private String idShipment;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "types")
    private String types;

    @ColumnInfo(name = "weight")
    private int weight;

    @ColumnInfo(name = "courierServices")
    private String courierServices;

    @ColumnInfo(name = "origin")
    private String origin;

    @ColumnInfo(name = "senderAddress")
    private String senderAddress;

    @ColumnInfo(name = "destination")
    private String destination;

    @ColumnInfo(name = "receiverAddress")
    private String receiverAddress;

    // SETTER DAN GETTER ===========================================================================

    @NonNull
    public String getIdShipment() {
        return idShipment;
    }

    public void setIdShipment(@NonNull String idShipment) {
        this.idShipment = idShipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCourierServices() {
        return courierServices;
    }

    public void setCourierServices(String courierServices) {
        this.courierServices = courierServices;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
}
