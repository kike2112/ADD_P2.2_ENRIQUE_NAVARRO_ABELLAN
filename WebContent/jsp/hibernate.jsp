<%@page import="dam2.add.p22.servicio.Propiedades"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%if (Propiedades.getPersistencia().equals("hibernate")) {%>
    <a class="opcion-cabecera">H</a>
<%}%>
