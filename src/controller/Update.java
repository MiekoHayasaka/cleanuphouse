package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoomDAO;
import dao.TaskDAO;
import model.CalendarLogic;
import model.Room;
import model.Task;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String room_id=request.getParameter("room_id");
		String s_id=request.getParameter("id");
		if(s_id == null) {
			String rname=request.getParameter("rname");
			//Room room=new Room(Integer.parseInt(room_id),rname);
			request.setAttribute("room_id", room_id);
			request.setAttribute("rname", rname);
			RequestDispatcher rd=request.getRequestDispatcher("/CleanUp/CreateTask");
			rd.forward(request, response);
		}else {
			RoomDAO rdao=new RoomDAO();
			Room room = rdao.findOne(Integer.parseInt(room_id));
			request.setAttribute("room", room);
			TaskDAO dao=new TaskDAO();
			Task task = dao.findOne(Integer.parseInt(s_id));
			request.setAttribute("task", task);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/update.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// タスクのデータ取得
		request.setCharacterEncoding("UTF-8");
		String s_id=request.getParameter("id");
		int id=Integer.parseInt(s_id);
		TaskDAO dao=new TaskDAO();
		Task task = dao.findOne(id);

		// タスクの更新
		String name=request.getParameter("name");
		String s_day=request.getParameter("day");
		int day=Integer.parseInt(s_day);
		String period=request.getParameter("period");
		Date updated=task.getUpdated();
		CalendarLogic cl=new CalendarLogic();
		int status=cl.Status(day,period,updated);
		task=new Task(id,name,day,period,status);
		dao.updateOne(task);

		// 更新した掃除場所の取得
		String s_room_id=request.getParameter("room_id");
		int room_id=Integer.parseInt(s_room_id);
		String rname=request.getParameter("rname");

		Room room = new Room(room_id,rname);
		request.setAttribute("room", room);
System.out.println(room_id);
		// タスクの一覧表示
		List<Task> list=dao.findRoom(room_id);
		request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/read.jsp");
		rd.forward(request, response);
	}
}
