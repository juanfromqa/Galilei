package sample;

public class SqliTabla {
    public String baseDeDatos;
    public String tabla;
    public String columnas;

    @Override
    public String toString() {
        return "SqliTabla{" +
                "baseDeDatos='" + baseDeDatos + '\'' +
                ", tabla='" + tabla + '\'' +
                ", columnas=" + columnas +
                '}';
    }

    public String getBaseDeDatos() {
        return baseDeDatos;
    }

    public void setBaseDeDatos(String baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getColumnas() {
        return columnas;
    }

    public void setColumnas(String columnas) {
        this.columnas = columnas;
    }




}
