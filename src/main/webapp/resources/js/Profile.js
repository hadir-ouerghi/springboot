var size = document.getElementsByClassName('usersSize')[0].innerText;

function hello(){
	for(var i=0 ; i<size ;i++)
	if(document.getElementsByClassName('test')[i].getElementsByClassName('checking')[0].innerText =="false")
		document.getElementsByClassName('test')[i].getElementsByClassName('btn btn-secondary')[0].style.visibility = 'hidden';
	else 
		document.getElementsByClassName('test')[i].getElementsByClassName('btn btn-primary')[0].style.visibility = 'hidden';
		
	
	

}

for(var i=0 ; i<size ;i++)
	if(document.getElementsByClassName('test')[i].getElementsByClassName('checking')[0].innerText =="false")
		document.getElementsByClassName('test')[i].getElementsByClassName('btn btn-secondary')[0].style.visibility = 'hidden';
	else 
		document.getElementsByClassName('test')[i].getElementsByClassName('btn btn-primary')[0].style.visibility = 'hidden';
		
	