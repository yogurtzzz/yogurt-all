package date;

import net.sf.cglib.core.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class DateAndTime {
    public static void main(String[] args) {
        LocalDate now = LocalDate.of(2019,12,31);
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
        int i = now.get(weekFields.weekOfYear());
        System.out.println("2019年的第" + i + "周");

        LocalDate thisDate = LocalDate.now();
        /** 获取本周的星期一 **/
        LocalDate thisWeekMonday = thisDate.with(DayOfWeek.MONDAY);
        LocalDate thisWeekSunday = thisDate.with(DayOfWeek.SUNDAY);
    }
}
