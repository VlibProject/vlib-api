CREATE TABLE users (
   id VARCHAR(40) PRIMARY KEY,
   email VARCHAR(50) NOT NULL,
   password VARCHAR(200) NOT NULL,
   status VARCHAR(20) NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE station (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    station_code VARCHAR(20) NOT NULL,
    latitude DECIMAL(9,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL,
    address VARCHAR(100),
    city VARCHAR(20),
    description VARCHAR(300),
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);