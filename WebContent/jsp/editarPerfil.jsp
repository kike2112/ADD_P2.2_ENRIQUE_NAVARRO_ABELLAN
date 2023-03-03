<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dam2.add.p22.modelo.Usuario"%>
<%@page import="dam2.add.p22.DAO.UsuarioDAOMemoria"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Usuario usuarioEditable = (Usuario) request.getSession().getAttribute("usuarioEditable");
    String error_email = (String) request.getAttribute("error_email");
    String idiomaSesion = (String) request.getSession().getAttribute("idioma");
    if (idiomaSesion == null) {
        idiomaSesion = "";
    }
    ResourceBundle rb = Propiedades.getIdiomaSesion(usuarioEditable.getEmail(), idiomaSesion);
%>    
<head>
    <title><%=rb.getString("Editar_perfil")%></title>
</head>
<script>
    function guardarCambios() {
        var respuesta = confirm("<%=rb.getString("Seguro_que_desea_guardar_las_modificaciones_a_este_Usuario")%>");
        if (respuesta == true) {
            return true;
        } else {
            return false;
        }
    }
</script>
<div class="ventana">
    <div class="titulo">
        <h2><%=rb.getString("Cambiar_Datos")%></h2>
    </div>       
    <form action="<%=request.getContextPath()%>/AccesoUsuario?opcion=editar&id_us=<%=usuarioEditable.getId_us()%>" method="post">
        <fieldset>   
            <div class="campo">
                <label for="email"><%=rb.getString("Email")%>:<span>*</span></label>
                <br>  
                <input class="caja-texto" type="text" id="email" name="email" placeholder="<%=rb.getString("Email")%>" value="<%=usuarioEditable.getEmail()%>" title="<%=rb.getString("Debe_introducir_un_email_de_usuario")%>">       
            </div>
<%
    if (error_email != null) {
%>
            <div class="campo" class="error_nombre"><p class="error"><%=error_email%></p></div>
<%
    }
%>
            <div class="campo">
                <label for="nombre"><%=rb.getString("Nombre")%>:<span>*</span></label>
                <br>  
                <input class="caja-texto" type="text" id="nombre" name="nombre" placeholder="<%=rb.getString("Nombre")%>" value="<%=usuarioEditable.getNombre()%>" title="<%=rb.getString("Debe_introducir_un_nombre")%>">
            </div>
            <div class="campo">
                <label for="apellido"><%=rb.getString("Primer_apellido")%>:<span>*</span></label>
                <br>  
                <input class="caja-texto" type="text" id="apellido" name="apellido" placeholder="<%=rb.getString("Primer_apellido")%>" value="<%=usuarioEditable.getApellido()%>" title="<%=rb.getString("Debe_introducir_un_apellido")%>" required>
            </div>
            <div class="campo">
                <label for="apellido2"><%=rb.getString("Segundo_apellido")%>:</label>
                <br>  
                <input class="caja-texto" type="text" id="apellido2" name="apellido2" placeholder="<%=rb.getString("Segundo_apellido")%>" value="<%=usuarioEditable.getApellido2()%>" title="<%=rb.getString("Puede_introducir_un_segundo_apellido")%>">
            </div>
            <div class="campo">
                <label for="telefono"><%=rb.getString("Telefono")%>:</label>
                <br>  
                <input class="caja-texto" type="tel" id="telefono" name="telefono" placeholder="<%=rb.getString("Telefono")%>" value="<%=usuarioEditable.getTelefono()%>" title="<%=rb.getString("Puede_introducir_un_telefono")%>" pattern="[0-9]{9}">
            </div>
            <div class="botones2">
                <div>
                    <input class="boton" type="submit" name="edit" value="<%=rb.getString("Guardar")%>" onclick="return guardarCambios()"/>
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