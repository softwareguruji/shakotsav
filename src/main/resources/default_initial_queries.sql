-- Role Setup

insert into role (role) values ('Super Admin');
insert into role (role) values ('Admin');
insert into role (role) values ('Supplier');
insert into role (role) values ('Table Owner');

-- Status Setup

insert into status (status_name) values ('Idle');
insert into status (status_name) values ('Requested');
insert into status (status_name) values ('Dispatched');
