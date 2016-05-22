CREATE TABLE LogDeleted 
(
	TabId numeric,
	TuplePos numeric,
	Flag numeric
)
/

CREATE TABLE UpdateLog 
(
	TabId numeric,
	TuplePos numeric,
	TupleSize numeric,
	CompleteTup char(498)
)
/

CREATE TABLE QEP
(
	IdGlobal numeric PRIMARY KEY,
	QEPStr char(458),
	SQLStr Blob,
	ExplainStr Blob
)
/

CREATE TABLE Users 
(
	IdGlobal numeric PRIMARY KEY,
	Name char(50),
	ValeurPIN char(130),
	ValeurFinger char(80),
	BackupKey char(80),
	VersionUser numeric
)
/

CREATE TABLE Websites 
(
	IdGlobal numeric PRIMARY KEY,
	NomFieldLogin char(80),
	NomFieldPass char(80),
	URLPageForm char(180),
	VersionSite numeric
)
/

CREATE TABLE IDs 
(
	IdGlobal numeric PRIMARY KEY,
	IdUser numeric REFERENCES Users(IdGlobal),
	IdSite numeric REFERENCES Websites(IdGlobal),
	ValeurLogin char(80),
	ValeurPass char(80),
	VersionID numeric
)
/

CREATE SKT IDs
(
	Users (IdGlobal),
	Websites (IdGlobal)
)
/
