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
import java.util.List;
import utils.PasswordUtils;

/**
 *
 * @author ASUS
 */
public class UserDAO {
    public UserDTO checkLogin(String username, String password) throws Exception {
        Connection conn = DbUtils.getConnection();
        String sql = "SELECT * FROM tblUsers WHERE username=? AND password=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new UserDTO(
                rs.getInt("userID"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("role")
            );
        }
        return null;
    }

    public boolean register(String username, String password, String email) throws Exception {
        Connection conn = DbUtils.getConnection();
        String checkSql = "SELECT * FROM tblUsers WHERE username=?";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        checkStmt.setString(1, username);
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next()) {
            return false;
        }
        String encryptedPassword = PasswordUtils.encryptSHA256(password);
        String sql = "INSERT INTO tblUsers (username, password, email, role) VALUES (?, ?, ?, 'user')";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, encryptedPassword);
        ps.setString(3, email);
        return ps.executeUpdate() > 0;
    }
    
    public boolean isEmailExist(String email) throws Exception {
    String sql = "SELECT email FROM Users WHERE email = ?";
    try (Connection con = DbUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, email);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // true nếu đã tồn tại
        }
    }
    }
    public List<UserDTO> getAllUsers() {
    List<UserDTO> userList = new ArrayList<>();
    String sql = "SELECT userID, username, password, email, role FROM tblUsers ORDER BY userID";
    try {
        Connection conn = DbUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            UserDTO user = new UserDTO();
            user.setUserID(rs.getInt("userID"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
            userList.add(user);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return userList;
    }
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE tblUsers SET password = ? WHERE username = ?";
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, username);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
