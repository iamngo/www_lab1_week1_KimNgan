package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.models.Log;
import vn.edu.iuh.fit.repository.LogRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    LogRepository logRepository = new LogRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btnSubmitFeature = req.getParameter("btnSubmitFeature");
        if(btnSubmitFeature.equalsIgnoreCase("Log out")){
            Optional<Log> log = logRepository.getLogByAccount(Long.parseLong(req.getParameter("accountID")));
            log.get().setLogOut(LocalDateTime.now());
            logRepository.update(log.get());
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }
}
