package ua.nure.butorin.SummaryTask4.db.entity;

public class Bill extends Entity {

	private static final long serialVersionUID = -806919123909627032L;

	private Long userId;

	private String operationName;

	private int amount;

	private boolean state;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Bill [userId=" + userId + ", operationName=" + operationName + ", amount=" + amount + ", state=" + state
				+ "]";
	}
}
