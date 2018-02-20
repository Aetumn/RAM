CREATE SCHEMA IF NOT EXISTS ram;

CREATE TABLE quote (
	id bigint primary key auto_increment, 
	text VARCHAR(200) not null,
	author VARCHAR(50) not null,
    creation_date TIMESTAMP not null,
    last_modif_date TIMESTAMP not null,
    last_modif_user VARCHAR(50) not null
);