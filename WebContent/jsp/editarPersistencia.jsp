<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="dam2.add.p22.modelo.Usuario"%>
<%@page import="dam2.add.p22.DAO.UsuarioDAOMemoria"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    String idiomaSesion = (String) request.getSession().getAttribute("idioma");
    if (idiomaSesion == null) {
        idiomaSesion = "";
    }
    ResourceBundle rb = Propiedades.getIdiomaSesion(usuario.getEmail(), idiomaSesion);
%>    
<head>
    <title><%=rb.getString("Editar_perfil")%></title>
</head>
<script>
    function guardarCambios() {
        var respuesta = confirm("<%=rb.getString("Seguro_que_desea_guardar_las_modificaciones")%>");
        if (respuesta == true) {
            return true;
        } else {
            return false;
        }
    }
</script>
<div class="ventana">
    <div class="titulo">
        <h2><%=rb.getString("CAMBIAR_REPOSITORIO")%></h2>
    </div>       
    <form action="<%=request.getContextPath()%>/SelectorPersistencia?opcionPersist=cambioRepo" method="get">
        <fieldset>   
            <div class="campo">
                <label for="persist"><%=rb.getString("Modifique_Persistencia")%>:</label>
                <br> 
                <select id="persist" name="persist">
                    <option class="caja-texto" value="memoria" selected>Memoria</option>
                    <option class="caja-texto" value="bdd">BDD</option>
                    <option class="caja-texto" value="hibernate">Hibernate</option>
                </select>             
            </div>
            <div class="campo">
                <label for="idioma"><%=rb.getString("Modifique_Idioma_Inicial")%>:</label>
                <br> 
                <select id="idioma" name="idioma">
                    <option class="caja-texto" value="en">Inglés</option>
                    <option class="caja-texto" value="es" selected>Español</option>
                </select>             
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