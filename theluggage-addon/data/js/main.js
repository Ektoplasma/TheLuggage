
function addClass(element, class_name) {
    if (typeof element != "undefined" && element !== null && typeof element.className != "undefined") {
        element.className += ' ' + class_name;
        element.className = element.className.replace(/^\s+|\s+$/g, '');
    }
    return;
}

function removeClass(element, class_name) {
    if (typeof element != "undefined" && element !== null && typeof element.className != "undefined") {
        if (class_name == "") {
            element.className = "";
        } else {
            element.className = element.className.replace(new RegExp('(^|\\b)' + class_name.split(' ').join('|') + '(\\b|$)', 'gi'), ' ').replace(/^\s+|\s+$/g, '');
        }
    }
    return;
}

function displayBlockEditor(){
  var p = document;

  var mainContainer = p.querySelector("#contain");

  var block = p.querySelector(".block");
  var cln = bock.cloneNode(true);
}

function displayProfile(/*II*/username, hostname) {
    var it = 1;
    var p = document;

        var mainContainer = p.querySelector("#contain");
        while (mainContainer.hasChildNodes()) {
          mainContainer.removeChild(mainContainer.firstChild);
        }
        removeClass(p.querySelector("#contain"), "offline");
        removeClass(p.querySelector("#overview"), "text-center");
        /*multi-profils non implémenté à cause de la restriction bdd*/
        //for (it = 0; it < ll.length; it += 1) {
            //var profil_info = ll[it];
      
            var profileWrapper  = p.createElement("div");
            addClass(profileWrapper, "profile");
            
            var editor = p.createElement("div");
            addClass(editor,"wrapper")

            var editorIcon = p.createElement("i");

            editorIcon.setAttribute("id","edit-pencil-" + it);
            addClass(editorIcon, "fi-pencil");
            addClass(editorIcon, "pencil");

            editor.appendChild(editorIcon);
            profileWrapper.appendChild(editor);

            var divCheck     = p.createElement("div");
            addClass(divCheck, "check");
            addClass(divCheck, "switch");
            addClass(divCheck, "round");
            addClass(divCheck, "tiny");
            var checkButton  = p.createElement("input");
            checkButton.setAttribute("id", "label-" + it);
            checkButton.setAttribute("type", "checkbox");
            checkButton.setAttribute("checked", "checked");
            var labelButton  = p.createElement("label");
            labelButton.setAttribute("for", "label-" + it);
            
            divCheck.appendChild(checkButton);
            divCheck.appendChild(labelButton);
            
            profileWrapper.appendChild(divCheck);
            
            var divInfo     = p.createElement("div");
            divInfo.setAttribute("id", "info-" + it);
            addClass(divInfo, "info");
            var titleP      = p.createElement("p");
            var wrapperInfo = p.createElement("p");
            //var hosterIcon  = p.createElement("img");
            var hostURL   = p.createElement("a");
            titleP.setAttribute("id", "title-" + it);
            //hosterIcon.setAttribute("src", "../img/icon-16.png");
            //hosterIcon.setAttribute("id", "icon-" + it);
            //addClass(hosterIcon, "icon");
            //wrapperInfo.appendChild(hosterIcon);
            wrapperInfo.appendChild(p.createTextNode(" "));
            hostURL.setAttribute("href", "#");
            hostURL.setAttribute("id", "link-" + it);
            addClass(hostURL, "host-link");
            wrapperInfo.appendChild(hostURL);
            
            divInfo.appendChild(titleP);
            divInfo.appendChild(wrapperInfo);
            
            profileWrapper.appendChild(divInfo);
      
            mainContainer.appendChild(profileWrapper);
            
            p.querySelector("#title-" + it).textContent = username;
            p.querySelector("#link-" + it).setAttribute("href", hostname);
            p.querySelector("#link-" + it).setAttribute("title", hostname);
            p.querySelector("#link-" + it).textContent = hostname;

            /*
            //utile pour le multi profil
            p.querySelector("#icon-" + it).setAttribute("src", profil_info.host_icon);
            p.querySelector("#title-" + it).textContent = profil_info.filename;
            p.querySelector("#link-" + it).setAttribute("href", profil_info.link);
            p.querySelector("#link-" + it).setAttribute("title", profil_info.link);
            p.querySelector("#link-" + it).textContent = profil_info.link;
            */
        //}
  
}

$(function() {

  var filler = '<br/><br/><img id="filler" src="../img/icon-menu.png" style="width:196px;height:228px;opacity:1;display: block;margin: 0 auto;">'

  var loggedIn = true;

    self.port.emit('lang', function(){});
    self.port.on('lang', function (obj, tokenUser) {
      lang = obj;
      $("title").text(lang.title);

      $("#popup-register").attr("title", lang.register);
      $("#register-label").text(lang.register);

      $("#popup-save").attr("title", lang.save);
      $("#save-label").text(lang.save);

      $("#popup-coffre").attr("title", lang.safe);
      $("#popup-settings").attr("title", lang.settings);
      $("#popup-auto").attr("title", lang.auto);

      $("#status-ctn").text(lang.status);
      $("#status").text(lang.online);

      $("#settings-username-label").text(lang.username);
      $("#settings-username").text(tokenUser);
      $("#settings-type").text(lang.auto);

      $("#auteurs").text(lang.authors);

      $("#username").text(tokenUser);

    });

    var send = false;
    self.port.on('fill', function (username,password,url) {

      send = false;

      $('#hostname').val(url);
      $('#user').val(username);
      $('#password').val(password);

      displayProfile(username, url);

    });

    self.port.on("creds", function(mail, uname, pass, url){
           send = true;

           $('#hostname').val(url);
           $('#user').val(uname);
           $('#mail').val(mail);
           $('#password').val(pass);

           displayProfile(uname, url);

    });

     $(document).on("click",'#edit-pencil-1', function() {
           var block = document.querySelector(".block");
           var cln = block.cloneNode(true);
           addClass(cln, "clone-1");
           document.querySelector("#contain").appendChild(cln);
           $('.clone-1').show();
           $('.profile').hide();
     });

    $(document).on("click",'#edit-hostname-1',function() {
           $('#hostname').prop("disabled", false);
           $('#helper').show();
     });

    $(document).on("click", '#edit-user-1', function() {
           $('#user').prop("disabled", false);
           $('#helper').show();
     });

    $(document).on("click", '#edit-mail-1', function() {
            $('#mail').prop("disabled", false);
            $('#helper').show();
    });

    $(document).on("click", '#edit-password-1', function() {
            $('#password').prop("disabled", false);
            $('#helper').show();
     });

    $(document).on("click", '#show-password-1', function() {
            $('.editor').toggle();
            $('#password').prop("type", "text");
     });

     $(document).on("click", '#hide-password-1', function() {
            $('.editor').toggle();
            $('#password').prop("type", "password");
     });

    $(document).on("click", '#popup-save', function(){
            $('input').each(function() {
                $(this).prop("disabled", true);
                $('#helper').hide();
            });
    });

    $(document).on("click", '#popup-register', function(){

            if (send === false) return false;

            url = $('#hostname').val();
            pass = $('#password').val();
            uname = $('#user').val();
            self.port.emit("creds_send",uname, pass, url);
            send = false;
            $('.block').hide();
            container = document.querySelector('#contain');
            container.innerHTML = filler;
            $('#filler').show();

    });

});

 
