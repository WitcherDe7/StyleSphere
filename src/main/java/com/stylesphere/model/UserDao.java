package com.stylesphere.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecom";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return con;
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String saltedPassword = "ASLheir_@#!!!owpe" + password; 
            byte[] hashedPassword = md.digest(saltedPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // handle exception
        }
        return null;
    }

    public String registration(String username, String email, String password) {
        String message = "";
        try {
            Connection con = getConnection();
            String hashedPassword = hashPassword(password);
            String query = "INSERT INTO users (username, full_name, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, username);
            pst.setString(3, email);
            pst.setString(4, hashedPassword);
            int rows = pst.executeUpdate();
            if (rows >= 1) {
                message = "Successfully registered";
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            message = e.getMessage();
        }
        return message;
    }

    public ResultSet fetchRecord() {
        try {
            Connection con = getConnection();
            String query = "SELECT * FROM users";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet table = st.executeQuery();
            // do not close connection here to allow further processing of the ResultSet
            return table;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ResultSet fetchUserRecord(String email) {
        try {
            Connection con = getConnection();
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, email);
            ResultSet table = st.executeQuery();
            // do not close connection here to allow further processing of the ResultSet
            return table;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

    
    public boolean checkLogin(String email, String password) {
        boolean isValid = false;
        try {
            Connection con = getConnection();
            String hashedPassword = hashPassword(password);
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, email);
            st.setString(2, hashedPassword);
            ResultSet table = st.executeQuery();
            if (table.next()) {
                isValid = true;
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
