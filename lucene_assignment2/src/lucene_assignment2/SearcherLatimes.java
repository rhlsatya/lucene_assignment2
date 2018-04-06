package lucene_assignment2;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.MultiSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.FSDirectory;

public class SearcherLatimes {
	
	public BooleanQuery.Builder booleanQuery;
	public Analyzer analyzer;
	public int retDocs;
	public static void main(String args[]) throws IOException
	{
		
	}
	
	public  String[][] getResults(String queryTitle, String queryDesc, String queryNarr) throws IOException, ParseException
	{
		String index = "/home/ubuntu/lucene_assignment2/lucene_assignment2/IndexedFiles/latimes/";
	    IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
	    IndexSearcher searcher = new IndexSearcher(reader);
	    Similarity similarity = new MultiSimilarity(new Similarity[]{new BM25Similarity(),new ClassicSimilarity()});
	    searcher.setSimilarity(similarity);
	    analyzer = new EnglishAnalyzer();
	    //EnglishAnalyzer analyzer = new EnglishAnalyzer();
	    booleanQuery = new BooleanQuery.Builder();
//	    if(queryTitle.contains("supercritical fluids"))
//	    {
//	    	addQuery(queryTitle, 0);
//	    }
//	    else
//	    {
//	    	addQuery(queryTitle, 1);
//	    }
//	    //addQuery(queryTitle, 1);
//	    addQuery(queryDesc, 0);
//	    addQuery(queryNarr, 0);
	    
		    //-------------------//
	    
	    
	    Map<String, Float> boostFields = new HashMap<String, Float>();
        boostFields.put("heading",20f);
        boostFields.put("subject",50f);
        boostFields.put("textcontent",255f);
        //java lucene_assignment2.BuildQuery
	    //javac SearcherFr94.java SearcherFbis.java SearcherLatimes.java SearcherFt.java BuildQuery.java
        //./trec_eval/trec_eval qrels.assignment2.part1 results.txt
        
      
        MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[]{"heading","subject","textcontent"}, analyzer, boostFields);
        parser.setAllowLeadingWildcard(true);
        
        Query query1 = parser.parse(QueryParser.escape(queryTitle));
		Query query2 = parser.parse(QueryParser.escape(queryDesc));
		Query query3 = parser.parse(QueryParser.escape(queryNarr));
		
		Query boostedTermQuery1 = new BoostQuery(query1, (float) 30.5);
	    Query boostedTermQuery2 = new BoostQuery(query2, 30);
	    Query boostedTermQuery3 = new BoostQuery(query3, (float) 7.5);
	    booleanQuery.add(boostedTermQuery1, Occur.MUST);
	    booleanQuery.add(boostedTermQuery2, Occur.SHOULD);
	    booleanQuery.add(boostedTermQuery3, Occur.SHOULD);
		//0.1063
	   
		    
		//aa
	    
	
	    
	   
	    
	    
	       // TokenStream reader1 = null;
	        //TokenStream stream = analyzer.tokenStream(null, new StringReader("author"));
	    TopDocs docs = searcher.search(booleanQuery.build(), 1000);
	    retDocs = docs.scoreDocs.length;
	    System.out.println ("length of top docs: " + retDocs);
	        
	    String results[][] = new String[retDocs][2];
	        int count = 0;
	    for( ScoreDoc doc : docs.scoreDocs) {
	        Document thisDoc = searcher.doc(doc.doc);
	        results[count][0] = thisDoc.get("docNo");
	        results[count][1] = Float.toString(doc.score);
	        count++;
	            //System.out.println(thisDoc.get("docNo"));
	        //System.out.println(thisDoc.get("docNo") + ": " + doc.score);
	        //writer.write(queryNo + " " + thisDoc.get("index") + "\n");
	    }
	    
	
	reader.close();
  	
		
		
		return results;
		
	}
	
	public void addQuery(String query, int flag) throws ParseException
	{
//		QueryParser parser1 = new QueryParser("byline", analyzer);
//		QueryParser parser2 = new QueryParser("correction", analyzer);
		QueryParser parser3 = new QueryParser("headline", analyzer);
		QueryParser parser4 = new QueryParser("subject", analyzer);
		QueryParser parser5 = new QueryParser("textcontent", analyzer);
//		QueryParser parser6 = new QueryParser("type", analyzer);
		
		    
//		Query query1 = parser1.parse(query);
//		Query query2 = parser2.parse(query);
		Query query3 = parser3.parse(query);
		Query query4 = parser4.parse(query);
		Query query5 = parser5.parse(query); // must
//		Query query6 = parser6.parse(query);
		
		
		
		
//	    Query boostedTermQuery1 = new BoostQuery(query1, (float) 1.5);
//	    Query boostedTermQuery2 = new BoostQuery(query2, 2);
	    Query boostedTermQuery3 = new BoostQuery(query3, (float) 2.5);
	    Query boostedTermQuery4 = new BoostQuery(query4, (float) 6.5);
	    Query boostedTermQuery5 = new BoostQuery(query5, 2);
//	    Query boostedTermQuery6 = new BoostQuery(query6, (float) 1.5);
	    
	    
	    
//	    booleanQuery.add(boostedTermQuery1, Occur.SHOULD);
//	    booleanQuery.add(boostedTermQuery2, Occur.SHOULD);
	    booleanQuery.add(boostedTermQuery3, Occur.SHOULD);
	    booleanQuery.add(boostedTermQuery4, Occur.SHOULD);
//	    booleanQuery.add(boostedTermQuery6, Occur.SHOULD);
	    if(flag == 1)
	    {
	    		booleanQuery.add(boostedTermQuery5, Occur.MUST);
		    
	    }
	    else
	    {
	    		booleanQuery.add(boostedTermQuery5, Occur.SHOULD);
		    
	    }
	    
	    
	}
	
	public int getRetDocs()
	{
		return retDocs;
	}

}
