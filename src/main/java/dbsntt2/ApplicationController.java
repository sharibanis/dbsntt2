package dbsntt2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
	List<Quote> listQuotes = new ArrayList<Quote>();
	public static ZoneOffset zone = ZoneOffset.of("Z");
	
	@PostMapping("/postquote")
	public void postQuote(Quote quote) {
		try {
			listQuotes.add(quote);
			System.out.println("Adding quote: " + quote.symbol);
		} catch (Exception ex) {
			System.out.println("Error adding quote: " + ex.toString());
		}
	}

	@GetMapping("/getquotes")
	public List<String> getQuotes() {
		List<Quote> listNewQuotes = new ArrayList<Quote>();
		List<String> newTickers = new ArrayList<String>();
		HashMap<String, Integer> newCountTickers = new HashMap<String, Integer>();
		int count = 0, max = 5;
		ArrayList<String> topNewTickers = new ArrayList<String>(max);
		long tenMinutes = 600L;
		try {
			LocalDateTime now = LocalDateTime.now();
			//get quotes of last 10 minutes
			for (Quote quote : listQuotes) {
				if ((now.toEpochSecond(zone) - quote.getTimeStamp().toEpochSecond(zone)) <=  tenMinutes) {
					listNewQuotes.add(quote);
					System.out.println("Adding new quotes: " + quote.toString());
				}
			}
			//get tickers of last 10 minutes
			for (Quote quote : listNewQuotes) {
				newTickers.add(quote.getSymbol());
				System.out.println("Adding newTickers : " + quote.getSymbol());
			}
			//get tickers and counts of last 10 minutes
			for (String ticker : newTickers) {
				count = Collections.frequency(newTickers, ticker);
				newCountTickers.put(ticker, count);
				System.out.println("newTickers and count: " + ticker +" : "+count);
			}
			// Sort tickers counts of based on count
			// create a list from elements of HashMap
			List<Map.Entry<String, Integer>> list = 
		               new LinkedList<Map.Entry<String, Integer>>(newCountTickers.entrySet());
			// create Comparator
			Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String,Integer>>() { 
				@Override 
				public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) { 
					Integer v1 = e1.getValue(); 
					Integer v2 = e2.getValue(); 
				return v1.compareTo(v2);
				} 
			};
			// Sort the list in descending order
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() { 
	            public int compare(Map.Entry<String, Integer> o1,  
	                               Map.Entry<String, Integer> o2) { 
	                return (o2.getValue()).compareTo(o1.getValue()); 
	            } 
			});
			// put data from sorted list into hashmap  
			HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
	        for (Map.Entry<String, Integer> entry : list) { 
	            temp.put(entry.getKey(), entry.getValue()); 
	        }
	        
	        TreeMap treeMap = new TreeMap<String, Integer>(Collections.reverseOrder());
			// put the top 5 tickers in the topNewTickers list
        	count = 0;
	        for (Map.Entry<String, Integer> mapElement : temp.entrySet()) {
	        	String key = (String)mapElement.getKey();
	        	Integer value = (Integer)mapElement.getValue();
	        	if (count++ < max) {
	            	topNewTickers.add(key);
	            	//System.out.println("Getting top 5 quotes... "+key + " : " + value); 
	        	}
	        }
	        topNewTickers.trimToSize();
		} catch (Exception ex) {
			System.out.println("Error getting quotes: " + ex.toString());
		}
		return topNewTickers;
	}
}
