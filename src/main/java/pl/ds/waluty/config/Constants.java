package pl.ds.waluty.config;

public final class Constants {
    private Constants() {}

    public static final String TABEL_A_KURS_SREDNI   = "https://api.nbp.pl/api/exchangerates/tables/a";
    public static final String TABEL_C_KURS_SPRZEDAZ = "https://api.nbp.pl/api/exchangerates/tables/c";
    public static final String KURS_TABELA_KOD_DATA = "http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{date}";
}
