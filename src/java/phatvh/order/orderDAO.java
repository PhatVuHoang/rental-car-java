/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.order;

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
public class orderDAO implements Serializable {

    public ArrayList<orderDTO> getAllOrder(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<orderDTO> list = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select id_car, quantity, rental_date, return_date,status, rate "
                        + "from order_detail "
                        + "where status = 1 and car_name like ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String idCar = rs.getString(1);
                    int quantity = rs.getInt(2);
                    String rentDate = rs.getString(3);
                    String returnDate = rs.getString(4);
                    boolean status = rs.getBoolean(5);
                    float rate = rs.getFloat(6);
                    orderDTO dto = new orderDTO(idCar, quantity, rate, rentDate, returnDate, status);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
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
        return list;
    }

    public void checkOut(String email, String idDiscount, String percent) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into [order](email,id_discount,percent_discount) "
                        + "values(?,?,?) ";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, idDiscount);
                pst.setString(3, percent);
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

    public void insertOrderDetail(String rentalDate, String returnDate, String idCar, String idOrder, String carName, String color, int year, float price, int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into order_detail(rental_date, return_date, id_car, id_invoice, car_name, color, year, price, quantity,status) "
                        + "values(?,?,?,?,?,?,?,?,?,1)";
                pst = con.prepareStatement(sql);
                pst.setString(1, rentalDate);
                pst.setString(2, returnDate);
                pst.setString(3, idCar);
                pst.setString(4, idOrder);
                pst.setString(5, carName);
                pst.setString(6, color);
                pst.setInt(7, year);
                pst.setFloat(8, price);
                pst.setInt(9, quantity);
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

    public String getIdOrder() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select MAX(id) "
                        + "from [order]";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String id = rs.getString(1);
                    return id;
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

    public ArrayList<orderDTO2> getOrderHistoryDetail(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<orderDTO2> list = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select a.id, a.date_order, b.car_name, b.color, b.year, b.price, b.quantity, b.status, b.rate, b.rental_date, b.return_date, b.id_car\n"
                        + "from [order] a join order_detail b on a.id = b.id_invoice\n"
                        + "where a.email = ? and b.status = 1 ";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String idOrder = rs.getString(1);
                    String dateOrder = rs.getString(2);
                    String carName = rs.getString(3);
                    String color = rs.getString(4);
                    int year = rs.getInt(5);
                    float price = rs.getFloat(6);
                    int quantity = rs.getInt(7);
                    boolean status = rs.getBoolean(8);
                    float rate = 0;
                    if (rs.getString(9) != null) {
                        rate = rs.getInt(9);
                    }
                    String rentalDate = rs.getString(10);
                    String returnDate = rs.getString(11);
                    String idCar = rs.getString(12);

                    orderDTO2 dto = new orderDTO2(idOrder, idCar, dateOrder, carName, color, year, price, quantity, status, rate, rentalDate, returnDate);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
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
        return list;
    }

    public ArrayList<orderDTO2> getOrder(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<orderDTO2> list = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select id, date_order, percent_discount\n"
                        + "from [order]\n"
                        + "where email = ? "
                        + "order by date_order ASC ";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String dateOroder = rs.getString(2);
                    String percent = rs.getString(3);
                    orderDTO2 dto = new orderDTO2(id, dateOroder, percent);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
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
        return list;
    }

    public ArrayList<orderDTO2> searchNameOrderDetail(String searchValue, String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<orderDTO2> list = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select a.id, a.date_order,b.car_name, b.color, b.year, b.price, b.quantity, b.status, b.rate, b.rental_date, b.return_date, b.id_car "
                        + "from [order] a join order_detail b on a.id = b.id_invoice "
                        + "where a.email = ? and b.car_name like ?  ";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, "%" + searchValue + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String idOrder = rs.getString(1);
                    String dateOrder = rs.getString(2);
                    String carName = rs.getString(3);
                    String color = rs.getString(4);
                    int year = rs.getInt(5);
                    float price = rs.getFloat(6);
                    int quantity = rs.getInt(7);
                    boolean status = rs.getBoolean(8);
                    String rentalDate = rs.getString(10);
                    String returnDate = rs.getString(11);
                    String idCar = rs.getString(12);
                    float rate = 0;
                    if (rs.getString(9) != null) {
                        rate = rs.getInt(9);
                    }

                    orderDTO2 dto = new orderDTO2(idOrder, idCar, dateOrder, carName, color, year, price, quantity, status, rate, rentalDate, returnDate);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
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
        return list;
    }

    public ArrayList<orderDTO2> searchDateOrderDetail(String email, String date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<orderDTO2> list = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select a.id, a.date_order, b.car_name, b.color, b.year, b.price, b.quantity, b.status, b.rate, b.rental_date, b.return_date, b.id_car\n"
                        + "from [order] a join order_detail b on a.id = b.id_invoice\n"
                        + "where a.email = ? and a.date_order = ? and status = 1";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, date);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String idOrder = rs.getString(1);
                    String dateOrder = rs.getString(2);
                    String carName = rs.getString(3);
                    String color = rs.getString(4);
                    int year = rs.getInt(5);
                    float price = rs.getFloat(6);
                    int quantity = rs.getInt(7);
                    boolean status = rs.getBoolean(8);
                    String rentalDate = rs.getString(10);
                    String returnDate = rs.getString(11);
                    String idCar = rs.getString(12);
                    float rate = 0;
                    if (rs.getString(9) != null) {
                        rate = rs.getInt(9);
                    }

                    orderDTO2 dto = new orderDTO2(idOrder, idCar, dateOrder, carName, color, year, price, quantity, status, rate, rentalDate, returnDate);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
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
        return list;
    }

    public void deleteOrder(String idCar, String idOrder) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update order_detail "
                        + "set status = 0 "
                        + "where id_car = ? and id_invoice = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, idCar);
                pst.setString(2, idOrder);
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

    public void feedBack(String idOrder, String idCar, float rate, String returnDate, String rentDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update order_detail "
                        + "set rate = ? "
                        + "where id_car = ? and id_invoice = ? and rental_date = ? and return_date = ? ";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, rate);
                pst.setString(2, idCar);
                pst.setString(3, idOrder);
                pst.setString(4, rentDate);
                pst.setString(5, returnDate);
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

    public float getAvgFeedBack(String idCar) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select avg(rate) "
                        + "from order_detail "
                        + "where id_car = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, idCar);
                rs = pst.executeQuery();
                if (rs.next()) {
                    float avg = rs.getFloat(1);
                    return avg;
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

    public void updateFeedBack(String idCar, float rate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update car "
                        + "set rate = ? "
                        + "where id = ? ";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, rate);
                pst.setString(2, idCar);
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

    public ArrayList<orderDTO2> searchNameId(String searchValue, String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<orderDTO2> listID = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select a.id,a.date_order, a.percent_discount\n"
                        + "from [order] a join order_detail b on a.id = b.id_invoice \n"
                        + "where a.email = ? and b.car_name like ? and b.status = 1 ";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, "%" + searchValue + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String date = rs.getString(2);
                    String percent = rs.getString(3);
                    orderDTO2 dto = new orderDTO2(id, date, percent);
                    if (listID == null) {
                        listID = new ArrayList<>();
                    }
                    listID.add(dto);
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
        return listID;
    }

    public ArrayList<orderDTO2> searchOrderDetail(String email, String idInvoice) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<orderDTO2> listDetail = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select id_invoice, id_car, a.date_order, car_name, color, quantity, price, status, rate, rental_date, return_date, year\n"
                        + "from [order] a join order_detail b on a.id = b.id_invoice \n"
                        + "where a.email = ? and id_invoice = ? and b.status = 1";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, idInvoice);
                rs = pst.executeQuery();
                while(rs.next()){
                    String idOrder = rs.getString(1);
                    String idCar = rs.getString(2);
                    String dateOrder = rs.getString(3);
                    String carName = rs.getString(4);
                    String color = rs.getString(5);
                    int quantity = rs.getInt(6);
                    float price = rs.getFloat(7);
                    boolean status = rs.getBoolean(8);
                    float rate = 0;
                    if(rs.getString(9) != null) {
                        rate = Float.parseFloat(rs.getString(9));
                    }
                    String rentDate = rs.getString(10);
                    String returnDate = rs.getString(11);
                    int year = rs.getInt(12);
                    orderDTO2 dto = new orderDTO2(idOrder, idCar, dateOrder, carName, color, year, price, quantity, status, rate, rentDate, returnDate);
                    if(listDetail == null) {
                        listDetail = new ArrayList<>();
                    }
                    listDetail.add(dto);
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
        return listDetail;
    }
}
