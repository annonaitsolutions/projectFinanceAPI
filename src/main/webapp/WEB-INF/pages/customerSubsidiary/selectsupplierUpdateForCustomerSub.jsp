<%@include file="taglib_includes.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h3 align="center">Supplier(For Update)</h3>
	    <form:form name="selectSupplierUpdate2" action="selectsupplierUpdateForCustomerSub2" method="post" commandName="supplierForm">
             <form:hidden path="transactionId" value="" />

				<table class="table-bordered" align="center">

					<tr>
						<td class="heading_text"><b>Id</b></td>
						<td><form:input path="id" id="id" readonly="true" />
					</tr>
					<tr>
						<td class="heading_text"><b>Supplier Name</b></td>
						<td><form:input path="name" id="name" readonly="true" />
					</tr>
					
					<tr>
						<td class="heading_text"><b>Supplier Name</b></td>
						<td><form:input path="supplierName" id="supplierName" readonly="true" />
					</tr>
					<tr>
						<td class="heading_text"><b>Pin Code</b></td>
						<td><form:input path="pinCode" id="pinCode"  />
					</tr>
					<tr>
						<td class="heading_text"><b>Country</b></td>
						<td><form:input path="country" id="country"  />
					</tr>
					<tr>
						<td class="heading_text"><b>State</b></td>
						<td><form:input path="state" id="state"/>
					</tr>
					<tr>
						<td class="heading_text"><b>City</b></td>
						<td><form:input path="city" id="city"/>
					</tr>
					<tr>
						<td class="heading_text"><b>Email</b></td>
						<td><form:input path="email" id="email"/>
					</tr>
				
					<tr>
						<td class="heading_text"><b>Supplier Company Name</b></td>
						<td><form:input path="companyName" id="companyName" readonly="true" />
					</tr>
					<tr>
						<td class="heading_text"><b>Supplier Bank</b></td>
						<td><form:input path="bank" id="bank" />
					</tr>
					<tr>
						<td class="heading_text"><b>Supplier Bank Email</b></td>
						<td><form:input path="bankEmail" id="bankEmail"/>
					</tr>
					<tr>
						<td class="heading_text"><b>Contact Number</b></td>
						<td><form:input path="contactNum" id="contactNum" />
					</tr>



					<tr>
						<td class="heading_text"><b>Address</b></td>
						<td><form:input path="address" id="address" />
					</tr>
					<tr>
						<td><b>Branch:</b></td>
						<td><form:input path="branch" value="" id="l1" /></td>

					</tr>

					</table>

				<table align="center">
					<tr>
						<td><input type="submit" size="3" value="Update"
							class="btn btn-primary"></td>
						<td><button type="button" name="Back"
								onclick="javascript:window.location='/annona/admin/createRole';"
								class="btn btn-success">Back</button></td>
					</tr>
				</table>


			</form:form>

		</div>
	</div>
</div>


</body>