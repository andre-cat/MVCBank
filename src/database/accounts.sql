CREATE TABLE Accounts (
id BIGINT AUTO_INCREMENT,
balance DOUBLE NOT NULL,
user_id BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES Users(id)
);

---

SELECT id, balance, user_id FROM Accounts;

INSERT INTO Accounts (id, balance, user_id)
VALUES (1, 0, 1)