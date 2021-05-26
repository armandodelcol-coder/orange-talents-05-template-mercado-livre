create table tb_product_opinion(
    id bigint primary key auto_increment,
    rating tinyint unsigned not null,
    title varchar(255) not null,
    description text not null,
    product_id bigint not null,
    user_id bigint not null,

    constraint fk_product_opinion_product foreign key (product_id) references tb_product (id),
    constraint fk_product_opinion_user foreign key (user_id) references tb_user (id)
) engine=InnoDB default charset=utf8mb4;