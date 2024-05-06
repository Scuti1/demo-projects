create table PERSONS
(
    ID       bigint       not null AUTO_INCREMENT PRIMARY KEY,
    FIRSTNAME varchar(128) not null,
    LASTNAME varchar(128) not null,
    REGNO    varchar(32)  not null,
    GENDER   varchar(12)  not null
);