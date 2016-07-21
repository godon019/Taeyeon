package godon.Environment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Godon on 2016-07-09.
 */
public class Frame extends JFrame {
    JTextField fileToLoad, fileToSave;//클래스 변수로 선언

    public boolean okayPushed = false;

    public Frame(){
        //new JFrame();생략됨 나자신이니까 쓸수 없음

        this.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);//프레임 완전하게 끄기
        this.setSize(800,400);
        this.setVisible(true);

        //Layout 배치설정자
        this.setLayout(new GridLayout(5,2));
        add(new JLabel("  welpany"));

        String defualtTextForLoadDirectory = Directories.loadDirectory;
        String defualtTextForSaveDirectory = Directories.saveDirectory;

        fileToLoad = makeTextField(defualtTextForLoadDirectory);
        fileToSave = makeTextField(defualtTextForSaveDirectory);

        JButton button = new JButton("실행");
        add(button);
        this.setVisible(true);
        //버튼 리스너 연결
        button.addActionListener(new Listener(this));
    }

    JTextField makeTextField(String defualtText){
        JPanel panel = new JPanel();
        panel.add(new JLabel("  저장할 파일 위치 :"));
        JTextField jTextField =new JTextField(60);
        jTextField.setText(defualtText);
        panel.add(jTextField);
        add(panel);
        return jTextField;
    }


    class Listener implements ActionListener {
        JFrame frame;
        public Listener(JFrame f){
            frame =f;
        }
        @Override
        synchronized public void actionPerformed(ActionEvent arg0) {
            //버튼을 누르면 이쪽으로 제어가 이동
            System.out.println(arg0.getActionCommand());

            Directories.loadDirectory = fileToLoad.getText();
            System.out.println(Directories.loadDirectory);

            Directories.saveDirectory = fileToSave.getText();
            System.out.println(Directories.saveDirectory);

            okayPushed = true;
            //다이얼로그
            JOptionPane.showMessageDialog(frame, Directories.loadDirectory+"\n"+Directories.saveDirectory);

        }
    }
}
