<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <script>
				function val(){
					
					var userName  = document.getElementById('typeOfLc');
					var userName  = document.getElementById('bankType');

					var canSubmit = true; 
					
					
				 	    if (document.getElementById('bankNameCorr').value == ''){
				       		document.getElementById('bankNameCorrError').style.display='block';
				       		canSubmit = false;
				       	}
				       	else{
				       		document.getElementById('bankNameCorrError').style.display='none';
				       	}
				 	   if (document.getElementById('bankLocCorr').value == ''){
				       		document.getElementById('bankLocCorrError').style.display='block';
				       		canSubmit = false;
				       	}
				       	else{
				       		document.getElementById('bankLocCorrError').style.display='none';
				       	}
			 	    if (document.getElementById('bankBranchCorr').value == ''){
			       		document.getElementById('bankBranchCorrError').style.display='block';
			       		canSubmit = false;
			       	}
			       	else{
			       		document.getElementById('bankBranchCorrError').style.display='none';
			       	}
			 	   if (document.getElementById('swiftCodeCorr').value == ''){
			       		document.getElementById('swiftCodeCorrError').style.display='block';
			       		canSubmit = false;
			       	}
			       	else{
			       		document.getElementById('swiftCodeCorrError').style.display='none';
			       	}
			 	  if (document.getElementById('accNum').value == ''){
			       		document.getElementById('accNumError').style.display='block';
			       		canSubmit = false;
			       	}
			       	else{
			       		document.getElementById('accNumError').style.display='none';
			       	}

					if(canSubmit == false){
						return false;
					}
				}
			

				function addRow(tableID) {
					 
				    var table = document.getElementById(tableID);
				   
				    var rowCount = table.rows.length;
				    var row = table.insertRow(rowCount);

				    var colCount = table.rows[1].cells.length;

				    for(var i=0; i<colCount; i++) {
				        var newcell = row.insertCell(i);                
				        
				        newcell.innerHTML = table.rows[1].cells[i].innerHTML;                
				        switch(newcell.childNodes[1].type) {
				            case "text":
				                    newcell.childNodes[1].value = "";
				                    break;                   
				            case "select-one":
				                    newcell.childNodes[1].selectedIndex = 0;
				                    break;
				        }
				    }
				}


				function deleteRow(tableID)
				{
				try
				     {
				    var table = document.getElementById(tableID);
				    var rowCount = table.rows.length;
				   
				                if (rowCount > 2) {                        
				                	 table.deleteRow(rowCount-1);
				                }else {
				                	alert("Cannot delete all the rows");	
				                }
				            
				        } catch(e)
				            {
				            alert(e);
				    }          
				}
		</script>
		<div class="col-sm-9 col-md-9 body_fixed">	
		<div class="col-sm-12 col-md-12 header_customer">
			<div class="heading"><h3 align="center"><spring:message code="label.poPayment"/></h3></div>
		</div>
<form:form  action="poPaymentHeadLcPageSave" name="msplan" commandName="letterOfCreditForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-6">
								<table class="theader">
						<form:hidden path="id"  />
										<form:hidden path="customerHeadEmail"  />
										<form:hidden path="customerBranchEmail"  />
										<form:hidden path="supplierEmail"  />
									<form:hidden path="transactionId" />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerHeadName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.supplierName"/>:</b></td>
										<td class="col-sm-6"><form:input path="supplierName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.poKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="poKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.balance"/>:</b></td>
										<td class="col-sm-6"><form:input path="payBalance" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.typeOfPayment"/>:</b></td>
										<td class="col-sm-6"><form:input path="typeOfTrans" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.typeOfLc"/>:</b>
										<td class="col-sm-6"><form:input path="typeOfLc" readonly="true" placeholder="Enter name"></form:input>
										
											</td>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.typeOfBank"/>:</b>
										<td class="col-sm-6"><form:input path="bankType" readonly="true" placeholder="Enter name"></form:input>
										
											</td>

									</table>
									</div>
									
									<table class="theader">
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.bankName"/>:</b>
										<td class="col-sm-6"><form:input path="bankName" readonly="true" placeholder="Enter name"></form:input>
										
											</td>													
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.bankBranch"/>:</b></td>
										<td class="col-sm-6"><form:input path="bankBranch" readonly="true" placeholder="Enter name" ></form:input>
					               								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.bankAddress"/>:</b></td>
										<td class="col-sm-6"><form:input path="bankAddress" readonly="true" placeholder="Enter name" ></form:input>
																							
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.ifscOrSwift"/>:</b></td>
										<td class="col-sm-6"><form:input path="swiftCode" readonly="true"  placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.accountNumber"/>:</b></td>
										<td class="col-sm-6"><form:input path="accNo" readonly="true" placeholder="Enter ac number" id="customerPrefix"></form:input>
										
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.contactNumber"/>:</b></td>
										<td class="col-sm-6"><form:input path="contactNum" readonly="true"  placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.contactPerson"/>:</b></td>
										<td class="col-sm-6"><form:input path="contactPerson" readonly="true"  placeholder="Enter contact Person" id="contactPerson"></form:input>
											
									</tr>
									</table>
					</div>
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.correspondenceList"/></h3>
	</div>	
									
				<div class="col-sm-12 col-md-12 col-lg-12"> 
				 <table class="host-list" id="dataTable">

					<tr class="chargescolor">
	                     
	                     <th><spring:message code="label.bankName"/></th>
						<th><spring:message code="label.bankAddress"/></th>
						<th><spring:message code="label.bankBranch"/></th>
						<th><spring:message code="label.ifscOrSwift"/></th>
						<th><spring:message code="label.accountNumber"/></th>
					</tr>		
					<tr>
					<td>
							<input type="text" name="bankNameCorr" id="bankNameCorr">	
							<div id="bankNameCorrError" class="error" style="display:none;color:red;"><spring:message code="label.validation"/></div> 
						</td>
					
						<td>
							<input type="text" name="bankLocCorr" id="bankLocCorr">	
							<div id="bankLocCorrError" class="error" style="display:none;color:red;"><spring:message code="label.validation"/></div> 
						</td>
						<td>
							<input type="text" name="bankBranchCorr" id="bankBranchCorr">	
							<div id="bankBranchCorrError" class="error" style="display:none;color:red;"><spring:message code="label.validation"/></div> 
						</td>
						
						<td>
							<input type="text" name="swiftCodeCorr" id="swiftCodeCorr" style="width: 111px;">	
							 <div id="swiftCodeCorrError" class="error"  style="display:none;color:red;"><spring:message code="label.validation"/></div> 
						</td>	
						<td>
							<input type="text" name="accNum" id="accNum" style="width: 111px;">	
						 	<div id="accNumError" class="error"  style="display:none;color:red;"><spring:message code="label.validation"/></div>  
						</td> 
						
					</tr>			
			</table>
						  	<div style="color:#FF4000" class="error_box"></div>
				<div id="bankNameCorrError" class="error" style="display:none"><spring:message code="label.validation"/></div>
				<div id="bankLocCorrError" class="error" style="display:none"><spring:message code="label.validation"/></div>
				<div id="bankBranchCorrError" class="error" style="display:none"><spring:message code="label.validation"/></div>
				<div id="swiftCodeCorrError" class="error" style="display:none"><spring:message code="label.validation"/></div>  
				<div id="accNumError" class="error" style="display:none"><spring:message code="label.validation"/></div>  
		<div class="col-sm-12 col-md-12 col-lg-12">
			<a class="btn btn-default"  onclick="addRow('dataTable')" value="Add New "
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.addNew"/></a>
			<a  class="btn btn-default"  onclick="deleteRow('dataTable')" value="Remove "
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.remove"/></a>
		
		</div>	
									
							<div class="col-sm-12 col-md-12 col-lg-12">	
								<p align="center" style="padding-top: 10px;">
					
								<input type="submit" class="btn btn-primary" value="Save">
								<a href="poPaymentHeadList" class="btn btn-success" name="back"  onclick="javascript:window.location='#';">Back</a>
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

