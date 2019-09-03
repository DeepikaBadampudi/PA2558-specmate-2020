package com.specmate.scheduler.iterators;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * A <code>DailyIterator</code> returns a sequence of dates on subsequent days
 * representing the same time each day.
 */
public class HourlyIterator implements ScheduleIterator {
	private ZonedDateTime zonedDateTime;

	public HourlyIterator(Date date, int... time) {
		this(getMinute(time), getSecond(time), date);
	}

	public HourlyIterator(int minute, int second, Date date) {
		
		// Get the specified date
		LocalDate localDate = date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();

		LocalDateTime localDT = LocalDateTime.of(localDate, LocalTime.now());
		localDT = localDT.withMinute(minute).withSecond(second).withNano(0);

		ZoneId currentZone = ZoneId.systemDefault();
		zonedDateTime = ZonedDateTime.of(localDT, currentZone);
	}

	@Override
	public Date next() {
		zonedDateTime = zonedDateTime.plusHours(1);
		return Date.from(zonedDateTime.toInstant());
	}

	private static int getMinute(int... time) {
		int temp = SchedulerUtils.getNumberIfExistsOrZero(0, time);
		return SchedulerUtils.normalizeInput(temp, 60);
	}

	private static int getSecond(int... time) {
		int temp = SchedulerUtils.getNumberIfExistsOrZero(1, time);
		return SchedulerUtils.normalizeInput(temp, 60);
	}
}