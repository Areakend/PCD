package eu.telecomnancy.pcd2k17.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDb extends MainDbhandler {
 
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