package io.github.akotu235.ocr;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class PdfToImage {
    public ArrayList<BufferedImage> convert(MultipartFile file) throws IOException {
        ArrayList<BufferedImage> images = new ArrayList<>();

        PDDocument document = PDDocument.load(file.getBytes());

        PDFRenderer pdfRenderer = new PDFRenderer(document);

        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            images.add(pdfRenderer.renderImageWithDPI(page, 300));
        }

        document.close();

        return images;
    }
}
