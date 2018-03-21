package lucene_assignment2;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
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
import org.apache.lucene.store.FSDirectory;

public class SearcherFbis {
	
	public BooleanQuery.Builder booleanQuery;
	public Analyzer analyzer;
	public static void main(String args[]) throws IOException
	{
		
	}
	
	public  String[][] getResults(String queryTitle, String queryDesc, String queryNarr) throws IOException, ParseException
	{
		String index = "/Users/rahulsatya/Desktop/IndexedFiles/fbis/";
	    String field = "author";
	    String queries = null;
	    int repeat = 0;
	    boolean raw = false;
	    IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
	    IndexSearcher searcher = new IndexSearcher(reader);
	    searcher.setSimilarity(new BM25Similarity());
	    analyzer = new StandardAnalyzer();
	    //EnglishAnalyzer analyzer = new EnglishAnalyzer();
	    booleanQuery = new BooleanQuery.Builder();
	    addQuery(queryTitle, 1);
	    addQuery(queryDesc, 0);
	    addQuery(queryNarr, 0);
	    
		    //-------------------//
		
	    
	
	    
	   
	    
	    String results[][] = new String[100][2];
	       // TokenStream reader1 = null;
	        //TokenStream stream = analyzer.tokenStream(null, new StringReader("author"));
	    TopDocs docs = searcher.search(booleanQuery.build(), 100);
	        System.out.println ("length of top docs: " + docs.scoreDocs.length);
	        int count = 0;
	    for( ScoreDoc doc : docs.scoreDocs) {
	        Document thisDoc = searcher.doc(doc.doc);
	        results[count][0] = thisDoc.get("docNo");
	        results[count][1] = Float.toString(doc.score);
	        count++;
	            //System.out.println(thisDoc.get("docNo"));
	        System.out.println(thisDoc.get("docNo") + ": " + doc.score);
	        //writer.write(queryNo + " " + thisDoc.get("index") + "\n");
	    }
	    
	
	    reader.close();
		return results;
		
		
	}
	public void addQuery(String query, int flag) throws ParseException
	{
		QueryParser parser1 = new QueryParser("heading", analyzer);
		QueryParser parser2 = new QueryParser("abs", analyzer);
		QueryParser parser3 = new QueryParser("date", analyzer);
		QueryParser parser4 = new QueryParser("fcontent", analyzer);
		QueryParser parser5 = new QueryParser("textcontent", analyzer);
		
		    
		Query query1 = parser1.parse(query);
		Query query2 = parser2.parse(query);
		Query query3 = parser3.parse(query);
		Query query4 = parser4.parse(query);
		Query query5 = parser5.parse(query); 
		
		
		
		
	    Query boostedTermQuery1 = new BoostQuery(query1, (float) 6.5);
	    Query boostedTermQuery2 = new BoostQuery(query2, 2);
	    Query boostedTermQuery3 = new BoostQuery(query3, (float) 1.5);
	    Query boostedTermQuery4 = new BoostQuery(query4, (float) 1.5);
	    Query boostedTermQuery5 = new BoostQuery(query5, 5);
	    
	    
	    
	    booleanQuery.add(boostedTermQuery1, Occur.SHOULD);
	    booleanQuery.add(boostedTermQuery2, Occur.SHOULD);
	    booleanQuery.add(boostedTermQuery3, Occur.SHOULD);
	    booleanQuery.add(boostedTermQuery4, Occur.SHOULD);
	    
	    if(flag == 1)
	    {
	    		booleanQuery.add(boostedTermQuery5, Occur.MUST);
		    
	    }
	    else
	    {
	    		booleanQuery.add(boostedTermQuery5, Occur.SHOULD);
		    
	    }
	    
	    
	}

}
