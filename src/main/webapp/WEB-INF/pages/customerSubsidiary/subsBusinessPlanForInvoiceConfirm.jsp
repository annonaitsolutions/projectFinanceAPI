<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script>

function validateForm() {
	/* var d = new Date().getTime();
	var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
		var r = (d + Math.random() * 16) % 16 | 0;
		d = Math.floor(d / 16);
		return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
	});
	document.msplan.transactionId.value = uuid; */
}
  </script>


<section class="container-fluid padding-top15">
	<div class="row-fluid">
		<div class="successMsg"
			style="text-align: center; color: green; font-size: 18px;">${success}</div>
		<div class="row-fluid apps">



			<form:form action="subsBusinessPlanForInvoicePost" method="post"
				commandName="invoiceForm" name="msplan" onsubmit="return validateForm()" >



				<br>
				<h3>Buyer Details</h3>
				<div class="col-sm-6">
					<table align="center">

         <form:hidden path="customerName" />
         <form:hidden path="customerHeadName" />
         <form:hidden path="customerHeadEmail" />
            <form:hidden path="customerBranchEmail" />
       
         <form:hidden path="masterKey"  />
 
           <form:hidden path="transactionId"  value="" />
						 <tr>
					<td class="heading_text"><b>Buyer Name</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerName"  id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="heading_text"><b>Buyer Email</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerEmail" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="heading_text"><b>Buyer Bank</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerBank"  id="name" readonly="true"/>
					</td>
				</tr>
                  <tr>
					<td class="heading_text"><b>Buyer Bank Email</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerBankEmail"  id="name" readonly="true"/>
					</td>
				</tr>
                    <tr>
					<td class="heading_text"><b>Buyer Country</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerCountry"  id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="heading_text"><b>Buyer State</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerState" id="name" readonly="true"/>
					</td>
				</tr>
						<tr>
					<td class="heading_text"><b>Buyer City</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerCity"  id="name" readonly="true"/>
					</td>
				</tr>
					</table>
					
					<h3>Existing Po Info</h3>
					
					<table align="center">
					<div class="col-sm-6">
						<tr>
							<td class="heading_text"><b>Po Key</b><span
								style="color: red">*</span></td>
							<td><form:input path="poKey" id="poKey" readonly="true" />
								</td>
						</tr>
						
						<tr>
							<td class="heading_text"><b>Po Date</b><span
								style="color: red">*</span></td>
							<td><form:input path="poDate" id="poDate"  readonly="true"/>
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Po Info</b><span style="color: red">*</span></td>
							<td><form:input path="poInfo" id="poInfo"  readonly="true" />
								</td>
						</tr>
						</div>
						</table>
						</div>
					<h3>Product Info</h3>
					
					<table align="center">
					<div class="col-sm-6">
						<tr>
							<td class="heading_text"><b>Goods Category</b><span
								style="color: red">*</span></td>
							<td>	<form:input path="goodsCategory"  id="name" readonly="true"/>
							
											   </td>
						</tr>
						
						<tr>
							<td class="heading_text"><b>Goods</b><span
								style="color: red">*</span></td>
							<td><form:input path="goods" id="goods" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Details</b><span style="color: red">*</span></td>
							<td><form:input path="goodsDetails" id="goodsDetails" readonly="true"  />
							</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Quantity</b><span
								style="color: red">*</span></td>
							<td><form:input path="quantity" id="quantity"  readonly="true"/>
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Weight</b><span
								style="color: red">*</span></td>
							<td><form:input path="weight" id="weight" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Licence</b><span
								style="color: red">*</span></td>
								<td><form:input path="licence" id="weight" readonly="true" />
																			   </td>
						</tr>
						<tr>
							<td class="heading_text"><b>Amount</b><span
								style="color: red">*</span></td>
							<td><form:input path="amount" id="amount" readonly="true"/>
								</td>
						</tr>
						
						</div>
					

				</table>
					</div>
					<table>
		
							<tr>
								<td><input type="submit" class="btn btn-primary" value="Continue"></td>
								<td><a href="user" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/>back</a></td>
							</tr>
							
					</table>
					</form:form>
					