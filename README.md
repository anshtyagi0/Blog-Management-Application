# Blog Management System

A Java-based desktop application built using **Swing** for managing blog posts. Users can register, log in, create blog posts, view all posts, add comments, and delete their own posts. The system uses a MySQL database to store user data, blog posts, and comments.

## Table of Contents
- [Overview](#overview)
- [Software Requirements](#software-requirements)
- [Hardware Requirements](#hardware-requirements)
- [Project Structure](#project-structure)
- [Installation and Setup](#installation-and-setup)
  - [1. Install Software Dependencies](#1-install-software-dependencies)
  - [2. Set up the Database](#2-set-up-the-database)
  - [3. Clone and Run the Project](#3-clone-and-run-the-project)
- [Features](#features)
- [Usage](#usage)
- [Implementation Details](#implementation-details)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## Overview
This project is a blog management system developed using Java Swing for the front-end, and MySQL for the back-end database. It allows users to manage their blog posts with features like:
- User registration and login
- Creating new blog posts
- Viewing blog posts
- Adding comments to blog posts
- Deleting blog posts
- MySQL database for persistence

## Software Requirements

| Component           | Version          |
|---------------------|------------------|
| Java Development Kit (JDK) | JDK 8+       |
| IDE (IntelliJ IDEA, Eclipse, etc.) | Any |
| MySQL Database Server | 5.7+            |
| MySQL Workbench (optional) | 8.0+        |
| JDBC Driver | MySQL JDBC Connector 8.0+ |
| Git (for version control) | Latest       |

### Java Libraries Used
- **Swing**: For building the GUI (JFrame, JPanel, JTable, etc.)
- **JDBC**: For database connectivity
- **MySQL Connector**: For connecting to the MySQL database

## Hardware Requirements

| Component           | Minimum Specification         |
|---------------------|-------------------------------|
| Processor           | 1.6 GHz or higher             |
| RAM                 | 2 GB or more                  |
| Storage             | 200 MB free space for project |
| Operating System    | Windows/Linux/macOS           |
| Internet            | Required for downloading dependencies and setting up the database |

## Project Structure
The project is divided into several key components:

1. **BlogManagementApp.java**: The main application window and GUI logic.
2. **UserDAO.java**: Handles all database operations related to users (registration, login, etc.).
3. **BlogPostDAO.java**: Handles all database operations related to blog posts (creation, viewing, deletion, etc.).
4. **CommentDAO.java**: Handles all database operations related to comments (adding comments to posts).
5. **DatabaseConnection.java**: Manages database connections using JDBC.

## Installation and Setup

### 1. Install Software Dependencies
- **Java**: Make sure the Java Development Kit (JDK) is installed on your machine. You can download it from [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
  
  Verify Java installation:
  ```bash
  java -version
  ```

- **MySQL**: Install MySQL Server and Workbench. You can download it from [MySQL Downloads](https://dev.mysql.com/downloads/installer/).

  After installation, ensure that the MySQL service is running:
  ```bash
  mysql --version
  ```

### 2. Set up the Database
1. Open MySQL Workbench and create a new database named `blog_management_system`.
   
2. Create the necessary tables for users, blog posts, and comments:
   
```sql
CREATE DATABASE blog_management_system;

USE blog_management_system;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    role VARCHAR(20)
);

CREATE TABLE blog_posts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    user_id INT,
    category_id INT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    content TEXT,
    user_id INT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES blog_posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

### 3. Clone and Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/blog-management-system.git
   cd blog-management-system
   ```

2. Open the project in your preferred IDE.

3. Update the MySQL database credentials in the `DatabaseConnection.java` file:

   ```java
   public class DatabaseConnection {
       private static final String URL = "jdbc:mysql://localhost:3306/blog_management_system";
       private static final String USER = "your_mysql_user";
       private static final String PASSWORD = "your_mysql_password";
   }
   ```

4. Build and run the project from your IDE.

## Features
- **User Registration**: Users can sign up with a username, password, email, and role.
- **Login**: Users must log in to create or manage blog posts.
- **Create Blog Post**: Registered users can create new blog posts with a title, content, and category.
- **View All Posts**: Users can view all blog posts in a window, where they can also add comments or delete their own posts.
- **Add Comment**: Users can add comments to individual blog posts.
- **Delete Blog Post**: Users can delete their own blog posts.

## Usage
1. **Login/Register**: First, register a new user or log in with an existing account.
2. **Create a Post**: Use the 'Create Blog Post' button to add a new post.
3. **View and Comment**: View all blog posts in the 'View All Posts' section, where you can also add comments or delete posts.

## Implementation Details
- **Java Swing**: The graphical interface was built using Swing components like `JFrame`, `JPanel`, `JTable`, `JButton`, `JTextField`, and `JLabel` for user interaction.
- **JDBC**: Java Database Connectivity (JDBC) is used to interact with the MySQL database. SQL queries are executed for user management, post creation, and comment handling.
- **MySQL**: MySQL serves as the back-end database for storing user data, blog posts, and comments. The database schema includes `users`, `blog_posts`, and `comments` tables.

## Screenshots
Will add soon

## Contributing
Feel free to fork this repository and submit pull requests. If you encounter any issues, please open an issue on the repository.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---
