<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script language="javascript">

function simple()
{
var p,r,n,i;
if(document.interest.principle.value == " " )
{
alert("PLEASE ENTER RATE OF EXCHANGE");
document.interest.principle.alert();
}
else if(document.interest.rate.value=="")
{
alert("ENTER RATE OF MARK UP");
document.interest.rate.blur();

}
else if(document.interest.value.value=="")
{
alert("ENTER NUMBER OF AMONT TO BE CONVERTED");
document.interest.value.prompt();
}
else
{
p=parseInt(document.interest.principle.value);

r=parseInt(document.interest.rate.value);

n=parseInt(document.interest.value.value);

i=n*(p+r);

document.interest.answer.value="" + i;
}
}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12">
		<form method="post" action="" name="interest">
		<h1 align="center"><spring:message code="label.curConvert"/></h1>
		<table align="center">
		<tr>
			<td class="col-sm-6">
				<label><spring:message code="label.from"/> :</label>
				<select id="xxfrom">
				<option value="USD"><spring:message code="label.usDollar"/></option>
				<option value="GBP">&pound;<spring:message code="label.britPound"/></option>
				<option value="EUR"><spring:message code="label.eurEuro"/></option>
				<option value="CAD"><spring:message code="label.canadian"/></option>
				<option value="AUD"><spring:message code="label.australian"/></option>
				<option value="JPY"><spring:message code="label.japYen"/></option>
				<option value="RUP">&#x20B9;<spring:message code="label.inRup"/></option>
				</select>
			</td>
			<td class="col-sm-6">
				<label><spring:message code="label.to"/>:</label>
			</td>
			<td class="col-sm-6">
				<select id="xxto">
				<option value="RUP">&#x20B9;<spring:message code="label.inRup"/></option>
				<option value="EUR"><spring:message code="label.eurEuro"/></option>
				<option value="USD"><spring:message code="label.usDollar"/></option>
				<option value="GBP">&pound;<spring:message code="label.britPound"/></option>
				<option value="CAD"><spring:message code="label.canadian"/></option>
				<option value="AUD"><spring:message code="label.australian"/></option>
				<option value="JPY"><spring:message code="label.japYen"/></option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="col-sm-6"><spring:message code="label.excRate"/></td>
			<td class="col-sm-6"><input type="text" name="principle" placeholder="Enter Rate Of Exchange" size="7"></td>
		</tr>
		
		<tr>
			<td class="col-sm-6"><spring:message code="label.markUp"/></td>
			<td class="col-sm-6"><input type="text" name="rate" placeholder="Enter Mark Up" size="7"></td>
		</tr>
		
		<tr>
			<td class="col-sm-6"><spring:message code="label.amtToCalc"/></td>
			<td class="col-sm-6"><input type="text" name="value" placeholder="Enter Amount To be Converted" size="7"></td>
		</tr>
		
		
		<tr>
			<td colspan="2" class="col-sm-12"><input type="button" value="CALCULATE" size="5" class="btn btn-success" onClick="simple()">
			<input type="reset" class="btn btn-primary" value="clear"></td>
		
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="col-sm-6"><b><spring:message code="label.convAmt"/></B></td>
			<td class="col-sm-6"><input type="text" name="answer" placeholder="Enter Convert Amount" size="7"></td>
		</tr>
		
		</table>
		</form>
</div>
</div>