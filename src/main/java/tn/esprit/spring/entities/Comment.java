package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int commentid;
	private String content;
	@Temporal(TemporalType.DATE)
	private Date commentdate;
	
	
	@ManyToOne
	Ads ads;
	@ManyToOne
	User user;
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public Date getCommentdate() {
		return commentdate;
	}
	public void setCommentdate(Date commentdate) {
		this.commentdate = commentdate;
	}
	public Comment() {		
	}
	@Override
	public String toString() {
		return "Comment [commentid=" + commentid + ", content=" + content + ", commentdate=" + commentdate + "]";
	}
	public void setAds(Ads ads) {
		this.ads =ads;
	}
	
	
}
