package lucene_assignment2;

import java.io.File;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class parser_fr94 {

        public static void main(String... args) throws Exception {
            File[] files = new File("/Users/rahulsatya/Downloads/Assignment Two/fr94/").listFiles();
            accessFiles(files);
            //parseFile("/Users/rahulsatya/Downloads/Assignment Two/fr94/01/fr940104.0");
        }

        public static void accessFiles(File[] files) throws Exception {

                    for (File file : files) {
                        if (file.isDirectory()) {
                            System.out.println("Directory: " + file.getName());
                            accessFiles(file.listFiles()); // Calls same method again.
                        } else {
                            try {
                                                parseFile(file.getPath());
                                        } catch (Exception e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                        }
                        }
                    }
                    
                }

        public static void parseFile(String path) throws Exception {

        	//System.out.println(path);
                File file = new File(path);
                Document doc = Jsoup.parse(file, "UTF-8", "");
                doc.select("footname").remove();
                doc.select("footnote").remove();
                doc.select("parent").remove();
                doc.select("rindock").remove();
                doc.select("cfrno").remove();
                doc.select("date").remove();
                doc.select("frfiling").remove();
                doc.select("billing").remove();
                doc.select("further").remove();
                doc.select("address").remove();
                doc.select("footcite").remove();
                doc.select("import").remove();
                doc.select("table").remove();
                doc.select("signer").remove();
                doc.select("signjob").remove();
                doc.select("action").remove();
                
                Elements docs = doc.select("doc");
                //System.out.println(docs.size());
                
                String docNo[] = new String[docs.size()];
                String usDept[] = new String[docs.size()];
                String agency[] = new String[docs.size()];
                String usBureau[] = new String[docs.size()];
                String docTitle[] = new String[docs.size()];
                String summary[] = new String[docs.size()];
                String supplem[] = new String[docs.size()];
                String textOther[] = new String[docs.size()];
                IndexFr94 id[] = new IndexFr94[docs.size()];
                Elements text = doc.select("text");
                int i = 0;
                for (Element e: docs) {
                		//System.out.println(e.getElementsByTag("docno").text());
                		docNo[i] = e.getElementsByTag("docno").text();
                		id[i] = new IndexFr94();
                		id[i].setDocNo(docNo[i]);
                		i++;
                		
                
                }
                i = 0;
                for (Element e: text) {
            			//System.out.println(e.getElementsByTag("usdept").text());
            			usDept[i] = e.getElementsByTag("usdept").text();
            			agency[i] = e.getElementsByTag("agency").text();
            			usBureau[i] = e.getElementsByTag("usbureau").text();
            			docTitle[i] = e.getElementsByTag("doctitle").text();
            			summary[i] = e.getElementsByTag("summary").text();
            			supplem[i] = e.getElementsByTag("supplem").text();
            			id[i].setOther(usDept[i], agency[i], usBureau[i], docTitle[i], summary[i], supplem[i]);
            			i++;
                }
                
                doc.select("usdept").remove();
                doc.select("agency").remove();
                doc.select("usbureas").remove();
                doc.select("doctitle").remove();
                doc.select("summary").remove();
                doc.select("supplem").remove();
                i = 0;
                for (Element e: text) {
            		//System.out.println(e.getElementsByTag("docno").text());
	            		textOther[i] = e.getElementsByTag("text").text();
	            		id[i].setOtherText(textOther[i]);
	            		i++;
                }

            }
      }