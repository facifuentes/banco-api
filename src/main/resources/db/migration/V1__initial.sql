CREATE TABLE sc_banco.cliente (
    cli_id SERIAL NOT NULL,
    cli_clave varchar(255) NOT NULL,
    cli_estado boolean NOT NULL,
    nombre varchar(255) NOT NULL,
    genero varchar(255) ,
    edad int ,
    identificacion varchar(20) NOT NULL,
    direccion varchar(255) NOT NULL,
    telefono varchar(20) NOT NULL,
    PRIMARY KEY (cli_id)
);

ALTER TABLE sc_banco.cliente ADD CONSTRAINT uk_identificacion UNIQUE (identificacion);


CREATE TABLE sc_banco.cuenta (
      cnt_id SERIAL NOT NULL,
      cnt_numero int NOT NULL,
      cnt_tipo varchar(50) NOT NULL,
      cnt_saldo_inicial int NOT NULL,
      cnt_estado boolean NOT NULL,
      cnt_cliente int NOT NULL,
      PRIMARY KEY (cnt_id)
);

ALTER TABLE ONLY sc_banco.cuenta
    ADD CONSTRAINT fk_cuenta_cliente FOREIGN KEY (cnt_cliente) REFERENCES sc_banco.cliente(cli_id) ON DELETE RESTRICT;

CREATE TABLE sc_banco.movimiento (
     mvt_id SERIAL NOT NULL,
     mvt_fecha timestamp without time zone default now() NOT NULL,
     mvt_tipo varchar(50) NOT NULL,
     mvt_valor int NOT NULL,
     mvt_saldo int NOT NULL,
     mvt_cuenta int NOT NULL,
     PRIMARY KEY (mvt_id)
);

ALTER TABLE ONLY sc_banco.movimiento
    ADD CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (mvt_cuenta) REFERENCES sc_banco.cuenta(cnt_id) ON DELETE RESTRICT;

