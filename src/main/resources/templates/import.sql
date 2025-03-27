INSERT INTO categories (name) VALUES ('CRUD'), ('Security'), ('Repository'), ('Controller'), ('Service'), ('REST API'), ('Thymeleaf');

INSERT INTO roles (name) VALUES ("ADMIN");

INSERT INTO roles (name) VALUES ("OPERATOR");

INSERT INTO role_user (role_id, user_id) VALUES (1, 1), (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6);

INSERT INTO states (name) VALUES ('To do'), ('In progress'), ('Completed');

INSERT INTO tickets (title, text, creation_date, category_id, state_id, user_id) VALUES ('Problema login', 'Impossibile accedere con le credenziali fornite.', '2025-03-25', 1, 1, 2), ('Errore connessione', 'La piattaforma non si connette al server.', '2025-03-26', 2, 2, 3), ('Bug interfaccia', 'Alcuni pulsanti non rispondono al click.', '2025-03-27', 3, 1, 4), ('Richiesta aggiornamento', 'Vorrei aggiornare il mio piano di supporto.', '2025-03-28', 1, 3, 5);

INSERT INTO users (username, password, state) VALUES ('Enrico', '{noop}alkatraz1', true), ('Riccardo', '{noop}alkatraz1', true), ('Amerigo', '{noop}alkatraz1', true), ('Davide', '{noop}alkatraz1', false), ('Paolo', '{noop}alkatraz1', false), ('Danilo', '{noop}alkatraz1', false);