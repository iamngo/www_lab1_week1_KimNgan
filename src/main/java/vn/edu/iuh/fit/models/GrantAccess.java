package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;

@Entity
@Table(name = "grant_access")
public class GrantAccess {
    @Id
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "is_grant")
    private int isGrant;
    private String note;

    public GrantAccess(Account account, Role role, int isGrant, String note) {
        this.account = account;
        this.role = role;
        this.isGrant = isGrant;
        this.note = note;
    }

    public GrantAccess() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(int isGrant) {
        this.isGrant = isGrant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "account=" + account +
                ", role=" + role +
                ", isGrant=" + isGrant +
                ", note='" + note + '\'' +
                '}';
    }
}
