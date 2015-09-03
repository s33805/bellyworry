package health.model;

import java.util.List;

public interface HealthDiaryDAO {
	public abstract HealthDiaryVO selectByPrimaryKey(long no);
	
	public abstract HealthDiaryVO selectByMemberNo(int memberNo);

	public abstract List<HealthDiaryVO> getAll();

	public abstract HealthDiaryVO insert(HealthDiaryVO vo);

	public abstract HealthDiaryVO update(HealthDiaryVO vo);

	public abstract boolean delete(long no);
}
