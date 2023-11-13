// Datos del tipó de gasto

INSERT INTO tipo_gastos (tipo) VALUES ('gastos comunes');
INSERT INTO tipo_gastos (tipo) VALUES ('Supermercado');
INSERT INTO tipo_gastos (tipo) VALUES ('Ferreteria');
INSERT INTO tipo_gastos (tipo) VALUES ('Leña');
INSERT INTO tipo_gastos (tipo) VALUES ('Ropa');
INSERT INTO tipo_gastos (tipo) VALUES ('Viaje');


//Datos de los gastos
INSERT INTO gastos (description,price,fecha_gasto,tipo_gasto_id) VALUES ('Se acaba de pagar la boleta de la luz',15100,'2021-03-22',1);
INSERT INTO gastos (description,price,fecha_gasto,tipo_gasto_id) VALUES ('Se Viajo a santiago comnprandose dos pasajes',43000,'2023-06-19',6);
INSERT INTO gastos (description,price,fecha_gasto,tipo_gasto_id) VALUES ('Se acaba de pagar la boleta del telefono',21456,'2023-07-22',1);
INSERT INTO gastos (description,price,fecha_gasto,tipo_gasto_id) VALUES ('Se compro mercaderia en el supermercado unico en Laja',65000,'2023-07-17',2);
INSERT INTO gastos (description,price,fecha_gasto,tipo_gasto_id) VALUES ('Se compro leña como dos metros',50000,'2023-05-17',4);
INSERT INTO gastos (description,price,fecha_gasto,tipo_gasto_id) VALUES ('Compre un timbre',6950,'2023-07-27',3);

// Datos de las notas
INSERT INTO notas (description,fecha_nota,estado) VALUES ('Se debe comprar leña para calentarse','2023-07-27','PENDIENTE')
INSERT INTO notas (description,fecha_nota,estado) VALUES ('Hoy me pagaron 60000 que me debia juan perez','2023-05-27','REALIZADO')
INSERT INTO notas (description,fecha_nota,estado) VALUES ('Tengo cita con el dentista el 3 de agosto ','2023-08-02','PENDIENTE')
INSERT INTO notas (description,fecha_nota,estado) VALUES ('Al final no se pudo salir a santiago como queria :( ','2023-03-27','DESCARTADO')
INSERT INTO notas (description,fecha_nota,estado) VALUES ('Hay que comprar un mac para trabajar','2023-06-27','PENDIENTE')