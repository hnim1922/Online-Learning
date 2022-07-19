/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Lesson;
import beans.Subject;
import daos.LessonDAO;
import daos.SubjectDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AdminFilterLessonServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "AdminLessonListServlet";

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
        String url = fail;
        try {
            String filter = request.getParameter("filter");

            LessonDAO dao = new LessonDAO();
            ArrayList<Lesson> lst = new ArrayList<>();

//            if (status.equals("all") && category.equals("all")) {
//                lst = dao.getAllSubject();
//            } else if (status.equals("all") && !category.equals("all")) {
//                int cate = Integer.parseInt(category);
//                lst = dao.getAllSubjectsByCategory(cate);
//            } else if (!status.equals("all") && category.equals("all")) {
//                boolean sta = Boolean.parseBoolean(status);
//                lst = dao.getAllSubjectsByStatus(sta);
//            } else {
//                int cate = Integer.parseInt(category);
//                boolean sta = Boolean.parseBoolean(status);
//                lst = dao.getAllSubjectsByFilter(sta, cate);
//            }
            
            if(filter.equals("all")){
                lst = dao.getAllLessons();
            }
            else{
                lst = dao.getAllLessonsBySubject(Integer.parseInt(filter));
            }

            HttpSession session = request.getSession();
            session.setAttribute("change", lst);
            request.setAttribute("LESSONSLIST_change", lst);
            url = success;
        } catch (Exception e) {
            e.printStackTrace();
            log("Error at AdminFilterLessonServlet: " + e.getMessage());
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
