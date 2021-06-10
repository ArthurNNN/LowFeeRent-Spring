package model;

public class Tenant extends Person {

	String requestId;

	public Tenant() {
		super();
		this.setId();
	}

	public Tenant(String requestId) {
		super();
		this.setId();
		this.requestId = requestId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "Tenant [requestId=" + requestId + "]" + super.toString();
	}
}
