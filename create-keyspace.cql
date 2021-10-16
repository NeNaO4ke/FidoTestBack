use fido;
create table if not exists usr
(
    username text,
    password text,
    email    text,
    PRIMARY KEY (email)
);
alter table usr
    add roles set<text>;
create table if not exists room
(
    name text,
    location text,
    capacity tinyint,
    PRIMARY KEY (name)
);
create table if not exists booking
(
    name  text,
    start timestamp,
    end   timestamp,
    PRIMARY KEY (name, start, end)
);
