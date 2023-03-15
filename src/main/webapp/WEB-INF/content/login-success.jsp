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
			Welcome to the <em>smallest</em> online shop in the world!!
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
		<article id="a01">
			<label class="mytitle">Choose an option:</label><br /> <a
				href="show-books.action">Show Catalog</a><br /> <a
				href="show-special-offer.action">Show Special Offers!</a><br />
			<s:if test="#session.admin">
				<a href="manage-books.action">Manage books</a>
			</s:if>
		</article>
	</section>
	<footer>
		<s:property value="registrologin" />
		<strong> Master in Web Engineering (miw.uniovi.es).</strong><br /> <em>University
			of Oviedo </em><br /> Visits:
		<s:property value="%{#application.counter}" />

	</footer>
</body>
