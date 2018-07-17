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
 * java 生成二维码 使用 zxing.jar包
 * 
 * @author 赵涛
 * @data 2018年7月17日
 */
public class CreateQRCode {

	private static final BarcodeFormat barcodeFormat = null;

	
	public static void main(String[] args) {
		//CreateCode();
		readQRCode();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	private static void CreateCode() {
		// 定义高宽
		int width = 300;
		int height = 300;
		String format = "png";
		String content = "赵涛";
		// 定义格式
		HashMap map = new HashMap();
		map.put(EncodeHintType.CHARACTER_SET,"Utf-8"); // 编码utf-8
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 居中
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
            BufferedImage image = ImageIO.read(new File("d:/赵涛.png"));
            
            HashMap hashMap = new HashMap();
            hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
            
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result result = formatReader.decode(binaryBitmap, hashMap);
            
            System.out.println("解析结果："+result.toString());
            System.out.println("二维码格式类型："+result.getBarcodeFormat());
            System.out.println("二维码文本："+result.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




























