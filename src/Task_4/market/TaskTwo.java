package Task_4.market;
import Task_4.exceptions.*;

import java.io.*;
import java.util.*;

//import static Task_4.market.Market.orders;


public class TaskTwo {

    public static void main(String[] args) throws UserNotFoudException, QuantityIsNegativeException, ProductNotFoundException, IOException {
        Market market = new Market();
        List<User> users = market.getUsers();
        int orderId1 = 0;
        int orderId2 = 0;
        List<Product> products = market.getProducts();

        System.out.println("Users list:  ------------------------------"); // вывод списка пользователей
        for (int i = 0; i < users.size(); i++) {
            System.out.println("User id: " + users.get(i).getId() + " ===> " + users.get(i));
        }

        System.out.println();
        System.out.println("Products list:  ------------------------------"); // вывод списка продуктов
        for (int j = 0; j < products.size(); j++) {
            System.out.println("Product id " + products.get(j).getId() + " ===> " + products.get(j).getId() + ", " + products.get(j).getTitle() + ", " + products.get(j).getPrice());
        }
        // создание заказов
//        buy(market, 1, 1, 15, "February_23");
//        buy(market, 4, 15, 15, "February_23"); // некорректный id продукта
//        buy(market, 4, 2, 15, "February_23");
//
//        buy(market, 1, 1, -2, "NewYear"); // некорректное количество в заказе
//        buy(market, 1, 1, 2, "NewYear");
//        buy(market, 4, 1, 15, "NewYear");

        // альтернативное создание заказов одновременно с созданием пользователей
        System.out.println("===========================================");




        // вывод заказов
        System.out.println();
        System.out.println("Orders list:  -----------------------------------------------------------------------------------------");
        List<Order> ordersList = Market.getOrders();
        String strOut = "";
        for (int m = 0; m < ordersList.size(); m++) {
            Order.printOrder(ordersList.get(m));
        }
//        String typeStr = Product.class.getTypeName().getClass().getTypeName();
//        System.out.println(typeStr);
//        System.out.println("toString: ===========================");
//        String foFile;

//        for (int i = 0; i < ordersList.size(); i++) {
//            foFile =ordersList.get(i).getId() + "," +
//                    ordersList.get(i).getUser().getId() + "," +
//                    ordersList.get(i).getDay() + "," +
//                    ordersList.get(i).getProductId() + "," +
//                    ordersList.get(i).getQuantity() + ",";
//            System.out.println(foFile);
//            System.out.println();
//
//        }
    }

    /**
     * Метод создания заказа
     * @param market - экземпляр класса Market
     * @param userId - id пользователя
     * @param productId - номер продукта
     * @param quantity - количество продукта
     * @param day - особенности дня формирования заказа
     * @throws UserNotFoudException - исключение на несуществующего юзера
     * @throws ProductNotFoundException - исключение на несуществующий продукт
     * @throws QuantityIsNegativeException - исключение на попытку ввода отрицательного числа продукта в заказ
     */
    public static void buy(Market market, int userId, int productId, int quantity, String day)
            throws UserNotFoudException, ProductNotFoundException, QuantityIsNegativeException {
        try {
            int orderId = market.createOrder(userId, day, productId, quantity);
            market.addProductToOrder(orderId, productId, quantity);
        } catch (UserNotFoudException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }
    }

}
