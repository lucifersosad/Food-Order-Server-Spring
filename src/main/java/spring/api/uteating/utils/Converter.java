package spring.api.uteating.utils;

import spring.api.uteating.entity.RestaurantWorkingTime;
import spring.api.uteating.model.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Converter {
    public static List<DateTime> convertToDateTimeList(List<RestaurantWorkingTime> restaurantWorkingTimes) {
        List<DateTime> dateTimeList = new ArrayList<>();

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        // Đặt múi giờ cho timeFormat
        timeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

        for (RestaurantWorkingTime workingTime : restaurantWorkingTimes) {
            String date = Helper.getDayOfWeek(workingTime.getWeek_day());
            // Convert thời gian sang múi giờ Việt Nam
            String openTime = timeFormat.format(workingTime.getOpen_hour());
            String closeTime = timeFormat.format(workingTime.getClose_hour());
            String time = openTime + "-" + closeTime;

            DateTime dateTime = new DateTime(date, time);
            dateTimeList.add(dateTime);
        }

        return dateTimeList;
    }
}