package com.nisum.web;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nisum.model.BlogComment;
import com.nisum.model.BlogEntry;
import com.nisum.service.BlogService;

@Controller

public class IndexController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("recipient", "Girls!!!");
		return "index";
	}

	@Autowired
	BlogService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		List<BlogEntry> bloglist = service.listAll();
		model.addAttribute("bloglist", bloglist);
		return "blog";
	}

	@RequestMapping(value = "/testEntry", method = RequestMethod.GET)
	public String test(@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "content", required = false) String content, Model model) {

		BlogEntry entry = new BlogEntry();
		entry.setAuthor(author);
		entry.setTitle(title);
		entry.setContent(content);

		BlogEntry newEntry = service.createNewEntry(entry);
		if (newEntry.getId() >= 1) {
			model.addAttribute("id", newEntry.getId());
			model.addAttribute("title", newEntry.getTitle());
			model.addAttribute("author", newEntry.getAuthor());
			model.addAttribute("content", newEntry.getContent());
			model.addAttribute("comments", newEntry.getCommList());
			List<BlogEntry> bloglist = service.listAll();
			model.addAttribute("bloglist", bloglist);
		}

		return "entry";
	}

	@RequestMapping(value = "/delEntry", method = RequestMethod.GET)
	public String deleteEntry(@RequestAttribute(value = "title", required = true) String title, Model model) {
		model.addAttribute("title", title);
		String message = service.deleteEntry(title);

		model.addAttribute("message", message);
		model.addAttribute("bloglist", service.listAll());
		return "delform";
	}

	@RequestMapping(value = "/updateEntry", method = RequestMethod.GET)
	public String updateEntry(@RequestAttribute(value = "oldTitle", required = true) String oldTitle,
			@RequestAttribute(value = "newTitle", required = true) String newTitle,
			@RequestAttribute(value = "newAuthor", required = true) String newAuthor,
			@RequestAttribute(value = "newContent", required = true) String newContent, Model model) {
		model.addAttribute("oldTitle", oldTitle);
		model.addAttribute("newTitle", newTitle);
		model.addAttribute("newAuthor", newAuthor);
		model.addAttribute("newContent", newContent);
		BlogEntry updatedEntry = service.updateBlogEntry(oldTitle, newTitle, newAuthor, newContent);

		model.addAttribute("id", updatedEntry.getId());
		model.addAttribute("author", updatedEntry.getAuthor());
		model.addAttribute("title", updatedEntry.getTitle());
		model.addAttribute("content", updatedEntry.getContent());
		model.addAttribute("bloglist", service.listAll());
		return "formupdate";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam(value = "title", required = true) String title, Model model) {
		BlogEntry entry2read = service.read(title);
		model.addAttribute("id", entry2read.getId());
		model.addAttribute("title", entry2read.getTitle());
		model.addAttribute("author", entry2read.getAuthor());
		model.addAttribute("content", entry2read.getContent());
		model.addAttribute("comments", entry2read.getCommList());
		List<BlogEntry> bloglist = service.listAll();
		model.addAttribute("bloglist", bloglist);
		return "entry";
	}

	@RequestMapping(value = "/addComment", method = RequestMethod.GET)
	public String read(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "commenter", required = true) String commenter,
			@RequestParam(value = "commentContent", required = true) String commentContent, Model model) {
		model.addAttribute("commenter", commenter);
		model.addAttribute("commentContent", commentContent);

		BlogEntry entry = service.read(title);
		BlogComment comment = new BlogComment();
		comment.setAuthor(commenter);
		comment.setContent(commentContent);
		List<BlogComment> comments = service.createNewComment(comment, entry);

		model.addAttribute("comments", comments);
		return "comment";
	}

	@RequestMapping(path = "/delComment", method = RequestMethod.GET)
	public String deleteComment(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "commenter", required = true) String commenter, Model model) {

		BlogEntry entry2delComm = service.read(title);
		String message = service.deleteComment(commenter, entry2delComm);
		model.addAttribute("comments", entry2delComm.getCommList());
		return "comment";

	}

	@RequestMapping(path = "/updateComment", method = RequestMethod.GET)
	public String updateComment(@RequestAttribute(value = "title", required = true) String title,
			@RequestAttribute(value = "oldCommenter", required = true) String oldCommenter,
			@RequestAttribute(value = "newCommenter", required = true) String newCommenter,
			@RequestAttribute(value = "newContent", required = true) String newContent, Model model) {

		BlogEntry entry2UpdateComm = service.read(title);
		BlogComment comment = service.updateComm(entry2UpdateComm, oldCommenter, newCommenter, newContent);
		model.addAttribute("comments", entry2UpdateComm.getCommList());
		return "comment";

	}

	@RequestMapping(path = "/listAuthor", method = RequestMethod.GET)
	public String listByAuthor(@RequestAttribute(value = "author", required = true) String author, Model model) {

		List<BlogEntry> authorlist = service.listAllByAuthor(author);
		model.addAttribute("authorlist", authorlist);
		return "authorlist";

	}

}
