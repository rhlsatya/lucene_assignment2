package lucene_assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.TopDocs;

import java.io.*;

public class BuildQuery {
	
	public static void main(String args[]) throws IOException, ParseException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("/Users/rahulsatya/Downloads/CS7IS3-Assignment2-Topics/")));
		String line;
		int i = 0;
		String queryTitle = "";
		String queryDesc= "";
		String queryNarr = "";
		String queryNum = "";
		String topDocsFr94[][] = new String[2][100];
		int topDocsFbis[][] = new int[2][1000];
		int topDocsLat[][] = new int[2][1000];
		int flag = 0;
		while((line = br.readLine()) != null)
		{
			if(line.startsWith("<top>") && i!=0)
			{
				
				//System.out.println("Sending data");
				SearcherFr94 sf = new SearcherFr94();
				topDocsFr94 = sf.getResults(queryTitle, queryDesc, queryNarr);
				SearcherFbis sfb = new SearcherFbis();
				topDocsFbis = sfb.getResults();
				SearcherLatimes sl = new SearcherLatimes();
				topDocsLat = sl.getResults();
				BuildQuery bq = new BuildQuery();
				bq.sortResults (queryNum, topDocsFr94, topDocsFbis, topDocsLat);
//				 
			}
			else if (line.startsWith("<num>"))
			{
				queryNum = "";
				queryNum = line.substring(14);
				System.out.println(queryNum);
				i++;
			}
			else if (line.startsWith("<title>"))
			{
				queryTitle = "";
				queryTitle = line.substring(7);
				System.out.println(queryTitle);
			}
			
			else if (line.startsWith("<desc>"))
			{
				
				queryDesc = "";
				String line1;
				while ((line1 = br.readLine()) != null)
				{
					if(line1.startsWith("<narr>"))
					{
						flag = 1;
						break;
					}
						
					else
						queryDesc = queryDesc + line1;
				}
				
				System.out.println(queryDesc);
			}
			
			if (line.startsWith("<narr>") || flag == 1)
			{
				
				queryNarr = "";
				String line1;
				while ((line1 = br.readLine()) != null)
				{
					if(line1.startsWith("</top>"))
					{
						flag = 0;
						break;
					}
						
					else
						queryNarr = queryNarr + line1;
				}
				
				System.out.println(queryNarr);
			}
		}
	}
	
	public void sortResults(String queryNum, String topDocsFr94[][], int topDocsFbis[][], int topDocsLat[][])
	{
		System.out.println(queryNum);
		for(int i = 0; i < 100; i++)
		{
			System.out.println(topDocsFr94[i][0] + ": " + topDocsFr94[i][1]);
		}
		
	}
}
