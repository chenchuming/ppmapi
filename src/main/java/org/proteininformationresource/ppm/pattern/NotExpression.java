package org.proteininformationresource.ppm.pattern;


public class NotExpression extends UnaryOperatorExpression {
	
	private String leftParenthesis;
	private String rightParenthesis;
	private Expression expression;
	
	
	
	/**
	 * 
	 */
	public NotExpression() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param leftParenthesis
	 * @param rightParenthesis
	 * @param expression
	 */
	public NotExpression(String leftParenthesis, String rightParenthesis, Expression expression) {
		super();
		this.leftParenthesis = leftParenthesis;
		this.rightParenthesis = rightParenthesis;
		this.expression = expression;
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
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}
	/**
	 * @param expression the expression to set
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((leftParenthesis == null) ? 0 : leftParenthesis.hashCode());
		result = prime * result + ((rightParenthesis == null) ? 0 : rightParenthesis.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof NotExpression)) {
			return false;
		}
		NotExpression other = (NotExpression) obj;
		if (expression == null) {
			if (other.expression != null) {
				return false;
			}
		} else if (!expression.equals(other.expression)) {
			return false;
		}
		if (leftParenthesis == null) {
			if (other.leftParenthesis != null) {
				return false;
			}
		} else if (!leftParenthesis.equals(other.leftParenthesis)) {
			return false;
		}
		if (rightParenthesis == null) {
			if (other.rightParenthesis != null) {
				return false;
			}
		} else if (!rightParenthesis.equals(other.rightParenthesis)) {
			return false;
		}
		return true;
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
		if(expression instanceof AndExpression || expression instanceof OrExpression) {
			str += " NOT ";
		}
		else {
			str += "-";
		}
		str += expression;
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
		str += " not ";
		str += expression.toHTML();
		if(rightParenthesis != null) {
			str += " ) ";
		}
		return str;
	}
	
	public String toSolrQuery() {
		String str = "";
		if(leftParenthesis != null) {
			str += "( ";
		}
		str += " NOT ";
		str += expression.toSolrQuery();
		if(rightParenthesis != null) {
			str += ") ";
		}
		return str;
	}
	
	

}
