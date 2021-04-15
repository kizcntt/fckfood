/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.DBconnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * 
 * @author Administrator
 */
public abstract class User {    
    protected static String username;
    protected String password;
    protected static String name;
    static int role;
    
    public static String userName(){
         return username;
     }
     public static String name(){
         return name;
     }
    
    public static void MsgBox(String ms){
        JOptionPane mess = new JOptionPane();
        mess.setMessageType(ERROR_MESSAGE);
        JDialog dialog = mess.createDialog("Error");
        dialog.setSize(400, 150);
        mess.setMessage(ms);
        dialog.show();
    }
    public static int checkLogin(String uid, String pass) throws SQLException {
        int role=0;
        if(!("".equals(uid) || "".equals(pass))){
            try{
            DBconnect cn = new DBconnect();
            cn.connectSQL();
            String sql = "SELECT username, password, roles FROM users";
            ResultSet rs = cn.LoadData(sql);
              // Duyệt trên kết quả trả về.
              while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                  String username = rs.getString(1);
                  String password = rs.getString(2);
                  int roles = rs.getInt(3);

                  if(username.equals(uid) && password.equals(pass)) {
                    System.out.print("successful");
                    role = roles;
                  }
                   else{
                      MsgBox("\n Tài khoản hoặc mật khẩu không đúng xin vui lòng thử lại \n\n");
                      return role=0;
                  }     
              }
              cn.closeCon();
            }
            catch(Exception ex) {
                        MsgBox("Không thể kết nối database");
                        System.out.println("Exception : " +ex.getMessage());
                        System.exit(250);
                        ex.printStackTrace();
                }
            return role;
        }else
            return role;
    }
    
    public static String userInfo(String uid) {
    try {
     
     DBconnect cn = new DBconnect();
    cn.connectSQL();
    String sql = "SELECT * FROM users WHERE username='"+uid+"'";
    ResultSet rs = cn.LoadData(sql);
       while (rs.next()){ // Di chuyển con trỏ xuống bản ghi kế tiếp.
            String uname = rs.getString("name");
            name = uname;
            User.username = uid;
       }
        cn.closeCon();
        return name;
        
      }
    catch(Exception ex) {
                MsgBox("Không thể kết nối database");
                System.out.println("Exception : " +ex.getMessage());
                System.exit(250);
                ex.printStackTrace();
        }
      return null;
    }
    public static void Logout() {
        User.name=null;
        User.username=null;
    }
     
   
    
    

}
