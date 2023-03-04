<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dam2.add.p22.modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    String idiomaSesion = (String) request.getSession().getAttribute("idioma");
    if (idiomaSesion == null) {
        idiomaSesion = "";
    }
    ResourceBundle rb = null;
    String opcionNav;
    if (usuario == null) {
    	rb = Propiedades.getIdiomaSesion("", idiomaSesion);
    	opcionNav = (String) request.getAttribute("opcion");
    } else {
    	rb = Propiedades.getIdiomaSesion(usuario.getEmail(), idiomaSesion);
    	opcionNav = (String) request.getSession().getAttribute("opcion");
    }
	if (opcionNav == null) {
		opcionNav = "";
	}
%>
<%
    if (opcionNav.equals("") || !opcionNav.equals("perfil")) {
%>
<nav>
    <div class="pre-nav"></div>
</nav>
<%
    } else if (opcionNav.equals("perfil")) {
%>
<nav>
    <div class="contenedor" id="menu-container">
        <ul id="ico-contenedor">
            <li>
                <ul class="contenido" id="menu">
                    <li><a class="opcion-menu" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=pintaEditar"><%=rb.getString("CAMBIAR_DATOS")%></a></li>
                    <li><a class="opcion-menu" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=pintaEditarPass"><%=rb.getString("CAMBIAR_CONTRASENA")%></a></li>
<%
    if (usuario.isRolAdmin()) {
%>                                      
                    <li><a class="opcion-menu" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=pintaEditarPersistencia"><%=rb.getString("CAMBIAR_REPOSITORIO")%></a></li>
                    <li><a class="opcion-menu" href="<%=request.getContextPath()%>/SelectorPersistencia?opcionPersist=resetBDD"><%=rb.getString("resetBDD")%></a></li>                              
<%      
    }
%>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<%
    }
%>