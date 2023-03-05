<%@page import="dam2.add.p22.modelo.Ubicacion"%>
<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dam2.add.p22.modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String error_email = (String) request.getAttribute("error_email");
    String error_pass = (String) request.getAttribute("error_pass");
    Usuario usuarioTemp = (Usuario) request.getAttribute("usuarioTemp");
    String email = usuarioTemp.getEmail();
    Ubicacion[] provincias = (Ubicacion[]) request.getAttribute("provincias");
    Ubicacion[] localidades = (Ubicacion[]) request.getAttribute("localidades");
    String idiomaSesion = (String) request.getSession().getAttribute("idioma");
    if (idiomaSesion == null) {
        idiomaSesion = "";
    }
    ResourceBundle rb = Propiedades.getIdiomaSesion(email, idiomaSesion);
%>
<title><%=rb.getString("Registro")%></title>
<div class="ventana">
    <div class="titulo">
        <h2><%=rb.getString("Registro_de_Usuario")%></h2>
    </div>       
    <form action="<%=request.getContextPath()%>/AccesoUsuario?opcion=agregar" method="post">
        <fieldset>
            <div class="campo">
                <label for="email"><%=rb.getString("Email")%>:<span>*</span></label>
                <br>  
<%
    if (email != null) {
    	if (!email.equals("") && !email.equals("null")) {
%>
                <input class="caja-texto" type="text" id="email" name="email" value="<%=email%>" title="<%=rb.getString("Debe_introducir_un_email_de_usuario")%>"> 
<%
    	}
    } 
    if (email == null || email.equals("null") || email.equals("")) {
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
                <label for="pass"><%=rb.getString("Contrasena")%>:<span>*</span></label>  
                <br>
                <input class="caja-texto" type="password" id="pass" name="pass" value="" title="<%=rb.getString("Debe_introducir_la_contrasena")%>"> 
            </div>                    
       <%
         if (error_pass != null) {
        %>
            <div class="campo" class="error_pass"><p class="error"><%=error_pass%></p></div>
        <%
         }         
       %>
            <div class="campo"> 
                <label for="pass2"><%=rb.getString("Confirme_Contrasena")%>:<span>*</span></label>
                <br>
                <input class="caja-texto" type="password" id="pass2" name="pass2" value="" title="<%=rb.getString("Debe_confirmar_la_contrasena")%>"> 
            </div>
            <div class="campo">
                <label for="nombre"><%=rb.getString("Nombre")%>:<span>*</span></label>
                <br>
<%
    if (usuarioTemp.getNombre() != null) {
        if (!usuarioTemp.getNombre().equals("") && !usuarioTemp.getNombre().equals("null")) {
%>                  
                <input class="caja-texto" type="text" id="nombre" name="nombre" value="<%=usuarioTemp.getNombre()%>" title="<%=rb.getString("Debe_introducir_un_nombre")%>">
<%
        }
    } 
    if (usuarioTemp.getNombre() == null || usuarioTemp.getNombre().equals("null") || usuarioTemp.getNombre().equals("")) {
%>                
                <input class="caja-texto" type="text" id="nombre" name="nombre" placeholder="<%=rb.getString("Nombre")%>" value="" title="<%=rb.getString("Debe_introducir_un_nombre")%>">
<%
    }
%>            
            </div>
            <div class="campo">
                <label for="apellido"><%=rb.getString("Primer_apellido")%>:<span>*</span></label>
                <br>  
<%
    if (usuarioTemp.getApellido() != null) {
        if (!usuarioTemp.getApellido().equals("") && !usuarioTemp.getApellido().equals("null")) {
%>                
                <input class="caja-texto" type="text" id="apellido" name="apellido" value="<%=usuarioTemp.getApellido()%>" title="<%=rb.getString("Debe_introducir_un_apellido")%>" required>
<%
        }
    } 
    if (usuarioTemp.getApellido() == null || usuarioTemp.getApellido().equals("null") || usuarioTemp.getApellido().equals("")) {
%>                
                <input class="caja-texto" type="text" id="apellido" name="apellido" placeholder="<%=rb.getString("Primer_apellido")%>" value="" title="<%=rb.getString("Debe_introducir_un_apellido")%>" required>
<%
    }
%>            
            </div>
            <div class="campo">
                <label for="apellido2"><%=rb.getString("Segundo_apellido")%>:</label>
                <br>  
<%
    if (usuarioTemp.getApellido2() != null) {
        if (!usuarioTemp.getApellido2().equals("") && !usuarioTemp.getApellido2().equals("null")) {
%>                
                <input class="caja-texto" type="text" id="apellido2" name="apellido2" value="<%=usuarioTemp.getApellido2()%>" title="<%=rb.getString("Puede_introducir_un_segundo_apellido")%>">
<%
        }
    } 
    if (usuarioTemp.getApellido2() == null || usuarioTemp.getApellido2().equals("null") || usuarioTemp.getApellido2().equals("")) {
%>                
                <input class="caja-texto" type="text" id="apellido2" name="apellido2" placeholder="<%=rb.getString("Segundo_apellido")%>" value="" title="<%=rb.getString("Puede_introducir_un_segundo_apellido")%>">
<%
    }
%>            
            </div>
            <div class="campo">
                <label for="telefono"><%=rb.getString("Telefono")%>:</label>
                <br>  
<%
    if (usuarioTemp.getTelefono() != null) {
        if (!usuarioTemp.getTelefono().equals("") && !usuarioTemp.getTelefono().equals("null")) {
%>               
                <input class="caja-texto" type="tel" id="telefono" name="telefono" value="<%=usuarioTemp.getTelefono()%>" title="<%=rb.getString("Puede_introducir_un_telefono")%>" pattern="[0-9]{9}">
<%
        }
    } 
    if (usuarioTemp.getTelefono() == null || usuarioTemp.getTelefono().equals("null") || usuarioTemp.getTelefono().equals("")) {
%>                
                <input class="caja-texto" type="tel" id="telefono" name="telefono" placeholder="<%=rb.getString("Telefono")%>" title="<%=rb.getString("Puede_introducir_un_telefono")%>" pattern="[0-9]{9}">
<%
    }
%>            
            </div>
            <div class="campo">
                <label for="provincia"><%=rb.getString("Seleccione_Provincia")%>:</label>
                <br> 
                <select id="provincia" name="provincia"> 
                    <option class="caja-texto" value="0" selected><%=rb.getString("Provincia")%></option>           
<%
    for (int i = 0; i < provincias.length; i++) {
%>                
                    <option class="caja-texto" value="<%=provincias[i].getId()%>"><%=provincias[i].getNm()%></option>
<%
    }
%>                 
                </select>             
            </div>
            <div class="campo">
                <label for="poblacion"><%=rb.getString("Seleccione_Poblacion")%>:</label>
                <br> 
                <select id="poblacion" name="poblacion">
                    <option class="caja-texto" value="0" selected><%=rb.getString("Localidad")%></option>
<%
    for (int i = 0; i < localidades.length; i++) {
 //       if (localidades[i].getId().startsWith("03")) {
%>                
                    <option class="caja-texto" value="<%=localidades[i].getId()%>"><%=localidades[i].getNm()%></option>
<%
//        }
    }
%>                 
                </select>
            </div>
            <div class="botones2">
                <div>
                    <input class="boton" type="submit" name="registrar" value="<%=rb.getString("Registrar")%>">
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