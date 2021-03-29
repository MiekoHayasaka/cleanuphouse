<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <section id="information">
        <div id="infoBox">
          <section class="info">
            <h3>１．<a href="/CleanUp/Create">おそうじ場所の登録</a></h3>
            <table id="center">
            <tr><th><p>１．先ずは掃除する部屋・エリアの登録をします。<br>
            「掃除場所の入力欄」には入力候補が表示されるのでそこから選んでください。また、候補にない場所は入力してください。<br>
            ２．「部屋・エリアの登録」をするとその部屋で行うタスクの登録ができるようになります。ここでタスクの登録を行うか、リンクをクリックするとタスク登録・一覧画面に遷移します。
            </p></th></tr>
            <tr><td><img src="images/cleanup1.png" alt="部屋・エリア登録" width="380"></td>
            </tr>
            </table>
          </section>
          <section class="info">
            <h3>２．タスクの登録・完了・更新・削除</h3>
            <table id="center">
            <tr><th><p>１.で登録した部屋・エリアで行うタスクの登録をし、掃除の頻度と最後に掃除した日を設定します。<br>
           【タスク例】<br>バスルーム ⇒ バスタブ、フロア、洗面台、トイレ、ミラー、バスマット交換<br>
           【頻度例】<br>バスマット交換 ⇒ 1か月毎、バスタブ⇒２日毎
           </p></th></tr>
           <tr><td><img src="images/cleanup2.png" alt="タスク登録・完了・更新・削除" width="380"></td>
            </tr>
            </table>
          </section>
          <section class="info">
            <h3>３．<a href="/CleanUp/Read">一覧表示</a></h3>
            <table id="center">
            <tr><th><p>登録した場所とタスクが一覧表示されます。次回掃除予定日までの日数を確認し、掃除が完了したら「完了！」ボタンを押しましょう。
          	</p></th></tr>
            <tr><td><img src="images/cleanup4.png" alt="一覧表示" width="380"></td>
            </tr>
            </table>
          </section>
        </div>
      </section>
    </div>
    <footer>
      &copy;CleanHouseProject
    </footer>
  </div>
</body>
</html>
