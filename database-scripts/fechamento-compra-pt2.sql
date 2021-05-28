alter table tb_purchase add column finalized_at datetime;
alter table tb_purchase add column failed_at datetime;

create table tb_transaction(
    id bigint primary key auto_increment,
    status varchar(255) not null,
    purchase_id bigint not null,
    transaction_code varchar(255) not null,
    gateway varchar(255) not null,
    created_at datetime not null,

    constraint fk_transaction_purchase foreign key (purchase_id) references tb_purchase (id)
) engine=InnoDB default charset=utf8mb4;
