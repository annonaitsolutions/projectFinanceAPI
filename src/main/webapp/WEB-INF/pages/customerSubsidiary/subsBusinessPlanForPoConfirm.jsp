<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script>

function validateForm() {
	
	/* var d = new Date().getTime();
    var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    document.msplan.poKey.value=uuid;
   
    var d1 = new Date().getTime();
	var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c1) {
		var r1 = (d1 + Math.random() * 16) % 16 | 0;
		d1 = Math.floor(d / 16);
		return (c1 == 'x' ? r1 : (r1 & 0x3 | 0x8)).toString(16);
	});
	document.msplan.transactionId.value = uuid1; */


 }
 
 
        
   </script>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

		<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
	
			<form:form action="subsBusinessPlanForPoPost" method="post"
				commandName="purchaseOrderForm" name="msplan" onsubmit="return validateForm()" >

				
				<div class="col-sm-12 col-md-6 col-lg-6">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center">SupplierDetails</h3>
				</div>
					<table align="center">
         
         <form:hidden path="customerHeadName" />
         <form:hidden path="customerHeadEmail" />
         <form:hidden path="customerBranchEmail" />
         <form:hidden path="customerName" />
         <form:hidden path="utilizedBusnsAmt"  />
         <form:hidden path="balance"  />
         <form:hidden path="masterKey"  />
          <form:hidden path="poKey"  value=""/>
           <form:hidden path="transactionId"  value="" />
						 <tr>
					<td class="heading_text"><b>Supplier Name<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierName"  id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="heading_text"><b>Supplier Email<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierEmail" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="heading_text"><b>Supplier Bank<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierBank"  id="name" readonly="true"/>
					</td>
				</tr>
                  <tr>
					<td class="heading_text"><b>Supplier Bank Email<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierBankEmail"  id="name" readonly="true"/>
					</td>
				</tr>
                    <tr>
					<td class="heading_text"><b>Supplier Country<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="Suppliercountry"  id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="heading_text"><b>Supplier State<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierState" id="name" readonly="true"/>
					</td>
				</tr>
						<tr>
					<td class="heading_text"><b>Supplier City<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierCity"  id="name" readonly="true"/>
					</td>
				</tr>
					</table>
				</div>
				<div class="col-sm-12 col-md-6 col-lg-6">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center">Product Info</h3>
				</div>
					
					<table align="center">
						<tr>
							<td class="heading_text"><b>Goods Category<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goodsCategory"  id="name" readonly="true"/> </td>
						</tr>
						<tr>
							<td class="heading_text"><b>Purchase Date<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="purchaseDate" id="purchaseDate" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Goods<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goods" id="goods" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Details<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goodsDetails" id="goodsDetails" readonly="true"  />
							</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Quantity<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="quantity" id="quantity"  readonly="true"/>
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Weight<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="weight" id="weight" readonly="true" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Licence<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
								<td><form:input path="licence" id="weight" readonly="true" />
																			   </td>
						</tr>
						<tr>
							<td class="heading_text"><b>Amount<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="amount" id="amount" readonly="true"/>
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Tenure<span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="tenure" id="tenure" readonly="true"/>
								</td>
						</tr>
						
				</table>
					</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
		
							<tr>
								<td><input type="submit" class="btn btn-primary" value="Continue"></td>
								<td><a href="user" class="btn btn-success" name="back"  onclick="javascript:window.location='#';">Back</a></td>
							</tr>
							
					</table>
					</div>
					</form:form>
</div>