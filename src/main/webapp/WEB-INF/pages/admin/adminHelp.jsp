<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.3.min.js"></script>
<script> 
$(document).ready(function(){
    $("#flip").click(function(){
        $("#panel").slideToggle("slow");
    });
	$("#flip1").click(function(){
        $("#panel1").slideToggle("slow");
    });
	$("#flip2").click(function(){
        $("#panel2").slideToggle("slow");
    });
	$("#flip3").click(function(){
        $("#panel3").slideToggle("slow");
    });
	$("#flip4").click(function(){
        $("#panel4").slideToggle("slow");
    });
	$("#flip5").click(function(){
        $("#panel5").slideToggle("slow");
    });
	$("#flip6").click(function(){
        $("#panel6").slideToggle("slow");
    });
});
</script>

<style> 
#panel, #flip,#panel1, #flip1,#panel2, #flip2,#panel3, #flip3,#panel4, #flip4,#panel5, #flip5,#panel6, #flip6 {
    padding: 6px;
    background-color: #C9C9C9;
    border: solid 1px #737373;
    color: #095679;
    font-family: sans-serif;
    letter-spacing: 0.5px;
}

#panel,#panel1,#panel2,#panel3,#panel4,#panel5,#panel6 {
    padding: 10px;
    display: none;
}
</style>
</head>
<body>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.bankAdminHelpModule"/></h3>
	</div>	
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div id="flip"><spring:message code="label.createRole"/></div>
		<div id="panel"><spring:message code="label.createRoleHelpData"/></div>
		<br>
		<div id="flip1"><spring:message code="label.sendNotification"/></div>
		<div id="panel1"><spring:message code="label.sendNotificationHelpData"/></div>
		<br>
		<div id="flip2"><spring:message code="label.helpDetails"/></div>
		<div id="panel2"><spring:message code="label.detailsHelpData"/></div>
		<br>
		<div id="flip3"><spring:message code="label.regulation"/></div>
		<div id="panel3"><spring:message code="label.regulationHelpData"/></div>
		<br>
		<div id="flip4"><spring:message code="label.bankDetailsHelp"/></div>
		<div id="panel4"><spring:message code="label.bankDetailsHelpData"/></div>
		<br>
		<div id="flip5"><spring:message code="label.blockRenewAccountsHelp"/></div>
		<div id="panel5"><spring:message code="label.blockRenewAccountsHelpData"/></div>
		<br>
		<div id="flip6"><spring:message code="label.swapRevertAccountHelp"/></div>
		<div id="panel6"><spring:message code="label.swapRevertAccountHelpData"/></div>
		
	</div>
</div>
</body>
</html>
