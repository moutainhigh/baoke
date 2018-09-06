//Validate
function validateInstall(){
	$("input[id='installbutton'][type='submit']").click(function(){
		if(''==$('input[id="filepath"]').val()){
			dialogAlert("alert","Error","Upload File couldn't be empty!");
			$('#alert').dialog('open');
			return false;
		}else{
			$("input[id='uploadForm']").submit();
		}
	});
}

function initInstallButton(){
	$("input[id='filebutton'][type='button'][value='Upload...']").click(function(){
		$("input[id='file'][type='file']").click();
		$("input[id='filepath'][type='text']").val($("input[id='file'][type='file']").attr('value'));
	});
}

function validateDelete(url){
	function okf(){
		location.href = url;
	}
	dialogConfirmation("alert","Info","Are you define Delete this iterm?",okf);
	$('#alert').dialog('open');
	return false;	
}
function validatePurge(url){
	function okf(){
		location.href = url;
	}
	dialogConfirmation("dialog","Info","Are you define Purge this iterm?",okf);
	$('#alert').dialog('open');
	return false;	
}


//Dialog
function dialogAlert(id,title,content){
	$("#"+id).html(content);
	$("#"+id).dialog({
		title:title,
		height: 100,
		width: 300,
		buttons:{
			"Ok": function() { 
				$(this).dialog("close"); 
			}
		}
	});	
}
function dialogConfirmation(id,title,content,okf){
	$("#"+id).html(content);
	$("#"+id).dialog({
		title:title,
		modal:true,
		buttons:{
			"Ok": okf,
			"Cancel": function() { 
				$(this).dialog("close");
			}
		}
	});	
}
function printBoolean(bool){
	if(bool==true){
		return "True";
	}else{
		return "False";
	}
}
