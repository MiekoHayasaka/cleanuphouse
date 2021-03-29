package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import dao.TaskDAO;


public class CalendarLogic  implements Serializable{

	// タスク登録・更新・完了時
	public int Status(int day,String period,Date updated) {
		int status=0;
		// 現在日時を取得
		Calendar today = Calendar.getInstance();

		// 最後に掃除した日
		Calendar last = Calendar.getInstance();
		last.setTime(updated);

		//最後に掃除した日 - 今日
		long lastMillis = last.getTimeInMillis();
		long todayMillis = today.getTimeInMillis();
		long t_diff = (lastMillis - todayMillis) / 1000 / 60 / 60 / 24;
		status=(int)t_diff;

		// 次回掃除予定日
		Calendar next = Calendar.getInstance();
		next.setTime(updated);
		if(period.equals("日")) {
			next.add(Calendar.DAY_OF_MONTH, day);
		}else if(period.equals("週")) {
			next.add(Calendar.WEEK_OF_YEAR, day);
		}else if(period.equals("ケ月")) {
			next.add(Calendar.MONTH, day);
		}else {
			next.add(Calendar.YEAR, day);
		}

		//次回掃除予定日 - 最後に掃除した日
		long nextMillis = next.getTimeInMillis();
		long diff = (nextMillis - lastMillis) / 1000 / 60 / 60 / 24;
		status +=(int)diff;

		return status;
	}

	// ステータスの更新時
	public void StatusUp(int room_id) {
		int status=0;
		TaskDAO dao = new TaskDAO();
		List<Task> list=dao.findRoom(room_id);

		// 現在日時を取得
		Calendar today = Calendar.getInstance();

		if(list != null && list.size() > 0){
			for(Task t:list){
				// 最後に掃除した日
				Calendar last = Calendar.getInstance();
				last.setTime(t.getUpdated());

				//最後に掃除した日 - 今日
				long lastMillis = last.getTimeInMillis();
				long todayMillis = today.getTimeInMillis();
				long t_diff = (lastMillis - todayMillis) / 1000 / 60 / 60 / 24;
				status=(int)t_diff;

				// 次回掃除予定日
				//Calendar next = Calendar.getInstance();
				String period=t.getPeriod();
				int day=t.getDay();

				if(period.equals("日")) {
					last.add(Calendar.DAY_OF_MONTH, day);
				}else if(period.equals("週")) {
					last.add(Calendar.WEEK_OF_YEAR, day);
				}else if(period.equals("ケ月")) {
					last.add(Calendar.MONTH,day);
				}else {
					last.add(Calendar.YEAR, day);
				}

				//次回掃除予定日 - 最後に掃除した日
				long nextMillis = last.getTimeInMillis();
				long diff = (nextMillis - lastMillis) / 1000 / 60 / 60 / 24;
				status +=(int)diff;

				// ステータスを更新
				int id=t.getId();
				Task task=new Task(id,status);
				dao.updateStatus(task);
			}
		}

	}

}