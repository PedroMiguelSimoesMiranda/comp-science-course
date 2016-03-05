drop table Pessoas cascade constraints;
create table Pessoas(
bi number(8) not null,
sexo char(1) not null CHECK ( sexo IN ( 'F' , 'M' ) ), 
nome varchar2(40) not null,
primary key (bi)
);

/*------------------------------------------------------------*/
drop table Clientes cascade constraints;
create table Clientes(
faltas_pagamento number(3),
num_cliente number(5) not null,
bi number(8) not null,
primary key(bi),
foreign key (bi) references Pessoas);

/*------------------------------------------------------------*/
drop table Empregados cascade constraints;
create table Empregados(
faltas number(3), 
num_empregado number(2)not null,
bi number(8) not null,
primary key(num_empregado),
foreign key (bi) references Pessoas);

/*------------------------------------------------------------*/
drop table Fornecedores cascade constraints;
create table Fornecedores(
num_fornecedor number(2) not null,
firma varchar2(40)not null,
bi number(8) not null,
primary key(bi),
foreign key (bi) references Pessoas);

/*------------------------------------------------------------*/
drop table Alugueres cascade constraints;
create table Alugueres(
data_aluguer date not null,
cod_aluguer number(5),
data_entrega date,
data_limite date not null,
preco_aluguer number(3) not null,
bi number(8) not null,
multa char(1) CHECK (multa in('Y' ,'N')),
foreign key (bi) references Clientes,
primary key(cod_aluguer)
);

/*------------------------------------------------------------*/

drop table Cliente_Alugueres cascade constraints;
create table Cliente_Alugueres (
bi number(8) not null,
cod_aluguer number(5),
primary key(bi,cod_aluguer),
foreign key (bi) references Clientes,
foreign key (cod_aluguer) references Alugueres);

/*------------------------------------------------------------*/
drop table Postos cascade constraints;

create table Postos(
/*horas_extra number(3),*/
posto varchar2(30) not null CHECK  (posto IN ('Gerente','Balcao', 'Limpeza')),
turno varchar2(5) not null CHECK (turno IN ('Noite', 'Dia')),
salario number(3) not null,
primary key(posto));

/*------------------------------------------------------------*/
drop table Produtos cascade constraints;
create table Produtos(
cod_produto number(5) not null,
nome_p varchar2(50) not null,
ano number(4) not null,
idade_minima number(2)not null,
produtora varchar(50) not null,
quantidade number(2) not null,
preco_aluguer number(1) not null,
alugado char(1) CHECK (alugado in ('Y' ,'N')),
primary key(cod_produto));

/*------------------------------------------------------------*/



drop table Aluguer_Produtos cascade constraints;
create table Aluguer_Produtos (
cod_produto number(5),
cod_aluguer number(5),
primary key(cod_produto,cod_aluguer),
foreign key (cod_produto) references Produtos,
foreign key (cod_aluguer) references Alugueres);


/*------------------------------------------------------------*/


drop table Filmes cascade constraints;
create table Filmes(
cod_produto number(5) not null,
realizador varchar (50) not null,
primary key (cod_produto),
foreign Key (cod_produto) references Produtos);
/*------------------------------------------------------------*/

drop table Jogos cascade constraints;
create table Jogos(
cod_produto number(5) not null,
requisitos varchar2(100) not null,
primary key (cod_produto),
foreign Key (cod_produto) references Produtos);
/*------------------------------------------------------------*/

drop table Fornecedores_Encomendas;
create table Fornecedores_Encomendas(
bi  number(8) not null,
cod_encomenda number(6)not null,
primary key(bi,cod_encomenda),
foreign key(bi) references Fornecedores,
foreign key(cod_encomenda) references Encomendas);

/*------------------------------------------------------------*/
drop table Encomendas cascade constraints;
create table Encomendas(
cod_encomenda number(6)not null,
data_entrega date not null,
quantidade number(2) not null,
data_encomenda date,
cod_produto number(5) not null,
primary key(cod_encomenda),
foreign key(cod_produto) references Produtos
);

/*-------------------------------------------------------------*/
drop table Contactos cascade constraints;
create table Contactos(
bi number(8) not null,
contacto number(9) not null,
primary key(bi,contacto), 
foreign key(bi) references Pessoas);

/*-------------------------------------------------------------*/
drop table Formatos cascade constraints;
create table Formatos(
cod_produto number(5) not null,
formato varchar2(10) not null CHECK ( formato IN('HDDVD','VHS','DVD','BLU-RAY')),
primary key (formato,cod_produto),
foreign key (cod_produto) references Produtos);

/*-------------------------------------------------------------*/
drop table Generos cascade constraints;
create table Generos(
cod_produto number(5) not null,
genero varchar2(50) not null,
primary key (genero,cod_produto),
foreign key (cod_produto) references Produtos);

/*-------------------------------------------------------------*/
drop table Legendagens cascade constraints;
create table Legendagens(
cod_produto number(5) not null,
legendagem varchar2(30) not null,
primary key(legendagem,cod_produto),
foreign key (cod_produto) references Produtos);



/*-------------------------------------------------------------*/
drop table Horarios;
create table Horarios(
cod_horario number(9)not null,
num_Empregado number(2) not null,
posto varchar2(30) not null ,
hora_entrada date,
horas_trabalho number(2),
/*horas_extra number(2),*/
primary key (cod_horario),
foreign key(num_Empregado) references Empregados,
foreign key(posto) references Postos);



drop sequence seq_pessoa;
create sequence seq_pessoa increment by 1 start with 1;

drop sequence seq_cliente;
create sequence seq_cliente increment by 1 start with 1;

drop sequence seq_empregado;
create sequence seq_empregado increment by 1 start with 1;

drop sequence seq_fornecedor;
create sequence seq_fornecedor increment by 1 start with 1;

drop sequence seq_aluguer;
create sequence seq_aluguer increment by 1 start with 1;

drop sequence seq_produto;
create sequence seq_produto increment by 1 start with 1;

drop sequence seq_encomenda;
create sequence seq_encomenda increment by 1 start with 1;

drop sequence seq_horario;
create sequence seq_horario increment by 1 start with 1;


drop view lista_clientes;
create view lista_clientes as
(select P.nome , C.num_cliente, P.bi ,T.contacto , P.sexo ,C.faltas_pagamento
from Pessoas P inner join Clientes C  on (P.bi=C.bi)inner join Contactos T on(P.bi=T.bi)
);

drop view lista_alugueres;
create view lista_alugueres as
(select A.cod_aluguer, P.nome, C.num_cliente, C.faltas_pagamento, A.data_aluguer, A.data_entrega, A.data_limite
from Pessoas P inner join Clientes C on(P.bi = C.bi ) inner join Cliente_Alugueres CA  on (C.bi = CA.bi )inner join Alugueres A on (CA.cod_aluguer=A.cod_aluguer)
);

drop view lista_fornecedores;
create view lista_fornecedores as 
(select F.firma , F.num_fornecedor , P.nome , P.bi ,P.sexo , C.contacto 
from Pessoas P inner join Fornecedores F on(P.bi=F.bi) inner join Contactos C on(P.bi=C.bi))
order by F.firma
;

drop view lista_empregados;
create view lista_empregados as 
(select P.nome , E.num_empregado , P.bi, C.contacto, P.sexo , E.faltas /*,PO.posto, PO.salario, PO.turno, H.hora_entrada, H.hora_saida*/
from Pessoas P inner join Empregados E on(P.bi=E.bi) 
inner join Contactos C on (P.bi=C.bi) /*
inner join Horarios H on (e.num_empregado=H.num_empregado)
inner join Postos PO on (H.posto = PO.posto)
*/
);
/*
drop view lista_encomendas;
create view lista_encomendas as 
(select E.cod_encomenda,PD.nome_p,P.nome, E.data_encomenda, E.data_entrega,EP.quantidade
from Fornecedores F inner join Pessoas P on(F.bi=P.bi) inner join Fornecedores_Encomendas FE on(F.bi=FE.bi) 
inner join Encomendas E on(FE.cod_encomenda=E.cod_encomenda) inner join Encomenda_Produtos EP on (E.cod_encomenda=EP.cod_encomenda) 
inner join Produtos PD on(EP.cod_produto=PD.cod_produto)
);*/



drop view lista_jogos_formatos;
create view lista_jogos_formatos as 
(select P.cod_produto,P.nome_p,P.preco_aluguer , P.quantidade, P.produtora, P.idade_minima,  P.ano, J.requisitos,FM.formato
from Jogos J inner join Produtos P on ( J.cod_produto = P.cod_Produto ) inner join Formatos FM on ( FM.cod_produto = P.cod_Produto )); 

drop view lista_jogos_generos;
create view lista_jogos_generos as 
(select P.cod_produto,P.nome_p,P.preco_aluguer , P.quantidade, P.produtora, P.idade_minima,  P.ano, J.requisitos,G.genero
from Jogos J inner join Produtos P on ( J.cod_produto = P.cod_Produto )inner join Generos G on ( G.cod_produto = P.cod_Produto ));

drop view lista_jogos_legendagens;
create view lista_jogos_legendagens as 
(select P.cod_produto,P.nome_p,P.preco_aluguer , P.quantidade, P.produtora, P.idade_minima,  P.ano, J.requisitos, L.legendagem
from Jogos J inner join Produtos P on ( J.cod_produto = P.cod_Produto ) inner join Legendagens L on ( L.cod_produto = P.cod_Produto ));


drop view lista_jogos;
create view lista_jogos as 
(select P.cod_produto,P.nome_p,P.preco_aluguer , P.quantidade, P.produtora, P.idade_minima,  P.ano, J.requisitos
from Jogos J inner join Produtos P on ( J.cod_produto = P.cod_Produto )
);


drop view lista_filmes_formatos;
create view lista_filmes_formatos as
(select P.nome_p,P.preco_aluguer, P.quantidade, P.produtora , P.idade_minima,  P.ano, F.realizador, FM.formato 
from Filmes F inner join Produtos P on ( F.cod_produto = P.cod_Produto ) inner join Formatos FM on ( FM.cod_produto = P.cod_Produto )
);

drop view lista_filmes_generos;
create view lista_filmes_generos as
(select P.nome_p,P.preco_aluguer, P.quantidade, P.produtora , P.idade_minima,  P.ano, F.realizador, G.genero
from Filmes F inner join Produtos P on ( F.cod_produto = P.cod_Produto )  inner join Generos G on ( G.cod_produto = P.cod_Produto )
);

drop view lista_filmes_legendagens;
create view lista_filmes_legendagens as
(select P.nome_p,P.preco_aluguer, P.quantidade, P.produtora , P.idade_minima,  P.ano, F.realizador, L.legendagem
from Filmes F inner join Produtos P on ( F.cod_produto = P.cod_Produto ) inner join Legendagens L on ( L.cod_produto = P.cod_Produto )
);


drop view lista_filmes;
create view lista_filmes as
(select P.nome_p,P.preco_aluguer, P.quantidade, P.produtora , P.idade_minima,  P.ano, F.realizador
from Filmes F inner join Produtos P on ( F.cod_produto = P.cod_Produto )
);


drop view lista_filmes_inserir;
create view lista_filmes_inserir as
(select P.cod_produto, P.nome_p,P.preco_aluguer, P.quantidade, P.produtora , P.idade_minima,  P.ano, F.realizador, FM.formato, G.genero, L.legendagem, P.alugado
from Filmes F inner join Produtos P on ( F.cod_produto = P.cod_Produto )
inner join Generos G on (P.cod_produto=G.cod_produto)
inner join Formatos FM on (G.cod_produto=FM.cod_produto)
inner join Legendagens L on (FM.cod_produto=L.cod_produto)
);

drop view lista_jogos_inserir;
create view lista_jogos_inserir as 
(select P.cod_produto,P.nome_p,P.preco_aluguer , P.quantidade, P.produtora, P.idade_minima,  P.ano, J.requisitos, FM.formato, G.genero, L.legendagem
from Jogos J inner join Produtos P on ( J.cod_produto = P.cod_Produto )
inner join Generos G on (P.cod_produto=G.cod_produto)
inner join Formatos FM on (G.cod_produto=FM.cod_produto)
inner join Legendagens L on (FM.cod_produto=L.cod_produto)
);
/*
drop view lista_horarios;
create view lista_horarios as 
(select H.num_empregado,PO.posto,H.hora_entrada,H.horas_trabalho, PO.salario, PO.turno
from Horarios H inner join Postos PO on (H.posto=PO.posto)
);
*/


create or replace trigger isaClient
instead of insert on lista_clientes
for each row
/*when Pessoas.bi not like :new.bi*/
begin
	insert into Pessoas (bi,sexo,nome) values(:new.bi,:new.sexo,:new.nome);
	insert into Contactos (bi,contacto) values(:new.bi,:new.contacto);
	insert into Clientes (faltas_pagamento,num_cliente,bi) values(:new.faltas_pagamento,:new.num_cliente,:new.bi);

end;
/


create or replace trigger isaEmployee
instead of insert on lista_empregados
for each row
/*when Pessoas.bi not like :new.bi*/
begin
	insert into Pessoas(bi, sexo,nome) values(:new.bi,:new.sexo , :new.nome);
	insert into Contactos (bi,contacto) values(:new.bi,:new.contacto);
	insert into Empregados(faltas,num_empregado,bi) values(:new.faltas,:new.num_empregado,:new.bi);
end;
/




create or replace trigger isaFornecedor
instead of insert on lista_fornecedores
for each row
/*when Pessoas.bi not like :new.bi*/
begin
	insert into Pessoas(bi, sexo,nome) values(:new.bi,:new.sexo,:new.nome);
	insert into Contactos (bi,contacto) values(:new.bi,:new.contacto);
	insert into Fornecedores (num_fornecedor, firma, bi)values(:new.num_fornecedor ,:new.firma, :new.bi );
end;
/

create or replace trigger insertFilm
instead of insert on lista_filmes_inserir
for each row
begin
	insert into Produtos(cod_produto,nome_p,preco_aluguer,quantidade,produtora,idade_minima,ano,alugado)
		 values(:new.cod_produto,:new.nome_p,:new.preco_aluguer,:new.quantidade,:new.produtora,:new.idade_minima,:new.ano,:new.alugado);
	insert into Filmes (cod_produto,realizador) values(:new.cod_produto,:new.realizador);
	insert into Formatos (cod_produto,formato) values(:new.cod_produto,:new.formato);
	insert into Generos (cod_produto,genero) values(:new.cod_produto ,:new.genero);
	insert into Legendagens (cod_produto,legendagem) values (:new.cod_produto,:new.legendagem);
end;
/

create or replace trigger insertGame
instead of insert on lista_jogos_inserir
for each row
begin
	insert into Produtos(cod_produto,nome_p,preco_aluguer,quantidade,produtora,idade_minima,ano)
		 values(:new.cod_produto,:new.nome_p,:new.preco_aluguer,:new.quantidade,:new.produtora,:new.idade_minima,:new.ano);
	insert into Jogos (cod_produto,requisitos) values(:new.cod_produto,:new.requisitos);
	insert into Formatos (cod_produto,formato) values(:new.cod_produto,:new.formato);
	insert into Generos (cod_produto,genero) values(:new.cod_produto ,:new.genero);
	insert into Legendagens (cod_produto,legendagem) values (:new.cod_produto,:new.legendagem);
end;
/



insert into Pessoas values(21491724,'F','Maria Rita');
insert into Pessoas values(26537837,'F','Ana Carvalho');
insert into Pessoas values(12431925,'M','Potter Cortez');
insert into Pessoas values(23099320,'M','Joao Lisboa');
insert into Pessoas values(21987519,'M','Carlos Porto');
insert into Pessoas values(21491725,'F','Matumbina Jaco');
insert into Pessoas values(84937933,'M','Tibursio Jaco');
insert into Pessoas values(51238575,'M','Carlos Freitas');
insert into Pessoas values(15927093,'M','Rodrigo Amaral');
insert into Pessoas values(98325637,'M','Carlos Segano');
insert into Pessoas values(84634422,'M','Sergio Santos');
insert into Pessoas values(12345788,'M','Tiago rodrigues');
insert into Pessoas values(93626266,'F','Carla Matias');


insert into Clientes values(0,seq_cliente.nextval,21491724); 
insert into Clientes values(0,seq_cliente.nextval,26537837); 
insert into Clientes values(1,seq_cliente.nextval,21987519); 
insert into Clientes values(2,seq_cliente.nextval,21491725); 
insert into Clientes values(0,seq_cliente.nextval,84937933); 
insert into Clientes values(0,seq_cliente.nextval,51238575); 
insert into Clientes values(1,seq_cliente.nextval,93626266); 


insert into Empregados values(0,seq_empregado.nextval,23099320); 
insert into Empregados values(2,seq_empregado.nextval,12431925);
insert into Empregados values(0,seq_empregado.nextval,98325637); 


insert into Fornecedores values(seq_fornecedor.nextval,'Arranja tudo',12345788); 
insert into Fornecedores values(seq_fornecedor.nextval,'Filmofsky',84634422); 


insert into Alugueres values (to_date('2009.04.10','YYYY.MM.DD'),seq_aluguer.nextval,NULL,to_date('2009.04.13','YYYY.MM.DD'),4,21491724,'N');
insert into Alugueres values (to_date('2009.04.11','YYYY.MM.DD'),seq_aluguer.nextval,NULL,to_date('2009.04.14','YYYY.MM.DD'),2,21987519,'N');
insert into Alugueres values (to_date('2009.04.17','YYYY.MM.DD'),seq_aluguer.nextval,NULL,to_date('2009.04.20','YYYY.MM.DD'),4,21491725,'N');
insert into Alugueres values (to_date('2009.04.20','YYYY.MM.DD'),seq_aluguer.nextval,NULL,to_date('2009.04.23','YYYY.MM.DD'),2,26537837,'N');
insert into Alugueres values (to_date('2009.04.25','YYYY.MM.DD'),seq_aluguer.nextval,NULL,to_date('2009.04.26','YYYY.MM.DD'),2,84937933,'Y');
insert into Alugueres values (to_date('2009.04.30','YYYY.MM.DD'),seq_aluguer.nextval,NULL,to_date('2009.05.02','YYYY.MM.DD'),4,93626266,'N');
insert into Alugueres values (to_date('2009.05.02','YYYY.MM.DD'),seq_aluguer.nextval,NULL,to_date('2009.05.05','YYYY.MM.DD'),2,51238575,'Y');
insert into Alugueres values (to_date('2009.05.04','YYYY.MM.DD'),seq_aluguer.nextval,NULL,to_date('2009.05.07','YYYY.MM.DD'),2,93626266,'N');
insert into Alugueres values (to_date('2009.05.10','YYYY.MM.DD'),seq_aluguer.nextval,NULL,to_date('2009.05.13','YYYY.MM.DD'),4,26537837,'N');
insert into Alugueres values (to_date('2009.05.12','YYYY.MM.DD'),seq_aluguer.nextval,NULL,to_date('2009.05.15','YYYY.MM.DD'),2,51238575,'N');



insert into Cliente_Alugueres values (21491724,1);
insert into Cliente_Alugueres values (21987519,2);
insert into Cliente_Alugueres values (21491725,3);
insert into Cliente_Alugueres values (26537837,4);
insert into Cliente_Alugueres values (84937933,5);
insert into Cliente_Alugueres values (93626266,6);
insert into Cliente_Alugueres values (51238575,7);
insert into Cliente_Alugueres values (93626266,8);
insert into Cliente_Alugueres values (51238575,9);
insert into Cliente_Alugueres values (93626266,10);


insert into Postos values('Gerente','Dia',600);
insert into Postos values('Limpeza','Noite',450);
insert into Postos values('Balcao','Dia',450);

insert into Produtos values(seq_produto.nextval,'Jurassic Park',1993,13,'Universal Pictures',2,3,'N'); 
insert into Produtos values(seq_produto.nextval,'Senhor dos aneis',2001,13,'New Line Cinema',2,3,'N'); 
insert into Produtos values(seq_produto.nextval,'Star wars',1977,10,'Lucasfilms',2,2,'N');
insert into Produtos values(seq_produto.nextval,'Titanic',1997,10,'Fox',2,2,'N');
insert into Produtos values(seq_produto.nextval,'300',2006,15,'Warner Bros. Pictures',4,3,'N');
insert into Produtos values(seq_produto.nextval,'Shawshank redemption',1994,13,'Castle Rock Entertainment',2,3,'N');
insert into Produtos values(seq_produto.nextval,'Back to the future 1',1985,10,'Universal Pictures',2,2,'N');
insert into Produtos values(seq_produto.nextval,'Tetris',1984,10,'Russian Guy',3,3,'N');
insert into Produtos values(seq_produto.nextval,'Pacman',1980,10,'Namco',3,2,'N');
insert into Produtos values(seq_produto.nextval,'Space invaders',1978,10,'Taito Corporation',3,3,'N');
insert into Produtos values(seq_produto.nextval,'GTA',1997,13,'Rockstar',3,2,'N');
insert into Produtos values(seq_produto.nextval,'Halo',2001,13,'Bungie',3,2,'N');
insert into Produtos values(seq_produto.nextval,'Super Mario',1981,10,'Nintendo',3,2,'N');
insert into Produtos values(seq_produto.nextval,'Sonic',1991,10,'Sega',2,3,'N');


insert into Aluguer_Produtos values(1,1);
insert into Aluguer_Produtos values(2,2);
insert into Aluguer_Produtos values(1,3);
insert into Aluguer_Produtos values(3,4);
insert into Aluguer_Produtos values(5,6);
insert into Aluguer_Produtos values(6,7);
insert into Aluguer_Produtos values(7,8);
insert into Aluguer_Produtos values(1,9);
insert into Aluguer_Produtos values(1,10);


insert into Filmes values(1,'Steven Spielberg');
insert into Filmes values(2,'Peter Jackson');
insert into Filmes values(3,'jorge lucas');
insert into Filmes values(4,'James Cameron');
insert into Filmes values(5,'Zack Snyder');
insert into Filmes values(6,'Frank Darabont');
insert into Filmes values(7,'steven spielberg');

insert into Jogos values(8,'PC');
insert into Jogos values(9,'PC');
insert into Jogos values(10,'PC');
insert into Jogos values(11,'PC');
insert into Jogos values(12,'NES');
insert into Jogos values(13,'NES');

insert into Jogos values(14,'DS');

insert into Fornecedores_Encomendas values(12345788,1);
insert into Fornecedores_Encomendas values(12345788,2);
insert into Fornecedores_Encomendas values(12345788,3);
insert into Fornecedores_Encomendas values(84634422,4);
insert into Fornecedores_Encomendas values(84634422,5);
insert into Fornecedores_Encomendas values(84634422,6);
insert into Fornecedores_Encomendas values(84634422,7);


insert into Encomendas values(seq_encomenda.nextval,to_date('2009.04.10','YYYY.MM.DD'),2,to_date('2009.04.05','YYYY.MM.DD'),1);
insert into Encomendas values(seq_encomenda.nextval,to_date('2009.04.14','YYYY.MM.DD'),2,to_date('2009.04.10','YYYY.MM.DD'),2);
insert into Encomendas values(seq_encomenda.nextval,to_date('2009.04.17','YYYY.MM.DD'),2,to_date('2009.04.13','YYYY.MM.DD'),3);
insert into Encomendas values(seq_encomenda.nextval,to_date('2009.04.20','YYYY.MM.DD'),2,to_date('2009.04.16','YYYY.MM.DD'),4);
insert into Encomendas values(seq_encomenda.nextval,to_date('2009.04.25','YYYY.MM.DD'),2,to_date('2009.04.20','YYYY.MM.DD'),5);
insert into Encomendas values(seq_encomenda.nextval,to_date('2009.04.30','YYYY.MM.DD'),2,to_date('2009.04.25','YYYY.MM.DD'),6);
insert into Encomendas values(seq_encomenda.nextval,to_date('2009.05.04','YYYY.MM.DD'),2,to_date('2009.04.30','YYYY.MM.DD'),7);
insert into Encomendas values(seq_encomenda.nextval,to_date('2009.05.09','YYYY.MM.DD'),2,to_date('2009.05.03','YYYY.MM.DD'),8);


insert into Contactos values(21491724,210556677);
insert into Contactos values(21491724,939304455);
insert into Contactos values(26537837,215436667);
insert into Contactos values(21987519,216798476);
insert into Contactos values(21491725,969372098);
insert into Contactos values(84937933,949678945);
insert into Contactos values(51238575,218693444);
insert into Contactos values(15927093,217676842);
insert into Contactos values(98325637,918754937);
insert into Contactos values(84634422,216476457);
insert into Contactos values(12345788,210997333);
insert into Contactos values(93626266,218347638);

insert into Formatos values(1,'VHS');
insert into Formatos values(2,'DVD');
insert into Formatos values(1,'DVD');
insert into Formatos values(3,'VHS');
insert into Formatos values(4,'HDDVD');
insert into Formatos values(6,'VHS');
insert into Formatos values(7,'DVD');
insert into Formatos values(9,'DVD');
insert into Formatos values(5,'HDDVD');
insert into Formatos values(8,'BLU-RAY');
insert into Formatos values(13,'BLU-RAY');
insert into Formatos values(10,'HDDVD');
insert into Formatos values(11,'BLU-RAY');
insert into Formatos values(12,'DVD');
insert into Formatos values(14,'DVD');

insert into Generos values(1,'Romance');
insert into Generos values(2,'Romance');
insert into Generos values(2,'Accao');
insert into Generos values(4,'Romance');
insert into Generos values(11,'Aventura');
insert into Generos values(3,'Accao');
insert into Generos values(5,'Aventura');
insert into Generos values(6,'Romance');
insert into Generos values(8,'Accao');
insert into Generos values(7,'Romance');
insert into Generos values(9,'Aventura');
insert into Generos values(10,'Romance');
insert into Generos values(12,'Accao');
insert into Generos values(13,'Romance');
insert into Generos values(14,'Aventura');


insert into Legendagens values(1,'Ingles');
insert into Legendagens values(1,'Alemao');
insert into Legendagens values(1,'Frances');
insert into Legendagens values(4,'Ingles');
insert into Legendagens values(2,'Alemao');
insert into Legendagens values(3,'Ingles');
insert into Legendagens values(5,'Ingles');
insert into Legendagens values(5,'Alemao');
insert into Legendagens values(6,'Ingles');
insert into Legendagens values(8,'Ingles');
insert into Legendagens values(7,'Alemao');
insert into Legendagens values(9,'Ingles');
insert into Legendagens values(12,'Alemao');
insert into Legendagens values(13,'Frances');
insert into Legendagens values(14,'Alemao');
insert into Legendagens values(12,'Portugues');
insert into Legendagens values(11,'Ingles');
insert into Legendagens values(10,'Alemao');

insert into Horarios values(seq_horario.nextval,1,'Gerente',to_date('12-31-2007 09:15','MM-DD-YYYY HH24:MI'),8);
insert into Horarios values(seq_horario.nextval,2,'Limpeza',to_date('12-31-2007 09:00','MM-DD-YYYY HH24:MI'),2);
insert into Horarios values(seq_horario.nextval,3,'Balcao',to_date('12-31-2007 10:10','MM-DD-YYYY HH24:MI'),8);
