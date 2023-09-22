package bussinesslayer.entity.user;

public abstract class User {
    protected int id;
    protected String name;
    protected int age;
    protected String email;
    protected String password;
    protected String phone_number;
    protected String address;

    public User(int id, String name, int age, String email, String password, String phone_number, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;

        this.phone_number = phone_number;
        this.address = address;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
