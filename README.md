# oop-java-bus-data-reader
자바 코딩 컨벤션 준수와 객체 지향 프로그래밍을 연습하기 위해 개인적으로 만들어본 예제입니다.

프로그램은 버스 데이터와 사용자 데이터를 읽어 조회 기간에 해당하는 내역과 운임 비용을 계산합니다.

연습하고자 집중했던 부분은 다음과 같습니다.

1. [소트웍스 앤솔러지](http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=9788992939249)의 객체지향 생활 체조 9규칙을 만족하고자 노력한다.

2. 예상하지 못한 예외 발생이 없도록 한다.

3. 자바 코딩 컨벤션을 준수한다.


## 필요 기능 목록

- #### 기록 조회
    - 사용자 데이터 파일은 이름, 사번, 카드 번호, 부서 정보를 포함한다.
    - 버스 데이터 파일은 카드 번호, 탑승일 정보를 포함한다.
    - 프로그램은 카드 번호를 기준으로 사용자 정보와 버스 탑승 정보를 매핑하여 조회한다.
    - 조회한 기록을 사번 순으로 정렬한다.
    - 버스별 

- #### 입력
    - 사용자 데이터(엑셀)를 입력받는다.
    - 버스 카드 기록 데이터를 입력받는다.
    - 조회 날짜 범위를 입력받는다.
    
- #### 출력 
    - 조회한 결과를 엑셀에 저장한다.
    - 발생한 예외의 메시지를 출력한다. 