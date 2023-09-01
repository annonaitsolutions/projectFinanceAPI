<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- To fetch the request url -->
<c:set var="req" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="baseURL" value="${fn:split(req,'/')}"/>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
    <div class="right-container" id="right-container">
        <div class="container-fluid">
            <div class="Flexi_deposit">

                <div class="header_customer">
                    <h3>
                        <spring:message code="label.loginDate"/>
                    </h3>
                </div>
                <div class="Success_msg">
                    <div class="successMsg" style="text-align: center; color: red;">
                        ${error}</div>
                </div>
                <div class="flexi_table" style="margin-top: 7px;">
                    <form:form action="saveLoginDate" class="form-horizontal"
                               autocomplete="off" commandName="loginDateForm" name="login" method="post"
                               onsubmit="return val()">
                        <%--                    <input type="hidden" name="menuId" id="menuId" value="${menuId}" />--%>
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <div class="form-group" style="align-items: center">

                                    <%--                            <label class="col-md-6 control-label" style="padding-top: 16px;"><spring:message--%>
                                    <%--                                    code="label.loginDate" /><span style="color: red">*</span></label>--%>

                                    <%--                            <div class="col-md-6">--%>
                                    <%--                                <form:input path="loginDateString" id="loginDate"--%>
                                    <%--                                            placeholder="Select Date"--%>
                                    <%--                                            class="myform-control " type="date" value="${loginDateString}"/>--%>
                                    <%--                            </div>--%>
                                <label class="col-md-6 control-label" style="margin-top:7px;"><spring:message
                                        code="label.loginDate"/><span style="color: red">*</span></label>

                                <div class="col-md-6 control-label">
                                    <form:input path="loginDateString" id="loginDate"
                                                placeholder="Select Date"
                                                class="myform-control" type="date" value="${loginDateString}"/>
                                </div>
                            </div>
                            <div
                                    style="text-align: left; margin-left: 250px; margin-top: 50px;">

                                <tr>
                                    <td><span id='validationError' style="color: red"></span></td>
                                </tr>
                                <tr>

                                    <td><input type="submit" size="3"
                                               style="margin-bottom: 17px;"
                                               value="<spring:message code="label.save"/>"
                                               class="btn btn-primary"></td>
                                </tr>
                            </div>
                        </div>

                        <sec:csrfInput/>


                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function val() {
        debugger;
        var validationError = document.getElementById('validationError');
        validationError.innerHTML = "";
        var errorMsg = "";
        var submit = true;
        var date = document.getElementById('loginDate').value;
        if (date == null || date == '') {
            document.getElementById('loginDate').style.borderColor = "red";
            errorMsg = "<br>Please insert the date.";
            validationError.innerHTML += errorMsg;
            return false;
        }

        var parts = date.split('/');
        var mydate = new Date(parts[2], parts[1] - 1, parts[0]);
        var userDate = mydate.getFullYear() + '/' + (mydate.getMonth() + 1) + '/' + mydate.getDate(); //2021/09/20

        //  var today = getTodaysDate();
        var today = getLoginDate();
// 	    var todayDate = null;

        // if(today==null || today == undefined){
        //     document.getElementById('loginDate').style.borderColor = "red";
        //     errorMsg = "SOMETHING WENT WRONG !!";
        //     validationError.innerHTML += errorMsg;
        //     return false;
        // }
// 	    else{
// 	    	  todayDate = today.getFullYear()+'/'+(today.getMonth()+1)+'/'+today.getDate();//2021/10/21
// 	    }

        // if (userDate<today) {
        //     document.getElementById('loginDate').style.borderColor = "red";
        //     errorMsg = "<br>Past Date Selected.";
        //     validationError.innerHTML += errorMsg;
        //     return false;
        // }

        return submit;
    }


    function getLoginDate() {
        debugger;
        var today = null;

        $.ajax({
            type: "GET",
            async: false,
            url: "<%=request.getContextPath()%>/main/loginDate",
            contentType: "application/json",
            dataType: "json",
            headers: {"${_csrf.headerName}": "${_csrf.token}"},
            success: function (response) {
                //	window.loginDateForFront = new Date(parseInt(response));
                today = new Date(parseInt(response))
            },
            error: function (e) {
                $('#error').html("Error occured!!")
                // window.loginDateForFront = getTodaysDate();
            }
        });
        return today;
    }

    var vars = [], hash;

    function getUrlVars() {

        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for (var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }

    $(document).ready(function () {
        debugger;
        var menuId = getUrlVars()["menuId"];
        var permissions = $("#menu_" + menuId).attr("permissions");
        //alert(permissions);
        // if (permissions.toLowerCase().indexOf("write") < 0) {
        //     $('input[type="Submit"]').hide();
        // }
    });
</script>

<style type="text/css">
    form {
        margin: 20px 0;
    }

    form input, button {
        padding: 5px;
    }

    table {
        width: 100%;
        margin-bottom: 20px;
        border-collapse: collapse;
    }

    table, th, td {
        border: 1px solid #cdcdcd;
    }

    table th, table td {
        padding: 10px;
        text-align: left;
    }

    input[type=checkbox], input[type=radio] {
        margin: 4px 1px 0px;
        margin-top: 1px \9;
        line-height: normal;
        zoom: 1.0;
    }

    .form-horizontal .control-label {
        padding-top: 0;
    }
</style>