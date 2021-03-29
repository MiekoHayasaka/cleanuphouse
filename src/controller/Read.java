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
 * Servlet implementation class Read
 */
@WebServlet("/Read")
public class Read extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RoomDAO rdao = new RoomDAO();
		List<Room> rlist=rdao.findAll();
		request.setAttribute("rlist", rlist);
		TaskDAO tdao = new TaskDAO();
		List<Task> tlist=tdao.findAll();
		request.setAttribute("tlist", tlist);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/readAll.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// タスクのデータ取得
		String id=request.getParameter("id");
		TaskDAO tdao=new TaskDAO();
		Task task=tdao.findOne(Integer.parseInt(id));
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
		tdao.updateDate(task);

		// 一覧表示
		RoomDAO rdao = new RoomDAO();
		List<Room> rlist=rdao.findAll();
		request.setAttribute("rlist", rlist);
		List<Task> tlist=tdao.findAll();
		request.setAttribute("tlist", tlist);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/readAll.jsp");
		rd.forward(request, response);
	}

}
