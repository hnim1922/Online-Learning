/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Category;
import beans.ErrorSubject;
import beans.Subject;
import beans.SubjectDimension;
import beans.User;
import daos.CategoryDAO;
import daos.SubjectDAO;
import daos.SubjectDimensionDAO;
import daos.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class AdminAddSubjectServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "AdminSubjectListServlet";
    private static final String invalid = "AdminSubjectListServlet";

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
            String name = request.getParameter("name");
            String userID = request.getParameter("owner");
            String thumbnail = request.getParameter("thumbnail");
            String categoryId = request.getParameter("category");
            String str_status = request.getParameter("status");
            String str_featureflag = request.getParameter("featureflag");
            String information = request.getParameter("information");

            UserDAO udao = new UserDAO();
            CategoryDAO cdao = new CategoryDAO();
            SubjectDimensionDAO ddao = new SubjectDimensionDAO();
            SubjectDAO sdao = new SubjectDAO();

            User user = udao.getUserbyID(Integer.parseInt(userID));
            Category category = cdao.getCategoryById(Integer.parseInt(categoryId));
            SubjectDimension dimension = ddao.getSubjectDimensionbyID(1);

            boolean valid = true;
            ErrorSubject error = new ErrorSubject();

            if (name.isEmpty()) {
                error.setNameError("Name shouldn't be empty!");
                valid = false;
            }

            if (thumbnail.isEmpty()) {
                error.setThumbnailimageError("Thumbnail shouldn't be empty!");
                valid = false;
            }

            if (information.isEmpty()) {
                error.setInformationError("Information shouldn't be empty!");
                valid = false;
            } else {
                if (information.length() > 500) {
                    error.setInformationError("Information shouldn't be too long! Keep it simple!");
                    valid = false;
                }
            }

            if (valid) {
                boolean status = Boolean.parseBoolean(str_status);
                boolean featureflag = Boolean.parseBoolean(str_featureflag);
                Subject sub = new Subject(0, thumbnail, name, featureflag, status, information, category, dimension, user, 0);
                boolean result = sdao.AddSubject(sub);
                if (result) {
                    url = success;
                } else {
                    request.setAttribute("ERROR", "Insert Failed!");
                }
            } else {
                url = invalid;
                request.setAttribute("INVALID", error);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at AdminAddSubjectServlet: " + e.getMessage());
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
