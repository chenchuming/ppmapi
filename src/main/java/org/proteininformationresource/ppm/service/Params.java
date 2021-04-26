package org.proteininformationresource.ppm.service;

public class Params {
	private String q;
	private String hl;
	private int hlFragSize;
	private int start;
	private String hlFl;
	private int rows;
	private String wt;
	private String version;
	/**
	 * 
	 */
	public Params() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param q
	 * @param hl
	 * @param hlFragSize
	 * @param start
	 * @param hlFl
	 * @param rows
	 * @param wt
	 * @param version
	 */
	public Params(String q, String hl, int hlFragSize, int start, String hlFl, int rows, String wt, String version) {
		super();
		this.q = q;
		this.hl = hl;
		this.hlFragSize = hlFragSize;
		this.start = start;
		this.hlFl = hlFl;
		this.rows = rows;
		this.wt = wt;
		this.version = version;
	}
	/**
	 * @return the q
	 */
	public String getQ() {
		return q;
	}
	/**
	 * @param q the q to set
	 */
	public void setQ(String q) {
		this.q = q;
	}
	/**
	 * @return the hl
	 */
	public String getHl() {
		return hl;
	}
	/**
	 * @param hl the hl to set
	 */
	public void setHl(String hl) {
		this.hl = hl;
	}
	/**
	 * @return the hlFragSize
	 */
	public int getHlFragSize() {
		return hlFragSize;
	}
	/**
	 * @param hlFragSize the hlFragSize to set
	 */
	public void setHlFragSize(int hlFragSize) {
		this.hlFragSize = hlFragSize;
	}
	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @return the hlFl
	 */
	public String getHlFl() {
		return hlFl;
	}
	/**
	 * @param hlFl the hlFl to set
	 */
	public void setHlFl(String hlFl) {
		this.hlFl = hlFl;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the wt
	 */
	public String getWt() {
		return wt;
	}
	/**
	 * @param wt the wt to set
	 */
	public void setWt(String wt) {
		this.wt = wt;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hl == null) ? 0 : hl.hashCode());
		result = prime * result + ((hlFl == null) ? 0 : hlFl.hashCode());
		result = prime * result + hlFragSize;
		result = prime * result + ((q == null) ? 0 : q.hashCode());
		result = prime * result + rows;
		result = prime * result + start;
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((wt == null) ? 0 : wt.hashCode());
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
		if (!(obj instanceof Params)) {
			return false;
		}
		Params other = (Params) obj;
		if (hl == null) {
			if (other.hl != null) {
				return false;
			}
		} else if (!hl.equals(other.hl)) {
			return false;
		}
		if (hlFl == null) {
			if (other.hlFl != null) {
				return false;
			}
		} else if (!hlFl.equals(other.hlFl)) {
			return false;
		}
		if (hlFragSize != other.hlFragSize) {
			return false;
		}
		if (q == null) {
			if (other.q != null) {
				return false;
			}
		} else if (!q.equals(other.q)) {
			return false;
		}
		if (rows != other.rows) {
			return false;
		}
		if (start != other.start) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) {
			return false;
		}
		if (wt == null) {
			if (other.wt != null) {
				return false;
			}
		} else if (!wt.equals(other.wt)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Params [q=" + q + ", hl=" + hl + ", hlFragSize=" + hlFragSize + ", start=" + start + ", hlFl=" + hlFl + ", rows=" + rows + ", wt=" + wt
				+ ", version=" + version + "]";
	}
	
}
