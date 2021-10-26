

INSERT INTO clientes (id, nombre, apellido, email ,create_at, foto) VALUES (1,'Rodrigo' , 'Jeldes' , 'rjeldes@mail.cl' , '1983-08-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (2, 'Julian' , 'Jeldes' , 'Julian@mail.cl' , '2016-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (3, 'Elmo' , 'Jhon' , 'elmo@mail.cl' , '2012-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email ,create_at, foto) VALUES (4,'Robert' , 'Jeldes' , 'rjeldes@mail.cl' , '1983-08-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (5, 'pepe' , 'Jeldes' , 'Julian@mail.cl' , '2016-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (6, 'jose' , 'Jhon' , 'elmo@mail.cl' , '2012-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email ,create_at, foto) VALUES (7,'Raul' , 'Jeldes' , 'rjeldes@mail.cl' , '1983-08-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (8, 'Juliberto' , 'Jeldes' , 'Julian@mail.cl' , '2016-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (9, 'Enrrique' , 'Jhon' , 'elmo@mail.cl' , '2012-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email ,create_at, foto) VALUES (10,'Ramiro' , 'Jeldes' , 'rjeldes@mail.cl' , '1983-08-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (11, 'fransisco' , 'Jeldes' , 'Julian@mail.cl' , '2016-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (12, 'Emanual' , 'Jhon' , 'elmo@mail.cl' , '2012-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email ,create_at, foto) VALUES (13,'Rnald' , 'Jeldes' , 'rjeldes@mail.cl' , '1983-08-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (14, 'Judith' , 'Jeldes' , 'Julian@mail.cl' , '2016-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (15, 'Evo' , 'Jhon' , 'elmo@mail.cl' , '2012-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email ,create_at, foto) VALUES (16,'Rodrigo' , 'Jeldes' , 'rjeldes@mail.cl' , '1983-08-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (27, 'Julian' , 'Jeldes' , 'Julian@mail.cl' , '2016-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (28, 'Elmo' , 'Jhon' , 'elmo@mail.cl' , '2012-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email ,create_at, foto) VALUES (19,'Rodrigo' , 'Jeldes' , 'rjeldes@mail.cl' , '1983-08-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (20, 'Julian' , 'Jeldes' , 'Julian@mail.cl' , '2016-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (21, 'Elmo' , 'Jhon' , 'elmo@mail.cl' , '2012-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email ,create_at, foto) VALUES (22,'Rodrigo' , 'Jeldes' , 'rjeldes@mail.cl' , '1983-08-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (23, 'Julian' , 'Jeldes' , 'Julian@mail.cl' , '2016-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (24, 'Elmo' , 'Jhon' , 'elmo@mail.cl' , '2012-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (25, 'Julian' , 'Jeldes' , 'Julian@mail.cl' , '2016-12-12', '');
INSERT INTO clientes (id, nombre, apellido, email,create_at, foto) VALUES (26, 'Elmo' , 'Jhon' , 'elmo@mail.cl' , '2012-12-12', '');



INSERT INTO productos (nombre, precio,create_at) VALUES ('Guitarra electrica fender', 1000000, now());
INSERT INTO productos (nombre, precio,create_at) VALUES ('Guitarra Acustica fender', 2123345, now());
INSERT INTO productos (nombre, precio,create_at) VALUES ('Bateria mapex', 5000000, now());
INSERT INTO productos (nombre, precio,create_at) VALUES ('Bajao electrico samic', 677000, now());
INSERT INTO productos (nombre, precio,create_at) VALUES ('Piano digital yamaha', 789000, now());
INSERT INTO productos (nombre, precio,create_at) VALUES ('Parlantes activos bose', 4567834, now());
INSERT INTO productos (nombre, precio,create_at) VALUES ('Amplificador marshall', 2045600, now());
INSERT INTO productos (nombre, precio,create_at) VALUES ('Cueardas de guitarra electrica Fender', 5000, now());


/*cargar facturas*/
INSERT INTO facturas (descripcion, observacion,cliente_id, create_at) VALUES ('Factura instrumentos', null,1, now());
INSERT INTO detalles_facturas (cantidad, factura_id,producto_id) VALUES (1,1,1);
INSERT INTO detalles_facturas (cantidad, factura_id,producto_id) VALUES (1,1,2);
INSERT INTO detalles_facturas (cantidad, factura_id,producto_id) VALUES (1,1,3);


