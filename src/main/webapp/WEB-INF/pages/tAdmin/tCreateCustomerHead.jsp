<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="tCustomerHeadConfirm" method="post" commandName="customerHeadForm" onsubmit="return val();">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.addCustomerHead"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
			<div class="content col-sm-12 col-md-6 col-lg-6">
						<table class="theader">
								<caption><spring:message code="label.addPersonalDetails"/></caption>
								<tr>
                                <td class="col-sm-6"><b><spring:message code="label.companyName" /></b><span style="color: red">*</span></td>
                                <td class="col-sm-6"><form:select path="companyId" readonly="true" id="companyId" style="width:205px;">
                                <form:option value=""><spring:message code="label.selectValue" /></form:option>
                                <c:forEach var="item" items="${companyList}">
                                <form:option value="${item.id}">${item.companyName}</form:option>
                                </c:forEach>
                                </form:select></td>
                                </td>
                                </tr>
									<tr>
									
										<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="name" placeholder="Enter Name"></form:input>
										<span id="nameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
									    <span id="nameError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																									
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.gender"/></b></td>
											
											<td class="col-sm-6"><form:select path="gender" style="width:205px">
											<form:option value=""><spring:message code="label.selectValue"/></form:option>
											    <form:option value="male"><spring:message code="label.male"/></form:option>
												<form:option value="female"><spring:message code="label.female"/></form:option>
												<form:option value="transgender"><spring:message code="label.transgender"/></form:option>
												</form:select>
										    </td>															
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="contactNum" placeholder="Enter Contact Number" id="contactNum"></form:input>
											<span id="contactNum1Error" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
										  <span id="contactNum1Error" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.altContactNo"/></b></td>
										<td class="col-sm-6"><form:input path="altContactNum" placeholder="Enter Alternative Contact Number" id="altContactNum"></form:input>
									<span id="contactNum2Error" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
										</td>
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.email"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="email" placeholder="Enter Email" id="email"></form:input>
											<span id="emailError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
										   <span id="emailError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.altEmail"/></b></td>
										<td class="col-sm-6"><form:input path="altEmail" placeholder="Enter Alternative Email" id="altEmail"></form:input>
									<span id="altemailError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
										
										</td>
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerPrefix" placeholder="Enter Customer Prefix" id="cusmprefix"></form:input>
									
										<span id="cusmprefixError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
									    <span id="cusmprefixError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
									
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.manager"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="manager" placeholder="Enter Manager Name" id="manager"></form:input>
											<span id="managerError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></span>
										    <span id="managerError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.managerEmail"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="managerEmail" placeholder="Enter Manager Email" id="managerEmail"></form:input>
											<span id="managerEmailError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></span>
										    <span id="managerEmailError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																									
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.accountExpiryDate"/></b><span style="color:red">*</span></td>										
										<td id="e1" class="col-sm-6"><form:input path="accExpiryDate" class="form-control" placeholder="Select date" style="width: 142px;" readonly="true" id="datepicker"/><i class="fa fa-calendar"></i>
									
										<span id="expiryDateError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
									   </td>
									
									</tr>
									
						</table>
						
				
				
			</div>
		
		<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
								<a href="tCustAdminPage" class="btn btn-success"><spring:message code="label.back"/></a>
								</p>
		</div>
		</div>
		</form:form>
		<div class="col-sm-12 col-md-12 col-lg-12">
		<span class="counter pull-right"></span>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<caption><spring:message code="label.customerHeadList"/></caption>
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.transactionId"/></th>
						<th><spring:message code="label.companyName"/></th>
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty model.customerList}">
						<c:forEach items="${model.customerList}" var="customer">
							<tr>
								<td><c:out value="${customer.id}"></c:out></td>
								<td><c:out value="${customer.name}"></c:out>
								<td><c:out value="${customer.transactionId}"></c:out>
								<td><c:out value="${customer.companyName}"></c:out></td>
								<td><c:out value="${customer.address}"></c:out></td>
								<td><c:out value="${customer.status}"></c:out></td>



								<td><a href="tSelectCustomerHead?id=${customer.id}"
									class="btn btn-primary"><spring:message code="label.edit" /></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>		
	</div>	 
		<script>
		
		$(function() {
			  $( "#datepicker" ).datepicker({format: 'dd/mm/yyyy'});
			   });
		$(document).ready(function() {
			$(':input','#customerHeadForm')
			  .not(':button, :submit, :reset, :hidden')
			  .val('')  
		});
		
			$('.accordion .item .heading').click(function() {
				
				var a = $(this).closest('.item');
				var b = $(a).hasClass('open');
				var c = $(a).closest('.accordion').find('.open');
					
				if(b != true) {
					$(c).find('.content').slideUp(200);
					$(c).removeClass('open');
				}

				$(a).toggleClass('open');
				$(a).find('.content').slideToggle(200);

			});
			
			function val(){
				
				var categoryName = document.getElementById('name');
				var categoryName = document.getElementById('address');
				 var categoryName = document.getElementById('pincode');
				var categoryName = document.getElementById('city');
				var categoryName = document.getElementById('contactno');
				var categoryName = document.getElementById('email');
				var categoryName = document.getElementById('cusmprefix');
				var companyName = document.getElementById('companyName');
				var companyPrefix = document.getElementById('companyPrefix');
				var accExpiryDate = document.getElementById('datepicker').value;
		    	var phoneNum            = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
				var reg                 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				var canSubmit = true;


				if (document.getElementById('name').value == ''){
					document.getElementById('nameError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('nameError').style.display='none';
				}
				if (document.getElementById('address').value == ''){
					document.getElementById('addressError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('addressError').style.display='none';
				}
				
				 if (document.getElementById('pincode').value == ''){
					document.getElementById('pincodeError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('pincodeError').style.display='none';
				}
				if (document.getElementById('city').value == ''){
					document.getElementById('cityError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('cityError').style.display='none';
				}
				
				
				 if(contactNum.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/) && !(document.getElementById('contactNum').value == '' )) {
			    	 
			    	  document.getElementById('contactNum1Error').style.display='none';
			      }
			      else {
			       
			        document.getElementById('contactNum1Error').style.display='block';
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
				if (document.getElementById('cusmprefix').value == ''){
					document.getElementById('cusmprefixError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('cusmprefixError').style.display='none';
				}
				
				if (document.getElementById('companyName').value == ''){
					document.getElementById('companyNameError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('companyNameError').style.display='none';
				}
				if (document.getElementById('companyPrefix').value == ''){
					document.getElementById('companyPrefixError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('companyPrefixError').style.display='none';
				}
				if (document.getElementById('manager').value == ''){
					document.getElementById('managerError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('managerError').style.display='none';
				}
				
				if(managerEmail.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('managerEmail').value == ''))
			  	{
			  		
			  		document.getElementById('managerEmailError').style.display='none';
			  	
			  	}
			  	else{
			  		document.getElementById('managerEmailError').style.display='block';
			  	     canSubmit = false;
			  	}
				
				
				
				if (accExpiryDate == ''){
					document.getElementById('expiryDateError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('expiryDateError').style.display='none';
				}
				
			if(canSubmit == false){
					return false;
				}
			}
			
			
			$(document).ready(function() {
				  $(".search").keyup(function () {
				    var searchTerm = $(".search").val();
				    var listItem = $('.results tbody').children('tr');
				    var searchSplit = searchTerm.replace(/ /g, "'):containsi('")
				    
				  $.extend($.expr[':'], {'containsi': function(elem, i, match, array){
				        return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
				    }
				  });
				    
				  $(".results tbody tr").not(":containsi('" + searchSplit + "')").each(function(e){
				    $(this).attr('visible','false');
				  });

				  $(".results tbody tr:containsi('" + searchSplit + "')").each(function(e){
				    $(this).attr('visible','true');
				  });

				  var jobCount = $('.results tbody tr[visible="true"]').length;
				    $('.counter').text(jobCount + ' item');

				  if(jobCount == '0') {$('.no-result').show();}
				    else {$('.no-result').hide();}
						  });
				});
		</script>

