package org.proteininformationresource.ppm.service;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PaginatedResultParam extends QueryParam {
	private Integer start;
	
	private Integer limit;
	
	private Integer currentPage;
	
	private Integer totalMatch;
	
//	private List<SortOrder> orderBy;

	private String sortBy;
	
	private String selectedIDs;
	
	
	/**
	 * 
	 */
	public PaginatedResultParam() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}


	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}


	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}


	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}


	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}


	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	/**
	 * @return the totalMatch
	 */
	public Integer getTotalMatch() {
		return totalMatch;
	}


	/**
	 * @param totalMatch the totalMatch to set
	 */
	public void setTotalMatch(Integer totalMatch) {
		this.totalMatch = totalMatch;
	}


	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}


	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}


	/**
	 * @return the selectedIDs
	 */
	public String getSelectedIDs() {
		return selectedIDs;
	}


	/**
	 * @param selectedIDs the selectedIDs to set
	 */
	public void setSelectedIDs(String selectedIDs) {
		this.selectedIDs = selectedIDs;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaginatedResultParam [start=" + start + ", limit=" + limit + ", currentPage=" + currentPage + ", totalMatch=" + totalMatch + ", sortBy="
				+ sortBy + ", selectedIDs=" + selectedIDs + "]";
	}


	public String toQueryString() {
		String query = "";
		query += super.toQueryString().substring(1);
		if(start != null) {
			query += "&start=" + start;
		}
		if(limit != null) {
			query += "&limit=" + limit;
		}
		if(currentPage != null) {
			query += "&currentPage=" + currentPage;
		}
		if(totalMatch != null) {
			query += "&totalMatch=" + totalMatch;
		}
		if(sortBy != null) {
			query += "&sortBy="+ sortBy;
		}
		if(selectedIDs != null) {
			query +=  "&selectedIDs=" + selectedIDs;
		}
		return query;
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((currentPage == null) ? 0 : currentPage.hashCode());
		result = prime * result + ((limit == null) ? 0 : limit.hashCode());
		result = prime * result + ((selectedIDs == null) ? 0 : selectedIDs.hashCode());
		result = prime * result + ((sortBy == null) ? 0 : sortBy.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((totalMatch == null) ? 0 : totalMatch.hashCode());
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
		if (!(obj instanceof PaginatedResultParam)) {
			return false;
		}
		PaginatedResultParam other = (PaginatedResultParam) obj;
		if (currentPage == null) {
			if (other.currentPage != null) {
				return false;
			}
		} else if (!currentPage.equals(other.currentPage)) {
			return false;
		}
		if (limit == null) {
			if (other.limit != null) {
				return false;
			}
		} else if (!limit.equals(other.limit)) {
			return false;
		}
		if (selectedIDs == null) {
			if (other.selectedIDs != null) {
				return false;
			}
		} else if (!selectedIDs.equals(other.selectedIDs)) {
			return false;
		}
		if (sortBy == null) {
			if (other.sortBy != null) {
				return false;
			}
		} else if (!sortBy.equals(other.sortBy)) {
			return false;
		}
		if (start == null) {
			if (other.start != null) {
				return false;
			}
		} else if (!start.equals(other.start)) {
			return false;
		}
		if (totalMatch == null) {
			if (other.totalMatch != null) {
				return false;
			}
		} else if (!totalMatch.equals(other.totalMatch)) {
			return false;
		}
		return true;
	}

	

}
