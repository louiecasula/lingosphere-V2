INSERT INTO languages (language_id, name, code)
VALUES (1, 'English', 'EN'),
       (2, 'Spanish', 'ES');

INSERT INTO words (word_id, language_id, text, level)
VALUES (1, 1, 'cat', 1),
       (2, 1, 'dog', 1),
       (3, 1, 'bird', 1),
       (4, 1, 'ambiguous', 2),
       (5, 1, 'comprehensive', 2),
       (6, 1, 'innovate', 2),
       (7, 1, 'quintessential', 3),
       (8, 1, 'subjugate', 3),
       (9, 1, 'pernicious', 3);

INSERT INTO words (word_id, language_id, text, level)
VALUES (10, 2, 'gatto', 1),
       (11, 2, 'perro', 1),
       (12, 2, 'pajaro', 1),
       (13, 2, 'ambiguo', 2),
       (14, 2, 'integral', 2),
       (15, 2, 'innovar', 2),
       (16, 2, 'quintesencial', 3),
       (17, 2, 'subyugar', 3),
       (18, 2, 'pernicioso', 3);