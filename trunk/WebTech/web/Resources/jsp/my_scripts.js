function loadXMLDoc(url) {
	var xmlhttp;
	var txt,x,i;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
		
		x=xmlhttp.responseXML.documentElement.getElementsByTagName("RECIPE");
		txt='<ul class="media-list">';
		for (i=0;i<x.length;i++)
		  {
			txt=txt + '<li class="media">';
			txt=txt + '<a class="pull-left" href="#">' +
					'<img class="media-object" src="Resources/icons/64x64/images.jpg">' + 
					'</a><div class="media-body">' +
					'<h4 class="media-heading">' +
					x[i].getAttribute('NAME') + '</h4>' +
					'<h5>'+ x[i].getElementsByTagName("INGREDIENT")[0].innerHTML +'</h5>'+		
					'<p>' + x[i].getElementsByTagName("SNIPPET")[0].innerHTML + '</p>' +
					'</div></li>';
			
		  }
		txt=txt + '</ul>';
		document.getElementById('txtRecetas').innerHTML=txt;
		}
	  };
	xmlhttp.open("GET",url,true);
	xmlhttp.send();
}


function showHint(str) {
	var xmlhttp;
	if (str.length===0)
	  { 
	  document.getElementById("txtHint").innerHTML="";
	  return;
	  }
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
		document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
		}
	  };
	xmlhttp.open("GET","gethint.php?q="+str,true);
	xmlhttp.send();
}