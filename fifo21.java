package database;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RootPaneContainer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

//import sun.jvm.hotspot.debugger.posix.elf.ELFProgramHeader;

//import com.sun.tools.javac.model.AnnotationProxyMaker;

//import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.GridLayout;

import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Panel;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Fifo21 
{
	private JFrame frmFifo; private DefaultTableModel taskTableModel; private DefaultTableModel inputTableModel; private DefaultTableModel chartTableModel;
	private DefaultTableModel outputTableModel; private JTable chartTable; private JComboBox<Integer> comboBox; private JComboBox<String> comboBoxPolicy;
	private JLabel lblinOrder; private JTable taskTable; private JTable inputTable; private JScrollPane scrollPane; private JScrollPane scrollPane_1;
	private JPanel simPanel; private JLayeredPane layeredPane; private JButton btnSetNumTasks; private JLabel lblNewLabel_1; private Panel startbtnpanel;
	private JPanel startMenu; private JButton btnStart; private JButton btnSetPolicy; private JScrollPane scrollPane_simTable; private JScrollPane scrollPane_2;
	private JButton btnGenerate; private JLabel lblNewLabel_2; private JTable outputTable; private JTextField avgTurnaroundTimeOutputField;
	private JTextField avgResponseTimeOutputField; private JButton btnReset; private JLabel lblNewLabel_4;
	
	private boolean fifoOrSJFMode; private int numTasks;
	private Integer taskAStartTime; private Integer taskBStartTime; private Integer taskCStartTime; private Integer taskDStartTime; private Integer taskEStartTime;
	private Integer taskADuration; private Integer taskBDuration; private Integer taskCDuration; private Integer taskDDuration; private Integer taskEDuration;
	private int taskAOrder; private int taskBOrder; private int taskCOrder; private int taskDOrder; private int taskEOrder; private int cycleNumber;
	private Integer taskATurnaroundTime; private Integer taskBTurnaroundTime; private Integer taskCTurnaroundTime; private Integer taskDTurnaroundTime;
	private Integer taskETurnaroundTime; private Integer taskAResponseTime; private Integer taskBResponseTime; private Integer taskCResponseTime;
	private Integer taskDResponseTime; private Integer taskEResponseTime;
	private Integer taskACounter; private Integer taskBCounter; private Integer taskCCounter; private Integer taskDCounter; private Integer taskECounter;
	private Float averageTurnAroundTime; private Float averageResponseTime; private String listTaskNames;
	
	private ArrayList<task> tasksInOrder = new ArrayList<task>();
	private task taskA = new task(0, 0, 0, 0, 'A', 0, 0); private task taskB = new task(0, 0, 0, 0, 'B', 0, 0); private task taskC = new task(0, 0, 0, 0, 'C', 0, 0);
	private task taskD = new task(0, 0, 0, 0, 'D', 0, 0); private task taskE = new task(0, 0, 0, 0, 'E', 0, 0); private Integer orderIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fifo21 window = new Fifo21();
					window.frmFifo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Fifo21() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		listTaskNames = "ABCDE";
		fifoOrSJFMode = false;
		cycleNumber = 0; orderIndex = 0;
		taskATurnaroundTime = 0; taskBTurnaroundTime = 0; taskCTurnaroundTime = 0; taskDTurnaroundTime = 0; taskETurnaroundTime = 0; averageTurnAroundTime = 0.000f;
		taskAResponseTime = 0; taskBResponseTime = 0; taskCResponseTime = 0; taskDResponseTime = 0; taskEResponseTime = 0; averageResponseTime = 0.000f;
		taskACounter = taskADuration; taskBCounter = taskBDuration; taskCCounter = taskCDuration; taskDCounter = taskDDuration; taskECounter = taskEDuration;
		
		tasksInOrder.add(taskA); tasksInOrder.add(taskB); tasksInOrder.add(taskC); tasksInOrder.add(taskD); tasksInOrder.add(taskE);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		DefaultTableCellRenderer colorRendererBLUE = new DefaultTableCellRenderer();
		{
			colorRendererBLUE.setForeground(Color.BLUE);
			colorRendererBLUE.setHorizontalAlignment(SwingConstants.CENTER);
			colorRendererBLUE.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		DefaultTableCellRenderer colorRendererRED = new DefaultTableCellRenderer();
		{
			colorRendererRED.setForeground(Color.RED);
			colorRendererRED.setHorizontalAlignment(SwingConstants.CENTER);
			colorRendererRED.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		
		DefaultTableCellRenderer colorRendererGREEN = new DefaultTableCellRenderer();
		{
			colorRendererGREEN.setForeground(Color.GREEN);
			colorRendererGREEN.setHorizontalAlignment(SwingConstants.CENTER);
			colorRendererGREEN.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		
		DefaultTableCellRenderer colorRendererPINK = new DefaultTableCellRenderer();
		{
			colorRendererPINK.setForeground(Color.PINK);
			colorRendererPINK.setHorizontalAlignment(SwingConstants.CENTER);
			colorRendererPINK.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		
		DefaultTableCellRenderer colorRendererORANGE = new DefaultTableCellRenderer();
		{
			colorRendererORANGE.setForeground(Color.ORANGE);
			colorRendererORANGE.setHorizontalAlignment(SwingConstants.CENTER);
			colorRendererORANGE.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		
		frmFifo = new JFrame();
		frmFifo.setResizable(false);
		frmFifo.setTitle("FIFO 21");
		frmFifo.setBounds(0, 0, 1260, 650);
		frmFifo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFifo.getContentPane().setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1255, 615);
		frmFifo.getContentPane().add(layeredPane);
		layeredPane.setLayout(null);
		
		simPanel = new JPanel();
		layeredPane.setLayer(simPanel, 1);
		simPanel.setBounds(10, 10, 1232, 594);
		layeredPane.add(simPanel);
		simPanel.setLayout(null);
		
		taskTable = new JTable();
		taskTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		taskTable.setEnabled(false);
		taskTable.setFillsViewportHeight(true);
		taskTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		taskTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task"
			}
		) {
			Class[] columnTypes = new Class[] {
				Character.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		taskTable.getColumnModel().getColumn(0).setResizable(false);
		taskTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		taskTable.getColumnModel().getColumn(0).setMinWidth(70);
		taskTable.getColumnModel().getColumn(0).setMaxWidth(70);
		taskTable.getTableHeader().setDefaultRenderer(centerRenderer);
		taskTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		taskTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		taskTable.setRowHeight(25);
		taskTable.setBounds(227, 50, 70, 154);
		simPanel.add(taskTable);
		taskTableModel = (DefaultTableModel) taskTable.getModel();
		taskTable.setEnabled(false);
		
		btnSetNumTasks = new JButton("set");
		btnSetNumTasks.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
						if (taskTableModel.getRowCount() > 0)
						{
							for (int i = taskTableModel.getRowCount() - 1; i >= 0; i--)
							{
								taskTableModel.removeRow(i);
							}
						}
						if (inputTableModel.getRowCount() > 0)
						{
							for (int i = inputTableModel.getRowCount() - 1; i >= 0; i--)
							{
								inputTableModel.removeRow(i);
							}
						}	
					for (int i = 0; i < (int) comboBox.getSelectedItem(); i++)
					{
						taskTableModel.addRow(new Object[] {(Character) listTaskNames.charAt(i)});
						inputTableModel.addRow(new Object[] {null,null});
					}
				btnGenerate.setEnabled(true);
			}
		});
		btnSetNumTasks.setBounds(258, 96, 57, 21);
		simPanel.add(btnSetNumTasks);
		btnSetNumTasks.setEnabled(false);
		
		comboBox = new JComboBox<Integer>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setBounds(199, 90, 49, 28);
		simPanel.add(comboBox);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		comboBox.addItem(5);
		comboBox.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("Number of Tasks:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(30, 90, 164, 29);
		simPanel.add(lblNewLabel);
		
		
		scrollPane = new JScrollPane(taskTable);
		scrollPane.setBounds(30, 143, 70, 154);
		simPanel.add(scrollPane);
		scrollPane.setEnabled(false);
		
		inputTable = new JTable();
		inputTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inputTable.setColumnSelectionAllowed(true);
		inputTable.setCellSelectionEnabled(true);
		inputTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inputTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Arrival", "Task Length"
			}
			) {
			Class[] columnTypes = new Class[] {
					Integer.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
		});
		inputTable.getColumnModel().getColumn(0).setResizable(false);
		inputTable.getColumnModel().getColumn(0).setPreferredWidth(90);
		inputTable.getColumnModel().getColumn(0).setMinWidth(90);
		inputTable.getColumnModel().getColumn(0).setMaxWidth(90);
		inputTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		inputTable.getColumnModel().getColumn(1).setMinWidth(110);
		inputTable.getColumnModel().getColumn(1).setMaxWidth(110);
		inputTable.setFillsViewportHeight(true);
		inputTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		inputTable.getTableHeader().setDefaultRenderer(centerRenderer);
		inputTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		inputTable.setRowHeight(25);
		simPanel.add(inputTable);
		inputTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		inputTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		inputTableModel = (DefaultTableModel) inputTable.getModel();
		inputTable.setEnabled(false);
		
		scrollPane_1 = new JScrollPane(inputTable);
		scrollPane_1.setBounds(98, 143, 200, 154);
		simPanel.add(scrollPane_1);
		scrollPane.setEnabled(false);
		
		JLabel lblSimMode = new JLabel("Policy:");
		lblSimMode.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSimMode.setBounds(30, 32, 82, 29);
		simPanel.add(lblSimMode);
		
		comboBoxPolicy = new JComboBox<String>();
		comboBoxPolicy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxPolicy.setBounds(98, 32, 68, 28);
		simPanel.add(comboBoxPolicy);
		comboBoxPolicy.addItem("FIFO");
		comboBoxPolicy.addItem("SJF");
		comboBoxPolicy.setEnabled(false);
		
		btnSetPolicy = new JButton("set");
		btnSetPolicy.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
					if(comboBoxPolicy.getSelectedIndex() == 0)
					{
						fifoOrSJFMode = false;
					}
					else 
					{
						fifoOrSJFMode = true;
					}
				btnSetNumTasks.setEnabled(true);
				comboBox.setEnabled(true);
				taskTable.setEnabled(true);
				inputTable.setEnabled(true);
			}
		});
		btnSetPolicy.setBounds(176, 39, 57, 21);
		simPanel.add(btnSetPolicy);
		btnSetPolicy.setEnabled(false);
		
		scrollPane_simTable = new JScrollPane(chartTable);
		scrollPane_simTable.setBounds(466, 38, 345, 538);
		simPanel.add(scrollPane_simTable);
		
		chartTable = new JTable();
		chartTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cycle", "A", "B", "C", "D", "E"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Character.class, Character.class, Character.class, Character.class, Character.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		chartTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		chartTable.getColumnModel().getColumn(0).setMinWidth(80);
		chartTable.getColumnModel().getColumn(0).setMaxWidth(80);
		chartTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		chartTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		chartTable.getColumnModel().getColumn(1).setMinWidth(50);
		chartTable.getColumnModel().getColumn(1).setMaxWidth(50);
		chartTable.getColumnModel().getColumn(1).setCellRenderer(colorRendererBLUE);
		chartTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		chartTable.getColumnModel().getColumn(2).setMinWidth(50);
		chartTable.getColumnModel().getColumn(2).setMaxWidth(50);
		chartTable.getColumnModel().getColumn(2).setCellRenderer(colorRendererRED);
		chartTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		chartTable.getColumnModel().getColumn(3).setMinWidth(50);
		chartTable.getColumnModel().getColumn(3).setMaxWidth(50);
		chartTable.getColumnModel().getColumn(3).setCellRenderer(colorRendererGREEN);
		chartTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		chartTable.getColumnModel().getColumn(4).setMinWidth(50);
		chartTable.getColumnModel().getColumn(4).setMaxWidth(50);
		chartTable.getColumnModel().getColumn(4).setMaxWidth(50);
		chartTable.getColumnModel().getColumn(4).setCellRenderer(colorRendererPINK);
		chartTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		chartTable.getColumnModel().getColumn(5).setMinWidth(50);
		chartTable.getColumnModel().getColumn(5).setMaxWidth(50);
		chartTable.getColumnModel().getColumn(5).setCellRenderer(colorRendererORANGE);
		chartTable.getTableHeader().setDefaultRenderer(centerRenderer);
		chartTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		chartTable.setFont(new Font("Tahoma", Font.BOLD, 18));
		chartTable.setRowHeight(25);
		chartTable.setFillsViewportHeight(true);
		scrollPane_simTable.setViewportView(chartTable);
		chartTableModel = (DefaultTableModel) chartTable.getModel();
		
		btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				btnReset.setEnabled(true);
				try //override default contingency for nullPointerException. Make the chastising system handle it instead.
				{
				numTasks = (int) comboBox.getSelectedItem();
				//fifoOrSJFMode = true;
						if (numTasks == 2)
						{
							tasksInOrder.get(0).setArrivalTime((Integer) inputTableModel.getValueAt(0, 0)); //start times
							tasksInOrder.get(1).setArrivalTime((Integer) inputTableModel.getValueAt(1, 0));
							
							tasksInOrder.get(0).setTaskDuration((Integer) inputTableModel.getValueAt(0, 1)); //durations
							tasksInOrder.get(1).setTaskDuration((Integer) inputTableModel.getValueAt(1, 1));
							
							tasksInOrder.get(0).setCounter(tasksInOrder.get(0).getTaskDuration()); //counters
							tasksInOrder.get(1).setCounter(tasksInOrder.get(1).getTaskDuration());
							
							if(fifoOrSJFMode == true)
							{
								if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()) //If 1 = 2...
								{
									sort(0, 1);
								}
								else
								{
									JOptionPane.showMessageDialog(frmFifo, "WHAAAA? You created a case that the author did not forsee?! Impossible!", "ERROR: AUTHOR MADE AN OOPSIE", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
							
									if (tasksInOrder.get(0).getArrivalTime() > 1000 || tasksInOrder.get(1).getArrivalTime() > 1000
											|| ((tasksInOrder.get(0).getTaskDuration() + tasksInOrder.get(1).getTaskDuration()) >= 1000))
									{
										JOptionPane.showMessageDialog(frmFifo, "I'm sorry, but I have set the graph capacity to 1000 entries to remove the risk of overload\nPlease make your numbers less than 1000.", "ERROR: INPUT TOO LARGE", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(0).getArrivalTime() < 0 || tasksInOrder.get(0).getTaskDuration() < 0 ||
											tasksInOrder.get(1).getArrivalTime() < 0 || tasksInOrder.get(1).getTaskDuration() < 0)
									{
										JOptionPane.showMessageDialog(frmFifo, "No item in the input table may be less than zero.\n\"Why,\" you ask? Well, that doesn't really make any sense :\\", "ERROR", JOptionPane.ERROR_MESSAGE);
										resetSim();
										return;
									}
									if (tasksInOrder.get(0).getTaskDuration() == 0 || tasksInOrder.get(1).getTaskDuration() == 0)
									{
										JOptionPane.showMessageDialog(frmFifo, "No task may have a length of zero.\nHow am I supposed to simulate nothing? :\\", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(1).getArrivalTime() < tasksInOrder.get(0).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(1).getCharacter() + " cannot be before Task " + tasksInOrder.get(0).getCharacter() + ".\nThe order of the tasks in the input table must be A,B.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(0).getArrivalTime() > 0) //If none of the tasks started yet...
									{
										for (int i = 0; i < tasksInOrder.get(0).getArrivalTime(); i++) 
										{
											chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null}); //stall
											tasksInOrder.get(0).setExitTime(tasksInOrder.get(0).getExitTime() + 1);
											tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
										}
									}
								while ((tasksInOrder.get(0).getCounter() > 0 || tasksInOrder.get(1).getCounter() > 0)
										&& chartTableModel.getRowCount() <= 1000) //Make sure that the size of the table never exceeds 1000; 
								{
										while (tasksInOrder.get(0).getCounter() > 0) //Task 1
										{
												if(cycleNumber > tasksInOrder.get(1).getArrivalTime()) //If Task 1 is overlapping Task 2...
												{
													tasksInOrder.get(1).setStartTime(tasksInOrder.get(1).getStartTime() + 1);
												}
											createRow(0);
											tasksInOrder.get(0).setExitTime(tasksInOrder.get(0).getExitTime() + 1);
											tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
											tasksInOrder.get(0).setCounter(tasksInOrder.get(0).getCounter() - 1); //decrement Task 1's counter
										}
										tasksInOrder.get(0).setFinished(true);
										
											if (cycleNumber < tasksInOrder.get(1).getArrivalTime()) //If the next task wasn't supposed to start this soon...
											{
												while(cycleNumber < tasksInOrder.get(1).getArrivalTime())
												{
													chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null}); //stall
													tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
												}
											}
												
										while (tasksInOrder.get(1).getCounter() > 0) //Task 2
										{
											createRow(1);
											tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
											tasksInOrder.get(1).setCounter(tasksInOrder.get(1).getCounter() - 1);
										}
										tasksInOrder.get(1).setFinished(true);
								}
								tasksInOrder.get(0).setResponseTime(tasksInOrder.get(0).getStartTime());
								tasksInOrder.get(1).setResponseTime(tasksInOrder.get(1).getStartTime() + 1);
								
								tasksInOrder.get(0).setTurnaroundTime((tasksInOrder.get(0).getExitTime() - tasksInOrder.get(0).getArrivalTime()));
								tasksInOrder.get(1).setTurnaroundTime((tasksInOrder.get(1).getExitTime() - tasksInOrder.get(1).getArrivalTime()));
								
									if(chartTableModel.getRowCount() >= 1000)
									{
										JOptionPane.showMessageDialog(frmFifo, "I'm sorry, but I have set the graph capacity to 1000 entries to remove the risk of overload\nPlease make your numbers a bit smaller.", "ERROR: OVER CAPACITY", JOptionPane.ERROR_MESSAGE);
										return;
									}
								//Generate the output table.
									outputTableModel.addRow(new Object[] {tasksInOrder.get(0).getCharacter(),tasksInOrder.get(0).getTurnaroundTime(),
											tasksInOrder.get(0).getResponseTime()});
									outputTableModel.addRow(new Object[] {tasksInOrder.get(1).getCharacter(),tasksInOrder.get(1).getTurnaroundTime(),
											tasksInOrder.get(1).getResponseTime()});
								
								//Calculate average turnaround time and average response time.
									averageTurnAroundTime = ((tasksInOrder.get(0).getTurnaroundTime().floatValue()
											+ tasksInOrder.get(1).getTurnaroundTime().floatValue()) / 2.000f);
									
									avgTurnaroundTimeOutputField.setText(averageTurnAroundTime.toString());
									
									averageResponseTime = ((tasksInOrder.get(0).getResponseTime().floatValue()
											+ tasksInOrder.get(1).getResponseTime().floatValue()) / 2.000f);
									
									avgResponseTimeOutputField.setText(averageResponseTime.toString());
						}
						else if (numTasks == 3)
						{
							tasksInOrder.get(0).setArrivalTime((Integer) inputTableModel.getValueAt(0, 0)); //start times
							tasksInOrder.get(1).setArrivalTime((Integer) inputTableModel.getValueAt(1, 0));
							tasksInOrder.get(2).setArrivalTime((Integer) inputTableModel.getValueAt(2, 0));
							
							tasksInOrder.get(0).setTaskDuration((Integer) inputTableModel.getValueAt(0, 1)); //durations
							tasksInOrder.get(1).setTaskDuration((Integer) inputTableModel.getValueAt(1, 1));
							tasksInOrder.get(2).setTaskDuration((Integer) inputTableModel.getValueAt(2, 1));
							
							tasksInOrder.get(0).setCounter(tasksInOrder.get(0).getTaskDuration()); //counters
							tasksInOrder.get(1).setCounter(tasksInOrder.get(1).getTaskDuration());
							tasksInOrder.get(2).setCounter(tasksInOrder.get(2).getTaskDuration());
							
							if(fifoOrSJFMode == true)
							{
								if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()) //If 1 = 2, but 2 != 3...
								{
									sort(0, 1);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()) //If 1 = 2 = 3...
								{
									sort(0, 2);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()) //If 1 != 2, but 2 = 3...
								{
									sort(1, 2);
								}
								else
								{
									JOptionPane.showMessageDialog(frmFifo, "WHAAAA? You created a case that the author did not forsee?! Impossible!", "ERROR: AUTHOR MADE AN OOPSIE", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
							
									if (tasksInOrder.get(0).getArrivalTime() < 0 || tasksInOrder.get(0).getTaskDuration() < 0 || tasksInOrder.get(1).getArrivalTime() < 0 
											|| tasksInOrder.get(1).getTaskDuration() < 0 || tasksInOrder.get(2).getArrivalTime() < 0 || tasksInOrder.get(2).getTaskDuration() < 0)
									{
										JOptionPane.showMessageDialog(frmFifo, "No item in the input table may be less than zero.\n\"Why,\" you ask? Well, that doesn't really make any sense :\\", "ERROR: DID SOMETHING DUMB", JOptionPane.ERROR_MESSAGE);
										resetSim();
										return;
									}
									if (tasksInOrder.get(0).getTaskDuration() == 0 || tasksInOrder.get(1).getTaskDuration() == 0 || tasksInOrder.get(2).getTaskDuration() == 0)
									{
										JOptionPane.showMessageDialog(frmFifo, "No task may have a length of zero.\nHow am I supposed to simulate nothing? :\\", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(1).getArrivalTime() < tasksInOrder.get(0).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(1).getCharacter() + " cannot be before Task " + tasksInOrder.get(0).getCharacter() + ".\nThe order of the tasks in the input table must be A,B,C.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(2).getArrivalTime() < tasksInOrder.get(1).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(2).getCharacter() + " cannot be before Task " + tasksInOrder.get(1).getCharacter() + ".\nThe order of the tasks in the input table must be A,B,C.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(2).getArrivalTime() < tasksInOrder.get(0).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(2).getCharacter() + " cannot be before Task " + tasksInOrder.get(0).getCharacter() + ".\nThe order of the tasks in the input table must be A,B,C.", "ERROR", JOptionPane.ERROR_MESSAGE);										return;
									}
									if (tasksInOrder.get(0).getArrivalTime() > 0) //If none of the tasks started yet...
									{
										for (int i = 0; i < tasksInOrder.get(0).getArrivalTime(); i++) 
										{
											chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null}); //stall
											tasksInOrder.get(0).setExitTime(tasksInOrder.get(0).getExitTime() + 1);
											tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
											tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
										}
									}
								while ((tasksInOrder.get(0).getCounter() > 0 || tasksInOrder.get(1).getCounter() > 0 || tasksInOrder.get(1).getCounter() > 0)
											&& chartTableModel.getRowCount() <= 1000) //Make sure that the size of the table never exceeds 1000; 
								{
									while (tasksInOrder.get(0).getCounter() > 0) //Task 1
									{
											if(cycleNumber > tasksInOrder.get(1).getArrivalTime() && cycleNumber <= tasksInOrder.get(2).getArrivalTime())
												//If Task 1 is overlapping Task 2, but not Task 3...
											{
												tasksInOrder.get(1).setStartTime(tasksInOrder.get(1).getStartTime() + 1);
											}
											else if (cycleNumber > tasksInOrder.get(1).getArrivalTime() && cycleNumber > tasksInOrder.get(2).getArrivalTime())
												//If Task 1 is overlapping BOTH Task 2 and Task 3...
											{
												tasksInOrder.get(1).setStartTime(tasksInOrder.get(1).getStartTime() + 1);
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
											}
											else //If neither is occurring...
											{
												//carry on
											}
										createRow(0);
										tasksInOrder.get(0).setExitTime(tasksInOrder.get(0).getExitTime() + 1);
										tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
										tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
										tasksInOrder.get(0).setCounter(tasksInOrder.get(0).getCounter() - 1);
									}
									tasksInOrder.get(0).setFinished(true);
									tasksInOrder.get(1).setResponseTime(tasksInOrder.get(1).getStartTime() - tasksInOrder.get(1).getArrivalTime());
									tasksInOrder.get(2).setResponseTime(tasksInOrder.get(2).getStartTime() - tasksInOrder.get(2).getArrivalTime());
									
										if (cycleNumber < tasksInOrder.get(1).getArrivalTime()) //If the next task wasn't supposed to start this soon...
										{
											while(cycleNumber < tasksInOrder.get(1).getArrivalTime())
											{
												chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null});
												tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
												tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
											}
										}
									
										while (tasksInOrder.get(1).getCounter() > 0) //Task 2
										{
											if(cycleNumber > tasksInOrder.get(2).getArrivalTime()) //If Task 2 is overlapping Task 3...
											{
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
											}
										createRow(1);
										tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
										tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
										tasksInOrder.get(1).setCounter(tasksInOrder.get(1).getCounter() - 1);
										}
										tasksInOrder.get(1).setFinished(true);
									
										if (cycleNumber < tasksInOrder.get(2).getArrivalTime()) //If the next task wasn't supposed to start this soon...
										{
											while(cycleNumber < tasksInOrder.get(2).getArrivalTime())
											{
												chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null});
												tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
											}
										}
										
									while (tasksInOrder.get(2).getCounter() > 0) //Task 3
									{
										createRow(2);
										tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
										tasksInOrder.get(2).setCounter(tasksInOrder.get(2).getCounter() - 1);
									}
									tasksInOrder.get(2).setFinished(true);
								}
								tasksInOrder.get(0).setResponseTime(tasksInOrder.get(0).getStartTime());
								tasksInOrder.get(1).setResponseTime(tasksInOrder.get(1).getStartTime() + 1);
								tasksInOrder.get(2).setResponseTime(tasksInOrder.get(2).getStartTime() + 1);
								
								tasksInOrder.get(0).setTurnaroundTime((tasksInOrder.get(0).getExitTime() - tasksInOrder.get(0).getArrivalTime()));
								tasksInOrder.get(1).setTurnaroundTime((tasksInOrder.get(1).getExitTime() - tasksInOrder.get(1).getArrivalTime()));
								tasksInOrder.get(2).setTurnaroundTime((tasksInOrder.get(2).getExitTime() - tasksInOrder.get(2).getArrivalTime()));
								
								if(chartTableModel.getRowCount() >= 1000)
								{
									JOptionPane.showMessageDialog(frmFifo, "I'm sorry, but I have set the graph capacity to 1000 entries to remove the risk of overload\nPlease make your numbers a bit smaller.", "ERROR: OVER CAPACITY", JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								outputTableModel.addRow(new Object[] {tasksInOrder.get(0).getCharacter(),tasksInOrder.get(0).getTurnaroundTime(),
										tasksInOrder.get(0).getResponseTime()});
								outputTableModel.addRow(new Object[] {tasksInOrder.get(1).getCharacter(),tasksInOrder.get(1).getTurnaroundTime(),
										tasksInOrder.get(1).getResponseTime()});
								outputTableModel.addRow(new Object[] {tasksInOrder.get(2).getCharacter(),tasksInOrder.get(2).getTurnaroundTime(),
										tasksInOrder.get(2).getResponseTime()});
							
								averageTurnAroundTime = ((tasksInOrder.get(0).getTurnaroundTime().floatValue() + tasksInOrder.get(1).getTurnaroundTime().floatValue()
										+ tasksInOrder.get(2).getTurnaroundTime().floatValue()) / 3.000f);
								
								avgTurnaroundTimeOutputField.setText(averageTurnAroundTime.toString());
								
								averageResponseTime = ((tasksInOrder.get(0).getResponseTime().floatValue() + tasksInOrder.get(1).getResponseTime().floatValue()
										+ tasksInOrder.get(2).getResponseTime().floatValue()) / 3.000f);
								
								avgResponseTimeOutputField.setText(averageResponseTime.toString());
						}
						else if (numTasks == 4)
						{
							tasksInOrder.get(0).setArrivalTime((Integer) inputTableModel.getValueAt(0, 0)); //start times
							tasksInOrder.get(1).setArrivalTime((Integer) inputTableModel.getValueAt(1, 0));
							tasksInOrder.get(2).setArrivalTime((Integer) inputTableModel.getValueAt(2, 0));
							tasksInOrder.get(3).setArrivalTime((Integer) inputTableModel.getValueAt(3, 0));
							
							tasksInOrder.get(0).setTaskDuration((Integer) inputTableModel.getValueAt(0, 1)); //durations
							tasksInOrder.get(1).setTaskDuration((Integer) inputTableModel.getValueAt(1, 1));
							tasksInOrder.get(2).setTaskDuration((Integer) inputTableModel.getValueAt(2, 1));
							tasksInOrder.get(3).setTaskDuration((Integer) inputTableModel.getValueAt(3, 1));
							
							tasksInOrder.get(0).setCounter(tasksInOrder.get(0).getTaskDuration()); //counters
							tasksInOrder.get(1).setCounter(tasksInOrder.get(1).getTaskDuration());
							tasksInOrder.get(2).setCounter(tasksInOrder.get(2).getTaskDuration());
							tasksInOrder.get(3).setCounter(tasksInOrder.get(3).getTaskDuration());
							
							if(fifoOrSJFMode == true)
							{
								if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() != (int) tasksInOrder.get(3).getArrivalTime()) //If only 1 = 2...
								{
									sort(0, 1);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() != (int) tasksInOrder.get(3).getArrivalTime()) //If 1 = 2 = 3, but 3 != 4...
								{
									sort(0, 2);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() != (int) tasksInOrder.get(3).getArrivalTime()) //If 1 != 2, 2 = 3, 3 != 4...
								{
									sort(1, 2);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int)tasksInOrder.get(3).getArrivalTime()) //If 1 != 2, but 2 = 3 = 4...
								{
									sort(1, 3);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()) //If only 3 = 4...
								{
									sort(2, 3);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()) //If 1 = 2 = 3 = 4...
								{
									sort(0, 3);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()) //If 1 = 2 and 3 = 4, but 2 != 3...
								{
									sort(0, 1);
									sort(2, 3);
								}
								else
								{
									JOptionPane.showMessageDialog(frmFifo, "WHAAAA? You created a case that the author did not forsee?! Impossible!", "ERROR: AUTHOR MADE AN OOPSIE", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
							
									if (tasksInOrder.get(0).getArrivalTime() > 1000 || tasksInOrder.get(1).getArrivalTime() > 1000
										|| (tasksInOrder.get(0).getTaskDuration() + tasksInOrder.get(1).getTaskDuration()
										+ tasksInOrder.get(2).getTaskDuration() + tasksInOrder.get(3).getTaskDuration()) >= 1000)
									{
										JOptionPane.showMessageDialog(frmFifo, "I'm sorry, but I have set the graph capacity to 1000 entries to remove the risk of overload\nPlease make your numbers less than 1000.", "ERROR: INPUT TOO LARGE", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(0).getArrivalTime() < 0 || tasksInOrder.get(0).getTaskDuration() < 0
										|| tasksInOrder.get(1).getArrivalTime() < 0 || tasksInOrder.get(1).getTaskDuration() < 0
										|| tasksInOrder.get(2).getArrivalTime() < 0 || tasksInOrder.get(2).getArrivalTime() < 0
										|| tasksInOrder.get(3).getArrivalTime() < 0 || tasksInOrder.get(3).getArrivalTime() < 0)
									{
										JOptionPane.showMessageDialog(frmFifo, "No item in the input table may be less than zero.\n\"Why\", you ask? Well, That doesn't really make any sense :\\ ", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(0).getTaskDuration() == 0 || tasksInOrder.get(1).getTaskDuration() == 0 || tasksInOrder.get(2).getTaskDuration() == 0 || tasksInOrder.get(3).getTaskDuration() == 0)
									{
										JOptionPane.showMessageDialog(frmFifo, "No task may have a length of zero.\nHow am I supposed to simulate nothing? :\\", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(1).getArrivalTime() < tasksInOrder.get(0).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(1).getCharacter() + " cannot be before Task " + tasksInOrder.get(0).getCharacter() + ".\nThe order of the tasks must be A,B,C,D.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(2).getArrivalTime() < tasksInOrder.get(1).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(2).getCharacter() + " cannot be before Task " + tasksInOrder.get(1).getCharacter() + ".\nThe order of the tasks must be A,B,C,D.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(2).getArrivalTime() < tasksInOrder.get(0).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(2).getCharacter() + " cannot be before Task " + tasksInOrder.get(0).getCharacter() + ".\nThe order of the tasks must be A,B,C,D.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(3).getArrivalTime() < tasksInOrder.get(0).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(3).getCharacter() + " cannot be before Task " + tasksInOrder.get(0).getCharacter() + ".\nThe order of the tasks must be A,B,C,D.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(3).getArrivalTime() < tasksInOrder.get(1).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(3).getCharacter() + " cannot be before Task " + tasksInOrder.get(1).getCharacter() + ".\nThe order of the tasks must be A,B,C,D.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(3).getArrivalTime() < tasksInOrder.get(2).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(3).getCharacter() + " cannot be before Task " + tasksInOrder.get(2).getCharacter() + ".\nThe order of the tasks must be A,B,C,D.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(0).getArrivalTime() > 0)
									{
										for (int i = 0; i < tasksInOrder.get(0).getArrivalTime(); i++)
										{
											chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null});
											tasksInOrder.get(0).setExitTime(tasksInOrder.get(0).getExitTime() + 1);
											tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
											tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
											tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
										}
									}
								while ((tasksInOrder.get(0).getCounter() > 0 || tasksInOrder.get(1).getCounter() > 0 || tasksInOrder.get(2).getCounter() > 0
										|| tasksInOrder.get(3).getCounter() > 0) && chartTableModel.getRowCount() <= 1000)
										//Make sure that the size of the table never exceeds 1000;
								{
									while (tasksInOrder.get(0).getCounter() > 0) //Task 1
									{
											if(cycleNumber > tasksInOrder.get(1).getArrivalTime() && cycleNumber > tasksInOrder.get(2).getArrivalTime()
													&& cycleNumber <= tasksInOrder.get(3).getArrivalTime())
												//If Task 1 is overlapping Task 2 and Task 3, but not Task 4...
											{
												tasksInOrder.get(1).setStartTime(tasksInOrder.get(1).getStartTime() + 1);
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
											}
											else if (cycleNumber > tasksInOrder.get(1).getArrivalTime() && cycleNumber > tasksInOrder.get(2).getArrivalTime()
													&& cycleNumber > tasksInOrder.get(3).getArrivalTime())
												//If Task 1 is overlapping Task 2, 3, AND 4...
											{
												tasksInOrder.get(1).setStartTime(tasksInOrder.get(1).getStartTime() + 1);
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
												tasksInOrder.get(3).setStartTime(tasksInOrder.get(3).getStartTime() + 1);
											}
											else if (cycleNumber > tasksInOrder.get(1).getArrivalTime() && cycleNumber <= tasksInOrder.get(2).getArrivalTime() && cycleNumber <= tasksInOrder.get(3).getArrivalTime())
												//If Task 1 is overlapping only Task 2...
											{
												tasksInOrder.get(1).setStartTime(tasksInOrder.get(1).getStartTime() + 1);
											}
											else //If none of these cases are occurring...
											{
												//carry on
											}
										createRow(0);
										tasksInOrder.get(0).setExitTime(tasksInOrder.get(0).getExitTime() + 1);
										tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
										tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
										tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
										tasksInOrder.get(0).setCounter(tasksInOrder.get(0).getCounter() - 1);
									}
									tasksInOrder.get(0).setFinished(true);
									
										if (cycleNumber < tasksInOrder.get(1).getArrivalTime()) //If the next task wasn't supposed to start this soon...
										{
											while(cycleNumber < tasksInOrder.get(1).getArrivalTime())
											{
												chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null});
												tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
												tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
												tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
											}
										}
									
									while (tasksInOrder.get(1).getCounter() > 0) //Task 2
									{
											if(cycleNumber > tasksInOrder.get(2).getArrivalTime() && cycleNumber <= tasksInOrder.get(3).getArrivalTime())
												//If Task 2 is overlapping Task 3, but not Task 4...
											{
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
											}
											else if(cycleNumber > tasksInOrder.get(2).getArrivalTime()) //If Task 2 is overlapping Task 3 AND Task 4...
											{
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
												tasksInOrder.get(3).setStartTime(tasksInOrder.get(3).getStartTime() + 1);
											}
											else //If neither is occurring...
											{
												//Carry on
											}
											createRow(1);
											tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
											tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
											tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
											tasksInOrder.get(1).setCounter(tasksInOrder.get(1).getCounter() - 1);
									}
									tasksInOrder.get(1).setFinished(true);
									
										if (cycleNumber < tasksInOrder.get(2).getArrivalTime()) //If the next task wasn't supposed to start this soon...
										{
											while(cycleNumber < tasksInOrder.get(2).getArrivalTime())
											{
												chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null});
												tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
												tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
											}
										}
									
									while (tasksInOrder.get(2).getCounter() > 0) //Task 3
									{
											if (cycleNumber > tasksInOrder.get(3).getArrivalTime()) //If Task 3 is overlapping Task 4...
											{
												tasksInOrder.get(3).setStartTime(tasksInOrder.get(3).getStartTime() + 1);
											}
											createRow(2);
											tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
											tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
											tasksInOrder.get(2).setCounter(tasksInOrder.get(2).getCounter() - 1);
									}
									tasksInOrder.get(2).setFinished(true);
									
										if (cycleNumber < tasksInOrder.get(3).getArrivalTime()) //If the next task wasn't supposed to start this soon...
										{
											while(cycleNumber < tasksInOrder.get(3).getArrivalTime())
											{
												chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null});
												tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
											}
										}
									
									while (tasksInOrder.get(3).getCounter() > 0) //Task 4
									{
										createRow(3);
										tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
										tasksInOrder.get(3).setCounter(tasksInOrder.get(3).getCounter() - 1);
									}
									tasksInOrder.get(3).setFinished(true);
								}
								tasksInOrder.get(0).setResponseTime(tasksInOrder.get(0).getStartTime());
								tasksInOrder.get(1).setResponseTime(tasksInOrder.get(1).getStartTime() + 1);
								tasksInOrder.get(2).setResponseTime(tasksInOrder.get(2).getStartTime() + 1);
								tasksInOrder.get(3).setResponseTime(tasksInOrder.get(3).getStartTime() + 1);
								
								tasksInOrder.get(0).setTurnaroundTime((tasksInOrder.get(0).getExitTime() - tasksInOrder.get(0).getArrivalTime()));
								tasksInOrder.get(1).setTurnaroundTime((tasksInOrder.get(1).getExitTime() - tasksInOrder.get(1).getArrivalTime()));
								tasksInOrder.get(2).setTurnaroundTime((tasksInOrder.get(2).getExitTime() - tasksInOrder.get(2).getArrivalTime()));
								tasksInOrder.get(3).setTurnaroundTime((tasksInOrder.get(3).getExitTime() - tasksInOrder.get(3).getArrivalTime()));
								
								if(chartTableModel.getRowCount() >= 1000)
								{
									JOptionPane.showMessageDialog(frmFifo, "I'm sorry, but I have set the graph capacity to 1000 entries to remove the risk of overload\nPlease make your numbers a bit smaller.", "ERROR: OVER CAPACITY", JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								outputTableModel.addRow(new Object[] {tasksInOrder.get(0).getCharacter(),tasksInOrder.get(0).getTurnaroundTime(),
										tasksInOrder.get(0).getResponseTime()});
								outputTableModel.addRow(new Object[] {tasksInOrder.get(1).getCharacter(),tasksInOrder.get(1).getTurnaroundTime(),
										tasksInOrder.get(1).getResponseTime()});
								outputTableModel.addRow(new Object[] {tasksInOrder.get(2).getCharacter(),tasksInOrder.get(2).getTurnaroundTime(),
										tasksInOrder.get(2).getResponseTime()});
								outputTableModel.addRow(new Object[] {tasksInOrder.get(3).getCharacter(),tasksInOrder.get(3).getTurnaroundTime(),
										tasksInOrder.get(3).getResponseTime()});
								
								averageTurnAroundTime = ((tasksInOrder.get(0).getTurnaroundTime().floatValue() + tasksInOrder.get(1).getTurnaroundTime().floatValue()
										+ tasksInOrder.get(2).getTurnaroundTime().floatValue() + tasksInOrder.get(3).getTurnaroundTime().floatValue()) / 4.000f);
								
								avgTurnaroundTimeOutputField.setText(averageTurnAroundTime.toString());
								
								averageResponseTime = ((tasksInOrder.get(0).getResponseTime().floatValue() + tasksInOrder.get(1).getResponseTime().floatValue()
										+ tasksInOrder.get(2).getResponseTime().floatValue() + tasksInOrder.get(3).getResponseTime().floatValue()) / 4.000f);
								
								avgResponseTimeOutputField.setText(averageResponseTime.toString());
						}
						else 
						{
							tasksInOrder.get(0).setArrivalTime((Integer) inputTableModel.getValueAt(0, 0)); //start times
							tasksInOrder.get(1).setArrivalTime((Integer) inputTableModel.getValueAt(1, 0));
							tasksInOrder.get(2).setArrivalTime((Integer) inputTableModel.getValueAt(2, 0));
							tasksInOrder.get(3).setArrivalTime((Integer) inputTableModel.getValueAt(3, 0));
							tasksInOrder.get(4).setArrivalTime((Integer) inputTableModel.getValueAt(4, 0));
							
							tasksInOrder.get(0).setTaskDuration((Integer) inputTableModel.getValueAt(0, 1)); //durations
							tasksInOrder.get(1).setTaskDuration((Integer) inputTableModel.getValueAt(1, 1));
							tasksInOrder.get(2).setTaskDuration((Integer) inputTableModel.getValueAt(2, 1));
							tasksInOrder.get(3).setTaskDuration((Integer) inputTableModel.getValueAt(3, 1));
							tasksInOrder.get(4).setTaskDuration((Integer) inputTableModel.getValueAt(4, 1));
							
							tasksInOrder.get(0).setCounter(tasksInOrder.get(0).getTaskDuration()); //counters
							tasksInOrder.get(1).setCounter(tasksInOrder.get(1).getTaskDuration());
							tasksInOrder.get(2).setCounter(tasksInOrder.get(2).getTaskDuration());
							tasksInOrder.get(3).setCounter(tasksInOrder.get(3).getTaskDuration());
							tasksInOrder.get(4).setCounter(tasksInOrder.get(4).getTaskDuration());
							
							if(fifoOrSJFMode == true)
							{
								if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() != (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() != (int) tasksInOrder.get(4).getArrivalTime()) //If only 1 = 2...
								{
									sort(0, 1);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() != (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() != (int) tasksInOrder.get(4).getArrivalTime()) //If 1 = 2 = 3, but 3 != 4 != 5...
								{
									sort(0, 2);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() != (int) tasksInOrder.get(4).getArrivalTime()) //If 1 = 2 = 3 = 4, but 4 != 5...
								{
									sort(0, 3);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() == (int) tasksInOrder.get(4).getArrivalTime()) //If 1 = 2 = 3 = 4 = 5...
								{
									sort(0, 4);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() != (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() == (int) tasksInOrder.get(4).getArrivalTime()) //If only 4 = 5...
								{
									sort(3, 4);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() == (int) tasksInOrder.get(4).getArrivalTime()) //If 1 != 2 != 3, but 3 = 4 = 5...
								{
									sort(2, 4);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() == (int) tasksInOrder.get(4).getArrivalTime()) //If 1 != 2, but 2 = 3 = 4 = 5...
								{
									sort(1, 4);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() != (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() != (int) tasksInOrder.get(4).getArrivalTime()) //If 1 != 2, 2 = 3, 3 != 4 != 5...
								{
									sort(1, 2);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() != (int) tasksInOrder.get(4).getArrivalTime()) //If 1 != 2 != 3, 3 = 4, but 4 != 5...
								{
									sort(2, 3);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() != (int) tasksInOrder.get(4).getArrivalTime()) //If 1 != 2, 2 = 3 = 4, but 4 != 5...
								{
									sort(1, 3);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() == (int) tasksInOrder.get(4).getArrivalTime()) //If 1 = 2 and 3 = 4 = 5...
								{
									sort(0, 1);
									sort(2, 4);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() == (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() != (int) tasksInOrder.get(4).getArrivalTime()) //If 1 = 2 and 3 = 4, but 4 != 5...
								{
									sort(0, 1);
									sort(2, 3);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() != (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() == (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() != (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() == (int) tasksInOrder.get(4).getArrivalTime()) //If 2 = 3 and 4 = 5...
								{
									sort(1, 2);
									sort(3, 4);
								}
								else if((int) tasksInOrder.get(0).getArrivalTime() == (int) tasksInOrder.get(1).getArrivalTime()
										&& (int) tasksInOrder.get(1).getArrivalTime() != (int) tasksInOrder.get(2).getArrivalTime()
										&& (int) tasksInOrder.get(2).getArrivalTime() != (int) tasksInOrder.get(3).getArrivalTime()
										&& (int) tasksInOrder.get(3).getArrivalTime() == (int) tasksInOrder.get(4).getArrivalTime()) //If 1 = 2 and 4 = 5...
								{
									sort(0, 1);
									sort(3, 4);
								}
								else
								{
									JOptionPane.showMessageDialog(frmFifo, "WHAAAA? You created a case that the author did not forsee?! Impossible!", "ERROR: AUTHOR MADE AN OOPSIE", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
									
									if (tasksInOrder.get(0).getArrivalTime() > 1000 || tasksInOrder.get(1).getArrivalTime() > 1000
											|| (tasksInOrder.get(0).getTaskDuration() + tasksInOrder.get(1).getTaskDuration()
													+ tasksInOrder.get(2).getTaskDuration() + tasksInOrder.get(3).getTaskDuration()
													+ tasksInOrder.get(4).getTaskDuration()) >= 1000)
									{
										JOptionPane.showMessageDialog(frmFifo, "I'm sorry, but I have set the graph capacity to 1000 entries to remove the risk of overload\nPlease make your numbers less than 1000.", "ERROR: INPUT TOO LARGE", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(0).getArrivalTime() < 0 || tasksInOrder.get(0).getTaskDuration() < 0
											|| tasksInOrder.get(1).getArrivalTime() < 0 || tasksInOrder.get(1).getTaskDuration() < 0
											|| tasksInOrder.get(2).getArrivalTime() < 0 || tasksInOrder.get(2).getArrivalTime() < 0
											|| tasksInOrder.get(3).getArrivalTime() < 0 || tasksInOrder.get(3).getArrivalTime() < 0
											|| tasksInOrder.get(4).getArrivalTime() < 0 || tasksInOrder.get(4).getArrivalTime() < 0)
									{
										JOptionPane.showMessageDialog(frmFifo, "No item in the input table may be less than zero.\n\"Why\", you ask? Well, That doesn't really make any sense :\\ ", "ERROR", JOptionPane.ERROR_MESSAGE);
										resetSim();
										return;
									}
									if (tasksInOrder.get(0).getTaskDuration() == 0 || tasksInOrder.get(1).getTaskDuration() == 0 || tasksInOrder.get(2).getTaskDuration() == 0 || tasksInOrder.get(3).getTaskDuration() == 0 || tasksInOrder.get(4).getTaskDuration() == 0)
									{
										JOptionPane.showMessageDialog(frmFifo, "No task may have a length of zero.\nHow am I supposed to simulate nothing? :\\", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(1).getArrivalTime() < tasksInOrder.get(0).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(1).getCharacter() + " cannot be before Task " + tasksInOrder.get(0).getCharacter() + ".\nThe order of the tasks must be A,B,C,D,E.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(2).getArrivalTime() < tasksInOrder.get(1).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(2).getCharacter() + " cannot be before Task " + tasksInOrder.get(1).getCharacter() + ".\nThe order of the tasks must be A,B,C,D,E.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(2).getArrivalTime() < tasksInOrder.get(0).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(2).getCharacter() + " cannot be before Task " + tasksInOrder.get(0).getCharacter() + ".\nThe order of the tasks must be A,B,C,D,E.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(3).getArrivalTime() < tasksInOrder.get(0).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(3).getCharacter() + " cannot be before Task " + tasksInOrder.get(0).getCharacter() + ".\nThe order of the tasks must be A,B,C,D,E.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(3).getArrivalTime() < tasksInOrder.get(1).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(3).getCharacter() + " cannot be before Task " + tasksInOrder.get(1).getCharacter() + ".\nThe order of the tasks must be A,B,C,D,E.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(3).getArrivalTime() < tasksInOrder.get(2).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(3).getCharacter() + " cannot be before Task " + tasksInOrder.get(2).getCharacter() + ".\nThe order of the tasks must be A,B,C,D,E.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(4).getArrivalTime() < tasksInOrder.get(0).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(4).getCharacter() + " cannot be before Task " + tasksInOrder.get(0).getCharacter() + ".\nThe order of the tasks must be A,B,C,D,E.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(4).getArrivalTime() < tasksInOrder.get(1).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(4).getCharacter() + " cannot be before Task " + tasksInOrder.get(1).getCharacter() + ".\nThe order of the tasks must be A,B,C,D,E.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(4).getArrivalTime() < tasksInOrder.get(2).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(4).getCharacter() + " cannot be before Task " + tasksInOrder.get(2).getCharacter() + ".\nThe order of the tasks must be A,B,C,D,E.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									if (tasksInOrder.get(4).getArrivalTime() < tasksInOrder.get(3).getArrivalTime())
									{
										JOptionPane.showMessageDialog(frmFifo, "The arrival time for Task " + tasksInOrder.get(4).getCharacter() + " cannot be before Task " + tasksInOrder.get(3).getCharacter() + ".\nThe order of the tasks must be A,B,C,D,E.", "ERROR", JOptionPane.ERROR_MESSAGE);
										return;
									}
									
									if (tasksInOrder.get(0).getArrivalTime() > 0)
									{
										for (int i = 0; i < tasksInOrder.get(0).getArrivalTime(); i++)
										{
											chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null}); //Stall
											tasksInOrder.get(0).setExitTime(tasksInOrder.get(0).getExitTime() + 1);
											tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
											tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
											tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
											tasksInOrder.get(4).setExitTime(tasksInOrder.get(4).getExitTime() + 1);
										}
									}
									while ((tasksInOrder.get(0).getCounter() > 0 || tasksInOrder.get(1).getCounter() > 0 || tasksInOrder.get(2).getCounter() > 0
											|| tasksInOrder.get(3).getCounter() > 0 || tasksInOrder.get(4).getCounter() > 0) && chartTableModel.getRowCount() <= 1000)
										//Make sure that the size of the table never exceeds 1000; 
									{
										while (tasksInOrder.get(0).getCounter() > 0) //Task 1
										{
											if(cycleNumber > tasksInOrder.get(1).getArrivalTime() && cycleNumber > tasksInOrder.get(2).getArrivalTime()
													&& cycleNumber <= tasksInOrder.get(3).getArrivalTime() && cycleNumber <= tasksInOrder.get(4).getArrivalTime())
												//If Task 1 is overlapping Task 2 and Task 3, but not Task 4 and Task 5...
											{
												tasksInOrder.get(1).setStartTime(tasksInOrder.get(1).getStartTime() + 1);
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
												
											}
											else if (cycleNumber > tasksInOrder.get(1).getArrivalTime() && cycleNumber > tasksInOrder.get(2).getArrivalTime()
													&& cycleNumber > tasksInOrder.get(3).getArrivalTime() && cycleNumber > tasksInOrder.get(4).getArrivalTime())
												//If Task 1 is overlapping Task 2, 3, 4, AND 5...
											{
												tasksInOrder.get(1).setStartTime(tasksInOrder.get(1).getStartTime() + 1);
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
												tasksInOrder.get(3).setStartTime(tasksInOrder.get(3).getStartTime() + 1);
												tasksInOrder.get(4).setStartTime(tasksInOrder.get(4).getStartTime() + 1);
											}
											else if (cycleNumber > tasksInOrder.get(1).getArrivalTime() && cycleNumber > tasksInOrder.get(2).getArrivalTime()
													&& cycleNumber > tasksInOrder.get(3).getArrivalTime() && cycleNumber <= tasksInOrder.get(4).getArrivalTime())
												//If Task 1 is overlapping Task 2, 3, and 4, but not Task 5...
											{
												tasksInOrder.get(1).setStartTime(tasksInOrder.get(1).getStartTime() + 1);
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
												tasksInOrder.get(3).setStartTime(tasksInOrder.get(3).getStartTime() + 1);
											}
											else if (cycleNumber > tasksInOrder.get(1).getArrivalTime() && cycleNumber <= tasksInOrder.get(2).getArrivalTime()
													&& cycleNumber <= tasksInOrder.get(3).getArrivalTime() && cycleNumber <= tasksInOrder.get(4).getArrivalTime())
												//If Task 1 is overlapping ONLY Task 2...
											{
												tasksInOrder.get(1).setStartTime(tasksInOrder.get(1).getStartTime() + 1);
											}
											else //If none of these cases are occurring...
											{
												//carry on
											}
										createRow(0);
										tasksInOrder.get(0).setExitTime(tasksInOrder.get(0).getExitTime() + 1);
										tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
										tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
										tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
										tasksInOrder.get(4).setExitTime(tasksInOrder.get(4).getExitTime() + 1);
										tasksInOrder.get(0).setCounter(tasksInOrder.get(0).getCounter() - 1); //decrement Task 1's counter
										}
										tasksInOrder.get(0).setFinished(true);
									
										if (cycleNumber < tasksInOrder.get(1).getArrivalTime()) //If the next task wasn't supposed to start this soon...
										{
											while(cycleNumber < tasksInOrder.get(1).getArrivalTime())
											{
												chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null});
												tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
												tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
												tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
												tasksInOrder.get(4).setExitTime(tasksInOrder.get(4).getExitTime() + 1);
											}
										}
									
										while (tasksInOrder.get(1).getCounter() > 0) //Task 2
										{
											if(cycleNumber > tasksInOrder.get(2).getArrivalTime() && cycleNumber > tasksInOrder.get(3).getArrivalTime()
													&& cycleNumber <= tasksInOrder.get(4).getArrivalTime()) //If Task 2 is overlapping Task 3 and Task 4, but not Task 5...
											{
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
												tasksInOrder.get(3).setStartTime(tasksInOrder.get(3).getStartTime() + 1);
											}
											else if(cycleNumber > tasksInOrder.get(2).getArrivalTime() && cycleNumber > tasksInOrder.get(3).getArrivalTime()
													&& cycleNumber > tasksInOrder.get(4).getArrivalTime()) //If Task 2 is overlapping Task 3, 4, AND 5...
											{
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
												tasksInOrder.get(3).setStartTime(tasksInOrder.get(3).getStartTime() + 1);
												tasksInOrder.get(4).setStartTime(tasksInOrder.get(4).getStartTime() + 1);
											}
											else if(cycleNumber > tasksInOrder.get(2).getArrivalTime() && cycleNumber <= tasksInOrder.get(3).getArrivalTime()
													&& cycleNumber <= tasksInOrder.get(4).getArrivalTime()) //If Task 2 is overlapping ONLY Task 3...
											{
												tasksInOrder.get(2).setStartTime(tasksInOrder.get(2).getStartTime() + 1);
											}
											else //If none of the above is occurring...
											{
												//Carry on
											}
										createRow(1);
										tasksInOrder.get(1).setExitTime(tasksInOrder.get(1).getExitTime() + 1);
										tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
										tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
										tasksInOrder.get(4).setExitTime(tasksInOrder.get(4).getExitTime() + 1);
										tasksInOrder.get(1).setCounter(tasksInOrder.get(1).getCounter() - 1); //decrement Task 2's counter
										}
										tasksInOrder.get(1).setFinished(true);
									
										if (cycleNumber < tasksInOrder.get(2).getArrivalTime()) //If the next task wasn't supposed to start this soon...
										{
											while(cycleNumber < tasksInOrder.get(2).getArrivalTime())
											{
												chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null});
												tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
												tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
												tasksInOrder.get(4).setExitTime(tasksInOrder.get(4).getExitTime() + 1);
											}
										}
									
										while (tasksInOrder.get(2).getCounter() > 0) //Task 3
										{
											if (cycleNumber > tasksInOrder.get(3).getArrivalTime() && cycleNumber <= tasksInOrder.get(4).getArrivalTime())
												//If Task 3 is overlapping Task 4, but not Task 5...
											{
												tasksInOrder.get(3).setStartTime(tasksInOrder.get(3).getStartTime() + 1);
											}
											else if(cycleNumber > tasksInOrder.get(3).getArrivalTime() && cycleNumber > tasksInOrder.get(4).getArrivalTime())
												//If Task 3 is overlapping Task 4 AND Task 5...
											{
												tasksInOrder.get(3).setStartTime(tasksInOrder.get(3).getStartTime() + 1);
												tasksInOrder.get(4).setStartTime(tasksInOrder.get(4).getStartTime() + 1);
											}
											else //If neither is occurring...
											{
												//Carry on
											}
										createRow(2);
										tasksInOrder.get(2).setExitTime(tasksInOrder.get(2).getExitTime() + 1);
										tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
										tasksInOrder.get(4).setExitTime(tasksInOrder.get(4).getExitTime() + 1);
										tasksInOrder.get(2).setCounter(tasksInOrder.get(2).getCounter() - 1); //decrement Task 3's counter
									}
									tasksInOrder.get(2).setFinished(true);
									
										if (cycleNumber < tasksInOrder.get(3).getArrivalTime()) //If the next task wasn't supposed to start this soon...
										{
											while(cycleNumber < tasksInOrder.get(3).getArrivalTime())
											{
												chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null});
												tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
												tasksInOrder.get(4).setExitTime(tasksInOrder.get(4).getExitTime() + 1);
											}
										}
									
									while (tasksInOrder.get(3).getCounter() > 0) //Task 4
									{
											if (cycleNumber > tasksInOrder.get(4).getArrivalTime()) //If Task 4 is overlapping Task 5...
											{
												tasksInOrder.get(4).setStartTime(tasksInOrder.get(4).getStartTime() + 1);
											}
										createRow(3);
										tasksInOrder.get(3).setExitTime(tasksInOrder.get(3).getExitTime() + 1);
										tasksInOrder.get(4).setExitTime(tasksInOrder.get(4).getExitTime() + 1);
										tasksInOrder.get(3).setCounter(tasksInOrder.get(3).getCounter() - 1); //decrement Task 4's counter
									}
									tasksInOrder.get(3).setFinished(true);
									
										if (cycleNumber < tasksInOrder.get(4).getArrivalTime()) //If the next task wasn't supposed to start this soon...
										{
											while(cycleNumber < tasksInOrder.get(4).getArrivalTime())
											{
												chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, null});
												tasksInOrder.get(4).setExitTime(tasksInOrder.get(4).getExitTime() + 1);
											}
										}
									
									while (tasksInOrder.get(4).getCounter() > 0) //Task 5
									{
										createRow(4);
										tasksInOrder.get(4).setExitTime(tasksInOrder.get(4).getExitTime() + 1);
										tasksInOrder.get(4).setCounter(tasksInOrder.get(4).getCounter() - 1); //decrement Task 5's counter
									}
									tasksInOrder.get(4).setFinished(true);
								}
								tasksInOrder.get(0).setResponseTime(tasksInOrder.get(0).getStartTime());
								tasksInOrder.get(1).setResponseTime(tasksInOrder.get(1).getStartTime() + 1);
								tasksInOrder.get(2).setResponseTime(tasksInOrder.get(2).getStartTime() + 1);
								tasksInOrder.get(3).setResponseTime(tasksInOrder.get(3).getStartTime() + 1);
								tasksInOrder.get(4).setResponseTime(tasksInOrder.get(4).getStartTime() + 1);
								
								tasksInOrder.get(0).setTurnaroundTime((tasksInOrder.get(0).getExitTime() - tasksInOrder.get(0).getArrivalTime()));
								tasksInOrder.get(1).setTurnaroundTime((tasksInOrder.get(1).getExitTime() - tasksInOrder.get(1).getArrivalTime()));
								tasksInOrder.get(2).setTurnaroundTime((tasksInOrder.get(2).getExitTime() - tasksInOrder.get(2).getArrivalTime()));
								tasksInOrder.get(3).setTurnaroundTime((tasksInOrder.get(3).getExitTime() - tasksInOrder.get(3).getArrivalTime()));
								tasksInOrder.get(4).setTurnaroundTime((tasksInOrder.get(4).getExitTime() - tasksInOrder.get(4).getArrivalTime()));
									
								if(chartTableModel.getRowCount() >= 1000)
								{
									JOptionPane.showMessageDialog(frmFifo, "I'm sorry, but I have set the graph capacity to 1000 entries to remove the risk of overload\nPlease make your numbers a bit smaller.", "ERROR: OVER CAPACITY", JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								outputTableModel.addRow(new Object[] {tasksInOrder.get(0).getCharacter(),tasksInOrder.get(0).getTurnaroundTime(),
										tasksInOrder.get(0).getResponseTime()});
								outputTableModel.addRow(new Object[] {tasksInOrder.get(1).getCharacter(),tasksInOrder.get(1).getTurnaroundTime(),
										tasksInOrder.get(1).getResponseTime()});
								outputTableModel.addRow(new Object[] {tasksInOrder.get(2).getCharacter(),tasksInOrder.get(2).getTurnaroundTime(),
										tasksInOrder.get(2).getResponseTime()});
								outputTableModel.addRow(new Object[] {tasksInOrder.get(3).getCharacter(),tasksInOrder.get(3).getTurnaroundTime(),
										tasksInOrder.get(3).getResponseTime()});
								outputTableModel.addRow(new Object[] {tasksInOrder.get(4).getCharacter(),tasksInOrder.get(4).getTurnaroundTime(),
										tasksInOrder.get(4).getResponseTime()});
								
								averageTurnAroundTime = ((tasksInOrder.get(0).getTurnaroundTime().floatValue() + tasksInOrder.get(1).getTurnaroundTime().floatValue()
										+ tasksInOrder.get(2).getTurnaroundTime().floatValue() + tasksInOrder.get(3).getTurnaroundTime().floatValue()
										+ tasksInOrder.get(4).getTurnaroundTime().floatValue()) / 5.000f);
								
								avgTurnaroundTimeOutputField.setText(averageTurnAroundTime.toString());
								
								averageResponseTime = ((tasksInOrder.get(0).getResponseTime().floatValue() + tasksInOrder.get(1).getResponseTime().floatValue()
										+ tasksInOrder.get(2).getResponseTime().floatValue() + tasksInOrder.get(3).getResponseTime().floatValue()
										+ tasksInOrder.get(4).getResponseTime().floatValue()) / 5.000f);
								
								avgResponseTimeOutputField.setText(averageResponseTime.toString());
							}
						inputTable.setEnabled(false);
						btnGenerate.setEnabled(false);
					}
				
				catch (Exception nullPointerException)
				{
					JOptionPane.showMessageDialog(frmFifo, "No item in the input table may be null.\nI can't help you if you don't give me what I need :(", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnGenerate.setBackground(Color.GREEN);
		btnGenerate.setBounds(305, 245, 116, 21);
		btnGenerate.setEnabled(false);
		simPanel.add(btnGenerate);
		
		lblNewLabel_2 = new JLabel("-->");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(431, 247, 57, 13);
		simPanel.add(lblNewLabel_2);
		
		JTextArea txtrFifofirstIn = new JTextArea();
		txtrFifofirstIn.setEditable(false);
		txtrFifofirstIn.setBackground(SystemColor.activeCaptionBorder);
		txtrFifofirstIn.setFont(new Font("Yu Gothic", Font.ITALIC, 15));
		txtrFifofirstIn.setLineWrap(true);
		txtrFifofirstIn.setWrapStyleWord(true);
		txtrFifofirstIn.setText("     FIFO (First In, First Out) is the CPU scheduling policy that handles the case where two or more tasks \"overlap\" or are trying to run at the same time. FIFO makes it so that the task with the lowest arrival time is the first of that group of tasks to be executed, followed by the appropriate task with the second-lowest arrival time, and so on. FIFO is a pretty straightforward policy, but things get very problematic when the first task is colossal in task duration (The convoy effect increases the turnaround time to obscene levels).\r\n     SJF (Shortest Job First) solves this issue (at least for the most part). As the name suggests, SJF doesn't consider arrival time but instead task length. The task in the relevant group of tasks with the shortest duration gets to be executed first. Of course, even if the shortest task is executed first, that doesn't mean that it can't be a colossal size. So while it greatly avoids that issue, it is definitely not immune. Another advantage of SJF is that it does better at response time. But keep in mind that the convoy effect can make the response time suffer as well.");
		txtrFifofirstIn.setBounds(848, 10, 358, 574);
		simPanel.add(txtrFifofirstIn);
		
		scrollPane_2 = new JScrollPane(outputTable);
		scrollPane_2.setBounds(30, 346, 385, 152);
		simPanel.add(scrollPane_2);
		
		outputTable = new JTable();
		outputTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		outputTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		outputTable.setCellSelectionEnabled(true);
		outputTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task", "Turnaround Time", "Response Time"
			}
				) {
			Class[] columnTypes = new Class[] {
				Character.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		outputTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputTable.setFillsViewportHeight(true);
		outputTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		outputTable.getColumnModel().getColumn(0).setMinWidth(70);
		outputTable.getColumnModel().getColumn(0).setMaxWidth(70);
		outputTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		outputTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		outputTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		outputTable.setRowHeight(25);
		outputTable.getColumnModel().getColumn(1).setPreferredWidth(160);
		outputTable.getColumnModel().getColumn(1).setMinWidth(160);
		outputTable.getColumnModel().getColumn(1).setMaxWidth(160);
		outputTable.getTableHeader().setDefaultRenderer(centerRenderer);
		outputTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		scrollPane_2.setViewportView(outputTable);
		outputTableModel = (DefaultTableModel) outputTable.getModel();
		
		JLabel lblNewLabel_3 = new JLabel("Calculations:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(30, 307, 164, 29);
		simPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Avg Turnaround:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3_1.setBounds(30, 508, 179, 29);
		simPanel.add(lblNewLabel_3_1);
		
		avgTurnaroundTimeOutputField = new JTextField();
		avgTurnaroundTimeOutputField.setBackground(Color.WHITE);
		avgTurnaroundTimeOutputField.setEditable(false);
		avgTurnaroundTimeOutputField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		avgTurnaroundTimeOutputField.setBounds(210, 508, 146, 26);
		simPanel.add(avgTurnaroundTimeOutputField);
		avgTurnaroundTimeOutputField.setColumns(10);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("cycles");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3_1_1.setBounds(366, 508, 66, 29);
		simPanel.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("Avg Response:");
		lblNewLabel_3_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3_1_2.setBounds(30, 547, 179, 29);
		simPanel.add(lblNewLabel_3_1_2);
		
		avgResponseTimeOutputField = new JTextField();
		avgResponseTimeOutputField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		avgResponseTimeOutputField.setEditable(false);
		avgResponseTimeOutputField.setColumns(10);
		avgResponseTimeOutputField.setBackground(Color.WHITE);
		avgResponseTimeOutputField.setBounds(210, 547, 146, 26);
		simPanel.add(avgResponseTimeOutputField);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("cycles");
		lblNewLabel_3_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3_1_1_1.setBounds(366, 547, 66, 29);
		simPanel.add(lblNewLabel_3_1_1_1);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				resetSim();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnReset.setForeground(Color.RED);
		btnReset.setBackground(UIManager.getColor("Button.background"));
		btnReset.setBounds(315, 276, 99, 21);
		btnReset.setEnabled(false);
		simPanel.add(btnReset);
		
		JLabel lblPressTabTo = new JLabel("Press TAB to make the last");
		lblPressTabTo.setBounds(304, 143, 164, 28);
		simPanel.add(lblPressTabTo);
		
		lblNewLabel_4 = new JLabel("table cell count.");
		lblNewLabel_4.setBounds(304, 160, 116, 21);
		simPanel.add(lblNewLabel_4);
		
		startbtnpanel = new Panel();
		layeredPane.setLayer(startbtnpanel, 3);
		startbtnpanel.setBounds(541, 484, 150, 75);
		layeredPane.add(startbtnpanel);
		startbtnpanel.setLayout(null);
		
		btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				btnStart.setEnabled(false);
				layeredPane.setLayer(startbtnpanel, 0);
				layeredPane.setLayer(startMenu, -1);
				btnSetPolicy.setEnabled(true);
				comboBoxPolicy.setEnabled(true);
			}
		});
		btnStart.setBounds(25, 12, 100, 50);
		startbtnpanel.add(btnStart);
		btnStart.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnStart.setEnabled(true);
		
		startMenu = new JPanel();
		startMenu.setBounds(10, 10, 1232, 594);
		layeredPane.add(startMenu);
		layeredPane.setLayer(startMenu, 2);
		startMenu.setLayout(null);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 1232, 594);
		lblNewLabel_1.setIcon(new ImageIcon(Fifo21.class.getResource("/fifo21-start-screen.png")));
		startMenu.add(lblNewLabel_1);
	}
	
	private void resetSim()
	{
			if (chartTableModel.getRowCount() > 0)
			{
				for (int i = chartTableModel.getRowCount() - 1; i >= 0; i--)
				{
					chartTableModel.removeRow(i);
				}
			
			}
			if (inputTableModel.getRowCount() > 0)
			{
					for (int i = inputTableModel.getRowCount() - 1; i >= 0; i--)
					{
						inputTableModel.removeRow(i);
					}
				inputTable.setEnabled(false);
			}
			if (taskTableModel.getRowCount() > 0)
			{
				for (int i = taskTableModel.getRowCount() - 1; i >= 0; i--)
				{
					taskTableModel.removeRow(i);
				}
			}
			if (outputTableModel.getRowCount() > 0)
			{
				for (int i = outputTableModel.getRowCount() - 1; i >= 0; i--)
				{
					outputTableModel.removeRow(i);
				}
			}
		btnSetNumTasks.setEnabled(false);
		btnGenerate.setEnabled(false);
		btnReset.setEnabled(false);
		comboBox.setSelectedIndex(0);
		comboBoxPolicy.setSelectedIndex(0);
		avgResponseTimeOutputField.setText(null);
		avgTurnaroundTimeOutputField.setText(null);
		cycleNumber = 0;
			for (int i = 0; i < tasksInOrder.size(); i++)
			{
				tasksInOrder.get(i).setExitTime(0);
				tasksInOrder.get(i).setStartTime(0);
				tasksInOrder.get(i).setArrivalTime(0);
				tasksInOrder.get(i).setResponseTime(0);
				tasksInOrder.get(i).setFinished(false);
				tasksInOrder.get(i).setExitTime(0);
				tasksInOrder.get(i).setCharacter((Character) listTaskNames.charAt(i));
			}
	}
	
	private void createRow(Integer order)
	{
		if(tasksInOrder.get(order).getCharacter() == 'A')
		{
			chartTableModel.addRow(new Object[] {cycleNumber++, 'A', null, null, null, null});
		}
		else if(tasksInOrder.get(order).getCharacter() == 'B')
		{
			chartTableModel.addRow(new Object[] {cycleNumber++, null, 'B', null, null, null});
		}
		else if(tasksInOrder.get(order).getCharacter() == 'C')
		{
			chartTableModel.addRow(new Object[] {cycleNumber++, null, null, 'C', null, null});
		}
		else if(tasksInOrder.get(order).getCharacter() == 'D')
		{
			chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, 'D', null});
		}
		else 
		{
			chartTableModel.addRow(new Object[] {cycleNumber++, null, null, null, null, 'E'});
		}
	}
	
	private void sort(Integer start, Integer end)
	{
		task temp = new task(0, 0, 0, 0, 'A', 0, 0);
			for (int i = start; i < end; i++) 
			{
				for (int j = i + 1; j < end + 1; j++)
				{ 
						if (tasksInOrder.get(i).getTaskDuration() > tasksInOrder.get(j).getTaskDuration()) 
						{
							temp = tasksInOrder.get(i);
							tasksInOrder.set(i, tasksInOrder.get(j));
							tasksInOrder.set(j, temp);
						}
				}
			}	
	}
}
		
