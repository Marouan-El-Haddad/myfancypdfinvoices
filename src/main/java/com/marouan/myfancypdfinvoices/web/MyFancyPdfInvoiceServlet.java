package com.marouan.myfancypdfinvoices.web;

import com.marouan.myfancypdfinvoices.service.InvoiceService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MyFancyPdfInvoiceServlet extends HttpServlet {

    private final transient InvoiceService invoiceService = new InvoiceService();
    private final transient ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) {
        switch (request.getRequestURI().toLowerCase()) {
            case "/" -> writeResponse(response, "text/html; charset=UTF-8",
                    """
                            <html lang="en"><body>
                                <h1>Hello World</h1>
                                <p>This is my very first, embedded Tomcat, HTML Page!</p>
                            </body>
                            </html>
                            """);
            case "/invoices" -> {
                try {
                    writeResponse(response, "application/json; charset=UTF-8",
                            objectMapper.writeValueAsString(invoiceService.findAll()));
                } catch (JsonProcessingException _) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
            default -> response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) {
        if (request.getRequestURI().equalsIgnoreCase("/invoices")) {
            var userId = request.getParameter("user_id");
            int amount;
            try {
                amount = Integer.parseInt(request.getParameter("amount"));
            } catch (NumberFormatException _) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            var invoice = invoiceService.create(userId, amount);
            try {
                writeResponse(response, "application/json; charset=UTF-8",
                        objectMapper.writeValueAsString(invoice));
            } catch (JsonProcessingException _) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void writeResponse(HttpServletResponse response, String contentType, String body) {
        response.setContentType(contentType);
        try {
            response.getWriter().print(body);
        } catch (IOException _) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
