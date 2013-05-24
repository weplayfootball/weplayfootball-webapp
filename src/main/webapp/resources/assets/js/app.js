var App = function () {

    function handleSliders() {
		/*
        $(function () {
            $('#ei-slider').eislideshow({
                animation			: 'center',
					autoplay			: true,
					slideshow_interval	: 3000,
					titlesFactor		: 0
            });
        });
		*/

        $( '#elastislide' ).elastislide( {
				minItems : 2
			} );

		$('#clients-flexslider').flexslider({
			animation: "slide",
			easing: "swing",
			animationLoop: true,
			itemWidth: 1,
			itemMargin: 1,
			minItems: 2,
			maxItems: 9,
			controlNav: false,
			directionNav: false,
			move: 2
		});
		
		$('#photo-flexslider').flexslider({
			animation: "slide",
			controlNav: false,
			animationLoop: false,
			itemWidth: 80,
			itemMargin: 0
		});	
		
		$('#testimonal_carousel').collapse({
			toggle: false
		});
    }

    function handleFancybox() {
		jQuery(".fancybox-button").fancybox({
		groupAttr: 'data-rel',
		prevEffect: 'none',
		nextEffect: 'none',
		closeBtn: true,
		helpers: {
			title: {
				type: 'inside'
				}
			}
		});
    }

    function handleIEFixes() {
        //fix html5 placeholder attribute for ie7 & ie8
        if (jQuery.browser.msie && jQuery.browser.version.substr(0, 1) < 9) { // ie7&ie8
            jQuery('input[placeholder], textarea[placeholder]').each(function () {
                var input = jQuery(this);

                jQuery(input).val(input.attr('placeholder'));

                jQuery(input).focus(function () {
                    if (input.val() == input.attr('placeholder')) {
                        input.val('');
                    }
                });

                jQuery(input).blur(function () {
                    if (input.val() == '' || input.val() == input.attr('placeholder')) {
                        input.val(input.attr('placeholder'));
                    }
                });
            });
        }
    }

    function handleBootstrap() {
        jQuery('.carousel').carousel({
            interval: 15000,
            pause: 'hover'
        });
        jQuery('.tooltips').tooltip();
        jQuery('.popovers').popover();
    }

    function handleMisc() {
        jQuery('.top').click(function () {
            jQuery('html,body').animate({
                scrollTop: jQuery('body').offset().top
            }, 'slow');
        }); //move to top navigator
    }


    return {

        init: function () {

            handleBootstrap();
            handleIEFixes();
            handleFancybox();
            handleSliders();
            handleMisc();
        }

    };

}();