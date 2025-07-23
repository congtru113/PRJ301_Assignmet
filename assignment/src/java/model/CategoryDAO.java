/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class CategoryDAO {
    public ArrayList<CategoryDTO> getAllCategories() throws Exception {
        ArrayList<CategoryDTO> list = new ArrayList<>();
        Connection conn = DbUtils.getConnection();
        String sql = "SELECT * FROM tblCategories";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new CategoryDTO(rs.getInt("categoryID"), rs.getString("categoryName")));
        }
        return list;
    }
}
