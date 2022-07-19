/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.PricePackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

/**
 *
 * @author ihtng
 */
public class PricePackageDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public PricePackageDAO() {
    }

    private void closeConnection() throws SQLException {
        if (con != null) {
            con.close();
        }
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
    }

    public ArrayList<Float> getPriceOfPackageById(int id) throws SQLException {
        ArrayList<Float> priceList = new ArrayList<>();
        String sql = "select listPrice, salePrice from pricepackage where pricePackageID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    priceList.add(rs.getFloat(1));
                    priceList.add(rs.getFloat(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return priceList;
    }

    public float getPricePackage(int pricePackageID) throws SQLException {
        String sql = "select salePrice from pricepackage where pricepackageID=?";
        float salePrice = 0;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, pricePackageID);

                rs = ps.executeQuery();
                if (rs.next()) {
                    salePrice = rs.getFloat("salePrice");
                }
            }
        } catch (Exception e) {
            System.out.println("getPricePackage :: " + e);
        } finally {
            closeConnection();
        }
        return salePrice;
    }

    public PricePackage getLowestPricePackageBySubjectID(int subjectID) throws SQLException {
        String sql = "select * from pricepackage where salePrice=(select min(saleprice) from (select * from pricepackage where subjectID = ?) as T) and subjectID = ?;";
        PricePackage pricePackage = new PricePackage();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, subjectID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    pricePackage.setPricePackageID(rs.getInt(1));
                    pricePackage.setName(rs.getString(2));
                    pricePackage.setAccessDuration(rs.getInt(3));
                    pricePackage.setStatus(rs.getString(4));
                    pricePackage.setListPrice(rs.getFloat(5));
                    pricePackage.setSalePrice(rs.getFloat(6));
                    pricePackage.setDescription(rs.getString(7));
                    pricePackage.setSubjectID(rs.getInt(8));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pricePackage;
    }

    public ArrayList<PricePackage> getPricePackageBySubjectId(int id) throws Exception {
        String sql = "select * from pricepackage where subjectID = ?";
        ArrayList<PricePackage> lst = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int pid = rs.getInt("pricePackageID");
                    String name = rs.getString("name");
                    int access = rs.getInt("accessDuration");
                    String status = rs.getString("status");
                    float listPrice = rs.getFloat("listPrice");
                    float salePrice = rs.getFloat("salePrice");
                    String desc = rs.getString("description");
                    int sid = rs.getInt("subjectID");
                    PricePackage pp = new PricePackage(pid, name, access, status, listPrice, salePrice, desc, sid);
                    lst.add(pp);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public List<PricePackage> getPricePackageBySubID(int subID) throws Exception {
        String sql = "select * from pricepackage where subjectID = ?;";
        List<PricePackage> list = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, subID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int pricePackageID = rs.getInt("pricePackageID");
                    String name = rs.getString("name");
                    int accessDuration = rs.getInt("accessDuration");
                    String status = rs.getString("status");
                    float listPrice = rs.getFloat("listPrice");
                    float salePrice = rs.getFloat("salePrice");
                    String description = rs.getString("description");
                    PricePackage pricePackage = new PricePackage(pricePackageID, name, accessDuration, status, listPrice, salePrice, description, subID);
                    list.add(pricePackage);
                }
            }
        } catch (Exception e) {
            System.out.println("getPricePackageBySubID :: " + e);
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean AddANewPricePackage(PricePackage p) throws Exception {
        String sql = "INSERT INTO pricepackage (name, accessDuration, status, listPrice, salePrice, description, subjectID)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean result = false;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, p.getName());
                ps.setInt(2, p.getAccessDuration());
                ps.setString(3, p.getStatus());
                ps.setFloat(4, p.getListPrice());
                ps.setFloat(5, p.getSalePrice());
                ps.setString(6, p.getDescription());
                ps.setInt(7, p.getSubjectID());
                ps.executeUpdate();

                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public PricePackage getPricePackageByPricePackageId(int id) throws Exception {
        String sql = "select * from pricepackage where pricePackageID = ?";
        PricePackage price = null;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int pid = rs.getInt("pricePackageID");
                    String name = rs.getString("name");
                    int access = rs.getInt("accessDuration");
                    String status = rs.getString("status");
                    float listPrice = rs.getFloat("listPrice");
                    float salePrice = rs.getFloat("salePrice");
                    String desc = rs.getString("description");
                    int sid = rs.getInt("subjectID");
                    
                    price = new PricePackage(pid, name, access, status, listPrice, salePrice, desc, sid);
                }
            }
        } finally {
            closeConnection();
        }
        return price;
    }
    
    public boolean UpdatePricePackage(PricePackage p) throws Exception{
        String sql = "update pricepackage set name=?, accessDuration=?, status=?, listPrice=?, salePrice=?, description=? where pricePackageID=?";
        boolean result = false;
        try{
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if(con!=null){
                ps = con.prepareStatement(sql);
                ps.setString(1, p.getName());
                ps.setInt(2, p.getAccessDuration());
                ps.setString(3, p.getStatus());
                ps.setFloat(4, p.getListPrice());
                ps.setFloat(5, p.getSalePrice());
                ps.setString(6, p.getDescription());
                ps.setInt(7, p.getPricePackageID());
                ps.executeUpdate();
                
                result = true;
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }
}
