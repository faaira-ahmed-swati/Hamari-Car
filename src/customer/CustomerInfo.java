package customer;
//customer class
public class CustomerInfo {
//    attributes
    String name;
    String password;
    int age;
    String phone;
    String DOB;
    String email;
//    constructor
    public CustomerInfo(String name,String password,int age,String phone,String DOB,String email){
        this.name=name;
        this.password=password;
        this.age=age;
        this.DOB=DOB;
        this.email=email;
        this.phone=phone;
    }
//getters and setters
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getDOB() {
        return DOB;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
