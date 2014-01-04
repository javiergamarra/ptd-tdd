package com.nhpatt.ptd.tdd;

public class UserStory {

	private Integer estimate;
	private PtdDate start;
	private PtdDate end;

	public UserStory() {
		super();
	}

	public UserStory(final Integer estimate) {
		this.estimate = estimate;
	}

	public UserStory(final Integer estimate, final PtdDate start,
			final PtdDate end) {
		this.estimate = estimate;
		this.start = start;
		this.end = end;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void start(final PtdDate ptdDate) {
		start = ptdDate;
	}

	public void end(final PtdDate end) {
		this.end = end;
	}

	public PtdDate getStart() {
		return start;
	}

	public PtdDate getEnd() {
		return end;
	}

}
