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
	$("#flip7").click(function(){
        $("#panel7").slideToggle("slow");
    });
	$("#flip8").click(function(){
        $("#panel8").slideToggle("slow");
    });
});
</script>

<style> 
#panel, #flip,#panel1, #flip1,#panel2, #flip2,#panel3, #flip3,#panel4, #flip4,#panel5, #flip5,#panel6, #flip6,#panel7, #flip7,#panel8,#flip8  {
    padding: 6px;
    background-color: #C9C9C9;
    border: solid 1px #737373;
    color: #095679;
    font-family: sans-serif;
    letter-spacing: 0.5px;
}

#panel,#panel1,#panel2,#panel3,#panel4,#panel5,#panel6,#panel7,#panel8 {
    padding: 10px;
    display: none;
}
</style>
</head>
<body>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.bankEmployeeHelp"/></h3>
	</div>	
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div id="flip"><spring:message code="label.bankEmployeeHelp1"/></div>
		<div id="panel"><spring:message code="label.bankEmployeeHelp2"/></div>
		<br>
		<div id="flip1"><spring:message code="label.bankEmployeeHelp3"/></div>
		<div id="panel1"><spring:message code="label.bankEmployeeHelp4"/></div>
		<br>
		<div id="flip2"><spring:message code="label.bankEmployeeHelp5"/></div>
		<div id="panel2"><spring:message code="label.bankEmployeeHelp6"/></div>
		<br>
		<div id="flip3"><spring:message code="label.bankEmployeeHelp7"/></div>
		<div id="panel3"><spring:message code="label.bankEmployeeHelp8"/></div>
		<br>
		<div id="flip4"><spring:message code="label.bankEmployeeHelp9"/></div>
		<div id="panel4"><spring:message code="label.bankEmployeeHelp10"/></div>
		<br>
		<div id="flip5"><spring:message code="label.bankEmployeeHelp11"/></div>
		<div id="panel5"><spring:message code="label.bankEmployeeHelp12"/></div>
		<br>
		<div id="flip6"><spring:message code="label.bankEmployeeHelp13"/></div>
		<div id="panel6"><spring:message code="label.bankEmployeeHelp14"/></div>
		
		<br>
		<div id="flip7"><spring:message code="label.bankEmployeeHelp15"/></div>
		<div id="panel7"><spring:message code="label.bankEmployeeHelp16"/></div>
		
		<br>
		<div id="flip8"><spring:message code="label.bankEmployeeHelp17"/></div>
		<div id="panel8"><spring:message code="label.bankEmployeeHelp18"/></div>
		
		
		
	</div>
</div>
</body>
</html>
