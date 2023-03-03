<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dam2.add.p22.DAO.UsuarioDAOMemoria"%>
<%@page import="dam2.add.p22.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String error_pass = (String) request.getAttribute("error_pass");
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    String idiomaSesion = (String) request.getSession().getAttribute("idioma");
    if (idiomaSesion == null) {
        idiomaSesion = "";
    }
    ResourceBundle rb = Propiedades.getIdiomaSesion(usuario.getEmail(), idiomaSesion);
%>
<title><%=rb.getString("Cambio_de_Contrasena")%></title>
<div class="ventana">
    <div class="titulo">
        <h2><%=rb.getString("Cambiar_Contrasena")%></h2>
    </div>       
    <form action="<%=request.getContextPath()%>/AccesoUsuario?opcion=editaPass" method="post">
        <fieldset>   
            <div class="campo">            
                <label for="pass"><%=rb.getString("Contrasena_actual")%>:<span>*</span></label>  
                <br>
                <input class="caja-texto" type="password" id="pass2" name="pass2" value="" title="<%=rb.getString("Debe_introducir_la_contrasena_actual")%>"> 
            </div>                    
       <%
         if (error_pass != null) {
        %>
            <div class="campo" class="error_pass"><p class="error"><%=error_pass%></p></div>
        <%
         }         
       %>
            <div class="campo">            
                <label for="pass"><%=rb.getString("Nueva_Contrasena")%>:<span>*</span></label>  
                <br>
                <input class="caja-texto" type="password" id="pass" name="pass" value="" title="<%=rb.getString("Debe_introducir_la_nueva_contrasena")%>"> 
            </div>                    
            <div class="campo"> 
                <label for="pass2"><%=rb.getString("Confirme_Nueva_Contrasena")%>:<span>*</span></label>
                <br>
                <input class="caja-texto" type="password" id="pass3" name="pass3" value="" title="<%=rb.getString("Debe_introducir_la_nueva_contrasena")%>"> 
            </div>
            <div class="botones2">
                <div>
                    <input class="boton" type="submit" name="guardar" value="<%=rb.getString("Guardar")%>">
                </div>
                <div>
                    <a href="<%=request.getContextPath()%>/AccesoUsuario?opcion=inicio">
                        <input class="boton" type="button" name="cancelar" value="<%=rb.getString("Cancelar")%>"/>
                    </a>
                </div>
            </div> 
        </fieldset>
    </form>
</div>