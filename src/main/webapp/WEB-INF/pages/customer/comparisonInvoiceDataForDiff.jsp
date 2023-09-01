<%@include file="taglib_includes.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="dt" %>
<!-- To fetch the request url -->
<c:set var="req" value="${requestScope['javax.servlet.forward.request_uri']}"/>      
<c:set var="baseURL" value="${fn:split(req,'/')}" />

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
          		 <h3 style="text-align:center"><spring:message code="label.buyerDifferentBankDetailsComparison"/></h3> 
           </div>
           
           <div class="col-sm-12 col-md-12 col-lg-12"> 
        	  <table align="center">
				<tr>
					<td  class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
					<td  class="col-sm-6">${model.sellerDiffBankEvent.customerName}</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b></td>
					<td class="col-sm-6">${model.sellerDiffBankEvent.buyer}</td>
				</tr>
				
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
					<td  class="col-sm-6">${model.sellerDiffBankEvent.masterKey}</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.goods"/></b></td>
					<td class="col-sm-6">${model.sellerDiffBankEvent.goods}</td>
				</tr>
				
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.cost"/></b></td>
					<td class="col-sm-6">${model.sellerDiffBankEvent.cost}
					</td>
				</tr>
			</table>
		<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
		
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
					</tr>
				</thead>
			<tbody>
				<tr>
					<td>1</td>
					<fmt:formatDate var='date1' value='${model.sellerDiffBankEvent.date1}' pattern="dd/MM/yyyy" />
					<c:set var="colour1" ><dt:compareDate date="${model.sellerDiffBankEvent.date1}"/></c:set>
					
					<td style="color:${colour1};width: 30px;">${model.sellerDiffBankEvent.first}</td>
					<td style="color:${colour1};width: 340px;">${model.sellerDiffBankEvent.event1}</td>					
					<td id="e1" style="width:85px;font-size: 12px;color:${colour1}">${date1}</td>
					<td></td>
					<td></td>
					<td></td>
					
			      <tr>
					<td>2</td>
					<fmt:formatDate var='date2' value='${model.sellerDiffBankEvent.date2}' pattern="dd/MM/yyyy" />
					<fmt:formatDate var='purchaseDate' value='${model.sellerDiffBankEventForm.purchaseDate}' pattern="dd/MM/yyyy" />
					<c:set var="colour2" ><dt:compareDate date="${model.sellerDiffBankEvent.date2}"/></c:set>
					<c:set var="colourA1" ><dt:compareDate date="${model.sellerDiffBankEventForm.purchaseDate}"/></c:set>
					
					<td style="color:${colour2};width: 30px;">${model.sellerDiffBankEvent.second}</td> 
					<td style="color:${colour2};width: 340px;">${model.sellerDiffBankEvent.event2}</td>					
					<td id="e2" style="width:85px;font-size: 12px;color:${colour2}">${date2}</td>					
					<td style="color:${colourA1};width:85px;font-size: 12px;">${purchaseDate}</td>
					<dt:compareDays days="${model.sellerDiffBankEventForm.poDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td>
					
					
				</tr>
			   <tr>
					<td>3</td>
					<fmt:formatDate var='date3' value='${model.sellerDiffBankEvent.date3}' pattern="dd/MM/yyyy" />
					<c:set var="colour3" ><dt:compareDate date="${model.sellerDiffBankEvent.date3}"/></c:set>
						
					<td style="color:${colour3};width: 30px;">${model.sellerDiffBankEvent.third}</td>
					<td style="color:${colour3};width: 340px;">${model.sellerDiffBankEvent.event3}</td>				
					<td id="e3" style="width:85px;color:${colour3}">${date3}</td>
					<td></td>
					<td></td>
					<td></td>
					
				</tr>
				<tr>
					<td>4</td>
					<fmt:formatDate var='date4' value='${model.sellerDiffBankEvent.date4}' pattern="dd/MM/yyyy" />
					<fmt:formatDate var='sentDate' value='${model.invoice.sentDate}' pattern="dd/MM/yyyy"/>
					<c:set var="colour4" ><dt:compareDate date="${model.sellerDiffBankEvent.date4}"/></c:set>
					<c:set var="colourB1" ><dt:compareDate date="${model.invoice.sentDate}"/></c:set>
					
					<td style="color:${colour4};width: 30px;">${model.sellerDiffBankEvent.fourth}</td>
					<td style="color:${colour4};width: 340px;">${model.sellerDiffBankEvent.event4}</td>					
					<td id="e4" style="width:85px;font-size: 12px;color:${colour4}">${date4}</td>					
					<td style="color:${colourB1};width:85px;font-size: 12px;">${sentDate}</td>
					<dt:compareDays days="${model.sellerDiffBankEventForm.sentDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td>
					
				</tr>
				<tr>
					<td>5</td>
					<fmt:formatDate var='date5' value='${model.sellerDiffBankEvent.date5}' pattern="dd/MM/yyyy" />
					<fmt:formatDate var='uploadDate' value='${model.sellerDiffBankEventForm.invoiceUpload.uploadDate}' pattern="dd/MM/yyyy" />
					<c:set var="colour5" ><dt:compareDate date="${model.sellerDiffBankEvent.date5}"/></c:set>
					<c:set var="colourC1" ><dt:compareDate date="${model.sellerDiffBankEventForm.invoiceUpload.uploadDate}"/></c:set>
					
					<td style="color:${colour5};width: 30px;">${model.sellerDiffBankEvent.fifth}</td>
					<td style="color:${colour5};width: 340px;">${model.sellerDiffBankEvent.event5}</td>					
					<td id="e5" style="color:${colour5}">${date5}</td>					
					<td style="color:${colourC1};width:85px;font-size: 12px;">${uploadDate}</td>
					<dt:compareDays days="${model.sellerDiffBankEventForm.uploadDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td>
					
				</tr>
				<tr>
					<td>6</td>
					<fmt:formatDate var='date6' value='${model.sellerDiffBankEvent.date6}' pattern="dd/MM/yyyy" />
					<c:set var="colour6" ><dt:compareDate date="${model.sellerDiffBankEvent.date6}"/></c:set>
					
					<td style="color:${colour6};width: 30px;">${model.sellerDiffBankEvent.six}</td>
					<td style="color:${colour6};width: 340px;">${model.sellerDiffBankEvent.event6}</td>					
					<td id="e6" style="color:${colour6}">${date6}</td>
					<td></td>
					<td></td>
					<td></td>
					
				</tr>
				 <tr>
					<td>7</td>
					<fmt:formatDate var='date7' value='${model.sellerDiffBankEvent.date7}' pattern="dd/MM/yyyy" />
					<c:set var="colour7" ><dt:compareDate date="${model.sellerDiffBankEvent.date7}"/></c:set>
					
					<td style="color:${colour7};width: 30px;">${model.sellerDiffBankEvent.seven}</td>
					<td style="color:${colour7};width: 340px;">${model.sellerDiffBankEvent.event7}</td>					
					<td id="e7" style="color:${colour7}">${date7}</td>
					<td></td>
					<td></td>
					<td></td>
					
				</tr>
				<tr>
					<td>8</td>
					<fmt:formatDate var='date8' value='${model.sellerDiffBankEvent.date8}' pattern="dd/MM/yyyy" />
					<c:set var="colour8" ><dt:compareDate date="${model.sellerDiffBankEvent.date8}"/></c:set>
					
					<td style="color:${colour8};width: 30px;">${model.sellerDiffBankEvent.eight}</td>
					<td style="color:${colour8};width: 340px;">${model.sellerDiffBankEvent.event8}</td>					
					<td id="e8" style="color:${colour8}">${date8}</td>
					<td></td>
					<td></td>
					<td></td>
					
				</tr>
				<tr>
					<td>9</td>
					<fmt:formatDate var='date9' value='${model.sellerDiffBankEvent.date9}' pattern="dd/MM/yyyy" />
					<c:set var="colour9" ><dt:compareDate date="${model.sellerDiffBankEvent.date9}"/></c:set>
					
					<td style="color:${colour9};width: 30px;">${model.sellerDiffBankEvent.nine}</td>
					<td style="color:${colour9};width: 340px;">${model.sellerDiffBankEvent.event9}</td>					
					<td id="e9" style="color:${colour9}">${date9}</td>
					<td></td>
					<td></td>
					<td></td>
					
				</tr>
				<tr>
					<td>10</td>
					<fmt:formatDate var='date10' value='${model.sellerDiffBankEvent.date10}' pattern="dd/MM/yyyy" />
					<c:set var="colour10" ><dt:compareDate date="${model.sellerDiffBankEvent.date10}"/></c:set>
					
					<td style="color:${colour10};width: 30px;">${model.sellerDiffBankEvent.ten}</td>
					<td style="color:${colour10};width: 340px;">${model.sellerDiffBankEvent.event10}</td>				
					<td id="e10" style="color:${colour10}">${date10}</td>
					<td></td>
					<td></td>
					<td></td>
					
				</tr> 
				
				<tr>
					<td>11</td>
					<fmt:formatDate var='date11' value='${model.sellerDiffBankEvent.date11}' pattern="dd/MM/yyyy" />
					<c:set var="colour11" ><dt:compareDate date="${model.sellerDiffBankEvent.date11}"/></c:set>
					
					<td style="color:${colour11};width: 30px;">${model.sellerDiffBankEvent.elven}</td>
					<td style="color:${colour11};width: 340px;">${model.sellerDiffBankEvent.event11}</td>					
					<td id="e11" style="color:${colour11};width:85px;font-size: 12px;">${date11}</td>
				    <td></td>
					<td></td>
					<td></td>
				</tr> 
				
				<tr>
					<td>12</td>
					<fmt:formatDate var='date12' value='${model.sellerDiffBankEvent.date12}' pattern="dd/MM/yyyy" />
					<fmt:formatDate var='receiveDate' value='${model.invoice.receiveDate}' pattern="dd/MM/yyyy" />
					<c:set var="colour12" ><dt:compareDate date="${model.sellerDiffBankEvent.date12}"/></c:set>
					<c:set var="colourF1" ><dt:compareDate date="${model.invoice.receiveDate}"/></c:set>
					
					<td style="color:${colour12};width: 30px;">${model.sellerDiffBankEvent.twelve}</td>
					<td style="color:${colour12};width: 340px;">${model.sellerDiffBankEvent.event12}</td>					
					<td id="e11" style="color:${colour12};width:85px;font-size: 12px;">${date12}</td>					
					<td style="color:${colourF1};width:85px;font-size: 12px;">${receiveDate}</td>
					<dt:compareDays days="${model.sellerDiffBankEventForm.receiveDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td>
					
				</tr> 
								
				<tr>
					<td>13</td>
					<fmt:formatDate var='date13' value='${model.sellerDiffBankEvent.date13}' pattern="dd/MM/yyyy" />
					<c:set var="colour13" ><dt:compareDate date="${model.sellerDiffBankEvent.date13}"/></c:set>
					
					<td style="color:${colour13};width: 30px;">${model.sellerDiffBankEvent.thirteen}</td>
					<td style="color:${colour13};width: 340px;">${model.sellerDiffBankEvent.event13}</td>					
					<td id="e11" style="color:${colour13};width:85px;font-size: 12px;"></td>
					<td></td>
					<td></td>
					<td></td>
				  
				</tr> 
				
				
				<tr>
					<td>14</td>
					<fmt:formatDate var='date14' value='${model.sellerDiffBankEvent.date14}' pattern="dd/MM/yyyy" />
					<c:set var="colour14" ><dt:compareDate date="${model.sellerDiffBankEvent.date14}"/></c:set>
					
					<td style="color:${colour14};width: 30px;">${model.sellerDiffBankEvent.fourteen}</td>
					<td style="color:${colour14};width: 340px;">${model.sellerDiffBankEvent.event14}</td>					
					<td id="e10" style="color:${colour14}">${date14}</td>
					<td></td>
					<td></td>
					<td></td>
					
				</tr> 
				
				
				<tr>
					<td>15</td>
					<fmt:formatDate var='date15' value='${model.sellerDiffBankEvent.date15}' pattern="dd/MM/yyyy" />
					<c:set var="colour15" ><dt:compareDate date="${model.sellerDiffBankEvent.date15}"/></c:set>
					
					<td style="color:${colour15};width: 30px;">${model.sellerDiffBankEvent.fifteen}</td>
					<td style="color:${colour15};width: 340px;">${model.sellerDiffBankEvent.event15}</td>					
					<td id="e10" style="color:${colour15}">${date15}</td>
					<td></td>
					<td></td>
					<td></td>
					
				</tr> 
				
				<tr>
					<td>16</td>
					<fmt:formatDate var='date16' value='${model.sellerDiffBankEvent.date16}' pattern="dd/MM/yyyy" />
					<fmt:formatDate var='closeDate' value='${model.invoice.closeDate}' pattern="dd/MM/yyyy" />
					<c:set var="colour16" ><dt:compareDate date="${model.sellerDiffBankEvent.date16}"/></c:set>
					<c:set var="colourG1" ><dt:compareDate date="${model.invoice.closeDate}"/></c:set>
					
					<td style="color:${colour16};width: 30px;">${model.sellerDiffBankEvent.sixteen}</td>
					<td style="color:${colour16};width: 340px;">${model.sellerDiffBankEvent.event16}</td>					
					<td id="e10" style="color:${colour16}">${date16}</td>					
					<td style="color:${colourG1};width:85px;font-size: 12px;">${closeDate}</td>
					<dt:compareDays days="${model.sellerDiffBankEventForm.closeDays}" />
						<td style="width:45px;font-size: 12px;">${delayDays }</td>
						<td style="width:45px;font-size: 12px;">${advDays}</td>
					
				</tr> 
				
				
				
		</table>
		 
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
					<c:forEach items="${model.invoiceDocs}" var="status">
					<tr>
				
						<td>${status.docName}</td>
						<td>${status.description}</td>
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
					<c:forEach items="${model.invoiceUploads}" var="status">
				<tr>
					<fmt:formatDate var='uploadDate' value='${status.uploadDate}' pattern="dd/MM/yyyy" />
					<td style="width: 92px;">${uploadDate}></td>
				
					<td>${status.document}</td>
					<td>${status.reason}</td>
				</tr>
					</c:forEach>
					</table>
					
					
		</div>
		<div class="col-sm-12 col-md-12"> &nbsp;</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
		
        <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
      	 	 <h3><spring:message code="label.feedBackModule"/></h3>
		</div>			
		<table align="center" width="600">

			<tr>
				<td><b><spring:message
							code="label.estimatedNumberOfDaysForTransaction" /></b></td>

				<td><c:out
						value="${model.sellerDiffBankEventForm.estimatedDays}" /></td>
			</tr>
			<tr>
				<td><b><spring:message
							code="label.actualNumberOfDaysForTransacion" /></b></td>

				<td class="col-sm-6">
				<c:choose>
				<c:when test="${closeDate == null || purchaseDate == null}">
				<c:set	var="actualNo" value="Pending" />
				</c:when>
				<c:otherwise>
				<c:set	var="actualNo" value="${model.sellerDiffBankEventForm.actualDays}" />				
				</c:otherwise>
				</c:choose> 
				</td>
			</tr>


			<tr>
				<td><b><spring:message
							code="label.feedback" /></b></td>

				<td>
				<c:choose>
				<c:when test="${actualNo == 'Pending'}">
				<c:set	var="feedback" value="Pending" />
				</c:when>
				<c:otherwise>
				<c:set	var="feedback" value="${model.sellerDiffBankEventForm.status}" />				
				</c:otherwise>
				</c:choose> 
				<c:out value="${feedback}" />
				</td>

			</tr>



		</table>
		 </div>
      
          <div class="col-sm-12 col-md-12 col-lg-12">
         	<c:if test="${baseURL[1] == 'bnkEmp'}"><c:set var="back" value="comparisonBankInvoice"/></c:if>
					<c:if test="${baseURL[1] == 'users'}"><c:set var="back" value="comparisonInvoice"/></c:if>
					<c:if test="${baseURL[1] == 'userBranch'}"><c:set var="back" value="comparisonBranchInvoice"/></c:if>
					<table align="center">					
						<tr>
							<td><a href="${back}" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>         
        </div>
