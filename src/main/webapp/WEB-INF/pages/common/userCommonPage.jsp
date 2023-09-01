
 <%@include file="taglib_includes.jsp" %>

 <div class="trading_masterplan">
 	
 	<div class="col-sm-6 col-md-6 col-lg-6 bg_color">
 		<a href="/annona/tUsers/tUser">
 					<div class="trading">
 						<h3><spring:message code="label.customerTr"/></h3>
 					</div>
 		<div class="col-sm-12 col-md-12 col-lg-12 table_trading">
 			
	 			<h4 style="text-align:center;"><spring:message code="label.newNot"/></h4>
	 			<c:choose>
	 			<c:when test="${empty tradenotificationList}">
	 				
 						<h5 style="text-align:center;"><spring:message code="label.noRecordsFound"/></h5>
 					
	 			</c:when>
	 			<c:otherwise>
	 			<table class="table trading_table" id="bootstrap-table">
					<thead>
						<tr>
							<th><spring:message code="label.id"/></th>
							<th><spring:message code="label.customerName"/></th>
							<th><spring:message code="label.notType"/></th>
							<th><spring:message code="label.notDate"/></th>
						
						</tr>
					</thead>
					<tbody>
						<c:if test="${! empty tradenotificationList}">
							<c:forEach items="${tradenotificationList}" var="notification">
								<tr>
									<td><c:out value="${notification.id}"></c:out></td>
									<td><c:out value="${notification.customerName}"></c:out></td>
									<td><c:out value="${notification.notificationType}"></c:out></td>
									<td><c:out value="${notification.notificationDate}"></c:out></td>
								
								</tr>
							</c:forEach>
						</c:if>
	
					</tbody>
				</table>
 				</c:otherwise>
 				</c:choose>	
		 		</div><%-- 
		 		<div class="col-sm-12 col-md-12 col-lg-12 table_trading">
		 			<h4 style="text-align:center;"><spring:message code="label.newNot"/></h4>
		 		</div> --%>
 		</a>
 	</div>
 	<div class="col-sm-6 col-md-6 col-lg-6 bg_color">
 		<a href="user">
 		<div class="Master_plan">
 			
 				<h3><spring:message code="label.customerMp"/></h3>
 			
 		</div>
 		<div class="col-sm-12 col-md-12 col-lg-12 table_trading">
 			
	 			<h4 style="text-align:center;"><spring:message code="label.newNot"/></h4>
	 			<c:choose>
	 			<c:when test="${empty notificationList}">
	 				
 						<h5 style="text-align:center;">No records found</h5>
 					
	 			</c:when>
	 			<c:otherwise>
	 			<table class="table trading_table" id="bootstrap-table">
					<thead>
						<tr>
							<th><spring:message code="label.id"/></th>
							<th><spring:message code="label.customerName"/></th>
							<th><spring:message code="label.notType"/></th>
							<th><spring:message code="label.notDate"/></th>
						
						</tr>
					</thead>
					<tbody>
						<c:if test="${! empty notificationList}">
							<c:forEach items="${notificationList}" var="notification">
								<tr>
									<td><c:out value="${notification.id}"></c:out></td>
									<td><c:out value="${notification.customerName}"></c:out></td>
									<td><c:out value="${notification.notificationType}"></c:out></td>
									<td><c:out value="${notification.notificationDate}"></c:out></td>
								
								</tr>
							</c:forEach>
						</c:if>
	
					</tbody>
				</table>
			</c:otherwise>
			</c:choose>
 		</div>
 		</a>
 	</div>
 	<audio id="startup">
		<source
			src="<%=request.getContextPath()%>/resources/audio/startup.mp3"
			type="audio/mp3">
	</audio>
 </div>
 <script>
var audio = document.getElementById("startup");
var played = ${sessionScope.played};
window.onload = playAudio();

function playAudio() {	
	if(!played){
		audio.play();
	}
}
audio.onended = function() {     
        <%session.setAttribute("played",true); %>;   
};
</script>