import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainFrame extends JFrame implements ActionListener{
	
	private TextPanel textArea;
	private JFileChooser fileChooser;
	
	public MainFrame() {
		super("TextEditor");
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}

		
		setJMenuBar(createMenuBar());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,400));
		setSize(600,600);
		setResizable(true);
		setLayout(new BorderLayout());
		
		textArea=new TextPanel();
		add(textArea,BorderLayout.CENTER);
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar=new JMenuBar();
		
		JMenu fileMenu=new JMenu("File");
		
		JMenuItem newText=new JMenuItem("New");
		JMenuItem saveText=new JMenuItem("Save");
		JMenuItem openText=new JMenuItem("Open");
		JMenuItem printText=new JMenuItem("Print");
		JMenuItem exit=new JMenuItem("Exit");
		
		JMenu editMenu=new JMenu("Edit");
		
		JMenuItem cut=new JMenuItem("Cut");
		JMenuItem copy=new JMenuItem("Copy");
		JMenuItem paste=new JMenuItem("Paste");
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		editMenu.setMnemonic(KeyEvent.VK_E);
		newText.setMnemonic(KeyEvent.VK_N);
		saveText.setMnemonic(KeyEvent.VK_S);
		openText.setMnemonic(KeyEvent.VK_O);
		printText.setMnemonic(KeyEvent.VK_P);
		cut.setMnemonic(KeyEvent.VK_X);
		copy.setMnemonic(KeyEvent.VK_C);
		paste.setMnemonic(KeyEvent.VK_T);
		exit.setMnemonic(KeyEvent.VK_I);
		
		
		newText.addActionListener(this);
		saveText.addActionListener(this);
		openText.addActionListener(this);
		printText.addActionListener(this);
		exit.addActionListener(this);
		fileMenu.add(newText);
		fileMenu.add(saveText);
		fileMenu.add(openText);
		fileMenu.add(printText);
		fileMenu.addSeparator();
		fileMenu.add(exit);
		
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		editMenu.add(cut);
		editMenu.add(copy);
		editMenu.add(paste);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		JTextArea text=textArea.getText();
		if(s.equals("Cut")) {
			text.cut();
		}
		else if(s.equals("Copy")) {
			text.copy();
		}
		else if(s.equals("Paste")){
			text.paste();
		}
		else if (s.equals("Save")) { 
            JFileChooser j = new JFileChooser("f:"); 
  
            int r = j.showSaveDialog(null); 
  
            if (r == JFileChooser.APPROVE_OPTION) { 
  
                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
  
                try { 
                    FileWriter wr = new FileWriter(fi, false); 
  
                    BufferedWriter w = new BufferedWriter(wr); 
  
                    w.write(text.getText()); 
  
                    w.flush(); 
                    w.close(); 
                } 
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(MainFrame.this, evt.getMessage()); 
                } 
            } 
            else
                JOptionPane.showMessageDialog(MainFrame.this, "the user cancelled the operation"); 
        } 
		else if (s.equals("Print")) { 
            try { 
                text.print(); 
            } 
            catch (Exception evt) { 
                JOptionPane.showMessageDialog(MainFrame.this, evt.getMessage()); 
            } 
        } 
        else if (s.equals("Open")) { 
            JFileChooser j = new JFileChooser("f:"); 
  
            int r = j.showOpenDialog(null); 
  
            if (r == JFileChooser.APPROVE_OPTION) { 
                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
  
                try { 
                    String s1 = "", sl = ""; 
  
                    FileReader fr = new FileReader(fi); 
  
                    BufferedReader br = new BufferedReader(fr); 
  
                    sl = br.readLine(); 
  
                    while ((s1 = br.readLine()) != null) { 
                        sl = sl + "\n" + s1; 
                    } 
  
                    text.setText(sl); 
                } 
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(MainFrame.this, evt.getMessage()); 
                } 
            } 
            else
                JOptionPane.showMessageDialog(MainFrame.this, "the user cancelled the operation"); 
        } 
        else if (s.equals("New")) { 
            text.setText(""); 
        } 
        else if(s.equals("Exit")) {
        	int action= JOptionPane.showConfirmDialog(MainFrame.this,"Do you wanna quit?","Confirm Exit",JOptionPane.OK_CANCEL_OPTION);
			if(action==JOptionPane.OK_OPTION) {
				System.exit(0); 
			}
        }
	}
}
