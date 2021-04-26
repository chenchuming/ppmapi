package org.proteininformationresource.ppm.pattern;

public class ProSite {

	private String psAC;
	
	private String psID;
	
	private String psPattern;

	/**
	 * 
	 */
	public ProSite() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the psPattern
	 */
	public String getPsPattern() {
		return psPattern;
	}

	/**
	 * @param psPattern the psPattern to set
	 */
	public void setPsPattern(String psPattern) {
		this.psPattern = psPattern;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((psAC == null) ? 0 : psAC.hashCode());
		result = prime * result + ((psID == null) ? 0 : psID.hashCode());
		result = prime * result + ((psPattern == null) ? 0 : psPattern.hashCode());
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
		if (!(obj instanceof ProSite)) {
			return false;
		}
		ProSite other = (ProSite) obj;
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
		if (psPattern == null) {
			if (other.psPattern != null) {
				return false;
			}
		} else if (!psPattern.equals(other.psPattern)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProSite [psAC=" + psAC + ", psID=" + psID + ", psPattern=" + psPattern + "]";
	}
	
	
}
