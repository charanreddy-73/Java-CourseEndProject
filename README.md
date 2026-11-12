
# Online Shopping Cart and Order Processing System


## 1. Project Overview

The **Online Shopping Cart and Order Processing System** is a Java-based console application designed to manage customer shopping carts, product details, price calculations, and order processing.

The project demonstrates the practical implementation of Java Collections Framework, Lambda Expressions, Functional Interfaces, Serialization, Deserialization, and Multithreading.

The application allows multiple customers to add products to their carts, calculate total and average prices, filter products based on price and category, and save and restore cart information using file serialization.

## 2. Objectives

- To implement an online shopping cart using Java.
- To use `ArrayList` for maintaining customer cart items.
- To use `Map` for storing product details and prices.
- To calculate the total and average price of products in a cart.
- To implement Lambda Expressions and Functional Interfaces for product filtering.
- To use Serialization and Deserialization for saving and restoring customer cart information.
- To demonstrate multithreading for processing multiple customers.
- To evaluate the suitability and performance of List and Map collections for different shopping operations.

## 3. Technologies and Concepts Used

| Technology / Concept | Purpose |
|---|---|
| Java | Core programming language |
| ArrayList | Stores customer cart items |
| Map | Maintains product names and prices |
| Lambda Expressions | Implements product filtering |
| Functional Interface | Defines custom filtering operations |
| Stream API | Processes and filters cart products |
| Serialization | Saves cart information |
| Deserialization | Restores saved cart information |
| Multithreading | Processes multiple customers |
| Synchronization | Controls access to shared Scanner input |

## 4. Project Structure

```text
OnlineShoppingCartProject/
│
├── ShoppingSystem.java
│
├── customer1.ser
├── customer2.ser
└── customer3.ser
```

**Note:** The `.ser` files are generated automatically when the application runs. They contain serialized customer cart information.

## 5. Main Components

### A. OnlineShoppingCart

This class manages the shopping cart of an individual customer.

**Key functionalities:**
- Uses `ArrayList` to store product names.
- Provides `addProduct()` to add products to the cart.
- Provides `getCart()` to return an unmodifiable view of cart items.
- Implements `Serializable` to support object serialization.
- Uses `saveCart()` to save cart information into a file.
- Uses `restoreCart()` to retrieve previously saved cart information.

### B. OrderProcessingSystem

This class manages product prices and order calculations.

A Map stores the available products and their prices.

| Product | Price |
|---|---:|
| Laptop | ₹50,000 |
| Headset | ₹25,000 |
| Iphone | ₹94,000 |

**Key functionalities:**
- Maintains product prices using a Map.
- Uses `calculateTotal()` to calculate the total cart value.
- Uses Java Stream API and `mapToDouble()` to process product prices.

### C. ProductFilter

A custom Functional Interface is defined using the `@FunctionalInterface` annotation.

```java
@FunctionalInterface
interface ProductFilter {
    boolean genFilter(String item, double value);
}
```

It accepts a product name and a numeric value and returns a Boolean result.

Two Lambda Expressions are implemented:

**1. Price-Based Filtering**

Displays products whose prices are greater than the average cart price.

**2. Category-Based Filtering**

Displays display devices such as Laptop and Iphone.

### D. ShoppingSystem

This is the main class that controls application execution.

**Key functionalities:**
- Accepts customer product inputs.
- Validates entered product names.
- Calculates total and average cart prices.
- Filters products using Lambda Expressions.
- Saves and restores customer carts.
- Creates three customer threads.
- Uses synchronization to control access to shared Scanner input.

## 6. Java Concepts Implemented

| Java Concept | Implementation |
|---|---|
| ArrayList | Stores products in customer carts |
| Map | Stores product names and prices |
| Collections | Provides an unmodifiable cart view |
| Stream API | Calculates totals and filters products |
| Lambda Expressions | Implements price and category filters |
| Functional Interface | ProductFilter |
| Serialization | Saves shopping cart objects |
| Deserialization | Restores shopping cart objects |
| Multithreading | Processes three customers |
| Synchronization | Controls shared input access |

## 7. Program Execution Flow

1. The application starts and creates three customer threads.
2. Each customer is prompted to enter the number of products.
3. Customers enter product names.
4. The system validates products using the product price Map.
5. Valid products are added to the customer's ArrayList cart.
6. The system calculates the total and average product prices.
7. Lambda Expressions filter products based on:
   - Products priced above the average.
   - Display devices belonging to the specified category.
8. The customer's cart is serialized and saved in a `.ser` file.
9. The saved cart is deserialized and restored.
10. The restored cart is displayed to the customer.
11. After all three threads complete, the application terminates.

## 8. Sample Output

```text
Customer 1

Enter number of products: 3

Enter product 1 (Laptop, Iphone, Headset):
Laptop

Enter product 2 (Laptop, Iphone, Headset):
Headset

Enter product 3 (Laptop, Iphone, Headset):
Iphone

Customer 1 Cart:
[Laptop, Headset, Iphone]

Total price: Rs. 169000.0
Average price: Rs. 56333.333333333336

Products above average price:
Iphone

Display devices:
Laptop
Iphone

Cart saved successfully: customer1.ser

Restored Customer 1 Cart:
[Laptop, Headset, Iphone]

All customer orders processed.
```

**Note:** The actual execution order of the three customer threads may vary.

## 9. Advantages

- Simple and user-friendly console interface.
- Efficient product price lookup using Map.
- Flexible cart management using ArrayList.
- Dynamic product filtering using Lambda Expressions.
- Reusable filtering logic through a Functional Interface.
- Persistent cart storage using Serialization.
- Restoration of customer cart information using Deserialization.
- Concurrent processing of multiple customers using threads.

## 10. Limitations

- The application supports only three predefined products.
- Product information is stored in memory and cannot be dynamically updated.
- Customer information is not stored permanently.
- The application does not include payment processing.
- It does not implement inventory or stock management.
- The application uses console-based input rather than a graphical interface.

## 11. How to Run the Project

### Prerequisites

- Java Development Kit (JDK 11 or later).
- Any Java-supported IDE, such as IntelliJ IDEA, Eclipse, or VS Code.

### Step 1: Clone the Repository

```bash
git clone https://github.com/charanreddy-73/Java-CourseEndProject/
```

### Step 2: Navigate to the Project Directory

```bash
cd OnlineShoppingCartProject
```

### Step 3: Compile the Java File

```bash
javac ShoppingSystem.java
```

### Step 4: Run the Application

```bash
java ShoppingSystem
```

### Step 5: Enter Customer Details

Enter the number of products and select products from the available options:

- Laptop
- Iphone
- Headset

The system will automatically calculate prices, filter products, and save and restore customer carts.

## 12. Conclusion

The Online Shopping Cart and Order Processing System demonstrates the practical implementation of important Java programming concepts in a real-world shopping scenario.

It combines ArrayList and Map collections for efficient data management, Lambda Expressions and Functional Interfaces for product filtering, and Serialization and Deserialization for persistent cart storage.

The project also demonstrates multithreading and synchronization to process multiple customers safely through a shared input resource.

Overall, this project provides an understanding of how Java Collections Framework and modern Java programming features can be applied to design and evaluate a basic online shopping cart and order processing application.

---

**Project Title:** Online Shopping Cart and Order Processing System

**Programming Language:** Java
