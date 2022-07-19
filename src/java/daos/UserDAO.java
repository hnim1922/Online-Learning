/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Quiz;
import beans.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBConnection;
import utils.SendingEmail;

/**
 *
 * @author ihtng
 */
public class UserDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserDAO() {
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

    public User getUserByEmail(String email) throws Exception {
        User user = new User();
        String sql = "select * from user where email=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    user.setUserID(rs.getInt(1));
                    user.setRoleID(rs.getString(2));
                    user.setSettingID(rs.getInt(3));
                    user.setEmail(rs.getString(4));
                    user.setPassword(rs.getString(5));
                    user.setGender(rs.getString(6));
                    user.setFullname(rs.getString(7));
                    user.setPhone(rs.getString(8));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return user;
    }

    public int updatePassword(String password, String email) throws Exception {
        int status = 0;
        String sql = "update user set password=?  where email=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, email);
                status = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return status;
    }

    public boolean isEmailExist(String email) throws Exception {
        String sql = "select email from user where email=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public void resetPasswordToNull(String email) throws Exception {
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement("update user set password = null where email=?");
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void changeNullPassword(String password) throws Exception {
        String sql = "update user set password=?  where password is null";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public boolean createAccount(User user) throws SQLException {
        String roleID = user.getRoleID();
        int setting = user.getSettingID();
        String email = user.getEmail();
        String password = user.getPassword();
        String gender = user.getGender();
        String fullname = user.getFullname();
        String phone = user.getPhone();
        String hash = user.getHash();

        boolean result = false;
        String sql = "INSERT INTO user (roleID, settingID, email, password, gender, fullname, phone, hash) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, roleID);
                ps.setInt(2, setting);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setString(5, gender);
                ps.setString(6, fullname);
                ps.setString(7, phone);
                ps.setString(8, hash);
                int iCount = ps.executeUpdate();
                if (iCount != 0) {
                    SendingEmail se = new SendingEmail(email, hash);
                    se.sendMail(password);
                    result = true;
                }
                ScoreDAO scoreDAO = new ScoreDAO();
                QuizDAO quizDAO = new QuizDAO();
                ArrayList<Quiz> quizList = quizDAO.getAllQuiz();
                for (Quiz quiz : quizList) {
                    scoreDAO.insertHighScore(user.getPhone(), quiz.getQuizID());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public User checkLogin(String email, String password) throws Exception {
        User user = null;
        String sql = "select * from user where email=? AND password=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String roleID = rs.getString("roleID");
                    int userID = Integer.parseInt(rs.getString("userID"));
                    email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    user = new User(userID, roleID, email, password, gender, fullname, phone);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return user;
    }

    public boolean updateUser(User u) throws Exception {
        String sql = "UPDATE user SET email=?, gender=?,"
                + "fullname=?, phone=? WHERE userID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, u.getEmail());
                ps.setString(2, u.getGender());
                ps.setString(3, u.getFullname());
                ps.setString(4, u.getPhone());
                ps.setInt(5, u.getUserID());

                ps.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<User> getAllUsers() throws Exception {
        String sql = "select * from user";

        ArrayList<User> lst = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int uid = rs.getInt("userID");
                    String rid = rs.getString("roleID");
                    int sid = rs.getInt("settingID");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String hash = rs.getString("hash");
                    String password = rs.getString("password");

                    User user = new User(uid, rid, sid, email, password, gender, fullname, phone, hash);

                    lst.add(user);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public User getUserbyID(int id) throws Exception {
        String sql = "select * from user where userID=?";
        User user = null;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int uid = rs.getInt("userID");
                    String rid = rs.getString("roleID");
                    int sid = rs.getInt("settingID");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String hash = rs.getString("hash");
                    String password = rs.getString("password");

                    user = new User(uid, rid, sid, email, password, gender, fullname, phone, hash);
                }
            }
        } finally {
            closeConnection();
        }
        return user;
    }

    public ArrayList<User> getAllUserbyName(String name) throws Exception {
        String sql = "select * from user where fullname like ?";
        ArrayList<User> lst = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int uid = rs.getInt("userID");
                    String rid = rs.getString("roleID");
                    int sid = rs.getInt("settingID");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String hash = rs.getString("hash");
                    String password = rs.getString("password");

                    User user = new User(uid, rid, sid, email, password, gender, fullname, phone, hash);

                    lst.add(user);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<User> getAllUserbyEmail(String semail) throws Exception {
        String sql = "select * from user where email like ?";
        ArrayList<User> lst = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + semail + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int uid = rs.getInt("userID");
                    String rid = rs.getString("roleID");
                    int sid = rs.getInt("settingID");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String hash = rs.getString("hash");
                    String password = rs.getString("password");

                    User user = new User(uid, rid, sid, email, password, gender, fullname, phone, hash);

                    lst.add(user);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<User> getAllUserbyPhone(String sphone) throws Exception {
        String sql = "select * from user where phone like ?";
        ArrayList<User> lst = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + sphone + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int uid = rs.getInt("userID");
                    String rid = rs.getString("roleID");
                    int sid = rs.getInt("settingID");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String hash = rs.getString("hash");
                    String password = rs.getString("password");

                    User user = new User(uid, rid, sid, email, password, gender, fullname, phone, hash);
                    lst.add(user);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<User> getAllUserbyGender(String fgender) throws Exception {
        String sql = "select * from user where gender like ?";
        ArrayList<User> lst = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + fgender + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int uid = rs.getInt("userID");
                    String rid = rs.getString("roleID");
                    int sid = rs.getInt("settingID");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String hash = rs.getString("hash");
                    String password = rs.getString("password");

                    User user = new User(uid, rid, sid, email, password, gender, fullname, phone, hash);

                    lst.add(user);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<User> getAllUserbyRole(String frole) throws Exception {
        String sql = "select * from user where roleID like ?";
        ArrayList<User> lst = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + frole + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int uid = rs.getInt("userID");
                    String rid = rs.getString("roleID");
                    int sid = rs.getInt("settingID");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String hash = rs.getString("hash");
                    String password = rs.getString("password");

                    User user = new User(uid, rid, sid, email, password, gender, fullname, phone, hash);

                    lst.add(user);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<User> getAllUserbyFilter(String frole, String fgender) throws Exception {
        String sql = "select * from user where roleID = ? and gender = ?";
        ArrayList<User> lst = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, frole);
                ps.setString(2, fgender);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int uid = rs.getInt("userID");
                    String rid = rs.getString("roleID");
                    int sid = rs.getInt("settingID");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String hash = rs.getString("hash");
                    String password = rs.getString("password");

                    User user = new User(uid, rid, sid, email, password, gender, fullname, phone, hash);

                    lst.add(user);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public boolean changeUser(User u) throws Exception {
        String sql = "update user set roleID = ? where userID=?";
        boolean result = false;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, u.getRoleID());
                ps.setInt(2, u.getUserID());

                ps.executeUpdate();
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<User> getAllUsersLimit(int start, int perPage) throws Exception {
        String sql = "select SQL_CALC_FOUND_ROWS * from user limit ?, ?";

        ArrayList<User> lst = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, start);
                ps.setInt(2, perPage);

                rs = ps.executeQuery();
                while (rs.next()) {
                    int uid = rs.getInt("userID");
                    String rid = rs.getString("roleID");
                    int sid = rs.getInt("settingID");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    String hash = rs.getString("hash");
                    String password = rs.getString("password");

                    User user = new User(uid, rid, sid, email, password, gender, fullname, phone, hash);

                    lst.add(user);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public boolean deleteUser(int id) throws Exception {

        String sql = "DELETE FROM user WHERE userID=?";
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

    public boolean addPhone(User u) throws Exception {
        boolean result = false;
        String sql = "INSERT INTO user (roleID, settingID, email, password, gender, fullname, phone, hash) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, u.getRoleID());
                ps.setInt(2, 1);
                ps.setString(3, u.getEmail());
                ps.setString(4, u.getPassword());
                ps.setString(5, u.getGender());
                ps.setString(6, u.getFullname());
                ps.setString(7, u.getPhone());
                ps.setString(8, u.getHash());

                ps.executeUpdate();
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkRegisExist(int userID) throws SQLException {
        boolean result = false;
        String sql = "select * from registration, user where registration.userID = User.userID  and User.userID = ? and registration.status = 0;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception e) {

        } finally {
            closeConnection();
        }

        return result;
    }
}
