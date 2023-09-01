<%@include file="taglib_includes.jsp" %>
<script>
  function validateForm() {
	var a = document.forms["myForm"]["customerStatus"].value;
	    var b=document.forms["myForm"]["customerComment"].value;
	    if(a=="Rejected")
	    	{
	    	 if (b == null || b == "") {
	 	        alert("Comment box must be filled out");
	 	        return false;
	 	    }
	    	}
   
 
	}
  </script>
 <div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
        	   <h3 style="text-align:center"><spring:message code="label.buyerSameBankDetails"/></h3> 
            </div>
            <form:form action="confirmSelectCustomerSameBankEvents" name="myForm" onsubmit="return validateForm();" method="post" commandName="buyerSameBankEventForm"> 
            <form:hidden path="id"/>
            <div class="col-sm-12 col-md-12 col-lg-12">
             <label><spring:message code="label.customerName"/>:</label><form:input id="customerName" path="customerName"  readonly="true"></form:input>
			<label><spring:message code="label.supplierName"/>:</label><form:input id="supplier" path="supplier" readonly="true"></form:input>
			<label><spring:message code="label.supplierBank"/>:</label><form:input id="supplierBank" path="supplierBank" readonly="true"></form:input>
			<label><spring:message code="label.masterKey"/>:</label><form:input id="masterKey" path="masterKey" readonly="true"></form:input>
			<label><spring:message code="label.goods"/>:</label><form:input id="goods" path="goods" readonly="true"></form:input>
            <label><spring:message code="label.cost"/>:</label><form:input path="cost" readonly="true"/>  
	           			
				<form:hidden path="bankReq"></form:hidden>
				<form:hidden path="status"></form:hidden>
				<form:hidden path="comment"></form:hidden>
				<form:hidden path="customerCol"></form:hidden>
				<form:hidden path="transactionId"></form:hidden>
			</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
		<table class="table-bordered">
			<thead>
				<tr>
					<th><spring:message code="label.stepNumber"/></th>
					<th><spring:message code="label.dayNumber"/></th>
					<th><spring:message code="label.date"/></th>
					<th><spring:message code="label.event"/></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td><form:input  value="1" size="3" style="width: 60px;" path="first" readonly="true"/></td>
					<td id="e1"><form:input path="date1" id="datepicker" readonly="true"></form:input></td>
					
					<td><form:input path="event1" style="width: 575px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/Contract.jpg" class="imagesize" alt="img"></img></td>
				</tr>
			      <tr>
					<td>2</td>
					<td><form:input size="3" style="width: 60px;" path="second" readonly="true"/></td> 
					<td id="e2"><form:input path="date2" id="date2" readonly="true"></form:input></td>
					<td><form:input path="event2" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
			   <tr>
					<td>3</td>
					<td><form:input size="3" style="width: 60px;" path="third" readonly="true"/></td>
					<td id="e3"><form:input path="date3" id="date3" readonly="true"></form:input></td>
					<td><form:input path="event3" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>4</td>
					<td> <form:input size="3" style="width: 60px;" path="fourth" readonly="true"/> </td>
					<td id="e4"><form:input path="date4" id="date4" readonly="true"></form:input></td>
					<td><form:input path="event4" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>5</td>
					<td> <form:input size="3" style="width: 60px;" path="fifth" readonly="true"/></td>
					<td id="e5"><form:input path="date5" id="date5" readonly="true"></form:input></td>
					<td><form:input path="event5" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>6</td>
					<td> <form:input size="3" style="width: 60px;" path="six" readonly="true"/></td>
					<td id="e6"><form:input path="date6" id="date6" readonly="true"></form:input></td>
					<td><form:input path="event6" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				 <tr>
					<td>7</td>
					<td><form:input size="3" style="width: 60px;" path="seven" readonly="true"/> </td>
					<td id="e7"><form:input path="date7" id="date7" readonly="true"></form:input></td>
					<td><form:input path="event7" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>8</td>
					<td><form:input size="3" style="width: 60px;" path="eight" readonly="true"/></td>
					<td id="e8"><form:input path="date8" id="date8" readonly="true"></form:input></td>
					<td><form:input path="event8" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/2.jpg" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>9</td>
					<td><form:input size="3" style="width: 60px;" path="nine" readonly="true"/></td>
					<td id="e9"><form:input path="date9" id="date9" readonly="true"></form:input></td>
					<td><form:input path="event9" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>10</td>
					<td> <form:input size="3" style="width: 60px;" path="ten" readonly="true"/> </td>
					<td id="e10"><form:input path="date10" id="date10" readonly="true"></form:input></td>
					<td><form:input path="event10" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>11</td>
					<td> <form:input size="3" style="width: 60px;" path="elven" readonly="true"/></td>
					<td id="e10"><form:input path="date11" id="date11" readonly="true"></form:input></td>
					<td><form:input path="event11" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr> 
				
		</table>
		</div>
		<div class="col-sm-12 col-md-12">&nbsp;</div>
		<table align="center">
					 
					<tr>
						<td><spring:message code="label.t&c"/>:<font color="red">*</font></td>
						<td>
							<select name="customerStatus">
							<option style="color:green" value="Yes"><spring:message code="label.yes"/></option>
						<option style="color:red" value="No"><spring:message code="label.no"/></option>
							</select>
						</td>
					</tr> 
					
					<tr>
						<td><spring:message code="label.comment"/> :</td>
						<td><textarea name="customerComment" rows="5" cols="20"></textarea></td>
					</tr>
		</table>
		<div class="col-sm-12 col-md-12">&nbsp;</div>
		<div class="col-sm-12 col-md-12">
			<table align="center">
				<tr>
					<td><input type="submit" size="3" value="Update" class="btn btn-primary"></td>
					<td><button type="button" name="Back"  onclick="history.back()" class="btn btn-success">Back</button></td>
				</tr>
			</table>
		</div>
            
            </form:form>
         
        </div>
   <style>
	img.imagesize {
    width: 60px;
    height: 60px;
    max-width: 60px;
}
td{
	text-align:center;
}
</style>