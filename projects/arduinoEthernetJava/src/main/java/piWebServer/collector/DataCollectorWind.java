/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution. 
 */

package piWebServer.collector;

import javax.servlet.http.HttpServletRequest;

import arduinoWindradConnection.ArduinoWindradConnection;

/**  
 * Diese Klasse stellt die Verbindung zum Arduino her. 
 * Sie stellt diese Daten als JSON-String anderen Klassen wieder zur Verfügung.
 * 
 * @author Denis Bytschkow
 */
public class DataCollectorWind implements DataCollectorInterface {
	
	ArduinoWindradConnection connection = new ArduinoWindradConnection();
	
	public DataCollectorWind(){
		new Thread(connection).start();
	}

	/* (non-Javadoc)
	 * @see piWebServer.collector.DataCollectorInterface#generateMessage(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String generateMessage(HttpServletRequest request){	
		String result = "";
		result = getAll();
		
		String path = request.getPathInfo();			
		String query = request.getQueryString();
		
		if (path.contains("status")){
			result = getStatus();
		}
		
		if (query != null && query.contains("wind")){
			
			String controlString = query.replace("wind=", "");			
			double setValue = 0.0;
			try {
				setValue = Double.parseDouble(controlString);
				if (setValue > 1.0) setValue = 1.0;
				if (setValue < 0.0) setValue = 0.0;
							
				connection.setPower(setValue);
				result = getStatus() + "\n\nneuer Sollwert Wind = "+setValue;
				System.out.println("query: " + query + "  --> gefilterter Wert: " +setValue);
								
			} catch (Exception e) {
				result = getFehlerMeldung();
				System.err.println("\nAchtung kann query nicht konvertieren, setze Wert = 0.0\n");				
			}			
			
		}else {
			if (query != null) result = getFehlerMeldung();
		}
			
		return result;
	}
	
	/* (non-Javadoc)
	 * @see piWebServer.collector.DataCollectorInterface#getAll()
	 */
	@Override
	public String getAll(){
		String result = getHeader();
		result += getStatus();
		return result;
	}
	
	/* (non-Javadoc)
	 * @see piWebServer.collector.DataCollectorInterface#getStatus()
	 */
	@Override
	public String getStatus(){
		String result = connection.getMessage(); 
		result = result.replace("windPower", "targetValue");
		result = result.replace("strom", "currentValue");
		return result;
	}

	private String getHeader() {		
		return "Willkommen, Sie rufen die REST API des RaspberryPI auf. \n\n"
				+ "Der RaspberryPI stellt die Verbindung zum entsprechenden Arduino her --> HIER das Windrad. \n\n"
				+ "Die Nutzung:\n"
				+ "192.168.1.162 --> liefert diese Nachricht\n"
				+ "192.168.1.162/status --> liefert den Status als reines JSON\n"
				+ "192.168.1.162/?wind=0.5 --> Query leitet Sollwert an das Windrad weiter. Erlaubte Werte 0.0 ... 1.0\n\n"
				+ "STATUS:\n";
	}
}
