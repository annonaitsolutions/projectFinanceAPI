<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.invDetails"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				 <div  class="sucess"><b><font color="green">${success}</font></b></div>
			</div>
			 <div class="col-sm-12 col-md-12 col-lg-12">
				
			<form:form action="invoiceInventoryupdateBranchShowSave" method="post"
				commandName="inventoryForm" name="inventory"
				onsubmit="return validateForm()">
				
				<div class="col-sm-12 col-md-12">
					<table align="center">
	
				     <form:hidden path="inventoryType"/>
				     <form:hidden path="id"/>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="customerName" id="customerName"
									readonly="true" /></td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="customerHeadName" id="customerName"
									readonly="true" /></td>
						</tr>
						<%-- <tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="buyerName" id="supplierName"
									readonly="true" /></td>
						</tr> --%>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="masterKey" id="masterKey"
									readonly="true" /></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.poKey"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="poKey" id="poKey" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.amount"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="amount" id="amount" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.avaQty"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="avail" id="avail"
									readonly="true" /></td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.usedQuantity"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="usedQuantity" id="usedQuantity"
									readonly="true" /></td>
						</tr>
						

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.damagedQuantity"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="damaged" id="damaged" readonly="true" />
							</td>
						</tr>


						<tr>
							<td class="col-sm-6"><b><spring:message code="label.quantityRemaining"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="total" id="total" readonly="true" /></td>
						</tr>

					</table>





				</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
							<td><a href="inventoryInvoiceBranchList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
			</form:form>
			</div>
			</div>