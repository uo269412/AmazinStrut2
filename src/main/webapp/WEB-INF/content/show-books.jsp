<!DOCTYPE html >
<%@ page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1" language="java"
	import="java.util.*, com.miw.model.Book,com.miw.presentation.book.*"
	errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
			<label class="mytitle">Choose an option:</label><br /> 
			<a href="add-to-shopping-cart-form.action">Añadir al carrito</a><br />
			<a href="view-shopping-cart.action">Ver carrito</a><br />
		</article>
		<article>
			<table>
				<caption>Our catalog:</caption>
				<thead>
					<tr>
						<th>Title</th>
						<th>Author</th>
						<th>Description</th>
						<th>Price</th>
						<th>Units</th>
						<th>Message</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#session.booklist" var="book">
						<tr>
							<td><s:property value="#book.title" /></td>
							<td><s:property value="#book.author" /></td>
							<td><s:property value="#book.description" /></td>
							<td><s:property value="#book.price" /> &euro;</td>
							<td><s:property value="#book.stock" /></td>
							<td><s:property value="#book.message" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</article>
		<s:a action="index">Volver</s:a>
	</section>
	<footer>
		<strong> Master in Web Engineering (miw.uniovi.es).</strong><br /> <em>University
			of Oviedo </em>
	</footer>
</body>