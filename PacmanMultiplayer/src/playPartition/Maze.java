package playPartition;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import common.Cell;
import common.MazeRemote;
import common.RemoteSession;

public class Maze {
	
	String MazeName = "Maze_Remote";
    String RegistryIP = "127.0.0.1";
	Registry registry = null;
	MazeRemote maze = null;
	RemoteSession session = null;

	public Maze() {
		
		try {
			registry = LocateRegistry.getRegistry(RegistryIP); //Obtener registry
			
		} catch (RemoteException e) {
			System.out.println("No se ha podido Localizar el rmiRegistry local");
			e.printStackTrace();
			System.exit(-1);
		}
		try {
			session = (RemoteSession) registry.lookup(MazeName);  //Obtener referencia al objeto remoto
			maze = session.start();
		} catch (Exception e) {
			System.out.println("El Cliente de nombre " + MazeName + " no esta registrado");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	
	public boolean removePoint(int pos) {
		try {
			return maze.removePoint(pos); //Llamo a la funcion remota
		} catch (RemoteException e) {
			System.out.println("Fallo al llamar a la funcion remota");
			e.printStackTrace();
		} 
		return false;
	}

	
	public List<Cell> getMazeDataList() {	
		try {
			return maze.getMazeDataList(); //Llamo a la funcion remota
		} catch (RemoteException e) {
			System.out.println("Fallo al llamar a la funcion remota");
			e.printStackTrace();
		} 
		return null;
	}

	
	public boolean checkMaze() {
		try {
			return maze.checkMaze(); //Llamo a la funcion remota
		} catch (RemoteException e) {
			System.out.println("Fallo al llamar a la funcion remota");
			e.printStackTrace();
		} 
		return false;
	} 

	
	public void initMazeData(int mazeLevel) {
		try {
			maze.initMazeData(mazeLevel); //Llamo a la funcion remota
		} catch (RemoteException e) {
			System.out.println("Fallo al llamar a la funcion remota");
			e.printStackTrace();
		} 
	}
	 
	
	
}
