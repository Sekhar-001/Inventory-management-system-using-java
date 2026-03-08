package inventory.service;

import inventory.database.MongoDBConnection;
import inventory.model.Product;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ProductService {

    private MongoCollection<Document> collection;

    public ProductService() {

        MongoDatabase database = MongoDBConnection.getDatabase();
        collection = database.getCollection("products");

    }

    // ADD PRODUCT
    public void addProduct(Product product) {

        Document doc = new Document("productId", product.getProductId())
                .append("name", product.getName())
                .append("quantity", product.getQuantity())
                .append("price", product.getPrice());

        collection.insertOne(doc);

        System.out.println("Product Added Successfully!");
    }

    // UPDATE STOCK
    public void updateProduct(int id, String name, int qty, double price) {

        Document query = new Document("productId", id);

        Document update = new Document("$set",
                new Document("name", name)
                .append("quantity", qty)
                .append("price", price));

        collection.updateOne(query, update);

        System.out.println("Product Updated Successfully!");
    }

    // DELETE PRODUCT
    public void deleteProduct(int productId) {

        Document query = new Document("productId", productId);

        collection.deleteOne(query);

        System.out.println("Product Deleted Successfully!");
    }

    // GET ALL PRODUCTS 
    public String getAllProducts() {

        StringBuilder json = new StringBuilder("[");

        for (Document doc : collection.find().sort(new Document("productId", 1))) {

            json.append("{")
                .append("\"productId\":").append(doc.getInteger("productId")).append(",")
                .append("\"name\":\"").append(doc.getString("name")).append("\",")
                .append("\"quantity\":").append(doc.getInteger("quantity")).append(",")
                .append("\"price\":").append(doc.getDouble("price"))
                .append("},");

        }

        if(json.length() > 1)
            json.deleteCharAt(json.length() - 1);

        json.append("]");

        return json.toString();
    }

    // SEARCH PRODUCT
    public String getProduct(int productId) {

        Document query = new Document("productId", productId);

        Document doc = collection.find(query).first();

        if (doc == null) {
            return "{}";
        }

        String json = "{"
                + "\"productId\":" + doc.getInteger("productId") + ","
                + "\"name\":\"" + doc.getString("name") + "\","
                + "\"quantity\":" + doc.getInteger("quantity") + ","
                + "\"price\":" + doc.getDouble("price")
                + "}";

        return json;
    }

}