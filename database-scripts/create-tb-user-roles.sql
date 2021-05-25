create table tb_user_roles(
    id bigint primary key auto_increment,
    user_id bigint not null,
    role_id bigint not null,

    constraint fk_user_role_user foreign key (user_id) references tb_user (id),
    constraint fk_user_role_role foreign key (role_id) references tb_role (id)
) engine=InnoDB default charset=utf8mb4;