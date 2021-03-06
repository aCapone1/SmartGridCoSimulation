/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution. 
 */

package _ieee14BusWithImport;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import powerflowApi.helper.IeeeSystemFromFile;
import topology.ActorTopology;

public class _ieee14BusTopologyImport {
	
	public static String simulationName = "IEEE14BusWithImport";
	public static Logger log = Logger.getLogger(_ieee14BusTopologyImport.class);
	
	/*
	public static void main(String[] args){
		log.setLevel(Level.TRACE);
		createTopology();
	}
	*/
	
	public static ActorTopology createTopology(){		
		ActorTopology top = new ActorTopology(simulationName);
		ArrayList<String> file = IeeeSystemFromFile.readFile("src/main/resources/ieeeExamples/14Bus/14BusData");		
		IeeeSystemFromFile.createSystemFromFile(top, file);
		return top;
	}	
	
	
	

}
