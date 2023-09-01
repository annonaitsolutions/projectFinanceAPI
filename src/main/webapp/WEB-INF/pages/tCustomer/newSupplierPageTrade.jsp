<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
	function val() {

		
		var supplierName=document.getElementById('supplierName');
		var companyName = document.getElementById("companyName");
		var bank = document.getElementById('bank');
		var ifsc = document.getElementById("ifsc");
		var bankEmail = document.getElementById('bankEmail');
		var branch = document.getElementById('branch');
		var address = document.getElementById('address');
		var pinCode = document.getElementById('pinCode');
		var country = document.getElementById('country');
		var state = document.getElementById('state');
	    var city = document.getElementById('city');
		var contactNum = document.getElementById('contactNum');
		
		var email = document.getElementById('email');
		var currencydeal=document.getElementById('currencydeal');
		var phoneNum            = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
		var reg                 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var nameval = /^[A-Za-z][A-Za-z0-9]*$/;
		var canSubmit = true;

		if (supplierName.value.match(/^[A-Za-z][A-Za-z0-9]*$/)
				&& !(document.getElementById('supplierName').value == '')) {

			document.getElementById('supplierNameError').style.display = 'none';
		} else {

			document.getElementById('supplierNameError').style.display = 'block';
			canSubmit = false;
		}

		if (document.getElementById('companyName').value == '') {
			document.getElementById('companyNameError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('companyNameError').style.display = 'none';
		}
		if (document.getElementById('bank').value == '') {
			document.getElementById('bankError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('bankError').style.display = 'none';
		}

		if(bankEmail.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('bankEmail').value == ''))
	  	{
	  		
	  		document.getElementById('emailError2').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('emailError2').style.display='block';
	  	     canSubmit = false;
	  	}
		if (document.getElementById('branch').value == '') {
			document.getElementById('branchError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('branchError').style.display = 'none';
		}

		if (document.getElementById('address').value == '') {
			document.getElementById('addressError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('addressError').style.display = 'none';
		}
		if (document.getElementById('pinCode').value == '') {
			document.getElementById('pinCodeError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('pinCodeError').style.display = 'none';
		}
		if (document.getElementById('country').value == '-1') {
			document.getElementById('countryError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('countryError').style.display = 'none';
		}

		if (document.getElementById('state').value == '-1'
				|| document.getElementById('state').value == '') {
			document.getElementById('stateError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('stateError').style.display = 'none';
		}

	

		if (document.getElementById('city').value == '') {
			document.getElementById('cityError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('cityError').style.display = 'none';
		}

		 if(contactNum.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/) && !(document.getElementById('contactNum').value == '' )) {
	    	 
	    	  document.getElementById('contactNum1Error').style.display='none';
	      }
	      else {
	       
	        document.getElementById('contactNum1Error').style.display='block';
	        canSubmit = false;
	      }

		 if(altContactNum.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/) ||(document.getElementById('altContactNum').value == '' )) {
	    	 
	    	  document.getElementById('contactNum2Error').style.display='none';
	      }
	      else {
	       
	        document.getElementById('contactNum2Error').style.display='block';
	        canSubmit = false;
	      } 
		 
			if(altEmail.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) ||(document.getElementById('altEmail').value == ''))
		  	{
		  		
		  		document.getElementById('altemailError').style.display='none';
		  	
		  	}
		  	else{
		  		document.getElementById('altemailError').style.display='block';
		  	     canSubmit = false;
		  	}
			
			if(email.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('email').value == ''))
		  	{
		  		
		  		document.getElementById('emailError').style.display='none';
		  	
		  	}
		  	else{
		  		document.getElementById('emailError').style.display='block';
		  	     canSubmit = false;
		  	} 

	
		
		if (document.getElementById('currencydeal').value == '') {
			document.getElementById('currencydealError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('currencydealError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}
	}
	
</script>
<script>
$(document).ready(function() {
$(':input','#supplierForm')
.not(':button, :submit, :reset, :hidden')
.val('')  
});
</script>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3><spring:message code="label.newSupplier"/></h3>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
		</div>
			<form:form action="SupplierPageForwardTrade" method="post"
				commandName="supplierTradeForm" id="newSupplier" onsubmit="return val();">

				
				<div class="col-sm-12 col-md-6 col-lg-6">
					<table align="center">


				<tr>
					<td class="col-sm-6"><b><spring:message code="label.customer"/> :</b><span style="color:red">*</span></td>
					<td class="col-sm-6">
					<form:input path="name" value="${model.supplierTradeForm.name}" id="name" readonly="true"/>
					</td>
				</tr>
				
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierName" /> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="supplierName" id="supplierName" placeholder="Enter Supplier Name"/>
								<div id="supplierNameError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="supplierNameError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierCompanyName"/> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="companyName" id="companyName" placeholder="Enter Supplier Company Name" />
								<div id="companyNameError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="companyNameError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierBank"/><spring:message code="label.supplierBank"/> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="bank" id="bank" placeholder="Enter Supplier Bank" />
								<div id="bankError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="bankError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
						<td class="col-sm-6"><b><spring:message code="label.ifscOrSwift" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="ifsc" id="ifsc" placeholder="Enter Ifsc or Swift Code" />
							<div id="ifscError" style="display: none; color: red;"> <spring:message code="label.validation" /></div>
					    </tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierBankEmail"/> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="bankEmail" id="bankEmail" placeholder="Enter Supplier Bank Email"/>
								<div id="emailError2" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="emailError2" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierBankBranch"/> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="branch" id="branch" placeholder="Enter Supplier Bank Branch"/>
								<div id="branchError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="branchError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.address"/> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="address" id="address" placeholder="Enter Address" />
								<div id="addressError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="addressError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.pincode" /> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="pinCode" id="pinCode" placeholder="Enter Pincode" />
								<div id="pinCodeError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="pinCodeError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
					</table>
				</div>
					<div class="col-sm-12 col-md-6 col-lg-6">
				<table align="center">
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.country"/> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:select path="country" id="country" value="" style="width:205px;"/>
								<div id="countryError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="countryError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.state"/> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:select path="state" id="state" value=""  style="width:205px;"/> <script
									language="javascript">
								populateCountries("country", "state");
							</script>
								<div id="stateError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="stateError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.city"/> :</b><span style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="city" id="city" placeholder="Enter City" />
								<div id="cityError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="cityError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="contactNum" id="contactNum" placeholder="Enter Contact Number" />
								<div id="contactNum1Error" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="contactNum1Error" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/> :</b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="email" id="email"  placeholder="Enter Email"/>
								<div id="emailError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="emailError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
						
						<td class="col-sm-6"><b><spring:message code="label.currencyDealt"/> :</b><span style="color: red">*</span></td>
						<td class="col-sm-6">
							<form:select path="currencydeal"  id="currencydeal" style="width:205px;">
							 <form:option value=""><spring:message code="label.selectValue"/></form:option> 					 
							<form:option   value="Rupee"><spring:message code="label.rupee"/></form:option>
							<form:option   value="Dollar"><spring:message code="label.dollar"/></form:option>
							</form:select>
							<div id="currencydealError" class="error" style="display:none; color: red;"><spring:message code="label.validation"/></div>
							<div id="currencydealError" class="error" style="display:none; color: red;"><spring:message code="label.validation"/></div>
					   </td>
						</tr>
			
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.altEmail"/> :</b><span style="color: red">*</td>
							<td class="col-sm-6"><form:input path="altEmail" id="altEmail" placeholder="Enter Alternate Email"/>
							<div id="altemailError" class="error" style="display:none;color: red;"><spring:message code="label.validation"/></div>
							<div id="altemailError" class="error" style="display:none;color: red;"><spring:message code="label.validation"/></div>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.altContactNo"/> :</b><span style="color: red">*</td>
							<td class="col-sm-6"><form:input path="altContactNum" id="altContactNum" placeholder="Enter Alternate Contact Number" />
							<div id="contactNum2Error" class="error" style="display:none;color: red;"><spring:message code="label.validation"/></div>
							<div id="contactNum2Error" class="error" style="display:none;color: red;"><spring:message code="label.validation"/></div>
					
						</tr>
				

				</table>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12"></div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="tUser" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
			</form:form>
			
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3><spring:message code="label.suppDetails"/></h3>
			</div>
							<div class="col-sm-12 col-md-12 col-lg-12">
	<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
</div>
		<table class="table table-hover data jqtable example" id="my-table">
					<thead>
						<tr>
							<th><spring:message code="label.id"/></th>
							<th><spring:message code="label.supplierName"/></th>
							<th><spring:message code="label.transactionId"/></th>
							<th><spring:message code="label.contactNumber"/>:</th>
							<th><spring:message code="label.address"/></th>
							<th><spring:message code="label.supplierBankBranch"/></th>
                            <th><spring:message code="label.status"/></th>
							<th><spring:message code="label.action"/></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${! empty supplierList}">
							<c:forEach items="${supplierList}" var="supplier">
								<tr>
									<td><c:out value="${supplier.id}"></c:out></td>
									<td><c:out value="${supplier.supplierName}"></c:out></td>
									<td><c:out value="${supplier.transactionId}"></c:out></td>
									<td><c:out value="${supplier.contactNum}"></c:out></td>
									<td><c:out value="${supplier.address}"></c:out></td>
									<td><c:out value="${supplier.branch}"></c:out></td>
                                    <td><c:out value="${supplier.cStatus}"></c:out></td>
                                    <td><a href="selectsupplierTradeUpdate?id=${supplier.id}"
										class="btn btn-primary"><spring:message code="label.edit"/></a></td>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
				</table>
		</div>
