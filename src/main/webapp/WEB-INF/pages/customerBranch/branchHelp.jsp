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
	
	$("#flip9").click(function(){
        $("#panel9").slideToggle("slow");
    });
	
	$("#flip10").click(function(){
        $("#panel10").slideToggle("slow");
    });
	
	$("#flip11").click(function(){
        $("#panel11").slideToggle("slow");
    });
	
});
</script>

<style> 
#panel, #flip,#panel1, #flip1,#panel2, #flip2,#panel3, #flip3,#panel4, #flip4,#panel5, #flip5,#panel6, #flip6,#panel7, #flip7,#panel8,#flip8,#panel9,#flip9,#panel10,#flip10,#panel11,#flip11  {
    padding: 6px;
    background-color: #C9C9C9;
    border: solid 1px #737373;
    color: #095679;
    font-family: sans-serif;
    letter-spacing: 0.5px;
}

#panel,#panel1,#panel2,#panel3,#panel4,#panel5,#panel6,#panel7,#panel8,#panel9,#panel10,#panel11 {
    padding: 10px;
    display: none;
}
</style>
</head>
<body>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.customerBranchHelp"/></h3>
	</div>	
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div id="flip"><spring:message code="label.customerBranchHelp1"/></div>
		<div id="panel"><spring:message code="label.customerBranchHelp2"/></div>
		<br>
		<div id="flip1"><spring:message code="label.customerBranchHelp3"/></div>
		<div id="panel1"><spring:message code="label.customerBranchHelp4"/></div>
		<br>
		<div id="flip2"><spring:message code="label.customerBranchHelp5"/></div>
		<div id="panel2"><spring:message code="label.customerBranchHelp6"/>
		
		</div>
		<br>
		<div id="flip3"><spring:message code="label.customerBranchHelp7"/></div>
		<div id="panel3"><spring:message code="label.customerBranchHelp8"/></div>
		<br>
		<div id="flip4"><spring:message code="label.customerBranchHelp9"/></div>
		<div id="panel4"><spring:message code="label.customerBranchHelp10"/></div>
		<br>
		<div id="flip5"><spring:message code="label.customerBranchHelp11"/></div>
		<div id="panel5"><spring:message code="label.customerBranchHelp12"/></div>
		<br>
		<div id="flip6"><spring:message code="label.customerBranchHelp13"/></div>
		<div id="panel6"><spring:message code="label.customerBranchHelp14"/></div>
		
		<br>
		<div id="flip7"><spring:message code="label.customerBranchHelp15"/></div>
		<div id="panel7"><spring:message code="label.customerBranchHelp16"/></div>
		
		<br>
		<div id="flip8"><spring:message code="label.customerBranchHelp17"/></div>
		<div id="panel8"><spring:message code="label.customerBranchHelp18"/></div>
		
		<br>
		<div id="flip9"><spring:message code="label.customerBranchHelp19"/></div>
		<div id="panel9"><spring:message code="label.customerHeadHelp20"/></div>
		
		<br>
		<div id="flip10"><spring:message code="label.customerBranchHelp21"/></div>
		<div id="panel10"><spring:message code="label.customerBranchHelp22"/></div>
		
		
		
		
	</div>
</div>
</body>
</html>
