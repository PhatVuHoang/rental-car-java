/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.discount;

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
public class discountDAO implements Serializable {

    public ArrayList<discountDTO> getDiscount() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<discountDTO> listCode = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select id_discount, code_discount, percent_discount ,expiration_date\n"
                        + "from discount ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String code = rs.getString(2);
                    int percent = rs.getInt(3);
                    String expiration = rs.getString(4);
                    discountDTO dto = new discountDTO(id, code, percent, expiration);
                    if (listCode == null) {
                        listCode = new ArrayList<>();
                    }
                    listCode.add(dto);
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
        return listCode;
    }
}
