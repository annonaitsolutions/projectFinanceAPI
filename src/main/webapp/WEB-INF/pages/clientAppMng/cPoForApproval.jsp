<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
<form:form action="cPoForApprovalConfirm" commandName="purchaseOrderForm" onsubmit="return val();">
<div class="col-sm-12 col-md-12 col-lg-12 header_customer"><h3 align="center"><spring:message code="label.purchaseOrder"/></h3></div>
				<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="flag"  />
									<form:hidden path="customerHeadEmail"  />
									<form:hidden path="customerBranchEmail"  />
								    <form:hidden path="supplierEmail"  />
								        <form:hidden path="poKey"  />
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerName"  readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerHeadName" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.supplierName"/>:</b></td>
										<td class="col-sm-7"><form:input path="supplierName" readonly="true"  id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-7"><form:input path="masterKey" readonly="true"  id="companyName"></form:input>
									</tr>	
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.goods"/>:</b></td>
										<td class="col-sm-7"><form:input path="goods" readonly="true"  id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.quantity"/>:</b></td>
										<td class="col-sm-7"><form:input path="quantity" readonly="true"  id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-7"><form:input path="amount" readonly="true"  id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insType"/>:</b></td>
										<td class="col-sm-7"><form:input path="insuranceType" id="insuranceType" readonly="true" placeholder="Enter Insurance Type"></form:input>
										
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insAmt"/>:</b></td>
										<td class="col-sm-7"><form:input path="insuranceAmount" id="insuranceAmount" readonly="true" placeholder="Enter Insurance Amount"></form:input>
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insDetails"/>:</b></td>
										<td class="col-sm-7"><form:textarea path="insuranceDetails" readonly="true" id="insuranceDetails" placeholder="Enter Insurance Details"></form:textarea>
									
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.startDate"/>:</b></td>
										<td class="col-sm-7"><form:input path="startDate" readonly="true" id="startDate" placeholder="Enter Date"></form:input>
									
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.endDate"/>:</b></td>
										<td class="col-sm-7"><form:input path="endDate" readonly="true" id="insuranceDetails" placeholder="Enter Insurance Details"></form:input>
									
										
									</tr>
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.status"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-7"><form:select path="status" id="status" style="width:205px;">
												 <form:option value=""><spring:message code="label.selectValue"/></form:option>
											    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
												<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
												</form:select>
											<td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/></font></td>
										    <td id="statusError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></td>
											</td>	
																									
									</tr>
									<form:hidden path="transactionId"  />
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.comment"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:textarea path="comment" id="comment" placeholder="Enter Comment"></form:textarea>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="commentError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
										</td>
									</tr>
									
							</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="cPoListForApproval" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				</div>
								


</form:form>
	<script>
		
			
				 
				function val(){
					
					var userName  = document.getElementById('comment');
					

					var canSubmit = true; 
					
					if (document.getElementById('comment').value == ''){
						document.getElementById('commentError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('commentError').style.display='none';
					}
					if (document.getElementById('status').value == ''){
						document.getElementById('statusError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('statusError').style.display='none';
					}

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
</div>

