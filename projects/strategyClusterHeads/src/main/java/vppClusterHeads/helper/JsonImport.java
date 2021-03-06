/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */

package vppClusterHeads.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

/**
 * @author Denis Bytschkow
 *
 */
public class JsonImport {
	
	public JsonElement root;
	
	public JsonImport(String string){
		Gson gson = new Gson();
		
		String json = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader(string))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        json = sb.toString();
	    } catch (IOException e) {
			e.printStackTrace();
		} 
		
		JsonElement result = gson.fromJson(json, JsonElement.class);		
		root = result;		
	}
	
	
	public JsonElement getRoot(){
		return root;
	}
}
