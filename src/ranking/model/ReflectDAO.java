package ranking.model;

import java.util.List;

public interface ReflectDAO {
	public abstract ReflectVO selectByPrimaryKey(int no);

	public abstract List<ReflectVO> getAll();

	public abstract int insert(ReflectVO vo);

	public abstract int update(ReflectVO vo);

	public abstract boolean delete(int no);
	
	public abstract int repeatReflect(ReflectVO vo);
	
	public abstract List<ReflectVO> getPage(int pageNo, int pageSize, int reflectedNo);
	
	public abstract int getPageTotalCount(int reflectedNo);
	
}
