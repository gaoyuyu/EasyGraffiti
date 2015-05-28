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
    private static final int I_WIDTH = 80; // ͼƬ���
    private static final int I_HEIGHT = 30; // ͼƬ�߶�
    private static Font VALIDATECODE_FONT = new Font("Times New Roman", 0, 20); // ����
    private static String codeName = null; // ���浽session���������

    public ValidateCode() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache"); // ���ò�����
        response.setHeader("Cache-Control", "no-cache"); // ���û����������������
        response.setDateHeader("Expires", 0L); // ���ò����ڡ�
        BufferedImage bImage = new BufferedImage(I_WIDTH, I_HEIGHT, 1);// ����һ�š�������
        Graphics g = bImage.getGraphics(); // ��ȡһ֧�����ʡ�
        g.setColor(Color.WHITE); // ��ɫ
        g.fillRect(0, 0, I_WIDTH, I_HEIGHT); // ��һ������,fillRect�����������
        g.setColor(Color.BLUE); // ��ɫ
        g.drawRect(0, 0, I_WIDTH - 1, I_HEIGHT - 1);// ���߿�,drawRect��һ�����ľ���
        g.setColor(Color.BLACK);// ��ɫ
        g.setFont(VALIDATECODE_FONT); // ��������
        String codeNumber = new String(""
                + (10000 + new Random().nextInt(89999))); // ���������֤��
        request.getSession().setAttribute(codeName, codeNumber); // ����֤�뱣�浽session����
        g.drawString(codeNumber, 7, 15); // ����֤�뻭��ͼƬ��
        for (int i = 0; i < 40; i++) { // ��ͼƬ����40�����
            int x = new Random().nextInt(I_WIDTH);
            int y = new Random().nextInt(I_HEIGHT);
            g.drawLine(x, y, x, y);
        }
        g.dispose(); // �����˽���������
        ImageIO.write(bImage, "JPEG", response.getOutputStream());

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        codeName = config.getInitParameter("code"); // ��ȡweb.xml��code��ֵ
    }

}