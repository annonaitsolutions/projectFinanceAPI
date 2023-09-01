<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9">	
<div class="col-sm-12 col-md-12">
<div class="heading"><h3 align="center">LC/BG</h3></div>
</div>

<form:form  action="#" name="msplan" commandName="letterOfCreditForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-6">
								<table class="theader">
						<form:hidden path="id"  />
										<form:hidden path="customerHeadEmail"  />
										<form:hidden path="customerBranchEmail"  />
										<form:hidden path="supplierEmail"  />
									<form:hidden path="transactionId" />
									<tr>
										<td class="col-sm-5"><b> Customer:</b></td>
										<td class="col-sm-7"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-5"><b> Customer Head:</b></td>
										<td class="col-sm-7"><form:input path="customerHeadName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-5"><b> Supplier Name:</b></td>
										<td class="col-sm-7"><form:input path="supplierName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-5"><b>PO Key:</b></td>
										<td class="col-sm-7"><form:input path="poKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-5"><b>Amount:</b></td>
										<td class="col-sm-7"><form:input path="amount" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								<tr>
										<td class="col-sm-5"><b>Type Of Payment:</b></td>
										<td class="col-sm-7"><form:input path="typeOfTrans" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
								<tr>
										<td class="col-sm-5"><b>Type Of LC:</b>
										<td class="col-sm-7"><form:input path="typeOfLc" readonly="true" placeholder="Enter name"></form:input>
										
											</td>
									<tr>
										<td class="col-sm-5"><b>Type Of Bank:</b>
										<td class="col-sm-7"><form:input path="bankType" readonly="true" placeholder="Enter name"></form:input>
										
											</td>

									</table>
									</div>
									
									<table class="theader">
						
									<tr>
										<td class="col-sm-5"><b> Bank Name:</b>
										<td class="col-sm-7"><form:input path="bankName" readonly="true" placeholder="Enter name"></form:input>
										
											</td>													
									</tr>
									<tr>
										<td class="col-sm-5"><b> Bank Branch:</b></td>
										<td class="col-sm-7"><form:input path="bankBranch" readonly="true" placeholder="Enter name" ></form:input>
					               								
									</tr>
									<tr>
										<td class="col-sm-5"><b> Bank Address:</b></td>
										<td class="col-sm-7"><form:input path="bankAddress" readonly="true" placeholder="Enter name" ></form:input>
																							
									</tr>
										
									
									<tr>
										<td class="col-sm-5"><b>Swift Code:</b></td>
										<td class="col-sm-7"><form:input path="swiftCode" readonly="true"  placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-5"><b>Ac Num:</b></td>
										<td class="col-sm-7"><form:input path="accNo" readonly="true" placeholder="Enter ac number" id="customerPrefix"></form:input>
										
									</tr>
									<tr>
										<td class="col-sm-5"><b>Contact Num:</b></td>
										<td class="col-sm-7"><form:input path="contactNum" readonly="true"  placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									</table>
									</div>
			
									
		

</form:form>
	</div>
	
				<br>
			<h3 align="middle">Correspondence List</h3>
			<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Head Name</th>
						<th>Supplier Name</th>
						<th>Bank Name</th>
						<th>Bank Branch</th>
						<th>Swift Code</th>
						<th>Acc Num</th>
	

						
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
</

