// ==UserScript==
// @name       Html Temporal tagger
// @namespace  http://codeminders.com/
// @version    0.1
// @description  Tag annotates all pages with temporal information
// @match      *://*/*
// @require    http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js
// @require    https://raw.githubusercontent.com/zurb/foundation/master/js/foundation/foundation.js
// @require    https://raw.githubusercontent.com/zurb/foundation/master/js/foundation/foundation.tooltip.js
// @copyright  2014+
// ==/UserScript==

// var TEMPORAL_EXTRACTION_SERVICE_URL = "http://ec2-54-81-15-231.compute-1.amazonaws.com:8080/timeextractor-0.0.2/api/annotate"

// Constants
var TEMPORAL_EXTRACTION_SERVICE_URL = "http://localhost:8080/timeextractor/api/annotate"
var METHOD_POST = "POST";
var CONTENT_TYPE = "application/json"
var DATA_TYPE = 'json'
$(document).ready(function() {
    // added styles for loader and highlight   
    addGlobalStyle('.loader {   position: fixed;        left: 0px;      top: 0px;       width: 100%;    height: 100%;   z-index: 9999;  background: url(http://www.ooyyo.bg/images/loading.gif) 50% 50% no-repeat rgb(249,249,249) }');
    addGlobalStyle('.highlight { background-color: yellow  }');
    // added 'Annotate' button   
    createAnnotateButton();
});
 // function that finds temporal expressions and highlights annotated text
 
function annotate()
{ 
    var html = $("html").html();
    $('body').prepend('<div class="loader"></div>');
     // wait until text is cleaned
     var json_to_get_temporal = [{ 'id': '1', 'html': html, date:"2014-07-27"}];
     $.when(temporalData(json_to_get_temporal)).then(function(data, textStatus, jqXHR){
                                highlight(html, data);
               // return to the top of page
               window.scroll(0,0);
               // remove loader
               $(".loader").fadeOut("slow");
        });     
 }
 // get temporal data from text service
 
 var temporalData = function(json){
                return  $.ajax({
                type: METHOD_POST,
                url: TEMPORAL_EXTRACTION_SERVICE_URL,
                data: JSON.stringify(json),
                crossDomain: true,
                contentType: CONTENT_TYPE,
                dataType: DATA_TYPE,
      }); 
}
 
 // function to highlight text on html page from position
 
 var highlight = function(html, data){
     //iterate through object
     var tags = []; 
     var selected = [];
     for (var property in data) {
         current_tag = data[property];
         if (current_tag.length==1){
            var base_tag =  $(html.substring(current_tag[0].htmlTagFrom,current_tag[0].htmlTagTo));
                var text = $.trim($(base_tag).text());
            var tag = $(current_tag[0].tag).filter(function(){ return $(this).text().toLowerCase() === $(base_tag).text().toLowerCase();})
            var result = {
             'tag':tag,
             'from': current_tag[0].from,
             'to': current_tag[0].to,
              'extractedTemporal':  current_tag[0].extractedTemporal
            };
            tags.push(result);
           }
         else{
            var count=0;
            var currentText='';
            var newText = '';
            var base_tag = $(html.substring(current_tag[0].htmlTagFrom,current_tag[0].htmlTagTo+count));
            var text = $.trim($(base_tag).text());
            var baseText = $.trim($(base_tag).text());
           
             // check if tag has childer
             
             if($(base_tag).children().length<2){
       
            for(var i=0; i<current_tag.length;i++){
                  var from = current_tag[i].from+count;
                  var to = current_tag[i].to+count;
                   var newText = baseText.substring(0, from)+"<span data-tooltip aria-haspopup=\"true\" class=\"has-tip highlight\" title=\""+ JSON.stringify(current_tag[i].extractedTemporal).replace(/"/g, '\'')+ "\">"+baseText.substring(from,to)+"</span>"+baseText.substring(to,baseText.length);
                                  baseText = newText;   
                  count=count+("<span data-tooltip aria-haspopup=\"true\" class=\"has-tip highlight\" title=\""+ JSON.stringify(current_tag[i].extractedTemporal).replace(/"/g, '\'')+ "\">").length+"</span>".length;
            }
             
            var tag = $(current_tag[0].tag).filter(function(){ return $(this).text().toLowerCase().trim() === text.toLowerCase().trim();})
            $(tag).text('');
                        $(tag).append(newText);
             }
           }
     }
     
     // only one tag with text
          for(var j =0; j<tags.length; j++){
         
       // if the same text with same content 
         
           if($(tags[j].tag).length>1 ){
                var tagss = $(tags[j].tag);
          for(var k =0; k<tagss.length;k++){
           var text = $(tagss[k]).text().trim();
           var newText = text.substring(0, tags[j].from)+"<span data-tooltip aria-haspopup=\"true\" class=\"has-tip highlight\" title=\""+ JSON.stringify(tags[j].extractedTemporal).replace(/"/g, '\'')+ "\">"+text.substring(tags[j].from,tags[j].to)+"</span>"+text.substring(tags[j].to,text.length);
           $(tagss[k]).text('');
               $(tagss[k]).append(newText);
            }
         }
          else{
          var text = $(tags[j].tag).text().trim();
          var newText = text.substring(0, tags[j].from)+"<span data-tooltip aria-haspopup=\"true\" class=\"has-tip highlight\" title=\""+ JSON.stringify(tags[j].extractedTemporal).replace(/"/g, '\'')+ "\">"+text.substring(tags[j].from,tags[j].to)+"</span>"+text.substring(tags[j].to,text.length);
                   $(tags[j].tag).text('');
           $(tags[j].tag).append(newText);
         }
         
     }
 }
       
var createAnnotateButton = function(){
    var input=document.createElement("input");
        input.type="button";
        input.value="Annotate"; 
        input.onclick = annotate;
    input.setAttribute('style','width:150px;height:80px; position: fixed;       top: 150px; right:20px; z-index:999; padding: .3em .6em; box-shadow: 0 2px 1px rgba(0,0,0,0.3),0 1px 0 rgba(255,255,255,0.4) inset; background-color: #333; color: #fff;border: 1px solid #000 !important;border-radius: 3px; text-decoration: none ');
        $('body').prepend(input);
}
// add custom css style to page
function addGlobalStyle(css) {
    var head, style;
    head = document.getElementsByTagName('head')[0];
    if (!head) { return; }
    style = document.createElement('style');
    style.type = 'text/css';
    style.innerHTML = css;
    head.appendChild(style);
}