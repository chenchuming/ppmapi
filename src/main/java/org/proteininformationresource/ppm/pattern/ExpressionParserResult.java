package org.proteininformationresource.ppm.pattern;

import org.proteininformationresource.ppm.support.web.Message;

public class ExpressionParserResult {
	
	String inputExpression;
	
	Expression expression;
	
	Message message;

	
	/**
	 * 
	 */
	public ExpressionParserResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the inputExpression
	 */
	public String getInputExpression() {
		return inputExpression;
	}

	/**
	 * @param inputExpression the inputExpression to set
	 */
	public void setInputExpression(String inputExpression) {
		this.inputExpression = inputExpression;
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

	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((inputExpression == null) ? 0 : inputExpression.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ExpressionParserResult)) {
			return false;
		}
		ExpressionParserResult other = (ExpressionParserResult) obj;
		if (expression == null) {
			if (other.expression != null) {
				return false;
			}
		} else if (!expression.equals(other.expression)) {
			return false;
		}
		if (inputExpression == null) {
			if (other.inputExpression != null) {
				return false;
			}
		} else if (!inputExpression.equals(other.inputExpression)) {
			return false;
		}
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExpressionParserResult [inputExpression=" + inputExpression + ", expression=" + expression + ", message=" + message + "]";
	}
	
	

}
