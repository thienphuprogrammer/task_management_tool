package bussinesslayer.entity.user;

public abstract class User {
    protected int id;
    protected String name;
    protected int age;
    protected String email;
    protected String password;
    protected String phoneNumber;
    protected String address;

    public User(int id, String name, int age, String email, String password, String phone_number, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;

        this.phoneNumber = phone_number;
        this.address = address;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
