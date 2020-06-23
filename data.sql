#           Entradas para el modelo Q
#

# primer producto
insert into producto (nombre, prod_maestro_id, reserva, nivel, costo, cS, cL, cH, tipo_demanda, tipo_producto) VALUES
('Faja Lisa 36"', null, 0, 1, 55, 2.5, 2, 5, 'Constante', 'Independiente');
# demanda del primer producto
insert into demanda (u_tiempo, cantidad, c_tiempo, t_disponible, producto_id)
values ('mes', 150, 1.0, null, 2);

# segundo producto
insert into producto (nombre, prod_maestro_id, reserva, nivel, costo, cS, cL, cH, tipo_demanda, tipo_producto) VALUES
('Faja Lisa 38"', null, 0, 1, 60, 2.5, 2, 5, 'Constante', 'Independiente');
# demanda del segundo producto
insert into demanda (u_tiempo, cantidad, c_tiempo, t_disponible, producto_id)
values ('mes', 210, 1.0, null, 3);

# tercer producto
insert into producto (nombre, prod_maestro_id, reserva, nivel, costo, cS, cL, cH, tipo_demanda, tipo_producto) VALUES
('Faja Lisa 40"', null, 0, 1, 65, 2.5, 2, 5, 'Constante', 'Independiente');
# demanda del primer producto
insert into demanda (u_tiempo, cantidad, c_tiempo, t_disponible, producto_id)
values ('mes', 200, 1.0, null, 4);

#           Modelo P
#

# producto de demanda real
insert into producto (nombre, prod_maestro_id, reserva, nivel, costo, cS, cL, cH, tipo_demanda, tipo_producto)
values ('Faja Vaquera', null, 0, 1, 80, 3.5, 2, 5, 'Real', 'Independiente');
# demanda 1
insert into demanda (u_tiempo, cantidad, c_tiempo, t_disponible, producto_id)
values ('mes', 80, 1.0, 20, 5);
# demanda 2
insert into demanda (u_tiempo, cantidad, c_tiempo, t_disponible, producto_id)
values ('mes', 90, 2.0, 22, 5);
# demanda 3
insert into demanda (u_tiempo, cantidad, c_tiempo, t_disponible, producto_id)
values ('mes', 100, 3.0, 21, 5);

# para las entradas del modelo P se necesita
# sigma = desvicion estandar = sqrt(1/N*(sumatoria(xi-miu)^2)
# miu = sumatoria de las demandas/ numero de demandas
# d (demanda diaria) = sumatoria de las demandas / sumatoria de los dias dispoonibles
# L lo posee el producto
# los demas parametros se piden al cliente


# modelo MRP y Plan Agregado
#

# producto por semana - al consultar ordenar por c_tiempo
insert into producto (nombre, prod_maestro_id, reserva, nivel, costo, cS, cL, cH, tipo_demanda, tipo_producto)
values ('Albarda', null, 0, 1.0, 5000, 300, 2, 10, 'Real', 'Dependiente');

# demandas MRP
insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('semana', 1.0, null, 6, 3);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('semana', 2.0, null, 6, 4);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('semana', 3.0, null, 6, 3);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('semana', 4.0, null, 6, 4);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('semana', 5.0, null, 6, 5);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('semana', 6.0, null, 6, 4);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('semana', 7.0, null, 6, 4);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('semana', 8.0, null, 6, 3);

# demandas para plan agregado

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('mes', 1.0, 20, 6, 14);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('mes', 2.0, 22, 6, 16);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('mes', 3.0, 19, 6, 13);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('mes', 4.0, 21, 6, 15);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('mes', 5.0, 20, 6, 15);

insert into demanda (u_tiempo, c_tiempo, t_disponible, producto_id, cantidad)
values ('mes', 6.0, 20, 6, 14);
