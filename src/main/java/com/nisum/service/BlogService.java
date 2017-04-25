package com.nisum.service;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.nisum.dao.BlogEntryDAO;
import com.nisum.dao.DAOInterface;
import com.nisum.model.BlogComment;
import com.nisum.model.BlogEntry;

@Service
public class BlogService implements ServiceInterface {
	private DAOInterface entryDAO = new BlogEntryDAO();

	public BlogEntry createNewEntry(BlogEntry entry) {
		BlogEntry newEntry=new BlogEntry();
		
		if(entry.getAuthor()!=null){
			if(entry.getTitle().length()>2&& entry.getContent().length()>5){
	    entry.setId(entryDAO.idGenerate4blog());
	     newEntry=entryDAO.create(entry);
		}
		}
	
		
		if(newEntry!=null){
			return newEntry;
		}

		return null;

	}

	public String deleteEntry(String title) {

		if (entryDAO.delete(entryDAO.searchByTitle(title))!="") {
			return "deleted";
		}
		return "Not Deleted";
	}

	public List<BlogEntry> listAll() {
		return entryDAO.listAll();

	}

	public BlogEntry updateBlogEntry(String oldTitle,String newTitle, String newAuthor, String newContent) {

		BlogEntry entry2Update = entryDAO.read(entryDAO.giveId(oldTitle));
		if(entry2Update!=null){
		
		if(!("").equals(newTitle)){
			entry2Update.setTitle(newTitle);
		}

		if(!("").equals(newAuthor)){

		entry2Update.setAuthor(newAuthor);
		}
		
if(!("").equals(newContent)){
		entry2Update.setContent(newContent);
}

		return entryDAO.update(entry2Update);
		}
		return null;

	}

	public List<BlogEntry> listAllByAuthor(String author) {

		List<BlogEntry> authorbloglist = new LinkedList<BlogEntry>();
		authorbloglist=entryDAO.searchByAuthor(author);
		return authorbloglist;
	}

	public BlogEntry read(String title) {
		
		return entryDAO.read(entryDAO.giveId(title));
	}

	public List<BlogComment> createNewComment(BlogComment comment, BlogEntry entry) {
		comment.setId(entryDAO.idGenerate4Comment());

		return entryDAO.addComment(comment, entry);

	}

	

public String deleteComment(String author,BlogEntry entry2delComm){
	List<BlogComment> commList=entry2delComm.getCommList();
	BlogComment comm2Del=entryDAO.giveComment(commList,author);
	return entryDAO.deleteComment(comm2Del,commList);
	
		}

public BlogComment updateComm(BlogEntry entry2UpdateComm,String oldCommenter, String newCommenter, String newCommContent) {

List<BlogComment>commList2updateComm=entry2UpdateComm.getCommList();
BlogComment comm2Update=entryDAO.giveComment(commList2updateComm, oldCommenter);
	
	if(!("").equals(newCommenter)){

	comm2Update.setAuthor(newCommenter);
	}
	
if(!("").equals(newCommContent)){
	comm2Update.setContent(newCommContent);
}

	return entryDAO.updateComment(commList2updateComm,comm2Update,oldCommenter);

}
}
