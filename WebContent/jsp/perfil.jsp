<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dam2.add.p22.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dam2.add.p22.DAO.UsuarioDAOMemoria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Usuario usuarioP = (Usuario) request.getSession().getAttribute("usuario");
    int id_us = usuarioP.getId_us();
    String idiomaSesion = (String) request.getSession().getAttribute("idioma");
    if (idiomaSesion == null) {
        idiomaSesion = "";
    }
    ResourceBundle rb = Propiedades.getIdiomaSesion(usuarioP.getEmail(), idiomaSesion);
%>
<head>
    <title><%=rb.getString("Perfil_Usuario")%></title>
    <link rel="stylesheet" href="./css/style-listado.css" type="text/css"></link>
</head>
<%
    
%>
<div class="ventana-listado">
    <h3><%=rb.getString("Perfil_de_Usuario")%></h3>
    <div class="contactos">
	    <div class="contacto">
	        <div class="c_datos">
	            <ul>
	                <li class="id">id<%=id_us%></li>
	            </ul>
	        </div>
	        <div class="c_datos2">
	            <ul>
	                <li class="nombre"><b><%=usuarioP.getNombre()%></b></li>
	                <li><%=usuarioP.getApellido()%> <%=usuarioP.getApellido2()%></li>
	            </ul>
	        </div>
	        <div class="c_datos3">
	            <ul>
	                <li><%=rb.getString("Email")%>: <a href="mailto:<%=usuarioP.getEmail()%>"><%=usuarioP.getEmail()%></a></li>
	                <li><%=rb.getString("Telefono")%>: <a href="tel:<%=usuarioP.getTelefono()%>"><%=usuarioP.getTelefono()%></a></li>
                    <li><%=rb.getString("Provincia")%>: <%=usuarioP.getProvincia()%></li>
                    <li><%=rb.getString("Localidad")%>: <%=usuarioP.getPoblacion()%></li>   
	            </ul>
	        </div>
	        <div class="c_fin">
	            <div class="boton-bus">
	                <a href="<%=request.getContextPath()%>/AccesoUsuario?opcion=inicio">
	                <input class="boton" type="button" name="busqueda" value="<%=rb.getString("Volver")%>"/>
	                </a>
	            </div>
	        </div>	        	          
	    </div>
    </div>
</div>