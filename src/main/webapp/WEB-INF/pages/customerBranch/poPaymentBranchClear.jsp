<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

			<form:form  action="#" name="msplan" commandName="letterOfCreditForm" onsubmit="return val();">
					<div class="col-sm-12 col-md-12 header_customer">
						<h3 align="center"><spring:message code="label.lcBg"/></h3>
					</div>
					<div class="col-sm-6 col-md-6 col-lg-6">
					
					
								<table>
									<form:hidden path="id"  />
										<form:hidden path="customerHeadEmail"  />
										<form:hidden path="customerBranchEmail"  />
										<form:hidden path="supplierEmail"  />
									<form:hidden path="transactionId" />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input></td>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerHeadName" placeholder="Enter name" readonly="true"></form:input></td>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.supplierName"/>:</b></td>
										<td class="col-sm-6"><form:input path="supplierName" placeholder="Enter name" readonly="true"></form:input></td>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.poKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="poKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
										</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.typeOfPayment"/>:</b></td>
										<td class="col-sm-6"><form:input path="typeOfTrans" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input></td>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.typeOfLc"/>:</b>
										<td class="col-sm-6"><form:input path="typeOfLc" readonly="true" placeholder="Enter name"></form:input>
										
											</td>
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.typeOfBank"/>:</b>
										<td class="col-sm-6"><form:input path="bankType" readonly="true" placeholder="Enter name"></form:input></td>
									</tr>
										
									

							</table>
						</div>		
						<div class="col-sm-6 col-md-6 col-lg-6">
									<table>
						
											<tr>
												<td class="col-sm-6"><b><spring:message code="label.bankName"/>:</b>
												<td class="col-sm-6"><form:input path="bankName" readonly="true" placeholder="Enter name"></form:input>
												
													</td>													
											</tr>
											<tr>
												<td class="col-sm-6"><b><spring:message code="label.bankBranch"/>:</b></td>
												<td class="col-sm-6"><form:input path="bankBranch" readonly="true" placeholder="Enter name" ></form:input></td>
							               								
											</tr>
											<tr>
												<td class="col-sm-6"><b><spring:message code="label.bankAddress"/>:</b></td>
												<td class="col-sm-6"><form:input path="bankAddress" readonly="true" placeholder="Enter name" ></form:input></td>
																									
											</tr>
												
											
											<tr>
												<td class="col-sm-6"><b><spring:message code="label.swiftCode"/>:</b></td>
												<td class="col-sm-6"><form:input path="swiftCode" readonly="true"  placeholder="Enter contact number" id="customerPrefix"></form:input></td>
													
											</tr>
										
											<tr>
												<td class="col-sm-6"><b><spring:message code="label.accountNumber"/>:</b></td>
												<td class="col-sm-6"><form:input path="accNo" readonly="true" placeholder="Enter ac number" id="customerPrefix"></form:input></td>
												
											</tr>
											<tr>
												<td class="col-sm-6"><b><spring:message code="label.chequeNum"/>:</b></td>
												<td class="col-sm-6"><form:input path="contactNum" readonly="true"  placeholder="Enter contact number" id="customerPrefix"></form:input></td>
													
											</tr>
									
									</table>
							</div>

</form:form>
	
		<div class="col-sm-12 col-md-12 header_customer">
				<br>
			<h3 align="middle"><spring:message code="label.correspondenceList"/></h3>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">

				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.bankName"/></th>
						<th><spring:message code="label.bankBranch"/></th>
						<th><spring:message code="label.swiftCode"/></th>
						<th><spring:message code="label.accountNumber"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty corrList}">
						<c:forEach items="${corrList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.supplierName}"></c:out>
                                <td><c:out value="${master.bankName}"></c:out>
                                <td><c:out value="${master.bankBranch}"></c:out>
                                <td><c:out value="${master.swiftCode}"></c:out>
                                 <td><c:out value="${master.accNum}"></c:out>
							</tr>
					</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>							
							<td><a href="poPaymentBranchFullList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
</div>
