INSERT INTO customer (name, email, mobile_number, pwd, role, create_dt) VALUES ('Happy','happy@example.com','9876548337', '$2y$12$oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2', 'company', CURDATE());
INSERT INTO customer (name, email, mobile_number, pwd, role, create_dt) VALUES ('Jayati', 'jayati@gmail.com', '+3163392606', '$2a$10$0KbKjtLjBTeMZC4cym7apuesK3sq6sXkUVcFaloSFVTifjJNc6jJW', 'broker', CURDATE());
INSERT INTO authorities (customer_id, name) VALUES (1, 'ROLE_COMPANY');
INSERT INTO authorities (customer_id, name) VALUES (2, 'ROLE_BROKER');
INSERT INTO car (make,mileage,net_price,number_of_doors,version,co2emission,created_at,updated_at,gross_price,model) values ('Hyundai',10000,59000,4,'x25',1.45,CURDATE(),CURDATE(),59000,'creta');
INSERT INTO car (make,mileage,net_price,number_of_doors,version,co2emission,created_at,updated_at,gross_price,model) values ('Hyundai',10000,79000,4,'m25',1.45,CURDATE(),CURDATE(),78000,'verna');