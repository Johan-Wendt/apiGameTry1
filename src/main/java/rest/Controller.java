package rest;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.common.primitives.Bytes;

public abstract class Controller {
	private byte typesControlled;
	private byte numberOfSubTypes = 0;
	private ArrayList<ActingObject> controlledObjects = new ArrayList<>();
	private int sendStart = 0;
	private boolean sendChangesOnly = false;
	
	
	public Controller() {
		
	}

	public byte getTypesControlled() {
		return typesControlled;
	}
	public void setTypesControlled(byte typesControlled) {
		this.typesControlled = typesControlled;
	}
	public byte getNumberOfSubTypes() {
		return numberOfSubTypes;
	}
	public void setNumberOfSubTypes(byte numberOfSubTypes) {
		this.numberOfSubTypes = numberOfSubTypes;
	}
	
	
	public byte[] getAllPositions() {
		byte[] result = new byte[3];
		int nrObjects = controlledObjects.size();
		if (nrObjects - sendStart > 0) {
			int maxNumberofSubeTypes = numberOfSubTypes;
			byte objectType = controlledObjects.get(0).getObjectType();

			int m = sendStart;
			int currentFoundSubs = 0;
			byte[] allSubTypes = new byte[nrObjects];
			byte[] foundSubTypes = new byte[maxNumberofSubeTypes];
			int[] sizeOfSubTypes = new int[maxNumberofSubeTypes];
			while (m < nrObjects) {
				ActingObject obj = controlledObjects.get(m);
				byte sub = obj.getObjectSubType();
				allSubTypes[m] = sub;
				int subIndex = byteArrayContains(foundSubTypes, sub);
				if (subIndex == -1) {
				//	if(numberOfSubTypes == 6) System.out.println("sub = " + sub);
					foundSubTypes[currentFoundSubs] = sub;
					sizeOfSubTypes[currentFoundSubs] = (byte) (obj.getLength() * 2);
					currentFoundSubs++;
				} else {
					sizeOfSubTypes[subIndex] += obj.getLength() * 2;
				}
				m++;
			}
			result = buildSendArray(objectType, foundSubTypes, allSubTypes, sizeOfSubTypes);

		} else {
			result[0] =typesControlled;
			result[1] = 0;
			result[2] = -1;
		}
		return result;
		
	}

	public VisibleObject checkCrash(byte xPosition, byte yPosition, MovingObject crasher) {
		for (ActingObject acter : controlledObjects) {
			byte[] positions = acter.getAllPositionsCrasch();
			int n = 0;
			while (n < (positions.length) / 2) {
				if (xPosition == positions[2 * n] && yPosition == positions[2 * n + 1]) {
					acter.handleCrashedInto(crasher);
					return acter;
				}
				n++;
			}
		}
		return null;
	}

	public void act(MasterController masterController) {
		for (ActingObject acter : controlledObjects) {
			acter.act(masterController);
		}
	}

	public ArrayList<ActingObject> getControlledObjects() {
		return controlledObjects;
	}

	public void disposeOfRemovables() {
		Iterator<ActingObject> itr = controlledObjects.iterator();
		while (itr.hasNext()) {
			ActingObject obj = itr.next();
			if (obj.isToBeRemoved()) {
				itr.remove();
			}
		}
	}

	private int byteArrayContains(byte[] array, byte test) {
		int k = 0;
		while (k < array.length) {
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

	private void addToCorrectArray(byte[][] fullResult, byte[] partResult, int[] indexes) {
		int n = 0;
		while (n < fullResult.length) {
			if (partResult[0] == fullResult[n][0]) {

				fullResult[n][indexes[n] + 2] = partResult[1];
				fullResult[n][indexes[n] + 1 + 2] = partResult[2];

				indexes[n] = indexes[n] + 2;
				return;
			}
			n++;
		}
	}

	private byte[][] createSubArrays(byte[] allSubs, byte[] foundSubTypes, int[] sizes) {
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

	private byte[][] createInfoArray(byte[] allSubTypes, byte[] foundSubTypes, int[] sizeOfSubTypes) {
		int[] indexes = new int[foundSubTypes.length];
		byte[][] sorter = createSubArrays(allSubTypes, foundSubTypes, sizeOfSubTypes);
		int current = sendStart;
		while (current < controlledObjects.size()) {
			ActingObject obj = controlledObjects.get(current);
			byte[] unpartedResult = obj.getAllPositionsSend();
			addToSendArray(unpartedResult, sorter, indexes);
			current++;
		}
		if (sendChangesOnly) {
			sendStart = current;
		}
		return sorter;
	}

	private byte[] buildSendArray(byte objectType, byte[] foundSubTypes, byte[] allSubTypes, int[] sizeOfSubTypes) {
		byte[] result = new byte[2];
		result[0] = objectType;
		result[1] = (byte) foundSubTypes.length;
		foundSubTypes = trimArray(foundSubTypes);
		byte[][] sorter = createInfoArray(allSubTypes, foundSubTypes, sizeOfSubTypes);
		byte[] end = { -1 };
		int p = 0;
		while (p < sorter.length) {
			byte[] subType = { foundSubTypes[p] };
			result = Bytes.concat(result, sorter[p]);
			p++;
		}
		result = Bytes.concat(result, end);
		return result;
	}
	public boolean isSendChangesOnly() {
		return sendChangesOnly;
	}

	public void setSendChangesOnly(boolean sendChangesOnly) {
		this.sendChangesOnly = sendChangesOnly;
	}
	
	public void printAllPositions() {
		int n = 0;
		for(ActingObject obj: controlledObjects) {
			byte [] result = obj.getPosition();
			System.out.println("x position for acting object is" + result[0]);
			System.out.println("y position for acting object is" + result[1]);
			n ++;
		}
		System.out.println("total number of objects is " + n);
	}

	


}
