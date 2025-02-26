ALTER TABLE appointments
CHANGE COLUMN professional_id professional  BIGINT NOT NULL;

ALTER TABLE appointments
CHANGE COLUMN customer_id customer  BIGINT NOT NULL;

ALTER TABLE appointments
ADD CONSTRAINT fk_customer FOREIGN KEY (customer) REFERENCES users (id) ON DELETE CASCADE;



