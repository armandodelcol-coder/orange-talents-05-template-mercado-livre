create table tb_purchase(
    id bigint primary key auto_increment,
    quantity smallint unsigned not null,
    gateway varchar(255) not null,
    created_at datetime not null,
    product_id bigint not null,
    purchaser_id bigint not null,
    current_product_price decimal(10,2) not null,
    code varchar(255) not null,

    constraint fk_purchase_product foreign key (product_id) references tb_product (id),
    constraint fk_purchase_purchaser foreign key (purchaser_id) references tb_user (id)
) engine=InnoDB default charset=utf8mb4;