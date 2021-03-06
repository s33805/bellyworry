package fun.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fun.model.dao.HealthViewDAOHbm;
import fun.model.dao.ViewClassDAOHbm;
import health.model.HealthDiaryVO;
import health.model.HeroHealthDiaryVO;

@Path("/healthview")
public class HealthViewService {
	private HealthViewDAO healthViewDAO = new HealthViewDAOHbm();
	private ViewClassDAO viewClassDAO = new ViewClassDAOHbm();
	
	public HealthViewVO selectByPrimaryKey(int no){
		return healthViewDAO.selectByPrimaryKey(no);
	}
	
	//前台JSP網頁專用
	@GET
	@Path("/{no}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HealthViewVO> selectByViewClassNo(@PathParam("no") int viewClassNo){
		ViewClassVO temp = viewClassDAO.selectByPrimaryKey(viewClassNo);
		return healthViewDAO.selectByViewClassVO(temp,0,0);
	}
	
	//後台CRUD使用
	public List<HealthViewVO> select(HealthViewVO vo) {
		List<HealthViewVO> result = null;
		if(vo!=null && vo.getNo()!=0) {
			HealthViewVO temp = healthViewDAO.selectByPrimaryKey(vo.getNo());
			if(temp!=null) {
				result = new ArrayList<HealthViewVO>();
				result.add(temp);
			}
		} else if(vo!=null && vo.getViewClassVO()!=null) {
			result = healthViewDAO.selectByViewClassVO(vo.getViewClassVO(),0,0);
		} else {
			result = healthViewDAO.getAll(0,0); 
		}
		return result;
	}
	public int insert(HealthViewVO vo){
		return healthViewDAO.insert(vo);
	}
	public int update(HealthViewVO vo){
		return healthViewDAO.update(vo);
	}
	public boolean delete(int no){
		return healthViewDAO.delete(no);
	}
	public static void main(String[] args){
		HealthViewService service = new HealthViewService();
//		System.out.println(service.selectByPrimaryKey(100001));
//		HealthViewVO vo = new HealthViewVO();
//		vo.setNo(3);
//		System.out.println(service.update(vo));
		
		ViewClassVO vo = new ViewClassService().getViewClass(100002);
		System.out.println(service.getPageDate(0, 8, vo));
	}
	
	public HealthViewPageVO getPageDate(int pageNo, int pageSize, ViewClassVO vo){	//只要pageNo=0 不分頁
		List<HealthViewVO> list = null;
		int rowCount = 0;
		int pageSizePatch = 1;
		if(pageSize>0){	//避免每頁低於1筆 
			pageSizePatch = pageSize;
		}
		if(vo==null){
			list = healthViewDAO.getAll(pageNo,pageSizePatch);
			rowCount = healthViewDAO.getDateTotalCount(null);
		}else{
			list = healthViewDAO.selectByViewClassVO(vo,pageNo,pageSizePatch);
			rowCount = healthViewDAO.getDateTotalCount(vo);
		}
		HealthViewPageVO result = new HealthViewPageVO(pageNo, pageSizePatch, rowCount, list);
		return result;
	}
	
}
