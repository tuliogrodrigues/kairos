package org.kairos.date.iso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class IsoZonedDateTime {

  private ZonedDateTime temporal;

  private IsoZonedDateTime(ZonedDateTime temporal) {
    this.temporal = temporal;
  }

  public static IsoZonedDateTime now() {
    return of(OffsetDateTime.now().toString());
  }

  public static IsoZonedDateTime of(final ZonedDateTime dateTime) {
    return new IsoZonedDateTime(ZonedDateTime.from(dateTime).withZoneSameInstant(ZoneOffset.UTC));
  }

  public static IsoZonedDateTime of(final OffsetDateTime dateTime) {
    return new IsoZonedDateTime(OffsetDateTime.from(dateTime).atZoneSameInstant(ZoneOffset.UTC));
  }

  public static IsoZonedDateTime of(final LocalDateTime dateTime) {
    return new IsoZonedDateTime(LocalDateTime.from(dateTime).atZone(ZoneOffset.UTC));
  }

  public static IsoZonedDateTime of(final LocalDate dateTime) {
    return new IsoZonedDateTime(LocalDate.from(dateTime).atStartOfDay().atZone(ZoneOffset.UTC));
  }

  public static synchronized IsoZonedDateTime of(final String dateValue) {
    TemporalAccessor ta = DateFormatter.apply(dateValue);

      if (ta instanceof ZonedDateTime) {
        return of((ZonedDateTime) ta);
      } else if (ta instanceof OffsetDateTime) {
        return of((OffsetDateTime) ta);
      } else if (ta instanceof LocalDateTime) {
        return of((LocalDateTime) ta);
      } else if (ta instanceof LocalDate) {
        return of((LocalDate) ta);
      }
      throw new DateTimeConversionException(ta.toString());
  }

  public static IsoZonedDateTime ofNullable(final String dateValue) {
    return (dateValue == null || dateValue.isEmpty()) ?
        null :
        IsoZonedDateTime.of(dateValue);
  }

  public static IsoZonedDateTime ofNullable(final ZonedDateTime dateValue) {
    return Optional.ofNullable(dateValue)
        .map(IsoZonedDateTime::of)
        .orElse(null);
  }

  public static IsoZonedDateTime ofNullable(final OffsetDateTime dateValue) {
    return Optional.ofNullable(dateValue)
        .map(IsoZonedDateTime::of)
        .orElse(null);
  }

  public static IsoZonedDateTime ofNullable(final LocalDateTime dateValue) {
    return Optional.ofNullable(dateValue)
        .map(IsoZonedDateTime::of)
        .orElse(null);
  }

  public static IsoZonedDateTime ofNullable(final LocalDate dateValue) {
    return Optional.ofNullable(dateValue)
        .map(IsoZonedDateTime::of)
        .orElse(null);
  }

  public OffsetDateTime offsetDateTime() {
    return temporal.toOffsetDateTime();
  }

  public LocalDateTime localDateTime() {
    return temporal.toLocalDateTime();
  }

  public LocalDate localDate() {
    return temporal.toLocalDate();
  }

  public ZonedDateTime zonedDateTime() {
    return temporal.withFixedOffsetZone();
  }

  @Override
  public String toString() {
    return temporal.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
  }
}
