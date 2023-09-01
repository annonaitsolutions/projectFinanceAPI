<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
				<div class="col-sm-12 col-md-12 col-lg-12">
					
		<div class="calendar-container">
			<div class="calendar">
				<header>
					<h2 class="month"></h2>
					<a class="btn-prev fontawesome-angle-left" href="#"></a>
					<a class="btn-next fontawesome-angle-right" href="#"></a>
				</header>
				<table>
					<thead class="event-days">
						<tr></tr>
					</thead>
					<tbody class="event-calendar">
						<tr class="1"></tr>
						<tr class="2"></tr>
						<tr class="3"></tr>
						<tr class="4"></tr>
						<tr class="5"></tr>
					</tbody>
				</table>
				<div class="list">
					<div class="day-event" date-month="3" date-day="4">
						<a href="#" class="close fontawesome-remove"></a>
						<h2 class="title">Lorem ipsum 1</h2>
						<p class="date">2014-12-16</p>
					</div>
					<div class="day-event" date-month="3" date-day="13" event-class="green-event">
						<a href="#" class="close fontawesome-remove"></a>
						<h2 class="title">Lorem ipsum 2</h2>
						<p class="date">2014-12-16</p>
					</div>
					<div class="day-event" date-month="3" date-day="14" event-class="blue-event">
						<a href="#" class="close fontawesome-remove"></a>
						<h2 class="title">Lorem ipsum 3</h2>
						<p class="date">2014-12-16</p>
					</div>
					<div class="day-event" date-month="3" date-day="16">
						<a href="#" class="close fontawesome-remove"></a>
						<h2 class="title">Lorem ipsum 4</h2>
						<p class="date">2014-12-16</p>
					</div>
					<div class="day-event" date-month="3" date-day="24">
						<a href="#" class="close fontawesome-remove"></a>
						<h2 class="title">Lorem ipsum 5</h2>
						<p class="date">2014-12-16</p>
					</div>
					<div class="day-event" date-month="3" date-day="31">
						<a href="#" class="close fontawesome-remove"></a>
						<h2 class="title">Lorem ipsum 6</h2>
						
					</div>
				</div>
			</div>
		</div>
	
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