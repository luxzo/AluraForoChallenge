create table topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(1000) not null,
    fecha datetime,
    autor varchar(100) not null,
    curso varchar(100) not null,
    status tinyint,

    primary key(id)
);

update topicos set status = 1