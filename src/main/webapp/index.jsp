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
			<li><a href="#">Start</a></li>
			<li><a href="http://miw.uniovi.es">About</a></li>
			<li><a href="mailto:dd@email.com">Contact</a></li>
		</ul>
	</nav>
	<section>
		<article>
			<label class="mytitle">Registrate con login, password, nombre
				y DNI</label><br />
			<div style="color: red;">
				<s:property value="#request.mymessageRegister" />
				<br />
			</div>
			<s:form action="register">
				<s:textfield name="login" label="Login" />
				<s:password name="password" label="Password" />
				<s:textfield name="name" label="Nombre" />
				<s:textfield name="dni" label="DNI" />
				<s:submit value="Registrar cuenta" />
			</s:form>
		</article>
		<article>
			<label class="mytitle">Introduce login and password:</label><br />
			<div style="color: red;">
				<s:property value="#request.mymessage" />
				<br />
			</div>
			<s:form action="login">
				<s:textfield name="loginInfo.login" label="Login" />
				<s:password name="loginInfo.password" label="Password" />
				<s:textfield name="loginInfo.captcha" label="Introduce 23344343" />
				<s:submit value="Iniciar sesion" />
			</s:form>
		</article>
	</section>
	<footer>

		<strong> Master in Web Engineering (miw.uniovi.es).</strong><br /> <em>University
			of Oviedo </em>
	</footer>
</body>
