package org.proteininformationresource.ppm.service;

import org.springframework.stereotype.Component;

@Component
public class QueryParam {
	private String pattern;
	
	private boolean swissProt;
	
	private boolean trEMBL;
	
	private boolean isoform;
	
	private boolean uniref100;
	
	private boolean leqi;

	private String taxonName;
	
	private String taxonId;
	
	private String taxonGroupName;
	
	private String taxonGroupId;
	
	private String totalCount;
	
	private String uniref100Count;
	
	private String spCount;
	
	private String trCount;
	
	private String isoformCount;
	
	private String releaseNumber;
	
	

	/**
	 * 
	 */
	public QueryParam() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the swissProt
	 */
	public boolean isSwissProt() {
		return swissProt;
	}



	/**
	 * @param swissProt the swissProt to set
	 */
	public void setSwissProt(boolean swissProt) {
		this.swissProt = swissProt;
	}



	/**
	 * @return the trEMBL
	 */
	public boolean isTrEMBL() {
		return trEMBL;
	}



	/**
	 * @param trEMBL the trEMBL to set
	 */
	public void setTrEMBL(boolean trEMBL) {
		this.trEMBL = trEMBL;
	}



	/**
	 * @return the isoform
	 */
	public boolean isIsoform() {
		return isoform;
	}



	/**
	 * @param isoform the isoform to set
	 */
	public void setIsoform(boolean isoform) {
		this.isoform = isoform;
	}



	/**
	 * @return the uniref100
	 */
	public boolean isUniref100() {
		return uniref100;
	}



	/**
	 * @param uniref100 the uniref100 to set
	 */
	public void setUniref100(boolean uniref100) {
		this.uniref100 = uniref100;
	}



	/**
	 * @return the leqi
	 */
	public boolean isLeqi() {
		return leqi;
	}



	/**
	 * @param leqi the leqi to set
	 */
	public void setLeqi(boolean leqi) {
		this.leqi = leqi;
	}



	/**
	 * @return the taxonName
	 */
	public String getTaxonName() {
		return taxonName;
	}



	/**
	 * @param taxonName the taxonName to set
	 */
	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}



	/**
	 * @return the taxonId
	 */
	public String getTaxonId() {
		return taxonId;
	}



	/**
	 * @param taxonId the taxonId to set
	 */
	public void setTaxonId(String taxonId) {
		this.taxonId = taxonId;
	}



	/**
	 * @return the taxonGroupName
	 */
	public String getTaxonGroupName() {
		return taxonGroupName;
	}



	/**
	 * @param taxonGroupName the taxonGroupName to set
	 */
	public void setTaxonGroupName(String taxonGroupName) {
		this.taxonGroupName = taxonGroupName;
	}



	/**
	 * @return the taxonGroupId
	 */
	public String getTaxonGroupId() {
		return taxonGroupId;
	}



	/**
	 * @param taxonGroupId the taxonGroupId to set
	 */
	public void setTaxonGroupId(String taxonGroupId) {
		this.taxonGroupId = taxonGroupId;
	}



	/**
	 * @return the totalCount
	 */
	public String getTotalCount() {
		return totalCount;
	}



	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}



	/**
	 * @return the uniref100Count
	 */
	public String getUniref100Count() {
		return uniref100Count;
	}



	/**
	 * @param uniref100Count the uniref100Count to set
	 */
	public void setUniref100Count(String uniref100Count) {
		this.uniref100Count = uniref100Count;
	}



	/**
	 * @return the spCount
	 */
	public String getSpCount() {
		return spCount;
	}



	/**
	 * @param spCount the spCount to set
	 */
	public void setSpCount(String spCount) {
		this.spCount = spCount;
	}



	/**
	 * @return the trCount
	 */
	public String getTrCount() {
		return trCount;
	}



	/**
	 * @param trCount the trCount to set
	 */
	public void setTrCount(String trCount) {
		this.trCount = trCount;
	}



	/**
	 * @return the isoformCount
	 */
	public String getIsoformCount() {
		return isoformCount;
	}



	/**
	 * @param isoformCount the isoformCount to set
	 */
	public void setIsoformCount(String isoformCount) {
		this.isoformCount = isoformCount;
	}



	/**
	 * @return the releaseNumber
	 */
	public String getReleaseNumber() {
		return releaseNumber;
	}



	/**
	 * @param releaseNumber the releaseNumber to set
	 */
	public void setReleaseNumber(String releaseNumber) {
		this.releaseNumber = releaseNumber;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryParam [pattern=" + pattern + ", swissProt=" + swissProt + ", trEMBL=" + trEMBL + ", isoform=" + isoform + ", uniref100=" + uniref100
				+ ", leqi=" + leqi + ", taxonName=" + taxonName + ", taxonId=" + taxonId + ", taxonGroupName=" + taxonGroupName + ", taxonGroupId="
				+ taxonGroupId + ", totalCount=" + totalCount + ", uniref100Count=" + uniref100Count + ", spCount=" + spCount + ", trCount=" + trCount
				+ ", isoformCount=" + isoformCount + ", releaseNumber=" + releaseNumber + "]";
	}

	
	public String toQueryString() {
		String query = "";
		if(pattern != null && pattern.length() > 0) {
			query += "&pattern=" + pattern;
		}
		if(swissProt) {
			query += "&swissProt="+swissProt;
		}
		if(trEMBL) {
			query += "&trEMBL=" + trEMBL;
		}
		if(isoform) {
			query += "&isoform=" + isoform;
		}
		if(uniref100) {
				query += "&uniref100=" + uniref100;
		}
		if(leqi) {
			query += "&leqi=" + leqi ;
		}
		if(taxonName != null && taxonName.length() > 0) {
			query += "&taxonName=" + taxonName;
		}
		if(taxonId != null) {
			query += "&taxonId=" + taxonId;
		}
		if(taxonGroupName != null && taxonGroupName.length() > 0) {
			query += "&taxonGroupName=" + taxonGroupName;
		}
		if(taxonGroupId != null) {
				query += "&taxonGroupId=" + taxonGroupId;
		}
		if(totalCount != null) {
			query += "&totalCount=" + totalCount;
		}
		if(uniref100Count != null) {
			query += "&uniref100Count=" + uniref100Count;
		}
		if(spCount != null) {
			query += "&spCount=" + spCount;
		}
		if(trCount != null) {
			query += "&trCount=" + trCount;
		}
		if(isoformCount != null) {
			query += "&isoformCount=" + isoformCount;
		}
		if(releaseNumber != null) {
			query += "&releaseNumber=" + releaseNumber;
		}
		return query;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isoform ? 1231 : 1237);
		result = prime * result + ((isoformCount == null) ? 0 : isoformCount.hashCode());
		result = prime * result + (leqi ? 1231 : 1237);
		result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
		result = prime * result + ((releaseNumber == null) ? 0 : releaseNumber.hashCode());
		result = prime * result + ((spCount == null) ? 0 : spCount.hashCode());
		result = prime * result + (swissProt ? 1231 : 1237);
		result = prime * result + ((taxonGroupId == null) ? 0 : taxonGroupId.hashCode());
		result = prime * result + ((taxonGroupName == null) ? 0 : taxonGroupName.hashCode());
		result = prime * result + ((taxonId == null) ? 0 : taxonId.hashCode());
		result = prime * result + ((taxonName == null) ? 0 : taxonName.hashCode());
		result = prime * result + ((totalCount == null) ? 0 : totalCount.hashCode());
		result = prime * result + ((trCount == null) ? 0 : trCount.hashCode());
		result = prime * result + (trEMBL ? 1231 : 1237);
		result = prime * result + (uniref100 ? 1231 : 1237);
		result = prime * result + ((uniref100Count == null) ? 0 : uniref100Count.hashCode());
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
		if (!(obj instanceof QueryParam)) {
			return false;
		}
		QueryParam other = (QueryParam) obj;
		if (isoform != other.isoform) {
			return false;
		}
		if (isoformCount == null) {
			if (other.isoformCount != null) {
				return false;
			}
		} else if (!isoformCount.equals(other.isoformCount)) {
			return false;
		}
		if (leqi != other.leqi) {
			return false;
		}
		if (pattern == null) {
			if (other.pattern != null) {
				return false;
			}
		} else if (!pattern.equals(other.pattern)) {
			return false;
		}
		if (releaseNumber == null) {
			if (other.releaseNumber != null) {
				return false;
			}
		} else if (!releaseNumber.equals(other.releaseNumber)) {
			return false;
		}
		if (spCount == null) {
			if (other.spCount != null) {
				return false;
			}
		} else if (!spCount.equals(other.spCount)) {
			return false;
		}
		if (swissProt != other.swissProt) {
			return false;
		}
		if (taxonGroupId == null) {
			if (other.taxonGroupId != null) {
				return false;
			}
		} else if (!taxonGroupId.equals(other.taxonGroupId)) {
			return false;
		}
		if (taxonGroupName == null) {
			if (other.taxonGroupName != null) {
				return false;
			}
		} else if (!taxonGroupName.equals(other.taxonGroupName)) {
			return false;
		}
		if (taxonId == null) {
			if (other.taxonId != null) {
				return false;
			}
		} else if (!taxonId.equals(other.taxonId)) {
			return false;
		}
		if (taxonName == null) {
			if (other.taxonName != null) {
				return false;
			}
		} else if (!taxonName.equals(other.taxonName)) {
			return false;
		}
		if (totalCount == null) {
			if (other.totalCount != null) {
				return false;
			}
		} else if (!totalCount.equals(other.totalCount)) {
			return false;
		}
		if (trCount == null) {
			if (other.trCount != null) {
				return false;
			}
		} else if (!trCount.equals(other.trCount)) {
			return false;
		}
		if (trEMBL != other.trEMBL) {
			return false;
		}
		if (uniref100 != other.uniref100) {
			return false;
		}
		if (uniref100Count == null) {
			if (other.uniref100Count != null) {
				return false;
			}
		} else if (!uniref100Count.equals(other.uniref100Count)) {
			return false;
		}
		return true;
	}


}
