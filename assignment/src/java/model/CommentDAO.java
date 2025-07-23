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
public class CommentDAO {
    public ArrayList<CommentDTO> getCommentsByGameID(int gameID) throws Exception {
        ArrayList<CommentDTO> list = new ArrayList<>();
        Connection conn = DbUtils.getConnection();
        String sql = "SELECT * FROM tblComments WHERE gameID=? AND status='approved'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, gameID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new CommentDTO(
                rs.getInt("commentID"),
                rs.getInt("gameID"),
                rs.getInt("userID"),
                rs.getString("commentText"),
                rs.getString("commentDate"),
                rs.getString("status")
            ));
        }
        return list;
    }    
}
