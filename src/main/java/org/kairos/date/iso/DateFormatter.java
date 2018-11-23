package org.kairos.date.iso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

interface DateFormatter {

  DateTimeFormatter OPTIONAL_ISO_ZONE_DATE_TIME_FORMATTER =
      new DateTimeFormatterBuilder()
          .parseStrict()
          .append(ISO_LOCAL_DATE)
          .optionalStart()
          .appendLiteral('T')
          .append(ISO_LOCAL_TIME)
          .optionalStart()
          .appendOffset("+HHMM", "Z")
          .optionalEnd()
          .optionalStart()
          .appendOffsetId()
          .optionalStart()
          .appendLiteral('[')
          .parseCaseSensitive()
          .appendZoneRegionId()
          .appendLiteral(']')
          .toFormatter();

  static TemporalAccessor apply(final String value) {
    try {
      return OPTIONAL_ISO_ZONE_DATE_TIME_FORMATTER.parseBest(
          value, ZonedDateTime::from, OffsetDateTime::from, LocalDateTime::from, LocalDate::from);
    } catch (Exception e) {
      throw new DateTimeConversionException(e.getMessage());
    }
  }
}
