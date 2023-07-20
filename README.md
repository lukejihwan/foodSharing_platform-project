# 음식 나눔 플랫폼 프로젝트

## 💻프로젝트 소개
Swing기반의 GUI모드로 식당운영자가 당일 나눔하고자 하는 음식을 플랫폼에 업로드하면 <br>
사용자인 취약계층 청소년들이 원하는 음식을 예약하는 시스템
## 💻개발인원: 개인

## 💻주요기능
- 사업자번호, ID, PW, 상호명, 주소, 업종선택(한식,중식 등)을 입력하는 사업자 회원가입과 로그인 기능 
- 음식명, 몇인분, 예약마감시간, 메모, 🖼️이미지 데이터를 입력하는 사업자 메뉴 등록 기능 
- 이름 ID, PW, PW확인, 생일, 나눔카드번호, 📱휴대폰번호를 입력하는 사용자 회원가입과 로그인 기능 
- 사용자 음식종류, 상호명, 주소 별 검색 기능 
- 사용자 예약시 사업자와 채팅기능

## 💻성과
- Swing기반 JButton,JTextField 디자인에 맞게 커스텀하여 재사용가능하도록 클래스 단위로 설계
- Connection 객체를 싱글톤 패턴으로 생성하여 메모리 효율성 증대
- 공통된 페이지 영역을 하나의 클래스로 묶어 각각의 페이지가 상속 받아 사용하도록 하여 코드 중복 제거

## 💻사용기술
### ⚙️javaSE : 
1. JFrame, JButton, JTextField 등 Swing기반으로 독립형 실행프로그램 설계
2. Connection 객체 싱글톤으로 구축 <br>

### ⚙️jdbc : 
1. 사업자, 사용자 회원가입 저장 쿼리 구현
2. 업로드 할 음식의 CRUD 구현 <br>
### ⚙️oracleDB

## 💻시연 영상
- 사업자 회원가입 <br>
![나눔플랫폼_사업자회원가입](https://github.com/lukejihwan/foodSharing_platform-project/assets/111648451/48f412d2-c147-4d9b-a089-739d7bbcaaad)


- 사업자 메뉴등록 <br>
![나눔플랫폼_상품등록](https://github.com/lukejihwan/foodSharing_platform-project/assets/111648451/61a8fb5e-dbd4-4a7e-b836-12c103ff4434)
