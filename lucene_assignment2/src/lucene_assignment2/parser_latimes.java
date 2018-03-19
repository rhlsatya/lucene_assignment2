package lucene_assignment2;

import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parser_latimes {
	
	public static void main(String... args) throws Exception 
	{
		File[] files = new File("/Users/rahulsatya/Downloads/Assignment Two/latimes/").listFiles();
        accessFiles(files);
        //parseFile("/Users/rahulsatya/Downloads/Assignment Two/fr94/01/fr940104.0");
    }

    	public static void accessFiles(File[] files) throws Exception 
    {
    		for (File file : files) 
    		{
    			if (file.isDirectory()) 
    			{
    				System.out.println("Directory: " + file.getName());
                accessFiles(file.listFiles());
             } 
    			else 
    			{
    				parseFile(file.getPath());
            
    			}
    		}
                
    }
    	public static void parseFile(String path) throws Exception 
    	{
    		File file = new File(path);
    		Document doc = Jsoup.parse(file, "UTF-8", "");
        doc.select("docid").remove();
        doc.select("tablerow").remove();
        doc.select("table").remove();
        doc.select("tablecell").remove();
        doc.select("rowrule").remove();
        doc.select("cellrule").remove();
        doc.select("section").remove();
        doc.select("length").remove();
        doc.select("graphic").remove();
        doc.select("docid").remove();
        doc.select("dateline").remove();
        doc.select("date").remove();
        doc.select("correction-date").remove();
        
        Elements docs = doc.select("doc");
        //System.out.println(docs.size());
        
        String textContent;
        String byLine;
        String correction;
        String docNo;
        String headline;
        String subject;
        String type;
        
        
        for (Element e: docs) {
    			//System.out.println(e.getElementsByTag("usdept").text());
        		docNo = e.getElementsByTag("docno").text();
        		textContent = e.getElementsByTag("text").text();
        		byLine = e.getElementsByTag("byline").text();
        		correction = e.getElementsByTag("correction").text();
        		headline = e.getElementsByTag("headline").text();
        		subject = e.getElementsByTag("subject").text();
        		type = e.getElementsByTag("type").text();
    			//System.out.println(docNo + ":   " + textContent);
        		IndexLatimes il = new IndexLatimes();
        		il.setFiles(docNo, textContent, byLine, correction, headline, subject, type);
        		
        				
        }
    	}
                
}
