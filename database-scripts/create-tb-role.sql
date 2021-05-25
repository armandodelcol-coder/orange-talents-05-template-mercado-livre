create table tb_role(
    id bigint primary key auto_increment,
    name varchar(255) not null unique

) engine=InnoDB default charset=utf8mb4;