delete from user_roles;
delete from role;
delete from myrole;
delete from roles_privileges;



insert into room (room_id,room_number,room_price,capacity,room_description,room_average_rating,hotel_id) 
	values (1,1,50,1,'Jednokrevetna',4.1,1);
insert into room (room_id,room_number,room_price,capacity,room_description,room_average_rating,hotel_id) 
	values (2,2,50,1,'Dvokrevetna',4.2,1);
insert into room (room_id,room_number,room_price,capacity,room_description,room_average_rating,hotel_id) 
	values (3,3,50,2,'Jednokrevetna',4.4,2);
insert into room (room_id,room_number,room_price,capacity,room_description,hotel_id) 
	values (4,4,50,2,'Dvokrevetna',2);
insert into room (room_id,room_number,room_price,capacity,room_description,room_average_rating,hotel_id) 
	values (5,5,50,3,'Dvokrevetna',4.6,2);
insert into room (room_id,room_number,room_price,capacity,room_description,room_average_rating,hotel_id) 
	values (6,11,100,1,'Jednokrevetna',4.7,2);
insert into room (room_id,room_number,room_price,capacity,room_description,room_average_rating,hotel_id) 
	values (7,12,100,3,'Dvokrevetna',3.9,2);
insert into room (room_id,room_number,room_price,capacity,room_description,hotel_id) 
	values (8,13,100,2,'Dvokrevetna',1);
insert into room (room_id,room_number,room_price,capacity,room_description,room_average_rating,hotel_id) 
	values (9,14,100,3,'Trokrevetna',4.4,2);
insert into room (room_id,room_number,room_price,capacity,room_description,hotel_id) 
	values (10,15,100,5,'Trokrevetna',1);
insert into room (room_id,room_number,room_price,capacity,room_description,room_average_rating,hotel_id) 
	values (11,21,200,4,'Trokrevetna',4.9,2);
insert into room (room_id,room_number,room_price,capacity,room_description,room_average_rating,hotel_id) 
	values (12,22,200,3,'Trokrevetna',5.0,2);
insert into room (room_id,room_number,room_price,capacity,room_description,hotel_id) 
	values (13,23,200,7,'Apartman',2);
insert into room (room_id,room_number,room_price,capacity,room_description,room_average_rating,hotel_id) 
	values (14,24,200,6,'Apartman',4.8,1);
insert into room (room_id,room_number,room_price,capacity,room_description,hotel_id) 
	values (15,25,200,5,'Apartman',1);

insert into room (room_id,room_number,room_price,capacity,room_description,hotel_id) 
	values (16,26,260,5,'Apartman',3);

insert into hotel (name,city,adress,description,average_rating) values ('Vojvodina','Novi Sad','Trg slobode 2, Novi Sad, Srbija','U samom centru grada. Stara arhitektura',4.3);
insert into hotel (name,city,adress,description,average_rating) values ('Grand hotel','Beograd','Bulevar Nikole Tesle 3, Beograd, Srbija','Lorem ipsum dolor sit amet, pri ei duis natum.',4.9);
insert into hotel (name,city,adress,description,average_rating) values ('Butique','Beograd','Trg Republike 3, Beograd, Srbija','Pro eu dolore vivendo ponderum.',4.3);
insert into hotel (name,city,adress,description,average_rating) values ('Prezident','Novi Sad','Futoška 109, Novi Sad, Srbija',' Eos ad oblique adolescens moderatius.',3.9);
--insert into hotel (name,city,adress,description,average_rating) values ('City hotel','Novi Sad','Bulevar Oslobodjenja 1','His at quis dico impedit, mea verear imperdiet ea.',4.7);
--insert into hotel (name,city,adress,description) values ('Hilton','Beograd','Bulevar Oslobodjenja 1','Eam inani senserit id.');
--insert into hotel (name,city,adress,description) values ('Crown Plaza','Beograd','Bulevar Oslobodjenja 1','Sed vidit prompta dissentiet at.');
--insert into hotel (name,city,adress,description,average_rating) values ('Sheraton','Beograd','Bulevar Oslobodjenja 1','Eu vix solum assentior voluptatum.',4.4);
--insert into hotel (name,city,adress,description,average_rating) values ('Sheraton','Novi Sad','Bulevar Oslobodjenja 1','Tantas recusabo ut pro.',4.1);
insert into hotel (name,city,adress,description,average_rating) values ('Biser','Derventa','Kralja Petra I, Derventa, Bosna i Hercegovina ','Cu sit sint ignota, sit id scaevola.',4.5);

insert into additional_service_hotel (name,price,hotel_id) values ('Transfer do aerodorma', 50,1);
insert into additional_service_hotel (name,price,hotel_id) values ('Parking', 30,1);
insert into additional_service_hotel (name,price,hotel_id) values ('Koristenje bazena', 20,1);
insert into additional_service_hotel (name,price,hotel_id) values ('Restoran', 10,1);
insert into additional_service_hotel (name,price,hotel_id) values ('Sobni servis', 50,1);
insert into additional_service_hotel (name,price,hotel_id) values ('Wellnes', 50,1);
insert into additional_service_hotel (name,price,hotel_id) values ('Spa', 30,1);
insert into additional_service_hotel (name,price,hotel_id) values ('WiFi', 10,1);
insert into additional_service_hotel (name,price,hotel_id) values ('Restoran', 10,1);

insert into aviocompany (name, adress, description,rating) values ('AirN', 'Aerodrom Beograd 59, Beograd, Srbija', 'opis',4);
insert into aviocompany (name, adress, description, rating) values ('TurkishAirlines', 'Yeşilköy, 34149 Bakırköy/İstanbul, Turkey', 'opis',3.2);
insert into aviocompany (name, adress, description,rating) values ('Urije', 'Prijedor Urije Airport, Prijedor, Bosna i Hercegovina', 'Najjaci aerodrom',5);


insert into destination(name, country, description) values ('Berlin', 'Njemacka','to');
insert into destination(name, country, description) values ('Minhen', 'Njemacka','from');
insert into destination(name, country, description) values ('Pariz', 'Francuska', 'to');
insert into destination(name, country, description) values ('London', 'Velika Britanija','from');
insert into destination(name, country, description) values ('New York', 'SAD',  'from');
insert into destination(name, country, description) values ('Moskva', 'Rusija','to');
insert into destination(name, country, description) values ('Rim', 'Italija','to');
insert into destination(name, country, description) values ('Peking', 'Kina', 'from');
insert into destination(name, country, description) values ('Tokio', 'Japan', 'from');
insert into destination(name, country, description) values ('Brazilija', 'Brazil','to');
insert into destination(name, country, description) values ('Rio de Janeiro', 'Brazil','to');
insert into destination(name, country, description) values ('New Delhi', 'Indija', 'from');





insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified,points,first_login) 
	values (1,'Sara','Celik','isasaracelik@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true,15,true);
insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified,first_login) values (2,'Admin','Admin','admin@gmail.com',
	'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true,true);
insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified,first_login) values (3,'Admin','Admin','avioadmin@gmail.com',
	'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true,true);
insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified,first_login) values (4,'Admin','Admin','hoteladmin@gmail.com',
	'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true,true);
insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified,first_login) values (5,'Admin','Admin','caradmin@gmail.com',
	'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true,true);
insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified,points,first_login) values (6,'Admin','Admin','tanja@gmail.com',
	'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true,16,false);

insert into role (id,name) values (1,'USER');
insert into role (id,name) values (2,'SYSTEM_ADMIN');
insert into role (id,name) values (3,'AVIO_ADMIN');
insert into role (id,name) values (4,'HOTEL_ADMIN');
insert into role (id,name) values (5,'CAR_ADMIN');

INSERT INTO user_roles(user_id, role_id) VALUES(1,1);
INSERT INTO user_roles(user_id, role_id) VALUES(7,1);

INSERT INTO user_roles(user_id, role_id) VALUES(2,1);


--sistemski administrator je Admin Admin admin@gmail.com 
INSERT INTO user_roles(user_id, role_id) VALUES(2,2);
--hotel admin je hoteladmin@gmail.com
INSERT INTO user_roles(user_id, role_id) VALUES(3,3);
INSERT INTO user_roles(user_id, role_id) VALUES(6,3);
--avio admin je avioadmin@gmail.com
INSERT INTO user_roles(user_id, role_id) VALUES(4,4);
--car admin je caradmin@gmail.com
INSERT INTO user_roles(user_id, role_id) VALUES(5,5);

insert into myrole (id,name) values (1,'registrationAgent');
insert into myrole (id,name) values (2,'login');
insert into myrole (id,name) values (3,'loginAdmin');
insert into myrole (id,name) values (4,'loginUser');

insert into myrole (id,name) values (5,'myProfile');

--Privilegije sistemskog admina: registruju aviokompanije, hotele, rent-a-car servise i njihove administratore.  
insert into myrole (id,name) values (6,'addAviocompany');
insert into myrole (id,name) values (7,'addHotel');
insert into myrole (id,name) values (8,'addRentACar');

insert into myrole (id,name) values (9,'addAvioAdmin');
insert into myrole (id,name) values (10,'addHotelAdmin');
insert into myrole (id,name) values (11,'addCarAdmin');

--privilegije hotel admina
insert into myrole (id,name) values (12,'addRoom');
insert into myrole (id,name) values (13,'addService');
insert into myrole (id,name) values (14,'getAdditionalServices');
insert into myrole (id,name) values (15,'changeHotel');
insert into myrole (id,name) values (16,'deleteHotel');
insert into myrole (id,name) values (18,'deleteRoom');
insert into myrole (id,name) values (19,'changeRoom');
insert into myrole (id,name) values (20,'deleteService');
insert into myrole (id,name) values (21,'changeService');
insert into myrole (id,name) values (22,'bookRoom');
insert into myrole (id,name) values (23,'lastWeekReservations');
insert into myrole (id,name) values (24,'getAllReservations');
insert into myrole (id,name) values (25,'getAllRatingsHotel');
insert into myrole (id,name) values (26,'getRatingRoom');
insert into myrole (id,name) values (27,'getHotelRevenue');


--privilegije car admina
insert into myrole (id,name) values (17,'addCar');

insert into roles_privileges(role_id,privilege_id) values (2,5);
insert into roles_privileges(role_id,privilege_id) values (2,6);
insert into roles_privileges(role_id,privilege_id) values (2,7);
insert into roles_privileges(role_id,privilege_id) values (2,8);
insert into roles_privileges(role_id,privilege_id) values (2,9);
insert into roles_privileges(role_id,privilege_id) values (2,10);
insert into roles_privileges(role_id,privilege_id) values (2,11);
insert into roles_privileges(role_id,privilege_id) values (1,2);

--hotel admin
insert into roles_privileges(role_id,privilege_id) values (4,7);
insert into roles_privileges(role_id,privilege_id) values (4,12);
insert into roles_privileges(role_id,privilege_id) values (4,13);
insert into roles_privileges(role_id,privilege_id) values (4,14);
insert into roles_privileges(role_id,privilege_id) values (4,15);
insert into roles_privileges(role_id,privilege_id) values (4,16);
insert into roles_privileges(role_id,privilege_id) values (4,18);
insert into roles_privileges(role_id,privilege_id) values (4,19);
insert into roles_privileges(role_id,privilege_id) values (4,20);
insert into roles_privileges(role_id,privilege_id) values (4,21);
insert into roles_privileges(role_id,privilege_id) values (4,23);
insert into roles_privileges(role_id,privilege_id) values (4,24);
insert into roles_privileges(role_id,privilege_id) values (4,25);
insert into roles_privileges(role_id,privilege_id) values (4,26);
insert into roles_privileges(role_id,privilege_id) values (4,27);
--car admin
insert into roles_privileges(role_id,privilege_id) values (5,17);

--reserve room
insert into roles_privileges(role_id,privilege_id) values (1,22);
insert into roles_privileges(role_id,privilege_id) values (2,22);
insert into roles_privileges(role_id,privilege_id) values (3,22);
insert into roles_privileges(role_id,privilege_id) values (4,22);
insert into roles_privileges(role_id,privilege_id) values (5,22);

insert into rentacar (name,city,adress,description,average_rating) values ('CarFlexi','Beograd','Dunavska 22, Beograd, Srbija','Servis broj 1.',4.2);
insert into rentacar (name,city,adress,description,average_rating) values ('EasyRentCars','Beograd','Knez Mihajlova 45, Beograd, Srbija','Najveci izbor vozila',3.9);
insert into rentacar (name,city,adress,description,average_rating) values ('EuropeCar','Beograd','Cara Dusana 10, Beograd, Srbija','Brzo do zeljenih vozila',4.8);
insert into rentacar (name,city,adress,description,average_rating) values ('Inex Rent A Car','Novi Sad','Bulevar Evrope 2, Novi Sad, Srbija','Najpovoljnije usluge',4.6);
insert into rentacar (name,city,adress,description,average_rating) values ('Max Rent A Car','Novi Sad','Bulevar Evrope 10, Novi Sad, Srbija','luux',3.3);

insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('BMW','NS-0786',500,'4.1',2011,1,4,1);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Audi','BG-4875',450,3.9,2008,1,4,1);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Audi3','555-212',450,3.9,2008,2,4,1);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('BMW5','ZR-4875',450,3.9,2008,2,4,1);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Peugeot','BG-J0PL',3.3,300,2006,3,3,2);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Mercedes-Benz','BC-4875',550,3.8,2009,3,4,2);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Fiat','KR-4875',350,3.1,'2004',4,2,3);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Mazda','JG-4875',350,3.1,'2004',4,2,3);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Citroen','KG-4875',350,3.1,'2004',5,2,3);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Jeep','VA-8979',350,3.1,'2004',5,2,3);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Golf','VR-4875',350,3.1,'2004',6,2,4);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Fiat','BC-4875',350,3.1,'2004',6,2,4);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Passat','AR-8765',350,3.1,'2004',7,2,4);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Renault','BR-4125',350,3.1,'2004',7,2,4);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Fiat 500','BO-4875',350,3.1,'2004',8,2,5);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Nissan','KK-4875',350,3.1,'2004',8,2,5);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('MiniCooper','JC-4875',350,3.1,'2004',9,2,5);
insert into car (car_name,car_number,price,average_rating,prod_year,filijale_id,category_id,rentacar_id) values ('Fiat','AC-4875',350,3.1,'2004',9,2,5);

insert into discount(discount_id,date_from,date_to,discount_price,car_car_id,rentacar_rentacar_id) values (1,'2019-09-05','2019-09-25',30.0,1,1);
insert into discount(discount_id,date_from,date_to,discount_price,car_car_id,rentacar_rentacar_id) values (2,'2019-09-05','2019-09-25',20.0,2,1);


--filijale za carflexi
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(1,'Srbija','Beograd','Tekelijina 53, Beograd, Srbija',1);
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(2,'Srbija','Novi Sad','Bulevar Evrope 2, Novi Sad, Srbija',1);
--filijale za easyrentcars
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(3,'Srbija','Beograd','Knez Mihajlova 45, Beograd, Srbija',2);

--filijale za EuropeCar
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(4,'Srbija','Beograd','Knez Mihajlova 55, Beograd, Srbija',3);
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(5,'Srbija','Beograd','Zeleni venac 5, Beograd, Srbija',3);

--filijale za Inex Rent A Car
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(6,'Srbija','Novi Sad','Bulevar Evrope 45, Novi Sad, Srbija',4);
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(7,'Srbija','Beograd','Kneginje Milice 10, Beograd, Srbija',4);

--filijale za Max Rent A Car
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(8,'Srbija','Novi Sad','Bulevar Evrope 10, Novi Sad, Srbija',5);
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(9,'Srbija','Beograd','Kneginje Milice 10, Beograd, Srbija',5);






insert into category(category_id,mark,description,seats,price) values (1,'A','City car',4,55);
insert into category(category_id,mark,description,seats,price) values (2,'B','Niska klasa',5,60);
insert into category(category_id,mark,description,seats,price) values (3,'C','Srednja klasa',5,60);
insert into category(category_id,mark,description,seats,price) values (4,'D','Visa srednja',7,70);


insert into seat_arrangement( name, seat_columns, seat_rows)values('ime',8,8);

insert into seat(discount_price, price, seat_class, seat_column, seat_row, state)values(0,125,'ECONOMY',1,1,'free');
insert into seat(discount_price, price, seat_class, seat_column, seat_row, state)values(0,125,'ECONOMY',1,2,'free');
insert into seat(discount_price, price, seat_class, seat_column, seat_row, state)values(0,125,'ECONOMY',1,3,'free');




insert into flight(seat_arrangement_id,distance,baggage_description,business_price,economy_price,first_price,premium_economy_price,average_rating,number_of_rating,sum_rating,take_off,landing, time, travel_time,number) values (1,50,'nema',500,100,200,300,1,2,3,'2019-02-10 10-30','2019-02-10 10-30',20,2.5,2);
insert into flight(seat_arrangement_id,distance,baggage_description,business_price,economy_price,first_price,premium_economy_price,average_rating,number_of_rating,sum_rating,take_off,landing, time, travel_time,number) values (1,50,'nema',500,100,200,300,1,2,3,'2019-02-10 10-30','2019-02-10 10-30',11,1,3);
insert into flight(seat_arrangement_id,distance,baggage_description,business_price,economy_price,first_price,premium_economy_price,average_rating,number_of_rating,sum_rating,take_off,landing, time, travel_time,number) values (1,50,'nema',500,100,200,300,1,2,3,'2019-02-10 10-30','2019-02-10 10-30',5,12,4);
insert into flight(seat_arrangement_id,distance,baggage_description,business_price,economy_price,first_price,premium_economy_price,average_rating,number_of_rating,sum_rating,take_off,landing, time, travel_time,number) values (1,50,'nema',500,100,200,300,1,2,3,'2019-02-10 10-30','2019-02-10 10-30',14,3.5,5);
insert into flight(seat_arrangement_id,average_rating, baggage_description, business_price, distance, economy_price, first_price, landing, number, number_of_rating, premium_economy_price, sum_rating, take_off, time, travel_time) values (1,4.3,'nema',789,25,125,541,'2019-08-10 10-30',0,0,458,0,'2019-08-13 10-30','5',5);



insert into avio_flights(aviocompany_id, flight_id) values (1,1);
insert into avio_flights(aviocompany_id, flight_id) values (1,2);
insert into avio_flights(aviocompany_id, flight_id) values (1,3);
insert into avio_flights(aviocompany_id, flight_id) values (1,4);
insert into avio_flights(aviocompany_id, flight_id) values (1,5);




insert into avio_destination(aviocompany_id, destination_id) values (1,1);
insert into avio_destination(aviocompany_id, destination_id) values (1,2);
insert into avio_destination(aviocompany_id, destination_id) values (1,3);
insert into avio_destination(aviocompany_id, destination_id) values (1,4);
insert into avio_destination(aviocompany_id, destination_id) values (1,5);

insert into avio_destination(aviocompany_id, destination_id) values (2,6);
insert into avio_destination(aviocompany_id, destination_id) values (2,7);
insert into avio_destination(aviocompany_id, destination_id) values (2,8);
insert into avio_destination(aviocompany_id, destination_id) values (2,9);
insert into avio_destination(aviocompany_id, destination_id) values (2,10);


insert into flight_destination(flight_id, destination_id) values (1,1);
insert into flight_destination(flight_id, destination_id) values (1,2);
insert into flight_destination(flight_id, destination_id) values (2,3);
insert into flight_destination(flight_id, destination_id) values (2,4);
insert into flight_destination(flight_id, destination_id) values (5,3);
insert into flight_destination(flight_id, destination_id) values (5,4);



/*insert into flight_dest( description, destinations_destination_id, flight_flight_id) values ('arrival',1,1);
insert into flight_dest( description, destinations_destination_id, flight_flight_id) values ('departure',2,1);
insert into flight_dest( description, destinations_destination_id, flight_flight_id) values ('departure',2,2);
insert into flight_dest( description, destinations_destination_id, flight_flight_id) values ('arrival',3,2);
insert into flight_dest( description, destinations_destination_id, flight_flight_id) values ('departure',3,3);
insert into flight_dest( description, destinations_destination_id, flight_flight_id) values ('arrival',1,3);
insert into flight_dest( description, destinations_destination_id, flight_flight_id) values ('departure',4,4);
insert into flight_dest( description, destinations_destination_id, flight_flight_id) values ('arrival',5,4);


insert into flight_dest( description, destinations_destination_id, flight_flight_id) values ('arrival',3,5);
insert into flight_dest( description, destinations_destination_id, flight_flight_id) values ('departure',4,5);
*/



insert into flight_seats(flight_flight_id, seats_seat_id)values(5,1);
insert into flight_seats(flight_flight_id, seats_seat_id)values(5,2);
insert into flight_seats(flight_flight_id, seats_seat_id)values(5,3);




insert into friends(friends_id, is_accepted, user1_user_id, user2_user_id) values (1,true,3,1);
insert into friends(friends_id, is_accepted, user1_user_id, user2_user_id) values (2,true,3,2);
insert into friends(friends_id, is_accepted, user1_user_id, user2_user_id) values (3,false,3,4);
insert into friends(friends_id, is_accepted, user1_user_id, user2_user_id) values (4,false,3,5);

insert into car_reservation(reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag) values (1,'2019-09-07 00:00:00','2019-09-09 00:00:00','Novi Sad','Beograd','B',2,2,50,'2019-09-07 00:00:00',12,1,true);
insert into car_reservation(reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag) values (2,'2019-08-07 00:00:00','2019-08-09 00:00:00','Novi Sad','Beograd','B',2,2,50,'2019-08-07 00:00:00',12,1,true);
insert into car_reservation(reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag) values (3,'2020-08-07 00:00:00','2020-08-09 00:00:00','Novi Sad','Beograd','B',2,2,50,'2020-08-07 00:00:00',14,1,true);

--PRIMJERI ZA IZVJESTAJ
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (4,'2018-10-15','2018-10-25','Novi Sad','Beograd','B',3,10,250,'2018-10-15',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (5,'2018-10-01','2018-10-13','Novi Sad','Beograd','B',3,10,250,'2018-09-15',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (6,'2019-11-10','2019-11-15','Novi Sad','Beograd','B',3,10,250,'2019-10-15',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (7,'2018-11-01','2018-11-06','Novi Sad','Beograd','B',3,10,250,'2018-10-15',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (8,'2019-12-09','2019-12-12','Novi Sad','Beograd','B',3,10,250,'2019-10-15',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (9,'2018-12-03','2018-12-11','Novi Sad','Beograd','B',3,10,250,'2018-10-15',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (10,'2019-01-01','2019-01-05','Novi Sad','Beograd','B',3,10,250,'2018-12-15',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (11,'2019-02-10','2019-07-13','Novi Sad','Beograd','B',3,10,250,'2019-02-07',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (12,'2019-03-05','2019-09-10','Novi Sad','Beograd','B',3,10,250,'2018-10-15',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (13,'2019-09-03','2019-07-04','Novi Sad','Beograd','B',3,10,250,'2018-10-15',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (14,'2019-09-05','2019-09-06','Novi Sad','Beograd','B',3,10,250,'2018-10-15',12,1,true);
insert into car_reservation (reservation_id,start_date,end_date,pickup_place,return_place,category,num_people,num_days,total_price,day_rez,car_id,user_user_id,flag)
	values (15,'2019-09-05','2019-09-06','Novi Sad','Beograd','B',3,10,250,'2018-10-15',12,1,true);

insert into rating_rentacarservice (ratingcar_id,user_user_id,rentacar_id,rate)
	values (1,1,4,5);
insert into rating_rentacarservice (ratingcar_id,user_user_id,rentacar_id,rate)
	values (2,1,4,4);
insert into rating_rentacarservice (ratingcar_id,user_user_id,rentacar_id,rate)
	values (3,1,4,3);

insert into rating_car (ratingcar_id,user_user_id,car_id,rate)
	values (1,1,12,5);
insert into rating_car (ratingcar_id,user_user_id,car_id,rate)
	values (2,1,12,2);
insert into rating_car (ratingcar_id,user_user_id,car_id,rate)
	values (3,1,14,3);





--PRIMJERI ZA IZVJESTAJ
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (1,'2018-10-15','2018-10-25',150.5,'arrived',5,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (2,'2018-10-01','2018-10-13',100.5,'arrived',2,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (3,'2019-11-10','2019-11-15',250.5,'confirmed',null,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (4,'2018-11-01','2018-11-06',200.5,'arrived',null,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (5,'2019-12-09','2019-12-12',250.5,'confirmed',null,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (6,'2018-12-03','2018-12-11',200.5,'arrived',null,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (7,'2019-01-01','2019-01-05',666.5,'confirmed',null,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (8,'2019-02-10','2019-07-13',121.5,'pending',null,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (9,'2019-03-05','2019-09-10',212.5,'pending',null,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (10,'2019-09-03','2019-07-04',120.5,'pending',null,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (11,'2019-09-05','2019-09-06',200.5,'pending',null,1,1);
insert into reservation_room (reservation_room_id,start_date,end_date,total_price,reservation_status,reservation_rating,user_id,room_id)	
	values (12,'2019-09-05','2019-09-06',200.5,'pending',null,1,1);


insert into pricing(pricing_id,price,date_from,date_to,room_id) 
	values (1,100,'2019-06-01','2019-07-01',1);
insert into pricing (pricing_id,price,date_from,date_to,room_id) 
	values (2,100,'2019-07-01','2019-08-01',1);
insert into pricing (pricing_id,price,date_from,date_to,room_id) 
	values (3,100,'2019-08-01','2019-09-01',1);	
insert into pricing (pricing_id,price,date_from,date_to,room_id) 
	values (4,100,'2019-09-01','2019-10-01',1);
insert into pricing (pricing_id,price,date_from,date_to,room_id) 
	values (5,100,'2019-10-01','2019-11-01',1);

--primjeri za ocjene hotela
insert into rating_hotel (ratinghotel_id,user_id,hotel_id,rate) 
	values (1,1,1,5);
insert into rating_hotel (ratinghotel_id,user_id,hotel_id,rate) 
	values (2,1,1,4);
insert into rating_hotel (ratinghotel_id,user_id,hotel_id,rate) 
	values (3,1,1,3);
--primjeri za ocjene soba
insert into rating_room (ratingroom_id,user_id,room_id,rate)
	values (1,1,8,5);
insert into rating_room (ratingroom_id,user_id,room_id,rate)
	values (2,1,1,2);
insert into rating_room (ratingroom_id,user_id,room_id,rate)
	values (3,1,2,3);

insert into system_discount (system_discount_id,amount,percent)
	values (1,200,10);
insert into system_discount (system_discount_id,amount,percent)
	values (2,200,3);
insert into system_discount (system_discount_id,amount,percent)
	values (3,5,10);

--sobe na popustu
insert into discount_hotel(discount_id,date_from,date_to,discount_price,room_room_id,hotel_hotel_id) values (1,'2019-09-05','2019-09-25',30.0,1,1);
insert into discount_hotel(discount_id,date_from,date_to,discount_price,room_room_id,hotel_hotel_id) values (2,'2019-09-05','2019-09-25',20.0,2,1);

