INSERT INTO USER(uname, upass, email) VALUES ('guestUser', 'guest', 'guest@guest.com');

INSERT INTO Route VALUES (1, 15.0);
INSERT INTO Route VALUES (2, 60.0);
INSERT INTO Route VALUES (3, 30.0);

INSERT INTO RouteStop VALUES (1, 1, 104);
INSERT INTO RouteStop VALUES (2, 1, 106);
INSERT INTO RouteStop VALUES (3, 2, 1);
INSERT INTO RouteStop VALUES (4, 2, 2);
INSERT INTO RouteStop VALUES (5, 2, 3);
INSERT INTO RouteStop VALUES (6, 2, 9);
INSERT INTO RouteStop VALUES (7, 3, 130);
INSERT INTO RouteStop VALUES (8, 3, 58);
INSERT INTO RouteStop VALUES (9, 3, 64);

INSERT INTO StoredRoute(sid, uname, rid, name, description, visible, rating) VALUES (1,'guestUser', 1, 'Exit Kelowna - BNA Pub Friday Night Fun', 'It is Friday night and You and your Friends do not know what to do ? Try out one of our recommended routes: Exit and BNA night is great for hanging out and having fun with your friends !', 1, 5.00);
INSERT INTO StoredRoute(sid, uname, rid, name, description, visible, rating) VALUES (2,'guestUser', 2, 'Norbert''s Wine Tour' , 'Visit Kelowna''s best wineries and enjoy the best quality wines that the Okanagan has to offer ! This recommended route is perfect for your first wine tasting experience in the Okanagan!',1 ,5.00);
INSERT INTO StoredRoute(sid, uname, rid, name, description, visible, rating) VALUES (3,'guestUser', 3, 'Active outdoor day','Would you like to take some fresh air? Go for a hike to Knox Mountain, then walk around at Kelowna City Park, also visit the Kusagai Garden. All 3 places are in Downtown and within walking distance.', 1, 5.0);