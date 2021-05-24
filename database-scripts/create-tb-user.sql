create table tb_user(
    id bigint primary key auto_increment,
    email varchar(255) not null,
    password varchar(255) not null,
    created_at datetime not null
) engine=InnoDB default charset=utf8mb4;