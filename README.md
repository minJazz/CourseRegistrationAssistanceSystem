# CourseRegistrationAssistanceSystem
대학교 수강신청 도우미 시스템

  
  - 클라이언트와 서버간의 소켓통신을 기반으로 데이터를(회원정보, 과목정보) 주고받는 시스템
  - DB를 활용하여 과목정보, 회원정보, 학생정보, 장바구니목록 등의 정보를 저장
  - Thread를 활용하여 서버와 클라이언트가 항시 소통하도록 함.
  - Thread를 활용한 다중 접속 가능. 서버와 클라이언트는 1:N관계.
  - 서버는 클라이언트에게 받은 통신을 통하여 원하는 데이터와 통신을 보낸다. 
  - 클라이언트는 서버에게 통신 과 요구한 데이터를 받는다.
  - DB, Server, Client간 3티어 방식을 채택하여 데이터 보안성이 우수하다.
  - 서버에서 데이터를 한번 더 검증한다.
  - 클라이언트는 UI상에서 다크모드를 사용할 수 있다.
