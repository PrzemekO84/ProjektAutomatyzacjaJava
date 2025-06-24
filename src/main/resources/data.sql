INSERT INTO users (id, username, password, role)
VALUES
    (1, 'admin', '$2a$10$Fhnvp.XblY6ue9U13WONuOCZwrL/hDcArTDDYPY7ZIfxk5t3GC7qG', 'ADMIN'),
    (2, 'doctor1', '$2a$10$Fhnvp.XblY6ue9U13WONuOCZwrL/hDcArTDDYPY7ZIfxk5t3GC7qG', 'DOCTOR'),
    (3, 'patient1', '$2a$10$Fhnvp.XblY6ue9U13WONuOCZwrL/hDcArTDDYPY7ZIfxk5t3GC7qG', 'PATIENT');

INSERT INTO doctor (id, name, specialization)
VALUES
    (1, 'Dr. Jan Kowalski', 'Kardiolog'),
    (2, 'Dr. Anna Nowak', 'Dermatolog');

INSERT INTO appointment (id, patient_id, doctor_id, date)
VALUES
    (1, 3, 1, '2025-06-01 10:00:00'),
    (2, 3, 2, '2025-06-02 12:00:00');
