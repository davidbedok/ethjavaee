function subscribe( sourcekey ) {
	var clientId = document.getElementById("clientid").value;
	var url = "services/subscription/subscribe?q=1";
	url += '&clientid='+clientId;
	url += '&sourcekey='+sourcekey;
	ajaxpage('GET', url,'cbf',cbSubscribe,sourcekey);
}

function cbSubscribe( value, param ) {
	if ( ( typeof(value) != 'undefined' ) && ( typeof(param) != 'undefined' ) ) {
		
	}
}

function unsubscribe( sourcekey ) {
	var clientId = document.getElementById("clientid").value;
	var url = "services/subscription/unsubscribe?q=1";
	url += '&clientid='+clientId;
	url += '&sourcekey='+sourcekey;
	ajaxpage('GET', url,'cbf',cbUnsubscribe,sourcekey);
}

function cbUnsubscribe( value, param ) {
	if ( ( typeof(value) != 'undefined' ) && ( typeof(param) != 'undefined' ) ) {
		
	}
}

function setupServerSentEventsSources( stream, callback ) {
	if (typeof (EventSource) !== "undefined") {
		var clientId = document.getElementById("clientid").value;
		var source = new EventSource("ArkStream?clientid=" + clientId);
		source.addEventListener(stream, function(event) {
			callback(event.data);
		}, false);
	} else {
		document.getElementById("error").innerHTML = "Sorry, your browser does not support server-sent events...";
	}
}

function incrementCounter( id ) {
	var counterElement = document.getElementById(id);
	var counter = parseInt(counterElement.innerHTML);
	counterElement.innerHTML = (counter + 1);	
}