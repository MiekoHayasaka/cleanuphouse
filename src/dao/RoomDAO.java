package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Room;

public class RoomDAO {
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

	public List<Room> findAll(){
		List<Room> list = new ArrayList<>();
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM rooms ");
			rs=ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Room room = new Room(id,name);
				list.add(room);
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return list;
	}

	public void insertOne(Room room) {
		try {
			this.connect();
			ps=db.prepareStatement("INSERT INTO rooms(name) VALUES(?)");
			ps.setString(1, room.getName());
			ps.executeUpdate();
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}

	public Room findOne(int id) {
		Room room=null;
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM rooms WHERE id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				String name=rs.getString("name");
				room=new Room(id,name);
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return room;
	}

	public void updateOne(Room room) {
		try {
			this.connect();
			ps=db.prepareStatement("UPDATE rooms SET name=? WHERE id=?");
			ps.setString(1, room.getName());
			ps.setInt(2, room.getId());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}

	public void deleteOne(int id) {
		try {
			this.connect();
			ps=db.prepareStatement("DELETE rooms,tasks FROM rooms LEFT JOIN tasks ON rooms.id=tasks.room_id WHERE rooms.id=?");
			ps.setInt(1, id);
			ps.execute();
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
}

