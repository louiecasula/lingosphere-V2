INSERT INTO languages (language_id, name, code) VALUES (1, 'English', 'EN');
INSERT INTO languages (language_id, name, code) VALUES (2, 'Spanish', 'ES');

INSERT INTO words (word_id, language_id, text, level) VALUES (1, 1, 'cat', 1);
INSERT INTO words (word_id, language_id, text, level) VALUES (2, 1, 'dog', 1);
INSERT INTO words (word_id, language_id, text, level) VALUES (3, 1, 'bird', 1);
INSERT INTO words (word_id, language_id, text, level) VALUES (4, 1, 'ambiguous', 2);
INSERT INTO words (word_id, language_id, text, level) VALUES (5, 1, 'comprehensive', 2);
INSERT INTO words (word_id, language_id, text, level) VALUES (6, 1, 'innovate', 2);
INSERT INTO words (word_id, language_id, text, level) VALUES (7, 1, 'quintessential', 3);
INSERT INTO words (word_id, language_id, text, level) VALUES (8, 1, 'subjugate', 3);
INSERT INTO words (word_id, language_id, text, level) VALUES (9, 1, 'pernicious', 3);

INSERT INTO words (word_id, language_id, text, level) VALUES (10, 2, 'gatto', 1);
INSERT INTO words (word_id, language_id, text, level) VALUES (11, 2, 'perro', 1);
INSERT INTO words (word_id, language_id, text, level) VALUES (12, 2, 'pajaro', 1);
INSERT INTO words (word_id, language_id, text, level) VALUES (13, 2, 'ambiguo', 2);
INSERT INTO words (word_id, language_id, text, level) VALUES (14, 2, 'integral', 2);
INSERT INTO words (word_id, language_id, text, level) VALUES (15, 2, 'innovar', 2);
INSERT INTO words (word_id, language_id, text, level) VALUES (16, 2, 'quintesencial', 3);
INSERT INTO words (word_id, language_id, text, level) VALUES (17, 2, 'subyugar', 3);
INSERT INTO words (word_id, language_id, text, level) VALUES (18, 2, 'pernicioso', 3);