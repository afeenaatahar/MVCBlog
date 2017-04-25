package com.nisum.model;

public class BlogComment  {


	private String author;
	private String content="";
	private int id;
	
	public int getId(){
		return this.id;
	}
	
public void setId(int newid){
	id=newid;
	
}
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String newCommenterName) {
		author = newCommenterName;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String newComment) {
		content+=newComment;
	}
	public int idGenerate(){
		return id++;
	}
@Override
public String toString() {
	return "Comments [id=" + id+"\n"+ ",  author=" + author+"\n" + ", content=" + content+"\n" + "]";
}
	
}
