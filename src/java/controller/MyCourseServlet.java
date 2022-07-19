/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import beans.Category;
import beans.Course;
import beans.Subject;
import beans.User;
import daos.CategoryDAO;
import daos.CourseDAO;
import daos.SubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ihtng
 */
public class MyCourseServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws Exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("USER");
            if (user != null) {
                int pageId = Integer.parseInt(request.getParameter("page"));
                int total = 3;
                if (pageId == 1) {
                } else {
                    pageId = pageId - 1;
                    pageId = pageId * total + 1;
                }
                CategoryDAO categoryDAO = new CategoryDAO();
                SubjectDAO subjectDAO = new SubjectDAO();
                CourseDAO courseDAO = new CourseDAO();
                ArrayList<Category> allCategoryList = categoryDAO.getAllCategory();
                ArrayList<Subject> featuredSubjectList = subjectDAO.getFeaturedSubject();
                ArrayList<Course> paidCourseList = new ArrayList<>();
                paidCourseList = courseDAO.getPaidCourse(pageId, total);
                request.setAttribute("allSubjectList", courseDAO.getAllPaidCourse());
                request.setAttribute("featuredSubjectList", featuredSubjectList);
                request.setAttribute("categoryList", allCategoryList);
                request.setAttribute("paidCourseList", paidCourseList);
                if (paidCourseList.size() > 0) {
                    request.getRequestDispatcher("my-course-list.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("no-course-page.jsp").forward(request, response);
                }
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('You have to login to use this feature');");
                 out.println("location='Homepage';");
                out.println("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
