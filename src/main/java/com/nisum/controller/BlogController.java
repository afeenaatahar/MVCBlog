package com.nisum.controller;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.model.BlogComment;
import com.nisum.model.BlogEntry;
import com.nisum.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	BlogService Service;
	
	@RequestMapping(path="", method=GET)
	public ResponseEntity<List<BlogEntry>> listAll(){
		List<BlogEntry> allEntries = Service.listAll();
		
		return new ResponseEntity<>(allEntries, HttpStatus.OK);
	}
	
	@RequestMapping(path="/hello",method=GET,produces=APPLICATION_JSON_VALUE)
	public String hello(){
		return "Hello World";
	}

	@RequestMapping(path="/testEntry",method=GET,produces=APPLICATION_JSON_VALUE)
	public ResponseEntity<BlogEntry> getTestEntry(){
		BlogEntry entry=new BlogEntry();
		entry.setAuthor("Afeena Atahar");
		entry.setTitle("My Test entry");
		entry.setContent("Hi, this is my new Test Entry based on Spring Framework");
		entry.setId(1);
		return new ResponseEntity<BlogEntry>(entry,HttpStatus.OK);
	}
	
	@RequestMapping(path="/createEntry",method=POST)
	public ResponseEntity<BlogEntry> createNewEntry(@RequestBody BlogEntry entry){
		BlogEntry newEntry=new BlogEntry();
		newEntry=Service.createNewEntry(entry);
		return new ResponseEntity<BlogEntry>(newEntry,HttpStatus.OK );
		
		
	}
	@RequestMapping(path="/deleteEntry",method=POST)
	public ResponseEntity<String> deleteEntry(@RequestBody Map<String,String> payload){
		String title = payload.get("title");
		String message=Service.deleteEntry(title);
		return new ResponseEntity<String>(message,HttpStatus.OK );
		
	
	}
	@RequestMapping(path="/updateEntry",method=POST)
	public ResponseEntity<BlogEntry> updateBlogEntry(@RequestBody Map<String ,String>payload ){
		String oldTitle= payload.get("oldTitle");
		String newTitle= payload.get("newTitle");
		String newAuthor=payload.get("newAuthor");
		String newContent=payload.get("newContent");
		
		
		BlogEntry updatedEntry=Service.updateBlogEntry(oldTitle, newTitle, newAuthor, newContent) ;
		return new ResponseEntity<BlogEntry>(updatedEntry,HttpStatus.OK );
		
	
	}
	
	
	@RequestMapping(path="/listAuthor",method=POST)
	public ResponseEntity<List<BlogEntry>> listByAuthor(@RequestBody Map<String,String> payload){
		String author = payload.get("author");
		List<BlogEntry> authorlist=Service.listAllByAuthor(author);
		return new ResponseEntity<List<BlogEntry>>(authorlist,HttpStatus.OK );
		
	
	}
	
	@RequestMapping(path="/read",method=POST)
	public ResponseEntity<BlogEntry> read(@RequestBody Map<String,String> payload){
		String title = payload.get("title");
		BlogEntry entry2Read=Service.read(title);
		return new ResponseEntity<BlogEntry>(entry2Read,HttpStatus.OK );
			
	}
	@RequestMapping(path="/addcomment",method=POST)
	public ResponseEntity<List<BlogComment>> createNewComment(@RequestBody Map<String,String> payload){
		String title = payload.get("title");
		String commenter=payload.get("commenter");
		String content=payload.get("content");
		
		BlogComment comment=new BlogComment();
		comment.setAuthor(commenter);
		comment.setContent(content);
		BlogEntry entry2Read=Service.read(title);
		List<BlogComment> commentlist=Service.createNewComment(comment, entry2Read);
		return new ResponseEntity<List<BlogComment>>(commentlist,HttpStatus.OK );
			
	}

	@RequestMapping(path="/deletecomment",method=POST)
	public ResponseEntity<String> deleteComment(@RequestBody Map<String,String> payload){
		String title = payload.get("title");
		String author=payload.get("author");
		BlogEntry entry2delComm=Service.read(title);
		String message=Service.deleteComment(author, entry2delComm);
		return new ResponseEntity<String>(message,HttpStatus.OK );
		
	
	}
	@RequestMapping(path="/updatecomment",method=POST)
	public ResponseEntity<BlogComment> updateComment(@RequestBody Map<String ,String>payload ){
		String title= payload.get("title");
		String oldCommenter= payload.get("oldCommenter");
		String newCommenter=payload.get("newCommenter");
		String newCommContent=payload.get("newContent");
		
		BlogEntry entry2UpdateComm=Service.read(title);
		BlogComment comment=Service.updateComm(entry2UpdateComm, oldCommenter, newCommenter, newCommContent);
		return new ResponseEntity<BlogComment>(comment,HttpStatus.OK );
		
	
	}
	
	
	
	
			
	}
	
	
