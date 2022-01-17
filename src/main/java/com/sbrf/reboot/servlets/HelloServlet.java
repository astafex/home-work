package com.sbrf.reboot.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/home-work/ask")
public class HelloServlet extends HttpServlet {
    private final AtomicInteger counter = new AtomicInteger(0);

    /**
     * Метод сервлета возвращает сгенерированную страницу HTML с передаваемым параметром, а также счетчиком вызова.
     *
     * @param req  информация передаваемая от клиента - серверу
     * @param resp ответ передаваемый от сервера - клиенту
     * @throws IOException если произошло исключение ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nameParameter = req.getParameter("name");
        if (nameParameter == null) {
            nameParameter = "dear friend";
        }
        PrintWriter pw = resp.getWriter();
        pw.printf("<html>" +
                "<p style=\"font-weight: bold\">Hello, %s!</p>" +
                "<p>Counter = %s...</p>" +
                "</html>", nameParameter, counter.getAndIncrement()
        );
    }
}
