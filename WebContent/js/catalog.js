$.ajax({
	url: 'http://localhost:8080/Controller',
	method: 'get',
	data: createDataForRequest(),
	success: function(response){
		console.log("send");
		
    },
    error: function(result){
    	console.log("error");
    }
});

function createDataForRequest(){
	console.log("here");
	var config = {}; //это объект
	config["command"] = "TAKE_ALL_BOOKS";
}