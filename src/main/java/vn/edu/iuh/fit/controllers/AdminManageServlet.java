package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.GrantAccess;
import vn.edu.iuh.fit.models.Role;
import vn.edu.iuh.fit.repository.AccountRepository;
import vn.edu.iuh.fit.repository.GrantAccessRepository;
import vn.edu.iuh.fit.repository.RoleRepository;

import java.io.IOException;
import java.util.List;

@WebServlet("/adminmanage")
public class AdminManageServlet extends HttpServlet {
    AccountRepository accountRepository = new AccountRepository();
    RoleRepository roleRepository = new RoleRepository();
    GrantAccessRepository grantAccessRepository = new GrantAccessRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btnSubmit = req.getParameter("btnSubmit");
        if(btnSubmit.equalsIgnoreCase("Account Manage")){
            List<Account> accounts = accountRepository.getAll();
            req.setAttribute("accounts", accounts);
            Account account = new Account("","","","",1);
            req.setAttribute("account", account);
            req.getRequestDispatcher("AccountManage.jsp").forward(req,resp);
        }
        else if (btnSubmit.equalsIgnoreCase("Grant Access Manage")) {
            List<Account> accounts = accountRepository.getAll();
            req.setAttribute("accounts", accounts);
            List<Role> roles = roleRepository.getAll();
            req.setAttribute("roles",roles);
            List<GrantAccess> grantAccesses = grantAccessRepository.getAll();
            req.setAttribute("grantAccesses", grantAccesses);
            GrantAccess grantAccess = new GrantAccess(accounts.get(0), roles.get(0),1,"");
            req.setAttribute("grantAccess", grantAccess);
            req.getRequestDispatcher("GrantAccessManage.jsp").forward(req,resp);
        }
    }
}
