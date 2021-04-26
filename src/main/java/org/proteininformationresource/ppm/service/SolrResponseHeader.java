package org.proteininformationresource.ppm.service;

public class SolrResponseHeader {
	private boolean zkConnected;
	private int status;
	private int QTime;
	private Params params;
	/**
	 * @param zkConnected
	 * @param status
	 * @param qTime
	 * @param params
	 */
	public SolrResponseHeader(boolean zkConnected, int status, int qTime, Params params) {
		super();
		this.zkConnected = zkConnected;
		this.status = status;
		this.QTime = QTime;
		this.params = params;
	}
	/**
	 * 
	 */
	public SolrResponseHeader() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the zkConnected
	 */
	public boolean isZkConnected() {
		return zkConnected;
	}
	/**
	 * @param zkConnected the zkConnected to set
	 */
	public void setZkConnected(boolean zkConnected) {
		this.zkConnected = zkConnected;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the QTime
	 */
	public int getqTime() {
		return QTime;
	}
	/**
	 * @param QTime the QTime to set
	 */
	public void setQTime(int QTime) {
		this.QTime = QTime;
	}
	/**
	 * @return the params
	 */
	public Params getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(Params params) {
		this.params = params;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		result = prime * result + QTime;
		result = prime * result + status;
		result = prime * result + (zkConnected ? 1231 : 1237);
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
		if (!(obj instanceof SolrResponseHeader)) {
			return false;
		}
		SolrResponseHeader other = (SolrResponseHeader) obj;
		if (params == null) {
			if (other.params != null) {
				return false;
			}
		} else if (!params.equals(other.params)) {
			return false;
		}
		if (QTime != other.QTime) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (zkConnected != other.zkConnected) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseHeader [zkConnected=" + zkConnected + ", status=" + status + ", QTime=" + QTime + ", params=" + params + "]";
	}
	
	
	
}
