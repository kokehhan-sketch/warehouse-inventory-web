# Warehouse Inventory Web App
A full-stack inventory management system built with Java, Spring Boot, and vanilla HTML/CSS/JavaScript.

## Features
* **RESTful API:** Built with Spring Boot to handle CRUD operations.
* **Interactive Frontend:** A dynamic HTML/JS dashboard that fetches live data from the API.
* **Design Patterns:** 
  * **Observer Pattern:** Triggers real-time low-stock alerts on the UI.
  * **Command Pattern:** Enables robust `Undo` functionality for all stock movements.

## How to Run Locally
1. Clone the repo.
2. Run `./mvnw spring-boot:run`.
3. Open `http://localhost:8080` in your browser.
