package mybean.data;

public class Comment {
	String worksid; //作品ID
	String username;//评论者
	String comment;//评论内容



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
