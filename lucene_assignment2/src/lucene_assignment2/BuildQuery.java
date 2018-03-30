package lucene_assignment2;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.TopDocs;

import java.io.*;


public class BuildQuery {
	
	public static void main(String args[]) throws IOException, ParseException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("/home/ubuntu/lucene_assignment2/lucene_assignment2/CS7IS3-Assignment2-Topics/")));
		String line;
		int i = 0;
		String queryTitle = "";
		String queryDesc= "";
		String queryNarr = "";
		String queryNum = "";
		String topDocsFr94[][] = new String[2][1000];
		String topDocsFbis[][] = new String[2][1000];
		String topDocsLat[][] = new String[2][1000];
		String topDocsFt[][] = new String[2][1000];
		int retDocsFR94 = 0;
		int retDocsFBIS = 0;
		int retDocsLA = 0;
		int retDocsFT = 0;
		int flag = 0;
		File file = new File("/home/ubuntu/lucene_assignment2/lucene_assignment2/results.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		while((line = br.readLine()) != null)
		{
			if(line.startsWith("<top>") && i!=0)
			{
				
				//System.out.println("Sending data");
				queryTitle = queryTitle.replaceAll("/", " ");
				queryDesc = queryDesc.replaceAll("/", " ");
				queryNarr = queryNarr.replaceAll("/", " ");
				SearcherFr94 sf = new SearcherFr94();
				topDocsFr94 = sf.getResults(queryTitle, queryDesc, queryNarr);
				retDocsFR94 = sf.getRetDocs();
				
				SearcherFbis sfb = new SearcherFbis();
				topDocsFbis = sfb.getResults(queryTitle, queryDesc, queryNarr);
				retDocsFBIS = sfb.getRetDocs();
				
				SearcherLatimes sl = new SearcherLatimes();
				topDocsLat = sl.getResults(queryTitle, queryDesc, queryNarr);
				retDocsLA = sl.getRetDocs();
				
				SearcherFt sft = new SearcherFt();
				topDocsFt = sft.getResults(queryTitle, queryDesc, queryNarr);
				retDocsFT = sft.getRetDocs();
				
				BuildQuery bq = new BuildQuery();
				bq.sortResults (writer, queryNum, topDocsFr94, topDocsFbis, topDocsLat, topDocsFt,
						retDocsFR94, retDocsFBIS, retDocsLA, retDocsFT);
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
		
		
		queryTitle = queryTitle.replaceAll("/", " ");
		queryDesc = queryDesc.replaceAll("/", " ");
		queryNarr = queryNarr.replaceAll("/", " ");
		SearcherFr94 sf = new SearcherFr94();
		topDocsFr94 = sf.getResults(queryTitle, queryDesc, queryNarr);
		retDocsFR94 = sf.getRetDocs();
		
		SearcherFbis sfb = new SearcherFbis();
		topDocsFbis = sfb.getResults(queryTitle, queryDesc, queryNarr);
		retDocsFBIS = sfb.getRetDocs();
		
		SearcherLatimes sl = new SearcherLatimes();
		topDocsLat = sl.getResults(queryTitle, queryDesc, queryNarr);
		retDocsLA = sl.getRetDocs();
		
		SearcherFt sft = new SearcherFt();
		topDocsFt = sft.getResults(queryTitle, queryDesc, queryNarr);
		retDocsFT = sft.getRetDocs();
		
		BuildQuery bq = new BuildQuery();
		bq.sortResults (writer, queryNum, topDocsFr94, topDocsFbis, topDocsLat, topDocsFt,
				retDocsFR94, retDocsFBIS, retDocsLA, retDocsFT);
		
		
		writer.close();
		
	}
	
	public void sortResults(BufferedWriter writer, String queryNum, String topDocsFr94[][], String topDocsFbis[][], String topDocsLat[][], String topDocsFt[][],
			int retDocsFR94, int retDocsFBIS, int retDocsLA, int retDocsFT) throws IOException
	{
		System.out.println(queryNum);
		int retDocs = retDocsFR94 + retDocsFBIS + retDocsLA + retDocsFT;
		String tempResults[][] = new String[retDocs][2];
		String finalResults[][] = new String[1000][2];
		double score[] = new double[retDocs];
		int count = 0;

		for(int i = 0; i < retDocsFR94; i++)
		{
			tempResults[count][0] = topDocsFr94[i][0];
			tempResults[count][1] = topDocsFr94[i][1];
			score[count] = Double.parseDouble(topDocsFr94[i][1]);
			//System.out.println(i + " - " + finalResults[i][0] + ": " + finalResults[i][1]);
			count++;
		}
		for(int i = 0; i < retDocsFBIS; i++)
		{
			tempResults[count][0] = topDocsFbis[i][0];
			tempResults[count][1] = topDocsFbis[i][1];
			score[count] = Double.parseDouble(topDocsFbis[i][1]);
			//System.out.println(i + " - " + finalResults[i][0] + ": " + finalResults[i][1]);
			count++;
		}
		for(int i = 0; i < retDocsLA; i++)
		{
			tempResults[count][0] = topDocsLat[i][0];
			tempResults[count][1] = topDocsLat[i][1];
			score[count] = Double.parseDouble(topDocsLat[i][1]);
			//System.out.println(i + " - " + finalResults[i][0] + ": " + finalResults[i][1]);
			count++;
		}
		for(int i = 0; i < retDocsFT; i++)
		{
			tempResults[count][0] = topDocsFt[i][0];
			tempResults[count][1] = topDocsFt[i][1];
			score[count] = Double.parseDouble(topDocsFt[i][1]);
			//System.out.println(count + " - " + tempResults[count][0] + ": " + tempResults[count][1]);
			count++;
		}
		
		
		double temp = 0; 
		String temp1 = "";
		int counter = 0;
		for (int i = 0; i < score.length - 1; i++)  
        {  
            int index = i; 
            
            for (int j = i + 1; j < score.length; j++)
            {  
                if (score[j] > score[index])
                {  
                		
                    index = j;//searching for lowest index  
                }  
            }  
            
            finalResults[counter][0] = tempResults[index][0];
            finalResults[counter][1] = Double.toString(score[index]);
            
            counter++;
            
            writer.write(queryNum + " 0 " + tempResults[index][0] + " " + tempResults[index][1] + " 0" + "\n");
            //System.out.println(queryNum + " " + tempResults[index][0]);
            
            temp = score[index];   
            score[index] = score[i];  
            score[i] = temp;  
            
            temp1 = tempResults[index][0];
            tempResults[index][0] = tempResults[i][0];
            tempResults[i][0] = temp1;
            
            
            
            if(counter == 1000)
            		break;
        }  
		
		
	}
}
