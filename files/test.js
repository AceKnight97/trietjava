create database canteen
use canteen
drop table FoodOrder
drop table Food
create table Food(
ID int primary key,
Title nvarchar(50),
Name nvarchar(50),
Rating int default 0,
Price float default 0,
QuantityType int default 0,
Image nvarchar(MAX),
CreatedAt DateTime
)



create table FoodOrder(
ID int not null,
Quantity float,
Food_ID int not null,
CreatedAt DateTime,
Email varchar(50)
)
alter table FoodOrder add primary key (ID, Food_ID)
alter table FoodOrder add foreign key (Food_ID) references Food(ID)



drop table [User]
create table [User](
ID int primary key,
Email varchar(MAX),
Phone varchar(MAX),
Address nvarchar(MAX),
Gender int,
DOB Date,
Username nvarchar(MAX),
Role int,
Password nvarchar(MAX)
)