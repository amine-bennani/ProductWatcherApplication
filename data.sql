CREATE TABLE product (
    sku INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE price (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sku INT,
    price DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (sku) REFERENCES product(sku)
);
-- Insert data into the Product table
INSERT INTO product (sku, name) VALUES ('1', 'spoon');

-- Insert data into the Prices table
INSERT INTO price (sku, date, price) VALUES
  ('1', '2023-10-07', 3.75),
  ('1', '2023-10-13', 18),
  ('1', '2023-10-23', 9),
  ('1', '2023-11-01', 33),
  ('1', '2023-11-07', 14),
  ('1', '2023-11-12', 7);

