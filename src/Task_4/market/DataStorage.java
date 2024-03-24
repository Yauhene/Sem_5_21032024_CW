package Task_4.market;

import java.util.*;

abstract class DataStorage {
    static List<User> users = new ArrayList<>();
    static List<Product> products = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        DataStorage.users = users;
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static void setProducts(List<Product> products) {
        DataStorage.products = products;
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static void setOrders(List<Order> orders) {
        DataStorage.orders = orders;
    }
}
