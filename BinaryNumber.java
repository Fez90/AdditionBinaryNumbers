import java.util.Arrays;

public class BinaryNumber {
	
	// Name: Feruz Karimov 
	 
	
	private int data [];
	private boolean overflow;
	
	public BinaryNumber(int length) {
		//Check if length is valid, not zero and positive integer
		if(length < 1) {
			
			System.out.println("Length cannot be negative or zero.");
		}
		//Populating array with zero values
		data = new int[length];
	}
	
	public BinaryNumber(String str) {
		//Checking if String has only zero or one values, if not, output message
		int length = str.length();
		int tempLength = 0;
		
		for(int i = 0; i < length; i++){
			
			char ch = str.charAt(i);
			
			if (ch == '0' || ch == '1') {
				tempLength++;
				
			} else {
				System.out.println("String must have 0 or 1.");
				break;
			}
		}
		//Filling array if previous condition does not have any other values than 0 or 1
		data = new int[tempLength];
		
		if(tempLength > 0) {
			
			for(int i = 0; i < length; i++) {
				
				char ch = str.charAt(i);
				data[i] = Character.getNumericValue(ch);
			}
		}
	}
	
	public int getLength() {
		//Returning length
		int length = data.length;
		
		return length;
	}
	
	public int getDigit(int index) {
		//If giving index cannot be reached within array, output message
		if(index<0 || index>data.length-1 ){
			System.out.println("Index is out of bond!");
			return -1;
		}
		
		return data[index];
	}
	
	public void shiftR(int amount) {
		//Checking for negative input
		if (amount < 0) {
			System.out.println("Please, enter positive integer!");
		}
		//Creating new array, that has larger size to be filled with zero integers
		int newArr [] = Arrays.copyOf(data, data.length + amount);
		//Filling new array with zero values that equal amount parameter
		for (int i = 0; i < amount; i++) {
			newArr[i] = 0;
		}
		for (int i = 0; i < data.length; i++) {
			newArr[i + amount] = data[i];
		}
		for (int i = 0; i < newArr.length; i++) {
			System.out.print(newArr[i]);
		}
		System.out.println();
	}
	
	public void add(BinaryNumber aBinaryNumber) {
		
		//Creating local variables for storing carry value and temporal sum array
		int carry = 0;
		int[] temp = new int[data.length];
		
		if(data.length != aBinaryNumber.getLength()) {
			//Checking if length of two binary numbers are coincide
			System.out.println("Lengths of binary numbers do not coincide.");
		}
		else {
			//populating temporary array after sum 
			for(int i=0; i< data.length; i++) {
				
				if((data[i] + aBinaryNumber.getDigit(i) + carry) == 0 ) {
					
					temp[i] = 0;
					carry=0;
				}
				else if((data[i] + aBinaryNumber.getDigit(i) + carry) == 1 ) {
					
					temp[i] = 1;
					carry=0;
				}
				else if((data[i] + aBinaryNumber.getDigit(i) + carry) == 2 ) {
					
					if(i==(data.length - 1)) {
						//Setting overflow flag to true, if there is a carryover digit at the addition
						overflow = true;
						break;
					}
					temp[i] = 0;
					carry=1;
				}
				else if((data[i] + aBinaryNumber.getDigit(i) + carry) == 3 ) {
					
					if(i==(data.length - 1)) {
						overflow = true;
						break;
					}
					temp[i] = 1;
					carry=1;
				}
			}
			
			if(carry == 1) {
				
				System.out.println("Sum resulted overflow.");
			}
		}
		
		data = temp;
		
	}
	
	public String toString() {
		//if number is result of overflow output message
		String stringOutput = "";
		
		if (overflow == true) {
			
			stringOutput = "Overflow";
			
		} else {
			
			for (int i = 0; i < data.length; i++) {
				stringOutput += data[i];
			}
		}
		
		return stringOutput;
	}
	
	public int toDecimal() {
		//Converting binary to decimal little-endian format.
		int decimal = 0;
		
		for (int i = 0; i< data.length; i++) {
			
			decimal += data[i] * Math.pow(2, i);
		}
		
		return decimal;
	}
	
	public void clearOverflow() {
		//Clearing overflow flag
		overflow = false;
		System.out.println("Overflow was cleared.");
	}
	


	public static void main(String[] args) {

		BinaryNumber data1 = new BinaryNumber("1011");
		BinaryNumber data2 = new BinaryNumber("0100");


		BinaryNumber data3 = new BinaryNumber("001001");
		BinaryNumber data4 = new BinaryNumber("00101");
		BinaryNumber data5 = new BinaryNumber("1110");
		BinaryNumber data6 = new BinaryNumber("1011");
		BinaryNumber data7 = new BinaryNumber(7);
		BinaryNumber data8 = new BinaryNumber("1111");

		//Test cases for methods and operations above
		System.out.println(data1);
		System.out.println(data2);

		//Converting data1 and data2 to decimal
		System.out.println(data1.toDecimal());
		System.out.println(data2.toDecimal());

		//Performing addition operation with two binary numbers.
		data1.add(data2);

		//Converting new number to Decimal and String after sum operation.Checking length and index positioning of new binary
		System.out.println(data1.toDecimal());
		System.out.println(data1.toString());
//		System.out.println(data1.getLength());
//		System.out.println(data1.getDigit(1));

		data1.add(data2);

		System.out.println(data1.toDecimal());
		System.out.println(data1.toString());

		data1.clearOverflow();
		System.out.println(data1.toDecimal());

		System.out.println(data1.toDecimal());
		System.out.println(data1.toString());

	}

}
