DROP DATABASE IF EXISTS alinity;
CREATE DATABASE alinity;
USE alinity;


-- creating the user table
DROP TABLE IF EXISTS User;
CREATE TABLE User
(
  userId INT AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(20) NOT NULL,
  birthday DATE NOT NULL,
  email VARCHAR(50) NOT NULL,
  PRIMARY KEY (userId)
);

-- creating the genre table
DROP TABLE IF EXISTS Genre;
CREATE TABLE Genre
(
  genreId INT AUTO_INCREMENT,
  genreName VARCHAR(50) NOT NULL,
  PRIMARY KEY (genreId)
);

-- creating the record_label table
DROP TABLE IF EXISTS Record_Label;
CREATE TABLE Record_Label
(
  recordLabelId INT AUTO_INCREMENT,
  recordLabelName VARCHAR(50) NOT NULL,
  dateCreated DATE NOT NULL,
  labelInfo VARCHAR(200) NOT NULL,
  PRIMARY KEY (recordLabelId)
);

-- creating the platform table
DROP TABLE IF EXISTS Platform;
CREATE TABLE Platform
(
  platformId INT AUTO_INCREMENT,
  platformName VARCHAR(50) NOT NULL,
  platformInfo VARCHAR(200) NOT NULL,
  PRIMARY KEY (platformId)
);

-- creating the artist table
DROP TABLE IF EXISTS Artist;
CREATE TABLE Artist
(
  artistId INT AUTO_INCREMENT,
  artistName VARCHAR(50) NOT NULL,
  artistInfo VARCHAR(200) NOT NULL,
  recordLabelId INT NOT NULL,
  PRIMARY KEY (artistId),
  CONSTRAINT FOREIGN KEY (recordLabelId) REFERENCES Record_Label(recordLabelId)
);

-- creating the album table
DROP TABLE IF EXISTS Album;
CREATE TABLE Album
(
  albumId INT AUTO_INCREMENT,
  albumName VARCHAR(50) NOT NULL,
  albumInfo VARCHAR(200) NOT NULL,
  releaseDate DATE NOT NULL,
  artistId INT NOT NULL,
  genreId INT NOT NULL,
  PRIMARY KEY (albumId),
  CONSTRAINT FOREIGN KEY (artistId) REFERENCES Artist(artistId),
  CONSTRAINT FOREIGN KEY (genreId) REFERENCES Genre(genreId)
);


-- creating the award table
DROP TABLE IF EXISTS Award;
CREATE TABLE Award
(
  awardId INT AUTO_INCREMENT,
  awardName VARCHAR(50) NOT NULL,
  awardInfo VARCHAR(200) NOT NULL,
  artistId INT NOT NULL,
  PRIMARY KEY (awardId),
  CONSTRAINT FOREIGN KEY (artistId) REFERENCES Artist(artistId)
);

-- creating the saved_artist table
CREATE TABLE Saved_Artist
(
  userId INT NOT NULL,
  artistId INT NOT NULL,
  PRIMARY KEY (userId, artistId),
  CONSTRAINT FOREIGN KEY (userId) REFERENCES User(userId),
  CONSTRAINT FOREIGN KEY (artistId) REFERENCES Artist(artistId)
);


-- creating the saved_album table
DROP TABLE IF EXISTS Saved_Album;
CREATE TABLE Saved_Album
(
  userId INT NOT NULL,
  albumId INT NOT NULL,
  PRIMARY KEY (userId, albumId),
  CONSTRAINT FOREIGN KEY (userId) REFERENCES User(userId),
  CONSTRAINT FOREIGN KEY (albumId) REFERENCES Album(albumId)
);

-- creating the artist_genre table
DROP TABLE IF EXISTS Artist_Genre;
CREATE TABLE Artist_Genre
(
  artistId INT NOT NULL,
  genreId INT NOT NULL,
  PRIMARY KEY (artistId, genreId),
  CONSTRAINT FOREIGN KEY (artistId) REFERENCES Artist(artistId),
  CONSTRAINT FOREIGN KEY (genreId) REFERENCES Genre(genreId)
);

-- creating the available_on table
DROP TABLE IF EXISTS Available_On;
CREATE TABLE Available_On
(
  artistId INT NOT NULL,
  platformId INT NOT NULL,
  PRIMARY KEY (artistId, platformId),
  CONSTRAINT FOREIGN KEY (artistId) REFERENCES Artist(artistId),
  CONSTRAINT FOREIGN KEY (platformId) REFERENCES Platform(platformId)
);

-- creating the song table
DROP TABLE IF EXISTS Song;
CREATE TABLE Song
(
  songId INT AUTO_INCREMENT,
  songName VARCHAR(50) NOT NULL,
  songDuration INT NOT NULL,
  albumId INT,
  artistId INT NOT NULL,
  genreId INT NOT NULL,
  PRIMARY KEY (songId),
  CONSTRAINT FOREIGN KEY (albumId) REFERENCES Album(albumId),
  CONSTRAINT FOREIGN KEY (artistId) REFERENCES Artist(artistId),
  CONSTRAINT FOREIGN KEY (genreId) REFERENCES Genre(genreId)
);

-- creating the saved_songs table
DROP TABLE IF EXISTS Saved_Songs;
CREATE TABLE Saved_Songs
(
  userId INT NOT NULL,
  songId INT NOT NULL,
  PRIMARY KEY (userId, songId),
  CONSTRAINT FOREIGN KEY (userId) REFERENCES User(userId),
  CONSTRAINT FOREIGN KEY (songId) REFERENCES Song(songId)
);



INSERT INTO User (username, password, birthday, email) VALUES('mjr8741', '12345', '1999-12-12', 'mjr8741@rit.edu');
INSERT INTO User (username, password, birthday, email) VALUES('lf7864', '54321', '1999-06-29', 'lf7894@rit.edu');
SELECT * FROM User;
INSERT INTO Genre (genreName) VALUES ('Rap'), ('Metal');
INSERT INTO Record_Label(recordLabelName, dateCreated, labelInfo) VALUES ('Test', '1991-03-21', 'Death Row Records was formed by some of the largest names in rap, such as Dr. DRE and 
Suge Knight'), ('Elektra Records', '1950-06-12', 'Elektra records is one of the oldest standing music record companies');
SELECT * FROM Record_Label;
INSERT INTO Platform(platformName, platformInfo) VALUES ('Spotify', 'Spotify is the leading music streaming platform in the world');
INSERT INTO Artist (artistName, artistInfo, recordLabelId) VALUES
('Eminem', 'M&M', 1),
('Metallica', 'Best Metal band!', 2),
('Snoop Dogg', 'Highest in the room', 1);
INSERT INTO Album (albumName, albumInfo, releaseDate, artistId, genreId) VALUES ('Gin and Juice', 'This was one of Snoop`s best selling albums', '2001-10-03', 3, 1);
INSERT INTO Song (songName, songDuration, albumId, artistId, genreId) VALUES ('CEO', '200', 1, 3, 1);
INSERT INTO Award (awardName, awardInfo, artistId) VALUES ('Best Rap Single', 'This song represents the best in rap of the year', 3);

INSERT INTO Saved_Artist (userId, artistId) VALUES (2, 3);
select * from Saved_Artist;
SELECT * FROM Artist;

SELECT * FROM Song NATURAL JOIN Artist;

SELECT User.userId, Artist.artistName FROM
Saved_Artist NATURAL JOIN Artist
NATURAL JOIN User
WHERE User.userId = '2';