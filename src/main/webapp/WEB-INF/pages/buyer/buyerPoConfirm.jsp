<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
function validateForm() {
	
	/*  var d = new Date().getTime();
	var y=document.buyerPOForm.customerHeadName.value;
	  var uuid = y+'PO-xxxxxx'.replace(/[xy]/g, function(c) {
	        var r = (d + Math.random()*16)%16 | 0;
	        d = Math.floor(d/16);
	        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    document.buyerPOForm.poKey.value=uuid;
   
    var d1 = new Date().getTime();
	var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c1) {
		var r1 = (d1 + Math.random() * 16) % 16 | 0;
		d1 = Math.floor(d / 16);
		return (c1 == 'x' ? r1 : (r1 & 0x3 | 0x8)).toString(16);
	});
	document.buyerPOForm.transactionId.value = uuid1;  */


 }
  </script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.purchaseOrder"/></h3>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
	</div>

		<form:form action="buyerPoPost" method="post" commandName="buyerPOForm" name="buyerPOForm" onsubmit="return validateForm()" >

					<form:hidden path="transactionId" value="" />
				      <form:hidden path="poKey" value=""/>
						<form:hidden path="salesOrderId" />
		             <%--   <form:hidden path="customerHeadName"  value=""/> --%>
			<div class="col-sm-12 col-md-12 col-lg-12">	
				<div class="col-sm-6 col-md-6 col-lg-6">	
					<table align="center">
					
						
						<tr>
								<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/>:</b><span
								style="color: red">*</span></td>
								<td class="col-sm-6"><form:input path="customerHeadName" id="customerHeadName" readonly="true"/></td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="name" readonly="true"/></td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/>:</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="email" id="email" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.goodsCategory"/>:</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="goodsCategory" id="goodsCategory"  readonly="true"/>
										</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.purchaseDate"/>:</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="purchaseDate" id="purchaseDate" readonly="true"/>
								</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.goods"/>:</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="goods" id="goods" readonly="true"/>
								</td>
						</tr>
					</table>
			</div>
			<div class="col-sm-6 col-md-6 col-lg-6">
				<table>	
				       <%-- <tr>
							<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b><span style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="masterKey" id="masterKey" readonly="true"/>
								</td>
						</tr> --%>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.details"/>:</b><span style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="goodsDetails" id="goodsDetails" readonly="true"/>
								</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.quantity"/>:</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="quantity" id="quantity" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.weight"/>:</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="weight" id="weight" readonly="true" />
								</td>
						</tr>
					
					<%-- 	<tr>
							<td class="col-sm-6"><b><spring:message code="label.poKey"/>:</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="poKey" id="poKey" readonly="true"/>
								</td>
						</tr> --%>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.amount"/>:</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="amount" id="amount" readonly="true"/>
								</td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.tenure"/>:</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="tenure" id="tenure" readonly="true" />
								</td>
						</tr>
				</table>
				</div>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">	&nbsp;</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="buyers" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
					</form:form>
	</div>