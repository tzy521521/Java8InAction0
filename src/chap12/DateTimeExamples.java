package chap12;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by tzy on 2017/7/26.
 */
public class DateTimeExamples {
    public static void main(String[] args) {
        //代码清单12-1:创建一个L
        LocalDate date= LocalDate.of(2017,7,25);
        System.out.println(date);
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfWeek());
        //从系统始终获取当前时间
        System.out.println(LocalDate.now());

        //代码清单12-2：使用TemporalField参数给get方法拿到同样的信息。
        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println(y+"/"+m+"/"+d);

        //代码清单12-3：创建LocalTime并且读取值。
        LocalTime time = LocalTime.of(13, 45, 20); // 13:45:20
        int hour = time.getHour(); // 13
        int minute = time.getMinute(); // 45
        int second = time.getSecond(); // 20
        System.out.print(time+" ");System.out.println(" "+hour+"-"+minute+"-"+second);
        //LocalDate和LocalTime都可以使用静态方法,一旦传递的字符串无法被解析成合法的LocalDate和LocalTime对象。这两个方法都会抛出DateTimeParseException等异常。
        LocalDate localDate=LocalDate.parse("2017-09-30");
        LocalTime localTime=LocalTime.parse("17:58:25");
        System.out.println(localDate);
        System.out.println(localTime);

        //12-4直接创建LocalDateTime，或者通过合并LocalDate和LocalTime对象。
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 10, 45, 20); // 2014-03-18T13:45
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt1);
        System.out.println(dt2);
        System.out.println(dt3);
        System.out.println(dt4);
        System.out.println(dt5);
        //toLocalDate()和toLocalTime()从LocalDateTime中提取LocalDate和LocalTime time1对象。
        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1);
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1);

        //向静态工厂方法中传递一个代表秒数的值，穿建一个Instant类实例。另一个增强版本第二个参数已纳米为单位的参数值来修正时间。
        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3,0));
        System.out.println(Instant.ofEpochSecond(2,1000000000));
        System.out.println(Instant.ofEpochSecond(4,-1000000000));

        //这些类都实现了Temporal接口。Duration类的静态工厂方法between可以求出两个Temporal对象之间的持续时间。(不能传递LocalDate对象)
        Duration d1 = Duration.between(LocalTime.of(13, 45, 10), time);
        System.out.println(d1);
        Duration d2 = Duration.between(LocalDateTime.now(),dt1);
        System.out.println(d2);
        Duration d3 =Duration.between(Instant.ofEpochSecond(3),Instant.ofEpochSecond(78));
        System.out.println(d3);
        //如果需要以年、月或者日的方式对多个时间单位建模，可以使用
        Period tenday=Period.between(LocalDate.of(2017,7,16),LocalDate.of(2017,7,26));
        System.out.println(tenday);

        //代码清单12-5：穿建Duration和Period对象。
        Duration treeMintes=Duration.ofMinutes(3);
        System.out.println(treeMintes);
        Duration treeMintes1=Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(treeMintes1);

        Period tenDays=Period.ofDays(10);
        Period threeWeeks=Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay=Period.of(2,6,1);
        System.out.println(tenDays);
        System.out.println(threeWeeks);
        System.out.println(twoYearsSixMonthsOneDay);

        //12-6 以比较指直观的方式操纵LocalDate对象,withAttribute方法会创建对象的一个副本。
        LocalDate localDate1 = LocalDate.of(2017,7,26);//2017-07-26
        LocalDate localDate2 = localDate1.withYear(2011);//2011-07-26
        LocalDate localDate3 = localDate1.withDayOfMonth(20);//2017-07-20
        LocalDate localDate4 = localDate1.with(ChronoField.MONTH_OF_YEAR,1);//2017-01-26
        System.out.println(localDate1);
        System.out.println(localDate2);
        System.out.println(localDate3);
        System.out.println(localDate4);

        //12.7 以相对的方式修改LocalDate对象属性。
        LocalDate localDate11 = localDate1.plusWeeks(1);//2017-08-02
        LocalDate localDate12 = localDate1.minusYears(3);//2014-07-26
        LocalDate localDate13 = localDate1.plus(6,ChronoUnit.MONTHS);//2018-01-26
        System.out.println(localDate11);
        System.out.println(localDate12);
        System.out.println(localDate13);

        //12-8使用预定义的TemporalAdjuster
        LocalDate localDate21 = localDate1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate localDate22 = localDate1.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(localDate21);
        System.out.println(localDate22);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");

        //12-9：TemporalAdjuster接口是一个函数市接口。
        useDateFormatter();
        //
        useZoneId();
    }
    public static void useDateFormatter(){
        LocalDate date=LocalDate.of(2014,3,18);
        String s1=date.format(DateTimeFormatter.BASIC_ISO_DATE);//20140318
        String s2=date.format(DateTimeFormatter.ISO_LOCAL_DATE);//2014-03-18
        System.out.println(date);
        System.out.println(s1);
        System.out.println(s2);

        //也可以通过解析代表日期或事件的字符串重新创建该日期对象。
        LocalDate date1=LocalDate.parse("20140319",DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(date1);
        LocalDate date2=LocalDate.parse("2014-03-19",DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date2);

        //代码清单12-10：DateTimeFormatter还支持一个静态工厂方法，可以按照某个特定的模式创建格式器。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fomattedDate=date.format(formatter);
        System.out.println(fomattedDate);
        //静态的parse方法使用同样的格式器解析了刚才生成的字符串，并重建了该日期对象
        LocalDate date3 =LocalDate.parse(fomattedDate,formatter);
        System.out.println(date3);

        //代码清单12-11：创建一个本地化的DateTimeFormatter
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        String fomattedDate1=date.format(italianFormatter);
        System.out.println(fomattedDate1);
        LocalDate date4 =LocalDate.parse(fomattedDate1,italianFormatter);
        System.out.println(date4);

        //代码清单12-12 构造一个DateTimerFormatter
        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        String fomattedDate2=date.format(complexFormatter);
        System.out.println(fomattedDate2);
    }

    public static void useZoneId(){

        //
        ZoneId romeZone =ZoneId.of("Europe/Rome");
        System.out.println(romeZone);
        //java8的新方法toZoneId将一个老的时区转换成ZoneID
        ZoneId zoneId1= TimeZone.getDefault().toZoneId();
        System.out.println(zoneId1);

        //代码清单12-13：为时间点添加时区信息。
        LocalDate date=LocalDate.of(2014,Month.MARCH,18);
        ZonedDateTime zonedDateTime=date.atStartOfDay(romeZone);
        System.out.println(zonedDateTime);

        LocalDateTime dateTime=LocalDateTime.of(2014,Month.MARCH,18,18,13,25);
        ZonedDateTime zonedDateTime1=dateTime.atZone(romeZone);
        System.out.println(zonedDateTime1);

        Instant instant =Instant.now();
        ZonedDateTime zonedDateTime2=instant.atZone(romeZone);
        System.out.println(zonedDateTime2);

        //通过ZoneId可以将LocalDateTime转换成Instant????
        //Instant instantFromeLoalDateTime=dateTime.toInstant(romeZone);
        //也可以反向得到LocalDateTime
        LocalDateTime timeFromeInstant=LocalDateTime.ofInstant(instant,romeZone);
        System.out.println(timeFromeInstant);

        //
        ZoneOffset newYorkOffset=ZoneOffset.of("-05:00");
        System.out.println(newYorkOffset);

        OffsetDateTime dateTimeInNeYork=OffsetDateTime.of(dateTime,newYorkOffset);
        System.out.println(dateTimeInNeYork);

        //使用别的日历系统。
        JapaneseDate japaneseDate=JapaneseDate.from(date);
        System.out.println(japaneseDate);

        Chronology japaneseChronology=Chronology.ofLocale(Locale.JAPAN);
        ChronoLocalDate now=japaneseChronology.dateNow();
        System.out.println(now);
    }
}
