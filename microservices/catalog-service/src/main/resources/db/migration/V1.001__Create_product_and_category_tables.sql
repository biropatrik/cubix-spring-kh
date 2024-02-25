create sequence category_seq start with 1 increment by 50;
create sequence product_seq start with 1 increment by 50;

create table category (id bigint not null, name varchar(255), primary key (id));
create table product (price integer not null, category_id bigint, id bigint not null, name varchar(255), primary key (id));

alter table if exists product add constraint FK_product_to_category foreign key (category_id) references category;
