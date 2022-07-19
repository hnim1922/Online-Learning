package daos;

import beans.Lesson;
import beans.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBConnection;

public class LessonDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public LessonDAO() {
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

    public int getNumberOfLessonbySubjectID(int id) throws Exception {
        int count = 0;
        String sql = "select * from lesson where subjectID=?";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    count = count + 1;
                }
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public ArrayList<Lesson> getLessonByCourseID(int courseID) throws Exception {
        String sql = "select * from lesson where courseID=?";
        ArrayList<Lesson> lessons = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, courseID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setLessonID(rs.getInt(1));
                    lesson.setLessonName(rs.getString(3));
                    lesson.setContent(rs.getString(2));
                    lesson.setStatus(rs.getBoolean(4));

                    lessons.add(lesson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lessons;
    }

    public Lesson getLessonByLessonIDAndSubjectID(int lesID, int courseID) throws Exception {
        String sql = "select * from lesson where lessonID=? and courseID=?";
        Lesson lesson = new Lesson();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, lesID);
                ps.setInt(2, courseID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    lesson.setLessonID(rs.getInt(1));
                    lesson.setLessonName(rs.getString(3));
                    lesson.setContent(rs.getString(2));
                    lesson.setStatus(rs.getBoolean(4));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lesson;
    }

    public int getNumOfLessonByCourseID(int courseID) throws SQLException {
        String sql = "select count(*) from lesson where courseID=?";
        int numOfLesson = 0;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, courseID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numOfLesson = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return numOfLesson;
    }

    public int updateStatus(int lessonID) throws SQLException {
        String sql = "update lesson set lessonStatus = 1 where lessonID=?";
        int status = 0;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, lessonID);
                status = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return status;
    }

    public boolean getStatus(int lessonID) throws SQLException {
        String sql = "select lessonStatus from lesson where lessonID=?";
        boolean lessonStatus = false;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, lessonID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    lessonStatus = rs.getBoolean(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lessonStatus;
    }

    public ArrayList<Integer> getLessonID() throws SQLException {
        String sql = "select lessonID from lesson order by lessonID;";
        ArrayList<Integer> lessonIDList = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    lessonIDList.add(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lessonIDList;
    }

    public ArrayList<Lesson> getAllLesson() throws SQLException {
        String sql = "select * from lesson";
        ArrayList<Lesson> lessonList = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setLessonID(rs.getInt("lessonID"));
                    lesson.setContent(rs.getString("content"));
                    lesson.setLessonName(rs.getString("lessonName"));

                    lessonList.add(lesson);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lessonList;
    }

    public Lesson getLessonByID(int lessonID) throws SQLException {
        String sql = "select * from lesson where lessonID=?";
        Lesson lesson = new Lesson();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, lessonID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    lesson.setLessonID(rs.getInt("lessonID"));
                    lesson.setContent(rs.getString("content"));
                    lesson.setLessonName(rs.getString("lessonName"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lesson;
    }

    public int updateLessonInfo(int lessonID, String lessonName, String content) throws SQLException {
        String sql = "update lesson set lessonName=?,  content=? where lessonID=?";
        int status = 0;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, lessonName);
                ps.setString(2, content);
                ps.setInt(3, lessonID);
                status = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return status;
    }

    public int addLesson(String content, String lessonName, int subjectID) throws SQLException {
        String sql = "insert into lesson (content, lessonName, subjectID) values (?, ?, ?)";
        int status = 0;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, content);
                ps.setString(2, lessonName);
                ps.setInt(3, subjectID);
                status = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return status;
    }

    DBConnection db_lesson = new DBConnection();

    public ArrayList<Lesson> getAllLessons() throws Exception {
        String sql = "select * from lesson";
        ArrayList<Lesson> lst = new ArrayList<>();
        SubjectDAO dao = new SubjectDAO();
        try {

            con = db_lesson.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int lessonID = rs.getInt("lessonID");
                    String content = rs.getString("content");
                    String lessonName = rs.getString("lessonName");

                    int subjectID = rs.getInt("subjectID");
                    Subject sub = dao.getSubjectById(subjectID);

                    int courseID = rs.getInt("courseID");
                    Lesson ls = new Lesson(lessonID, content, lessonName, sub, courseID);
                    lst.add(ls);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Lesson> getAllLessonsBySubject(int search_subjectID) throws Exception {
        String sql = "select * from lesson where subjectID=?";
        ArrayList<Lesson> lst = new ArrayList<>();
        SubjectDAO dao = new SubjectDAO();
        try {
            con = db_lesson.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, search_subjectID);

                rs = ps.executeQuery();
                while (rs.next()) {
                    int lessonID = rs.getInt("lessonID");
                    String content = rs.getString("content");
                    String lessonName = rs.getString("lessonName");

                    int subjectID = rs.getInt("subjectID");
                    Subject sub = dao.getSubjectById(subjectID);
                    int courseID = rs.getInt("courseID");
                    Lesson ls = new Lesson(lessonID, content, lessonName, sub, courseID);
                    lst.add(ls);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Lesson> getAllLessonsByName(String search_name) throws Exception {
        String sql = "select * from lesson where lessonName like ?";
        ArrayList<Lesson> lst = new ArrayList<>();
        SubjectDAO dao = new SubjectDAO();
        try {
            con = db_lesson.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + search_name + "%");

                rs = ps.executeQuery();
                while (rs.next()) {
                    int lessonID = rs.getInt("lessonID");
                    String content = rs.getString("content");
                    String lessonName = rs.getString("lessonName");

                    int subjectID = rs.getInt("subjectID");
                    Subject sub = dao.getSubjectById(subjectID);

                    int courseID = rs.getInt("courseID");
                    Lesson ls = new Lesson(lessonID, content, lessonName, sub, courseID);
                    lst.add(ls);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public Lesson getALessonByID(int id) throws Exception {
        String sql = "select * from lesson where lessonID=?";
        Lesson ls = new Lesson();
        try {
            con = db_lesson.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int lid = rs.getInt("lessonID");
                    String content = rs.getString("content");
                    String lessonName = rs.getString("lessonName");
                    int sid = rs.getInt("SubjectID");
                    
                    SubjectDAO sdao = new SubjectDAO();
                    Subject sub = sdao.getSubjectById(sid);
                    
                    ls = new Lesson(lid, content, lessonName, sub, 0);
                }
            }
        } finally {
            closeConnection();
        }
        return ls;
    }

    public boolean addANewLesson(String lsContent, String lsName, int lsSubject) throws Exception {
        String sql = "insert into lesson (content, lessonName, subjectID)"
                + "values (?, ?, ?)";
        boolean result = false;
        try {
            con = db_lesson.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, lsContent);
                ps.setString(2, lsName);
                ps.setInt(3, lsSubject);

                ps.executeUpdate();
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean ChangeLesson(String lsName, String lsContentChange, String lsNameChange, int lsSubjectChange) throws Exception{
        String sql = "update lesson set lessonName =?, content = ?, subjectID=? where lessonName=?";
        boolean result = false;
        try{
            con = db_lesson.getConnection();
            if(con!=null){
                ps = con.prepareStatement(sql);
                ps.setString(1, lsNameChange);
                ps.setString(2, lsContentChange);
                ps.setInt(3, lsSubjectChange);
                ps.setString(4, lsName);
                
                ps.executeUpdate();
                result = true;
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<String> getAllLessonsName() throws Exception {
        String sql = "select lessonName from lesson";
        ArrayList<String> lst = new ArrayList<>();
        SubjectDAO dao = new SubjectDAO();
        try {

            con = db_lesson.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String lessonName = rs.getString("lessonName");
                    lst.add(lessonName);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }
}
