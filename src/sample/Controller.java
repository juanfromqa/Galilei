package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.SqliConstants.*;


public class Controller implements Initializable {

    @FXML
    private AnchorPane headerPanel;
    @FXML
    private AnchorPane homePanel;
    @FXML
    private AnchorPane urlSinParametrosPanel;
    @FXML
    private AnchorPane resultadoPanel;
    @FXML
    private AnchorPane cargandoPanel;
    @FXML
    private AnchorPane reportePanel;
    @FXML
    private AnchorPane acercaDePanel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private JFXTextField urlTextField;
    @FXML
    private JFXTextField urlTextField1;
    @FXML
    private Label ejecutandoTxt;
    @FXML
    private JFXCheckBox booleanBasedCheckBox;
    @FXML
    private JFXCheckBox errorBasedCheckBox;
    @FXML
    private JFXCheckBox unionBasedCheckBox;
    @FXML
    private JFXCheckBox stackedBasedCheckBox;
    @FXML
    private JFXCheckBox timeBasedCheckBox;
    @FXML
    private JFXCheckBox inlineBasedCheckBox;
    @FXML
    private JFXCheckBox checkBoxAtaque1;
    @FXML
    private JFXCheckBox checkBoxAtaque2;
    @FXML
    private JFXCheckBox checkBoxAtaque3;
    @FXML
    private JFXCheckBox checkBoxAtaque4;
    @FXML
    private JFXCheckBox checkBoxAtaque5;
    @FXML
    private JFXCheckBox checkBoxAtaque6;
    @FXML
    private JFXButton siguienteBtn;
    @FXML
    private JFXButton cancelarBtn;
    @FXML
    private JFXButton btnGenerarReporte;
    @FXML
    private JFXTextField txtUrlAnalizada;
    @FXML
    private Label txtDuracionTotal;
    @FXML
    private ImageView imgVulnerable;
    @FXML
    private ImageView imgInfo;
    @FXML
    private ImageView imgWarning;
    @FXML
    private Label txtReporte1;
    @FXML
    private Label txtReporte2;
    @FXML
    private Label txtReporte3;
    @FXML
    private Label txtReporte4;
    @FXML
    private Label txtReporte5;
    @FXML
    private Label txtDuracionDesc;
    @FXML
    private Label txtUrlError;
    @FXML
    private Label txtUrlError2;
    @FXML
    private ImageView imgNoVulnerable;
    @FXML
    private Label txtReporteSitio;
    @FXML
    private Label txtReporteVulnerable;
    @FXML
    private Label txtReporteAtaque5;
    @FXML
    private Label txtReporteDuracion;
    @FXML
    private Label txtReporteUsuario;
    @FXML
    private Label txtReporteServidor;
    @FXML
    private Label txtReporteDbms;
    @FXML
    private JFXTextArea txtReportTables;
    @FXML
    private JFXTextArea txtReportPayloads;
    @FXML
    private Label txtReporteUsuarioDba;

    private Boolean isSuccess;
    private ProgressThread pb;
    private boolean isUrlValida = false;
    private boolean isUrlSinParametros = false;
    private SqliExecutor sqliExecutor;
    ArrayList<String> tecnicas = new ArrayList<String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pb = new ProgressThread(progressBar);
        booleanBasedCheckBox.setSelected(true);
        btnGenerarReporte.setVisible(true);
        txtDuracionDesc.setVisible(true);
        txtDuracionTotal.setVisible(true);
        booleanBasedCheckBox.setSelected(true);
        errorBasedCheckBox.setSelected(true);
        unionBasedCheckBox.setSelected(true);
        stackedBasedCheckBox.setSelected(true);
        timeBasedCheckBox.setSelected(true);
        inlineBasedCheckBox.setSelected(true);
        txtUrlError.setVisible(false);
    }

    public void onAnalizerButtonClicked(MouseEvent mouseEvent) throws InterruptedException {

        if (isTecnicasSelected()) {
            ejecutarAnalisis();
            urlTextField1.setText(urlTextField.getText());
        } else {
            System.out.println("Hubo un error");
        }
    }

    private boolean isTecnicasSelected() {
        txtUrlError2.setVisible(false);
        if (booleanBasedCheckBox.isSelected())
            tecnicas.add(TECNICA_SQL_BOOLEAN_BASED);
        if (errorBasedCheckBox.isSelected())
            tecnicas.add(TECNICA_SQL_ERROR_BASED);
        if (unionBasedCheckBox.isSelected())
            tecnicas.add(TECNICA_SQL_UNION_QUERY_BASED);
        if (stackedBasedCheckBox.isSelected())
            tecnicas.add(TECNICA_SQL_STACKED_QUERIES);
        if (timeBasedCheckBox.isSelected())
            tecnicas.add(TECNICA_SQL_TIME_BASED_BLIND);
        if (inlineBasedCheckBox.isSelected())
            tecnicas.add(TECNICA_SQL_TIME_INLINE_QUERIES);
        if (!(tecnicas.isEmpty())) {
            return true;
        } else {
            txtUrlError2.setVisible(true);
            return false;
        }
    }

    public void onGenerarReporteButtonClicked(MouseEvent mouseEvent) throws InterruptedException {
        desactivarOtrosPanelesExcepto(reportePanel);
    }

    public void onContinuarAnalisisClicked() throws InterruptedException {
        desactivarOtrosPanelesExcepto(cargandoPanel);
        iniciarAnalisisSql();
    }

    public void onCancelarButtonClicked(MouseEvent mouseEvent) throws InterruptedException {
        if (pb.isAlive()) pb.interrupt();
        desactivarOtrosPanelesExcepto(homePanel);
        tecnicas.clear();
        txtUrlError.setVisible(false);
        txtUrlError2.setVisible(false);


    }

    public void onInfoButtonClicked(MouseEvent mouseEvent) throws InterruptedException {
        desactivarOtrosPanelesExcepto(acercaDePanel);
    }

    public void onInicioButtonClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void onSiguienteButtonClicked(MouseEvent mouseEvent) throws InterruptedException {
        desactivarOtrosPanelesExcepto(resultadoPanel);
        txtUrlAnalizada.setText(urlTextField.getText());
        presentarResumen(sqliExecutor.getSqliResult());
        System.err.println(sqliExecutor.getSqliResult());
        txtDuracionTotal.setText(sqliExecutor.getSqliResult().getDuracionEjecucion());
        if (isSuccess){
            llenarReporte(sqliExecutor.getSqliResult());
        }
    }

    // Helpers para esta clase
    private void desactivarOtrosPanelesExcepto(AnchorPane anchorPane) throws InterruptedException {
        homePanel.setVisible(false);
        resultadoPanel.setVisible(false);
        cargandoPanel.setVisible(false);
        reportePanel.setVisible(false);
        resultadoPanel.setVisible(false);
        acercaDePanel.setVisible(false);
        urlSinParametrosPanel.setVisible(false);
        anchorPane.setVisible(true);
        headerPanel.setVisible(true);
    }

    private void desactivarOtrasImagenesExcepto(ImageView imageView) {
        imgWarning.setVisible(false);
        imgVulnerable.setVisible(false);
        imgNoVulnerable.setVisible(false);
        imgInfo.setVisible(false);
        imageView.setVisible(true);
    }

    private void iniciarAnalisisSql() throws InterruptedException {
        txtUrlAnalizada.setText(urlTextField.getText());
        pb.start();
        pb.setShouldStop(false);
        sqliExecutor = new SqliExecutor();
        isSuccess = sqliExecutor.ejecutarAnalisisSql(urlTextField.getText() , tecnicas);
        pb.setShouldStop(isSuccess);
        if (isSuccess) {
            ejecutandoTxt.setText("Análisis finalizado");
            siguienteBtn.setVisible(true);
            cancelarBtn.setVisible(false);
            sqliExecutor.getSqliResult().setUrl(txtUrlAnalizada.getText());
        } else {
            ejecutandoTxt.setText("Ha ocurrido un error inesperado!");
        }
    }

    private void continuarAnalisis() throws InterruptedException {
        desactivarOtrosPanelesExcepto(urlSinParametrosPanel);
    }

    private boolean validarUrlSinParametros(String url) {
        if (!(url.contains("id=") || url.contains("ID="))) {
            return true;
        }
        return false;
    }

    public boolean validarUrl(String url) {
        if (url.contains(".") && !url.isEmpty()) {
            isUrlValida = true;
        } else {
            isUrlValida = false;
        }
        return isUrlValida;
    }

    public void ejecutarAnalisis() throws InterruptedException {

        isUrlValida = validarUrl(urlTextField.getText());


        if (isUrlValida) {
            isUrlSinParametros = validarUrlSinParametros(urlTextField.getText());
            if (isUrlSinParametros) {
                continuarAnalisis();
            } else {
                if (isUrlValida) {
                    desactivarOtrosPanelesExcepto(cargandoPanel);
                    iniciarAnalisisSql();
                    sqliExecutor.setIsUrlSinParametros(true);
                }
            }

        } else {
            txtUrlError.setVisible(true);
        }
    }

    private void presentarResumen(SqliResult sqliResult) {

        if (sqliResult.isSeguro) {
            desactivarOtrasImagenesExcepto(imgNoVulnerable);
            txtReporte1.setText(MSJ_NO_VULNERABLE);
        }

        if (sqliResult.isUrlMalformada()) {
            desactivarOtrasImagenesExcepto(imgInfo);
            txtReporte1.setText(MSJ_URL_MALFORMADA);
            btnGenerarReporte.setVisible(false);
            txtDuracionTotal.setVisible(false);
            txtDuracionDesc.setVisible(false);
        }

        if (sqliResult.isVulnerable || sqliResult.isVulnerablePrimeraOcasion) {
            desactivarOtrasImagenesExcepto(imgVulnerable);
            txtReporte1.setText(MSJ_VULNERABLE_1);
            txtReporte5.setVisible(true);
            txtReporte5.setText(MSJ_VULNERABLE_2);
        }

        if (sqliResult.isWarning || sqliResult.isHttpError) {
            desactivarOtrasImagenesExcepto(imgWarning);
            txtReporte1.setText(MSJ_WARNING_1);
            txtReporte2.setVisible(true);
            txtReporte3.setVisible(true);
            txtReporte4.setVisible(true);
            txtReporte2.setText(MSJ_WARNING_2);
            txtReporte3.setText(MSJ_WARNING_TIMEOUT);
            txtReporte4.setText(MSJ_WARNING_HTTP);
        }
    }

    private void llenarReporte(SqliResult sqliResult) {
        sqliExecutor.setTecnicas(tecnicas);
        txtReporteSitio.setText(" " + sqliResult.getUrl());
        if (sqliResult.isVulnerable) {
            txtReporteVulnerable.setText(" " + "SI");
        } else {
            txtReporteVulnerable.setText(" " + "NO");
        }
        txtReporteDuracion.setText(" " + sqliResult.getDuracionEjecucion());
        if (sqliResult.getDbms() == null) {
            txtReporteDbms.setText("");
        } else {
            txtReporteDbms.setText(" " + sqliResult.getDbms());
        }
        if (sqliResult.isUsuarioDba) {
            txtReporteUsuarioDba.setText(" SI");
        } else {
            if (sqliResult.getUsuarioDb() == null) {
                txtReporteUsuarioDba.setText("");
            } else {
                txtReporteUsuarioDba.setText(" NO");
            }
        }
        if (sqliResult.getUsuarioDb() == null) {
            txtReporteUsuario.setText("");
        } else {
            txtReporteUsuario.setText(" " + sqliResult.getUsuarioDb());
        }
        txtReporteServidor.setText(sqliResult.getWebServer());
        if (!(sqliResult.getSqliPayload() == null || sqliResult.getTablas() == null)) {
            txtReportPayloads.setVisible(true);
            txtReportTables.setVisible(true);
            txtReportPayloads.setText(sqliResult.getSqliPayload().toString());
            txtReportTables.setText(sqliResult.getTablas().toString());
        }
        if (!(sqliResult.isUrlMalformada() || sqliResult.isInestable)) {
            for (int i = 0; i < sqliResult.getTecnicasUsadas().size(); i++) {

                if (sqliResult.getTecnicasUsadas().get(i) == TECNICA_SQL_BOOLEAN_BASED) {
                    checkBoxAtaque1.setSelected(true);
                }
                if (sqliResult.getTecnicasUsadas().get(i) == TECNICA_SQL_ERROR_BASED) {
                    checkBoxAtaque2.setSelected(true);
                }
                if (sqliResult.getTecnicasUsadas().get(i) == TECNICA_SQL_UNION_QUERY_BASED) {
                    checkBoxAtaque3.setSelected(true);
                }
                if (sqliResult.getTecnicasUsadas().get(i) == TECNICA_SQL_STACKED_QUERIES) {
                    checkBoxAtaque4.setSelected(true);
                }
                if (sqliResult.getTecnicasUsadas().get(i) == TECNICA_SQL_TIME_BASED_BLIND) {
                    checkBoxAtaque5.setSelected(true);
                }
                if (sqliResult.getTecnicasUsadas().get(i) == TECNICA_SQL_TIME_INLINE_QUERIES) {
                    checkBoxAtaque6.setSelected(true);
                }

            }
        }


    }
}




