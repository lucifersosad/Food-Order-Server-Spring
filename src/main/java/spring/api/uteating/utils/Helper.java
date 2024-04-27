package spring.api.uteating.utils;

public class Helper {
    public static final String[] DAYS_OF_WEEK = {"Chủ nhật", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7",};

    public static String getDayOfWeek(int weekDay) {
        if (weekDay >= 1 && weekDay <= 7) {
            return DAYS_OF_WEEK[weekDay - 1];
        } else {
            return "Invalid Weekday";
        }
    }

    public static String formatDisplayTotalReview(int totalReviews) {
        return String.valueOf(totalReviews) + "+";
    }
}
