
$(document).ready(function() {
	
    $("#requestForm").submit(function(e){
           e.preventDefault();
    });
    
    $("#myButton").click(function(e){
            dataString = $("#myAjaxRequestForm").find('input, select, textarea, button').serialize();
            var text = $("textarea#text").val(); 
            dataString = "Text =" + text;
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/timeextractor/api/getAllAnnotationsForPlainText",
                data: text,
                contentType: "text/plain",
                success: function(data, textStatus, jqXHR) {
                    $("#ajaxResponse").html("");
                    text1 = text;
                    var index = (text.length);
                    var k = 0;
                    for (var obj in data) {
                    	var fr = data[obj].from+k; 
                    	var to = data[obj].to+k;
                    	if(fr==0){
                    		text1 ="<b>"+text1.substring(fr,to)+"</b>"+text1.substring(to,index);
                    	}
                    	else{
                    	text1 = text1.substring(0, fr)+"<b>"+text1.substring(fr, to)+"</b>"+text1.substring(to,index);   
                    	}
                    	k=k+7;
                    	index=index+k;
                    }     
                    $("#ajaxResponse").append(text1);             
                 },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log("Something really bad happened " + textStatus);
                      $("#ajaxResponse").html(jqXHR.responseText);
                },
                
                beforeSend: function(jqXHR, settings){
                    settings.data += "&dummyData=whatever";
                    $('#myButton').attr("disabled", true);
                },
                
                complete: function(jqXHR, textStatus){
                    $('#myButton').attr("disabled", false);
                }
      });        
    });
 
});