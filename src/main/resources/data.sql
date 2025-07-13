INSERT INTO roles (id, role_name) values (1, 'ADMIN');
INSERT INTO roles (id, role_name) values (2, 'USER');
INSERT INTO users (last_name, first_name, birth_date, email, phone_number, role_id)
VALUES ('Arisov', 'Efim', CURRENT_TIMESTAMP, 'arisov@telros.ru',
        '+7(812)603-28-28', 1);
INSERT INTO credentials (login, password) VALUES ('admin', '$2a$12$YfRF5qVdSVF2YXEWn3l55ORWYiuFAD.uNuAfYoPhknUKEClVblm3m');
UPDATE users SET credential_id = 1 WHERE users.email LIKE 'arisov@telros.ru';