package daos;

import beans.Answer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBConnection;
import java.util.List;

public class AnswerDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;


    public AnswerDAO() {
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

    public ArrayList<Answer> getAllAnswer() throws SQLException {
        ArrayList<Answer> answerList = new ArrayList<>();
        String sql = "select answerID, answer.questionID, result, answer.content from answer";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int aid = rs.getInt("answerID");
                    int qid = rs.getInt("questionID");
                    boolean result = rs.getBoolean("result");
                    String content = rs.getString("content");
                    Answer answer = new Answer(aid, qid, result, content);
                    answerList.add(answer);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();

        }
        return answerList;
    }

    public ArrayList<Answer> getAllCorrectAnswer() throws SQLException {
        ArrayList<Answer> correctList = new ArrayList<>();
        String sql = "select answerID, answer.questionID, result, answer.content from answer where result =1;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int aid = rs.getInt("answerID");
                    int qid = rs.getInt("questionID");
                    boolean result = rs.getBoolean("result");
                    String content = rs.getString("content");
                    Answer answer = new Answer(aid, qid, result, content);
                    correctList.add(answer);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();

        }
        return correctList;
    }
    
    public String getCorrectAnswer(int questionID) throws SQLException {
        String correctAns = "";
        String sql = "select content from answer where questionID=? and result=1";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    correctAns = rs.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return correctAns;
    }
    
    public List<Answer> getAnswerByQuestionID(int questionID) throws SQLException {
        List<Answer> list = new ArrayList<>();
        String sql = "select * from answer where questionID = ?;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, questionID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int answerID = rs.getInt("answerID");
                    int result = rs.getInt("result");
                    String content = rs.getString("content");

                    Answer answer = new Answer(answerID, questionID, result, content);
                    list.add(answer);
                }
            }
        } catch (Exception e) {
            System.out.println("getAnswerByQuestionID :: " + e);
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean addNewAnswerByQuestionID(Answer answer) throws SQLException {
        boolean result = false;
        String sql = "insert into answer(questionID, result, content) values (?, ?,?);";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, answer.getQuestionID());
                ps.setInt(2, answer.getResult());
                ps.setString(3, answer.getContent());

                int iCount = ps.executeUpdate();
                if (iCount != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("addNewAnswerByQuestionID :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean editAnswer(Answer answer) throws SQLException {
        boolean result = false;
        String sql = "update answer\n"
                + "set result = ?, content = ?\n"
                + "where answerID = ?;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, answer.getResult());
                ps.setString(2, answer.getContent());
                ps.setInt(3, answer.getAnswerID());

                int iCount = ps.executeUpdate();
                if (iCount != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("editAnswer :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean deleteAnswer(int answerID) throws SQLException{
        boolean result = false;
        String sql = "delete from answer where answerID =?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            
            if (con != null){
                ps = con.prepareStatement(sql);
                ps.setInt(1, answerID);
                
                int iCount = ps.executeUpdate();
                if (iCount != 0){
                    result = true;
                }
            }
        } catch (Exception e){
            System.out.println("deleteAnswer :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }
}
