
# --- !Ups
 
create table blog (
    id    bigint(20) NOT NULL AUTO_INCREMENT,
    title  varchar(255),
    body   varchar(255),
    PRIMARY KEY (id)
);
 
# --- !Downs
 
drop table blog;