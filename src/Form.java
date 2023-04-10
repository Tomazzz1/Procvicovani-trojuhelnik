import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Form extends JFrame {
    private JPanel main;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton buttonK;
    private JButton buttonL;
    private JMenuBar menuBar;
    private JMenu menuSoubor;
    private JMenu menuAkce;
    private JMenuItem miNacti;
    private JMenuItem miUloz;
    private JMenuItem miKopiruj;
    private JMenuItem miLze;
    private JButton buttonOk;
    private JFileChooser fileChooser = new JFileChooser(".");
    private static final String NAZEV_SOUBORU = "soubor.txt";
    





    public Form(){
        initComponents();
        buttonK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setText(textField1.getText());
                textField3.setText(textField1.getText());
            }
        });
        buttonL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strA = textField1.getText();
                String strB = textField2.getText();
                String strC = textField3.getText();

                if(strA.isEmpty() || strB.isEmpty() || strC.isEmpty()){
                    JOptionPane.showMessageDialog(main,"Zadejte prosím délky všech stran", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double a = Double.parseDouble(strA);
                double b = Double.parseDouble(strB);
                double c = Double.parseDouble(strC);

                if (a + b > c && a + c > b && b + c > a){
                    JOptionPane.showMessageDialog(main,"Ze stran délek A: " + a + " B: " + b + " C: " + c +
                            " lze sestrojit trojúhelník", "Výsledek", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(main,"Ze stran délek A: " + a + " B: " + b + " C: " + c +
                            " nelze sestrojit trojúhelník", "Výsledek", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }
    private void lzeSestrojit(){
        String strA = textField1.getText();
        String strB = textField2.getText();
        String strC = textField3.getText();

        if(strA.isEmpty() || strB.isEmpty() || strC.isEmpty()){
            JOptionPane.showMessageDialog(main,"Zadejte prosím délky všech stran", "Chyba", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);
        double c = Double.parseDouble(strC);

        if (a + b > c && a + c > b && b + c > a){
            JOptionPane.showMessageDialog(main,"Ze stran délek A: " + a + " B: " + b + " C: " + c +
                    " lze sestrojit trojúhelník", "Výsledek", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(main,"Ze stran délek A: " + a + " B: " + b + " C: " + c +
                    " nelze sestrojit trojúhelník", "Výsledek", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void initComponents(){
        setContentPane(main);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuSoubor = new JMenu("Soubor");
        menuBar.add(menuSoubor);
        menuAkce = new JMenu("Akce");
        menuBar.add(menuAkce);
        miNacti = new JMenuItem("Načti");
        miUloz = new JMenuItem("Ulož");
        miKopiruj = new JMenuItem("Kopíruj A do B i C");
        miLze = new JMenuItem("Lze sestrojit trojúhelník?");
        menuSoubor.add(miNacti);
        menuSoubor.add(miUloz);
        menuAkce.add(miKopiruj);
        menuAkce.add(miLze);
        miKopiruj.addActionListener(e -> kopirujText());
        miNacti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(main);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(NAZEV_SOUBORU));
                    String radek = reader.readLine();
                    String[] castiRadku = radek.split(";");
                    textField1.setText(castiRadku[0]);
                    textField2.setText(castiRadku[1]);
                    textField3.setText(castiRadku[2]);
                    reader.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                }
            }
        });
        miUloz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showSaveDialog(main);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                   try{
                       FileWriter writer = new FileWriter(NAZEV_SOUBORU);
                       writer.write(textField1.getText() + ";" + textField2.getText() + ";" + textField3.getText());
                       writer.close();
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);

                   }
                }
            }
        });
        miLze.addActionListener(e ->lzeSestrojit());

    }
    public void kopirujText(){
        textField2.setText(textField1.getText());
        textField3.setText(textField1.getText());
    }


    public static void main(String[] args) {
        Form form = new Form();
        form.setVisible(true);
        form.setContentPane(form.main);
        form.setDefaultCloseOperation(EXIT_ON_CLOSE);
        form.setSize(300,300);

    }
}
