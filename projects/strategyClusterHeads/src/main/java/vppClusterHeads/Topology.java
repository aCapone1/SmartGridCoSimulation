/*
 * Copyright (c) 2011-2015, fortiss GmbH.
 * Licensed under the Apache License, Version 2.0.
 *
 * Use, modification and distribution are subject to the terms specified
 * in the accompanying license file LICENSE.txt located at the root directory
 * of this software distribution.
 */

package vppClusterHeads;

import topology.ActorTopology;
import vppClusterHeads.helper.JsonElement;
import vppClusterHeads.helper.JsonImport;

/**
 * 
 * This is the topology for the vpp simulation
 * 
 * @author bytschkow
 *
 */

public class Topology {
	
	/**
	 * ACHTUNG Name der Simulation muss ohne Leerzeichen sein! (wg. AKKA)
	 */
	public static String simulationName = "ClusterHeads";
	
	public static ActorTopology createTopology(){
		ActorTopology top = new ActorTopology(simulationName);
		
		/*
		 *  Actor Topology
		 */
		String root = "DEMS";
		top.addActor(root, ClusterHeadActorFactory.createDEMSActor());
		
		// CLusterHEADExternal
		String clusterExternal = root + "/DEMO-Tisch";
		top.addActorAsChild(clusterExternal, ClusterHeadActorFactory.createExternalClusterHeadActor());
		top.addActorAsChild(clusterExternal + "/SolarFORTISS", ClusterHeadActorFactory.createEmptyActor());
		top.addActorAsChild(clusterExternal + "/SolarDEMO", ClusterHeadActorFactory.createEmptyActor());
		top.addActorAsChild(clusterExternal + "/WindRadDEMO", ClusterHeadActorFactory.createEmptyActor());
		top.addActorAsChild(clusterExternal + "/BHKWDEMO", ClusterHeadActorFactory.createEmptyActor()); 	
		
		/*****************************************
		 *  CLusterHEAD1 hat:  
		 *  
		 *  - x Solar Anlagen
		 *  - x WasserKraftwerke
		 *  - x BioGas
		 *  - x BioMasse
		 *  - x Wind
		 *  
		 *****************************************/
		
		String cluster1  = root + "/Cluster-1";
		top.addActorAsChild(cluster1, ClusterHeadActorFactory.createClusterHeadActor());	
				
		cluster1 = cluster1 + "/";

		
		top.addActorAsChild(cluster1 + "DachSWMZentrale", ClusterHeadActorFactory.createSolarActor(120.55));		
		
		top.addActorAsChild(cluster1 + "DachMTZ", ClusterHeadActorFactory.createSolarActor(66.85)); 
		top.addActorAsChild(cluster1 + "DachIsar2", ClusterHeadActorFactory.createSolarActor(17.4)); 
		top.addActorAsChild(cluster1 + "DachMoosburgBetriebshof", ClusterHeadActorFactory.createSolarActor(25.5));
		top.addActorAsChild(cluster1 + "DachSchulungszentrum", ClusterHeadActorFactory.createSolarActor(20.58));
		top.addActorAsChild(cluster1 + "GrosseSolaranlage1", ClusterHeadActorFactory.createSolarActor(1000.));
		top.addActorAsChild(cluster1 + "Leitzachwerk3", ClusterHeadActorFactory.createWaterActor(380.));
		top.addActorAsChild(cluster1 + "Maxwerk", ClusterHeadActorFactory.createWaterActor(500.));
		top.addActorAsChild(cluster1 + "Stadtbachstufe", ClusterHeadActorFactory.createWaterActor(50.));
		top.addActorAsChild(cluster1 + "KleinwasserKWSempt", ClusterHeadActorFactory.createWaterActor(50.));
		top.addActorAsChild(cluster1 + "KleinwasserKWHammer", ClusterHeadActorFactory.createWaterActor(38.));
		top.addActorAsChild(cluster1 + "WindFroettmaning", ClusterHeadActorFactory.createWindActor(2300.0));
		top.addActorAsChild(cluster1 + "WindAnlage2", ClusterHeadActorFactory.createWindActor(4000.0));
		top.addActorAsChild(cluster1 + "BioGasTierparkHellabrunn", ClusterHeadActorFactory.createBioGasActor(300.0));
		top.addActorAsChild(cluster1 + "BioGasMichaelibad", ClusterHeadActorFactory.createBioGasActor(350.0));
		top.addActorAsChild(cluster1 + "BioGas1", ClusterHeadActorFactory.createBioGasActor(700.0));
		top.addActorAsChild(cluster1 + "BioMass1", ClusterHeadActorFactory.createBioMassActor(6500.0));
		top.addActorAsChild(cluster1 + "BioMass2", ClusterHeadActorFactory.createBioMassActor(3500.0));		
		top.addActorAsChild(cluster1 + "BioMass3", ClusterHeadActorFactory.createBioMassActor(8500.0));
		
		
		/*****************************************
		 *  CLusterHEAD2 hat:  
		 *  
		 *  - 4 Solar Anlagen
		 *  - x WasserKraftwerke
		 *  - x BioGas
		 *  - x BioMasse
		 *  - x Wind
		 *  
		 *****************************************/
		
		
		String cluster2 = root + "/Cluster-2";
		top.addActorAsChild(cluster2, ClusterHeadActorFactory.createClusterHeadActor());
		
		cluster2 = cluster2 +"/";
		
		top.addActorAsChild(cluster2 + "DachGEWOFAG", ClusterHeadActorFactory.createSolarActor(37.00));
		top.addActorAsChild(cluster2 + "FassadeHeizwerkFreiham", ClusterHeadActorFactory.createSolarActor(32.50));
		top.addActorAsChild(cluster2 + "DachGewerbehofGiesing", ClusterHeadActorFactory.createSolarActor(48.60));
		top.addActorAsChild(cluster2 + "DachMesseRiemParkhaus", ClusterHeadActorFactory.createSolarActor(57.75));
		top.addActorAsChild(cluster2 + "DachRiemersee", ClusterHeadActorFactory.createSolarActor(10.92));
		top.addActorAsChild(cluster2 + "DachHansJensenWeg10", ClusterHeadActorFactory.createSolarActor(45.50));
		top.addActorAsChild(cluster2 + "DachPasingerFabrik", ClusterHeadActorFactory.createSolarActor(6.34));
		top.addActorAsChild(cluster2 + "DachTramWerkstaette", ClusterHeadActorFactory.createSolarActor(89.86));
		top.addActorAsChild(cluster2 + "DachMGS", ClusterHeadActorFactory.createSolarActor(29.0));
		top.addActorAsChild(cluster2 + "DachU-BahnhofNeuperlachSued", ClusterHeadActorFactory.createSolarActor(3.18));
		top.addActorAsChild(cluster2 + "DachHeizwerkRiem", ClusterHeadActorFactory.createSolarActor(12.92));
		top.addActorAsChild(cluster2 + "DachSchuleDroste-Huelshoff-Str", ClusterHeadActorFactory.createSolarActor(12.92));
		top.addActorAsChild(cluster2 + "GrosseSolarAnlage2", ClusterHeadActorFactory.createSolarActor(1200.00));
		top.addActorAsChild(cluster2 + "GrosseSolarAnlage3", ClusterHeadActorFactory.createSolarActor(1200.00));
		top.addActorAsChild(cluster2 + "BioGas2", ClusterHeadActorFactory.createBioGasActor(3000.0));
		top.addActorAsChild(cluster2 + "BioGas3", ClusterHeadActorFactory.createBioGasActor(2000.0));
		top.addActorAsChild(cluster2 + "Isarwerk1.1", ClusterHeadActorFactory.createWaterActor(800.));
		top.addActorAsChild(cluster2 + "Isarwerk1.2", ClusterHeadActorFactory.createWaterActor(800.));
		top.addActorAsChild(cluster2 + "Isarwerk1.3", ClusterHeadActorFactory.createWaterActor(800.));
		top.addActorAsChild(cluster2 + "Wasserkraftwerk4", ClusterHeadActorFactory.createWaterActor(2000.));
		top.addActorAsChild(cluster2 + "WindAnlage7", ClusterHeadActorFactory.createWindActor(4000.0));
		top.addActorAsChild(cluster2 + "BioMass7", ClusterHeadActorFactory.createBioMassActor(3050.0));
		top.addActorAsChild(cluster2 + "BioMass8", ClusterHeadActorFactory.createBioMassActor(4850.0));
		
		/*****************************************
		 *  CLusterHEAD3 hat:  
		 *  
		 *  - 4 Solar Anlagen
		 *  - x WasserKraftwerke
		 *  - x BioGas
		 *  - x BioMasse
		 *  - x Wind
		 *  
		 *****************************************/
		
		
		String cluster3 = root + "/Cluster-3";
		top.addActorAsChild(cluster3, ClusterHeadActorFactory.createClusterHeadActor());
		
		cluster3 = cluster3 +"/";
		
		top.addActorAsChild(cluster3 + "DachMesseMuenchen", ClusterHeadActorFactory.createSolarActor(1016.00));
		top.addActorAsChild(cluster3 + "GrosseSolarAnlage3", ClusterHeadActorFactory.createSolarActor(1016.00));
		top.addActorAsChild(cluster3 + "Isarwerk2.1", ClusterHeadActorFactory.createWaterActor(630.));
		top.addActorAsChild(cluster3 + "Isarwerk2.2", ClusterHeadActorFactory.createWaterActor(630.));
		top.addActorAsChild(cluster3 + "Isarwerk2.3", ClusterHeadActorFactory.createWaterActor(630.));
		top.addActorAsChild(cluster3 + "Isarwerk2.4", ClusterHeadActorFactory.createWaterActor(630.));
		top.addActorAsChild(cluster3 + "Isarwerk3.1", ClusterHeadActorFactory.createWaterActor(1600.));
		top.addActorAsChild(cluster3 + "Isarwerk3.2", ClusterHeadActorFactory.createWaterActor(1600.));
		top.addActorAsChild(cluster3 + "BioGas4", ClusterHeadActorFactory.createBioGasActor(650.0));
		top.addActorAsChild(cluster3 + "BioGas5", ClusterHeadActorFactory.createBioGasActor(650.0));
		top.addActorAsChild(cluster3 + "BioGas6", ClusterHeadActorFactory.createBioGasActor(650.0));
		top.addActorAsChild(cluster3 + "BioGas7", ClusterHeadActorFactory.createBioGasActor(650.0));
		top.addActorAsChild(cluster3 + "WindAnlage3", ClusterHeadActorFactory.createWindActor(4000.0));
		top.addActorAsChild(cluster3 + "WindAnlage4", ClusterHeadActorFactory.createWindActor(4000.0));
		top.addActorAsChild(cluster3 + "WindAnlage5", ClusterHeadActorFactory.createWindActor(4000.0));
		
		
		for (int i = 0; i < 40; i++) {
			top.addActorAsChild(cluster3 + "Wind" + i, ClusterHeadActorFactory.createWindActor(10.0));				
		}
		
		/*****************************************
		 *  CLusterHEAD4 hat:
		 *  
		 *  - x Solar Anlagen
		 *  - x WasserKraftwerke
		 *  - x BioGas
		 *  - x BioMasse
		 *  - x Wind
		 *  
		 *****************************************/
		
		
		String cluster4;
		cluster4 = root + "/Cluster-4";
		top.addActorAsChild(cluster4, ClusterHeadActorFactory.createClusterHeadActor());
		
		cluster4 = cluster4 +"/";
		
		top.addActorAsChild(cluster4 + "Praterkraftwerk", ClusterHeadActorFactory.createWaterActor(2500.));
		top.addActorAsChild(cluster4 + "Wasserkraftwerk1", ClusterHeadActorFactory.createWaterActor(2500.));
		top.addActorAsChild(cluster4 + "Wasserkraftwerk2", ClusterHeadActorFactory.createWaterActor(1000.));
		top.addActorAsChild(cluster4 + "GrosseSolarAnlage3", ClusterHeadActorFactory.createSolarActor(2016.00));
		top.addActorAsChild(cluster4 + "GrosseSolarAnlage4", ClusterHeadActorFactory.createSolarActor(2016.00));
		top.addActorAsChild(cluster4 + "GrosseSolarAnlage5", ClusterHeadActorFactory.createSolarActor(2016.00));
		top.addActorAsChild(cluster4 + "BioMass4", ClusterHeadActorFactory.createBioMassActor(1300.0));
		top.addActorAsChild(cluster4 + "BioMass5", ClusterHeadActorFactory.createBioMassActor(1700.0));
		top.addActorAsChild(cluster4 + "BioMass6", ClusterHeadActorFactory.createBioMassActor(1700.0));
		top.addActorAsChild(cluster4 + "BioGas4", ClusterHeadActorFactory.createBioGasActor(3000.0));
		top.addActorAsChild(cluster4 + "WindAnlage8", ClusterHeadActorFactory.createWindActor(4000.0));
		
		
		for (int i = 0; i < 60; i++) {
			top.addActorAsChild(cluster4 + "Solar" + i, ClusterHeadActorFactory.createSolarActor(10.0));			
		}
		
		
		return top;
	}

	/**
	 * @param string
	 * @return
	 */
	public static ActorTopology createTopologyFromJson(String string) {		
		ActorTopology top = new ActorTopology(simulationName);		
		JsonImport model = new JsonImport(string);
		JsonElement root = model.getRoot();
		root.createActorTopology(top);
		return top;
	}
}
