create table categoria (
  codcat int auto_increment,
  descripcion varchar(45) not null unique,
  primary key(codcat)
);

create table producto (
  codpro int auto_increment,
  descripcion varchar(45) not null unique,
  precio double not null,
  cantidad int not null,
  fecha date not null,
  codcat int not null,
  primary key(codpro),
  foreign key(codcat) references categoria(codcat)
);

create table usuario (
  idusuario int not null auto_increment,
  username varchar(45) not null unique,
  password varchar(60) not null,
  nombre varchar(45) not null,
  paterno varchar(45) not null,
  materno varchar(45) not null,
  telefono varchar(45) not null unique,
  dni char(8) not null unique,
  estado boolean not null default true,
  primary key (idusuario)
);

create table roles (
  idrol int not null auto_increment,
  descripcion varchar(45) not null unique,
  primary key (idrol)
);

create table user_roles (
  idusuario int not null,
  idrol int not null,
  primary key (idusuario, idrol),
  foreign key(idrol) references roles(idrol),
  foreign key(idusuario) references usuario(idusuario)
);