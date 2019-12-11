$(function(){
	$('#connect').click(function(){
		var channel = {
				"user1": $('#myname').val(),
				"user2": $('#hisname').val()
			};
		$.ajax({
			type: 'POST',
			headers: { 
        		"Accept" : "application/json; charset=utf-8",
        		"Content-Type": "application/json; charset=utf-8"
    		},
			url: 'http://localhost:8080/channel',	
			data: JSON.stringify(channel),
			success: function(data){
				console.log(data.id);
			},
			error: function(e){
				console.log('Error '+ e)
			}
		})
	})

	var stompClient = null;
	
	var socket = new SockJS('http://localhost:8080/chat');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame){
		console.log('Connected: ' + frame);
		stompClient.subscribe('/user/topic/message', function(message){
			console.log("Received "+message)
			$('#chat').append('<p>'+message.body+'</p>');
		})
	},
	function(error){
		console.log("Disconnected");
	})

	$("#send").click(function(e){
		stompClient.send('/app/chat.private.'+$("#sendTo").val(), {}, $('#message').val());
	})

})