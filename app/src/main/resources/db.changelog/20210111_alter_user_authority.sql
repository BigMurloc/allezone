alter table app_user_authority drop constraint if exists fkq3y9v2em5ia10xv9e76ya53sf;

alter table app_user_authority
	add constraint fkq3y9v2em5ia10xv9e76ya53sf
		foreign key (app_user_id) references app_user
			on delete cascade;
