/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.category;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class categoryDTO implements Serializable{
    private String idCategory;
    private String nameCategory;

    public categoryDTO() {
    }

    public categoryDTO(String idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
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

    /**
     * @return the nameCategory
     */
    public String getNameCategory() {
        return nameCategory;
    }

    /**
     * @param nameCategory the nameCategory to set
     */
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
    
    
}
