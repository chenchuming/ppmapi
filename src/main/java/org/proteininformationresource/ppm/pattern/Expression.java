package org.proteininformationresource.ppm.pattern;

public  class Expression {
	
	private String leftParenthesis;
	private String rightParenthesis;
	private String input;
	private String psAC;
	private String psID;
	private String pattern;
	private String parsedPattern;
	
	
	


	/**
	 * 
	 */
	public Expression() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the input
	 */
	public String getInput() {
		return input;
	}


	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}


	/**
	 * @return the psAC
	 */
	public String getPsAC() {
		return psAC;
	}


	/**
	 * @param psAC the psAC to set
	 */
	public void setPsAC(String psAC) {
		this.psAC = psAC;
	}


	/**
	 * @return the psID
	 */
	public String getPsID() {
		return psID;
	}


	/**
	 * @param psID the psID to set
	 */
	public void setPsID(String psID) {
		this.psID = psID;
	}


	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}


	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	/**
	 * @return the parsedPattern
	 */
	public String getParsedPattern() {
		return parsedPattern;
	}


	/**
	 * @param parsedPattern the parsedPattern to set
	 */
	public void setParsedPattern(String parsedPattern) {
		this.parsedPattern = parsedPattern;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		result = prime * result + ((leftParenthesis == null) ? 0 : leftParenthesis.hashCode());
		result = prime * result + ((parsedPattern == null) ? 0 : parsedPattern.hashCode());
		result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
		result = prime * result + ((psAC == null) ? 0 : psAC.hashCode());
		result = prime * result + ((psID == null) ? 0 : psID.hashCode());
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
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Expression)) {
			return false;
		}
		Expression other = (Expression) obj;
		if (input == null) {
			if (other.input != null) {
				return false;
			}
		} else if (!input.equals(other.input)) {
			return false;
		}
		if (leftParenthesis == null) {
			if (other.leftParenthesis != null) {
				return false;
			}
		} else if (!leftParenthesis.equals(other.leftParenthesis)) {
			return false;
		}
		if (parsedPattern == null) {
			if (other.parsedPattern != null) {
				return false;
			}
		} else if (!parsedPattern.equals(other.parsedPattern)) {
			return false;
		}
		if (pattern == null) {
			if (other.pattern != null) {
				return false;
			}
		} else if (!pattern.equals(other.pattern)) {
			return false;
		}
		if (psAC == null) {
			if (other.psAC != null) {
				return false;
			}
		} else if (!psAC.equals(other.psAC)) {
			return false;
		}
		if (psID == null) {
			if (other.psID != null) {
				return false;
			}
		} else if (!psID.equals(other.psID)) {
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
		return "Expression [leftParenthesis=" + leftParenthesis + ", rightParenthesis=" + rightParenthesis + ", input=" + input + ", psAC=" + psAC + ", psID="
				+ psID + ", pattern=" + pattern + ", parsedPattern=" + parsedPattern + "]";
	}


	public String toHTML() {
		String str = "";
		String htmlInput = input;
		htmlInput = htmlInput.replace("<", "&lt;").replace(">", "&gt;");
		if(leftParenthesis != null) {
			str += " ( ";
		}
		if(psAC != null) {
			str += "<a href=\"https://prosite.expasy.org/"+ psAC+"\" data-toggle=\"tooltip\" title=\""+pattern+"\" target=\"_blank\">"+htmlInput+"</a>";
		}
		else {
			str += htmlInput;
		}
		if(rightParenthesis != null) {
			str += " ) ";
		}
		return str;
	}
	public String toSolrQuery() {
		String str = "";
		if(leftParenthesis != null) {
			str += " ( ";
		}
		str += parsedPattern;
		if(rightParenthesis != null) {
			str += " ) ";
		}
		return str;
	}
	
	
	
}
