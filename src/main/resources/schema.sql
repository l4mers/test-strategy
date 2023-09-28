
CREATE TABLE Login (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         login_id INT,
                         email VARCHAR(255),
                         password VARCHAR(255),
                         FOREIGN KEY (login_id) REFERENCES UserInfo(id)
);


CREATE TABLE UserInfo (
                      id INT,
                      name VARCHAR(255),
                      age INT,
                      balance_id INT,
                      FOREIGN KEY (balance_id) REFERENCES Balance(id)
);


CREATE TABLE Balance (
                     id INT PRIMARY KEY AUTO_INCREMENT,
                     balance INT
);
