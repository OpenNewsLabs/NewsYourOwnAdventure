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
	$(this).parent('.chapter').css('margin-bottom','25%');
	
	// Reveal next Chapter
	$(this).parent('.chapter').next().css('visibility','visible');
	
	// Dim previous Chapter
	$(this).parent('.chapter').animate({ 
		opacity: 0.3,
	}, 'fast');
	
	// Queue next next Chapter (visibility: hidden)
	grabChapter();
	
	$(this).parent('.chapter').next().children('.question').click(nextChapter);
	
	// Scroll whole story up to center next Chapter
	//$(this).parents('.container').animate({
	//	top: '-='+($(this).parent('.chapters').next().offset().top-$(this).parent('.chapters').offset().top)
	//}, 'fast');
	
	$.scrollTo($(this));
}

function grabChapter() {
	$('.container').append('	<div class="chapter">
			<div class="nine columns offset-by-three">
				<p>United Nations monitors in Syria have suspended operations because of the increasing violence over the last 10 days by President Bashar al-Assad's forces and rebels seeking his overthrow, the head of the observer mission said on Saturday.</p>
			
				<div class="question">
					<a href="#">Who is the head of the observer mission?</a>
				</div><!-- question -->
			
				<div class="question">
					<a href="#">Where did the violence begin?</a>
				</div><!-- question -->			
			
				<div class="options">
					<hr/>
					<div class="right">
						<a href="#">View Stats</a>
					</div>
					<div class="options left">
						<a href="#">Share</a>
					</div>
				</div><!-- options -->
			</div><!-- nine columns -->
		</div><!-- chapter -->');	
}

$(document).ready(function() {	
	$('.question').click(nextChapter);
	
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