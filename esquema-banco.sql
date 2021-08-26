Nome do Banco: rede-social

create table usuario(
	id int,
	nome varchar(50),
	sexo varchar(10),
	email varchar(50),
	telefone int,
	senha varchar(50),
	nick varchar(50),
	primary key(id)
);

create table post(
	id int,
	texto varchar(50),
	id_user int,
	img bytea,
	primary key(id),
	foreign key (id_user) references usuario
);

create table seguidores(
	id int,
	id_seguidor int,
	id_seguindo int,
	primary key(id),
	foreign key(id_seguindo) references usuario,
	foreign key(id_seguidor) references usuario
);

create table curtidas(
	id int,
	id_post int,
	id_usuario int,
	primary key(id),
	foreign key(id_post) references post,
	foreign key(id_usuario) references usuario
);

create sequence id_usuario;
create sequence id_post;
create sequence id_curtidas;
create sequence id_seguidores;

