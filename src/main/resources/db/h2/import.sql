insert into posts (id, title, content, status, created_at) values
('1', 'My first post', 'This my content', 0, now()),
('2', 'My second post', 'This my content', 0, now());

insert into authors (id, name) values
('1', 'Andre'),
('2', 'Luana');

insert into post_authors (post_id, author_id) values
('1', '1'),
('2', '2');