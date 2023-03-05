<%@page import="dam2.add.p22.utiles.Ruta"%>
<%@page import="dam2.add.p22.servicio.Agenda"%>
<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@page import="dam2.add.p22.modelo.Usuario"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="./css/style.css" type="text/css"></link>
</head>
<%
    ResourceBundle rb = null;
    String idiomaSesion = (String) request.getSession().getAttribute("idioma");
    if (idiomaSesion == null) {
        idiomaSesion = "";
    }
    Usuario usuarioI = (Usuario) request.getSession().getAttribute("usuario");
    String opcion;
    if (usuarioI == null) {
    	rb = Propiedades.getIdiomaSesion("", idiomaSesion);
    	opcion = (String) request.getAttribute("opcion");
    } else {
    	rb = Propiedades.getIdiomaSesion(usuarioI.getEmail(), idiomaSesion);
    	opcion = (String) request.getSession().getAttribute("opcion");
    }
    if (usuarioI == null && opcion == null) {
        opcion = (String) request.getSession().getAttribute("opcion");
    }
    
    if (opcion == null) {
        opcion = ""; 
    }
    
    if (opcion.equals("")) {    
%>
    <title><%=rb.getString("Pagina_principal")%></title>
<%              
    }
%> 
<body>
    <jsp:include page="/jsp/header.jsp"/>
    <jsp:include page="/jsp/nav.jsp"/>
    <main>
    
    
<%if (opcion.equals("registro")) {%>
    <jsp:include page="/jsp/registro.jsp"/>
    
<%} else if (opcion.equals("login")) {%>        
	<jsp:include page="/jsp/login.jsp"/>
	
<%} else if (opcion.equals("acceso_admin")) {%>
    <jsp:include page="/jsp/listado.jsp"/>
    
<%} else if (opcion.equals("acceso")) {
///////////// Acceso cliente

  } else if (opcion.equals("perfil")) {%>
    <jsp:include page="/jsp/perfil.jsp"/>
    
<%} else if (opcion.equals("editarPerfil")) {%>
    <jsp:include page="/jsp/editarPerfil.jsp"/> 
    
<%} else if (opcion.equals("editarPass")) {%>
    <jsp:include page="/jsp/editarPass.jsp"/>

<%} else if (opcion.equals("editarPersistencia")) {%>
    <jsp:include page="/jsp/editarPersistencia.jsp"/>
    
<%} else if (opcion.equals("noTieneAcceso")) {%>
    <jsp:include page="/jsp/noAcceso.jsp"/>
<%}%>

    <br/><br/><br/><br/><br/><br/><br/>
    </main>
    <jsp:include page="/jsp/footer.jsp"/> 
</body>
</html>