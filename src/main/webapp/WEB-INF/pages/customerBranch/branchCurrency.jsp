<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<script language="JavaScript">
function euroConverter(){
document.converter.dollar.value = document.converter.euro.value * 1.470
document.converter.pound.value = document.converter.euro.value * 0.717
document.converter.yen.value = document.converter.euro.value * 165.192
document.converter.rupee.value=document.converter.euro.value *  74.15
document.converter.riyal.value=document.converter.euro.value * 4.29
document.converter.yuhan.value=document.converter.euro.value*7.23
}
function dollarConverter(){
document.converter.euro.value = document.converter.dollar.value * 0.680
document.converter.pound.value = document.converter.dollar.value * 0.488
document.converter.yen.value = document.converter.dollar.value * 112.36
document.converter.rupee.value=document.converter.dollar.value * 64.80
document.converter.riyal.value=document.converter.dollar.value * 3.75
document.converter.yuhan.value=document.converter.dollar.value*6.36
}
function poundConverter(){
document.converter.dollar.value = document.converter.pound.value * 2.049
document.converter.euro.value = document.converter.pound.value * 1.394
document.converter.yen.value = document.converter.pound.value * 230.27
document.converter.rupee.value=document.converter.pound.value * 100.35
document.converter.riyal.value=document.converter.pound.value * 5.81
document.converter.yuhan.value=document.converter.pound.value* 9.84
}
function yenConverter(){
document.converter.dollar.value = document.converter.yen.value * 0.0089
document.converter.pound.value = document.converter.yen.value * 0.00434
document.converter.euro.value = document.converter.yen.value * 0.00605
document.converter.rupee.value=document.converter.yen.value * 0.55
document.converter.riyal.value=document.converter.yen.value * 0.032 
document.converter.yuhan.value=document.converter.yen.value*0.053 
}
function rupeeConverter(){
document.converter.dollar.value = document.converter.rupee.value *  0.015
document.converter.pound.value = document.converter.rupee.value * 0.0100
document.converter.euro.value = document.converter.rupee.value * 0.013
document.converter.yen.value=document.converter.rupee.value*1.83
document.converter.riyal.value=document.converter.rupee.value*0.058
document.converter.yuhan.value=document.converter.rupee.value*0.098
	}
function riyalConverter(){
	document.converter.dollar.value = document.converter.riyal.value * 0.27
	document.converter.pound.value = document.converter.riyal.value *0.17
	document.converter.euro.value = document.converter.riyal.value * 0.23
	document.converter.yen.value=document.converter.riyal.value* 31.57
	document.converter.rupee.value=document.converter.riyal.value*17.28
	document.converter.yuhan.value=document.converter.riyal.value*1.69
		}
function chineseConverter(){
	document.converter.dollar.value = document.converter.yuhan.value * 0.16
	document.converter.pound.value = document.converter.yuhan.value *0.10
	document.converter.euro.value = document.converter.yuhan.value * 0.14
	document.converter.yen.value=document.converter.yuhan.value* 18.74
	document.converter.rupee.value=document.converter.yuhan.value*10.21
	document.converter.riyal.value=document.converter.yuhan.value*0.59
		}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<form name="converter">
		<table  align="center">
			<tr>
				<td class="col-sm-6"><b><spring:message code="label.euro"/> (&euro;):</b></td>
				<td class="col-sm-6"><input type="text" name="euro" placeholder="Enter Euro" onChange="euroConverter()" /></td>
			</tr>
			<tr>
				<td class="col-sm-6"><b><spring:message code="label.dollar"/>:</b> </td>
				<td class="col-sm-6"><input type="text" name="dollar"placeholder="Enter Dollar" onChange="dollarConverter()" /></td>
			</tr>
			<tr>
				<td class="col-sm-6"><b><spring:message code="label.pound"/> (&pound;):</b></td>
				<td class="col-sm-6"><input type="text" name="pound" placeholder="Enter Pound" onChange="poundConverter()" /></td>
			</tr>
			<tr>
				<td class="col-sm-6"><b><spring:message code="label.yen"/> (&yen;):</b> </td>
				<td class="col-sm-6"><input type="text" name="yen" placeholder="Enter Yen" onChange="yenConverter()" /></td>
			</tr>
			<tr>
				<td class="col-sm-6"><b><spring:message code="label.indRup"/> (&#x20B9;) :</b> </td>
				<td class="col-sm-6"><input type="text" name="rupee" placeholder="Enter IndRupee" onChange="rupeeConverter()" /></td>
			</tr>
			<tr>
				<td class="col-sm-6"><b><spring:message code="label.saudi"/> : </b> </td>
				<td class="col-sm-6"><input type="text" name="riyal"placeholder="Enter saudi Riyal"  onChange="riyalConverter()" /></td>
			</tr>
			<tr>
				<td class="col-sm-6"><b><spring:message code="label.yuhan"/> (&yen;) :</b> </td>
				<td class="col-sm-6"><input type="text" name="yuhan" placeholder="Enter Yuhan" onChange="chineseConverter()" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="Convert!" /></td>
			</tr>
		</table>
		</form>
	</div>
</div>