package lucene_assignment2;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexFbis {
	
	public String textContent;
	public String abs;
	public String date1;
	public String docNo;
	public String fContent;
	public String heading;
	
	public static void main(String args[])throws IOException
	{
		
	}

	public void setFiles(String docNo, String textContent, String abs, String date1, String fContent, String heading) throws IOException
	{
		this.docNo = docNo;
		this.textContent = textContent;
		this.abs = abs;
		this.date1 = date1;
		this.fContent = fContent;
		this.heading = heading;
		
		indexing();
	}
	
	
	public void indexing() throws IOException
	{
		String indexPath = "/Users/rahulsatya/Desktop/IndexedFiles/fbis";
		Directory dir = FSDirectory.open(Paths.get(indexPath));
		Analyzer analyzer = new EnglishAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir, iwc);
		Document doc = new Document();
		
		InputStream DocNo = new ByteArrayInputStream(docNo.getBytes(StandardCharsets.UTF_8));
		InputStream TextContent = new ByteArrayInputStream(textContent.getBytes(StandardCharsets.UTF_8));
		InputStream Abs = new ByteArrayInputStream(abs.getBytes(StandardCharsets.UTF_8));
		InputStream Date1 = new ByteArrayInputStream(date1.getBytes(StandardCharsets.UTF_8));
		InputStream FContent = new ByteArrayInputStream(fContent.getBytes(StandardCharsets.UTF_8));
		InputStream Heading = new ByteArrayInputStream(heading.getBytes(StandardCharsets.UTF_8));
		
		doc.add(new StringField("docNo", docNo, Field.Store.YES)); // to retain for later
		doc.add(new TextField("docno", new BufferedReader(new InputStreamReader(DocNo, StandardCharsets.UTF_8))));
		doc.add(new TextField("textcontent", new BufferedReader(new InputStreamReader(TextContent, StandardCharsets.UTF_8))));
		doc.add(new TextField("abs", new BufferedReader(new InputStreamReader(Abs, StandardCharsets.UTF_8))));
		doc.add(new TextField("date", new BufferedReader(new InputStreamReader(Date1, StandardCharsets.UTF_8))));
		doc.add(new TextField("fcontent", new BufferedReader(new InputStreamReader(FContent, StandardCharsets.UTF_8))));
		doc.add(new TextField("heading", new BufferedReader(new InputStreamReader(Heading, StandardCharsets.UTF_8))));
		
		writer.addDocument(doc);
		writer.close();
		System.out.println(docNo + ": Indexed"); 
	    
	}
	
}
