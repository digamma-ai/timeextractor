$(document).ready()
{
	$('#post_api').hide();
	$('#get_api').hide();

}

$('#api_type').on('change', function() {
	var option = $(this).find('option:selected').val();
	if (option == 'post') {
		$('#get_api').hide();
		$('#post_api').show();

	} else if (option == 'get') {
		$('#post_api').hide();
		$('#get_api').show();

	} else {
		$('#post_api').hide();
		$('#get_api').hide();

	}
});