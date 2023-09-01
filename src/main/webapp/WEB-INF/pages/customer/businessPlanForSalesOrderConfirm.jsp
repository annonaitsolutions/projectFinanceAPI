<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script>

function validateForm() {
	/* var d = new Date().getTime();
	var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
		var r = (d + Math.random() * 16) % 16 | 0;
		d = Math.floor(d / 16);
		return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
	});
	document.msplan.transactionId.value = uuid; */
}
  </script>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
		</div>
		<form:form action="businessPlanForSalesOrderPost" method="post"
				commandName="salesOrderForm" name="msplan" onsubmit="return validateForm()" >

			
		 <div class="col-sm-6">
		 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
		 </div>
		 <table>

         <form:hidden path="customerName" />
         <form:hidden path="customerHeadEmail"  />
         <form:hidden path="masterKey"  />
        
 
           <form:hidden path="transactionId"  value="" />
						 <tr>
					<td class="heading_text"><b><spring:message code="label.buyerName"/></b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerName"  id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="heading_text"><b><spring:message code="label.buyerEmail"/></b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerEmail" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="heading_text"><b><spring:message code="label.buyerBank"/></b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerBank"  id="name" readonly="true"/>
					</td>
				</tr>
                  <tr>
					<td class="heading_text"><b><spring:message code="label.buyerBankEmail"/></b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerBankEmail"  id="name" readonly="true"/>
					</td>
				</tr>
                    <tr>
					<td class="heading_text"><b><spring:message code="label.buyerCountry"/></b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerCountry"  id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="heading_text"><b><spring:message code="label.buyerState"/></b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerState" id="name" readonly="true"/>
					</td>
				</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.buyerCity"/></b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerCity"  id="name" readonly="true"/>
					</td>
				</tr>
					</table>
				</div>
				<div class="col-sm-6">
				 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.menuMasterPlan"/></h3>
				 </div>	
				<table align="center">
				
						<tr>
							<td class="heading_text"><b><spring:message code="label.masterPlanKey"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="masterPlanKey" id="masterPlanKey" readonly="true" />
								</td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.masterplaneDate"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="masterPlanDate" id="masterPlanDate"  readonly="true"/>
								</td>
						</tr>
					
<%--						<tr>--%>
<%--							<td class="heading_text"><b><spring:message code="label.tenure"/></b><span style="color: red">*</span></td>--%>
<%--							<td><form:input path="tenure" id="tenure"  readonly="true" />--%>
<%--								</td>--%>
<%--						</tr>--%>
						</div>
						</table>
					</div>
					<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3 align="center"><spring:message code="label.productInfo"/></h3>
					 </div>	
					<table align="center">
						<tr>
							<td class="heading_text"><b><spring:message code="label.goodsCategory"/></b><span
								style="color: red">*</span></td>
							<td>	<form:input path="goodsCategory"  id="name" readonly="true"/>
							
											   </td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.goods"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="goods" id="goods" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.details"/></b><span style="color: red">*</span></td>
							<td><form:input path="goodsDetails" id="goodsDetails" readonly="true"  />
							</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.quantity"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="quantity" id="quantity"  readonly="true"/>
								</td>
						</tr>
						
						<%-- <tr>
							<td class="heading_text"><b><spring:message code="label.whName"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="wareHouseName" id="wareHouseName"  readonly="true"/>
								</td>
						</tr> --%>
						<tr>
							<td class="heading_text"><b><spring:message code="label.weight"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="weight" id="weight" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.license"/></b><span
								style="color: red">*</span></td>
								<td><form:input path="licence" id="weight" readonly="true" />
																			   </td>
						</tr>
							<tr>
							<td class="heading_text"><b><spring:message code="label.servicetax"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="serviceTax" id="serviceTax" readonly="true"/>
								</td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.dutytax"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="dutytax" id="dutytax" readonly="true"/>
								</td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.vattax"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="vattax" id="vattax" readonly="true"/>
								</td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.amount"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="amount" id="amount" readonly="true"/>
								</td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.totalcost"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="answer" id="answer" readonly="true"/>
								</td>
						</tr>
							<tr>
										<td class="heading_text"><b><spring:message code="label.insType"/></b></td>
										<td><form:input path="insuranceType" id="insuranceType" readonly="true" placeholder="Enter Insurance Type"></form:input>
										
									</tr>
									<tr>
										<td class="heading_text"><b><spring:message code="label.insAmt"/></b></td>
										<td><form:input path="insuranceAmount" id="insuranceAmount" readonly="true" placeholder="Enter Insurance Amount"></form:input>
										
										
									</tr>
									<tr>
										<td class="heading_text"><b><spring:message code="label.insDetails"/></b></td>
										<td><form:textarea path="insuranceDetails" id="insuranceDetails" readonly="true" placeholder="Enter Insurance Details"></form:textarea>
										
										
									</tr>
										<tr>
						             <td class="heading_text"><b><spring:message code="label.startDate"/></b></td>
						                 <td>
							                <form:input path="startDate"  class="form-control" readonly="true" placeholder="Enter Date" id="startDate"/></td>
							             </tr>
						        
						        	<tr>
						                <td class="heading_text"><b><spring:message code="label.endDate"/></b></td>
						                     <td>
							                   <form:input path="endDate"  class="form-control" readonly="true" placeholder="Enter Date"  id="endDate"/></td>
							    </tr>
						
						</div>
					

				</table>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="businessPlan" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
					</form:form>
</div>