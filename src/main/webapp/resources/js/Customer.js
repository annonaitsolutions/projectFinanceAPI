	var i=0;
	var scriptArray = ["https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"];
	function dJSol() {
		d(i);
	}
	function d(i) {
		var element = document.createElement("script");
		element.src = scriptArray[i];
		document.body.appendChild(element);
		element.onload = function() {
			i++;
			if(i<scriptArray.length) {
				d(i);
			}
		}
	}
	 if (window.addEventListener)
	 window.addEventListener("load", dJSol, false);
	 else if (window.attachEvent)
	 window.attachEvent("onload", dJSol);
	 else window.onload = dJSol;
	 
	   var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
