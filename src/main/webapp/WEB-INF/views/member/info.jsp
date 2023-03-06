<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myInfo</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<h1>내 정보 보기</h1>
<form id="frmInfo" method="get">
	<input value="${membervo.id }" type="text" name="id" readonly="readonly"><br>
	<input value="${membervo.passwd }" type="password" placeholder="pass" readonly="readonly"><br>
	<input value="${membervo.name }"  type="text" readonly="readonly"><br>
	<input value="${membervo.email }" type="text" placeholder="email" readonly="readonly"><br>
	
	<!-- <button type="button" onclick="frmSubmit('update');">수정</button>
	<button type="button" onclick="frmSubmit('delete');">탈퇴</button>
	 -->
	<button type="button">수정</button>
	<button type="button">탈퇴</button>
</form>

<script>

	$("button").eq(2).click('update',frmSubmit2);
	$("button").eq(3).click('delete',frmSubmit2);
	
	/* 
	$("button").first().click(frmSubmit); 버튼 태그의 첫번째 것 ==> 이건 불가
	$("button").click(frmSubmit);
	

	function frmSubmit(event){
		
		//form.action = targetEle; // 이건 form 태그가 1개이어야만 한다.
		
		// 이거 사용하면 버튼간 가능
		frmInfo.action= event.data;
		frmInfo.submit();
		
		//document.getElementById("frmInfo").action = targetEle;
		//document.getElementByIdClassName("aaa")[0]
		//document.querySelector(".aaa.bbb[type= text]").action = targetEle; // aaa 와 bbb 의 이름을 가진(type 이 text 인)
		//document.querySelectorAll("#formInfo")[0].action = targetEle;
		// $("#frmInfo").attr("action",targetEle);
		//$("#frmInfo").attr("action",targetEle);
		//$("#frmInfo").submit;
		
	}*/
	
function frmSubmit2(event){
		
		//form.action = targetEle; // 이건 form 태그가 1개이어야만 한다.
		
		// 이거 사용하면 버튼간 가능
		frmInfo.action= event.data;
		frmInfo.submit();
		
		//document.getElementById("frmInfo").action = targetEle;
		//document.getElementByIdClassName("aaa")[0]
		//document.querySelector(".aaa.bbb[type= text]").action = targetEle; // aaa 와 bbb 의 이름을 가진(type 이 text 인)
		//document.querySelectorAll("#formInfo")[0].action = targetEle;
		// $("#frmInfo").attr("action",targetEle);
		//$("#frmInfo").attr("action",targetEle);
		//$("#frmInfo").submit;
		
	}


</script>


</body>
</html>