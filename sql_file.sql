DROP SCHEMA IF EXISTS gonature;

CREATE SCHEMA gonature;
use gonature;

CREATE TABLE visitors (
    ID VARCHAR(50) PRIMARY KEY,
    Email VARCHAR(100),
    Telephone VARCHAR(15),
    isInstructor VARCHAR(3)
);

CREATE TABLE instructor (
    ID VARCHAR(50) PRIMARY KEY,
    Email VARCHAR(100),
    Telephone VARCHAR(15),
    activated VARCHAR(3)
);

CREATE TABLE parks (
    ParkName VARCHAR(30) PRIMARY KEY,
    gap INT,
    EstimatedTime INT,
	capacity INT,
    visitorsWithOrder INT,
    visitorsWithoutOrder INT
);

CREATE TABLE reservations (
    Type VARCHAR(20),
    NumberOfVisitors INT,
    ReservationDate DATE,
    Hour VARCHAR(20),
    Minute VARCHAR(20),
    Park VARCHAR(20),
    Telephone VARCHAR(15),
    Email VARCHAR(100),
    ReservationID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    visitorID VARCHAR(50), 
    isConfirmed boolean,
    InvitedInAdvance boolean,
    payed boolean,
    processed VARCHAR(10),
	FOREIGN KEY (visitorID) REFERENCES visitors(Id),
    FOREIGN KEY (Park) REFERENCES parks(ParkName)
);

CREATE TABLE users (
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    username VARCHAR(30) PRIMARY KEY,
    password VARCHAR(30),
    workerNumber INT,
    Email VARCHAR(100),
    role VARCHAR(50),
    parkWork VARCHAR(30),
    FOREIGN KEY (parkWork) REFERENCES parks(ParkName)
);


CREATE TABLE cancellationsReports (
	Type VARCHAR(20),
    Date DATE,
    Park VARCHAR(30),
    reservationId INT PRIMARY KEY,
    ReservationIdCancelled VARCHAR(10),
    ReservationIdNotActivated VARCHAR(10),
    FOREIGN KEY (Park) REFERENCES parks(parkName)
);

CREATE TABLE numOfVisitorsReports (
    month VARCHAR(10),
    year VARCHAR(10),
	groupNumbers VARCHAR(10),
    individuals VARCHAR(10),
    Park VARCHAR(30),
    madeBy VARCHAR(30),
	PRIMARY KEY(month, year, madeBy),
    FOREIGN KEY (madeBy) REFERENCES users(username),
    FOREIGN KEY (Park) REFERENCES parks(parkName)
);

CREATE TABLE NotFullReports(
    month VARCHAR(10),
    year VARCHAR(10),
    amount INT,
	Park VARCHAR(30),
    madeBy VARCHAR(30),
	PRIMARY KEY(month, year, madeBy),
    FOREIGN KEY (madeBy) REFERENCES users(username),
    FOREIGN KEY (Park) REFERENCES parks(parkName)
);

CREATE TABLE waiting_list (
    Type VARCHAR(20),
    NumberOfVisitors INT,
    ReservationDate DATE,
    Hour VARCHAR(20),
    Minute VARCHAR(20),
    Park VARCHAR(20),
    Telephone VARCHAR(15),
    Email VARCHAR(100),
    Queue int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    visitorID VARCHAR(50), 
    isConfirmed boolean,
    InvitedInAdvance boolean,
    payed boolean,
    processed VARCHAR(10),
	FOREIGN KEY (visitorID) REFERENCES visitors(ID),
    FOREIGN KEY (Park) REFERENCES parks(ParkName)
);

CREATE TABLE requested_changes (
    parkName VARCHAR(20),
    madeBy VARCHAR(20),
	variableToChange VARCHAR(50) ,
	newValue VARCHAR(50),
	PRIMARY KEY(parkName,variableToChange),
	FOREIGN KEY (parkName) REFERENCES parks(ParkName),
	FOREIGN KEY (madeBy) REFERENCES users(username)
);

CREATE TABLE processedRes(
    NumberOfVisitors INT,
    Park VARCHAR(20),
    ReservationID INT PRIMARY KEY,
    enterHour INT,
    enterMin INT,
    exitHour INT,
    exitMin INT,
    date DATE,
    reservationType VARCHAR(20),
    FOREIGN KEY (Park) REFERENCES parks(parkName)
);

CREATE TABLE full_park(
	parkName VARCHAR(20),
    year VARCHAR(10),
    month VARCHAR(10), 
    day VARCHAR(10),
    hour INT,
    FOREIGN KEY (parkName) REFERENCES parks(parkName)
);

CREATE TABLE inbox(
msgId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
visitorID VARCHAR(50),
title VARCHAR(20),
content VARCHAR(100),
FOREIGN KEY (visitorID) REFERENCES visitors(Id)
);

INSERT INTO visitors (ID, Email, Telephone, isInstructor) 
VALUES 
	(123456789, 'mbadashehade@gmail.com', '0523148812', '0'),
    (987654321, 'abdallah@gmail.com', '0585171611', '0');
    
INSERT INTO instructor (ID, Email, Telephone, activated) 
VALUES 
	(123456789, 'mbadashehade@gmail.com', '0523148812', '0'),
    (987654321, 'abdallah@gmail.com', '0585171611', '0'),
    (123456788, 'bolos@gmail.com', '0525983023', '0'),
    (987654324, 'Mayar@gmail.com', '0525817233', '0');
    
INSERT INTO parks (ParkName, gap, EstimatedTime, capacity,visitorsWithOrder,visitorsWithoutOrder)
VALUES('Park 1', 5, 4, 20, 0, 0),
		('Park 2', 5, 4, 20, 0, 0),
		('Park 3', 5, 4, 20, 0, 0);
        
 INSERT INTO full_park (parkName, year, month, day, hour)
VALUES
('Park 1', '2024', '2', '28', 15),
('Park 1', '2024', '2', '28', 16),
('Park 1', '2024', '2', '28', 17),
('Park 1', '2024', '2', '28', 12),
('Park 1', '2024', '2', '29', 15), -- new one
('Park 1', '2024', '2', '29', 11),
('Park 1', '2024', '2', '29', 20),
('Park 1', '2024', '2', '29', 19),
('Park 1', '2024', '2', '29', 17),
('Park 1', '2024', '2', '29', 14),
('Park 1', '2024', '2', '29', 13),-- 
('Park 1', '2024', '3', '28', 15),
('Park 1', '2024', '3', '28', 16),
('Park 1', '2024', '3', '28', 17),
('Park 1', '2024', '3', '28', 12),
('Park 1', '2024', '3', '29', 15), -- new one
('Park 1', '2024', '3', '29', 11),
('Park 1', '2024', '3', '29', 20),
('Park 1', '2024', '3', '29', 19),
('Park 1', '2024', '3', '29', 17),
('Park 1', '2024', '3', '29', 14),
('Park 1', '2024', '3', '29', 13),
('Park 1', '2024', '4', '1', 15), -- new one
('Park 1', '2024', '4', '1', 16),
('Park 1', '2024', '4', '1', 17),
('Park 1', '2024', '4', '1', 12),
('Park 1', '2024', '4', '1', 15),
--
('Park 2', '2024', '2', '28', 15),
('Park 2', '2024', '2', '28', 16),
('Park 2', '2024', '2', '28', 17),
('Park 2', '2024', '2', '28', 12),
('Park 2', '2024', '2', '29', 15), -- new one
('Park 2', '2024', '2', '29', 11),
('Park 2', '2024', '2', '19', 11),
('Park 2', '2024', '2', '29', 20),
('Park 2', '2024', '2', '29', 19),
('Park 2', '2024', '2', '29', 17),
('Park 2', '2024', '2', '29', 14),
('Park 2', '2024', '2', '29', 13),-- 
('Park 2', '2024', '3', '28', 15),
('Park 2', '2024', '3', '28', 16),
('Park 2', '2024', '3', '28', 17),
('Park 2', '2024', '3', '28', 12),
('Park 2', '2024', '3', '29', 15), -- new one
('Park 2', '2024', '3', '29', 11),
('Park 2', '2024', '3', '29', 20),
('Park 2', '2024', '3', '29', 19),
('Park 2', '2024', '3', '29', 17),
('Park 2', '2024', '3', '29', 14),
('Park 2', '2024', '3', '29', 13);       
        
INSERT INTO reservations (Type, NumberOfVisitors, ReservationDate, Hour, Minute, Park, Telephone, Email, visitorID, isConfirmed, InvitedInAdvance, payed, processed) 
VALUES
('Private', 4, '2024-3-31', '1', '00', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '-1'),
('Organized Group', 4, '2024-3-31', '1', '00', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '1', '-1'),
('Organized Group', 4, '2024-3-31', '1', '00', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '-1'),

('Private', 4, '2024-1-15', '10', '00', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '1'),
('Organized Group', 6, '2024-1-15', '11', '30', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '1'),
('Organized Group', 2, '2024-3-25', '14', '15', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '1'),
('Organized Group', 8, '2024-2-10', '9', '00', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '1'),
('Private', 3, '2024-1-5', '13', '45', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '1'),
--
('Private', 3, '2024-3-30', '11', '00', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '-1'),
('Private', 3, '2024-4-4', '11', '00', 'Park 1', '0525983023', 'asda@ada.com', '987654321', '1', '1', '0', '-1'),
('Private', 3, '2024-3-30', '11', '00', 'Park 1', '0525983023', 'asda@ada.com', '987654321', '1', '1', '0', '1'),
('Private', 3, '2024-4-1', '11', '00', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '-1'),
('Private', 3, '2024-4-3', '11', '00', 'Park 1', '0525983023', 'asda@ada.com', '987654321', '1', '1', '0', '-1'),
('Private', 7, '2024-5-5', '11', '00', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '-1'),
('Private', 8, '2024-5-5', '11', '00', 'Park 1', '0525983023', 'asda@ada.com', '123456789', '1', '1', '0', '-1');

INSERT INTO waiting_list (Type, NumberOfVisitors, ReservationDate, Hour, Minute, Park, Telephone, Email, visitorID, isConfirmed, InvitedInAdvance, payed, processed)
VALUES
('Private', 4, '2024-5-5', '11', '00', 'Park 1', '0525983023', 'asda@ada.com', '987654321', '1', '1', '0', '-1');


    
INSERT INTO users (firstName, lastName,username, password,workerNumber,Email,role,parkWork) 
VALUES 
	('user1', 'lname', 'park manager', '123','222222','user1@mail.com','parkManager','Park 1'),
    ('user2', 'lname', 'dep manager', '123','1111111','user2@mail.com','depManager','Park 1'),
    ('user3', 'lname', 'entry manager', '123','1111112','user3@mail.com','entryManager','Park 1'),
    ('user4', 'lname', 'service agent', '123','1111113','user4@mail.com','serviceAgent','Park 1'),
	('user5', 'lname', 'user23', '123','222222','user5@mail.com','parkManager','Park 2'),
    ('user7', 'lname', 'user22', '123','1111113','user7@mail.com','entryManager','Park 2'),
    ('user8', 'lname', 'user33', '123','222222','user8@mail.com','parkManager','Park 3'),
    ('user10', 'lname', 'user32', '123','1111113','user10@mail.com','entryManager','Park 3');


INSERT INTO cancellationsReports (Type, Date, Park, reservationId, ReservationIdCancelled,ReservationIdNotActivated) 
VALUES  ('Private', '2024-03-25', 'Park 1', 406, 'Yes','No'),
     	('Private', '2024-03-23', 'Park 1', 407, 'No','Yes'),
    	('Private', '2024-03-25', 'Park 1', 408, 'Yes','No'),
        ('Organized Group', '2024-03-25', 'Park 1', 409, 'Yes','No'),
        ('Organized Group', '2024-03-23', 'Park 1', 401, 'No','Yes'),
        ('Private', '2024-03-25', 'Park 1', 402, 'Yes','No'),
        ('Organized Group', '2024-03-25', 'Park 1', 403, 'No','Yes'),
	    ('Organized Group', '2024-03-25', 'Park 1', 404, 'Yes','No'),
        ('Private', '2024-02-20', 'Park 1', 412, 'Yes','No'),
        ('Organized Group', '2024-02-20', 'Park 1', 413, 'No','Yes'),
	    ('Organized Group', '2024-02-20', 'Park 1', 414, 'Yes','No');


INSERT INTO NotFullReports (month, year, amount, Park, madeBy) 
VALUES ('January', '2024', 100, 'Park 1', 'park manager'),
     	('May', '2021', 105, 'Park 1', 'park manager'),
    	('June', '2023', 140, 'Park 2', 'park manager'),
		('July', '2023', 140, 'Park 2', 'park manager'),
		('August', '2023', 140, 'Park 2', 'park manager'),
		('September', '2023', 145, 'Park 2', 'park manager'),
        ('December', '2022', 200, 'Park 3', 'park manager');
        
  
INSERT INTO numOfVisitorsReports (month, year, groupNumbers, individuals, Park, madeBy) 
VALUES 
    ('1', '2024', '120', '100', 'Park 1', 'park manager'),
    ('2', '2023', '60', '200', 'Park 1', 'park manager'),
    ('3', '2022', '200', '40', 'Park 1', 'park manager'),
    ('4', '2023', '150', '125', 'Park 1', 'park manager'),
    ('5', '2024', '124', '152', 'Park 1', 'park manager');


INSERT INTO requested_changes (parkName, madeBy, variableToChange, newValue)
 VALUES
('Park 1', 'park manager', 'gap', '7'),
('Park 1', 'park manager', 'capacity', '30'),
('Park 1', 'park manager', 'EstimatedTime', '3');



INSERT INTO processedRes (NumberOfVisitors, Park, ReservationID, enterHour, enterMin, exitHour, exitMin, date, reservationType) 
VALUES 
(2, 'Park 1', 501, 8, 0, 11, 15, '2024-1-15', 'Private'),
(4, 'Park 1', 502, 10, 0, 12, 30, '2024-1-15', 'Private'),
(6, 'Park 1', 503, 11, 30, 15, 0, '2024-1-15', 'Organized Group'),
(15, 'Park 1', 504, 13, 30, 16, 20, '2024-1-15', 'Organized Group'),
(2, 'Park 1', 505, 14, 15, 16, 45, '2024-3-25', 'Organized Group'),
(8, 'Park 1', 506, 9, 0, 11, 30, '2024-2-10', 'Organized Group'),
(3, 'Park 1', 507, 13, 45, 15, 15, '2024-1-05', 'Private');