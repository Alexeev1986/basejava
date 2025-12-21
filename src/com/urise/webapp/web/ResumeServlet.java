package com.urise.webapp.web;

import com.urise.webapp.exception.StorageException;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResumeServlet extends HttpServlet {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error load org.postgresql.Driver", e);
        }
    }

    private final ResumeService resumeService = new ResumeService();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        /* String name = request.getParameter("name");
        response.getWriter().write(name == null ? "Helo World" : "Hello " + name + "!");*/
        response.getWriter().write("""
                                    <html lang="ru">
                                    <style>
                                    table, th, td {
                                      border:2px solid black;
                                      text-align: center;
                                    }
                                    </style>
                                    <body>
                                    <h2 style="text-align: center;">Resume table</h2>
                                    <table style="width:40%; margin: 0 auto;">
                                      <tr>
                                        <th>UUID</th>
                                        <th>FULL_NAME</th>
                                      </tr>
                                    """);
        Map<String, String> resumes = resumeService.getResumes();
        for (Map.Entry<String, String> e : resumes.entrySet()) {
            response.getWriter().write("<tr>");
            response.getWriter().write("<td>" + e.getKey() + "</td>");
            response.getWriter().write("<td>" + e.getValue() + "</td>");
            response.getWriter().write("</tr>");
        }
        response.getWriter().write("""        
                                    </table>
                                    </body>
                                    </html>
                                   """);
    }
}
