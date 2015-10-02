package no.uio.ifi.models;

import java.io.IOException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	public Crawler(String startUrl){
		this.startUrl = startUrl;
	}
	String startUrl;
	int count = 0;
	int totalCount = 0;
	public static void main(String[] args) throws IOException{
		Crawler myCrawler = new Crawler("https://www.youtube.com");
		myCrawler.crawlPage("https://www.youtube.com");
	}
	
	public void crawlPage(String url){
		try {
			Document webSite = Jsoup.connect(url).get();
			Elements linkedUrls = webSite.select("a[href]");
		//	Elements jsons = webSite.select("categories");
			int randomStop = ThreadLocalRandom.current().nextInt(0, 25);
		//	System.out.println(webSite);
//			crawlPage("https://www.youtube.com/watch?v=" + randomUrlGenerator());  // link.attr("href"));
			System.out.println("Number of urls are: " + linkedUrls.size());
			for(Element link : linkedUrls){	
			//	System.out.println(link);
				if(link.toString().contains("watch?v")){
					count++;
					if(count == randomStop){
		//				System.out.println(link.attr("Meta"));
						String crawlTo = startUrl + link.attr("href");
						crawlPage(crawlTo);
						System.out.println("Crawling "+ crawlTo);
					}
				}
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception");
			e.printStackTrace();
		}
	}
	private String randomUrlGenerator(){
		String alfabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		String randomValue = "";
		for(int i = 0; i<11; i++){
			randomValue +=alfabet.charAt(random.nextInt(alfabet.length()));
		}
	//	System.out.println(randomValue);
		return randomValue;
	}
}
