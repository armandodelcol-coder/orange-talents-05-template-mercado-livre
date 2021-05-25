create table tb_product_images(
    id bigint primary key auto_increment,
    bucket_url varchar(255) not null,
    product_id bigint,

    constraint fk_product_images_product foreign key (product_id) references tb_product (id) on delete cascade
) engine=InnoDB default charset=utf8mb4;