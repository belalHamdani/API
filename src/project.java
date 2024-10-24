
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class project implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    public JTextArea tya;
    public JTextArea po;
    private JTextArea ta;
    private int WIDTH=800;
    private int HEIGHT=700;


    public project() {
        prepareGUI();
    }

    public static void main(String[] args) {
        project example3 = new project();
        example3.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Avatar Last Airbender");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());

        tya=new JTextArea("tya");
        po = new JTextArea("po");

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);


        //end menu at top



        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,3)); //set the layout of the pannel

        mainFrame.add(controlPanel);
        //mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {
        JButton NextButton = new JButton("Next");


        JButton backButton = new JButton("back");
        JLabel tickaLabel = new JLabel("ticka");





        backButton.setActionCommand("Back");
        controlPanel.add(tya);

        mainFrame.add(NextButton, BorderLayout.EAST);
        controlPanel.add(po);
        mainFrame.add(backButton, BorderLayout.WEST);




        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }


}

