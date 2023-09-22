package bussinesslayer.entity.user;

public class Member extends User {
    private String role;
    private boolean gender;

    public Member(int id, String name, int age, String email, String password, String phone_number, String address, String role, boolean gender) {
        super(id, name, age, email, password, phone_number, address);
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
