/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.discount;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class discountDTO implements Serializable{
    private String id;
    private String code;
    private int percentDiscount;
    private String expirationDate;

    public discountDTO() {
    }

    public discountDTO(String id, String code, int percentDiscount, String expirationDate) {
        this.id = id;
        this.code = code;
        this.percentDiscount = percentDiscount;
        this.expirationDate = expirationDate;
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the percentDiscount
     */
    public int getPercentDiscount() {
        return percentDiscount;
    }

    /**
     * @param percentDiscount the percentDiscount to set
     */
    public void setPercentDiscount(int percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    /**
     * @return the expirationDate
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    
    
}
