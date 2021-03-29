package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoomDAO;
import dao.TaskDAO;
import model.Room;
import model.Task;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// タスクの削除
		String s_id=request.getParameter("id");
		TaskDAO dao = new TaskDAO();
		if(s_id != null){
			dao.deleteOne(Integer.parseInt(s_id));
		}
		// 掃除場所の取得
		request.setCharacterEncoding("UTF-8");
		String room_id=request.getParameter("room_id");
		String rname=request.getParameter("rname");
		Room room=new Room(Integer.parseInt(room_id),rname);
		request.setAttribute("room", room);

		// タスクの一覧表示
		List<Task> list=dao.findRoom(Integer.parseInt(room_id));
		request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/read.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ルームの削除
		String room_id=request.getParameter("room_id");
		RoomDAO dao = new RoomDAO();
		if(room_id != null){
			dao.deleteOne(Integer.parseInt(room_id));
		}

		response.sendRedirect("/CleanUp/Create");
	}

}
