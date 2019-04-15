/*
--queries to delete all values
delete from login;
delete from deleted_students;
delete from invigilates;
delete from writes;
delete from student;
delete from teacher;
delete from room;
delete from school;
---
*/

--to drop all tables
drop table login;
drop table writes;
drop table student;
drop table invigilates;
drop table teacher;
drop table room;
drop table school;
drop table deleted_students;
---

--create table commands
create table school(
school_id varchar(5),
name varchar(30),
address varchar(30),
center_id number(5),
primary key(school_id)
);

create table teacher(
teacher_id varchar(7),
school_id varchar(5),
name varchar(15),
phone_no number(10),
primary key(teacher_id),
foreign key(school_id) references school
on delete cascade
);

create table student(
student_id varchar(10),
school_id varchar(5),
name varchar(15),
phone_no number(10),
address varchar(30),
primary key(student_id),
foreign key(school_id) references school
on delete cascade
);

create table room(
school_id varchar(5),
room_no number(3),
capacity number(2),
occupancy int default(0),
check (occupancy <= capacity),
primary key(school_id,room_no),
foreign key(school_id) references school
on delete cascade
);

create table invigilates(
teacher_id varchar(7),
school_id varchar(5),
room_no number(3),
primary key(teacher_id),
foreign key(teacher_id) references teacher
on delete cascade,
foreign key(school_id,room_no) references room
on delete cascade
);

create table writes(
student_id varchar(10),
school_id varchar(5), --The school_id of the center in which he writes
room_no number(3),
primary key(student_id),
foreign key(student_id) references student
on delete cascade,
foreign key(school_id,room_no) references room
on delete cascade
);

create table login(
username varchar(10),
password varchar(10),
access_id number(1),
check (access_id in (0,1,2)),
primary key(username)
);

create table deleted_students(
	student_id varchar(10),
	school_id varchar(5),
	name varchar(15),
	phone_no number(10),
	address varchar(30),
	primary key(student_id)
);
-------


--required functions
create or replace function center_availability(sid in varchar)
	return number
	is
	flag number(1);
	capacity number(2);
	occupancy int;
	begin
	select sum(capacity), sum(occupancy) into capacity, occupancy
	from room where school_id=sid;
	if occupancy >= capacity then
		flag := 1; --center full then flag is 1;
	else
		flag := 0;
	end if;
	return flag;
end;
/
-------


-----procedures
create or replace procedure student_delete(stud_id in varchar) is
	temp number(1) := 0;
	s_id varchar(5);
	r_no number(3);
	begin
	select count(*) into temp from writes where student_id=stud_id;
	if temp > 0 then
		select school_id,room_no into s_id,r_no from writes where student_id = stud_id;
		dbms_output.put_line(s_id||' '||r_no);
		update room set occupancy = occupancy-1 where school_id = s_id and room_no = r_no;
		dbms_output.put_line('student in writes');
	else
		dbms_output.put_line('student not in writes');
	end if;
	delete from student where student_id = stud_id;
	delete from login where username = stud_id;
end;
/

create or replace procedure writes_insert (stud_id in varchar,sid in varchar) is
	cursor c1 is select room_no,capacity,occupancy from room where school_id = sid for update;
begin
	for v in c1
	loop
		if v.occupancy < v.capacity then
			insert into writes values(stud_id,sid,v.room_no);
			update room set occupancy = occupancy+1 where current of c1;
			exit;
		end if;
	end loop;
end;
/

---not used in GUI. But still useful.
create or replace procedure writes_delete(stud_id in varchar) is
s_id varchar(5);
r_no number(3);
begin
	select school_id,room_no into s_id,r_no from writes where student_id = stud_id;
	update room set occupancy = occupancy-1 where school_id = s_id and room_no = r_no;
	delete from writes where student_id = stud_id;
end;
/


create or replace procedure center_delete(s_id varchar) is
begin
	delete from writes where school_id = s_id;
	delete from invigilates where school_id = s_id;
	update room set occupancy = 0 where school_id = s_id;
	update school set center_id = -1 where school_id = s_id;
end;
/
-------------

----triggers
create or replace trigger stud_del
before delete on student
for each row
begin
insert into deleted_students values (:OLD.student_id,:OLD.school_id,:OLD.name,:OLD.phone_no,:OLD.address);
end;
/
-------


------insert statements

insert into login values ('admin', 'pass', 0);

insert into school values('10101','St. Josephs High School','Manipal',95678);
insert into school values('10122','Little Flowers High School','Karkala',67435);
insert into school values('21013','Narayana High School','Udupi',71239);
insert into school values('20045','Stonewall School of Excellence','Manglore',80567);

insert into login values ('10101','10101',1);
insert into login values ('10122','10122',1);
insert into login values ('21013','21013',1);
insert into login values ('20045','20045',1);


insert into teacher values('AB31245','10101','Singh',4792764);
insert into teacher values('AC22222','10101','Kumar',3927401);
insert into teacher values('BC74569','10122','Sudarsdhan',4723930);
insert into teacher values('CD34902','10122','Henry',3473920);
insert into teacher values('AW45698','21013','Peter',9846532);
insert into teacher values('QW36750','21013','Rao',9945362);
insert into teacher values('FF45367','20045','Reddy',8230125);
insert into teacher values('ER00134','20045','Bajaj',7639201);


insert into student values ('1709005278','10101', 'Shankar',4959165,'Manipal');
insert into student values ('1709004343','10101', 'Brandt',8943832,'Manipal');
insert into student values ('1709002344','10101', 'Chavez',3159971,'Manipal');
insert into student values ('1708016578','10122', 'Peltier',3297727,'Karkala');
insert into student values ('1708014563','10122', 'Levy',2293786,'Karkala');
insert into student values ('1708076897','10122', 'Williams',3692556,'Karkala');
insert into student values ('1704432451','21013', 'Sanchez',8471594,'Udupi');
insert into student values ('1704465743','21013', 'Snow',2882698,'Udupi');
insert into student values ('1704432190','21013', 'Brown',7864934,'Udupi');
insert into student values ('1706069696','20045', 'Arun',8647881,'Manglore');
insert into student values ('1706042042','20045', 'Bourikas',7837633,'Manglore');
insert into student values ('1706066677','20045', 'Tanaka',9530653,'Manglore');

insert into login values ('1709005278','1709005278',2);
insert into login values ('1709004343','1709004343',2);
insert into login values ('1709002344','1709002344',2);
insert into login values ('1708016578','1708016578',2);
insert into login values ('1708014563','1708014563',2);
insert into login values ('1708076897','1708076897',2);
insert into login values ('1704432451','1704432451',2);
insert into login values ('1704465743','1704465743',2);
insert into login values ('1704432190','1704432190',2);
insert into login values ('1706069696','1706069696',2);
insert into login values ('1706042042','1706042042',2);
insert into login values ('1706066677','1706066677',2);


insert into room values('10101',201,10,0);
insert into room values('10101',202,10,0);
insert into room values('10101',203,10,0);
insert into room values('10122',311,10,0);
insert into room values('10122',312,10,0);
insert into room values('10122',313,10,0);
insert into room values('21013',101,10,0);
insert into room values('21013',102,10,0);
insert into room values('21013',103,10,0);
insert into room values('20045',315,10,0);
insert into room values('20045',316,10,0);
insert into room values('20045',317,10,0);


insert into invigilates values('AB31245','10101',201);
insert into invigilates values('AC22222','10101',202);
insert into invigilates values('BC74569','10122',311);
insert into invigilates values('CD34902','10122',312);
insert into invigilates values('AW45698','21013',101);
insert into invigilates values('QW36750','21013',102);
insert into invigilates values('FF45367','20045',315);
insert into invigilates values('ER00134','20045',316);


begin
writes_insert('1709005278', '10122');
writes_insert('1709004343','21013');
writes_insert('1709002344','20045');
writes_insert('1708016578','10101');
writes_insert('1708014563','21013');
writes_insert('1708076897','20045');
writes_insert('1704432451','10101');
writes_insert('1704465743','10122');
writes_insert('1704432190','20045');
writes_insert('1706069696','10101');
writes_insert('1706042042','10122');
writes_insert('1706066677','21013');
end;
/

/* --if all the students have to be deleted from writes safely
begin
writes_delete('1709005278');
writes_delete('1709004343');
writes_delete('1709002344');
writes_delete('1708016578');
writes_delete('1708014563');
writes_delete('1708076897');
writes_delete('1704432451');
writes_delete('1704465743');
writes_delete('1704432190');
writes_delete('1706069696');
writes_delete('1706042042');
writes_delete('1706066677');
end;
/
*/

/* --to clear all centers. Useful
begin
center_delete('10101');
center_delete('10122');
center_delete('21013');
center_delete('20045');
end;
/
*/

set serveroutput on;

commit;
