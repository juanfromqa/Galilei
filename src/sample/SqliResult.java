package sample;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import static sample.SqliConstants.*;

public class SqliResult {
    public boolean isErrorNoExiste;
    public boolean isVulnerable;
    public boolean isVulnerablePrimeraOcasion;
    public boolean isSinParametros;
    public boolean isSeguro;
    public boolean setUrlMalformada;
    public boolean isInestable;
    public boolean isWarning;
    public boolean isHttpError;
    public boolean isUsuarioDba;
    public String linksVisitados;
    public String peticionesHttp;
    public String url;
    public String fechaInicio;
    public String fechaFin;
    public String inicioEjecucion;
    public String finEjecucion;
    public String duracionEjecucion;
    public String usuarioDb;
    public String webServer;
    public String dbms;
    public ArrayList<SqliPayload> sqliPayloads;
    public ArrayList<SqliTabla> tablas;
    public ArrayList<String> tecnicasUsadas;

    @Override
    public String toString() {
        return "SqliResult{" +
                "url=" + url +
                "isErrorNoExiste=" + isErrorNoExiste +
                ", isVulnerable=" + isVulnerable +
                ", isVulnerablePrimeraOcasion=" + isVulnerablePrimeraOcasion +
                ", isSinParametros=" + isSinParametros +
                ", isSeguro=" + isSeguro +
                ", setUrlMalformada=" + setUrlMalformada +
                ", isInestable=" + isInestable +
                ", isWarning=" + isWarning +
                ", isHttpError=" + isHttpError +
                ", inicioEjecucion='" + inicioEjecucion + '\'' +
                ", finEjecucion='" + finEjecucion + '\'' +
                ", duracionEjecucion='" + duracionEjecucion + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", tecnicasUsadas='" + tecnicasUsadas + '\'' +
                ", usuarioDb='" + usuarioDb + '\'' +
                ", isUsuarioDba='" + isUsuarioDba + '\'' +
                ", webServer='" + webServer + '\'' +
                ", dbms='" + dbms + '\'' +
                ", linksVisitados='" + linksVisitados + '\'' +
                ", peticionesHttp='" + peticionesHttp + '\'' +
                ", sqliPayloads='" + sqliPayloads + '\'' +
                ", tablas='" + tablas + '\'' +
                '}';
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public ArrayList<SqliTabla> getTablas() {
        return tablas;
    }

    public void setTablas(ArrayList<SqliTabla> tablas) {
        for (int i = 1; i > tablas.size(); i++){
            tablas.get(i).getBaseDeDatos().substring(10);
            tablas.get(i).getTabla().substring(7);
        }

        this.tablas = tablas;
    }

    public ArrayList<SqliPayload> getSqliPayload() {
        return sqliPayloads;
    }

    public void setSqliPayload(ArrayList<SqliPayload> sqliPayloads) {

        for (int i = 1; i > sqliPayloads.size(); i++){
            sqliPayloads.get(i).getTitulo().substring(11);
            sqliPayloads.get(i).getTipo().substring(10);

        }

        this.sqliPayloads = sqliPayloads;
    }

    public String getPeticionesHttp() {
        return peticionesHttp;
    }

    public void setPeticionesHttp(String peticionesHttp) {
        System.out.println("peticiones???? " +peticionesHttp);
        this.peticionesHttp = peticionesHttp.substring(67,71);
    }

    public String getLinksVisitados() {
        return linksVisitados;
    }

    public void setLinksVisitados(String linksVisitados) {
        this.linksVisitados = linksVisitados;
    }

    public String getDbms() {
        return dbms;
    }

    public void setDbms(String dbms) {

        this.dbms = dbms.substring(15);
    }

    public String getWebServer() {
        return webServer;
    }

    public void setWebServer(String webServer) {
        System.out.println("dbms???? -> "+webServer);
        this.webServer = webServer.substring(28);
    }

    public boolean isUsuarioDba() {
        return isUsuarioDba;
    }

    public void setUsuarioDba(String usuarioDba) {
        this.isUsuarioDba = usuarioDba.contains("True");
    }

    public String getUsuarioDb() {
        return usuarioDb;
    }

    public void setUsuarioDb(String usuarioDb) {
        String usuario = usuarioDb.substring(17);
        this.usuarioDb = usuario.substring(1, usuario.length()-1);
    }

    public ArrayList<String> getTecnicasUsadas() {
        return tecnicasUsadas;
    }

    public void setTecnicasUsadas(ArrayList<String> tecnicas) {
        this.tecnicasUsadas = tecnicas;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }



    public boolean isSetUrlMalformada() {
        return setUrlMalformada;
    }

    public void setSetUrlMalformada(boolean setUrlMalformada) {
        this.setUrlMalformada = setUrlMalformada;
    }

    public void setWarning(boolean warning) {
        isWarning = warning;
    }

    public boolean isHttpError() {
        return isHttpError;
    }

    public void setHttpError(boolean httpError) {
        isHttpError = httpError;
    }

    public boolean isErrorNoExiste() {
        return isErrorNoExiste;
    }

    public boolean isVulnerable() {
        return isVulnerable;
    }

    public boolean isVulnerablePrimeraOcasion() {
        return isVulnerablePrimeraOcasion;
    }

    public boolean isSinParametros() {
        return isSinParametros;
    }

    public boolean isSeguro() {
        return this.isSeguro;
    }

    public boolean isUrlMalformada() {
        return setUrlMalformada;
    }

    public boolean isInestable() {
        return isInestable;
    }

    public boolean isWarning() {
        return isWarning;
    }

    public String getInicioEjecucion() {
        return inicioEjecucion;
    }

    public String getFinEjecucion() {
        return finEjecucion;
    }

    public void setErrorNoExiste(boolean errorNoExiste) {
        isErrorNoExiste = errorNoExiste;
    }

    public void setVulnerable(boolean vulnerable) {
        isVulnerable = vulnerable;
    }

    public void setSinParametros(boolean sinParametros) {
        isSinParametros = sinParametros;
    }

    public void setSeguro(boolean seguro) {
        isSeguro = seguro;
    }

    public void setUrlMalformada(boolean malformado) {
        setUrlMalformada = malformado;
    }

    public void setInestable(boolean isInesable) {
        isInestable = isInesable;
    }

    public void setVulnerablePrimeraOcasion(boolean vulnerablePrimeraOcasion) {
        isVulnerablePrimeraOcasion = vulnerablePrimeraOcasion;
    }

    public void setInicioEjecucion(String inicioEjecucion) {
        String inicio = inicioEjecucion.substring(15, 23);
        Date myDate = new Date();
        SimpleDateFormat mdyFormat;
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
        this.inicioEjecucion = fecha.concat("T").concat(inicio);
    }

    public void setFinEjecucion(String finEjecucion) {
        String fin = finEjecucion.substring(13, 21);
        Date myDate = new Date();
        SimpleDateFormat mdyFormat;
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
        this.finEjecucion = fecha.concat("T").concat(fin);
    }

    public void setWarnings(boolean warnings) {
        this.isWarning = true;
    }

    public void setDuracionEjecucion(String inicio, String fin) {

        this.fechaInicio = inicio;
        this.fechaFin = fin;

        int intSegundos;
        int intHoras;
        int intMinutos;
        String horas;
        String minutos;
        String segundos;

        LocalDateTime localDateInicio = LocalDateTime.parse(inicio);
        LocalDateTime localDateFin = LocalDateTime.parse(fin);
        intHoras = localDateFin.getHour() - localDateInicio.getHour();
        intMinutos = localDateFin.getMinute() - localDateInicio.getMinute();

        if (localDateFin.getSecond() > localDateInicio.getSecond()) {
            intSegundos = localDateFin.getSecond() - localDateInicio.getSecond();
        } else {
            intSegundos = localDateInicio.getSecond() - localDateFin.getSecond();
        }

        horas = String.format("%02d", intHoras);
        minutos = String.format("%02d", intMinutos);
        segundos = String.format("%02d", intSegundos);

        this.duracionEjecucion = horas + ":" + minutos + ":" + segundos;
    }

    public String getDuracionEjecucion() {
        return duracionEjecucion;
    }


}
