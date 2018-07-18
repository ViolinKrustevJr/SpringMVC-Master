var input = document.getElementById("search");
var  autocomplete_results=document.getElementById("autocomplete_results");
var results;

function ajax_get(url, callback) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            try {
                var data = JSON.parse(xmlhttp.responseText);
                console.log(xmlhttp.responseText);
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

input.addEventListener('keyup', function(e) {
  input_val = this.value; 
  ajax_get('/9gag.com/tags/'+input_val,function(tags){

  if (input_val.length > 0) {

    autocomplete_results = document.getElementById("autocomplete-results");
    autocomplete_results.innerHTML = '';
    
    for (i = 0; i < tags.length; i++) {
      autocomplete_results.innerHTML +='<div class="form-control"><a href="tags/search/'+tags[i].id+'">'+ tags[i].name +'</a></div>';
    }
  } else {
    tags_to_show = [];
    autocomplete_results.innerHTML = '';
  }
 })
})