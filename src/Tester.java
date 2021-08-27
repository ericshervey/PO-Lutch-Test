import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester {

	/*
	 * Create a function that takes a number as an argument and returns true if the number is a valid credit card number, false otherwise.

		Credit card numbers must be between 14-19 digits in length, and pass the Luhn test, described below:
		
		Remove the last digit (this is the "check digit").
		Reverse the number.
		Double the value of each digit in odd-numbered positions. If the doubled value has more than 1 digit, add the digits together (e.g. 8 x 2 = 16 ➞ 1 + 6 = 7).
		Add all digits.
		Subtract the last digit of the sum (from step 4) from 10. The result should be equal to the check digit from step 1.
		Examples
		validateCard(1234567890123456) --> false
		
		// Step 1: check digit = 6, num = 123456789012345
		// Step 2: num reversed = 543210987654321
		// Step 3: digit array after selective doubling: [1, 4, 6, 2, 2, 0, 9, 8, 5, 6, 1, 4, 6, 2, 2]
		// Step 4: sum = 58
		// Step 5: 10 - 8 = 2 (not equal to 6) --> false
		
		validateCard(1234567890123452) --> true
	 */
	
	
	public static void main(String[] args)
	{
		System.out.println(LuhnTest());
	}
	
	private static boolean LuhnTest ()
	{
		Scanner kIn = new Scanner(System.in);
		
		Matcher m;
		Pattern p;
		String num;
		
		do {
		System.out.print("Number: ");
		num = kIn.nextLine();
		
		p = Pattern.compile("^\\d{14,19}$"); 
		m = p.matcher(num);
		} while(!m.find());
		
		int check = Integer.parseInt(num.charAt(num.length() - 1) + "");
		
		num = num.substring(0, num.length() - 1);
		
		int sum = 0;
		
		String rNum = new StringBuilder(num).reverse().toString();
		//System.out.println("Reversed: " + rNum);
		
		//String newString = "";
		
		for(int ix = 0; ix < rNum.length(); ix++)
		{
			if( ix % 2 == 0 || ix == 0)
			{
				int i = (Integer.parseInt(rNum.charAt(ix) + "") * 2);
				
				if( i >= 10)
				{
					i = (i / 10 ) + (i - 10);
				}
				
				sum += i;
				
				//newString += i +"";
			}
			else
			{
				int i = (Integer.parseInt(rNum.charAt(ix) + ""));
				sum += i;
				//newString += rNum.charAt(ix);
			}
		}
		
		while(sum > 10)
		{
			sum = sum % 10;
		}
		
		if(10 - sum == check)
			return true;
		
		System.out.println(sum);
		
		return false;
	}
	
	private static String[] stringBuckets()
	{
		Scanner kIn = new Scanner(System.in);
		
		System.out.print("Bucker Size: ");
		int bSize = kIn.nextInt();
		kIn.nextLine();
		//int bSize = 5;
		
		System.out.println("Enter Statement. . . ");
		String s = kIn.nextLine();
		//String s = "Sally Sells Seashells By the sea";

		String[] sArr = s.split(" ");
		/*
		for(String s2 : sArr)
		{
			System.out.println(s2);
		}
		*/
		
		ArrayList<String> buckets = new ArrayList<String>();
		
		String temp = "";
		int rSpace = bSize;
		
		boolean phraseValid = true;
		
		for(int ix = 0; ix < sArr.length; ix++)
		{
			//System.out.println("\n" + sArr[ix] + " " + sArr[ix].length() + " " + rSpace);
			
			
			if(rSpace == bSize && sArr[ix].length() <= rSpace) //first entry in bucket, just add.
			{
				//System.out.println("ix option 1 : " + sArr[ix]);
				temp += sArr[ix];
				rSpace -= sArr[ix].length();
			}
			else if(sArr[ix].length() + 1 <= rSpace) //+1 for nth entry in bucket for the white space.
			{
				//System.out.println("ix option 2 : " + sArr[ix]);
				temp += " " + sArr[ix];
				//System.out.println(rSpace + " - " + (sArr[ix].length() + 1));
				rSpace -= sArr[ix].length() + 1;
			}
			else if(sArr[ix].length() <= bSize) //Can fit in a bucket, but not current bucket.
			{
				//System.out.println("ix option 3 : " + sArr[ix]);
				buckets.add(temp);
				temp = sArr[ix];
				rSpace = bSize - sArr[ix].length();
			}
			
			if( sArr[ix].length() > bSize)
			{
				//System.out.println("ix option 4");
				phraseValid = false;
				//System.out.println("Invalid Phrase " + sArr[ix]);
			}
		}
		buckets.add(temp);
		//System.out.println(buckets.toString());
		
		if(phraseValid == true)
		{
			String[] rArr = buckets.toArray(new String[buckets.size()]);
			return rArr;
			
		}
		else
		{
			String[] rArr = {""};
			return rArr;
		}
	}
}
