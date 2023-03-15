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
		</ul>
	</nav>
	<section>
		<article>
			<label class="mytitle">Crea un nuevo libro en la base de
				datos</label><br />
			<div style="color: red;">
				<s:property value="#request.mymessageCreation" />
				<br />
			</div>
			<div style="color: green;">
				<s:property value="#request.mymessageCorrectCreation" />
				<br />
			</div>
			<s:form action="addbook">
				<s:textfield name="title" label="Título" />
				<s:textfield name="description" label="Descripción" />
				<s:textfield name="author" label="Autor" />
				<s:textfield name="taxgroup" label="TaxGroup" />
				<s:textfield name="basePrice" label="Precio base" />
				<s:submit value="Insertar libro" />
			</s:form>
		</article>
		<article>
			<label class="mytitle">Cambia las unidades que tiene el libro</label><br />
			<div style="color: red;">
				<s:property value="#request.mymessageUpdate" />
				<br />
			</div>
			<div style="color: green;">
				<s:property value="#request.mymessageCorrectUpdate" />
				<br />
			</div>
			<s:form action="updatebook">
				<s:textfield name="updatetitle" label="Título" />
				<s:textfield name="updatestock" label="Unidades" />
				<s:submit value="Actualizar libro" />
			</s:form>
		</article>
		<s:a action="index">Volver al perfil</s:a>
	</section>
	<footer>

		<strong> Master in Web Engineering (miw.uniovi.es).</strong><br /> <em>University
			of Oviedo </em>
	</footer>
</body>
