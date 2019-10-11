package com.example.mwservice.svcpassbook;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.itextpdf.text.Annotation.FILE;
import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_TOP;

public class EntriesActivity extends AppCompatActivity {
    TextView txtAccNumber;
    TextView txtDate, txtDate1, txtDate2, txtDate3, txtDate4, txtprint;
    String strName = " Nihar Atul Prabhu", strContact = "9821831167", strAddress = "GB26, Highland Corporate Center,\nThane West.400607";
    String strAccNumber;
    RelativeLayout rlMain;
    NotificationCompat.Builder mBuilder;
    NotificationManager mNotifyManager;
    ListView listView;
    ArrayList<DataModel> dataModels;
    public String strDate, strDate0, strDate1, strDate2, strDate3, strDate4;
    public static CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.small_svc_logo_dash);
        getSupportActionBar().setTitle(" m-Passbook");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        txtDate = (TextView) findViewById(R.id.textView13);
        txtDate1 = (TextView) findViewById(R.id.textView21);

        txtprint = (TextView) findViewById(R.id.textView14);
        listView = (ListView) findViewById(R.id.listview);

        dataModels = new ArrayList<>();
        dataModels.add(new DataModel("04/04/2018", "Goibibo/534sal", "9916434252", "2500", "", "85,543.91Cr"));
        dataModels.add(new DataModel("18/03/2018", "NEFT Transfer Ajinkya Vichare", "9816434242", "1500", "", "88,043.91Cr"));
        dataModels.add(new DataModel("12/03/2018", "Cash withdrawal Near Kapurbawdi", "9416434232", "5000", "", "89,543.71Cr"));
        dataModels.add(new DataModel("10/02/2018", "Goibibo/524sal", "8916434232", "2500", "", "94,543.71Cr"));
        dataModels.add(new DataModel("12/01/2018", "Interest 5412455", "6466434232", "", "675", "97,043.71Cr"));
        dataModels.add(new DataModel("03/01/2018", "NEFT Transfer Yashwant Vichare", "6466434232", "1200", "", "96,368.71Cr"));

        adapter = new CustomListAdapter(dataModels, getApplicationContext());
        listView.setAdapter(adapter);

        txtDate1 = (TextView) findViewById(R.id.txtStartDate);
        txtDate2 = (TextView) findViewById(R.id.eg14);
        txtAccNumber = (TextView) findViewById(R.id.eg12);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        strDate1 = preferences.getString("Date1", "empty");
        strDate2 = preferences.getString("Date2", "empty");
        strAccNumber = preferences.getString("AccNumber", "empty");

        txtAccNumber.setText(strAccNumber);
        txtDate1.setText(strDate1);
        txtDate2.setText(strDate2);
    }

    private void download() {
        String FILE = Environment.getExternalStorageDirectory().toString() + "/Download/" + strAccNumber + ".pdf";

        Document document = new Document(PageSize.A4);

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/PDF");
        myDir.mkdirs();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(FILE));

            // Open Document for Writting into document
            document.open();

            // User Define Method
            addMetaData(document);
            addTitlePage(document);
            document.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Close Document after writting all content


        Toast.makeText(EntriesActivity.this, "PDF File is Created. Location : " + FILE,
                Toast.LENGTH_LONG).show();
    }

    public void addMetaData(Document document) {
        document.addTitle("RESUME");
        document.addSubject("Person Info");
        document.addKeywords("Personal,	Education, Skills");
        document.addAuthor("TAG");
        document.addCreator("TAG");
    }

    public void addTitlePage(Document document) throws DocumentException {
        // Font Style for Document
        Font nameTitle = new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD);
        Font nameTitle2 = new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.NORMAL);
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD
                | Font.UNDERLINE, BaseColor.GRAY);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        Paragraph preface = new Paragraph();


        Paragraph para1 = new Paragraph();

       /* Drawable d = getResources ().getDrawable (R.drawable.super_small_svc_logo);
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapData = stream.toByteArray();
        try {
            Image i = Image.getInstance(bitmapData);
            i.setAlignment(ALIGN_CENTER |ALIGN_TOP);
            para1.add(i);
            para1.add("\n");
            para1.add("\n");
            para1.add("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        para1.setFont(nameTitle2);
        para1.add("SVC");
        para1.setFont(nameTitle);
        para1.add("  CO-OPERATIVE");
        para1.setFont(nameTitle2);
        para1.add(" BANK LTD");
        para1.add("\n");
        para1.add("\n");
        para1.setAlignment(Element.ALIGN_LEFT);


        Paragraph para2 = new Paragraph("Account ID :  " + strAccNumber);
        para2.setAlignment(Element.ALIGN_LEFT);

        Paragraph para0 = new Paragraph("Between : " + strDate1 + "-" + strDate2);
        para0.setAlignment(Element.ALIGN_RIGHT);

        Paragraph para3 = new Paragraph("Name :  " + strName);
        // para4.setIndentationLeft(78);
        para3.setAlignment(Element.ALIGN_LEFT);

        Paragraph para4 = new Paragraph("Contact Number : " + strContact);
        para4.setAlignment(Element.ALIGN_LEFT);

        Paragraph para5 = new Paragraph("Communication : " + strAddress);
        para5.add("\n");
        para5.add("\n");
        para5.add("\n");
        para5.setAlignment(Element.ALIGN_LEFT);
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", normal);
        Section subCatPart = catPart.addSection(subPara);



        createTable(subCatPart);

        document.add(para1);
        document.add(para2);
        document.add(para0);
        document.add(para3);
        document.add(para4);
        document.add(para5);

        document.add(preface);


    }


    private static void createTable(Section subCatPart)

            throws DocumentException {

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2, 3, 2, 2, 2, 3});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);


        PdfPCell c1 = new PdfPCell(new Phrase("Date"));
        c1.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Particular"));
        c1.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cheque \nNumber"));
        c1.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Withdraw\n(dr)"));
        c1.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Deposit\n(Cr)"));
        c1.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Balance"));
        c1.setHorizontalAlignment(ALIGN_CENTER);
        table.addCell(c1);


        table.setHeaderRows(1);

        table.getDefaultCell().setHorizontalAlignment(ALIGN_CENTER);

        table.addCell("04/04/2018");
        table.addCell("Goibibo/534sal");
        //table.addCell("9916434252");
        table.addCell("2500");
        table.addCell("");
        table.addCell("85,543.91Cr");


        table.setHeaderRows(2);

        table.addCell("18/03/2018");
        table.addCell("NEFT Transfer Ajinkya Vichare");
        table.addCell("9816434242");
        table.addCell("1500");
        table.addCell("");
        table.addCell("88,043.91Cr");

        table.setHeaderRows(3);

        table.addCell("12/03/2018");
        table.addCell("Cash withdrawal Near Kapurbawdi");
        table.addCell("9416434232");
        table.addCell("5000");
        table.addCell("");
        table.addCell("89,543.71Cr");

        table.setHeaderRows(4);

        table.addCell("10/02/2018");
        table.addCell("Goibibo/524sal");
        table.addCell("8916434232");
        table.addCell("2500");
        table.addCell("");
        table.addCell("94,543.71Cr");


        table.setHeaderRows(5);

        table.addCell("12/01/2018");
        table.addCell("Interest 5412455");
        table.addCell("6466434232");
        table.addCell("");
        table.addCell("675");
        table.addCell("97,043.71Cr");

        table.setHeaderRows(6);

        table.addCell("03/01/2018");
        table.addCell("NEFT Transfer Yashwant Vichare");
        table.addCell("6466434232");
        table.addCell("1200");
        table.addCell("");
        table.addCell("96,368.71Cr");

        subCatPart.add(table);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        menu.findItem(R.id.download).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(EntriesActivity.this, DashboardActivity.class);
                startActivity(intent);
                break;
            case R.id.alert:
                Intent intent1 = new Intent(EntriesActivity.this, ROIAlertActivity.class);
                startActivity(intent1);
                return true;

            case R.id.action_logout:
                Intent intent2 = new Intent(EntriesActivity.this, LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.download:

                mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + strAccNumber + ".pdf");

                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    i.setAction(Intent.ACTION_VIEW);
                    i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                    Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + FILE);
                    try {

                        uri = FileProvider.getUriForFile(EntriesActivity.this, BuildConfig.APPLICATION_ID + ".provider",
                                targetFile());

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    i.setDataAndType(uri, "application/pdf");

                } else {
                    i.setDataAndType(Uri.fromFile(f), "application/pdf");
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                }

                PendingIntent pIntent;
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {

                    pIntent = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);

                    mBuilder = new NotificationCompat.Builder(this);
                    mBuilder.setContentTitle("Your PDF");
                    mBuilder.setSmallIcon(R.drawable.small_svc_logo);
                    mBuilder.setContentText("View PDF");
                    mBuilder.setContentIntent(pIntent);
                    mBuilder.setAutoCancel(true);
                    mNotifyManager.notify(1, mBuilder.build());
                    download();
                } else {
                    int notifyID = 1;
                    pIntent = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);
                    String CHANNEL_ID = "my_channel_01";// The id of the channel.
                    CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
// Create a notification and set the notification channel.
                    Notification notification = new Notification.Builder(EntriesActivity.this)
                            .setContentTitle("Your PDF")
                            .setContentText("View PDF")
                            .setSmallIcon(R.drawable.small_svc_logo)
                            .setContentIntent(pIntent)
                            .setAutoCancel(true)
                            .setChannelId(CHANNEL_ID)
                            .build();


                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.createNotificationChannel(mChannel);

// Issue the notification.
                    mNotificationManager.notify(notifyID , notification);
                    download();
                }


        }
        return super.onOptionsItemSelected(item);
    }

    private File targetFile() throws IOException {
        File targetFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + strAccNumber + ".pdf");
        return targetFile;
    }

}
