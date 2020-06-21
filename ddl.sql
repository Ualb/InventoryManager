create table producto
(
    producto_id     integer
        constraint producto_pk
            primary key,
    nombre          text not null,
    prod_maestro_id integer
        references producto,
    reserva         float,
    nivel           integer,
    costo           float,
    cS              float,
    cL              float,
    cH              float,
    tipoDemanda     text not null
);

create table demanda
(
    demanda_id   integer
        primary key autoincrement,
    u_tiempo     text  not null,
    c_tiempo     float not null,
    t_disponible float,
    producto_id  integer
        references producto
);

create table historial
(
    historial_id integer
        primary key autoincrement,
    q_optimo     float not null,
    rop          float not null,
    t_espera     float not null,
    fecha        date  not null,
    producto_id  integer
        references producto
);