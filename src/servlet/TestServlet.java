package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
    private int count;
    static Map<String, String> operations;

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
        request.getParameter("p1");
        count++;
        PrintWriter out = response.getWriter();
        request.getSession().setAttribute("count", count);
        operations = new HashMap<>();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Test</title>");
            out.println("<head>");
            out.println("<body>");
            out.println("<h1>Hello</h1>");
            Enumeration i = request.getParameterNames();
            List<String> mass = new ArrayList<>();
            while (i.hasMoreElements()) {
                mass.add((String) i.nextElement());
            }
            i = request.getParameterNames();
            while (i.hasMoreElements()) {
                out.println("<h3>" + i.nextElement() + "</h5>");
            }
            out.println("<h4>******************************************************************</h4>");
            out.println("<h4>" + mass.get(0) + "</h4>");
            out.println("<h4>" + request.getParameter(mass.get(0)) + "</h4>");
            out.println("<h4>" + request.getParameter(mass.get(1)) + "</h4>");
            out.println("<h4>" + request.getParameter(mass.get(2)) + "</h4>");
            out.println("<h4>" + Integer.parseInt(request.getParameter(mass.get(0))) + "</h4>");
            out.println("<h4>******************************************************************</h4>");
            switch (request.getParameter(mass.get(2))) {
                case "plus": {
                    out.println("plus");
                    out.println("<h4>" + (Double.parseDouble(request.getParameter(mass.get(0))) + Double.parseDouble(request.getParameter(mass.get(1)))) + "</h4>");
                    operations.put(request.getSession().getId(), "plus" + (Double.parseDouble(request.getParameter(mass.get(0))) + Double.parseDouble(request.getParameter(mass.get(1)))) + "");
                    break;
                }
                case "-": {
                    out.println("difference");
                    out.println("<h4>" + (Double.parseDouble(request.getParameter(mass.get(0))) - Double.parseDouble(request.getParameter(mass.get(1)))) + "</h4>");
                    operations.put(request.getSession().getId(), "difference" + (Double.parseDouble(request.getParameter(mass.get(0))) + Double.parseDouble(request.getParameter(mass.get(1)))) + "");
                    break;
                }
                case "/": {
                    out.println("ratio");
                    out.println("<h4>" + (Double.parseDouble(request.getParameter(mass.get(0))) / Double.parseDouble(request.getParameter(mass.get(1)))) + "</h4>");
                    operations.put(request.getSession().getId(), "ratio" + (Double.parseDouble(request.getParameter(mass.get(0))) + Double.parseDouble(request.getParameter(mass.get(1)))) + "");
                    break;
                }
                case "*": {
                    out.println("multiply");
                    out.println("<h4>" + (Double.parseDouble(request.getParameter(mass.get(0))) * Double.parseDouble(request.getParameter(mass.get(1)))) + "</h4>");
                    operations.put(request.getSession().getId(), "multiply" + (Double.parseDouble(request.getParameter(mass.get(0))) + Double.parseDouble(request.getParameter(mass.get(1)))) + "");
                    break;
                }
                default: {
                    out.println("You have not selected the operation or it's invalid: " + request.getParameter(mass.get(2)));
                    operations.put(request.getSession().getId(), "Invalid operation:" + request.getParameter(mass.get(2)));
                }
            }
            out.println("<h4>******************************************************************</h4>");
            for (Map.Entry<String, String> entry : operations.entrySet()) {
                out.println("<h4>" + entry.getKey() + " : " + entry.getValue() + "</h4>");
            }
            out.println("<h4>******************************************************************</h4>");
            out.println("<h4>" + request.getSession().getAttribute("count") + "</h4>");
            out.println("<h4>" + request.getSession().getId() + "</h4>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}
