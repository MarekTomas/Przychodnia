drop database if exists przychodnia;
create database przychodnia DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

use przychodnia;

#drop table users;
#drop table doctors;
drop table visits;
drop table zarezerwowane;

create table users(
id INT primary key AUTO_INCREMENT,
imie VARCHAR(25) NOT NULL,
nazwisko VARCHAR(25) NOT NULL,
e_mail VARCHAR(25) NOT NULL,
pesel  VARCHAR(11) NOT NULL,
uprawnienia VARCHAR(10) DEFAULT 'user'
);

create table doctors(
id_d INT primary key AUTO_INCREMENT,
imie VARCHAR(25) NOT NULL,
nazwisko VARCHAR(25) NOT NULL,
e_mail VARCHAR(25) NOT NULL,
pesel  VARCHAR(11) NOT NULL,
uprawnienia VARCHAR(10) DEFAULT 'doctor',
specjalizacja Varchar(25) NOT NULL,
id int,
FOREIGN KEY(id) REFERENCES users (id)
);


create table visits(
id_v INT primary key AUTO_INCREMENT,
day_v date NOT NULL,
time_v time NOT NULL,
id_d int,
FOREIGN KEY(id_d) REFERENCES doctors (id_d)
);

Create table zarezerwowane(
id_v INT ,
imie VARCHAR(25) NOT NULL,
nazwisko VARCHAR(25) NOT NULL,
specjalizacja Varchar(25),
day_v date NOT NULL,
time_v time NOT NULL,
pesel  VARCHAR(11) NOT NULL
);



insert into users
(id, imie, nazwisko,e_mail,pesel,uprawnienia)
values 
(1, 'Kacper', 'Nawrocki','KW@gmail.com','11111111111','user'),
(2, 'Hanna', 'Nowakowska','hn@gmail.com','22222222222','user' ),
(3, 'Julia', 'Białek','JB@gmail.com','33333333333','user' ),
(4, 'Amelia', 'Rutkowska','AR@gmail.com','44444444444','user' ),
(5, 'Marian', 'Wiśniewska','MW@gmail.com','55555555555','user' ),
(6, 'Jakub', 'Kozłowski','JK@gmail.com','66666666666','user'),
(7, 'Oliwier', 'Dąbrowski','OD@gmail.com','77777777777','user'),
(8, 'Janina', 'Kowalczyk','JK@gmail.com','88888888888','user'),
(9, 'Wiktor', 'Ziółkowski','WZ@gmail.com','99999999999','user'),
(10, 'Michał', 'Marek','MM@gmail.com','12222222222','user'),
(11, 'Jakub', 'Sosnowski','JS@gmail.com','13333333333','user'),
(12, 'Karolina', 'Michalska','KM@gmail.com','14444444444','user'),
(13, 'Klaudia', 'Wiśniewska','KW@gmail.com','15555555555','user'),
(14, 'Oliwia', 'Krajewska','OK@gmail.com','16666666666', 'user'),
(15, 'Milena', 'Sikora','MS@gmail.com','17777777777','user'),
(16, 'Natali', 'Mikołajczyk','NM@gmail.com','18888888888','user'),
(17, 'Milena', 'Barańska','MB@gmail.com','19999999999','user'),
(18, 'Aleksander', 'Wójcik','AW@gmail.com','21111111111','user'),
(19, 'Wiktoria', 'Kozłowska','WK@gmail.com','23333333333','user'),
(20, 'Stefan', 'Głowacki','SG@gmail.com','24444444444','user'),
(21,'a','a','a','11111111111','user'),
(22,'a','a','a','a','user')
;


insert into doctors
(id_d, imie, nazwisko,e_mail,pesel,uprawnienia,specjalizacja)
values 
(1, 'Grażyna', 'Żywicka','GZ@gmail.com','99999999999','doctor','Chirurg'),
(2, 'Barbara', 'Zderkiewicz','BZ@gmail.com','99999999999','doctor','Urolog'),
(3, 'Marcin', 'Dziurka','MD@gmail.com','99999999999','doctor','Kardiolog'),
(4, 'Katarzyna', 'Lis','KT@gmail.com','99999999999','doctor','Pediatra'),
(5, 'Kinga', 'Rusin','KR@gmail.com','99999999999','doctor','Dermatolog'),
(6, 'Krzysztof', 'Krawczyk','KK@gmail.com','99999999999','doctor','Chirurg'),
(7, 'Marcin', 'Niska','MN@gmail.com','99999999999','doctor','Ortopeda'),
(8, 'Marek', 'Kowalski','MK@gmail.com','99999999999','doctor','Okulista'),
(9, 'Izabela', 'Piętka','IP@gmail.com','99999999999','doctor','Psycholog'),
(10, 'Michał', 'Nóż','MN@gmail.com','99999999999','doctor','Alergolog'),
(11, 'Genowefa', 'Oczko','GO@gmail.com','99999999999','doctor','Kardiolog'),
(12, 'Marcin', 'Niska','MN@gmail.com','99999999999','doctor','Laryngolog'),
(13, 'Tomasz', 'Olcha','TO@gmail.com','99999999999','doctor','Pediatra'),
(14, 'Waldemar', 'Poprawka','WP@gmail.com','99999999999','doctor','Diabetolog'),
(15, 'Leokadia', 'Niewiadomska','LN@gmail.com','99999999999','doctor','Onkolog')
;

insert into visits(day_v,time_v,id_d)values('2018-01-25','17:20:00',1);
insert into visits(day_v,time_v,id_d)values('2018-01-25','17:35:00',1);
insert into visits(day_v,time_v,id_d)values('2018-01-25','17:50:00',1);
insert into visits(day_v,time_v,id_d)values('2018-01-25','18:05:00',1);
insert into visits(day_v,time_v,id_d)values('2018-01-25','18:20:00',1);
insert into visits(day_v,time_v,id_d)values('2018-01-28','10:00:00',1);
insert into visits(day_v,time_v,id_d)values('2018-01-28','10:15:00',1);
insert into visits(day_v,time_v,id_d)values('2018-01-28','10:30:00',1);
insert into visits(day_v,time_v,id_d)values('2018-01-28','10:45:00',1);
insert into visits(day_v,time_v,id_d)values('2018-01-28','11:00:00',1);

insert into visits(day_v,time_v,id_d)values('2018-01-15','15:10:00',2);
insert into visits(day_v,time_v,id_d)values('2018-01-15','15:40:00',2);
insert into visits(day_v,time_v,id_d)values('2018-01-18','8:00:00',2);
insert into visits(day_v,time_v,id_d)values('2018-01-18','8:30:00',2);
insert into visits(day_v,time_v,id_d)values('2018-01-18','9:00:00',2);
insert into visits(day_v,time_v,id_d)values('2018-01-18','9:30:00',2);
insert into visits(day_v,time_v,id_d)values('2018-01-25','11:15:00',2);
insert into visits(day_v,time_v,id_d)values('2018-01-25','11:45:00',2);
insert into visits(day_v,time_v,id_d)values('2018-01-30','10:45:00',2);
insert into visits(day_v,time_v,id_d)values('2018-01-30','11:15:00',2);

insert into visits(day_v,time_v,id_d)values('2018-01-07','16:00:00',3);
insert into visits(day_v,time_v,id_d)values('2018-01-07','16:45:00',3);
insert into visits(day_v,time_v,id_d)values('2018-01-07','17:30:00',3);
insert into visits(day_v,time_v,id_d)values('2018-01-07','18:15:00',3);

insert into visits(day_v,time_v,id_d)values('2018-01-04','9:00:00',4);
insert into visits(day_v,time_v,id_d)values('2018-01-04','9:30:00',4);
insert into visits(day_v,time_v,id_d)values('2018-01-09','11:15:00',4);
insert into visits(day_v,time_v,id_d)values('2018-01-09','11:45:00',4);

insert into visits(day_v,time_v,id_d)values('2018-01-11','13:40:00',5);
insert into visits(day_v,time_v,id_d)values('2018-01-11','13:50:00',5);
insert into visits(day_v,time_v,id_d)values('2018-01-11','14:00:00',5);
insert into visits(day_v,time_v,id_d)values('2018-01-11','14:10:00',5);

insert into visits(day_v,time_v,id_d)values('2018-01-27','12:05:00',6);
insert into visits(day_v,time_v,id_d)values('2018-01-27','12:20:00',6);
insert into visits(day_v,time_v,id_d)values('2018-01-27','12:35:00',6);
insert into visits(day_v,time_v,id_d)values('2018-01-29','18:10:00',6);

insert into visits(day_v,time_v,id_d)values('2018-01-07','10:00:00',7);
insert into visits(day_v,time_v,id_d)values('2018-01-07','11:00:00',7);
insert into visits(day_v,time_v,id_d)values('2018-01-18','08:00:00',7);
insert into visits(day_v,time_v,id_d)values('2018-01-18','09:00:00',7);

insert into visits(day_v,time_v,id_d)values('2018-02-16','09:45:00',8);
insert into visits(day_v,time_v,id_d)values('2018-02-16','10:00:00',8);
insert into visits(day_v,time_v,id_d)values('2018-02-16','10:15:00',8);
insert into visits(day_v,time_v,id_d)values('2018-02-16','10:30:00',8);

insert into visits(day_v,time_v,id_d)values('2018-01-12','9:30:00',9);
insert into visits(day_v,time_v,id_d)values('2018-01-12','11:00:00',9);
insert into visits(day_v,time_v,id_d)values('2018-01-12','12:30:00',9);
insert into visits(day_v,time_v,id_d)values('2018-01-12','14:00:00',9);

insert into visits(day_v,time_v,id_d)values('2018-01-28','17:15:00',10);
insert into visits(day_v,time_v,id_d)values('2018-02-28','17:30:00',10);
insert into visits(day_v,time_v,id_d)values('2018-02-28','17:45:00',10);
insert into visits(day_v,time_v,id_d)values('2018-02-28','18:00:00',10);
insert into visits(day_v,time_v,id_d)values('2018-03-01','08:00:00',10);
insert into visits(day_v,time_v,id_d)values('2018-03-01','08:15:00',10);

insert into visits(day_v,time_v,id_d)values('2018-02-16','10:15:00',11);
insert into visits(day_v,time_v,id_d)values('2018-02-16','10:30:00',11);
insert into visits(day_v,time_v,id_d)values('2018-02-16','10:15:00',11);
insert into visits(day_v,time_v,id_d)values('2018-02-16','10:30:00',11);

insert into visits(day_v,time_v,id_d)values('2018-02-23','15:15:00',12);
insert into visits(day_v,time_v,id_d)values('2018-02-23','15:30:00',12);
insert into visits(day_v,time_v,id_d)values('2018-02-23','15:45:00',12);
insert into visits(day_v,time_v,id_d)values('2018-02-23','16:00:00',12);
insert into visits(day_v,time_v,id_d)values('2018-02-23','16:15:00',12);
insert into visits(day_v,time_v,id_d)values('2018-02-23','16:30:00',12);
insert into visits(day_v,time_v,id_d)values('2018-02-23','16:45:00',12);
insert into visits(day_v,time_v,id_d)values('2018-02-23','17:00:00',12);

insert into visits(day_v,time_v,id_d)values('2018-02-28','17:15:00',13);
insert into visits(day_v,time_v,id_d)values('2018-02-28','17:30:00',13);
insert into visits(day_v,time_v,id_d)values('2018-02-28','17:45:00',13);
insert into visits(day_v,time_v,id_d)values('2018-02-28','18:00:00',13);
insert into visits(day_v,time_v,id_d)values('2018-03-01','08:00:00',13);
insert into visits(day_v,time_v,id_d)values('2018-03-01','08:15:00',13);

insert into visits(day_v,time_v,id_d)values('2018-02-28','17:15:00',14);
insert into visits(day_v,time_v,id_d)values('2018-02-28','17:30:00',14);
insert into visits(day_v,time_v,id_d)values('2018-02-28','17:45:00',14);
insert into visits(day_v,time_v,id_d)values('2018-02-28','18:00:00',14);
insert into visits(day_v,time_v,id_d)values('2018-03-01','08:00:00',14);
insert into visits(day_v,time_v,id_d)values('2018-03-01','08:15:00',14);

insert into visits(day_v,time_v,id_d)values('2018-02-28','17:15:00',15);
insert into visits(day_v,time_v,id_d)values('2018-02-28','17:30:00',15);
insert into visits(day_v,time_v,id_d)values('2018-02-28','17:45:00',15);
insert into visits(day_v,time_v,id_d)values('2018-02-28','18:00:00',15);
insert into visits(day_v,time_v,id_d)values('2018-03-01','08:00:00',15);
insert into visits(day_v,time_v,id_d)values('2018-03-01','08:15:00',15);


select * from doctors;
select * from users;
select * from visits;
select * from zarezerwowane;

select id_v,imie, nazwisko, specjalizacja, day_v, time_v, pesel from zarezerwowane ;

select id_v,imie, nazwisko, specjalizacja, day_v, time_v from doctors natural join visits where id_d = id_d ORDER BY day_v asc;
SELECT uprawnienia FROM users WHERE e_mail='KW@gmail.com' and pesel='11111111111';
select imie, nazwisko, specjalizacja, day_v, time_v from doctors natural join visits where id_d = id_d;
insert into zarezerwowane(imie, nazwisko, specjalizacja, day_v, time_v)values('"+tc_imie.getText()+"','"+tc_nazwisko.getText()+"','"+tz_specjalizacja.getText()+"','"+tc_data.getCellData(5)+"','"+tc_godzina.getCellData(6)+"');"+"where id="+id_selected;
