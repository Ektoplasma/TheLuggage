$(function() {

	var globalUname = "default";
	var globalPass;

	var shouldGrab = true;
	// ajouter la détection de formulaire (prendre ce qui a été fait ci-dessous) + émission du signal vers self
	var arrayForm = $('body').find('form');

	arrayForm.each(function(index){
		if($(this).attr('method') === 'post'){
			var formFound = $(this);
			var url = document.location.host;

			userField = formFound.find('#username, input[type=text],textarea').filter(':visible:first');
			fieldLname = userField.attr('name');

			passField = formFound.find('input:password').filter(':visible:first');
			fieldPname = passField.attr('name');

			if (passField.length > 0) {
				self.port.emit("creds_fill", fieldLname, fieldPname, url);
			};

		}

	});

	self.port.on("fill", function(fieldL, fieldP, username, password, state){

				if(state === true){//si true, un profil existe
					login = 'input[name='+fieldL+']';
					pass = 'input[name='+fieldP+']';

					$(login).eq(0).val(username);
					$(pass).eq(0).val(password);

					globalUname = username;
					globalPass = password;

					shouldGrab = false;

				}
				else shouldGrab = true;


	});

	$( ":submit, input[value=submit]").click(function() {

		
			
		  form = $(this).closest("form");

	  	  var pass = $("input:password").val();

		  if (form.attr('method') === "post" && pass !== null && pass !== "") {

			  uname = form.find('#username, input[type=text],textarea').filter(':visible:first').val();
			  var mail = "";

			  if (form.find('#email, input[type=email]').length > 0) {
			  	mail = form.find('#email, input[type=email]').val();
			  } else if (form.find('#email, input[type=text],textarea').filter(':visible:eq(1)').length > 0){
			  	mail = form.find('#email, input[type=text],textarea').filter(':visible:eq(1)').val();
			  }
			  else mail = "N/A";

			  var url = document.location.host;

			  if (shouldGrab === true || globalUname !== uname) {

			  	self.port.emit("creds_submit",mail,uname, pass, url, "N/A", "N/A");
			  //pour l'instant, pas d'identification FieldL et FieldP
			  }
		  
		  }
			  
	  });
});