import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class Notepad implements ActionListener {
    JFrame frame;
    JTextArea area;
    JMenuBar menubar;
    Notepad(){
        frame= new JFrame("Notepad");
        frame.setSize(800,800);

        area= new JTextArea();
        frame.add(area);

        menubar= new JMenuBar();
        JMenu file= new JMenu("File");
        JMenu edit= new JMenu("Edit");

        JMenuItem open= new JMenuItem("Open");
        JMenuItem save= new JMenuItem("Save");
        JMenuItem print= new JMenuItem("Print");
        JMenuItem newfile = new JMenuItem("New");

        open.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        newfile.addActionListener(this);


        file.add(open);
        file.add(save);
        file.add(print);
        file.add(newfile);

        JMenuItem cut= new JMenuItem("Cut");
        JMenuItem copy= new JMenuItem("Copy");
        JMenuItem paste= new JMenuItem("Paste");
        JMenuItem close = new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(close);

        menubar.add(file);
        menubar.add(edit);



        frame.setJMenuBar(menubar);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
    public static void main(String arg[]){
        Notepad object= new Notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call=e.getActionCommand();
        if(call=="New")
        {
            area.setText("");
        }
        else if(call=="Cut")
        {
            area.cut();
        }
        else if(call=="Copy")
        {
            area.copy();
        }
        else if(call=="Paste")
        {
            area.paste();
        }
        else if(call=="Close")
        {
            frame.setVisible(false);
        }
        else if(call=="Save")
        {
            JFileChooser filechooser = new JFileChooser("C");
            int ans= filechooser.showOpenDialog(null);
            if(ans==filechooser.APPROVE_OPTION);
            {
                File file= new File(filechooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writter= null;
                try{
                    writter = new BufferedWriter(new FileWriter(file, false));
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
                try{
                    writter.write(area.getText());
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
                try{
                    writter.flush();
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
                try{
                    writter.close();
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }


            }
        }
        else if(call=="Open")
        {
            JFileChooser fileChooser1 = new JFileChooser("c");
            int ans=fileChooser1.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION)
            {
                File file= new File(fileChooser1.getSelectedFile().getAbsolutePath());
                try{
                    String s1="", s2="";
                    BufferedReader bufferedReader= new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null)
                    {
                        s2+=s1+"\n";
                    }
                    area.setText(s2);
                }
                catch(IOException ex)
                {
                    throw new RuntimeException();
                }
            }

        }
        else if(call=="Print")
        {
            try{
                area.print();
            }
            catch (PrinterException ex)
            {
                throw new RuntimeException();
            }
        }
    }
}
