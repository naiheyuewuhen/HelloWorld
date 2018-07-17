package cn.chetech.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 
 * java ���ɶ�ά�� ʹ�� zxing.jar��
 * 
 * @author ����
 * @data 2018��7��17��
 */
public class CreateQRCode {

	private static final BarcodeFormat barcodeFormat = null;

	
	public static void main(String[] args) {
		//CreateCode();
		readQRCode();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	private static void CreateCode() {
		// ����߿�
		int width = 300;
		int height = 300;
		String format = "png";
		String content = "����";
		// �����ʽ
		HashMap map = new HashMap();
		map.put(EncodeHintType.CHARACTER_SET,"Utf-8"); // ����utf-8
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // ����
		map.put(EncodeHintType.MARGIN, 2); // margin 2
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, barcodeFormat.QR_CODE, width, height,map);
			Path file = new File("d:/java/QRCode"+content+".png").toPath();
			MatrixToImageWriter.writeToPath(bitMatrix,format,file);  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private static void readQRCode() {
        try {
            MultiFormatReader formatReader = new MultiFormatReader();
            BufferedImage image = ImageIO.read(new File("d:/����.png"));
            
            HashMap hashMap = new HashMap();
            hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
            
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result result = formatReader.decode(binaryBitmap, hashMap);
            
            System.out.println("���������"+result.toString());
            System.out.println("��ά���ʽ���ͣ�"+result.getBarcodeFormat());
            System.out.println("��ά���ı���"+result.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




























