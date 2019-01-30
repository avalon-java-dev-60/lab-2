/*
Задача 2. Калькулятор. Определите веб-форму, содержащую две формы отправки.Первая форма отправки содержит два текстовых поля ввода и кнопки "Сложить" и "Очистить". Данные этой формы должны отпавляться методом GET, а сервер должен возвращать сумму введённых чисел. Вторая форма отправки также содержит два текстовых поля ввода и кнопки "Умножить" и "Очистить". Данные этой формы должны отпавляться методом ЗЩЫЕ, а сервер должен возвращать произведение введённых чисел. В случае, когда результат вычисления не может быть корректно отображаться (например, при вводе не числовых значений или при выходе результата за пределы представления типов данных) сервер должен вернуть сообщение с описанием ошибки.
 */
package process;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ella
 */
public class Process extends HttpServlet {

    private String wrongMessage;
    private String wrongResultType = "<span style='color:red;'>"
            + "Результат выходит за пределы типа int</span>";
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String first;
    private String second;
    private int cleanFirst;
    private int cleanSecond;

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
        initialization(request, response);
        setEncoding();
        if (!isValid()) {
            String text = "<span style='color:red;'>"
                    + wrongMessage + "</span>";
            print(text);
            return;
        }
        if (Integer.MAX_VALUE - cleanFirst < cleanSecond) {
            print(wrongResultType);
            return;
        }
        print(cleanFirst + cleanSecond);
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
        initialization(request, response);
        setEncoding();
        if (!isValid()) {
            String text = "<span style='color:red;'>"
                    + wrongMessage + "</span>";
            print(text);
            return;
        }
        if (Integer.MAX_VALUE/(float)cleanFirst < cleanSecond) {
            print(wrongResultType);
            return;
        }
        print(cleanFirst * cleanSecond);

    }

    private void initialization(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        first = request.getParameter("first");
        second = request.getParameter("second");
    }

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void setEncoding()
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
    }

    private boolean isValid() {
        if (first.trim().isEmpty() || second.trim().isEmpty()) {
            wrongMessage = "Одно из полей не заполнено";
            return false;
        }
        try {
            cleanFirst = Integer.parseInt(first);
            cleanSecond = Integer.parseInt(second);
        } catch (NumberFormatException e) {
            wrongMessage = "Одно из полей не является числом";
            return false;
        }
        return true;
    }

    private void print(Object text) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<h3>Сложение</h3>\n"
                    + "        <form action=\"process\" method=\"GET\">\n"
                    + "            <label>Перове слагаемое:</label><br>\n"
                    + "            <input type=\"text\" name=\"first\"><br><br>\n"
                    + "            <label>Второе слагаемое:</label><br>\n"
                    + "            <input type=\"text\" name=\"second\"><br><br>\n"
                    + "            <button type=\"submit\" name=\"sum\" value=\"1\">Сложить</button>\n"
                    + "            <button type=\"reset\">Очистить</button>\n"
                    + "        </form><br><br>\n"
                    + "        <h3>Умножение</h3>\n"
                    + "        <form action=\"process\" method=\"POST\">\n"
                    + "            <label>Первый множетель:</label><br>\n"
                    + "            <input type=\"text\" name=\"first\"><br><br>\n"
                    + "            <label>Второй множетель:</label><br>\n"
                    + "            <input type=\"text\" name=\"second\"><br><br>\n"
                    + "            <button type=\"submit\" name=\"mult\" value=\"1\">Умножить</button>\n"
                    + "            <button type=\"reset\">Очистить</button>\n"
                    + "        </form><br><br>"
                    + "<div style='position:absolute; top: 20px; right:20px; text-align: right'>"
                    + "<h3>Результат:</h3>" + text + "</div>");
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
