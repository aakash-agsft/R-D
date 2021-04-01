package com.example.rtftopdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.parser.RtfParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryWithActualLibrary {
    public static void main(String[] args) {
        String inputFile = "try.rtf";
        String outputFile = "actual_converted.pdf";
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            document.open();
            RtfParser parser = new RtfParser(null);
            parser.convertRtfDocument(new FileInputStream(inputFile), document);
            document.close();
            System.out.println("Finished");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
