package io.github.akotu235.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class OcrService {
    public String doOCR(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String extension = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(".") + 1);

        BufferedImage bufferedImage;
        StringBuilder content = new StringBuilder();

        if(extension.equals("pdf")){
            PdfToImage pdfToImage = new PdfToImage();
            ArrayList<BufferedImage> images = pdfToImage.convert(file);
            for (BufferedImage img:images) {
                content.append(BufferedImageToString(img));
            }
        }
        else {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            content = new StringBuilder(BufferedImageToString(bufferedImage));
        }

        return content.toString();
    }

    String BufferedImageToString(BufferedImage bufferedImage){
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