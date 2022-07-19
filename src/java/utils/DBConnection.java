/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class DBConnection {
    private final String servername = "localhost";
    private final String dbname = "SWP391_OnlineLearn";
    private final String portNumber = "3306";
    private final String userID = "root";
    private final String password = "minh127$$$$";


    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://" + servername + ":" + portNumber + "/" + dbname + "?zeroDateTimeBehaviour=CONVERT_TO_NULL";
        Class.forName("com.mysql.cj.jdbc.Driver");    
        return DriverManager.getConnection(url, userID, password);
    }
} 
