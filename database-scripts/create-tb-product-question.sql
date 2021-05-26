create table tb_product_question(
    id bigint primary key auto_increment,
    title varchar(255) not null,
    created_at datetime not null,
    product_id bigint not null,
    user_id bigint not null,

    constraint fk_product_question_product foreign key (product_id) references tb_product (id),
    constraint fk_product_question_user foreign key (user_id) references tb_user (id)
) engine=InnoDB default charset=utf8mb4;