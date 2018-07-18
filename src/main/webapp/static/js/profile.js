
function selectPosts() {
	$('#postsBtn').addClass('selected');
	$('#votedBtn').removeClass('selected');
	$('#commentedBtn').removeClass('selected');
	$('#postsTab').removeClass('tab-hidden');
	$('#votedTab').addClass('tab-hidden');
	$('#commentedTab').addClass('tab-hidden');
}

function selectVoted() {
	$('#votedBtn').addClass('selected');
	$('#postsBtn').removeClass('selected');
	$('#commentedBtn').removeClass('selected');
	$('#votedTab').removeClass('tab-hidden');
	$('#postsTab').addClass('tab-hidden');
	$('#commentedTab').addClass('tab-hidden');
}

function selectCommented() {
	$('#commentedBtn').addClass('selected');
	$('#votedBtn').removeClass('selected');
	$('#postsBtn').removeClass('selected');
	$('#commentedTab').removeClass('tab-hidden');
	$('#votedTab').addClass('tab-hidden');
	$('#postsTab').addClass('tab-hidden');
}
