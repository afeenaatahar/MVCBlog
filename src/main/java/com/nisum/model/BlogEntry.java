package com.nisum.model;

import java.util.LinkedList;
import java.util.List;

public class BlogEntry {

	private int id;
	private String title;
	private String author;
	private String content="";
	private List<BlogComment> comments = new LinkedList<BlogComment>();

	public int getId() {
		return this.id;
	}

	public void setId(int newId) {
		id = newId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String newAuthor) {
		author = newAuthor;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String newContent) {
		content+= newContent;
		
	}

	public void setComment(BlogComment comment) {
		comments.add(comment);
		
	}
	 public List<BlogComment> getCommList(){
		 return comments;
	 }

	@Override
	public String toString() {
		return "BlogEntry [id=" + id +"\n"+ ", title=" + title+"\n" + ", author=" + author+"\n" + ", content=" + content + "\n"+",comments="+"\n"+comments+"]";
	}
		


	
	

}
