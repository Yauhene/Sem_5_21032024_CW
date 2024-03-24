package Task_4.market;

import java.util.*;

import Task_4.market.Market.*;

import static Task_4.market.Market.addUserToList;

//import static market.Market.users;

public class User {
    private static int count = 0;
    private int id;
    private String name;
    private int age;
    private String phone;
    public enum Gender { male, female };
    private Gender sex;



    public User(String name, int age, String phone, String sex) {
        this.id = ++count;
        this.name = name;
        this.age = age;
        this.sex = Gender.valueOf(sex);
        this.phone = phone;
        addUserToList(this);
    }
// --- для создания списка из файла (id уже есть) ---------
    public User(int id, String name, int age, String phone, String sex) {
        this.id = id;
        ++count;
        this.name = name;
        this.age = age;
        this.sex = Gender.valueOf(sex);
        this.phone = phone;
//        addUserToList(this);
    }

    public static User getUserById(int id) {
        User result = null;
        List<User> usersList = new ArrayList<>();
        usersList = Market.getUsers();
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getId() == id) {
                result = usersList.get(i);
                break;
            }
        }
        return result;
    }
    public int getId() {
        return id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex =  Gender.valueOf(sex);
    }
    @Override
    public String toString() {
        return "User name ='" + name + '\'' +
                ", gender = " + sex +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name) && Objects.equals(phone, user.phone) && sex == user.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, phone, sex);
    }
}