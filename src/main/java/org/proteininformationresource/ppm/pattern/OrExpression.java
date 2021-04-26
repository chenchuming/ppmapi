package org.proteininformationresource.ppm.pattern;



public class OrExpression extends BinaryExpression{
	
	/**
	 * 
	 */
	public OrExpression() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String leftParenthesis;
	private String rightParenthesis;
	private Expression leftExpression;
	private Expression rightExpression;
	
	
	
	/**
	 * @param leftParenthesis
	 * @param rightParenthesis
	 * @param leftExpression
	 * @param rightExpression
	 */
	public OrExpression(String leftParenthesis, String rightParenthesis, Expression leftExpression, Expression rightExpression) {
		super();
		this.leftParenthesis = leftParenthesis;
		this.rightParenthesis = rightParenthesis;
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	/**
	 * @return the leftParenthesis
	 */
	public String getLeftParenthesis() {
		return leftParenthesis;
	}
	/**
	 * @param leftParenthesis the leftParenthesis to set
	 */
	public void setLeftParenthesis(String leftParenthesis) {
		this.leftParenthesis = leftParenthesis;
	}
	/**
	 * @return the rightParenthesis
	 */
	public String getRightParenthesis() {
		return rightParenthesis;
	}
	/**
	 * @param rightParenthesis the rightParenthesis to set
	 */
	public void setRightParenthesis(String rightParenthesis) {
		this.rightParenthesis = rightParenthesis;
	}
	/**
	 * @return the leftExpression
	 */
	public Expression getLeftExpression() {
		return leftExpression;
	}
	/**
	 * @param leftExpression the leftExpression to set
	 */
	public void setLeftExpression(Expression leftExpression) {
		this.leftExpression = leftExpression;
	}
	/**
	 * @return the rightExpression
	 */
	public Expression getRightExpression() {
		return rightExpression;
	}
	/**
	 * @param rightExpression the rightExpression to set
	 */
	public void setRightExpression(Expression rightExpression) {
		this.rightExpression = rightExpression;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "";
		if(leftParenthesis != null) {
			str += " ( ";
		}
		str += leftExpression;
		str += " or ";
		str += rightExpression;
		if(rightParenthesis != null) {
			str += " ) ";
		}
		return str;
	}
	
	public String toHTML() {
		String str = "";
		if(leftParenthesis != null) {
			str += " ( ";
		}
		str += leftExpression.toHTML();
		str += " or ";
		str += rightExpression.toHTML();
		if(rightParenthesis != null) {
			str += " ) ";
		}
		return str;
	}
	
	public String toSolrQuery() {
		String str = "";
		if(leftParenthesis != null) {
			str += "(";
		}
		str += leftExpression.toSolrQuery();
		str += " OR ";
		str += rightExpression.toSolrQuery();
		if(rightParenthesis != null) {
			str += ")";
		}
		return str;
	}

}
