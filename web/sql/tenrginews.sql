create  table users(
    id int auto_increment primary key,
    email varchar(100) not null,
    password varchar(100) not null,
    full_name varchar(150) not null,
    role_id int
);

create table news_category(
    id int auto_increment primary key,
    name varchar(100) not null
);

create table news_language(
    id int auto_increment primary key,
    name varchar(100) not null,
    code varchar(50) not null
);

create table news_content(
    id int auto_increment primary key,
    title varchar(300) not null ,
    content text not null ,
    post_date timestamp not null ,
    category_id int not null,
    language_id int not null,
    users_id int not null,
    foreign key(category_id) references news_category(id),
    foreign key(language_id) references news_language(id),
    foreign key(users_id) references users(id)
);

create table comments(
    id int auto_increment primary key ,
    comment text not null ,
    post_date timestamp not null ,
    users_id int not null ,
    news_id int not null ,
    foreign key(users_id) references users(id),
    foreign key(news_id) references news_content(id)
);

insert into users(id, email, password, full_name, role_id) value(null,'elzhas.96@mail.ru','12345','Yelzhas Taubay',1);
insert into users(id, email, password, full_name, role_id) value(null,'aknur.98@mail.ru','12345','Aknur Taubay',1);
insert into users(id, email, password, full_name, role_id) value(null,'yedil.03@mail.ru','12345','Yedil Aktau',2);
insert into users(id, email, password, full_name, role_id) value(null,'almas.01@mail.ru','12345','Assilbek Almas',2);

insert into news_category(id, name) value(null,'Sport');
insert into news_category(id, name) value(null,'Economic');
insert into news_category(id, name) value(null,'Culture');

insert into news_language(id, name, code) value(null,'ҚазаҚша','Kaz');
insert into news_language(id, name, code) value(null,'Русский','Rus');
insert into news_language(id, name, code) value(null,'English','USA');

insert into news_content(id, title, content, post_date, category_id, language_id)
value (null,'Cristiano Ronaldo','Cristiano failed in Manchester United this season',now(),1,3);

insert into comments(id, comment, post_date, users_id, news_id)
value(null,'Cristiano is GOAT, bro.What are you talking about, bro?',1,1);


