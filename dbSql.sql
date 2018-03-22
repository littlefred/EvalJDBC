CREATE TABLE teach ( 
teach_pk_id SERIAL PRIMARY KEY,
name VARCHAR(30) CHECK (name <> '')
);

CREATE TABLE class (
class_pk_id SERIAL PRIMARY KEY,
name VARCHAR(15) CHECK (name <> '')
);

CREATE TABLE teacher (
teacher_pk_id SERIAL PRIMARY KEY,
kind VARCHAR(3) NOT NULL,
name VARCHAR(30) CHECK (name <> ''),
firstName VARCHAR(15) CHECK (name <> ''),
birthDate DATE NOT NULL,
teach_pk_id INT REFERENCES teach(teach_pk_id) NOT NULL
);

CREATE TABLE student (
student_pk_id SERIAL PRIMARY KEY,
kind VARCHAR(3) NOT NULL,
name VARCHAR(30) CHECK (name <> ''),
firstName VARCHAR(15) CHECK (name <> ''),
birthDate DATE NOT NULL,
class_pk_id INT REFERENCES class(class_pk_id) NOT NULL
);
