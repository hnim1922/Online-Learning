/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Blog;
import beans.Category;
import beans.Subject;
import beans.User;
import daos.BlogDAO;
import daos.CategoryDAO;
import daos.SubjectDAO;
import daos.UserDAO;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ihtng
 */
public class LoginServlet extends HttpServlet {

    String adminsuccess = "adminview.jsp";
    String cussuccess = "HomepageServlet";
    String expsuccess = "expview.jsp";
    String maksuccess = "makview.jsp";
    String salsuccess = "salview.jsp";

    String ERROR = "login-fail.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            BlogDAO  blogDAO = new BlogDAO();
            List<Blog> firstFive = blogDAO.getFirstFive();
            ArrayList<Category> allCategoryList = categoryDAO.getAllCategory();
            ArrayList<Subject> featuredSubjectList = subjectDAO.getFeaturedSubject();
            request.setAttribute("firstFive", firstFive);
            request.setAttribute("featuredSubjectList", featuredSubjectList);
            request.setAttribute("categoryList", allCategoryList);
            request.setAttribute("firstFive", firstFive);
            String email = request.getParameter("email");
            String password = request.getParameter("psw");

            UserDAO dao = new UserDAO();
            User user = dao.checkLogin(email, password);
            if (user != null) {
                //check for Admin role
                if (user.getRoleID().equals("ADM")) {
                    url = adminsuccess;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                }
                //check for Customer role
                if (user.getRoleID().equals("CUS")) {
                    url = cussuccess;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                }
                if (user.getRoleID().equals("SAL")) {
                    url = salsuccess;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                }
                if (user.getRoleID().equals("EXP")) {
                    url = expsuccess;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                }
                if (user.getRoleID().equals("MKT")) {
                    url = maksuccess;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                }
            } else {
                request.setAttribute("ERROR", "Invalid email or password");
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
