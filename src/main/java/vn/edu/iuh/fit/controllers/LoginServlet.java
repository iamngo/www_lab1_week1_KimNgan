package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.db.ConnectionDB;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.Log;
import vn.edu.iuh.fit.models.Role;
import vn.edu.iuh.fit.repository.AccountRepository;
import vn.edu.iuh.fit.repository.LogRepository;
import vn.edu.iuh.fit.repository.RoleRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    AccountRepository accountRepository = new AccountRepository();
    RoleRepository roleRepository = new RoleRepository();
    LogRepository logRepository = new LogRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email =req.getParameter("email");
        String password = req.getParameter("password");
        Optional<Account> account = accountRepository.checkAccount(email,password);

        if(account.get()!=null){
            if(account.get().getEmail().equalsIgnoreCase("admin@gmail.com")){
                List<Account> accounts = accountRepository.getAll();
                Account acc = new Account("","","","",1);
                req.setAttribute("account", acc);
                req.setAttribute("btnSubmit", "Thêm");
                req.setAttribute("accounts", accounts);
                req.getRequestDispatcher("AdminManage.jsp").forward(req,resp);
            }else{
                List<Role> roles = roleRepository.getRolesByAccount(account.get().getId());
                req.setAttribute("account", account.get());
                req.setAttribute("roles", roles);
                req.getRequestDispatcher("AccountInfo.jsp").forward(req,resp);
            }
            Log log = new Log(LocalDateTime.now(),null,"note",account.get());
            logRepository.create(log);

        }else{
            req.getRequestDispatcher("ErrorSignin.jsp");
        }
    }
}
