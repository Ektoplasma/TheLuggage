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
	ValeurPIN char(50),
	ValeurFinger char(50),
	BackupKey char(50),
	VersionUser numeric
)
/

CREATE TABLE Websites 
(
	IdGlobal numeric PRIMARY KEY,
	NomFieldLogin char(50),
	NomFieldPass char(50),
	URLPageForm char(150),
	VersionSite numeric
)
/

CREATE TABLE IDs 
(
	IdGlobal numeric PRIMARY KEY,
	IdUser numeric REFERENCES Users(IdGlobal),
	IdSite numeric REFERENCES Websites(IdGlobal),
	ValeurLogin char(50),
	ValeurPass char(50),
	VersionID numeric
)
/

CREATE SKT IDs
(
	Users (IdGlobal),
	Websites (IdGlobal)
)
/