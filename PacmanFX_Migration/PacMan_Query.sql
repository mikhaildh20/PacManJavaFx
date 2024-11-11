CREATE DATABASE PacMan

USE PacMan


CREATE TABLE LeaderBoard(
	id			INT PRIMARY KEY IDENTITY,
	player		VARCHAR(7),
	difficulty	VARCHAR(15),
	score		INT,
	play_date	DATE
)

ALTER PROCEDURE sp_inputLeaderBoard
	@player		VARCHAR(7),
	@diff		VARCHAR(15),
	@score		INT
AS
BEGIN
	INSERT INTO LeaderBoard VALUES(@player,@diff,@score,GETDATE())
END

EXEC sp_inputLeaderBoard 'alif','EASY',300
EXEC sp_inputLeaderBoard 'galuh','EASY',400
EXEC sp_inputLeaderBoard 'mike','HARD',300

SELECT * FROM LeaderBoard
DROP TABLE LeaderBoard

SELECT TOP 10 * FROM LeaderBoard WHERE difficulty = 'EASY' ORDER BY score DESC