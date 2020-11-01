insert all 
into types (type_id, type_name) VALUES(1, 'Single')
into types (type_id, type_name) VALUES(2, 'Double')
into types (type_id, type_name) VALUES(3, 'Triple')
into types (type_id, type_name) VALUES(4, 'Quad')
into types (type_id, type_name) VALUES(5, 'Queen')
into types (type_id, type_name) VALUES(6, 'King')
into types (type_id, type_name) VALUES(7, 'Twin')
into types (type_id, type_name) VALUES(8, 'Suite')
select 1 from dual;


insert all 
into rooms (room_id, type_id, room_number, room_rating) VALUES(1, '1', '101', 5)
into rooms (room_id, type_id, room_number, room_rating) VALUES(2, '3', '102', 5)
into rooms (room_id, type_id, room_number, room_rating) VALUES(3, '5', '203', 6)
into rooms (room_id, type_id, room_number, room_rating) VALUES(4, '7', '204', 7)
into rooms (room_id, type_id, room_number, room_rating) VALUES(5, '8', '305', 8)
into rooms (room_id, type_id, room_number, room_rating) VALUES(6, '6', '306', 10)
into rooms (room_id, type_id, room_number, room_rating) VALUES(7, '4', '307', 8)
into rooms (room_id, type_id, room_number, room_rating) VALUES(8, '2', '308', 9)

select 1 from dual;

insert all 
into reservation_types (reservation_type_id, reservation_type) VALUES(1, 'Telephone')
into reservation_types (reservation_type_id, reservation_type) VALUES(2, 'In Person')
into reservation_types (reservation_type_id, reservation_type) VALUES(3, 'Internet')
into reservation_types (reservation_type_id, reservation_type) VALUES(4, 'Central Reservation System')
into reservation_types (reservation_type_id, reservation_type) VALUES(5, 'Letter')


select 1 from dual;


insert all 
into food_type (food_type_id, food_type) VALUES(1, 'All Inclusive')
into food_type (food_type_id, food_type) VALUES(2, 'Ultra All Inclusive')
into food_type (food_type_id, food_type) VALUES(3, 'Bed and Breakfast')
into food_type (food_type_id, food_type) VALUES(4, 'Half Board')
into food_type (food_type_id, food_type) VALUES(5, 'Full Board')
into food_type (food_type_id, food_type) VALUES(6, 'Self Catering')


select 1 from dual;


insert all 
into countys (county_id, country_name) VALUES(1, 'Bulgaria')
into countys (county_id, country_name) VALUES(2, 'Albania')
into countys (county_id, country_name) VALUES(3, 'Bosnia and Herzegovina')
into countys (county_id, country_name) VALUES(4, 'Croatia')
into countys (county_id, country_name) VALUES(5, 'Kosovo')
into countys (county_id, country_name) VALUES(6, 'Montenegro')
into countys (county_id, country_name) VALUES(7, 'North Macedonia')
into countys (county_id, country_name) VALUES(8, 'Romania')
into countys (county_id, country_name) VALUES(9, 'Serbia')
into countys (county_id, country_name) VALUES(10, 'Slovenia')
into countys (county_id, country_name) VALUES(11, 'Greece')
into countys (county_id, country_name) VALUES(12, 'Turkey')
into countys (county_id, country_name) VALUES(13, 'China')
into countys (county_id, country_name) VALUES(14, 'India')
into countys (county_id, country_name) VALUES(15, 'United States')
into countys (county_id, country_name) VALUES(16, 'Indonesia')
into countys (county_id, country_name) VALUES(17, 'Pakistan')
into countys (county_id, country_name) VALUES(18, 'Brazil')
into countys (county_id, country_name) VALUES(19, 'Nigeria')
into countys (county_id, country_name) VALUES(20, 'Russia')
into countys (county_id, country_name) VALUES(21, 'Mexico')
into countys (county_id, country_name) VALUES(22, 'Japan')
into countys (county_id, country_name) VALUES(23, 'Germany')
into countys (county_id, country_name) VALUES(24, 'France')
into countys (county_id, country_name) VALUES(25, 'Egypt')
into countys (county_id, country_name) VALUES(26, 'United Kingdom')
into countys (county_id, country_name) VALUES(27, 'Italy')
into countys (county_id, country_name) VALUES(28, 'Spain')
into countys (county_id, country_name) VALUES(29, 'Uktaine')
into countys (county_id, country_name) VALUES(30, 'Iraq')
into countys (county_id, country_name) VALUES(31, 'Poland')
into countys (county_id, country_name) VALUES(32, 'Canada')
into countys (county_id, country_name) VALUES(33, 'Portugal')
into countys (county_id, country_name) VALUES(34, 'Hungary')
into countys (county_id, country_name) VALUES(35, 'Belarus')
into countys (county_id, country_name) VALUES(36, 'Austria')
into countys (county_id, country_name) VALUES(37, 'Switzerland')
into countys (county_id, country_name) VALUES(38, 'Denmark')
into countys (county_id, country_name) VALUES(39, 'Finland')
into countys (county_id, country_name) VALUES(40, 'Slovakia')
into countys (county_id, country_name) VALUES(41, 'Norway')
into countys (county_id, country_name) VALUES(42, 'Ireland')
into countys (county_id, country_name) VALUES(43, 'Panama')
into countys (county_id, country_name) VALUES(44, 'Croatia')
into countys (county_id, country_name) VALUES(45, 'Puerto Rico')
into countys (county_id, country_name) VALUES(46, 'Latvia')
into countys (county_id, country_name) VALUES(47, 'Estonia')
into countys (county_id, country_name) VALUES(48, 'Cyprus')
into countys (county_id, country_name) VALUES(49, 'Malta')
into countys (county_id, country_name) VALUES(50, 'Iceland')




select 1 from dual;


insert all 
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(1, 'Parking', 10)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(2, 'Taxi', 10)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(3, 'Lobby Bar Breakfast', 15)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(4, 'Lobby Bar Lunch', 15)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(5, 'Lobby Bar Dinner', 15)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(6, 'Lobby Bar Drinks', 15)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(7, 'Late Check In/Out', 10)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(8, 'Early Check In/Out', 10)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(9, 'Lost Key/Card', 10)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(10, 'Safe Box 7 Days', 35)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(12, 'Safe Box 14 Days', 60)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(13, 'Cleaning', 20)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(14, 'SPA', 70)
into addit_serv (addit_serv_id, addit_serv_title, addit_serv_price) VALUES(15, 'NoServices', 0)

select 1 from dual;




insert all 
into user_roles (user_role_id, user_role) VALUES(1, 'Administrator')
into user_roles (user_role_id, user_role) VALUES(2, 'Hotel Owner')
into user_roles (user_role_id, user_role) VALUES(3, 'Manager')
into user_roles (user_role_id, user_role) VALUES(4, 'Receptionist')


select 1 from dual;

insert all 
into users (user_id, user_name, user_password, role_id) VALUES(1, 'Admin', '7Zq112!mHz', 1)
into users (user_id, user_name, user_password, role_id) VALUES(2, 'Ivan.Evgeniev', 'BlueskYes13', 2)
into users (user_id, user_name, user_password, role_id) VALUES(3, 'Kalina.Yordanova', 'KotkataMIEbqla!!', 3)
into users (user_id, user_name, user_password, role_id) VALUES(4, 'Luchezar.Mihailov', 'V1568ZH', 4)




select 1 from dual;

insert all 
into clients (client_id, client_name, client_birth_date, country_id, client_sex, client_passport_number, client_passport_date,client_car_number,client_note,client_rating) 
VALUES(1, 'Kaloyan Kyuchukov', 24.09.1999, 1, 1, 18621369, 13.02.2029, В1369НМ, '', 7)

into clients (client_id, client_name, client_birth_date, country_id, client_sex, client_passport_number, client_passport_date,client_car_number,client_note,client_rating) 
VALUES(2, 'Yordan Boev', 17.08.1970, 1, 1, 51267821, 16.09.2022, СВ1160СВ,'', 5)

into clients (client_id, client_name, client_birth_date, country_id, client_sex, client_passport_number, client_passport_date,client_car_number,client_note,client_rating) 
VALUES(3, 'Lyubomir Manushkin', 25.006.1988, 1, 1, 66261894, 19.01.2028, В9999СА,'', 8)





select 1 from dual;


insert all 
into reservation (reservation_id, reservation_type, room_type, cancel_type, vucher, start_date, end_date, adults, food_type_id, room_id, total_price, reservation_status_id, notes, receptionist_id) 
VALUES(110,1,3,0,0,10.01.2020,17.01.2020,'',2,101,250,1,'',1)

into reservation (reservation_id, reservation_type, room_type, cancel_type, vucher, start_date, end_date, adults, food_type_id, room_id, total_price, reservation_status_id, notes, receptionist_id) 
VALUES(111,2,2,2,1,15.02.2020,17.02.2020,'',1,201,100,2,'',1)

into reservation (reservation_id, reservation_type, room_type, cancel_type, vucher, start_date, end_date, adults, food_type_id, room_id, total_price, reservation_status_id, notes, receptionist_id) 
VALUES(112,3,1,0,0,17.02.2020,24.02.2020,'',4,306,280,1,'',1)





select 1 from dual;

insert all 
into reservation_status (reservation_status_id, reservation_status) 
VALUES(1, 'Confirmed')

into reservation_status (reservation_status_id, reservation_status) 
VALUES(2, 'Canceled')

into reservation_status (reservation_status_id, reservation_status) 
VALUES(3, 'Awaiting Confirmation')


select 1 from dual;

insert all 
into reservation_cancel_types (reservation_cancel_type_id, reservation_cancel_type)
VALUES(1, 'Almost instant')

into reservation_cancel_types (reservation_cancel_type_id, reservation_cancel_type)
VALUES(2, 'Delayed')

into reservation_cancel_types (reservation_cancel_type_id, reservation_cancel_type)
VALUES(0, 'Not Cancelled')


select 1 from dual;
