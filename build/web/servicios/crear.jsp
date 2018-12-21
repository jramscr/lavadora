<%-- 
    Document   : crear
    Created on : Dec 20, 2018, 9:45:16 PM
    Author     : jramos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                                <li class="active"> <a href="clientes/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Clientes</a></li>
                                <li><a href="clientes/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Cliente</a></li>
                            </ul>
                        </li>
                        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#menu2"> Prendas <i class="glyphicon glyphicon-chevron-right"></i></a>

                            <ul class="nav nav-stacked collapse" id="menu2">
                                <li><a href="prendas/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Prendas</a></li>
                                <li><a href="prendas/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Prenda</a></li>
                            </ul>
                        </li>
                        <li class="nav-header">
                            <a href="#" data-toggle="collapse" data-target="#menu3">Servicios<i class="glyphicon glyphicon-chevron-right"></i></a>
                            <ul class="nav nav-stacked collapse" id="menu3">
                                <li><a href="servicios/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Servicios</a></li>
                                <li><a href="servicios/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Servicio</a></li>
                            </ul>
                        </li>
                    </ul>

                    <hr>

                    <a href="#"><strong>Utilidades</strong></a>

                    <hr>

                    <ul class="nav nav-stacked">
                        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#menu4">Estado de los Servicios<i class="glyphicon glyphicon-chevron-right"></i></a>
                            <ul class="nav nav-stacked collapse" id="menu4">
                                <li> <a href="estado_servicios/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Estados</a></li>
                                <li><a href="estado_servicios/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Estado</a></li>
                            </ul>
                        </li>

                        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#menu5">Tipo de Prenda<i class="glyphicon glyphicon-chevron-right"></i></a>
                            <ul class="nav nav-stacked collapse" id="menu5">
                                <li> <a href="tipo_prendas/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Tipos de Prendas</a></li>
                                <li><a href="tipo_prendas/crear.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Tipo de Prenda</a></li>
                            </ul>
                        </li>

                        <li class="nav-header"> <a href="#" data-toggle="collapse" data-target="#menu6">Tipo de Tela<i class="glyphicon glyphicon-chevron-right"></i></a>
                            <ul class="nav nav-stacked collapse" id="menu6">
                                <li> <a href="tipo_telas/listar.jsp"><i class="glyphicon glyphicon-list"></i> &nbsp; Lista de Tipos de Tela</a></li>
                                <li><a href="tipo_telas/listar.jsp"><i class="glyphicon glyphicon-plus-sign"></i> &nbsp; Crear Tipo de Tela</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /col-3 -->
                <div class="col-sm-9">

                    <!-- column 2 -->
                    <!--                    <ul class="list-inline pull-right">
                                            <li><a title="Add Widget" data-toggle="modal" href="#addWidgetModal"><span class="glyphicon glyphicon-plus-sign"></span> Add Widget</a></li>
                                        </ul>-->
                    <a href="/TareaLavaDora"><strong><i class="glyphicon glyphicon-dashboard"></i> My Dashboard</strong></a>
                    <hr>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        <i class="glyphicon glyphicon-wrench pull-right"></i>
                                        <h4>Crear Cliente</h4>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <form class="form form-vertical" action="${pageContext.request.contextPath}/clientes" method="post">
                                        <div class="control-group">
                                            <label for="cedula">Cédula: </label>
                                            <div class="controls">
                                                <input type="text" name="cedula" id="cedula" value="${cedula}" class="form-control">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label for="nombre">Nombre:</label>
                                            <div class="controls">
                                                <input type="text" name="nombre" id="nombre" value="${nombre}" class="form-control">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label for="celular">Celular:</label>
                                            <div class="controls">
                                                <input type="text" name="celular" id="celular" value="${celular}" class="form-control">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label for="email">Email: </label>
                                            <div class="controls">
                                                <input type="text" name="email" id="email" value="${email}" class="form-control">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label for="direccion">Dirección </label>
                                            <div class="controls">
                                                <input type="textarea" name="direccion" id="direccion" value="${direccion}" class="form-control">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label></label>
                                            <div class="controls">
                                                <input type="submit" name="signup" value="Crear Cliente" class="btn btn-success">
                                            </div>
                                        </div>
                                    </form>
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
