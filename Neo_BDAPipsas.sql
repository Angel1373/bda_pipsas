create database bda_pipsas;
use bda_pipsas;


-- tabla domicilios de los clientes
create table domicilios (
    id_domicilio int auto_increment primary key,
    calle varchar(100) not null,
    colonia varchar(100) not null,
    numero varchar(10) not null
);


-- tabla clientes
create table clientes (
    id_cliente int auto_increment primary key,
    nombres varchar(100) not null,
    apellido_paterno varchar(50) not null,
    apellido_materno varchar(50),
    fecha_nacimiento date not null,
    edad int not null,
    contrasena varchar(100) not null,
    id_domicilio int,
    foreign key (id_domicilio) references domicilios(id_domicilio)
);


-- tabla telefonos de los clientes
create table telefonos (
    id_telefono int auto_increment primary key,
    numero varchar(15) not null,
    etiqueta varchar(50) not null,
    id_cliente int not null,
    foreign key (id_cliente) references clientes(id_cliente)
);

-- tabla pizzas
create table pizzas (
    id_pizza int auto_increment primary key,
    nombre varchar(100) not null,
    tamano varchar(20) not null,
    descripcion varchar(200) not null,
    precio decimal(10,2) not null,
    disponible boolean not null
);


-- tabla ingredientes
create table ingredientes (
    id_ingrediente int auto_increment primary key,
    nombre varchar(30) not null
);

-- tabla ingredinetes en pizzas
create table ingredientesEnPizzas (
    id_ingredienteEnPizza int auto_increment primary key,
    id_pizza int not null,
    id_ingrediente int not null,
    foreign key (id_pizza) references pizzas(id_pizza),
    foreign key (id_ingrediente) references ingredientes(id_ingrediente)
);

-- tabla cupones
create table cupones (
    id_cupon int auto_increment primary key,
    fechaHoraInicioVigencia datetime not null,
    fechaHoraFinVigencia datetime,
    usos_total int not null,
    usos_max int,
    codigo varchar(50) not null
);


-- tabla pedidos
create table pedidos (
    id_pedido int auto_increment primary key,
    estado enum('pendiente','en preparacion','listo','entregado','cancelado','no reclamado') not null,
    notas varchar(500),
    costo decimal(10,2) not null
);

-- tabla pedidos programados
create table pedidosProgramados (
    id_pedido int primary key,
    id_cliente int not null,
    id_cupon int,
    foreign key (id_pedido) references pedidos(id_pedido),
    foreign key (id_cliente) references clientes(id_cliente),
    foreign key (id_cupon) references cupones(id_cupon)
);

-- tabla pedidos express
create table pedidosExpress (
    id_pedido int primary key,
    nip int not null,
    folio varchar(15) not null,
    foreign key (id_pedido) references pedidos(id_pedido)
);

-- tabla de pizzas en pedidos

create table pizzasEnPedidos (
    id_pizzaEnPedido int auto_increment primary key,
    id_pedido int not null,
    id_pizza int not null,
    cantidad_pizza int not null,
    notas varchar(500),
    foreign key (id_pedido) references pedidos(id_pedido),
    foreign key (id_pizza) references pizzas(id_pizza)
);

-- tabla del historial de los cambios de estado
-- a esta solo se le meten datos con el trigger especial para esta tabla

create table historialCambiosEstado (
    id_cambio int auto_increment primary key,
    id_pedido int not null,
    fechaHoraCambio datetime not null,
    estadoAnterior varchar(50),
    estadoNuevo varchar(50) not null,
    foreign key (id_pedido) references pedidos(id_pedido)
);

-- trigger que registra cambios de estado de un pedido
-- es el que le mete datos a la tabla del historial esa de arriba

delimiter $$

create trigger registra_historial_cambio_estado
after update on pedidos
for each row
begin
    if old.estado != new.estado then
        insert into historialCambiosEstado (id_pedido, fechaHoraCambio, estadoAnterior, estadoNuevo) values (
            old.id_pedido,
            now(),
            old.estado,
            new.estado
        );
    end if;
end$$

delimiter ;


-- trigger que va a cambiar los pedidos programados a express cuando se cumpla la condicion
delimiter $$

create trigger convertir_a_express
after update on pedidos
for each row
begin
    if new.estado in ('cancelado','no reclamado') then
        if exists (
            select 1 from pedidosprogramados 
            where id_pedido = new.id_pedido
        ) then
            delete from pedidosprogramados 
            where id_pedido = new.id_pedido;

            insert into pedidosexpress (id_pedido, nip, folio) values (
				new.id_pedido,
                floor(10000000 + rand() * 90000000), -- genera el nip aleatorio 8 digitos
                concat('exp-', new.id_pedido, '-', floor(rand()*1000)) -- genera el folio
            );

        end if;

    end if;
end$$

delimiter ;


-- storaged procedure y transaccion
-- lo que hace es crear un pedido programado que si incluya cupon
-- y valida que el cupon exista, y se pueda usar
delimiter $$

create procedure crear_pedido_programado_con_cupon(
    in p_id_cliente int,
    in p_codigo_cupon varchar(50),
    in p_notas varchar(500),
    in p_costo decimal(10,2)
)
begin

    declare v_id_cupon int;
    declare v_fecha_inicio datetime;
    declare v_fecha_fin datetime;
    declare v_usos_total int;
    declare v_usos_max int;
    declare v_nuevo_id_pedido int;

    -- por si detecta una sqlexception, hace rollback
    declare exit handler for sqlexception
    begin
        rollback;
    end;

    start transaction;

    -- buscar cupon por el codigo que se puso
    select id_cupon, fechaHoraInicioVigencia, fechaHoraFinVigencia,
           usos_total, usos_max
    into v_id_cupon, v_fecha_inicio, v_fecha_fin,
         v_usos_total, v_usos_max
    from cupones
    where codigo = p_codigo_cupon;

    -- validar que exista
    if v_id_cupon is null then
        signal sqlstate '45000' -- esto de aqui es para lazar una sqlexception manual, significa que no existe el cupon y hace rollback
        set message_text = 'el cupon no existe';
    end if;

    -- validacion de las fechas
    if not (
        now() >= v_fecha_inicio
        and (v_fecha_fin is null or now() <= v_fecha_fin)
    ) then
        signal sqlstate '45000'
        set message_text = 'el cupon no esta vigente';
    end if;

    -- validar los usos
    if v_usos_max is not null and v_usos_total >= v_usos_max then
        signal sqlstate '45000'
        set message_text = 'el cupon ya alcanzo su limite de usos';
    end if;

    -- crear pedido
    insert into pedidos (estado, notas, costo)
    values ('pendiente', p_notas, p_costo);

    set v_nuevo_id_pedido = last_insert_id();

    -- insertar en pedidosProgramados
    insert into pedidosProgramados (id_pedido, id_cliente, id_cupon)
    values (v_nuevo_id_pedido, p_id_cliente, v_id_cupon);

    -- actualiza los usos del cupon
    update cupones
    set usos_total = usos_total + 1
    where id_cupon = v_id_cupon;

    commit;

end$$

delimiter ;