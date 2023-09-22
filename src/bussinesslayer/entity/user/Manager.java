package bussinesslayer.entity.user;

public class Manager {
    private String role;
    private boolean gender;

    public Manager(String role, boolean gender) {
        this.role = role;
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
