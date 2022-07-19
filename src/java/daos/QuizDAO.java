/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;


import beans.Question;
import beans.Quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

/**
 *
 * @author acer
 */
public class QuizDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public QuizDAO() {
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
  
  public Quiz getQuizbyID(int id) throws SQLException
    {
        Quiz quiz = new Quiz();
        String sql= "select * from quiz where quizID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    quiz.setLessonID(rs.getInt(2));
                }
    }  
            }catch(Exception ex) 
                    {
                    ex.printStackTrace();
                    } finally {
                            closeConnection();

                        }return quiz;
    }
    


    public ArrayList<Quiz> getQuizByName(String keyword) throws SQLException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = "select * from quiz inner join lesson on quiz.lessonID=lesson.lessonID inner join subject on lesson.subjectID=subject.subjectID where quizName like ?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizID(rs.getInt("quizID"));
                    quiz.setLessonID(rs.getInt("lessonID"));
                    quiz.setQuizName(rs.getString("quizName"));
                    quiz.setLevel(rs.getString("level"));
                    quiz.setNumOfQuestion(rs.getInt("numOfQuestion"));
                    quiz.setDuration(rs.getInt("duration"));
                    quiz.setPassRate(rs.getFloat("passRate"));
                    quiz.setQuizType(rs.getString("quizType"));
                    quiz.setSubjectName(rs.getString("name"));
                    quiz.setIsTaken(rs.getBoolean("isTaken"));
                    quizzes.add(quiz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return quizzes;
    }

    public ArrayList<Quiz> getQuizByNamePaginated(String keyword, int start, int total) throws SQLException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = "select * from quiz inner join lesson on quiz.lessonID=lesson.lessonID inner join subject on lesson.subjectID=subject.subjectID where quizName like ? limit " + (start - 1) + "," + total;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizID(rs.getInt("quizID"));
                    quiz.setLessonID(rs.getInt("lessonID"));
                    quiz.setQuizName(rs.getString("quizName"));
                    quiz.setLevel(rs.getString("level"));
                    quiz.setNumOfQuestion(rs.getInt("numOfQuestion"));
                    quiz.setDuration(rs.getInt("duration"));
                    quiz.setPassRate(rs.getFloat("passRate"));
                    quiz.setQuizType(rs.getString("quizType"));
                    quiz.setSubjectName(rs.getString("name"));
                    quiz.setIsTaken(rs.getBoolean("isTaken"));
                    quizzes.add(quiz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return quizzes;
    }

    public ArrayList<Quiz> getQuizBySubject(String keyword) throws SQLException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = "select * from quiz inner join lesson on quiz.lessonID=lesson.lessonID inner join subject on lesson.subjectID=subject.subjectID where subject.name=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, keyword);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizID(rs.getInt("quizID"));
                    quiz.setLessonID(rs.getInt("lessonID"));
                    quiz.setQuizName(rs.getString("quizName"));
                    quiz.setLevel(rs.getString("level"));
                    quiz.setNumOfQuestion(rs.getInt("numOfQuestion"));
                    quiz.setDuration(rs.getInt("duration"));
                    quiz.setPassRate(rs.getFloat("passRate"));
                    quiz.setQuizType(rs.getString("quizType"));
                    quiz.setSubjectName(rs.getString("name"));
                    quiz.setIsTaken(rs.getBoolean("isTaken"));
                    quizzes.add(quiz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return quizzes;
    }

    public ArrayList<Quiz> getQuizBySubjectPaginated(String keyword, int start, int total) throws SQLException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = "select * from quiz inner join lesson on quiz.lessonID=lesson.lessonID inner join subject on lesson.subjectID=subject.subjectID where subject.name=? limit " + (start - 1) + "," + total;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, keyword);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizID(rs.getInt("quizID"));
                    quiz.setLessonID(rs.getInt("lessonID"));
                    quiz.setQuizName(rs.getString("quizName"));
                    quiz.setLevel(rs.getString("level"));
                    quiz.setNumOfQuestion(rs.getInt("numOfQuestion"));
                    quiz.setDuration(rs.getInt("duration"));
                    quiz.setPassRate(rs.getFloat("passRate"));
                    quiz.setQuizType(rs.getString("quizType"));
                    quiz.setSubjectName(rs.getString("name"));
                    quiz.setIsTaken(rs.getBoolean("isTaken"));
                    quizzes.add(quiz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return quizzes;
    }

    public ArrayList<Quiz> getQuizByType(String keyword) throws SQLException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = "select * from quiz inner join lesson on quiz.lessonID=lesson.lessonID inner join subject on lesson.subjectID=subject.subjectID where quizType=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, keyword);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizID(rs.getInt("quizID"));
                    quiz.setLessonID(rs.getInt("lessonID"));
                    quiz.setQuizName(rs.getString("quizName"));
                    quiz.setLevel(rs.getString("level"));
                    quiz.setNumOfQuestion(rs.getInt("numOfQuestion"));
                    quiz.setDuration(rs.getInt("duration"));
                    quiz.setPassRate(rs.getFloat("passRate"));
                    quiz.setQuizType(rs.getString("quizType"));
                    quiz.setSubjectName(rs.getString("name"));
                    quiz.setIsTaken(rs.getBoolean("isTaken"));
                    quizzes.add(quiz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return quizzes;
    }

    public ArrayList<Quiz> getQuizByTypePaginated(String keyword, int start, int total) throws SQLException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = "select * from quiz inner join lesson on quiz.lessonID=lesson.lessonID inner join subject on lesson.subjectID=subject.subjectID where quizType=? limit " + (start - 1) + "," + total;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, keyword);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizID(rs.getInt("quizID"));
                    quiz.setLessonID(rs.getInt("lessonID"));
                    quiz.setQuizName(rs.getString("quizName"));
                    quiz.setLevel(rs.getString("level"));
                    quiz.setNumOfQuestion(rs.getInt("numOfQuestion"));
                    quiz.setDuration(rs.getInt("duration"));
                    quiz.setPassRate(rs.getFloat("passRate"));
                    quiz.setQuizType(rs.getString("quizType"));
                    quiz.setSubjectName(rs.getString("name"));
                    quiz.setIsTaken(rs.getBoolean("isTaken"));
                    quizzes.add(quiz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return quizzes;
    }

    public ArrayList<String> getQuizType() throws SQLException {
        ArrayList<String> quizTypes = new ArrayList<>();
        String sql = "select distinct(quizType) from quiz;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    quizTypes.add(rs.getString(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return quizTypes;
    }

    public Quiz getQuizbyID1(int id) throws SQLException {
        Quiz quiz = new Quiz();
        String sql = "select * from quiz inner join lesson on quiz.lessonID=lesson.lessonID inner join subject on lesson.subjectID=subject.subjectID where quizID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    quiz.setQuizID(rs.getInt("quizID"));
                    quiz.setLessonID(rs.getInt("lessonID"));
                    quiz.setQuizName(rs.getString("quizName"));
                    quiz.setLevel(rs.getString("level"));
                    quiz.setNumOfQuestion(rs.getInt("numOfQuestion"));
                    quiz.setDuration(rs.getInt("duration"));
                    quiz.setPassRate(rs.getFloat("passRate"));
                    quiz.setQuizType(rs.getString("quizType"));
                    quiz.setSubjectName(rs.getString("name"));
                    quiz.setIsTaken(rs.getBoolean("isTaken"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();

        }
        return quiz;
    }

    public ArrayList<Quiz> getAllQuizByLessonID(int lessonID) throws SQLException {
        ArrayList<Quiz> quizList = new ArrayList<>();
        String sql = "select * from quiz where lessonID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, lessonID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizID(rs.getInt(1));
                    quiz.setLessonID(rs.getInt(2));

                    quizList.add(quiz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return quizList;
    }

    public ArrayList<Quiz> getAllQuiz() throws SQLException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = "select * from quiz inner join lesson on quiz.lessonID=lesson.lessonID inner join subject on lesson.subjectID=subject.subjectID;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizID(rs.getInt("quizID"));
                    quiz.setLessonID(rs.getInt("lessonID"));
                    quiz.setQuizName(rs.getString("quizName"));
                    quiz.setLevel(rs.getString("level"));
                    quiz.setNumOfQuestion(rs.getInt("numOfQuestion"));
                    quiz.setDuration(rs.getInt("duration"));
                    quiz.setPassRate(rs.getFloat("passRate"));
                    quiz.setQuizType(rs.getString("quizType"));
                    quiz.setSubjectName(rs.getString("name"));
                    quiz.setIsTaken(rs.getBoolean("isTaken"));
                    quizzes.add(quiz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return quizzes;
    }

    public ArrayList<Quiz> getQuizPaginated(int start, int total) throws SQLException {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = "select * from quiz inner join lesson on quiz.lessonID=lesson.lessonID inner join subject on lesson.subjectID=subject.subjectID limit " + (start - 1) + "," + total;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setQuizID(rs.getInt("quizID"));
                    quiz.setLessonID(rs.getInt("lessonID"));
                    quiz.setQuizName(rs.getString("quizName"));
                    quiz.setLevel(rs.getString("level"));
                    quiz.setNumOfQuestion(rs.getInt("numOfQuestion"));
                    quiz.setDuration(rs.getInt("duration"));
                    quiz.setPassRate(rs.getFloat("passRate"));
                    quiz.setQuizType(rs.getString("quizType"));
                    quiz.setSubjectName(rs.getString("name"));
                    quiz.setIsTaken(rs.getBoolean("isTaken"));
                    quizzes.add(quiz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return quizzes;
    }

    public void uncheckFK() throws SQLException {
        String sql = "set foreign_key_checks=0;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkFK() throws SQLException {
        String sql = "set foreign_key_checks=1;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int deleteQuizByID(int quizID) throws SQLException {
        int status = 0;
        String sql = "delete from quiz where quizID=?;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                uncheckFK();
                ps = con.prepareStatement(sql);
                ps.setInt(1, quizID);
                status = ps.executeUpdate();
                checkFK();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return status;
    }

    protected int getNumOfQuestion(int quizID) throws SQLException {
        int numOfQuestion = 0;
        String sql = "select @numOfQuestion := count(questionID) from question where quizID=?;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, quizID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    numOfQuestion = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return numOfQuestion;
    }

    public int updateQuizInfo(int quizID, int lessonID, String quizName, String level, int duration, float passRate, String quizType) throws SQLException {
        int status = 0;
        int numOfQuestion = getNumOfQuestion(quizID);
        String sql = "update quiz set lessonID=?, quizName=?, level=?, numOfQuestion=?, duration=?, passRate=?, quizType=? where quizID=?;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, lessonID);
                ps.setString(2, quizName);
                ps.setString(3, level);
                ps.setInt(4, numOfQuestion);
                ps.setInt(5, duration);
                ps.setFloat(6, passRate);
                ps.setString(7, quizType);
                ps.setInt(8, quizID);
                status = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return status;
    }

    public int addQuiz(int lessonID, String quizName, String level, int duration, float passRate, String quizType) throws SQLException {
        int status = 0;
        String sql = "insert into quiz(lessonID, quizName, level, duration, passRate, quizType) values (?, ?, ?, ?, ?, ?);";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, lessonID);
                ps.setString(2, quizName);
                ps.setString(3, level);
                ps.setInt(4, duration);
                ps.setFloat(5, passRate);
                ps.setString(6, quizType);

                status = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return status;
    }
    
    public List<Quiz> getQuizName() throws SQLException{
        List<Quiz> list = new ArrayList();
        String sql = "select quizID, quizName from quiz;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            
            if (con != null){
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery(); 
                
                while(rs.next()){
                    int quizID = rs.getInt("quizID");
                    String quizName = rs.getString("quizName");
                    Quiz quiz = new Quiz(quizID, quizName);
                    
                    list.add(quiz);
                }
            }
        } catch(Exception e){
            System.out.println("getQuizName");
        } finally {
            closeConnection();
        }
        return list;
    }
}
