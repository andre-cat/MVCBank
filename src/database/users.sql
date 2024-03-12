CREATE TABLE Users (
id BIGINT,
username VARCHAR(30) NOT NULL,
password VARCHAR(30) NOT NULL,
`name` VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
phone BIGINT,
email VARCHAR(50),
PRIMARY KEY (id)
);

INSERT INTO Users (id, username, password, name, last_name, phone, email)
VALUES (1, 'username', 'passworrd','soila','cerda',3002557629,'hi@gmail.com');