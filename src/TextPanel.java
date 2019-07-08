import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel{
	private JTextArea text;
	public TextPanel() {
		
		text=new JTextArea();
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(text),BorderLayout.CENTER);
	}
	
	public JTextArea getText() {
		return text;
	}
}
