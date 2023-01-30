package unimodal;

public class UnimodalSequence {

	/**
	 * Determine the length of the longest unimodal sequence in intArray
	 * 
	 * @param intArray
	 *            is not null
	 * @return the length of the longest unimodal sequence in intArray
	 * @throws NoUnimodalSequenceException
	 *             if there is no unimodal sequence in intArray
	 */
	public static int getLength_longestUnimodalSequence(int[] intArray) throws NoUnimodalSequenceException {
		int index = 0;
		int length = 0;
		int start_index, end_index;
		while(index < intArray.length - 2) {
			if(intArray[index + 1] > intArray[index]){
				start_index = index;
				while((index < intArray.length - 2) && (intArray[index + 1] > intArray[index])){
					index++;
				}
				if(intArray[index + 1] < intArray[index]){
					while(intArray[index + 1] < intArray[index]){
						index++;
						if(index > intArray.length - 2) {
							break;
						}
					}
					end_index = index;
					if((end_index - start_index + 1) > length){
						length = end_index - start_index + 1;
					}
				}
			} else {
				index++;
			}
		}
		if(length == 0) {
			throw new NoUnimodalSequenceException();
		}
		return length;
	}
}
