package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


import static sample.SqliConstants.*;

public class SqliExecutor {

    public SqliResult sqliResult;
    public ArrayList<SqliPayload> payloads = new ArrayList<SqliPayload>();
    public SqliPayload payload = new SqliPayload();
    public ArrayList<SqliTabla> tablas = new ArrayList<SqliTabla>();
    public SqliTabla tabla = new SqliTabla();
    private ArrayList<String> tecnicas = new ArrayList<>();
    private boolean isUrlSinParametros;

    public Boolean ejecutarAnalisisSql(String url, ArrayList<String> tecnicas) {


        this.tecnicas = tecnicas;
        sqliResult = new SqliResult();
        try {
            String urlFinal = obtenerComandoPython(url);
            System.out.println("Ejecutando el siguiente comando: " + urlFinal);

            Process p = Runtime.getRuntime().exec(urlFinal);

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            in.lines().forEach(logLine -> {
                        validaLinea(logLine);
                        System.out.println(logLine.toString());
                        generaReporte(logLine);
                    }

            );
            if (!(payloads.isEmpty() || tablas.isEmpty())) {

                sqliResult.setSqliPayload(payloads);
                sqliResult.setTablas(tablas);


            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getCause());
            return false;
        }

    }

    private void generaReporte(String logLine) {

        if (logLine.contains("current user: ")) {
            sqliResult.setUsuarioDb(logLine);

        }
        if (logLine.contains("current user is DBA:")) {
            sqliResult.setUsuarioDba(logLine);
        }

        if (logLine.contains("web application technology:")) {
            sqliResult.setWebServer(logLine);
        }
        if (logLine.contains("back-end DBMS:")) {
            sqliResult.setDbms(logLine);
        }
        if (logLine.contains("injection point(s) with a total of")) {
            sqliResult.setPeticionesHttp(logLine);
        }

        if (logLine.contains("Type:")) {
            payload.setTipo(logLine);
        }
        if (logLine.contains("Title:")) {
            payload.setTitulo(logLine);
        }
        if (logLine.contains("Payload:")) {
            payload.setPayload(logLine);
            payloads.add(payload);
            payload = new SqliPayload();
        }


        if (logLine.contains("Database: ")) {
            tabla.setBaseDeDatos(logLine);
        }
        if (logLine.contains("Table: ")) {
            tabla.setTabla(logLine);
        }
        if (logLine.contains("columns]")) {
            tabla.setColumnas(logLine);
            tablas.add(tabla);
            tabla = new SqliTabla();
        }


    }

    private void validaLinea(String logLine) {

        if (logLine.contains(INICIO_SQLI)) {
            sqliResult.setInicioEjecucion(logLine.toString());
        }
        if (logLine.contains(FIN_SQLI)) {
            sqliResult.setFinEjecucion(logLine.toString());
            sqliResult.setDuracionEjecucion(sqliResult.getInicioEjecucion(), sqliResult.getFinEjecucion());
        }
        if (logLine.contains(NO_ENCONTRADO)) {
            sqliResult.setErrorNoExiste(true);
        }
        if (logLine.contains(VULNERABLE)) {
            sqliResult.setVulnerable(true);
        }
        if (logLine.contains(VULNERABLE_PRIMERA_OCASION)) {
            sqliResult.setVulnerablePrimeraOcasion(true);
        }
        if (logLine.contains(SIN_PARAMETROS)) {
            sqliResult.setSinParametros(true);
        }
        if (logLine.contains(TARGET_INESTABLE)) {
            sqliResult.setInestable(true);
        }
        if (logLine.contains(URL_MALFORMADA) || logLine.contains(URL_INEXISTENTE) || logLine.contains(URL_INVALIDA)) {
            sqliResult.setUrlMalformada(true);
        }

        if (logLine.contains(NO_VULNERABLE)) {
            sqliResult.setSeguro(true);
        }

        if (logLine.contains(ERRORES_HTTP) || logLine.contains(ERROR_TIMEOUT)) {
            sqliResult.setWarnings(true);
        }
        if (logLine.contains(TARGET_STABLE)) {
            sqliResult.setSeguro(true);
        }
    }

    private String obtenerComandoPython(String target) {

        StringBuffer url = new StringBuffer();
        url.append(SqliConstants.PYTHON2);
        url.append(SqliConstants.SQLMAP);
        url.append(SqliConstants.COMMAND);
        url.append(target);
        url.append(SqliConstants.BATCH);
        url.append(CRAWL);
        //if(this.isUrlSinParametros){
        // url.append(CRAWL);
        //}

        url.append(" ");
        for(int i = 0; i< tecnicas.size() ;i++){
            if(tecnicas.get(i) == TECNICA_SQL_BOOLEAN_BASED){
                url.append(TECHNIQUE_B);
            }if(tecnicas.get(i) == TECNICA_SQL_ERROR_BASED){
                url.append(TECHNIQUE_E);
            }if(tecnicas.get(i) == TECNICA_SQL_UNION_QUERY_BASED){
                url.append(TECHNIQUE_U);
            }if(tecnicas.get(i) == TECNICA_SQL_STACKED_QUERIES){
                url.append(TECHNIQUE_S);
            }if(tecnicas.get(i) == TECNICA_SQL_TIME_BASED_BLIND){
                url.append(TECHNIQUE_T);
            }if(tecnicas.get(i) == TECNICA_SQL_TIME_INLINE_QUERIES){
                url.append(TECHNIQUE_Q);
            }
        }

        return url.toString();
    }

    public SqliResult getSqliResult() {
        return this.sqliResult;
    }

    public void setIsUrlSinParametros(boolean b) {
        this.isUrlSinParametros = b;
    }

    public boolean getIsUrlSinParametros(){
        return this.isUrlSinParametros;
    }

    public void setTecnicas(ArrayList<String> tecnicas){
        sqliResult.setTecnicasUsadas(tecnicas);
        this.tecnicas = tecnicas;
    }
}


