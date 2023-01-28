

package com.util.poster;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImgUtil {

    /**
     * @param qrCodeImg
     * @param headImg
     * @param name
     * @param width
     * @param savePath
     * @return
     * @throws IOException
     */
    public static File combine(BufferedImage headImg, File qrCodeImg, String name, int width, int downHeight, String savePath) throws IOException {
        BufferedImage bufferedImage = combine(headImg, file2Img(qrCodeImg), name, width, downHeight);
        imgSave(bufferedImage, savePath);
        return new File(savePath);
    }


    public static BufferedImage combine(BufferedImage headImg, BufferedImage qrCodeImg, String name, int width, int downHeight) throws IOException {

/*        //头像、名字、二维码比例：1，2，2
      double section = MathUtil.divide(width, 5, 5);
        //头像初始化宽度
        int headWidth = 44;
        //二维码初始化高度
        int qrCodeWidth = (int) ((int) (headWidth)*1.7);*/

        //固定距边框像素
        int paddingBoxWidth = 12;
        //缩小头像到固定大小40*40，此方法返回源图像按给定宽度、高度限制下缩放后的图像
        BufferedImage head = scaleByPercentage(headImg, 62, 62);
        //缩小二维码到固定大小100*100，此方法返回源图像按给定宽度、高度限制下缩放后的图像
        BufferedImage qrCode = scaleByPercentage(qrCodeImg, 100, 100);

//        int height = Math.max(head.getHeight(), qrCode.getHeight());
//        int qrCodeHeight = qrCode.getHeight();

        BufferedImage image = new BufferedImage(width, downHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();
        Font font = new Font("宋体", Font.BOLD, 18);
        //定义一个340*116的画布
        g.fillRect(0, 0, width, downHeight);
        g.setBackground(Color.WHITE);
        //画头像
        g.drawImage(head, paddingBoxWidth, paddingBoxWidth * 2, head.getWidth(), head.getHeight(), null);
        //画二维码
        g.drawImage(qrCode, width - qrCode.getWidth() - paddingBoxWidth + 3, paddingBoxWidth - 3, qrCode.getWidth(), qrCode.getHeight(), Color.black, null);
        //设置透明度
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f));
//        g.setColor(new Color(255, 255, 255));
        //设置背景颜色
        g.setColor(Color.GRAY);
        //设置个人名称
        g.drawString(name, paddingBoxWidth * 2 + head.getWidth(), head.getHeight() / 2 + paddingBoxWidth * 2 - 3);
        //设置字体
        g.setFont(font);
        g.dispose();
        return image;
    }

    public static File combine(File a, File b, int width, String savePath) throws IOException {
        BufferedImage bufferedImage = combine(file2Img(a), file2Img(b), width);
        imgSave(bufferedImage, savePath);
        return new File(savePath);
    }

    public static BufferedImage combine(BufferedImage a, BufferedImage b, int width) throws IOException {

        int aw = a.getWidth();
        int bw = b.getWidth();

        double scaleA = MathUtil.divide(width, aw, 5);
        double scaleB = MathUtil.divide(width, bw, 5);

        BufferedImage newA = resizeByScale(a, scaleA);
        BufferedImage newB = resizeByScale(b, scaleB);

        int w = newA.getWidth();
        int h = newA.getHeight() + newB.getHeight();

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();

        g.drawImage(a, 0, 0, w, newA.getHeight(), null);
        g.drawImage(b, 0, newA.getHeight(), w, newB.getHeight(), null);
        g.dispose();

        return image;
    }


    public static BufferedImage path2Img(String path) throws IOException {
        return ImageIO.read(new File(path));
    }

    public static BufferedImage file2Img(File file) throws IOException {
        return ImageIO.read(file);
    }

    public static BufferedImage stream2Img(InputStream file) throws IOException {
        return ImageIO.read(file);
    }

    /**
     * 按比例缩放
     *
     * @throws IOException
     */
    public static BufferedImage resizeByHeight(BufferedImage img, int height) throws IOException {

        double scaleA = MathUtil.divide(height, img.getHeight(), 5);
        //获取缩放后的长和宽
        int width = (int) (scaleA * img.getWidth());
        //获取缩放后的Image对象
        Image _img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        //新建一个和Image对象相同大小的画布
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D graphics = image.createGraphics();
        //将Image对象画在画布上,最后一个参数,ImageObserver:接收有关 Image 信息通知的异步更新接口,没用到直接传空
        graphics.drawImage(_img, 0, 0, null);
        //释放资源
        graphics.dispose();
        return image;
    }

    /**
     * 按比例缩放
     *
     * @throws IOException
     */
    public static BufferedImage resizeByWidth(BufferedImage img, int width) throws IOException {

        double scaleA = MathUtil.divide(width, img.getWidth(), 5);
        int height = img.getHeight();
        //获取缩放后的长和宽
        int _height = (int) (scaleA * height);
        //获取缩放后的Image对象
        Image _img = img.getScaledInstance(width, _height, Image.SCALE_DEFAULT);
        //新建一个和Image对象相同大小的画布
        BufferedImage image = new BufferedImage(width, _height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D graphics = image.createGraphics();
        //将Image对象画在画布上,最后一个参数,ImageObserver:接收有关 Image 信息通知的异步更新接口,没用到直接传空
        graphics.drawImage(_img, 0, 0, null);
        //释放资源
        graphics.dispose();
        return image;
    }

    /**
     * 按比例缩放
     *
     * @param scale 缩放比例,大于1为放大，小于1为缩小
     * @throws IOException
     */
    public static BufferedImage resizeByScale(BufferedImage img, double scale) throws IOException {
        int width = 300;
        int height = 300;
        //获取缩放后的长和宽
        int _width = (int) (width * scale);
        int _height = (int) (height * scale);
        //获取缩放后的Image对象
        Image _img = img.getScaledInstance(_width, _height, Image.SCALE_DEFAULT);
        //新建一个和Image对象相同大小的画布
        BufferedImage image = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D graphics = image.createGraphics();
        graphics.fillRect(0, 0, width, height);
        //将Image对象画在画布上,最后一个参数,ImageObserver:接收有关 Image 信息通知的异步更新接口,没用到直接传空
        graphics.drawImage(_img, 0, 0, null);
        //释放资源
        graphics.dispose();
        return image;
    }

    public static BufferedImage resizeBySize(BufferedImage img, int width, int height) {
        //与按比例缩放的不同只在于,不需要获取新的长和宽,其余相同.
        Image _img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(_img, 0, 0, null);
        graphics.dispose();
        return image;
    }

    /**
     * 图片大小缩放
     *
     * @param srcImage 源图片
     * @param width    宽
     * @param height   高
     * @return
     */
    public static BufferedImage resize(BufferedImage srcImage, int width, int height) {
        //文件转成9*8像素，为算法比较通用的长宽
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        return buffImg;
    }


    /**
     * 保存图片
     *
     * @param image
     * @param path
     * @return
     * @throws IOException
     */
    public static boolean imgSave(BufferedImage image, String path) throws IOException {
        int index = path.lastIndexOf(".");
        String formatName = path.substring(index + 1);
        if (formatName.equalsIgnoreCase("jpg") || formatName.equalsIgnoreCase("jpeg")) { //重画一下，要么会变色
            BufferedImage tag;
            tag = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_BGR);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            image = tag;
        }
        return ImageIO.write(image, formatName, new File(path));
    }

    /**
     * rbg转gray
     *
     * @param rgb
     * @return
     */
    public static int rbg2Gray(int rgb) {
        int alpha = (0xFF << 24);
        int red = (rgb & 0x00FF0000) >> 16;
        int green = (rgb & 0x0000FF00) >> 8;
        int blue = rgb & 0x000000FF;

        int grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
        grey = (alpha | (grey << 16) | (grey << 8) | grey);


//        int r = rgb >> 16 & 0xff;
//        int g = rgb >> 8 & 0xff;
//        int b = rgb & 0xff;
//        int gray = (r * 30 + g * 59 + b * 11) / 100;

        return grey;
    }

    public static BufferedImage img2Gray(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage image = img.getSubimage(0, 0, img.getWidth(), img.getHeight());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int gray = ImgUtil.rbg2Gray(rgb);
                image.setRGB(x, y, gray);
                if (rgb > 0 && rgb < 255) {
                    System.out.println(rgb);
                }
            }
        }
        return image;
    }

    public static int[] img2Array(BufferedImage img) {

        int width = img.getWidth();
        int height = img.getHeight();
        int[] array = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int index = x + width * y;
                array[index] = rgb;
            }
        }
        return array;
    }

    public static boolean compareGrey(int current, int next) {
        if (current > next) {
            return true;
        }
        return false;
    }

    //把头像切为圆角图片
    public static BufferedImage cutHeadImages(File headImg) {
        BufferedImage avatarImage = null;
        try {
            avatarImage = ImageIO.read(headImg);
            avatarImage = scaleByPercentage(avatarImage, avatarImage.getWidth(), avatarImage.getWidth());
            int width = avatarImage.getWidth();
            // 透明底的图片
            BufferedImage formatAvatarImage = new BufferedImage(width, width, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics = formatAvatarImage.createGraphics();
            //把图片切成一个园
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //留一个像素的空白区域，这个很重要，画圆的时候把这个覆盖
            int border = 1;
            //图片是一个圆型
            Ellipse2D.Double shape = new Ellipse2D.Double(border, border, width - border * 2, width - border * 2);
            //需要保留的区域
            graphics.setClip(shape);
            graphics.drawImage(avatarImage, border, border, width - border * 2, width - border * 2, Color.white, null);
            graphics.dispose();
            //在圆图外面再画一个圆
            //新创建一个graphics，这样画的圆不会有锯齿
            graphics = formatAvatarImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int border1 = 3;
            //画笔是4.5个像素，BasicStroke的使用可以查看下面的参考文档
            //使画笔时基本会像外延伸一定像素，具体可以自己使用的时候测试
            Stroke s = new BasicStroke(5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            graphics.setStroke(s);
            graphics.setColor(Color.white);
            graphics.drawOval(border1, border1, width - border1 * 2, width - border1 * 2);
            graphics.dispose();
            return formatAvatarImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 缩小Image，此方法返回源图像按给定宽度、高度限制下缩放后的图像
     *
     * @param inputImage ：压缩后宽度
     *                   ：压缩后高度
     * @throws IOException return
     */
    public static BufferedImage scaleByPercentage(BufferedImage inputImage, int newWidth, int newHeight) {
        // 获取原始图像透明度类型
        try {
            int type = inputImage.getColorModel().getTransparency();
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();
            // 开启抗锯齿
            RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 使用高质量压缩
            renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            BufferedImage img = new BufferedImage(newWidth, newHeight, type);
            Graphics2D graphics2d = img.createGraphics();
            graphics2d.setRenderingHints(renderingHints);
            graphics2d.drawImage(inputImage, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
            graphics2d.dispose();
            return img;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param a
     * @param b
     * @param width
     * @param savePath
     * @return
     * @throws IOException
     */
    public static File combinePosterAndQrcode(File a, File b, int width, String savePath) throws IOException {
        BufferedImage bufferedImage = combineImges(file2Img(a), file2Img(b), width);
        imgSave(bufferedImage, savePath);
        return new File(savePath);
    }

    public static File combinePosterAndQrcodeBySet(File a, File b, String savePath, Integer qrCodeLeft, Integer qrCodeTop, Integer qrCodeWidth) throws IOException {
        BufferedImage bufferedImage = combineImgesBySet(file2Img(a), file2Img(b), qrCodeLeft, qrCodeTop, qrCodeWidth);
        imgSave(bufferedImage, savePath);
        return new File(savePath);
    }

    /**
     * 合并图片  海报预览图
     *
     * @param posterSrcUrl
     * @param qrCodeUrl
     * @param width
     * @return
     * @throws IOException
     */
    public static BufferedImage combineImges(BufferedImage posterSrcUrl, BufferedImage qrCodeUrl, int width) throws IOException {
        int downHeight = posterSrcUrl.getHeight() + qrCodeUrl.getHeight();

        //按固定宽度340进行缩放图片，此方法返回源图像按给定宽度、高度限制下缩放后的图像
        BufferedImage posterSrc = resizeByWidth(posterSrcUrl, 340);

        BufferedImage image = new BufferedImage(width, posterSrc.getHeight() + qrCodeUrl.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        Font font = new Font("宋体", Font.PLAIN, 18);
        g.fillRect(0, 0, width, downHeight);
        g.setBackground(Color.WHITE);
        //底图
        g.drawImage(posterSrc, 0, 0, 340, posterSrc.getHeight(), null);
        //画二维码
        g.drawImage(qrCodeUrl, 0, posterSrc.getHeight() + 3, posterSrc.getWidth(), qrCodeUrl.getHeight(), Color.black, null);
        //设置透明度
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f));
        g.setFont(font);
        g.dispose();
        return image;
    }

    public static BufferedImage combineImgesBySet(BufferedImage poster, BufferedImage qrCode, Integer qrCodeLeft, Integer qrCodeTop, Integer qrCodeWidth) throws IOException {

        //按固定宽度高度缩放
//        BufferedImage poster = resizeByWidth(background, width);

//        BufferedImage qrCodeNew = null;
        //取短边为准
//        if (backgroundNew.getHeight() > backgroundNew.getWidth()) {
//            qrCodeNew = resizeByWidth(qrCode, backgroundNew.getWidth()/3);
//        }else {
//            qrCodeNew = resizeByHeight(qrCode, backgroundNew.getHeight()/3);
//        }
//        int paddingBoxWidth = 6;
        Graphics2D g = poster.createGraphics();
        //画二维码，位置在右下角
        //g.drawImage(qrCodeNew, backgroundNew.getWidth() - qrCodeNew.getWidth() - 15, backgroundNew.getHeight() - qrCodeNew.getHeight() - 15, Color.black, null);
        float percent = (float) 288 / poster.getWidth();//288是前端的宽度

        int left = poster.getWidth() * qrCodeLeft / 100;
        int top = poster.getHeight() * qrCodeTop / 100;

        int width = poster.getWidth() * qrCodeWidth / 100;

        BufferedImage qrCodeNew = resizeByWidth(qrCode, width);

        g.drawImage(qrCodeNew, left, top, Color.black, null);

        g.dispose();
        return poster;
    }

}
