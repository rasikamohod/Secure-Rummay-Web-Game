CREATE DATABASE IF NOT EXISTS rummy;
USE rummy;

CREATE TABLE IF NOT EXISTS users (
  user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL
);

INSERT INTO users (username, email) VALUES("testuser", "testuser@example.com");
INSERT INTO users (username, email) VALUES("testusera", "testusera@example.com");
INSERT INTO users (username, email) VALUES("testuserb", "testuserb@example.com");
INSERT INTO users (username, email) VALUES("testuserc", "testuserc@example.com");
INSERT INTO users (username, email) VALUES("testuserd", "testuserd@example.com");
INSERT INTO users (username, email) VALUES("testusere", "testusere@example.com");