create DATABASE IF NOT EXISTS test;
CREATE TABLE user (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(32) ,
  age INT(10),
  address VARCHAR(200)
);

INSERT INTO user (name, age, address) VALUES ('user1', 26, '山东');
INSERT INTO user (name, age, address) VALUES ('user2', 26, '山东');
INSERT INTO user (name, age, address) VALUES ('user3', 26, '山东');
INSERT INTO user (name, age, address) VALUES ('user4', 26, '山东');
INSERT INTO user (name, age, address) VALUES ('user5', 26, '山东');
INSERT INTO user (name, age, address) VALUES ('user6', 26, '山东');
INSERT INTO user (name, age, address) VALUES ('user7', 26, '山东');