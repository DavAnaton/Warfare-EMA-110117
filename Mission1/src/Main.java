import java.util.ArrayList;

import algorithms.DFS;
import algorithms.State;
import models.containers.*;
import models.shipments.*;

public class Main {

	public static void main(String[] args) {
		
		// 9 spots of 4 boxes each
		Cargobay cargobay = new Cargobay(9, 4);
		// Artillery, Ammo, LightFight, Food
		ArrayList<GenericShipment> loadingDock = generateLoadingDock(8, 9, 8, 11);
		
		State conf = DFS.DFS(cargobay, loadingDock);
		
		System.out.println(conf);

	}

	/**
	 * Generates a list of shipment to load on the cargo bay
	 * @param artillery The number of boxes of Artillery
	 * @param ammo The number of boxes of Ammo
	 * @param lightfight The number of boxes of LightFight
	 * @param food The number of boxes of Food
	 * @return The list of Shipments
	 */
	public static ArrayList<GenericShipment> generateLoadingDock(int artillery, int ammo, int lightfight, int food){
		ArrayList<GenericShipment> loadingDock = new ArrayList<GenericShipment>();

		for (int i = 0; i < artillery; i++) {
			loadingDock.add(new Artillery());
		}
		for (int i = 0; i < ammo; i++) {
			loadingDock.add(new Ammo());
		}
		for (int i = 0; i < lightfight; i++) {
			loadingDock.add(new LightFight());
		}
		for (int i = 0; i < food; i++) {
			loadingDock.add(new Food());
		}
		return loadingDock;
	}
}
