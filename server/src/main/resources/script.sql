drop database documents_life_cycle;

create database documents_life_cycle CHARACTER SET utf8 COLLATE utf8_unicode_ci;

use documents_life_cycle;

create table roles(
role_name varchar(40) primary key
);

insert into roles values('Админ'), ('Работник'), ('Клиент');

create table users(
id int primary key auto_increment,
login varchar(40) not null,
pass varchar(40) not null,
user_status int not null,
user_role varchar(40) not null,
    FOREIGN KEY (user_role) REFERENCES roles(role_name) ON DELETE CASCADE on update cascade
);

insert into users values(1, 'admin', 'admin', 1, 'Админ');
insert into users values(2, 'worker', 'worker', 1, 'Работник');
insert into users values(3, 'client', 'client', 1, 'Клиент');
insert into users values(4, 'client2', 'client2', 1, 'Клиент');

create table person(
id int primary key auto_increment,
client_name varchar(100) not null,
unique_identificator varchar(14) not null,
communication varchar(400) not null,
address varchar(150),
client_type varchar(15) not null,
user_id int not null,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE on update cascade
);

insert into person values(1, 'Клиент 1', '12345677654321', 'нету', 'нету', 'Физ', 3);
insert into person values(2, 'Клиент 2', '76543211234567', 'есть', 'есть', 'Юр', 4);
insert into person values(3, 'Работник 1', '76543211234599', 'нету', 'есть', '-', 2);

create table documents_templates(
template_id int not null primary key auto_increment,
template_name varchar(100),
term float not null
);

insert into documents_templates values (1, 'Договор найма работника', 2);
insert into documents_templates values (2, 'Заявление на увольнение работника', 5);
insert into documents_templates values (3, 'Договор купли продажи', 1);

create table documents(
id int primary key auto_increment,
document_name varchar(500) not null,
template int not null,
    FOREIGN KEY (template) REFERENCES documents_templates(template_id) ON DELETE CASCADE on update cascade,
compiler varchar(40) not null,
count int not null,
transaction_price float not null,
term float,
client_id int not null,
    FOREIGN KEY (client_id) REFERENCES person(id) ON DELETE CASCADE on update cascade
);

create table state_body(
id int primary key auto_increment,
name varchar(100) not null,
term float not null,
communication varchar(100) not null
)