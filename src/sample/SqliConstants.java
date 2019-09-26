package sample;

public final class SqliConstants {

    // Mensajes en pantalla
    public static final String MSJ_NO_VULNERABLE = "No se encontraron vulnerabilidades de inyección SQL";
    public static final String MSJ_URL_MALFORMADA = "La URL no se pudo procesar, verifique que sea correcta";
    public static final String MSJ_VULNERABLE_1 = "ALERTA! Su sitio es vulnerable a ataques de inyección SQL";
    public static final String MSJ_VULNERABLE_2 = "Consulte el reporte para mayor información";
    public static final String MSJ_WARNING_1 = "El sitio no se analizó por completo";
    public static final String MSJ_WARNING_2 = "Posibles causas:";
    public static final String MSJ_WARNING_TIMEOUT = "- Se alcanzó el máximo tiempo de espera (10 segundos)";
    public static final String MSJ_WARNING_HTTP = "- Existe alguna protección HTTP en el sitio";

    // Mensajes de sitio no vulnerable
    public static final String NO_VULNERABLE = "all tested parameters do not appear to be injectable";

    // Mensajes de errores
    public static final String SIN_PARAMETROS = "no parameter(s) found for testing in the provided data";
    public static final String TARGET_INESTABLE = "target URL content is not stable";
    public static final String TARGET_STABLE = "target URL content is stable";

    public static final String NO_ENCONTRADO = "does not exist";
    public static final String URL_MALFORMADA = "no usable links found";
    public static final String URL_INEXISTENTE = "' does not exist";
    public static final String URL_INVALIDA = "invalid target URL";
    public static final String ERRORES_HTTP = "HTTP error codes detected during run";
    public static final String ERROR_TIMEOUT = "connection timed out to the target URL";

    // Mensajes de éxito en las validaciones
    public static final String VULNERABLE = "fetched data logged";
    public static final String VULNERABLE_PRIMERA_OCASION = "sqlmap identified the following injection point";

    // Fechas
    public static final String INICIO_SQLI = "[*] starting @";
    public static final String FIN_SQLI = "[*] ending @";

    // Comandos
    public static final String PYTHON2 = "python2 ";
    public static final String SQLMAP = "/Users/juan/Documents/GalileaSW/sqlmap-master/sqlmap.py ";
    public static final String COMMAND = "-u ";
    public static final String BATCH = " --batch --alert=ALERT --schema --timeout=10 --current-user --is-dba --random-agent -v 2"; //
    public static final String CRAWL = " --crawl=2";
    public static final String TECHNIQUE = " --technique=";
    public static final String TECHNIQUE_B = "B";
    public static final String TECHNIQUE_E = "E";
    public static final String TECHNIQUE_U = "U";
    public static final String TECHNIQUE_S = "S";
    public static final String TECHNIQUE_T = "T";
    public static final String TECHNIQUE_Q = "Q";


    public static final String TECNICA_SQL_BOOLEAN_BASED = "- Ataque basado en booleanos (Boolean based)";
    public static final String TECNICA_SQL_ERROR_BASED = "- Ataque basado en errores (Error based)";
    public static final String TECNICA_SQL_UNION_QUERY_BASED = "- Ataque basado en consultas 'UNION' (Union query based)";
    public static final String TECNICA_SQL_STACKED_QUERIES = "- Ataque basado en consultas concatenadas (Stacked queries)";
    public static final String TECNICA_SQL_TIME_BASED_BLIND = "- Ataque basados en tiempos de respuesta (Time based blind)";
    public static final String TECNICA_SQL_TIME_INLINE_QUERIES = "- Ataques basados en consultas en línea";

}

//TODO(juanhernandezlia): http://www.romanianwriters.ro/s.php?id=1
// --output-dir para elegir donde guardar el resultado
// --mobile
// --force-ssl
// --technique: []
// --threads=3
// -v 6