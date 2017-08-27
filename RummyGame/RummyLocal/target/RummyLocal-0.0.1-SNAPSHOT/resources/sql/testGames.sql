CREATE DATABASE IF NOT EXISTS rummy;
USE rummy;

CREATE TABLE IF NOT EXISTS games (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  game_id INT NOT NULL,
  user_id INT NOT NULL,
  win VARCHAR(4) NOT NULL,
  FOREIGN KEY fk_user(user_id)
  REFERENCES users(user_id)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

INSERT INTO games (game_id, user_id, win) VALUES(1, 1, "yes");
INSERT INTO games (game_id, user_id, win) VALUES(1, 3, "no");
INSERT INTO games (game_id, user_id, win) VALUES(2, 2, "yes");
INSERT INTO games (game_id, user_id, win) VALUES(2, 4, "no");
INSERT INTO games (game_id, user_id, win) VALUES(3, 3, "no");
INSERT INTO games (game_id, user_id, win) VALUES(3, 5, "yes");
