package eu.telecomnancy.pcd2k17.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UpdateDb extends MainDbhandler {
 
    
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