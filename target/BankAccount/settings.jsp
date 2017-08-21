<%--
  Created by IntelliJ IDEA.
  User: Mathenge
  Date: 8/18/2017
  Time: 12:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Edit Maximum Limits
                <small>Settings</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Settings</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Limits Edit-->
            <div class="box box-default">
                <form method="post" id="limits-form">
                <div class="box-header with-border">
                    <h3 class="box-title">Deposit Limits</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-remove"></i></button>
                    </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="maxDailyDeposit">Max Daily Deposit Amount</label>
                                <div class="input-group">
                                    <span class="input-group-addon">$</span>
                                    <input type="text" name="maxDailyDeposit" id="maxDailyDeposit" class="form-control"
                                           value="${ limits.daily_deposit }">
                                    <span class="input-group-addon">.00</span>
                                </div>
                            </div><!-- /.form-group -->
                            <div class="form-group">
                                <label for="maxDepositTransaction">Max Deposit per Transaction</label>
                                <div class="input-group">
                                    <span class="input-group-addon">$</span>
                                    <input type="text" name="maxDepositTransaction" id="maxDepositTransaction" class="form-control"
                                           value="${ limits.deposit_transaction }">
                                    <span class="input-group-addon">.00</span>
                                </div>
                            </div><!-- /.form-group -->
                        </div><!-- /.col -->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="maxDepositFrequency">Max Deposit Frequency </label>
                                <div class="input-group">
                                    <input type="text" name="maxDepositFrequency" id="maxDepositFrequency" class="form-control"
                                           value="${ limits.deposit_frequency }">
                                    <span class="input-group-addon">/ Day</span>
                                </div>
                            </div><!-- /.form-group -->
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.box-body -->
                <div class="box-header with-border">
                    <h3 class="box-title">Withdrawal Limits</h3>
                    <div class="box-tools pull-right">
                    </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="maxDailyWithdrawal">Max Daily Withdrawal Amount</label>
                                <div class="input-group">
                                    <span class="input-group-addon">$</span>
                                    <input type="text" name="maxDailyWithdrawal" id="maxDailyWithdrawal" class="form-control"
                                           value="${ limits.daily_withdrawal }">
                                    <span class="input-group-addon">.00</span>
                                </div>
                            </div><!-- /.form-group -->
                            <div class="form-group">
                                <label for="maxWithdrawalTransaction">Max Withdrawal per Transaction</label>
                                <div class="input-group">
                                    <span class="input-group-addon">$</span>
                                    <input type="text" name="maxWithdrawalTransaction" id="maxWithdrawalTransaction" class="form-control"
                                           value="${ limits.withdrawal_transaction }">
                                    <span class="input-group-addon">.00</span>
                                </div>
                            </div><!-- /.form-group -->
                        </div><!-- /.col -->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="maxWithdrawalFrequency">Max Withdrawal Frequency </label>
                                <div class="input-group">
                                    <input type="text" name="maxWithdrawalFrequency" id="maxWithdrawalFrequency" class="form-control"
                                           value="${ limits.withdrawal_frequency }">
                                    <span class="input-group-addon">/ Day</span>
                                </div>
                            </div><!-- /.form-group -->
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.box-body -->
                <div class="box-footer">
                    <button type="submit" id="save-settings" class="btn btn-primary">Submit</button>
                </div>
                </form>
            </div><!-- /.box -->

        </section><!-- /.content -->
</html>
<script type="text/javascript">

    $(document).ready(function(){

        $('#limits-form')
            .bootstrapValidator(
                {
                    excluded: ':disabled',
                    message : 'This value is not valid',
                    feedbackIcons : {
                        valid : 'glyphicon glyphicon-ok',
                        invalid : 'glyphicon glyphicon-remove',
                        validating : 'glyphicon glyphicon-refresh'
                    },
                    fields : {
                        maxDailyDeposit : {
                            validators : {
                                notEmpty : {
                                    message : 'Please enter the Max Daily Deposit Amount'
                                }
                            }
                        },
                        maxDepositTransaction : {
                            validators : {
                                notEmpty : {
                                    message : 'Please enter max Deposit per Transaction'
                                }
                            }
                        },
                        maxDepositFrequency : {
                            validators : {
                                notEmpty : {
                                    message : 'Please enter daily Maximum Deposit Frequency'
                                }
                            }
                        },
                        maxDailyWithdrawal: {
                            validators : {
                                notEmpty : {
                                    message : 'Please enter the Max Daily Withdrawal Amount'
                                }
                            }
                        },
                        maxWithdrawalTransaction : {
                            validators : {
                                notEmpty : {
                                    message : 'Please enter max Withdrawal per Transaction'
                                }
                            }
                        },
                        maxWithdrawalFrequency: {
                            validators : {
                                notEmpty : {
                                    message : 'Please enter daily Maximum Withdrawal Frequency'
                                }
                            }
                        }
                    }
                })
            .on(
                'success.form.bv',
                function(e) {
                    start_wait();
                    // Prevent form submission
                    e.preventDefault();

                    var btn = "btn-other-settings";
                    var form = "limits-form";

                    $('#' + btn).val('Please wait...');
                    $.ajax({
                        url: "http://localhost:8080/BankAccount/" + 'dashboard',
                            type : 'post',
                            data : {
                                ACTION: 'UPDATE_SETTINGS',
                                maxDailyDeposit: $('#maxDailyDeposit')
                                    .val(),
                                maxDepositTransaction: $('#maxDepositTransaction')
                                    .val(),
                                maxDepositFrequency : $('#maxDepositFrequency')
                                    .val(),
                                maxDailyWithdrawal : $('#maxDailyWithdrawal')
                                    .val(),
                                maxWithdrawalTransaction : $('#maxWithdrawalTransaction')
                                    .val(),
                                maxWithdrawalFrequency: $('#maxWithdrawalFrequency')
                                    .val()
                            },
                            dataType : 'json',
                        success: function(json) {
                            $('#' + btn).val('Done');
                            if(json.success)
                            {
                                $('#' + form)[0].reset();

                                html = 'Settings successfully saved';
                            }
                            else
                                html = 'Settings could not be saved';
                            bootbox.alert(html);
                        }
                        });
                });
    });

</script>
