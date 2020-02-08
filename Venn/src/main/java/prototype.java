import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class prototype {

	private JFrame frame;
	private final JButton btnHelp = new JButton("Help");
	private JTextField txtLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prototype window = new prototype();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public prototype() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
			}
		});
		frame.setBounds(100, 100, 726, 667);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(30, 566, 513, 41);
		frame.getContentPane().add(panel);
		
		JButton btnFile = new JButton("File");
		panel.add(btnFile);
		panel.add(btnHelp);
		
		JButton btnShare = new JButton("Share");
		panel.add(btnShare);
		
		JButton btnNewButton = new JButton("Voice");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(30, 25, 513, 130);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labelPanel.setBounds(0, 0, 513, 91);
		panel_1.add(labelPanel);
		
		JLabel lblDragable = new JLabel("drag-able");
		labelPanel.add(lblDragable);
		
		JLabel label = new JLabel("");
		labelPanel.add(label);
		
		JButton btnAddLabel = new JButton("+ Label");
		btnAddLabel.setBounds(326, 95, 93, 35);
		panel_1.add(btnAddLabel);
		
		txtLabel = new JTextField();
		txtLabel.setText("Add Your Label");
		txtLabel.setBounds(10, 95, 308, 35);
		panel_1.add(txtLabel);
		txtLabel.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(427, 95, 86, 35);
		panel_1.add(btnRemove);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(30, 168, 513, 355);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 6, 198, 336);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		panel_3.add(panel_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(303, 6, 198, 336);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		panel_3.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		panel_6.setBackground(Color.PINK);
		panel_6.setBounds(210, 6, 93, 336);
		panel_3.add(panel_6);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBounds(555, 25, 141, 495);
		frame.getContentPane().add(panel_4);
		
		JButton btnShape = new JButton("Shape");
		panel_4.add(btnShape);
		
		JButton btnColor = new JButton("Color");
		panel_4.add(btnColor);
		
		JButton btnDraw = new JButton("Draw");
		panel_4.add(btnDraw);
		
		JButton btnAddImage = new JButton("Add Image");
		panel_4.add(btnAddImage);
		
		JButton btnText = new JButton("Text");
		panel_4.add(btnText);
		btnAddLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel label = new JLabel(" ");
				label.setBorder(BorderFactory.createLineBorder(Color.black));
				label.setText(" "+ txtLabel.getText()+ " ");
				labelPanel.add(label);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnShare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
