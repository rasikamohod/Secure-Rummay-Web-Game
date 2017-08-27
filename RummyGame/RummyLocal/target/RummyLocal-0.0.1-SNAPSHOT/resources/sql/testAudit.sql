CREATE DATABASE IF NOT EXISTS rummy;
USE rummy;

CREATE TABLE IF NOT EXISTS audit (
  audit_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  game_id INT NOT NULL,
  user_id INT NOT NULL,
  r_action VARCHAR(10) NOT NULL,
  r_cards VARCHAR(20) NOT NULL,
  r_status VARCHAR(10) NOT NULL,
  r_message VARCHAR(100) NOT NULL
);

INSERT INTO audit (game_id, user_id, r_action, r_cards, r_status, r_message) VALUES(3, 1, "MELD", "2C,3C,4C", "success", "Created Meld");
INSERT INTO audit (game_id, user_id, r_action, r_cards, r_status, r_message) VALUES(3, 1, "LAY", "AC", "success", "Lay AC on Meld 2C,3C,4C");
INSERT INTO audit (game_id, user_id, r_action, r_cards, r_status, r_message) VALUES(3, 1, "DRAW", "6C", "success", "Created Meld");
INSERT INTO audit (game_id, user_id, r_action, r_cards, r_status, r_message) VALUES(3, 1, "DISCARD", "5D", "success", "Discarded Card 2C");
INSERT INTO audit (game_id, user_id, r_action, r_cards, r_status, r_message) VALUES(3, 1, "DRAW", "5C", "success", "5C Drawn from pile");
INSERT INTO audit (game_id, user_id, r_action, r_cards, r_status, r_message) VALUES(3, 1, "LAY", "5C", "success", "Laid 5C on 2C,3C,4C");
INSERT INTO audit (game_id, user_id, r_action, r_cards, r_status, r_message) VALUES(3, 1, "DISCARD", "2H", "success", "Discarded 2H");
INSERT INTO audit (game_id, user_id, r_action, r_cards, r_status, r_message) VALUES(3, 1, "COMPLETE", "", "success", "Discard All Card");
