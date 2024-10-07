//package com.huntercodexs.simpleapidemo.service;
//
//import com.huntercodexs.jeasypdf.core.dto.*;
//import com.huntercodexs.jeasypdf.core.enumerator.*;
//import com.huntercodexs.jeasypdf.template.JEasyPdfTemplate;
//import com.huntercodexs.jeasypdf.template.setting.JEasyPdfTemplateSettings;
//import com.huntercodexs.jeasypdf.template.setting.slim.SlimDataContent;
//import com.huntercodexs.jeasypdf.template.setting.slim.SlimTemplateSettings;
//import org.krysalis.barcode4j.HumanReadablePlacement;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@Service
//public class JeasyPdfTestService {
//
//    private final static String path = "./src/main/resources/files/pdf";
//    private final static String pdfFilenameLetter = path+"/my-jeasypdf-test-template-slim-LETTER.pdf";
//    private final static String pdfFilenameLetterLayout = path+"/my-jeasypdf-test-template-slim-LETTER-LAYOUT.pdf";
//    private final static String pdfFilenameA4 = path+"/my-jeasypdf-test-template-slim-A4.pdf";
//    private final static String pdfFilenameA4Layout = path+"/my-jeasypdf-test-template-slim-A4-LAYOUT.pdf";
//    private final static String imgJava = "./src/main/resources/files/images/ads/java.png";
//    private final static String imgBackground = "./src/main/resources/files/images/background/jeasypdf-background-sample-7.jpg";
//    private final static String userPassword = "123456";
//    private final static String ownerPassword = "password";
//
//    private JEasyPdfDocument documentSettings(PageSizeToJEasyPdf pageType) {
//        JEasyPdfDocument settings = new JEasyPdfDocument();
//        settings.setStartPage(1);
//        settings.setEndPage(1);
//        settings.setNumberOfPages(1);
//        settings.setDate("1990-01-01 10:00:00");
//        settings.setTitle("Title Test");
//        settings.setAuthor("Huntercodexs");
//        settings.setSubject("Document Test");
//        settings.setFontName(FontNameToJEasyPdf.COURIER);
//        settings.setFontSize(FontSizeToJEasyPdf.X_SMALL);
//        settings.setKeywords("test, pdf, java, huntercodexs");
//        settings.setUserPassword(null); //TODO: CHECK BUG WHEN PASSWORD EXISTS
//        settings.setOwnerPassword(null); //TODO: CHECK BUG WHEN PASSWORD EXISTS
//        settings.setProtectionLevel(ProtectionLevelToJEasyPdf.HIGH);
//
//        if (pageType.equals(PageSizeToJEasyPdf.LETTER)) {
//            settings.setFilenamePath(pdfFilenameLetter);
//        } else if (pageType.equals(PageSizeToJEasyPdf.A4)) {
//            settings.setFilenamePath(pdfFilenameA4);
//        } else if (pageType.equals(PageSizeToJEasyPdf.LETTER_LAYOUT)) {
//            settings.setFilenamePath(pdfFilenameLetterLayout);
//        } else if (pageType.equals(PageSizeToJEasyPdf.A4_LAYOUT)) {
//            settings.setFilenamePath(pdfFilenameA4Layout);
//        }
//
//        return settings;
//    }
//
//    private JEasyPdfPage pageSettings(PageSizeToJEasyPdf pageType) {
//        JEasyPdfPage settings = new JEasyPdfPage();
//        settings.setWidth(842);
//        settings.setHeight(595);
//        settings.setOffsetX(0);
//        settings.setOffsetY(0);
//        settings.setLineHeight(18);
//        settings.setPageNumber(1);
//        settings.setMargin(0);
//        settings.setPadding(0);
//        settings.setPageSize(pageType);
//        settings.setFontName(FontNameToJEasyPdf.COURIER);
//        settings.setFontSize(FontSizeToJEasyPdf.SMALL);
//        settings.setFontColor(ColorsToJEasyPdf.BLACK);
//        settings.setPageColor(ColorsToJEasyPdf.WHITE);
//        settings.setTextContent(null);
//        settings.setImageFilepath(null);
//        settings.setByteContent(null);
//        return settings;
//    }
//
//    private JEasyPdfContainer containerSettings() {
//        JEasyPdfContainer settings = new JEasyPdfContainer();
//        settings.setWidth(570);
//        settings.setHeight(780);
//        settings.setOffsetX(20);
//        settings.setOffsetY(20);
//        settings.setBorder(true);
//        settings.setRoundedBorder(false);
//        settings.setBorderWidth(1);
//        settings.setBackColor(ColorsToJEasyPdf.WHITE);
//        settings.setBorderColor(ColorsToJEasyPdf.GRAY);
//        return settings;
//    }
//
//    private JEasyPdfTable tableSettings() {
//        JEasyPdfTable settings = new JEasyPdfTable();
//        settings.setWidth(540);
//        settings.setHeight(90);
//        settings.setOffsetX(0);
//        settings.setOffsetY(0);
//        settings.setBorder(true);
//        settings.setHeaderColor(ColorsToJEasyPdf.BLACK);
//        settings.setCelColor(ColorsToJEasyPdf.ICE);
//        settings.setBorderColor(ColorsToJEasyPdf.BLACK);
//        settings.setTableTemplate(TableDimensionsToJEasyPdf.TABLE_5X6);
//        return settings;
//    }
//
//    private JEasyPdfText textSettings() {
//        JEasyPdfText settings = new JEasyPdfText();
//        settings.setWidth(0);
//        settings.setHeight(780);
//        settings.setOffsetX(0);
//        settings.setOffsetY(0);
//        settings.setLineHeight(18);
//        settings.setLetterSpace(1);
//        settings.setBold(false);
//        settings.setItalic(false);
//        settings.setUnderline(false);
//        settings.setFontName(FontNameToJEasyPdf.HELVETICA);
//        settings.setFontSize(FontSizeToJEasyPdf.NORMAL);
//        return settings;
//    }
//
//    private JEasyPdfImage imageSettings() {
//        JEasyPdfImage settings = new JEasyPdfImage();
//        settings.setWidth(500);
//        settings.setHeight(780);
//        settings.setOffsetX(25);
//        settings.setOffsetY(100);
//        settings.setMaxWidth(500);
//        settings.setMaxHeight(780);
//        settings.setBorder(false);
//        settings.setResize(false);
//        settings.setFilenamePath(imgJava);
//        settings.setImageType(ImageTypeToJEasyPdf.JPEG);
//        settings.setImageQuality(ImageQualityToJEasyPdf.GOOD);
//        return settings;
//    }
//
//    private JEasyPdfBarcode barcodeSettings() {
//        JEasyPdfBarcode settings = new JEasyPdfBarcode();
//        settings.setDpi(400);
//        settings.setWidth(500);
//        settings.setHeight(50);
//        settings.setFontSize(2);
//        settings.setLineHeight(20);
//        settings.setOffsetX(55);
//        settings.setOffsetY(35); //650,500,345,190,35
//        settings.setFixQuiteZone(0);
//        settings.setDoQuiteZone(false);
//        settings.setData("123456789123456789123456789123456789");
//        settings.setFontName(FontNameToJEasyPdf.COURIER);
//        settings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
//        settings.setCodeType4Scanner(CodeType4ScannerToJEasyPdf.CODE128);
//        return settings;
//    }
//
//    private JEasyPdfQrCode qrCodeSettings() {
//        JEasyPdfQrCode settings = new JEasyPdfQrCode();
//        settings.setDpi(400);
//        settings.setMargin(0);
//        settings.setMatrix(100);
//        settings.setSize(100);
//        settings.setOnColor(0xFF000001);
//        settings.setOffColor(0xFFFFFFFF);
//        settings.setOffsetX(470); //40,260,470
//        settings.setOffsetY(348); //655,502,348,193,38
//        settings.setData("https://huntercodexs.com");
//        settings.setCodeType4Scanner(CodeType4ScannerToJEasyPdf.QRCODE);
//        return settings;
//    }
//
//    private SlimTemplateSettings slimSettingsLetterLayout() {
//        SlimTemplateSettings settings = new SlimTemplateSettings();
//
//        //General
//        settings.setTemplateTitleEnabled(true);
//        settings.setBoxWidth(new int[]{570,570,570,570,570});
//        settings.setBoxAdjustOffsetX(new int[]{0,0,0,0,0});
//        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
//        settings.setBoxBorderEnabled(new boolean[]{true, true, true, true, true});
//        settings.setBoxBackColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.WHITE
//        });
//
//        /*Title*/
//        boolean titleOn = false;
//        settings.setLeftTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
//        settings.setCenterTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
//        settings.setRightTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
//
//        /*Column*/
//        boolean columnLeftOn = false;
//        boolean columnCenterOn = false;
//        boolean columnRightOn = false;
//        settings.setColumnBoxLeftEnable(new boolean[]{columnLeftOn,columnLeftOn,columnLeftOn,columnLeftOn,columnLeftOn});
//        settings.setColumnBoxCenterEnable(new boolean[]{columnCenterOn,columnCenterOn,columnCenterOn,columnCenterOn,columnCenterOn});
//        settings.setColumnBoxRightEnable(new boolean[]{columnRightOn,columnRightOn,columnRightOn,columnRightOn,columnRightOn});
//
//        /*Table*/
//        boolean tableOn = false;
//        settings.setTableEnable(new boolean[]{tableOn,tableOn,tableOn,tableOn,tableOn});
//
//        /*Image*/
//        boolean imageLeftOn = false;
//        boolean imageCenterOn = false;
//        boolean imageRightOn = false;
//        settings.setLeftImageEnable(new boolean[]{imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn});
//        settings.setCenterImageEnable(new boolean[]{imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn});
//        settings.setRightImageEnable(new boolean[]{imageRightOn,imageRightOn,imageRightOn,imageRightOn,imageRightOn});
//
//        /*Signature Box*/
//        boolean signatureOn = false;
//        settings.setLeftSignatureBoxEnable(signatureOn);
//        settings.setCenterSignatureBoxEnable(signatureOn);
//        settings.setRightSignatureBoxEnable(signatureOn);
//
//        /*Signature Tape*/
//        settings.setSignatureTapeEnable(false);
//
//        /*Text Content*/
//        boolean textOn = false;
//        settings.setTextEnable(new boolean[]{textOn,textOn,textOn,textOn,textOn});
//
//        /*Barcode Content*/
//        boolean barcodeOn = false;
//        settings.setBarcodeEnabled(new boolean[]{barcodeOn,barcodeOn,barcodeOn,barcodeOn,barcodeOn});
//
//        /*QRCode Content*/
//        boolean qrCodeLeftOn = false;
//        boolean qrCodeCenterOn = false;
//        boolean qrCodeRightOn = false;
//        settings.setQrCodeLeftEnable(new boolean[]{qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn});
//        settings.setQrCodeCenterEnable(new boolean[]{qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn});
//        settings.setQrCodeRightEnable(new boolean[]{qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn});
//
//        return settings;
//    }
//
//    private SlimTemplateSettings slimSettingsA4Layout() {
//        SlimTemplateSettings settings = new SlimTemplateSettings();
//
//        //General
//        settings.setTemplateTitleEnabled(true);
//        settings.setBoxWidth(new int[]{570,570,570,570,570});
//        settings.setBoxAdjustOffsetX(new int[]{0,0,0,0,0});
//        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
//        settings.setBoxBorderEnabled(new boolean[]{true, true, true, true, true});
//        settings.setBoxBackColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.WHITE
//        });
//
//        /*Title*/
//        boolean titleOn = false;
//        settings.setLeftTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
//        settings.setCenterTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
//        settings.setRightTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
//
//        /*Column*/
//        boolean columnLeftOn = false;
//        boolean columnCenterOn = false;
//        boolean columnRightOn = false;
//        settings.setColumnBoxLeftEnable(new boolean[]{columnLeftOn,columnLeftOn,columnLeftOn,columnLeftOn,columnLeftOn});
//        settings.setColumnBoxCenterEnable(new boolean[]{columnCenterOn,columnCenterOn,columnCenterOn,columnCenterOn,columnCenterOn});
//        settings.setColumnBoxRightEnable(new boolean[]{columnRightOn,columnRightOn,columnRightOn,columnRightOn,columnRightOn});
//
//        /*Table*/
//        boolean tableOn = false;
//        settings.setTableEnable(new boolean[]{tableOn,tableOn,tableOn,tableOn,tableOn});
//
//        /*Image*/
//        boolean imageLeftOn = false;
//        boolean imageCenterOn = false;
//        boolean imageRightOn = false;
//        settings.setLeftImageEnable(new boolean[]{imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn});
//        settings.setCenterImageEnable(new boolean[]{imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn});
//        settings.setRightImageEnable(new boolean[]{imageRightOn,imageRightOn,imageRightOn,imageRightOn,imageRightOn});
//
//        /*Signature Box*/
//        boolean signatureOn = false;
//        settings.setLeftSignatureBoxEnable(signatureOn);
//        settings.setCenterSignatureBoxEnable(signatureOn);
//        settings.setRightSignatureBoxEnable(signatureOn);
//
//        /*Signature Tape*/
//        settings.setSignatureTapeEnable(false);
//
//        /*Text Content*/
//        boolean textOn = false;
//        settings.setTextEnable(new boolean[]{textOn,textOn,textOn,textOn,textOn});
//
//        /*Barcode Content*/
//        boolean barcodeOn = false;
//        settings.setBarcodeEnabled(new boolean[]{barcodeOn,barcodeOn,barcodeOn,barcodeOn,barcodeOn});
//
//        /*QRCode Content*/
//        boolean qrCodeLeftOn = false;
//        boolean qrCodeCenterOn = false;
//        boolean qrCodeRightOn = false;
//        settings.setQrCodeLeftEnable(new boolean[]{qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn});
//        settings.setQrCodeCenterEnable(new boolean[]{qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn});
//        settings.setQrCodeRightEnable(new boolean[]{qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn});
//
//        return settings;
//    }
//
//    private SlimTemplateSettings slimSettingsLetter() {
//        SlimTemplateSettings settings = new SlimTemplateSettings();
//
//        //General
//        settings.setTemplateTitleEnabled(true);
//        settings.setBoxWidth(new int[]{570,620,570,570,570});
//        settings.setBoxAdjustOffsetX(new int[]{0,-20,0,0,0});
//        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
//        settings.setBoxBorderEnabled(new boolean[]{false, false, false, true, false});
//        settings.setBoxBackColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.NONE,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.WHITE
//        });
//
//        /*Title*/
//        boolean titleOn = false;
//        settings.setLeftTitleAdjustmentX(0);
//        settings.setLeftTitleAdjustmentY(-90);
//        settings.setCenterTitleAdjustmentX(0);
//        settings.setCenterTitleAdjustmentY(0);
//        settings.setRightTitleAdjustmentX(0);
//        settings.setRightTitleAdjustmentY(0);
//        settings.setLeftTitleEnable(new boolean[]{true,titleOn,titleOn,titleOn,titleOn});
//        settings.setCenterTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
//        settings.setRightTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
//        settings.setLeftTitleColor(ColorsToJEasyPdf.GRAY);
//        settings.setCenterTitleColor(ColorsToJEasyPdf.GRAY);
//        settings.setRightTitleColor(ColorsToJEasyPdf.RED_DARK);
//        settings.setLeftTitleSize(FontSizeToJEasyPdf.LARGE);
//        settings.setCenterTitleSize(FontSizeToJEasyPdf.MEDIUM);
//        settings.setRightTitleSize(FontSizeToJEasyPdf.MEDIUM);
//        settings.setLeftTitleFont(FontNameToJEasyPdf.HELVETICA_B);
//        settings.setCenterTitleFont(FontNameToJEasyPdf.COURIER_B);
//        settings.setRightTitleFont(FontNameToJEasyPdf.HELVETICA_B);
//
//        /*Column*/
//        boolean columnLeftOn = false;
//        boolean columnCenterOn = false;
//        boolean columnRightOn = false;
//        settings.setColumnBoxWidth(170);
//        settings.setColumnBoxHeight(90);
//        settings.setColumnBoxChars(25);
//        settings.setColumnBoxOffsetX(new int[]{35,220,405});
//        settings.setColumnBoxOffsetY(new int[]{655,500,345,190,35});
//        settings.setColumnBoxLeftPadding(new int[]{5,5,5,5,5});
//        settings.setColumnBoxCenterPadding(new int[]{5,5,5,5,5});
//        settings.setColumnBoxRightPadding(new int[]{5,5,5,5,5});
//        settings.setColumnBoxLeftBorderWidth(new int[] {1,1,1,1,1});
//        settings.setColumnBoxCenterBorderWidth(new int[] {1,1,1,1,1});
//        settings.setColumnBoxRightBorderWidth(new int[] {1,1,1,1,1});
//        settings.setColumnBoxLeftLineHeight(new int[] {14,14,14,14,14});
//        settings.setColumnBoxCenterLineHeight(new int[] {14,14,14,14,14});
//        settings.setColumnBoxRightLineHeight(new int[] {14,14,14,14,14});
//        settings.setColumnBoxLeftAdjustmentX(new int[]{10,10,10,10,10});
//        settings.setColumnBoxLeftAdjustmentY(new int[]{0,0,0,0,0});
//        settings.setColumnBoxCenterAdjustmentX(new int[]{0,0,0,0,0});
//        settings.setColumnBoxCenterAdjustmentY(new int[]{0,0,0,0,0});
//        settings.setColumnBoxRightAdjustmentX(new int[]{0,0,0,0,0});
//        settings.setColumnBoxRightAdjustmentY(new int[]{0,0,0,0,0});
//        settings.setColumnBoxLeftEnable(new boolean[]{columnLeftOn,true,columnLeftOn,columnLeftOn,columnLeftOn});
//        settings.setColumnBoxCenterEnable(new boolean[]{columnCenterOn,true,columnCenterOn,columnCenterOn,columnCenterOn});
//        settings.setColumnBoxRightEnable(new boolean[]{columnRightOn,true,columnRightOn,columnRightOn,columnRightOn});
//        settings.setColumnBoxLeftBorderEnable(new boolean[]{true,true,true,true,true});
//        settings.setColumnBoxCenterBorderEnable(new boolean[]{true,true,true,true,true});
//        settings.setColumnBoxRightBorderEnable(new boolean[]{true,true,true,true,true});
//        settings.setColumnBoxLeftBackColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.RED_LIGHT,
//                ColorsToJEasyPdf.RED,
//                ColorsToJEasyPdf.GREEN,
//                ColorsToJEasyPdf.BLUE
//        });
//        settings.setColumnBoxCenterBackColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.YELLOW,
//                ColorsToJEasyPdf.RED_BRIGHT,
//                ColorsToJEasyPdf.GRAY,
//                ColorsToJEasyPdf.GOLD,
//                ColorsToJEasyPdf.PURPLE
//        });
//        settings.setColumnBoxRightBackColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.MAGENTA,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.GREEN_SEA,
//                ColorsToJEasyPdf.BLUE_SAD,
//                ColorsToJEasyPdf.GOLD_DARK
//        });
//        settings.setColumnBoxLeftBorderColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.YELLOW,
//                ColorsToJEasyPdf.BLUE,
//                ColorsToJEasyPdf.ORANGE
//        });
//        settings.setColumnBoxCenterBorderColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.RED,
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.PINK,
//                ColorsToJEasyPdf.ORANGE
//        });
//        settings.setColumnBoxRightBorderColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.GOLD,
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.GRAY,
//                ColorsToJEasyPdf.BLACK
//        });
//        settings.setColumnBoxLeftTextColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.YELLOW,
//                ColorsToJEasyPdf.BLUE,
//                ColorsToJEasyPdf.WHITE
//        });
//        settings.setColumnBoxCenterTextColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.RED,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.PINK,
//                ColorsToJEasyPdf.CYAN
//        });
//        settings.setColumnBoxRightTextColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.GOLD,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.BLACK
//        });
//        settings.setColumnBoxLeftFontSize(new FontSizeToJEasyPdf[]{
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL
//        });
//        settings.setColumnBoxCenterFontSize(new FontSizeToJEasyPdf[]{
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL
//        });
//        settings.setColumnBoxRightFontSize(new FontSizeToJEasyPdf[]{
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL
//        });
//        settings.setColumnBoxLeftFontName(new FontNameToJEasyPdf[]{
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA
//        });
//        settings.setColumnBoxCenterFontName(new FontNameToJEasyPdf[]{
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA
//        });
//        settings.setColumnBoxRightFontName(new FontNameToJEasyPdf[]{
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA
//        });
//
//        /*Table*/
//        boolean tableOn = false;
//        settings.setTableWidth(540);
//        settings.setTableHeight(90);
//        settings.setTableOffsetX(35);
//        settings.setTableColumnWidth(90);
//        settings.setTableColumnHeight(18);
//        settings.setTableHeaderHeight(30);
//        settings.setTableHeaderAdjustOffsetX(20);
//        settings.setTableHeaderAdjustOffsetY(30);
//        settings.setTableBodyAdjustOffsetX(3);
//        settings.setTableBodyAdjustOffsetY(5);
//        settings.setTableContainerOffsetY(new int[]{656, 500, 346, 190, 35});
//        settings.setTableHeaderOffsetY(new int[]{728, 572, 418, 262, 107});
//        settings.setTableColumnOffsetX(new int[] {35,125,215,305,395,485});
//        settings.setTableDataOffsetY(new int[]{710, 554, 400, 244, 89});
//        settings.setTableHeaderColor(ColorsToJEasyPdf.BLUE_SEA);
//        settings.setTableBodyColor(ColorsToJEasyPdf.ICE);
//        settings.setTableBorderColor(ColorsToJEasyPdf.WHITE);
//        settings.setTableHeaderFontColor(ColorsToJEasyPdf.WHITE);
//        settings.setTableBodyFontColor(ColorsToJEasyPdf.GRAY);
//        settings.setTableHeaderFontSize(FontSizeToJEasyPdf.NORMAL);
//        settings.setTableBodyFontSize(FontSizeToJEasyPdf.SMALL);
//        settings.setTableHeaderFontName(FontNameToJEasyPdf.HELVETICA_B);
//        settings.setTableBodyFontName(FontNameToJEasyPdf.HELVETICA);
//        settings.setTableSize(TableDimensionsToJEasyPdf.TABLE_5X6);
//        settings.setTableEnable(new boolean[]{tableOn,tableOn,tableOn,true,tableOn});
//
//        /*Image*/
//        boolean imageLeftOn = false;
//        boolean imageCenterOn = false;
//        boolean imageRightOn = false;
//        settings.setImageWidth(180);
//        settings.setImageHeight(70);
//        settings.setImageAdjustOffsetX(0);
//        settings.setImageAdjustOffsetY(20);
//        settings.setImageOffsetX(new int[]{35,215,395});
//        settings.setImageOffsetY(new int[]{670,515,360,205,55});
//        settings.setLeftImageEnable(new boolean[]{true,imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn});
//        settings.setCenterImageEnable(new boolean[]{imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn});
//        settings.setRightImageEnable(new boolean[]{imageRightOn,imageRightOn,imageRightOn,imageRightOn,imageRightOn});
//
//        /*Signature Box*/
//        boolean signatureOn = false;
//        settings.setSignatureBoxWidth(200);
//        settings.setSignatureBoxHeight(100);
//        settings.setSignatureBoxAdjustOffsetX(15);
//        settings.setSignatureBoxOffsetX(new int[]{55,210,355});
//        settings.setSignatureBoxOffsetY(new int[]{35,122,100});
//        settings.setSignatureBoxDigitalTitleOffsetX(new int[]{105,260,405});
//        settings.setSignatureBoxContentOffsetX(new int[]{70,230,370});
//        settings.setSignatureBoxBorderEnable(true);
//        settings.setLeftSignatureBoxEnable(signatureOn);
//        settings.setCenterSignatureBoxEnable(signatureOn);
//        settings.setRightSignatureBoxEnable(true);
//        settings.setSignatureFontSize(FontSizeToJEasyPdf.NORMAL);
//        settings.setSignatureFontName(FontNameToJEasyPdf.HELVETICA_B);
//        settings.setSignatureBoxColor(ColorsToJEasyPdf.GRAY);
//
//        /*Signature Tape*/
//        settings.setSignatureTapeWidth(500);
//        settings.setSignatureTapeHeight(30);
//        settings.setSignatureTapeOffsetX(55);
//        settings.setSignatureTapeOffsetY(35);
//        settings.setSignatureTapeTitleOffsetX(260);
//        settings.setSignatureTapeTitleOffsetY(57);
//        settings.setSignatureTapeValueOffsetX(130);
//        settings.setSignatureTapeValueOffsetY(40);
//        settings.setSignatureTapeAdjustOffsetX(10);
//        settings.setSignatureTapeEnable(false);
//        settings.setSignatureTapeFontSize(FontSizeToJEasyPdf.SMALL);
//        settings.setSignatureTapeFontName(FontNameToJEasyPdf.HELVETICA_B);
//        settings.setSignatureTapeColor(ColorsToJEasyPdf.GRAY);
//
//        /*Text Content*/
//        boolean textOn = false;
//        settings.setLineHeight(18);
//        settings.setTextOffsetX(35);
//        settings.setTextChars(98);
//        settings.setTextOffsetY(new int[]{732,577,421,266,111});
//        settings.setTextEnable(new boolean[]{textOn,textOn,true,textOn,textOn});
//        settings.setTextColor(ColorsToJEasyPdf.WHITE);
//        settings.setTextSize(FontSizeToJEasyPdf.NORMAL);
//        settings.setTextFont(FontNameToJEasyPdf.HELVETICA);
//
//        /*Barcode Content*/
//        boolean barcodeOn = false;
//        settings.setBarcodeDpi(400);
//        settings.setBarcodeWidth(300);
//        settings.setBarcodeHeight(30);
//        settings.setBarcodeAdjustOffsetX(-10);
//        settings.setBarcodeAdjustOffsetY(0);
//        settings.setBarcodeOffsetY(new int[]{655,500,345,190,35});
//        settings.setBarcodeInfoOffsetY(new int[]{750, 595, 440, 285, 130});
//        settings.setBarcodeValueOffsetY(new int[]{690, 535, 380, 225, 70});
//        settings.setBarcodeAmountOffsetY(new int[]{745, 590, 435, 280, 125});
//        settings.setBarcodeShowText(false);
//        settings.setBarcodeEnabled(new boolean[]{barcodeOn,barcodeOn,barcodeOn,barcodeOn,true});
//
//        /*QRCode Content*/
//        boolean qrCodeLeftOn = false;
//        boolean qrCodeCenterOn = false;
//        boolean qrCodeRightOn = false;
//        settings.setQrCodeDpi(400);
//        settings.setQrCodeWidth(500);
//        settings.setQrCodeHeight(50);
//        settings.setQrCodeAdjustOffsetX(0);
//        settings.setQrCodeAdjustOffsetY(0);
//        settings.setQrCodeOffsetX(new int[]{40,260,470});
//        settings.setQrCodeOffsetY(new int[]{655,502,348,193,38});
//        settings.setQrCodeInfoOffsetX(new int[]{145, 365, 365});
//        settings.setQrCodeInfoOffsetY(new int[]{745, 595, 440, 285, 130});
//        settings.setQrCodeLeftEnable(new boolean[]{qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn});
//        settings.setQrCodeCenterEnable(new boolean[]{qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn});
//        settings.setQrCodeRightEnable(new boolean[]{true,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn});
//
//        return settings;
//    }
//
//    private SlimTemplateSettings slimSettingsA4() {
//        SlimTemplateSettings settings = new SlimTemplateSettings();
//
//        //General
//        settings.setTemplateTitleEnabled(false);
//        settings.setBoxWidth(new int[]{570,700,570,570,570});
//        settings.setBoxAdjustOffsetX(new int[]{0,-20,0,0,0});
//        settings.setBoxAdjustOffsetY(new int[]{0,0,0,0,0});
//        settings.setBoxBorderEnabled(new boolean[]{false, false, false, true, false});
//        settings.setBoxBackColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.NONE,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.WHITE
//        });
//
//        /*Title*/
//        boolean titleOn = false;
//        settings.setLeftTitleAdjustmentX(0);
//        settings.setLeftTitleAdjustmentY(-70);
//        settings.setCenterTitleAdjustmentX(0);
//        settings.setCenterTitleAdjustmentY(0);
//        settings.setRightTitleAdjustmentX(0);
//        settings.setRightTitleAdjustmentY(0);
//        settings.setLeftTitleEnable(new boolean[]{true,titleOn,titleOn,titleOn,titleOn});
//        settings.setCenterTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
//        settings.setRightTitleEnable(new boolean[]{titleOn,titleOn,titleOn,titleOn,titleOn});
//        settings.setLeftTitleColor(ColorsToJEasyPdf.GRAY);
//        settings.setCenterTitleColor(ColorsToJEasyPdf.GRAY);
//        settings.setRightTitleColor(ColorsToJEasyPdf.RED_DARK);
//        settings.setLeftTitleSize(FontSizeToJEasyPdf.LARGE);
//        settings.setCenterTitleSize(FontSizeToJEasyPdf.MEDIUM);
//        settings.setRightTitleSize(FontSizeToJEasyPdf.MEDIUM);
//        settings.setLeftTitleFont(FontNameToJEasyPdf.HELVETICA_B);
//        settings.setCenterTitleFont(FontNameToJEasyPdf.COURIER_B);
//        settings.setRightTitleFont(FontNameToJEasyPdf.HELVETICA_B);
//
//        /*Column*/
//        boolean columnLeftOn = false;
//        boolean columnCenterOn = false;
//        boolean columnRightOn = false;
//        settings.setColumnBoxWidth(165);
//        settings.setColumnBoxHeight(90);
//        settings.setColumnBoxChars(25);
//        settings.setColumnBoxOffsetX(new int[]{35,215,395});
//        settings.setColumnBoxOffsetY(new int[]{655,524,345,190,35});
//        settings.setColumnBoxLeftPadding(new int[]{5,5,5,5,5});
//        settings.setColumnBoxCenterPadding(new int[]{5,5,5,5,5});
//        settings.setColumnBoxRightPadding(new int[]{5,5,5,5,5});
//        settings.setColumnBoxLeftBorderWidth(new int[] {1,1,1,1,1});
//        settings.setColumnBoxCenterBorderWidth(new int[] {1,1,1,1,1});
//        settings.setColumnBoxRightBorderWidth(new int[] {1,1,1,1,1});
//        settings.setColumnBoxLeftLineHeight(new int[] {14,14,14,14,14});
//        settings.setColumnBoxCenterLineHeight(new int[] {14,14,14,14,14});
//        settings.setColumnBoxRightLineHeight(new int[] {14,14,14,14,14});
//        settings.setColumnBoxLeftAdjustmentX(new int[]{10,10,10,10,10});
//        settings.setColumnBoxLeftAdjustmentY(new int[]{0,0,0,0,0});
//        settings.setColumnBoxCenterAdjustmentX(new int[]{0,0,0,0,0});
//        settings.setColumnBoxCenterAdjustmentY(new int[]{0,0,0,0,0});
//        settings.setColumnBoxRightAdjustmentX(new int[]{0,0,0,0,0});
//        settings.setColumnBoxRightAdjustmentY(new int[]{0,0,0,0,0});
//        settings.setColumnBoxLeftEnable(new boolean[]{columnLeftOn,true,columnLeftOn,columnLeftOn,columnLeftOn});
//        settings.setColumnBoxCenterEnable(new boolean[]{columnCenterOn,true,columnCenterOn,columnCenterOn,columnCenterOn});
//        settings.setColumnBoxRightEnable(new boolean[]{columnRightOn,true,columnRightOn,columnRightOn,columnRightOn});
//        settings.setColumnBoxLeftBorderEnable(new boolean[]{true,true,true,true,true});
//        settings.setColumnBoxCenterBorderEnable(new boolean[]{true,true,true,true,true});
//        settings.setColumnBoxRightBorderEnable(new boolean[]{true,true,true,true,true});
//        settings.setColumnBoxLeftBackColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.RED_LIGHT,
//                ColorsToJEasyPdf.RED,
//                ColorsToJEasyPdf.GREEN,
//                ColorsToJEasyPdf.BLUE
//        });
//        settings.setColumnBoxCenterBackColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.YELLOW,
//                ColorsToJEasyPdf.RED_BRIGHT,
//                ColorsToJEasyPdf.GRAY,
//                ColorsToJEasyPdf.GOLD,
//                ColorsToJEasyPdf.PURPLE
//        });
//        settings.setColumnBoxRightBackColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.MAGENTA,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.GREEN_SEA,
//                ColorsToJEasyPdf.BLUE_SAD,
//                ColorsToJEasyPdf.GOLD_DARK
//        });
//        settings.setColumnBoxLeftBorderColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.YELLOW,
//                ColorsToJEasyPdf.BLUE,
//                ColorsToJEasyPdf.ORANGE
//        });
//        settings.setColumnBoxCenterBorderColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.RED,
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.PINK,
//                ColorsToJEasyPdf.ORANGE
//        });
//        settings.setColumnBoxRightBorderColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.GOLD,
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.GRAY,
//                ColorsToJEasyPdf.BLACK
//        });
//        settings.setColumnBoxLeftTextColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.BLACK,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.YELLOW,
//                ColorsToJEasyPdf.BLUE,
//                ColorsToJEasyPdf.WHITE
//        });
//        settings.setColumnBoxCenterTextColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.RED,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.PINK,
//                ColorsToJEasyPdf.CYAN
//        });
//        settings.setColumnBoxRightTextColor(new ColorsToJEasyPdf[]{
//                ColorsToJEasyPdf.GOLD,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.RED_DARK,
//                ColorsToJEasyPdf.WHITE,
//                ColorsToJEasyPdf.BLACK
//        });
//        settings.setColumnBoxLeftFontSize(new FontSizeToJEasyPdf[]{
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL
//        });
//        settings.setColumnBoxCenterFontSize(new FontSizeToJEasyPdf[]{
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL
//        });
//        settings.setColumnBoxRightFontSize(new FontSizeToJEasyPdf[]{
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL,
//                FontSizeToJEasyPdf.NORMAL
//        });
//        settings.setColumnBoxLeftFontName(new FontNameToJEasyPdf[]{
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA
//        });
//        settings.setColumnBoxCenterFontName(new FontNameToJEasyPdf[]{
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA
//        });
//        settings.setColumnBoxRightFontName(new FontNameToJEasyPdf[]{
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA,
//                FontNameToJEasyPdf.HELVETICA
//        });
//
//        /*Table*/
//        boolean tableOn = false;
//        settings.setTableWidth(528);
//        settings.setTableHeight(90);
//        settings.setTableOffsetX(34);
//        settings.setTableColumnWidth(90);
//        settings.setTableColumnHeight(18);
//        settings.setTableHeaderHeight(30);
//        settings.setTableHeaderAdjustOffsetX(18);
//        settings.setTableHeaderAdjustOffsetY(30);
//        settings.setTableBodyAdjustOffsetX(3);
//        settings.setTableBodyAdjustOffsetY(5);
//        settings.setTableContainerOffsetY(new int[]{656, 500, 346, 214, 35});
//        settings.setTableHeaderOffsetY(new int[]{728, 572, 418, 262, 107});
//        settings.setTableColumnOffsetX(new int[] {35,125,215,305,395,485});
//        settings.setTableDataOffsetY(new int[]{710, 554, 400, 244, 89});
//        settings.setTableHeaderColor(ColorsToJEasyPdf.BLUE_SEA);
//        settings.setTableBodyColor(ColorsToJEasyPdf.ICE);
//        settings.setTableBorderColor(ColorsToJEasyPdf.WHITE);
//        settings.setTableHeaderFontColor(ColorsToJEasyPdf.WHITE);
//        settings.setTableBodyFontColor(ColorsToJEasyPdf.GRAY);
//        settings.setTableHeaderFontSize(FontSizeToJEasyPdf.NORMAL);
//        settings.setTableBodyFontSize(FontSizeToJEasyPdf.SMALL);
//        settings.setTableHeaderFontName(FontNameToJEasyPdf.HELVETICA_B);
//        settings.setTableBodyFontName(FontNameToJEasyPdf.HELVETICA);
//        settings.setTableSize(TableDimensionsToJEasyPdf.TABLE_5X6);
//        settings.setTableEnable(new boolean[]{tableOn,tableOn,tableOn,true,tableOn});
//
//        /*Image*/
//        boolean imageLeftOn = false;
//        boolean imageCenterOn = false;
//        boolean imageRightOn = false;
//        settings.setImageWidth(180);
//        settings.setImageHeight(70);
//        settings.setImageAdjustOffsetX(0);
//        settings.setImageAdjustOffsetY(44);
//        settings.setImageOffsetX(new int[]{35,215,395});
//        settings.setImageOffsetY(new int[]{670,515,360,205,55});
//        settings.setLeftImageEnable(new boolean[]{true,imageLeftOn,imageLeftOn,imageLeftOn,imageLeftOn});
//        settings.setCenterImageEnable(new boolean[]{imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn,imageCenterOn});
//        settings.setRightImageEnable(new boolean[]{imageRightOn,imageRightOn,imageRightOn,imageRightOn,imageRightOn});
//
//        /*Signature Box*/
//        boolean signatureOn = false;
//        settings.setSignatureBoxWidth(200);
//        settings.setSignatureBoxHeight(100);
//        settings.setSignatureBoxAdjustOffsetX(10);
//        settings.setSignatureBoxOffsetX(new int[]{55,210,355});
//        settings.setSignatureBoxOffsetY(new int[]{59,146,124});
//        settings.setSignatureBoxDigitalTitleOffsetX(new int[]{105,260,405});
//        settings.setSignatureBoxContentOffsetX(new int[]{70,230,370});
//        settings.setSignatureBoxBorderEnable(true);
//        settings.setLeftSignatureBoxEnable(signatureOn);
//        settings.setCenterSignatureBoxEnable(signatureOn);
//        settings.setRightSignatureBoxEnable(true);
//        settings.setSignatureFontSize(FontSizeToJEasyPdf.NORMAL);
//        settings.setSignatureFontName(FontNameToJEasyPdf.HELVETICA_B);
//        settings.setSignatureBoxColor(ColorsToJEasyPdf.GRAY);
//
//        /*Signature Tape*/
//        settings.setSignatureTapeWidth(500);
//        settings.setSignatureTapeHeight(30);
//        settings.setSignatureTapeOffsetX(55);
//        settings.setSignatureTapeOffsetY(35);
//        settings.setSignatureTapeTitleOffsetX(260);
//        settings.setSignatureTapeTitleOffsetY(57);
//        settings.setSignatureTapeValueOffsetX(130);
//        settings.setSignatureTapeValueOffsetY(40);
//        settings.setSignatureTapeAdjustOffsetX(10);
//        settings.setSignatureTapeEnable(false);
//        settings.setSignatureTapeFontSize(FontSizeToJEasyPdf.SMALL);
//        settings.setSignatureTapeFontName(FontNameToJEasyPdf.HELVETICA_B);
//        settings.setSignatureTapeColor(ColorsToJEasyPdf.GRAY);
//
//        /*Text Content*/
//        boolean textOn = false;
//        settings.setLineHeight(18);
//        settings.setTextOffsetX(35);
//        settings.setTextChars(98);
//        settings.setTextOffsetY(new int[]{732,577,445,266,111});
//        settings.setTextEnable(new boolean[]{textOn,textOn,true,textOn,textOn});
//        settings.setTextColor(ColorsToJEasyPdf.WHITE);
//        settings.setTextSize(FontSizeToJEasyPdf.NORMAL);
//        settings.setTextFont(FontNameToJEasyPdf.HELVETICA);
//
//        /*Barcode Content*/
//        boolean barcodeOn = false;
//        settings.setBarcodeDpi(400);
//        settings.setBarcodeWidth(300);
//        settings.setBarcodeHeight(30);
//        settings.setBarcodeAdjustOffsetX(-15);
//        settings.setBarcodeAdjustOffsetY(24);
//        settings.setBarcodeOffsetY(new int[]{655,500,345,190,35});
//        settings.setBarcodeInfoOffsetY(new int[]{750, 595, 440, 285, 130});
//        settings.setBarcodeValueOffsetY(new int[]{690, 535, 380, 225, 70});
//        settings.setBarcodeAmountOffsetY(new int[]{745, 590, 435, 280, 125});
//        settings.setBarcodeShowText(false);
//        settings.setBarcodeEnabled(new boolean[]{barcodeOn,barcodeOn,barcodeOn,barcodeOn,true});
//
//        /*QRCode Content*/
//        boolean qrCodeLeftOn = false;
//        boolean qrCodeCenterOn = false;
//        boolean qrCodeRightOn = false;
//        settings.setQrCodeDpi(400);
//        settings.setQrCodeWidth(500);
//        settings.setQrCodeHeight(50);
//        settings.setQrCodeAdjustOffsetX(-10);
//        settings.setQrCodeAdjustOffsetY(24);
//        settings.setQrCodeOffsetX(new int[]{40,260,470});
//        settings.setQrCodeOffsetY(new int[]{655,502,348,193,38});
//        settings.setQrCodeInfoOffsetX(new int[]{145, 365, 365});
//        settings.setQrCodeInfoOffsetY(new int[]{745, 595, 440, 285, 130});
//        settings.setQrCodeLeftEnable(new boolean[]{qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn,qrCodeLeftOn});
//        settings.setQrCodeCenterEnable(new boolean[]{qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn,qrCodeCenterOn});
//        settings.setQrCodeRightEnable(new boolean[]{true,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn,qrCodeRightOn});
//
//        return settings;
//    }
//
//    private SlimDataContent slimData() {
//        SlimDataContent settings = new SlimDataContent();
//
//        /*Title*/
//        settings.setLeftTitleContent("My Sample Slim Template");
//        settings.setCenterTitleContent("Title of Section 2");
//        settings.setRightTitleContent("Title of Section 3");
//
//        /*Column*/
//        HashMap<Integer, String> columnContentMap = new HashMap<>();
//        String lorenText =
//                "There are many the Lorena\n" +
//                        "of passages of All lorem off\n" +
//                        "but the majority All the into\n" +
//                        "in some form, by All the off\n" +
//                        "randomised words a Lorem";
//        for (int i = 0; i < 15; i++) {
//            columnContentMap.put(i, (i+1)+".\n"+lorenText);
//        }
//        settings.setColumnContent(columnContentMap);
//
//        /*Table*/
//        List<String> tableHeaderContent = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            tableHeaderContent.add("Header " + i);
//        }
//        HashMap<Integer, List<String>> tableHeaderMap = new HashMap<>();
//        for (int j = 0; j < 5; j++) {
//            tableHeaderMap.put(j, tableHeaderContent);
//        }
//
//        List<String> tableBodyContent = new ArrayList<>();
//        for (int i = 0; i < 24; i++) {
//            tableBodyContent.add("Data " + i);
//        }
//        HashMap<Integer, List<String>> tableBodyMap = new HashMap<>();
//        for (int j = 0; j < 5; j++) {
//            tableBodyMap.put(j, tableBodyContent);
//        }
//
//        settings.setTableHeaderContent(tableHeaderMap);
//        settings.setTableBodyContent(tableBodyMap);
//
//        /*Image*/
//        settings.setLeftImagePaths(new String[]{
//                "./src/main/resources/files/images/ads/logo-tester.png",
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png"
//        });
//        settings.setCenterImagePaths(new String[]{
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png"
//        });
//        settings.setRightImagePaths(new String[]{
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png",
//                "./src/main/resources/files/images/ads/java.png"
//        });
//
//        /*Signature*/
//        settings.setSignaturePersonName("John Smith Mountain");
//        settings.setSignaturePersonDoc("123456789011");
//        settings.setSignatureRecord("9089739827389");
//        settings.setSignatureDateGmt("2020.01.01 10:00:00 -03:00");
//
//        /*Text*/
//        HashMap<Integer, String> textContentMap = new HashMap<>();
//        String textContent1 =
//                "1. One in the simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
//                        "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
//                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
//                        "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
//                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
//                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make.";
//        String textContent2 =
//                "2. Two more simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
//                        "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
//                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
//                        "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
//                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
//                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make.";
//        String textContent3 =
//                "3. Three is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
//                        "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
//                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
//                        "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
//                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
//                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make.";
//        String textContent4 =
//                "4. Four is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
//                        "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
//                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
//                        "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
//                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
//                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make.";
//        String textContent5 =
//                "5. is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been\n" +
//                        "the industry, Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum\n" +
//                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer took off\n" +
//                        "galley of type and scrambled it to make a type specimen book, has been the industry's standard from\n" +
//                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make more\n" +
//                        "text ever since the 1500s and scrambled it to make a type specimen book scrambled it to make.";
//
//        textContentMap.put(0, textContent1);
//        textContentMap.put(1, textContent2);
//        textContentMap.put(2, textContent3);
//        textContentMap.put(3, textContent4);
//        textContentMap.put(4, textContent5);
//        settings.setTextContent(textContentMap);
//
//        /*Barcode*/
//        HashMap<Integer, String> barcodeValueMap = new HashMap<>();
//        HashMap<Integer, String> barcodeOneMap = new HashMap<>();
//        HashMap<Integer, String> barcodeTwoMap = new HashMap<>();
//        HashMap<Integer, String> barcodeThreeMap = new HashMap<>();
//        HashMap<Integer, String> barcodeFourMap = new HashMap<>();
//        HashMap<Integer, String> barcodeAmountMap = new HashMap<>();
//        for (int i = 0; i < 5; i++) {
//            barcodeValueMap.put(i, i+"23456789123456789123456789123456789");
//            barcodeOneMap.put(i, i + " John Smith Viz Owing");
//            barcodeTwoMap.put(i, i + " Street Saint Luz Fright 200 ");
//            barcodeThreeMap.put(i, i + " This is a message for the customer");
//            barcodeFourMap.put(i, i + " Space reserved to operator");
//            barcodeAmountMap.put(i, "R$ 1.000.00"+i+",00");
//        }
//        settings.setBarcodeValue(barcodeValueMap);
//        settings.setBarcodeInfoOne(barcodeOneMap);
//        settings.setBarcodeInfoTwo(barcodeTwoMap);
//        settings.setBarcodeInfoThree(barcodeThreeMap);
//        settings.setBarcodeInfoFour(barcodeFourMap);
//        settings.setBarcodeInfoFour(barcodeFourMap);
//        settings.setBarcodeAmount(barcodeAmountMap);
//
//        /*QRCode*/
//        HashMap<Integer, String> qrCodeValueMap = new HashMap<>();
//        HashMap<Integer, String> qrCodeOneMap = new HashMap<>();
//        HashMap<Integer, String> qrCodeTwoMap = new HashMap<>();
//        HashMap<Integer, String> qrCodeThreeMap = new HashMap<>();
//        HashMap<Integer, String> qrCodeFourMap = new HashMap<>();
//        HashMap<Integer, String> qrCodeAmountMap = new HashMap<>();
//        for (int i = 0; i < 15; i++) {
//            qrCodeValueMap.put(i, "https://www.huntercodexs.com/"+(i+1));
//            qrCodeOneMap.put(i, "John Smith Viz");
//            qrCodeTwoMap.put(i, "Street 123 - FL");
//            qrCodeThreeMap.put(i, "Pay for it easily");
//            qrCodeFourMap.put(i, "huntercodexs.com");
//            qrCodeAmountMap.put(i, "R$ 1000,00");
//        }
//        settings.setQrCodeValue(qrCodeValueMap);
//        settings.setQrCodeInfoOne(qrCodeOneMap);
//        settings.setQrCodeInfoTwo(qrCodeTwoMap);
//        settings.setQrCodeInfoThree(qrCodeThreeMap);
//        settings.setQrCodeInfoFour(qrCodeFourMap);
//        settings.setQrCodeAmount(qrCodeAmountMap);
//
//        return settings;
//    }
//
//    private JEasyPdfTemplateSettings easyPdfTemplateSettingsLetterLayout() {
//        PageSizeToJEasyPdf pageType = PageSizeToJEasyPdf.LETTER_LAYOUT;
//        JEasyPdfTemplateSettings settings = new JEasyPdfTemplateSettings();
//        settings.setTemplate(JEasyPdfTemplates.SLIM);
//        settings.setImageBackground(imgBackground);
//        settings.setDocument(documentSettings(pageType));
//        settings.setPage(pageSettings(pageType));
//        settings.setContainer(containerSettings());
//        settings.setTable(tableSettings());
//        settings.setText(textSettings());
//        settings.setImage(imageSettings());
//        settings.setBarcode(barcodeSettings());
//        settings.setQrCode(qrCodeSettings());
//        settings.setSlim(slimSettingsLetterLayout());
//        settings.setSlimContent(slimData());
//        return settings;
//    }
//
//    private JEasyPdfTemplateSettings easyPdfTemplateSettingsA4Layout() {
//        PageSizeToJEasyPdf pageType = PageSizeToJEasyPdf.A4_LAYOUT;
//        JEasyPdfTemplateSettings settings = new JEasyPdfTemplateSettings();
//        settings.setTemplate(JEasyPdfTemplates.SLIM);
//        settings.setImageBackground(imgBackground);
//        settings.setDocument(documentSettings(pageType));
//        settings.setPage(pageSettings(pageType));
//        settings.setContainer(containerSettings());
//        settings.setTable(tableSettings());
//        settings.setText(textSettings());
//        settings.setImage(imageSettings());
//        settings.setBarcode(barcodeSettings());
//        settings.setQrCode(qrCodeSettings());
//        settings.setSlim(slimSettingsA4Layout());
//        settings.setSlimContent(slimData());
//        return settings;
//    }
//
//    private JEasyPdfTemplateSettings easyPdfTemplateSettingsLetter() {
//        PageSizeToJEasyPdf pageType = PageSizeToJEasyPdf.LETTER;
//        JEasyPdfTemplateSettings settings = new JEasyPdfTemplateSettings();
//        settings.setTemplate(JEasyPdfTemplates.SLIM);
//        settings.setImageBackground(imgBackground);
//        settings.setDocument(documentSettings(pageType));
//        settings.setPage(pageSettings(pageType));
//        settings.setContainer(containerSettings());
//        settings.setTable(tableSettings());
//        settings.setText(textSettings());
//        settings.setImage(imageSettings());
//        settings.setBarcode(barcodeSettings());
//        settings.setQrCode(qrCodeSettings());
//        settings.setSlim(slimSettingsLetter());
//        settings.setSlimContent(slimData());
//        return settings;
//    }
//
//    private JEasyPdfTemplateSettings easyPdfTemplateSettingsA4() {
//        PageSizeToJEasyPdf pageType = PageSizeToJEasyPdf.A4;
//        JEasyPdfTemplateSettings settings = new JEasyPdfTemplateSettings();
//        settings.setTemplate(JEasyPdfTemplates.SLIM);
//        settings.setImageBackground(imgBackground);
//        settings.setDocument(documentSettings(pageType));
//        settings.setPage(pageSettings(pageType));
//        settings.setContainer(containerSettings());
//        settings.setTable(tableSettings());
//        settings.setText(textSettings());
//        settings.setImage(imageSettings());
//        settings.setBarcode(barcodeSettings());
//        settings.setQrCode(qrCodeSettings());
//        settings.setSlim(slimSettingsA4());
//        settings.setSlimContent(slimData());
//        return settings;
//    }
//
//    public void slimPdfLetter() {
//        JEasyPdfTemplate templateManager = new JEasyPdfTemplate();
//        templateManager.easyPdfTemplateSlim(easyPdfTemplateSettingsLetter());
//    }
//
//    public void slimPdfA4() {
//        JEasyPdfTemplate templateManager = new JEasyPdfTemplate();
//        templateManager.easyPdfTemplateSlim(easyPdfTemplateSettingsA4());
//    }
//
//}
