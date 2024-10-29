
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    int charNum = 0;


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
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Next")) {

               if(charNum<19) {
                   charNum++;
               }
               else {
                   charNum = 0;
               }

                try {
                    pull();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Next API");
            }
                if (command.equals("Back")) {

                    if(charNum>0) {
                        charNum--;
                    }
                    try {
                        pull();
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("Back API");



                }


        }
    }
    public  void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";
        try {
            //   https://pokeapi.co/api/v2/pokemon/ditto
            URL url = new URL("https://last-airbender-api.fly.dev/api/v1/characters");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) parser.parse(totlaJson);
        System.out.println(jsonArray);

        try {

            int f = jsonArray.size();
           // for (int z = 0; z < f; ++z) {
                JSONObject gaga = (JSONObject) jsonArray.get(charNum);


                String name = (String) gaga.get("name");

                org.json.simple.JSONArray msg = (org.json.simple.JSONArray) gaga.get("allies");

                int n = msg.size(); //(msg).length();

                for (int i = 0; i < n; ++i) {
                    String test = (String) msg.get(i);
                    System.out.println("\n"+"My allies are " + test);
                    tya.append("\n"+"My allies are " + test);


                    // System.out.println(person.getInt("key"));
                }
                org.json.simple.JSONArray smg = (org.json.simple.JSONArray) gaga.get("enemies");

                int a = smg.size(); //(msg).length();

                for (int i = 0; i < a; ++i) {
                    String test = (String) smg.get(i);
                    System.out.println(test);
                    tya.append("\n"+"My enemies are " + test);
                }
//            org.json.simple.JSONArray aff = (org.json.simple.JSONArray) gaga.get("affiliation");
//
//            int w = aff.size(); //(msg).length();
//
//            for (int i = 0; i < w; ++i) {
//                String test = (String) aff.get(i);
//                System.out.println(test);
//            }

                //     String spg = (String) gaga.get("gender");


                //   System.out.println(spg);


                // System.out.println(person.getInt("key"));

                //  org.json.simple.JSONArray sag = (org.json.simple.JSONArray) gaga.get("hair");

                //   int q = sag.size(); //(msg).length();

                // for (int i = 0; i < q; ++i) {
                //      String test = (String) sag.get(i);
                //    System.out.println(test);


                // System.out.println(person.getInt("key"));
                //   }
                String height = (String) gaga.get("height");
                System.out.println("My name is " + name);
            tya.append("\n"+"My name is " + name);
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void showEventDemo() {
        JButton NextButton = new JButton("Next");


        JButton backButton = new JButton("back");
        JLabel tickaLabel = new JLabel("ticka");





        backButton.setActionCommand("Back");
        NextButton.setActionCommand("Next");
        NextButton.addActionListener(new ButtonClickListener());
        backButton.addActionListener(new ButtonClickListener());
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

