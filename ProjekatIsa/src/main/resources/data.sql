delete from user_roles;
delete from role;
delete from myrole;
delete from roles_privileges;



insert into room (room_number,room_price,room_description,room_average_rating) values (1,50,'Jednokrevetna',4.1);
insert into room (room_number,room_price,room_description,room_average_rating) values (2,50,'Dvokrevetna',4.2);
insert into room (room_number,room_price,room_description,room_average_rating) values (3,50,'Jednokrevetna',4.4);
insert into room (room_number,room_price,room_description) values (4,50,'Dvokrevetna');
insert into room (room_number,room_price,room_description,room_average_rating) values (5,50,'Dvokrevetna',4.6);
insert into room (room_number,room_price,room_description,room_average_rating) values (11,100,'Jednokrevetna',4.7);
insert into room (room_number,room_price,room_description,room_average_rating) values (12,100,'Dvokrevetna',3.9);
insert into room (room_number,room_price,room_description) values (13,100,'Dvokrevetna');
insert into room (room_number,room_price,room_description,room_average_rating) values (14,100,'Trokrevetna',4.4);
insert into room (room_number,room_price,room_description) values (15,100,'Trokrevetna');
insert into room (room_number,room_price,room_description,room_average_rating) values (21,200,'Trokrevetna',4.9);
insert into room (room_number,room_price,room_description,room_average_rating) values (22,200,'Trokrevetna',5.0);
insert into room (room_number,room_price,room_description) values (23,200,'Apartman');
insert into room (room_number,room_price,room_description,room_average_rating) values (24,200,'Apartman',4.8);
insert into room (room_number,room_price,room_description) values (25,200,'Apartman');

insert into hotel (name,adress,description,average_rating) values ('Vojvodina','Novi Sad','U samom centru grada. Stara arhitektura',4.3);
insert into hotel (name,adress,description,average_rating) values ('Grand hotel','Novi Sad','Lorem ipsum dolor sit amet, pri ei duis natum.',4.9);
insert into hotel (name,adress,description,average_rating) values ('Butique','Beograd','Pro eu dolore vivendo ponderum.',4.3);
insert into hotel (name,adress,description,average_rating) values ('Prezident','Novi Sad',' Eos ad oblique adolescens moderatius.',3.9);
insert into hotel (name,adress,description,average_rating) values ('City hotel','Novi Sad','His at quis dico impedit, mea verear imperdiet ea.',4.7);
insert into hotel (name,adress,description) values ('Hilton','Beograd','Eam inani senserit id.');
insert into hotel (name,adress,description) values ('Crown Plaza','Beograd','Sed vidit prompta dissentiet at.');
insert into hotel (name,adress,description,average_rating) values ('Sheraton','Beograd','Eu vix solum assentior voluptatum.',4.4);
insert into hotel (name,adress,description,average_rating) values ('Sheraton','Novi Sad','Tantas recusabo ut pro.',4.1);
insert into hotel (name,adress,description,average_rating) values ('Biser','Derventa','Cu sit sint ignota, sit id scaevola.',4.5);

insert into additional_service_hotel (name,price) values ('Transfer do aerodorma', 50);
insert into additional_service_hotel (name,price) values ('Parking', 30);
insert into additional_service_hotel (name,price) values ('Koristenje bazena', 20);
insert into additional_service_hotel (name,price) values ('Restoran', 10);
insert into additional_service_hotel (name,price) values ('Sobni servis', 50);
insert into additional_service_hotel (name,price) values ('Wellnes', 50);
insert into additional_service_hotel (name,price) values ('Spa', 30);
insert into additional_service_hotel (name,price) values ('WiFi', 10);
insert into additional_service_hotel (name,price) values ('Restoran', 10);

/*insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,1);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,2);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,3);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,4);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,5);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,6);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,7);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,8);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,9);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,10);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,11);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,12);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,13);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,14);
insert into `isa`.`hotel_rooms` (`hotel_id`, `room_id`) VALUES (1,15);*/


/*

insert into hotel_additional_service(hotel_id,additional_service_id) values (1,1);
insert into hotel_additional_service(hotel_id,additional_service_id) values (1,2);
insert into hotel_additional_service(hotel_id,additional_service_id) values (1,3);
insert into hotel_additional_service(hotel_id,additional_service_id) values (1,4);
insert into hotel_additional_service(hotel_id,additional_service_id) values (1,5);
insert into hotel_additional_service(hotel_id,additional_service_id) values (1,6);
insert into hotel_additional_service(hotel_id,additional_service_id) values (1,7);
insert into hotel_additional_service(hotel_id,additional_service_id) values (1,8);
insert into hotel_additional_service(hotel_id,additional_service_id) values (1,9);*/




insert into aviocompany (name, adress, description) values ('AirN', 'Beograd', 'opis');
insert into aviocompany (name, adress, description) values ('TurkishAirlines', 'Istanbul', 'opis');
insert into aviocompany (name, adress, description) values ('Urije', 'Prijedor', 'Najjaci aerodrom');


insert into destination(name, country) values ('Berlin', 'Njemacka');
insert into destination(name, country) values ('Minhen', 'Njemacka');
insert into destination(name, country) values ('Pariz', 'Francuska');
insert into destination(name, country) values ('London', 'Velika Britanija');
insert into destination(name, country) values ('New York', 'SAD');
insert into destination(name, country) values ('Moskva', 'Rusija');
insert into destination(name, country) values ('Rim', 'Italija');
insert into destination(name, country) values ('Peking', 'Kina');
insert into destination(name, country) values ('Tokio', 'Japan');
insert into destination(name, country) values ('Brazilija', 'Brazil');
insert into destination(name, country) values ('Rio de Janeiro', 'Brazil');
insert into destination(name, country) values ('New Delhi', 'Indija');





insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified) values (1,'Sara','Celik','isasaracelik@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true);
insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified) values (2,'Admin','Admin','admin@gmail.com',
	'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true);
insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified) values (3,'Admin','Admin','avioadmin@gmail.com',
	'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true);
insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified) values (4,'Admin','Admin','hoteladmin@gmail.com',
	'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true);
insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified) values (5,'Admin','Admin','caradmin@gmail.com',
	'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true);


insert into role (id,name) values (1,'USER');
insert into role (id,name) values (2,'SYSTEM_ADMIN');
insert into role (id,name) values (3,'AVIO_ADMIN');
insert into role (id,name) values (4,'HOTEL_ADMIN');
insert into role (id,name) values (5,'CAR_ADMIN');

INSERT INTO user_roles(user_id, role_id) VALUES(1,5);
INSERT INTO user_roles(user_id, role_id) VALUES(2,1);


--sistemski administrator je Admin Admin admin@gmail.com 
INSERT INTO user_roles(user_id, role_id) VALUES(2,2);
--hotel admin je hoteladmin@gmail.com
INSERT INTO user_roles(user_id, role_id) VALUES(3,3);
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

--car admin
insert into roles_privileges(role_id,privilege_id) values (5,17);




insert into rentalcars (name,adress,description,average_rating) values ('CarFlexi','Beograd','adjiaisdj',4.2);
insert into rentalcars (name,adress,description,average_rating) values ('EasyRentCars','Beograd','bla bla bla',3.9);
insert into rentalcars (name,adress,description,average_rating) values ('EuropeCar','Beograd','cccc',4.8);
insert into rentalcars (name,adress,description,average_rating) values ('Inex Rent A Car','Novi Sad','Najpovoljnije usluge',4.6);
insert into rentalcars (name,adress,description,average_rating) values ('Max Rent A Car','Novi Sad','luux',3.3);

insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('BMW',1,500,4.1,'2011',1,4);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Audi',2,450,3.9,'2008',1,4);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Audi3',555-212,450,3.9,'2008',2,4);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('BMW5',2,450,3.9,'2008',2,4);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Peugeot',5,3.3,300,'2006',3,3);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Mercedes-Benz',7,550,3.8,'2009',3,4);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Fiat',4,350,3.1,'2004',4,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Mazda',4,350,3.1,'2004',4,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Citroen',4,350,3.1,'2004',5,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Jeep',4,350,3.1,'2004',5,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Golf',4,350,3.1,'2004',6,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Fiat',4,350,3.1,'2004',6,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Passat',4,350,3.1,'2004',7,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Renault',4,350,3.1,'2004',7,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Fiat 500',4,350,3.1,'2004',8,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Nissan',4,350,3.1,'2004',8,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('MiniCooper',4,350,3.1,'2004',9,2);
insert into car (car_name,car_number,car_price,rating,prod_year,filijale_id,category_id) values ('Fiat',4,350,3.1,'2004',9,2);









--filijale za carflexi
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(1,'Srbija','Beograd','Tekelijina 53',1);
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(2,'Srbija','Novi Sad','Bulevar Evrope 2',1);
--filijale za easyrentcars
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(3,'Srbija','Beograd','Knez Mihajlova 45',2);

--filijale za EuropeCar
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(4,'Srbija','Beograd','Knez Mihajlova 55',3);
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(5,'Srbija','Beograd','Zeleni venac 5',3);

--filijale za Inex Rent A Car
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(6,'Srbija','Novi Sad','Bulevar Evrope 45',4);
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(7,'Srbija','Beograd','Kneginje Milice 10',4);

--filijale za Max Rent A Car
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(8,'Srbija','Novi Sad','Bulevar Evrope 10',5);
insert into filijale(filijale_id,drzava,grad,adresa,rentacar_id) values(9,'Srbija','Beograd','Kneginje Milice 10',5);






insert into category(category_id,mark,description,seats,price) values (1,'A','City car',4,55);
insert into category(category_id,mark,description,seats,price) values (2,'B','Niska klasa',5,60);
insert into category(category_id,mark,description,seats,price) values (3,'C','Srednja klasa',5,60);
insert into category(category_id,mark,description,seats,price) values (4,'D','Visa srednja',7,70);

insert into flight(distance,baggage_description,business_price,economy_price,first_price,premium_economy_price,average_rating,number_of_rating,sum_rating,take_off,landing, time, travel_time,number,seat) values (50,'nema',500,100,200,300,1,2,3,'2019-02-10 10-30','2019-02-10 10-30','bnbnb',2.5,2,true);
insert into flight(distance,baggage_description,business_price,economy_price,first_price,premium_economy_price,average_rating,number_of_rating,sum_rating,take_off,landing, time, travel_time,number,seat) values (50,'nema',500,100,200,300,1,2,3,'2019-02-10 10-30','2019-02-10 10-30','bnbfdfnb',1,3,true);
insert into flight(distance,baggage_description,business_price,economy_price,first_price,premium_economy_price,average_rating,number_of_rating,sum_rating,take_off,landing, time, travel_time,number,seat) values (50,'nema',500,100,200,300,1,2,3,'2019-02-10 10-30','2019-02-10 10-30','bnfnb',12,4,true);
insert into flight(distance,baggage_description,business_price,economy_price,first_price,premium_economy_price,average_rating,number_of_rating,sum_rating,take_off,landing, time, travel_time,number,seat) values (50,'nema',500,100,200,300,1,2,3,'2019-02-10 10-30','2019-02-10 10-30','bnfsanb',3.5,5,true);


insert into avio_flights(aviocompany_id, flight_id) values (1,1);
insert into avio_flights(aviocompany_id, flight_id) values (1,2);
insert into avio_flights(aviocompany_id, flight_id) values (1,3);
insert into avio_flights(aviocompany_id, flight_id) values (1,4);



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


insert into friends(friends_id, is_accepted, user1_user_id, user2_user_id) values (1,true,3,1);
insert into friends(friends_id, is_accepted, user1_user_id, user2_user_id) values (2,true,3,2);
insert into friends(friends_id, is_accepted, user1_user_id, user2_user_id) values (3,false,3,4);
insert into friends(friends_id, is_accepted, user1_user_id, user2_user_id) values (4,false,3,5);




