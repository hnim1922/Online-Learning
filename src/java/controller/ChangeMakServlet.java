/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.UserDAO;
import beans.ErrorObject;
import beans.User;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class ChangeMakServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "makview.jsp";
    private static final String invalid = "makview.jsp";

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
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();

            int userid = Integer.parseInt(request.getParameter("userID"));
            String roleid = request.getParameter("roleID");
            String email = request.getParameter("email");
            String fullname = request.getParameter("fullname");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");

                                   String phonePattern = "^[0-9]+$";
            boolean valid = true;
            ErrorObject errorObj = new ErrorObject();

            if (fullname.trim().isEmpty()) {
                errorObj.setFullnameError("fullname shouldn't be empty!");
                valid = false;
            }
            if (!phone.isEmpty()) {
                if (phone.length() < 10 || phone.length() > 10) {
                    errorObj.setPhoneError("Phone shoule be at length exactly of 10 numbers!");
                    valid = false;
                }
                if (!phone.matches(phonePattern)) {
                    errorObj.setPhoneError("Phone shoule not contain any characters!");
                    valid = false;
                }
            } else {
                errorObj.setPhoneError("Phone shouldn't be empty");
                valid = false;
            }

            if (valid) {
                User user = new User(userid, roleid, email, phone, gender, fullname, phone);
                if (dao.updateUser(user)) {
                    session.setAttribute("USER", user);
                    url = success;
                } else {
                    request.setAttribute("ERROR", "Insert Failed!");
                }
            } else {
                url = invalid;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at ChangeMakController: " + e.getMessage());
            e.printStackTrace();
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
