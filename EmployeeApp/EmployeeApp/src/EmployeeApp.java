/* 1/19/17 Rio Felici
 */

import employee.Employee;
import employee.EmployeeList;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.NumberFormat;

public class EmployeeApp extends JFrame {
    File f;

    EmployeeList list = new EmployeeList();

    class EmployeePanel extends JPanel{

        public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font f = new Font("Monotype Corsiva", Font.ITALIC, 72);
        g.setFont(f);
  //    g.setColor(Color.MAGENTA);
        g.drawString("Name ", 10, 60);
        g.drawString("Age ", 340, 60);
        g.drawString("Income ", 590 , 60);

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(0);
        FontMetrics fm = g.getFontMetrics();

        for ( int i=0 ; i <list.length() ; i++ ) {
            Employee temp = list.get(i);
            g.drawString( temp.getName(), 20, 140 + i*60 );
            g.drawString(temp.getAge() + " ", 350, 140 + i*60 );

            String s = nf.format(temp.getIncome());
            int w = fm.stringWidth(s);
            g.drawString(s, 770-w, 120+i*60);
        }
        if (list.length()>0) {
            g.drawString("Average: " + list.getAverageAge(), 20, 130 + list.length()*70);
            }
        }
    }

    public EmployeeApp() {
        super("Employee Application");

        final EmployeePanel ep = new EmployeePanel();

//Menu Bar
        JMenuBar jmb = new JMenuBar();
        setJMenuBar(jmb);

//File Menu
        JMenu jm = jmb.add(new JMenu("File"));

//File Menu -> New MenuItem
        JMenuItem jmi = jm.add(new JMenuItem("New...", 'N'));
        jmi.addActionListener(e ->{
            String s = JOptionPane.showInputDialog(EmployeeApp.this, "Enter 1 or more Employees (Name Age Income)");
            if (s!= null) {
                list.getData(s);
                repaint();} //Repaint updates screen because JAVA DOES NOT UPDATE AUTOMATICALLY
        });

//File Menu -> Add MenuItem
        jmi = jm.add(new JMenuItem("Add...",'A'));
        jm.addSeparator();

//File Menu -> Open MenuItem
        jmi = jm.add(new JMenuItem("Open...",'O'));
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File (".")); //Sets current directory to the current working file (in this case EmployeeApp)
        jmi.addActionListener(e ->{
            if(jfc.showOpenDialog(EmployeeApp.this) == JFileChooser.APPROVE_OPTION) {
                f = jfc.getSelectedFile();
                int size = (int) f.length();
                char ch[] = new char[size]; //Reads an Array of bytes
                try{
                    FileReader fr = new FileReader(f);
                    fr.read(ch, 0 , size);
                    fr.close();
                }catch(FileNotFoundException ex) {
                    System.err.println("File does not " + "exist.");
                }catch(IOException excp) {
                    System.err.println("IO");
                }
                String s = new String(ch); //Converts char to string
                list.getData(s);
                repaint();
            }
        });

//File Menu -> Save MenuItem
        jmi = jm.add(new JMenuItem("Save",'S'));
        // if file is null we need to make new file
        // if (f == null)

        // if file is not null we need to get it and save it to that same file


//File Menu -> Save As MenuItem
        jmi = jm.add(new JMenuItem("Save As...",'V'));
        jfc.setCurrentDirectory(new File ("."));
        jmi.addActionListener(e ->{
            if(jfc.showSaveDialog(EmployeeApp.this) == JFileChooser.APPROVE_OPTION) {
                f = jfc.getSelectedFile();
                int size = (int) f.length();
                char ch[] = new char[size]; //Reads an Array of bytes

                try (FileWriter fw = new FileWriter(f)) {
                    fw.write(ch, 0 , size);
                }
                catch(FileNotFoundException ex) {
                    System.err.println("File does not " + "exist.");
                }catch(IOException excp) {
                    System.err.println("IO");
                }
                String s = new String(ch); //Converts char to string
                list.getData(s);
                repaint();
            }
        });
        jm.addSeparator();

//File Menu -> Exit MenuItem
        jmi = jm.add(new JMenuItem("Exit",'E'));
        jmi.addActionListener( e-> System.exit(0));

//Sort Menu
        jm  = jmb.add(new JMenu("Sort"));
        ButtonGroup bg = new ButtonGroup();

//Sort Menu -> Name MenuItem
        jmi = jm.add(new JRadioButtonMenuItem("Name"));
        jmi.setMnemonic('N');
        bg.add(jmi);
        jmi.addActionListener(e ->{
            list.sortByName();
            repaint();
            });

//Sort Menu -> Age MenuItem
        jmi = jm.add(new JRadioButtonMenuItem("Age"));
        jmi.setMnemonic('A');
        bg.add(jmi);
        jmi.addActionListener(e ->{
            list.sortByAge();
            repaint();
        });

//Sort Menu -> Income MenuItem
        jmi = jm.add(new JRadioButtonMenuItem("Income"));
        jmi.setMnemonic('I');
        bg.add(jmi);
        jmi.addActionListener(e ->{
            list.sortByIncome();
            repaint();
            });

//Tools Menu
        jm = jmb.add(new JMenu("Tools"));

//Tools Menu -> Set Font Color MenuItem
        jmi = jm.add(new JMenuItem("Set Font Color", 'F'));
        jmi.addActionListener(e->{
            Color c = JColorChooser.showDialog(EmployeeApp.this, "Choose Font Color", ep.getForeground());
            if (c!=null) {
                ep.setForeground(c);
                repaint();
            }
        });

//Tools Menu -> Set Background Color MenuItem
        jmi = jm.add(new JMenuItem("Set Background Color", 'B'));
        jmi.addActionListener(e->{
            Color c = JColorChooser.showDialog(EmployeeApp.this, "Choose Font Color", ep.getBackground());
            if (c!=null) {
                ep.setBackground(c);
                repaint();
            }
        });

//Help Menu
        jm = jmb.add(new JMenu("Help"));

//Help Menu -> About MenuItem
        jmi = jm.add(new JMenuItem("About",'A'));
        jmi.addActionListener( e -> JOptionPane.showMessageDialog
                (EmployeeApp.this, new JLabel("<html><big><center>"
                                                + "Employee Application v0.2<br>"
                                                + "Copyright \u00a9 2017 Rio Felici<br>"
                                                + "All Rights Reserved.")));


        this.add(ep);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(800, 800, 800, 800);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new EmployeeApp();
    }
}