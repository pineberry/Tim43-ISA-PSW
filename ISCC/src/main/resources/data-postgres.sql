insert into clinic_center_administrator (email, password, first_name, last_name, phone_number, address, city, state, first_login)
values ('isa.pws43@gmail.com', 'nesto', 'Dusan', 'Petkovic', '065282115', 'Lamela e5', 'Teslic', 'Bosna', 'true');

insert into clinic (name, address, description, average_rating)
values ('medika43', 'utopija', 'bolje da ne dolazite', 0);
insert into clinic (name, address, description, average_rating)
values ('izetMed', 'Bascarsija 88', 'Sve rjesavamo maksuzijom', 5);

insert into medical_room (room_name, room_number) values ('Room 1', 1);
insert into procedure_type (type_description, type_name) values ('Description', ' Type 1');
insert into procedure_type (type_description, type_name) values ('Description 2', ' Type 2');

insert into patient (email, password, first_name, last_name, phone_number, address, city, state, health_care_number, status)
values ('dusan.petkovic02@gmail.com', 'password', 'Dusan', 'Petkovic', '123123456', 'Karadjordjeva Lamela E5', 'Teslic', 'Bosna i Hercegovina', '0122HH', 'activated');
insert into patient (email, password, first_name, last_name, phone_number, address, city, state, health_care_number, status)
values ('teodora.95mandic@gmail.com', 'pass', 'Teodora', 'Mandic', '12345', 'Adresa', 'Grad', 'Drzava', '0asd122HH', 'activated');

insert into medical_record (patient_id) values (1);

insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id)
values ('dejan.radulovic@iscc.com', 'nestonesto', 'Dejan', 'Radulovic', 'Druga Tita 33', 'Kula', 'Srbija', '12345123', 3.2, 'false', '07:00', '16:00', 1, 1);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id)
values ('marko.markovic@mail.com', 'markopass', 'Marko', 'Markovic', 'Druga Tita 33', 'Kula', 'Srbija', '12345123', 3.2, 'false', '07:00', '16:00', 1, 1);
insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id)
values ('damir.fazlinovic@mail.com', 'damirpass', 'Damir', 'Fazlinovic', 'Bascarsija 88', 'Sarajevo', 'Bosna i Hercegovina', '033123456', 2.8, 'false', '09:00', '12:00', 2, 2);

insert into medical_procedure (procedure_type_id, date_of_procedure, medical_room_id, doctor_id, price, start_time, end_time, discount, booked)
values (1, '2019-12-12 12:30:00', 1, 1, 162, '12:00', '13:00', 0.0, true);

insert into nurse (email, password, first_name, last_name, phone_number, address, city, state, first_login, clinic_id, on_vacation)
values ('judimila@gmail.com', 'judi4life', 'Judmila', 'Karapandza', '2130457', 'Palih Boraca 1', 'Rakovac Donji', 'Danska', 'false', 1, 'false');

insert into medicine (name, code) values ('Bromezepan', '1A');
insert into medicine (name, code) values ('Paracetamol', '2A');
insert into medicine (name, code) values ('Kafetin', '3A');

insert into diagnosis(name, latin_name, code) values ('Upala uva', 'Upalius usus', '1A1C');
insert into diagnosis(name, latin_name, code) values ('Upala sinusa', 'Rinitis', '1A2C');






