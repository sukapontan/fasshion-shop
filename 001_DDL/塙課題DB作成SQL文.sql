◆データベース作成
create database fashionshop_suka;

◆テーブル作成
ユーザーテーブル
create table user(id int not null primary key auto_increment,userType int not null,name varchar(20) ,pass varchar(20),branch_id int);
insert into user(userType,name,pass,branch_id) values(1,"従業員","eee",3);
insert into user(userType,name,pass,branch_id) values(2,"管理者","aaa",2);
insert into user(userType,name,pass,branch_id) values(3,"顧客","ccc",1);


ウォレットテーブル
create table wallet(user_id int not null primary key,balance int,update_date datetime);
insert into wallet(user_id,balance,update_date) values(1,10000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(2,10000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(3,10000,cast(now() as datetime));



在庫テーブル
create table stock(stock_id int not null primary key auto_increment,branch_id int not null,product_code int not null,product_name varchar(20),color varchar(10),size varchar(3),price int,quantity int);
insert into stock values(0,1,1,"スカＴシャツ","赤","S",2000,10);
insert into stock values(0,1,2,"スカＴシャツ","赤","M",2000,10);
insert into stock values(0,1,3,"スカＴシャツ","赤","L",2000,10);
insert into stock values(0,1,4,"スカＴシャツ","緑","S",2000,10);
insert into stock values(0,1,5,"スカＴシャツ","緑","M",2000,10);
insert into stock values(0,1,6,"スカＴシャツ","緑","L",2000,10);
insert into stock values(0,1,7,"スカＴシャツ","黒","S",2000,10);
insert into stock values(0,1,8,"スカＴシャツ","黒","M",2000,10);
insert into stock values(0,1,9,"スカＴシャツ","黒","L",2000,10);
insert into stock values(0,2,1,"スカＴシャツ","赤","S",2000,10);
insert into stock values(0,2,2,"スカＴシャツ","赤","M",2000,10);
insert into stock values(0,2,3,"スカＴシャツ","赤","L",2000,10);
insert into stock values(0,2,4,"スカＴシャツ","緑","S",2000,10);
insert into stock values(0,2,5,"スカＴシャツ","緑","M",2000,10);
insert into stock values(0,2,6,"スカＴシャツ","緑","L",2000,10);
insert into stock values(0,2,7,"スカＴシャツ","黒","S",2000,10);
insert into stock values(0,2,8,"スカＴシャツ","黒","M",2000,10);
insert into stock values(0,2,9,"スカＴシャツ","黒","L",2000,10);
insert into stock values(0,3,1,"スカＴシャツ","赤","S",2000,10);
insert into stock values(0,3,2,"スカＴシャツ","赤","M",2000,10);
insert into stock values(0,3,3,"スカＴシャツ","赤","L",2000,10);
insert into stock values(0,3,4,"スカＴシャツ","緑","S",2000,10);
insert into stock values(0,3,5,"スカＴシャツ","緑","M",2000,10);
insert into stock values(0,3,6,"スカＴシャツ","緑","L",2000,10);
insert into stock values(0,3,7,"スカＴシャツ","黒","S",2000,10);
insert into stock values(0,3,8,"スカＴシャツ","黒","M",2000,10);
insert into stock values(0,3,9,"スカＴシャツ","黒","L",2000,10);

発注テーブル
create table fashionshop_suka.stockorder (no int primary key auto_increment,product_name varchar(10),price int,color varchar(10),size varchar(3),branch_id int,order_quantity int,del_flg int, status int,index(no));

売上テーブル
create table earnings(no int primary key auto_increment,earnings int,ins_date date,branch_id int,del_flg int);