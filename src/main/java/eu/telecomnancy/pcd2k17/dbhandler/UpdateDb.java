package eu.telecomnancy.pcd2k17.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 

public class UpdateDb {

    private Connection connect() {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/eu/telecomnancy/pcd2k17/database/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    
    public void update(String title, String description, String discipline) {
        String sql = "UPDATE assignments SET description = ? , "
                + "discipline = ? "
                + "WHERE title = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
        	pstmt.setString(1, description);
            pstmt.setString(2, discipline);
            pstmt.setString(3, title);
            pstmt.executeUpdate();
            System.out.println("MAJ OK");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public static void main(String[] args) {
        
        UpdateDb app = new UpdateDb();
        app.update("moci2k17", "Surprise", "MACI");
    }
 
}