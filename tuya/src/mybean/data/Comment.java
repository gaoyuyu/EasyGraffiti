package mybean.data;

public class Comment {
	String worksid; //��ƷID
	String username;//������
	String comment;//��������



	public String getWorksId() {
		return worksid;
	}

	public void setWorksId(String id) {
		this.worksid = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Comment [worksid=" + worksid + ", username=" + username
				+ ", comment=" + comment + "]";
	}


}
