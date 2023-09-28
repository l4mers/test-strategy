-- Insert test data into Login
INSERT INTO Login (login_id, email, password) VALUES (1, 'person1@hej.se', 'skumtomte');
INSERT INTO Login (login_id, email, password) VALUES (2, 'person2@hej.se', 'fladdermus');
INSERT INTO Login (login_id, email, password) VALUES (3, 'person3@hej.se', 'gynekolog');

-- Insert test data into UserInfo
INSERT INTO UserInfo (name, age, balance_id) VALUES ('Bertil', 83, 10);
INSERT INTO UserInfo (name, age, balance_id) VALUES ('Göran', 42, 11);
INSERT INTO UserInfo (name, age, balance_id) VALUES ('Märit', 99, 12);

-- Insert test data into Balance
INSERT INTO Balance (balance) VALUES (12434);
INSERT INTO Balance (balance) VALUES (87234);
INSERT INTO Balance (balance) VALUES (82364);