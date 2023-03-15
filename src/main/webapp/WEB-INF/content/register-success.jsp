<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<head>
<title>Amazin</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
<%@ taglib uri="/struts-tags" prefix="s" %>  
  
Welcome, <s:property value="loginInfo.login"/>, contraseña: <s:property value="loginInfo.password"/>, nombre: <s:property value="name"/>, dni: <s:property value="dni"/>
</body>
