package com.nisum.dao;
import java.util.List;

import com.nisum.model.BlogComment;
import com.nisum.model.BlogEntry;

public interface DAOInterface {
	BlogEntry create(BlogEntry newEntry);
	String delete(BlogEntry entry);
	BlogEntry update(BlogEntry newEntry);
	 BlogEntry read(int id);
	 List<BlogEntry> listAll() ;
	 List<BlogEntry> searchByAuthor(String author);
	 BlogEntry searchByTitle(String title);
	 List<BlogComment> addComment(BlogComment comment, BlogEntry entry);
	 Integer idGenerate4Comment();
	 Integer giveId(String title);
	 BlogComment giveComment(List<BlogComment> commlist,String commenter);
	 String deleteComment(BlogComment comment,List<BlogComment> commlist);
	 BlogComment updateComment(List<BlogComment> commList2updateComm,BlogComment comm2Update,String oldCommenter);
	int idGenerate4blog();
		

}
