/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
 
public class DBconnect {

    public Connection conn;
    //Phuong thuc thuc hien ket noi CSDL
    public void connectSQL() throws SQLException{
           try{
            String hostName = "localhost";
            String dbName = "fckfood";
            String userName = "dbadmin";
            String password = "admin";
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName+"?useUnicode=yes&characterEncoding=UTF-8";
                conn = DriverManager.getConnection(connectionURL, userName, password);
           }
           catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Ket noi CSDL that bai","Thong bao",1); 
            }
        
    }
    
    public void closeCon() throws SQLException{
        conn.close();
    }
    
    //Phuong thuc dung de truy van CSDL
    public ResultSet LoadData(String sql){
        ResultSet result = null;
        try 
        {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        return null ;
        }
    } 
    //Phuong thuc thuc hien Them, Xoa, Sua du lieu
    public void UpdateData(String sql){ 
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
