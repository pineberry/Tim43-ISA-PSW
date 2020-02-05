insert into clinic_center_administrator (email, password, first_name, last_name, phone_number, address, city, state, first_login)
values ('isa.pws43@gmail.com', 'nesto', 'Dusan', 'Petkovic', '065282115', 'Lamela e5', 'Teslic', 'Bosna', 'true');

insert into clinic (name, address, description, average_rating)
values ('MediGroup dr Ristić Pariske komune', 'Pariske Komune 26, 11070 Novi Beograd', 'Dom zdravlja', 0);
insert into clinic (name, address, description, average_rating)
values ('Medika Jedro', 'Jurija Gagarina 14, 11070 Novi Beograd', 'Dom zdravlja', 0);
insert into clinic (name, address, description, average_rating)
values ('Medika dr Cvjetković', 'Balzakova 44, Novi Sad 21000', 'Dom zdravlja', 0);
insert into clinic (name, address, description, average_rating)
values ('Zdravolije', 'Bulevar Kralja Aleksandra 294, 11050 Beograd', 'Dom zdravlja', 0);
insert into clinic (name, address, description, average_rating)
values ('Zdravo Med Medigroup', 'Vuka Karadžića 19, Subotica', 'Zavod za zdravstvenu zaštitu radnika', 0);
insert into clinic (name, address, description, average_rating)
values ('IzetMed', 'Bascarsija 88', 'Sve rjesavamo maksuzijom', 5);
insert into clinic (name, address, description, average_rating)
values ('Medika43', 'Bulevar Cara Lazara 2, Novi Sad 21000', 'poliklinika', 0);

insert into medical_room (room_name, room_number, clinic_id) values ('GM-a', 1, 1);
insert into medical_room (room_name, room_number, clinic_id) values ('GM-a', 2, 1);
insert into medical_room (room_name, room_number, clinic_id) values ('GM-b', 1, 1);
insert into medical_room (room_name, room_number, clinic_id) values ('GM-b', 2, 1);

insert into procedure_type (type_name, type_description) values ('Hematology', 'Medical examination');
insert into procedure_type (type_name, type_description) values ('Endocrinology', 'Medical examination');
insert into procedure_type (type_name, type_description) values ('Cardiology', 'Medical examination');
insert into procedure_type (type_name, type_description) values ('Adolescent medicine', 'Medical examination');
insert into procedure_type (type_name, type_description) values ('Pulmonology', 'Medical examination');
insert into procedure_type (type_name, type_description) values ('Sports medicine', 'Medical examination');
insert into procedure_type (type_name, type_description) values ('Oftamology', 'Medical examination');
insert into procedure_type (type_name, type_description) values ('Geriatric medicine', 'Medical examination');

insert into patient (email, password, first_name, last_name, phone_number, address, city, state, health_care_number, status)
values ('dusan.petkovic02@gmail.com', 'password', 'Dusan', 'Petkovic', '123123456', 'Karadjordjeva Lamela E5', 'Teslic', 'Bosna i Hercegovina', '0122HH', 'activated');
insert into patient (email, password, first_name, last_name, phone_number, address, city, state, health_care_number, status)
values ('teodora.95mandic@gmail.com', 'pass', 'Teodora', 'Mandic', '12345', 'Adresa', 'Grad', 'Drzava', '0asd122HH', 'activated');
insert into patient (email, password, first_name, last_name, phone_number, address, city, state, health_care_number, status)
values ('pacijent@gmail.com', 'nesto', 'Marko', 'Markovic', '06000060', 'Centar', 'Novi Sad', 'Srbija', 's007', 'activated');

insert into medical_record (height, weight, blood_type, patient_id) values (174, 72, 'AB-', 1);
insert into medical_record (height, weight, blood_type, patient_id, date_of_birth) values (168, 59, 'O+', 2, '1995-05-27');

insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('dejan.radulovic@iscc.com', 'nestonesto', 'Dejan', 'Radulovic', 'Druga Tita 33', 'Kula', 'Srbija', '12345123', 3.2, 'false', '07:00', '16:00', 1, 1, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('marko.markovic@mail.com', 'markopass', 'Marko', 'Markovic', 'Druga Tita 33', 'Kula', 'Srbija', '12345123', 3.2, 'false', '07:00', '16:00', 4, 1, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('damir.fazlinovic@mail.com', 'damirpass', 'Damir', 'Fazlinovic', 'Bascarsija 88', 'Sarajevo', 'Bosna i Hercegovina', '033123456', 2.8, 'false', '09:00', '12:00', 3, 6, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('brankabajic@mail.com', 'nestonesto', 'Branka', 'Bajić', 'Pariske Komune 26', 'Novi Beograd', 'Srbija', '123456', 0, 'false', '07:00', '16:00', 1, 1, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('idajovanovic@mail.com', 'nestonesto', 'Ida', 'Jovanović', 'Jurija Gagarina 14', 'Novi Beograd', 'Srbija', '123456', 0, 'false', '07:00', '16:00', 3, 2, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('vesnabogdanovic@mail.com', 'nestonesto', 'Vesna', 'Bogdanović', 'Balzakova 44', 'Novi Sad', 'Srbija', '123456', 0, 'false', '07:00', '16:00', 4, 3, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('sladjanadrobnjak@mail.com', 'nestonesto', 'Slađana', 'Drobnjak', 'Bulevar Kralja Aleksandra 294', 'Beograd', 'Srbija', '123456', 0, 'false', '07:00', '16:00', 2, 4, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('zorantomic@mail.com', 'nestonesto', 'Zoran', 'Tomić', 'Vuka Karadžića 19', 'Subotica', 'Srbija', '123456', 0, 'false', '07:00', '16:00', 7, 5, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('slavicabbumbic@mail.com', 'nestonesto', 'Slavica', 'Bajagić Bumbić', 'Bascarsija 88', 'Sarajevo', 'Bosna i Hercegovina', '123456', 0, 'false', '07:00', '16:00', 5, 2, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('zagorkabbasta@mail.com', 'nestonesto', 'Zagorka', 'Bećarević Basta', 'Bulevar Cara Lazara 2', 'Novi Sad', 'Srbija', '123456', 0, 'false', '07:00', '16:00', 6, 7, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('ivanabjankucic@mail.com', 'nestonesto', 'Ivana', 'Branković-Jankucić', 'Pariske Komune 26', 'Novi Beograd', 'Srbija', '123456', 0, 'false', '07:00', '16:00', 8, 1, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('petarbursac@mail.com', 'nestonesto', 'Petar', 'Bursać', 'Balzakova 44', 'Novi Sad', 'Srbija', '123456', 0, 'false', '07:00', '16:00', 8, 3, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('veselinkajovanovic@mail.com', 'nestonesto', 'Veselinka', 'Jovanović', 'Bulevar Kralja Aleksandra 294', 'Beograd', 'Srbija', '123456', 0, 'false', '07:00', '16:00', 2, 4, true);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('dragoslavmaksimovic@mail.com', 'nestonesto', 'Dragoslav', 'Maksimović', 'Vuka Karadžića 19', 'Subotica', 'Srbija', '123456', 0, 'false', '07:00', '16:00', 4, 5, true);


insert into medical_procedure (procedure_type_id, date_of_procedure, medical_room_id, doctor_id, patient_id, price, start_time, end_time, discount, booked)
values (1, '2019-12-12 12:30:00', 1, 1, 2, 162, '12:00', '13:00', 0.0, true);
insert into medical_procedure (procedure_type_id, date_of_procedure, medical_room_id, doctor_id, patient_id, price, start_time, end_time, discount, booked)
values (1, '2020-10-3 12:30:00', 3, 1, 2, 162, '12:00', '13:00', 0.0, true);
insert into medical_procedure (procedure_type_id, date_of_procedure, medical_room_id, doctor_id, patient_id, price, start_time, end_time, discount, booked)
values (3, '2019-12-20 11:00:00', 2, 3, 2, 180, '11:00', '11:30', 0.0, true);
insert into medical_procedure (procedure_type_id, date_of_procedure, medical_room_id, doctor_id, patient_id, price, start_time, end_time, discount, booked)
values (3, '2020-10-10 13:00:00', 2, 3, 2, 150, '13:00', '13:30', 0.0, true);
insert into medical_procedure (procedure_type_id, date_of_procedure, medical_room_id, doctor_id, patient_id, price, start_time, end_time, discount, booked)
values (2, '2020-3-1 12:30:00', 1, 1, 3, 162, '08:00', '9:30', 0.0, true);
insert into medical_procedure (procedure_type_id, date_of_procedure, medical_room_id, doctor_id, patient_id, price, start_time, end_time, discount, booked)
values (1, '2020-2-9 12:30:00', 1, 1, 3, 162, '8:40', '9:50', 0.0, true);

insert into nurse (email, password, first_name, last_name, phone_number, address, city, state, first_login, clinic_id, on_vacation)
values ('judimila@gmail.com', 'judi4life', 'Judmila', 'Karapandza', '2130457', 'Palih Boraca 1', 'Rakovac Donji', 'Danska', 'false', 1, 'false');

insert into medicine (name, code) values ('Bromezepan', '1A');
insert into medicine (name, code) values ('Paracetamol', '2A');
insert into medicine (name, code) values ('Kafetin', '3A');
insert into medicine (name, code) values ('Panklav', '4-A1');

insert into diagnosis(name, latin_name, code) values ('Upala uva', 'Upalius usus', '1A1C');
insert into diagnosis(name, latin_name, code) values ('Upala sinusa', 'Rinitis', '1A2C');
insert into diagnosis(name, latin_name, code) values ('Zapaljenje zdrela', 'Laringitis', '1A3C');
insert into diagnosis(name, latin_name, code) values ('Inflamacija brongija', 'Bronhitis', '1A4C');
insert into diagnosis(name, latin_name, code) values ('Hronicna glavobolja', 'Migrena', '1A5C');
insert into diagnosis(name, latin_name, code) values ('Dijabetes tip I', 'Diabetes mellitus', '1B1C');
insert into diagnosis(name, latin_name, code) values ('Dijabetes tip II', 'Diabetes mellitus II', '1B2C');
--insert into diagnosis(name, latin_name, code) values ('', '', '');

insert into report (notes, diagnosis_id, doctor_id, record_id) 
values ('Pacijent se zali na zacepljeno levo uvo. Izrazena osetljivost pri dodiru oko usnog kanala', 1, 4, 2);
insert into report (notes, diagnosis_id, doctor_id, record_id) 
values ('Pacijent se zali na zacepljenje sinusa, ne moze da dise kroz nos', 2, 4, 2);
insert into report (notes, diagnosis_id, doctor_id, record_id) 
values ('Vidljiva inflamacija zadnjeg dela zdrela i grkljana, otekli krajnici', 3, 6, 2);
insert into report (notes, diagnosis_id, doctor_id, record_id) 
values ('Pacijent se zali na konstantne bolove u predelu prednjeg dela lobanje i slepoocnica', 5, 2, 2);

insert into prescription (checked, medicine_id, clinic_id, nurse_id, report_id) values ('true', 4, 4, 1, 1);
insert into prescription (checked, medicine_id, clinic_id, nurse_id, report_id) values ('true', 2, 4, 1, 2);
insert into prescription (checked, medicine_id, clinic_id, nurse_id, report_id) values ('true', 4, 4, 1, 2);
insert into prescription (checked, medicine_id, clinic_id, nurse_id, report_id) values ('true', 4, 4, 1, 3);
insert into prescription (checked, medicine_id, clinic_id, nurse_id, report_id) values ('true', 3, 4, 1, 4);

insert into clinic_administrator (email, password, first_name, last_name, phone_number, address, city, state, clinic_id, first_login)
values ('admin@gmail.com', 'nesto', 'Dwight', 'Schrute', '065000000', 'Schrute Farm', 'Scranton', 'Pennsylvania', 1, false);

insert into report(notes, diagnosis_id, doctor_id, record_id) values ('Random notes', 1, 1, 1);





