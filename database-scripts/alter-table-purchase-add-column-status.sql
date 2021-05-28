alter table tb_purchase add column status varchar(255) not null;

update tb_purchase set status = "INICIADA";