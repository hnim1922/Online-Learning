/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.SubjectDimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBConnection;

/**
 *
 * @author Admin
 */
public class SubjectDimensionDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public SubjectDimensionDAO() {
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
    
    public ArrayList<SubjectDimension> getAllSubjectDimension() throws Exception{
        ArrayList<SubjectDimension> lst = new ArrayList<>();
        String sql = "SELECT * FROM subjectdimension";
        try{
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if(con!=null){
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    int subdimenID = rs.getInt("dimensionID");
                    String type = rs.getString("type");
                    String name = rs.getString("name");
                    String desc = rs.getString("description");
                    
                    SubjectDimension dimen = new SubjectDimension(subdimenID, type, name, desc);
                    lst.add(dimen);
                }
            }
        }
        finally{
            closeConnection();
        }
        return lst;
    }
    
    public SubjectDimension getSubjectDimensionbyID(int fid) throws Exception{
        SubjectDimension dimen = null;
        String sql = "SELECT * FROM subjectdimension where dimensionID = ?";
        try{
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if(con!=null){
                ps = con.prepareStatement(sql);
                ps.setInt(1, fid);
                rs = ps.executeQuery();
                while(rs.next()){
                    int subdimenID = rs.getInt("dimensionID");
                    String type = rs.getString("type");
                    String name = rs.getString("name");
                    String desc = rs.getString("description");
                    
                    dimen = new SubjectDimension(subdimenID, type, name, desc);
                }
            }
        }
        finally{
            closeConnection();
        }
        return dimen;
    }
    
    public boolean AddANewDimension(SubjectDimension dimen) throws Exception {
        String sql = "INSERT INTO subjectdimension (type, name, description)\n"
                + "VALUES (?, ?, ?)";
        boolean result = false;
        try{
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if(con!=null){
                ps = con.prepareStatement(sql);
                ps.setString(1, dimen.getType());
                ps.setString(2, dimen.getName());
                ps.setString(3, dimen.getDescription());
                ps.executeUpdate();
                
                result = true;
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }
    
    public boolean deleteDimension(int id) throws Exception {

        String sql = "DELETE FROM subjectdimension WHERE dimensionID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);

                ps.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean UpdateDimesion(SubjectDimension dimen) throws Exception{
        String sql = "update subjectdimension set type=?, name=?, description=? where dimensionID=?";
        boolean result = false;
        try{
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if(con!=null){
                ps = con.prepareStatement(sql);
                ps.setString(1, dimen.getType());
                ps.setString(2, dimen.getName());
                ps.setString(3, dimen.getDescription());
                ps.setInt(4, dimen.getDimensionID());
                
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
