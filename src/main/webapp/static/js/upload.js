window.URL = window.URL || window.webkitURL;

var model=document.getElementsByClassName('model-upload-p')[0];
var closeBtn=document.getElementsByClassName('closeBtnPost')[0];
var closeBtn2=document.getElementsByClassName('closeBtnPost')[1];
var p=document.getElementsByClassName("upload-post")[0];
var divUpload=document.getElementsByClassName("upload-model")[0];
var imgUpload=document.getElementsByClassName("upload-img")[0];
var videoUpload=document.getElementsByClassName("upload-video")[0];
imgUpload.addEventListener("click",postUploadImage);
videoUpload.addEventListener("click",postUploadVideo);
closeBtn.addEventListener("click",closeModelPost);
closeBtn2.addEventListener("click",closeModelPost);
window.addEventListener('click',clickOutsidePost);

function openModelPost(){
    model.style.display ='block';
    p.style.display="block";
    divUpload.style.display="none";
}

function closeModelPost(){
   model.style.display ='none';
}

function clickOutsidePost(e){
    if(e.target==model){
    model.style.display ='none';
    }
 }


function postUploadVideo(e){
	postUpload(true);
}

function postUploadImage(e){
	postUpload(false);
}
function postUpload(isVideo){
	if(isVideo){
		document.getElementsByName("file")[0].setAttribute("accept" ,"video/*")
		document.getElementById("typeRadio").value = "video";
	}else{
		document.getElementsByName("file")[0].setAttribute("accept" ,"image/*")
		document.getElementById("typeRadio").value = "image";
	}
    p.style.display="none";
    divUpload.style.display="block";
}

document.getElementById('ajax-upload').addEventListener("submit", function(e){
    e.preventDefault()
    var form = e.target;
    var data = new FormData(form);
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
    	if (request.readyState == 4 && request.status == 200){
    		model.style.display ='none';
    		alert("Upload Successful!");       		 
    	}
    	 
   }      
    request.open(form.method, form.action);
    request.send(data);
})


function handleFile(files) {      
      var img = document.getElementById("currentPhoto");
      img.src = window.URL.createObjectURL(files[0]);
      img.height = 60;
      img.onload = function() {
        window.URL.revokeObjectURL(this.src);
      }
  }