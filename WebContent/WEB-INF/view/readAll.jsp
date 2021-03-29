<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*"%>
<%
	List<Room> rlist = (List<Room>)request.getAttribute("rlist");
	List<Task> tlist = (List<Task>)request.getAttribute("tlist");
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
<h2>お掃除の予定一覧</h2>
<div id="wrapper">
<% if(tlist != null && tlist.size() > 0){ %>
<table class="list-table">
<%for(Room r:rlist){ %>
<tr><th><a href="/CleanUp/CreateTask?room_id=<%=r.getId()%>&rname=<%=r.getName()%>">
<h3><%=r.getName() %></h3></th>
<th><input type="hidden" name="room_id" value="<%=r.getId()%>"></thd>
<th></th><th></th>
</tr>
<tr><td>タスク</td><td>掃除の間隔</td><td>予定日まで</td><td>掃除完了</td>
</tr>
  <%for(Task t:tlist){
	if(r.getId() == t.getRoom_id()){
  %>
	<form action="/CleanUp/Read" method="post">
	<input type="hidden" name="id" value="<%=t.getId()%>">
	<tr>
	<td id="green"><%=t.getName() %></td>
	<td><%=t.getDay() %> <%=t.getPeriod() %>毎</td>
	<% if(t.getStatus()<0){ %>
	<td id="red"><%=t.getStatus()%> 日</td>
	<%}else if(t.getStatus()==0){ %>
	<td id="yellow"><%=t.getStatus()%> 日</td>
	<%}else { %>
	<td id="blue"><%=t.getStatus()%> 日</td>
	<%} %>
	<td><button type="submit"  onclick="return alert('頑張りましたね！お疲れさまでした。');">完了！</button></td>
	</tr>
	</form>
	<%} %>
	<%} %>
<%} %>
</table>
<%} else{ %>
<p>まだおそうじの登録はされていません</p>
<%} %>
</div>
</div>
    <footer>
      &copy;CleanHouseProject
    </footer>
</div>
</body>
</html>