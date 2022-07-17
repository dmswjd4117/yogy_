
INSERT INTO users(name, password, phone, email) VALUES('test0', '$2a$10$wT3nhqsvkf8ph9A3Ux4iAuto8KZ4UPSY1yRG65Zke9LICD/qmRNO2', '010-1234-1234', 'test0@gmail.com');
INSERT INTO users(name, password, phone, email) VALUES('test1', '$2a$10$wT3nhqsvkf8ph9A3Ux4iAuto8KZ4UPSY1yRG65Zke9LICD/qmRNO2', '010-2222-2222', 'test1@gmail.com');
INSERT INTO users(name, password, phone, email) VALUES('test2', '$2a$10$wT3nhqsvkf8ph9A3Ux4iAuto8KZ4UPSY1yRG65Zke9LICD/qmRNO2', '010-3333-3333', 'test2@gmail.com');

INSERT INTO owner(name, password, phone, email) VALUES('owner0', '$2a$10$wT3nhqsvkf8ph9A3Ux4iAuto8KZ4UPSY1yRG65Zke9LICD/qmRNO2', '010-0000-000', 'owner00@gmail.com');
INSERT INTO owner(name, password, phone, email) VALUES('owner1', '$2a$10$wT3nhqsvkf8ph9A3Ux4iAuto8KZ4UPSY1yRG65Zke9LICD/qmRNO2', '010-1111-1111', 'owner11@gmail.com');
INSERT INTO owner(name, password, phone, email) VALUES('owner2', '$2a$10$wT3nhqsvkf8ph9A3Ux4iAuto8KZ4UPSY1yRG65Zke9LICD/qmRNO2', '010-2222-1111', 'owner22@gmail.com');
INSERT INTO owner(name, password, phone, email) VALUES('owner3', '$2a$10$wT3nhqsvkf8ph9A3Ux4iAuto8KZ4UPSY1yRG65Zke9LICD/qmRNO2', '010-3333-3333', 'owner33@gmail.com');

INSERT INTO store(name, phone, ownerId, openStatus, category, buildingManagementNum, address) VALUE ('store1', '051-23-123', 1, 'OPEN', 'CAFE', '2611010100100600048001276', 'address detail1');
INSERT INTO store(name, phone, ownerId, openStatus, category, buildingManagementNum, address) VALUE ('store2', '051-12-1111', 2, 'OPEN', 'CAFE', '2611010100100600048001276', 'address detail2');
INSERT INTO store(name, phone, ownerId, openStatus, category, buildingManagementNum, address) VALUE ('store3', '051-12-1111', 3, 'OPEN', 'CHICKEN', '2611010100100600048001276', 'address detail3');
INSERT INTO store(name, phone, ownerId, openStatus, category, buildingManagementNum, address) VALUE ('store4', '051-444-4444', 4, 'OPEN', 'CHICKEN', '2611010100100600048001276', 'address detail4');

-- 대연동 법정동 코드 2123001500
INSERT INTO store_delivery_location(addressCode, addressName, storeId) VALUES (2123001500,'대연동', 1);
INSERT INTO store_delivery_location(addressCode, addressName, storeId) VALUES (2123001500,'대연동', 2);
INSERT INTO store_delivery_location(addressCode, addressName, storeId) VALUES (2123001500,'대연동', 3);
-- 용호동 법정도 코드 2123001600
INSERT INTO store_delivery_location(addressCode,addressName, storeId) VALUES (2123001600,'용호동', 1);
INSERT INTO store_delivery_location(addressCode,addressName, storeId) VALUES (2123001600,'용호동',2);
