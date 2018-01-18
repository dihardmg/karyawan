INSERT INTO c_security_permission (id, permission_label, permission_value) VALUES
('KEGIATAN', 'Halaman Kegiatan', 'ROLE_KEGIATAN'),
('KARYAWAN_ALL', 'All Kegiatan Karyawan', 'ROLE_KARYAWAN_ALL'),
('MASTER_KARYAWAN', 'Halaman karyawan', 'ROLE_MASTER_KARYAWAN'),
('MASTER_ALAMAT', 'Halaman alamat', 'ROLE_MASTER_ALAMAT'),

('USER_LOGGED_IN', 'Get User Loggin Information', 'ROLE_USER_LOGGED_IN');

INSERT INTO c_security_role (id, description, name) VALUES
('ADMINISTRATOR', 'Application Administrator', 'Administrator');


INSERT INTO c_security_role_permission (id_role, id_permission) VALUES
('ADMINISTRATOR', 'USER_LOGGED_IN'),
('ADMINISTRATOR', 'KEGIATAN'),
('ADMINISTRATOR', 'KARYAWAN_ALL'),
('ADMINISTRATOR', 'MASTER_ALAMAT'),
('ADMINISTRATOR', 'MASTER_KARYAWAN');



INSERT INTO c_security_user (id, active, username, id_role) VALUES
('admin', true,'admin', 'ADMINISTRATOR');

INSERT INTO c_security_user_password (id_user, password) VALUES
('admin', '$2a$10$wT/obdcvI07jSor8roN3NeuWhEdlarvunJ14ZOAFdo1g/lZ1uHbGe');

