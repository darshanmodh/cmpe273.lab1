$(document).ready(function() {
	
	getInventory();
	
	$(document.body).on('click', ':button, .DELETE_BTN', function(e) {
		//console.log(this);
		var $this = $(this)
			, book_name = $this.val()
			, obj = {book_name : book_name}
			, $tr = $this.closest('tr')
			, isbn = $tr.find('.CL_isbn').text();
		
		deleteInventory(obj, book_name, isbn);
	});
});

function deleteInventory(obj, book_name, isbn) {
	
	ajaxObj = {  
			type: "DELETE",
			url: url+"/cmpe273.lab1/rest/delete/" + book_name + "/" + isbn,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				//console.log(data);
				$('#delete_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				getInventory();
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function getInventory() {
	
	var d = new Date()
		, n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: url+"/cmpe273.lab1/rest/catalog", 
			data: "ts="+n,
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				//console.log(data);
				var html_string = "";
				
				$.each(data, function(index1, val1) {
					//console.log(val1);
					html_string = html_string + templateGetInventory(val1);
				});
				
				$('#get_book').html("<table border='1'>" + html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function templateGetInventory(param) {
	return '<tr>' +
				'<td class="CL_book_name">' + param.book_name + '</td>' +
				'<td class="CL_author_name">' + param.author_name + '</td>' +
				'<td class="CL_category">' + param.category + '</td>' +
				'<td class="CL_price">' + param.price + '</td>' +
				'<td class="CL_isbn">' + param.isbn + '</td>' +
				'<td class="CL_PC_PARTS_BTN"> <button class="DELETE_BTN" value="'+param.book_name +'" type="button">Delete</button> </td>' +
			'</tr>';
}