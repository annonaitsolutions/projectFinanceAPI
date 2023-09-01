<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script type="text/javascript" src="jquery-1.2.6.min.js">

</script>
	<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.fileUpload1.transactionId.value = uuid; */

}
	
	
	var document = document.getElementById('document');
	var reason = document.getElementById('reason');
	
	
	var canSubmit = true;

	if (document.getElementById('document').value == '') {
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
<style type="text/css">
.bankemp_footer.col-sm-12 {
    margin-top: 40px;
}
</style>
</head>
<body>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>	
			</div>
			<form:form method="post" enctype="multipart/form-data" name="fileUpload1" commandName="uploadedFileForm" action="fileUpload" onsubmit="return validateForm();">

	

		
									<div class="container">
		<div class="row">
			<div class="successMsg"
				style="text-align: center; color: green; font-size: 18px;">${success}</div>
			<section>
				<div class="container">
					<div class="row">

						<div class="mastersub">
							<h3 align="center"
								style="font-size: 19px; font-weight: 400; margin-top: -6px; color: #04ADF1;">New
								Buyer Upload Documents</h3>
						</div>


						<tr>
							<td class="col-sm-2" style="width: 23.666667%;"><b>Customer
									Name</b></td>
							<td class="col-sm-8"><form:input path="customerName"
									value="${user.userName}" placeholder="Enter name"
									readonly="true" style="width:85%"></form:input>
						</tr>
						<form:hidden path="transactionId" value="" />
						<form:hidden path="customerHeadKey" />
						<form:hidden path="customerHeadName" />
						<tr>
							<td class="col-sm-2" style="width: 23.666667%;"><b>Type
									Of Document</b></td>
							<td class="col-sm-8"><form:input path="document"
									placeholder="Enter name" style="width:85%"></form:input>
						</tr>
						<tr>
							<td class="col-sm-2" style="width: 23.666667%;"><b>Reason
									For Upload:</b><span style="color: red">*</span></td>

							<td class="col-sm-8"><form:select path="reason" id="status"
									style="width:86.9%">
									<form:option value="">Select A Value</form:option>
									<form:option value="collateral">Collateral</form:option>
									<form:option value="buyer">Add Buyer</form:option>
									<form:option value="supplier">Add Supplier</form:option>
								</form:select></td>

						</tr>
					</div>
					<center>

						<div class="col-sm-11">
							<p>Note:</p>
							<ol>
								<li>Upload the selected Documents</li>
								<li>In case of Additional Documents enter the name of the
									document in the form and then upload it.</li>
							</ol>

						</div>
						<div class="col-sm-11">
							<input name="files" type="file" id="fileValue${status.index}"
								multiple="multiple" onchange="myFunction(${status.index})" />

						</div>

						<div class="col-sm-11">
							<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="Save">
								<a href="user" class="btn btn-success" name="back"
									onclick="javascript:window.location='#';" />back</a>

							</p>
						</div>
</form:form>
</center>

</body>
</html>