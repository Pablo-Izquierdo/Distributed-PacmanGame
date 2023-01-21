package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSession extends Remote {
	
	public MazeRemote start() throws RemoteException;
	
}
