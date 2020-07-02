package parser;

public class LexerHelper {
	
	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {			System.out.println(e);
		}
		return -1;
	}

	public static char lexemeToChar(String str) {
		char[] chars = str.toCharArray();
		if(chars[1] != '\\'){

			return chars[1];
		}else{
			switch (chars[2]){
				case 'n':
					return '\n';
				case 't':
					return '\t';
				default:
					if(chars.length ==3)
						return chars[1];
					else
						return (char)Integer.parseInt(str.substring(2, str.length()-1));
			}
		}

	}
	public static double lexemeToReal(String str) {
		try {
			return Double.parseDouble(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

}
