insert into clinic_center_administrator (email, password, first_name, last_name, phone_number, address, city, state)
values ('isa.pws43@gmail.com', 'nesto', 'Dusan', 'Petkovic', '065282115', 'Lamela e5', 'Teslic', 'Bosna');

insert into clinic (name, address, description, average_rating)
values ('medika43', 'utopija', 'bolje da ne dolazite', 0);

insert into medical_room (room_name, room_number) values ('Room 1', 1);
insert into procedure_type (type_description, type_name) values ('Description', ' Type 1');

insert into medical_record default values;

insert into patient (email, password, first_name, last_name, phone_number, address, city, state, health_care_number, status)
values ('dusan.petkovic02@gmail.com', 'password', 'Dusan', 'Petkovic', '123123456', 'Karadjordjeva Lamela E5', 'Teslic', 'Bosna i Hercegovina', '0122HH', 'activated');

insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end)
values ('dejan.radulovic@iscc.com', 'nestonesto', 'Dejan', 'Radulovic', 'Druga Tita 33', 'Kula', 'Srbija', '12345123', 3.2, 'false', '07:00', '16:00');

insert into medicine (name, code) values ('Bromezepan', '1A');
insert into medicine (name, code) values ('Paracetamol', '2A');
insert into medicine (name, code) values ('Kafetin', '3A');

insert into diagnosis(name, latin_name, code) values ('Upala uva', 'Upalius usus', '1A1C');
insert into diagnosis(name, latin_name, code) values ('Upala sinusa', 'Rinitis', '1A2C');






