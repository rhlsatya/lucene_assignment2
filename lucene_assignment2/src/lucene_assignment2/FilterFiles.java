package lucene_assignment2;
import java.io.*;
public class FilterFiles 
{
	public static void main(String args[]) throws IOException
	{
		//File folder = new File("/Users/rahulsatya/Downloads/AssignmentTwo/ft");
		File folder = new File("/Users/rahulsatya/Downloads/Assignment Two/fr94");
		
		FilterFiles ff = new FilterFiles();
		ff.getFilesFromFolder(folder, "/fr94/");
		
	}
	
	public void getFilesFromFolder(final File folder, String folderName) throws IOException {
	    for (final File fileEntry : folder.listFiles()) {
	    		if (fileEntry.isDirectory()) {
	        	
	            getFilesFromFolder(fileEntry, (folderName + fileEntry.getName() + "/"));
	        } else {
	            String s = fileEntry.getName();
	            if(!s.equals(".DS_Store"))
	            {
	            		//System.out.println(s);
	            	
	            	
	///////////////// ---- REMOVE COMMENT BELOW IF THE FUNCTION IS CHANGED. REMOVE BEFORE SUBMISSION!!! ------- ////////////////
	            	
	            	
	            		//removeData(fileEntry, folderName );
	            		extractFields(fileEntry.getName(), folderName);
	            }

	        }
	    }
	}
	
	public void extractFields(String fileName, String folderName) throws IOException
	{
		//System.out.println("/Users/rahulsatya/eclipse-workspace/lucene_assignment2/removed_data/" + folderName + fileName);
		File fileEntry = new File("/Users/rahulsatya/eclipse-workspace/lucene_assignment2/removed_data/" + folderName + fileName);
		BufferedReader br = new BufferedReader(new FileReader(fileEntry));
		String line;
		String docNo = "";
		String agency = "";
		while((line = br.readLine()) != null)
		{
			if(line.contains("<DOC>"))
			{
				String line1 = br.readLine();
				while((line1 = br.readLine()) != null)
				{
					if(line1.contains("</DOC>"))
						break;
					else if(line1.contains("<DOCNO>"))
					{
						int i = 0;
						if(line1.contains("</DOCNO>"))
						{
							docNo = line1.substring(line1.indexOf("<DOCNO>") + 7, line1.indexOf("</DOCNO>"));
							i = 1;
						}
						else
						{
							docNo = line1.substring(line1.indexOf("<DOCNO>") + 7);
						}
						String sample;
						while((sample = br.readLine()) != null && i == 0)
						{
							
							if(sample.contains("</DOCNO>"))
							{
								i++;
								docNo = docNo + sample.substring(0, sample.indexOf("</DOCNO>"));
								break;
							}
							else
							{
								docNo = docNo + sample;
							}
						}
					}
						
				}
				//------IndexDocument id = new IndexDocument();
				//------id.printFields(docNo);//, agency);
			}
		}
	}
	
	
	
	public void removeData(File fileEntry, String folderName) throws IOException
	{
		
		new File("/Users/rahulsatya/eclipse-workspace/lucene_assignment2/removed_data/" + folderName).mkdirs();
		
		File newFile = new File("/Users/rahulsatya/eclipse-workspace/lucene_assignment2/removed_data/" + folderName + fileEntry.getName());
		BufferedReader br = new BufferedReader(new FileReader(fileEntry));
		BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
		String line;
		//System.out.println(fileEntry.getName() + ": " + folderName);
		
		while((line = br.readLine()) != null)
		{
			if(!line.startsWith("<!-- PJG"))
			{
				line = line.replaceAll("&blank;/", " ");
				line = line.replaceAll("/&blank;", " ");
				line = line.replaceAll("&blank;", " ");
				line = line.replaceAll("&hyph;", "-");
				line = line.replaceAll("&sect;", " ");
				line = line.replaceAll("&para;", " ");
				line = line.replaceAll("&cir;", " ");
				line = line.replaceAll("&rsquo;", "'");
				line = line.replaceAll("&mu;", " u ");
				line = line.replaceAll("&times;", " x ");
				line = line.replaceAll("&bull;", " ");
				line = line.replaceAll("&ge;", ">=");
				line = line.replaceAll("&reg;", " ");
				line = line.replaceAll("&cent;", "\\$");
				line = line.replaceAll("&amp;", "&");
				line = line.replaceAll("&gt;", ">");
				line = line.replaceAll("&lt;", "<");
				
				
//				if(line.contains("<TABLE>"))
//				{
//					int i = 0;
//					if(line.indexOf("<TABLE>") != 0)
//					{
//						bw.write(line.substring(0, line.indexOf("<TABLE>")) + "\n");
//					}
//					if(line.contains("</TABLE>"))
//					{
//						bw.write(line.substring((line.indexOf("</TABLE>") + 8)) + "\n");
//						i++;
//					}
//					
//					
//					while(i == 0)
//					{
//						String sample = br.readLine();
//						if(sample.contains("</TABLE>"))
//						{
//							i++;
//							bw.write(line.substring((line.indexOf("</TABLE>") + 8)) + "\n");
//						}
//					}
//				}
				if(line.contains("<TABLE>"))
				{
					int i = 0;
					if(line.contains("</TABLE>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</TABLE>"))
							i++;
					}
				}
				else if(line.contains("<IMPORT>"))
				{
					int i = 0;
					if(line.contains("</IMPORT>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</IMPORT>"))
							i++;
					}
				}
				
				else if(line.contains("<FOOTNOTE>"))
				{
					int i = 0;
					if(line.contains("</FOOTNOTE>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</FOOTNOTE>"))
							i++;
					}
				}
				
				else if(line.contains("<FOOTNAME>"))
				{
					int i = 0;
					if(line.contains("</FOOTNAME>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</FOOTNAME>"))
							i++;
					}
				}
				else if(line.contains("<FOOTCITE>"))
				{
					int i = 0;
					if(line.contains("</FOOTCITE>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</FOOTCITE>"))
							i++;
					}
				}
				else if(line.contains("<ADDRESS>"))
				{
					int i = 0;
					if(line.contains("</ADDRESS>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</ADDRESS>"))
							i++;
					}
				}
				else if(line.contains("<FURTHER>"))
				{
					int i = 0;
					if(line.contains("</FURTHER>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</FURTHER>"))
							i++;
					}
				}
				else if(line.contains("<BILLING>"))
				{
					int i = 0;
					if(line.contains("</BILLING>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</BILLING>"))
							i++;
					}
				}
				else if(line.contains("<FRFILING>"))
				{
					int i = 0;
					if(line.contains("</FRFILING>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</FRFILING>"))
							i++;
					}
				}
				else if(line.contains("<DATE>"))
				{
					int i = 0;
					if(line.contains("</DATE>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</DATE>"))
							i++;
					}
				}
				else if(line.contains("<CFRNO>"))
				{
					int i = 0;
					if(line.contains("</CFRNO>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</CFRNO>"))
							i++;
					}
				}
				else if(line.contains("<RINDOCK>"))
				{
					int i = 0;
					if(line.contains("</RINDOCK>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</RINDOCK>"))
							i++;
					}
				}
				else if(line.contains("<PARENT>"))
				{
					int i = 0;
					if(line.contains("</PARENT>"))
						i = 1;
					while(i == 0)
					{
						String sample = br.readLine();
						if(sample.contains("</PARENT>"))
							i++;
					}
				}
				
//				else if(line.contains("<FOOTCITE>"))
//				{
//					String sample = br.readLine();	
//				}
				else
					bw.write(line + "\n");
			}
		}
		
	}
	
	
	
	
}
