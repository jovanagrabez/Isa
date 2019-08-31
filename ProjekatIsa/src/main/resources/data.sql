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


/*insert into additional_service_hotel (name,price) values ('Transfer do aerodorma', 50);
insert into additional_service_hotel (name,price) values ('Parking', 30);
insert into additional_service_hotel (name,price) values ('Koristenje bazena', 20);
insert into additional_service_hotel (name,price) values ('Restoran', 10);
insert into additional_service_hotel (name,price) values ('Sobni servis', 50);
insert into additional_service_hotel (name,price) values ('Wellnes', 50);
insert into additional_service_hotel (name,price) values ('Spa', 30);
insert into additional_service_hotel (name,price) values ('WiFi', 10);
insert into additional_service_hotel (name,price) values ('Restoran', 10);

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


insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified) values (1,'Sara','Celik','isasaracelik@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',true,true);
insert into user (user_id,first_name,last_name,email,password_hash,enabled,verified) values (2,'Admin','Admin','admin@gmail.com',
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

insert into roles_privileges(role_id,privilege_id) values (2,5);
insert into roles_privileges(role_id,privilege_id) values (2,6);
insert into roles_privileges(role_id,privilege_id) values (2,7);
insert into roles_privileges(role_id,privilege_id) values (2,8);
insert into roles_privileges(role_id,privilege_id) values (2,9);
insert into roles_privileges(role_id,privilege_id) values (2,10);
insert into roles_privileges(role_id,privilege_id) values (2,11);
insert into roles_privileges(role_id,privilege_id) values (1,2);




/*insert into rentalcars (name,adress,description,average_rating) values ('CarFlexi','Beograd','adjiaisdj',4.2);
insert into rentalcars (name,adress,description,average_rating) values ('EasyRentCars','Beograd','bla bla bla',3.9);
insert into rentalcars (name,adress,description,average_rating) values ('EuropeCar','Beograd','cccc',4.8);
insert into rentalcars (name,adress,description,average_rating) values ('Inex Rent A Car','Novi Sad','Najpovoljnije usluge',4.6);
insert into rentalcars (name,adress,description,average_rating) values ('Max Rent A Car','Novi Sad','luux',3.3);
*/
insert into car (car_name,car_number,car_price) values ('BMW',1,500);
insert into car (car_name,car_number,car_price) values ('Audi',2,450);
insert into car (car_name,car_number,car_price) values ('Peugeot',5,300);
insert into car (car_name,car_number,car_price) values ('Mercedes-Benz',7,550);
insert into car (car_name,car_number,car_price) values ('Fiat',4,350);

insert into flight(take_off,landing, time, travel_time,number,seat) values ('lalal','lalala','bnbnb','jdjj',2,true);
insert into flight(take_off,landing, time, travel_time,number,seat) values ('fkijgdf','fdfd','bnbfdfnb','jdggjj',3,true);

insert into avio_flights(aviocompany_id, flight_id) values (1,1);
insert into avio_flights(aviocompany_id, flight_id) values (1,2);
