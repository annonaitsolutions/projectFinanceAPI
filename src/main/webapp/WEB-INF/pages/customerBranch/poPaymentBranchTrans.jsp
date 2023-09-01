<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">

				<table align="center" width="400">


					<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/>:</b></td>
						<td>${model.purchaseOrderForm.transactionId}</td>
					<tr>
						<td><b><spring:message code="label.transactionType"/>:</b></td>
						<td><spring:message code="label.poPayment"/></td>
					</tr>

					
					<tr>
						<td><b><spring:message code="label.transactionStatus"/>:</b></td>
						<td><font color="green"><spring:message code="label.updateSuccessfully"/>.</font></td>
					</tr>
				

				</table>

			</div>
			<div class="col-sm-12 col-md-12 col-lg-12" align="center">

		<video id="paymentVideo" width="400" height="300" controls loop>
			<source	src="" type="video/mp4" id="payment">
		</video>
	</div>
		</div>
		
		
		<script>
window.onload = payment();
function payment() { 
   var videos=document.getElementById('paymentVideo');
   var payment = '${model.purchaseOrderForm.typeOfTrans}';
   var eventVideos = document.getElementById('payment');  
   var video;
   if(payment == 'transfer') {
   	video = 'payment_mnyTrans';
   }else {
	   video = 'payment_cheque'; 
   }
   
      $(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/"+video+".mp4");
      videos.load();
      videos.play();      
   
}
</script>
