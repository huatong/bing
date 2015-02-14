package bing;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;


public class BingTest {
	
		String query = "columbia";
		String accountKey = "NzspjS8F9PrxrRVM8NnCbNtVPKuSH9WGA6RC6jIKmAQ";
		String bingUrl = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%27" + query + "%27&$top=10&$format=Atom";
		
		// Default Constructor
		public BingTest() {
			
		}

		// Constructor
		public BingTest(String query, String accountKey){
			this.query = query;
			this.accountKey = accountKey;
		}
		
		

		// Returns the xml texts from bing API as a string
		public String useAPI() throws Exception{
			byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
			String accountKeyEnc = new String(accountKeyBytes);

			URL url = new URL(bingUrl);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
					
			InputStream inputStream = (InputStream) urlConnection.getContent();		
			byte[] contentRaw = new byte[urlConnection.getContentLength()];
			inputStream.read(contentRaw);
			String content = new String(contentRaw);
			
			return content;
		}
			
			
		
		
		

}
