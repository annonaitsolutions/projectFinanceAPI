<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@include file="taglib_includes.jsp"%>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
	<form:form action="tCreateCompanyConfirm" method="post"
		commandName="companyForm" onsubmit="return val();">



		<div class="content">

			<table class="theader broad">
				<caption>
					<spring:message code="label.companyDetails" />
				</caption>
				<tr class="rowcolor">
					<td class="col-sm-6"><b><spring:message
								code="label.companyName" /></b></td>
					<td class="col-sm-6"><form:input path="companyName"
							placeholder="Enter Company Name" id="companyName"></form:input> <span
						id="companyNameError" class="error" style="display: none;"><font
							color="red"><spring:message code="label.validation" />*</font></span> <span
						id="companyNameError" class="error" style="display: none"><spring:message
								code="label.validation" /></span></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.companyPrefix" /></b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="companyPrefix"
							placeholder="Enter Company Prefix" id="companyPrefix"></form:input>
						<span id="companyPrefixError" class="error" style="display: none;"><font
							color="red"><spring:message code="label.validation" />*</font></span> <span
						id="companyPrefixError" class="error" style="display: none"><spring:message
								code="label.validation" /></span></td>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.address" /></b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="address"
							placeholder="Enter Address" id="address"></form:input> <span
						id="addressError" class="error" style="display: none;"><font
							color="red"><spring:message code="label.validation" />*</font></span> <span
						id="addressError" class="error" style="display: none"><spring:message
								code="label.validation" /></span></td>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.pincode" /></b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="pincode"
							placeholder="Enter Pincode" id="pincode"></form:input> <span
						id="pincodeError" class="error" style="display: none;"><font
							color="red"><spring:message code="label.validation" />*</font></span> <span
						id="pincodeError" class="error" style="display: none"><spring:message
								code="label.validation" /></span></td>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.country" /></b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:select path="country" id="country"
							placeholder="Select Country" style="width:205px;"></form:select>
						<div id="countryError" style="display: none; color: red;">
							<spring:message code="label.validation2" />
						</div>
						<div id="countryError" style="display: none; color: red;">
							<spring:message code="label.validation2" />
						</div></td>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.state" /></b><span
						style="color: red">*</span></td>
					<td class="col-sm-6"><form:select path="state"
							placeholder="Select State" id="state" style="width:205px;"></form:select>
						<script>
							populateCountries("country", "state");
						</script>
						<div id="stateError" style="display: none; color: red;">State
							is Mandatory</div>
						<div id="stateError" style="display: none; color: red;">State
							is Mandatory</div></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.city" /></b><span
						style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="city"
							placeholder="Enter City" id="city"></form:input> <span
						id="cityError" class="error" style="display: none;"><font
							color="red"><spring:message code="label.validation" />*</font></span> <span
						id="cityError" class="error" style="display: none"><spring:message
								code="label.validation" /></span></td>
				</tr>

			</table>
			<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
								<a href="bankEmp" class="btn btn-success"><spring:message code="label.back"/></a>
								</p>
		</div>
		</div>
	</form:form>
	
	<%-- 
	
	<div class="col-sm-12 col-md-12 col-lg-12">
		
		<span class="counter pull-right"></span>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<caption><spring:message code="label.clientAdmin"/></caption>
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
					<c:if test="${! empty adminList}">
						<c:forEach items="${adminList}" var="customer">
							<tr>
								<td><c:out value="${customer.id}"></c:out></td>
								<td><c:out value="${customer.name}"></c:out>
								<td><c:out value="${customer.transactionId}"></c:out>
								<td><c:out value="${customer.companyName}"></c:out></td>
								<td><c:out value="${customer.address}"></c:out></td>
								<td><c:out value="${customer.status}"></c:out></td>



								<td><a href="selectClientAdmin?id=${customer.id}"
									class="btn btn-primary"><spring:message code="label.edit" /></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>
	
	
	
 --%>	
	</div>
	
<script>
		
		  $(function() {
			  $( "#datepicker" ).datepicker({format: 'dd/mm/yyyy'});
			   });

			 
			
		$(document).ready(function() {
			$(':input','#clientAdminForm')
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
				
/* 				var categoryName = document.getElementById('name'); */
				var categoryName = document.getElementById('address');
				 var categoryName = document.getElementById('pincode');
				var categoryName = document.getElementById('city');
/* 				var categoryName = document.getElementById('contactno');
 */				var country = document.getElementById('country');
				var state = document.getElementById("state");
			/* 	var categoryName = document.getElementById('email');
				var categoryName = document.getElementById('cusmprefix'); */
				var companyName = document.getElementById('companyName');
				var companyPrefix = document.getElementById('companyPrefix');
/* 				var accExpiryDate = document.getElementById('datepicker').value; */
				var phoneNum            = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
				var reg                 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				var canSubmit = true;


				/* if (document.getElementById('name').value == ''){
					document.getElementById('nameError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('nameError').style.display='none';
				} */
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
				 /* if(contactno.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/) && !(document.getElementById('contactno').value == '' )) {
			    	 
			    	  document.getElementById('contactnoError').style.display='none';
			      }
			      else {
			       
			        document.getElementById('contactnoError').style.display='block';
			        canSubmit = false;
			      } */

			      
			  	/* if(email.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('email').value == ''))
			  	{
			  		
			  		document.getElementById('emailError1').style.display='none';
			  	
			  	}
			  	else{
			  		document.getElementById('emailError1').style.display='block';
			  	     canSubmit = false;
			  	}
			  	
			  	 if(altcontactNum.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/) ||(document.getElementById('altcontactNum').value == '' )) {
			    	 
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
				} */
				
				if (document.getElementById('city').value == '') {
					document.getElementById('cityError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('cityError').style.display = 'none';
				}
				
				
				if (document.getElementById('state').value == '-1'
					|| document.getElementById('state').value == '') {
				document.getElementById('stateError').style.display = 'block';
				canSubmit = false;
			} else {
				document.getElementById('stateError').style.display = 'none';
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
				
				/* if (accExpiryDate == ''){
					document.getElementById('expiryDateError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('expiryDateError').style.display='none';
				} */
				
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

	