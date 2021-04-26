package org.proteininformationresource.ppm.pattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;
import java.util.TreeSet;

import org.proteininformationresource.ppm.support.web.Message;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class MyExpressionParser {
	
	
	//static String inputExpression = "<M-K-S  and not  ( <M-R-R-G or <MR-G ) or ( <M-D-R-G or <M-R-F )";
	//static String inputExpression = "not PS00004 and P-x(2)-G-E-S-G(2)-[AS]";
	static String inputExpression = "PS00004 and RK(2)-x-[ST]-T-D";
	static List<Message> messages = new ArrayList<Message>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//A combination of PROSITE accessions/identifiers e.g. PS50240 and PS50068, e.g. PS50240 and not ( PS00134 or PS00135 )
		//A combination of PROSITE accessions/identifiers and your own pattern e.g. PS50240 and P-x(2)-G-E-S-G(2)-[AS]
		
		Expression result  = parseExpression(inputExpression.replace("-", ""));
		if(messages.size() == 0) {
			System.out.println();
			System.out.println();
			System.out.println("{!complexphrase inOrder=true} seq: ("+result.toSolrQuery()+")");
		}
	}

	private static Expression parseExpression(String inputExpression) {
		Expression expression = null;
		//ExpressionParserResult expressionParserResult = new ExpressionParserResult();
		if(inputExpression.indexOf(" and ") != -1) {
			AndExpression andOperatorExpression = parseAndOperatorExpression(inputExpression);
			expression = andOperatorExpression;
		}
		else if(inputExpression.indexOf(" or ") != -1) {
			OrExpression orOperatorExpression = parseOrOperatorExpression(inputExpression);
			expression = orOperatorExpression;
		}
		else if(inputExpression.indexOf(" not ") != -1 || inputExpression.indexOf("not ") != -1) {
			NotExpression notOperatorExpression = parseNotOperatorExpression(inputExpression);
			expression = notOperatorExpression;
		}
		else {
			expression = new Expression();
			String ps = PSLookup(inputExpression);
			if(ps != null) {
				System.out.println("??"+inputExpression);
				expression.setInput(inputExpression);
				expression.setPattern(ps);
				expression.setParsedPattern(PatternParser.parsePattern(ps).getParsedPattern());
			}
			else {
				System.out.println("!!"+inputExpression);
				expression.setInput(inputExpression);
				expression.setPattern(inputExpression);
				expression.setParsedPattern(PatternParser.parsePattern(inputExpression).getParsedPattern());
			}
			
		}
		return expression;
		
	}

	private static NotExpression parseNotOperatorExpression(String inputExpression) {
		
		String leftParenthesis = null;
		String rightParenthesis = null;
		String line = inputExpression.trim();
		if(isBalanced(line) && line.startsWith("(") && line.endsWith(")")) {
			line = line.substring(1, line.length() - 1);
			line = line.trim();
			leftParenthesis = " ( ";
			rightParenthesis = " ) ";
		}
		line = line.replaceFirst("not", "");
		line = line.trim();
		System.out.println("NOT: "+ inputExpression);
		System.out.println("NOT right: "+ line);
		Expression notExpression = parseExpression(line);
		NotExpression notOperatorExpression = new NotExpression(leftParenthesis, leftParenthesis, notExpression);
		return notOperatorExpression;
	}

	private static OrExpression parseOrOperatorExpression(String inputExpression) {
		
		String leftParenthesis = null;
		String rightParenthesis = null;
		Expression leftExpression = null;
		Expression rightExpression = null;
		String line = inputExpression.trim();
		if(isBalanced(line) && line.startsWith("(") && line.endsWith(")")) {
			line = line.substring(1, line.length() - 1);
			line = line.trim();
		}
		String[] expressions = line.split(" or ", 2);
		System.out.println("OR: "+ inputExpression);
		System.out.println("OR Left: "+ expressions[0]);
		System.out.println("OR Right: "+ expressions[1]);
		leftExpression = parseExpression(expressions[0]);

		rightExpression = parseExpression(expressions[1]);
		
		
		OrExpression orOperatorExpression = new OrExpression(leftParenthesis, rightParenthesis, leftExpression, rightExpression);
		return orOperatorExpression;
	}

	private static AndExpression parseAndOperatorExpression(String inputExpression) {
		
		String leftParenthesis = null;
		String rightParenthesis = null;
		Expression leftExpression = null;
		Expression rightExpression = null;
		String line = inputExpression.trim();
		if(isBalanced(line) && line.startsWith("(") && line.endsWith(")")) {
			line = line.substring(1, line.length() - 1);
			line = line.trim();
		}
		String[] expressions = line.split(" and ", 2);
		System.out.println("AND: "+ inputExpression);
		System.out.println("AND Left: "+ expressions[0]);
		System.out.println("AND Right: "+ expressions[1]);
		leftExpression = parseExpression(expressions[0]);

		rightExpression = parseExpression(expressions[1]);
		
		
		AndExpression andOperatorExpression = new AndExpression(leftParenthesis, rightParenthesis, leftExpression, rightExpression);
		//System.out.println(rightExpression);
		return andOperatorExpression;
		
	}

	private static boolean isBalanced(String expression)
	// Postcondition: A true return value indicates that the parentheses in the
	// given expression are balanced. Otherwise the return value is false.
	// Note that characters other than ( ) { } and [ ] are ignored.
	{
		// Meaningful names for characters
		final char LEFT_NORMAL = '(';
		final char RIGHT_NORMAL = ')';
		final char LEFT_CURLY = '{';
		final char RIGHT_CURLY = '}';
		final char LEFT_SQUARE = '[';
		final char RIGHT_SQUARE = ']';
		final char LEFT_ANGLE = '<';
		final char RIGHT_ANGLE = '>';

		Stack<Character> store = new Stack<Character>(); // Stores parens
		int i; // An index into the string
		boolean failed = false; // Change to true for a mismatch

		for (i = 0; !failed && (i < expression.length()); i++) {
			switch (expression.charAt(i)) {
			case LEFT_NORMAL:
			case LEFT_CURLY:
			case LEFT_SQUARE:
			case LEFT_ANGLE:
				store.push(expression.charAt(i));
				break;
			case RIGHT_NORMAL:
				if (store.isEmpty() || (store.pop() != LEFT_NORMAL))
					failed = true;
				break;
			case RIGHT_CURLY:
				if (store.isEmpty() || (store.pop() != LEFT_CURLY))
					failed = true;
				break;
			case RIGHT_SQUARE:
				if (store.isEmpty() || (store.pop() != LEFT_SQUARE))
					failed = true;
				break;
			case RIGHT_ANGLE:
				if (store.isEmpty() || (store.pop() != LEFT_ANGLE))
					failed = true;
				break;
			}
		}

		return (store.isEmpty() && !failed);
	}
	
	private static String PSLookup(String pattern) {
		String result = null;
		Resource resource = new ClassPathResource("/prosite.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			
			TreeSet<Object> keys = new TreeSet<Object>(props.keySet());
			for (Object key : keys) {
				
				String ps = (String) key;
				if(ps.equals(pattern)) {
					
					return (String) props.get(key);
					//System.out.println(key+"|"+result);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
