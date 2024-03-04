drop database if exists scolarite_jv_iris_24; 
create database scolarite_jv_iris_24; 
use scolarite_jv_iris_24; 

create table classe (
	idclasse int(3) not null auto_increment, 
	nom varchar (50), 
	salle varchar (50), 
	diplome varchar (50), 
	primary key (idclasse)
);

create table Professeur (
	idprofesseur int(3) not null auto_increment, 
	nom varchar (50), 
	prenom varchar (50), 
	email varchar (50),
	diplome varchar (50), 
	primary key (idprofesseur)
);

create table Etudiant (
	idetudiant int(3) not null auto_increment, 
	nom varchar (50), 
	prenom varchar (50), 
	email varchar (50),
	dateNais date, 
	idclasse int(3) not null,
	primary key (idetudiant), 
	foreign key (idclasse) references classe (idclasse)
);
create table Enseignement (
	idenseignement int(3) not null auto_increment, 
	matiere  varchar (50), 
	coeff int(2), 
	nbheures int(3), 
	idclasse int(3) not null,
	idprofesseur int(3) not null,
	primary key (idenseignement), 
	foreign key (idclasse) references classe (idclasse), 
	foreign key (idprofesseur) references Professeur (idprofesseur)
); 

create table user (
	iduser int(3) not null auto_increment, 
	nom varchar(50), 
	prenom varchar(50), 
	email varchar(50),
	mdp varchar(50), 
	role enum("admin", "user"), 
	primary key(iduser)
);

insert into user values 
(null, "Fabien", "Vincent", "a@gmail.com", "123","admin"); 
insert into user values 
(null, "JeanM", "Anthony", "b@gmail.com", "456","user"); 















