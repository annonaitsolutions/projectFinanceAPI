<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">
			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.userList"/></h3>
		</div>
		
		<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="successMsg"><b><font color="green">${successMsg}</font></b></div>
	 	</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.userName"/></th>
						<th><spring:message code="label.role"/></th>
						<th><spring:message code="label.status"/></th>	
						<th><spring:message code="label.reason"/></th>	
						<th><spring:message code="label.accountExpiryDate"/></th>				
						<th><spring:message code="label.action"/></th>
						<th><spring:message code="label.action"/></th>
						<th><spring:message code="label.accountRenew"/></th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty userList}">
						<c:forEach items="${userList}" var="user">
							<tr>
								<td><c:out value="${user.id}"></c:out></td>
								<td><c:out value="${user.userName}"></c:out></td>
								<td><c:out value="${user.currentRole}"></c:out></td>
								<td><c:out value="${user.accessStatus}"></c:out></td>
								 <td><c:out value="${user.reason}"></c:out></td>
								<fmt:formatDate value="${user.accExpiryDate}" pattern="dd/MM/yyyy" var="formattedDate"/>
								<td><c:out value="${formattedDate}"></c:out></td>							
								<td><c:choose>
									<c:when test="${user.accessStatus=='Blocked' || user.accessStatus=='Block' || user.accessStatus=='Unblock'}">
    									<a href="#"  class="btn btn-primary"  disabled="true"><spring:message code="label.block"/></a>
  									</c:when>									
									<c:otherwise>
									<a href="#" data-toggle="modal" data-target="#login-modal" 
								       data-id="${user.id}" data-status="Block" 
										 class="open-rateDialog btn btn-primary" >Block</a>
    									<%-- <a href="blockUnblockUser?id=${user.id}&status=Block"
										 class="btn btn-primary" >Block</a> --%>
										   </c:otherwise>
								</c:choose>
								</td>
									<td><c:choose>
									<c:when test="${user.accessStatus=='Unblocked' || user.accessStatus=='Unblock' || user.accessStatus=='Block'}">
    									<a href="#"  class="btn btn-primary"  disabled="true"><spring:message code="label.unblock"/></a>
  									</c:when>  																		
									<c:otherwise>
    									<a href="blockUnblockUser?id=${user.id}&status=Unblock"
										 class="btn btn-primary" >Unblock</a>
										   </c:otherwise>
								</c:choose></td>
								<td><a href="getRenewAccount?id=${user.id}" class="btn btn-primary"><spring:message code="label.renew"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>

</div>
<section>
		<div class="container">
			<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header login_modal_header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h3 class="modal-title" id="myModalLabel">Block/Unblock</h3>
							</div>
							<div class="modal-body login-modal">
								<div class="clearfix"></div>
								<div id='social-icons-conatainer'>
									<div class='modal-body-left'>
										<form:form id="form" action="blockUnblockUser" commandName="endUserForm" >
										<div class="form-group">
											<form:hidden path="id" value="" id="id"/>
											<b><spring:message code="label.status"/></b><form:input path="accessStatus" id="status" readonly="true"/><br>
											<b><spring:message code="label.reason"/></b><form:textarea path="reason" id="reason"/>	
																																
										</div>
										
										<input type="submit" class="btn btn-primary" value="Submit">
										<button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="label.close"/></button>
										</form:form>
									</div>
								</div>																												
								<div class="clearfix"></div>
							</div>
      	
						</div>
					</div>
			</div>
        </div>
	</section>
<script>
$(document).on("click", ".open-rateDialog", function () {
    $(".modal-body #id").val( $(this).data('id') );
    $(".modal-body #status").val( $(this).data('status') );
    
});

</script>
