function ajaxpage(method, url, containerId, callBackFunc, callBackFuncParam ){
	var page_request = false;
	if (window.XMLHttpRequest){
		page_request = new XMLHttpRequest();
	} else if (window.ActiveXObject){ 
		try {
			page_request = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e){
			try{
				page_request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e){}
		}
	} else {
		return false;
	}
		
	page_request.onreadystatechange=function(){
		loadpage(page_request, containerId, callBackFunc, callBackFuncParam);
	}
	
	page_request.open(method, url, true);	
	page_request.send('selected=0');
}

function loadpage(page_request, containerId, callBackFunc, callBackFuncParam){
	if (page_request.readyState == 4 && (page_request.status==200 || window.location.href.indexOf("http")==-1)){		
		if (containerId == 'cbf'){			
			callBackFunc(page_request.responseText,callBackFuncParam);
		} else {
			document.getElementById(containerId).innerHTML = page_request.responseText;
		}
	}
}