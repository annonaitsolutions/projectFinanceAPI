$(document).ready(function(){
	// Write on keyup event of keyword input element
	$("#kwd_search").keyup(function(){
		// When value of the input is not blank
        var term=$(this).val()
		if( term != "")
		{
			// Show only matching TR, hide rest of them
			$("#my-table tbody>tr").hide();
            $("#my-table td").filter(function(){
                   return $(this).text().toLowerCase().indexOf(term ) >-1
            }).parent("tr").show();
		}
		else
		{
			// When there is no input or clean again, show everything back
			$("#my-table tbody>tr").show();
		}
	});
	$(".kwd_search").keyup(function(){
		// When value of the input is not blank
        var term=$(this).val()
		if( term != "")
		{
			// Show only matching TR, hide rest of them
			$(".my-table tbody>tr").hide();
            $(".my-table td").filter(function(){
                   return $(this).text().toLowerCase().indexOf(term ) >-1
            }).parent("tr").show();
		}
		else
		{
			// When there is no input or clean again, show everything back
			$(".my-table tbody>tr").show();
		}
	});
	// Pagination for table
	(function($) {

		$.fn.simplePagination = function(options) {
			
			var defaults = {
				perPage: 5,
				containerClass: '',
				previousButtonClass: '',
				nextButtonClass: '',
				previousButtonText: 'Previous',
				nextButtonText: 'Next',
				currentPage: 1
			};

			var settings = $.extend({}, defaults, options);

			return this.each(function() {
				var $rows = $('tbody tr', this);
				var pages = Math.ceil($rows.length/settings.perPage);

				var container = document.createElement('div');
				var bPrevious = document.createElement('button');
				var bNext = document.createElement('button');
				var of = document.createElement('span');

				bPrevious.innerHTML = settings.previousButtonText;
				bNext.innerHTML = settings.nextButtonText;

				container.className = settings.containerClass;
				bPrevious.className = settings.previousButtonClass;
				bNext.className = settings.nextButtonClass;

				bPrevious.style.marginRight = '8px';
				bNext.style.marginLeft = '8px';
				container.style.textAlign = "center";
				container.style.marginBottom = '20px';

				container.appendChild(bPrevious);
				container.appendChild(of);
				container.appendChild(bNext);

				$(this).after(container);

				update();

				$(bNext).click(function() {
					if (settings.currentPage + 1 > pages) {
						settings.currentPage = pages;
					} else {
						settings.currentPage++;
					}

					update();
				});

				$(bPrevious).click(function() {
					if (settings.currentPage - 1 < 1) {
						settings.currentPage = 1;
					} else {
						settings.currentPage--;
					}

					update();
				});

				function update() {
					var from = ((settings.currentPage - 1) * settings.perPage) + 1;
					var to = from + settings.perPage - 1;

					if (to > $rows.length) {
						to = $rows.length;
					}

					$rows.hide();
					$rows.slice((from-1), to).show();

					of.innerHTML = from + ' to ' + to + ' of ' + $rows.length + ' entries';

					if ($rows.length <= settings.perPage) {
						$(container).hide();
					} else {
						$(container).show();
					}
				}
			});

		}

	}(jQuery));
	$(".example").simplePagination({
		previousButtonClass: "btn btn-primary",
		nextButtonClass: "btn btn-primary"
	});

});   