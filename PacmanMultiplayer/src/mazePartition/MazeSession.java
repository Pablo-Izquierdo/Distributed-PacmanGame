package mazePartition;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.MazeBuilderAlgorithmI;
import common.MazeRemote;
import common.RemoteSession;

public class MazeSession extends UnicastRemoteObject implements RemoteSession {

	
	private static final long serialVersionUID = 1L;

	public MazeSession () throws RemoteException {
		super();
	}

	@Override
	public MazeRemote start() {

		MazeBuilderAlgorithmI builder = new MazeBuilderText();
		MazeProxy mazeProxy = new MazeProxy(builder);
		MazeRemote mazeRemote = null;
		try {
			mazeRemote = (MazeRemote) UnicastRemoteObject.exportObject(mazeProxy, 18000);
		} catch (RemoteException e) {
			System.out.println("Problema al extraer el objeto");
		}

		return mazeRemote;

	}

}
