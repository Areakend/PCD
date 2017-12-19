package eu.telecomnancy.pcd2k17.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDb {

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
 
    public void delete(String title) {
        String sql = "DELETE FROM assignments WHERE title = ?";
        
        System.out.println("Deleting");
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	
        	System.out.println("Deleting...");
 
            pstmt.setString(1, title);
            pstmt.executeUpdate();
            System.out.println("Delete OK");
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    
    public static void main(String[] args) {
        DeleteDb app = new DeleteDb();
        app.delete("moci2k17");
    }
 
}