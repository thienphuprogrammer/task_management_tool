package bussinesslayer.entity.user;

public class Admin extends User {
    public Admin() {
    }

    public Admin(int id, String name, int age, String email, String password, String phone_number, String address) {
        super(id, name, age, email, password, phone_number, address);
    }

    public Admin(String name, int age, String email, String password, String phone_number, String address) {
        super(name, age, email, password, phone_number, address);
    }
}
