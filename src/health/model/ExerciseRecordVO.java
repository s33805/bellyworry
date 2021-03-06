package health.model;

import java.io.Serializable;

import calories.model.ExerciseCalVO;
import register.model.MemberVO;

public class ExerciseRecordVO implements Serializable {
	// //*****************many-to-one use*********************
	private long no;
	// private MemberVO memberVO;
	private ExerciseCalVO exerciseCalVO;
	private java.sql.Date date;
	private int count;

	@Override
	public String toString() {
		return "{" + no + ":" + memberNo/* memberVO.getMemberNo() */+ ":"
				+ exerciseCalVO + ":" + date + ":" + count + "}";
	}

	// public MemberVO getMemberVO() {
	// return memberVO;
	// }
	// public void setMemberVO(MemberVO memberVO) {
	// this.memberVO = memberVO;
	// }
	public ExerciseCalVO getExerciseCalVO() {
		return exerciseCalVO;
	}

	public void setExerciseCalVO(ExerciseCalVO exerciseCalVO) {
		this.exerciseCalVO = exerciseCalVO;
	}

	// //*********************************************************

	// ************** without many-to-one*********************
	// private long no;
	private int memberNo;

	// private int exerciseNo;
	// private java.util.Date date;
	// private int count;
	//
	// @Override
	// public String toString() {
	// return "{"+no+":"+memberNo+":"+exerciseNo+":"+date+":"+count+"}";
	// }
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	// public int getExerciseNo() {
	// return exerciseNo;
	// }
	// public void setExerciseNo(int exerciseNo) {
	// this.exerciseNo = exerciseNo;
	// }
	// *********************************************************

	@Override
	public boolean equals(Object obj) {
		if (obj != null && (obj instanceof ExerciseRecordVO)) {
			ExerciseRecordVO temp = (ExerciseRecordVO) obj;
			if (this.no == temp.no) {
				return true;
			}
		}
		return false;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
