package rest;

import java.util.ArrayList;

import com.google.common.primitives.Bytes;

public abstract class MovingController extends Controller {
	private ArrayList<MovingObject> controlledMovers = new ArrayList<>();
	private MasterController masterController;
	
	public MovingController() {
		
	}
	
	public byte[] getAllPositions() {
		byte[] result = new byte[0];
		byte[] concatenator = {-1};
		for(MovingObject mover: controlledMovers) {
			result = Bytes.concat(result, mover.getAllPositionsSend(), concatenator);
		}
		return result;
	}

}
