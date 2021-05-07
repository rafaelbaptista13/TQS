CREATE TABLE books (
  id BIGSERIAL PRIMARY KEY,
  title varchar(255) not null
);

INSERT into books (title) VALUES ('Os Maias');