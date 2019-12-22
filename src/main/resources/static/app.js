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
			url: '/channel',	
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
	
	var socket = new SockJS('/chat');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame){
		console.log('Connected: ' + frame);
		stompClient.subscribe('/user/topic/message', function(message){
			console.log("Received "+message)
			$('#chat').append('<p>'+message.body+'</p>');
		})

		stompClient.subscribe('/topic/chat.login', function(message){
		    var loginEvent = JSON.parse(message.body);
            $('#activeUsers')
                .append('<p id="user-'+loginEvent.username+'">'+loginEvent.username+'</p>');
		})

		stompClient.subscribe('/topic/chat.logout', function(message){
		    var loginEvent = JSON.parse(message.body);
            $("#user-"+loginEvent.username).remove();
        })
	},
	function(error){
		console.log("Disconnected");
	})

	$("#send").click(function(e){
		stompClient.send('/app/chat.private.'+$("#sendTo").val(), {}, $('#message').val());
	})

})
