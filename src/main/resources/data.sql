--Insertar estilos
INSERT INTO style VALUES (1, 'Blues');
INSERT INTO style VALUES (2, 'Country');
INSERT INTO style VALUES (3, 'Electronic music');
INSERT INTO style VALUES (4, 'Folk');
INSERT INTO style VALUES (5, 'Hip Hop');
INSERT INTO style VALUES (6, 'Jazz');
INSERT INTO style VALUES (7, 'Latin');
INSERT INTO style VALUES (8, 'Pop');
INSERT INTO style VALUES (9, 'Soul');
INSERT INTO style VALUES (10, 'Rock');
INSERT INTO style VALUES (11, 'Classical music');
INSERT INTO style VALUES (12, 'Other');

--Artistas de ejemplo
INSERT INTO artist VALUES (1, 'Avicii', 2009);
INSERT INTO artist VALUES (2, 'Rolling Stones', 1987);

--Relaciones artista-style
INSERT INTO artist_style VALUES(1, 3);
INSERT INTO artist_style VALUES(2, 10);

--Personas de ejemplo
INSERT INTO person VALUES (1, TRUE, 'pablo', '12345', 1992);
INSERT INTO person VALUES (2, TRUE, 'pedro', '12345', 1997);
INSERT INTO person VALUES (3, TRUE, 'paco', '12345', 1991);

--Relaciones artista-persona
INSERT INTO artist_person VALUES(1, 1);
INSERT INTO artist_person VALUES(1, 2);
INSERT INTO artist_person VALUES(2, 3);

--Relaciones artista-artista
INSERT INTO artist_related VALUES(1, 2);
INSERT INTO artist_related VALUES(2, 1);