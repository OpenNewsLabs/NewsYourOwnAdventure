/**
 * UX Functions for NEWSQUEST
 * Erhardt Graeff
 * 6/17/2012
 */

// UX events after user chooses a Question 
function nextChapter() {
	// Disable old Question buttons
	$(this).unbind();
	
	// Hide Share + Stats links
		
	// Add margin to current Chapter
	$(this).parent('.chapters').css('margin-bottom','25%');
	
	// Reveal next Chapter
	$(this).parent('.chapters').next().css('visibility','visible');
	
	// Dim previous Chapter
	$(this).parent('.chapters').animate({ 
		opacity: 0.3,
	}, 'fast');
	
	// Queue next next Chapter (visibility: hidden)
	$('.container').append('<div class="chapters" style="visibility:hidden;"><div class="paragraphs">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.</div><div class="questions">QUESTIONS</div></div>');
	
	$(this).parent('.chapters').next().children('.questions').click(nextChapter);
	
	// Scroll whole story up to center next Chapter
	//$(this).parents('.container').animate({
	//	top: '-='+($(this).parent('.chapters').next().offset().top-$(this).parent('.chapters').offset().top)
	//}, 'fast');
	
	$.scrollTo($(this));
}

$(document).ready(function() {	
	$('.questions').first().click(nextChapter);
	
	// Toggle on/off the Mini-Masthead based on location of scrollbar (a.k.a. location of Main-Masthead)
	$(window).scroll(function() {
		var scrollTop = $(window).scrollTop();
		if (scrollTop >= 0) {
			$('#mini-masthead').fadeIn('fast');
		} else {
			$('#mini-masthead').fadeOut('fast');
		}
	});
});	