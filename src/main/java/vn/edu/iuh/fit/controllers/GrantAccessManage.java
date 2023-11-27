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

@WebServlet("/grantaccess")
public class GrantAccessManage extends HttpServlet {
    AccountRepository accountRepository = new AccountRepository();
    RoleRepository roleRepository = new RoleRepository();
    GrantAccessRepository grantAccessRepository = new GrantAccessRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roles = roleRepository.getAll();
        List<GrantAccess> grantAccesses = grantAccessRepository.getAll();
        List<Account> accounts = accountRepository.getAll();
        GrantAccess grantAccess = new GrantAccess(accounts.get(0), roles.get(0), 1, "");
        String btnSubmitFeature = req.getParameter("btnSubmitFeature");

        if(btnSubmitFeature.equals("Thêm")){
            String account = req.getParameter("selectAccount");
            String role = req.getParameter("selectRole");
            int isGrant = Integer.parseInt(req.getParameter("selectIsGrant"));
            String note = req.getParameter("note");
            GrantAccess grant = new GrantAccess(accountRepository.getByID(Long.parseLong(account)).get(), roleRepository.getByID(Long.parseLong(role)), isGrant, note);
            boolean result = grantAccessRepository.create(grant);
            if(result){
                grantAccesses = grantAccessRepository.getAll();
            }
        } else if (btnSubmitFeature.equalsIgnoreCase("Cập Nhật")) {
            String account = req.getParameter("selectAccount");
            String role = req.getParameter("selectRole");
            int isGrant = Integer.parseInt(req.getParameter("selectIsGrant"));
            String note = req.getParameter("note");
            grantAccess = grantAccessRepository.getByID(accountRepository.getByID(Long.parseLong(account)).get().getId(),roleRepository.getByID(Long.parseLong(role)).getId());
            grantAccess.setNote(note);
            grantAccess.setIsGrant(isGrant);
            boolean result = grantAccessRepository.update(grantAccess);
            if(result){
                grantAccesses = grantAccessRepository.getAll();
            }
        }
        else if(btnSubmitFeature.equalsIgnoreCase("Xóa")){
            String accountID = req.getParameter("accountID");
            String roleID = req.getParameter("roleID");
            boolean rs = grantAccessRepository.delete(Long.parseLong(accountID),Long.parseLong(roleID));
            if(rs){
                grantAccesses = grantAccessRepository.getAll();
            }
        }
        else if(btnSubmitFeature.equalsIgnoreCase("selectRow")){
            String accountID = req.getParameter("accountID");
            String roleID = req.getParameter("roleID");
            grantAccess = grantAccessRepository.getByID(Long.parseLong(accountID),Long.parseLong(roleID));
        }

        req.setAttribute("grantAccess", grantAccess);
        req.setAttribute("grantAccesses", grantAccesses);
        req.setAttribute("accounts", accounts);
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("GrantAccessManage.jsp").forward(req,resp);

    }
}
