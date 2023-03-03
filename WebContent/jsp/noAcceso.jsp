<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@page import="dam2.add.p22.modelo.Usuario"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    String idiomaSesion = (String) request.getSession().getAttribute("idioma");
    if (idiomaSesion == null) {
        idiomaSesion = "";
    }
    ResourceBundle rb = null;
    if (usuario != null) {
        rb = Propiedades.getIdiomaSesion(usuario.getEmail(), idiomaSesion);
    } else {
        rb = Propiedades.getIdiomaSesion("", idiomaSesion);
    }
%>
<title><%=rb.getString("Seguridad")%></title>
<div class="ventana-listado">
    <h3><%=rb.getString("No_tiene_acceso_a_esta_zona")%></h3>
</div>