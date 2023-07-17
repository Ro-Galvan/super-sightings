DROP DATABASE IF EXISTS superSightingsDBtest;
CREATE DATABASE superSightingsDBtest;
USE superSightingsDBtest;

DROP TABLE IF EXISTS superpowers;
CREATE TABLE superpowers(
	superpowerID INT AUTO_INCREMENT,
    superpowerName VARCHAR(50) NOT NULL,
   PRIMARY KEY (superpowerID)
);

DROP TABLE IF EXISTS super;
CREATE TABLE super(
	superID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	superName VARCHAR(25) NOT NULL,
    superDesc VARCHAR(255) NOT NULL,
    isEvil BOOLEAN DEFAULT false NOT NULL,
    superpowerID INT NOT NULL,
	FOREIGN KEY (superpowerID) REFERENCES superpowers(superpowerID)
);

DROP TABLE IF EXISTS location;
CREATE TABLE location(
	locationID INT AUTO_INCREMENT PRIMARY KEY,
	locationName VARCHAR(50) NOT NULL,
	locationDes VARCHAR(255) NOT NULL,
	locationAddress VARCHAR(255) NOT NULL,
	latitude VARCHAR(50) NOT NULL,
	longitude VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS sighting;
CREATE TABLE sighting(
	sightingID INT PRIMARY KEY AUTO_INCREMENT,
    dateSighted TIMESTAMP,
    locationID INT NOT NULL,
    superID INT NOT NULL,
    FOREIGN KEY fk_Location (locationID) REFERENCES location(locationID),
    FOREIGN KEY fk_Character (superID) REFERENCES super(superID)
);

DROP TABLE IF EXISTS org;
CREATE TABLE org(
	orgID INT PRIMARY KEY AUTO_INCREMENT,
	orgName VARCHAR(50) NOT NULL,
	orgDes TEXT NOT NULL,
	orgAddress VARCHAR(255) NOT NULL,
	orgNumber CHAR(12) NOT NULL,
	isEvil BOOLEAN
);

DROP TABLE IF EXISTS superOrg;
CREATE TABLE superOrg (
	superOrgID INT PRIMARY KEY  AUTO_INCREMENT,
	superID INT NOT NULL,
	orgID INT NOT NULL,
	FOREIGN KEY (superID) REFERENCES super(superID),
	FOREIGN KEY (orgID) REFERENCES org(orgID)
);