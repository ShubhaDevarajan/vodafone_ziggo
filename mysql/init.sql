CREATE DATABASE IF NOT EXISTS vodafone_ziggo;
USE vodafone_ziggo;
CREATE TABLE IF NOT EXISTS vodafone_ziggo.orders (
                                       order_id varchar(100) PRIMARY KEY,
                                       product_id varchar(100),
                                       email varchar(100)
);

CREATE TABLE IF NOT EXISTS vodafone_ziggo.users (
                                      user_id varchar(50) PRIMARY KEY,
                                      email varchar(100),
                                      first_name varchar(100),
                                      last_name varchar(100)
);