-- Con este fichero creamos la inicialización de datos. En este caso los posiobles usuarios que se pueden logar
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1000 increment by 1;


-- Contraseña: Admin1
insert into usuarios (id, full_name, email, username, password, created_at, last_password_change_at)
values (1, 'Admin admin', 'admin@proyectotfg.net','admin','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into user_entity_roles (user_entity_id, roles) values (1,'USER');
insert into user_entity_roles (user_entity_id, roles) values (1,'ADMIN');


-- Contraseña: Marialopez1
insert into usuarios (id, full_name, email, username, password, created_at, last_password_change_at)
values (2, 'María López', 'maria.lopez@proyectotfg.net','marialopez','$2a$10$ev.rv6yUA.UE9.Ndw4aSC.wRo6UlP6OkjAe48SmEN.elw4WAyfT0S',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into user_entity_roles (user_entity_id, roles) values (2,'USER');

-- Contraseña: Angelmartinez1
insert into usuarios (id, full_name, email, username, password, created_at, last_password_change_at)
values (3, 'Ángel Martínez', 'angel.martinez@proyectotfg.net','angelmartinez','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into user_entity_roles (user_entity_id, roles) values (3,'USER');

-- Contraseña: Anajimenez1
insert into usuarios (id, full_name, email, username, password, created_at, last_password_change_at)
values (4, 'Ana Jiménez', 'ana.jimenez@proyectotfg.net','anajimenez','$2a$10$IF4e6GpTAO5pQOLwy.Bn7.hBGgeOOMCIyEhvEkeikkrlBY5emp6vy',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into user_entity_roles (user_entity_id, roles) values (4,'USER');


