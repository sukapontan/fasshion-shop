◆データベース作成
create database fashionshop_suka;

◆テーブル作成
ユーザーテーブル
create table user(id int not null primary key auto_increment,userType int not null,name varchar(20) ,pass varchar(20),branch_id int);
insert into user(userType,name,pass,branch_id) values(1,"従業員","eee",3);
insert into user(userType,name,pass,branch_id) values(2,"管理者","aaa",2);
insert into user(userType,name,pass,branch_id) values(3,"顧客","ccc",1);
insert into user(userType,name,pass,branch_id) values(1,"emp","emp",3);
insert into user(userType,name,pass,branch_id) values(2,"admin","admin",2);
insert into user(userType,name,pass,branch_id) values(3,"user","user",1);


ウォレットテーブル
create table wallet(user_id int not null primary key,balance int,update_date datetime);
insert into wallet(user_id,balance,update_date) values(1,10000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(2,10000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(3,10000,cast(now() as datetime));



在庫テーブル
create table stock(stock_id int not null primary key auto_increment,branch_id int not null,branch_name varchar(10),product_code int not null,product_name varchar(20),color varchar(10),size varchar(3),price int,quantity int);
insert into stock values(0,1,"L・A支店",1,"スカＴシャツ","赤","S",2000,10);
insert into stock values(0,1,"L・A支店",2,"スカＴシャツ","赤","M",2000,10);
insert into stock values(0,1,"L・A支店",3,"スカＴシャツ","赤","L",2000,10);
insert into stock values(0,1,"L・A支店",4,"スカＴシャツ","緑","S",2000,10);
insert into stock values(0,1,"L・A支店",5,"スカＴシャツ","緑","M",2000,10);
insert into stock values(0,1,"L・A支店",,6,"スカＴシャツ","緑","L",2000,10);
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

stockテーブルカラム追加SQL(2020/11/04)　※create分は修正済み
alter table stock add branch_name varchar(10) after branch_id;
update stock set branch_name = 'L・A支店' where branch_id = 1;
update stock set branch_name = '埼玉国スカ支店' where branch_id = 2;
update stock set branch_name = '赤坂支店' where branch_id = 3;


発注テーブル
create table fashionshop_suka.stockorder (no int primary key auto_increment,product_name varchar(10),price int,color varchar(10),size varchar(3),branch_id int,order_quantity int,del_flg int, status int,index(no));

売上テーブル
create table earnings(no int primary key auto_increment,earnings int,ins_date date,branch_id int,del_flg int);

earningsテーブルカラム追加SQL
alter table earnings add product_name varchar(10);
alter table earnings add quantity int;
alter table earnings add color varchar(10);
alter table earnings add product_name size(5);


