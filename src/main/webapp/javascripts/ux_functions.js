/**
 * UX Functions for NEWSQUEST
 * Erhardt Graeff
 * 6/17/2012
 */

$(document).ready(function() {	
	// Toggle on/off the Mini-Masthead based on location of scrollbar (a.k.a. location of Main-Masthead)
	$(window).scroll(function() {
		var scrollTop = $(window).scrollTop();
		if (scrollTop >= 100) {
			$('#minimast-inner').fadeIn('fast');
		} else {
			$('#minimast-inner').fadeOut('fast');
		}
	});
});	