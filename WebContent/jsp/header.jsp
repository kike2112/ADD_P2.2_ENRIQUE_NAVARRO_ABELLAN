<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dam2.add.p22.modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String idiomaSesion = (String) request.getSession().getAttribute("idioma");
    if (idiomaSesion == null) {
    	idiomaSesion = "";
    }
    String opcion;   
    String emailHeader = (String) request.getAttribute("email");
    Usuario usuarioHeader = (Usuario) request.getSession().getAttribute("usuario");
    ResourceBundle rb = null;
    if (usuarioHeader != null) {
    	opcion = (String) request.getSession().getAttribute("opcion");
    	rb = Propiedades.getIdiomaSesion(usuarioHeader.getEmail(), idiomaSesion);
    } else {
    	opcion = (String) request.getAttribute("opcion");
    	rb = Propiedades.getIdiomaSesion("", idiomaSesion);
    }
    if (usuarioHeader == null && opcion == null) {
        opcion = (String) request.getSession().getAttribute("opcion");
    }
    if (opcion == null) {
        opcion = ""; 
    }
%> 
<header>
    <div class="contenedor" id="cabecera-container">
        <div class="contenido" id="cabecera">            
            <div>
<%      if (idiomaSesion.equals("es")) {%> 
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/Controlidioma?idioma=en&opcion=<%=opcion%>">
                    <img src="./img/en.png" alt="Login" width="30px">
                </a>
<%      } else {%> 
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/Controlidioma?idioma=es&opcion=<%=opcion%>">
                    <img src="./img/es.png" alt="Login" width="30px">
                </a>
        
<%      }%>                 
            </div>
<%
    if (usuarioHeader == null) {
%>
            <div>      
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=login">LOGIN</a>      
<%      if (emailHeader == null) {%>                         
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=registro&email=null"><%=rb.getString("REGISTRO")%></a>
<%      } else {%>                
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=registro&email=<%=emailHeader%>"><%=rb.getString("REGISTRO")%></a>
<%      }%>            
            </div>
<%
    } else if (usuarioHeader != null) {
%>            
            <div>
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=perfil"><%=usuarioHeader.getNombre().toUpperCase()%></a>          
                <a class="opcion-cabecera" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=cerrarSesion"><%=rb.getString("CERRAR_SESION")%></a>         
            </div>
<%
    }
%>
        </div>
    </div>
</header>