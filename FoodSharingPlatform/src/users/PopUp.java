package users;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import memberStore.StoreMenuDTO;

//상세보기를 클릭할때 나오는 팝업창 설계
public class PopUp extends JFrame{
	JPanel p_storeName;
	JLabel la_storeName_title;
	JLabel la_storeName;
	
	JPanel p_address;
	JLabel la_address_title;
	JLabel la_address;
	
	JPanel p_memo;
	JLabel la_memo_title;
	JLabel la_memo;
	
	public PopUp(StoreMenuDTO storeMenuDTO) {
		super("상세보기 창");
		p_storeName=new JPanel();
		la_storeName_title=new JLabel("상호명 : ");
		la_storeName=new JLabel(storeMenuDTO.getStoreMemberDTO().getStoreName()); //데이터의 상호명 오게하기
		
		p_address=new JPanel();
		la_address_title=new JLabel("주소 : ");
		la_address=new JLabel(storeMenuDTO.getStoreMemberDTO().getAddress()); //데이터의 주소 오게하기
		
		p_memo=new JPanel();
		la_memo_title=new JLabel("메모 : ");
		la_memo=new JLabel(storeMenuDTO.getMemo()); //데이터의 주소 오게하기
		
		//디자인
		Font font=new Font("굴림", Font.BOLD, 13);
		setLayout(new FlowLayout());
		Dimension p_size=new Dimension(290,30);
		
		p_storeName.setPreferredSize(p_size);
		la_storeName_title.setFont(font);
		la_storeName.setFont(font);
		
		p_address.setPreferredSize(p_size);
		la_address_title.setFont(font);
		la_address.setFont(font);
		
		p_memo.setPreferredSize(new Dimension(290,70));
		la_memo_title.setFont(font);
		la_memo.setFont(font);
		
		//붙이기
		p_storeName.add(la_storeName_title);
		p_storeName.add(la_storeName);
		add(p_storeName);
		
		p_address.add(la_address_title);
		p_address.add(la_address);
		add(p_address);
		
		p_memo.add(la_memo_title);
		p_memo.add(la_memo);
		add(p_memo);
		
		setSize(300, 200);
		setVisible(true);
	}
	
}










