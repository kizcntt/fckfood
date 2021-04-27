/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.DBconnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * 
 * @author Administrator
 */
public class User {    
    protected static String username;
    protected String password;
    protected static String name;
    protected String Dob;
    protected String phone;
    protected String mail;
    protected int role;
    DBconnect cn = new DBconnect();
    protected String ngays,thangs,nams ="";
    
    public void setName(){
       this.name = "lmao";
    }
    public String getNgay(){ return this.ngays; }
    public void setNgay(String ngay){ this.ngays = ngay; }
        
    public String getThang(){return this.thangs;}
    public void setThang(String thang){ this.thangs = thang; }
        
    public String getNam(){ return this.nams; }
    public void setNam(String nam){ this.nams = nam; }
    
    public ResultSet getAllNV() throws SQLException{
        cn.connectSQL();
        String sql = "select username,name,DATE_FORMAT(DoB,'%d/%m/%Y'),phone,mail,case when roles = 1 then 'admin' else 'nhân viên' end from users";
        return cn.LoadData(sql);
    }
    
    public void getInfoNV(String uname) throws SQLException{
        cn.connectSQL();
        String sql = "select * from users where username ='"+uname+"'";
        ResultSet rs = cn.LoadData(sql);
        while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                  this.username = rs.getString(1);
                  this.password = rs.getString(2);
                  this.name = rs.getString(3);
                  this.Dob = rs.getString(4);
                  this.phone = rs.getString(5);
                  this.mail = rs.getString(6);
                  this.role = rs.getInt(7);
        }
    }
    
    public ResultSet getUser(String uname) throws SQLException{
        cn.connectSQL();
        String sql = "select username,name,DATE_FORMAT(DoB,'%d/%m/%Y'),phone,mail,case when roles = 1 then 'admin' else 'nhân viên' end from users where username ='"+uname+"'";
        return cn.LoadData(sql);
    }
    
    public static String userName(){
         return username;
     }
     public static String name(){
         return name;
     }
    
    public void MsgBox(String ms){
        JOptionPane mess = new JOptionPane();
        mess.setMessageType(ERROR_MESSAGE);
        JDialog dialog = mess.createDialog("Error");
        dialog.setSize(400, 150);
        mess.setMessage(ms);
        dialog.show();
    }
    public int checkLogin(String uid, String pass) {
        if(!("".equals(uid) || "".equals(pass))){
            try{
                getInfoNV(uid);
                if(username.equals(uid) && password.equals(pass)) {
                   System.out.print("successful");
                  }
                   else{
                      MsgBox("\n Tài khoản hoặc mật khẩu không đúng xin vui lòng thử lại \n\n");
                      return role=0;
                  }     
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
    
    public boolean ThemNV(String uid,String name,String phone,String mail,int role){
        String DoB;
        DoB = this.nams +"-"+this.thangs+"-"+this.ngays;
        if(uid.equals("") || name.equals("") && this.nams.equals("")){
            return false;
        }
        try{
        switch(this.thangs){
            case "02":
                if(Integer.parseInt(this.ngays)>29)
                 return false;
                break;
            case "04" : case "06" : case "09" : case "11" :
                if(Integer.parseInt(this.ngays)>30)
                 return false;
                 break;
        }

        cn.connectSQL();
        String sql = "INSERT INTO users values('" + uid +"','123456','"+name+"','" + DoB +"','" + phone + "','"+mail+"','"+role+"')";
        cn.UpdateData(sql);
        return true;
        }
        catch(SQLException ex){
        return false;
        }
    }
    
    public void UpdateNV(String uid,String name,String phone,String mail,int role) throws SQLException{ 
        if(uid.equals("") || name.equals("")){
            throw new NullPointerException("Name can't null");
        }
        cn.connectSQL();
        String sql = "UPDATE users SET	username = '"+uid+"', name = '"+name+"', phone = '"+phone+"', mail = '"+mail+"' , roles = "+role+" WHERE username = '"+uid+"';";
        cn.UpdateData(sql);
    }
    
    public void DelNV(String uid) throws SQLException{ 
      cn.connectSQL();
       String sql = "DELETE FROM users WHERE username = '"+uid+"'";
       cn.UpdateData(sql);
    }
    
    public ResultSet searchNV(String kw, String clause) throws SQLException {
     cn.connectSQL();
      String sql = "select username,name,DATE_FORMAT(DoB,'%d/%m/%Y'),phone,mail,case when roles = 1 then 'admin' else 'nhân viên' end from users where "+kw+" LIKE '%"+clause+"%'";
      return cn.LoadData(sql);
  }
    
    public static void Logout() {
        User.name=null;
        User.username=null;
    }
    
    
    public void RegExDoB(String string){
        final Pattern pattern = Pattern.compile("(\\d{2})");
        final Matcher matcher = pattern.matcher(string);
        int a=0;
        String ngay = "",thang="",nam="";
        
        while (matcher.find()) {
            if(a==0){
                ngay=matcher.group().toString();
                
            }
            if(a==1){
               thang= matcher.group().toString();
            }
            if(a>1){
            nam += matcher.group().toString();
            }
            a++;
        }
        ngays=ngay;
        thangs=thang;
        nams=nam;
    }
     
   
    
    

}
