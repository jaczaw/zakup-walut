package pl.ds.waluty.config;

public final class Constants {
    private Constants() {}

    public static final String TABEL_A_KURS_SREDNI   = "https://api.nbp.pl/api/exchangerates/tables/a";
    public static final String TABEL_C_KURS_SPRZEDAZ = "https://api.nbp.pl/api/exchangerates/tables/c";
    public static final String KURS_SREDNI           = "http://api.nbp.pl/api/exchangerates/rates/a/{code}";
    public static final String KURS_SPRZEDAZ         = "http://api.nbp.pl/api/exchangerates/rates/c/{code}";


}
