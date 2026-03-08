package inventory.api;

import static spark.Spark.*;

import inventory.service.ProductService;
import inventory.model.Product;

public class ProductAPI {

    public static void startServer() {

        port(8080);

        // CORS Configuration
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers", "*");
        });

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        ProductService service = new ProductService();

        // Test API
        get("/test", (req,res) -> "Inventory API Running");

        // Get all products
        get("/products", (req,res) -> {

            res.type("application/json");

            return service.getAllProducts();

        });

        // Add product
        post("/addProduct", (req,res) -> {

            int id = Integer.parseInt(req.queryParams("id"));
            String name = req.queryParams("name");
            int qty = Integer.parseInt(req.queryParams("qty"));
            double price = Double.parseDouble(req.queryParams("price"));

            Product p = new Product(id, name, qty, price);

            service.addProduct(p);

            return "Product Added Successfully";

        });

        put("/updateProduct",(req,res)->{

            int id=Integer.parseInt(req.queryParams("id"));
            String name=req.queryParams("name");
            int qty=Integer.parseInt(req.queryParams("qty"));
            double price=Double.parseDouble(req.queryParams("price"));

            service.updateProduct(id, name, qty, price);

            return "Product Updated Successfully";

        });

        // Delete product
        delete("/deleteProduct/:id",(req,res)->{

            int id=Integer.parseInt(req.params(":id"));

            service.deleteProduct(id);

            return "Product Deleted";

        });

        // Search product
        get("/search/:id",(req,res)->{

            int id=Integer.parseInt(req.params(":id"));

            res.type("application/json");

            return service.getProduct(id);

        });

    }
}