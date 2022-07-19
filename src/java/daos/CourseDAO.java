/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Course;
import beans.PricePackage;
import beans.Subject;
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
public class CourseDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public CourseDAO() {
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

    public boolean createCourse(Course course) throws SQLException {
        String sql = "insert into course (subjectID, registrationID, title, tagline, briefInfo, status) values (?, ?, ?, ?,?, 0);";
        boolean result = false;
        int subID = course.getSubjectID();
        int regisID = course.getRegistrationID();
        String title = course.getTitle();
        String tagline = course.getTagline();
        String briefInfo = course.getBriefInfo();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, subID);
                ps.setInt(2, regisID);
                ps.setString(3, title);
                ps.setString(4, tagline);
                ps.setString(5, briefInfo);

                int iCount = ps.executeUpdate();
                if (iCount != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {

        } finally {
            closeConnection();
        }
        return result;
    }
  
    public List<Course> getCourseFromRegistration (int regisID) throws SQLException{
        List<Course> list  = new ArrayList<>();
        String sql = "select distinct c.*, s.thumbnail, p.pricepackageID, p.salePrice from course c, subject s, pricepackage p where registrationID = ? and c.subjectID = s.subjectID and p.subjectID = s.subjectID group by courseID;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, regisID);
            rs = ps.executeQuery();

            while (rs.next()){
                int courseID = rs.getInt("courseID");
                int subjectID = rs.getInt("subjectID");
                String title = rs.getString("title");
                String tagline = rs.getString("tagline");
                String briefInfo = rs.getString("briefInfo");
                float status = rs.getFloat("status");
                String thumbnail = rs.getString("thumbnail");
                int pricePackageID = rs.getInt("pricePackageID");
                float salePrice = rs.getFloat("salePrice");
                PricePackage pricePackage = new PricePackage(pricePackageID, salePrice);
                Subject subject = new Subject(subjectID, pricePackage, thumbnail);
                
                Course course = new Course(courseID, subjectID, regisID, title, tagline, briefInfo, status, subject);
                list.add(course);
            }
        } catch(Exception e) {
            System.out.println("getCourseFromRegistration :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public List<Course> getFullCourseFromRegistration (int regisID) throws SQLException{
        List<Course> list  = new ArrayList<>();
        String sql = "select distinct c.*, s.thumbnail, p.pricepackageID, p.salePrice, p.listPrice from course c, subject s, pricepackage p where registrationID = ? and c.subjectID = s.subjectID and p.subjectID = s.subjectID group by courseID;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, regisID);
            rs = ps.executeQuery();

            while (rs.next()){
                int courseID = rs.getInt("courseID");
                int subjectID = rs.getInt("subjectID");
                String title = rs.getString("title");
                String tagline = rs.getString("tagline");
                String briefInfo = rs.getString("briefInfo");
                float status = rs.getFloat("status");
                String thumbnail = rs.getString("thumbnail");
                int pricePackageID = rs.getInt("pricePackageID");
                float salePrice = rs.getFloat("salePrice");
                PricePackage pricePackage = new PricePackage(pricePackageID, salePrice);
                Subject subject = new Subject(subjectID, pricePackage, thumbnail);
                
                Course course = new Course(courseID, subjectID, regisID, title, tagline, briefInfo, status, subject);
                list.add(course);
            }
        } catch(Exception e) {
            System.out.println("getCourseFromRegistration :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }
    
    public boolean deleteAllCourse(int regisID) throws SQLException{
        boolean result = false;
        String sql = "delete from course where registrationID = ?;";
        
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null){
                ps = con.prepareStatement(sql);
                ps.setInt(1, regisID);

                int iCount = ps.executeUpdate();

                if (iCount != 0){
                    result = true;
                }
            }
        } catch(Exception e){
            System.out.println("deleteAllCourse :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }


    public boolean deleteCourse (int courseID) throws SQLException{
        boolean result = false;
        String sql = "delete from course where courseID = ?";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null){
                ps = con.prepareStatement(sql);
                ps.setInt(1, courseID);

                int iCount = ps.executeUpdate();

                if (iCount != 0){
                    result = true;
                }
            }
        } catch(Exception e){
            System.out.println("deleteCourse :: "  + e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<Course> getPaidCourse(int start, int total) throws SQLException {
        String sql = "select * from course a inner join registration b on a.registrationID = b.registrationID inner join subject c on a.subjectID = c.subjectID inner join category d on c.categoryID = d.categoryID where b.status = 1 limit " + (start - 1) + "," + total;
        ArrayList<Course> paidCourseList = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Subject subject = new Subject();
                    Course course = new Course();
                    subject.setSubjectID(rs.getInt(2));
                    subject.setName(rs.getString(18));
                    subject.setInformation(rs.getString(21));
                    subject.setCategoryName(rs.getString(23));
                    subject.setThumbnail(rs.getString(17));
                    course.setCourseID(rs.getInt(1));
                    course.setSubject(subject);
                    course.setStatus(rs.getFloat(7));

                    paidCourseList.add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return paidCourseList;
    }

    public ArrayList<Course> getAllPaidCourse() throws SQLException {
        String sql = "select * from course a inner join registration b on a.registrationID = b.registrationID inner join subject c on a.subjectID = c.subjectID inner join category d on c.categoryID = d.categoryID where b.status = 1";
        Subject subject = new Subject();
        ArrayList<Course> paidCourseList = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Course course = new Course();
                    subject.setSubjectID(rs.getInt(2));
                    subject.setName(rs.getString(18));
                    subject.setInformation(rs.getString(21));
                    subject.setCategoryName(rs.getString(23));
                    subject.setThumbnail(rs.getString(17));
                    course.setSubject(subject);
                    course.setStatus(rs.getFloat(7));

                    paidCourseList.add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return paidCourseList;

    }

    public int updateStatus(int courseID, int subjectID) throws SQLException {
        String sql = "update course set status = status + 1/? where courseID=?";
        int status = 0;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                LessonDAO lessonDAO = new LessonDAO();
                ps.setInt(1, lessonDAO.getNumOfLessonByCourseID(courseID));
                ps.setInt(2, courseID);
                status = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return status;
    }
}