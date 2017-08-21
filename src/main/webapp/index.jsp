<%--
  Created by IntelliJ IDEA.
  User: Mathenge
  Date: 8/18/2017
  Time: 12:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bank Account | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="assets/dist/css/AdminLTE.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="assets/plugins/iCheck/flat/blue.css">
    <!-- Date Picker -->
    <link rel="stylesheet" href="assets/plugins/datepicker/datepicker3.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- Header -->
    <header class="main-header">
        <!-- Logo -->
        <a href="" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>B</b>A</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Bank</b>Account</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="assets/img/user2-160x160.jpg" class="user-image" alt="User Image">
                            <span class="hidden-xs">Bank User</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- SideBar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="assets/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Bank Admin</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <!-- search form -->
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
                </div>
            </form>
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul id="main-menu" class="sidebar-menu nav">
                <li class="header">MAIN NAVIGATION</li>

                <li id="dashboard-li" class="active treeview">
                    <a href="javascript:void(0);">
                        <i class="fa fa-dashboard"></i> <span>Dashboard</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                </li>

                <li id="main-settings-li" class="active treeview">
                    <a href="javascript:void(0);">
                        <i class="fa fa-edit"></i>Settings<i class="fa fa-angle-right pull-right"></i>
                    </a>
                </li>

            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper" id="dashboard">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Transactions Summary
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Dashboard</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Info boxes -->
            <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-aqua"><i class="ion ion-ios-calculator-outline"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">Total Transactions</span>
                            <span class="info-box-number"> ${totalTransactionCount} </span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div><!-- /.col -->
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-red"><i class="fa fa-money"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">Total Deposits</span>
                            <span class="info-box-number"> <fmt:formatNumber value = "${totalDeposits}" type = "currency"/> </span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div><!-- /.col -->

                <!-- fix for small devices only -->
                <div class="clearfix visible-sm-block"></div>

                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-green"><i class="ion ion-ios-redo-outline"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">Total Withdrawals</span>
                            <span class="info-box-number"> <fmt:formatNumber value = "${totalWithdrawals}" type = "currency"/> </span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div><!-- /.col -->
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-yellow"><i class="ion ion-ios-people-outline"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">Total Amount<br> Transacted</span>
                            <span class="info-box-number"><fmt:formatNumber value = "${totalTransacted}" type = "currency"/></span>
                        </div><!-- /.info-box-content -->
                    </div><!-- /.info-box -->
                </div><!-- /.col -->
            </div><!-- /.row -->

            <!-- Main row -->
            <div class="row">
                <!-- Left col -->
                <div class="col-md-12">

                    <div class="row">

                    <!-- TABLE: LATEST ORDERS -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">Latest Transactions</h3>
                            <div class="box-tools pull-right">
                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                            </div>
                        </div><!-- /.box-header -->
                        <div class="box-body">
                            <div class="table-responsive">
                                <table class="table no-margin">

                                    <thead><tr><th>Date</th><th>Transaction Type</th><th>Amount</th></tr></thead>
                                    <tbody>
                                        <c:forEach var="transactions" items="${transactions}">
                                            <tr><td>${ transactions.transaction_date }</td><td>${ transactions.transaction_type }</td><td><fmt:formatNumber value = "${ transactions.transaction_amount }" type = "currency"/></td></tr>
                                        </c:forEach>
                                    </tbody>

                                </table>
                            </div><!-- /.table-responsive -->
                        </div><!-- /.box-body -->
                        <!--<div class="box-footer clearfix">
                            <a href="" class="btn btn-sm btn-info btn-flat pull-left">Place New Order</a>
                            <a href="" class="btn btn-sm btn-default btn-flat pull-right">View All Orders</a>
                        </div><!-- /.box-footer -->
                    </div><!-- /.box -->
                </div><!-- /.col -->
                </div>
            </div><!-- /.row -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
        </div>
        <strong>Copyright &copy; 2017.</strong> All rights reserved.
    </footer>

</div> <!-- ./wrapper -->

<!-- jQuery 2.1.4 -->
<script src="assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.5 -->
<script src="assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="assets/bootstrap/js/bootstrapValidator.min.js"></script>
<!-- Slimscroll -->
<script src="assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- AdminLTE App -->
<script src="assets/dist/js/app.min.js"></script>
<script type="text/javascript" src="assets/custom/js/bootbox.min.js"></script>

<script type="text/javascript">

    var menu_done = false;

    var content_done = false;

    function start_wait() {
        $('#wait-dialog').modal({
            backdrop : 'static',
            keyboard : false
        });
    }
    function stop_wait() {
        $('#wait-dialog').modal('hide');
    }

    function loadDashboard(MODULE)
    {
        $.ajax({
            async: true,
            url: "http://localhost:8080/BankAccount/" + 'dashboard',
            type: 'get',
            data: {dashboard: MODULE},
            dataType: 'html',
            success: function(html) {
                $('#dashboard').fadeOut('slow', function() {
                    content_done = true;
                    if(menu_done)
                    {
                        stop_wait();
                    }
                    $('#dashboard').html(html);
                    $('#dashboard').fadeIn('slow');
                });
            }
        });
    }

    $(document).ready(function(){

        function m_switch(MODULE)
        {
            menu_done = true;
            start_wait();
            loadDashboard(MODULE);
        }

        $('#main-settings-li').click(function(){

            $('#main-settings-li').addClass('active');

            m_switch("SETTINGS");
        });

        $('#dashboard-li').click(function(){

            start_wait();

            window.location.href = "http://localhost:8080/BankAccount/summary";

        });

    });

</script>
</body>
</html>
