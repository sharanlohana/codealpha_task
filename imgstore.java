/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.FileInputStream;
import java.io.*;
import java.sql.*;
/**
 *
 * @author PMYLS
 */
public class imgstore { 
 
   
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement ps = null;
        FileInputStream fis = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new", "root", "210903");

            // Prepare the SQL statement
            String query = "INSERT INTO image (id, img1) VALUES (?, ?)";
            ps = con.prepareStatement(query);

            // Set the ID (change the ID to whatever is appropriate)
            ps.setInt(1, 28);

            // Check if the file exists before trying to open it
            File imageFile = new File("D:\\javaScript\\pics\\4.jpg");
            if (!imageFile.exists()) {
                System.out.println("File not found at: " + imageFile.getAbsolutePath());
                return;  // Exit if the file is not found
            }

            // Open the image file as a FileInputStream
            fis = new FileInputStream(imageFile);

            // Set the image file as a Binary Stream in the SQL statement
            ps.setBinaryStream(2, fis, fis.available());

            // Execute the query to insert the image into the database
            ps.executeUpdate();

            System.out.println("Image successfully stored in the database.");

        } catch (Exception e) {
            // Print any exceptions that occur
            e.printStackTrace();
        } finally {
            // Close the resources to prevent memory leaks
            try {
                if (fis != null) fis.close(); // Close the FileInputStream
                if (ps != null) ps.close();   // Close the PreparedStatement
                if (con != null) con.close(); // Close the database connection
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}



    

