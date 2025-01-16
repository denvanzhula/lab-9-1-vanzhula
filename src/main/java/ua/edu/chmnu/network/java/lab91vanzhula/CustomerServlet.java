package ua.edu.chmnu.network.java.lab91vanzhula;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.edu.chmnu.network.java.lab91vanzhula.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        ObjectMapper objectMapper = new ObjectMapper();
        List<Customer> customers = List.of(objectMapper.readValue(
                new File(getServletContext().getRealPath("/WEB-INF/customers.json")), Customer[].class
        ));

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Customer Orders</title></head><body>");
        out.println("<h1>Customer Orders</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>Name</th><th>Phone</th><th>Address</th><th>Photo</th><th>Orders (Last Month)</th></tr>");

        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        int maxOrders = customers.stream()
                .mapToInt(customer -> (int) customer.getOrders().stream().filter(order -> order.getDate().isAfter(oneMonthAgo)).count())
                .max().orElse(0);

        for (Customer customer : customers) {
            long recentOrders = customer.getOrders().stream().filter(order -> order.getDate().isAfter(oneMonthAgo)).count();

            String rowStyle = "";
            if (recentOrders == maxOrders && recentOrders > 0) {
                rowStyle = " style='font-weight:bold; color:blue;'";
            } else if (recentOrders == 0) {
                rowStyle = " style='color:red;'";
            }

            out.printf("<tr%s><td>%s</td><td>%s</td><td>%s</td><td><img src='%s' width='50'></td><td>%d</td></tr>",
                    rowStyle, customer.getName(), customer.getPhone(), customer.getAddress(),
                    customer.getPhoto(), recentOrders);
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}
