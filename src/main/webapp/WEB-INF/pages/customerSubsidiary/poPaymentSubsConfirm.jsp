<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9">	
<div class="col-sm-12 col-md-12">
<div class="heading"><h3 align="center">PO Payment</h3></div>
</div>

<form:form  action="poPaymentSubsSave" name="myForm" commandName="purchaseOrderForm" onsubmit="return val();">

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
										<td class="col-sm-5"><b>Cheque Num:</b></td>
										<td class="col-sm-7"><form:input path="chequenum" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									</table>
									</div>
						
									
							<div class="col-sm-12 col-md-12 col-lg-12">	
								<p align="center" style="padding-top: 10px;">
					
					<input type="submit" class="btn btn-primary" value="Save">
								<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/>back</a>
								</p>
							</div>
									
	</div>

</form:form>
	</div>
<style>
.link p a{
color: tomato;
}
</style>

