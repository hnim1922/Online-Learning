/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Calendar;
import beans.Registration;
import beans.Subject;
import beans.User;
import daos.RegistrationDAO;
import daos.SubjectDAO;
import daos.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DashboardServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "dashboard.jsp";

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
            SubjectDAO sdao = new SubjectDAO();
            ArrayList<Subject> sub_lst = sdao.getAllSubjectNameandID();

            UserDAO udao = new UserDAO();
            ArrayList<User> user_lst = udao.getAllUsers();
            
            RegistrationDAO rdao = new RegistrationDAO();
            ArrayList<Registration> regis_lst = rdao.getAllRegis(1);

            ArrayList<Calendar> cal_lst = new ArrayList<>();
            
            for(int i = 1; i< 13; i++){
                Calendar cal = new Calendar(0, 0, i, 0);
                cal_lst.add(cal);
            }
            
            for (Registration registration : regis_lst) {
                Date rdate = registration.getRegisTime();
                int month = rdate.getMonth()+1;
                
                for (Calendar calendar : cal_lst) {
                    if(month == calendar.getMonth()){
                        int count = calendar.getCountmonth();
                        calendar.setCountmonth(count + 1);
                    }
                }   
            }
            
//            for (Calendar calendar : cal_lst) {
//                System.out.println(calendar.getMonth());
//                System.out.println(calendar.getCountmonth());
//            }

            ArrayList<Registration> regis_lst_eq_1 = rdao.getAllRegis(1);
            ArrayList<Registration> regis_lst_eq_0 = rdao.getAllRegis(0);

            int count1 = 0;
            for (Registration registration : regis_lst_eq_1) {
                count1 = count1 + 1;
            }
            
            int count0 = 0;
            for (Registration registration : regis_lst_eq_0) {
                count0 = count0 + 1;
            }
            
            int user_count = 0;
            for (User user : user_lst) {
                String check = user.getRoleID();
                if(check.equals("CUS")){
                    user_count = user_count + 1;
                }
            }
            
            System.out.println(".....................");
            int count_check = 0;
            for (User user : user_lst) {
                int check = user.getUserID();
                ArrayList<Registration> checklst = rdao.getAllRegisBaseUser(check);
                if(!checklst.isEmpty()){
                    count_check ++;
                }
            }
            
            
            request.setAttribute("sublist", sub_lst);
            request.setAttribute("regislist", cal_lst);
            request.setAttribute("count0", count0);
            request.setAttribute("count1", count1);
            request.setAttribute("usercount", user_count);
            request.setAttribute("usercount_check", count_check);
            
            url = success;

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at AdminDashboardServlet: " + e.getMessage());
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
