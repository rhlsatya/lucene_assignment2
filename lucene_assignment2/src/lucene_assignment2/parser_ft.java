package lucene_assignment2;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class parser_ft 
{
	public static void main(String args[]) throws Exception
	{
		File[] file = new File("/Users/rahulsatya/Downloads/Assignment Two/ft/").listFiles();
		accessFiles(file);
	}
	
	public static void accessFiles(File[] files) throws Exception
	{
		for(File f :files)
		{
			if(f.isDirectory())
			{
				System.out.println("Directory :" + f.getName());
				accessFiles(f.listFiles());
			}
			else
			{
				parseFile(f.getPath());
			}
		}	
	}
	
	public static void parseFile(String path) throws Exception 
	{
		File file = new File(path);
		Document doc = Jsoup.parse(file, "UTF-8", "");

		Elements docs = doc.select("doc");
        
        String docNo;
        String headline;
        String textContent;
        
        
 
        for (Element e: docs)
        {
        		
        		docNo = e.getElementsByTag("docno").text();
        		
        		headline = e.getElementsByTag("headline").text();
        		textContent = e.getElementsByTag("text").text();
        		//heading = h1 + h2 + h3 + h4 + h5 + h6 + h7 + h8;       		
        		IndexFt ft = new IndexFt();
        		ft.setFiles(docNo, headline, textContent);
        }       
	}
	

}
