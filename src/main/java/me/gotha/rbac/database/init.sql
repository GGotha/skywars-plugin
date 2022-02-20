CREATE TABLE IF NOT EXISTS players (
       id INTEGER PRIMARY KEY AUTOINCREMENT,
       name VARCHAR NOT NULL,
       active BOOLEAN NOT NULL,
       created_at DATETIME NOT NULL,
       updated_at DATETIME
);


CREATE TABLE IF NOT EXISTS minigames (
     id INTEGER PRIMARY KEY AUTOINCREMENT,
     name VARCHAR NOT NULL,
     active BOOLEAN NOT NULL,
     created_at DATETIME NOT NULL,
     updated_at DATETIME
);


CREATE TABLE IF NOT EXISTS lobbies (
       id INTEGER PRIMARY KEY AUTOINCREMENT,
       id_minigame int NOT NULL,
       level_name VARCHAR NOT NULL,
       active BOOLEAN NOT NULL,
       created_at DATETIME NOT NULL,
       updated_at DATETIME,
       FOREIGN KEY (id_minigame) REFERENCES minigames(id)
    );

CREATE TABLE IF NOT EXISTS lobby_players (
     id INTEGER PRIMARY KEY AUTOINCREMENT,
     id_lobby int NOT NULL,
     id_player int NOT NULL,
     active BOOLEAN NOT NULL,
     created_at DATETIME NOT NULL,
     updated_at DATETIME,
     FOREIGN KEY (id_lobby) REFERENCES lobbies(id)
    FOREIGN KEY (id_player) REFERENCES players(id)
    );

INSERT INTO minigames VALUES (1, 'Skywars', true, DATE('now'), null);
INSERT INTO minigames VALUES (2, 'Bedwars', false, DATE('now'), null);