package eu.telecomnancy.pcd2k17.dbhandler;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class SelectDb {

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

    
   public void selectAll(){
        String sql = "SELECT title, description, discipline FROM assignments";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getString("title") +  "\t" + 
                                   rs.getString("description") + "\t" +
                                   rs.getString("discipline"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   

   public void getAssignmentsOf(String discipline){
              String sql = "SELECT title, description, discipline "
                         + "FROM assignments WHERE discipline = ?";
       
       try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
           
           pstmt.setString(1, discipline);
           //
           ResultSet rs  = pstmt.executeQuery();
           
           while (rs.next()) {
               System.out.println(rs.getString("title") +  "\t" + 
                                  rs.getString("description") + "\t" +
                                  rs.getString("discipline"));
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   
    public static void main(String[] args) {
        SelectDb app = new SelectDb();
        app.selectAll();
        System.out.println(" ");
        SelectDb app2 = new SelectDb();
        app2.getAssignmentsOf("PCD");
    }
 
}