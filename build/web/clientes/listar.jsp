<%-- 
    Document   : listar
    Created on : Dec 20, 2018, 9:48:50 PM
    Author     : jramos
--%>

<%@page import="java.util.List"%>
<%@page import="logica.modelos.Cliente"%>
<%@page import="logica.database.DBManager"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Clientes</title>
    </head>
    <body>

    </body>
</html>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Lava Dora</title>
        <meta name="generator" content="Bootply" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!--<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">-->

        <!--[if lt IE 9]>
                <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <link href="../css/styles.css" rel="stylesheet">
    </head>
    <body>
        <!-- Main -->
        <div class="container-fluid p4">
            <div class="row" style="margin-top: 2rem;">
                <div class="col-sm-3">
                    <!-- Left column -->
                    <a href="#"><strong>Operaciones</strong></a>

                    <hr>

                    <ul class="nav nav-stacked">
                        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#userMenu">Clientes<i class="glyphicon glyphicon-chevron-right"></i></a>
                            <ul class="nav nav-stacked collapse" id="userMenu">
                                <li class="active"> <a href="../clientes/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Clientes</a></li>
                                <li><a href="../clientes/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Cliente</a></li>
                            </ul>
                        </li>
                        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#menu2"> Prendas <i class="glyphicon glyphicon-chevron-right"></i></a>

                            <ul class="nav nav-stacked collapse" id="menu2">
                                <li><a href="../prendas/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Prendas</a></li>
                                <li><a href="../prendas/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Prenda</a></li>
                            </ul>
                        </li>
                        <li class="nav-header">
                            <a href="#" data-toggle="collapse" data-target="#menu3">Servicios<i class="glyphicon glyphicon-chevron-right"></i></a>
                            <ul class="nav nav-stacked collapse" id="menu3">
                                <li><a href="../servicios/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Servicios</a></li>
                                <li><a href="../servicios/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Servicio</a></li>
                            </ul>
                        </li>
                    </ul>

                    <hr>

                    <a href="#"><strong>Utilidades</strong></a>

                    <hr>

                    <ul class="nav nav-stacked">
                        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#menu4">Estado de los Servicios<i class="glyphicon glyphicon-chevron-right"></i></a>
                            <ul class="nav nav-stacked collapse" id="menu4">
                                <li> <a href="../estado_servicios/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Estados</a></li>
                                <li><a href="../estado_servicios/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Estado</a></li>
                            </ul>
                        </li>

                        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#menu5">Tipo de Prenda<i class="glyphicon glyphicon-chevron-right"></i></a>
                            <ul class="nav nav-stacked collapse" id="menu5">
                                <li> <a href="../tipo_prendas/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Tipos de Prendas</a></li>
                                <li><a href="../tipo_prendas/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Tipo de Prenda</a></li>
                            </ul>
                        </li>

                        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#menu6">Tipo de Tela<i class="glyphicon glyphicon-chevron-right"></i></a>
                            <ul class="nav nav-stacked collapse" id="menu6">
                                <li> <a href="../tipo_telas/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Tipos de Tela</a></li>
                                <li><a href="../tipo_telas/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Tipo de Tela</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /col-3 -->
                <div class="col-sm-9">
                    <a href="/TareaLavaDora"><strong><i class="glyphicon glyphicon-dashboard"></i> My Dashboard</strong></a>
                    <hr>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        <i class="glyphicon glyphicon-wrench pull-right"></i>
                                        <h4>Lista de Clientes</h4>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <%
                                        DBManager manager = DBManager.getInstance();
                                        ArrayList<Object> clientes = manager.obtenerLista("Cliente");
                                    %>
                                    <% if (clientes != null) { %>
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th>Cédula</th>
                                                    <th>Nombre</th>
                                                    <th>Celular</th>
                                                    <th>Email</th>
                                                    <th>Dirección</th>
                                                    <th>Opciones</th>
                                                </tr>
                                            </thead>
                                            <% for(Object cliente : clientes) { %>
                                                <% List<String> valores = manager.obtenerValoresDeAtributos(cliente); %>
                                                <tr>
                                                    <td><%= valores.get(1) %></td>
                                                    <td><%= valores.get(2) %></td>
                                                    <td><%= valores.get(3) %></td>
                                                    <td><%= valores.get(4) %></td>
                                                    <td><%= valores.get(5) %></td>
                                                    <td>
                                                        <form action="${pageContext.request.contextPath}/clientes" method="get">
                                                            <input type="hidden" id="codigoCliente" name="codigoCliente" value=<%= cliente %>>
                                                            <input type="submit" name="mostrar" value="Ver Cliente" class="btn btn-success">
                                                        </form>
                                                    </td>
                                                    <td>
                                                        <form action="${pageContext.request.contextPath}/clientes" method="post">
                                                            <input type="hidden" name="action" value="delete"/>
                                                            <input type="hidden" name="codigoCliente" value=<%= cliente %>>
                                                            <input type="submit" name="borrar" value="Borrar Cliente" class="btn btn-danger">
                                                        </form>
                                                    </td>
                                                </tr>
                                            <% } %>
                                        </table>
                                    <% } else { %>
                                        <h1>No se han registrado clientes todavía</h1>
                                    <% }%>
                                </div>
                                <!--/panel content-->
                            </div>
                            <!--/panel-->
                        </div>
                        <!--/col-span-6-->
                    </div>
                </div>
                <!--/col-span-9-->
            </div>
        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/scripts.js"></script>
    </body>
</html>
