/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBConnection;

/**
 *
 * @author ihtng
 */
public class CategoryDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public CategoryDAO() {
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

    public ArrayList<Category> getAllCategory1() throws Exception {
        String sql = "SELECT * FROM Category";
        ArrayList<Category> list = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int categoryID = rs.getInt("categoryID");
                    String categoryName = rs.getString("category_name");

                    Category category = new Category(categoryID, categoryName);
                    list.add(category);
                }
            }

        } catch (Exception e) {
            System.out.println("CategoryDAO :: " + e);
        }

        return list;
    }

    public ArrayList<Category> getAllCategory() throws SQLException {
        ArrayList<Category> categoryList = new ArrayList<>();
        String sql = "select * from category;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Category category = new Category();
                    category.setCategoryID(rs.getInt(1));
                    category.setCategoryName(rs.getString(2));
                    categoryList.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return categoryList;
    }

    public String getCategoryNameById(int id) throws SQLException {
        String sql = "select category_name from category where categoryID=?";
        String categoryName = null;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    categoryName = rs.getString("category_name");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return categoryName;
    }

//    public Category getAllCategory(int id) throws Exception{
//        String sql = "select * from category where categoryID = ?";
//        Category cate = null;
//        try{
//            DBConnection db = new DBConnection();
//            con = db.getConnection();
//            if(con!=null){
//                ps = con.prepareStatement(sql);
//                ps.setInt(1, id);
//                rs = ps.executeQuery();
//                if(rs.next()){
//                    int cateID = rs.getInt("categoryID");
//                    String cateName = rs.getString("categoryName");
//                    
//                    cate = new Category(cateID, cateName);
//                }
//            }
//        }
//        finally{
//            closeConnection();
//        }
//        return cate;
//    }
    public Category getCategoryById(int fid) throws Exception {
        String sql = "select * from category where categoryID=?";
        Category cate = null;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, fid);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("categoryID");
                    String name = rs.getString("category_name");

                    cate = new Category(id, name);
                }
            }
        } finally {
            closeConnection();
        }
        return cate;
    }
}
