<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">
			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.userList"/></h3>
		</div>
		
		<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
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
						<th><spring:message code="label.blockStatus"/></th>
						<th><spring:message code="label.renewStatus"/></th>					
						<th><spring:message code="label.block"/></th>
						<th><spring:message code="label.renew"/></th>
						
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
								<td><c:out value="${user.accRenewStatus}"></c:out></td>	
								<td>
								<c:choose>
									<c:when test="${user.accessStatus=='Block' || user.accessStatus=='Unblock'}">
    									<a href="getBlockRenewApproval?id=${user.id}"
										   class="btn btn-primary" onclick="return confirm();"><spring:message code="label.approveReject"/></a>
  									</c:when>									
									<c:otherwise>
										<a href="#"  class="btn btn-primary"  disabled="true"><spring:message code="label.approveReject"/></a>    									
									</c:otherwise>
								</c:choose>
								</td>	
								<td>
								<c:choose>
									<c:when test="${user.accRenewStatus=='Pending'}">
    									<a href="getBlockRenewApproval?id=${user.id}"
										   class="btn btn-primary" onclick="return confirm();"><spring:message code="label.approveReject"/></a>
  									</c:when>									
									<c:otherwise>
										<a href="#"  class="btn btn-primary"  disabled="true"><spring:message code="label.approveReject"/></a>    									
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>

</div>
<script type="text/javascript">
function confirm(){
	  if (confirm("Are you sure you want to Close?")){
	    return true;
	  }else{
	    return false;
	  }
	}
</script>