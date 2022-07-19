/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Answer;
import daos.AnswerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author takah
 */
public class AddNewAnswerServlet extends HttpServlet {

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
            int questionID = Integer.parseInt(request.getParameter("questionID"));
            int result = Integer.parseInt(request.getParameter("result"));
            String content = request.getParameter("answerContent");
            Answer answer = new Answer(questionID, result, content);
            AnswerDAO answerDAO = new AnswerDAO();
            boolean finalResult = answerDAO.addNewAnswerByQuestionID(answer);
            if (finalResult) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Add successful');");
                out.println("location='expQuestionDetails.jsp';");
                out.println("</script>");
                System.out.println("Success");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Add failed');");
                out.println("location='expQuestionDetails.jsp';");
                out.println("</script>");
                System.out.println("failed");
            }
            request.getRequestDispatcher("QuestionDetailsServlet").forward(request, response);
        } catch (Exception e) {
            System.out.println("AddNewAnswerServlet :: " + e);
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
