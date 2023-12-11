package com.tomtech.colegio2.activity.QR;
import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import com.tomtech.colegio2.R;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QRLectorActivity extends AppCompatActivity {
    private Vibrator vibrator;
    private static final String TAG = QRLectorActivity.class.getSimpleName();
    private static final int CAMERA_PERMISSION_REQUEST = 123;
    private boolean isProcessingResult = false;
    private Set<String> scannedCodes = new HashSet<>();
    private DecoratedBarcodeView barcodeView;


    private BarcodeCallback barcodeCallback = new BarcodeCallback() {



        @Override
        public void barcodeResult(BarcodeResult result) {
            MediaPlayer mediaPlayer = MediaPlayer.create(QRLectorActivity.this,R.raw.beep);

           mediaPlayer.start();
            // Obtener el resultado del escaneo del código de barras

            if (isProcessingResult) {
                // Ya se está procesando un resultado, ignorar el escaneo actual
                return;
            }
            isProcessingResult = true;
            String barcodeValue = result.getText();

            if (scannedCodes.contains(barcodeValue)) {
                // El código ya ha sido escaneado, puedes ignorarlo o mostrar un mensaje
                Toast.makeText(QRLectorActivity.this, "Este código ya ha sido escaneado", Toast.LENGTH_SHORT).show();
            } else {
                // Agregar el código escaneado a la lista de códigos escaneados

              //  registo_qr.textos.add(barcodeValue);


                QRregistroActivity.llenar(barcodeValue);
                barcodeView.getBarcodeView().stopDecoding();
                barcodeView.getBarcodeView().pause();

                finish();

                // Realizar acciones adicionales con el código escaneado
                Toast.makeText(QRLectorActivity.this, "Código de barras: " + barcodeValue, Toast.LENGTH_SHORT).show();



            }
            isProcessingResult = false;
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
            // Aquí puedes realizar alguna acción cuando se detecten posibles puntos de resultado
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_lector);

        barcodeView = findViewById(R.id.barcode_scanner_view);

        // Configurar formatos de códigos de barras para escanear
        Collection<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39);
        barcodeView.getBarcodeView().setDecoderFactory(new DefaultDecoderFactory(formats));




        // Configurar callback para recibir el resultado del escaneo
        barcodeView.decodeContinuous(barcodeCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestCameraPermission();
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
        } else {
            // Permiso de cámara concedido, iniciar la vista previa
            startCameraPreview();
        }
    }

    private void startCameraPreview() {
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
        barcodeView.getBarcodeView().stopDecoding();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        barcodeView.getBarcodeView().stopDecoding();
    }

}