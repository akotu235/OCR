package io.github.akotu235.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class OcrService {
    public String doOCR(BufferedImage bufferedImage) throws IOException {
        ITesseract instance = new net.sourceforge.tess4j.Tesseract();
        instance.setDatapath("src/main/resources/tessdata");
        instance.setLanguage("pol");

        try {
            return instance.doOCR(bufferedImage);
        } catch (TesseractException e) {
            return "";
        }
    }
}