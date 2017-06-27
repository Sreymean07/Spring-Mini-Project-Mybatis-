package org.kshrd.model;


public class Role {


    private String fullName;
    private String role;
    private String userName;
    private String password;
    private String confirm_password;
    private String email;


    public Role(String fullName, String role, String userName, String password, String confirm_password, String email) {
        super();
        this.fullName = fullName;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.confirm_password = confirm_password;
        this.email = email;
    }
    public Role() {
        // TODO Auto-generated constructor stub
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirm_password() {
        return confirm_password;
    }
    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
