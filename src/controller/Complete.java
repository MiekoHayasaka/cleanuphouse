package controller;

import java.io.IOException;
import java.util.Date;
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
 * Servlet implementation class Complete
 */
@WebServlet("/Complete")
public class Complete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// タスクのデータ取得
		String id=request.getParameter("id");
		TaskDAO dao=new TaskDAO();
		Task task=dao.findOne(Integer.parseInt(id));
		int day=task.getDay();
		String period=task.getPeriod();

		// 今日の日付を取得
		Date udate=new Date();
		java.sql.Date updated = new java.sql.Date(udate.getTime());

		// 掃除完了日・ステータスの更新
		CalendarLogic cl=new CalendarLogic();
		int status=cl.Status(day,period,updated);
		task.setStatus(status);
		task.setUpdated(updated);
		dao.updateDate(task);

		// 掃除場所の取得
		String room_id=request.getParameter("room_id");
		RoomDAO rdao=new RoomDAO();
		Room room = rdao.findOne(Integer.parseInt(room_id));
		request.setAttribute("room", room);

		// タスクの一覧表示
		List<Task> list=dao.findRoom(Integer.parseInt(room_id));
		request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/read.jsp");
		rd.forward(request, response);
	}

}
