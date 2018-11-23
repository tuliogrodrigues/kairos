package org.kairos.date.iso;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsoZonedDateTimeTest {

  private final String simpleDate = "2018-04-04";
  private final String isoSimpleDate = "2018-04-04T00:00:00";
  private final String offsetDateTimeWithTimeZoneWithColon = "2018-04-04T01:00:00+01:00";
  private final String offsetDateTimeWithTimeZone = "2018-04-04T01:00:00+0100";
  private final String offsetDateTimeWithTimeZoneV2 = "1999-07-26T00:00:00.000+0300";
  private final String zoneDateTimeAtVienna = "2018-04-04T02:00:00.000+02:00[Europe/Vienna]";

  private final String offsetDateTimeTargetUtc = "2018-04-04T00:00:00Z";

  private final Pattern offsetDateTimeUTCPattern = Pattern.compile("\\d{4}(-\\d{2}){2}T(\\d{2}:){2}\\d{2}(.\\d{2,3})?Z");

  @Test
  public void T1_dateTime_withSimpleDate_shouldReturnAnInstance() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(simpleDate);
    assertNotNull(dateTime);
  }

  @Test
  public void T1_dateTime_withIsoSimpleDate_shouldReturnAnInstance() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(isoSimpleDate);
    assertNotNull(dateTime);
  }

  @Test
  public void T1_dateTime_withOffsetDateTime_shouldReturnAnInstance() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(offsetDateTimeTargetUtc);
    assertNotNull(dateTime);
  }

  @Test
  public void T1_dateTime_withSimpleDate_shouldReturnAStringWithZonedIsoDate() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(simpleDate);
    assertEquals(dateTime.toString(), offsetDateTimeTargetUtc);
  }

  @Test
  public void T1_dateTime_withIsoSimpleDate_shouldReturnAStringWithAZonedDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(isoSimpleDate);
    assertEquals(dateTime.toString(), offsetDateTimeTargetUtc);
  }

  @Test
  public void T1_dateTime_withOffsetDateTime_shouldReturnAStringWithAZonedDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(offsetDateTimeTargetUtc);
    assertEquals(dateTime.toString(), offsetDateTimeTargetUtc);
  }

  @Test
  public void T1_dateTime_withOffsetDateTimeWithTimezone_shouldReturnAStringWithAZonedDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(offsetDateTimeWithTimeZone);
    assertEquals(dateTime.toString(), offsetDateTimeTargetUtc);
  }

  @Test
  public void T1_dateTime_withOffsetDateTimeWithTimezoneV2_shouldReturnAStringWithAZonedDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(offsetDateTimeWithTimeZoneV2);
    assertNotNull(dateTime);
  }

  @Test
  public void T1_dateTime_withOffsetDateTimeWithTimezoneWithColon_shouldReturnAStringWithAZonedDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(offsetDateTimeWithTimeZoneWithColon);
    assertEquals(dateTime.toString(), offsetDateTimeTargetUtc);
  }

  @Test
  public void T1_dateTime_withZonedDateTimeAtVienna_shouldReturnAStringWithAZonedDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(zoneDateTimeAtVienna);
    assertEquals(dateTime.toString(), offsetDateTimeTargetUtc);
  }


  @Test
  public void T1_dateTime_withSimpleDate_shouldReturnALocalDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(simpleDate);
    assertEquals(dateTime.localDateTime(), LocalDateTime.parse(isoSimpleDate));
  }

  @Test
  public void T1_dateTime_withIsoSimpleDate_shouldReturnTheSameLocalDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(isoSimpleDate);
    assertEquals(dateTime.localDateTime(), LocalDateTime.parse(isoSimpleDate));
  }

  @Test
  public void T1_dateTime_withOffsetDateTime_shouldReturnALocalDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(offsetDateTimeTargetUtc);
    assertEquals(dateTime.localDateTime(), LocalDateTime.parse(isoSimpleDate));
  }

  @Test
  public void T1_dateTime_withSimpleDate_shouldReturnTheSameLocalDate() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(simpleDate);
    assertEquals(dateTime.localDate(), LocalDate.parse(simpleDate));
  }

  @Test
  public void T1_dateTime_withIsoSimpleDate_shouldReturnALocalDate() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(isoSimpleDate);
    assertEquals(dateTime.localDate(), LocalDate.parse(simpleDate));
  }

  @Test
  public void T1_dateTime_withOffsetDateTime_shouldReturnALocalDate() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(offsetDateTimeTargetUtc);
    assertEquals(dateTime.localDate(), LocalDate.parse(simpleDate));
  }

  @Test
  public void T1_dateTime_withSimpleDate_shouldReturnTheOffsetDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(simpleDate);
    assertEquals(dateTime.offsetDateTime(), OffsetDateTime.parse(offsetDateTimeTargetUtc));
  }

  @Test
  public void T1_dateTime_withIsoSimpleDate_shouldReturnAOffsetDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(isoSimpleDate);
    assertEquals(dateTime.offsetDateTime(), OffsetDateTime.parse(offsetDateTimeTargetUtc));
  }

  @Test
  public void T1_dateTime_withOffsetDateTime_shouldReturnTheSameOffsetDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(offsetDateTimeTargetUtc);
    assertEquals(dateTime.offsetDateTime(), OffsetDateTime.parse(offsetDateTimeTargetUtc));
  }

  @Test
  public void T1_dateTime_withOffsetDateTimeWithTimezone_shouldReturnTheSameOffsetDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(offsetDateTimeWithTimeZone);
    assertEquals(dateTime.offsetDateTime(), OffsetDateTime.parse(offsetDateTimeTargetUtc));
  }

  @Test
  public void T1_dateTime_withOffsetDateTimeWithTimezoneColon_shouldReturnTheSameOffsetDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(offsetDateTimeWithTimeZoneWithColon);
    assertEquals(dateTime.offsetDateTime(), OffsetDateTime.parse(offsetDateTimeTargetUtc));
  }

  @Test
  public void T1_dateTime_withZonedDateTimeAtVienna_shouldReturnTheSameOffsetDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(zoneDateTimeAtVienna);
    assertEquals(dateTime.offsetDateTime(), OffsetDateTime.parse(offsetDateTimeTargetUtc));
  }

  @Test
  public void T1_dateTime_withZonedDateTimeAtVienna_shouldReturnTheSameZonedDateTime() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.of(zoneDateTimeAtVienna);
    assertEquals(dateTime.zonedDateTime(), ZonedDateTime.parse(offsetDateTimeTargetUtc));
  }

  @Test
  public void T5_dateTime_withBadFormattedDate_shouldThrowDateTimeConversionException() {
    assertThrows(DateTimeConversionException.class,
        () -> IsoZonedDateTime.of(simpleDate + "Z"));
  }

  @Test
  public void T5_dateTime_withNullDate_shouldThrowDateTimeConversionException() {
    assertThrows(DateTimeConversionException.class,
        () -> {
          String dateTime = null;
          IsoZonedDateTime.of(dateTime);
        });
  }


  @Test
  public void T2_dateTimeOfNullable_withValidDate_shouldReturnAnInstance() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.ofNullable(simpleDate);
    assertNotNull(dateTime);
  }

  @Test
  public void T2_dateTimeOfNullable_withEmptyDate_shouldReturnNull() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.ofNullable("");
    assertNull(dateTime);
  }

  @Test
  public void T2_dateTimeOfNullable_withNullOffsetDateTime_shouldReturnNull() {
    OffsetDateTime dateValue = null;
    IsoZonedDateTime dateTime = IsoZonedDateTime.ofNullable(dateValue);
    assertNull(dateTime);
  }

  @Test
  public void T2_dateTimeOfNullable_withNullZonedDateTime_shouldReturnNull() {
    ZonedDateTime dateValue = null;
    IsoZonedDateTime dateTime = IsoZonedDateTime.ofNullable(dateValue);
    assertNull(dateTime);
  }

  @Test
  public void T2_dateTimeOfNullable_withNullLocalDateTime_shouldReturnNull() {
    LocalDateTime dateValue = null;
    IsoZonedDateTime dateTime = IsoZonedDateTime.ofNullable(dateValue);
    assertNull(dateTime);
  }

  @Test
  public void T2_dateTimeOfNullable_withNullLocalDate_shouldReturnNull() {
    LocalDate dateValue = null;
    IsoZonedDateTime dateTime = IsoZonedDateTime.ofNullable(dateValue);
    assertNull(dateTime);
  }

  @Test
  public void T1_dateTime_now_shouldReturnCurrentDay() {
    IsoZonedDateTime dateTime = IsoZonedDateTime.now();
    assertNotNull(dateTime);
    assertEquals(dateTime.localDate(), LocalDate.now());
  }

  @Test
  public void T1_of_zonedDateTime_shouldReturnAFormattedIsoZonedDateTime() {
    IsoZonedDateTime zonedDateTime = IsoZonedDateTime.of(ZonedDateTime.now());
    Matcher matcher = offsetDateTimeUTCPattern.matcher(zonedDateTime.toString());

    assertAll("zonedDateTime",
        () ->  assertNotEquals(zonedDateTime, ZonedDateTime.now()),
        () -> assertTrue(matcher.matches()));
  }

  @Test
  public void T1_of_offsetDateTime_shouldReturnAFormattedIsoZonedDateTime() {
    IsoZonedDateTime offsetDateTime = IsoZonedDateTime.of(OffsetDateTime.now());
    Matcher matcher = offsetDateTimeUTCPattern.matcher(offsetDateTime.toString());

    assertAll("offsetDateTime",
        () ->  assertNotEquals(offsetDateTime, ZonedDateTime.now()),
        () -> assertTrue(matcher.matches()));
  }

  @Test
  public void T1_of_localDateTime_shouldReturnAFormattedIsoZonedDateTime() {
    IsoZonedDateTime localDateTime = IsoZonedDateTime.of(LocalDateTime.now());
    Matcher matcher = offsetDateTimeUTCPattern.matcher(localDateTime.toString());

    assertAll("localDateTime",
        () ->  assertNotEquals(localDateTime, ZonedDateTime.now()),
        () -> assertTrue(matcher.matches()));
  }

  @Test
  public void T1_of_localDate_shouldReturnAFormattedIsoZonedDateTime() {
    IsoZonedDateTime localDate = IsoZonedDateTime.of(LocalDate.now());
    Matcher matcher = offsetDateTimeUTCPattern.matcher(localDate.toString());

    assertAll("localDate",
        () ->  assertNotEquals(localDate, ZonedDateTime.now()),
        () -> assertTrue(matcher.matches()));
  }
}
