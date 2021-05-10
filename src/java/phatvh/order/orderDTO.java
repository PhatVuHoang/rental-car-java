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
public class orderDTO implements Serializable{
    private String idCar;
    private int quantity;
    private float rate;
    private String rentDate;
    private String returnDate;
    private boolean status;

    public orderDTO() {
    }

    public orderDTO(String idCar, int quantity, float rate, String rentDate, String returnDate, boolean status) {
        this.idCar = idCar;
        this.quantity = quantity;
        this.rate = rate;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.status = status;
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

    
}
