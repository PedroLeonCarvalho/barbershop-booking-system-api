CREATE TABLE time_slots (
id bigint not null auto_increment PRIMARY KEY,
appointment_date DATE NOT NULL,
appointment_time DECIMAL (10,2) NOT NULL,
is_booked TINYINT (1) DEFAULT 0 );



