/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.ErrorSlider;
import beans.User;
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
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class ChangeSliderServlet extends HttpServlet {

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
        String url = ERROR;
        response.setContentType("text/html;charset=UTF-8");
        try  {
            String title = request.getParameter("title");
            String backlink = request.getParameter("backlink");
            String image = request.getParameter("image");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            int sliderID = Integer.parseInt(request.getParameter("sliderID"));
            SliderDAO sliderDAO = new SliderDAO();

            boolean valid = true;
            ErrorSlider errorslider = new ErrorSlider();
            if (title.isEmpty()) {
                errorslider.setErrorTitle("Title should not be Empty!");
                valid = false;
            }
            if (image.isEmpty()) {
                // fetches input stream of the upload file for the blob column
                errorslider.setErrorImage("Image cant be empty");
                valid = false;
            }
           if (backlink.isEmpty()) {
                // fetches input stream of the upload file for the blob column
                errorslider.setErrorBacklink("Backlink cant be empty");
                valid = false;
            }
           if (sliderDAO.getSliderbyTitle(title) !=null)
            {
                errorslider.setErrorTitle("Title is existed");
            }
            if (valid) {
                boolean rs = sliderDAO.updateSliderInfo(sliderID, title, image, backlink, status);
                if (rs) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Change failed");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorslider);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at ChangeSliderServlet: " + e.getMessage());
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
