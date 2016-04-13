package rest;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.common.primitives.Bytes;

public abstract class MovingController extends Controller {
	private ArrayList<MovingObject> controlledMovers = new ArrayList<>();
	private MasterController masterController;

	public MovingController(MasterController masterController) {
		this.masterController = masterController;
	}

	public byte[] getAllPositions() {
		byte[] result = new byte[3];
		int nrObjects = controlledMovers.size();
		if (nrObjects > 0) {
			// This is to be changed to a ControllerSpecifik number!!!
			int maxNumberofSubeTypes = 5;
			byte objectType = controlledMovers.get(0).getObjectType();

			int m = 0;
			int currentFoundSubs = 0;
			byte[] allSubTypes = new byte[controlledMovers.size()];
			byte[] foundSubTypes = new byte[maxNumberofSubeTypes];
			byte[] sizeOfSubTypes = new byte[maxNumberofSubeTypes];
			while (m < nrObjects) {
				MovingObject obj = controlledMovers.get(m);
				byte sub = obj.getObjectSubType();
				allSubTypes[m] = sub;
				int subIndex = byteArrayContains(foundSubTypes, sub);
				if (subIndex == -1) {
					// System.out.println("sub added = " + sub);
					// allSubTypes[m] = sub;
					foundSubTypes[currentFoundSubs] = sub;
					sizeOfSubTypes[currentFoundSubs] = (byte) (obj.getLength() * 2);
					// System.out.println("with length = " +
					// sizeOfSubTypes[currentFoundSubs]);
					currentFoundSubs++;
				} else {
					sizeOfSubTypes[subIndex] += obj.getLength() * 2;
				}

				m++;
			}
			result = buildSendArray(objectType, foundSubTypes, allSubTypes, sizeOfSubTypes);

		} else {
			//byte[] result = new byte[3];
			result[0] = super.getTypesControlled();
			result[1] = 0;
			result[2] = -1;
			// System.out.println("got out ugly");
		}
		return result;
		
	}

	public VisibleObject checkCrash(byte xPosition, byte yPosition, MovingObject crasher) {
		// First byte stands for crash with player, second for player number of
		// the crasher.
		// returns first byte -1 if no crash
		// byte[] result = new byte[2];
		for (MovingObject mover : controlledMovers) {
			byte[] positions = mover.getAllPositionsCrasch();
			// for(Snake playerInner: players) {
			int n = 0;
			while (n < (positions.length) / 2) {
				// System.out.println("x Tested = " + positions[2 * n]);
				// System.out.println("y Tested = " + positions[2 * n + 1]);
				if (xPosition == positions[2 * n] && yPosition == positions[2 * n + 1]) {
					// System.out.println("Bang");

					// result[0] = mover.getObjectType();
					// result[1] = mover.getObjectSubType();
					mover.handleCrashedInto(crasher);
					return mover;
				}
				n++;
			}
			// }

		}
		// result[0] = -1;
		return null;
	}

	public void move() {
		for (MovingObject mover : controlledMovers) {
			mover.move(masterController);
		}
	}

	public ArrayList<MovingObject> getMovers() {
		return controlledMovers;
	}

	public void disposeOfRemovables() {
		Iterator<MovingObject> itr = controlledMovers.iterator();
		while (itr.hasNext()) {
			MovingObject obj = itr.next();
			if (obj.isToBeRemoved()) {
				// System.out.println("removed");
				itr.remove();
			}
		}
	}

	private int byteArrayContains(byte[] array, byte test) {
		int k = 0;
		while (k < array.length) {
			// System.out.println("got contains test");
			if (array[k] == 0) {
				return -1;
			}
			if (array[k] == test) {
				return k;
			}
			k++;
		}
		return -1;
	}

	private byte[] trimArray(byte[] toTrim) {
		// System.out.println("got into trim = ");
		int k = 0;
		int j = 0;
		while (j < toTrim.length) {

			if (toTrim[j] != 0) {
				// System.out.println("got trim = " + toTrim[j]);
				k++;
			}
			j++;

		}
		byte[] result = new byte[k];
		int n = 0;
		while (n < k) {
			result[n] = toTrim[n];
			n++;

		}
		// System.out.println("got out of trim = ");
		return result;
	}

	private void addToSendArray(byte[] adder, byte[][] send, int[] indexes) {
		byte[] unpartedResult = adder;
		int k = 1;
		while (k < unpartedResult.length) {
			byte[] partResult = new byte[3];
			partResult[0] = unpartedResult[0];
			partResult[1] = unpartedResult[k];
			partResult[2] = unpartedResult[k + 1];
			addToCorrectArray(send, partResult, indexes);
			k += 2;

		}
	}

	private void addToCorrectArray(byte[][] arrArr, byte[] arr, int[] indexes) {

		// byte[] result = new byte[2];
		int n = 0;
		while (n < arrArr.length) {
			// System.out.println("arr[0] = " + arr[0]);
			// System.out.println("arrArr[n][0] = " + arrArr[n][0]);
			if (arr[0] == arrArr[n][0]) {
				// System.out.println("Bytes added to array!");

				arrArr[n][indexes[n] + 2] = arr[1];
				arrArr[n][indexes[n] + 1 + 2] = arr[2];
				// System.out.println("x = " + arrArr[n][indexes[n] + 2]);
				// System.out.println("y = " + arrArr[n][indexes[n] + 1 + 2]);
				indexes[n] = indexes[n] + 2;
				return;
			}
			n++;
		}
	}

	private byte[][] createSubArrays(byte[] allSubs, byte[] foundSubTypes, byte[] sizes) {
		// System.out.println("Creating sub arrays");
		int length = foundSubTypes.length;
		byte[][] result = new byte[length][];

		int n = 0;
		while (n < length) {

			result[n] = new byte[sizes[n] + 2];
			result[n][0] = foundSubTypes[n];
			result[n][1] = (byte) (sizes[n] / 2);
			n++;
		}
		return result;

	}

	private byte[][] createInfoArray(byte[] allSubTypes, byte[] foundSubTypes, byte[] sizeOfSubTypes) {
		int[] indexes = new int[foundSubTypes.length];
		byte[][] sorter = createSubArrays(allSubTypes, foundSubTypes, sizeOfSubTypes);
		int current = 0;
		while (current < controlledMovers.size()) {
			MovingObject obj = controlledMovers.get(current);
			byte[] unpartedResult = obj.getAllPositionsSend();
			addToSendArray(unpartedResult, sorter, indexes);
			current++;
		}
		return sorter;
	}

	private byte[] buildSendArray(byte objectType, byte[] foundSubTypes, byte[] allSubTypes, byte[] sizeOfSubTypes) {
		byte[] result = new byte[2];
		result[0] = objectType;
		result[1] = (byte) foundSubTypes.length;
		foundSubTypes = trimArray(foundSubTypes);
		byte[][] sorter = createInfoArray(allSubTypes, foundSubTypes, sizeOfSubTypes);
		byte[] end = { -1 };
		int p = 0;
		while (p < sorter.length) {
			// System.out.println("got third");
			byte[] subType = { foundSubTypes[p] };
			// result = Bytes.concat(result, subType, sorter[p]);
			result = Bytes.concat(result, sorter[p]);
			p++;
		}
		result = Bytes.concat(result, end);
		// result[result.length - 1] = -1;
		return result;
	}

}
