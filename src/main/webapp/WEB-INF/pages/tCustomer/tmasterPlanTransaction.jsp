<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 body_fixed">
			
			
				  <table align="center" width="800" height="80" style="margin-top: 50px;">
					<tr>
						<td><b><spring:message code="label.transactionId"/>:</b></td>
						<td>${model.tMasterPlanForm.transactionId}</td>
					<tr>
						<td><b><spring:message code="label.transactionType"/>:</b></td>
						<td><spring:message code="label.masterPlan"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.transactionStatus"/>:</b></td>
						<td><font color="green"><spring:message code="label.submitSuccessfully"/>.</font></td>
					</tr>
					<tr>
						<td><b><spring:message code="label.note"/> :</b></td>
						<td><font color="green"><font color="orange"><spring:message code="label.noteMessage"/> .</font></font></td>
					</tr>
					<tr>
						<td><b><spring:message code="label.note"/> :</b></td>
						<td><font color="orange"><spring:message code="label.noteMessage1"/> <font color="black"><spring:message code="label.or"/><a href="tmasterPlanPending"><font color="red"><spring:message code="label.clickHere"/></a></font></td>
					</tr>

				</table>  
			
</div>	
<style>
.bankemp_footer.col-sm-12 {
    margin-top: 125px;
}
</style>