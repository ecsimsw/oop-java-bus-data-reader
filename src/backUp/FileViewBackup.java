package backUp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileViewBackup extends JFrame {

    private JPanel contentPane;
    private JTextField txtFileName;
    private JButton button;

    private JFileChooser jfc = new JFileChooser();
    private JButton btnSave;
    private JTextArea ta;


    /**
     * Launch the application.
     */
    public static void run() {
        try {
            FileViewBackup frame = new FileViewBackup();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public FileViewBackup() {

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 688, 471);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("파일이름");
        lblNewLabel.setBounds(55, 59, 57, 15);
        contentPane.add(lblNewLabel);

        JButton btnOk = new JButton("파일정보");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            //new File(파일 또는 디렉토리) : 파일의 정보를 가리키는 객체
                File file =new File(txtFileName.getText());
                if(!file.exists()){
                    try{
                        //file.createNewFile();//빈 파일을 생성
                        file.mkdir();//디렉토리 생성
                    }catch(Exception e2){
                        e2.printStackTrace();
                    }
                }

                String str ="파일이름:"+file.getName()+"\n"
                        +"파일크기:"+file.length()+"\n"
                        +"파일여부:"+file.isFile()+"\n"
                        +"파일종류:"+file.isFile() +"\n"
                        +"상위디렉토리:"+file.getParent();

                ta.setText(str); //TextArea 내용 수정

            }
        });
        btnOk.setBounds(482, 55, 137, 23);
        contentPane.add(btnOk);

        txtFileName = new JTextField();
        txtFileName.setBounds(126, 56, 344, 21);
        contentPane.add(txtFileName);
        txtFileName.setColumns(10);

        JButton btnRead = new JButton("파일내용 읽기");
        btnRead.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //파일 이름
                String file=txtFileName.getText();
                String str="";
                BufferedReader reader=null;

                try{
                    //new FileInputStream(파일객체) 파일의 내용을 읽는 객체
                    reader =new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    while(true){
                        str =reader.readLine(); //한줄을 읽음
                        if(str==null)break; //더이상 내용이 없으면 while 종료
                        ta.append(str+"\n");//내용을 덧붙임
                    }
                    reader.close(); //BufferedReader 닫기
                }catch(Exception e2){
                    e2.printStackTrace();
                }finally{ // 예외에 관계없이 항상 실행
                    if(reader !=null){
                        try{
                            reader.close();
                        }catch(IOException e1){
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

        btnRead.setBounds(126, 87, 137, 23);
        contentPane.add(btnRead);

        button = new JButton("파일열기");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(jfc.showOpenDialog(FileViewBackup.this)== JFileChooser.APPROVE_OPTION){

                    File file=new File(jfc.getSelectedFile().toString());

                    if(!file.exists()){
                        try {
                            file.createNewFile();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    txtFileName.setText(jfc.getSelectedFile().toString());
                }

            }
        });
        button.setBounds(482, 22, 137, 23);
        contentPane.add(button);

        //파일 저장 버튼
        btnSave = new JButton("파일내용저장");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                BufferedWriter writer=null;
                try{
                    String file=txtFileName.getText();//파일저장 위치 + 파일 이름
                    // new FileWriter(파일) 파일에 문자 단위 기록
                    writer=new BufferedWriter(new FileWriter(file));
                    String str=ta.getText();
                    writer.write(str);//파일 내용 저장

                }catch(Exception e2){
                    e2.printStackTrace();
                }finally {
                    try{
                        if(writer!=null){
                            writer.close(); // 버퍼 닫기
                        }
                    }catch(Exception e3){
                        e3.printStackTrace();
                    }
                }
            }
        });
        btnSave.setBounds(344, 87, 125, 23);
        contentPane.add(btnSave);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(44, 131, 575, 260);
        contentPane.add(scrollPane);

        ta = new JTextArea();
        scrollPane.setViewportView(ta);
    }




}
