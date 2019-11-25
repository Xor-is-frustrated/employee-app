
function convert1($inputs)
{
	var unindexed_array = $inputs.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });
    var data = JSON.stringify( indexed_array );
    // console.log(data);
    return data;
}

function employee(event)
{
		// alert("fne");
		var $inputs = $('#employee-form');
		// console.log($inputs);
		var json = convert1($inputs);
		// console.log(json);
		// alert("ok");
		$.ajax({
			url:"./api",
			type:"POST",
			data:json,
			success:function(response){
				// alert("employee created");
				display_employee_list();
			},
			error:function(){
				alert("error in creating employee");
			}

		});

		return false;
} 

function delete1(id)
{
		$.ajax({
			url:'./api?id='+id,
			type:'DELETE',
			success:function(response){
				// alert("employee created");
				console.log('Deleted');
				display_employee_list();
			},
			error:function(){
				// alert("error in creating employee");
				console.log('error in Deletion');
			}	
		});
}

function table(response)
{
		var tbody=$('#employee-table').find('tbody');
		tbody.empty();
		for(var i in response)
		{
				var row=response[i];
				var text='<tr><td>'+row.id+'</td><td>'+row.name+'</td><td>'+row.age+'</td><td>'+'<button onClick="delete1(\'' + row.id + '\')">Delete</button>'+'</td></tr>';
				tbody.append(text);

		}
}

function display_employee_list(event)
{
		$.ajax({
			url:"./api",
			type:"GET",
			
			success:function(response){
				// alert("fine");
				console.log(response);
				// alert("oookk");
				table(response);

			},
			error:function(){
				alert("no broo");
			}

		});
}


function init()
{
		// alert("fine");
		display_employee_list();
		$("#employee-form").click(employee);
		$("#view-employee-list").click(display_employee_list);
}


$(document).ready(init);