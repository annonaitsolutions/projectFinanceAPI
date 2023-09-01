<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
function validateForm() {
function myFunction(a)
{
	
	var files = document.getElementById("fileValue"+a).files; 
	document.getElementById("fileName"+a).value="";
	var validExts = new Array("jpg", "jpeg", "png", "gif");
    for (var i = 0; i < files.length; i++){    	
    	var fileExt= /[^.]+$/.exec( files[i].name);    	
    	
    	if (validExts.indexOf(fileExt.toString()) < 0) {
    	      alert("Invalid file selected, valid files are of " +
    	               validExts.toString() + " types."); 
    	      document.getElementById("fileValue"+a).value="";
    	    }
    	else {
    		document.getElementById("fileName"+a).value += files[i].name+",";	
    	}
    	
    }
}
</script>
<div class="col-sm-9 col-md-9 body_fixed">

	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3>
			<spring:message code="label.purchaseOrderUploadDocs" />
		</h3>
	</div>
	<form:form method="post" enctype="multipart/form-data"
		name="purchasefileUpload" commandName="purchaseOrderForm"
		action="fileUpload1" onsubmit="return validateForm();">

		<div class="successMsg"
			style="text-align: center; color: green; font-size: 18px;">${success}</div>



		<form:hidden path="id" />
		<table align="center">
			<tr>
				<td class="col-sm-6"><b><spring:message
							code="label.customerName" /></b></td>
				<td class="col-sm-6"><form:input path="customerName"
						value="${user.userName}" placeholder="Enter name" readonly="true"></form:input>
			</tr>
			<form:hidden path="poKey" />
			 <tr>
										<td class="col-sm-6"><b><spring:message code="label.docsType"/>:</b></td>
										<td class="col-sm-6"><form:input path="document" id="Document" placeholder="Enter Type of Document"/>
										<div id="documentError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
	                                  <div id="documentError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>														
									</tr>
								<tr>
											<td class="col-sm-6"><b><spring:message code="label.reasonUpload"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:textarea path="reason" id="reason" placeholder="Enter Reason"></form:textarea>
										
				                          <div id="reasonError" style="display: none; color: red;"><spring:message code="label.validation2"/></div>
	                                     <div id="reasonError" style="display: none; color: red;"><spring:message code="label.validation2"/></div>
											</td>	
												 <form:hidden path="transactionId" id="transactionId" value="" />
											     <form:hidden path="poKey"/>
											     <form:hidden path="masterKey"/>												
									</tr>
		</table>
		<div class="col-sm-12 col-md-12">
			<table align="center">
				<tr>
					<td>
						<p>
							<b><spring:message code="label.note" /></b>
						</p>
					</td>
				</tr>
				<tr>
					<td>
						<ol>
							<li><spring:message code="label.uploadSelectedDocs" /></li>
							<li><spring:message code="label.additionalDocs" /></li>
						</ol>
					</td>
				</tr>
				<tr>
					<td><input name="files" type="file"
						id="fileValue${status.index}" multiple="multiple"
						onchange="myFunction(${status.index})" /></td>
				</tr>
			</table>

			<div class="col-sm-12 col-md-12 col-lg-12">
				<table align="center">
					<tr>
						<td class="col-sm-8"><input type="submit"
							value="<spring:message code="label.save"/>"
							class="btn btn-primary"></td>
						<td><a href="PoBankEmpList" class="btn btn-success"> <spring:message
									code="label.back" /></a></td>
					</tr>
				</table>
			</div>
	</form:form>
</div>
<script>
	function validateForm() {

	 	var Document = document.getElementById('document'); 
	 	var file=document.getElementById('file'); 
	
		var reason = document.getElementById('reason');
	    var pattern='^.*\.(jpg|JPG|gif|GIF|doc|DOC|pdf|PDF)$';
		var canSubmit = true;
		
		 if (document.getElementById('Document').value == '') {
			document.getElementById('documentError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('documentError').style.display = 'none';
		} 
			
		
	
		 if (document.getElementById('reason').value == '') {
			document.getElementById('reasonError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('reasonError').style.display = 'none';
		} 
		
		if (canSubmit == false) {
			return false;
		}
		

		
	}
</script>
