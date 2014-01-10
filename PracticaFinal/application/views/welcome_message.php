<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Welcome to Look & Cook</title>

	<style type="text/css">

	::selection{ background-color: #E13300; color: white; }
	::moz-selection{ background-color: #E13300; color: white; }
	::webkit-selection{ background-color: #E13300; color: white; }

	body {
		background-color: #fff;
		margin: 40px;
		font: 13px/20px normal Helvetica, Arial, sans-serif;
		color: #4F5155;
		background-image: url(../../mi_fondo.png);
	    background-repeat: repeat-x;
	}

	a {
		color: #003399;
		background-color: transparent;
		font-weight: normal;
	}

	h1 {
		color: #444;
		background-color: transparent;
		border-bottom: 1px solid #D0D0D0;
		font-size: 19px;
		font-weight: normal;
		margin: 0 0 14px 0;
		padding: 14px 15px 10px 15px;
	}

	code {
		font-family: Consolas, Monaco, Courier New, Courier, monospace;
		font-size: 12px;
		background-color: #f9f9f9;
		border: 1px solid #D0D0D0;
		color: #002166;
		display: block;
		margin: 14px 0 14px 0;
		padding: 12px 10px 12px 10px;
	}

	#body{
		margin: 0 15px 0 15px;
		
	}
	
	p.footer{
		text-align: right;
		font-size: 11px;
		border-top: 1px solid #D0D0D0;
		line-height: 32px;
		padding: 0 10px 0 10px;
		margin: 20px 0 0 0;
	}
	
	#wrapper{
		margin: 10px;
		border: 1px solid #D0D0D0;
		-webkit-box-shadow: 0 0 8px #D0D0D0;
	}
	#menu {
	margin: 0px;
	padding: 0px;
	float: right;
	width: 250px;
	background-color: #FFF;
}
    </style>
</head>
<body>

<div id="wrapper">
	<div id="header"> 
    	<a href="welcome_message.php"><img src="RecursosPracticaFinal/logos/tesla-motors.png" width="83" height="90" alt="logo"></a>
    	<h1><a name="top"></a>Todo Motor presenta Tesla Motors' Telsa Roadster</h1>
    </div>
    <div id="menu">
    	<ul>
      		<li><a href="teslaroadster.html">Presentacion</a></li>
      		<li><a href="fotostesla.html">Galería de Imágenes</a></li>
      		<li><a href="detalles.html">Detalles</a></li>
      		<li><a href="encargo.html">Encargo</a></li>
    	</ul>
  	</div>

	<div id="search">
		<p>Esta va a ir cambiando según si estas buscando o vas leyendo la receta.</p>
        <div id="ingredients"><p>ingredientes</p></div> 
        <div id="results"><p>resultado de recetas</p></div> 
    </div>
    
    <div id="recommended"><p>recomendadas</p></div>
    <div id="related"><p>relacionadas</p></div>
    
    

		


	<p class="footer">Page rendered in <strong>{elapsed_time}</strong> seconds</p>
</div>

</body>
</html>