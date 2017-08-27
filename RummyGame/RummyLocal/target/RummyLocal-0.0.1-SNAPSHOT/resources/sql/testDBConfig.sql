DROP DATABASE testrummy;
CREATE DATABASE IF NOT EXISTS testrummy;
USE testrummy;

CREATE TABLE IF NOT EXISTS users (
  userid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL
);

INSERT INTO users (username, email) VALUES("testuser", "testuser@example.com");
INSERT INTO users (username, email) VALUES("testusera", "testusera@example.com");
INSERT INTO users (username, email) VALUES("testuserb", "testuserb@example.com");
INSERT INTO users (username, email) VALUES("testuserc", "testuserc@example.com");
INSERT INTO users (username, email) VALUES("testuserd", "testuserd@example.com");
INSERT INTO users (username, email) VALUES("testusere", "testusere@example.com");

CREATE TABLE IF NOT EXISTS testrummy.games (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  gameid INT NOT NULL,
  userid INT NOT NULL,
  win VARCHAR(4),
  FOREIGN KEY fk_user(userid)
  REFERENCES users(userid)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

INSERT INTO testrummy.games (gameid, userid, win) VALUES(1, 1, "yes");
INSERT INTO testrummy.games (gameid, userid, win) VALUES(1, 3, "no");
INSERT INTO testrummy.games (gameid, userid, win) VALUES(2, 2, "yes");
INSERT INTO testrummy.games (gameid, userid, win) VALUES(2, 4, "no");
INSERT INTO testrummy.games (gameid, userid, win) VALUES(3, 3, "no");
INSERT INTO testrummy.games (gameid, userid, win) VALUES(3, 5, "yes");
INSERT INTO testrummy.games (gameid, userid) VALUES(4, 1);
INSERT INTO testrummy.games (gameid, userid) VALUES(4, 6);
INSERT INTO testrummy.games (gameid, Userid) VALUES(5, 2);

CREATE TABLE IF NOT EXISTS stats (
  statid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  wins INT NOT NULL,
  losses INT NOT NULL,
  userid INT NOT NULL,
  FOREIGN KEY fk_user(userid)
  REFERENCES users(userid)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

INSERT INTO stats (wins, losses, userid) VALUES(3, 2, 1);
INSERT INTO stats (wins, losses, userid) VALUES(2, 3, 2);
INSERT INTO stats (wins, losses, userid) VALUES(1, 1, 3);
INSERT INTO stats (wins, losses, userid) VALUES(7, 5, 4);
INSERT INTO stats (wins, losses, userid) VALUES(2, 2, 5);
INSERT INTO stats (wins, losses, userid) VALUES(6, 2, 6);

CREATE TABLE IF NOT EXISTS audit (
  auditid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  gameid INT NOT NULL,
  userid INT NOT NULL,
  raction VARCHAR(10) NOT NULL,
  rcards VARCHAR(20) NOT NULL,
  rstatus VARCHAR(10) NOT NULL,
  rmessage VARCHAR(100) NOT NULL
);

INSERT INTO audit (gameid, userid, raction, rcards, rstatus, rmessage) VALUES(3, 1, "MELD", "2C,3C,4C", "success", "Created Meld");
INSERT INTO audit (gameid, userid, raction, rcards, rstatus, rmessage) VALUES(3, 1, "LAY", "AC", "success", "Lay AC on Meld 2C,3C,4C");
INSERT INTO audit (gameid, userid, raction, rcards, rstatus, rmessage) VALUES(3, 1, "DRAW", "6C", "success", "Created Meld");
INSERT INTO audit (gameid, userid, raction, rcards, rstatus, rmessage) VALUES(3, 1, "DISCARD", "5D", "success", "Discarded Card 2C");
INSERT INTO audit (gameid, userid, raction, rcards, rstatus, rmessage) VALUES(3, 1, "DRAW", "5C", "success", "5C Drawn from pile");
INSERT INTO audit (gameid, userid, raction, rcards, rstatus, rmessage) VALUES(3, 1, "LAY", "5C", "success", "Laid 5C on 2C,3C,4C");
INSERT INTO audit (gameid, userid, raction, rcards, rstatus, rmessage) VALUES(3, 1, "DISCARD", "2H", "success", "Discarded 2H");
INSERT INTO audit (gameid, userid, raction, rcards, rstatus, rmessage) VALUES(3, 1, "COMPLETE", "", "success", "Discard All Card");
