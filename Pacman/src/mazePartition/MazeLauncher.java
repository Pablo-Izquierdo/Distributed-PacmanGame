package mazePartition;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import common.MazeBuilderAlgorithmI;
import common.MazeRemote;

public class MazeLauncher {

	public static void main(String[] args) {
		Registry registry = null;
		String MazeName = "Maze_Remote";
		
		// Crea el MazeProxy
		MazeBuilderAlgorithmI builder = new MazeBuilderText();
		Maze maze = new Maze(builder);
		MazeRemote mazeRemote = null;
		
		try {
			mazeRemote = (MazeRemote) UnicastRemoteObject.exportObject(maze, 18000);
		} catch (RemoteException e) {
			System.out.println("Problema al extraer el objeto");
		}
		
		try {
			registry = LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.out.println("Problema al crear el Registro");
		}
		
		try {
			registry.bind(MazeName, mazeRemote);
		} catch (Exception e) {
			System.out.println("Problema al añadir Maze_Remote al registro");
		}
		
		System.out.println("El Servidor MazeRemote esta instalado");
	
		System.out.println ("Se ha iniciado el servidor");
	}
}
