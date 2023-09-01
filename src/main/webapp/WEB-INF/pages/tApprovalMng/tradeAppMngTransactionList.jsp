<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.transactionDetails"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<font color="red">${success } </font>
			</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		

				<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.transactionId"/></th>
						<th><spring:message code="label.transactionType"/></th>
						<th><spring:message code="label.transactionStatus"/></th>
						
					</tr>
				</thead>
				    <tbody class="searchable">
					<c:if test="${! empty transactionPageList}">

						<c:forEach items="${transactionPageList}" var="transaction">
							<tr>
								<td><c:out value="${transaction.id}"></c:out></td>
								<td><c:out value="${transaction.transactionId}"></c:out></td>
								<td><c:out value="${transaction.transactionType}"></c:out></td>
								<td><c:out value="${transaction.transactionStatus}"></c:out></td>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
            </table>
         </div>
        </div>
 <style>


.bankemp_footer.col-sm-12 {
    margin-top: 52px;
}
</style>
<script>
		$(function() {
			$("#example").simplePagination({
				previousButtonClass: "btn btn-primary",
				nextButtonClass: "btn btn-primary"
			});


		});
	</script>
<script>
        $(document).ready(function(){
            $('.data').after('<div class="nav2"></div>');
            var rowsShown = 6;
            var rowsTotal = $('.data tbody tr').length;
            var numPages = rowsTotal/rowsShown;
            for(i = 0;i < numPages;i++) {
                var pageNum = i + 1;
                $('.nav2').append('<a href="#" rel="'+i+'">'+pageNum+'</a> ');
            }
            $('.data tbody tr').hide();
            $('.data tbody tr').slice(0, rowsShown).show();
            $('.nav2 a:first').addClass('active');
            $('.nav2 a').bind('click', function(){
 
                $('.nav2 a').removeClass('active');
                $(this).addClass('active');
                var currPage = $(this).attr('rel');
                var startItem = currPage * rowsShown;
                var endItem = startItem + rowsShown;
                $('.data tbody tr').css('opacity','0.0').hide().slice(startItem, endItem).
                        css('display','table-row').animate({opacity:1}, 300);
            });
        });
  </script>
  <script>
$(document).ready(function () {

    (function ($) {

        $('#filter').keyup(function () {

            var rex = new RegExp($(this).val(), 'i');
            $('.searchable tr').hide();
            $('.searchable tr').filter(function () {
                return rex.test($(this).text());
            }).show();

        })

    }(jQuery));

});
</script>
  