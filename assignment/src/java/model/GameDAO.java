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

/**
 *
 * @author ASUS
 */
public class GameDAO {
    public ArrayList<GameDTO> getAllGames() throws Exception {
        ArrayList<GameDTO> list = new ArrayList<>();
        Connection conn = DbUtils.getConnection();
        String sql = "SELECT * FROM tblGames";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            GameDTO game = new GameDTO(
                rs.getInt("gameID"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("imageURL"),
                rs.getString("fileURL"),
                rs.getInt("categoryID")
            );
            list.add(game);
        }
        return list;
    }

    public GameDTO getGameByID(int gameID) throws Exception {
        Connection conn = DbUtils.getConnection();
        String sql = "SELECT * FROM tblGames WHERE gameID=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, gameID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new GameDTO(
                rs.getInt("gameID"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("imageURL"),
                rs.getString("fileURL"),
                rs.getInt("categoryID")
            );
        }
        return null;
    }
    public List<GameDTO> searchByName(String keyword) throws Exception {
        List<GameDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblGames WHERE title LIKE ?";
        try (Connection con = DbUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GameDTO game = new GameDTO(
                        rs.getInt("gameID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("imageURL"),
                        rs.getString("fileURL"),
                        rs.getInt("categoryID")
                    );
                    list.add(game);
                }
            }
        }
        return list;
    }
    public boolean insertGame(GameDTO game) throws Exception {
    String sql = "INSERT INTO tblGames (title, description, imageURL, fileURL, categoryID) VALUES (?, ?, ?, ?, ?)";
    try (Connection con = DbUtils.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, game.getTitle());
        ps.setString(2, game.getDescription());
        ps.setString(3, game.getImageURL());
        ps.setString(4, game.getFileURL());
        ps.setInt(5, game.getCategoryID());
        return ps.executeUpdate() > 0;
    }
    }
    public boolean isTitleExist(String title) throws Exception {
    String sql = "SELECT COUNT(*) FROM tblGames WHERE title = ?";
    try (Connection conn = DbUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    public void updateGame(GameDTO game) throws Exception {
    String sql = "UPDATE tblGames SET title=?, description=?, imageURL=?, fileURL=?, categoryID=? WHERE gameID=?";
    try (Connection con = DbUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, game.getTitle());
        ps.setString(2, game.getDescription());
        ps.setString(3, game.getImageURL());
        ps.setString(4, game.getFileURL());
        ps.setInt(5, game.getCategoryID());
        ps.setInt(6, game.getGameID());
        ps.executeUpdate();
        }
    }
    public boolean deleteGame(int gameID) {
    String sql = "DELETE FROM tblGames WHERE gameID = ?";
    try (Connection conn = DbUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, gameID);
        int affectedRows = ps.executeUpdate();
        return affectedRows > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
    }
}
