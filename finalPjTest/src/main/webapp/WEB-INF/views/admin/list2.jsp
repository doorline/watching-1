<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.finalPj.testpj.dto.ProductDTO" %>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<title>list</title>
</head>
<style>
	body{
		background-color:black;
		line-height:2em;
		color:#fff;
		margin:30px;
		}
	ul li{
		list-style:none;
		}
	.list{		
		display:table;
		width:900px;
		margin:auto;
		position: relative;
		}
	.boardDiv{
		width:100%;
	}
	.board{
		clear:both;
    	position:relative;
    	padding: 0 auto;
    	min-width:40px;
	}
	.menu{
		border-bottom:2px solid silver;
		font-size:14pt;
		font-weight:bold;
		vertical-align:baseline;
		color: #fff;	
	}
	.line{
		font-size:12pt;
    	border-bottom:1px solid silver;
    	vertical-align:baseline;
    	color: #fff;
	}
	<!--게시판 열 설정 -->
	.board>li:first-child {width:10%;}
	.board>li:first-child +li{width:10%;}
	.board>li:first-child +li+li{width:50%;}
	.board>li:first-child +li+li+li{width:15%;}
	.board>li:first-child +li+li+li+li{width:15%;}
	
	.paging{
		clear:both;
		position:relative;
	}
	<!--버튼 -->
	
	<!--검색 -->
	.search{
		margin:auto;
		clear:both;
		vertical-align:middle;
	}
	.search_input{
		width:300px;
		height:25px;
		border:0;		
	}
	.search_btn{
		width:40px;
		height:29px;
		border:0;
		background-color:#DC143C;
		vertical-align:bottom;
		font-weight:bold;
		border-radius:1px;
	}
	.search_select{
		width:55px;
		height:25px;
		border:0;
		border-radius:1px;
	}
	<!--a태그 설정 -->
	a{text-decoration:none;}
	a:link{
		text-decoration:none;
		color: #fff;		
		}
	a:visited{
		text-decoration:none;
		color: #fff;
		}
	a:hover{
		color: #DC143C;
	}
	.m5{margin:5px;}
	.mt10{margin:10px 0 0 0;}
	.btn-m5{margin:5px 0 0 5px;}
	.m20{margin:20px;}
	.fl{float:left;}
	.tc{text-align:center;}
</style>
<script>
	$(document).on('click','#btnSearch',function(e){
		e.preventDefault();
		var url="${targetURL}";
		url = url+"?searchType="+$('#searchType').val();
		url = url+"&keyword="+$('#keyword').val();
		url = url+"&nowPage="+$('#nowPage').val();
		location.href = url;
		console.log(url);
	});
</script>
<body>

	<h1>관리자 모드</h1>
	
	<hr>
	
<div class="list">
<form action="/admin/delete" >
	<div class="boardDiv m20">
		<ul class="board">
			<li class="fl tc menu"><span style="margin:10px;"></span></li>
			<li class="fl tc menu">pCode</li>
			<li class="fl tc menu">제목</li>
			<li class="fl tc menu">게시자</li>
			<li class="fl tc menu">업로드 일자</li>
		</ul>
		<c:forEach items="${dtos}" var="dtos">
		 	<ul class="board">
		 		<li class="fl tc line"><input name="pCodes" type="checkbox" value ="${dtos.pCode}"></li>
				<li class="fl tc line">${dtos.pCode}</li>
				<li class="fl tc line"><a href="/admin/view?pCode=${dtos.pCode}">${dtos.pName}</a></li>
				<li class="fl tc line">${dtos.aId}</li>
				<li class="fl tc line">${dtos.pUploadDate}</li>
			</ul>
		</c:forEach>
		 <ul class="board">
			<li class="fl tc btn-m5">
				<style>
					.d_btn{
						border:0;
						height:25px;
						background-color:#DC143C;
						border-radius:1px;
						font-weight:bold;
					}
				</style>
				<button type="submit" class="d_btn">삭제</button>
			</li> 
			<li class="fl tc btn-m5">
				<input type="button" onclick="location.href='uploadView'" value="업로드 작성" class="d_btn"/>
			</li>
		</ul>
	</div>
</form>

<div class="paging tc">
	<c:if test="${paging.startPage != 1}">
 		<a href="/admin/list?nowPage=${paging.startPage -1}">&lt;</a>
 	</c:if>
 	<c:if test="${search.keyword == null }">
	 	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
	 		<c:choose>
	 			<c:when test="${p == paging.nowPage}">
	 				<b>${p}</b>	
	 			</c:when>
	 			<c:when test="${p != paging.nowPage}">
	 				<a href="/admin/list?nowPage=${p}">${p}</a>
	 			</c:when>
	 		</c:choose>
	 	</c:forEach>
 	</c:if>
 	<c:if test="${search.keyword != null }">
	 	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
	 		<c:choose>
	 			<c:when test="${p == paging.nowPage}">
	 				<b>${p}</b>	
	 			</c:when>
	 			<c:when test="${p != paging.nowPage}">
	 				<a href="/admin/list?searchType=${search.searchType}&keyword=${search.keyword}&nowPage=${p}">${p}</a>
	 			</c:when>
	 		</c:choose>
	 	</c:forEach>
 	</c:if>
 	<c:if test="${paging.startPage != paging.lastPage}">
 		<a href="/admin/list?nowPage=${paging.startPage +1}">&gt;</a>
 	</c:if>
</div>

<div class="search tc btn-m10">
	<select name="searchType" id="searchType" class="search_select">
		<option value="pName">제목</option>
		<option value="pContent">내용</option>
	</select>
	
	<input type="text" name="keyword" id="keyword" class="search_input">
	<input type="hidden" name="nowPage" id="nowPage" value="${paging.nowPage}">
	
	<c:url var="targetURL" value="/admin/list"></c:url>
	<button name="btnSearch" id="btnSearch" class="search_btn">검색</button>
</div>

</div>
</body>
</html>