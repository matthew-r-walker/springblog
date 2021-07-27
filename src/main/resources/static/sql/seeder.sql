USE springblog_db;

TRUNCATE users;
TRUNCATE posts;
TRUNCATE ads;
TRUNCATE categories;

INSERT INTO users (username, email, password)
VALUES ('admin', 'admin@email.com', 'password')


INSERT INTO posts (id, title, body, user_id)
VALUES (1, 'Greg likes potato chips', 'He sure does look at him go!', 1),
       (2, 'Hi, my name Borat', 'Woo wow wee wow', 1),
       (3, 'BINGO BANGO', 'My name''s Funbucket!', 1),
       (4, 'Test post', 'oooooo weeeee', 1),
       (5, 'I''m Mr. Meseeks', 'Look at meeee', 1),
       (6, 'Teststst', 'bzzzzzzzt', 1);

INSERT INTO ads (description, title, user_id)
VALUES ('Greg likes potato chips', 'He sure does look at him go!', 1),
       ('Hi, my name Borat', 'Woo wow wee wow', 1),
       ('BINGO BANGO', 'My name''s Funbucket!', 1),
       ('Test post', 'oooooo weeeee', 1),
       ('I''m Mr. Meseeks', 'Look at meeee', 1),
       ('Teststst', 'bzzzzzzzt', 1);

INSERT INTO categories (name)
VALUES ('lorem'), ('ipsum'), ('dolor'), ('sit'), ('amet');

INSERT INTO ads_categories (ad_id, category_id)
VALUES (1, 1), (1, 2), (2, 2), (3, 3), (4, 4), (5, 5);