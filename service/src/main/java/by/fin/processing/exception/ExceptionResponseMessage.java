package by.fin.processing.exception;

public final class ExceptionResponseMessage {
    public static final String WEEKENDS = "weekends";
    public static final String EXCHANGE_RATES = "exchange_rates";
    public static final String DATE = "date";
    public static final String DATES_INTERVAL = "Заданная дата не входит в период от 01.12.2022 до 31.05.2023";
    public static final String DATES_MIX_UP = "Дата начала не может быть позже даты конца интервала";
    public static final String SERVER_NOT_RESPONDED = "Невозможно получить данные курсов валют с сервера";
    public static final String INVALID_CURRENCY_TYPE = "Проверьте правильность введённой валюты";
    public static final String RATES_NOT_FOUND_IN_MONTH = "В заданный месяц и год курсов валют не найдено";

    private ExceptionResponseMessage() {

    }
}
