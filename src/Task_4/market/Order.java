package Task_4.market;

import java.util.*;

import static Task_4.market.User.Gender.female;
import static Task_4.market.User.Gender.male;

public class Order {

    private static int count = 1;
    private int id;
    private int userId;
    private int maxId;

    private Map<Integer,Integer> products;
    private int quantity;
    public enum Holiday {notHoliday, NewYear, March_8, February_23};
    private Holiday day;
    private double discount = 0;
    private int productId;

    public Order(int userId, String day, int productId, int quantity) {
        if (Market.getOrders().size() > 0) {
            this.id = count + 1;
            count++;
        } else {
            this.id = count;
            count++;
        }

        this.day = Holiday.valueOf(day);
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        switch (Holiday.valueOf(day)) {
            case NewYear : {
                this.discount = 20;
                break;
            }
            case March_8 : {
                if (User.getUserById(userId).getSex().equals(female)) {
                    this.discount = 15;
                    break;
                }
            }
            case February_23 : {
                if (User.getUserById(userId).getSex().equals(male)) {
                    this.discount = 15;
                    break;
                }
            }
            default : {
                this.discount = 0;
            }
        }
        products = new HashMap<Integer, Integer>();
    }

    // --- версия для загрузки из файла (id уже есть) ---------
    public Order(int id, int userId, String day, int productId, int quantity) {

        this.id = id;
        ++count;
        this.day = Holiday.valueOf(day);
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        switch (Holiday.valueOf(day)) {
            case NewYear : {
                this.discount = 20;
                break;
            }
            case March_8 : {
                if (User.getUserById(userId).getSex().equals(female)) {
                    this.discount = 15;
                    break;
                }
            }
            case February_23 : {
                if (User.getUserById(userId).getSex().equals(male)) {
                    this.discount = 15;
                    break;
                }
            }
            default : {
                this.discount = 0;
            }
        }
        products = new HashMap<Integer, Integer>();
    }
private static Object findById(int id, List<Object> list) {
    for (Object o : list) {
        if (o instanceof User && id == ((User) o).getId()) {
            return o;
        }
    }
    for (Object o : list) {
        if (o instanceof Product && id == ((Product) o).getId()) {
            return o;
        }
    }
    for (Object o : list) {
        if (o instanceof Order && id == ((Order) o).getId()) {
            return o;
        }
    }
    return null;
}
    public int getQuantity() {
        return quantity;
    }

    public int getProductId() {
        return productId;
    }

    public Holiday getDay() {
        return day;
    }


    public int getId() {
        return id;
    }

    public User getUser() {
        return (User.getUserById(userId));
    }
    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void add (int productId, int quantity) {
        products.put(productId, quantity);
    }

    /**
     * Метод вывода списка заказов
     * @param order
     */
    public static void printOrder(Order order) {
        String offset = " ".repeat(5);

        String strOrder = "Order " + order.getId() + ": ";
        String strUser = offset + "User " + order.getUser().getId() +  ": " + order.getUser().getName() +
                ", " + order.getUser().getAge() + " age, gender " + order.getUser().getSex() + ",  phone " + order.getUser().getPhone();
        String strProduct = offset + "Product " + order.productId + ": " + "title " + Market.getProduct(order.productId).getTitle() +
                ", price " + Market.getProduct(order.productId).getPrice() + ", quantity " + order.quantity;
        String strCalculations = offset + "Calculate of order: day " + order.day + ", " + " discount " + order.discount + "%, FINAL PRICE IS: " +
                Market.getProduct(order.productId).getPrice() + " * " + order.quantity + " * " + (1 - order.discount/100) + " = " +
                Market.getProduct(order.productId).getPrice() * order.quantity * (1 - order.discount/100);

        System.out.println(strOrder);
        System.out.println(strUser);
        System.out.println(strProduct);
        System.out.println(strCalculations);
        System.out.println("-".repeat(100));
    }
    @Override
    public String toString() {
        return "Order{" + getId() +
                "user=" + (User.getUserById(userId)) +
                ", products=" + products +
                '}';
    }
}