<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
<form:form action="cancelPoAppPost" commandName="purchaseOrderForm" onsubmit="return val();">
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 align="center"><spring:message code="label.purchaseOrder"/></h3>
						</div>
								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="flag"  />
									<form:hidden path="customerHeadEmail"  />
									<form:hidden path="customerBranchEmail"  />
								    <form:hidden path="supplierEmail"  />
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerHeadName" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.supplierName"/>:</b></td>
										<td class="col-sm-7"><form:input path="supplierName" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-7"><form:input path="masterKey" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
									</tr>	
								
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.status"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="status" readonly="true" id="comment" placeholder="Enter Comment"></form:input>
										
									</tr>
									<form:hidden path="transactionId"  />
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.comment"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="cancelComment" readonly="true" id="comment" placeholder="Enter Comment"></form:input>
										
									</tr>
											
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="cancelPoAppList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
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
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
</div>

