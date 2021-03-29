package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Task;

public class TaskDAO {
	private Connection db; //cleanupデータベースに接続されたコネクション
	private PreparedStatement ps; //sql文を保持する変数
	private ResultSet rs; //結果セット(SQL文を実行した結果の集合)を保持

	//接続処理
	private void connect() throws NamingException,SQLException{
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/jsp");
		db = ds.getConnection();
	}

	//切断処理
	private void disconnect() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(db != null) {
				db.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	// タスクの一覧表示
	public List<Task> findAll(){
		List<Task> list = new ArrayList<>();
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM tasks ORDER BY status");
			rs=ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int day = rs.getInt("day");
				String period = rs.getString("period");
				int room_id = rs.getInt("room_id");
				Date updated = rs.getDate("updated");
				int status = rs.getInt("status");
				//int season = rs.getInt("season");
				//int importance = rs.getInt("importance");
				Task task = new Task(id,name,day,period,room_id,updated,status);
				list.add(task);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return list;
	}

	// タスクの一覧表示（場所毎）
	public List<Task> findRoom(int room_id){
		List<Task> list = new ArrayList<>();
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM tasks WHERE room_id=? ORDER BY status");
			ps.setInt(1, room_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int day = rs.getInt("day");
				String period = rs.getString("period");
				Date updated = rs.getDate("updated");
				int status = rs.getInt("status");
				//int season = rs.getInt("season");
				//int importance = rs.getInt("importance");
				Task task = new Task(id,name,day,period,room_id,updated,status);
				list.add(task);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return list;
	}

	public void insertOne(Task task) {
		try {
			this.connect();
			ps=db.prepareStatement("INSERT INTO tasks(name,day,period,room_id,updated,status) VALUES(?,?,?,?,?,?)");
			ps.setString(1, task.getName());
			ps.setInt(2, task.getDay());
			ps.setString(3, task.getPeriod());
			ps.setInt(4, task.getRoom_id());
			ps.setDate(5, task.getUpdated());
			ps.setInt(6, task.getStatus());
			ps.executeUpdate();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}

	public Task findOne(int id) {
		Task task=null;
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM tasks WHERE id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				String name=rs.getString("name");
				int day = rs.getInt("day");
				String period = rs.getString("period");
				int room_id = rs.getInt("room_id");
				Date updated = rs.getDate("updated");
				int status = rs.getInt("status");
				task=new Task(id,name,day,period,room_id,updated,status);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return task;
	}

	// タスクの更新
	public void updateOne(Task task) {
		try {
			this.connect();
			ps=db.prepareStatement("UPDATE tasks SET name=?,day=?,period=?, status=? WHERE id=?");
			ps.setString(1, task.getName());
			ps.setInt(2, task.getDay());
			ps.setString(3, task.getPeriod());
			ps.setInt(4, task.getStatus());
			ps.setInt(5, task.getId());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}

	// 掃除完了時のタスク更新
	public void updateDate(Task task) {
		try {
			this.connect();
			ps=db.prepareStatement("UPDATE tasks SET updated=?, status=? WHERE id=?");
			ps.setDate(1, task.getUpdated());
			ps.setInt(2, task.getStatus());
			ps.setInt(3, task.getId());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}

	// ステータスの更新
	public void updateStatus(Task task) {
		try {
			this.connect();
			ps=db.prepareStatement("UPDATE tasks SET status=? WHERE id=?");
			ps.setInt(1, task.getStatus());
			ps.setInt(2, task.getId());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	public void deleteOne(int id) {
		try {
			this.connect();
			ps=db.prepareStatement("DELETE FROM tasks WHERE id=?");
			ps.setInt(1, id);
			ps.execute();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
}


