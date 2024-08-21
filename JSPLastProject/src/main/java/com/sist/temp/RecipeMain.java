package com.sist.temp;
import java.io.*;
import java.util.*;
public class RecipeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try
        {
        	FileInputStream fis=new FileInputStream("c:\\webDev\\RECIPEDETAIL.csv");
        	BufferedReader br=new BufferedReader(new InputStreamReader(fis,"UTF-8"));
        	while(true)
        	{
        		String s=br.readLine();
        		if(s==null) break;
        		System.out.println(s);
        	}
        	fis.close();
        	//System.out.println(sb.toString());
        }catch(Exception ex) {}
	}

}
