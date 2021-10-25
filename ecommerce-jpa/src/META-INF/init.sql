INSERT INTO customers (birthday, first_name, last_name, reg_date, status) VALUES (curdate(),'Caleb', 'Hernández', now(), 'ACTIVE');
INSERT INTO customers (birthday, first_name, last_name, reg_date, status) VALUES (curdate(),'Juan', 'Pérez', now(), 'ACTIVE');

INSERT INTO products (name, price, reg_date, status, version) VALUES ('Product 1', 10, now(), 'ACTIVE', 0);
INSERT INTO products (name, price, reg_date, status, version) VALUES ('Product 2', 20, now(), 'ACTIVE', 0);
INSERT INTO products (name, price, reg_date, status, version) VALUES ('Product 3', 30, now(), 'ACTIVE', 0);
INSERT INTO products (name, price, reg_date, status, version) VALUES ('Product 4', 40, now(), 'ACTIVE', 0);
INSERT INTO products (name, price, reg_date, status, version) VALUES ('Product 5', 50, now(), 'ACTIVE', 0);
INSERT INTO products (name, price, reg_date, status, version) VALUES ('Product 6', 60, now(), 'ACTIVE', 0);
INSERT INTO products (name, price, reg_date, status, version) VALUES ('Product 7', 70, now(), 'ACTIVE', 0);
INSERT INTO products (name, price, reg_date, status, version) VALUES ('Product 8', 80, now(), 'ACTIVE', 0);
INSERT INTO products (name, price, reg_date, status, version) VALUES ('Product 9', 90, now(), 'ACTIVE', 0);
INSERT INTO products (name, price, reg_date, status, version) VALUES ('Product 10', 100, now(), 'ACTIVE', 0);

INSERT INTO clasifications (name) VALUES ('Clasification 1');
INSERT INTO clasifications (name) VALUES ('Clasification 2');
INSERT INTO clasifications (name) VALUES ('Clasification 3');

INSERT INTO orders (reg_date, status, total, fk_customer) VALUES (now(), 'ACTIVE', 3850, 1);
INSERT INTO orders (reg_date, status, total, fk_customer) VALUES (now(), 'ACTIVE', 550, 2);

INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (1 , 1,  10, 10, 1, 0);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (2 , 2,  40, 20, 1, 1);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (3 , 3,  90, 30, 1, 2);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (4 , 4,  160, 40, 1, 3);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (5 , 5,  250, 50, 1, 4);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (6 , 6,  360, 60, 1, 5);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (7 , 8,  640, 80, 1, 6);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (8 , 9,  810, 90, 1, 7);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (9, 10, 1000, 100, 1, 8);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (1 , 1,  10, 10, 2, 0);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (2 , 2,  40, 20, 2, 1);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (3 , 3,  90, 30, 2, 2);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (4 , 4,  160, 40, 2, 3);
INSERT INTO order_lines (fk_product, quantity, total, unit_price, fk_order, ctr) VALUES (5 , 5,  250, 50, 2, 4); 


