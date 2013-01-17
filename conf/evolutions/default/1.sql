# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table samochod (
  id                        bigint auto_increment not null,
  marka                     varchar(255),
  model                     varchar(255),
  typ                       varchar(255),
  num_rej                   varchar(255),
  data_produkcji            datetime,
  przeglad                  datetime,
  ubezpieczenie             datetime,
  constraint pk_samochod primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table samochod;

SET FOREIGN_KEY_CHECKS=1;

