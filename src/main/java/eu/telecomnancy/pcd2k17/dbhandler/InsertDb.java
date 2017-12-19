package eu.telecomnancy.pcd2k17.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDb {//extends MainDbhandler {
	
 
    public void insert(String title, String description, String discipline) {
        String sql = "INSERT INTO Assignments(title,description,discipline) VALUES(?,?,?);";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, discipline);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    
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
    
    public static void main(String[] args) {
 
        InsertDb app = new InsertDb();
        app.insert("pcd2k17", "c'est la merde", "PCD");
        app.insert("rs2k17", "c'est encore la merde", "RS");
        app.insert("moci2k17", "como esta en la casa", "MOCI");
        app.insert("pcd2k16", "youteub", "PCD");
    }
 
}