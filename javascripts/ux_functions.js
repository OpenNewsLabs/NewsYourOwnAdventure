/**
 * UX Functions for NEWSQUEST
 * Erhardt Graeff
 * 6/17/2012
 */

// UX events after user chooses a Question 
function nextChapter() {
	// Disable old Question buttons
	$(this).unbind();
	$(this).parents('.question').next().children().unbind();
	
	// Hide Share + Stats links
		
	// Add margin to current Chapter
	$(this).parents('.container').css('margin-bottom','15%');
	
	// Reveal next Chapter
	$(this).parents('.container').next().css('visibility','visible');
	
	// Dim previous Chapter
	$(this).parents('.container').animate({ 
		opacity: 0.4,
	}, 'fast');
	
	// Queue next next Chapter (visibility: hidden) and bind event handler
	grabChapter(this);
	
	// Scroll whole story up to center next Chapter
	$.scrollTo($(this).siblings('.options'));
}

// Grab the next chapter from the server
function grabChapter(currentQuestion) {
	$('body').append('<div class="container">NEW DATA</div>');	

	// Bind event handler
	$(currentQuestion).parents('.container').next().children('.question').click(nextChapter);
}

$(document).ready(function() {	
	$('.question').click(nextChapter);
	
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