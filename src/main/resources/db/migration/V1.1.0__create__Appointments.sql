CREATE TABLE services (
id bigint not null auto_increment PRIMARY KEY,
name VARCHAR (100) NOT NULL,
duration_minutes INT NOT NULL,
price DECIMAL (10,2) NOT NULL,
description VARCHAR (255) NOT NULL,
is_active TINYINT (1) DEFAULT 1 );


CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    customer_id bigint NOT NULL,
    professional_id bigint NOT NULL,
    service_id bigint NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_professional FOREIGN KEY (professional_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_service FOREIGN kEY (service_id) REFERENCES services (id) ON DELETE CASCADE
    );



