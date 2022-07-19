/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Blog;
import utils.BlogStatus;
import java.sql.Connection;
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
public class BlogDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

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

    public ArrayList<Blog> getAllBlog1() throws Exception {
        String sql = "SELECT * FROM Blog";
        ArrayList<Blog> list = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    // query information in db
                    int blogID = rs.getInt("blogID");
                    int userID = rs.getInt("userID");
                    int categoryID = rs.getInt("categoryID");
                    String thumbnail = rs.getString("thumbnail");
                    String title = rs.getString("title");
                    String updatedDate = rs.getString("updatedDate");
                    String blogDetail = rs.getString("blogDetail");
                    int flag = rs.getInt("flag");
                    int status = rs.getInt("status");
                    BlogStatus blogStatus;
                    boolean blogFlag = (flag == 1);
                    // convert status type to enum
                    if (status == 1) {
                        blogStatus = BlogStatus.PUBLISH;
                    } else {
                        blogStatus = BlogStatus.DRAFT;
                    }

                    Blog blog = new Blog(blogID, userID, categoryID, thumbnail, title, updatedDate, blogDetail, blogFlag, blogStatus);
                    list.add(blog);
                }
            }
        } catch (Exception e) {
            System.out.println("BlogDAO :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public ArrayList<Blog> getAllBlog() throws Exception {
        String sql = "select b.*, u.fullname from blog b, user u where u.userID = b.userID;";
        ArrayList<Blog> list = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    // query information in db
                    int blogID = rs.getInt("blogID");
                    String fullname = rs.getString("fullname");
                    int categoryID = rs.getInt("categoryID");
                    String thumbnail = rs.getString("thumbnail");
                    String title = rs.getString("title");
                    String updatedDate = rs.getString("updatedDate");
                    String blogDetail = rs.getString("blogDetail");
                    int flag = rs.getInt("flag");
                    int status = rs.getInt("status");
                    BlogStatus blogStatus;
                    boolean blogFlag = (flag == 1);
                    // convert status type to enum
                    if (status == 1) {
                        blogStatus = BlogStatus.PUBLISH;
                    } else {
                        blogStatus = BlogStatus.DRAFT;
                    }

                    Blog blog = new Blog(blogID, fullname, categoryID, thumbnail, title, updatedDate, blogDetail, blogFlag, blogStatus);
                    list.add(blog);
                }
            }
        } catch (Exception e) {
            System.out.println("getAllBlog :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public List<Blog> getFirstFive() throws SQLException {
        String sql = "select * from (select b.*, u.fullname from blog b, user u where u.userID = b.userID order by updatedDate desc) as T limit 5;";
        ArrayList<Blog> list = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    // query information in db
                    int blogID = rs.getInt("blogID");
                    String fullname = rs.getString("fullname");
                    int categoryID = rs.getInt("categoryID");
                    String thumbnail = rs.getString("thumbnail");
                    String title = rs.getString("title");
                    String updatedDate = rs.getString("updatedDate");
                    String blogDetail = rs.getString("blogDetail");
                    int flag = rs.getInt("flag");
                    int status = rs.getInt("status");
                    BlogStatus blogStatus;
                    boolean blogFlag = (flag == 1);
                    // convert status type to enum
                    if (status == 1) {
                        blogStatus = BlogStatus.PUBLISH;
                    } else {
                        blogStatus = BlogStatus.DRAFT;
                    }

                    Blog blog = new Blog(blogID, fullname, categoryID, thumbnail, title, updatedDate, blogDetail, blogFlag, blogStatus);
                    list.add(blog);
                }
            }
        } catch (Exception e) {
            System.out.println("getFirstFive :: " + e);
        } finally {
            closeConnection();
        }

        return list;
    }

    public int getTotal() throws SQLException {
        String sql = "select count(*) as total from blog;";

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

    public List<Blog> getPaging(int index) throws Exception {
        String sql = "select * from (select b.*, u.fullname,  row_number() over (order by updatedDate desc) as r from blog b, user u where u.userID = b.userID) as x where r between (? * 9 - 8) and (? * 9)";
        ArrayList<Blog> list = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);

                ps.setInt(1, index);
                ps.setInt(2, index);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String fullname = rs.getString("fullname");
                    int categoryID = rs.getInt("categoryID");
                    String thumbnail = rs.getString("thumbnail");
                    String title = rs.getString("title");
                    String updatedDate = rs.getString("updatedDate");
                    String blogDetail = rs.getString("blogDetail");
                    int flag = rs.getInt("flag");
                    int status = rs.getInt("status");
                    BlogStatus blogStatus;
                    boolean blogFlag = (flag == 1);
                    // convert status type to enum
                    if (status == 1) {
                        blogStatus = BlogStatus.PUBLISH;
                    } else {
                        blogStatus = BlogStatus.DRAFT;
                    }

                    Blog blog = new Blog(blogID, fullname, categoryID, thumbnail, title, updatedDate, blogDetail, blogFlag, blogStatus);
                    list.add(blog);
                }
            }
        } catch (Exception e) {
            System.out.println("getPaging :: " + e);
        } finally {
            closeConnection();
        }
        return list;
    }

    public int getCountSearch(String txt) throws Exception {
        String sql = "select count(*) as total from blog where title like ?";

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

    public List<Blog> getSearchList(String txt, int index) throws SQLException {
        String sql = "select * from (select b.*, u.fullname,  row_number() over (order by updatedDate desc) as r from blog b, user u where u.userID = b.userID and title like ?) as x where r between (?* 9 - 8) and (? * 9);";
        ArrayList<Blog> list = new ArrayList<>();

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
                    int blogID = rs.getInt("blogID");
                    String fullname = rs.getString("fullname");
                    int categoryID = rs.getInt("categoryID");
                    String thumbnail = rs.getString("thumbnail");
                    String title = rs.getString("title");
                    String updatedDate = rs.getString("updatedDate");
                    String blogDetail = rs.getString("blogDetail");
                    int flag = rs.getInt("flag");
                    int status = rs.getInt("status");
                    BlogStatus blogStatus;
                    boolean blogFlag = (flag == 1);
                    // convert status type to enum
                    if (status == 1) {
                        blogStatus = BlogStatus.PUBLISH;
                    } else {
                        blogStatus = BlogStatus.DRAFT;
                    }

                    Blog blog = new Blog(blogID, fullname, categoryID, thumbnail, title, updatedDate, blogDetail, blogFlag, blogStatus);
                    list.add(blog);
                }
            }
        } catch (Exception e) {
            System.out.println("getPaging :: " + e);
        } finally {
            closeConnection();
        }
        return list;
    }

    public Blog getBlog(int blogID) throws Exception {
        String sql = "SELECT b.*, u.fullname FROM Blog b, User u where blogID=? and u.userID = b.userID;";
        Blog blog = null;

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();

            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, blogID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    int categoryID = rs.getInt("categoryID");
                    String thumbnail = rs.getString("thumbnail");
                    String title = rs.getString("title");
                    String updatedDate = rs.getString("updatedDate");
                    String blogDetail = rs.getString("blogDetail");
                    int flag = rs.getInt("flag");
                    int status = rs.getInt("status");
                    BlogStatus blogStatus;
                    boolean blogFlag = (flag == 1);
                    // convert status type to enum
                    if (status == 1) {
                        blogStatus = BlogStatus.PUBLISH;
                    } else {
                        blogStatus = BlogStatus.DRAFT;
                    }

                    blog = new Blog(blogID, fullname, categoryID, thumbnail, title, updatedDate, blogDetail, blogFlag, blogStatus);
                }
            }
        } catch (Exception e) {
            System.out.println("getBlog :: " + e);
        } finally {
            closeConnection();
        }

        return blog;
    }

    public List<Blog> searchByCat(int catID) {
        String sql = "SELECT b.*, u.fullname FROM Blog b, User u where categoryID=? and u.userID = b.userID;";
        List<Blog> list = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, catID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String fullname = rs.getString("fullname");
                    String thumbnail = rs.getString("thumbnail");
                    String title = rs.getString("title");
                    String updatedDate = rs.getString("updatedDate");
                    String blogDetail = rs.getString("blogDetail");
                    int flag = rs.getInt("flag");
                    int status = rs.getInt("status");
                    BlogStatus blogStatus;
                    boolean blogFlag = (flag == 1);
                    // convert status type to enum
                    if (status == 1) {
                        blogStatus = BlogStatus.PUBLISH;
                    } else {
                        blogStatus = BlogStatus.DRAFT;
                    }

                    Blog blog = new Blog(blogID, fullname, catID, thumbnail, title, updatedDate, blogDetail, blogFlag, blogStatus);
                    list.add(blog);
                }
            }
        } catch (Exception e) {
            System.out.println("searchByCat :: " + e);
        }
        return list;
    }

    public List<Blog> searchByCatPaging(int catID, int index) {
        String sql = "select * from (select b.*, u.fullname,  row_number() over (order by updatedDate desc) as r from blog b, user u where u.userID = b.userID and categoryID=?) as x where r between (?* 9 - 8) and (? * 9);";
        List<Blog> list = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, catID);
                ps.setInt(2, index);
                ps.setInt(3, index);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String fullname = rs.getString("fullname");
                    String thumbnail = rs.getString("thumbnail");
                    String title = rs.getString("title");
                    String updatedDate = rs.getString("updatedDate");
                    String blogDetail = rs.getString("blogDetail");
                    int flag = rs.getInt("flag");
                    int status = rs.getInt("status");
                    BlogStatus blogStatus;
                    boolean blogFlag = (flag == 1);
                    // convert status type to enum
                    if (status == 1) {
                        blogStatus = BlogStatus.PUBLISH;
                    } else {
                        blogStatus = BlogStatus.DRAFT;
                    }

                    Blog blog = new Blog(blogID, fullname, catID, thumbnail, title, updatedDate, blogDetail, blogFlag, blogStatus);
                    list.add(blog);
                }
            }
        } catch (Exception e) {
            System.out.println("searchByCat :: " + e);
        }
        return list;
    }

    public boolean AddPost(int userID, int catID, String thumbnail, String title, String date, String detail, boolean flag, boolean status) throws SQLException {
        boolean result = false;
        String sql = "insert into blog(userID, categoryID, thumbnail,title, updatedDate, blogDetail, flag, status ) values ( ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, userID);
                ps.setInt(2, catID);
                ps.setString(3, thumbnail);
                ps.setString(4, title);
                ps.setString(5, date);
                ps.setString(6, detail);
                ps.setBoolean(7, flag);
                ps.setBoolean(8, status);
                ps.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updatePostInfo(int userID, int catID, String thumbnail, String title, String date, String detail, boolean flag, boolean status) throws SQLException {
        boolean rs = false;
        String sql = "update blog set userID=?, catergoryID=?, thumbnail=?, title=?, date=?, detail=?,flag=?, status=?    where blogID=?;";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, userID);
                ps.setInt(2, catID);
                ps.setString(3, thumbnail);
                ps.setString(4, title);
                ps.setString(5, date);
                ps.setString(6, detail);
                ps.setBoolean(7, flag);
                ps.setBoolean(8, status);
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
}
