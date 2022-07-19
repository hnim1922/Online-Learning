/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.ErrorObject;
import beans.User;
import daos.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Admin
 */
public class AdminAddServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "AdminUserListServlet";
    private static final String invalid = "AdminUserListServlet";

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
            UserDAO dao = new UserDAO();
            ArrayList<User> lst = dao.getAllUsers();

            String roleID = request.getParameter("roleID");
            String email = request.getParameter("email");
            String fullname = request.getParameter("fullname");
            String password = request.getParameter("password");
            String re_password = request.getParameter("re_password");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");

            String phonePattern = "^0[0-9]{9}$";
            boolean valid = true;
            ErrorObject errorObj = new ErrorObject();

            if (!password.equals(re_password)) {
                valid = false;
                errorObj.setRe_passwordError("Password doesn't match");
            }

            for (User user : lst) {
                if (user.getEmail().equals(email)) {
                    valid = false;
                    errorObj.setEmailError("Email is already existed!");
                }
            }
            if (email.isEmpty()) {
                valid = false;
                errorObj.setEmailError("Email must not be empty!");
            }
            if (password.isEmpty()) {
                valid = false;
                errorObj.setPasswordError("Password must not be empty!");
            }
            if (re_password.isEmpty()) {
                valid = false;
                errorObj.setRe_passwordError("Re-password must not be empty!");
            }
            if (fullname.isEmpty()) {
                valid = false;
                errorObj.setFullnameError("Fullname must not be empty!");
            }
            if (!phone.isEmpty()) {
                if (phone.length() < 10 || phone.length() > 10) {
                    errorObj.setPhoneError("Phone shoule be at length exactly of 10 numbers!");
                    valid = false;
                } else if (!phone.matches(phonePattern)) {
                    errorObj.setPhoneError("Phone shoule start with 0!");
                    valid = false;
                }
                for (User user : lst) {
                    if (user.getPhone().equals(phone)) {
                        valid = false;
                        errorObj.setEmailError("Phone is already existed!");
                    }
                }
            } else {
                errorObj.setPhoneError("Phone shouldn't be empty");
                valid = false;
            }

            String myHash;
            Random rand = new Random();
            rand.nextInt(999999);
            myHash = DigestUtils.md5Hex("" + rand);

            if (valid) {
                User user = new User(0, roleID, 1, email, password, gender, fullname, phone, myHash);
                boolean result = dao.createAccount(user);
                if (result) {
                    url = success;
                } else {
                    request.setAttribute("ERROR", "Insert Failed!");
                }
            } else {
                url = invalid;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at AdminAddServlet: " + e.getMessage());
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
