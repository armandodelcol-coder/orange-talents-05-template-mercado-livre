create table tb_product(
    id bigint primary key auto_increment,
    name varchar(255) not null,
    price decimal(10, 2) not null,
    stock smallint unsigned not null,
    description text not null,
    category_id bigint not null,
    created_at datetime not null,
    owner_id bigint not null,

    constraint fk_product_category foreign key (category_id) references tb_category (id),
    constraint fk_product_owner foreign key (owner_id) references tb_user (id)
) engine=InnoDB default charset=utf8mb4;

create table tb_attribute(
    id bigint primary key auto_increment,
    name varchar(255) not null,
    description varchar(255) not null,
    product_id bigint not null,

    constraint fk_attribute_product foreign key (product_id) references tb_product (id)
) engine=InnoDB default charset=utf8mb4;