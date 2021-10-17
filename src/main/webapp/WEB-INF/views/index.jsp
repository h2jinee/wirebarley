<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="col-md-12">
    <h1>환율 계산</h1>
    
    <div class="col-md-4">
    	<form>
		    <div class="form-group">
		    	<div>
				    <span>송금국가: 미국(USD)</span>
		    	</div>
		    	<div>
				    <span>수취국가: </span>
				    <select id="country">
				    	<option value="USDKRW">한국(KRW)</option>
				    	<option value="USDJPY">일본(JPY)</option>
				    	<option value="USDPHP">필리핀(PHP)</option>
				    </select>
		    	</div>
		    	<div>
				    <span>환율: </span>
				    <span id="exchangeRate"></span>
				    <span id="quotes"></span>
		    	</div>
		    	<div>
				    <span>송금액: </span>
				    <input type="text" id="price" value="100">
				    <span>USD</span>
		    	</div>
		    	<div>
			    	<button type="button" id="btn">Submit</button>
		    	</div>
		    </div>
		 </form>
		 
	 </div>
	 <div>
	 	<div id="total"></div>
	 </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript"> 
	
	var rate = 0;
	
   // 들어갈 코드 내용들
   $(function() {
		
	   document.getElementById("btn").onclick=submit;
	   exchangeRate();
	   
	   $('#country').on('change', function() {
		   exchangeRate();
	   });
	   
   });
   
   function submit(){
	   
	   //var exchangeRate = parseFloat($("#exchangeRate").text().replace(/[^\d]+/g, ""));
	   var price = parseFloat($("#price").val());
	   
	   var total = (rate * price);
	   total = total.toFixed(2);
	   total = total.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	   
	   $("#total").text("수취금액은 어쩌고 " + total + " " + $("#quotes").text().replace('/USD','') + " 입니다.");
   }
   
   function exchangeRate(){
	   
	   //alert($("#country option:selected").val());
	   var country = $('#country option:selected').val();
	   
	   $.ajax({
		   type: 'GET',
		   url: '/api/v1/exchangeRate',
		   dataType: 'json',
		   contentType:'application/json; charset=utf-8',
	   }).done(function(res) { 
		   //console.log(JSON.stringify(res.quotes)); 
		   
		   var keys = Object.keys(res.quotes);
		   for (var i=0; i<keys.length; i++){
			   var key = keys[i];
			   //console.log("key : " + key + ", value : " + res.quotes[key]);
			   if(key === country){
				   
				   var obj = res.quotes[key];
				   
				   rate = Math.floor(obj * 100) / 100;
				   
				   $("#exchangeRate").text(numberFormat(obj) + " ");
				   $("#quotes").text(key.replace('USD','') + "/USD");
				   console.log(res.quotes[key]);
			   }
		   }
		   
		   
	   }).fail(function (error) { 
		   console.log(JSON.stringify(error)); 
	   });
   }
   
   // 천단위 콤마, 소숫점 2째까지 보여주기
   function numberFormat(obj) {
	   var parts = Math.floor(obj * 100) / 100;
	   parts = parts.toString().split(".");
	   parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	   
	   return parts.join(".");
   }
   

</script> 
</body>
</html>
