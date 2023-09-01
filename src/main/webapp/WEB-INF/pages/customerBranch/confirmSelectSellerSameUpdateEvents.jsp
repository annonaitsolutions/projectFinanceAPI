<%@include file="taglib_includes.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
function validateForm()
{

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

<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
			</div>
			<form:form name="event" action="selectUpdateSellerSameBankEvents" method="post" commandName="sellerSameBankEventForm" onsubmit="return validateForm();">
		<div class="col-sm-12 col-md-12 col-lg-12">
				<table align="center">
				<tr>
					<td  class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
					<td  class="col-sm-6"><form:input id="customerName" path="customerName" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b></td>
					<td class="col-sm-6"><form:input id="buyer" path="buyer" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerBank"/></b></td>
					<td class="col-sm-6"><form:input id="buyerBank" path="buyerBank" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
					<td  class="col-sm-6"><form:input id="masterKey" path="masterKey" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.goods"/></b></td>
					<td class="col-sm-6"><form:input id="goods" path="goods" readonly="true"></form:input></td>
				</tr>
				
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.cost"/></b></td>
					<td class="col-sm-6"><form:input id="cost" path="cost" readonly="true"></form:input>
				</td>
				</tr>
			</table>
				
				<form:hidden path="transactionId"></form:hidden>
				<form:hidden path="id"></form:hidden>
				<form:hidden path="invoiceKey"></form:hidden>
				
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
		<table class="table-bordered theme">
			<thead>
				<tr>
					<th><spring:message code="label.stepNo"/></th>
					<th><spring:message code="label.dayNo"/></th>
					<th><spring:message code="label.date"/></th>
					<th><spring:message code="label.event"/></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td><form:input  value="1" size="3" path="first" readonly="true"/></td>
					<td id="e1"><form:input id="datepicker" path="date1" readonly="true" style="width: 97px;"/>
					
					</td>
				
					<td><form:input path="event1" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>
			     <tr>
					<td>2</td>
					<td><form:input size="3"  path="second" readonly="true"/></td> 
					<td id="e2"><form:input path="date2" id="date2" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event2" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/invoice.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>3</td>
					<td><form:input size="3"  path="third" readonly="true"/></td>
					<td id="e3"><form:input path="date3" id="date3" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event3" readonly="true"/></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/letterOfCredit.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>4</td>
					<td> <form:input size="3"  path="fourth" readonly="true"/> </td>
					<td id="e4"><form:input path="date4" id="date4" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event4" readonly="true"/></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/truckSendGoods.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>5</td>
					<td> <form:input size="3" path="fifth" readonly="true"/></td>
					<td id="e5"><form:input path="date5" id="date5" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event5" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>6</td>
					<td> <form:input size="3" path="six" readonly="true"/></td>
					<td id="e6"><form:input path="date6" id="date6" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event6" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/phone.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>7</td>
					<td><form:input size="3" path="seven" readonly="true"/> </td>
					<td id="e7"><form:input path="date7" id="date7" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event7" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankIntimates.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>8</td>
					<td><form:input size="3" path="eight" readonly="true"/></td>
					<td id="e8"><form:input path="date8" id="date8" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event8" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/accepted.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>9</td>
					<td><form:input size="3" path="nine" readonly="true"/></td>
					<td id="e9"><form:input path="date9" id="date9" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event9" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>10</td>
					<td> <form:input size="3" path="ten" readonly="true"/> </td>
					<td id="e10"><form:input path="date10" id="date10" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event10" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/goodsRecevied.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>11</td>
					<td> <form:input size="3" path="elven" readonly="true"/></td>
					<td id="e10"><form:input path="date11" id="date11" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event11" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>12</td>
					<td> <form:input size="3" path="twelve" readonly="true"/> </td>
					<td id="e10"><form:input  path="date12" id="date12" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event12" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>13</td>
					<td> <form:input size="3" path="thirteen" readonly="true"/></td>
					<td id="e10"><form:input path="date13" id="date13" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input path="event13" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>  
			
		</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 align="center"><spring:message code="label.preplanDocsToBeSubmitted"/></h3>
				</div> 
			<div class="col-sm-12 col-md-12 col-lg-12"> 
			 <table class="host-list" align="center" id="dataTable">
			<tr class="chargescolor">
	                     
	                     <th><spring:message code="label.docsName"/></th>
						<th><spring:message code="label.description"/></th>
					</tr>		
		<c:forEach items="${model.invoiceDocForms}" var="status">
				<tr>
				
					<td><input type="text" name="colourDelayI" value="${status.docName}" readonly="readonly"   /></td>
					<td><input type="text" name="colourAdvanceI" value="${status.description}" readonly="readonly" /></td>
					</tr>
					</c:forEach>
					</table></div><br>
					
					<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 align="center"><spring:message code="label.addMoreDocs"/></h3>
				</div> 
			<div class="col-sm-12 col-md-12 col-lg-12"> 
			 <table class="host-list" align="center" id="dataTable">

					<tr class="chargescolor">
	                     
	                   <th><spring:message code="label.docsName"/></th>
						<th><spring:message code="label.description"/></th>
					</tr>		
					<tr>
					<td>
							<form:input path="docName" id="docName"></form:input>	
							
						</td>
					
						<td>
							<form:input path="description" id="description"></form:input>
						</td>
						
						
			</table>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12" align="center">
			<a class="btn btn-default"  onclick="addRow('dataTable')" value="Add Document"
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.addDocs"/></a>
			<a  class="btn btn-default"  onclick="deleteRow('dataTable')" value="Remove Document"
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.removeDocs"/></a>
		</div><br>
		<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
							<td><a href="sellerSameViewAllEvents" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</form:form>
		</div>
<style>
input#event1,input#event2,input#event3,input#event4,input#event5,input#event6,input#event7,input#event8,input#event9,input#event10,input#event11,input#event12,input#event13{
    width: 600px;
}
input#first,input#second,input#third,input#fourth,input#fifth,input#six,input#seven,input#eight,input#nine,input#ten,input#elven,input#twelve,input#thirteen {
    width: 60px;
}
</style>	
	