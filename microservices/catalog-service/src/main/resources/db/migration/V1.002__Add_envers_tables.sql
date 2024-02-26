create sequence revinfo_seq start with 1 increment by 50;

create table category_aud (rev integer not null, revtype smallint, name varchar(255) not null, primary key (rev, name));
create table product_aud (price integer, rev integer not null, revtype smallint, id bigint not null, category_name varchar(255), name varchar(255), primary key (rev, id));
create table revinfo (rev integer not null, revtstmp bigint, primary key (rev));

alter table if exists category_aud add constraint category_aud foreign key (rev) references revinfo;
alter table if exists product_aud add constraint product_aud foreign key (rev) references revinfo;
