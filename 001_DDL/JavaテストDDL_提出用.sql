���f�[�^�x�[�X�쐬
create database fashionshop;

�����[�U�[�e�[�u��
CREATE��
create table user(id int not null primary key auto_increment,userType int not null,name varchar(20) ,pass varchar(20),branch_id int,branch_name varchar(20));

INSERT��
�]�ƈ�
insert into user(userType,name,pass,branch_id,branch_name) values(1,"emp","emp",1,"L�EA�x�X");
insert into user(userType,name,pass,branch_id,branch_name) values(1,"emp2","emp2",2,"��ʍ��X�J�x�X");
insert into user(userType,name,pass,branch_id,branch_name) values(1,"emp3","emp3",3,"�ԍ�x�X");
�Ǘ���
insert into user(userType,name,pass,branch_id,branch_name) values(2,"admin","admin",2,"��ʍ��X�J�x�X");
insert into user(userType,name,pass,branch_id,branch_name) values(2,"hanawa","hanawa",3,"�ԍ�x�X");
�ڋq
insert into user(userType,name,pass,branch_id,branch_name) values(3,"client","client",null,null);
insert into user(userType,name,pass,branch_id,branch_name) values(3,"client2","client2",null,null);
insert into user(userType,name,pass,branch_id,branch_name) values(3,"client3","client3",null,null);

���E�H���b�g�e�[�u��
create table wallet(user_id int not null primary key,balance int,update_date datetime);
insert into wallet(user_id,balance,update_date) values(1,1000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(2,20000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(3,300000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(4,1000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(5,20000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(6,300000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(7,1000,cast(now() as datetime));
insert into wallet(user_id,balance,update_date) values(8,20000,cast(now() as datetime));

���݌Ƀe�[�u��
create table stock(stock_id int not null primary key auto_increment,branch_id int not null,branch_name varchar(10),product_code int not null,product_name varchar(20),color varchar(10),size varchar(3),price int,quantity int);
insert into stock values(0,1,"L�EA�x�X",1,"�X�J�s�V���c","��","S",2000,10);
insert into stock values(0,1,"L�EA�x�X",2,"�X�J�s�V���c","��","M",2000,10);
insert into stock values(0,1,"L�EA�x�X",3,"�X�J�s�V���c","��","L",2000,10);
insert into stock values(0,1,"L�EA�x�X",4,"�X�J�s�V���c","��","S",2000,10);
insert into stock values(0,1,"L�EA�x�X",5,"�X�J�s�V���c","��","M",2000,10);
insert into stock values(0,1,"L�EA�x�X",6,"�X�J�s�V���c","��","L",2000,10);
insert into stock values(0,1,"L�EA�x�X",7,"�X�J�s�V���c","��","S",2000,10);
insert into stock values(0,1,"L�EA�x�X",8,"�X�J�s�V���c","��","M",2000,10);
insert into stock values(0,1,"L�EA�x�X",9,"�X�J�s�V���c","��","L",2000,10);
insert into stock values(0,2,"��ʍ��X�J�x�X",1,"�X�J�s�V���c","��","S",2000,10);
insert into stock values(0,2,"��ʍ��X�J�x�X",2,"�X�J�s�V���c","��","M",2000,10);
insert into stock values(0,2,"��ʍ��X�J�x�X",3,"�X�J�s�V���c","��","L",2000,10);
insert into stock values(0,2,"��ʍ��X�J�x�X",4,"�X�J�s�V���c","��","S",2000,10);
insert into stock values(0,2,"��ʍ��X�J�x�X",5,"�X�J�s�V���c","��","M",2000,10);
insert into stock values(0,2,"��ʍ��X�J�x�X",6,"�X�J�s�V���c","��","L",2000,10);
insert into stock values(0,2,"��ʍ��X�J�x�X",7,"�X�J�s�V���c","��","S",2000,10);
insert into stock values(0,2,"��ʍ��X�J�x�X",8,"�X�J�s�V���c","��","M",2000,10);
insert into stock values(0,2,"��ʍ��X�J�x�X",9,"�X�J�s�V���c","��","L",2000,10);
insert into stock values(0,3,"�ԍ�x�X",1,"�X�J�s�V���c","��","S",2000,10);
insert into stock values(0,3,"�ԍ�x�X",2,"�X�J�s�V���c","��","M",2000,10);
insert into stock values(0,3,"�ԍ�x�X",3,"�X�J�s�V���c","��","L",2000,10);
insert into stock values(0,3,"�ԍ�x�X",4,"�X�J�s�V���c","��","S",2000,10);
insert into stock values(0,3,"�ԍ�x�X",5,"�X�J�s�V���c","��","M",2000,10);
insert into stock values(0,3,"�ԍ�x�X",6,"�X�J�s�V���c","��","L",2000,10);
insert into stock values(0,3,"�ԍ�x�X",7,"�X�J�s�V���c","��","S",2000,10);
insert into stock values(0,3,"�ԍ�x�X",8,"�X�J�s�V���c","��","M",2000,10);
insert into stock values(0,3,"�ԍ�x�X",9,"�X�J�s�V���c","��","L",2000,10);

�������e�[�u��
create table fashionshop.stockorder (no int primary key auto_increment,product_name varchar(10),price int,color varchar(10),size varchar(3),branch_id int,order_quantity int,del_flg int, status int,index(no));

������e�[�u��
create table earnings(no int primary key auto_increment,earnings int,ins_date date,branch_id int,del_flg int,product_name varchar(10),quantity int,color varchar(10),size varchar(5));

