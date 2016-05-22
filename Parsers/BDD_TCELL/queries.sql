--/**************************** Requete AJOUTSITE ****************************/

--public static String EP_AJOUTSITECHECK=
SELECT W.IdGlobal
FROM Websites W
WHERE W.URLPageForm = ?
/

--public static String EP_PREAJOUTSITE=
SELECT W.IdGlobal
FROM Websites W
/

--public static String EP_AJOUTSITE=
INSERT INTO Websites VALUES (?,?,?,?,?)
/

--/**************************** Requete CONNEXION ****************************/

--public static String EP_CONNEXION=
SELECT U.IdGlobal, U.BackupKey
FROM Users U
WHERE U.Name = ?
AND U.ValeurFinger = ?
AND U.ValeurPIN = ?
/

--/**************************** Requete INSCRIPTION ****************************/

--public static String EP_INSCRIPTION=
SELECT U.IdGlobal
FROM Users U
WHERE U.Name = ?
/

--public static String EP_PREINSCRIPTION=
SELECT U.IdGlobal
FROM Users U
/

--public static String EP_INSCRIPTIONINSERT=
INSERT INTO Users VALUES (?,?,?,?,?,?)
/

--/**************************** Requete MODIFFORM ****************************/

--public static String EP_PREMODIFFORM=
SELECT W.IdGlobal, W.NomFieldLogin, W.NomFieldPass, W.VersionSite
FROM Websites W
WHERE W.URLPageForm = ?
/

--public static String EP_MODIFFORMDELETE=
DELETE FROM Websites
WHERE IdGlobal = ? 
/

--public static String EP_MODIFFORM=
INSERT INTO Websites VALUES (?,?,?,?,?)
/

--/**************************** Requete MODIFID ****************************/

--public static String EP_PREMODIFID=
SELECT W.IdGlobal
FROM Websites W
WHERE W.URLPageForm = ?
/

--public static String EP_MODIFIDSELECT=
SELECT I.IdGlobal, I.ValeurLogin, I.ValeurPass, I.VersionID
FROM IDs I
WHERE I.IdSite = ?
AND I.IdUser = ?
/

--public static String EP_MODIFIDDELETE=
DELETE FROM IDs
WHERE IdGlobal = ? 
/

--public static String EP_MODIFID=
INSERT INTO IDs VALUES (?,?,?,?,?,?)
/

--/**************************** Requete REMPLIRFORM ****************************/

--public static String EP_PREREMPLIRFORM=
SELECT W.IdGlobal, W.NomFieldLogin, W.NomFieldPass, W.VersionSite
FROM Websites W
WHERE W.URLPageForm = ?
/

--public static String EP_REMPLIRFORMDELETE=
DELETE FROM Websites
WHERE IdGlobal = ? 
/

--public static String EP_REMPLIRFORMINSERT=
INSERT INTO Websites VALUES (?,?,?,?,?)
/

--public static String EP_REMPLIRFORMW=
SELECT W.NomFieldLogin, W.NomFieldPass
FROM Websites W
WHERE W.IdGlobal = ?
/

--public static String EP_REMPLIRFORMI=
SELECT I.ValeurLogin, I.ValeurPass
FROM IDs I
WHERE I.IdUser = ?
/

--public static String EP_REMPLIRFORM=
SELECT W.NomFieldLogin, W.NomFieldPass, I.ValeurLogin, I.ValeurPass
FROM Websites W, IDs I
WHERE W.IdGlobal=I.IdSite
AND W.IdGlobal = ?
AND I.IdUser = ?
/

--/**************************** Requete SUPPRID ****************************/

--public static String EP_PRESUPPRID=
SELECT W.IdGlobal
FROM Websites W
WHERE W.URLPageForm = ?
/

--public static String EP_SUPPRID=
DELETE FROM IDs
WHERE IdSite = ? 
AND IdUser = ?
/

--/**************************** Requete SUPPRUSER ****************************/

--public static String EP_PRESUPPRUSER=
SELECT U.IdGlobal
FROM Users U
WHERE U.Name = ?
AND U.ValeurPIN = ?
/

--public static String EP_SUPPRUSER_USER=
DELETE FROM Users
WHERE IdGlobal = ?
/

--public static String EP_SUPPRUSER_IDS=
DELETE FROM IDs
WHERE IdUser = ?
/

--/**************************** Requete AJOUTID ****************************/

--public static String EP_PREAJOUTID=
SELECT W.IdGlobal
FROM Websites W
WHERE W.URLPageForm = ?
/

--public static String EP_AJOUTIDCHECK=
SELECT I.IdGlobal
FROM IDs I
WHERE I.IdUser = ?
AND I.IdSite = ?
AND I.ValeurLogin = ?
AND I.ValeurPass = ?
/

--public static String EP_AJOUTIDSELECT=
SELECT I.IdGlobal
FROM IDs I
/

--public static String EP_AJOUTID=
INSERT INTO IDs VALUES (?,?,?,?,?,?)
/

--/**************************** Requete TESTS ****************************/

--public static String EP_TESTUSERS=
SELECT *
FROM Users
/

--public static String EP_TESTWEBSITES=
SELECT *
FROM Websites
/

--public static String EP_TESTIDS=
SELECT *
FROM IDs
/
