package rest;

import java.util.ArrayList;
import java.util.Iterator;
import com.google.common.primitives.Bytes;

public abstract class VisibleController extends Controller {
	private ArrayList<VisibleObject> controlledObjects = new ArrayList<>();
	private int sendStart = 0;
	private boolean sendChangesOnly = false;

	public VisibleController() {

	}

	@Override
	public byte[] getAllPositions() {
		byte[] result = new byte[2];
		if (controlledObjects.size() - sendStart> 0) {
			// This is to be changed to a ControllerSpecifik number!!!
			int maxNumberofSubeTypes = 5;
			byte objectType = controlledObjects.get(0).getObjectType();
			int nrObjects = controlledObjects.size();
			if (nrObjects > 0) {
				result[0] = controlledObjects.get(0).getObjectType();
				int m = sendStart;
				int currentFoundSubs = 0;
				byte[] allSubTypes = new byte[controlledObjects.size()];
				byte[] foundSubTypes = new byte[maxNumberofSubeTypes];
				while (m < controlledObjects.size()) {
					VisibleObject obj = controlledObjects.get(m);
					byte sub = obj.getObjectSubType();
					allSubTypes[m] = sub;
					
					if (!byteArrayContains(foundSubTypes, sub)) {
						//allSubTypes[m] = sub;
						foundSubTypes[currentFoundSubs] = sub;
						currentFoundSubs++;
					}
					m++;
				}
				result[1] = (byte) (currentFoundSubs);
				foundSubTypes = trimArray(foundSubTypes);
				int[] indexes = new int[currentFoundSubs];
				// byte[] amountOFeachSort = new byte[currentFoundSubs];
				byte[][] sorter = new byte[currentFoundSubs][];
				createSubArrays(sorter, allSubTypes, foundSubTypes);
				int n = 0;
				int current = sendStart;
				while (current < controlledObjects.size()) {
					byte[] partResult = new byte[3];
					VisibleObject obj = controlledObjects.get(current);
					partResult[0] = obj.getObjectSubType();
					partResult[1] = obj.getxPos();
					partResult[2] = obj.getyPos();
					addToCorrectArray(sorter, partResult, indexes);
					current++;
				}
				if (sendChangesOnly) {
					sendStart = current;
				}
				result[0] = objectType;
				byte[] end = { -1 };
				int p = 0;
				while (p < sorter.length) {
					System.out.println("got third");
					byte[] subType = { foundSubTypes[p] };
					//result = Bytes.concat(result, subType, sorter[p]);
					result = Bytes.concat(result, sorter[p]);
					p++;
				}
				result = Bytes.concat(result, end);
				// result[result.length - 1] = -1;
				int t = 0;

			}
		} else {
			result = new byte[3];
			result[0] = super.getTypesControlled();
			result[1] = 0;
			result[2] = -1;
		}
		return result;
	}

	public VisibleObject crashCheck(byte x, byte y, MovingObject crasher) {
		byte[] result = new byte[2];
		Iterator<VisibleObject> itr = controlledObjects.iterator();
		while (itr.hasNext()) {
			VisibleObject obj = itr.next();
			if (obj.isInPosition(x, y)) {
				// result[0] = obj.getObjectType();
				// result[1] = obj.getObjectSubType();
				obj.handleCrashedInto(crasher);
				return obj;
			}
		}

		// result[0] = -1;
		return null;
	}

	public void disposeOfRemovables() {
		Iterator<VisibleObject> itr = controlledObjects.iterator();
		while (itr.hasNext()) {
			VisibleObject obj = itr.next();
			if (obj.isToBeRemoved()) {
				itr.remove();
			}
		}
	}

	public ArrayList<VisibleObject> getControlledObjects() {
		return controlledObjects;
	}

	public int getSendStart() {
		return sendStart;
	}

	public void setSendStart(int sendStart) {
		this.sendStart = sendStart;
	}

	public boolean isSendChangesOnly() {
		return sendChangesOnly;
	}

	public void setSendChangesOnly(boolean sendChangesOnly) {
		this.sendChangesOnly = sendChangesOnly;
	}

	private boolean byteArrayContains(byte[] array, byte test) {
		boolean contains = false;
		int k = 0;
		while (k < array.length) {
			if (array[k] == 0) {
				return false;
			}
			if (array[k] == test) {
				return true;
			}
			k++;
		}
		return contains;
	}

	private byte[] trimArray(byte[] toTrim) {
		int k = 0;
		int j = 0;
		while (j < toTrim.length) {
			
			if (toTrim[j] != 0) {
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
		return result;
	}

	private void addToCorrectArray(byte[][] arrArr, byte[] arr, int[] indexes) {
		byte[] result = new byte[2];
		int n = 0;
		while (n < arrArr.length) {
			if (arr[0] == arrArr[n][0]) {
				
				arrArr[n][indexes[n] + 2] = arr[1];
				arrArr[n][indexes[n] + 1 + 2] = arr[2];
				indexes[n] = indexes[n] + 2;
				return;
			}
			n++;
		}
	}

	private void createSubArrays(byte[][] sorter, byte[] allSubs, byte[] foundSubTypes) {

		int n = 0;
		while (n < foundSubTypes.length) {
			int k = 0;
			int size = 0;
			while (k < allSubs.length) {
				if (allSubs[k] == foundSubTypes[n]) {
					
					size += 2;
				}
				k++;
			}
			
			sorter[n] = new byte[size + 2];
			sorter[n][0] = foundSubTypes[n];
			sorter[n][1] = (byte) (size / 2);
			n ++;
		}

	}

}
