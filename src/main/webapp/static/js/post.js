
var m=document.getElementById('simpleModel');
var commentsDiv=document.getElementsByClassName('model-comments')[0];
window.addEventListener('click',clickOutside);

function ajax_get_p(url, callback) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            try {
                var data = JSON.parse(xmlhttp.responseText);
            } catch(err) {
                console.log(err.message + " in " + xmlhttp.responseText);
                return;
            }
            callback(data);
        }
    };
 
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function ajax_vote(url,postId) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
           
        }
    };
    var form = new FormData();
    form.append("id", postId);
    xmlhttp.open("POST", url, true);
    xmlhttp.send(form);
}


function upvotePost(postId){
	ajax_vote('/9gag.com/upvote',postId);
	ajax_get_p('/9gag.com/votes/'+postId, function(resultPoints) {
		document.getElementById(postId).textContent=resultPoints;
	});
}

function downvotePost(postId){
	ajax_vote('/9gag.com/downvote',postId);
	ajax_get_p('/9gag.com/votes/'+postId, function(resultPoints) {
		document.getElementById(postId).textContent=resultPoints;
	});
}

function upvoteComment(commentId){
	ajax_vote('/9gag.com/comment/upvote',commentId);
	countPointsComment(commentId);
}

function downvoteComment(commentId){
	ajax_vote('/9gag.com/comment/downvote',commentId);
	countPointsComment(commentId);
}

function openModel(postId){
	ajax_get_p('/9gag.com/post/'+postId, function(post) {
        
        var modelComments=document.getElementsByClassName("model-comments")[0];
        modelComments.innerHTML='';
        for(var i=0;i<post.comments.length;i++){
            modelComments.innerHTML+='<div>'
                             +'<div onclick="openProfile('+post.comments[i].owner.id+')">'+post.comments[i].owner.username+'</div>'                 
//                    		 +'<button onclick="deleteComment('+post.comments[i].id+')" value="delete"></button>'
                             +'<div>'+post.comments[i].content+'</div>'
                             +'<button class="L mainButton" width="2%"  onclick="upvoteComment('+post.comments[i].id+')">'
                             +'<span class="glyphicon glyphicon-thumbs-up"></span> </button>'
                             +'<button class="D mainButton" onclick="downvoteComment('+post.comments[i].id+')">'
                             +'<span class="glyphicon glyphicon-thumbs-down"></span> </button>'
                             +'<div name="'+post.comments[i].id+'"onclick="countPointsComment('+post.comments[i].id+')"> click to see points </div>'
                             +'</div>';
                          
        }
        modelComments.innerHTML+='<form method="post" action="add/comment">'
                            +'<input type="hidden" name="postId" value="'+postId+'">'
                            +'<textarea name="content" cols="41" rows="5" style="resize: none;"></textarea>'
                            +'<input type="submit" value="add comment" onclick="addComment(this)">'
                            +'</form>';
                              

		var modelFooter=document.getElementsByClassName("model-footer")[0];
		modelFooter.innerHTML='';
        modelFooter.innerHTML+='<button class="L mainButton" onclick="upvotePost('+postId+')">'
                             +'<span class="glyphicon glyphicon-thumbs-up"></span> </button>'
                             +'<button class="D mainButton" onclick="downvotePost('+postId+')">'
                             +'<span class="glyphicon glyphicon-thumbs-down"></span> </button>'
                             +'<button class="C mainButton" onclick="openModel(${post.id})">'
                             +'<span class="glyphicon glyphicon-pencil"></span> </button>';
                          	                
                    	          
		 var title=document.getElementById("title");
		 title.innerHTML='';
		 title.textContent=post.title;
		 title.innerHTML+='<button onclick="deletePost('+postId+')" value="delete"></button>';
	     var pic=document.getElementById("pic");
	     pic.setAttribute("src","img/"+post.imageURL);
	     m.style.display="block";     
	});
 }

function openProfile(userId){

}
function addComment(e){
    e.preventDefault()
    var form = e;
    var data = new FormData(form);
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
    	if (request.readyState == 4 && request.status == 200){
    		window.location.href="/";
    	 } 	 
   }      
    request.open(form.method, form.action);
    request.send(data);
}

function countPoints(postId){
	ajax_get_p('/9gag.com/votes/'+postId, function(resultPoints) {
        result=resultPoints;
        document.getElementById(postId).textContent=resultPoints;
	});
}

function countPointsComment(commentId){
    ajax_get_p('/9gag.com/comment/votes/'+commentId, function(resultPoints) {
        result=resultPoints;
        document.getElementsByName(commentId)[0].textContent=resultPoints;
	});
}

function closeModel(){
   m.style.display ='none';
}

function clickOutside(e){
    if(e.target==m){
    m.style.display ='none';
    }
 }

function deletePost(postId){
    ajax_vote('/9gag.com/post/delete',postId);
 }


 function deleteComment(commentId){
    ajax_vote('/9gag.com/comment/delete',commentId);
 }
