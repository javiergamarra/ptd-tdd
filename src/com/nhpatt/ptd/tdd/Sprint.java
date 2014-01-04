package com.nhpatt.ptd.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Sprint {

	private final Integer days;
	private final PtdDate start;
	private final List<UserStory> userStories = new ArrayList<UserStory>();

	public Sprint(final Integer days) {
		this(days, new PtdDate());
	}

	public Sprint(final Integer days, final PtdDate start) {
		this.days = days;
		this.start = start;
	}

	public Integer getDays() {
		return days;
	}

	public PtdDate getStart() {
		return start;
	}

	public PtdDate getEnd() {
		return start.nextDays(days);
	}

	public void addUserStory(final UserStory userStory) {
		userStories.add(userStory);
	}

	public List<UserStory> getUserStories() {
		return userStories;
	}

	public Double getIdealProgress(final Integer day) {
		int estimates = 0;
		for (final UserStory userStory : userStories) {
			estimates += userStory.getEstimate();
		}
		return estimates * (Double.valueOf(day) / days);
	}

	public Integer getRealProgress(final Integer day) {
		int estimates = 0;
		for (final UserStory userStory : userStories) {
			if (userStory.getEnd() != null
					&& start.nextDays(day).after(userStory.getEnd())) {
				estimates += userStory.getEstimate();
			}
		}
		return estimates;
	}

	public Double normalizedProgress(final Double progress) {
		return progress / getTotalEstimates();
	}

	public Integer getTotalEstimates() {
		int estimates = 0;
		for (final UserStory userStory : userStories) {
			estimates += userStory.getEstimate();
		}
		return estimates;
	}

	public static Sprint exampleSprint() {
		final Calendar calendarStart = Calendar.getInstance();
		calendarStart.set(2013, 7, 8);
		final PtdDate start = new PtdDate(calendarStart);

		final Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.set(2013, 7, 12);
		final PtdDate end = new PtdDate(calendarEnd);

		final Calendar calendarEnd16 = Calendar.getInstance();
		calendarEnd16.set(2013, 7, 16);

		final Sprint sprint = new Sprint(21, start);
		sprint.addUserStory(new UserStory(3, start, end));
		sprint.addUserStory(new UserStory(2, start, end));
		sprint.addUserStory(new UserStory(2, start, new PtdDate(calendarEnd16)));
		sprint.addUserStory(new UserStory(4));
		return sprint;
	}
}
