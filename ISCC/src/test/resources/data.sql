insert into clinic (name, address, description, average_rating)
values ('Test klinika', 'Ulica Uliceva 1', 'Opis klinike', 3);

insert into procedure_type (type_name, type_description, price) values ('Tip procedure', 'Opis procedure', 100);

insert into doctor (email, password, first_name, last_name, address, city, state, phone_number, average_rating, on_vacation, workingtime_start, workingtime_end, specialized_id, clinic_id, first_login)
values ('doctor@mail.com', 'password', 'Marko', 'Markovic', 'Ulica Uliceva 1', 'Gradograd', 'Drznana', '123456789', 3.0, 'false', '09:00', '16:00', 1, 1, true);

insert into medical_room (room_name, room_number, clinic_id) values ('Room', 1, 1);

insert into patient (email, password, first_name, last_name, phone_number, address, city, state, health_care_number, status)
values ('patient@mail.com', 'password', 'Petar', 'Petrovic', '111222333', 'Bulevar Kralja Petra 88', 'Novi Sad', 'Srbija', '12gc86swA', 'activated');