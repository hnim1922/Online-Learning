/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.User;
import daos.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Minh
 */
public class SignUpServlet extends HttpServlet {

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
            String email = request.getParameter("email");
            String fullname = request.getParameter("fullname");
            String password = request.getParameter("new-password");
            String reenter_password = request.getParameter("re-enter-password");
            String gender = request.getParameter("gender");
            String phoneNumber = request.getParameter("phone-number");

            if (!password.equals(reenter_password)) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Re-password mismatch');");
                 out.println("location='Homepage';");
                out.println("</script>");
            } else {
               
                
                // create random number for check valid email
                String myHash;
                Random rand = new Random();
                rand.nextInt(999999);
                myHash = DigestUtils.md5Hex("" + rand);
                
                // Create Bean
                User user = new User();
                user.setRoleID("CUS");
                user.setEmail(email);
                user.setFullname(fullname);
                user.setPassword(password);
                user.setGender(gender);
                user.setPhone(phoneNumber);
                user.setHash(myHash);
                user.setSettingID(1);
                
                UserDAO userDAO = new UserDAO();
                boolean result = userDAO.createAccount(user);
                if (result) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Signup successfull!');");
                     out.println("location='Homepage';");
                    out.println("</script>");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Your email is not verification yet!');");
                     out.println("location='Homepage';");
                    out.println("</script>");
                }
            }
        } catch (SQLException ex) {
//            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
            log(ex.getMessage());
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
