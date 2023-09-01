<%@include file="taglib_includes.jsp" %>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
     	       <h3 align="center"><spring:message code="label.buyerDiffBankDet"/></h3> 
            </div>
            <form:form action="selectSendingDiffBankEvents" method="post" commandName="buyerDiffBankEventForm"> 
            <label><spring:message code="label.customerName"/>:</label><form:input id="customerName" path="customerName"  readonly="true"></form:input>
			<label><spring:message code="label.supplierName"/>:</label><form:input id="supplier" path="supplier" readonly="true"></form:input>
			<label><spring:message code="label.supplierBank"/>:</label><form:input id="supplierBank" path="supplierBank" readonly="true"></form:input>
			<label><spring:message code="label.masterKey"/>:</label><form:input id="masterKey" path="masterKey" readonly="true"></form:input>
			<label><spring:message code="label.goods"/>:</label><form:input id="goods" path="goods" readonly="true"></form:input> 
            <label><spring:message code="label.cost"/>:</label><form:input path="cost" readonly="true"/> 
<form:hidden path="id"></form:hidden>
<form:hidden path="status"></form:hidden>
<form:hidden path="bankReq"></form:hidden>
<form:hidden path="customerStatus"></form:hidden>
<form:hidden path="transactionId"></form:hidden>
<form:hidden path="customerCol"></form:hidden>
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
					<td><form:input  value="1" size="3" path="first" readonly="true"/></td>
					<td id="e1"><form:input path="date1" readonly="true"></form:input></td>
					
					<td><form:input path="event1" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/Contract.jpg" class="imagesize" alt="img"></img></td>
				</tr>
			      <tr>
					<td>2</td>
					<td><form:input size="3"  path="second" onchange="valid()" readonly="true"/></td> 
					<td id="e2"><form:input path="date2" readonly="true"></form:input></td>
					<td><form:input path="event2" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
			   <tr>
					<td>3</td>
					<td><form:input size="3"  path="third" onchange="valid1()" readonly="true"/></td>
					<td id="e3"><form:input path="date3" readonly="true"></form:input></td>
					<td><form:input path="event3" readonly="true" />
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>4</td>
					<td> <form:input size="3"  path="fourth" onchange="valid2()" readonly="true"/> </td>
					<td id="e4"><form:input path="date4" readonly="true"></form:input></td>
					<td><form:input path="event4" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>5</td>
					<td> <form:input size="3" path="fifth" onchange="valid3()" readonly="true"/></td>
					<td id="e5"><form:input path="date5" readonly="true"></form:input></td>
					<td><form:input path="event5" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>6</td>
					<td> <form:input size="3" path="six" onchange="valid4()" readonly="true"/></td>
					<td id="e6"><form:input path="date6" readonly="true"></form:input></td>
					<td><form:input path="event6" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				 <tr>
					<td>7</td>
					<td><form:input size="3" path="seven" onchange="valid5()" readonly="true"/> </td>
					<td id="e7"><form:input path="date7" readonly="true"></form:input></td>
					<td><form:input path="event7" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>8</td>
					<td><form:input size="3" path="eight" onchange="valid6()" readonly="true"/></td>
					<td id="e8"><form:input path="date8" readonly="true"></form:input></td>
					<td><form:input path="event8" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/2.jpg" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>9</td>
					<td><form:input size="3" path="nine" onchange="valid7()" readonly="true"/></td>
					<td id="e9"><form:input path="date9" readonly="true"></form:input></td>
					<td><form:input path="event9" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>10</td>
					<td> <form:input size="3" path="ten" onchange="valid8()" readonly="true"/> </td>
					<td id="e10"><form:input path="date10" readonly="true"></form:input></td>
					<td><form:input path="event10" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>11</td>
					<td> <form:input size="3" path="elven" onchange="valid9()" readonly="true"/></td>
					<td id="e11"><form:input path="date11" readonly="true"></form:input></td>
					<td><form:input path="event11" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>12</td>
					<td> <form:input size="3" path="twelve" onchange="valid10()" readonly="true"/></td>
					<td id="e12"><form:input path="date12" readonly="true"></form:input></td>
					<td><form:input path="event12" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>13</td>
					<td> <form:input size="3" path="thirteen" onchange="valid11()" readonly="true"/></td>
					<td id="e13"><form:input path="date13" readonly="true"></form:input></td>
					<td><form:input path="event13" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr> 
				
		</table>
		<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
		<div >${success}</div>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
		<table align="center">
			<tr>
				<td><input type="submit" size="3" value="Send" class="btn btn-primary"></td>
				<td><button type="button" name="Back"  onclick="javascript:window.location='/annona/bnkEmp/bankEmp';" class="btn btn-success">Back</button></td>
			</tr>
		</table>
		</div>
            
            </form:form>
         
        </div>

<script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
</script>

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