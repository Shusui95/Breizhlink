( function( $ ) {
$( document ).ready(function() {
//$(".hidden").css('display', 'none');
	$('#table').DataTable( {
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.15/i18n/French.json"
        }
    });
	////cdn.datatables.net/plug-ins/1.10.15/i18n/French.json
	
	$('#table_length select').on('change', function(){
		$('#background').height($('#content').height());
	});
	$('#table tr td:nth-child(2)').css('overflow-x', 'scroll');
	$('#table tr td').css('max-width', '350px');
var url      = window.location.href;
var navActiv = url.split('/');
console.log("navActiv "+navActiv);
navActiv = navActiv[4];
console.log("navActiv3 "+navActiv);
console.log("navActiv3 "+(navActiv == 'presentation'));
$('#cssmenu ul li').removeClass('active');
if(navActiv == ''){
	$('.accueil').addClass('active');
}
if(navActiv == 'presentation'){
	$('.presentation').addClass('active');
}
if(navActiv == 'creer-compte'){
	$('.creer-compte').addClass('active');
}
if(navActiv == 'raccourcir'){
	$('.raccourcir').addClass('active');
}
if(navActiv == 'compte'){
	$('.compte').addClass('active');
}
if(navActiv == 'connexion'){
	$('.connexion').addClass('active');
}

// Display or not input when checkbox is check
var checkboxPassword= null;
$('#background').height($('#content').height());
$('#passwordInput').on('click', function(){
	checkboxPassword = $('#passwordInput:checked').length;
	
	if(checkboxPassword > 0){
		$('.inputPass').removeClass('hidden');
	}else{
		$('.inputPass').addClass('hidden');
	}
	$('#background').height($('#content').height());
});

$('#checkboxPassword').on('click', function(){
	checkboxPassword = $('#checkboxPassword:checked').length;
	
	if(checkboxPassword > 0){
		$('#password').removeClass('hidden');
	}else{
		$('#password').addClass('hidden');
	}
	$('#background').height($('#content').height());
});

$('#radioClics').on('click', function(){
	checkboxPassword = $('#radioClics:checked').length;
	
	if(checkboxPassword > 0){
		$('#numberInput').removeClass('hidden');
	}else{
		$('#numberInput').addClass('hidden');
	}
	$('#background').height($('#content').height());
});





$('#cssmenu').prepend('<div id="menu-button">Menu</div>');
	$('#cssmenu #menu-button').on('click', function(){
		var menu = $(this).next('ul');
		if (menu.hasClass('open')) {
			menu.removeClass('open');
		}
		else {
			menu.addClass('open');
		}
	});
});

} )( jQuery );
