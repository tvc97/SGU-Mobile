package com.example.kiemdien;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.*;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnBarcode;
    private Button btnSearch;
    private EditText txtMSSV;
    private Button btnLoad;
    private Button btnSave;
    private ListView lvSV;

    private final int FILE_REQUEST_CODE = 0xBABE;
    private final int PERMISSIONS_REQUEST_CODE = 0xCAFE;
    private final int PERMISSIONS_WRITE_CODE = 0xAAAA;

    public StudentListAdapter adapter;
    private ArrayList<Student> studentList, _temp;
    private String filePath, currentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBarcode = (ImageButton) findViewById(R.id.btnBarcode);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        txtMSSV = (EditText) findViewById(R.id.txtMSSV);
        lvSV = (ListView) findViewById(R.id.lvSV);

        btnBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScan();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyFilter(txtMSSV.getText().toString());
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionsAndOpenFilePicker();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToCsv();
            }
        });

        txtMSSV.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    public void loadFromCsv(String path) {
        currentFilter = "";
        if(studentList == null) {
            studentList = _temp = new ArrayList<>();
        } else {
            studentList.clear();
            _temp.clear();
        }
        try {
            FileReader fr = new FileReader(path);
            CSVReader reader = new CSVReader(fr);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Student std = new Student(nextLine[0], nextLine[1], nextLine[2].replace(' ', '\n'));
                studentList.add(std);
            }
            fr.close();
        } catch (IOException e) {

        }

        adapter = new StudentListAdapter(this, R.layout.adapter_view_layout, studentList);
        lvSV.setAdapter(adapter);
    }

    public void saveToCsv() {
        if(filePath != null) {
            checkWritePermission();
            StringBuffer sb = new StringBuffer();
            for(Student std: studentList) {
                sb.append(std.getId() + "," + std.getName() + "," + std.getCheckIn().replace('\n', ' ') + "\n");
            }
            writeToFile(sb.toString().trim(), filePath);
        }
    }

    public void applyFilter(String mssv) {
        if(studentList != null) {
            currentFilter = mssv;
            _temp = new ArrayList<>();
            for(Student std :  studentList) {
                if(std.getId().contains(mssv)) {
                    _temp.add(std);
                }
            }
            adapter = new StudentListAdapter(this, R.layout.adapter_view_layout, _temp);
            lvSV.setAdapter(adapter);
        }
    }

    public void updateTimeForMSSV(String mssv) {
        if(studentList != null) {
            for(Student std : studentList) {
                if(std.getId() == mssv) {
                    std.setCheckIn(getCurrentTime());
                }
            }
            applyFilter(currentFilter);
            Toast.makeText(this, "Đã cập nhật kiểm diện", Toast.LENGTH_SHORT).show();
        }
    }

    public String getCurrentTime() {
        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss\ndd/MM/yyyy");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
        return stringBuffer.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if(result != null) {
                    if(result.getContents() == null) {

                    } else {
                        txtMSSV.setText(result.getContents());
                    }
                } else {
                    super.onActivityResult(requestCode, resultCode, data);
                }
                break;

            case FILE_REQUEST_CODE:
                filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                loadFromCsv(filePath);
                break;
        }
    }

    public void startScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(Portrait.class);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("Quét mã barcode");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    public void openFilePicker() {
        new MaterialFilePicker()
                .withTitle("Chọn file csv")
                .withPath("/sdcard")
                .withActivity(this)
                .withRequestCode(FILE_REQUEST_CODE)
                .withFilter(Pattern.compile(".*\\.csv$"))
                .start();
    }

    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void checkWritePermission() {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_WRITE_CODE);
            }
        } else {
        }
    }

    private void showError() {
        Toast.makeText(this, "Vui lòng cho phép truy cập dữ liệu", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
            break;
            case PERMISSIONS_WRITE_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    showError();
                }
            }

        }
    }

    private void writeToFile(String data, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            fos.close();
            Toast.makeText(this, "Đã lưu thành công", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
