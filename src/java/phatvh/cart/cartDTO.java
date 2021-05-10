/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.cart;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class cartDTO implements Serializable{
    private String idCar;
    private String nameCar;
    private String color;
    private int year;
    private int quantity;
    private float price;
    private String cateCar;
    private String rentDate;
    private String returnDate;

    public cartDTO() {
    }

    public cartDTO(String idCar, String nameCar, String color, int year, int quantity, float price, String cateCar, String rentDate, String returnDate) {
        this.idCar = idCar;
        this.nameCar = nameCar;
        this.color = color;
        this.year = year;
        this.quantity = quantity;
        this.price = price;
        this.cateCar = cateCar;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
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
     * @return the nameCar
     */
    public String getNameCar() {
        return nameCar;
    }

    /**
     * @param nameCar the nameCar to set
     */
    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
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
     * @return the cateCar
     */
    public String getCateCar() {
        return cateCar;
    }

    /**
     * @param cateCar the cateCar to set
     */
    public void setCateCar(String cateCar) {
        this.cateCar = cateCar;
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

    
    
}
