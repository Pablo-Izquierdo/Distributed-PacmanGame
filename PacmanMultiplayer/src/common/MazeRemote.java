package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MazeRemote extends Remote {
	
	 public boolean removePoint (int pos) throws RemoteException;
	 public List<Cell> getMazeDataList () throws RemoteException;
	 public boolean checkMaze() throws RemoteException;
	 public void initMazeData(int mazeLevel) throws RemoteException;
}
