package lucene_assignment2;
import java.io.*;
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
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.MultiSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexLatimes {

	public String textContent;
	public String byLine;
	public String correction;
	public String docNo;
	public String headline;
	public String subject;
	public String type;
    
	public static void main(String args[])throws IOException
	{
		
	}
	
	public void setFiles(String docNo, String textContent, String byLine, String correction, String headline, String subject, String type) throws IOException
	{
		this.docNo = docNo;
		this.textContent = textContent;
		this.byLine = byLine;
		this.correction = correction;
		this.headline = headline;
		this.subject = subject;
		this.type = type;
		
		indexing();
	}
	
	
	public void indexing() throws IOException
	{
		String indexPath = "/Users/rahulsatya/Desktop/IndexedFiles/latimes";
		Directory dir = FSDirectory.open(Paths.get(indexPath));
		Analyzer analyzer = new EnglishAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		Similarity similarity = new MultiSimilarity(new Similarity[]{new BM25Similarity(),new ClassicSimilarity()});
		iwc.setSimilarity(similarity);
		IndexWriter writer = new IndexWriter(dir, iwc);
		Document doc = new Document();
		
		InputStream DocNo = new ByteArrayInputStream(docNo.getBytes(StandardCharsets.UTF_8));
		InputStream TextContent = new ByteArrayInputStream(textContent.getBytes(StandardCharsets.UTF_8));
		InputStream ByLine = new ByteArrayInputStream(byLine.getBytes(StandardCharsets.UTF_8));
		InputStream Correction = new ByteArrayInputStream(correction.getBytes(StandardCharsets.UTF_8));
		InputStream Headline = new ByteArrayInputStream(headline.getBytes(StandardCharsets.UTF_8));
		InputStream Subject = new ByteArrayInputStream(subject.getBytes(StandardCharsets.UTF_8));
		InputStream Type = new ByteArrayInputStream(type.getBytes(StandardCharsets.UTF_8));
		
		doc.add(new StringField("docNo", docNo, Field.Store.YES));
		doc.add(new TextField("docno", new BufferedReader(new InputStreamReader(DocNo, StandardCharsets.UTF_8))));
		doc.add(new TextField("textcontent", new BufferedReader(new InputStreamReader(TextContent, StandardCharsets.UTF_8))));
		doc.add(new TextField("byline", new BufferedReader(new InputStreamReader(ByLine, StandardCharsets.UTF_8))));
		doc.add(new TextField("correction", new BufferedReader(new InputStreamReader(Correction, StandardCharsets.UTF_8))));
		doc.add(new TextField("headline", new BufferedReader(new InputStreamReader(Headline, StandardCharsets.UTF_8))));
		doc.add(new TextField("subject", new BufferedReader(new InputStreamReader(Subject, StandardCharsets.UTF_8))));
		doc.add(new TextField("type", new BufferedReader(new InputStreamReader(Type, StandardCharsets.UTF_8))));
		
		writer.addDocument(doc);
		writer.close();
		System.out.println(docNo + ": Indexed"); 
	      
	}
}
