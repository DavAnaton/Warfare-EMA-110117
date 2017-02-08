import java.util.ArrayList;

import algorithms.DFS;
import algorithms.State;
import models.containers.*;
import models.shipments.*;

public class Main {

	public static void main(String[] args) {

		Cargobay cargobay = new Cargobay(9, 4);
		ArrayList<GenericShipment> loadingDock = new ArrayList<GenericShipment>();

		for (int i = 0; i < 8; i++) {
			loadingDock.add(new Artillery());
		}
		for (int i = 0; i < 9; i++) {
			loadingDock.add(new Ammo());
		}
		for (int i = 0; i < 8; i++) {
			loadingDock.add(new LightFight());
		}
		for (int i = 0; i < 11; i++) {
			loadingDock.add(new Food());
		}
		
			System.out.println(DFS.DFS(cargobay, loadingDock));
		
//		System.out.println(cargobay);

	}

}
