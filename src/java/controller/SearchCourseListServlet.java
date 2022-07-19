/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Category;
import beans.Subject;
import daos.CategoryDAO;
import daos.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ihtng
 */
public class SearchCourseListServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int pageId = Integer.parseInt(request.getParameter("page"));
            int total = 3;
            if (pageId == 1) {
            } else {
                pageId = pageId - 1;
                pageId = pageId * total + 1;
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("keyword", request.getParameter("keyword"));
            SubjectDAO subjectDAO = new SubjectDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<Category> allCategoryList = categoryDAO.getAllCategory();
            ArrayList<Subject> featuredSubjectList = subjectDAO.getFeaturedSubject();
            ArrayList<Subject> subjectList = subjectDAO.getSubjectByName(request.getParameter("keyword"), pageId, total);
            request.setAttribute("allSearchedSubjectList", subjectDAO.getSubjectByName(request.getParameter("keyword")));
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("featuredSubjectList", featuredSubjectList);
            request.setAttribute("categoryList", allCategoryList);
            if (subjectList.size() > 0) {
                request.getRequestDispatcher("search-course-list.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("search-no-course-page.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchCourseListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchCourseListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
