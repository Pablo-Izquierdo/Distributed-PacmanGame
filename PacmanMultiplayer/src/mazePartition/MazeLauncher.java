package mazePartition;

import java.rmi.RemoteException; 
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class MazeLauncher {

	public static void main(String[] args) {
		
		Registry registry = null;
		String MazeName = "Maze_Remote";
		
		MazeSession session = null;
		
		// Crea el MazeProxy
		try {
			session = new MazeSession();
		} catch (RemoteException e1) {
			System.out.println("Problema el crear MazeSession");
			e1.printStackTrace();
		}
		
		try {
			registry = LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.out.println("Problema al crear el Registro");
		}
		
		try {
			registry.bind(MazeName, session);
		} catch (Exception e) {
			System.out.println("Problema al añadir Maze_Remote al registro");
		}
		
		System.out.println("El Servidor MazeRemote esta instalado");
	
		System.out.println ("Se ha iniciado el servidor");
	}
}
