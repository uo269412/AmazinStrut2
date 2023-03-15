<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<head>
<title>Amazin</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<header>
		<h1 class="header">Amazin.com</h1>
		<h2 class="centered">
			<s:text name="welcome" />
		</h2>
	</header>
	<nav>
		<ul>
			<li><a href="index.action">Start</a></li>
			<li><a href="http://miw.uniovi.es">About</a></li>
			<li><a href="mailto:dd@email.com">Contact</a></li>
			<li><a href="sign-out.action">Cerrar sesión</a></li>
			<li><a href="manage-books.action">Manage books</a></li>
		</ul>
	</nav>
	<section>
		<article>
			<label class="mytitle">Resultados de la operación</label><br />
			<div style="color: green;">
				<strong> Se ha realizado la acción correctamente</strong>
				<br />
			</div>

		</article>
		<s:a action="managebooks">Volver</s:a>
	</section>
	<footer>

		<strong> Master in Web Engineering (miw.uniovi.es).</strong><br /> <em>University
			of Oviedo </em>
	</footer>
</body>