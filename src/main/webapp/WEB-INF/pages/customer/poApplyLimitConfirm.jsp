<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
			</div>	
			<form:form action="poApplyLimitPost" name="regulation" commandName="limitBurstForm" onsubmit="return validateForm()">
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
									<tr>
									<form:hidden path="id"/>
									<form:hidden path="customerEmail"/>
									<form:hidden path="transactionId"/>
								
					  <td class="heading_text"><b><spring:message code="label.customerName"/></b></td>
					<td>
					<form:input path="customerHeadName" id="customerHeadName" readonly="true" />
					</td>
				    </tr>
				    <tr>
				     <td class="heading_text"><b><spring:message code="label.supplierName"/></b></td>
					<td>
					<form:input path="supplierName" id="supplierName" readonly="true" />
					</td>
				    </tr>
				    <tr>
					  <td class="heading_text"><b><spring:message code="label.masterKey"/></b></td>
					<td>
					<form:input path="masterKey" id="masterKey" readonly="true" />
					</td>
				    </tr>
				     <tr>
					  <td class="heading_text"><b><spring:message code="label.poKey"/></b></td>
					<td>
					<form:input path="poKey" id="poKey" readonly="true" />
					</td>
				    </tr>
				     <tr>
					  <td class="heading_text"><b><spring:message code="label.amount"/></b></td>
					<td>
					<form:input path="reqAmt" id="reqAmt" readonly="true" />
					</td>
				    </tr>
				     <tr>
					  <td class="heading_text"><b><spring:message code="label.tenure"/></b></td>
					<td>
					<form:input path="tenure" id="tenure" readonly="true" />
					</td>
				    </tr>
				
					</table>
					</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="masterPlanLimitBurstList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
			</form:form>
	</div>