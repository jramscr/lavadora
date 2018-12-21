/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jramos
 * Created: Dec 20, 2018
 */

CREATE TABLE tbl_cliente (
    codigoCliente INT NOT NULL AUTO_INCREMENT,
    cedula VARCHAR(35),
    nombre VARCHAR(35),
    celular VARCHAR(30),
    email VARCHAR(30),
    direccion VARCHAR(70),
    PRIMARY KEY (codigoCliente)
);

CREATE TABLE tbl_tipoprenda (
    codigoTipoPrenda INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(35),
    PRIMARY KEY (codigoTipoPrenda)
);

CREATE TABLE tbl_tipotela (
    codigoTipoTela INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(35),
    PRIMARY KEY (codigoTipoTela)
);

CREATE TABLE tbl_prenda (
    codigoPrenda INT NOT NULL AUTO_INCREMENT,
    codigoCliente INT,
    codigoTipoPrenda INT,
    codigoTipoTela INT,
    especificaciones VARCHAR(200),
    PRIMARY KEY (codigoPrenda),
    FOREIGN KEY (codigoCliente) REFERENCES tbl_cliente(codigoCliente),
    FOREIGN KEY (codigoTipoPrenda) REFERENCES tbl_tipo_prenda(codigoTipoPrenda),
    FOREIGN KEY (codigoTipoTela) REFERENCES tbl_tipo_tela(codigoTipoTela)
);

CREATE TABLE tbl_estadoservicio (
    codigoEstadoServicio INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(35),
    PRIMARY KEY (codigoEstadoServicio)
);

CREATE TABLE tbl_servicio (
    codigoServicio INT NOT NULL AUTO_INCREMENT,
    codigoPrenda INT,
    codigoCliente INT,
    codigoEstadoServicio INT,
    fechaRecibido DATE,
    fechaDevolucion DATE,
    especificaciones VARCHAR(200),
    comentarios VARCHAR(200),
    PRIMARY KEY (codigoServicio),
    FOREIGN KEY (codigoPrenda) REFERENCES tbl_prenda(codigoPrenda),
    FOREIGN KEY (codigoCliente) REFERENCES tbl_cliente(codigoCliente),
    FOREIGN KEY (codigoEstadoServicio) REFERENCES tbl_estado_servicio(codigoEstadoServicio)
);

