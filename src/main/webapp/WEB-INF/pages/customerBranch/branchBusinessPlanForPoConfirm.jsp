<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script>

function validateForm() {

 }
  
   </script>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

		<div class="successMsg"
			style="text-align: center; color: green; font-size: 18px;">${success}</div>
		<form:form action="branchBusinessPlanForPoPost" method="post"
				commandName="purchaseOrderForm" name="msplan" onsubmit="return validateForm()" >
	<br>
				
				<div class="col-sm-12 col-md-6 col-lg-6">
					<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3 align="center"><spring:message code="label.suppDetails"/></h3>
					</div>
					<table align="center">
         
			         <form:hidden path="customerHeadName" />
			         <form:hidden path="customerHeadEmail" />
			         <form:hidden path="customerBranchEmail" />
			         <form:hidden path="customerName" />
			         <form:hidden path="utilizedBusnsAmt"  />
			         <form:hidden path="balance"  />
			         <form:hidden path="masterKey"  />
			         <form:hidden path="poKey"  value=""/>
         			 <form:hidden path="transactionId"  value="" />
						 <tr>
					<td class="heading_text"><b><spring:message code="label.supplierName"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierName"  id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="heading_text"><b><spring:message code="label.supplierEmail"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierEmail" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="heading_text"><b><spring:message code="label.supplierBank"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierBank"  id="name" readonly="true"/>
					</td>
				</tr>
                  <tr>
					<td class="heading_text"><b><spring:message code="label.supplierBankEmail"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierBankEmail"  id="name" readonly="true"/>
					</td>
				</tr>
                    <tr>
					<td class="heading_text"><b><spring:message code="label.supplierCountry"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="Suppliercountry"  id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="heading_text"><b><spring:message code="label.supplierState"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierState" id="name" readonly="true"/>
					</td>
				</tr>
						
					</table>
				</div>
					
					<div class="col-sm-12 col-md-6 col-lg-6">
					<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3 align="center"><spring:message code="label.productInfo"/></h3>
					</div>
					<table align="center">
						<tr>
							<td class="heading_text"><b><spring:message code="label.goodsCategory"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goodsCategory"  id="name" readonly="true"/></td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.purchaseDate"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="purchaseDate" id="purchaseDate" readonly="true" /></td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.goods"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goods" id="goods" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.country"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="country" id="country" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.state"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="state" id="state" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.details"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goodsDetails" id="goodsDetails" readonly="true"  />
							</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.quantity"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="quantity" id="quantity"  readonly="true"/>
								</td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.whName"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="wareHouseName" id="wareHouseName"  readonly="true"/>
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.weight"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="weight" id="weight" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.license"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
								<td><form:input path="licence" id="weight" readonly="true" />
																			   </td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.amount"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="amount" id="amount" readonly="true"/>
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.tenure"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="tenure" id="tenure" readonly="true"/>
								</td>
						</tr>
						
						
					

				</table>
					</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="branchBusinessPlan" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
					
					</form:form>
</div>