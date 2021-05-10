/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phatvh.ultilities.DBHelper;

/**
 *
 * @author ASUS
 */
public class accountDAO implements Serializable {

    public accountDTO checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select email, phone, name, address, role, status, verify_code "
                        + "from account "
                        + "where email = ? and password = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String email = rs.getString(1);
                    String phone = rs.getString(2);
                    String name = rs.getString(3);
                    String address = rs.getString(4);
                    boolean role = rs.getBoolean(5);
                    boolean status = rs.getBoolean(6);
                    String code = rs.getString(7);
                    accountDTO dto = new accountDTO(email, phone, name, address, role, status, code);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean checkEmailExist(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select email "
                        + "from account "
                        + "where email = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public void insertAccount(String email, String name, String password, String phone, String address, boolean status, String verifyCode) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into account(email,phone,name,address,password,status,verify_code)\n"
                        + "values(?,?,?,?,?,?,?)";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                pst.setString(2, phone);
                pst.setString(3, name);
                pst.setString(4, address);
                pst.setString(5, password);
                pst.setBoolean(6, status);
                pst.setString(7, verifyCode);
                pst.executeUpdate();
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void updateStatusAccount(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update account "
                        + "set status = 1 "
                        + "where email = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.executeUpdate();
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
