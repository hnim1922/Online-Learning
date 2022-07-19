/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnection;

/**
 *
 * @author acer
 */
public class ScoreDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ScoreDAO() {
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

    public boolean insertHighScore(String phone, int quizID) throws SQLException {
        String sql = "INSERT into Score values(?,?,?)";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, phone);
                ps.setInt(2, quizID);
                ps.setString(3, "0");
                ps.executeUpdate();
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public String updateHighScore(String phone, int quizID, String score) throws SQLException {
        String currentHighScore = getHighScore(phone, quizID);
//        System.out.println("xx");
//        System.out.println(Integer.parseInt(currentHighScore));
//        System.out.println(Integer.parseInt(score));
        if (Integer.parseInt(currentHighScore) >= Integer.parseInt(score)) {
            return currentHighScore;
        }
        String sql = "Update Score Set score=? Where phone=? and quizID=?";
        System.out.println(phone);
        System.out.println(quizID);
        System.out.println(score);
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps= con.prepareStatement(sql);
                ps.setString(1, score);
                ps.setString(2, phone);
                ps.setInt(3, quizID);

                ps.executeUpdate();

                return score;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return currentHighScore;
    }

    public String getHighScore(String phone, int quizID) throws SQLException {
        String sql = "SELECT score FROM Score WHERE phone=? and  quizID=?";
        String res = "0";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, phone);
                ps.setInt(2, quizID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    res = rs.getString("score").trim();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
}
