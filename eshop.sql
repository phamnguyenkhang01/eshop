use test;

CREATE TABLE if NOT EXISTS product (
    pid int(8) NOT NULL AUTO_INCREMENT,
    name varchar(128) NOT NULL,
    price float NOT NULL,
    quantity int NOT NULL,
    image varchar(128) NOT NULL,
    PRIMARY KEY (pid)
);

INSERT INTO product(name, price, quantity, image) VALUES("Gaming Laptop", 1200.00, 10, "images/laptop.jpg");
INSERT INTO product(name, price, quantity, image) VALUES("GPU 3080 Ti", 1949.86, 10, "images/gpu.jpg");
INSERT INTO product(name, price, quantity, image) VALUES("Mechanical Keyboard", 89.99, 10, "images/keyboard.jpg");
INSERT INTO product(name, price, quantity, image) VALUES("Condenser Microphone", 231.18, 10, "images/microphone.jpg");
INSERT INTO product(name, price, quantity, image) VALUES("Curved Monitor", 325.20, 10, "images/monitor.jpg");
INSERT INTO product(name, price, quantity, image) VALUES("Wireless Mouse", 38.99, 10, "images/mouse.jpg");
INSERT INTO product(name, price, quantity, image) VALUES("Bluetooth Speaker", 109.31, 10, "images/speaker.jpg");
INSERT INTO product(name, price, quantity, image) VALUES("USB HD Camera", 205.49, 10, "images/camera.jpg");



CREATE TABLE if NOT EXISTS productOrder (
    oid int(8) NOT NULL AUTO_INCREMENT,
    description varchar(512) NOT NULL,
    total float NOT NULL,
    date_time timestamp NOT NULL,
    PRIMARY KEY (`oid`)
);

CREATE TABLE if NOT EXISTS orderDetails (
    oid int(8) NOT NULL,
    pid int(8) NOT NULL,
    PRIMARY KEY(oid, pid),
    quantity int NOT NULL,
    FOREIGN KEY (oid) REFERENCES productOrder(oid),
    FOREIGN KEY (pid) REFERENCES product(pid)
);