package com.nhpatt.ptd.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class BurndownTest {

	private Sprint sprint;
	private PtdDate start;
	private UserStory userStoryWith3PH;

	@Before
	public void setUp() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(2013, 7, 8);
		start = new PtdDate(calendar);
		sprint = new Sprint(21, start);
		userStoryWith3PH = new UserStory(3);
	}

	@Test
	public void aSprintHas21DaysTest() {
		final Integer days = 21;
		final Sprint sprint = new Sprint(days);

		assertEquals(days, sprint.getDays());
	}

	@Test
	public void aSprintHasAStartingDateTest() {
		final Sprint sprint = new Sprint(21, start);

		assertEquals("08/08/2013", sprint.getStart().toString());
	}

	@Test
	public void aSprintEndsTest() {

		assertEquals("29/08/2013", sprint.getEnd().toString());
	}

	@Test
	public void aSprintHasUserStoriesTest() {
		sprint.addUserStory(new UserStory());

		assertFalse(sprint.getUserStories().isEmpty());
	}

	@Test
	public void aUserStoryHasAnEstimateTest() {
		final UserStory userStory = new UserStory(3);

		assertEquals(new Integer(3), userStory.getEstimate());
	}

	@Test
	public void aUserStoryStartsInADateTest() {
		final Calendar start = Calendar.getInstance();
		start.set(2013, 7, 8);
		userStoryWith3PH.start(new PtdDate(start));

		assertEquals("08/08/2013", userStoryWith3PH.getStart().toString());
	}

	@Test
	public void aUserStoryEndsInADateTest() {
		final Calendar end = Calendar.getInstance();
		end.set(2013, 7, 12);
		userStoryWith3PH.end(new PtdDate(end));

		assertEquals("12/08/2013", userStoryWith3PH.getEnd().toString());
	}

	@Test
	public void aSprintHasIdealProgressForDay0Test() {

		assertEquals(Double.valueOf(0), sprint.getIdealProgress(0));
	}

	@Test
	public void aSprintHasIdealProgressForLastDayTest() {
		sprint.addUserStory(userStoryWith3PH);

		assertEquals(Double.valueOf(3), sprint.getIdealProgress(21));
	}

	@Test
	public void aSprintHasIdealProgressForMidDayTest() {
		sprint.addUserStory(userStoryWith3PH);

		assertTrue(1.4D < sprint.getIdealProgress(10)
				&& sprint.getIdealProgress(10) < 1.6D);
	}

	@Test
	public void aSprintHasRealProgressForMidDayTest() {
		final Calendar end = Calendar.getInstance();
		end.set(2013, 7, 12);
		sprint.addUserStory(new UserStory(3, start, new PtdDate(end)));
		sprint.addUserStory(new UserStory(2, start, new PtdDate(end)));
		sprint.addUserStory(new UserStory(7));

		assertEquals(new Integer(5), sprint.getRealProgress(10));
	}

}
