
/*
 * This Java source file was generated by the Gradle 'init' task.

 */

import org.junit.Test;

import com.nisum.model.BlogComment;
import com.nisum.model.BlogEntry;
import com.nisum.service.BlogService;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;

public class BlogServiceTest {
	private BlogService servEntry;

	List<BlogEntry> bloglist = new LinkedList<BlogEntry>();

	@Before
	public void setup() {
		servEntry = new BlogService();
		
		
		
		BlogEntry entry = new BlogEntry();
		entry.setId(1);
		entry.setTitle("My Blog");
		entry.setAuthor("Afeena");
		entry.setContent("hi,this is my new blog");
		entry.setContent("kindly share my blog");
	
		BlogComment comment=new BlogComment();
		comment.setAuthor("Atahar");
		comment.setContent("it looks good");
		entry.setComment(comment);
		servEntry.createNewEntry(entry);

		entry = new BlogEntry();
		entry.setId(9);
		entry.setTitle("global warming");
		entry.setAuthor("varun");
		entry.setContent("hi,this is about global warming");
		entry.setContent("kindly share my blog");
		 
		BlogComment comment1=new BlogComment();
		comment1.setAuthor("Atahar");
		comment1.setContent("it looks good");
		entry.setComment(comment1);
		entry.setComment(comment1);
		servEntry.createNewEntry(entry);
		
	}

	@Test
	public void shouldCreateNewEntry() throws Exception {
		servEntry = new BlogService();
		BlogEntry entry = new BlogEntry();
		entry.setId(5);
		entry.setTitle("global warming");
		entry.setAuthor("varun");
		entry.setContent("hi,this is about global warming");
		entry.setContent("kindly share my blog");
		entry.setComment(null);

		bloglist = servEntry.listAll();
		for (BlogEntry entry1 : bloglist) {
			if (entry1.getId() == entry.getId()) {
				Assert.assertFalse(true);
			} else {
				if (entry.getTitle().length() > 3 && entry.getTitle().length() < 50) {

					if (entry.getAuthor().length() > 2 && entry.getAuthor().length() < 50) {
						if (entry.getTitle().length() > 10 && entry.getTitle().length() < 50000) {
							BlogEntry newEntry = servEntry.createNewEntry(entry);
							Assert.assertNotNull(newEntry);
						}
					}
				}
				Assert.assertFalse(true);
			}
		}

	}
@Test
	public void shouldDeleteEntry() throws Exception {
		String title = "global warming";
		boolean sure= true;
		BlogEntry entry2del = servEntry.read(title);
		servEntry.deleteEntry(title);
		for (BlogEntry entry : servEntry.listAll()) {
			if (entry2del.getId() == entry.getId()) {
				 sure= false;
			}
		}
		Assert.assertTrue(sure);
	}

	@Test
	public void shouldListAll() throws Exception {

		Assert.assertNotNull(servEntry.listAll());

	}

	@Test
	public void shouldupdateBlogEntry() throws Exception {
		
		String title= "My first Blog entry";
		
		BlogEntry actualEntry = servEntry.read("My Blog");
		BlogEntry updatedEntry = servEntry.updateBlogEntry("My Blog",title, "Afeena","hi,this is my new blog");
		
		Assert.assertEquals(title,updatedEntry.getTitle());
		
		
	}

	@Test

	public void shouldListAllByAuthor() throws Exception {
		String author = "Afeena";
		Assert.assertNotNull(servEntry.listAllByAuthor(author));
	}

	@Test
	public void shouldReadBlog() throws Exception {
		String title="My Blog";
		BlogEntry entry2read=servEntry.read(title);
		Assert.assertNotNull(entry2read);
	}

	/// read option still not working
	@Test
	public void shouldCreateNewComment() throws Exception {
		BlogEntry CommEntry = servEntry.read("My Blog");
		BlogComment comment = new BlogComment();
		comment.setAuthor("Atahar");
		comment.setContent("it looks good");
		Assert.assertNotNull(servEntry.createNewComment(comment, CommEntry));
		comment.setAuthor("Ali");
		comment.setContent("Hey nice!!!!");
		Assert.assertNotNull(servEntry.createNewComment(comment, CommEntry));
	}
@Test
	public void shouldDeleteComment() throws Exception{
		BlogEntry entry2delComm=servEntry.read("My Blog");
	
		
		String str=servEntry.deleteComment("Atahar",entry2delComm);
		Assert.assertEquals(str, "Comment Deleted");
		
	}
@Test
public void shouldUpdateComment() throws Exception{
	BlogEntry entry2UpdateComm=servEntry.read("global warming");
	BlogComment updatedComm=servEntry.updateComm(entry2UpdateComm,"Atahar","Ali","hey nice!!");
	Assert.assertNotEquals("Atahar",updatedComm.getAuthor());
	
}

}