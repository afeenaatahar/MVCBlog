package com.nisum.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.nisum.model.BlogComment;
import com.nisum.model.BlogEntry;

public class BlogEntryDAO implements DAOInterface {
	private List<BlogEntry> bloglist = new LinkedList<BlogEntry>();
	private static Integer idGenerator = 1;
	private static Integer id=0;
	BlogComment comment = new BlogComment();
	private List<BlogComment> comments = new LinkedList<BlogComment>();

	public BlogEntry create(BlogEntry newEntry) {

		bloglist.add(newEntry);


		for (BlogEntry entry : bloglist) {
			if (newEntry.equals(entry)) {
				return entry;
			}
		}

		return null;

	}

	public String delete(BlogEntry entry) {// pass the blogEntry
		String str = "";
		for (BlogEntry delEntry : bloglist) {
			if (delEntry.getId() == entry.getId()) {
				bloglist.remove(entry);
				str = "deleted";
				return str;
			}

		}

		return "";
	}

	public BlogEntry update(BlogEntry newEntry) {// update the bloglist
		for (BlogEntry entry : bloglist) {
			if (entry.getId() == newEntry.getId()) {
				entry = newEntry;
				return entry;

			}
		}

		return null;

	}

	public BlogEntry read(int id) {

		for (BlogEntry entry : bloglist) {
			if (entry.getId() == id) {
				return entry;

			}

		}

		return null;
	}

	public List<BlogEntry> listAll() {
		List<BlogEntry> listCopy = new LinkedList<BlogEntry>();
		listCopy.addAll(bloglist);
		return listCopy;
	}

	public List<BlogEntry> searchByAuthor(String author) {
		List<BlogEntry> authorbloglist = new LinkedList<BlogEntry>();
		BlogEntry authorEntry = new BlogEntry();
		for (BlogEntry entry : bloglist) {
			if (entry.getAuthor().equals(author)) {
				authorEntry = entry;
				authorbloglist.add(authorEntry);

			}
		}
		return authorbloglist;
	}

	public BlogEntry searchByTitle(String title) {
		BlogEntry searchEntry = new BlogEntry();

		for (BlogEntry entry : bloglist) {
			if (entry.getTitle().equals(title)) {
				searchEntry = entry;
			return searchEntry;
			}
		}
	return null;
	}

	public List<BlogComment> addComment(BlogComment comment, BlogEntry entry) {
		
		entry.setComment(comment);
		return entry.getCommList();

	}

	public int idGenerate4blog() {
		return idGenerator++;

	}
	public Integer idGenerate4Comment() {
		return id++;

	}

	public Integer giveId(String title) {

		for (BlogEntry entry : bloglist) {
			if (entry.getTitle().equals(title)) {
				return entry.getId();
			}

		}
		return null;

	}

	public BlogComment giveComment(List<BlogComment> commlist, String commenter) {
		for (BlogComment comm : commlist) {
			if (comm.getAuthor().equals(commenter)) {
				return comm;
			}
		}
		return null;
	}

	public String deleteComment(BlogComment comment, List<BlogComment> commlist) {

		if (commlist.remove(comment)) {
			return "Comment Deleted";
			
		}

		return "";
	}

	public BlogComment updateComment(List<BlogComment> commList2updateComm, BlogComment comm2Update,
			String oldCommenter) {
		for (BlogComment comm : commList2updateComm) {
			if (comm.getId() == comm2Update.getId()) {
				comm = comm2Update;
				return comm;
			}
		}
		return null;
	}

}
