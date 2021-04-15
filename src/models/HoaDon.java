package models;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import database.DBconnect;
import static models.User.MsgBox;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author NhokKiZ
 */
public class HoaDon {
    protected String MaHD;
    protected String nhanvien;
    public int sl;
    protected int thanhtien;
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author NhokKiZ
 */

 public void InsertHD(String uname,long tien) throws SQLException{ 
     DBconnect cn = new DBconnect();  
     cn.connectSQL();
     String sql = "INSERT INTO hoadon values(NULL,'"+uname+"', CURDATE(),'"+tien+"')";
     cn.UpdateData(sql);
    }
 
 public int GetMaHD() throws SQLException{
     DBconnect cn = new DBconnect();  
     cn.connectSQL();
     String sql = "SELECT MaHD FROM hoadon ORDER BY MaHD DESC LIMIT 1;";
     ResultSet rs = cn.LoadData(sql);
     while (rs.next()){ // Di chuyển con trỏ xuống bản ghi kế tiếp.
            int mahd = rs.getInt(1);
            return mahd;
       }
        cn.closeCon();
        return 0;
 }
 
   public void InsertCTHD(int mahd,String masp,int sl) throws SQLException{ 
     DBconnect cn = new DBconnect();  
     cn.connectSQL();
     String sql = "INSERT INTO chitiethd values('"+mahd+"', '"+masp+"' ,'"+sl+"')";
     cn.UpdateData(sql);
    }
  
  
}
//
