/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Question;
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
public class QuestionDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public QuestionDAO() {
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

    public ArrayList<Question> getQuestionbyQuizID(int id) throws SQLException{ 
        ArrayList<Question> questionList = new ArrayList<>();
        String sql= "select   question.questionID, question.quizID, question.content from question inner join quiz on question.quizID=quiz.quizID where quiz.quizID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int qid = rs.getInt("questionID");
                    int qid2 = rs.getInt("quizID");
                    String content = rs.getString("content");
                    Question question = new Question(qid, qid2, content);
                    questionList.add(question);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        finally {
            closeConnection();

        }
        return questionList;
    }

//    public ArrayList<Question> getQuestionbyQuizID(int id) throws SQLException {
//        ArrayList<Question> questionList = new ArrayList<>();
//        String sql = "select   question.questionID, question.quizID, question.content, question.level, question.lessonName, question.subjectName, question.dimensionName from question inner join quiz on question.quizID=quiz.quizID where quiz.quizID=?";
//        try {
//            DBConnection db = new DBConnection();
//            ps.setInt(1, id);
//
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int questionID = rs.getInt("questionID");
//                int quizID = rs.getInt("quizID");
//                String content = rs.getString("content");
//                String level = rs.getString("level");
//                String lessonName = rs.getString("lessonName");
//                String subjectName = rs.getString("subjectName");
//                String dimensionName = rs.getString("dimensionName");
//
//                Question question = new Question(questionID, quizID, content, level, lessonName, subjectName,
//                        dimensionName);
//                questionList.add(question);
//            }
//        } catch (Exception e) {
//            System.out.println("getQuestionPaging :: " + e);
//        } finally {
//            closeConnection();
//        }
//
//        return questionList;
//    }

    public List<Question> getQuestionPaging(int index) throws SQLException {
        String sql = "select T.*, s.name as subjectName, d.name as dimensionName, l.lessonName from \n"
                + "(select question.*, quiz.level, row_number() over (order by questionID) as row_num  from question, quiz where quiz.quizID = question.quizID ) as T, lesson l, subject s, subjectdimension d  \n"
                + "where l.subjectID = s.subjectID and s.dimensionID = d.dimensionID and row_num between (? * 3 - 2) and (? * 3) group by questionID ;";
        List<Question> list = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, index);
                ps.setInt(2, index);

                rs = ps.executeQuery();
                while (rs.next()) {
                    int questionID = rs.getInt("questionID");
                    int quizID = rs.getInt("quizID");
                    String content = rs.getString("content");
                    String level = rs.getString("level");
                    String lessonName = rs.getString("lessonName");
                    String subjectName = rs.getString("subjectName");
                    String dimensionName = rs.getString("dimensionName");

                    Question question = new Question(questionID, quizID, content, level, lessonName, subjectName,
                            dimensionName);
                    list.add(question);
                }
            }
        } catch (Exception e) {
            System.out.println("getQuestionPaging :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public int getTotal() throws SQLException {
        String sql = "select count(*) as total from question;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("getTotal :: " + e);
        } finally {
            closeConnection();
        }
        return 0;
    }

    public int getCountSearch(String txt) throws Exception {
        String sql = "select count(*) as total from question where content like ?";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("getCountSearch :: " + e);
        } finally {
            closeConnection();
        }
        return 0;
    }

    public List<Question> getQuestionPaging(int index, String txt) throws SQLException {
        String sql = "select T.*, s.name as subjectName, d.name as dimensionName, l.lessonName from \n"
                + "(select question.*, quiz.level, row_number() over (order by questionID) as row_num  from question, quiz where quiz.quizID = question.quizID and content like ?) as T, lesson l, subject s, subjectdimension d  \n"
                + "where l.subjectID = s.subjectID and s.dimensionID = d.dimensionID and row_num between (? * 3 - 2) and (? * 3) group by questionID ;";
        List<Question> list = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + txt + "%");
                ps.setInt(2, index);
                ps.setInt(3, index);

                rs = ps.executeQuery();
                while (rs.next()) {
                    int questionID = rs.getInt("questionID");
                    int quizID = rs.getInt("quizID");
                    String content = rs.getString("content");
                    String level = rs.getString("level");
                    String lessonName = rs.getString("lessonName");
                    String subjectName = rs.getString("subjectName");
                    String dimensionName = rs.getString("dimensionName");

                    Question question = new Question(questionID, quizID, content, level, lessonName, subjectName, dimensionName);
                    list.add(question);
                }
            }
        } catch (Exception e) {
            System.out.println("getQuestionPaging :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public boolean addQuestion(int quizID, String content) throws SQLException {
        boolean result = false;
        String sql = "insert into question(quizID, content) values (?, ?)";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, quizID);
                ps.setString(2, content);

                int iCount = ps.executeUpdate();
                if (iCount != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("addQuestion :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public Question getQuestion(int questionID) throws SQLException {
        Question question = null;
        String sql = "select T.*, s.name as subjectName, d.name as dimensionName, l.lessonName from \n"
                + "(select question.*, quiz.level from question, quiz where quiz.quizID = question.quizID and question.questionID = ?) as T, lesson l, subject s, subjectdimension d  \n"
                + "where l.subjectID = s.subjectID and s.dimensionID = d.dimensionID group by questionID;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    int quizID = rs.getInt("quizID");
                    String content = rs.getString("content");
                    String level = rs.getString("level");
                    String lessonName = rs.getString("lessonName");
                    String subjectName = rs.getString("subjectName");
                    String dimensionName = rs.getString("dimensionName");
                    question = new Question(questionID, quizID, content, level, lessonName, subjectName, dimensionName);
                }
            }
        } catch (Exception e) {
            System.out.println("getQuestion :: " + e);
        } finally {
            closeConnection();
        }
        return question;
    }

    public boolean editQuestion(int questionID, String content) throws SQLException {
        boolean result = false;
        String sql = "update question\n" + "set content = ?\n" + "where questionID = ?;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, content);
                ps.setInt(2, questionID);

                int iCount = ps.executeUpdate();
                if (iCount != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("editQuestion :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteQuestion(int questionID) throws SQLException {
        boolean result = false;
        String sql = "delete from question where questionID = ?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);

                int iCount = ps.executeUpdate();

                if (iCount != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("deleteQuestion :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Question> getQuestionID() throws SQLException{
        List<Question> list = new ArrayList<>();
        String sql = "select questionID from question";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            
            if (con != null){
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    int questionID = rs.getInt("questionID");
                    Question question = new Question(questionID);
                    list.add(question);
                }
            }
        } catch (Exception e){
            System.out.println("getQuestionID :: " + e);
        } finally {
            closeConnection();
        }
        return list;
    }
}
