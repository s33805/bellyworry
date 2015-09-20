package controller;

import health.model.HealthDiaryService;
import health.model.HealthDiaryVO;
import health.model.dao.HealthDiaryDaoHbm;
import hibernate.util.HibernateUtil;
import init.GlobalService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/health.do")
public class healthServlet extends HttpServlet {
	private HealthDiaryService service;

	@Override
	public void init() throws ServletException {
		service = new HealthDiaryService();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HealthDiaryVO vo = new HealthDiaryVO(); // vo拿來裝insert資料
		HealthDiaryVO old = new HealthDiaryVO(); // 用來裝dateSelect 回傳的資料
		GlobalService globalService = new GlobalService();

		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMessage = new HashMap<String, String>();
		request.setAttribute("errorMessage", errorMessage);

		String no = request.getParameter("id");
		String date = request.getParameter("date");
		if (date == null || date.trim().length() == 0) {
			errorMessage.put("dateError", "時間欄必須填寫");
		}
		String height = request.getParameter("height");
		if (height == null || height.trim().length() == 0) {
			errorMessage.put("heightError", "身高欄必須寫");
		}
		String weight = request.getParameter("weight");
		if (weight == null || weight.trim().length() == 0) {
			errorMessage.put("weightError", "體重欄必須寫");
		}
		String waistline = request.getParameter("waistline");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String share = request.getParameter("share");

		// 格式判斷
		float heightchange = 0;
		if (height != null) {
			try {
				heightchange = Float.parseFloat(height);
			} catch (NumberFormatException e) {
				errorMessage.put("heightError", "格式錯誤");
			}
		}
		float weightchange = 0;
		if (weight != null) {
			try {
				weightchange = Float.parseFloat(weight);
			} catch (NumberFormatException e) {
				errorMessage.put("weightError", "格式錯誤");
			}
		}
		float waistlinechange = 0;
		if (waistline != null) {
			try {
				waistlinechange = Float.parseFloat(waistline);
			} catch (NumberFormatException e) {
				errorMessage.put("waistlineError", "格式錯誤");
			}
		}
		String shareCheange = "0";
		if (share != null) {
			shareCheange = "1";
		}
		Date date1 = globalService.convertDate(date);
		if (!errorMessage.isEmpty()) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/healthForm.jsp");
			rd.forward(request, response);
			return;
		}

		// ****************************************************************************//
		int id = Integer.parseInt(no);
		vo.setMemberNo(Integer.parseInt(no));
		// ****************************************************************************//
		vo.setDate(new java.sql.Date(date1.getTime()));
		vo.setHeight(heightchange);
		vo.setWeight(weightchange);
		vo.setWaistline(waistlinechange);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setShare(shareCheange);
		

		
		List<HealthDiaryVO> list = service.dateSelect(vo.getMemberNo(),vo.getDate());
		
		if (!list.isEmpty()) {
			old = list.get(0); // list取出來第0位
		}

		if (list.isEmpty()) { // 判斷list是否為空isEmpty()回傳boolean true為空值,false有值
			System.out.println("Insert");
			service.insert(vo);
			request.setAttribute("vo", vo);
		} else {
			String inputDate = globalService.convertString(vo.getDate()); // 把insert時間轉換成字串
			String sqlDate = globalService.convertString(old.getDate()); // 資料庫取出的時間轉換成字串
			if (inputDate.equals(sqlDate)) {
				vo.setNo(old.getNo()); // 將新資料vo取代舊主鍵vo1內的資料(vo1資料庫內有的舊主鍵)
				if (vo.getWaistline() == 0) {
					vo.setWaistline(old.getWaistline());
				}

				service.update(vo);
				request.setAttribute("vo", vo);
				System.out.println("Update");

			}
		}

		RequestDispatcher rd = request
				.getRequestDispatcher("/healthSuccess.jsp");
		rd.forward(request, response);
		return;

	}

}
