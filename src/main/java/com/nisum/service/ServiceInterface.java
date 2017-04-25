package com.nisum.service;
import java.util.List;

import com.nisum.model.BlogComment;
import com.nisum.model.BlogEntry;

public interface ServiceInterface {
	BlogEntry createNewEntry(BlogEntry entry);
	String deleteEntry(String title);
	List<BlogEntry> listAll();
	BlogEntry updateBlogEntry(String oldTitle,String newTitle, String newAuthor, String newContent);
	List<BlogEntry> listAllByAuthor(String author);

BlogEntry read(String title);
List<BlogComment> createNewComment(BlogComment comment, BlogEntry entry);
String deleteComment(String author,BlogEntry entry2delComm);
BlogComment updateComm(BlogEntry entry2UpdateComm,String oldCommenter, String newCommenter, String newCommContent);

}