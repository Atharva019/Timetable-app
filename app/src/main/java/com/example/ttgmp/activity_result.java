package com.example.ttgmp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class activity_result extends AppCompatActivity {
    Button create,share,open;
    String filePath;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toast.makeText(this, "Working on timetable", Toast.LENGTH_SHORT).show();
        StorageManager manager = (StorageManager) getSystemService(STORAGE_SERVICE);
        StorageVolume volume = manager.getStorageVolumes().get(0);
        File input = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            filePath = volume.getDirectory().getPath()+"/Download/timetable.xlsx";
            input = new File(filePath);
        }
        if (!input.exists()){
            try {
                input.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "file created", Toast.LENGTH_SHORT).show();
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("mono");
        Toast.makeText(this, "Workbook created", Toast.LENGTH_SHORT).show();
        HSSFRow rowHead = (sheet).createRow((short) 0);
        //writing head row of timetable
        rowHead.createCell(0).setCellValue("Day/Time");
        rowHead.createCell(1).setCellValue("9.00-10.00");
        rowHead.createCell(2).setCellValue("10.00-11.00");
        rowHead.createCell(3).setCellValue("11.00-12.00");
        rowHead.createCell(4).setCellValue("12.00-1.00");
        rowHead.createCell(5).setCellValue("1.00-2.00");
        rowHead.createCell(6).setCellValue("2.00-3.00");
        rowHead.createCell(7).setCellValue("3.00-4.00");

        Toast.makeText(this, "Row head added", Toast.LENGTH_SHORT).show();

        String mad = "Mobile Application Development";
        String mgt = "Management";
        String cpe = "Capstone Project Execution";
        String eti = "Emerging Trends in computer & Information Technology";
        String ede = "Entrepreneurship Development";
        String pwp = "Programming with Python";
        String wbp = "Web Based Application Development Using PHP";
        HSSFRow row1 = (sheet).createRow((short)1);
        row1.createCell(0).setCellValue("Monday");
        row1.createCell(1).setCellValue(mad);
        row1.createCell(2).setCellValue(cpe);
        row1.createCell(3).setCellValue(mgt);
        row1.createCell(4).setCellValue(eti);
        row1.createCell(5).setCellValue("B");
        row1.createCell(6).setCellValue(cpe);
        row1.createCell(7).setCellValue(mad);

        HSSFRow row2 = (sheet).createRow((short)2);
        row2.createCell(0).setCellValue("Tuesday");
        row2.createCell(1).setCellValue(mad);
        row2.createCell(2).setCellValue(mad);
        row2.createCell(3).setCellValue(mgt);
        row2.createCell(4).setCellValue(eti);
        row2.createCell(5).setCellValue("R");
        row2.createCell(6).setCellValue(cpe);
        row2.createCell(7).setCellValue(mad);

        HSSFRow row3 = (sheet).createRow((short)3);
        row3.createCell(0).setCellValue("Wednesday");
        row3.createCell(1).setCellValue(wbp);
        row3.createCell(2).setCellValue(pwp);
        row3.createCell(3).setCellValue(mgt);
        row3.createCell(4).setCellValue(mgt);
        row3.createCell(5).setCellValue("E");
        row3.createCell(6).setCellValue(wbp);
        row3.createCell(7).setCellValue(ede);

        HSSFRow row4 = (sheet).createRow((short)4);
        row4.createCell(0).setCellValue("Thursday");
        row4.createCell(1).setCellValue(pwp);
        row4.createCell(2).setCellValue(mad);
        row4.createCell(3).setCellValue(ede);
        row4.createCell(4).setCellValue(wbp);
        row4.createCell(5).setCellValue("A");
        row4.createCell(6).setCellValue(pwp);
        row4.createCell(7).setCellValue(pwp);

        HSSFRow row5 = (sheet).createRow((short)5);
        row5.createCell(0).setCellValue("Friday");
        row5.createCell(1).setCellValue(mgt);
        row5.createCell(2).setCellValue(mad);
        row5.createCell(3).setCellValue(eti);
        row5.createCell(4).setCellValue(eti);
        row5.createCell(5).setCellValue("K");
        row5.createCell(6).setCellValue(mad);
        row5.createCell(7).setCellValue(mgt);

        //'B' Division timetable
        HSSFRow B_div = (sheet).createRow((short)7);
        B_div.createCell(3).setCellValue("B Division");
        HSSFRow Brow1 = (sheet).createRow((short)8);
        Brow1.createCell(0).setCellValue("Monday");
        Brow1.createCell(1).setCellValue(cpe);
        Brow1.createCell(2).setCellValue(ede);
        Brow1.createCell(3).setCellValue(pwp);
        Brow1.createCell(4).setCellValue(cpe);
        Brow1.createCell(5).setCellValue("B");
        Brow1.createCell(6).setCellValue(ede);
        Brow1.createCell(7).setCellValue(eti);

        HSSFRow Brow2 = (sheet).createRow((short)9);
        Brow2.createCell(0).setCellValue("Tuesday");
        Brow2.createCell(1).setCellValue(cpe);
        Brow2.createCell(2).setCellValue(pwp);
        Brow2.createCell(3).setCellValue(eti);
        Brow2.createCell(4).setCellValue(mad);
        Brow2.createCell(5).setCellValue("R");
        Brow2.createCell(6).setCellValue(mad);
        Brow2.createCell(7).setCellValue(ede);

        HSSFRow Brow3 = (sheet).createRow((short)10);
        Brow3.createCell(0).setCellValue("Wednesday");
        Brow3.createCell(1).setCellValue(mad);
        Brow3.createCell(2).setCellValue(eti);
        Brow3.createCell(3).setCellValue(eti);
        Brow3.createCell(4).setCellValue(ede);
        Brow3.createCell(5).setCellValue("E");
        Brow3.createCell(6).setCellValue(pwp);
        Brow3.createCell(7).setCellValue(wbp);

        HSSFRow Brow4 = (sheet).createRow((short)11);
        Brow4.createCell(0).setCellValue("Thursday");
        Brow4.createCell(1).setCellValue(mgt);
        Brow4.createCell(2).setCellValue(mgt);
        Brow4.createCell(3).setCellValue(pwp);
        Brow4.createCell(4).setCellValue(pwp);
        Brow4.createCell(5).setCellValue("A");
        Brow4.createCell(6).setCellValue(cpe);
        Brow4.createCell(7).setCellValue(cpe);

        HSSFRow Brow5 = (sheet).createRow((short)12);
        Brow5.createCell(0).setCellValue("Friday");
        Brow5.createCell(1).setCellValue(ede);
        Brow5.createCell(2).setCellValue(cpe);
        Brow5.createCell(3).setCellValue(pwp);
        Brow5.createCell(4).setCellValue(mgt);
        Brow5.createCell(5).setCellValue("K");
        Brow5.createCell(6).setCellValue(wbp);
        Brow5.createCell(7).setCellValue(wbp);

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(filePath);

            workbook.write(fileOut);
            //closing the Stream
            fileOut.close();
            //closing the workbook
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, "all data successfully written in file", Toast.LENGTH_SHORT).show();
        open = findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                Intent in_crt = new Intent(Intent.ACTION_VIEW, MediaStore.Downloads.INTERNAL_CONTENT_URI);
                in_crt.setType("*/*");
                startActivity(in_crt);
            }
        });
//        try {
//            G12.TimetableGA.main(null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //Timetable timetable = initializeTimetable();
        //tt.setText(""+Adapter.teachInfos_arr);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showResult(int classIndex, String module, int groupId, String room, String professor, String time) throws IOException {
        Toast.makeText(this, "in show result method", Toast.LENGTH_SHORT).show();
//        StorageManager manager = (StorageManager) getSystemService(STORAGE_SERVICE);
//        StorageVolume volume = manager.getStorageVolumes().get(0);
//        File input = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
//            filePath = volume.getDirectory().getPath()+"/Download/first.xlsx";
//            input = new File(filePath);
//        }
//        if (!input.exists()){
//            try {
//                input.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Toast.makeText(getApplicationContext(), "file created", Toast.LENGTH_SHORT).show();
//        }else {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("mono");
            Toast.makeText(this, "Workbook created", Toast.LENGTH_SHORT).show();
            HSSFRow rowHead = (sheet).createRow((short) 0);
            //writing head row of timetable
            rowHead.createCell(0).setCellValue("Day/Time");
            rowHead.createCell(1).setCellValue("9.00-10.00");
            rowHead.createCell(2).setCellValue("10.00-11.00");
            rowHead.createCell(3).setCellValue("11.00-12.00");
            rowHead.createCell(4).setCellValue("12.00-1.00");
            rowHead.createCell(5).setCellValue("1.00-2.00");
            rowHead.createCell(6).setCellValue("2.00-3.00");
            rowHead.createCell(7).setCellValue("3.00-4.00");

            Toast.makeText(this, "Row head added", Toast.LENGTH_SHORT).show();
            int rowCounter = 1, cellCounter = 7;
            for (int i = 0; i < cellCounter; i++) {
                //creating the other rows
                HSSFRow row = sheet.createRow((short) rowCounter);
                row.createCell(i).setCellValue(module);
                rowCounter++;
            }


            HSSFRow staffHead = sheet.createRow((short) rowCounter + 3);
            staffHead.createCell(1).setCellValue("Subject");
            staffHead.createCell(2).setCellValue("Teacher");
            HSSFRow staff = sheet.createRow((short) rowCounter + 1);
            staff.createCell(1).setCellValue("" + module);
            staff.createCell(2).setCellValue("" + professor);

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            //closing the Stream
            fileOut.close();
            //closing the workbook
            workbook.close();

            Toast.makeText(this, "all data successfully written in file", Toast.LENGTH_SHORT).show();
//        }
    }
}