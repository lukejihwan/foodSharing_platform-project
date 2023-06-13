package memberStore;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Page extends JPanel{
	StoreMain storeMain;
	String path="C:/java_workspace2/MyProject/src/memberStore/res/store.png";
	Image image;
	
	public Page(StoreMain storeMain) {
		this.storeMain=storeMain;
		setPreferredSize(new Dimension(980, 650));
		
		image=Toolkit.getDefaultToolkit().getImage(path);
		//getImage()에다가 절대 경로를 적어주어야 함....패키지 내 경로는 안돼...
	}
}
