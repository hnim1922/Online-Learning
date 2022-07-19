/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.ErrorLesson;
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
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AdminAddLessonServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "AdminLessonListServlet";
    private static final String invalid = "AdminLessonListServlet";

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
            LessonDAO dao = new LessonDAO();

            String name = request.getParameter("name");
            String sub = request.getParameter("subject");
            String content = request.getParameter("content");

            boolean valid = true;
            ErrorLesson error = new ErrorLesson();

            if (name.isEmpty()) {
                error.setNameError("Lesson name shouldn't be empty!");
                valid = false;
            } 
           
            if (content.isEmpty()) {
                error.setContentError("Content shouldn't be empty!");
                valid = false;
            }

            if (valid) {
                boolean result = dao.addANewLesson(content, name, Integer.parseInt(sub));
                if (result) {
                    url = success;
                } else {
                    request.setAttribute("ERROR", "insert FAILED!");
                }
            } else {
                url = invalid;
                request.setAttribute("INVALID", error);
            }
        } catch (Exception ex) {
            log("ERROR at AdminAddLessonServlet:" + ex.getMessage());
            ex.printStackTrace();
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
