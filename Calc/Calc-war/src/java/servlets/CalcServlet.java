/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Victor.Malmygin
 */
public class CalcServlet extends HttpServlet {
    
//    private String first;
//    private String second;
    private String result;

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
        
        Map<String,String[]> map = request.getParameterMap();
        Enumeration<String> names = request.getParameterNames();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + result + "</h1>");
            
//            for (Entry entry : map.entrySet()){
//                out.println(entry.getKey() + " " + ((String[])entry.getValue())[0] + "<br>");
//            }

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
        
        String firststring = request.getParameter("first");
        String secondstring = request.getParameter("second");
       
        if (firststring.matches("[+-]?\\d+")){
            try{
                int first = Integer.parseInt(firststring);
                int second = Integer.parseInt(secondstring);
                
                if ( ( first >=0 && second >=0 && (first + second) > 0 )
                  || ( first < 0 && second >=0 )
                  || ( first >=0 && second < 0 )
                  || ( first < 0 && second < 0 && (first + second) < 0 ) ){
                   int sum = first + second;
                   result = "summ is " + sum;
                }else{
                    result = "result is out of range";
                }
            }catch(NumberFormatException e){
                result = "input values is out of range";
            }
        }else{
            result = "input values must be a number";
        }
               
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
        
        String firststring = request.getParameter("first");
        String secondstring = request.getParameter("second");
       
        if (firststring.matches("[+-]?\\d+")){
            try{
                int first = Integer.parseInt(firststring);
                int second = Integer.parseInt(secondstring);
                
                if ( ( first >=0 && second >=0 && (Integer.MAX_VALUE / first) >= second )
                  || ( first < 0 && second >=0 && (Integer.MIN_VALUE / first * -1) >= second )
                  || ( first >=0 && second < 0 && (Integer.MIN_VALUE / first * -1) <= second )
                  || ( first < 0 && second < 0 && (Integer.MAX_VALUE / first) <= second ) ){
                   int mult = first * second;
                   result = "multiplication is " + mult;
                }else{
                    int coll = (Integer.MIN_VALUE / first * -1);
                    result = "result is out of range " + coll;
                }
            }catch(NumberFormatException e){
                result = "input values is out of range";
            }
        }else{
            result = "input values must be a number";
        }
        
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
