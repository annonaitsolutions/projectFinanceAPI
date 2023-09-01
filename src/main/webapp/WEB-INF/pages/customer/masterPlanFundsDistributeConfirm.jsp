<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	
<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.fund.transactionId.value = uuid; */
	}
</script>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
	</div>
		<div class="col-sm-12 col-md-12 col-lg-12">			
			<form:form action="masterPlanFundsDistributePost" name="fund" commandName="fundsDistributeForm" onsubmit="return validateForm()">
				<table align="center">
					<tr>
							<form:hidden path="transactionId" value="" />
							<form:hidden path="flag"/>
							<form:hidden path="id"/>
							<form:hidden path="distributedAmount"/>
					 		<td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
							<td class="col-sm-6"><form:input path="customerHeadName" id="customerHeadName" readonly="true" /></td>
				    </tr>
				    <tr>
					  		<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
							<td class="col-sm-6"><form:input path="masterKey" id="masterKey" readonly="true" /></td>
				    </tr>
				    
				    <tr>
					 		<td class="col-sm-6"><b><spring:message code="label.sanctionedAmount"/></b></td>
							<td class="col-sm-6">
								<form:input path="buyingCostSanc" id="buyingCostSanc" readonly="true" />
							</td>
				    </tr>
				    <tr>
					 		 <td class="col-sm-6"><b><spring:message code="label.distAmt"/></b></td>
							<td class="col-sm-6">
								<form:input path="distributedAmount" id="distributedAmount" readonly="true" />
							</td>
				    </tr>
				      <tr>
					 		 <td class="col-sm-6"><b><spring:message code="label.customerHeadEmail"/></b></td>
							<td class="col-sm-6">
							<form:input path="custHeadEmail" id="custHeadEmail" readonly="true" />
					</td>
				    </tr>
				    <tr>
					 		 <td class="col-sm-6"><b><spring:message code="label.balance"/></b></td>
							<td class="col-sm-6">
							<form:input path="balance" id="buyingCostSanc" readonly="true"/>
					</td>
				    </tr>
					
				<%-- 	<tr>
							<td class="col-sm-6"><b><spring:message code="label.distrib"/>:</b></td>
							<td class="col-sm-6">
							<input type=radio readonly="readonly" name=regulation value='Banned'  onClick="radio_check()";><spring:message code="label.br"/>
                                      <input type=radio readonly="readonly" name=regulation value='Not Banned'onClick="radio_check()";><spring:message code="label.subs"/><br />
                                    </td>
						</tr>  --%>
					<tr>
					 	 <td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
						<td class="col-sm-6">
						<form:input path="customerName" id="customerName" readonly="true"/>
					</td>
				    </tr> 
									
					<tr>
					 <td class="col-sm-6"><b><spring:message code="label.amount"/></b></td>
					<td class="col-sm-6">
					<form:input path="amount" id="amount" readonly="true"/>
					</td>
				    </tr>
					</table>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="masterPlanFunds" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
			</form:form>
</div>
</div>