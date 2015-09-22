$(document).ready(function() {
	
	getInventory();
	
});

function getInventory() {
	
	var d = new Date()
		, n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:8084/cmpe273.lab1/rest/catalog", 
			data: "ts="+n, 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				var html_string = "<table border='1'>" +
						"<tr><th>Book Name</th>" +
						"<th>Author Name</th>" +
						"<th>Category</th>" +
						"<th>Price</th>" +
						"<th>ISBN</th>" +
						"</th></tr>";
				
				$.each(data, function(index1, val1) {
					html_string = html_string + templateGetInventory(val1);
				});
				
				$('#get_books').html(html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function templateGetInventory(param) {
	return '<tr>' +
				'<td class="book_name">' + param.book_name + '</td>' +
				'<td class="author_name">' + param.author_name + '</td>' +
				'<td class="category">' + param.category + '</td>' +
				'<td class="price">' + param.price + '</td>' +
				'<td class="isbn">' + param.isbn + '</td>' +
			'</tr>';
}

