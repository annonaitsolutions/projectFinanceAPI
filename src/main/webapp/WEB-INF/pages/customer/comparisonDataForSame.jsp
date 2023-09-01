<%@include file="taglib_includes.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="dt" %>
<script src="<%=request.getContextPath()%>/resources/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/highcharts-3d.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/exporting.js"></script>

<!-- To fetch the request url -->
<c:set var="req" value="${requestScope['javax.servlet.forward.request_uri']}"/>      
<c:set var="baseURL" value="${fn:split(req,'/')}" />
         


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">		
          		 <h3 style="text-align:center"><spring:message code="label.supplierSameBankDetailsComparison"/></h3> 
           </div>           
           <div class="col-sm-12 col-md-12 col-lg-12" style="right: 36px; "> 
        	  <table align="center">
        	 
				<tr>
					<td  class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
					<td  class="col-sm-6"><c:out value="${model.buyerSameBankEvent.customerName}" /></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.supplierName"/></b></td>
					<td class="col-sm-6"><c:out value="${model.buyerSameBankEvent.supplier}"/></td>
				</tr>
				
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
					<td  class="col-sm-6"><c:out value="${model.buyerSameBankEvent.masterKey}"/></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.goods"/></b></td>
					<td class="col-sm-6"><c:out value="${model.buyerSameBankEvent.goods}" /></td>
				</tr>
				
				
			</table>
		<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<table class="table table-responsive table-bordered">
				<thead>
					<tr>
					<th><spring:message code="label.stepNo"/></th>
						<th><spring:message code="label.dayNo"/></th>
						<th><spring:message code="label.event"/></th>
						<th><spring:message code="label.preplanDate"/></th>
						<th><spring:message code="label.systemCapturedDate"/></th>
						<th><spring:message code="label.delayInDays"/></th>
						<th><spring:message code="label.advanceInDays"/></th>
						<th><spring:message code="label.dispute"/></th>
					
					</tr>
				</thead>
			<tbody>
				<tr>
					<td>1</td>
					<fmt:formatDate var='date1' value='${model.buyerSameBankEvent.date1}' pattern="dd/MM/yyyy" />					
					<c:set var="colour1" ><dt:compareDate date="${model.buyerSameBankEvent.date1}"/></c:set>
											
					<td style="color:${colour1};width:30px;"><c:out value="${model.buyerSameBankEvent.first}" /></td>
					<td style="color:${colour1};width: 340px;"><c:out value="${model.buyerSameBankEvent.event1}"/></td>					
					<td id="e1" style="color:${colour1};width:85px;"><c:out value="${date1}"/></td>									
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			      <tr>
					<td>2</td>
					<fmt:formatDate  var='date2' value='${model.buyerSameBankEvent.date2}' pattern="dd/MM/yyyy" />
					<fmt:formatDate  var='purchaseDate' value='${model.purchaseOrders.purchaseDate}' pattern="dd/MM/yyyy" type="date"/>
					<c:set var="colour2" ><dt:compareDate date="${model.buyerSameBankEvent.date2}"/></c:set>
					<c:set var="colourA1" ><dt:compareDate date="${model.purchaseOrders.purchaseDate}"/></c:set>
					
					<td style="color:${colour2};width:30px;"><c:out value="${model.buyerSameBankEvent.second}"/></td> 
					<td style="color:${colour2};width: 340px;"><c:out value="${model.buyerSameBankEvent.event2}"/></td>					
					<td id="e2" style="width:85px;color:${colour2}"><c:out value="${date2}" /></td>					
					<td style="width:85px;color:${colourA1}"><c:out value="${purchaseDate}"/></td>				
					 <dt:compareDays days="${model.buyerSameBankEventForm.poDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td> 
					<td></td>
				</tr>
			   <tr>
					<td>3</td>
					<fmt:formatDate var='date3' value='${model.buyerSameBankEvent.date3}' pattern="dd/MM/yyyy" />
					<c:set var="colour3" ><dt:compareDate date="${model.buyerSameBankEvent.date3}"/></c:set>
									
					<td style="color:${colour3};width:30px;"><c:out  value="${model.buyerSameBankEvent.third}"/></td>
					<td style="color:${colour3};width: 340px;"><c:out value="${model.buyerSameBankEvent.event3}"/></td>					
					<td id="e3" style="color:${colour3};width:85px;"><c:out value="${date3}"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>4</td>
					<fmt:formatDate var='date4' value='${model.buyerSameBankEvent.date4}' pattern="dd/MM/yyyy" />
					<fmt:formatDate var='sentDate' value='${model.purchaseOrders.sentDate}' pattern="dd/MM/yyyy"/>
					<c:set var="colour4" ><dt:compareDate date="${model.buyerSameBankEvent.date4}"/></c:set>
					<c:set var="colourB1" ><dt:compareDate date="${model.purchaseOrders.sentDate}"/></c:set>
					
					<td style="color:${colour4};width:30px;"><c:out value="${model.buyerSameBankEvent.fourth}"/> </td>
					<td style="color:${colour4};width: 340px;"><c:out value="${model.buyerSameBankEvent.event4}"/></td>
					<td id="e4" style="color:${colour4};width:60px;"><c:out value="${date4}"/></td>										
					<td style="color:${colourB1};width:85px;font-size: 12px;"><c:out value="${sentDate}"/></td>
					 <dt:compareDays days="${model.buyerSameBankEventForm.sentDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td> 					
					<td></td>
				</tr>
				<tr>
					<td>5</td>
					<fmt:formatDate var='date5' value='${model.buyerSameBankEvent.date5}' pattern="dd/MM/yyyy" />
					<fmt:formatDate var='uploadDate' value='${model.buyerSameBankEventForm.supplierDate}' pattern="dd/MM/yyyy" />
					<c:set var="colour5" ><dt:compareDate date="${model.buyerSameBankEvent.date5}"/></c:set>
					<c:set var="colourC1" ><dt:compareDate date="${model.buyerSameBankEventForm.supplierDate}"/></c:set>
									
					<td style="color:${colour5};width:30px;"><c:out value="${model.buyerSameBankEvent.fifth}" /></td>
			    	<td style="color:${colour5};width: 340px;"><c:out value="${model.buyerSameBankEvent.event5}"/></td>					
					<td id="e5" style="color:${colour5}"><c:out value="${date5}"/></td>					
					<td style="color:${colourC1};width:85px;font-size: 12px;"><c:out value="${uploadDate}" /></td>
					<dt:compareDays days="${model.buyerSameBankEventForm.uploadDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td> 	
								
					<td></td>
				</tr>
				<tr>
					<td>6</td>
					<fmt:formatDate var='date6' value='${model.buyerSameBankEvent.date6}' pattern="dd/MM/yyyy" />
					<c:set var="colour6" ><dt:compareDate date="${model.buyerSameBankEvent.date6}"/></c:set>
					
					<td style="color:${colour6};width:30px;"><c:out value="${model.buyerSameBankEvent.six}"/></td>
					<td style="color:${colour6};width: 340px;"><c:out value="${model.buyerSameBankEvent.event6}"/></td>					
					<td id="e6" style="color:${colour6}"><c:out value="${date6}"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				 <tr>
					<td>7</td>
					<fmt:formatDate var='date7' value='${model.buyerSameBankEvent.date7}' pattern="dd/MM/yyyy" />
					<fmt:formatDate var='forwardDate' value='${model.purchaseOrders.forwardDate}' pattern="dd/MM/yyyy" />
					<c:set var="colour7" ><dt:compareDate date="${model.buyerSameBankEvent.date7}"/></c:set>
					<c:set var="colourD1" ><dt:compareDate date="${model.purchaseOrders.forwardDate}"/></c:set>					
					
					<td style="color:${colour7};width:30px;"><c:out value="${model.buyerSameBankEvent.seven}"/> </td>
			        <td style="color:${colour7};width: 340px;"><c:out value="${model.buyerSameBankEvent.event7}"/></td>					
					<td id="e7" style="color:${colour7}"><c:out value="${date7}" /></td>					
					<td style="color:${colourD1};width:85px;font-size: 12px;"><c:out value="${forwardDate}" /></td>
					<dt:compareDays days="${model.buyerSameBankEventForm.forwardDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td> 	
							
					<td></td>
				</tr>
				<tr>
					<td>8</td>
					<fmt:formatDate var='date8' value='${model.buyerSameBankEvent.date8}' pattern="dd/MM/yyyy" />
					<c:set var="colour8" ><dt:compareDate date="${model.buyerSameBankEvent.date8}"/></c:set>
					
					<td style="color:${colour8};width:30px;"><c:out value="${model.buyerSameBankEvent.eight}" /></td>
			        <td style="color:${colour8};width: 340px;"><c:out value="${model.buyerSameBankEvent.event8}"/></td>					
					<td id="e8" style="color:${colour8}"><c:out value="${date8}"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>9</td>
					<fmt:formatDate var='date9' value='${model.buyerSameBankEvent.date9}' pattern="dd/MM/yyyy" />
					<fmt:formatDate var='poPayDate' value='${model.purchaseOrders.poPayDate}' pattern="dd/MM/yyyy" />
					<c:set var="colour9" ><dt:compareDate date="${model.buyerSameBankEvent.date9}"/></c:set>
					<c:set var="colourE1" ><dt:compareDate date="${model.purchaseOrders.poPayDate}"/></c:set>
					
					<td style="color:${colour9};width:30px;"><c:out value="${model.buyerSameBankEvent.nine}"/></td>
		        	<td style="color:${colour9};width: 340px;"><c:out value="${model.buyerSameBankEvent.event9}"/></td>					
					<td id="e9" Style="color:${colour9}"><c:out value="${date9}"/></td>					
					<td style="color:${colourE1};width:85px;font-size: 12px;"><c:out value="${poPayDate}" /></td>
					<dt:compareDays days="${model.buyerSameBankEventForm.poPaymentDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td> 						
					
					<td></td>
				</tr>
				<tr>
					<td>10</td>
					<fmt:formatDate var='date10' value='${model.buyerSameBankEvent.date10}' pattern="dd/MM/yyyy" />
					<fmt:formatDate var='receiveDate' value='${model.purchaseOrders.receiveDate}' pattern="dd/MM/yyyy" />
					<c:set var="colour10" ><dt:compareDate date="${model.buyerSameBankEvent.date10}"/></c:set>
					<c:set var="colourF1" ><dt:compareDate date="${model.purchaseOrders.receiveDate}"/></c:set>
					
					<td style="color:${colour10};width:30px;"><c:out value="${model.buyerSameBankEvent.ten}"/> </td>
		         	<td style="color:${colour10};width: 340px;"><c:out value="${model.buyerSameBankEvent.event10}"/></td>					
					<td id="e10" style="color:${colour10}"><c:out value="${date10}"/></td>					
					<td style="color:${colourF1};width:85px;font-size: 12px;"><c:out value="${receiveDate}" /></td>
					<dt:compareDays days="${model.buyerSameBankEventForm.receiveDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td> 	
					
					<td></td>
				</tr>
				<fmt:formatDate var='date11' value='${model.buyerSameBankEvent.date11}' pattern="dd/MM/yyyy" />
				<c:if test="${model.comparisonList eq null}"><spring:message code="noRecordsFound" /></c:if> 
				<c:if test="${model.comparisonList ne null}">
				<c:forEach items="${model.comparisonList}" var="status">
				
				<tr>
				
					<td></td>
					<td> </td>
					<td></td>					
					<td></td>
					
					<fmt:formatDate var='disputDate' value='${status.disputeDate}' pattern="dd/MM/yyyy" />
					<c:set var="colourI1" ><dt:compareDate date="${status.disputeDate}"/></c:set>
					
					<td style="color:${colourI1};width:85px;font-size: 12px;"><c:out value="${disputDate}" /></td>
					<dt:compareDays days="${status.disputeDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td> 	
					
					<td><c:out value="${status.disputeRaised}"/></td>
					
					
				</tr> 
				<tr>
					<td></td>
					<td> </td>
					<td></td>					
					<td></td>
					<fmt:formatDate var='statusDate' value='${status.statusDate}' pattern="dd/MM/yyyy" />
					<c:set var="colourJ1" ><dt:compareDate date="${status.statusDate}"/></c:set>
					
					<td style="color:${colourJ1};width:85px;font-size: 12px;"><c:out value="${statusDate}"/></td>
					<dt:compareDays days="${status.statusDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td> 	
					
					<td><c:out value="${status.disputeUpdate}"/></td>
				</tr> 
				</c:forEach>
				</c:if>
				
				<tr>
					<td>11</td>
					<fmt:formatDate var='closePoDate' value='${model.purchaseOrders.closePoDate}' pattern="dd/MM/yyyy" />
					<c:set var="colour11" ><dt:compareDate date="${model.buyerSameBankEvent.date11}"/></c:set>
					<c:set var="colourG1" ><dt:compareDate date="${model.purchaseOrders.closePoDate}"/></c:set>
								
					<td style="color:${colour11};width:30px;"><c:out value="${model.buyerSameBankEvent.elven}"/></td>
         			<td style="color:${colour11};width: 340px;"><c:out value="${model.buyerSameBankEvent.event11}"/></td>					
					<td id="e11" style="color:${colour11};"><c:out value="${date11}"/></td>				    
					<td style="color:${colourG1};width:85px; font-size: 12px"><c:out value="${closePoDate}" /></td>
					<dt:compareDays days="${model.buyerSameBankEventForm.poCloseDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td> 	
					
					<td></td>
				</tr> 
				
		</table>
		 </div>
	</div>	
		<div class="col-sm-12 col-md-6 col-lg-6"> 
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 align="center"><spring:message code="label.preplanDocs"/></h3>
				</div> 
			 <table class="host-list"  id="dataTable">
					<tr class="chargescolor">
	                   <th><spring:message code="label.docsName"/></th>
						<th><spring:message code="label.description"/></th>
					</tr>		
					<c:forEach items="${model.purchaseDocForms}" var="status">
					<tr>
				
						<td><c:out value="${status.docName}" /></td>
						<td><c:out value="${status.description}" /></td>
					</tr>
					</c:forEach>
			</table>
		</div>
		<div class="col-sm-12 col-md-6 col-lg-6"> 		
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 align="center"><spring:message code="label.systemCapturedDocs"/></h3>
				</div> 
			 <table class="host-list"  id="dataTable">
					<tr class="chargescolor">
	                    <th><spring:message code="label.date"/></th> 
	                     <th><spring:message code="label.docsName"/></th>
						<th><spring:message code="label.description"/></th>
					</tr>		
					<c:forEach items="${model.poUploadForms}" var="status">
				<tr>
					<fmt:formatDate var='datePO' value='${status.uploadDate}' pattern="dd/MM/yyyy" />
					<td><c:out value="${datePO}"/></td>
				
					<td><c:out value="${status.document}"  /></td>
					<td><c:out value="${status.reason}"/></td>
				</tr>
					</c:forEach>
					</table>
					
					
		</div>			
		<div class="col-sm-12 col-md-12"> &nbsp;</div>
		<div class="col-sm-6 col-md-6 col-lg-6">
		
        <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
      	 	 <h3><spring:message code="label.feedBackModule"/></h3>
		</div>			
					
					<table  width="500">
					
					<tr>
								<td><b><spring:message code="label.estimatedNumberOfDaysForTransaction"/></b></td>
								
								<td><c:out value="${model.buyerSameBankEventForm.estimatedDays}" /> 
																	
								</td>																
							</tr>	
							<tr>
								<td><b><spring:message code="label.actualNumberOfDaysForTransacion"/></b></td>
								
								<td>
								<c:choose>
				<c:when test="${closePoDate == null || purchaseDate == null}">
				<c:set	var="actualNo" value="Pending" />
				</c:when>
				<c:otherwise>
				<c:set	var="actualNo" value="${model.buyerSameBankEventForm.actualDays}" />				
				</c:otherwise>
				</c:choose> 
						<c:out value="${actualNo}" />											
								</td>																
							</tr>	
							
							
							<tr>
								<td><b><spring:message code="label.feedback"/></b></td>
								
								<td><c:choose>
				<c:when test="${actualNo == 'Pending'}">
				<c:set	var="feedback" value="Pending" />
				</c:when>
				<c:otherwise>
				<c:set	var="feedback" value="${model.buyerSameBankEventForm.status}" />				
				</c:otherwise>
				</c:choose> 
						<c:out value="${feedback}" />   </td>																

							</tr>	
							<tr>
								<td><b><spring:message code="label.estimatedExtraInterestAmount"/></b></td>
								
								<td><c:out value="${model.buyerSameBankEventForm.interest}" /> 
																						
							</tr>	
							
						
					</table>
		 </div>
       <div class="col-sm-6 col-md-6 col-lg-6">
			<div id="container"></div>
		</div>
        <div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
        
         	<div class="col-sm-12 col-md-12 col-lg-12">
         	<c:if test="${baseURL[1] == 'bnkEmp'}"><c:set var="back" value="comparisonBankPO"/></c:if>
					<c:if test="${baseURL[1] == 'users'}"><c:set var="back" value="comparisonPO"/></c:if>
					<c:if test="${baseURL[1] == 'userBranch'}"><c:set var="back" value="comparisonBranchPO"/></c:if>
					<table align="center">					
						<tr>
							<td><a href="${back}" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
        </div>
<script>
        $(function () {
    // Set up the chart
    var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',            
            type: 'column',
            options3d: {
                enabled: true,
                alpha: 15,
                beta: 15,
                depth: 50,
                viewDistance: 25
            }
        },
        title: {
            text: 'Events'
        },      
        
        xAxis: { 
        	categories:[${model.buyerSameBankEventForm.interest}],
            title: {
                text: 'Interest'
            },
            
            labels: {
                rotation: -45,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Days'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">Interest :{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} </b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
            	dataLabels: {
                    enabled: true,
                    rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    format: '{point.y}', // one decimal
                    y: 10, // 10 pixels down from the top
                    style: {
                        fontSize: '10px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                },
                borderWidth: 0,
                depth: 25
            }
        },
        series:[{
        	name:'Estimated Days',
        	data:[${model.buyerSameBankEventForm.estimatedDays}]
        },{
        	name:'Actual Days',
        	data:[${model.buyerSameBankEventForm.actualDays}]        	
        }]
       
    });    
});
  </script>
 
<style>
#container, #sliders {
    min-width: 310px; 
    max-width: 800px;
    margin: 0 auto;
}
#container {
    height: 300px; 
}
</style>