create table tb_category(
    id bigint primary key auto_increment,
    name varchar(255) not null unique,
    parent_category_id bigint,

    constraint fk_category_parent_category foreign key (parent_category_id) references tb_category(id)
) engine=InnoDB default charset=utf8mb4;