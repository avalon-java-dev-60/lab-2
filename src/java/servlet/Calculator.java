package servlet;

import java.io.IOException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Calculator", urlPatterns = {"/Calculator"})
public class Calculator extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("views/Calculator.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void sum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        int i = 0;
        String Num1 = request.getParameter("number1");
        String Num2 = request.getParameter("number2");
        String Num3 = request.getParameter("number3");
        String Num4 = request.getParameter("number4");

        if (((Num1 != null || Num2 != null)) || (Num3 == null && Num4 == null)) {
            if ((Num1.isEmpty() == false) && (Num2.isEmpty() == false)) {
                if (Num1.matches("-?[\\d]+") == true && Num2.matches("-?[\\d]+") == true) {
                    request.setAttribute("number1", Num1);
                    int number1 = Integer.parseInt(Num1);
                    System.out.println(number1);
                    int number2 = Integer.parseInt(Num2);
                    System.out.println(number2);
                    int sum = number1 + number2;
                    System.out.println(sum);
                    request.setAttribute("number1", Num1);
                    request.setAttribute("number2", Num2);
                    request.setAttribute("Sum", sum);
                    requestDispatcher = request.getRequestDispatcher("views/Calculator.jsp");
                    requestDispatcher.forward(request, response);

                } else {
                    String error = "Repeat your input, please input only numeric";
                    request.setAttribute("error1", error);
                    requestDispatcher = request.getRequestDispatcher("views/Calculator.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                String error = "Some fields are empty. Repeat your input";
                request.setAttribute("error1", error);
                requestDispatcher = request.getRequestDispatcher("views/Calculator.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        
    }
    protected void multiplicate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        int i = 0;
        String Num1 = request.getParameter("number1");
        String Num2 = request.getParameter("number2");
        String Num3 = request.getParameter("number3");
        String Num4 = request.getParameter("number4");

        if ((Num3 != null || Num4 != null) || (Num1 == null && Num2 == null)) {
            if ((Num3.isEmpty() == false) && (Num4.isEmpty() == false)) {
                if (Num3.matches("-?[\\d]+") == true && Num4.matches("-?[\\d]+") == true) {
                    request.setAttribute("number3", Num3);
                    int number3 = Integer.parseInt(Num3);
                    System.out.println(number3);
                    int number4 = Integer.parseInt(Num4);
                    System.out.println(number4);
                    int multiplicate = number3 * number4;
                    System.out.println(multiplicate);
                    request.setAttribute("Multiplicate", multiplicate);
                    requestDispatcher = request.getRequestDispatcher("views/Calculator.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    String error = "Repeat your input, please input only numeric";
                    request.setAttribute("error2", error);
                    requestDispatcher = request.getRequestDispatcher("views/Calculator.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                String error = "Some fields are empty. Repeat your input";
                request.setAttribute("error2", error);
                requestDispatcher = request.getRequestDispatcher("views/Calculator.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("SumButton") != null) {
            sum(request, response);
        }
        if (request.getParameter("MultiplicateButton") != null)
            multiplicate(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
