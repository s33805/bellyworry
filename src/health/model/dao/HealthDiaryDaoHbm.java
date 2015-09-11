package health.model.dao;

import init.GlobalService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import calories.model.MenuVO;
import health.model.HealthDiaryDAO;
import health.model.HealthDiaryVO;
import hibernate.util.HibernateUtil;

public class HealthDiaryDaoHbm implements HealthDiaryDAO {

	@Override
	public HealthDiaryVO selectByPrimaryKey(long no) {
		HealthDiaryVO healthDiaryVO = null;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
	try {
		session.beginTransaction();
		healthDiaryVO = (HealthDiaryVO) session.get(HealthDiaryVO.class, no);
		session.getTransaction().commit();
		} catch (RuntimeException ex) {
		session.getTransaction().rollback();
		throw ex;
		}
		return healthDiaryVO;
	}	
	@Override
	public List<HealthDiaryVO> selectByMemberNo(int memberNo) {
		List<HealthDiaryVO> healthDiaryVO = null;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
	try {
		session.beginTransaction();
		Query query=session.createQuery("from HealthDiaryVO where memberNo=?");
		query.setParameter(0, memberNo);
		healthDiaryVO=query.list();
//		healthDiaryVO = (HealthDiaryVO) session.get(HealthDiaryVO.class, memberNo);
		session.getTransaction().commit();
		} catch (RuntimeException ex) {
		session.getTransaction().rollback();
		throw ex;
		}
		return healthDiaryVO;
	}
	
	
	@Override
	public List<HealthDiaryVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<HealthDiaryVO> result = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from HealthDiaryVO order by no");
			result = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return result;
	}
	
	@Override
	public long insert(HealthDiaryVO vo) {
		long result=0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			result=vo.getNo();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			result=0;
			throw ex;
		}
		return result;
	}
	@Override
	public int update(HealthDiaryVO vo) {
		int result=0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			result=1;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			result=0;
			throw ex;
		}
		return result;
	}

	
	@Override
	public boolean delete(long no) {
		boolean result=false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete from menu where menuNo=?");
//			query.setParameter(0, menuNo);
//			System.out.println("刪除的筆數=" + query.executeUpdate());
			Query query = session.createQuery("delete from HealthDiaryVO where no=?");
			query.setParameter(0, no);			
			int i = query.executeUpdate();			
			if(i>0){
				result = true;
			}
			session.getTransaction().commit();
			System.out.println("刪除的筆數=" + i);
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;			
		}
//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
//			EmpVO empVO = new EmpVO();
//			empVO.setEmpno(empno);
//			session.delete(empVO);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方emp2.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
//			HealthDiaryVO healthDiaryVO = (HealthDiaryVO) session.get(HealthDiaryVO.class, no);
//			session.delete(healthDiaryVO);
//			session.getTransaction().commit();
//			result =true;
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//			
//		}
		return result;
	}


}
