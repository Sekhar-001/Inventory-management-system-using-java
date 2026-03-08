package inventory;

import inventory.api.ProductAPI;

public class MainApp {

    public static void main(String[] args) {

        ProductAPI.startServer();

        System.out.println("Server running at http://localhost:8080");

    }
}