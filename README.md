# Inventory Management System

## 📌 Project Description
This project is a simple **Inventory Management System** developed using **Java, MongoDB, HTML, CSS, and JavaScript**.  
The system helps manage product inventory by allowing users to add, update, delete, and search products.

It provides a dashboard interface where inventory data is displayed in a table format and stored in a MongoDB database.

---

## 🎯 Problem Statement
Managing product inventory manually can be time-consuming and error-prone.  
This project aims to build a digital system that allows users to efficiently manage inventory by performing CRUD operations and tracking product information such as ID, name, quantity, and price.

---

## 🛠 Tools & Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Backend development |
| Spark Framework | REST API server |
| MongoDB | Database |
| MongoDB Compass | Database GUI |
| HTML | Frontend structure |
| CSS | User interface styling |
| JavaScript | Client-side functionality |
| Eclipse IDE | Development environment |
| Git & GitHub | Version control |

---

## ⚙ Features
- Add new products
- View all products
- Update product details
- Delete products
- Search product by ID
- Inventory dashboard
- Data stored in MongoDB database

---

## 🗂 Project Structure
InventorySystem
│
├── src
│   └── inventory
│       ├── api
│       │    └── ProductAPI.java
│       │
│       ├── database
│       │    └── MongoDBConnection.java
│       │
│       ├── model
│       │    └── Product.java
│       │
│       ├── service
│       │    └── ProductService.java
│       │
│       └── MainApp.java
│
├── frontend
│   ├── index.html
│   ├── script.js
│   └── style.css
│
└── README.md
\



## 🗂System Architecture:
The system follows a client-server architecture.
Frontend (HTML, CSS, JavaScript)
        │
        │ HTTP Requests (Fetch API)
        ▼
Backend API (Java + Spark Framework)
        │
        │ Database Operations
        ▼
MongoDB Database
