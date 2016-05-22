const {Cc, Ci} = require("chrome");
let data = require("sdk/self").data;
let menuitems = require("menuitem");
let prompts = Cc["@mozilla.org/embedcomp/prompt-service;1"].getService(Ci.nsIPromptService);
let { ToggleButton } = require("sdk/ui/button/toggle");
let panels = require("sdk/panel");
let pageMod = require("sdk/page-mod");
let { ActionButton } = require('sdk/ui/button/action');
let { getActiveView }=require("sdk/view/core");
let _ = require("sdk/l10n").get;
let isLogged = false;
let { setInterval, clearInterval } = require("sdk/timers");
const { EventTarget } = require("sdk/event/target");
let target = EventTarget();
let { emit } = require('sdk/event/core');
let IDinterval = 0;
let button = null;
let panel_form = null;
let onceWarned = false;
let tokenUser = "Foobar";
/* ========= Parser Sortant ========= */
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

/* ======================== */


/* ========= Parser Entrant ========= */

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

/* ================ */

/* ========base 64 ======= */
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
/* =====================*/


/* ========= Menu Item ========= */
var menuitem = menuitems.Menuitem({
  id: "theluggage-menu",
  menuid: "menu_ToolsPopup",
  label: "The Luggage",
  onCommand: function(){
    prompts.alert(null, _("welcome"), _("greetings"));
  },
  insertbefore: "menu_pageInfo"
});
/* ========================= */

/* ==== Communication Serveur JAVA === */
function sendAjax(ajaxConfig) {
  var request = require("sdk/request").Request({
    url: ajaxConfig.url,
    contentType: ajaxConfig.contentType,
    content: ajaxConfig.data,
    headers: ajaxConfig.headers,
    onComplete: function(response){
      var dataR = response.json;
      if(!dataR)
        dataR = response.text;

      //console.log("Ajax complete", response.status, ajaxConfig.url, response.text);
      if(ajaxConfig.complete){
        ajaxConfig.complete(dataR);

      }

      if(response.status >= 400){ //got error
        if(ajaxConfig.error){
          //console.log("Ajax error", response.status, ajaxConfig.url, response.text);
          ajaxConfig.error(dataR);
        }
      }
      else{ //success
        if(ajaxConfig.success){
          //console.log("Ajax success", ajaxConfig.url, response.text);
          ajaxConfig.success(dataR);
        }
      }

    }

  });

  switch(ajaxConfig.type){
    case "POST":
      request.post();
      break;
    case "PUT":
      request.put();
    case "DELETE":
      request.delete();
    default:
      request.get();
  }

}

/* ======================= */

/* ==== Login Tester ==== */

function loginTester(){

  var options = {
        url:        'http://127.0.0.1:9000/',
        contentType: 'text/plain',
        type: 'GET',
        success: function(reponse){
                          if(reponse.length > 0){ 
                            isLogged = true;
                            clearInterval(IDinterval);
                            tokenUser = reponse;
                            emit(target, 'loggedIn');
                          }
                          else if (onceWarned === false){
                            onceWarned = true;
                            prompts.alert(null, "Authentification", "Veuillez vous connecter sur le token");
                          }
                        },
        error: function(reponse){prompts.alert(null, "failed", "failure");}

        // other available options:
        //url:       url         // override for form's 'action' attribute
        //type:      type        // 'get' or 'post', override for form's 'method' attribute
        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type)
        //clearForm: true        // clear all form fields after successful submit
        //resetForm: true        // reset the form after successful submit

        // $.ajax options can be used here too, for example:
        //timeout:   3000
  };
  sendAjax(options);
}

var interval = 1000 * 2 * 1;

if (isLogged === false ) {
  IDinterval = setInterval(loginTester, interval);
}

/* ================ */

/* ==== Toolbar Button ==== */
button = ToggleButton({
  id: "theluggage-link",
  label: "The Luggage",
  icon: {
    "16": "./img/icon-16-disabled.png",
    "32": "./img/icon-32-disabled.png",
    "64": "./img/icon-64-disabled.png"
  },
    onChange: handleChange,
    badgeColor: "#5FB404"
  });

function handleChange(state){
  if (isLogged === true) {
    if (state.checked) {
      button.badge = '';
      console.log("handleChange triggering");
      panel_form.show({
        position: button
      });
    }
  }
  else button.state('window', {checked: false});
}

function handleHide() {
  button.state('window', {checked: false});
}

function handleSubmitSuccess(mail, uname, pass, url){

          if (button.badge !== null) {
            if (typeof button.badge !== "number")
              button.badge = 0;
          }
          button.badge ++;
          panel_form.port.emit("creds", mail, uname, pass, url);
}

function handleFillSuccess(profils,url){

        if (profils.length > 0 && profils[0].login !== undefined) {
          panel_form.port.emit("fill", profils[0].login,profils[0].mdp, url);
          return true;
        }

        return false;
}
/* =============== */

target.on('loggedIn', function() { 

button.icon = {
    "16": "./img/icon-16.png",
    "32": "./img/icon-32.png",
    "64": "./img/icon-64.png"
  };

/* === Gestion Panel === */
panel_form = require("sdk/panel").Panel({
  width: 357,
  height: 423,
  contentURL: data.url("./view/panel.html"),
  contentScriptFile: [data.url("./js/jquery-2.2.0.min.js"), data.url("./js/main.js")],
  onHide: handleHide
});

/* hack q&d pour activer les attributs title dans le panel */
getActiveView(panel_form).setAttribute('tooltip', 'aHTMLTooltip');

panel_form.port.on("lang", function(){

      var langs = "auto,authors,back,cancel,email,greetings,logout,offline,online,register,safe,save,settings,status,title,username,welcome".split(',');
      var langsResults = {};
      var i;
      for (i = 0; i < langs.length; i++) {
        langsResults[langs[i]] = _(langs[i]);
      }
      panel_form.port.emit("lang", langsResults, tokenUser);

});

panel_form.port.on("creds_send", function(username, password, URLPageForm){

  var param = {login:username, mdp:password, URL:URLPageForm};
  var parsed = parseOut("AjoutID", param);
  var options = {
        url:        'http://127.0.0.1:9000/theluggage',
        contentType: 'text/plain',
        data: 'data='+parsed,
        type: 'POST',
        success: function(reponse){var message = parseIn(reponse); if (message === true) prompts.alert(null, "Enregistrement", "OK !");},
        error: function(reponse){prompts.alert(null, "failed", dataR.status);}

  };
  sendAjax(options);
});

/* ====================== */


/* === Page grabber === */
pageMod.PageMod({
  include: ["*"],
  contentScriptFile: [data.url("./js/jquery-2.2.0.min.js"), data.url("./js/grabber.js")],
  onAttach: function onAttach(worker) {
     worker.port.on("creds_submit", function(mail, uname, pass, url, champLogin, champPassword){

          var param = {login:uname, mdp:pass, URL:url};
          var parsed = parseOut("CheckID", param);

          var options = {
                url:        'http://127.0.0.1:9000/theluggage',
                contentType: 'text/plain',
                data: 'data='+parsed,
                type: 'POST',
                success: function(reponse){/*pas sur*/var message = parseIn(reponse); if(message === false) handleSubmitSuccess(mail, uname, pass, url);},
                error: function(reponse){}

          };
          sendAjax(options);
          /* =========== */

     });

     /* event creds fill : site avec formulaire repéré*/
      worker.port.on("creds_fill", function(fieldLog, fieldPass, url){

        var param = {nomFieldLogin:fieldLog, nomFieldMdp:fieldPass, URL:url};
        var profils = [];
        var profzero = {login:"", mdp:""}
        profils.push(profzero);
        var parsed = parseOut("RemplirForm", param);
        var options = {
                url:        'http://127.0.0.1:9000/theluggage',
                contentType: 'text/plain',
                data: 'data='+parsed,
                type: 'POST',
                success: function(reponse){var profils = parseIn(reponse); if(handleFillSuccess(profils,url) === true) worker.port.emit("fill", fieldLog, fieldPass, profils[0].login, profils[0].mdp, true);},
                error: function(reponse){}

          };
          sendAjax(options);
     });
  }
});
/* =============== */


}); // fin event target


