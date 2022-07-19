/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Slider;
import java.io.InputStream;
import java.sql.Blob;
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
public class SliderDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public SliderDAO() {
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

    public ArrayList<Slider> getAllSlider() throws Exception {
        ArrayList<Slider> sliderList = new ArrayList<>();
        String sql = "select sliderID, slider.title, slider.image, backlink, slider.status from slider";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("sliderID");
                    String title = rs.getString("title");
                    String image = rs.getString("image");
                    String backlink = rs.getString("backlink");
                    boolean status = rs.getBoolean("status");
                    Slider slider = new Slider(id, title, image, backlink, status);
                    sliderList.add(slider);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sliderList;
    }

    public ArrayList<Slider> getAllSlider(int start, int total) throws Exception {
        ArrayList<Slider> sliderList = new ArrayList<>();
        String sql = "select sliderID, slider.title, slider.image, backlink, slider.status from slider limit " + (start - 1) + "," + total;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("sliderID");
                    String title = rs.getString("title");
                    String image = rs.getString("image");
                    String backlink = rs.getString("backlink");
                    boolean status = rs.getBoolean("status");
                    Slider slider = new Slider(id, title, image, backlink, status);
                    sliderList.add(slider);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return sliderList;
    }

    public ArrayList<Slider> getSliderbyTitle(String keyword) throws SQLException {
        ArrayList<Slider> sliderListbyTitle = new ArrayList<>();
        String sql = "select sliderID, slider.title, slider.image, backlink, slider.status from slider where slider.title like ?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("sliderID");
                    String title = rs.getString("title");
                    String image = rs.getString("image");
                    String backlink = rs.getString("backlink");
                    boolean status = rs.getBoolean("status");
                    Slider slider = new Slider(id, title, image, backlink, status);
                    sliderListbyTitle.add(slider);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return sliderListbyTitle;
    }

    public ArrayList<Slider> getSliderbyTitle(String keyword, int start, int total) throws SQLException {
        ArrayList<Slider> sliderListbyTitle = new ArrayList<>();
        String sql = "select sliderID, slider.title, slider.image, backlink, slider.status from slider where slider.title like ? limit " + (start - 1) + "," + total;
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("sliderID");
                    String title = rs.getString("title");
                    String image = rs.getString("image");
                    String backlink = rs.getString("backlink");
                    boolean status = rs.getBoolean("status");
                    Slider slider = new Slider(id, title, image, backlink, status);
                    sliderListbyTitle.add(slider);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return sliderListbyTitle;
    }

    public Slider getSliderbyID(int id) throws SQLException {
        Slider slider = new Slider();
        String sql = "select sliderID, slider.title, slider.image, slider.backlink, slider.status from slider where sliderID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    slider.setSliderID(rs.getInt("sliderID"));
                    slider.setTitle(rs.getString("title"));
                    slider.setImage(rs.getString("image"));
                    slider.setBacklink(rs.getString("backlink"));
                    slider.setStatus(rs.getBoolean("status"));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return slider;
    }

    public boolean AddSlider(String title, String image, String backlink, boolean status) throws SQLException {
        boolean result = false;
        String sql = "insert into slider(title, image, backlink, status)values ( ?, ?, ?, ?);";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, image);
                ps.setString(3, backlink);
                ps.setBoolean(4, status);

                ps.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return status;
    }

    public boolean updateSliderInfo(int sliderID, String title, String image, String backlink, boolean status) throws SQLException {
        boolean rs = false;
        String sql = "update slider set title=?, image=?, backlink=?, status=? where sliderID=?;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, image);
                ps.setString(3, backlink);
                ps.setBoolean(4, status);
                ps.setInt(5, sliderID);
                ps.executeUpdate();
                rs = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return rs;
    }

    public List<Slider> getThreeSlider() throws SQLException {
        String sql = "SELECT * FROM slider  ORDER BY  sliderID LIMIT 3;";
        ArrayList<Slider> sliderList = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("sliderID");
                    String title = rs.getString("title");
                    String image = rs.getString("image");
                    String backlink = rs.getString("backlink");
                    boolean status = rs.getBoolean("status");
                    Slider slider = new Slider(id, title, image, backlink, status);
                    sliderList.add(slider);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sliderList;
    }
}
