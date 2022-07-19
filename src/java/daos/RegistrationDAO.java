/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Registration;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBConnection;

/**
 *
 * @author Minh
 */
public class RegistrationDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public RegistrationDAO() {
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

    public boolean createNewRegistration(Registration registration) throws SQLException {
        boolean result = false;

        Date registrationTime = new java.sql.Date(registration.getRegisTime().getTime());
        Date validFrom = new java.sql.Date(registration.getValidFrom().getTime());
        Date validTo = new java.sql.Date(registration.getValidTo().getTime());
        int userID = registration.getUserID();

        String sql = "insert into registration (registrationTime, validFrom, validTo, userID) values (?, ?, ?, ?);";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setDate(1, registrationTime);
                ps.setDate(2, validFrom);
                ps.setDate(3, validTo);
                ps.setInt(4, userID);

                int iCount = ps.executeUpdate();
                if (iCount != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("createNewRegistration :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public float getTotalByUserID(int userID) throws SQLException {
        String sql = "select totalPrice from registration where userID = ? and status = 0;";
        float total = 0;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, userID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    total = rs.getFloat("totalPrice");
                }
            }
        } catch (Exception e) {
            System.out.println("getTotalByUserID :: " + e);
        } finally {
            closeConnection();
        }
        return total;
    }

    public boolean updateTotal(int userID, float total) throws Exception {
        boolean result = false;
        String sql = "update registration \n" + "set totalPrice = ?\n" + "where userID = ? and status = 0;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setFloat(1, total);
                ps.setInt(2, userID);

                int iCount = ps.executeUpdate();
                if (iCount != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("updateTotal :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public int returnRegisID(int userID) throws SQLException {
        int regisID = 0;
        String sql = "select registrationID from registration where userID = ? and status = 0;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, userID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    regisID = rs.getInt("registrationID");
                }
            }
        } catch (Exception e) {
            System.out.println("returnRegisID :: " + e);
        } finally {
            closeConnection();
        }

        return regisID;
    }

    public boolean checkDuplicate(int regisID, int subID) throws SQLException {
        String sql = "select * from course where subjectID = ? and registrationID = ?;";
        boolean result = false;

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, subID);
                ps.setInt(2, regisID);

                rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("checkDuplicate :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public Registration getRegistration(int regisID) throws SQLException {
        Registration registration = null;
        String sql = "select * from registration where registrationID = ?;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, regisID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    Date registrationTime = rs.getDate("registrationTime");
                    Date validFrom = rs.getDate("validFrom");
                    Date validTo = rs.getDate("validTo");
                    int userID = rs.getInt("userID");

                    registration = new Registration(registrationTime, validFrom, validTo, userID);
                }
            }
        } catch (Exception e) {
            System.out.println("getRegistration :: " + e);
        } finally {
            closeConnection();
        }
        return registration;
    }

    public Registration getFullRegistration(int regisID) throws SQLException {
        Registration registration = null;
        String sql = "select * from registration where registrationID = ?;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, regisID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    int status = rs.getInt("status");
                    Date registrationTime = rs.getDate("registrationTime");
                    Date validFrom = rs.getDate("validFrom");
                    Date validTo = rs.getDate("validTo");
                    int userID = rs.getInt("userID");

                    registration = new Registration(regisID, registrationTime, status, validFrom, validTo, userID);
                }
            }
        } catch (Exception e) {
            System.out.println("getRegistration :: " + e);
        } finally {
            closeConnection();
        }
        return registration;
    }

    public boolean updateStatus(int regisID, int status) throws SQLException {
        boolean result = false;
        String sql = "update registration set status = ? where registrationID = ?;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, status);
                ps.setInt(2, regisID);

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

    public boolean updateStatus(int userID) throws SQLException {
        boolean result = false;
        String sql = "update registration set status = true where userID = ?;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, userID);

                int iCount = ps.executeUpdate();

                if (iCount != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("updateStatus :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Registration> getAllRegistration() throws SQLException {
        List<Registration> list = new ArrayList<>();
        String sql = "select u.email, r.* from registration r, user u where r.userID = u.userID;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int regisID = rs.getInt("registrationID");
                Date regisTime = rs.getDate("registrationTime");
                int status = rs.getInt("status");
                Date validTo = rs.getDate("validTo");
                int userID = rs.getInt("userID");
                String email = rs.getString("email");
                Registration regis = new Registration(regisID, regisTime, status, regisTime, validTo, userID, email);

                list.add(regis);
            }
        } catch (Exception e) {
            System.out.println("getAllRegistration :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public List<Registration> getRegistrationByUID() throws SQLException {
        List<Registration> list = new ArrayList<>();
        String sql = "select r.*, u.email from registration r, user u where u.userID = r.userID order by r.userID ;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int regisID = rs.getInt("registrationID");
                Date regisTime = rs.getDate("registrationTime");
                int status = rs.getInt("status");
                Date validTo = rs.getDate("validTo");
                int userID = rs.getInt("userID");
                String email = rs.getString("email");
                Registration regis = new Registration(regisID, regisTime, status, regisTime, validTo, userID, email);
                list.add(regis);
            }
        } catch (Exception e) {
            System.out.println("getRegistrationByUID :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public List<Registration> getRegistrationByEmail() throws SQLException {
        List<Registration> list = new ArrayList<>();
        String sql = "select r.*, u.email from registration r, user u where u.userID = r.userID order by u.email;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int regisID = rs.getInt("registrationID");
                Date regisTime = rs.getDate("registrationTime");
                int status = rs.getInt("status");
                Date validTo = rs.getDate("validTo");
                int userID = rs.getInt("userID");
                String email = rs.getString("email");
                Registration regis = new Registration(regisID, regisTime, status, regisTime, validTo, userID, email);
                list.add(regis);
            }
        } catch (Exception e) {
            System.out.println("getRegistrationByUID :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public List<Registration> getRegistrationByRegistrationTime() throws SQLException {
        List<Registration> list = new ArrayList<>();
        String sql = "select r.*, u.email from registration r, user u where u.userID = r.userID order by r.registrationTime;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int regisID = rs.getInt("registrationID");
                Date regisTime = rs.getDate("registrationTime");
                int status = rs.getInt("status");
                Date validTo = rs.getDate("validTo");
                int userID = rs.getInt("userID");
                String email = rs.getString("email");
                Registration regis = new Registration(regisID, regisTime, status, regisTime, validTo, userID, email);
                list.add(regis);
            }
        } catch (Exception e) {
            System.out.println("getRegistrationByUID :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public List<Registration> getRegistrationByStatus() throws SQLException {
        List<Registration> list = new ArrayList<>();
        String sql = "select r.*, u.email from registration r, user u where u.userID = r.userID order by r.status;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int regisID = rs.getInt("registrationID");
                Date regisTime = rs.getDate("registrationTime");
                int status = rs.getInt("status");
                Date validTo = rs.getDate("validTo");
                int userID = rs.getInt("userID");
                String email = rs.getString("email");
                Registration regis = new Registration(regisID, regisTime, status, regisTime, validTo, userID, email);
                list.add(regis);
            }
        } catch (Exception e) {
            System.out.println("getRegistrationByUID :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public boolean updateValidTo(int regisID, Date newValidTo) throws SQLException {
        boolean result = false;
        String sql = "update registration \n" + "set validTo = ?\n" + "where registrationID = ?;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(2, regisID);
                ps.setDate(1, newValidTo);

                int iCount = ps.executeUpdate();
                if (iCount != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("updateValidTo :: " + e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Registration> getSearchList(String txtSearch) throws SQLException {
        List<Registration> list = new ArrayList<>();
        String sql = "select u.email, r.* from registration r, user u where u.email like ? and r.userID = u.userID;";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + txtSearch + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    int regisID = rs.getInt("registrationID");
                    Date regisTime = rs.getDate("registrationTime");
                    int status = rs.getInt("status");
                    Date validTo = rs.getDate("validTo");
                    int userID = rs.getInt("userID");
                    String email = rs.getString("email");
                    Registration regis = new Registration(regisID, regisTime, status, regisTime, validTo, userID, email);
                    list.add(regis);
                }
            }
        } catch (Exception e) {
            System.out.println("getSearchList :: " + e);
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<Registration> getAllRegis(int sts) throws Exception {
        ArrayList<Registration> lst = new ArrayList<>();
        String sql = "SELECT * from registration where status = ?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, sts);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int regisID = rs.getInt("registrationID");
                    Date regisTime = rs.getDate("registrationTime");
                    int status = rs.getInt("status");
                    Date validFrom = rs.getDate("validFrom");
                    Date validTo = rs.getDate("validTo");
                    int userID = rs.getInt("userID");

                    Registration regis = new Registration(regisID, regisTime, status, validFrom, validTo, userID);
                    lst.add(regis);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Registration> getAllRegisBaseSubject(int subjectID) throws Exception {
        ArrayList<Registration> lst = new ArrayList<>();
        String sql = "SELECT r.* FROM course c, registration r, subject s\n"
                + "WHERE s.subjectID = ? and s.subjectID = c.subjectID and c.registrationID = r.registrationID and r.status = 1";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, subjectID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int regisID = rs.getInt("registrationID");
                    Date regisTime = rs.getDate("registrationTime");
                    int status = rs.getInt("status");
                    Date validFrom = rs.getDate("validFrom");
                    Date validTo = rs.getDate("validTo");
                    int userID = rs.getInt("userID");

                    Registration regis = new Registration(regisID, regisTime, status, validFrom, validTo, userID);
                    lst.add(regis);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Registration> getAllRegisBaseUser(int userID) throws Exception {
        ArrayList<Registration> lst = new ArrayList<>();
        String sql = "SELECT r.* FROM registration r, user u\n"
                + "WHERE r.userID = u.userID and u.userID = ?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int regisID = rs.getInt("registrationID");
                    Date regisTime = rs.getDate("registrationTime");
                    int status = rs.getInt("status");
                    Date validFrom = rs.getDate("validFrom");
                    Date validTo = rs.getDate("validTo");

                    Registration regis = new Registration(regisID, regisTime, status, validFrom, validTo, userID);
                    lst.add(regis);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }
}
