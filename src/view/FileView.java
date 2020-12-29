package view;

import controller.MainController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class FileView extends JFrame {
    private final JTextField firstBusFilePath = new JTextField();
    private final JTextField secondBusFilePath = new JTextField();
    private final JTextField thirdBusFilePath = new JTextField();
    private final JTextField fourthBusFilePath = new JTextField();
    private final JTextField dateFrom = new JTextField(10);
    private final JTextField dateTo = new JTextField(10);
    private final JPanel contentPane = new JPanel();
    private final JTextArea textArea = new JTextArea();

    public static void run() {
        try {
            FileView frame = new FileView();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileView() {
        super("KITECH 버스 태그 기록 조회");

        setMenuBar();
        setDateRange();
        setFirstBus();
        setSecondBus();
        setThirdBus();
        setFourthBus();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 688, 471);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(44, 175, 575, 220);
        contentPane.add(scrollPane);
    }

    private void setMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu settingMenu = getSettingMenuOnMenuBar();
        JMenu runningMenu = getRunMenuOnMenuBar();
        menuBar.add(settingMenu);
        menuBar.add(runningMenu);
        setJMenuBar(menuBar);
    }

    private JMenu getSettingMenuOnMenuBar() {
        JMenu settingMenu = new JMenu("Setting");
        settingMenu.add(new JMenuItem("저장 경로 변경"));
        settingMenu.add(new JMenuItem("도움말"));

        return settingMenu;
    }

    private JMenu getRunMenuOnMenuBar() {
        JMenu runningMenu = new JMenu("Run");

        JMenuItem executeBtn = new JMenuItem("실행");
        runningMenu.add(executeBtn);
        executeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = search();
                textArea.append(result+"\n");
            }
        });
        return runningMenu;
    }

    private String search(){
        List<String> dataPaths = new ArrayList<>();
        dataPaths.add(firstBusFilePath.getText());
        dataPaths.add(secondBusFilePath.getText());
        dataPaths.add(thirdBusFilePath.getText());
        dataPaths.add(fourthBusFilePath.getText());

        LocalDate fromDate;
        LocalDate toDate;

        try{
            fromDate = parseToLocalDate(dateFrom);
            toDate = parseToLocalDate(dateTo);
        }catch (Exception e){
            return "올바르지 않은 형식의 날짜 입력입니다.\n";
        }

        MainController mainController = new MainController(dataPaths, fromDate, toDate);
        return mainController.run();
    }

    private LocalDate parseToLocalDate(JTextField dateField){
        String[] dates = dateField.getText().split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);
        return LocalDate.of(year, month, day);
    }

    private void setFirstBus() {
        JLabel lblNewLabel = new JLabel("평택 버스");
        lblNewLabel.setBounds(55, 22, 70, 15);
        contentPane.add(lblNewLabel);

        JButton readFileBtn = new JButton("파일 열기");
        readFileBtn.setBounds(499, 19, 120, 23);
        contentPane.add(readFileBtn);

        readFileBtn.addActionListener(e -> firstBusFilePath.setText(readFilePath()));
        firstBusFilePath.setBounds(126, 20, 361, 21);
        contentPane.add(firstBusFilePath);
        firstBusFilePath.setColumns(10);
    }

    private void setSecondBus() {
        JLabel lblNewLabel = new JLabel("교대 버스");
        lblNewLabel.setBounds(55, 52, 70, 15);
        contentPane.add(lblNewLabel);

        JButton readFileBtn = new JButton("파일 열기");
        readFileBtn.setBounds(499, 49, 120, 23);
        contentPane.add(readFileBtn);

        readFileBtn.addActionListener(e -> secondBusFilePath.setText(readFilePath()));
        secondBusFilePath.setBounds(126, 50, 361, 21);
        contentPane.add(secondBusFilePath);
        secondBusFilePath.setColumns(10);
    }

    private void setThirdBus() {
        JLabel lblNewLabel = new JLabel("사당 버스");
        lblNewLabel.setBounds(55, 82, 70, 15);
        contentPane.add(lblNewLabel);

        JButton readFileBtn = new JButton("파일 열기");
        readFileBtn.setBounds(499, 79, 120, 23);
        contentPane.add(readFileBtn);

        readFileBtn.addActionListener(e -> thirdBusFilePath.setText(readFilePath()));
        thirdBusFilePath.setBounds(126, 80, 361, 21);
        contentPane.add(thirdBusFilePath);
        thirdBusFilePath.setColumns(10);
    }

    private void setFourthBus() {
        JLabel lblNewLabel = new JLabel("천안 버스");
        lblNewLabel.setBounds(55, 112, 70, 15);
        contentPane.add(lblNewLabel);

        JButton readFileBtn = new JButton("파일 열기");
        readFileBtn.setBounds(499, 109, 120, 23);
        contentPane.add(readFileBtn);

        readFileBtn.addActionListener(e -> fourthBusFilePath.setText(readFilePath()));
        fourthBusFilePath.setBounds(126, 110, 361, 21);
        contentPane.add(fourthBusFilePath);
        fourthBusFilePath.setColumns(10);
    }

    private String readFilePath() {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(FileView.this) == JFileChooser.APPROVE_OPTION) {
            return jfc.getSelectedFile().toString();
        }
        return null;
    }

    private void setDateRange() {
        JLabel fromLabel = new JLabel("시작 일자");
        fromLabel.setBounds(55, 143, 70, 15);
        contentPane.add(fromLabel);

        dateFrom.setBounds(126, 140, 100, 23);
        dateFrom.setText("yyyy-dd-mm");
        contentPane.add(dateFrom);

        JLabel toLabel = new JLabel("종료 일자");
        toLabel.setBounds(250, 143, 70, 15);
        contentPane.add(toLabel);

        dateTo.setBounds(321, 140, 100, 23);
        dateTo.setText("yyyy-dd-mm");
        contentPane.add(dateTo);
    }
}