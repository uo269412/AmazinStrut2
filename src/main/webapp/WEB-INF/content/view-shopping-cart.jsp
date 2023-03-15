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
		<article>
			<table>
				<thead>
					<tr>
						<th>Book ID</th>
						<th>Quantity</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="book" value="%{#session.shoppingcart.list}">
						<tr>
							<td><s:property value="key" /></td>
							<td><s:property value="value" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<div style="color: red;">
				<s:property value="#request.purchaseError" />
				<br />
			</div>
			<strong>Precio total: <s:property
					value="%{#session.shoppingcart.cost}" /></strong> <br>
			<s:form action="buybooks">
				<s:submit value="Comprar libros" />
			</s:form>
			<s:a action="show-books.action">Volver al catálogo de libros</s:a>
		</article>
	</section>
	<footer>
		<strong> Master in Web Engineering (miw.uniovi.es).</strong><br /> <em>University
			of Oviedo </em>
	</footer>
</body>