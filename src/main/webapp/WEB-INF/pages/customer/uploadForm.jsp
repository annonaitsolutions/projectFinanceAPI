<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
function validateForm() {
 var d = new Date().getTime();
	/* var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
		var r = (d + Math.random() * 16) % 16 | 0;
		d = Math.floor(d / 16);
		return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
	});

	document.fileUpload1.transactionId.value = uuid;  */
	
 	var Document = document.getElementById('document'); 
 	var file=document.getElementById('file'); 
	var uploadComment = document.getElementById('uploadComment');
	var reason = document.getElementById('reason');
    var pattern='^.*\.(jpg|JPG|gif|GIF|doc|DOC|pdf|PDF)$';
	var canSubmit = true;
	
	 if (document.getElementById('Document').value == '') {
		document.getElementById('documentError').style.display = 'block';
		canSubmit = false;
	} else {
		document.getElementById('documentError').style.display = 'none';
	} 
		
	if (document.getElementById('uploadComment').value == '') {
		document.getElementById('uploadCommentError').style.display = 'block';
		canSubmit = false;
	} else {
		document.getElementById('uploadCommentError').style.display = 'none';
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

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="successMsg"
			style="text-align: center; color: green; font-size: 18px;">${success}</div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer"></div>
	<form:form method="post" enctype="multipart/form-data"
		name="fileUpload1" commandName="uploadedFileForm" action="fileUpload"
		onsubmit="return validateForm();">


		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<div class="successMsg"
				style="text-align: center; color: green; font-size: 18px;">${success}</div>
		</div>

		<div class="mastersub">
			<h3 align="center"
				style="font-size: 19px; font-weight: 400; margin-top: -6px; color: #04ADF1;">
				<spring:message code="label.uploadDocsForBuyer" />
			</h3>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<table align="center">
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.customerName" /></b></td>
					<td class="col-sm-6"><form:input path="customerName"
							value="${user.userName}" placeholder="Enter name" readonly="true"></form:input>
				</tr>
				<form:hidden path="transactionId" id="transactionId" value="" />
				<form:hidden path="customerHeadKey" />
				<form:hidden path="customerHeadName" />
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.docsType" /></b></td>
					<td class="col-sm-6"><form:input path="document" id="Document"
							placeholder="Enter Type of Document" />

						<div id="documentError" style="display: none; color: red;">
							<spring:message code="label.validation" />
						</div>
						<div id="documentError" style="display: none; color: red;">
							<spring:message code="label.validation" />
						</div></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.comment" /></b></td>
					<td class="col-sm-6"><form:input path="uploadComment"
							id="uploadComment" placeholder="Enter Comment" />
						<div id="uploadCommentError" style="display: none; color: red;">
							<spring:message code="label.validation" />
						</div>
						<div id="uploadCommentError" style="display: none; color: red;">
							<spring:message code="label.validation" />
						</div></td>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.reasonUpload" />:</b><span style="color: red">*</span></td>

					<td class="col-sm-6"><form:select path="reason" id="reason"
							style="width: 207px;">
							
							<form:option value=""><spring:message code="label.selectValue"/></form:option>
											<form:option value="collateral"><spring:message code="label.collateral"/></form:option>
											<form:option value="buyer"><spring:message code="label.buyer"/></form:option>
											<form:option value="supplier"><spring:message code="label.supplier"/></form:option>
											
												<form:option value="supplier"><spring:message code="label.restrictedLicenseDoc"/></form:option>
												<form:option value="supplier"><spring:message code="label.purchaseOrderDocs"/></form:option>
												<form:option value="supplier"><spring:message code="label.invoiceDocs"/></form:option>
												<form:option value="supplier"><spring:message code="label.payDocs"/></form:option>
												<form:option value="supplier"><spring:message code="label.masterDocs"/></form:option>
												<form:option value="supplier"><spring:message code="label.otherdocs"/></form:option>
						</form:select>
						<div id="reasonError" style="display: none; color: red;">
							<spring:message code="label.validation" />
						</div>
						<div id="reasonError" style="display: none; color: red;">
							<spring:message code="label.validation" />
						</div></td>

				</tr>
			</table>
		</div>
		<div class="col-sm-12 col-md-12">
			<table align="center">
				<tr>
					<td><label><spring:message code="label.note" />:</label>
						<ol>
							<li><spring:message code="label.uploadSelectedDocs" /></li>
							<li><spring:message code="label.additionalDocs" /></li>
						</ol></td>
				</tr>
			</table>
		</div>
		<div class="col-sm-12 col-md-12" style="text-align: center;">
			<input name="files" type="file" id="fileValue${status.index}"
				multiple="multiple" onchange="myFunction(${status.index})" />
		</div>

		<div class="col-sm-12 col-md-12 col-lg-12">
			<p align="center" style="padding-top: 10px;">
				<input type="submit" class="btn btn-primary"
					value="<spring:message code="label.save"/>"> <a href="user"
					class="btn btn-success"><spring:message code="label.back" /></a>
			</p>
		</div>
	</form:form>
</div>
