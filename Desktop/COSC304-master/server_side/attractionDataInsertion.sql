CREATE TABLE User (
  uname VARCHAR(45) NOT NULL,
  upass VARCHAR(45) NOT NULL,
  first_name VARCHAR(25),
  last_name VARCHAR(25),
  email VARCHAR(128) NOT NULL,
  country VARCHAR(45),
  PRIMARY KEY (uname)
);

CREATE TABLE Route (
  rid int AUTO_INCREMENT,
  travel_time DECIMAL(10,2),
  PRIMARY KEY (rid)
);

CREATE TABLE Attraction (
  aid int AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  address VARCHAR(45),
  zip VARCHAR(7),
  city VARCHAR(45),
  country VARCHAR(45),
  lat DECIMAL(10,7) NOT NULL,
  lng DECIMAL(10,7) NOT NULL,
  type VARCHAR(20) NOT NULL,
  description VARCHAR(200),
  rating DECIMAL(3,2),
  picture VARCHAR(512) CHARACTER SET 'ascii' COLLATE 'ascii_general_ci',
  PRIMARY KEY (aid)
);

CREATE TABLE StoredRoute (
  sid int AUTO_INCREMENT,
  uname VARCHAR(45) NOT NULL,
  rid int NOT NULL,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(200),
  picture VARCHAR(512) CHARACTER SET 'ascii' COLLATE 'ascii_general_ci',
  date_taken DATE,
  visible tinyint(1) NOT NULL,
  rating DECIMAL(3,2),
  PRIMARY KEY (sid, uname, rid),
  FOREIGN KEY (uname) REFERENCES User(uname),
  FOREIGN KEY (rid) REFERENCES Route(rid)
);

CREATE TABLE RouteStop (
  id int AUTO_INCREMENT,
  rid int NOT NULL,
  aid int NOT NULL,
  PRIMARY KEY(id, rid, aid),
  FOREIGN KEY (rid) REFERENCES Route(rid),
  FOREIGN KEY (aid) REFERENCES Attraction(aid)
);



CREATE TABLE AttractionPhoto (
    aid int,
    picture VARCHAR(512) CHARACTER SET 'ascii' COLLATE 'ascii_general_ci',
    PRIMARY KEY(aid, picture),
    FOREIGN KEY (aid) REFERENCES Attraction(aid)
);

INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (1, 49.839825, -119.431216, 'SpierHead Winery', '3950 Spiers Rd', 'V1W 4B3', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (2, 49.85189, -119.46511, 'Sperling Vineyards', '1405 Pioneer Rd', 'V1W 4M6', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (3, 49.859538, -119.375084, 'Rose House of Winery', '2270 Garner Rd', 'V1P 1E2', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (4, 49.894937, -119.488345, 'Sandhill Wines', '1125 Richter St', 'V1Y 2K6', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (5, 49.84296, -119.56664, 'Quails'' Gate', '3303 Boucherie Rd', 'V1Z 2H3', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (6, 49.95416, -119.35223, 'Ancient Hill Winery', '4918 Anderson Road', 'V1X 7V7', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (7, 49.78905, -119.545278, 'CedarCreek Estate Winery', '5445 Lakeshore Rd', 'V1W 4S5', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (8, 49.807832, -119.499872, 'Summerhill Pyramid Winery', '4870 Chute Lake Rd', 'V1W 4M3', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (9, 49.845055, -119.438653, 'The View Winery & Vineyard', '2287 Ward Rd', 'V1W 4R5', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (10, 49.832219, -119.456922, 'Tantalus Vineyards', '1670 DeHart Rd', 'V1W 4N6', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (11, 49.87113, -119.54273, 'Rollingdale Winery', '2306 Hayman Rd', 'V1Z 1Z5', 'West Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (12, 49.7974, -119.528156, 'St Hubertus & Oak Bay Estate Winery', '5205 Lakeshore Rd', 'V1W 4J1', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (13, 49.85636, -119.55022, 'Mt. Boucherie Estate Winery', '829 Douglas Rd', 'V1Z 1N9', 'West Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (14, 49.78905, -119.545278, 'Vineyard Terrace Restaurant', '5445 Lakeshore Rd', 'V1W 4S5', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (15, 49.85716, -119.55555, 'Beaumont Family Estate Winery', '2775 Boucherie Rd', 'V1Z 2G4', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (16, 49.84159, -119.55957, 'Uncorked Wine Tours Kelowna', '1055 Allison Place', 'V1Z 2E1', 'West Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (17, 49.83635, -119.58663, 'Mission Hill Family Estate Winery', '1730 Mission Hill Rd', 'V4T 2E4', 'West Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (18, 49.85756, -119.55902, 'Little Straw Vineyards', '2815 Ourtoland Rd', 'V1Z 2H7', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (19, 49.82764, -119.62927, 'Distinctly Kelowna Tours', '2475 Dobbin Rd #22', 'V4T 2E9', 'West Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (20, 49.8558, -119.55563, 'Volcanic Hills Estate Winery', '2845 Boucherie Rd', 'V1Z 2G6', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (21, 49.894158, -119.495312, 'Waterfront Wines - Restaurant & Wine Bar', '1180 Sunset Dr', 'V1Y 9W6', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (22, 49.860094, -119.41201, 'The Vibrant Vine', '3240 Pooley Rd', 'V1W 4G7', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (23, 49.86671, -119.40465, 'Camelot Vineyards', '3489 E Kelowna Rd', 'V1W 4H1', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (24, 49.86437, -119.55532, 'Grizzli Winery', '2550 Boucherie Rd', 'V1Z 2E6', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (25, 49.875407, -119.548624, 'Indigenous World Winery', '2218 Horizon Drive E', 'V1Z 3L4', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (26, 49.84571, -119.56147, 'The hatch', '3225 Boucherie Rd', 'V1Z 2G9', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (27, 50.046715, -119.438284, 'Ex Nihilo Vineyards', '1525 Camp Rd', 'V4V 1K1', 'Lake Country', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (28, 49.890065, -119.486588, 'Experience Wine Tours', '763 Fuller Ave', 'V1Y 6X2', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (29, 49.838379, -119.407313, 'Meadow Vista Honey Wines', '3975 June Springs Rd', 'V1W 4E4', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (30, 49.8412, -119.64488, 'Kalala Organic Vineyards Ltd', '3361 Glencoe Rd', 'V4T 1M1', 'West Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (31, 49.83182, -119.64199, 'Off The Grid Organic Winery', '3623 Glencoe Rd', 'V4T 1L8', 'West Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (32, 49.858499, -119.372785, 'Frequency Winery - The Wine & Sound', '2261 Garner Rd', 'V1P 1E2', 'Kelowna', 'Canada', 'Winery');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (33, 49.850176, -119.443937, 'McMillan Farms', '3690 Berard Rd #1', 'V1W 4A9', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (34, 49.889484, -119.35519, 'Woodhaven Tree Farm & Tree Movers', '1705 Swainson Rd', 'V1P 1C5', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (35, 49.87431, -119.383868, 'Kempf Orchards', '1409 Teasdale Rd', 'V1P 1C8', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (36, 49.823845, -119.449266, 'Okanagan Lavender and Herb Farm', '4380 Takla Rd', 'V1W 3C4', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (37, 49.82785, -119.433359, 'Arion Therapeutic Farm', '2457 Saucier Rd', 'V1W 4B8', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (38, 49.855226, -119.45992, 'Fairweather Farms Ltd', '3527 Benvoulin Rd', 'V1W 4M5', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (39, 49.827821, -119.443291, 'Arlo''s Honey Farm', '4329 Bedford Lane', 'V1W 3C5', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (40, 49.908629, -119.422481, 'Falcon Ridge Farms', '579 Rifle Rd', 'V1V 2G3', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (41, 49.831435, -119.478745, 'Old Meadows Organic Farm & Market', '4213 Gordon Dr', 'V1W 1S4', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (42, 49.850742, -119.441325, 'Kelowna Free Graze Lamb', '3652 Spiers Rd', 'V1W 4A9', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (43, 49.932912, -119.36418, 'Eco Turf Farms', '3330 Old Vernon Road', 'V1X 6P3', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (44, 49.911157, -119.386497, 'De Simone Farms Ltd', '1685 Rutland Rd N', 'V1X 4Z8', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (45, 50.022204, -119.40379, 'Kangaroo Creek Farm', '3193 Hill Rd', 'V4V 1T7', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (46, 49.851571, -119.371593, 'Canyon Ridge Daylily Farm', '2801 Walburn Rd', 'V1P 1E3', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (47, 49.883882, -119.372052, 'Hillcrest Farm Market & Cafe', '700 Highway 33 East', 'V1X 7W4', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (48, 49.853907, -119.474398, 'Lavender and Lace Floral Farm', '3609 Gordon Dr', 'V1W 4M8', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (49, 49.873736, -119.590945, 'Mountain Valley Farm', '2010 Bartley Rd', 'V1Z 2M6', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (50, 49.821771, -119.439055, 'Sunshine Farms', '2225 Saucier Rd', 'V1W 4B8', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (51, 49.934012, -119.386094, 'Mac Donnell Turf Farms', '3345 Bulman Rd', 'V1X 7V1 ', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (52, 49.828598, -119.405979, 'Caldwell Heritage Farm', '4275 Goodison Rd', 'V1W 4C6', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (53, 49.810957, -119.627295, 'Gellatly Nut Farm Regional Park', '2375 Whitworth Rd', 'V4T 2K3', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (54, 49.82, -119.46, 'Stewart Bros Nurseries Ltd', '4500 Stewart Rd W', 'V1W 4N5', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (55, 49.872658, -119.388875, 'Arndt Orchards', '1555 Teasdale Rd', 'V1P 1C8', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (56, 49.862822, -119.444888, 'Wise Earth Farm', '2071 Fisher Rd', 'V1W 2H4', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (57, 49.843845, -119.46601, 'Cattail Creek Farms', '3830 Casorso Rd', 'V1W 4R7', 'Kelowna', 'Canada', 'Farm/Orchard');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (58, 49.885154, -119.499644 ,'Kelowna City Park' ,'1600 Abbott Street' ,'V1Y 8S3' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (59, 49.893695, -119.495128 ,'Waterfront Park' ,'1200 Water Street' ,'V1Y 9V1' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (60, 49.904305, -119.490187 ,'Knox Mountain Park' ,'621 Broadway Ave' ,'V1Y 7M8' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (61, 49.87511, -119.47211 ,'Stillingfleet Park' ,'1284 McBride Rd' ,'V1Y 4A3' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (62, 49.855754, -119.491092 ,'Boyce -Gyro Beach Park' ,'3400 Lakeshore Road' ,'V1W 3S9' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (63, 49.880335, -119.416346 ,'Mission Creek Regional Park' ,'2551 Springfield Road' ,'V1X 1C3' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (64, 49.886916, -119.496475 ,'Kusagai Gardens' ,'1435 Water Street' ,'V1Y 6V7' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (65, 49.867148, -119.488355 ,'Osprey Park' ,'2600 Richter Street' ,'V1Y 2R3' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (66, 49.871105, -119.486784 ,'Cameron Park' ,'746 Birch Avenue' ,'V1Y 9V6' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (67, 49.89999, -119.46332 ,'Sonora Park' ,'1603 Sonora Drive' ,'V1Y 8Z2' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (68, 49.883221, -119.457621 ,'Parkinson Recreation Park' ,'1800 Parkinson Way' ,'V1Y 4P9' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (69, 49.892305, -119.404228 ,'Ben Lee Park' ,'900 Houghton Road' ,'V1X 2C8' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (70, 49.8621, -119.464 ,'Munson Pond Park' ,'3070 Burtch Road' ,'V1W 5G2' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (71, 49.891157, -119.537474 ,'Lombardy Park' ,'18 Clement Avenue' ,'V1Y 9R8' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (72, 49.848823, -119.486428 ,'Rotary Beach Park' ,'3736 Lakeshore Road' ,'V1W 3K3' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (73, 49.848907, -119.486017 ,'Jack Robertson Memorial Park' ,'1655 Willow Crescent' ,'V1Y 4K6' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (74, 49.87914, -119.477265 ,'Millbridge Park' ,'1962 Gordon Drive' ,'V1Y 3C2' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (75, 49.89059, -119.39047 ,'Rutland Centennial Park' ,'1008 Shepherd Road' ,'V1X 2C4' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (76, 49.86802, -119.49538 ,'Kinsmen Park' ,'2600 Abbott Street' ,'V1Y 1G4' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (77, 49.872537, -119.497013 ,'Strathcona Park' ,'2290 Abbott Street' ,'V1Y 1E3' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (78, 49.90396, -119.3904 ,'Chichester Wetland Park' ,'250 Sumac Road W' ,'V1X 6W8' ,'Kelowna' ,'Canada' ,'Park');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (79, 49.880315, -119.534393, 'Landmark Cinemas 8 Xtreme West Kelowna', '525 Highway 97 South', 'V1Z 4C9', 'West Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (80, 49.901305, -119.406501, 'Landmark Cinemas Grand 10 Kelowna', '948 McCurdy Road East #110', 'V1X 2P7', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (81, 49.88511, -119.49438, 'Blue Gator Bar & Grill', '441 Lawrence Ave', 'V1Y 6L5', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (82, 49.891581, -119.49491, 'Rotary Centre for the Arts', '421 Cawston Ave', 'V1Y 6Z1', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (83, 49.888346, -119.496475, 'Kelowna Community Theatre', '1375 Water St', 'V1Y 0B4', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (84, 49.886175, -119.493138, 'The Grateful Fed Pub & Restaurant', '509 Bernard Ave', 'V1Y 8R2', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (85, 49.891361, -119.496755, 'Lake City Casino Kelowna', '1300 Water St', 'V1Y 9P3', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (86, 49.882166, -119.464329, 'Dakoda''s Sports Bar & Grill', '1574 Harvey Ave', 'V1Y 6G2', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (87, 49.83177, -119.623497, 'Landmark Cinemas Encore West Kelowna', '3645 Gosset Rd #200', 'V4T 2N8', 'West Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (88, 49.88392, -119.4974, 'Festivals Kelowna', '260 Harvey Ave', 'V1Y 7S5', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (89, 49.88571, -119.49911, 'Okanagan Express Entertainment Limousine Services', '1585 Abbott St', 'V1Y 1A8', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (90, 49.87312, -119.36566, 'Kelowna Dragon Boat Club', '1270 Toovey Road', 'V1X 6R4', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (91, 49.876645, -119.463836, 'Chances Kelowna', '1585 Springfield Rd', 'V1Y 5V5', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (92, 49.88974, -119.49305, 'Kelowna Actors Studio', '1379 Ellis St', 'V1Y 1Z9', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (93, 49.88497, -119.48891, 'Tourism Kelowna', '1626 Richter St #214', 'V1Y 2M3', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (94, 49.88565, -119.49732, 'LIQUID ZOO', '274 Lawrence Ave', 'V1Y 6L3', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (95, 49.889355, -119.496755, 'Rose''s Waterfront Pub', '1352 Water St', 'V1Y 9P4', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (96, 49.900273, -119.409497, 'Heights Nightclub', '#100 - 2789 Hwy 97N', 'V1X4J8', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (97, 49.888799, -119.38567, 'HQ49', '165 BC-33', 'V1X 2A1', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (98, 49.879398, -119.460594, 'Gateway Casinos & Entertainment Ltd Corporate Office', '1708 Dolphin Ave #905', 'V1Y 9S4', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (99, 49.884888, -119.493714, 'Yamas Taverna', '1630 Ellis St', 'V1Y 8L1', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (100, 49.893221, -119.496017, 'Prospera Place', '1223 Water Street', 'V1Y 9V1', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (101, 49.885205, -119.496241, 'Wine & Art Piano Bar', '315 Lawrence Ave', 'V1Y 6L4', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (102, 49.886721, -119.437615, 'Rusty''s Steakhouse', '1525 Dilworth Dr', 'V1Y 9N5', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (103, 49.87797, -119.45893, 'OK Corral & Cabaret', '1978 Kirschner Rd', 'V1Y 4N6', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (104, 49.88655, -119.424509, 'EXIT Kelowna', '2453 Hwy 97 N', 'V1X 4J2', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (105, 49.885538, -119.436268, 'Ballet Kelowna', '2283 Leckie Rd', 'V1X 6Y5', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (106, 49.892721, -119.493724, 'BNA Brewing Co', '1250 Ellis St', 'V1Y 1Z4', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (107, 49.904683, -119.406023, 'Scandia Golf & Games', '2898 Hwy 97 N', 'V1X 5C1', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (108, 49.86565, -119.49231, 'Airwaves Music DJs', '467 Osprey Ave', 'V1Y 5A3', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (109, 49.88962, -119.42323, 'Avalon Event Rentals', '2-1660 Powick Rd', 'V1X 7G5', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (110, 49.82142, -119.383355, 'Myra Canyon Adventure Park', '4429 June Springs Rd', 'V1W 4C7', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (111, 49.85013, -119.61309, 'Kelowna Magician & Entertainer Leif David - Ali K. Zam Productions', '3155 Sandstone Dr', 'V4T 1S8', 'West Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (112, 49.885976, -119.422716, 'Play It Again Sports', '1778 Baron Rd', 'V1X 7G9', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (113, 49.886263, -119.422258, 'Deschners Danceforce', '1774 Baron Rd', 'V1X 7G9', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (114, 49.950791, -119.349944, 'Sunset Ranch Golf & Country Club', '5101 Upper Booth Rd', 'V1X 7V8', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (115, 49.8795, -119.446878, 'Bowie''s Marketing & Entrtn', '2142 Vasile Rd', 'V1Y 6H5', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (116, 49.90776, -119.39905, 'Beyond The Crux Climbing Gym Inc.', '685 Finns Rd #5', 'V1X 5B7', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (117, 49.87772, -119.45191, 'Planet Lazer', '1960 Springfield Rd', 'V1Y 5V7', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (118, 49.901305, -119.406501, 'Energyplex Family Recreation Centre Kelowna', '948 McCurdy Rd', 'V1X 2P7', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (119, 49.875725, -119.548605, 'Safari Ridge Adventure Park', '2216 Horizon Dr', 'V1Z 3L4', 'West Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (120, 49.88315, -119.45607, 'Parkinson Recreation Park', '800 Parkinson Way', 'V1Y 8G7', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (121, 49.899785, -119.381075, 'Kelowna Family YMCA', '375 Hartman Rd', 'V1X 2M9', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (122, 49.836363, -119.482343, 'H2O Adventure + Fitness Centre', '4075 Gordon Dr', 'V1W 5J2', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (123, 49.881755, -119.456327, 'Kelowna Pool Tables & Games Room Furniture', '1823 Harvey Ave #101', 'V1Y 6G4', 'Kelowna', 'Canada', 'Entertainment');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (124, 49.8496977, -119.4719015, 'Father Pandosy Mission', '3685 Benvoulin Road', 'V1W 4M7', 'Kelowna', 'Canada', 'Historical/Museum');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (125, 49.8708961, -119.4793798, 'Guisachan House', '1060 Cameron Ave', 'V1W 4T2', 'Kelowna', 'Canada', 'Historical/Museum');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (126, 49.78625, -119.374947, 'Myra Trestles', 'Kettle Valley Rail Trail', 'V0H 1N0', 'Kelowna', 'Canada', 'Historical/Museum');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (127, 49.8731456, -119.4453749, 'Benvoulin Heritage Church', '2279 Benvoulin Rd', 'V1W 2C8', 'Kelowna', 'Canada', 'Historical/Museum');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (128, 49.891494, -119.4961623, 'The Laurel Building', '1304 Ellis St', 'V1Y 1Z9', 'Kelowna', 'Canada', 'Historical/Museum');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (129, 49.891494, -119.4961623, 'The Laurel Building', '1304 Ellis St', 'V1Y 1Z9', 'Kelowna', 'Canada', 'Historical/Museum');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (130, 49.9042721, -119.4927011, 'Knox Mountain', '621 Broadway Ave', 'V1Y 7M8', 'Kelowna', 'Canada', 'Hiking Trail');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (131, 49.9734393, -119.3670004, 'Mill Creek Regional Park', 'Spencer Rd', 'V1X 7T7', 'Kelowna', 'Canada', 'Hiking Trail');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (132, 49.961605, -119.434219, 'Stephens Coyote Ridge Regional Park', '2388 Glenmore Road North', 'V1V 2C5', 'Kelowna', 'Canada', 'Hiking Trail');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (133, 49.929554, -119.518974, 'Bear Creek Provincial Park', '107 Westside Rd', 'V0E 1S0', 'Kelowna', 'Canada', 'Hiking Trail');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (134, 49.924342, -119.476902, 'Paul''s Tomb', 'null', 'null', 'Kelowna', 'Canada', 'Hiking Trail');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (135, 49.776985, -119.525223, 'Cedar Mountain Regional Park', '5627 Lakeshore Road', 'V1W 4J4', 'Kelowna', 'Canada', 'Hiking Trail');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (136, 49.8791673, -119.566542, 'Rose Valley Regional Park', '1805 Westlake Rd', 'V1Z 3H9', 'West Kelowna', 'Canada', 'Hiking Trail');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (137, 49.848709, -119.545891, 'Kalamoir Park', '2777 Casa Loma Road', 'V1Z 1T6', 'West Kelowna', 'Canada', 'Hiking Trail');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (138, 49.847649, -119.351768, 'Scenic Canyon Regional Park', '3842 Senger Road', 'V1W 4S4', 'Kelowna', 'Canada', 'Hiking Trail');
INSERT INTO Attraction(aid, lat, lng, name, address, zip, city, country, type) VALUES (139, 50.041655, -119.278956, 'Wrinkly Face Provicinal Park', 'null', 'V1P 1A2', 'Kelowna', 'Canada', 'Hiking Trail');
