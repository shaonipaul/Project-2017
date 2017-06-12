var webSocket;
$(document).ready(function() {
var id = document.getElementById("uid");
		var echoText = document.getElementById("echoText");
		//echoText.value = "";
		webSocket = new WebSocket("ws://localhost:8080/Master/websocketendpoint/"+id.value);
		var message = document.getElementById("message");
		webSocket.onopen = function(message){ wsOpen(message);};
		webSocket.onmessage = function(message){ wsGetMessage(message);};
		webSocket.onclose = function(message){ wsClose(message);};
		webSocket.onerror = function(message){ wsError(message);};
		
		setTimeout(status, 5000);
		
});
		
		
		function wsOpenConnection(){
			webSocket = new WebSocket("ws://localhost:8080/Master/websocketendpoint/22");
		}
		function wsOpen(message){
			//echoText.value += "Connected ... \n";
		}
		function wsSendMessage(node, tid, tname){
			
			var val = node.parentElement.parentElement.firstElementChild;
			var val1 = node.parentElement.parentElement.previousElementSibling;
			var uid = document.getElementById("uid");
			var uname = document.getElementById("uname");
			
			var msg = '{"toId":"'+tid+'", "toName":"'+tname+'","fromId":"'+uid.value+'", "fromName":"'+uname.value+'", "message":"'+val.value+'"}';
			//console.log(msg);
			webSocket.send(msg);
			console.log(val1);
			val1.innerHTML+="<p><strong>"+uname.value+":&nbsp;</strong>"+val.value+"</p>";
			val.value = "";
			//echoText.value += "Message sended to the server : " + message.value + "\n";
			//message.value = "";
		}
		function wsCloseConnection(){
			webSocket.close();
		}
		function wsGetMessage(message){
			var c =  $.parseJSON(message.data);
			register_popup(c.fromId, c.fromName);
			var e = document.getElementById(c.fromId);
			var val1 = e.firstElementChild.nextElementSibling;
			val1.innerHTML+="<p><strong>"+c.fromName+":&nbsp;</strong>"+c.message+"</p>";
		}
		function wsClose(message){
			//echoText.value += "Disconnect ... \n";
		}

		function wserror(message){
			//echoText.value += "Error ... \n";
		}
		
		  //this function can remove a array element.
        Array.remove = function(array, from, to) {
            var rest = array.slice((to || from) + 1 || array.length);
            array.length = from < 0 ? array.length + from : from;
            return array.push.apply(array, rest);
        };
    
        //this variable represents the total number of popups can be displayed according to the viewport width
        var total_popups = 0;
        
        //arrays of popups ids
        var popups = [];
    
        //this is used to close a popup
        function close_popup(id)
        {
            for(var iii = 0; iii < popups.length; iii++)
            {
                if(id == popups[iii])
                {
                    Array.remove(popups, iii);
                    
                    document.getElementById(id).style.display ="none";
                    
                    calculate_popups();
                    
                    return;
                }
            }   
        }
    
        //displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
        function display_popups(id)
        {
            var right = 220;
            
            var iii = 0;
            for(iii; iii < total_popups; iii++)
            {
                if(popups[iii] != undefined)
                {
                    var element = document.getElementById(popups[iii]);
                    element.style.right = right + "px";
                    right = right + 320;
                    element.style.display = "block";
                }
            }
            
            for(var jjj = iii; jjj < popups.length; jjj++)
            {
                var element = document.getElementById(popups[jjj]);
                element.style.display = "none";
            }
            save(id);
        }
        
        //creates markup for a new popup. Adds the id to popups array.
        function register_popup(id, name)
        {
            
            for(var iii = 0; iii < popups.length; iii++)
            {   
                //already registered. Bring it to front.
                if(id == popups[iii])
                {
                    Array.remove(popups, iii);
                
                    popups.unshift(id);
                    
                    calculate_popups();
                    
                    
                    return;
                }
            }               
            
            var element = '<div class="popup-box chat-popup" id="'+ id +'">';
            element = element + '<div class="popup-head">';
            element = element + '<div class="popup-head-left">'+ name +'</div>';
            element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
            
            element = element + '<div style="clear: both"></div></div><div class="popup-messages"></div>';
            element = element + '<div class="bottom"> <textarea row="1"></textarea>';
            element = element + '<div class="button"><button style="height:60%;" onclick="wsSendMessage(this,';
            element = element +	"'"+id+"' ,'"+name+"'";
            element = element + ');">Send</button></div></div></div>';

            document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element;  
    
            
            
            popups.unshift(id);
                    
            calculate_popups(id);
            
        }
        
        //calculate the total number of popups suitable and then populate the toatal_popups variable.
        function calculate_popups(id)
        {
            var width = window.innerWidth;
            if(width < 540)
            {
                total_popups = 0;
            }
            else
            {
                width = width - 200;
                //320 is width of a single popup box
                total_popups = parseInt(width/320);
            }
            
            display_popups(id);
            
        }
        
        //recalculate when window is loaded and also when window is resized.
        window.addEventListener("resize", calculate_popups);
        window.addEventListener("load", calculate_popups);
        
        
        function status() {
        	  $.ajax({
        	    url: 'http://localhost:8080/Master/OnlineStatusServlet', 
        	    success: function(data) {
//        	      $('.result').html(data);
        	    	var d = "";
        	    	
        	    	$.each(JSON.parse(data), function(idx, obj) {
        	
        	    	    d = d+ '<div class="sidebar-name">';
        	    		d = d+'<a href="javascript:register_popup(';
        	    		d = d+ "'"+obj.id+"', '"+obj.name+"');";
        	    		d = d+ '"> <img width="30" height="30" src="http://localhost:8080/Master/profile.png" />';
        	    		d = d + "<span>"+obj.name+"</span> </a> </div>"
            
        	    	});
        	    	document.getElementById("side").innerHTML = d;
        	    	console.log(data);
        	    },
        	    complete: function() {
        	      // Schedule the next request when the current one's complete
        	      setTimeout(status, 5000);
        	    }
        	  });
        	};
        	
        	
        	function save(to){
        		var from = document.getElementById("uid");
        		var e = document.getElementById(to);
                 console.log(e);
   				var val1 = e.firstElementChild.nextElementSibling;
   				var val2 = val1.innerHTML;
   				val1.innerHTML = "";
   				
        		$.ajax({
        			url: 'http://localhost:8080/Master/MessageServlet?to='+to+'&from='+from.value ,
        		 success: function(data) {
//           	      $('.result').html(data);
        			 console.log(data);
           	    	var d = JSON.parse(data);
           	    
           	    	var l = d.l; 
           	    	for(var i=0;i<l.length; i++ ) {
           	    	
           				val1.innerHTML+="<p><strong>"+l[i].fromName+":&nbsp;</strong>"+l[i].message+"</p>";
           				
               
           	    	}
           	    	
           	    	val1.innerHTML+=val2;
           	    	
           	    }
        		
        		});
        	}
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	