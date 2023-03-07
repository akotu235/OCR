package io.github.akotu235.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class OcrService {
    public String doOCR(MultipartFile file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));

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