<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*,model.*"%>
<%
	List<Room> list = (List<Room>)request.getAttribute("list");
	Date udate=new Date();
	java.sql.Date updated = new java.sql.Date(udate.getTime());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>キレイなお家</title>
  <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
  <link rel="stylesheet" href="css/main.css"/>
</head>
<body>
  <div class="container">
  <div id="right">
		<a href="">ログイン</a>
		<a href="">新規登録</a>
		</div>
    <header>
      <h1>キレイなお家</h1>
    </header>
    <nav>
        <a href="index.jsp">つかい方</a>
        <a href="/CleanUp/Create">おそうじ登録</a>
        <a href="/CleanUp/Read">一覧表示</a>
    </nav>
    <div role="main">
<h2>部屋・エリア登録</h2>
<form action="/CleanUp/Create" method="post">
<table class="form-table">
<tr>
<th>掃除場所の入力</th>
<td>
<input type="text" name="name" list="room">
<datalist id="room">
<option value="リビング">リビング</option>
<option value="ダイニング">ダイニング</option>
<option value="キッチン">キッチン</option>
<option value="寝室">寝室</option>
<option value="バスルーム">バスルーム</option>
<option value="トイレ">トイレ</option>
<option value="玄関">玄関</option>
<option value="ベランダ">ベランダ</option>
</datalist>
</td></tr>
<tr><td></td><td><button type="submit">部屋・エリア登録</button></td></tr>
</table>
</form>

<h2>タスク登録　/　部屋・エリア削除</h2>
<div id="wrapper">
<% if(list != null && list.size() > 0){ %>
<table class="form-table">
<%for(Room r:list){ %>

<form action="/CleanUp/Delete" method="post">
<tr>
<th>部屋・エリア</th>
<td><a href="/CleanUp/CreateTask?room_id=<%=r.getId()%>&rname=<%=r.getName()%>">
<%=r.getName() %></a>
<input type="hidden" name="room_id" value="<%=r.getId() %>">
　　<button type="submit"  onclick="return confirm('[<%=r.getName()%>]を削除してよろしいですか？');">部屋削除</button>
</td>
</tr>
</form>

<form action="/CleanUp/CreateTask" method="post">
<tr>
<th>タスクの追加</th>
<td><input type="text" name="name" placeholder="例：床掃除"></td>
</tr>
<tr>
<th>掃除間隔</th>
<td><input type="number" name="day" min="1" max="31" placeholder="日数">
<select name="period">
<option value="日">日</option>
<option value="週">週</option>
<option value="ケ月">ケ月</option>
<option value="年">年</option>
</select>毎</td>
</tr>
<tr>
<th>最後に掃除した日</th>
<td><input type="date" name="updated" value="<%=updated%>"></td>
</tr>
<tr>
<td><input type="hidden" name="room_id" value="<%=r.getId() %>">
<input type="hidden" name="rname" value="<%=r.getName() %>"></td>
<td><button type="submit">タスク登録</button></td>
</tr>
</form>

<%} %>
</table>
<%} %>
</div>
</div>
    <footer>
      &copy;CleanHouseProject
    </footer>
  </div>
</body>
</html>