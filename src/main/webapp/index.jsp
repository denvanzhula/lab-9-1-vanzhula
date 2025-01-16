<%@ page import="ua.edu.chmnu.network.java.lab91vanzhula.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Orders</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        .highlight {
            font-weight: bold;
            color: blue;
        }
        .no-orders {
            color: red;
        }
        img {
            width: 50px;
            height: auto;
        }
    </style>
</head>
<body>
<h1>Customer Orders</h1>
<table>
    <tr>
        <th>Name</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Photo</th>
        <th>Orders (Last Month)</th>
    </tr>

    <!-- Статичні приклади для візуалізації -->
    <tr class="highlight">
        <td>John Doe</td>
        <td>+123456789</td>
        <td>123 Main St, Springfield</td>
        <td><img src="https://via.placeholder.com/50" alt="John's Photo"></td>
        <td>5</td>
    </tr>
    <tr class="no-orders">
        <td>Jane Smith</td>
        <td>+987654321</td>
        <td>456 Elm St, Springfield</td>
        <td><img src="https://via.placeholder.com/50" alt="Jane's Photo"></td>
        <td>0</td>
    </tr>
    <tr>
        <td>Michael Brown</td>
        <td>+112233445</td>
        <td>789 Oak St, Springfield</td>
        <td><img src="https://via.placeholder.com/50" alt="Michael's Photo"></td>
        <td>2</td>
    </tr>

    <%
        List<Customer> customers =
                (List<ua.edu.chmnu.network.java.lab91vanzhula.model.Customer>) request.getAttribute("customers");

        if (customers != null) {
            int maxOrders = customers.stream()
                    .mapToInt(c -> (int) c.getOrders().stream()
                            .filter(o -> o.getDate().isAfter(java.time.LocalDate.now().minusMonths(1)))
                            .count())
                    .max()
                    .orElse(0);

            for (ua.edu.chmnu.network.java.lab91vanzhula.model.Customer customer : customers) {
                long recentOrders = customer.getOrders().stream()
                        .filter(o -> o.getDate().isAfter(java.time.LocalDate.now().minusMonths(1)))
                        .count();

                String rowClass = "";
                if (recentOrders == maxOrders && recentOrders > 0) {
                    rowClass = "highlight";
                } else if (recentOrders == 0) {
                    rowClass = "no-orders";
                }
    %>
    <tr class="<%= rowClass %>">
        <td><%= customer.getName() %></td>
        <td><%= customer.getPhone() %></td>
        <td><%= customer.getAddress() %></td>
        <td><img src="<%= customer.getPhoto() %>" alt="Customer Photo"></td>
        <td><%= recentOrders %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>