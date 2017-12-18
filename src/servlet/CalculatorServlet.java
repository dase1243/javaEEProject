package servlet;

import claculator.Operations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CalculatorServlet", urlPatterns = {"/CalculatorServlet"})
public class CalculatorServlet extends HttpServlet {

    public List<String> listOperations;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Test</title>");
        out.println("<head>");
        out.println("<body>");

        try {
            double one = Double.parseDouble(request.getParameter("one"));
            double two = Double.parseDouble(request.getParameter("two"));
            String operation = request.getParameter("operation");

            HttpSession session = request.getSession(true);

            double result = calcResult(operation, one, two);

            if (session.isNew()) {
                listOperations = new ArrayList<>();
            } else {
                listOperations = (ArrayList<String>) session.getAttribute("formula");
            }
            listOperations.add(one + " " + returnOperationSign(operation) + " " + two + " = " + result);
            session.setAttribute("formula", listOperations);

            out.println("<h1>ID вашей сессии равен: " + request.getSession().getId() + "</h1>");
            out.println("<h3>Список операций (всего: " + listOperations.size() + ")</h3>");

            for (String entry : listOperations) {
                out.println("<h3>" + entry + "</h3>");
            }

        } catch (Exception e) {

            out.println("<h3>Invalid params or operation, check validation of it. Error stack: ");
            e.printStackTrace();
            out.println("</h3>");
        } finally {
            out.println("</body>");
            out.println("</html>");
            out.close();
        }

    }

    private String returnOperationSign(String operation) {
        switch (operation) {
            case "add":
                return "+";
            case "subtract":
                return "-";
            case "multiply":
                return "*";
            case "divide":
                return "/";
        }
        return "Invalid operation";
    }

    private double calcResult(String operation, double one, double two) {
        double result = 0;

        switch (operation) {
            case "add":
                result = Operations.add(one, two);
                break;
            case "subtract":
                result = Operations.substract(one, two);
                break;
            case "multiply":
                result = Operations.multiply(one, two);
                break;
            case "divide":
                result = Operations.divide(one, two);
                break;
        }

        return result;
    }
}

