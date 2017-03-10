/**
 * 
 */
( function() {
	new WOW().init();
	
	var $item = $('.carousel .item'); 
	var $wHeight = $(window).height();
	$item.eq(0).addClass('active');
	$item.height($wHeight); 
	$item.addClass('full-screen');


	$(window).on('resize', function (){
	  $wHeight = $(window).height();
	  $item.height($wHeight);
	});

	$('.carousel').carousel({
	  interval: 6000,
	  pause: "false"
	});
	
	
} );