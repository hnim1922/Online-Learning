/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Calendar;
import beans.Registration;
import daos.RegistrationDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DashboardDateServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "dashboarddate.jsp";

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
            String date = request.getParameter("date");
            String[] lst = date.split("-");
            int month = Integer.parseInt(lst[1]);
            int day = Integer.parseInt(lst[2]);

            RegistrationDAO rdao = new RegistrationDAO();
            ArrayList<Registration> regis_lst = rdao.getAllRegis(1);
            ArrayList<Registration> regis_lst_check = new ArrayList<>();
            ArrayList<Registration> regis_lst_check_2 = new ArrayList<>();

            for (Registration registration : regis_lst) {
                if (month == (registration.getRegisTime().getMonth() + 1)) {
                    regis_lst_check.add(registration);
                }
            }

            int check_day = day + 7;
            for (Registration registration : regis_lst_check) {
                if (registration.getRegisTime().getDate() >= day && registration.getRegisTime().getDate() <= check_day) {
                    regis_lst_check_2.add(registration);
                }
            }

            ArrayList<Calendar> cal_lst = new ArrayList<>();
//            switch (month) {
//                case 2:
//                    for (int i = 1; i < 29; i++) {
//                        Calendar cal = new Calendar(0, 0, 0, i);
//                        cal_lst.add(cal);
//                    }
//                    break;
//                case 4:
//                case 6:
//                case 9:
//                case 11:
//                    for (int i = 1; i < 31; i++) {
//                        Calendar cal = new Calendar(0, 0, 0, i);
//                        cal_lst.add(cal);
//                    }
//                    break;
//                default:
//                    for (int i = 1; i < 32; i++) {
//                        Calendar cal = new Calendar(0, 0, 0, i);
//                        cal_lst.add(cal);
//                    }
//                    break;
//            }
//
//            for (Calendar calendar : cal_lst) {
//                System.out.println(calendar.getDate());
//            }

            int check_month = 0;
            int flag = 0;
            int max_day = 0;

            switch (month) {
                case 2:
                    max_day = 28;
                    if (check_day > 28) {
                        check_day = check_day - 28;
                        check_month = month + 1;
                        flag = 1;
                        System.out.println("check 2");
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    max_day = 30;
                    if (check_day > 30) {
                        check_day = check_day - 30;
                        check_month = month + 1;
                        flag = 1;
                        System.out.println("check 11");
                    }
                    break;
                default:
                    max_day = 31;
                    if (check_day > 31) {
                        check_day = check_day - 31;
                        check_month = month + 1;
                        flag = 1;
                        System.out.println("check 12");
                    }
                    break;
            }

            System.out.println(max_day);
            if (flag == 0) {
                check_month = month;
            }

            System.out.println(day);
            System.out.println(month);
            System.out.println("=====");
            System.out.println(check_day);
            System.out.println(check_month);

            for (int i = 0; i < 7; i++) {
                Calendar cal = new Calendar();
                if (day + i > max_day) {
                    cal = new Calendar(0, 0, month+1, day - max_day+i);
                } else {
                    cal = new Calendar(0, 0, month, day + i);
                }
                cal_lst.add(cal);
            }

            System.out.println("------------------------");
            for (Calendar calendar : cal_lst) {
                System.out.println(calendar.getMonth() + "-" + calendar.getDate());
            }

            System.out.println("--------------------------------------------------------------------------");
            for (Registration registration : regis_lst_check_2) {
                int rdate = registration.getRegisTime().getDate();
                for (Calendar calendar : cal_lst) {
                    if (rdate == calendar.getDate()) {
                        int count = calendar.getCountdate();
                        calendar.setCountdate(count + 1);
                    }
                }
            }

            System.out.println("------------------------------------------------------");
            for (Calendar calendar : cal_lst) {
                System.out.println(calendar.getDate());
                System.out.println(calendar.getCountdate());
            }

            request.setAttribute("regislist", cal_lst);

            url = success;
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at DashboardDateServlet: " + e.getMessage());
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
