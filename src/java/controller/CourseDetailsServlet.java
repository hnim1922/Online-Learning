/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Category;
import beans.PricePackage;
import beans.Subject;
import daos.CategoryDAO;
import daos.PricePackageDAO;
import daos.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ihtng
 */
public class CourseDetailsServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int subjectID = Integer.parseInt(request.getParameter("subjectID"));
            SubjectDAO subjectDAO = new SubjectDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            PricePackageDAO pricePackageDAO = new PricePackageDAO();
            List<PricePackage> pricePackageList = pricePackageDAO.getPricePackageBySubID(subjectID);
            ArrayList<Category> allCategoryList = categoryDAO.getAllCategory();
            ArrayList<Subject> featuredSubjectList = subjectDAO.getFeaturedSubject();
            Subject subject = subjectDAO.getSubjectById(subjectID);
            request.setAttribute("subjectID", subjectID);
            request.setAttribute("subjectThumbnail", subject.getThumbnail());
            request.setAttribute("subjectTitle", subject.getName());
            request.setAttribute("subjectCategory", subject.getCategoryName());
            request.setAttribute("subjectInfo", subject.getInformation());
            request.setAttribute("subjectPricePackage", subject.getPricePackage());
            request.setAttribute("featuredSubjectList", featuredSubjectList);
            request.setAttribute("categoryList", allCategoryList);
            request.setAttribute("pricepackageList", pricePackageList);
            request.getRequestDispatcher("course-details.jsp").forward(request, response);
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
            Logger.getLogger(CourseDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CourseDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CourseDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CourseDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
