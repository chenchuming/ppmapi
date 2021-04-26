package org.proteininformationresource.ppm.pattern;

public class AndExpression extends BinaryExpression {
	
	private String leftParenthesis;
	private String rightParenthesis;
	private Expression leftExpression;
	private Expression rightExpression;
	
	
	/**
	 * 
	 */
	public AndExpression() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param leftParenthesis
	 * @param rightParenthesis
	 * @param leftExpression
	 * @param rightExpression
	 */
	public AndExpression(String leftParenthesis, String rightParenthesis, Expression leftExpression, Expression rightExpression) {
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((leftExpression == null) ? 0 : leftExpression.hashCode());
		result = prime * result + ((leftParenthesis == null) ? 0 : leftParenthesis.hashCode());
		result = prime * result + ((rightExpression == null) ? 0 : rightExpression.hashCode());
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
		if (!(obj instanceof AndExpression)) {
			return false;
		}
		AndExpression other = (AndExpression) obj;
		if (leftExpression == null) {
			if (other.leftExpression != null) {
				return false;
			}
		} else if (!leftExpression.equals(other.leftExpression)) {
			return false;
		}
		if (leftParenthesis == null) {
			if (other.leftParenthesis != null) {
				return false;
			}
		} else if (!leftParenthesis.equals(other.leftParenthesis)) {
			return false;
		}
		if (rightExpression == null) {
			if (other.rightExpression != null) {
				return false;
			}
		} else if (!rightExpression.equals(other.rightExpression)) {
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
		str += leftExpression;
		str += " and ";
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
		str += " and ";
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
		str += " AND ";
		str += rightExpression.toSolrQuery();
		if(rightParenthesis != null) {
			str += ")";
		}
		return str;
	}
	

}
