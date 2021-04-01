package com.example.rtftopdf;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import java.io.*;

@SpringBootApplication
public class RtftopdfApplication {

	public static void main(String[] args) throws IOException, BadLocationException {
		SpringApplication.run(RtftopdfApplication.class, args);
		String inputFile = "original.rtf";
		Reader rtf = new FileReader(inputFile);
		JEditorPane p = new JEditorPane();
		p.setContentType("text/rtf");
		EditorKit kitRtf = p.getEditorKitForContentType("text/rtf");
		try {
			kitRtf.read(rtf, p.getDocument(), 0);
			EditorKit kitHtml = p.getEditorKitForContentType("text/html");
			Writer writer = new StringWriter();
			kitHtml.write(writer, p.getDocument(), 0, p.getDocument().getLength());
			String html = writer.toString().replace("<<","&lt;&lt;").replace(">>","&gt;&gt;");
			HtmlConverter.convertToPdf(html, new FileOutputStream("converted-pdf.pdf"));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
