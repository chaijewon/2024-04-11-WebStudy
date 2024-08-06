   window.onload=function(){
	   $.ajax({
			type:'post',
			url:'food.do',
			success:function(result)
			{
				let json=JSON.parse(result)
				//console.log(json)
				foodPrint(json)
			}
		})
	$('#prev').click(function(){
		let no=$(this).attr("data-type")
		let cpage=$('#cpage').text()
		let curpage=parseInt(cpage)
		if(curpage>1)
		{
			curpage--
			$.ajax({
				type:'post',
				url:'food.do',
				data:{"type":no,"page":curpage},
				success:function(result)
				{
					let json=JSON.parse(result)
					foodPrint(json)
				}
			})
		}
	})
	$('#next').click(function(){
		let no=$(this).attr("data-type")
		let cpage=$('#cpage').text()
		let tpage=$('#tpage').text()
		let curpage=parseInt(cpage)
		let totalpage=parseInt(tpage)
		if(curpage<totalpage)
		{
			curpage++
			$.ajax({
				type:'post',
				url:'food.do',
				data:{"type":no,"page":curpage},
				success:function(result)
				{
					let json=JSON.parse(result)
					foodPrint(json)
				}
			})
		}
	})
	}
function change(no)
{
	$.ajax({
		type:'post',
		url:'food.do',
		data:{"type":no},
		success:function(result)
		{
			let json=JSON.parse(result)
			//console.log(json)
			foodPrint(json)
		}
	})
}
function foodPrint(json)
{
	$('#cpage').text(json[0].curpage)
	$('#tpage').text(json[0].totalpage)
	
	let html='';
	json.map((food)=>{
		html+='<div class="col-sm-3">'
	      +'<div class="thumbnail">'
	      +'<img src="'+food.poster+'" style="width: 100%" class="images" onclick="detail('+food.fno+')">'
	      +'<p class="a">'+food.name+'</p>'
	      +'</div>'
	      +'</div>'
	})
	$('#prev').attr("data-type",json[0].type)
	$('#next').attr("data-type",json[0].type)
	$('#print').html(html)
}
function detail(fno)
{
	alert("fno="+fno)
}