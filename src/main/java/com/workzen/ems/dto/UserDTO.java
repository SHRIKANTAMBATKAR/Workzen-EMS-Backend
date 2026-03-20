package com.workzen.ems.dto;

public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String role;
    private Boolean active;
    private String mobile;

    public UserDTO() {}

    public UserDTO(String id, String name, String email, String role, Boolean active, String mobile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.active = active;
        this.mobile = mobile;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
}
