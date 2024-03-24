package Task_4.market;

import Task_4.exceptions.*;

import java.io.*;
import java.util.*;

import static Task_4.market.Market.ObjectType.*;
import static Task_4.market.DataStorage.*;


public class Market {
    final File productsF = new File("src/Task_4/products.txt");
    final File usersF = new File("src/Task_4/users.txt");
    final File ordersF = new File("src/Task_4/orders.txt");
    enum ObjectType {PRODUCT, USER, ORDER}


    public Market() throws IOException {

// инициализация магазина (первичные списки пользователей и товаров
//        new User("Tom", 45, "11111", "male");
//        new User("Bob", 26, "22222", "male");
//        new User("Jim", 53, "33333", "male");
//        new User("John", 40, "44444", "male");
//        new User("Emma", 24, "77777", "female");
//        new User("Anna", 42, "88888", "female");

//        loadData(productsF, products); // ============  отдельный метод инициализации продуктов =================
        // ============ вынесено в отдельный метод инициализации продуктов =================
//        products = new ArrayList<>(List.of(
//                new Product("Milk", 89),
//                new Product("Bread", 26),
//                new Product("Cheese", 125)
//        ));
        List<Order> orders = DataStorage.getOrders();

        // чтение экземпляров классов User, Product и Order
        readData(usersF);
        readData(productsF);
        readData(ordersF);

        // добавление заказов в дополнение к загруженным из файла
        try {
            createOrder((new User("Philly", 22, "666666", "male")).getId(), "notHoliday", 1,4);
            createOrder((new User("Yoko", 22, "88888", "female")).getId(), "March_8", 2,14);

        } catch (UserNotFoudException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }

        // запись экземпляров классов User, Product и Order в соответствующие файлы
        writeData(usersF, users, false);
        writeData(productsF, products, false);
        writeData(ordersF, orders, false);

    }

    /**
     * Метод записи содержимого списков юзеров, продуктов и заказов в
     * соответствующие классы
     * @param file - файл-приемник
     * @param list - список объектов класса
     * @param append - флаг дозаписи в существующий файл
     * @throws IOException - исключение ввода-вывода
     */
    void writeData(File file, List list, boolean append) throws IOException {
        String typeName = file.getName();
    switch (file.getName()) {
        case "products.txt" :
            makeData(file, products, PRODUCT, append);
            break;
        case "users.txt" :
            makeData(file, users, USER, append);
            break;
        case "orders.txt" :
            makeData(file, orders, ORDER, append);
            break;
        default :
            System.out.println("Incorrect file name: " + file.getName());
            break;
    }
}

    /**
     * Метод подготовки данных к записи в файл
     * @param file - файл-приемник
     * @param list - исходный список экземпляров класса
     * @param type - тип сохраняемых объектов
     * @param append - флаг дозаписи в файл
     * @throws IOException
     */
    public void makeData(File file, List list, ObjectType type, boolean append) throws IOException {
        String collectString = "";
        
        switch (type) {
            case PRODUCT:
                for (int i = 0; i < list.size(); i++) {
                    collectString += ((Product) list.get(i)).getId() + "," +
                            ((Product) list.get(i)).getTitle() + "," +
                            ((Product) list.get(i)).getPrice() + "," + "\n";
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
                    writer.write(collectString);
                    writer.flush();
                }
                break;
            case USER:
                for (int i = 0; i < list.size(); i++) {
                    collectString += ((User) list.get(i)).getId() + "," +
                            ((User) list.get(i)).getName() + "," +
                            ((User) list.get(i)).getAge() + "," +
                            ((User) list.get(i)).getPhone() + "," +
                            ((User) list.get(i)).getSex() + "," + "\n";
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
                    writer.write(collectString);
                    writer.flush();
                }
                break;

            case ORDER:
                for (int i = 0; i < list.size(); i++) {
//                    int id, int userId, String day, int productId, int quantity
                    collectString += ((Order) list.get(i)).getId() + "," +
                            ((Order) list.get(i)).getUser().getId() + "," +
                            ((Order) list.get(i)).getDay() + "," +
                            ((Order) list.get(i)).getProductId() + "," +
                            ((Order) list.get(i)).getQuantity() + "," + "\n";
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
                    writer.write(collectString);
                    writer.flush();
                }
                break;

            default : System.out.println("Incorrect collection name: " + type);
        }

}

    /**
     * Метод чтения экземпляров класса из файла-источника
     * @param file - файл-источник
     * @throws IOException - исключение ввода-вывода
     */
    void readData(File file) throws IOException {
        switch (file.getName()) {
            case "products.txt" :
                loadData(file, products, PRODUCT);
                break;
            case "users.txt" :
                loadData(file, users, USER);
                break;
            case "orders.txt" :
                loadData(file, orders, ORDER);
                break;
            default :
                System.out.println("Incorrect file name: " + file.getName());
                break;
        }
    }

    /**
     * Метод преобразования информации из файла-источника в список экземпляров класса
     * @param file - файл-источник
     * @param list - список экземпляров класса
     * @param type - тип класса
     * @throws IOException - исключение ввода-вывода
     */
    public void loadData(File file, List list, ObjectType type) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String str;
        String conrolStr = "";
        String[] prod = new String[3];
        while ((str = br.readLine()) != null) {
            prod = str.split(",");

            switch (type) {
                case PRODUCT -> list.add(new Product(Integer.parseInt(prod[0]), prod[1], Integer.parseInt(prod[2])));
                case USER -> list.add(new User(Integer.parseInt(prod[0]), prod[1], Integer.parseInt(prod[2]), prod[3], prod[4]));
                case ORDER -> list.add(new Order(Integer.parseInt(prod[0]),Integer.parseInt(prod[1]), prod[2], Integer.parseInt(prod[3]), Integer.parseInt(prod[4])));
                default -> System.out.println("Incorrect type.");
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e);
    } catch (IOException e) {
        System.out.println(e.getMessage());
    } catch (NumberFormatException e) {
        System.out.println(Arrays.toString(e.getStackTrace()));
    }

}
    /**
     * Функция создания заказа
     * @param userId - id пользователя
     * @param day - особенности дня покупки
     * @param productId - id товара
     * @param quantity - количество товара
     * @return - id заказа
     * @throws UserNotFoudException
     * @throws ProductNotFoundException
     * @throws QuantityIsNegativeException
     */
    public int createOrder(int userId, String day, int productId, int quantity)
            throws UserNotFoudException, ProductNotFoundException, QuantityIsNegativeException {
        boolean userFound = false;
        boolean productFound = false;
        boolean quantityPositive = false;
        int position = -1;
        int uID = 0;
        Order order = null;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                userFound = true;
                position = i;
                uID = users.get(i).getId();
            }
        }
        if (!userFound) throw new UserNotFoudException("User not found: " + userId);

        long count = products.stream().filter(o -> o.getId() == productId).count();
        if (count == 0) {
            throw new ProductNotFoundException("product with id " + productId + " not found");
        } else {
            productFound = true;
        }
        if (quantity > 0) quantityPositive = true;
        if(userFound && productFound && quantityPositive) {
            order = new Order(uID, day, productId, quantity);
            orders.add(order);
            return order.getId();
        } else {
            return -1;
        }
    }

    public static void addUserToList (User user) {
        users.add(user);
    }
    public void addProductToOrder(int orderId, int productId, int quantity) throws ProductNotFoundException, QuantityIsNegativeException {
        long count = products.stream().filter(o -> o.getId() == productId).count();
        try {
            if (count == 0) throw new ProductNotFoundException("product with id " + productId + " not found");
//        if (!products.contains(productId)) throw new ProductNotFoundException("product with id " + productId + " not found");
            if (quantity <= 0) throw new QuantityIsNegativeException("quantity of product id " + productId + " is negative");
            Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().get();
            order.add(productId, quantity);
        } catch (ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Product> getProducts() {
        return products;
    }
    public static Product getProduct(int productId) {
        Product result = null;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                result = products.get(i);
            }
        }
        return result;
    }

    public static List<Order> getOrders() {
        return orders;
    }
    public static void addUser(String name, int age, String phone, String sex) {
        users.add(new User(name, age, phone, sex));
    }
}