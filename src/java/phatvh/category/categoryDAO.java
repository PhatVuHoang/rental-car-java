/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.category;

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
public class categoryDAO implements Serializable {

    public ArrayList<categoryDTO> getCategory() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<categoryDTO> listCategory = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select id, category_name "
                        + "from category ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                    String idCategory = rs.getString(1);
                    String nameCategory = rs.getString(2);
                    categoryDTO dto = new categoryDTO(idCategory, nameCategory);
                    if(listCategory == null) {
                        listCategory = new ArrayList<>();
                    }
                    listCategory.add(dto);
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
        return listCategory;
    }
}
