/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.order;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class orderDTO2 implements Serializable{
    private String idOrder;
    private String idCar;
    private String dateOrder;
    private String carName;
    private String color;
    private int year;
    private float price;
    private int quantity;
    private boolean status;
    private float rate;
    private String rentDate;
    private String returnDate;
    private String percent;

    public orderDTO2() {
    }

    public orderDTO2(String idOrder, String dateOrder, String percent) {
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
        this.percent = percent;
    }

    public orderDTO2(String idOrder, String idCar, String dateOrder, String carName, String color, int year, float price, int quantity, boolean status, float rate, String rentDate, String returnDate) {
        this.idOrder = idOrder;
        this.idCar = idCar;
        this.dateOrder = dateOrder;
        this.carName = carName;
        this.color = color;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.rate = rate;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    /**
     * @return the idOrder
     */
    public String getIdOrder() {
        return idOrder;
    }

    /**
     * @param idOrder the idOrder to set
     */
    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    /**
     * @return the idCar
     */
    public String getIdCar() {
        return idCar;
    }

    /**
     * @param idCar the idCar to set
     */
    public void setIdCar(String idCar) {
        this.idCar = idCar;
    }

    /**
     * @return the dateOrder
     */
    public String getDateOrder() {
        return dateOrder;
    }

    /**
     * @param dateOrder the dateOrder to set
     */
    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    /**
     * @return the carName
     */
    public String getCarName() {
        return carName;
    }

    /**
     * @param carName the carName to set
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the rate
     */
    public float getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(float rate) {
        this.rate = rate;
    }

    /**
     * @return the rentDate
     */
    public String getRentDate() {
        return rentDate;
    }

    /**
     * @param rentDate the rentDate to set
     */
    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    
    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
    
}
