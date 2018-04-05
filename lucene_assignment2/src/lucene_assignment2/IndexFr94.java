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

public class IndexFr94 {
	
	public String docNo;
	String usDept;
	String agency;
	String usBureau;
	String docTitle;
	String summary;
	String textOther;
	String supplem;
	public static void main (String args[])
	{
		//IndexDocument id = new IndexDocument();
		//id.setDocNo("4");
		//System.out.println(docNo);
	}
	
	public void printFields(String docNo)//, String agency)
	{
		System.out.println(docNo);
	}
	public void setDocNo(String docNo)
	{
		this.docNo = docNo;
		//System.out.println("hh: " + this.docNo);
	}
	public void setOther(String usDept, String agency,String usBureau,String docTitle,String summary, String supplem)
	{
		this.usDept = usDept;
		this.agency = agency;
		this.usBureau = usBureau;
		this.docTitle = docTitle;
		this.summary = summary;
		this.supplem = supplem;
		//System.out.println("hh: " + this.usDept);
	}
	
	public void setOtherText (String textOther) throws IOException
	{
		this.textOther = textOther;
		//System.out.println("hh: " + this.textOther);
		indexing();
	}
	
	public void indexing() throws IOException
	{
		String indexPath = "/Users/rahulsatya/Desktop/IndexedFiles/fr94/";
		Directory dir = FSDirectory.open(Paths.get(indexPath));
		Analyzer analyzer = new EnglishAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir, iwc);
		Document doc = new Document();
		
		
		InputStream DocNo = new ByteArrayInputStream(docNo.getBytes(StandardCharsets.UTF_8));
		InputStream UsDept = new ByteArrayInputStream(usDept.getBytes(StandardCharsets.UTF_8));
		InputStream Agency = new ByteArrayInputStream(agency.getBytes(StandardCharsets.UTF_8));
		InputStream UsBureau = new ByteArrayInputStream(usBureau.getBytes(StandardCharsets.UTF_8));
		InputStream DocTitle = new ByteArrayInputStream(docTitle.getBytes(StandardCharsets.UTF_8));
		InputStream Summary = new ByteArrayInputStream(summary.getBytes(StandardCharsets.UTF_8));
		InputStream Supplem = new ByteArrayInputStream(supplem.getBytes(StandardCharsets.UTF_8));
		InputStream TextOther = new ByteArrayInputStream(textOther.getBytes(StandardCharsets.UTF_8));
		
		doc.add(new StringField("docNo", docNo, Field.Store.YES));
		//doc.add(new TextField("docno", new BufferedReader(new InputStreamReader(DocNo, StandardCharsets.UTF_8))));
		doc.add(new TextField("usdept", new BufferedReader(new InputStreamReader(UsDept, StandardCharsets.UTF_8))));
		doc.add(new TextField("agency", new BufferedReader(new InputStreamReader(Agency, StandardCharsets.UTF_8))));
		doc.add(new TextField("usbureau", new BufferedReader(new InputStreamReader(UsBureau, StandardCharsets.UTF_8))));
		doc.add(new TextField("dotitle", new BufferedReader(new InputStreamReader(DocTitle, StandardCharsets.UTF_8))));
		doc.add(new TextField("summary", new BufferedReader(new InputStreamReader(Summary, StandardCharsets.UTF_8))));
		doc.add(new TextField("supplem", new BufferedReader(new InputStreamReader(Supplem, StandardCharsets.UTF_8))));
		doc.add(new TextField("textother", new BufferedReader(new InputStreamReader(TextOther, StandardCharsets.UTF_8))));
		
		writer.addDocument(doc);
		writer.close();
		System.out.println(docNo + ": Indexed"); 
	      
	}

}




//Checking contents of text tag
//if(line1.contains("<TEXT>"))
//{
//	String line2;
//	while((line2 = br.readLine()) != null)
//	{
//		if(line2.contains("</TEXT>"))
//			break;
//		else if(line2.contains("<AGENCY>"))
//		{
//			int i = 0;
//			if(line2.contains("</AGENCY>"))
//			{
//				agency = line2.substring(line2.indexOf("<AGENCY>") + 8, line1.indexOf("</AGENCY>"));
//				i = 1;
//			}
//			else
//			{
//				if(line2.length() > 8)
//					agency = line1.substring(line1.indexOf("<AGENCY>") + 8);
//			}
//			String sample;
//			while((sample = br.readLine()) != null && i == 0)
//			{
//				
//				if(sample.contains("</AGENCY>"))
//				{
//					i++;
//					agency = agency + sample.substring(0, sample.indexOf("</AGENCY>"));
//					break;
//				}
//				else
//				{
//					agency = agency + sample;
//				}
//			}
//		}
//	}
//}