package org.proteininformationresource.ppm.pattern;

public class BinaryExpression extends Expression {
	Expression leftExpression;
	
	Expression rightExpression;

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
		result = prime * result + ((rightExpression == null) ? 0 : rightExpression.hashCode());
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
		if (!(obj instanceof BinaryExpression)) {
			return false;
		}
		BinaryExpression other = (BinaryExpression) obj;
		if (leftExpression == null) {
			if (other.leftExpression != null) {
				return false;
			}
		} else if (!leftExpression.equals(other.leftExpression)) {
			return false;
		}
		if (rightExpression == null) {
			if (other.rightExpression != null) {
				return false;
			}
		} else if (!rightExpression.equals(other.rightExpression)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BinaryOperatorExpression [leftExpression=" + leftExpression + ", rightExpression=" + rightExpression + "]";
	}

	
	

}
