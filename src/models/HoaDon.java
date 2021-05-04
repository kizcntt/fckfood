package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import database.DBconnect;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NhokKiZ
 */
public class HoaDon {

    protected String MaHD;
    protected String nhanvien;
    public int sl;
    protected int thanhtien;
    DBconnect cn = new DBconnect();

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author NhokKiZ
     */

    public ResultSet getThangHD() throws SQLException {
        cn.connectSQL();
        String sql = "SELECT MONTH(NGAYBAN) from hoadon group by MONTH(NGAYBAN)";
        return cn.LoadData(sql);
    }
    
    public ResultSet getNgayHD(int thang) throws SQLException {
        cn.connectSQL();
        String sql = "SELECT DAY(NGAYBAN) from hoadon where MONTH(NGAYBAN)='"+thang+"'  group by NGAYBAN";
        return cn.LoadData(sql);
    }
    
    public ResultSet searchHD(int ngay,int thang) throws SQLException {
        cn.connectSQL();
        String sql = "CALL SearchHD("+ngay+","+thang+")";
        return cn.LoadData(sql);
    }
    
    public ResultSet GetAllHD() throws SQLException {
        cn.connectSQL();
        String sql = "SELECT mahd,nhanvien,DATE_FORMAT(ngayban,'%d/%m/%Y'),thanhtien from hoadon";
        return cn.LoadData(sql);
    }

    public ResultSet GetCTHD(int mahd) throws SQLException {
        cn.connectSQL();
        String sql = "SELECT tensp,t.dongia,h.sl,(h.sl*t.dongia) as 'tien' from thucdon t,chitiethd h WHERE h.masp = t.masp and h.mahd = " + mahd;
        return cn.LoadData(sql);
    }

    public void InsertHD(String uname, long tien) throws SQLException {
        cn.connectSQL();
        String sql = "INSERT INTO hoadon values(NULL,'" + uname + "', CURDATE(),'" + tien + "')";
        cn.UpdateData(sql);
    }

    public int GetMaHD() throws SQLException {
        cn.connectSQL();
        String sql = "SELECT MaHD FROM hoadon ORDER BY MaHD DESC LIMIT 1;";
        ResultSet rs = cn.LoadData(sql);
        while (rs.next()) { // Di chuyển con trỏ xuống bản ghi kế tiếp.
            int mahd = rs.getInt(1);
            return mahd;
        }
        cn.closeCon();
        return 0;
    }

    public void InsertCTHD(int mahd, String masp, int sl) throws SQLException {
        cn.connectSQL();
        String sql = "INSERT INTO chitiethd values('" + mahd + "', '" + masp + "' ,'" + sl + "')";
        cn.UpdateData(sql);
        String update = "UPDATE thucdon SET soluong = soluong - " + sl + " WHERE masp = '" + masp + "'";
        cn.UpdateData(update);
    }

    public long GetDT(String query) throws SQLException {
        long dt = 0;
        cn.connectSQL();
        String sql = "SELECT SUM(thanhtien) FROM hoadon WHERE " + query + "(ngayban) = " + query + "(CURDATE())";
        ResultSet rs = cn.LoadData(sql);
        if (rs.next()) {
            dt = rs.getLong(1);
        }
        return dt;
    }

    public long GetTDT() throws SQLException {
        long dt = 0;
        cn.connectSQL();
        String sql = "SELECT SUM(thanhtien) FROM hoadon";
        ResultSet rs = cn.LoadData(sql);
        if (rs.next()) {
            dt = rs.getLong(1);
        }
        return dt;
    }

}
//
