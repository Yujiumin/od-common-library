package security

import cn.fullstack.od.common.core.util.LocalDateTimeUtils
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.ZoneId

class LocalDateTimeUtilsTests extends Specification {

    def "test format localDateTime with default pattern"() {
        given:
        def localDateTime = LocalDateTime.of(2024, 12, 15, 13, 20, 31)
        when:
        def formatVal = LocalDateTimeUtils.format(localDateTime)
        then:
        formatVal == "2024-12-15 13:20:31"
    }

    def "test format localDateTime with custom pattern"() {
        when:
        def format = LocalDateTimeUtils.format(dateTime, pattern)
        then:
        format == formatValue
        where:
        dateTime                                   | pattern                 | formatValue
        LocalDateTime.of(2024, 12, 15, 13, 20, 31) | "yyyyMMddHHmmss"        | "20241215132031"
        LocalDateTime.of(2024, 12, 15, 13, 20, 31) | "yyyy-MM-dd HH:mm:ss"   | "2024-12-15 13:20:31"
        LocalDateTime.of(2024, 12, 15, 13, 20, 31) | "yyyy/MM/dd HH:mm:ss"   | "2024/12/15 13:20:31"
        LocalDateTime.of(2024, 12, 15, 13, 20, 31) | "yyyy-MM-dd'T'HH:mm:ss" | "2024-12-15T13:20:31"
    }

    def "test parse formatVal with default pattern"() {
        given:
        def formatVal = "2024-12-15 13:20:31"
        when:
        def localDateTime = LocalDateTimeUtils.parse(formatVal)
        then:
        localDateTime == LocalDateTime.of(2024, 12, 15, 13, 20, 31)
    }

    def "test parse formatVal with custom pattern"() {
        when:
        def dateTime = LocalDateTimeUtils.parse(formatValue, pattern)
        then:
        dateTime == expectDateTime
        where:
        formatValue           | pattern                 | expectDateTime
        "20241215132031"      | "yyyyMMddHHmmss"        | LocalDateTime.of(2024, 12, 15, 13, 20, 31)
        "2024-12-15 13:20:31" | "yyyy-MM-dd HH:mm:ss"   | LocalDateTime.of(2024, 12, 15, 13, 20, 31)
        "2024/12/15 13:20:31" | "yyyy/MM/dd HH:mm:ss"   | LocalDateTime.of(2024, 12, 15, 13, 20, 31)
        "2024-12-15T13:20:31" | "yyyy-MM-dd'T'HH:mm:ss" | LocalDateTime.of(2024, 12, 15, 13, 20, 31)
    }

    def "test parse localDateTime to java date"() {
        given:
        def calendar = Calendar.getInstance(Locale.CHINA)
        calendar.set(2024, 11, 15, 13, 20, 31)
        calendar.set(Calendar.MILLISECOND, 0)
        def localDateTime = LocalDateTime.of(2024, 12, 15, 13, 20, 31)
        when:
        def javaDate = LocalDateTimeUtils.toJavaDate(localDateTime)
        then:
        calendar.time == javaDate
    }

    def "test parse timestamp to localDateTime"() {
        given:
        def timestamp = 1734264452000
        when:
        def dateTime = LocalDateTimeUtils.parse(timestamp)
        then:
        dateTime.isEqual(LocalDateTime.of(2024, 12, 15, 20, 7, 32))
    }

    def "test parse localDateTime to timestamp"() {
        given:
        def dateTime = LocalDateTime.of(2024, 12, 15, 20, 7, 32)
        when:
        def timestamp = LocalDateTimeUtils.toEpochMilli(dateTime)
        then:
        timestamp == 1734264452000
    }

    def "test parse localDateTime to timestamp with zoneId"() {
        given:
        def dateTime = LocalDateTime.of(2024, 12, 15, 20, 7, 32)
        when:
        def timestamp = LocalDateTimeUtils.toEpochMilli(dateTime, ZoneId.systemDefault())
        then:
        timestamp == 1734264452000
    }
}
