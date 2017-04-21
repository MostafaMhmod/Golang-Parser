import java.lang.System;
import java.io.*;
import java.util.Stack;
class Lexer {
	Yylex tokenizer;
	public  Lexer(String fileName) 
	{
	  try
	  {
	  tokenizer=new Yylex(new BufferedReader(new FileReader(fileName)));
	  }
	  catch(Exception e)
	  {
	  }	 
	}
	public Token nextToken()
	{
		Token next=null;
		try
		{
		 next=  tokenizer.getToken();
		}
		catch(Exception e)
		{
		}
		return next;
	}
	}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

	//initialize  variables to be used by class
	//boolean existAnError=false;
	public static Stack<String> errorTrack = new Stack<String>();
	public static boolean checkForError(String compare) {
		if (errorTrack.peek().equals(compare))
			return true;
		else
			return false;
	}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

	//Add code to be executed on initialization of the lexer
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NO_ANCHOR,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NOT_ACCEPT,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"5:9,3,1,5:2,44,5:18,2,31,43,5:2,48,35,5,49,50,47,36,30,37,22,4,41,40:9,26,2" +
"7,32,19,33,5:2,42:26,53,45,54,5,42,46,7,38,8,28,11,20,10,25,12,42,9,29,13,1" +
"7,14,6,42,15,18,16,21,23,24,42,39,42,51,34,52,5:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,124,
"0,1:3,2,1,3,4,5,6,1:2,4,7,8,9,10,11,12,1:8,13,14,15,1:8,16,1,14,1,14:7,17,1" +
"4:6,18,19,20,21,22,19,17,23,22,1,23,24,25,26,16,27,17,28,29,30,31,32,33,34," +
"35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59," +
"60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,14,77")[0];

	private int yy_nxt[][] = unpackFromString(78,55,
"1,2,56,3,4,5,6,122,100,122:2,101,58,122:2,118,102,122,119,7,84,122,8,87,122" +
":2,9,10,123,122,11,12,13,59,14,15,16,17,111,122,18,60,122,57,-1,5,63,19,20," +
"21,22,23,24,25,26,-1:59,27,-1:56,122,120,122:11,-1,122:2,-1,122:3,-1:2,122:" +
"2,-1:8,122:2,64:2,122,-1:31,65,-1:57,29,-1:51,30,-1:54,65,-1:12,31,-1:4,32," +
"-1:51,34,-1:55,35,-1:55,36,-1:55,37,-1:57,18:2,-1:15,27:42,-1,27:10,-1:6,12" +
"2:13,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:34,41,-1:34,70:10," +
"86,70:42,-1:2,70:10,86,70:30,49,70:11,-1:2,56,-1:54,61:10,85,61:30,38,61:11" +
",-1:6,122:7,112,122:5,-1,28,122,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:" +
"31,65,-1:13,33,-1:61,64:2,-1:15,66:44,39,66:8,-1:6,122:9,40,122:3,-1,122:2," +
"-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:14,61:10,85,61:30,62,61:11,-1:6," +
"122:9,42,122:3,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:5" +
",43,122:7,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:5,44,1" +
"22:7,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:5,45,122:7," +
"-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:2,46,122:10,-1,1" +
"22:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:10,47,122:2,-1,122:2" +
",-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:3,48,122:9,-1,122:2,-1,1" +
"22:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:10,50,122:2,-1,122:2,-1,122:3" +
",-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:11,51,122,-1,122:2,-1,122:3,-1:2," +
"122:2,-1:8,122:2,64:2,122,-1:18,122:10,52,122:2,-1,122:2,-1,122:3,-1:2,122:" +
"2,-1:8,122:2,64:2,122,-1:18,122:13,-1,122:2,-1,122:2,53,-1:2,122:2,-1:8,122" +
":2,64:2,122,-1:18,122:5,54,122:7,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64" +
":2,122,-1:18,122:10,55,122:2,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,1" +
"22,-1:18,122:8,67,122:4,-1,122,91,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-" +
"1:14,61:10,85,61:30,38,61,68,61:9,-1:2,70:10,86,70:32,72,70:9,-1:6,122,69,1" +
"22:11,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:12,71,-1,1" +
"22:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:12,73,-1,122:2,-1,12" +
"2:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,74,122:12,-1,122:2,-1,122:3,-1:2,1" +
"22:2,-1:8,122:2,64:2,122,-1:18,122:11,75,122,-1,122:2,-1,122:3,-1:2,122:2,-" +
"1:8,122:2,64:2,122,-1:18,122:12,76,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2," +
"64:2,122,-1:18,122,77,122:11,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,1" +
"22,-1:18,122:9,78,122:3,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1" +
":18,122:9,79,122:3,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,1" +
"22:2,80,122:10,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:2" +
",81,122:10,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:4,82," +
"122:8,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:13,-1,122:" +
"2,-1,122:3,-1:2,122,83,-1:8,122:2,64:2,122,-1:18,122,88,122:6,103,122:4,-1," +
"122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:13,-1,122:2,-1,122:" +
"3,-1:2,122,89,-1:8,122:2,64:2,122,-1:18,122:13,-1,122:2,-1,122:3,-1:2,122:2" +
",-1:8,122,90,64:2,122,-1:18,122:11,92,122,-1,122:2,-1,122:3,-1:2,122:2,-1:8" +
",122:2,64:2,122,-1:18,122:5,93,122:7,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:" +
"2,64:2,122,-1:18,122:8,94,122:4,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:" +
"2,122,-1:18,122:13,-1,122,95,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18," +
"122:13,-1,122,96,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:10,97,12" +
"2:2,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122,98,122:11,-1" +
",122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:13,-1,122,99,-1,12" +
"2:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:9,104,122:3,-1,122:2,-1,122:3," +
"-1:2,122:2,-1:8,122:2,64:2,122,-1:18,105,122:12,-1,122:2,-1,122:3,-1:2,122:" +
"2,-1:8,122:2,64:2,122,-1:18,122:10,106,122:2,-1,122:2,-1,122:3,-1:2,122:2,-" +
"1:8,122:2,64:2,122,-1:18,122:9,107,122:3,-1,122:2,-1,122:3,-1:2,122:2,-1:8," +
"122:2,64:2,122,-1:18,122:6,108,122:6,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:" +
"2,64:2,122,-1:18,122:3,109,122:9,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64" +
":2,122,-1:18,122,110,122:11,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,12" +
"2,-1:18,122:5,113,122:7,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1" +
":18,122:10,114,122:2,-1,122:2,-1,122,115,122,-1:2,122:2,-1:8,122:2,64:2,122" +
",-1:18,122:2,116,122:10,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1" +
":18,122:13,-1,117,122,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:18,122:5,1" +
"21,122:7,-1,122:2,-1,122:3,-1:2,122:2,-1:8,122:2,64:2,122,-1:12");

	public Token getToken ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

	//Add code to be executed when the end of the file is reached
	if(errorTrack.isEmpty())
	{
	return (new Token(Token.EOF,"Done"));
	}
	else if (!errorTrack.isEmpty())
	{
	return (new Token(Token.EOF,"There is some "+ errorTrack.peek() +" that is not closed"));
	}
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 0:
						{}
					case -2:
						break;
					case 1:
						
					case -3:
						break;
					case 2:
						{}
					case -4:
						break;
					case 3:
						{}
					case -5:
						break;
					case 4:
						{ return (new Token(Token.SLASH,yytext()));}
					case -6:
						break;
					case 5:
						{
	int lineValue=yyline;
	lineValue++;
  return new Token(Token.ERROR, "Invalid input: " + yytext() + " in line " + lineValue + "");
}
					case -7:
						break;
					case 6:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -8:
						break;
					case 7:
						{ return (new Token(Token.EQUAL,yytext()));}
					case -9:
						break;
					case 8:
						{ return (new Token(Token.DOT,yytext()));}
					case -10:
						break;
					case 9:
						{ return (new Token(Token.COLON,yytext()));}
					case -11:
						break;
					case 10:
						{ return (new Token(Token.SEMI_COLON,yytext()));}
					case -12:
						break;
					case 11:
						{ return (new Token(Token.COMMA,yytext()));}
					case -13:
						break;
					case 12:
						{ return (new Token(Token.EXCLAMATION,yytext()));}
					case -14:
						break;
					case 13:
						{ return (new Token(Token.REL_OP,yytext()));}
					case -15:
						break;
					case 14:
						{ return (new Token(Token.BAR,yytext()));}
					case -16:
						break;
					case 15:
						{ return (new Token(Token.AMBERSAND,yytext()));}
					case -17:
						break;
					case 16:
						{ return (new Token(Token.PLUS,yytext()));}
					case -18:
						break;
					case 17:
						{ return (new Token(Token.MINUS,yytext()));}
					case -19:
						break;
					case 18:
						{ return (new Token(Token.INT_LIT,yytext()));}
					case -20:
						break;
					case 19:
						{ return (new Token(Token.ASTRISK,yytext()));}
					case -21:
						break;
					case 20:
						{ return (new Token(Token.PERCENT,yytext()));}
					case -22:
						break;
					case 21:
						{
	errorTrack.push("(");
	return (new Token(Token.OPEN_PARAN,yytext()));
}
					case -23:
						break;
					case 22:
						{ 
	int lineValue=yyline;
	lineValue++;
	if(checkForError("(")&&(!errorTrack.isEmpty()))
	{
	  errorTrack.pop();
	  return (new Token(Token.CLOSE_PARAN,yytext()));
	}
	else if(((!errorTrack.isEmpty())&&(checkForError("(")==false)))
	{
	   return new Token(Token.ERROR, ") has no matching ( in line" + " "+ lineValue + "");
	}
	else
	{
	   return new Token(Token.ERROR, "You have a missing bracket in line" + " "+ lineValue + "");
	}
}
					case -24:
						break;
					case 23:
						{
	errorTrack.push("{"); 
	return (new Token(Token.OPEN_CURLY,yytext()));
	}
					case -25:
						break;
					case 24:
						{ 
	int lineValue=yyline;
	lineValue++;
	if(checkForError("{")&&(!errorTrack.isEmpty()))
	{
	  errorTrack.pop();
	  return (new Token(Token.CLOSE_CURLY,yytext()));
	}
	if(checkForError("(")||checkForError("["))
	{
	   return new Token(Token.ERROR, "You have a missing bracket in line" + " "+ lineValue + "");
	}
	else if(((!errorTrack.isEmpty())&&(checkForError("{")==false)))
	{
	   return new Token(Token.ERROR, "} has no matching { in line" + " "+ lineValue + "");
	}
}
					case -26:
						break;
					case 25:
						{ 
	errorTrack.push("["); 
	return (new Token(Token.OPEN_SQUARE,yytext()));
}
					case -27:
						break;
					case 26:
						{ 
	if(checkForError("[")&&(!errorTrack.isEmpty()))
	{
	  errorTrack.pop();
	  return (new Token(Token.CLOSE_SQUARE,yytext()));
	}
	else if(errorTrack.isEmpty())
	{
	   return new Token(Token.ERROR, "] has no matching [ in line" + " "+ yyline + "");
	}
	else if(((!checkForError("["))&&(!errorTrack.isEmpty())))
	{
	   return new Token(Token.ERROR, "You have a missing bracket in line" + " "+ yyline + "");
	}
	}
					case -28:
						break;
					case 27:
						{}
					case -29:
						break;
					case 28:
						{ return (new Token(Token.IF,yytext()));}
					case -30:
						break;
					case 30:
						{ return (new Token(Token.COLON_EQUAL,yytext()));}
					case -31:
						break;
					case 31:
						{ return (new Token(Token.SHIFT_LEFT,yytext()));}
					case -32:
						break;
					case 32:
						{ return (new Token(Token.LESS_DASH,yytext()));}
					case -33:
						break;
					case 33:
						{ return (new Token(Token.SHIFT_RIGHT,yytext()));}
					case -34:
						break;
					case 34:
						{ return (new Token(Token.OR_OP,yytext()));}
					case -35:
						break;
					case 35:
						{ return (new Token(Token.AND_OP,yytext()));}
					case -36:
						break;
					case 36:
						{ return (new Token(Token.INCREMENT,yytext()));}
					case -37:
						break;
					case 37:
						{ return (new Token(Token.DECREMENT,yytext()));}
					case -38:
						break;
					case 38:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -39:
						break;
					case 39:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -40:
						break;
					case 40:
						{ return (new Token(Token.FOR,yytext()));}
					case -41:
						break;
					case 41:
						{ return (new Token(Token.CDOTS,yytext()));}
					case -42:
						break;
					case 42:
						{ return (new Token(Token.VAR,yytext()));}
					case -43:
						break;
					case 43:
						{ return (new Token(Token.CASE,yytext()));}
					case -44:
						break;
					case 44:
						{ return (new Token(Token.ELSE,yytext()));}
					case -45:
						break;
					case 45:
						{ return (new Token(Token.TYPE,yytext()));}
					case -46:
						break;
					case 46:
						{ return (new Token(Token.FUNC,yytext()));}
					case -47:
						break;
					case 47:
						{ return (new Token(Token.CONST,yytext()));}
					case -48:
						break;
					case 48:
						{ return (new Token(Token.BREAK,yytext()));}
					case -49:
						break;
					case 49:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -50:
						break;
					case 50:
						{ return (new Token(Token.IMPORT,yytext()));}
					case -51:
						break;
					case 51:
						{ return (new Token(Token.RETURN,yytext()));}
					case -52:
						break;
					case 52:
						{ return (new Token(Token.STRUCT,yytext()));}
					case -53:
						break;
					case 53:
						{ return (new Token(Token.SWITCH,yytext()));}
					case -54:
						break;
					case 54:
						{ return (new Token(Token.PACKAGE,yytext()));}
					case -55:
						break;
					case 55:
						{ return (new Token(Token.DEFAULT,yytext()));}
					case -56:
						break;
					case 56:
						{}
					case -57:
						break;
					case 57:
						{
	int lineValue=yyline;
	lineValue++;
  return new Token(Token.ERROR, "Invalid input: " + yytext() + " in line " + lineValue + "");
}
					case -58:
						break;
					case 58:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -59:
						break;
					case 59:
						{ return (new Token(Token.REL_OP,yytext()));}
					case -60:
						break;
					case 60:
						{ return (new Token(Token.INT_LIT,yytext()));}
					case -61:
						break;
					case 62:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -62:
						break;
					case 63:
						{
	int lineValue=yyline;
	lineValue++;
  return new Token(Token.ERROR, "Invalid input: " + yytext() + " in line " + lineValue + "");
}
					case -63:
						break;
					case 64:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -64:
						break;
					case 65:
						{ return (new Token(Token.REL_OP,yytext()));}
					case -65:
						break;
					case 67:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -66:
						break;
					case 69:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -67:
						break;
					case 71:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -68:
						break;
					case 73:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -69:
						break;
					case 74:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -70:
						break;
					case 75:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -71:
						break;
					case 76:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -72:
						break;
					case 77:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -73:
						break;
					case 78:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -74:
						break;
					case 79:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -75:
						break;
					case 80:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -76:
						break;
					case 81:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -77:
						break;
					case 82:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -78:
						break;
					case 83:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -79:
						break;
					case 84:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -80:
						break;
					case 87:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -81:
						break;
					case 88:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -82:
						break;
					case 89:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -83:
						break;
					case 90:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -84:
						break;
					case 91:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -85:
						break;
					case 92:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -86:
						break;
					case 93:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -87:
						break;
					case 94:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -88:
						break;
					case 95:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -89:
						break;
					case 96:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -90:
						break;
					case 97:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -91:
						break;
					case 98:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -92:
						break;
					case 99:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -93:
						break;
					case 100:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -94:
						break;
					case 101:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -95:
						break;
					case 102:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -96:
						break;
					case 103:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -97:
						break;
					case 104:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -98:
						break;
					case 105:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -99:
						break;
					case 106:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -100:
						break;
					case 107:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -101:
						break;
					case 108:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -102:
						break;
					case 109:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -103:
						break;
					case 110:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -104:
						break;
					case 111:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -105:
						break;
					case 112:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -106:
						break;
					case 113:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -107:
						break;
					case 114:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -108:
						break;
					case 115:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -109:
						break;
					case 116:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -110:
						break;
					case 117:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -111:
						break;
					case 118:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -112:
						break;
					case 119:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -113:
						break;
					case 120:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -114:
						break;
					case 121:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -115:
						break;
					case 122:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -116:
						break;
					case 123:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -117:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
