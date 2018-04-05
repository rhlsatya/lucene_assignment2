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

public class IndexFt
{
    String textContent;
    String docNo;
    String headline;
    
    public static void main(String args[])
    {
    	
    }
   
    public void setFiles(String docNo, String headline, String textContent) throws IOException
	{
		this.docNo = docNo;
		this.textContent = textContent;
		this.headline = headline;
		
		/*
		System.out.println(docNo);
		System.out.println(textContent);
		System.out.println(page);
		System.out.println(date);
		System.out.println(profile);
		System.out.println(headline);
		System.out.println(profile);
		*/
		
		indexing();
	}
    
    public void indexing() throws IOException
	{
		String indexPath = "/Users/rahulsatya/Desktop/IndexedFiles/ft";
		Directory dir = FSDirectory.open(Paths.get(indexPath));
		Analyzer analyzer = new EnglishAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir, iwc);
		Document doc = new Document();
		
		InputStream DocNo = new ByteArrayInputStream(docNo.getBytes(StandardCharsets.UTF_8));
		InputStream TextContent = new ByteArrayInputStream(textContent.getBytes(StandardCharsets.UTF_8));
		InputStream Headline = new ByteArrayInputStream(headline.getBytes(StandardCharsets.UTF_8));
		
		doc.add(new StringField("docNo", docNo, Field.Store.YES)); // to retain for later
		doc.add(new TextField("docno", new BufferedReader(new InputStreamReader(DocNo, StandardCharsets.UTF_8))));
		doc.add(new TextField("textcontent", new BufferedReader(new InputStreamReader(TextContent, StandardCharsets.UTF_8))));
		doc.add(new TextField("headline", new BufferedReader(new InputStreamReader(Headline, StandardCharsets.UTF_8))));
		
		writer.addDocument(doc);
		writer.close();
		System.out.println(docNo + ": Indexed"); 
	      
	}

	
}
