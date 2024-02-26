create sequence product_seq start with 1 increment by 50;

create table category (name varchar(255) not null, primary key (name));
create table product (price integer not null, id bigint not null, category_name varchar(255), name varchar(255), primary key (id));

alter table if exists product add constraint FK_product_to_category foreign key (category_name) references category;
