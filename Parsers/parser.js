
/*la fonction parseOut constitue le message qui serra envoyé vers
l'application, en fonction d'une action definie en conception et
des parametre dont cette action a besoin */

/*le parametre est un objet de type {URL:, login:, mdp:,nomFieldLogin:,nomFieldMdp:}*/

function parseOut(action, parametre){

//declaration de variable correspondant aux mots cles de notre protocole
	var add = "INSERT:";
	var read = "SELECT:";
	var suppr = "DELETE:";
	var maj	= "UPDATE:";

	var query = "Q:";
	var anser = "A:";
	var noanser = "X:";

	var modF = "ModifForm";
	var modI = "ModifID";
	var addS = "AjoutSite";
	var fillF = "RemplirForm";
	var supp = "SupprID";
	var addI = "AjoutID";
	var chkI = "CheckID";


	var toSend;

//parsing en fonction de l'action
	switch(action){
		case modF:
			toSend = noanser+maj+action+":"+parametre.nomFieldLogin+";"+parametre.nomFieldMdp+";"+parametre.URL;
			break;
		case modI:
			toSend = noanser+maj+action+":"+parametre.login+";"+parametre.mdp+";"+parametre.URL;
			break;
		case addS:
			toSend = noanser+add+action+":"+parametre.nomFieldLogin+";"+parametre.nomFieldMdp+";"+parametre.URL;
			break;
		case fillF:
			toSend = query+read+action+":"+parametre.nomFieldLogin+";"+parametre.nomFieldMdp+";"+parametre.URL;
			break;
		case supp:
			toSend = noanser+suppr+action+":"+parametre.URL;
			break;
		case addI:
			toSend = noanser+add+action+":"+parametre.login+";"+parametre.mdp+";"+parametre.URL;
			break;
		case chkI:
			toSend = query+read+action+":"+parametre.login+";"+parametre.mdp+";"+parametre.URL;
			break;
		default:
			toSend = "erreur ParseOut: action inconnue ";
	}


	return Base64.encode(toSend);
}


/* la fonction parseIn decortique les messages arrivant a l'add-on
depuis l'application, ce sont des messages contenant des liste d'id*/

function parseIn(messageEncoded){

	var message = Base64.decode(messageEncoded);

	var separateur = ":"
	var separateurParam = "|";
	var anser = "A";


	var messageSplit = message.split(separateur);
	var log;
	var logSplit;
	var logArr = [] ;

	var requeteMdp = "query";
	var requeteRead = "exist";
	if(messageSplit[0]==anser){
		if(messageSplit[1]==requeteMdp){
			for(var i=2; i<messageSplit.length ; i++){
				logSplit=messageSplit[i].split(separateurParam);
				log={login:logSplit[0], mdp:logSplit[1]};
				logArr.push(log);
			}
		} else if (messageSplit[1] == requeteRead) {
			if(messageSplit[2] == "true")
				return true;
			else if (messageSplit[2] == "false")
				return false;
			else
			  return "erreur: ParserIn js, reponse non reconnue";
		}else {
			return "erreur: ParserIn js, combinanison fausse";
		}
	}
	else{
		return "erreur: parseIn js";
	}
	return logArr; //retourne un tableau d'objet de type {login:,mdp:}
}


var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
							encode:function(e){
								var t="";
								var n,r,i,s,o,u,a;
								var f=0;
								e=Base64._utf8_encode(e);
								while(f<e.length){
									n=e.charCodeAt(f++);
									r=e.charCodeAt(f++);
									i=e.charCodeAt(f++);
									s=n>>2;
									o=(n&3)<<4|r>>4;
									u=(r&15)<<2|i>>6;
									a=i&63;
									if(isNaN(r)){
										u=a=64
									}
									else if(isNaN(i)){
											a=64
									}
									t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)
								}
								return t
							},
							decode:function(e){
								var t="";
								var n,r,i;
								var s,o,u,a;
								var f=0;
								e=e.replace(/[^A-Za-z0-9\+\/\=]/g,"");
								while(f<e.length){
									s=this._keyStr.indexOf(e.charAt(f++));
									o=this._keyStr.indexOf(e.charAt(f++));
									u=this._keyStr.indexOf(e.charAt(f++));
									a=this._keyStr.indexOf(e.charAt(f++));
									n=s<<2|o>>4;r=(o&15)<<4|u>>2;
									i=(u&3)<<6|a;
									t=t+String.fromCharCode(n);
									if(u!=64){
										t=t+String.fromCharCode(r);
									}
									if(a!=64){
										t=t+String.fromCharCode(i);
									}
								}
								t=Base64._utf8_decode(t);
								return t;
							},
							_utf8_encode:function(e){
								e=e.replace(/\r\n/g,"\n");
								var t="";
								for(var n=0;n<e.length;n++){
									var r=e.charCodeAt(n);
									if(r<128){
										t+=String.fromCharCode(r);
									}else if(r>127&&r<2048){
										t+=String.fromCharCode(r>>6|192);
										t+=String.fromCharCode(r&63|128);
									}
									else{
										t+=String.fromCharCode(r>>12|224);
										t+=String.fromCharCode(r>>6&63|128);
										t+=String.fromCharCode(r&63|128);
									}
								}
								return t;
							},
							_utf8_decode:function(e){
								var t="";
								var n=0;
								var r=c1=c2=0;
								while(n<e.length){
									r=e.charCodeAt(n);
									if(r<128){
										t+=String.fromCharCode(r);
										n++;
									}
									else if(r>191&&r<224){
										c2=e.charCodeAt(n+1);
										t+=String.fromCharCode((r&31)<<6|c2&63);
										n+=2;
									}
									else{
										c2=e.charCodeAt(n+1);
										c3=e.charCodeAt(n+2);
										t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);
										n+=3;
									}
								}
								return t;
							}
							}
