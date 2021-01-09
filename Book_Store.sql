create database Book_Store;
use Book_Store;

create table Customer (
	cID int primary key auto_increment,
    name varchar(30),
    gender enum('M','F'),
    address varchar(50),
    emailID varchar(50) unique,
    password varchar(15),
    city varchar(20),
    zipcode varchar(6),
    state varchar(20),
    country varchar(20),
    contactNo varchar(10) unique,
    accountNo varchar(16) unique,
    balance_amt float 
);

create table Seller (
	sID int primary key auto_increment,
	name varchar(30),
    gender enum('M','F'),
    address varchar(50),
    emailID varchar(50) unique,
    password varchar(30),
    city varchar(20),
    zipcode varchar(6),
    state varchar(20),
    country varchar(20),
    contactNo varchar(10) unique
);

create table Book (
	bID int primary key auto_increment,
    sID int,
    foreign key(sID) references Seller(sID),
	name varchar(100) ,
    author varchar(30),
    genre varchar (20),
    quantity int,
    price float,
    discount float,
    releaseDate date
);

create table Orders (
	oID int primary key auto_increment,
    cID int, foreign key(cID) references Customer(cID),
    bID int, foreign key(bID) references Book(bID)
);

create table Cart (
	cartID int primary key auto_increment,
    cID int, foreign key(cID) references Customer(cID),
    bID int, foreign key(bID) references Book(bID)
);

insert into seller (name,gender,address,emailID,password,city,zipcode,state,country,contactNo)
VALUES ('Abhishek Singh Naruka','M','BH-1 lnmiit Rupa ki Nagal','19ucs261@lnmiit.ac.in','pass123','Jaipur','302006','Rajasthan','India',7976637234),
('Mohit Khandelwal','M','Jaipur Rajasthan','mohitKhandelwal@gmail.com','pass123','Jaipur','302006','Rajasthan','India',7972347125);

insert into customer (name,gender,address,emailID,password,city,zipcode,state,country,contactNo,accountNo,balance_amt)
VALUES ('Jaskaran','M','BH-1 lnmiit Rupa ki Nagal','19ucs262@lnmiit.ac.in','pass234','Jaipur','302006','Rajasthan','India',8264683476,'19897532405',109231),
('Sakshi Mittal','F','GH-1 lnmiit Rupa ki Nagal','19ucs265@lnmiit.ac.in','passxyz','Jaipur','302006','Rajasthan','India',8742967183,'19997507405',80765);

insert into book (sID,name,author,genre,quantity,price,discount,releaseDate)
VALUES (1,'Three man in a boat','Jerome K. Jerome','comedy',50,128,0,'1889-02-07'),
(2,'The Hitchhiker’s Guide to the Galaxy','Douglas Adams','comedy',20,560,0,'1979-12-09'),
(1,'The Innocents Abroad',' Mark Twain','comedy',10,1186.5,0,'1869-03-15'),
(1,'House of Leaves','Mark Z. Danielewski','horror',36,1400,0,'2000-03-07'),
(1,'The Road','Cormac McCarthy','horror',24,70,0,'2006-09-26'),
(2,'The haunting of hill House','Laura Miller','horror',13,770,0,'1959-09-16'),
(2,'Dracula','Bram Stoker','horror',48,490,0,'1897-05-26'),
(2,'If You Tell','Grag Olsen','thriller',12,840,0,'2009-07-12'),
(1,'I Am Watching You','Teresa Driscoll','thriller',108,204,0,'2017-10-01'),
(1,'Thin Air','Lisa Grey','thriller',43,545,0,'2009-06-23'),
(2,'Java The Complete Refrence','Herbert Schildt','computer science',200,726,0,'1997-03-30'),
(1,'Let Us C','Yashavant Kanetkar','computer science',250,253,0,'2016-07-17'),
(1,'C++ Programming Language','Bjarne Strousstrup','computer science',100,5227,0,'1985-09-19'),
(1,'Database System Concepts','Abraham Silberschatz','computer science',68,746,0,'2019-02-12'),
(2,'Digital System','Ronal J. Tocci','computer science',89,354,0,'2009-01-01'),
(2,'Intriduction tO Algorithms','Thomas H. Cormen','computer science',15,3911,0,'1989-04-25'),
(1,'Harry Potter:The complete collection','J.K. Rowling','fantasy',728,2325,0,'2007-07-21'),
(1,'The Lord of the Rings','Gergory Bassham','fantasy',59,1516,0,'2003-08-04'),
(1,'The Hobbit: The Battle of the Five Armies Visual Companion','Jude Fisher','fantasy',70,699,0,'2014-11-20');

insert into cart(cID,bID)
VALUES(1,11),
(1,12),
(1,18),
(1,5),
(1,16),
(2,1),
(2,3),
(2,7),
(2,17);

insert into  orders(cID,bID)
VALUES(1,13),
(1,5),
(2,7),
(2,13);