package myservlet.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateCode extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int I_WIDTH = 80; // 图片宽度
    private static final int I_HEIGHT = 30; // 图片高度
    private static Font VALIDATECODE_FONT = new Font("Times New Roman", 0, 20); // 字体
    private static String codeName = null; // 保存到session里面的名字

    public ValidateCode() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache"); // 设置不缓存
        response.setHeader("Cache-Control", "no-cache"); // 设置缓存控制器，不缓存
        response.setDateHeader("Expires", 0L); // 设置不过期。
        BufferedImage bImage = new BufferedImage(I_WIDTH, I_HEIGHT, 1);// 生成一张“画布”
        Graphics g = bImage.getGraphics(); // 获取一支“画笔”
        g.setColor(Color.WHITE); // 白色
        g.fillRect(0, 0, I_WIDTH, I_HEIGHT); // 画一个矩形,fillRect填充整个矩形
        g.setColor(Color.BLUE); // 蓝色
        g.drawRect(0, 0, I_WIDTH - 1, I_HEIGHT - 1);// 画边框,drawRect画一个空心矩形
        g.setColor(Color.BLACK);// 黑色
        g.setFont(VALIDATECODE_FONT); // 设置字体
        String codeNumber = new String(""
                + (10000 + new Random().nextInt(89999))); // 生成随机验证码
        request.getSession().setAttribute(codeName, codeNumber); // 将验证码保存到session里面
        g.drawString(codeNumber, 7, 15); // 将验证码画到图片上
        for (int i = 0; i < 40; i++) { // 给图片加上40个躁点
            int x = new Random().nextInt(I_WIDTH);
            int y = new Random().nextInt(I_HEIGHT);
            g.drawLine(x, y, x, y);
        }
        g.dispose(); // 画好了将笔收起来
        ImageIO.write(bImage, "JPEG", response.getOutputStream());

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        codeName = config.getInitParameter("code"); // 获取web.xml里code的值
    }

}