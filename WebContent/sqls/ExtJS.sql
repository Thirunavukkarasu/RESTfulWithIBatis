create table Groups (
id number(20) not null,
name VARCHAR2(45) not null,
CONSTRAINT groups_pk PRIMARY KEY (id) );

create table Users( 
  id number(20) not null, 
  name VARCHAR2(45) not null, 
  userName VARCHAR2(45) not null,
  password VARCHAR2(45) not null, 
  email varchar2(100) not null,
  picture varchar2(100),
  group_id number(10) not null
);

INSERT INTO Groups(id,name) VALUES (1234,'admin');

INSERT INTO Users(id,name,userName,password,email,group_id) VALUES (1,'Thirunavukkarasu','thiru','thiru','tamilan.arasu@gmail.com',1234);


create table Products (
product_id number(20) not null,
product_name VARCHAR2(45) not null,
CONSTRAINT products_pk PRIMARY KEY (product_id) );

INSERT INTO Products(product_id,product_name) VALUES (2,'Complan');