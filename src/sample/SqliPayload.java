package sample;

public class SqliPayload {
    public String payload;
    public String titulo;
    public String tipo;

    @Override
    public String toString() {
        return "SqliPayload{" +
                "payload='" + payload + '\'' +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {

        this.payload = payload.substring(13);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo.substring(11);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.substring(10);
    }



}
