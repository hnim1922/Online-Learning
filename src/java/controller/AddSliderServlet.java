/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.ErrorSlider;
import beans.User;
import daos.QuizDAO;
import daos.SliderDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
//@MultipartConfig    
public class AddSliderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "/SliderListServlet?page=1";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "/SliderListServlet?page=1";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String title = request.getParameter("title");
            System.out.println(title);
            String image = request.getParameter("image");// Retrieves <input type="file" name="file">
            String backlink = request.getParameter("backlink");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            SliderDAO sliderDAO = new SliderDAO();
            boolean valid = true;
            ErrorSlider errorslider = new ErrorSlider();

            if (title.trim().isEmpty()) {
                errorslider.setErrorTitle("Title should not be Empty!");
                valid = false;
            }
            if (image.trim().isEmpty()) {
                errorslider.setErrorImage("Image should not be Empty!");
                valid = false;
            }
            if (backlink.trim().isEmpty()) {
                errorslider.setErrorBacklink("Back link should not be Empty!");
                valid = false;
            }
            if (sliderDAO.getSliderbyTitle(title) != null) {
                errorslider.setErrorTitle("Title is existed");
                valid = false;
            }
            if (valid) {
                boolean rs = sliderDAO.AddSlider(title, image, backlink, status);
                if (rs) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Add failed");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorslider);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at AddSliderServlet: " + e.getMessage());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddQuizServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddQuizServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
