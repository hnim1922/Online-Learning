/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import beans.Blog;
import beans.Category;
import beans.Subject;
import beans.User;
import daos.BlogDAO;
import daos.CategoryDAO;
import daos.CourseDAO;
import daos.RegistrationDAO;
import daos.SubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Minh
 */
public class DeleteAllServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            CategoryDAO categoryDAO = new CategoryDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            BlogDAO blogDAO = new BlogDAO();
            RegistrationDAO regisDAO = new RegistrationDAO();
            CourseDAO courseDAO = new CourseDAO();

            List<Blog> firstFive = blogDAO.getFirstFive();
            ArrayList<Category> allCategoryList = categoryDAO.getAllCategory();
            ArrayList<Subject> featuredSubjectList = subjectDAO.getFeaturedSubject();
            request.setAttribute("firstFive", firstFive);
            request.setAttribute("featuredSubjectList", featuredSubjectList);
            request.setAttribute("categoryList", allCategoryList);
            request.setAttribute("firstFive", firstFive);
            // create DAO that can change the status of registration base on userID
            User user = (User) session.getAttribute("USER");
            int userID = user.getUserID();
            int regisID = regisDAO.returnRegisID(userID);

            boolean result = courseDAO.deleteAllCourse(regisID);

            if (result){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Complete delete all course from registration');");
                 out.println("location='Homepage';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Sorry. Your registration is failed to delete');");
                 out.println("location='Homepage';");
                out.println("</script>");
            }
            request.getRequestDispatcher("Homepage").forward(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
           System.out.println("CheckOutServlet ::  " + e);
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
