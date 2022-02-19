CREATE TABLE IF NOT EXISTS players (
       name VARCHAR NOT NULL,
       active BOOLEAN NOT NULL,
       created_at DATETIME NOT NULL,
       updated_at DATETIME
);


CREATE TABLE IF NOT EXISTS minigames (
       name VARCHAR NOT NULL,
       active BOOLEAN NOT NULL,
       created_at DATETIME NOT NULL,
       updated_at DATETIME
);


CREATE TABLE IF NOT EXISTS lobbies (
       id_minigame int NOT NULL,
       active BOOLEAN NOT NULL,
       created_at DATETIME NOT NULL,
       updated_at DATETIME,
       FOREIGN KEY (id_minigame) REFERENCES minigames(id)
);

CREATE TABLE IF NOT EXISTS lobby_players (
       id_lobby int NOT NULL,
       id_player int NOT NULL,
       active BOOLEAN NOT NULL,
       created_at DATETIME NOT NULL,
       updated_at DATETIME,
       FOREIGN KEY (id_lobby) REFERENCES lobbies(id)
       FOREIGN KEY (id_player) REFERENCES players(id)
);

INSERT INTO minigames VALUES ('Skywars', true, DATE('now'), null);
INSERT INTO minigames VALUES ('Bedwars', false, DATE('now'), null);