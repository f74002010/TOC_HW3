//F74002010
//資訊104
//張恩瑋


import java.io.*;
import java.net.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

public class TocHw3 {
	public static void main(String[] args) throws Exception,IOException,FileNotFoundException
	{
		String web = args[0];
        
		read(web);
		
		try
		{
			if(args.length == 4)
			{			
				JSONArray data = new JSONArray(new JSONTokener(new FileReader(new File("url.json"))));  
			
				String district ;
				String road;
				int year;
				int price;
				int count = 0;
				int sum = 0;
				int average = 0;
				int i;
			 
				for(i = 0; i < data.length(); i++)
				{
					JSONObject obj = data.getJSONObject(i);
					district = obj.getString("鄉鎮市區");
					road = obj.getString("土地區段位置或建物區門牌");
					year = obj.getInt("交易年月");
					price = obj.getInt("總價元");

					if(district.equals(args[1]))
					{
						if(road.indexOf(args[2]) != -1)
						{
							int regyear;
							int valid;
							
							regyear = year/100;
							valid = regyear-Integer.valueOf(args[3]);
							
							if(valid >= 0)
							{
								System.out.print(args[1]);
								System.out.print(" " +  road);
								System.out.printf(" %d" ,year);
								System.out.printf(" %d\n" , price);
								sum = sum+price;
								count++;
							
							}
							
						}
						
					}	
					
				}
				
				average = sum/count;
				System.out.println("Output:");
				System.out.printf("%d" , average);
			 
			}
			else
				System.out.println("Unexpected Argument Numbers!");
			
		}
		catch(IOException e)
		{
			System.out.println("File Not Found!");
			
		}
		
	}
	
	public static void read( String strURL )
	{
		String check = null ;
		
		try
		{    
			URL pageUrl = new URL(strURL );
			BufferedReader bis = new BufferedReader(new InputStreamReader(pageUrl.openStream(), "UTF-8"));
			BufferedWriter bos = new BufferedWriter(new FileWriter("url.json", false));
			
			while ((check = bis.readLine()) != null)
			{
				bos.write(check);        
				
			}
			
			bos.close();
			bis.close();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
        }
		
    }

}
