◆データベース作成
create database fashionshop;

◆ユーザーテーブル
CREATE文
create table user(id int not null primary key auto_increment,userType int not null,name varchar(20) ,pass varchar(20),branch_id int,branch_name varchar(20));

INSERT文
従業員
insert into user(userType,name,pass,branch_id,branch_name) values(1,"emp","emp",1,"L・A支店");
insert into user(userType,name,pass,branch_id,branch_name) values(1,"emp2","emp2",2,"埼玉国スカ支店");
insert into user(userType,name,pass,branch_id,branch_name) values(1,"emp3","emp3",3,"赤坂支店");
管理者
insert into user(userType,name,pass,branch_id,branch_name) values(2,"admin","admin",2,"埼玉国スカ支店");
insert into user(userType,name,pass,branch_id,branch_name) values(2,"hanawa","hanawa",3,"赤坂支店");
顧客
insert into user(userType,name,pass,branch_id,branch_name) values(3,"client","client",null,null);
insert into user(userType,name,pass,branch_id,branch_name) values(3,"client2","client2",null,null);
insert into user(userType,name,pass,branch_id,branch_name) values(3,"client3","client3",null,null);

◆ウォレットテーブル
create table wallet(user_id int not null primary key,balance int,update_date datetime);
insert into wallet(user_id,balance,update_date) values(1,1000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(2,20000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(3,300000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(4,1000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(5,20000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(6,300000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(7,1000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(8,20000,cast(now() as datetime));

◆在庫テーブル
create table stock(stock_id int not null primary key auto_increment,branch_id int not null,branch_name varchar(10),product_code int not null,product_name varchar(20),color varchar(10),size varchar(3),price int,quantity int);
insert into stock values(0,1,"L・A支店",1,"スカＴシャツ","赤","S",2000,10);
insert into stock values(0,1,"L・A支店",2,"スカＴシャツ","赤","M",2000,10);
insert into stock values(0,1,"L・A支店",3,"スカＴシャツ","赤","L",2000,10);
insert into stock values(0,1,"L・A支店",4,"スカＴシャツ","緑","S",2000,10);
insert into stock values(0,1,"L・A支店",5,"スカＴシャツ","緑","M",2000,10);
insert into stock values(0,1,"L・A支店",6,"スカＴシャツ","緑","L",2000,10);
insert into stock values(0,1,"L・A支店",7,"スカＴシャツ","黒","S",2000,10);
insert into stock values(0,1,"L・A支店",8,"スカＴシャツ","黒","M",2000,10);
insert into stock values(0,1,"L・A支店",9,"スカＴシャツ","黒","L",2000,10);
insert into stock values(0,2,"埼玉国スカ支店",1,"スカＴシャツ","赤","S",2000,10);
insert into stock values(0,2,"埼玉国スカ支店",2,"スカＴシャツ","赤","M",2000,10);
insert into stock values(0,2,"埼玉国スカ支店",3,"スカＴシャツ","赤","L",2000,10);
insert into stock values(0,2,"埼玉国スカ支店",4,"スカＴシャツ","緑","S",2000,10);
insert into stock values(0,2,"埼玉国スカ支店",5,"スカＴシャツ","緑","M",2000,10);
insert into stock values(0,2,"埼玉国スカ支店",6,"スカＴシャツ","緑","L",2000,10);
insert into stock values(0,2,"埼玉国スカ支店",7,"スカＴシャツ","黒","S",2000,10);
insert into stock values(0,2,"埼玉国スカ支店",8,"スカＴシャツ","黒","M",2000,10);
insert into stock values(0,2,"埼玉国スカ支店",9,"スカＴシャツ","黒","L",2000,10);
insert into stock values(0,3,"赤坂支店",1,"スカＴシャツ","赤","S",2000,10);
insert into stock values(0,3,"赤坂支店",2,"スカＴシャツ","赤","M",2000,10);
insert into stock values(0,3,"赤坂支店",3,"スカＴシャツ","赤","L",2000,10);
insert into stock values(0,3,"赤坂支店",4,"スカＴシャツ","緑","S",2000,10);
insert into stock values(0,3,"赤坂支店",5,"スカＴシャツ","緑","M",2000,10);
insert into stock values(0,3,"赤坂支店",6,"スカＴシャツ","緑","L",2000,10);
insert into stock values(0,3,"赤坂支店",7,"スカＴシャツ","黒","S",2000,10);
insert into stock values(0,3,"赤坂支店",8,"スカＴシャツ","黒","M",2000,10);
insert into stock values(0,3,"赤坂支店",9,"スカＴシャツ","黒","L",2000,10);

◆発注テーブル
create table fashionshop.stockorder (no int primary key auto_increment,product_name varchar(10),price int,color varchar(10),size varchar(3),branch_id int,order_quantity int,del_flg int, status int,index(no));

◆売上テーブル
create table earnings(no int primary key auto_increment,earnings int,ins_date date,branch_id int,del_flg int,product_name varchar(10),quantity int,color varchar(10),size varchar(5));

