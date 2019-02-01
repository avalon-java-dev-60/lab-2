<%-- 
    Document   : StartPage
    Created on : 20.01.2019, 22:15:42
    Author     : Havok
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Start Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1>This is Lab-2</h1>
        <p>You can sum or multiplicate here </p>
        <form id="sum" method="post">
            <label>Numbers:
                <input type="Num" name="number1"><input type="Num" name="number2"><br />
            </label>
            <button type="SumButton" name="SumButton" onclick="">Sum</button><button type="button" name="Clear1" onclick="resetFormSum()">Clear</button>
            <%if (request.getAttribute("Sum") != null) {
                    out.println("<p>Sum: " + request.getAttribute("Sum") + "</p>");
                }
                if (request.getAttribute("error1") != null) {
                    out.println("<p>" + request.getAttribute("error1") + "</p>");
                }
            %>
        </form>
        <form id="mult" method="post">
            <label>Numbers:
                <input type="Num" name="number3"><input type="Num" name="number4"><br />
            </label>
            <button type="MultiplicateButton" name="MultiplicateButton">Multiplicate</button><button type="button" name="Clear2" onclick="resetFormMult()">Clear</button>
            <%if (request.getAttribute("Multiplicate") != null) {
                    out.println("<p>Multiplicate: " + request.getAttribute("Multiplicate") + "</p>");
                }
                if (request.getAttribute("error2") != null) {
                    out.println("<p>" + request.getAttribute("error2") + "</p>");
                }
            %>
        </form>
        <script>
            function resetFormSum() {
                document.getElementById("sum").reset();
            }
            function resetFormMult() {
                document.getElementById("mult").reset();
            }
        </script>
    </body>
</html>
