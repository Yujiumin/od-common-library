package security

import cn.fullstack.od.common.core.util.LocalDateTimeUtils
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * @date 2024/12/15
 */
class LocalDateTimeUtilsTests extends Specification {

    def "test format localDateTime"() {
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
}
