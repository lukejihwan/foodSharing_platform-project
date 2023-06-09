package memberStore;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class FoodItem extends JPanel{
	StoreMenuDTO storeMenuDTO;
	Image image;
	
	public FoodItem(StoreMenuDTO storeMenuDTO) {
		this.storeMenuDTO=storeMenuDTO;
		setPreferredSize(new Dimension(710, 100));
		
		createImage();
	}
	
	public void createImage() {
		File file=new File(storeMenuDTO.getFilename());
		try {
			image=ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D) g;
		
		g2.setColor(StoreMain.col2);
		g2.fillRoundRect(0, 0, 710, 100, 20, 20);
		
		g2.drawImage(image, 20, 10, 90, 80, this);
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("굴림", Font.BOLD, 15));
		
//		setBorder();
		
		g2.drawString("가게이름", 120, 20);
		g2.drawString(storeMenuDTO.getStoreMemberDTO().getStoreName(),200, 20); //상호명 나올 곳
		
		g2.drawString("음식이름", 120, 50);
		g2.drawString(storeMenuDTO.getFoodName(), 200, 50);//음식이름 나올 곳
		
		g2.drawString("음식양", 120, 80);
		g2.drawString(storeMenuDTO.getServings(), 200, 80); //몇인분 나오는 곳
		
		g2.drawString("음식종류", 350, 20);
		g2.drawString(storeMenuDTO.getStoreMemberDTO().getFoodCategory(), 450, 20); //음식종류 나오는 곳

		g2.drawString("예약마감시간", 350, 50);
		g2.drawString(storeMenuDTO.getDeadLine(), 450, 50); //예약마감시간 나오는 곳
		
		g2.drawString("주소", 350, 80);
		g2.drawString(storeMenuDTO.getStoreMemberDTO().getAddress(), 450, 80); //가게 주소 나오는 곳
	}
	
}
