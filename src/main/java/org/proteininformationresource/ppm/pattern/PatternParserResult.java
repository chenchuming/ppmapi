package org.proteininformationresource.ppm.pattern;

import org.proteininformationresource.ppm.support.web.Message;

public class PatternParserResult {
	private String parsedPattern;
	
	private String inputPattern;
	
	private Message log;

	/**
	 * 
	 */
	public PatternParserResult() {
		super();
		// TODO Auto-generated constructor stub
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

	/**
	 * @return the inputPattern
	 */
	public String getInputPattern() {
		return inputPattern;
	}

	/**
	 * @param inputPattern the inputPattern to set
	 */
	public void setInputPattern(String inputPattern) {
		this.inputPattern = inputPattern;
	}

	/**
	 * @return the log
	 */
	public Message getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(Message log) {
		this.log = log;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inputPattern == null) ? 0 : inputPattern.hashCode());
		result = prime * result + ((log == null) ? 0 : log.hashCode());
		result = prime * result + ((parsedPattern == null) ? 0 : parsedPattern.hashCode());
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
		if (!(obj instanceof PatternParserResult)) {
			return false;
		}
		PatternParserResult other = (PatternParserResult) obj;
		if (inputPattern == null) {
			if (other.inputPattern != null) {
				return false;
			}
		} else if (!inputPattern.equals(other.inputPattern)) {
			return false;
		}
		if (log == null) {
			if (other.log != null) {
				return false;
			}
		} else if (!log.equals(other.log)) {
			return false;
		}
		if (parsedPattern == null) {
			if (other.parsedPattern != null) {
				return false;
			}
		} else if (!parsedPattern.equals(other.parsedPattern)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PatternParserResult [parsedPattern=" + parsedPattern + ", inputPattern=" + inputPattern + ", log=" + log + "]";
	}

	
	
}
