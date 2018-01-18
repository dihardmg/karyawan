
CREATE TABLE c_security_permission (
    id character varying(255) NOT NULL,
    permission_label character varying(255) NOT NULL,
    permission_value character varying(255) NOT NULL
);

CREATE TABLE c_security_role (
    id character varying(255) NOT NULL,
    description character varying(255),
    name character varying(255) NOT NULL
);

CREATE TABLE c_security_role_permission (
    id_role character varying(255) NOT NULL,
    id_permission character varying(255) NOT NULL
);

CREATE TABLE c_security_user (
    id character varying(255) NOT NULL,
    active boolean NOT NULL,
    username character varying(255) NOT NULL,
    id_role character varying(255) NOT NULL
);

CREATE TABLE c_security_user_password (
    id_user character varying(36) NOT NULL,
    password character varying(255) NOT NULL
);

CREATE TABLE karyawan(
  id character varying(255) NOT NULL,
  nama character varying(255) NOT NULL,
  keterangan character varying(255)
);
CREATE TABLE alamat(
  id character varying(255) NOT NULL,
   id_karyawan character varying(255) NOT NULL,
   nama character varying(255) NOT NULL,
  alamat character varying(255) NOT NULL
);

CREATE TABLE tambahan(
  id character varying(255) NOT NULL,
  tambahan character varying(255) NOT NULL,
  alamat_id character varying(255) NOT NULL
  );
