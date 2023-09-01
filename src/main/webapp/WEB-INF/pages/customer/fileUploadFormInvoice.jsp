<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.fileUploadInovice.transactionId.value = uuid; */
	}
</script>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.PoUploadDocs"/></h3>
			
		</div>
<form:form method="post" enctype="multipart/form-data" name="fileUploadInovice" commandName="inoviceUploadForm" action="fileUploadInovice" onsubmit="return validateForm();">
			
			                      
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">				
				<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
			</div>
		
						<div class="mastersub">
							<h3 align="center"
								style="font-size: 19px; font-weight: 400; margin-top: -6px; color: #04ADF1;"><spring:message code="label.newPoUploadDocs"/></h3>
						</div>
			<div class="col-sm-12 col-md-12 col-lg-12">	
				<table align="center">
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
							<td class="col-sm-6"><form:input path="customerName"
									value="${user.userName}" placeholder="Enter name"
									readonly="true"></form:input>
						</tr>
					  <form:hidden path="transactionId" value="" />
						<form:hidden path="customerHeadKey" />
						<form:hidden path="customerHeadName" />
						<form:hidden path="poKey"/>
						
      			 
		
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.docsType"/></b></td>
							<td class="col-sm-6"><form:input path="document" id="Document" placeholder="Enter name"></form:input>
								<div id="documentError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
	                       <div id="documentError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
							
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.reasonForUpload"/></b><span style="color: red">*</span></td>
                         <td class="col-sm-6"><form:input path="reason" id="reason" placeholder="Enter reason" style="width: 207px;"/>
                           <div id="reasonError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
	                     <div id="reasonError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
						</tr>
				</table>
					</div>
				<div class="col-sm-12 col-md-12">
					<table align="center">
						<tr>
							<td>
							<label><spring:message code="label.note"/>:</label>
							<ol>
								<li><spring:message code="label.uploadSelectedDocs"/></li>
								<li><spring:message code="label.additionalDocs"/></li>
							</ol>
							</td>
						</tr>
					</table>
				</div>
				<div class="col-sm-12 col-md-12" style="text-align: center;">
					<input name="files" type="file" id="fileValue${status.index}" multiple="multiple" onchange="myFunction(${status.index})" />
				</div>

						<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="invoiceApprovedList" class="btn btn-success"><spring:message code="label.back"/></a></td>
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