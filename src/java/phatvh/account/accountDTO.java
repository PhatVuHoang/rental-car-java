/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.account;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class accountDTO implements Serializable{
    private String email;
    private String phone;
    private String name;
    private String address;
    private boolean role;
    private boolean status;
    private String verifyCode;

    public accountDTO() {
    }

    public accountDTO(String email, String phone, String name, String address, boolean role, boolean status, String verifyCode) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.role = role;
        this.status = status;
        this.verifyCode = verifyCode;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
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
     * @return the verifyCode
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * @param verifyCode the verifyCode to set
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    
}
