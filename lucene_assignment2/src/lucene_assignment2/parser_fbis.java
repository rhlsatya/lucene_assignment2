package lucene_assignment2;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class parser_fbis {
	public static void main(String... args) throws Exception 
	{
		File[] files = new File("/Users/rahulsatya/Downloads/Assignment Two/fbis/").listFiles();
        accessFiles(files);
        //parseFile("/Users/rahulsatya/Downloads/Assignment Two/fr94/01/fr940104.0");
    }

    	public static void accessFiles(File[] files) throws Exception 
    {
    		for (File file : files) 
    		{
//    			if (file.isDirectory()) 
//    			{
//    				System.out.println("Directory: " + file.getName());
//                accessFiles(file.listFiles());
//             } 
//    			else 
//    			{
    				parseFile(file.getPath());
            
//    			}
    		}
                
    }
    	
    	public static void parseFile(String path) throws Exception 
    	{
    		File file = new File(path);
    		Document doc = Jsoup.parse(file, "UTF-8", "");
    		
            
    		doc.select("au").remove();
        doc.select("fig").remove();
        doc.select("ht").remove();
        doc.select("tr").remove();
        doc.select("txt5").remove();
        
        Elements docs = doc.select("doc");
        
        String textContent;
        String fContent;
        String h1;
        String h2;
        String h3;
        String h4;
        String h5;
        String h6;
        String h7;
        String h8;
        String docNo;
        String date1;
        String abs;
        String heading;
        
        
        
        for (Element e: docs) {
    			//System.out.println(e.getElementsByTag("usdept").text());
        		Document doc1 = Jsoup.parse(e.toString(), "", Parser.htmlParser());
        		docNo = e.getElementsByTag("docno").text();
        		abs = e.getElementsByTag("abs").text();
        		date1 = e.getElementsByTag("date1").text();
        		fContent = e.getElementsByTag("f").text();
        		textContent = e.getElementsByTag("text").text();
        		h1 = e.getElementsByTag("h1").text();
        		h2 = e.getElementsByTag("h2").text();
        		h3 = e.getElementsByTag("h3").text();
        		h4 = e.getElementsByTag("h4").text();
        		h5 = e.getElementsByTag("h5").text();
        		h6 = e.getElementsByTag("h6").text();
        		h7 = e.getElementsByTag("h7").text();
        		h8 = e.getElementsByTag("h8").text();
        		
        		heading = h1 + h2 + h3 + h4 + h5 + h6 + h7 + h8;
        		
        		IndexFbis ifb = new IndexFbis();
        		ifb.setFiles(docNo, textContent, abs, date1, fContent, heading);
        }
        
    	}

}
