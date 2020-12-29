package view;

import javax.swing.*;
import java.awt.event.InputEvent;

public class MainView {
    // 프레임 선언
    JFrame jFrame = new JFrame("생산기술연구원 버스 데이터 리더");

    // Swing에서의 텍스트 영역.. 스크롤바가 존재하지 않는다.
    JTextArea textArea = new JTextArea("내용 입력하세요.", 5, 10);// 스크롤바 없음


    // Swing에서 스크롤바를 넣기 위해 아래와 같이 사용한다.
    JScrollPane jp = new JScrollPane(textArea);// 스클롤바 만들기

    // 메뉴 선언
    JMenuBar menuBar = new JMenuBar();
    JMenu settingMenu = new JMenu("Setting");
    JMenu helpMenu = new JMenu("Help");
    JMenu run = new JMenu("Run");

    public MainView() {
        // 파일 메뉴 생성
        settingMenu.add(new JMenuItem("새 파일"));
        settingMenu.getItem(0).setAccelerator
                (KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK ^ InputEvent.ALT_MASK)); //단축키설정
        settingMenu.add(new JMenuItem("열기"));
        settingMenu.add(new JMenuItem("저장"));
        settingMenu.addSeparator(); //구분선 추가
        settingMenu.add(new JMenuItem("종료"));

        // 도움 메뉴 생성
        helpMenu.add(new JMenuItem("버전"));
        helpMenu.add(new JMenuItem("정보"));

        // 메뉴를 메뉴바에 등록
        menuBar.add(settingMenu);
        menuBar.add(helpMenu);
        menuBar.add(run);

        // 메뉴바 추가
        jFrame.setJMenuBar(menuBar);

        // 텍스트 영역 추가
        jFrame.add(jp, "Center");

        // 프레임 크기 지정
        jFrame.setSize(1000, 1000);

        // 프레임 보이기
        jFrame.setVisible(true);

        // swing에만 있는 X버튼 클릭시 종료
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}