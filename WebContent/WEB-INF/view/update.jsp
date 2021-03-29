<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*"%>
<%
Room room=(Room)request.getAttribute("room");
Task task=(Task)request.getAttribute("task");
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
<h2><%=room.getName() %>のタスク更新</h2>
<form action="/CleanUp/Update" method="post">
<table class="form-table">
<tr>
<th>タスク</th>
<td><input type="text" name="name" value="<%=task.getName() %>"></td>
</tr>
<tr>
<th>掃除の間隔</th>
<td><input type="number" name="day" min="1" max="31" value="<%=task.getDay() %>">
<select name="period">
<% switch(task.getPeriod()){
case "日":%>
<option value="日" selected>日</option>
<option value="週">週</option>
<option value="ケ月">ケ月</option>
<option value="年">年</option>
<% break;
case "週": %>
<option value="日">日</option>
<option value="週" selected>週</option>
<option value="ケ月">ケ月</option>
<option value="年">年</option>
<% break;
case "ケ月": %>
<option value="日">日</option>
<option value="週">週</option>
<option value="ケ月" selected>カ月</option>
<option value="年">年</option>
<% break;
case "年": %>
<option value="日">日</option>
<option value="週">週</option>
<option value="ケ月">カ月</option>
<option value="年" selected>年</option>
<% break;}%>
</select> 毎</td>
</tr>
<tr><td>
<input type="hidden" name="id" value="<%=task.getId() %>">
<input type="hidden" name="room_id" value="<%=room.getId() %>">
<input type="hidden" name="rname" value="<%=room.getName() %>">
</td>
<td><button type="submit">タスク更新</button></td>
</tr>
</table>
</form>
</div>
<br><br><br><br><br>
    <footer>
      &copy;CleanHouseProject
    </footer>
</div>
</body>
</html>