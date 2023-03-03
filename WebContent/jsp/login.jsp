<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String error_email = (String) request.getAttribute("error_email");
    String error_pass = (String) request.getAttribute("error_pass");
    String email = (String) request.getAttribute("email");
    String idiomaSesion = (String) request.getSession().getAttribute("idioma");
    if (idiomaSesion == null) {
        idiomaSesion = "";
    }
    ResourceBundle rb = Propiedades.getIdiomaSesion(email, idiomaSesion);
%>
<head>
    <title><%=rb.getString("Login")%></title>
</head>    
<div class="ventana">
    <div class="titulo">
        <h2><%=rb.getString("Acceso_Usuarios")%></h2>
    </div>       
    <form action="<%=request.getContextPath()%>/AccesoUsuario?opcion=acceso" method="post">
        <fieldset>   
            <div class="campo">
                <label for="name"><%=rb.getString("Introduzca_sus_datos")%>:</label>
                <br>  
<%
	if (email != null) {
%>
                <input class="caja-texto" type="text" id="email" name="email" value="<%=email%>" title="<%=rb.getString("Debe_introducir_un_email_de_usuario")%>">
<%
	} else {
%>
                <input class="caja-texto" type="text" id="email" name="email" placeholder="<%=rb.getString("Email_de_usuario")%>" value="" title="<%=rb.getString("Debe_introducir_un_email_de_usuario")%>">
<%
    }
%>         
            </div>
<%
	if (error_email != null) {
%>
            <div class="campo" class="error_nombre"><p class="error"><%=error_email%></p></div>
<%
    }
%>
            <div class="campo">            
                <label for="pass"><%=rb.getString("Contrasena")%>:</label>  
                <br>
                <input class="caja-texto" type="password" id="pass2" name="pass2" value="" title="<%=rb.getString("Debe_introducir_la_contrasena")%>">    
            </div>
<%
	if (error_pass != null) {
%>
             <div class="campo" class="error_pass"><p class="error"><%=error_pass%></p></div>
<%
    }         
%>
            <div class="campo" class="campomini" class="registro">
                <div class="campomini">
                     <a class="registro" href="<%=request.getContextPath()%>/AccesoUsuario?opcion=registro&email=<%=email%>"><%=rb.getString("Registrarse")%></a>
                </div>
            </div>
            <div class="botones2">
                <div>
                    <input class="boton" type="submit" name="aceptar" value="<%=rb.getString("Aceptar")%>">
                </div>
                <div>
                    <a href="<%=request.getContextPath()%>">
                        <input class="boton" type="button" name="cancelar" value="<%=rb.getString("Cancelar")%>"/>
                    </a>
                </div>
            </div> 
        </fieldset>
    </form>
</div>