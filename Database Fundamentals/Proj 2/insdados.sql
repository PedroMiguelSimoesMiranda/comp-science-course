
insert into cursos values(seq_curso.nextval,'Engenharia Informatica');
insert into cursos values(seq_curso.nextval,'Engenharia Electrotecnica');
insert into cursos values(seq_curso.nextval,'Engenharia do Ambiente');
insert into cursos values(seq_curso.nextval,'Matematica');
insert into cursos values(seq_curso.nextval,'Arquitectura');


insert into alunos values(seq_aluno.nextval,'Joaquim Pires Lopes','Lisboa',to_date('1965.01.01','YYYY.MM.DD'),'M',2);
insert into alunos values(seq_aluno.nextval,'Ana Maria Fonseca','Setubal',to_date('1967.03.01','YYYY.MM.DD'),'F',1);
insert into alunos values(seq_aluno.nextval,'Paula Antunes','Lisboa',to_date('1974.07.13','YYYY.MM.DD'),'F',2);
insert into alunos values(seq_aluno.nextval,'Joana Ramalho Silva','Costa da Caparica',to_date('1974.09.23','YYYY.MM.DD'),'F',3);
insert into alunos values(seq_aluno.nextval,'Rui Manuel Silva','Cascais',to_date('1974.08.15','YYYY.MM.DD'),'M',1);
insert into alunos values(seq_aluno.nextval,'Joao Paulo Santos','Lisboa',to_date('1981.11.16','YYYY.MM.DD'),'M',1);
insert into alunos values(seq_aluno.nextval,'Cristina Fernandes Lopes','Lisboa',to_date('1966.01.07','YYYY.MM.DD'),'F',1);
insert into alunos values(seq_aluno.nextval,'Miguel Pinto Leite','Cascais',to_date('1974.01.07','YYYY.MM.DD'),'M',3);
insert into alunos values(seq_aluno.nextval,'Francisco Costa Rosa',NULL,to_date('1978.02.16','YYYY.MM.DD'),'M',4);
insert into alunos values(seq_aluno.nextval,'Elsa Fialho Pinto',NULL,to_date('1979.10.29','YYYY.MM.DD'),'F',1);
insert into alunos values(seq_aluno.nextval,'Maria Pinto Ribeiro',NULL,to_date('1983.10.20','YYYY.MM.DD'),'F',1);


insert into departamentos values( seq_departamento.nextval,
                 'Departamento de Informatica');
insert into departamentos values( seq_departamento.nextval, 
                 'Departamento de Electrotecnia');
insert into departamentos values( seq_departamento.nextval, 
                 'Departamento de Matematica');
insert into departamentos values( seq_departamento.nextval, 
                 'Departamento de Ambiente');


insert into cadeiras values(seq_cadeira.nextval,'Programacao 1',4,1);
insert into cadeiras values(seq_cadeira.nextval,'Introducao Inteligencia Artificial',4,1);
insert into cadeiras values(seq_cadeira.nextval,'Redes de Computadores',4,1);
insert into cadeiras values(seq_cadeira.nextval,'Bases de Dados 1',4,1);
insert into cadeiras values(seq_cadeira.nextval,'Bases de Dados 2',3,1);
insert into cadeiras values(seq_cadeira.nextval,'Analise de Sistemas 1',4,1);
insert into cadeiras values(seq_cadeira.nextval,'Sistemas Distribuidos',4,1);
insert into cadeiras values(seq_cadeira.nextval,'Sistemas Logicos',4,2);
insert into cadeiras values(seq_cadeira.nextval,'Microprocessadores',4,2);
insert into cadeiras values(seq_cadeira.nextval,'Electronica Geral',3,2);
insert into cadeiras values(seq_cadeira.nextval,'Analise Matematica 1',4,3);
insert into cadeiras values(seq_cadeira.nextval,'Calculo Numerico',3,3);
insert into cadeiras values(seq_cadeira.nextval,'Analise Matematica 2',5,3);
insert into cadeiras values(seq_cadeira.nextval,'Climatologia',4,4);
insert into cadeiras values(seq_cadeira.nextval,'Poluicao do Ar',2,4);

insert into categorias values(seq_categoria.nextval,'Assistente',2071);
insert into categorias values(seq_categoria.nextval,
                 'Professor Auxiliar',2885);
insert into categorias values(seq_categoria.nextval,'Professor Associado',3255);
insert into categorias values(seq_categoria.nextval,'Professor Catedratico',4217);


insert into docentes values(seq_docente.nextval,'Joana Ramalho Silva',1,1);
insert into docentes values(seq_docente.nextval,'Filipe Costa Rocha',2,1);
insert into docentes values(seq_docente.nextval,'Joao Mario Cunha',1,3);
insert into docentes values(seq_docente.nextval,'Carla Maria Vargas',3,2);
insert into docentes values(seq_docente.nextval,'Antonio Miguel Horta',3,3);
insert into docentes values(seq_docente.nextval,'Eduardo Jose Durao',2,1);
insert into docentes values(seq_docente.nextval,'Manuela Bravo Neves',1,2);
insert into docentes values(seq_docente.nextval,'Sofia Alves Prazeres',1,3);
insert into docentes values(seq_docente.nextval,'Joana Alves Pereira',4,2);



insert into curso_cadeira values(1,1,1);
insert into curso_cadeira values(1,2,8);
insert into curso_cadeira values(1,3,5);
insert into curso_cadeira values(1,4,7);
insert into curso_cadeira values(1,5,8);
insert into curso_cadeira values(1,6,6);
insert into curso_cadeira values(1,7,8);
insert into curso_cadeira values(1,8,2);
insert into curso_cadeira values(1,11,1);
insert into curso_cadeira values(1,12,5);
insert into curso_cadeira values(1,13,2);
insert into curso_cadeira values(2,1,3);
insert into curso_cadeira values(2,4,9);
insert into curso_cadeira values(2,8,2);
insert into curso_cadeira values(2,9,4);
insert into curso_cadeira values(2,10,3);
insert into curso_cadeira values(2,11,1);
insert into curso_cadeira values(3,1,3);
insert into curso_cadeira values(3,8,4);
insert into curso_cadeira values(3,11,1);
insert into curso_cadeira values(3,14,4);
insert into curso_cadeira values(3,15,5);
insert into curso_cadeira values(4,1,3);
insert into curso_cadeira values(4,8,4);
insert into curso_cadeira values(4,11,1);
insert into curso_cadeira values(4,12,3);
insert into curso_cadeira values(4,13,2);


insert into inscricoes values( 1,2, 1,to_date('1997.09.03','YYYY.MM.DD'),to_date('1998.02.11','YYYY.MM.DD'),10);
insert into inscricoes values( 1,2, 8,to_date('1997.09.05','YYYY.MM.DD'),null,null);
insert into inscricoes values( 1,2, 9,to_date('1998.10.06','YYYY.MM.DD'),null,null);
insert into inscricoes values( 2,1, 1,to_date('1995.10.23','YYYY.MM.DD'),to_date('1996.02.11','YYYY.MM.DD'),11);
insert into inscricoes values( 2,1,11,to_date('1995.09.23','YYYY.MM.DD'),to_date('1996.03.12','YYYY.MM.DD'),13);
insert into inscricoes values( 2,1, 4,to_date('1998.09.05','YYYY.MM.DD'),null,null);
insert into inscricoes values( 2,1, 6,to_date('1998.03.05','YYYY.MM.DD'),to_date('1998.07.05','YYYY.MM.DD'),15);
insert into inscricoes values( 3,2, 1,to_date('1990.10.05','YYYY.MM.DD'),to_date('1991.02.11','YYYY.MM.DD'),12);
insert into inscricoes values( 3,2, 9,to_date('1991.09.06','YYYY.MM.DD'),null,null);
insert into inscricoes values( 4,3,14,to_date('1990.10.05','YYYY.MM.DD'),to_date('1991.03.11','YYYY.MM.DD'),10);
insert into inscricoes values( 5,1,1 ,to_date('1998.09.07','YYYY.MM.DD'),null,null);
insert into inscricoes values( 6,1,1 ,to_date('1998.09.23','YYYY.MM.DD'),null,null);
insert into inscricoes values( 6,1,11,to_date('1998.09.23','YYYY.MM.DD'),null,null);
insert into inscricoes values( 7,1, 1,to_date('1996.09.08','YYYY.MM.DD'),to_date('1997.02.10','YYYY.MM.DD'),13);
insert into inscricoes values( 7,1,11,to_date('1996.09.06','YYYY.MM.DD'),to_date('1997.03.07','YYYY.MM.DD'),17);
insert into inscricoes values( 7,1, 4,to_date('1997.11.05','YYYY.MM.DD'),to_date('1998.07.12','YYYY.MM.DD'),15);
insert into inscricoes values( 7,1, 2,to_date('1997.11.05','YYYY.MM.DD'),to_date('1998.03.11','YYYY.MM.DD'),13);
insert into inscricoes values( 8,3,14,to_date('1991.10.11','YYYY.MM.DD'),to_date('1992.03.12','YYYY.MM.DD'),14);
insert into inscricoes values( 9,4, 1,to_date('1998.10.07','YYYY.MM.DD'),null,null);
insert into inscricoes values( 9,4,11,to_date('1998.10.07','YYYY.MM.DD'),to_date('1999.03.07','YYYY.MM.DD'),12);
insert into inscricoes values( 9,4,13,to_date('1998.10.07','YYYY.MM.DD'),null,null);
insert into inscricoes values(10,1,11,to_date('1992.09.11','YYYY.MM.DD'),to_date('1993.02.12','YYYY.MM.DD'),15);
insert into inscricoes values(10,1,1 ,to_date('1993.09.12','YYYY.MM.DD'),to_date('1994.02.11','YYYY.MM.DD'),12);


insert into historico_categorias values(1,1,to_date('1998.11.27','YYYY.MM.DD'));
insert into historico_categorias values(2,1,to_date('1997.12.13','YYYY.MM.DD'));
insert into historico_categorias values(3,1,to_date('1995.08.15','YYYY.MM.DD'));
insert into historico_categorias values(3,2,to_date('1997.01.08','YYYY.MM.DD'));
insert into historico_categorias values(3,3,to_date('1997.02.19','YYYY.MM.DD'));
insert into historico_categorias values(4,2,to_date('1985.02.03','YYYY.MM.DD'));
insert into historico_categorias values(4,3,to_date('1990.10.21','YYYY.MM.DD'));
insert into historico_categorias values(4,4,to_date('1995.09.17','YYYY.MM.DD'));
insert into historico_categorias values(5,3,to_date('1997.03.22','YYYY.MM.DD'));
insert into historico_categorias values(6,3,to_date('1995.05.02','YYYY.MM.DD'));
insert into historico_categorias values(7,1,to_date('1999.01.05','YYYY.MM.DD'));
insert into historico_categorias values(8,2,to_date('1996.11.07','YYYY.MM.DD'));
insert into historico_categorias values(9,2,to_date('2000.11.07','YYYY.MM.DD'));


insert into precedencias values(1,5,4);
insert into precedencias values(1,5,3);
insert into precedencias values(1,7,3);
insert into precedencias values(1,4,6);
insert into precedencias values(1,3,1);
insert into precedencias values(1,6,1);
insert into precedencias values(4,12,13);
insert into precedencias values(4,13,11);

commit;