package cn.hutool.core.date;

import cn.hutool.core.date.BetweenFormatter.Level;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateBetweenTest {

	@Test
	public void betweenYearTest() {
		Date start = DateUtil.parse("2017-02-01 12:23:46");
		Date end = DateUtil.parse("2018-02-01 12:23:46");
		long betweenYear = new DateBetween(start, end).betweenYear(false);
		assertEquals(1, betweenYear);

		Date start1 = DateUtil.parse("2017-02-01 12:23:46");
		Date end1 = DateUtil.parse("2018-03-01 12:23:46");
		long betweenYear1 = new DateBetween(start1, end1).betweenYear(false);
		assertEquals(1, betweenYear1);

		// 不足1年
		Date start2 = DateUtil.parse("2017-02-01 12:23:46");
		Date end2 = DateUtil.parse("2018-02-01 11:23:46");
		long betweenYear2 = new DateBetween(start2, end2).betweenYear(false);
		assertEquals(0, betweenYear2);
	}

	@Test
	public void betweenYearTest2() {
		Date start = DateUtil.parse("2000-02-29");
		Date end = DateUtil.parse("2018-02-28");
		long betweenYear = new DateBetween(start, end).betweenYear(false);
		assertEquals(18, betweenYear);
	}

	@Test
	public void betweenYearTest3() {
		Date start = DateUtil.parse("20170301");
		Date end = DateUtil.parse("2024-02-29 14:56:18");
		long betweenYear = new DateBetween(start, end).betweenYear(false);
		assertEquals(6, betweenYear);
	}

	@Test
	public void betweenMonthTest() {
		Date start = DateUtil.parse("2017-02-01 12:23:46");
		Date end = DateUtil.parse("2018-02-01 12:23:46");
		long betweenMonth = new DateBetween(start, end).betweenMonth(false);
		assertEquals(12, betweenMonth);

		Date start1 = DateUtil.parse("2017-02-01 12:23:46");
		Date end1 = DateUtil.parse("2018-03-01 12:23:46");
		long betweenMonth1 = new DateBetween(start1, end1).betweenMonth(false);
		assertEquals(13, betweenMonth1);

		// 不足
		Date start2 = DateUtil.parse("2017-02-01 12:23:46");
		Date end2 = DateUtil.parse("2018-02-01 11:23:46");
		long betweenMonth2 = new DateBetween(start2, end2).betweenMonth(false);
		assertEquals(11, betweenMonth2);
	}

	@Test
	public void betweenMinuteTest() {
		Date date1 = DateUtil.parse("2017-03-01 20:33:23");
		Date date2 = DateUtil.parse("2017-03-01 23:33:23");
		String formatBetween = DateUtil.formatBetween(date1, date2, Level.SECOND);
		assertEquals("3小时", formatBetween);
	}

	@Test
	public void betweenWeeksTest(){
		final long betweenWeek = DateUtil.betweenWeek(
				DateUtil.parse("2020-11-21"),
				DateUtil.parse("2020-11-23"), false);

		final long betweenWeek2 = LocalDateTimeUtil.between(
				LocalDateTimeUtil.parse("2020-11-21", "yyy-MM-dd"),
				LocalDateTimeUtil.parse("2020-11-23", "yyy-MM-dd"),
				ChronoUnit.WEEKS);
		assertEquals(betweenWeek, betweenWeek2);
	}

	@Test
	public void issueI97U3JTest(){
		String dateStr1 = "2024-02-29 23:59:59";
		Date sdate = DateUtil.parse(dateStr1);

		String dateStr2 = "2023-03-01 00:00:00";
		Date edate = DateUtil.parse(dateStr2);

		long result = DateUtil.betweenYear(sdate, edate, false);
		assertEquals(0, result);
	}
}
