/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.car;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class carDTO implements Serializable{
    private String id;
    private String nameCar;
    private String imgCar;
    private String color;
    private int year;
    private float price;
    private int quantity;
    private float rate;
    private String idCategory;

    public carDTO() {
    }

    public carDTO(String id, String nameCar, String imgCar, String color, int year, float price, int quantity, float rate, String idCategory) {
        this.id = id;
        this.nameCar = nameCar;
        this.imgCar = imgCar;
        this.color = color;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.rate = rate;
        this.idCategory = idCategory;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the imgCar
     */
    public String getImgCar() {
        return imgCar;
    }

    /**
     * @param imgCar the imgCar to set
     */
    public void setImgCar(String imgCar) {
        this.imgCar = imgCar;
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
     * @return the idCategory
     */
    public String getIdCategory() {
        return idCategory;
    }

    /**
     * @param idCategory the idCategory to set
     */
    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    
}
