/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.DBconnect;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NhokKiZ
 */
public class ThucDon {
    protected String masp;
    protected String tensp;
    public static int gia;
    public int soluong;
    DBconnect cn = new DBconnect();
    
 
 public ResultSet Thucdon() throws SQLException { 
     cn.connectSQL();
      String sql = "select * from thucdon";
      return cn.LoadData(sql);
  }
 
  public ResultSet getMon(int loai) throws SQLException {
     cn.connectSQL();
      String sql = "select * from thucdon where loai = "+loai;
      return cn.LoadData(sql);
  }
  public ResultSet getMontheoma(String masp) throws SQLException {
     cn.connectSQL();
      String sql = "select * from thucdon where masp = '"+masp+"'";
      return cn.LoadData(sql);
  }
  
  public ResultSet getMonTheogia(long gia) throws SQLException {
     cn.connectSQL();
      String sql = "select * from thucdon where dongia = "+gia;
      return cn.LoadData(sql);
  }
  
  public String currentLast() throws SQLException {
     String lastSP = null;
     try{
     cn.connectSQL();
      String sql = "select masp from thucdon order by masp desc limit 1";
      ResultSet rs = cn.LoadData(sql);
       while (rs.next()){ // Di chuyển con trỏ xuống bản ghi kế tiếp.
            lastSP = rs.getString(1);
       }
       cn.closeCon();
       return lastSP;
      }
    catch(Exception ex) {
                System.out.println("Exception : " +ex.getMessage());
                System.exit(250);
                ex.printStackTrace();
        }
      return null;
    }
  public void InsertSanpham(String ma, int loai, String ten, long dongia, int sl) throws SQLException{ 
      if(ten.equals(""))
          throw new NullPointerException("name not accept null");
      cn.connectSQL();
       String sql = "INSERT INTO thucdon values('" + ma +"',"+loai+",N'" + ten +"','" + dongia + "','"+sl+"')";
       cn.UpdateData(sql);
    }
  public void UpdateSanpham(String ma, int loai, String ten, long dongia, int sl) throws SQLException{ 
      if(ten.equals(""))
        throw new NullPointerException("name not accept null");
      cn.connectSQL();
       String sql = "UPDATE thucdon SET	masp = '"+ma+"', loai = "+loai+", tensp = '"+ten+"', dongia = "+dongia+" , soluong = "+sl+" WHERE masp = '"+ma+"';";
       cn.UpdateData(sql);
    }
  
  public void DelSanpham(String ma) throws SQLException{ 
      cn.connectSQL();
       String sql = "DELETE FROM thucdon WHERE masp = '"+ma+"'";
       cn.UpdateData(sql);
    }
  public ResultSet searchMon(String kw, String clause) throws SQLException {
     cn.connectSQL();
      String sql = "select * from thucdon where "+kw+" LIKE '%"+clause+"%'";
      return cn.LoadData(sql);
  }
  }



//