package mazePartition;

import java.util.List;

import common.Cell;
import common.MazeBuilderAlgorithmI;
import common.MazeRemote;


public class MazeProxy implements MazeRemote{
	
	Maze maze = null;
	
	public MazeProxy (MazeBuilderAlgorithmI builder) {
		maze = new Maze(builder);
	}

	@Override
	public boolean removePoint(int pos) {
		return maze.removePoint(pos);
	}

	@Override
	public List<Cell> getMazeDataList() {
		return maze.getMazeDataList();
	}

	@Override
	public boolean checkMaze() {
		return maze.checkMaze();
	}

	@Override
	public void initMazeData(int mazeLevel) {
		maze.initMazeData(mazeLevel);
		
	}
	
	
}
