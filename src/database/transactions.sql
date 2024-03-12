CREATE TABLE Transactions (
id BIGINT AUTO_INCREMENT,
`value` DOUBLE NOT NULL,
cost DOUBLE NOT NULL,
`date` DATETIME NOT NULL,
description VARCHAR(255),
type ENUM("DEPOSIT","PURCHASE","WITHDRAWAL") NOT NULL,
subtype ENUM("ATM","BRANCH","ACCOUNT","CARDPHYSICALSTORE","CARDONLINESTORE") NOT NULL,
place_id VARCHAR(255),
account_id BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (account_id) REFERENCES Accounts(id)
);

SELECT id, value, cost, date, description, type, subtype, place_id, account_id FROM Transactions;

INSERT INTO Transactions (id, value, cost, date, description, type, subtype, place_id, account_id)
VALUES (1, 0, 0,  '2024-01-01 23:11', 'transaction', 'DEPOSIT', 'ATM', '124343', 1);