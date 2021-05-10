/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.car;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import phatvh.ultilities.DBHelper;

/**
 *
 * @author ASUS
 */
public class carDAO implements Serializable {

    public int numberOfCar(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(id) "
                        + "from car "
                        + "where car_name like ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                rs = pst.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count;
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
        return 0;
    }

    public int numberOfCarCate(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(a.id) \n"
                        + "from car  a join category b on a.id_category = b.id\n"
                        + "where b.category_name = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, searchValue);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count;
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
        return 0;
    }

    public ArrayList<carDTO> getAllCar(int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<carDTO> listCar = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select id, car_name, image_car, color, year, price, quantity, rate, id_category "
                        + "from car "
                        + "where quantity > 0 "
                        + "order by create_date "
                        + "offset ? rows "
                        + "fetch next 20 rows only ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, index);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String img = rs.getString(3);
                    String color = rs.getString(4);
                    int year = rs.getInt(5);
                    float price = rs.getFloat(6);
                    int quantity = rs.getInt(7);
                    float rate = rs.getFloat(8);
                    String idCategory = rs.getString(9);
                    carDTO dto = new carDTO(id, name, img, color, year, price, quantity, rate, idCategory);
                    if (listCar == null) {
                        listCar = new ArrayList<>();
                    }
                    listCar.add(dto);
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
        return listCar;
    }

    public ArrayList<carDTO> searchNameCar(String searchValue, String cate, int index) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<carDTO> listCar = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select a.id, car_name, image_car, color, year, price, quantity, rate, id_category "
                        + "from car a join category b on a.id_category = b.id "
                        + "where car_name like ? and category_name like ? and quantity > 0 "
                        + "order by create_date desc "
                        + "offset ? rows "
                        + "fetch next 20 rows only ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                pst.setString(2, "%" + cate + "%");
                pst.setInt(3, index);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String carName = rs.getString(2);
                    String carImg = rs.getString(3);
                    String color = rs.getString(4);
                    int year = rs.getInt(5);
                    float price = rs.getFloat(6);
                    int quantity = rs.getInt(7);
                    float rate = rs.getFloat(8);
                    String idCategory = rs.getString(9);
                    carDTO car = new carDTO(id, carName, carImg, color, year, price, quantity, rate, idCategory);
                    if (listCar == null) {
                        listCar = new ArrayList<>();
                    }
                    listCar.add(car);
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
        return listCar;
    }

    public void updateQuantity(String id, int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update car "
                        + "set quantity = ? "
                        + "where id = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, quantity);
                pst.setString(2, id);
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
