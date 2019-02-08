/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weblab;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MultatulyIM
 */
public class servHandler extends HttpServlet {

    static int type = 0;

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

        String fterm;
        String sterm;
        if (type == 1) {
            fterm = request.getParameter("fterm");
            sterm = request.getParameter("sterm");
        } else {
            fterm = request.getParameter("fmult");
            sterm = request.getParameter("smult");
        }
        long res = 0;
        String message = "";
        if (fterm != null && !fterm.trim().isEmpty() && sterm != null && !sterm.trim().isEmpty()) {
            try {

                if (type == 1) {
                    res =(long) parseInt(fterm) + (long)parseInt(sterm);
                } else {
                    res = (long)parseInt(fterm) * (long)parseInt(sterm);
                }

                if (res <= Integer.MAX_VALUE && res >= Integer.MIN_VALUE) {
                    if (type == 1) {
                        message = "Result add: " + fterm + "+" + sterm + "=" + res;
                    } else {
                        message = "Result multiply: " + fterm + "*" + sterm + "=" + res;
                    }

                } else {
                    message = "Error: The result of calculations is outside the integer range!";
                }

            } catch (NumberFormatException e) {
                res = 0;
                message = "Error: Input numbers must be of type integer! " + e.getMessage();
            }
        } else {
            res = 0;
            message = "Error: you must enter two numbers!";
        }

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Result of calculations</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + message + "</h1>");
//            out.println("<br>");
//            out.println("<h2> max: " + Integer.MAX_VALUE + "</h2>");
//            out.println("<br>");
//            out.println("<h2>min: " + Integer.MIN_VALUE + "</h2>");
            out.println("</body>");
            out.println("</html>");
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
        type = 1;
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
        type = 2;
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
