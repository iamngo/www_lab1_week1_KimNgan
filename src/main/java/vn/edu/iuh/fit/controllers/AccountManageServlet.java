package vn.edu.iuh.fit.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.repository.AccountRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/accountmanage")
public class AccountManageServlet extends HttpServlet {
    AccountRepository accountRepository = new AccountRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> accounts = null;
        accounts = accountRepository.getAll();
        String btnSubmitFeature = req.getParameter("btnSubmitFeature");
        Optional<Account> account = Optional.of(new Account("", "", "", "", 1));

        if(btnSubmitFeature.equals("Thêm")){
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");
            int status = Integer.parseInt(req.getParameter("status"));

            Account acc = new Account(fullName, password, email, phone, status);
            boolean result = accountRepository.create(acc);
            if(result){
                accounts = accountRepository.getAll();
            }
        }else if(btnSubmitFeature.equalsIgnoreCase("Cập Nhật")) {
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");
            int status = Integer.parseInt(req.getParameter("status"));
            account = accountRepository.getByID(Long.parseLong(req.getParameter("accountID")));
            account.get().setFullName(fullName);
            account.get().setEmail(email);
            account.get().setPassword(password);
            account.get().setPhone(phone);
            account.get().setStatus(status);
            boolean result = accountRepository.update(account.get());
            if (result) {
                accounts = accountRepository.getAll();
            }
        }
        else if(btnSubmitFeature.equalsIgnoreCase("Xóa")){
            boolean rs = accountRepository.delete(Long.parseLong(req.getParameter("accountID")));
            if(rs){
                accounts = accountRepository.getAll();
            }
        }else{
            account = accountRepository.getByID(Long.parseLong(btnSubmitFeature));
        }


        req.setAttribute("accounts",accounts);
        req.setAttribute("account", account.get());
        req.getRequestDispatcher("AccountManage.jsp").forward(req,resp);
    }
}
