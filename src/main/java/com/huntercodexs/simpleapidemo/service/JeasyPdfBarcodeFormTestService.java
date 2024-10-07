//package com.huntercodexs.simpleapidemo.service;
//
//import com.huntercodexs.jeasypdf.JEasyPdf;
//import com.huntercodexs.jeasypdf.core.dto.*;
//import com.huntercodexs.jeasypdf.core.enumerator.*;
//import org.krysalis.barcode4j.HumanReadablePlacement;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class JeasyPdfBarcodeFormTestService {
//
//    private final static String PATH = "./src/main/resources/files";
//    private final static String FILEPATH_SOURCE = PATH+"/txt/file.txt";
//    private final static String FILEPATH_TARGET = PATH+"/pdf/my-jeasypdf-test.pdf";
//    private final static String FILEPATH_TARGET_IMAGE = PATH+"/pdf/my-jeasypdf-test-image.pdf";
//    private final static String FILEPATH_TARGET_ADD_IMAGE = PATH+"/pdf/my-jeasypdf-test-add-image.pdf";
//    private final static String FILEPATH_TARGET_IMAGE_PASSWORD = PATH+"/pdf/my-jeasypdf-test-image-password.pdf";
//    private final static String FILEPATH_TARGET_MERGER = PATH+"/pdf/merger/my-jeasypdf-test-merged.pdf";
//    private final static String FILEPATH_TARGET_SCANNER = PATH+"/pdf/my-jeasypdf-test-barcode.pdf";
//    private final static String FILEPATH_TARGET_CONTAINER = PATH+"/pdf/my-jeasypdf-test-container.pdf";
//    private final static String FILEPATH_TARGET_BC_128 = PATH+"/pdf/my-jeasypdf-test-bc128.pdf";
//    private final static String FILEPATH_TARGET_BC_39 = PATH+"/pdf/my-jeasypdf-test-bc39.pdf";
//    private final static String FILEPATH_TARGET_PDF_417 = PATH+"/pdf/my-jeasypdf-test-bc-pdf417.pdf";
//    private final static String FILEPATH_TARGET_QRCODE = PATH+"/pdf/my-jeasypdf-test-qrcode.pdf";
//    private final static String FILEPATH_TARGET_BC = PATH+"/pdf/my-jeasypdf-test-barcode-form.pdf";
//    private final static String FILEPATH_TARGET_PASSWORD = PATH+"/pdf/my-jeasypdf-test-password.pdf";
//    private final static String IMAGE_PATH = "./src/main/resources/files/images/ads/file.png";
//    private final static String IMAGE_PATH_ADS = "./src/main/resources/files/images/ads/img.png";
//    private final static String USER_PASSWORD = "123456";
//    private final static String OWNER_PASSWORD = "password";
//    private final static String BARCODE_TEXT_VALUE = "03399.31339 03600.000008 74216.301015 7 96480000061000";
//
//    private JEasyPdfDocument documentSettings(String filenamePath) {
//        JEasyPdfDocument settings = new JEasyPdfDocument();
//        settings.setStartPage(1);
//        settings.setEndPage(1);
//        settings.setNumberOfPages(1);
//        settings.setDate("1990-01-01 10:00:00");
//        settings.setTitle("Title Test");
//        settings.setAuthor("Huntercodexs");
//        settings.setSubject("Test");
//        settings.setFontName(FontNameToJEasyPdf.COURIER);
//        settings.setFontSize(FontSizeToJEasyPdf.X_SMALL);
//        settings.setKeywords("test, pdf, java, huntercodexs");
//        settings.setUserPassword(null);
//        settings.setOwnerPassword(null);
//        settings.setProtectionLevel(ProtectionLevelToJEasyPdf.HIGH);
//        settings.setFilenamePath(filenamePath);
//        return settings;
//    }
//
//    private JEasyPdfPage pageSettings() {
//        JEasyPdfPage settings = new JEasyPdfPage();
//        settings.setWidth(500);
//        settings.setHeight(780);
//        settings.setOffsetX(25);
//        settings.setOffsetY(750);
//        settings.setLineHeight(18);
//        settings.setPageNumber(1);
//        settings.setMargin(0);
//        settings.setPadding(0);
//        settings.setPageSize(PageSizeToJEasyPdf.LETTER);
//        settings.setFontName(FontNameToJEasyPdf.COURIER);
//        settings.setFontSize(FontSizeToJEasyPdf.X_SMALL);
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
//        settings.setHeight(750);
//        settings.setOffsetX(20);
//        settings.setOffsetY(20);
//        settings.setBorder(true);
//        settings.setRoundedBorder(false);
//        settings.setBackColor(ColorsToJEasyPdf.BLUE_SAD);
//        settings.setBorderColor(ColorsToJEasyPdf.BLACK);
//        return settings;
//    }
//
//    private JEasyPdfText textSettings() {
//        JEasyPdfText settings = new JEasyPdfText();
//        settings.setWidth(500);
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
//        settings.setImageType(ImageTypeToJEasyPdf.JPEG);
//        return settings;
//    }
//
//    private JEasyPdfTable tableSettings() {
//        JEasyPdfTable settings = new JEasyPdfTable();
//        settings.setWidth(100);
//        settings.setHeight(100);
//        settings.setOffsetX(0);
//        settings.setOffsetY(0);
//        settings.setBorder(true);
//        settings.setHeaderColor(ColorsToJEasyPdf.ORANGE);
//        settings.setCelColor(ColorsToJEasyPdf.ICE);
//        settings.setBorderColor(ColorsToJEasyPdf.BLACK);
//        settings.setTableTemplate(TableDimensionsToJEasyPdf.TABLE_5X6);
//        return settings;
//    }
//
//    private JEasyPdfBarcode barcode128Settings(String barcodeText) {
//        JEasyPdfBarcode settings = new JEasyPdfBarcode();
//        settings.setDpi(400);
//        settings.setWidth(500);
//        settings.setHeight(50);
//        settings.setFontSize(2);
//        settings.setLineHeight(20);
//        settings.setOffsetX(55);
//        settings.setOffsetY(100);
//        settings.setFixQuiteZone(0);
//        settings.setDoQuiteZone(false);
//        settings.setData(barcodeText);
//        settings.setFontName(FontNameToJEasyPdf.COURIER);
//        settings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
//        settings.setCodeType4Scanner(CodeType4ScannerToJEasyPdf.CODE128);
//        return settings;
//    }
//
//    private JEasyPdfBarcodeForm barcodeFormSettings() {
//
//        JEasyPdfBarcodeForm settings = new JEasyPdfBarcodeForm();
//        String barcodeValue = "03399.31339 03600.000008 74216.301015 7 96480000061000";
//
//        settings.setAdjustOffsetX(0);
//        settings.setAdjustOffsetY(0);
//        settings.setQrcode(true);
//        settings.setRevealFields(false);
//
//        /*Fields Left*/
//        settings.getFields().setFieldLeft1("Local de pagamento");
//        settings.getFields().setFieldLeft2("Cedente");
//        settings.getFields().setFieldLeft3("Data do documento");
//        settings.getFields().setFieldLeft4("No. documento");
//        settings.getFields().setFieldLeft5("Especie doc.");
//        settings.getFields().setFieldLeft6("Data processamento");
//        settings.getFields().setFieldLeft7("Uso do banco");
//        settings.getFields().setFieldLeft8("Carteira");
//        settings.getFields().setFieldLeft9("Quantidade");
//        settings.getFields().setFieldLeft10("(x)Valor");
//        settings.getFields().setFieldLeft11("Instruções (texto de responsabilidade do cedente)");
//        settings.getFields().setFieldLeft12("qrcode");
//        settings.getFields().setFieldLeft13("Sacado");
//
//        /*Fields Right*/
//        settings.getFields().setFieldRight1("Vencimento");
//        settings.getFields().setFieldRight2("Agencia/Codigo Cedente");
//        settings.getFields().setFieldRight3("Carteira/Nosso Numero");
//        settings.getFields().setFieldRight4("(=)Valor documento");
//        settings.getFields().setFieldRight5("(-)Desconto/Abatimentos");
//        settings.getFields().setFieldRight6("(-)Outras deduções");
//        settings.getFields().setFieldRight7("(+)Mora/Multa");
//        settings.getFields().setFieldRight8("(+)Outros acresciscimos");
//        settings.getFields().setFieldRight9("(=)Valor cobrado");
//        settings.getFields().setFieldRight10("Cod. baixa");
//
//        /*Fields Footer*/
//        settings.getFields().setFieldFooter1("Sacador/Avalista");
//        settings.getFields().setFieldFooter2("Autenticação mecanica - Ficha de Compensação");
//
//        /*Data Header*/
//        settings.getData().setDataHeaderImage("./src/main/resources/files/images/ads/file.png");
//        settings.getData().setDataHeaderOperator("1234-56");
//        settings.getData().setDataHeaderBarcode(barcodeValue);
//
//        /*Data Left*/
//        settings.getData().setDataLeft1("PAGAVEL PREFERENCIALMENTE NAS AGENCIAS DO BANCO XYZ");
//        settings.getData().setDataLeft2("NFE Associados e outros");
//        settings.getData().setDataLeft3("12/01/2000");
//        settings.getData().setDataLeft4("NF 1/1000");
//        settings.getData().setDataLeft5("Test");
//        settings.getData().setDataLeft6("14/10/1999");
//        settings.getData().setDataLeft7("Test");
//        settings.getData().setDataLeft8("008");
//        settings.getData().setDataLeft9("Vinte milhoes de reais");
//        settings.getData().setDataLeft10("R$ 2144,84");
//        List<String> dataLeft11 = Arrays.asList(
//                "Não receber apos o vencimento",
//                "Boleto de teste 1 de 100 para referencia",
//                "478234908840398 432894 09843290 001",
//                "478234908840398 432894 09843290 002",
//                "478234908840398 432894 09843290 003",
//                "478234908840398 432894 09843290 004",
//                "478234908840398 432894 09843290 005",
//                "478234908840398 432894 09843290 006");
//        settings.getData().setDataLeft11(dataLeft11);
//        settings.getData().setDataLeft12(barcodeValue);
//        List<String> dataLeft13 = Arrays.asList(
//                "DISTRIBUIDORA DE AGUAS MINERAIS CPNJ 99.309.309/0001-12",
//                "AV DAS FONTES DE AGUA 1888 10 ANDAR",
//                "BAIRRO DAS FONTES ONDE TEM AGUA - CEP 123341230");
//        settings.getData().setDataLeft13(dataLeft13);
//
//        /*Data Right*/
//        settings.getData().setDataRight1("30/12/2020");
//        settings.getData().setDataRight2("123.123.123/0001-88");
//        settings.getData().setDataRight3("0000000000001-22");
//        settings.getData().setDataRight4("R$ 1693,95");
//        settings.getData().setDataRight5("R$ 13,95");
//        settings.getData().setDataRight6("R$ 24,95");
//        settings.getData().setDataRight7("R$ 10,00");
//        settings.getData().setDataRight8("R$ 16,00");
//        settings.getData().setDataRight9("R$ 2144,84");
//        settings.getData().setDataRight10("000001");
//
//        /*Data Footer*/
//
//        settings.setBorderStyle(QrCodeBorderStyleToJEasyPdf.LEFT_BORDERED);
//
//        return settings;
//    }
//
//    private JEasyPdfBarcode barcode39Settings() {
//        JEasyPdfBarcode settings = new JEasyPdfBarcode();
//        settings.setDpi(400);
//        settings.setWidth(200);
//        settings.setHeight(40);
//        settings.setFontSize(4);
//        settings.setLineHeight(20);
//        settings.setOffsetX(200);
//        settings.setOffsetY(100);
//        settings.setFixQuiteZone(0);
//        settings.setDoQuiteZone(false);
//        settings.setData("123456789");
//        settings.setFontName(FontNameToJEasyPdf.COURIER);
//        settings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
//        settings.setCodeType4Scanner(CodeType4ScannerToJEasyPdf.CODE39);
//        return settings;
//    }
//
//    private JEasyPdfBarcode barcodePdf417Settings() {
//        JEasyPdfBarcode settings = new JEasyPdfBarcode();
//        settings.setDpi(400);
//        settings.setWidth(200);
//        settings.setHeight(40);
//        settings.setFontSize(4);
//        settings.setLineHeight(20);
//        settings.setOffsetX(200);
//        settings.setOffsetY(100);
//        settings.setFixQuiteZone(0);
//        settings.setDoQuiteZone(false);
//        settings.setData("1234567890");
//        settings.setFontName(FontNameToJEasyPdf.COURIER);
//        settings.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
//        settings.setCodeType4Scanner(CodeType4ScannerToJEasyPdf.PDF417);
//        return settings;
//    }
//
//    private JEasyPdfQrCode qrCodeSettings() {
//        JEasyPdfQrCode settings = new JEasyPdfQrCode();
//        settings.setDpi(400);
//        settings.setMargin(0);
//        settings.setMatrix(200);
//        settings.setSize(200);
//        settings.setOnColor(0xFF000001);
//        settings.setOffColor(0xFFFFFFFF);
//        settings.setOffsetX(200);
//        settings.setOffsetY(100);
//        settings.setData("https://huntercodexs.com");
//        settings.setCodeType4Scanner(CodeType4ScannerToJEasyPdf.QRCODE);
//        return settings;
//    }
//
//    public void barcodeForm() {
//        JEasyPdfDocument docSet = documentSettings(FILEPATH_TARGET_BC);
//
//        JEasyPdfPage pageSet = pageSettings();
//        pageSet.setPageNumber(1);
//
//        JEasyPdfBarcode bcSet = barcode128Settings(BARCODE_TEXT_VALUE);
//        bcSet.setWidth(370);
//        bcSet.setHeight(40);
//        bcSet.setOffsetX(45);
//        bcSet.setOffsetY(340);
//        bcSet.setTextPosition(HumanReadablePlacement.HRP_NONE);
//
//        JEasyPdfBarcodeForm bcFormSet = barcodeFormSettings();
//        bcFormSet.setBarcode(bcSet);
//
//        JEasyPdf jEasyPdf = new JEasyPdf();
//        jEasyPdf.pdfCreate(docSet, pageSet);
//        jEasyPdf.pdfAddBarcodeForm(docSet, pageSet, bcFormSet);
//    }
//
//}
