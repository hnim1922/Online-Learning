/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.SubjectDimension;
import beans.Category;
import beans.PricePackage;
import beans.Subject;
import beans.User;
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
public class SubjectDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public SubjectDAO() {
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

    public ArrayList<Subject> getFeaturedSubject() throws Exception {
        ArrayList<Subject> featuredSubjectList = new ArrayList<>();
        String sql = "select subject.subjectID, subject.name, subject.thumbnail, subject.information from subject inner join category on category.categoryID = subject.categoryID where featureflag = 1";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Subject featureSubject = new Subject();
                    featureSubject.setSubjectID(rs.getInt(1));

//                    featureSubject.setDimensionID(rs.getInt(3));
//                    featureSubject.setCategoryName(rs.getString(11));
                    featureSubject.setThumbnail(rs.getString(3));
                    featureSubject.setName(rs.getString(2));
                    featureSubject.setInformation(rs.getString(4));
//                    featureSubject.setFeatureFlag(rs.getBoolean(7));
//                    featureSubject.setStatus(rs.getBoolean(8));
//                    featureSubject.setInformation(rs.getString(9));

                    featuredSubjectList.add(featureSubject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return featuredSubjectList;
    }

    public ArrayList<Subject> getAllSubjectWithLowestPricePackage() throws SQLException {
        PricePackage pricePackage = new PricePackage();
        ArrayList<Subject> subjectList = new ArrayList<>();
        String sql = "select salePrice, listPrice, pricepackage.subjectID, subject.name, subject.information, category_name from pricepackage  inner join subject on subject.subjectID = pricepackage.subjectID inner join category on category.categoryID = subject.categoryID where salePrice=(select min(saleprice) from (select * from pricepackage where pricepackage.subjectID = subject.subjectID) as T)";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Subject subject = new Subject();
                    pricePackage.setSalePrice(rs.getFloat(1));
                    pricePackage.setListPrice(rs.getFloat(2));
                    subject.setSubjectID(rs.getInt(3));
                    subject.setName(rs.getString(4));
                    subject.setInformation(rs.getString(5));
                    subject.setCategoryName(rs.getString(6));
                    subject.setPricePackage(pricePackage);

                    subjectList.add(subject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return subjectList;
    }

    public ArrayList<Subject> getAllSubjectWithLowestPricePackage(int start, int total) throws SQLException {
        ArrayList<Subject> subjectList = new ArrayList<>();
        String sql = "select salePrice, listPrice, pricepackage.subjectID, subject.name, subject.information, category_name, subject.thumbnail from pricepackage  inner join subject on subject.subjectID = pricepackage.subjectID inner join category on category.categoryID = subject.categoryID where salePrice=(select min(saleprice) from (select * from pricepackage where pricepackage.subjectID = subject.subjectID) as T) limit " + (start - 1) + "," + total;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PricePackage pricePackage = new PricePackage();
                    Subject subject = new Subject();
                    pricePackage.setSalePrice(rs.getFloat(1));
                    pricePackage.setListPrice(rs.getFloat(2));
                    subject.setSubjectID(rs.getInt(3));
                    subject.setName(rs.getString(4));
                    subject.setInformation(rs.getString(5));
                    subject.setCategoryName(rs.getString(6));
                    subject.setThumbnail(rs.getString(7));
                    subject.setPricePackage(pricePackage);
                    subjectList.add(subject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return subjectList;
    }

    public ArrayList<Subject> getSubjectByName(String keyword) throws SQLException {
        ArrayList<Subject> subjectList = new ArrayList<>();
        String sql = "select salePrice, listPrice, pricepackage.subjectID, subject.name, subject.information, category_name, subject.thumbnail from pricepackage  inner join subject on subject.subjectID = pricepackage.subjectID inner join category on category.categoryID = subject.categoryID where salePrice=(select min(saleprice) from (select * from pricepackage where pricepackage.subjectID = subject.subjectID) as T) and subject.name like ?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    PricePackage pricePackage = new PricePackage();
                    Subject subject = new Subject();
                    pricePackage.setSalePrice(rs.getFloat(1));
                    pricePackage.setListPrice(rs.getFloat(2));
                    subject.setSubjectID(rs.getInt(3));
                    subject.setName(rs.getString(4));
                    subject.setInformation(rs.getString(5));
                    subject.setCategoryName(rs.getString(6));
                    subject.setThumbnail(rs.getString(7));
                    subject.setPricePackage(pricePackage);
                    subjectList.add(subject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return subjectList;
    }

    public ArrayList<Subject> getSubjectByName(String keyword, int start, int total) throws SQLException {
        ArrayList<Subject> subjectList = new ArrayList<>();
        String sql = "select salePrice, listPrice, pricepackage.subjectID, subject.name, subject.information, category_name, subject.thumbnail from pricepackage  inner join subject on subject.subjectID = pricepackage.subjectID inner join category on category.categoryID = subject.categoryID where salePrice=(select min(saleprice) from (select * from pricepackage where pricepackage.subjectID = subject.subjectID) as T) and subject.name like ? limit " + (start - 1) + "," + total;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    PricePackage pricePackage = new PricePackage();
                    Subject subject = new Subject();
                    pricePackage.setSalePrice(rs.getFloat(1));
                    pricePackage.setListPrice(rs.getFloat(2));
                    subject.setSubjectID(rs.getInt(3));
                    subject.setName(rs.getString(4));
                    subject.setInformation(rs.getString(5));
                    subject.setCategoryName(rs.getString(6));
                    subject.setThumbnail(rs.getString(7));
                    subject.setPricePackage(pricePackage);
                    subjectList.add(subject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return subjectList;
    }

    public ArrayList<Float> getPriceBySubjectID(int id) throws SQLException {
        ArrayList<Float> priceList = new ArrayList<>();
        String sql = "select listPrice, salePrice from subject inner join pricepackage on pricepackage.pricePackageID = subject.pricePackageID where subjectID=?";
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

    public Subject getSubjectById(int id) throws SQLException {
        Subject subject = new Subject();
        PricePackage pricePackage = new PricePackage();
        String sql = "select salePrice, listPrice, pricepackage.subjectID, subject.name, subject.information, category_name, subject.thumbnail from pricepackage  inner join subject on subject.subjectID = pricepackage.subjectID inner join category on category.categoryID = subject.categoryID where salePrice=(select min(saleprice) from (select * from pricepackage where pricepackage.subjectID = ?) as T) and subject.subjectID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setInt(2, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    pricePackage.setSalePrice(rs.getFloat(1));
                    pricePackage.setListPrice(rs.getFloat(2));
                    subject.setSubjectID(rs.getInt(3));
                    subject.setName(rs.getString(4));
                    subject.setInformation(rs.getString(5));
                    subject.setCategoryName(rs.getString(6));
                    subject.setThumbnail(rs.getString(7));
                    subject.setPricePackage(pricePackage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subject;
    }

    public ArrayList<Subject> getAllSubject() throws Exception {
        ArrayList<Subject> lst = new ArrayList<>();
        String sql = "SELECT * FROM subject";
        CategoryDAO cdao = new CategoryDAO();
        SubjectDimensionDAO ddao = new SubjectDimensionDAO();
        UserDAO udao = new UserDAO();
        LessonDAO ldao = new LessonDAO();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int subId = rs.getInt("subjectID");

                    int cateId = rs.getInt("categoryID");
                    Category cate = cdao.getCategoryById(cateId);

                    int dimenId = rs.getInt("dimensionID");
                    SubjectDimension dimen = ddao.getSubjectDimensionbyID(dimenId);

                    int userId = rs.getInt("userID");
                    User user = udao.getUserbyID(userId);

                    String thumbnail = rs.getString("thumbnail");
                    String name = rs.getString("name");
                    boolean featureflag = rs.getBoolean("featureflag");
                    boolean status = rs.getBoolean("status");
                    String information = rs.getString("information");
                    int lessons_num = ldao.getNumberOfLessonbySubjectID(subId);

                    Subject sub = new Subject(subId, thumbnail, name, featureflag, status, information, cate, dimen, user, lessons_num);
                    lst.add(sub);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<String> getAllSubjectName() throws SQLException {
        ArrayList<String> subjectNameList = new ArrayList<>();
        String sql = "select name from subject";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String subjectName = rs.getString(1);
                    subjectNameList.add(subjectName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return subjectNameList;
    }

    public ArrayList<Subject> getAllSubjectsByName(String sname) throws Exception {
        ArrayList<Subject> lst = new ArrayList<>();
        String sql = "select * from subject where name like ?";
        CategoryDAO cdao = new CategoryDAO();
        SubjectDimensionDAO ddao = new SubjectDimensionDAO();
        UserDAO udao = new UserDAO();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + sname + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int subId = rs.getInt("subjectID");

                    int cateId = rs.getInt("categoryID");
                    Category cate = cdao.getCategoryById(cateId);

                    int dimenId = rs.getInt("dimensionID");
                    SubjectDimension dimen = ddao.getSubjectDimensionbyID(dimenId);

                    int userId = rs.getInt("userID");
                    User user = udao.getUserbyID(userId);

                    String thumbnail = rs.getString("thumbnail");
                    String name = rs.getString("name");
                    boolean featureflag = rs.getBoolean("featureflag");
                    boolean status = rs.getBoolean("status");
                    String information = rs.getString("information");

                    Subject sub = new Subject(subId, thumbnail, name, featureflag, status, information, cate, dimen, user);
                    lst.add(sub);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Subject> getAllSubjectsByCategory(int scate) throws Exception {
        ArrayList<Subject> lst = new ArrayList<>();
        String sql = "select * from subject where categoryID = ?";
        CategoryDAO cdao = new CategoryDAO();
        SubjectDimensionDAO ddao = new SubjectDimensionDAO();
        UserDAO udao = new UserDAO();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, scate);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int subId = rs.getInt("subjectID");

                    int cateId = rs.getInt("categoryID");
                    Category cate = cdao.getCategoryById(cateId);

                    int dimenId = rs.getInt("dimensionID");
                    SubjectDimension dimen = ddao.getSubjectDimensionbyID(dimenId);

                    int userId = rs.getInt("userID");
                    User user = udao.getUserbyID(userId);

                    String thumbnail = rs.getString("thumbnail");
                    String name = rs.getString("name");
                    boolean featureflag = rs.getBoolean("featureflag");
                    boolean status = rs.getBoolean("status");
                    String information = rs.getString("information");

                    Subject sub = new Subject(subId, thumbnail, name, featureflag, status, information, cate, dimen, user);
                    lst.add(sub);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Subject> getAllSubjectsByStatus(boolean sstatus) throws Exception {
        ArrayList<Subject> lst = new ArrayList<>();
        String sql = "select * from subject where status = ?";
        CategoryDAO cdao = new CategoryDAO();
        SubjectDimensionDAO ddao = new SubjectDimensionDAO();
        UserDAO udao = new UserDAO();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, sstatus);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int subId = rs.getInt("subjectID");

                    int cateId = rs.getInt("categoryID");
                    Category cate = cdao.getCategoryById(cateId);

                    int dimenId = rs.getInt("dimensionID");
                    SubjectDimension dimen = ddao.getSubjectDimensionbyID(dimenId);

                    int userId = rs.getInt("userID");
                    User user = udao.getUserbyID(userId);

                    String thumbnail = rs.getString("thumbnail");
                    String name = rs.getString("name");
                    boolean featureflag = rs.getBoolean("featureflag");
                    boolean status = rs.getBoolean("status");
                    String information = rs.getString("information");

                    Subject sub = new Subject(subId, thumbnail, name, featureflag, status, information, cate, dimen, user);
                    lst.add(sub);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Subject> getAllSubjectsByFilter(boolean sstatus, int scate) throws Exception {
        ArrayList<Subject> lst = new ArrayList<>();
        String sql = "select * from subject where categoryID = ? and status = ?";
        CategoryDAO cdao = new CategoryDAO();
        SubjectDimensionDAO ddao = new SubjectDimensionDAO();
        UserDAO udao = new UserDAO();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, scate);
                ps.setBoolean(2, sstatus);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int subId = rs.getInt("subjectID");

                    int cateId = rs.getInt("categoryID");
                    Category cate = cdao.getCategoryById(cateId);

                    int dimenId = rs.getInt("dimensionID");
                    SubjectDimension dimen = ddao.getSubjectDimensionbyID(dimenId);

                    int userId = rs.getInt("userID");
                    User user = udao.getUserbyID(userId);

                    String thumbnail = rs.getString("thumbnail");
                    String name = rs.getString("name");
                    boolean featureflag = rs.getBoolean("featureflag");
                    boolean status = rs.getBoolean("status");
                    String information = rs.getString("information");

                    Subject sub = new Subject(subId, thumbnail, name, featureflag, status, information, cate, dimen, user);
                    lst.add(sub);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public boolean AddSubject(Subject sub) throws Exception {
        boolean result = false;
        String sql = "INSERT INTO Subject (dimensionID, categoryID, thumbnail, name, featureFlag, status, information, userID) \n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, 1);
                ps.setInt(2, sub.getCategory().getCategoryID());
                ps.setString(3, sub.getThumbnail());
                ps.setString(4, sub.getName());
                ps.setBoolean(5, sub.isFeatureFlag());
                ps.setBoolean(6, sub.isStatus());
                ps.setString(7, sub.getInformation());
                ps.setInt(8, sub.getUser().getUserID());

                ps.executeUpdate();
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public Subject getOneSubjectsById(int sid) throws Exception {
        Subject sub = null;
        String sql = "select * from subject where subjectID = ?";
        CategoryDAO cdao = new CategoryDAO();
        SubjectDimensionDAO ddao = new SubjectDimensionDAO();
        UserDAO udao = new UserDAO();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, sid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int subId = rs.getInt("subjectID");

                    int cateId = rs.getInt("categoryID");
                    Category cate = cdao.getCategoryById(cateId);

                    int dimenId = rs.getInt("dimensionID");
                    SubjectDimension dimen = ddao.getSubjectDimensionbyID(dimenId);

                    int userId = rs.getInt("userID");
                    User user = udao.getUserbyID(userId);

                    String thumbnail = rs.getString("thumbnail");
                    String name = rs.getString("name");
                    boolean featureflag = rs.getBoolean("featureflag");
                    boolean status = rs.getBoolean("status");
                    String information = rs.getString("information");

                    sub = new Subject(subId, thumbnail, name, featureflag, status, information, cate, dimen, user);

                }
            }
        } finally {
            closeConnection();
        }
        return sub;
    }

    public String getSubjectNameByID(int subID) throws SQLException {
        String subjectName = "";
        String sql = "select name from subject where subjectID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, subID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    subjectName = rs.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return subjectName;
    }

    public boolean changeSubject(Subject sub) throws Exception {
        String sql = "update subject set name=?, featureflag=?, status=?, information=?, categoryID=? where subjectID=?";
        boolean result = false;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);

                ps.setString(1, sub.getName());
                ps.setBoolean(2, sub.isFeatureFlag());
                ps.setBoolean(3, sub.isStatus());
                ps.setString(4, sub.getInformation());
                ps.setInt(5, sub.getCategory().getCategoryID());
                ps.setInt(6, sub.getSubjectID());

                ps.executeUpdate();
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    
    public ArrayList<Subject> getAllSubjectNameandID() throws Exception {
        ArrayList<Subject> lst = new ArrayList<>();
        String sql = "SELECT * FROM subject";
        CategoryDAO cdao = new CategoryDAO();
        SubjectDimensionDAO ddao = new SubjectDimensionDAO();
        UserDAO udao = new UserDAO();
        LessonDAO ldao = new LessonDAO();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int subId = rs.getInt("subjectID");
                    String name = rs.getString("name");
                    Subject sub = new Subject(subId, "", name, true, true, "", null, null, null, 0);
                    lst.add(sub);

                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Integer> getAllSubjectID() throws Exception {
        ArrayList<Integer> lst = new ArrayList<>();
        String sql = "SELECT subjectID FROM subject";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int subId = rs.getInt("subjectID");
                    lst.add(subId);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }
}
