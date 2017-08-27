CREATE DATABASE IF NOT EXISTS rummy;
USE rummy;

CREATE TABLE IF NOT EXISTS stats (
  stat_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  wins INT NOT NULL,
  losses INT NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY fk_user(user_id)
  REFERENCES users(user_id)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

INSERT INTO stats (wins, losses, user_id) VALUES(3, 2, 1);
INSERT INTO stats (wins, losses, user_id) VALUES(2, 3, 2);
INSERT INTO stats (wins, losses, user_id) VALUES(1, 1, 3);
INSERT INTO stats (wins, losses, user_id) VALUES(7, 5, 4);
INSERT INTO stats (wins, losses, user_id) VALUES(2, 2, 5);
INSERT INTO stats (wins, losses, user_id) VALUES(6, 2, 6);
